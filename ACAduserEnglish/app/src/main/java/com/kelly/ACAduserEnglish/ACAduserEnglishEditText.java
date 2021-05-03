package com.kelly.ACAduserEnglish;

import android.content.Context;
//import android.support.v4.view.ViewCompat;
import android.text.InputFilter;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.EditText;

import androidx.core.view.ViewCompat;

public class ACAduserEnglishEditText extends androidx.appcompat.widget.AppCompatEditText {
    private boolean sign;
    private EditText txtView;
    private String val;

    public ACAduserEnglishEditText(Context content, boolean sign2, String val2) {
        super(content);
        this.sign = sign2;
        this.val = val2;
        setGravity(3);
        setEnabled(this.sign);
        setBackgroundColor(-4210753);
        if (sign2) {
            setTextColor(View.MEASURED_STATE_MASK);
            setKeyListener(new DigitsKeyListener(false, true));
            setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)});
        } else {
            setTextColor(View.MEASURED_STATE_MASK);
        }
        setText(this.val);
        setFocusableInTouchMode(true);
    }

    public int getEditValue() {
        String txtStr = this.txtView.getText().toString().trim();
        if (txtStr.matches("\\d{1,}")) {
            return Integer.parseInt(txtStr);
        }
        return 0;
    }
}
