package com.kelly.ACAduserEnglish;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.widget.TextView;

public class MainActivity extends Activity {
    private static final int REQUEST_CODE = 1;
    private int Bluetooth_Enable = 1;
    private int MotorNotRun = 0;
    private int mSingleChoiceID = 0;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Предупреждение!");
        dialogBuilder.setMessage("Предупреждение:Запрещено выполнять какие-либо настройки в пользовательской программе во время работы двигателя.\r\n\r\n Нажмите 'Да' для продолжения если мотор не вращается.\r\n Нажмите 'Нет' если мотор вращается. Запустите программу заново, когда мотор будет остановлен.");
        dialogBuilder.setPositiveButton("Нет", new DialogInterface.OnClickListener() {
            /* class com.kelly.ACAduserEnglish.MainActivity.AnonymousClass1 */

            public void onClick(DialogInterface dialog, int whichButton) {
                MainActivity.this.MotorNotRun = 0;
                if (BluetoothAdapter.getDefaultAdapter() != null) {
                    BluetoothAdapter.getDefaultAdapter().disable();
                    ACAduserEnglishDeviceKerry.getInstance().closeBluetoothComDevice();
                }
                ACAduserEnglishDeviceKerry.getInstance().closeUsbComDevice();
                ACAduserEnglishAppManager.getInstance().exitAndroidActivity();
            }
        });
        dialogBuilder.setNegativeButton("Да", new DialogInterface.OnClickListener() {
            /* class com.kelly.ACAduserEnglish.MainActivity.AnonymousClass2 */

            public void onClick(DialogInterface dialog, int whichButton) {
                MainActivity.this.MotorNotRun = 1;
                MainActivity.this.showitem();
            }
        });
        Dialog dialog = dialogBuilder.create();
        dialog.setCancelable(false);
        dialog.show();
        TextView txtView = new TextView(this);
        txtView.setBackgroundResource(R.drawable.mybackimg_hor);
        setContentView(txtView);
        ACAduserEnglishAppManager.getInstance().add(this);
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode != 4) {
            return true;
        }
        new ACAduserEnglishExitConfirm(this).sendEmptyMessage(0);
        return true;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void showitem() {
        if (BluetoothAdapter.getDefaultAdapter() == null || this.Bluetooth_Enable == 0) {
            new Handler().postDelayed(new Thread() {
                /* class com.kelly.ACAduserEnglish.MainActivity.AnonymousClass3 */

                public void run() {
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, ACAduserEnglishKellyPage.class);
                    intent.putExtra("comtype", "0");
                    MainActivity.this.startActivityForResult(intent, 1);
                }
            }, 800);
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Выберите тип COM порта:");
        builder.setSingleChoiceItems(new String[]{"FT232 COM", "Bluetooth COM"}, 0, new DialogInterface.OnClickListener() {
            /* class com.kelly.ACAduserEnglish.MainActivity.AnonymousClass4 */

            public void onClick(DialogInterface dialog, int whichButton) {
                MainActivity.this.mSingleChoiceID = whichButton;
            }
        });
        builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            /* class com.kelly.ACAduserEnglish.MainActivity.AnonymousClass5 */

            public void onClick(DialogInterface dialog, int whichButton) {
                new Handler().postDelayed(new Thread() {
                    /* class com.kelly.ACAduserEnglish.MainActivity.AnonymousClass5.AnonymousClass1 */

                    public void run() {
                        Intent intent = new Intent();
                        intent.setClass(MainActivity.this, ACAduserEnglishKellyPage.class);
                        intent.putExtra("comtype", new StringBuilder(String.valueOf(MainActivity.this.mSingleChoiceID)).toString());
                        MainActivity.this.startActivityForResult(intent, 1);
                    }
                }, 100);
            }
        });
        builder.setCancelable(false);
        builder.create().show();
    }
}
