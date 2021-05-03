package com.kelly.ACAduserEnglish;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;

public class ACAduserEnglishDialogMessage extends Handler {
    protected String btnTxt = "OK";
    protected Context context;
    protected String message;
    protected String title;

    public ACAduserEnglishDialogMessage(Context context2, String message2) {
        this.message = message2;
        this.title = "Message";
        this.context = context2;
    }

    public void setButtonText(String btnTxt2) {
        this.btnTxt = btnTxt2;
    }

    public void handleMessage(Message msg) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this.context);
        dialogBuilder.setTitle(this.title);
        dialogBuilder.setMessage(this.message);
        dialogBuilder.setPositiveButton(this.btnTxt, new DialogInterface.OnClickListener() {
            /* class com.kelly.ACAduserEnglish.ACAduserEnglishDialogMessage.AnonymousClass1 */

            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });
        Dialog dialog = dialogBuilder.create();
        dialog.setCancelable(false);
        dialog.show();
    }
}
