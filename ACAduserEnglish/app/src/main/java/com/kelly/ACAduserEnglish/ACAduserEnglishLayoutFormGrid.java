package com.kelly.ACAduserEnglish;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class ACAduserEnglishLayoutFormGrid extends ViewGroup {
    private int formColCount = 4;
    private int gridHorGrap = 10;
    private int gridVerGrap = 5;
    private int marginValue = 0;
    private int mode = 0;
    private int rowHeight = 48;

    public ACAduserEnglishLayoutFormGrid(Context context) {
        super(context);
        EditText txtField = new EditText(context);
        txtField.setText("Height");
        txtField.measure(View.MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        this.rowHeight = txtField.getMeasuredHeight();
    }

    public void setFormColumnCount(int formColCount2) {
        if (formColCount2 <= 0) {
            formColCount2 = 4;
        }
        this.formColCount = formColCount2;
    }

    public void setMode(int mode2) {
        this.mode = mode2;
    }

    public void setHorVerGrap(int gridHorGrap2, int gridVerGrap2) {
        this.gridHorGrap = gridHorGrap2;
        this.gridVerGrap = gridVerGrap2;
    }

    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int specWidth = View.MeasureSpec.getSize(widthMeasureSpec);
        int specHeight = View.MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = View.MeasureSpec.getMode(heightMeasureSpec);
        int count = getChildCount();
        if (heightMode != 1073741824) {
            int maxRowCount = 0;
            for (int i = 0; i < count; i++) {
                LayoutParams params = (LayoutParams) getChildAt(i).getLayoutParams();
                if (params.rowCount + params.rowIndex > maxRowCount) {
                    maxRowCount = params.rowCount + params.rowIndex;
                }
            }
            specHeight = ((this.rowHeight + this.gridVerGrap) * maxRowCount) + this.marginValue + this.marginValue;
        }
        setMeasuredDimension(specWidth, specHeight);
        int colWidth = ((specWidth - this.marginValue) - this.marginValue) / this.formColCount;
        for (int i2 = 0; i2 < count; i2++) {
            View child = getChildAt(i2);
            LayoutParams params2 = (LayoutParams) child.getLayoutParams();
            child.measure(View.MeasureSpec.makeMeasureSpec((params2.colCount * colWidth) - this.gridHorGrap, widthMode), View.MeasureSpec.makeMeasureSpec((params2.rowCount * (this.rowHeight + this.gridVerGrap)) - this.gridVerGrap, heightMode));
        }
    }

    /* access modifiers changed from: protected */
    @SuppressLint("WrongConstant")
    public void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int count = getChildCount();
        int colWidth = (((right - left) - this.marginValue) - this.marginValue) / this.formColCount;
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != 8) {
                LayoutParams lp = (LayoutParams) child.getLayoutParams();
                int childTop = this.marginValue + (lp.rowIndex * (this.rowHeight + this.gridVerGrap)) + (this.gridVerGrap / 2);
                int childBottom = ((lp.rowCount * (this.rowHeight + this.gridVerGrap)) + childTop) - this.gridVerGrap;
                if (lp.colIndex > 200 || lp.colIndex <= 100) {
                    if (lp.colIndex <= 300 && lp.colIndex > 200) {
                        int childLeft = this.marginValue + ((lp.colIndex - 200) * colWidth) + (((1 - ((lp.colIndex - 200) % 2)) * colWidth) / 2) + (this.gridHorGrap / 2);
                        child.layout(childLeft, childTop, (((lp.colCount * colWidth) + childLeft) - (colWidth / 2)) - (this.gridHorGrap / 2), childBottom);
                    } else if (lp.colIndex > 300) {
                        int childLeft2 = this.marginValue + ((lp.colIndex - 300) * colWidth) + (colWidth / 2) + (this.gridHorGrap / 2);
                        child.layout(childLeft2, childTop, ((lp.colCount * colWidth) + childLeft2) - (this.gridHorGrap / 2), childBottom);
                    } else {
                        int childLeft3 = this.marginValue + (lp.colIndex * colWidth) + (this.gridHorGrap / 2);
                        child.layout(childLeft3, childTop, ((lp.colCount * colWidth) + childLeft3) - (this.gridHorGrap / 2), childBottom);
                    }
                } else if (lp.colIndex == 103) {
                    int childLeft4 = this.marginValue + ((((lp.colIndex - 102) * this.formColCount) / 2) * colWidth) + (this.gridHorGrap / 2);
                    child.layout(childLeft4, childTop, (lp.colCount + childLeft4) - (this.gridHorGrap / 2), childBottom);
                } else {
                    int childLeft5 = this.marginValue + ((((lp.colIndex - 100) * this.formColCount) / 3) * colWidth) + (this.gridHorGrap / 2);
                    child.layout(childLeft5, childTop, (lp.colCount + childLeft5) - (this.gridHorGrap / 2), childBottom);
                }
            }
        }
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public int colCount;
        public int colIndex;
        public int rowCount;
        public int rowIndex;

        public LayoutParams(int colIndex2, int rowIndex2, int colCount2, int rowCount2) {
            super(-2, -2);
            this.colIndex = colIndex2;
            this.rowIndex = rowIndex2;
            this.colCount = colCount2;
            this.rowCount = rowCount2;
        }
    }

    public static class LayoutParams2 extends ViewGroup.MarginLayoutParams {
        public int colCount;
        public int colIndex;
        public int rowCount;
        public int rowIndex;

        public LayoutParams2(int colIndex2, int rowIndex2, int colCount2, int rowCount2) {
            super(-2, -2);
            this.colIndex = colIndex2;
            this.rowIndex = rowIndex2;
            this.colCount = colCount2;
            this.rowCount = rowCount2;
        }
    }
}
