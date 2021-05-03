package com.kelly.ACAduserEnglish;

import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

public class ACAduserEnglishDeviceKerry {
    public static final int KELLY_DEV_PRODUCT = 24577;
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private static final int STATE_CONNECTED = 3;
    private static final int STATE_NONE = 0;
    public static final int baudRate = 19200;
    public static final int dataBits = 8;
    private static ACAduserEnglishDeviceKerry instance = new ACAduserEnglishDeviceKerry();
    public static final int parity = 0;
    public static final int stopBits = 1;
    public int COMType = -1;
    public byte ETS_RX_BYTES = 0;
    public byte ETS_RX_CMD = 0;
    public byte[] ETS_RX_DATA = new byte[16];
    private boolean ETS_Rx_Error = false;
    private boolean ETS_Rx_Status = false;
    public byte ETS_TX_BYTES = 0;
    public byte ETS_TX_CMD = 0;
    public byte[] ETS_TX_DATA = new byte[16];
    private int Timeout = 0;
    private boolean bReadTheadEnable = false;
    private int bluetooth_mState = 0;
    private UsbDeviceConnection conn;
    private UsbEndpoint endPointIn;
    private UsbEndpoint endPointOut;
    private UsbInterface intf;
    public StringBuffer logTxtSb = new StringBuffer();
    private BluetoothAdapter mAdapter;
    private BluetoothDevice mmDevice;
    private InputStream mmInStream;
    private OutputStream mmOutStream;
    public BluetoothSocket mmSocket;
    private int readIndex;
    public byte[] readInfo = new byte[32];
    private int readTimeOut = 45;
    private int sendTimeOut = 45;
    private byte[] tempInfo = new byte[32];
    private long[] time = new long[32];

    public static ACAduserEnglishDeviceKerry getInstance() {
        return instance;
    }

    private ACAduserEnglishDeviceKerry() {
    }

    public void resetLogBuffer() {
        this.logTxtSb.delete(0, this.logTxtSb.length());
    }

    public String getLogBufferInfo() {
        String logTxt = this.logTxtSb.toString();
        resetLogBuffer();
        return logTxt;
    }

    public StringBuffer getlogTxtSb() {
        return this.logTxtSb;
    }

    public void closeReadThead() {
        this.bReadTheadEnable = false;
    }

    public boolean openUsbComDevice(Context context) {
        UsbManager usbManager = (UsbManager) context.getSystemService(Context.USB_SERVICE);
        UsbDevice usbComDev = null;
        Map usbDevMap = usbManager.getDeviceList();
        Iterator keyIte = usbDevMap.keySet().iterator();
        while (true) {
            if (keyIte.hasNext()) {
                UsbDevice usbDev = (UsbDevice) usbDevMap.get((String) keyIte.next());
                if (usbManager.hasPermission(usbDev)) {
                    if (usbDev.getProductId() == 24577) {
                        usbComDev = usbDev;
                        break;
                    }
                } else {
                    usbManager.requestPermission(usbDev, PendingIntent.getBroadcast(context, 6902, new Intent(), PendingIntent.FLAG_ONE_SHOT));
                    this.logTxtSb.append("no permission to access device\n");
                    return false;
                }
            } else {
                break;
            }
        }
        if (usbComDev == null) {
            this.logTxtSb.append("not find usb device\n");
            return false;
        }
        this.conn = usbManager.openDevice(usbComDev);
        new ACAduserEnglishUsbDevice().configUsbDeviceParamter(this.conn, baudRate, 8, 1, 0);
        this.intf = usbComDev.getInterface(0);
        this.conn.claimInterface(this.intf, true);
        this.endPointIn = this.intf.getEndpoint(0);
        this.endPointOut = this.intf.getEndpoint(1);
        this.logTxtSb.append("open device success\n");
        return true;
    }

