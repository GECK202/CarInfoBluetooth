package com.kelly.ACAduserEnglish;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;

public class ACAduserEnglishExitConfirm extends Handler {
    private Activity context;
    protected String noBtnTxt = "Отмена";
    protected String okBtnTxt = "OK";

    public ACAduserEnglishExitConfirm(Activity context2) {
        this.context = context2;
    }

    public void handleMessage(Message msg) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this.context);
        dialogBuilder.setTitle("Подтверждение");
        dialogBuilder.setMessage("Хотите выйти?");
        dialogBuilder.setPositiveButton(this.okBtnTxt, new DialogInterface.OnClickListener() {
            /* class com.kelly.ACAduserEnglish.ACAduserEnglishExitConfirm.AnonymousClass1 */

            public void onClick(DialogInterface dialog, int whichButton) {
                if (BluetoothAdapter.getDefaultAdapter() != null) {
                    BluetoothAdapter.getDefaultAdapter().disable();
                    ACAduserEnglishDeviceKerry.getInstance().closeBluetoothComDevice();
                }
                ACAduserEnglishDeviceKerry.getInstance().closeUsbComDevice();
                ACAduserEnglishAppManager.getInstance().exitAndroidActivity();
            }
        });
        dialogBuilder.setNegativeButton(this.noBtnTxt, new DialogInterface.OnClickListener() {
            /* class com.kelly.ACAduserEnglish.ACAduserEnglishExitConfirm.AnonymousClass2 */

            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });
        Dialog dialog = dialogBuilder.create();
        dialog.setCancelable(false);
        dialog.show();
    }
}