    public boolean openBluetoothComDevice(String Mac_address) {
        this.mAdapter = BluetoothAdapter.getDefaultAdapter();
        this.mmDevice = this.mAdapter.getRemoteDevice(Mac_address);
        this.mAdapter.cancelDiscovery();
        try {
            this.mmSocket = this.mmDevice.createRfcommSocketToServiceRecord(MY_UUID);
        } catch (IOException e) {
        }
        try {
            this.mmSocket.connect();
            try {
                this.mmInStream = this.mmSocket.getInputStream();
                this.mmOutStream = this.mmSocket.getOutputStream();
            } catch (IOException e2) {
            }
            if (this.mmSocket.isConnected()) {
                this.bluetooth_mState = 3;
            }
            return true;
        } catch (IOException e3) {
            try {
                this.mmSocket.close();
            } catch (IOException e4) {
            }
            return false;
        }
    }

    public int sendcmd() {
        byte[] sendtmp = new byte[(this.ETS_TX_BYTES + 3)];
        sendtmp[0] = this.ETS_TX_CMD;
        sendtmp[1] = this.ETS_TX_BYTES;
        if (this.ETS_TX_BYTES == 0) {
            sendtmp[this.ETS_TX_BYTES + 2] = this.ETS_TX_CMD;
        } else {
            for (int i = 0; i < this.ETS_TX_BYTES; i++) {
                sendtmp[i + 2] = this.ETS_TX_DATA[i];
            }
            sendtmp[this.ETS_TX_BYTES + 2] = calculateCheckNumber(sendtmp, 0, this.ETS_TX_BYTES + 2);
        }
        ACAduserEnglishByteUtil.printByteArrayHex(sendtmp, 0, sendtmp.length);
        if (this.COMType == 1) {
            try {
                if (this.bluetooth_mState != 3) {
                    return 0;
                }
                write(sendtmp);
            } catch (Exception e) {
            }
        } else {
            try {
                if (this.conn == null || this.conn.bulkTransfer(this.endPointOut, sendtmp, sendtmp.length, 100) <= 0) {
                    return 0;
                }
            } catch (Exception e2) {
            }
        }
        return 1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0044  */
    public int readcmd() {
        if (this.COMType == 1) {
            this.Timeout = 300;
        } else {
            this.Timeout = 100;
        }
        this.time[0] = System.currentTimeMillis();
        while (System.currentTimeMillis() - this.time[0] < ((long) this.Timeout) && !this.ETS_Rx_Status) {
            while (System.currentTimeMillis() - this.time[0] < ((long) this.Timeout)) {
                while (System.currentTimeMillis() - this.time[0] < ((long) this.Timeout)) {
                }
            }
        }
        if (this.ETS_Rx_Status) {
            this.ETS_Rx_Status = false;
            byte[] buffer = new byte[(this.readInfo[1] + 3)];
            System.arraycopy(this.readInfo, 0, buffer, 0, buffer.length);
            if (buffer[0] != this.ETS_TX_CMD) {
                return 2;
            }
            if (this.ETS_Rx_Error) {
                this.ETS_Rx_Error = false;
                return 3;
            }
            ACAduserEnglishByteUtil.printByteArrayHex(buffer, 0, buffer.length);
            this.ETS_RX_CMD = this.readInfo[0];
            this.ETS_RX_BYTES = this.readInfo[1];
            if (this.ETS_RX_BYTES <= 0) {
                return 1;
            }
            for (int i = 0; i < this.ETS_RX_BYTES; i++) {
                this.ETS_RX_DATA[i] = this.readInfo[i + 2];
            }
            return 1;
        }
        this.readIndex = 0;
        this.readInfo[1] = 0;
        return 0;
    }

    public void write(byte[] buffer) {
        try {
            this.mmOutStream.write(buffer);
        } catch (IOException e) {
        }
    }

    public void closeUsbComDevice() {
        try {
            if (this.conn != null) {
                this.conn.releaseInterface(this.intf);
                this.conn.close();
                this.conn = null;
            }
        } catch (Exception e) {
        }
    }

    public void closeBluetoothComDevice() {
        try {
            if (this.mmSocket != null) {
                this.mmSocket.close();
                this.mmSocket = null;
                this.bluetooth_mState = 0;
            }
        } catch (IOException e) {
        }
    }

    public static byte calculateCheckNumber(byte[] cmdInfo, int offset, int length) {
        int readDataSum = 0;
        int endreadIndex = offset + length;
        for (int i = offset; i < endreadIndex; i++) {
            readDataSum += cmdInfo[i];
        }
        return (byte) readDataSum;
    }

    public void ReadThread() {
        if (this.COMType == 1) {
            new BluetoothReadThread().start();
        } else {
            new ReadThread().start();
        }
    }

    public class ReadThread extends Thread {
        public ReadThread() {
        }

        public void run() {
            ACAduserEnglishDeviceKerry.this.readIndex = 0;
            ACAduserEnglishDeviceKerry.this.ETS_Rx_Error = false;
            ACAduserEnglishDeviceKerry.this.bReadTheadEnable = true;
            while (ACAduserEnglishDeviceKerry.this.bReadTheadEnable) {
                if (ACAduserEnglishDeviceKerry.this.conn != null) {
                    try {
                        int readcount = ACAduserEnglishDeviceKerry.this.conn.bulkTransfer(ACAduserEnglishDeviceKerry.this.endPointIn, ACAduserEnglishDeviceKerry.this.tempInfo, 32, 100);
                        if (readcount > 2) {
                            System.arraycopy(ACAduserEnglishDeviceKerry.this.tempInfo, 2, ACAduserEnglishDeviceKerry.this.readInfo, ACAduserEnglishDeviceKerry.this.readIndex, readcount - 2);
                            ACAduserEnglishDeviceKerry.this.readIndex = (ACAduserEnglishDeviceKerry.this.readIndex + readcount) - 2;
                        }
                        if (ACAduserEnglishDeviceKerry.this.readInfo[1] > 16) {
                            ACAduserEnglishDeviceKerry.this.readInfo[1] = 16;
                        }
                        if (ACAduserEnglishDeviceKerry.this.readIndex >= ACAduserEnglishDeviceKerry.this.readInfo[1] + 3 && ACAduserEnglishDeviceKerry.calculateCheckNumber(ACAduserEnglishDeviceKerry.this.readInfo, 0, ACAduserEnglishDeviceKerry.this.readInfo[1] + 2) == ACAduserEnglishDeviceKerry.this.readInfo[ACAduserEnglishDeviceKerry.this.readInfo[1] + 2]) {
                            ACAduserEnglishDeviceKerry.this.ETS_Rx_Status = true;
                            ACAduserEnglishDeviceKerry.this.readIndex = 0;
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }
    }

    public class BluetoothReadThread extends Thread {
        public BluetoothReadThread() {
        }

        public void run() {
            ACAduserEnglishDeviceKerry.this.readIndex = 0;
            ACAduserEnglishDeviceKerry.this.ETS_Rx_Error = false;
            ACAduserEnglishDeviceKerry.this.bReadTheadEnable = true;
            while (ACAduserEnglishDeviceKerry.this.bReadTheadEnable) {
                if (ACAduserEnglishDeviceKerry.this.bluetooth_mState == 3) {
                    try {
                        int readcount = ACAduserEnglishDeviceKerry.this.mmInStream.read(ACAduserEnglishDeviceKerry.this.tempInfo);
                        if (readcount > 0) {
                            for (int i = 0; i < readcount; i++) {
                                ACAduserEnglishDeviceKerry.this.readInfo[ACAduserEnglishDeviceKerry.this.readIndex] = ACAduserEnglishDeviceKerry.this.tempInfo[i];
                                ACAduserEnglishDeviceKerry.this.readIndex++;
                            }
                        }
                        if (ACAduserEnglishDeviceKerry.this.readInfo[1] > 16) {
                            ACAduserEnglishDeviceKerry.this.readInfo[1] = 16;
                        }
                        if (ACAduserEnglishDeviceKerry.this.readIndex >= ACAduserEnglishDeviceKerry.this.readInfo[1] + 3 && ACAduserEnglishDeviceKerry.calculateCheckNumber(ACAduserEnglishDeviceKerry.this.readInfo, 0, ACAduserEnglishDeviceKerry.this.readInfo[1] + 2) == ACAduserEnglishDeviceKerry.this.readInfo[ACAduserEnglishDeviceKerry.this.readInfo[1] + 2]) {
                            ACAduserEnglishDeviceKerry.this.ETS_Rx_Status = true;
                            ACAduserEnglishDeviceKerry.this.readIndex = 0;
                        }
                    } catch (IOException e) {
                    }
                }
            }
        }
    }
}
