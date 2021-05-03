package com.kelly.ACAduserEnglish;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
//import android.support.v4.internal.view.SupportMenu;
//import android.support.v4.view.MotionEventCompat;
//import android.support.v4.view.PagerTabStrip;
//import android.support.v4.view.ViewCompat;
//import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.core.internal.view.SupportMenu;
import androidx.core.view.MotionEventCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.PagerTabStrip;
import androidx.viewpager.widget.ViewPager;

import com.kelly.ACAduserEnglish.ACAduserEnglishLayoutFormGrid;
import java.lang.reflect.Array;
import java.util.List;
import java.util.Set;

public class ACAduserEnglishKellyPage extends Activity {
    static final int CAL_DATA_FLASH_ADDRESS = 6144;
    static final int CODE_END_ADDRESS = 131072;
    static final int CODE_START_ADDRESS = 8192;
    static final int DATA_BUFF_SIZE = 2048;
    static final int DATA_END_ADDRESS = 34816;
    static final int DATA_START_ADDRESS = 32768;
    private static int[] DataValue = new int[512];
    static final byte ETS_BURNT_CHECKSUM = -77;
    static final byte ETS_BURNT_FLASH = -78;
    static final byte ETS_BURNT_RESET = -76;
    static final byte ETS_CHECK_IDENTIFY_STATUS = 68;
    static final byte ETS_CODE_END_ADDRESS = -75;
    static final byte ETS_CODE_VERSION = 17;
    static final byte ETS_ENTRY_IDENTIFY = 67;
    static final byte ETS_ERASE_FLASH = -79;
    static final byte ETS_GET_HALL_SEQUENCE = 78;
    static final byte ETS_GET_PHASE_I_AD = 53;
    static final byte ETS_GET_PMSM_PARM = 75;
    static final byte ETS_GET_RESOLVER_INIT_ANGLE = 77;
    static final byte ETS_GET_SEED = -31;
    static final byte ETS_QUIT_IDENTIFY = 66;
    static final byte ETS_VALIDATE_SEED = -30;
    static final byte ETS_WRITE_PMSM_PARM = 76;
    static final int FLASH_BUFF_SIZE = 196608;
    static final int FLASH_END_ADDRESS = 262144;
    static final int FLASH_START_ADDRESS = 65536;
    static final int KACI = 2;
    static final int KBLS = 1;
    static final int MCU_MCF51AC = 2;
    static final int MCU_MPC560P = 1;
    static final int height_value = 600;
    static final int text_value = 200;
    static final int width_value = 850;
    private boolean BootloaderMode = false;
    private boolean BootloaderRunning = false;
    public int COMType = -1;
    private TextView[] Cal_View_Array = new TextView[12];
    private int Cal_page = 0;
    private String[][] Calibration_Value_Array;
    private TextView[] Calibration_View_Array;
    private Button Change_Cal;
    private CheckBox[] CheckBox_Array = new CheckBox[16];
    private Button ComStatus;
    private boolean Com_Status = false;
    private boolean DEBUG = false;
    private float EditText_height = 0.0f;
    private float EditText_size = 0.0f;
    final int En = 8;
    private Button Entry_Identify;
    int Hall_Idf_Str_doing_flag = 0;
    int Hall_Idf_Str_finish_flag = 0;
    private boolean IdentifyDEBUG = false;
    private RelativeLayout IdentifyGrid;
    private boolean IdentifyShow = false;
    int Identify_Entry = 0;
    int Identify_Flag = 0;
    private Button Identify_Hall;
    int Identify_Quit = 0;
    private Button Identify_Resolver;
    private boolean Identify_Show_En = false;
    int Identify_Status = 0;
    private TextView[] Identify_View_Array = new TextView[12];
    private ACAduserEnglishEditText Identifyceshi;
    private ACAduserEnglishLayoutFormGrid IdentifyformGrid;
    private String Mac_address = "";
    private RelativeLayout MainGrid;
    private int[] MonitorDataValue = new int[50];
    private int[] MonitorDataValueBackup = new int[50];
    private RelativeLayout MonitorGrid;
    private boolean Monitor_Read_Enable = true;
    private EditText[] Monitor_View_Array = new EditText[43];
    private ACAduserEnglishLayoutFormGrid MonitorformGrid;
    private String[] MonitorshowStr = new String[48];
    private Button Quit_Identify;
    private boolean RW_Debug = false;
    private boolean ReadThreadStart = false;
    private Button ReadZeroCurrent;
    private Button Read_Cal;
    private TextView[] Readzero_View_Array = new TextView[7];
    private TextView[] Readzero_View_Array2 = new TextView[7];
    private ACAduserEnglishLayoutFormGrid ReadzeroformGrid;
    int Resolver_Idf_Str_doing_flag = 0;
    int Resolver_Idf_Str_finish_flag = 0;
    private int Screen_height = 0;
    private int Screen_width = 0;
    private ScrollView ScrollGrid;
    private ScrollView ScrollIdentifyGrid;
    private ScrollView ScrollMonitorGrid;
    final int Size_small = 16;
    private int Soft_Ver = 0;
    private float Textsize = 0.0f;
    private TextView[] Tips_View_Array = new TextView[3];
    private Button Write_Cal;
    private ACAduserEnglishPagerAdapter adapter;
    private ACAduserEnglishLayoutFormGrid bottomView;
    private ACAduserEnglishEditText ceshi;
    private Button closeDevice;
    private final int col1 = 5;
    private final int col2 = 3;
    private final int colume = 24;
    private ACAduserEnglishDeviceKerry deviceKelly = ACAduserEnglishDeviceKerry.getInstance();
    private String[] device_name;
    private ACAduserEnglishLayoutFormGrid formGrid;
    private Handler handler;
    private ACAduserEnglishLayoutFormGrid headView;
    private int isCAN = 0;
    private int isIpad = 0;
    private Button lastpagebtn;
    private StringBuffer logTxtSb = new StringBuffer();
    private String[] mac_address;
    final int name = 4;
    private Button nextpagebtn;
    final int offset = 0;
    private Button openDevice;
    private ViewPager pager;
    final int pos = 2;
    final int pro = 1;
    final int rangH = 6;
    final int rangL = 5;
    private Button readBtn;
    private AlertDialog.Builder readzerodlg;
    private Button sendBtn;
    final int show = 7;
    final int sw = 3;
    private int tabIndex = 0;
    int test = 0;
    final int tips = 9;
    private Button tipsText;
    private View viewid;
    private int[] zeroint = new int[5];

    private void resetLogBuffer() {
        this.logTxtSb.delete(0, this.logTxtSb.length());
    }

    private String getLogBufferInfo() {
        String logTxt = this.logTxtSb.toString();
        resetLogBuffer();
        return logTxt;
    }

    public void ShowKellypageCalibration() {
        this.tipsText = new Button(this);
        this.tipsText.setText("TIPS:");
        this.tipsText.setGravity(19);
        this.tipsText.setTextColor(View.MEASURED_STATE_MASK);
        this.tipsText.setTextSize(15.0f);
        this.tipsText.setBackgroundColor(-4210753);
        this.headView.addView(this.tipsText, new ACAduserEnglishLayoutFormGrid.LayoutParams(0, 0, 20, 1));
        this.Cal_page = 0;
        this.Soft_Ver = 0;
        this.lastpagebtn = new Button(this);
        this.lastpagebtn.setText("<-");
        this.lastpagebtn.setTextSize(0, this.EditText_size - 2.0f);
        this.lastpagebtn.setEnabled(false);
        this.lastpagebtn.setTextColor(-7829368);
        this.lastpagebtn.setOnClickListener(new View.OnClickListener() {
            /* class com.kelly.ACAduserEnglish.ACAduserEnglishKellyPage.AnonymousClass1 */

            public void onClick(View arg0) {
                ACAduserEnglishKellyPage.this.formGrid.post(new Thread() {
                    /* class com.kelly.ACAduserEnglish.ACAduserEnglishKellyPage.AnonymousClass1.AnonymousClass1 */

                    public void run() {
                        if (ACAduserEnglishKellyPage.this.Cal_page > 0 && ACAduserEnglishKellyPage.this.UpdataValue() == 1) {
                            if (ACAduserEnglishKellyPage.this.Cal_page == 2) {
                                ACAduserEnglishKellyPage.this.nextpagebtn.setEnabled(true);
                                ACAduserEnglishKellyPage.this.nextpagebtn.setTextColor(View.MEASURED_STATE_MASK);
                            }
                            if (ACAduserEnglishKellyPage.this.Cal_page == 1) {
                                ACAduserEnglishKellyPage.this.lastpagebtn.setEnabled(false);
                                ACAduserEnglishKellyPage.this.lastpagebtn.setTextColor(-7829368);
                            }
                            ACAduserEnglishKellyPage aCAduserEnglishKellyPage = ACAduserEnglishKellyPage.this;
                            aCAduserEnglishKellyPage.Cal_page--;
                            ACAduserEnglishKellyPage.this.formGrid.removeAllViews();
                            ACAduserEnglishKellyPage.this.ShowKellypageCalpage();
                        }
                    }
                });
            }
        });
        this.headView.addView(this.lastpagebtn, new ACAduserEnglishLayoutFormGrid.LayoutParams(20, 0, 2, 1));
        this.nextpagebtn = new Button(this);
        this.nextpagebtn.setText("->");
        this.nextpagebtn.setTextSize(0, this.EditText_size - 2.0f);
        this.nextpagebtn.setTextColor(View.MEASURED_STATE_MASK);
        this.nextpagebtn.setOnClickListener(new View.OnClickListener() {
            /* class com.kelly.ACAduserEnglish.ACAduserEnglishKellyPage.AnonymousClass2 */

            public void onClick(View arg0) {
                ACAduserEnglishKellyPage.this.formGrid.post(new Thread() {
                    /* class com.kelly.ACAduserEnglish.ACAduserEnglishKellyPage.AnonymousClass2.AnonymousClass1 */

                    public void run() {
                        if (ACAduserEnglishKellyPage.this.Cal_page < 2 && ACAduserEnglishKellyPage.this.UpdataValue() == 1) {
                            ACAduserEnglishKellyPage.this.Cal_page++;
                            if (ACAduserEnglishKellyPage.this.Cal_page == 2) {
                                ACAduserEnglishKellyPage.this.nextpagebtn.setEnabled(false);
                                ACAduserEnglishKellyPage.this.nextpagebtn.setTextColor(-7829368);
                            } else if (ACAduserEnglishKellyPage.this.Cal_page == 1) {
                                ACAduserEnglishKellyPage.this.lastpagebtn.setEnabled(true);
                                ACAduserEnglishKellyPage.this.lastpagebtn.setTextColor(View.MEASURED_STATE_MASK);
                            }
                            ACAduserEnglishKellyPage.this.formGrid.removeAllViews();
                            ACAduserEnglishKellyPage.this.ShowKellypageCalpage();
                        }
                    }
                });
            }
        });
        this.headView.addView(this.nextpagebtn, new ACAduserEnglishLayoutFormGrid.LayoutParams(22, 0, 2, 1));
        String Temp_asc = ACAduserEnglishByteUtil.printIntArrayString(DataValue, 0, 2, 7, "a");
        ACAduserEnglishByteUtil.printIntArrayString(DataValue, 16, 2, 3, "h");
        int Soft_Version2 = (DataValue[16] << 8) + DataValue[17];
        int ControlType1 = DataValue[84];
        int ControlType2 = DataValue[85];
        if (ControlType2 != 75 || (ControlType2 == 75 && ControlType1 == 54)) {
            this.isCAN = 1;
        } else {
            this.isCAN = 0;
        }
        String Name = Temp_asc.substring(1, 4);
        String NewName = Temp_asc.substring(1, 3);
        if ((Name != null && Name.equals("BLS")) || ((NewName != null && NewName.equals("LS")) || (Name != null && Name.equals("BSS")))) {
            this.Soft_Ver = 1;
            if (Soft_Version2 >= 265) {
                this.Identify_Show_En = false;
                this.Calibration_Value_Array = (String[][]) Array.newInstance(String.class, ACAduserEnglishSetting.Calibration_Value_Array_KBLS0109.length, 10);
                for (int i = 0; i < this.Calibration_Value_Array.length; i++) {
                    for (int k = 0; k < 10; k++) {
                        this.Calibration_Value_Array[i][k] = ACAduserEnglishSetting.Calibration_Value_Array_KBLS0109[i][k];
                    }
                }
            } else if (Soft_Version2 >= 262) {
                this.Identify_Show_En = false;
                this.Calibration_Value_Array = (String[][]) Array.newInstance(String.class, ACAduserEnglishSetting.Calibration_Value_Array_KBLS0106.length, 10);
                for (int i2 = 0; i2 < this.Calibration_Value_Array.length; i2++) {
                    for (int k2 = 0; k2 < 10; k2++) {
                        this.Calibration_Value_Array[i2][k2] = ACAduserEnglishSetting.Calibration_Value_Array_KBLS0106[i2][k2];
                    }
                }
            } else if (Soft_Version2 >= 261) {
                this.Identify_Show_En = false;
                this.Calibration_Value_Array = (String[][]) Array.newInstance(String.class, ACAduserEnglishSetting.Calibration_Value_Array_KBLS0105.length, 10);
                for (int i3 = 0; i3 < this.Calibration_Value_Array.length; i3++) {
                    for (int k3 = 0; k3 < 10; k3++) {
                        this.Calibration_Value_Array[i3][k3] = ACAduserEnglishSetting.Calibration_Value_Array_KBLS0105[i3][k3];
                    }
                }
            } else if (Soft_Version2 >= 260) {
                this.Identify_Show_En = false;
                this.Calibration_Value_Array = (String[][]) Array.newInstance(String.class, ACAduserEnglishSetting.Calibration_Value_Array_KBLS0104.length, 10);
                for (int i4 = 0; i4 < this.Calibration_Value_Array.length; i4++) {
                    for (int k4 = 0; k4 < 10; k4++) {
                        this.Calibration_Value_Array[i4][k4] = ACAduserEnglishSetting.Calibration_Value_Array_KBLS0104[i4][k4];
                    }
                }
            } else if (Soft_Version2 >= 258) {
                this.Identify_Show_En = true;
                this.Calibration_Value_Array = (String[][]) Array.newInstance(String.class, ACAduserEnglishSetting.Calibration_Value_Array_KBLS0102.length, 10);
                for (int i5 = 0; i5 < this.Calibration_Value_Array.length; i5++) {
                    for (int k5 = 0; k5 < 10; k5++) {
                        this.Calibration_Value_Array[i5][k5] = ACAduserEnglishSetting.Calibration_Value_Array_KBLS0102[i5][k5];
                    }
                }
            } else if (Soft_Version2 >= 257) {
                this.Identify_Show_En = true;
                this.Calibration_Value_Array = (String[][]) Array.newInstance(String.class, ACAduserEnglishSetting.Calibration_Value_Array_KBLS0101.length, 10);
                for (int i6 = 0; i6 < this.Calibration_Value_Array.length; i6++) {
                    for (int k6 = 0; k6 < 10; k6++) {
                        this.Calibration_Value_Array[i6][k6] = ACAduserEnglishSetting.Calibration_Value_Array_KBLS0101[i6][k6];
                    }
                }
            } else {
                showLogInfo("Имя модуля или версия приложения не определено!");
                return;
            }
        } else if ((Name == null || !Name.equals("ACS")) && (NewName == null || !NewName.equals("PS"))) {
            if ((Name == null || !Name.equals("ACI")) && (Name == null || Name.equals("ACS") || NewName == null || !NewName.equals("AC"))) {
                showLogInfo("Невозможно идентифицировать имя модуля!");
                return;
            }
            this.Identify_Show_En = false;
            this.Soft_Ver = 2;
            if (Soft_Version2 >= 259) {
                this.Calibration_Value_Array = (String[][]) Array.newInstance(String.class, ACAduserEnglishSetting2.Calibration_Value_Array_KACI0103.length, 10);
                for (int i7 = 0; i7 < this.Calibration_Value_Array.length; i7++) {
                    for (int k7 = 0; k7 < 10; k7++) {
                        this.Calibration_Value_Array[i7][k7] = ACAduserEnglishSetting2.Calibration_Value_Array_KACI0103[i7][k7];
                    }
                }
            } else if (Soft_Version2 >= 258) {
                this.Calibration_Value_Array = (String[][]) Array.newInstance(String.class, ACAduserEnglishSetting2.Calibration_Value_Array_KACI0102.length, 10);
                for (int i8 = 0; i8 < this.Calibration_Value_Array.length; i8++) {
                    for (int k8 = 0; k8 < 10; k8++) {
                        this.Calibration_Value_Array[i8][k8] = ACAduserEnglishSetting2.Calibration_Value_Array_KACI0102[i8][k8];
                    }
                }
            } else if (Soft_Version2 >= 257) {
                this.Calibration_Value_Array = (String[][]) Array.newInstance(String.class, ACAduserEnglishSetting2.Calibration_Value_Array_KACI0101.length, 10);
                for (int i9 = 0; i9 < this.Calibration_Value_Array.length; i9++) {
                    for (int k9 = 0; k9 < 10; k9++) {
                        this.Calibration_Value_Array[i9][k9] = ACAduserEnglishSetting2.Calibration_Value_Array_KACI0101[i9][k9];
                    }
                }
            } else {
                showLogInfo("Module Name or Software Version not match!");
                return;
            }
        } else if (Soft_Version2 != 257) {
            showLogInfo("Module Name or Software Version not match!");
            return;
        }
        if (!this.IdentifyShow && this.Identify_Show_En) {
            this.adapter.addSubView(this.IdentifyGrid, "AC Identify");
            this.pager.setAdapter(this.adapter);
            this.IdentifyShow = true;
        }
        this.Calibration_View_Array = new TextView[this.Calibration_Value_Array.length];
        ShowKellypageCalpage();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    @SuppressLint("ResourceType")
    private void ShowKellypageCalpage() {
        int line2;
        int j = 0;
        int l = 0;
        int btn_count = 0;
        int textview_count = 0;
        for (int i = 0; i < this.Calibration_Value_Array.length; i++) {
            if (this.isIpad == 1) {
                if (Integer.parseInt(this.Calibration_Value_Array[i][7]) == 1 && Integer.parseInt(this.Calibration_Value_Array[i][1]) > 0 && Integer.parseInt(this.Calibration_Value_Array[i][0]) >= this.Cal_page * 128 && Integer.parseInt(this.Calibration_Value_Array[i][0]) < (this.Cal_page + 1) * 128) {
                    textview_count++;
                }
            } else if (Integer.parseInt(this.Calibration_Value_Array[i][7]) == 1 && Integer.parseInt(this.Calibration_Value_Array[i][1]) >= 0 && Integer.parseInt(this.Calibration_Value_Array[i][0]) >= this.Cal_page * 128 && Integer.parseInt(this.Calibration_Value_Array[i][0]) < (this.Cal_page + 1) * 128) {
                textview_count++;
            }
        }
        int line_count = textview_count % 2 == 0 ? textview_count / 2 : (textview_count / 2) + 1;
        for (int i2 = 0; i2 < this.Calibration_Value_Array.length; i2++) {
            boolean sign = true;
            if (Integer.parseInt(this.Calibration_Value_Array[i2][7]) == 1 && Integer.parseInt(this.Calibration_Value_Array[i2][0]) >= this.Cal_page * 128 && Integer.parseInt(this.Calibration_Value_Array[i2][0]) < (this.Cal_page + 1) * 128) {
                l++;
                String result = ACAduserEnglishByteUtil.printIntArrayString(DataValue, Integer.parseInt(this.Calibration_Value_Array[i2][0]), Integer.parseInt(this.Calibration_Value_Array[i2][1]), Integer.parseInt(this.Calibration_Value_Array[i2][2]), this.Calibration_Value_Array[i2][3]);
                if (Integer.parseInt(this.Calibration_Value_Array[i2][8]) == 1) {
                    sign = true;
                } else if (Integer.parseInt(this.Calibration_Value_Array[i2][8]) == 0) {
                    sign = false;
                }
                if (Integer.parseInt(this.Calibration_Value_Array[i2][1]) > 0) {
                    j++;
                    if (Integer.parseInt(this.Calibration_Value_Array[i2][0]) == 25 || Integer.parseInt(this.Calibration_Value_Array[i2][0]) == 27) {
                        String Voltstr = ACAduserEnglishByteUtil.printIntArrayString(DataValue, 3, 2, 1, "a");
                        if (Integer.parseInt(Voltstr) >= 24 && Integer.parseInt(Voltstr) <= 144 && Integer.parseInt(Voltstr) % 12 == 0) {
                            result = new StringBuilder(String.valueOf(Integer.parseInt(result))).toString();
                        }
                    }
                    this.Calibration_View_Array[i2] = new ACAduserEnglishEditText(this, sign, result);
                    this.Calibration_View_Array[i2].setTextSize(15.0f);
                    if (this.isIpad != 1) {
                        addFormGridView(this.Calibration_Value_Array[i2][4], this.Calibration_View_Array[i2], ((l - 1) / line_count) * 12, (l - (((l - 1) / line_count) * line_count)) - 1);
                    } else if (this.Cal_page == 0) {
                        addFormGridView(this.Calibration_Value_Array[i2][4], this.Calibration_View_Array[i2], ((j - 1) / line_count) * 8, (j - (((j - 1) / line_count) * line_count)) - 1);
                    } else {
                        addFormGridView(this.Calibration_Value_Array[i2][4], this.Calibration_View_Array[i2], ACAduserEnglishByteUtil.get_Index2(textview_count, j, 0) * 8, ACAduserEnglishByteUtil.get_Index2(textview_count, j, 1));
                    }
                    if (Integer.parseInt(this.Calibration_Value_Array[i2][8]) == 1) {
                        this.Calibration_View_Array[i2].setBackground(getViewRectangleBorderDrawable(-5592406, -1));
                    } else {
                        this.Calibration_View_Array[i2].setTextColor(-4210753);
                        this.Calibration_View_Array[i2].setBackground(getViewRectangleBorderDrawable(-5592406, -2894893));
                    }
                    this.Calibration_View_Array[i2].setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        /* class com.kelly.ACAduserEnglish.ACAduserEnglishKellyPage.AnonymousClass3 */

                        public void onFocusChange(View view, boolean hasFocus) {
                            int max;
                            int min;
                            if (hasFocus) {
                                for (int i = 0; i < ACAduserEnglishKellyPage.this.Calibration_Value_Array.length; i++) {
                                    if (view == ACAduserEnglishKellyPage.this.Calibration_View_Array[i]) {
                                        ACAduserEnglishKellyPage.this.viewid = ACAduserEnglishKellyPage.this.Calibration_View_Array[i];
                                        if (Integer.parseInt(ACAduserEnglishKellyPage.this.Calibration_Value_Array[i][0]) == 25 || Integer.parseInt(ACAduserEnglishKellyPage.this.Calibration_Value_Array[i][0]) == 27) {
                                            String Voltstr = ACAduserEnglishByteUtil.printIntArrayString(ACAduserEnglishKellyPage.DataValue, 3, 2, 1, "a");
                                            if (Integer.parseInt(Voltstr) == 80) {
                                                max = (((ACAduserEnglishKellyPage.DataValue[23] * 256) + ACAduserEnglishKellyPage.DataValue[24]) * 125) / 100;
                                                min = 18;
                                            } else {
                                                max = ACAduserEnglishByteUtil.getVoltRange(Voltstr, 1);
                                                min = ACAduserEnglishByteUtil.getVoltRange(Voltstr, 0);
                                            }
                                            ACAduserEnglishKellyPage.this.tipsText.setText("TIPS:" + ACAduserEnglishKellyPage.this.Calibration_Value_Array[i][9] + ",Range " + min + "~" + max + ".");
                                        } else {
                                            ACAduserEnglishKellyPage.this.tipsText.setText("TIPS:" + ACAduserEnglishKellyPage.this.Calibration_Value_Array[i][9]);
                                        }
                                    }
                                }
                            }
                        }
                    });
                } else if (Integer.parseInt(this.Calibration_Value_Array[i2][1]) == 0) {
                    btn_count++;
                    this.CheckBox_Array[i2] = new CheckBox(this);
                    this.CheckBox_Array[i2].setTextColor(View.MEASURED_STATE_MASK);
                    if (i2 < 16) {
                        if (result != null && result.equals("1")) {
                            this.CheckBox_Array[i2].setText("Да");
                            this.CheckBox_Array[i2].setChecked(true);
                        } else if (result != null && result.equals("0")) {
                            this.CheckBox_Array[i2].setText("Нет");
                            this.CheckBox_Array[i2].setChecked(false);
                        }
                    }
                    if (this.isIpad == 1) {
                        addFormGridView(this.Calibration_Value_Array[i2][4], this.CheckBox_Array[i2], 16, btn_count - 1);
                    } else {
                        addFormGridView(this.Calibration_Value_Array[i2][4], this.CheckBox_Array[i2], ((l - 1) / line_count) * 12, (l - (((l - 1) / line_count) * line_count)) - 1);
                    }
                    if (Integer.parseInt(this.Calibration_Value_Array[i2][8]) == 1) {
                        this.CheckBox_Array[i2].setButtonDrawable(R.layout.checkbox);
                        this.CheckBox_Array[i2].setEnabled(true);
                        this.CheckBox_Array[i2].setBackground(getViewRectangleBorderDrawable(-5592406, -1));
                    } else {
                        this.CheckBox_Array[i2].setEnabled(false);
                        this.CheckBox_Array[i2].setTextColor(-4210753);
                        this.CheckBox_Array[i2].setButtonDrawable(R.layout.checkbox);
                        this.CheckBox_Array[i2].setBackground(getViewRectangleBorderDrawable(-5592406, -2894893));
                    }
                    this.CheckBox_Array[i2].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        /* class com.kelly.ACAduserEnglish.ACAduserEnglishKellyPage.AnonymousClass4 */

                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            for (int i = 0; i < 16; i++) {
                                if (buttonView == ACAduserEnglishKellyPage.this.CheckBox_Array[i]) {
                                    if (isChecked) {
                                        ACAduserEnglishKellyPage.this.CheckBox_Array[i].setText("Да");
                                    } else {
                                        ACAduserEnglishKellyPage.this.CheckBox_Array[i].setText("Нет");
                                    }
                                }
                            }
                        }
                    });
                    this.CheckBox_Array[i2].setOnClickListener(new View.OnClickListener() {
                        /* class com.kelly.ACAduserEnglish.ACAduserEnglishKellyPage.AnonymousClass5 */

                        public void onClick(View arg0) {
                            for (int i = 0; i < 16; i++) {
                                if (arg0 == ACAduserEnglishKellyPage.this.CheckBox_Array[i]) {
                                    ACAduserEnglishKellyPage.this.tipsText.setText("TIPS:" + ACAduserEnglishKellyPage.this.Calibration_Value_Array[i][9]);
                                }
                            }
                            if (ACAduserEnglishKellyPage.this.viewid != null) {
                                for (int i2 = 0; i2 < ACAduserEnglishKellyPage.this.Calibration_Value_Array.length; i2++) {
                                    if (ACAduserEnglishKellyPage.this.viewid == ACAduserEnglishKellyPage.this.Calibration_View_Array[i2]) {
                                        ACAduserEnglishKellyPage.this.Calibration_View_Array[i2].clearFocus();
                                        ((InputMethodManager) ACAduserEnglishKellyPage.this.getSystemService("input_method")).hideSoftInputFromWindow(ACAduserEnglishKellyPage.this.Calibration_View_Array[i2].getWindowToken(), 0);
                                    }
                                }
                            }
                        }
                    });
                }
            }
        }
        if (this.Cal_page == 0) {
            this.ReadZeroCurrent = new Button(this);
            this.ReadZeroCurrent.setText("ReadZero");
            this.ReadZeroCurrent.setGravity(49);
            this.ReadZeroCurrent.setTextColor(View.MEASURED_STATE_MASK);
            this.ReadZeroCurrent.setOnClickListener(new View.OnClickListener() {
                /* class com.kelly.ACAduserEnglish.ACAduserEnglishKellyPage.AnonymousClass6 */

                public void onClick(View arg0) {
                    ACAduserEnglishKellyPage.this.formGrid.post(new Thread() {
                        /* class com.kelly.ACAduserEnglish.ACAduserEnglishKellyPage.AnonymousClass6.AnonymousClass1 */

                        public void run() {
                            ACAduserEnglishKellyPage.this.readzero();
                        }
                    });
                }
            });
            this.formGrid.addView(this.ReadZeroCurrent, new ACAduserEnglishLayoutFormGrid.LayoutParams(16, btn_count, 3, 1));
        }
        if (this.Cal_page > 0) {
            line_count = (j + -1) % 3 == 0 ? (j - 1) / 3 : ((j - 1) / 3) + 1;
        }
        if (this.isIpad == 1) {
            if (j - line_count > btn_count) {
                line2 = j - line_count;
            } else {
                line2 = btn_count;
            }
            for (int i3 = 0; i3 < 2; i3++) {
                TextView Verticalbar = new TextView(this);
                Verticalbar.setBackgroundColor(-5592474);
                if (this.Cal_page == 0) {
                    if (i3 == 0) {
                        this.formGrid.addView(Verticalbar, new ACAduserEnglishLayoutFormGrid.LayoutParams(i3 + 101, 0, 10, line_count));
                    } else {
                        this.formGrid.addView(Verticalbar, new ACAduserEnglishLayoutFormGrid.LayoutParams(i3 + 101, 0, 10, line2));
                    }
                } else if (j % 3 != 1) {
                    this.formGrid.addView(Verticalbar, new ACAduserEnglishLayoutFormGrid.LayoutParams(i3 + 101, 0, 10, line_count));
                } else if (i3 == 0) {
                    this.formGrid.addView(Verticalbar, new ACAduserEnglishLayoutFormGrid.LayoutParams(i3 + 101, 0, 10, line_count + 1));
                } else {
                    this.formGrid.addView(Verticalbar, new ACAduserEnglishLayoutFormGrid.LayoutParams(i3 + 101, 0, 10, line_count));
                }
            }
            return;
        }
        TextView Verticalbar2 = new TextView(this);
        Verticalbar2.setBackgroundColor(-5592474);
        this.formGrid.addView(Verticalbar2, new ACAduserEnglishLayoutFormGrid.LayoutParams(103, 0, (this.Screen_width / 256) + 5, line_count));
    }

    public void readzero() {
        this.readzerodlg = new AlertDialog.Builder(this);
        this.readzerodlg.setTitle("ReadZero");
        this.ReadzeroformGrid = new ACAduserEnglishLayoutFormGrid(this);
        this.ReadzeroformGrid.setFormColumnCount(24);
        this.ReadzeroformGrid.setBackgroundColor(-4210753);
        this.ReadzeroformGrid.setFocusable(true);
        this.ReadzeroformGrid.setFocusableInTouchMode(true);
        for (int i = 0; i < 7; i++) {
            if (i == 0 || i == 3) {
                EditText label = new EditText(this);
                label.setText(ACAduserEnglishSetting.Readzero_View_Array[i][4]);
                label.setBackgroundColor(-4210753);
                label.setEnabled(false);
                label.setGravity(19);
                label.setTextColor(View.MEASURED_STATE_MASK);
                this.ReadzeroformGrid.addView(label, new ACAduserEnglishLayoutFormGrid.LayoutParams(0, i, 10, 1));
            } else {
                int j = i < 3 ? i - 1 : i - 2;
                this.Readzero_View_Array[j] = new ACAduserEnglishEditText(this, true, "");
                this.Readzero_View_Array2[j] = new ACAduserEnglishEditText(this, false, "");
                addReadzeroFormGridView(ACAduserEnglishSetting.Readzero_View_Array[i][4], this.Readzero_View_Array[j], this.Readzero_View_Array2[j], 0, i);
                this.Readzero_View_Array[j].setBackground(getViewRectangleBorderDrawable(-5592406, -1));
                this.Readzero_View_Array2[j].setBackground(getViewRectangleBorderDrawable(-5592406, -1));
            }
        }
        new Button(this);
        Button Readzerobtn = new Button(this);
        Readzerobtn.setText("Read");
        Readzerobtn.setGravity(49);
        Readzerobtn.setTextColor(View.MEASURED_STATE_MASK);
        Readzerobtn.setOnClickListener(new View.OnClickListener() {
            /* class com.kelly.ACAduserEnglish.ACAduserEnglishKellyPage.AnonymousClass7 */

            public void onClick(View arg0) {
                ACAduserEnglishKellyPage.this.ReadzeroformGrid.post(new Thread() {
                    /* class com.kelly.ACAduserEnglish.ACAduserEnglishKellyPage.AnonymousClass7.AnonymousClass1 */

                    public void run() {
                        ACAduserEnglishKellyPage.this.Readzc();
                    }
                });
            }
        });
        this.ReadzeroformGrid.addView(Readzerobtn, new ACAduserEnglishLayoutFormGrid.LayoutParams(18, 5, 4, 1));
        new Button(this);
        Button Updatazerobtn = new Button(this);
        Updatazerobtn.setText("Update");
        Updatazerobtn.setGravity(49);
        Updatazerobtn.setTextColor(View.MEASURED_STATE_MASK);
        Updatazerobtn.setOnClickListener(new View.OnClickListener() {
            /* class com.kelly.ACAduserEnglish.ACAduserEnglishKellyPage.AnonymousClass8 */

            public void onClick(View arg0) {
                ACAduserEnglishKellyPage.this.ReadzeroformGrid.post(new Thread() {
                    /* class com.kelly.ACAduserEnglish.ACAduserEnglishKellyPage.AnonymousClass8.AnonymousClass1 */

                    public void run() {
                        ACAduserEnglishKellyPage.this.Updatazc();
                    }
                });
            }
        });
        this.ReadzeroformGrid.addView(Updatazerobtn, new ACAduserEnglishLayoutFormGrid.LayoutParams(18, 6, 4, 1));
        this.readzerodlg.setView(this.ReadzeroformGrid);
        this.readzerodlg.setPositiveButton("Back", new DialogInterface.OnClickListener() {
            /* class com.kelly.ACAduserEnglish.ACAduserEnglishKellyPage.AnonymousClass9 */

            public void onClick(DialogInterface dialog, int whichButton) {
                ACAduserEnglishKellyPage.this.formGrid.removeAllViews();
                ACAduserEnglishKellyPage.this.ShowKellypageCalpage();
            }
        });
        this.readzerodlg.setCancelable(false);
        this.readzerodlg.create().show();
    }

    public void Updatazc() {
        for (int i = 0; i < 5; i++) {
            if (this.Readzero_View_Array[i].getText().toString().equals("")) {
                return;
            }
        }
        for (int i2 = 0; i2 < 2; i2++) {
            DataValue[(i2 * 2) + 33] = this.zeroint[i2] >> 8;
            DataValue[(i2 * 2) + 34] = this.zeroint[i2] & MotionEventCompat.ACTION_MASK;
        }
        for (int i3 = 0; i3 < 3; i3++) {
            DataValue[(i3 * 2) + 40] = this.zeroint[i3 + 2] >> 8;
            DataValue[(i3 * 2) + 41] = this.zeroint[i3 + 2] & MotionEventCompat.ACTION_MASK;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r5v35, resolved type: byte[] */
    /* JADX DEBUG: Multi-variable search result rejected for r5v36, resolved type: byte */
    /* JADX DEBUG: Multi-variable search result rejected for r5v54, resolved type: byte[] */
    /* JADX DEBUG: Multi-variable search result rejected for r5v55, resolved type: byte */
    /* JADX DEBUG: Multi-variable search result rejected for r5v66, resolved type: byte[] */
    /* JADX DEBUG: Multi-variable search result rejected for r5v67, resolved type: byte */
    /* JADX WARN: Multi-variable type inference failed */
    public void Readzc() {
        int temp_return = 0;
        int[] rx = new int[16];
        for (int i = 0; i < 5; i++) {
            this.zeroint[i] = 0;
        }
        this.deviceKelly.ETS_TX_CMD = ETS_GET_PHASE_I_AD;
        this.deviceKelly.ETS_TX_BYTES = 0;
        this.deviceKelly.sendcmd();
        for (int i2 = 0; i2 < 2; i2++) {
            temp_return = this.deviceKelly.readcmd();
            if (temp_return == 1 && this.deviceKelly.ETS_RX_CMD == 53) {
                break;
            }
        }
        if (temp_return == 1 && this.deviceKelly.ETS_RX_CMD == 53) {
            for (int i3 = 0; i3 < 10; i3++) {
                rx[i3] = this.deviceKelly.ETS_RX_DATA[i3];
                if (rx[i3] < 0) {
                    rx[i3] = rx[i3] + 256;
                }
            }
            for (int i4 = 0; i4 < 5; i4++) {
                this.zeroint[i4] = (rx[i4 * 2] * 256) + rx[(i4 * 2) + 1];
                this.Readzero_View_Array2[i4].setText(new StringBuilder(String.valueOf(this.zeroint[i4])).toString());
            }
        }
        this.deviceKelly.ETS_TX_CMD = ETS_GET_PHASE_I_AD;
        this.deviceKelly.ETS_TX_BYTES = 0;
        this.deviceKelly.sendcmd();
        for (int i5 = 0; i5 < 2; i5++) {
            temp_return = this.deviceKelly.readcmd();
            if (temp_return == 1 && this.deviceKelly.ETS_RX_CMD == 53) {
                break;
            }
        }
        if (temp_return == 1 && this.deviceKelly.ETS_RX_CMD == 53) {
            for (int i6 = 0; i6 < 10; i6++) {
                rx[i6] = this.deviceKelly.ETS_RX_DATA[i6];
                if (rx[i6] < 0) {
                    rx[i6] = rx[i6] + 256;
                }
            }
            for (int i7 = 0; i7 < 5; i7++) {
                this.Readzero_View_Array2[i7].setText(String.valueOf(this.Readzero_View_Array2[i7].getText().toString()) + "," + new StringBuilder().append((rx[i7 * 2] * 256) + rx[(i7 * 2) + 1]).toString());
            }
            for (int i8 = 0; i8 < 5; i8++) {
                if (this.zeroint[i8] > (rx[i8 * 2] * 256) + rx[(i8 * 2) + 1]) {
                    this.zeroint[i8] = (rx[i8 * 2] * 256) + rx[(i8 * 2) + 1];
                }
            }
        }
        this.deviceKelly.ETS_TX_CMD = ETS_GET_PHASE_I_AD;
        this.deviceKelly.ETS_TX_BYTES = 0;
        this.deviceKelly.sendcmd();
        for (int i9 = 0; i9 < 2; i9++) {
            temp_return = this.deviceKelly.readcmd();
            if (temp_return == 1 && this.deviceKelly.ETS_RX_CMD == 53) {
                break;
            }
        }
        if (temp_return == 1 && this.deviceKelly.ETS_RX_CMD == 53) {
            for (int i10 = 0; i10 < 10; i10++) {
                rx[i10] = this.deviceKelly.ETS_RX_DATA[i10];
                if (rx[i10] < 0) {
                    rx[i10] = rx[i10] + 256;
                }
            }
            for (int i11 = 0; i11 < 5; i11++) {
                this.Readzero_View_Array2[i11].setText(String.valueOf(this.Readzero_View_Array2[i11].getText().toString()) + "," + new StringBuilder().append((rx[i11 * 2] * 256) + rx[(i11 * 2) + 1]).toString());
            }
            for (int i12 = 0; i12 < 5; i12++) {
                if (this.zeroint[i12] > (rx[i12 * 2] * 256) + rx[(i12 * 2) + 1]) {
                    this.zeroint[i12] = (rx[i12 * 2] * 256) + rx[(i12 * 2) + 1];
                }
            }
        }
        for (int i13 = 0; i13 < 5; i13++) {
            this.Readzero_View_Array[i13].setText(new StringBuilder(String.valueOf(this.zeroint[i13])).toString());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private int UpdataValue() {
        int max;
        int min;
        for (int i = 0; i < this.Calibration_Value_Array.length; i++) {
            String result = "";
            if (Integer.parseInt(this.Calibration_Value_Array[i][8]) == 1 && Integer.parseInt(this.Calibration_Value_Array[i][7]) == 1 && Integer.parseInt(this.Calibration_Value_Array[i][0]) >= this.Cal_page * 128 && Integer.parseInt(this.Calibration_Value_Array[i][0]) < (this.Cal_page + 1) * 128) {
                if (Integer.parseInt(this.Calibration_Value_Array[i][1]) != 0) {
                    result = this.Calibration_View_Array[i].getText().toString();
                } else if (this.CheckBox_Array[i].isChecked()) {
                    result = "1";
                } else if (!this.CheckBox_Array[i].isChecked()) {
                    result = "0";
                }
                if (result.equals("")) {
                    showLogInfo(String.valueOf(this.Calibration_Value_Array[i][4]) + "is null,please input again!");
                    return 0;
                }
                if (Integer.parseInt(this.Calibration_Value_Array[i][0]) == 25 || Integer.parseInt(this.Calibration_Value_Array[i][0]) == 27) {
                    String Voltstr = ACAduserEnglishByteUtil.printIntArrayString(DataValue, 3, 2, 1, "a");
                    if (Integer.parseInt(Voltstr) == 80) {
                        max = (((DataValue[23] * 256) + DataValue[24]) * 125) / 100;
                        min = 18;
                    } else {
                        max = ACAduserEnglishByteUtil.getVoltRange(Voltstr, 1);
                        min = ACAduserEnglishByteUtil.getVoltRange(Voltstr, 0);
                    }
                    int resultInt = Integer.parseInt(result);
                    if (resultInt < min || resultInt > max) {
                        showLogInfo(String.valueOf(this.Calibration_Value_Array[i][4]) + " Range is " + min + "-" + max + ",please input again!");
                        return 0;
                    }
                } else if (ACAduserEnglishByteUtil.GetRangeLimit(Integer.parseInt(this.Calibration_Value_Array[i][0])) == 1 || (this.Soft_Ver == 1 && Integer.parseInt(this.Calibration_Value_Array[i][0]) == 105)) {
                    try {
                        Integer.parseInt(result);
                    } catch (Exception e) {
                        showLogInfo(String.valueOf(this.Calibration_Value_Array[i][4]) + "is null!");
                    }
                    if (Integer.parseInt(result) > Integer.parseInt(this.Calibration_Value_Array[i][6]) || Integer.parseInt(result) < Integer.parseInt(this.Calibration_Value_Array[i][5])) {
                        showLogInfo(String.valueOf(this.Calibration_Value_Array[i][4]) + "range not match,please input again!");
                        return 0;
                    }
                }
                try {
                    if (ACAduserEnglishByteUtil.printStringArrayInt(DataValue, Integer.parseInt(this.Calibration_Value_Array[i][0]), Integer.parseInt(this.Calibration_Value_Array[i][1]), Integer.parseInt(this.Calibration_Value_Array[i][2]), this.Calibration_Value_Array[i][3], result) == 0) {
                        showLogInfo("Transform Error!");
                    }
                } catch (Exception e2) {
                    showLogInfo("Error");
                }
            }
        }
        return 1;
    }

    public void ShowKellypageMonitor() {
        int rowCount;
        int textview_count = 0;
        int j = 0;
        for (int i = 0; i < ACAduserEnglishSetting.Monitor_Value_Array.length; i++) {
            if (Integer.parseInt(ACAduserEnglishSetting.Monitor_Value_Array[i][7]) == 1) {
                textview_count++;
            }
        }
        for (int i2 = 0; i2 < ACAduserEnglishSetting.Monitor_Value_Array.length; i2++) {
            if (Integer.parseInt(ACAduserEnglishSetting.Monitor_Value_Array[i2][7]) == 1) {
                j++;
                if (i2 == 0) {
                    this.MonitorshowStr[i2] = "";
                    this.Monitor_View_Array[i2] = new Monitor_View(this, i2);
                    this.Monitor_View_Array[i2].setEnabled(false);
                    TextView label = new ACAduserEnglishEditText(this, false, "");
                    label.setText(ACAduserEnglishSetting.Monitor_Value_Array[i2][4]);
                    label.setGravity(19);
                    label.setTextColor(View.MEASURED_STATE_MASK);
                    this.MonitorformGrid.addView(label, new ACAduserEnglishLayoutFormGrid.LayoutParams(0, 0, 5, 1));
                    this.MonitorformGrid.addView(this.Monitor_View_Array[i2], new ACAduserEnglishLayoutFormGrid.LayoutParams(5, 0, 19, 1));
                    this.Monitor_View_Array[i2].setBackground(getViewRectangleBorderDrawable(-5592406, -2894893));
                } else {
                    this.MonitorshowStr[i2] = "0";
                    this.Monitor_View_Array[i2] = new Monitor_View(this, i2);
                    this.Monitor_View_Array[i2].setEnabled(false);
                    this.Monitor_View_Array[i2].setBackground(getViewRectangleBorderDrawable(-5592406, -2894893));
                    int colIndex = ACAduserEnglishByteUtil.get_Index(textview_count, j, 0);
                    addMonitorFormGridView(ACAduserEnglishSetting.Monitor_Value_Array[i2][4], this.Monitor_View_Array[i2], colIndex * 8, ACAduserEnglishByteUtil.get_Index(textview_count, j, 1));
                }
            }
        }
        for (int i3 = 0; i3 < 2; i3++) {
            if ((j - 1) % 3 == 0) {
                rowCount = (j - 1) / 3;
            } else {
                rowCount = ((j - 1) / 3) + 1;
            }
            TextView Verticalbar = new TextView(this);
            Verticalbar.setBackgroundColor(-5592474);
            if ((j - 1) % 3 != 1) {
                this.MonitorformGrid.addView(Verticalbar, new ACAduserEnglishLayoutFormGrid.LayoutParams(i3 + 101, 1, 10, rowCount));
            } else if (i3 == 0) {
                this.MonitorformGrid.addView(Verticalbar, new ACAduserEnglishLayoutFormGrid.LayoutParams(i3 + 101, 1, 10, rowCount));
            } else {
                this.MonitorformGrid.addView(Verticalbar, new ACAduserEnglishLayoutFormGrid.LayoutParams(i3 + 101, 1, 10, rowCount - 1));
            }
        }
        ACAduserEnglishAppManager.getInstance().add(this);
    }

    /* access modifiers changed from: package-private */
    public class Monitor_View extends androidx.appcompat.widget.AppCompatEditText {
        private int icount;

        public Monitor_View(Context context, int view_count) {
            super(context);
            this.icount = view_count;
        }

        public void onDraw(Canvas canvas) {
            Paint xyTextPaint = new Paint();
            xyTextPaint.setColor(View.MEASURED_STATE_MASK);
            xyTextPaint.setTextSize(ACAduserEnglishKellyPage.this.EditText_size);
            xyTextPaint.setAntiAlias(true);
            canvas.drawText(ACAduserEnglishKellyPage.this.MonitorshowStr[this.icount], (float) (ACAduserEnglishKellyPage.this.Screen_width / 64), (ACAduserEnglishKellyPage.this.EditText_height - (ACAduserEnglishKellyPage.this.EditText_size / 2.0f)) - ((float) (((int) ACAduserEnglishKellyPage.this.EditText_height) / 12)), xyTextPaint);
        }
    }

    public void ShowKellypageIdentify() {
        Button Cal_Title = new Button(this);
        Cal_Title.setEnabled(false);
        Cal_Title.setText("Cal Value");
        Cal_Title.setTextSize(15.0f);
        Cal_Title.setGravity(49);
        Cal_Title.setTextColor(-16744448);
        Cal_Title.setBackgroundColor(-4210753);
        this.IdentifyformGrid.addView(Cal_Title, new ACAduserEnglishLayoutFormGrid.LayoutParams(1, 0, 5, 1));
        Button Identify_Title = new Button(this);
        Identify_Title.setEnabled(false);
        Identify_Title.setText("Identify Value");
        Identify_Title.setTextSize(15.0f);
        Identify_Title.setGravity(49);
        Identify_Title.setTextColor(-16744448);
        Identify_Title.setBackgroundColor(-4210753);
        this.IdentifyformGrid.addView(Identify_Title, new ACAduserEnglishLayoutFormGrid.LayoutParams(9, 0, 5, 1));
        Button Menu_Title = new Button(this);
        Menu_Title.setEnabled(false);
        Menu_Title.setText("Button");
        Menu_Title.setTextSize(15.0f);
        Menu_Title.setTextColor(-16744448);
        Menu_Title.setGravity(49);
        Menu_Title.setBackgroundColor(-4210753);
        this.IdentifyformGrid.addView(Menu_Title, new ACAduserEnglishLayoutFormGrid.LayoutParams(19, 0, 3, 1));
        for (int i = 0; i < 12; i++) {
            this.Cal_View_Array[i] = new ACAduserEnglishEditText(this, true, "");
            this.Cal_View_Array[i].setTextSize(15.0f);
            this.Cal_View_Array[i].setBackground(getViewRectangleBorderDrawable(-5592406, -1));
            addIdentifyFormGridView(ACAduserEnglishSetting.Identify_Name_Array[i][4], this.Cal_View_Array[i], 0, i + 1);
        }
        for (int i2 = 0; i2 < 12; i2++) {
            this.Identify_View_Array[i2] = new ACAduserEnglishEditText(this, false, "");
            this.Identify_View_Array[i2].setTextSize(15.0f);
            this.Identify_View_Array[i2].setBackground(getViewRectangleBorderDrawable(-5592406, -2894893));
            addIdentifyFormGridView(ACAduserEnglishSetting.Identify_Name_Array[i2][4], this.Identify_View_Array[i2], 8, i2 + 1);
        }
        this.Read_Cal = new Button(this);
        if (this.isIpad == 1) {
            this.Read_Cal.setText("Read Cal");
        } else {
            this.Read_Cal.setText("Read");
        }
        this.Read_Cal.setTextSize(15.0f);
        this.Read_Cal.setGravity(49);
        this.Read_Cal.setTextColor(View.MEASURED_STATE_MASK);
        this.Read_Cal.setOnClickListener(new View.OnClickListener() {
            /* class com.kelly.ACAduserEnglish.ACAduserEnglishKellyPage.AnonymousClass10 */

            public void onClick(View arg0) {
                ACAduserEnglishKellyPage.this.ReadCal();
            }
        });
        if (this.isIpad == 1) {
            this.IdentifyformGrid.addView(this.Read_Cal, new ACAduserEnglishLayoutFormGrid.LayoutParams(16, 1, 3, 1));
        } else {
            this.IdentifyformGrid.addView(this.Read_Cal, new ACAduserEnglishLayoutFormGrid.LayoutParams(16, 1, 4, 1));
        }
        this.Change_Cal = new Button(this);
        if (this.isIpad == 1) {
            this.Change_Cal.setText("Change Cal");
        } else {
            this.Change_Cal.setText("Change");
        }
        this.Change_Cal.setTextSize(15.0f);
        this.Change_Cal.setTextColor(View.MEASURED_STATE_MASK);
        this.Change_Cal.setGravity(49);
        this.Change_Cal.setOnClickListener(new View.OnClickListener() {
            /* class com.kelly.ACAduserEnglish.ACAduserEnglishKellyPage.AnonymousClass11 */

            public void onClick(View arg0) {
                ACAduserEnglishKellyPage.this.ChangeCal();
            }
        });
        if (this.isIpad == 1) {
            this.IdentifyformGrid.addView(this.Change_Cal, new ACAduserEnglishLayoutFormGrid.LayoutParams(16, 2, 3, 1));
        } else {
            this.IdentifyformGrid.addView(this.Change_Cal, new ACAduserEnglishLayoutFormGrid.LayoutParams(16, 2, 4, 1));
        }
        this.Entry_Identify = new Button(this);
        this.Entry_Identify.setTextSize(15.0f);
        if (this.isIpad == 1) {
            this.Entry_Identify.setText("Enter Iden");
        } else {
            this.Entry_Identify.setText("Enter");
        }
        this.Entry_Identify.setTextColor(View.MEASURED_STATE_MASK);
        this.Entry_Identify.setGravity(49);
        this.Entry_Identify.setOnClickListener(new View.OnClickListener() {
            /* class com.kelly.ACAduserEnglish.ACAduserEnglishKellyPage.AnonymousClass12 */

            public void onClick(View arg0) {
                ACAduserEnglishKellyPage.this.EntryIdentify();
            }
        });
        int rowIndex = 4 + 1;
        this.IdentifyformGrid.addView(this.Entry_Identify, new ACAduserEnglishLayoutFormGrid.LayoutParams(20, 4, 4, 1));
        for (int i3 = 0; i3 < this.Tips_View_Array.length; i3++) {
            this.Tips_View_Array[i3] = new ACAduserEnglishEditText(this, false, "Static");
            this.Tips_View_Array[i3].setTextSize(15.0f);
            this.Tips_View_Array[i3].setTextColor(-16744448);
            this.Tips_View_Array[i3].setBackground(getViewRectangleBorderDrawable(-5592406, -2894893));
            if (i3 == 0) {
                this.IdentifyformGrid.addView(this.Tips_View_Array[i3], new ACAduserEnglishLayoutFormGrid.LayoutParams(16, (i3 + 5) - 1, 4, 2));
            } else {
                this.IdentifyformGrid.addView(this.Tips_View_Array[i3], new ACAduserEnglishLayoutFormGrid.LayoutParams(16, i3 + 5, 4, 1));
            }
        }
        this.Quit_Identify = new Button(this);
        if (this.isIpad == 1) {
            this.Quit_Identify.setText("Quit Iden");
        } else {
            this.Quit_Identify.setText("Quit");
        }
        this.Quit_Identify.setTextSize(15.0f);
        this.Quit_Identify.setTextColor(View.MEASURED_STATE_MASK);
        this.Quit_Identify.setGravity(49);
        this.Quit_Identify.setOnClickListener(new View.OnClickListener() {
            /* class com.kelly.ACAduserEnglish.ACAduserEnglishKellyPage.AnonymousClass13 */

            public void onClick(View arg0) {
                ACAduserEnglishKellyPage.this.QuitIdentify();
            }
        });
        int rowIndex2 = rowIndex + 1;
        this.IdentifyformGrid.addView(this.Quit_Identify, new ACAduserEnglishLayoutFormGrid.LayoutParams(20, rowIndex, 4, 1));
        this.Identify_Resolver = new Button(this);
        this.Identify_Resolver.setTextSize(15.0f);
        if (this.isIpad == 1) {
            this.Identify_Resolver.setText("Iden Resolver");
        } else {
            this.Identify_Resolver.setText("Resolver");
        }
        this.Identify_Resolver.setTextColor(View.MEASURED_STATE_MASK);
        this.Identify_Resolver.setGravity(49);
        this.Identify_Resolver.setOnClickListener(new View.OnClickListener() {
            /* class com.kelly.ACAduserEnglish.ACAduserEnglishKellyPage.AnonymousClass14 */

            public void onClick(View arg0) {
                ACAduserEnglishKellyPage.this.IdentifyResolver();
            }
        });
        int rowIndex3 = rowIndex2 + 1;
        this.IdentifyformGrid.addView(this.Identify_Resolver, new ACAduserEnglishLayoutFormGrid.LayoutParams(20, rowIndex2, 4, 1));
        this.Identify_Hall = new Button(this);
        if (this.isIpad == 1) {
            this.Identify_Hall.setText("Iden Hall");
        } else {
            this.Identify_Hall.setText("Hall");
        }
        this.Identify_Hall.setTextSize(15.0f);
        this.Identify_Hall.setTextColor(View.MEASURED_STATE_MASK);
        this.Identify_Hall.setGravity(49);
        this.Identify_Hall.setOnClickListener(new View.OnClickListener() {
            /* class com.kelly.ACAduserEnglish.ACAduserEnglishKellyPage.AnonymousClass15 */

            public void onClick(View arg0) {
                ACAduserEnglishKellyPage.this.IdentifyHall();
            }
        });
        int rowIndex4 = rowIndex3 + 1;
        this.IdentifyformGrid.addView(this.Identify_Hall, new ACAduserEnglishLayoutFormGrid.LayoutParams(20, rowIndex3, 4, 1));
        this.Write_Cal = new Button(this);
        this.Write_Cal.setTextSize(15.0f);
        if (this.isIpad == 1) {
            this.Write_Cal.setText("Write Cal");
        } else {
            this.Write_Cal.setText("Write");
        }
        this.Write_Cal.setTextColor(View.MEASURED_STATE_MASK);
        this.Write_Cal.setGravity(49);
        this.Write_Cal.setOnClickListener(new View.OnClickListener() {
            /* class com.kelly.ACAduserEnglish.ACAduserEnglishKellyPage.AnonymousClass16 */

            public void onClick(View arg0) {
                ACAduserEnglishKellyPage.this.WriteCal();
            }
        });
        int i4 = rowIndex4 + 1;
        this.IdentifyformGrid.addView(this.Write_Cal, new ACAduserEnglishLayoutFormGrid.LayoutParams(20, rowIndex4, 4, 1));
        for (int i5 = 0; i5 < 2; i5++) {
            TextView Verticalbar = new TextView(this);
            Verticalbar.setBackgroundColor(-5592474);
            this.IdentifyformGrid.addView(Verticalbar, new ACAduserEnglishLayoutFormGrid.LayoutParams(i5 + 101, 0, 8, 13));
        }
        TextView statement = new ACAduserEnglishEditText(this, false, "");
        statement.setLineSpacing(1.0f, 1.5f);
        statement.setTextSize(15.0f);
        this.IdentifyformGrid.addView(statement, new ACAduserEnglishLayoutFormGrid.LayoutParams(0, 14, 24, 20));
        this.logTxtSb.append("Operation Procedure:\n");
        this.logTxtSb.append("TIPS:Before Indentify,you must check driving wheels of vehicle is hung,and Motor is non-loaded!!\n");
        this.logTxtSb.append("TIPS:The Identify has no effect on KACI Series!!\n");
        this.logTxtSb.append("1、 You need read data from Calibration at first,comparing with the results of Identify.\n");
        this.logTxtSb.append("2、Click the button 'Read Cal' ,read data from Calibration.\n");
        this.logTxtSb.append("3、Click the button 'Enter Iden' ,Identify Flag will be send to controller,then controller will be restart and wait for the Indentify Command!\n\n");
        this.logTxtSb.append("TIPS:You need click the right button according to the tpye of Motor Sensor Type,Hall to Identify Hall,Resolver to Identify Resolver.\n\n");
        this.logTxtSb.append("Identify Resolver:\n");
        this.logTxtSb.append("4、Click the button 'Iden Resolver' to Identify Resolver Start Angle,the status is showed in the text before the button in Identifing.\n");
        this.logTxtSb.append("5、After Identify,Resolver Start Angle will be showed.If the result of Exchange Phase AB is 255,the identification is Failed,you need to retry Identify Resolver.\n     If the result is 0 or 1,the identification is Successful,it means the direction.\n\n");
        this.logTxtSb.append("Identify Hall:\n");
        this.logTxtSb.append("4、Click the button 'Iden Hall' to Identify Hall phase sequence,the status is showed in the text before the button in Identifing.\n");
        this.logTxtSb.append("5、After Identify,Hall phase sequence will be showed.If the result of all HALL is 22,it means Identify Failed,you need to rey Identify Hall.\n      If the result is 0-7,it means Identify Succeed!\n\n");
        this.logTxtSb.append("6、Then click the button 'Write Cal',The result of Identify Value will be writed to Calibration.\n");
        this.logTxtSb.append("7、Click the button 'Quit Iden',Quit Identify Flag will be send to controller,the controller will be restart in normal.\n");
        this.logTxtSb.append("8、If you don't click the button 'Write Cal' before Quit Identify,the result will be ignored!\n");
        this.logTxtSb.append("9、After all,Please close the app.\n\n");
        this.logTxtSb.append("TIPS:If the test of status is 'Don't Start' or 'Wait for other operation completely',please restart the controller and app to retry!\n           If you want to Identify Resolver but click 'Iden Hall',or conversely,please restart the controller and app to retry!");
        showTextView(statement, getLogBufferInfo());
        if (this.IdentifyDEBUG) {
            this.Identifyceshi = new ACAduserEnglishEditText(this, false, "");
            TextView label = new TextView(this);
            label.setText("Debug");
            label.setGravity(17);
            label.setTextColor(-12303224);
            this.IdentifyformGrid.addView(label, new ACAduserEnglishLayoutFormGrid.LayoutParams(0, 35, 2, 400));
            this.IdentifyformGrid.addView(this.Identifyceshi, new ACAduserEnglishLayoutFormGrid.LayoutParams(2, 35, 20, 400));
        }
    }

    public void MainInterface() {
        Button compView = new Button(this);
        compView.setBackgroundColor(0);
        if (this.isIpad == 1) {
            compView.setText("Контроллер LLC");
        } else {
            compView.setText("LLC");
        }
        compView.setGravity(19);
        compView.setTextColor(-12303224);
        if (this.isIpad == 1) {
            this.bottomView.addView(compView, new ACAduserEnglishLayoutFormGrid.LayoutParams(0, 0, 4, 1));
        } else {
            this.bottomView.addView(compView, new ACAduserEnglishLayoutFormGrid.LayoutParams(0, 0, 5, 1));
        }
        Button ComStatusname = new Button(this);
        ComStatusname.setBackgroundColor(0);
        if (this.COMType == 0) {
            ComStatusname.setText("Статус:");
        } else if (this.COMType == 1) {
            ComStatusname.setText("Bluetooth:");
        }
        ComStatusname.setTextColor(View.MEASURED_STATE_MASK);
        ComStatusname.setGravity(21);
        if (this.isIpad == 1) {
            this.bottomView.addView(ComStatusname, new ACAduserEnglishLayoutFormGrid.LayoutParams(2, 0, 2, 1));
        } else {
            this.bottomView.addView(ComStatusname, new ACAduserEnglishLayoutFormGrid.LayoutParams(4, 0, 5, 1));
        }
        this.ComStatus = new Button(this);
        this.ComStatus.setBackgroundColor(0);
        this.ComStatus.setTextColor(View.MEASURED_STATE_MASK);
        this.ComStatus.setGravity(19);
        if (this.isIpad == 1) {
            this.bottomView.addView(this.ComStatus, new ACAduserEnglishLayoutFormGrid.LayoutParams(4, 0, 2, 1));
        } else {
            this.bottomView.addView(this.ComStatus, new ACAduserEnglishLayoutFormGrid.LayoutParams(9, 0, 5, 1));
        }
        this.openDevice = new Button(this);
        if (this.isIpad != 1) {
            this.openDevice.setText("Open");
        } else if (this.COMType == 1) {
            this.openDevice.setText("Connect");
        } else {
            this.openDevice.setText("Open Device");
        }
        this.openDevice.setTextColor(View.MEASURED_STATE_MASK);
        this.openDevice.setOnClickListener(new View.OnClickListener() {
            /* class com.kelly.ACAduserEnglish.ACAduserEnglishKellyPage.AnonymousClass17 */

            public void onClick(View arg0) {
                ACAduserEnglishKellyPage.this.formGrid.post(new Thread() {
                    /* class com.kelly.ACAduserEnglish.ACAduserEnglishKellyPage.AnonymousClass17.AnonymousClass1 */

                    public void run() {
                        if (ACAduserEnglishKellyPage.this.COMType == 0) {
                            ACAduserEnglishKellyPage.this.opendevice();
                        } else if (ACAduserEnglishKellyPage.this.COMType == 1 && !ACAduserEnglishKellyPage.this.Com_Status) {
                            ACAduserEnglishKellyPage.this.openbluetoothdevice();
                        }
                    }
                });
            }
        });
        this.bottomView.addView(this.openDevice, new ACAduserEnglishLayoutFormGrid.LayoutParams(6, 0, 2, 1));
        this.closeDevice = new Button(this);
        if (this.isIpad != 1) {
            this.closeDevice.setText("Close");
        } else if (this.COMType == 1) {
            this.closeDevice.setText("Disconnect");
        } else {
            this.closeDevice.setText("Close Device");
        }
        this.closeDevice.setTextColor(View.MEASURED_STATE_MASK);
        this.closeDevice.setOnClickListener(new View.OnClickListener() {
            /* class com.kelly.ACAduserEnglish.ACAduserEnglishKellyPage.AnonymousClass18 */

            public void onClick(View arg0) {
                ACAduserEnglishKellyPage.this.formGrid.post(new Thread() {
                    /* class com.kelly.ACAduserEnglish.ACAduserEnglishKellyPage.AnonymousClass18.AnonymousClass1 */

                    public void run() {
                        ACAduserEnglishKellyPage.this.closedevice();
                    }
                });
            }
        });
        this.bottomView.addView(this.closeDevice, new ACAduserEnglishLayoutFormGrid.LayoutParams(8, 0, 2, 1));
        this.readBtn = new Button(this);
        if (this.isIpad == 1) {
            this.readBtn.setText("Чтение");
        } else {
            this.readBtn.setText("Ч");
        }
        this.readBtn.setTextColor(View.MEASURED_STATE_MASK);
        this.readBtn.setOnClickListener(new View.OnClickListener() {
            /* class com.kelly.ACAduserEnglish.ACAduserEnglishKellyPage.AnonymousClass19 */

            public void onClick(View arg0) {
                ACAduserEnglishKellyPage.this.formGrid.post(new Thread() {
                    /* class com.kelly.ACAduserEnglish.ACAduserEnglishKellyPage.AnonymousClass19.AnonymousClass1 */

                    public void run() {
                        try {
                            ACAduserEnglishKellyPage.this.readDataFromKelly();
                        } catch (Exception e) {
                            ACAduserEnglishKellyPage.this.showLogInfo("Read Error");
                        }
                    }
                });
            }
        });
        if (this.isIpad == 1) {
            this.bottomView.addView(this.readBtn, new ACAduserEnglishLayoutFormGrid.LayoutParams(10, 0, 1, 1));
        } else {
            this.bottomView.addView(this.readBtn, new ACAduserEnglishLayoutFormGrid.LayoutParams(20, 0, 2, 1));
        }
        this.sendBtn = new Button(this);
        if (this.isIpad == 1) {
            this.sendBtn.setText("Запись");
        } else {
            this.sendBtn.setText("З");
        }
        this.sendBtn.setEnabled(false);
        this.sendBtn.setTextColor(-7829368);
        this.sendBtn.setOnClickListener(new View.OnClickListener() {
            /* class com.kelly.ACAduserEnglish.ACAduserEnglishKellyPage.AnonymousClass20 */

            public void onClick(View arg0) {
                ACAduserEnglishKellyPage.this.formGrid.post(new Thread() {
                    /* class com.kelly.ACAduserEnglish.ACAduserEnglishKellyPage.AnonymousClass20.AnonymousClass1 */

                    public void run() {
                        try {
                            ACAduserEnglishKellyPage.this.sendDataToKelly();
                        } catch (Exception err) {
                            ACAduserEnglishKellyPage.this.showLogInfo(err.getMessage());
                        }
                    }
                });
            }
        });
        if (this.isIpad == 1) {
            this.bottomView.addView(this.sendBtn, new ACAduserEnglishLayoutFormGrid.LayoutParams(11, 0, 1, 1));
        } else {
            this.bottomView.addView(this.sendBtn, new ACAduserEnglishLayoutFormGrid.LayoutParams(22, 0, 2, 1));
        }
    }

    @SuppressLint("ResourceType")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        setRequestedOrientation(0);
        try {
            this.COMType = Integer.parseInt(getIntent().getExtras().getString("comtype"));
            this.deviceKelly.COMType = this.COMType;
        } catch (Exception e) {
            System.out.println("The COMType is wrong!");
        }
        if (this.COMType == 1 && !BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            BluetoothAdapter.getDefaultAdapter().enable();
        }
        getWindow().setSoftInputMode(32);
        WindowManager wm = getWindowManager();
        Point size = new Point();
        wm.getDefaultDisplay().getSize(size);
        //int width = size.x; int height =
        this.Screen_width = size.x;//wm.getDefaultDisplay().getWidth();
        this.Screen_height =size.y;// wm.getDefaultDisplay().getHeight();
        getWindow().setSoftInputMode(32);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        double screenInches = Math.sqrt(Math.pow((double) (((float) dm.widthPixels) / dm.xdpi), 2.0d) + Math.pow((double) (((float) dm.heightPixels) / dm.ydpi), 2.0d));
        System.out.println("The screenInches is:" + screenInches);
        EditText txtField = new EditText(this);
        txtField.setText("0");
        this.EditText_size = txtField.getTextSize();
        txtField.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        this.EditText_height = (float) txtField.getMeasuredHeight();
        System.out.println("The screen is:" + this.Screen_width + "*" + this.Screen_height);
        System.out.println("EditText_size =" + this.EditText_size);
        System.out.println("EditText_height =" + this.EditText_height);
        if (screenInches >= 6.0d) {
            this.isIpad = 1;
        } else {
            this.isIpad = 2;
        }
        if (this.isIpad == 1) {
            this.Textsize = this.EditText_size;
        } else if (this.isIpad == 2) {
            this.Textsize = 16.0f;
        }
        this.formGrid = new ACAduserEnglishLayoutFormGrid(this);
        this.formGrid.setFormColumnCount(24);
        this.formGrid.setBackgroundColor(-4210753);
        this.formGrid.setFocusableInTouchMode(true);
        this.bottomView = new ACAduserEnglishLayoutFormGrid(this);
        if (this.isIpad == 1) {
            this.bottomView.setFormColumnCount(12);
        } else {
            this.bottomView.setFormColumnCount(24);
        }
        this.bottomView.setBackgroundColor(-1);
        this.bottomView.setBackground(getViewRectangleBorderDrawable(-5592406, -10053121));
        this.bottomView.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        this.headView = new ACAduserEnglishLayoutFormGrid(this);
        this.headView.setFormColumnCount(24);
        this.headView.setBackgroundColor(-4210753);
        this.headView.setBackground(getViewRectangleBorderDrawable(-5592406, -4210753));
        this.headView.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        this.ScrollGrid = new ScrollView(this);
        this.ScrollGrid.addView(this.formGrid);
        this.ScrollGrid.setId(1);
        this.bottomView.setId(2);
        this.headView.setId(3);
        RelativeLayout.LayoutParams bottomParams = new RelativeLayout.LayoutParams(-1, -2);
        bottomParams.addRule(12);
        RelativeLayout.LayoutParams headParams = new RelativeLayout.LayoutParams(-1, -2);
        headParams.addRule(10);
        RelativeLayout.LayoutParams centerParams = new RelativeLayout.LayoutParams(-2, -2);
        centerParams.addRule(2, this.bottomView.getId());
        centerParams.addRule(3, this.headView.getId());
        this.MainGrid = new RelativeLayout(this);
        this.MainGrid.setBackgroundColor(-4210753);
        this.MainGrid.setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
        this.MainGrid.addView(this.headView, headParams);
        this.MainGrid.addView(this.bottomView, bottomParams);
        this.MainGrid.addView(this.ScrollGrid, centerParams);
        this.MonitorformGrid = new ACAduserEnglishLayoutFormGrid(this);
        this.MonitorformGrid.setFormColumnCount(24);
        this.MonitorformGrid.setBackgroundColor(-4210753);
        this.MonitorformGrid.setFocusable(true);
        this.MonitorformGrid.setFocusableInTouchMode(true);
        this.IdentifyformGrid = new ACAduserEnglishLayoutFormGrid(this);
        this.IdentifyformGrid.setFormColumnCount(24);
        this.IdentifyformGrid.setBackgroundColor(-4210753);
        this.IdentifyformGrid.setFocusable(true);
        this.IdentifyformGrid.setFocusableInTouchMode(true);
        this.ScrollMonitorGrid = new ScrollView(this);
        this.ScrollMonitorGrid.addView(this.MonitorformGrid);
        this.ScrollIdentifyGrid = new ScrollView(this);
        this.ScrollIdentifyGrid.addView(this.IdentifyformGrid);
        this.MonitorGrid = new RelativeLayout(this);
        this.MonitorGrid.setBackgroundColor(-4210753);
        this.MonitorGrid.setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
        this.MonitorGrid.addView(this.ScrollMonitorGrid);
        this.IdentifyGrid = new RelativeLayout(this);
        this.IdentifyGrid.setBackgroundColor(-4210753);
        this.IdentifyGrid.setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
        this.IdentifyGrid.addView(this.ScrollIdentifyGrid);
        MainInterface();
        ShowKellypageMonitor();
        ShowKellypageIdentify();
        ACAduserEnglishAppManager.getInstance().add(this);
        setContentView(R.layout.main);
        this.pager = (ViewPager) findViewById(R.id.viewPager);
        PagerTabStrip pageTab = (PagerTabStrip) findViewById(R.id.viewTab);
        pageTab.setTextColor(-12303224);
        pageTab.setGravity(17);
        pageTab.setBackgroundDrawable(getViewRectangleBorderDrawable(-5592406, -10053121));
        pageTab.setBackgroundColor(-10053121);
        pageTab.setTabIndicatorColor(-16711936);
        this.adapter = new ACAduserEnglishPagerAdapter();
        this.adapter.addSubView(this.MainGrid, "Калибровка");
        this.adapter.addSubView(this.MonitorGrid, "Монитор");
        this.pager.setAdapter(this.adapter);
        this.pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            /* class com.kelly.ACAduserEnglish.ACAduserEnglishKellyPage.AnonymousClass21 */

            @Override // android.support.v4.view.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int state) {
            }

            @Override // android.support.v4.view.ViewPager.OnPageChangeListener
            public void onPageScrolled(int curPage, float slidePercent, int moveDistance) {
            }

            @Override // android.support.v4.view.ViewPager.OnPageChangeListener
            public void onPageSelected(int pageIndex) {
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>enter tab " + pageIndex);
                ACAduserEnglishKellyPage.this.tabIndex = pageIndex;
                if (ACAduserEnglishKellyPage.this.tabIndex == 1) {
                    Thread tempThread = new MonitorThread();
                    tempThread.start();
                    System.out.println(">>>>start kelly monitor Thread");
                    System.out.println(tempThread.getId());
                }
            }
        });
        if (!this.ReadThreadStart) {
            this.ReadThreadStart = true;
            this.deviceKelly.ReadThread();
        }
        new Thread() {
            /* class com.kelly.ACAduserEnglish.ACAduserEnglishKellyPage.AnonymousClass22 */

            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        try {
                            if (!ACAduserEnglishKellyPage.this.isAppOnForeground(ACAduserEnglishKellyPage.this) && ACAduserEnglishKellyPage.this.COMType == 0) {
                                ACAduserEnglishKellyPage.this.closedevice();
                            }
                        } catch (Exception e) {
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        return;
                    }
                }
            }
        }.start();
    }

    public void opendevice() {
        if (!this.deviceKelly.openUsbComDevice(this)) {
            this.ComStatus.setTextColor(-16744448);
            this.ComStatus.setText("Open Failed");
            showLogInfo("Open Device Failed!");
            this.Com_Status = false;
            return;
        }
        int temp_return = 0;
        this.deviceKelly.ETS_TX_CMD = ETS_CODE_VERSION;
        this.deviceKelly.ETS_TX_BYTES = 0;
        for (int j = 0; j < 2; j++) {
            this.deviceKelly.sendcmd();
            temp_return = this.deviceKelly.readcmd();
            if (temp_return == 1) {
                break;
            }
        }
        if (temp_return != 1) {
            this.ComStatus.setTextColor(-16744448);
            this.ComStatus.setText("Open failed");
            showLogInfo("Open Flash failed!");
            return;
        }
        this.ComStatus.setTextColor(-16744448);
        this.ComStatus.setText("Opened");
        this.Com_Status = true;
    }

    public void closedevice() {
        if (this.COMType == 1) {
            this.deviceKelly.closeBluetoothComDevice();
            this.ComStatus.setTextColor(-16744448);
            this.ComStatus.setText("Disconnected");
            this.Com_Status = false;
        } else if (this.Com_Status) {
            this.deviceKelly.closeUsbComDevice();
            this.ComStatus.setTextColor(-16744448);
            this.ComStatus.setText("Closed");
            this.Com_Status = false;
        }
    }

    public void openbluetoothdevice() {
        int i = 0;
        Set<BluetoothDevice> pairedDevices = BluetoothAdapter.getDefaultAdapter().getBondedDevices();
        this.mac_address = new String[pairedDevices.size()];
        this.device_name = new String[pairedDevices.size()];
        if (pairedDevices.size() == 0) {
            showLogInfo("No pairedDevices,please open Bluetooth and pair first!");
        } else if (pairedDevices.size() > 0) {
            for (BluetoothDevice device : pairedDevices) {
                this.device_name[i] = device.getName();
                this.mac_address[i] = device.getAddress();
                i++;
            }
            AlertDialog.Builder deviceList = new AlertDialog.Builder(this);
            String[] item = new String[pairedDevices.size()];
            for (int j = 0; j < pairedDevices.size(); j++) {
                item[j] = String.valueOf(this.device_name[j]) + ":" + this.mac_address[j];
            }
            deviceList.setTitle("PairedDevices:");
            deviceList.setItems(item, new DialogInterface.OnClickListener() {
                /* class com.kelly.ACAduserEnglish.ACAduserEnglishKellyPage.AnonymousClass23 */

                public void onClick(DialogInterface dialog, int whichButton) {
                    ACAduserEnglishKellyPage.this.Mac_address = ACAduserEnglishKellyPage.this.mac_address[whichButton];
                    ACAduserEnglishKellyPage.this.ComStatus.setTextColor(-16744448);
                    ACAduserEnglishKellyPage.this.ComStatus.setText("Connecting");
                    new Handler().postDelayed(new Thread() {
                        /* class com.kelly.ACAduserEnglish.ACAduserEnglishKellyPage.AnonymousClass23.AnonymousClass1 */

                        public void run() {
                            if (!ACAduserEnglishKellyPage.this.deviceKelly.openBluetoothComDevice(ACAduserEnglishKellyPage.this.Mac_address)) {
                                ACAduserEnglishKellyPage.this.ComStatus.setTextColor(-16744448);
                                ACAduserEnglishKellyPage.this.ComStatus.setText("Connect failed!");
                                ACAduserEnglishKellyPage.this.showLogInfo("Connect Bluetooth failed!");
                                ACAduserEnglishKellyPage.this.Com_Status = false;
                                return;
                            }
                            ACAduserEnglishKellyPage.this.deviceKelly.ETS_TX_CMD = ACAduserEnglishKellyPage.ETS_CODE_VERSION;
                            ACAduserEnglishKellyPage.this.deviceKelly.ETS_TX_BYTES = 0;
                            ACAduserEnglishKellyPage.this.deviceKelly.sendcmd();
                            if (ACAduserEnglishKellyPage.this.deviceKelly.readcmd() != 1) {
                                ACAduserEnglishKellyPage.this.ComStatus.setTextColor(-16744448);
                                ACAduserEnglishKellyPage.this.ComStatus.setText("Connect failed!");
                                ACAduserEnglishKellyPage.this.showLogInfo("open Flash failed!");
                                return;
                            }
                            ACAduserEnglishKellyPage.this.ComStatus.setTextColor(-16744448);
                            ACAduserEnglishKellyPage.this.ComStatus.setText("Connected");
                            ACAduserEnglishKellyPage.this.Com_Status = true;
                        }
                    }, 10);
                }
            });
            deviceList.create().show();
        }
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode != 4) {
            return true;
        }
        new ACAduserEnglishExitConfirm(this).sendEmptyMessage(0);
        return true;
    }

    private void addFormGridView(String title, View view, int colIndex, int rowIndex) {
        EditText label = new EditText(this);
        label.setText(title);
        label.setEnabled(false);
        label.setBackgroundColor(-4210753);
        label.setGravity(19);
        label.setTextSize(15.0f);
        label.setTextColor(View.MEASURED_STATE_MASK);
        if (this.isIpad == 1) {
            this.formGrid.addView(label, new ACAduserEnglishLayoutFormGrid.LayoutParams(colIndex, rowIndex, 5, 1));
            this.formGrid.addView(view, new ACAduserEnglishLayoutFormGrid.LayoutParams(colIndex + 5, rowIndex, 3, 1));
            return;
        }
        this.formGrid.addView(label, new ACAduserEnglishLayoutFormGrid.LayoutParams(colIndex, rowIndex, 7, 1));
        this.formGrid.addView(view, new ACAduserEnglishLayoutFormGrid.LayoutParams(colIndex + 7, rowIndex, 5, 1));
    }

    private void addMonitorFormGridView(String title, View view, int colIndex, int rowIndex) {
        EditText label = new EditText(this);
        label.setText(title);
        label.setBackgroundColor(-4210753);
        label.setEnabled(false);
        label.setGravity(19);
        label.setTextColor(View.MEASURED_STATE_MASK);
        this.MonitorformGrid.addView(label, new ACAduserEnglishLayoutFormGrid.LayoutParams(colIndex, rowIndex, 5, 1));
        this.MonitorformGrid.addView(view, new ACAduserEnglishLayoutFormGrid.LayoutParams(colIndex + 5, rowIndex, 3, 1));
    }

    private void addIdentifyFormGridView(String title, View view, int colIndex, int rowIndex) {
        TextView label = new TextView(this);
        if (this.isIpad == 1) {
            label = new EditText(this);
        }
        label.setText(title);
        label.setBackgroundColor(-4210753);
        label.setEnabled(false);
        label.setGravity(19);
        label.setTextColor(View.MEASURED_STATE_MASK);
        this.IdentifyformGrid.addView(label, new ACAduserEnglishLayoutFormGrid.LayoutParams(colIndex, rowIndex, 5, 1));
        this.IdentifyformGrid.addView(view, new ACAduserEnglishLayoutFormGrid.LayoutParams(colIndex + 5, rowIndex, 3, 1));
    }

    /* JADX DEBUG: Multi-variable search result rejected for r7v25, resolved type: int[] */
    /* JADX DEBUG: Multi-variable search result rejected for r9v7, resolved type: byte[] */
    /* JADX DEBUG: Multi-variable search result rejected for r9v8, resolved type: byte */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void readDataFromKelly() {
        int Read_ok = 1;
        if (this.DEBUG) {
            this.deviceKelly.resetLogBuffer();
        }
        if (!this.RW_Debug) {
            if (!this.Com_Status) {
                showLogInfo("Device is not opened!Please open device first!");
                return;
            }
            try {
                this.deviceKelly.ETS_TX_CMD = -15;
                this.deviceKelly.ETS_TX_BYTES = 0;
                loop0:
                for (int j = 0; j < 2; j++) {
                    this.deviceKelly.sendcmd();
                    for (int i = 0; i < 5; i++) {
                        if (this.deviceKelly.readcmd() == 1) {
                            break loop0;
                        }
                    }
                }
            } catch (Exception e) {
                showLogInfo("Open Flash Internal Error!");
            }
            int temp_return = 0;
            for (int i2 = 0; i2 < 32; i2++) {
                try {
                    this.deviceKelly.ETS_TX_CMD = -14;
                    this.deviceKelly.ETS_TX_BYTES = 3;
                    this.deviceKelly.ETS_TX_DATA[0] = (byte) (i2 * 16);
                    this.deviceKelly.ETS_TX_DATA[1] = 16;
                    this.deviceKelly.ETS_TX_DATA[2] = (byte) ((i2 * 16) >> 8);
                    for (int j2 = 0; j2 < 2; j2++) {
                        this.deviceKelly.sendcmd();
                        for (int k = 0; k < 5; k++) {
                            temp_return = this.deviceKelly.readcmd();
                            if (temp_return == 1) {
                                break;
                            }
                        }
                    }
                    if (temp_return != 1) {
                        showLogInfo("Data Read Error,Maybe the controller no power-on or wire-disconnect!");
                        return;
                    }
                    if (temp_return == 1) {
                        Read_ok = 1;
                    }
                    if (Read_ok == 1) {
                        for (int j3 = 0; j3 < 16; j3++) {
                            DataValue[(i2 * 16) + j3] = this.deviceKelly.ETS_RX_DATA[j3];
                            if (DataValue[(i2 * 16) + j3] < 0) {
                                int[] iArr = DataValue;
                                int i3 = (i2 * 16) + j3;
                                iArr[i3] = iArr[i3] + 256;
                            }
                        }
                    }
                } catch (Exception e2) {
                }
            }
        }
        try {
            this.formGrid.removeAllViews();
            this.headView.removeAllViews();
            ShowKellypageCalibration();
        } catch (Exception e3) {
            showLogInfo("Interface Show Error!Please Try Again!");
        }
        if (this.DEBUG) {
            showTextView(this.ceshi, this.deviceKelly.getLogBufferInfo());
        }
        this.sendBtn.setEnabled(true);
        this.sendBtn.setTextColor(View.MEASURED_STATE_MASK);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void sendDataToKelly() {
        int Write_ok = 1;
        int temp_return = 0;
        if (this.DEBUG) {
            this.deviceKelly.resetLogBuffer();
        }
        if (!this.RW_Debug) {
            if (!this.Com_Status) {
                showLogInfo("Device is not opened!Please open device first!");
                return;
            }
            try {
                this.deviceKelly.ETS_TX_CMD = -15;
                this.deviceKelly.ETS_TX_BYTES = 0;
                loop0:
                for (int j = 0; j < 2; j++) {
                    this.deviceKelly.sendcmd();
                    for (int i = 0; i < 5; i++) {
                        temp_return = this.deviceKelly.readcmd();
                        if (temp_return == 1) {
                            break loop0;
                        }
                    }
                }
                if (temp_return != 1) {
                    showLogInfo("Open Flash Failed!");
                    return;
                }
            } catch (Exception e) {
                showLogInfo("Open Flash Internal Error!");
            }
        }
        if (UpdataValue() == 1) {
            if (!this.RW_Debug) {
                int i2 = 0;
                while (i2 < 40) {
                    if (i2 < 39) {
                        this.deviceKelly.ETS_TX_CMD = -13;
                        this.deviceKelly.ETS_TX_BYTES = 16;
                        this.deviceKelly.ETS_TX_DATA[0] = (byte) (i2 * 13);
                        this.deviceKelly.ETS_TX_DATA[1] = 13;
                        this.deviceKelly.ETS_TX_DATA[2] = (byte) ((i2 * 13) >> 8);
                        for (int j2 = 0; j2 < 13; j2++) {
                            this.deviceKelly.ETS_TX_DATA[j2 + 3] = (byte) DataValue[(i2 * 13) + j2];
                        }
                        for (int j3 = 0; j3 < 3; j3++) {
                            try {
                                this.deviceKelly.sendcmd();
                                while (0 < 10) {
                                    temp_return = this.deviceKelly.readcmd();
                                    if (temp_return == 1) {
                                        break;
                                    }
                                    i2++;
                                }
                            } catch (Exception e2) {
                                showLogInfo("Write Error!");
                            }
                        }
                        if (temp_return != 1) {
                            Write_ok = 0;
                        }
                    } else if (i2 == 39) {
                        try {
                            this.deviceKelly.ETS_TX_CMD = -13;
                            this.deviceKelly.ETS_TX_BYTES = 8;
                            this.deviceKelly.ETS_TX_DATA[0] = -5;
                            this.deviceKelly.ETS_TX_DATA[1] = 5;
                            this.deviceKelly.ETS_TX_DATA[2] = 1;
                            for (int j4 = 507; j4 < 512; j4++) {
                                this.deviceKelly.ETS_TX_DATA[(j4 - 507) + 3] = (byte) DataValue[j4];
                            }
                            for (int j5 = 0; j5 < 3; j5++) {
                                this.deviceKelly.sendcmd();
                                while (0 < 10) {
                                    temp_return = this.deviceKelly.readcmd();
                                    if (temp_return == 1) {
                                        break;
                                    }
                                    i2++;
                                }
                            }
                            if (temp_return != 1) {
                                Write_ok = 0;
                            }
                        } catch (Exception e3) {
                            showLogInfo("Write Error!");
                        }
                    }
                    i2++;
                }
                this.deviceKelly.ETS_TX_CMD = -12;
                this.deviceKelly.ETS_TX_BYTES = 0;
                this.deviceKelly.sendcmd();
                for (int i3 = 0; i3 < 30 && (temp_return = this.deviceKelly.readcmd()) != 1; i3++) {
                }
                if (temp_return != 1) {
                    showLogInfo("Write To Flash Error!");
                    this.deviceKelly.closeUsbComDevice();
                    return;
                } else if (Write_ok == 0) {
                    showLogInfo("Write Data Error!");
                    return;
                }
            }
            if (this.DEBUG) {
                showTextView(this.ceshi, this.deviceKelly.getLogBufferInfo());
            }
            this.formGrid.removeAllViews();
            this.headView.removeAllViews();
            showLogInfo("Data Write Completely!");
            this.sendBtn.setEnabled(false);
            this.sendBtn.setTextColor(-7829368);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean isAppOnForeground(Context context) {
        List<ActivityManager.RunningAppProcessInfo> appProcesses = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE)).getRunningAppProcesses();
        String packageName = getPackageName();
        if (appProcesses == null) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(packageName) && appProcess.importance == 100) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void showLogInfo(final String txt) {
        this.formGrid.post(new Thread() {
            /* class com.kelly.ACAduserEnglish.ACAduserEnglishKellyPage.AnonymousClass24 */

            public void run() {
                new ACAduserEnglishDialogMessage(ACAduserEnglishKellyPage.this, txt).sendEmptyMessage(0);
            }
        });
    }

    private void showLogInfo3(final String txt) {
        this.IdentifyformGrid.post(new Thread() {
            /* class com.kelly.ACAduserEnglish.ACAduserEnglishKellyPage.AnonymousClass25 */

            public void run() {
                new ACAduserEnglishDialogMessage(ACAduserEnglishKellyPage.this, txt).sendEmptyMessage(0);
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void showTextView(final TextView view, final String txt) {
        view.post(new Thread() {
            /* class com.kelly.ACAduserEnglish.ACAduserEnglishKellyPage.AnonymousClass26 */

            public void run() {
                view.setText(txt == null ? "" : txt);
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void viewsetBackgroundColor(final EditText view, final int color) {
        this.MonitorformGrid.post(new Thread() {
            /* class com.kelly.ACAduserEnglish.ACAduserEnglishKellyPage.AnonymousClass27 */

            public void run() {
                view.setBackgroundColor(color);
            }
        });
    }

    public static Drawable getViewRectangleBorderDrawable(int strokeColor, int fillColor) {
        GradientDrawable graDrawable = new GradientDrawable();
        graDrawable.setStroke(1, strokeColor);
        graDrawable.setColor(fillColor);
        graDrawable.setCornerRadius(0.0f);
        return graDrawable;
    }

    private void addReadzeroFormGridView(String title, View view, View view2, int colIndex, int rowIndex) {
        EditText label = new EditText(this);
        label.setText(title);
        label.setBackgroundColor(-4210753);
        label.setEnabled(false);
        label.setGravity(19);
        label.setTextColor(View.MEASURED_STATE_MASK);
        this.ReadzeroformGrid.addView(label, new ACAduserEnglishLayoutFormGrid.LayoutParams(colIndex, rowIndex, 6, 1));
        this.ReadzeroformGrid.addView(view, new ACAduserEnglishLayoutFormGrid.LayoutParams(colIndex + 6, rowIndex, 4, 1));
        this.ReadzeroformGrid.addView(view2, new ACAduserEnglishLayoutFormGrid.LayoutParams(colIndex + 10, rowIndex, 6, 1));
    }

    /* JADX DEBUG: Multi-variable search result rejected for r6v21, resolved type: byte[] */
    /* JADX DEBUG: Multi-variable search result rejected for r6v22, resolved type: byte */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void ReadCal() {
        int temp_return = 0;
        int read_ok = 1;
        this.deviceKelly.ETS_TX_CMD = ETS_GET_PMSM_PARM;
        this.deviceKelly.ETS_TX_BYTES = 0;
        this.deviceKelly.sendcmd();
        if (this.IdentifyDEBUG) {
            this.deviceKelly.resetLogBuffer();
        }
        for (int i = 0; i < 10 && (temp_return = this.deviceKelly.readcmd()) != 1; i++) {
        }
        if (temp_return == 1 && this.deviceKelly.ETS_RX_CMD == 75) {
            int[] tempInt = new int[13];
            for (int i2 = 0; i2 < 13; i2++) {
                tempInt[i2] = this.deviceKelly.ETS_RX_DATA[i2];
                if (tempInt[i2] < 0) {
                    tempInt[i2] = tempInt[i2] + 256;
                }
            }
            for (int i3 = 0; i3 < ACAduserEnglishSetting.Identify_Name_Array.length; i3++) {
                showTextView(this.Cal_View_Array[i3], ACAduserEnglishByteUtil.printIntArrayString(tempInt, Integer.parseInt(ACAduserEnglishSetting.Identify_Name_Array[i3][0]), Integer.parseInt(ACAduserEnglishSetting.Identify_Name_Array[i3][1]), Integer.parseInt(ACAduserEnglishSetting.Identify_Name_Array[i3][2]), ACAduserEnglishSetting.Identify_Name_Array[i3][3]));
            }
            if (this.IdentifyDEBUG) {
                showTextView(this.Identifyceshi, this.deviceKelly.getLogBufferInfo());
            }
        } else {
            read_ok = 0;
        }
        if (read_ok == 0) {
            showLogInfo("Read Failed,Please check the wire and power! \nDon't write data and run Indentify before data read!");
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void ChangeCal() {
        int Write_ok = 1;
        int temp_return = 0;
        if (this.IdentifyDEBUG) {
            this.deviceKelly.resetLogBuffer();
        }
        this.deviceKelly.ETS_TX_CMD = -15;
        this.deviceKelly.ETS_TX_BYTES = 0;
        loop0:
        for (int j = 0; j < 2; j++) {
            this.deviceKelly.sendcmd();
            for (int i = 0; i < 10; i++) {
                temp_return = this.deviceKelly.readcmd();
                if (temp_return == 1) {
                    break loop0;
                }
            }
        }
        if (temp_return != 1) {
            Write_ok = 0;
        }
        if (Write_ok == 1) {
            this.deviceKelly.ETS_TX_CMD = ETS_WRITE_PMSM_PARM;
            this.deviceKelly.ETS_TX_BYTES = 13;
            int[] tempInt = new int[13];
            for (int i2 = 0; i2 < 12; i2++) {
                String result = this.Cal_View_Array[i2].getText().toString();
                if (!(result == null || ACAduserEnglishByteUtil.printStringArrayInt(tempInt, Integer.parseInt(ACAduserEnglishSetting.Identify_Name_Array[i2][0]), Integer.parseInt(ACAduserEnglishSetting.Identify_Name_Array[i2][1]), Integer.parseInt(ACAduserEnglishSetting.Identify_Name_Array[i2][2]), ACAduserEnglishSetting.Identify_Name_Array[i2][3], result) == 1)) {
                    showLogInfo3("Transform Error!");
                }
            }
            for (int i3 = 0; i3 < 13; i3++) {
                this.deviceKelly.ETS_TX_DATA[i3] = (byte) tempInt[i3];
                this.deviceKelly.logTxtSb.append(new StringBuilder().append((int) this.deviceKelly.ETS_TX_DATA[i3]).toString());
            }
            this.deviceKelly.sendcmd();
            for (int i4 = 0; i4 < 10 && (temp_return = this.deviceKelly.readcmd()) != 1; i4++) {
            }
            if (temp_return != 1) {
            }
            this.deviceKelly.ETS_TX_CMD = -12;
            this.deviceKelly.ETS_TX_BYTES = 0;
            this.deviceKelly.sendcmd();
            for (int i5 = 0; i5 < 10 && (temp_return = this.deviceKelly.readcmd()) != 1; i5++) {
            }
            if (temp_return != 1) {
                Write_ok = 0;
            } else {
                Write_ok = 1;
            }
        }
        if (Write_ok == 1) {
            showLogInfo3("Change  Succeed!");
        } else {
            showLogInfo3("Change Failed!");
        }
        if (this.IdentifyDEBUG) {
            showTextView(this.Identifyceshi, this.deviceKelly.getLogBufferInfo());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void EntryIdentify() {
        if (this.IdentifyDEBUG) {
            this.deviceKelly.resetLogBuffer();
        }
        int temp_return = 0;
        int i = 0;
        while (true) {
            if (i >= 5) {
                break;
            }
            this.deviceKelly.ETS_TX_CMD = ETS_ENTRY_IDENTIFY;
            this.deviceKelly.ETS_TX_BYTES = 0;
            this.deviceKelly.sendcmd();
            this.deviceKelly.readcmd();
            this.deviceKelly.ETS_TX_CMD = ETS_CHECK_IDENTIFY_STATUS;
            this.deviceKelly.ETS_TX_BYTES = 0;
            this.deviceKelly.sendcmd();
            for (int j = 0; j < 10 && (temp_return = this.deviceKelly.readcmd()) != 1; j++) {
            }
            if (temp_return == 1 && this.deviceKelly.ETS_RX_CMD == 68 && this.deviceKelly.ETS_RX_DATA[0] == -86) {
                this.Identify_Entry = 1;
                this.Identify_Quit = 0;
                break;
            }
            i++;
        }
        if (this.Identify_Entry == 1) {
            this.Identify_Flag = 1;
            showTextView(this.Tips_View_Array[0], "Entry Identify\nPlease send CMD!");
            return;
        }
        this.Identify_Flag = 0;
        showTextView(this.Tips_View_Array[0], "Entry Failed!");
        if (this.IdentifyDEBUG) {
            showTextView(this.Identifyceshi, this.deviceKelly.getLogBufferInfo());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void QuitIdentify() {
        int temp_return = 0;
        if (this.IdentifyDEBUG) {
            this.deviceKelly.resetLogBuffer();
        }
        int i = 0;
        while (true) {
            if (i >= 5) {
                break;
            }
            this.deviceKelly.ETS_TX_CMD = ETS_QUIT_IDENTIFY;
            this.deviceKelly.ETS_TX_BYTES = 0;
            this.deviceKelly.sendcmd();
            this.deviceKelly.readcmd();
            this.deviceKelly.ETS_TX_CMD = ETS_CHECK_IDENTIFY_STATUS;
            this.deviceKelly.ETS_TX_BYTES = 0;
            this.deviceKelly.sendcmd();
            for (int j = 0; j < 10 && (temp_return = this.deviceKelly.readcmd()) != 1; j++) {
            }
            if (temp_return == 1 && this.deviceKelly.ETS_RX_CMD == 68 && this.deviceKelly.ETS_RX_DATA[0] == 85) {
                this.Identify_Quit = 1;
                this.Identify_Entry = 0;
                break;
            }
            i++;
        }
        if (this.Identify_Quit == 1) {
            this.Identify_Flag = 1;
            showTextView(this.Tips_View_Array[0], "Quit Succeed!");
            return;
        }
        this.Identify_Flag = 0;
        showTextView(this.Tips_View_Array[0], "Quit Failed!");
        if (this.IdentifyDEBUG) {
            showTextView(this.Identifyceshi, this.deviceKelly.getLogBufferInfo());
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void IdentifyResolver() {
        int temp_return = 0;
        if (this.Resolver_Idf_Str_doing_flag == 0 && this.Identify_Status == 0) {
            this.deviceKelly.ETS_TX_CMD = ETS_GET_RESOLVER_INIT_ANGLE;
            this.deviceKelly.ETS_TX_BYTES = 1;
            this.deviceKelly.ETS_TX_DATA[0] = 1;
            this.deviceKelly.sendcmd();
            for (int j = 0; j < 10 && (temp_return = this.deviceKelly.readcmd()) != 1; j++) {
            }
            if (temp_return == 1 && this.deviceKelly.ETS_RX_CMD == 77) {
                if (this.deviceKelly.ETS_RX_DATA[0] != 1) {
                    showTextView(this.Tips_View_Array[1], "Wait for other operation completely!");
                    this.Resolver_Idf_Str_doing_flag = 0;
                    this.Resolver_Idf_Str_finish_flag = 0;
                } else if (this.deviceKelly.ETS_RX_DATA[1] == 0) {
                    showTextView(this.Tips_View_Array[1], "Please Enter Identify First!");
                    this.Resolver_Idf_Str_doing_flag = 0;
                    return;
                } else if (this.deviceKelly.ETS_RX_DATA[2] == 1) {
                    showTextView(this.Tips_View_Array[1], "Start Identify!");
                    this.Resolver_Idf_Str_doing_flag = 1;
                    this.Resolver_Idf_Str_finish_flag = 0;
                    this.Identify_Status = 1;
                    new IdentifyThread().start();
                    System.out.println(">>>>start kelly Identify Thread");
                } else {
                    showTextView(this.Tips_View_Array[1], "Wait for other operation completely!");
                    this.Resolver_Idf_Str_doing_flag = 0;
                    this.Resolver_Idf_Str_finish_flag = 0;
                }
            }
        }
        if (this.Identify_Status != 0) {
            showTextView(this.Tips_View_Array[1], "Wait for other operation completely!");
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void IdentifyHall() {
        int temp_return = 0;
        if (this.Hall_Idf_Str_doing_flag == 0 && this.Identify_Status == 0) {
            this.deviceKelly.ETS_TX_CMD = ETS_GET_HALL_SEQUENCE;
            this.deviceKelly.ETS_TX_BYTES = 1;
            this.deviceKelly.ETS_TX_DATA[0] = 1;
            this.deviceKelly.sendcmd();
            for (int j = 0; j < 10 && (temp_return = this.deviceKelly.readcmd()) != 1; j++) {
            }
            if (temp_return == 1 && this.deviceKelly.ETS_RX_CMD == 78) {
                if (this.deviceKelly.ETS_RX_DATA[0] != 1) {
                    showTextView(this.Tips_View_Array[2], "Wait for other operation completely!");
                    this.Hall_Idf_Str_doing_flag = 0;
                    this.Hall_Idf_Str_finish_flag = 0;
                } else if (this.deviceKelly.ETS_RX_DATA[1] == 0) {
                    showTextView(this.Tips_View_Array[2], "Please Enter Identify First!");
                    this.Hall_Idf_Str_doing_flag = 0;
                    return;
                } else if (this.deviceKelly.ETS_RX_DATA[2] == 1) {
                    showTextView(this.Tips_View_Array[2], "Start Identify!");
                    this.Hall_Idf_Str_doing_flag = 1;
                    this.Hall_Idf_Str_finish_flag = 0;
                    this.Identify_Status = 2;
                    new IdentifyThread().start();
                    System.out.println(">>>>start kelly Identify Thread");
                } else {
                    showTextView(this.Tips_View_Array[2], "Wait for other operation completely!");
                    this.Hall_Idf_Str_doing_flag = 0;
                    this.Hall_Idf_Str_finish_flag = 0;
                }
            }
        }
        if (this.Identify_Status != 0) {
            showTextView(this.Tips_View_Array[2], "Wait for other operation completely!");
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void WriteCal() {
        String result;
        int Write_ok = 1;
        int temp_return = 0;
        if (this.IdentifyDEBUG) {
            this.deviceKelly.resetLogBuffer();
        }
        this.deviceKelly.ETS_TX_CMD = -15;
        this.deviceKelly.ETS_TX_BYTES = 0;
        loop0:
        for (int j = 0; j < 2; j++) {
            this.deviceKelly.sendcmd();
            for (int i = 0; i < 10; i++) {
                temp_return = this.deviceKelly.readcmd();
                if (temp_return == 1) {
                    break loop0;
                }
            }
        }
        if (temp_return != 1) {
            Write_ok = 0;
        }
        if (Write_ok == 1) {
            this.deviceKelly.ETS_TX_CMD = ETS_WRITE_PMSM_PARM;
            this.deviceKelly.ETS_TX_BYTES = 13;
            int[] tempInt = new int[13];
            for (int i2 = 0; i2 < 12; i2++) {
                if (!this.Identify_View_Array[i2].getText().toString().equals("")) {
                    result = this.Identify_View_Array[i2].getText().toString();
                } else if (!this.Cal_View_Array[i2].getText().toString().equals("")) {
                    result = this.Cal_View_Array[i2].getText().toString();
                } else {
                    return;
                }
                if (result != null) {
                    ACAduserEnglishByteUtil.printStringArrayInt(tempInt, Integer.parseInt(ACAduserEnglishSetting.Identify_Name_Array[i2][0]), Integer.parseInt(ACAduserEnglishSetting.Identify_Name_Array[i2][1]), Integer.parseInt(ACAduserEnglishSetting.Identify_Name_Array[i2][2]), ACAduserEnglishSetting.Identify_Name_Array[i2][3], result);
                }
            }
            for (int i3 = 0; i3 < 13; i3++) {
                this.deviceKelly.ETS_TX_DATA[i3] = (byte) tempInt[i3];
                this.deviceKelly.logTxtSb.append(new StringBuilder().append((int) this.deviceKelly.ETS_TX_DATA[i3]).toString());
            }
            this.deviceKelly.sendcmd();
            for (int i4 = 0; i4 < 10 && (temp_return = this.deviceKelly.readcmd()) != 1; i4++) {
            }
            if (temp_return != 1) {
            }
            this.deviceKelly.ETS_TX_CMD = -12;
            this.deviceKelly.ETS_TX_BYTES = 0;
            this.deviceKelly.sendcmd();
            for (int i5 = 0; i5 < 10 && (temp_return = this.deviceKelly.readcmd()) != 1; i5++) {
            }
            if (temp_return != 1) {
                Write_ok = 0;
            } else {
                Write_ok = 1;
            }
        }
        if (Write_ok == 1) {
            showLogInfo3("Write Succeed!");
        } else {
            showLogInfo3("Write Failed!");
        }
        if (this.IdentifyDEBUG) {
            showTextView(this.Identifyceshi, this.deviceKelly.getLogBufferInfo());
        }
    }

    /* access modifiers changed from: package-private */
    public class IdentifyThread extends Thread {
        IdentifyThread() {
        }

        public void run() {
            int temp_return = 0;
            while (ACAduserEnglishKellyPage.this.tabIndex == 2) {
                try {
                    if (ACAduserEnglishKellyPage.this.Resolver_Idf_Str_doing_flag == 1 && ACAduserEnglishKellyPage.this.Identify_Status == 1) {
                        ACAduserEnglishKellyPage.this.deviceKelly.ETS_TX_CMD = ACAduserEnglishKellyPage.ETS_GET_RESOLVER_INIT_ANGLE;
                        ACAduserEnglishKellyPage.this.deviceKelly.ETS_TX_BYTES = 1;
                        ACAduserEnglishKellyPage.this.deviceKelly.ETS_TX_DATA[0] = 2;
                        ACAduserEnglishKellyPage.this.deviceKelly.sendcmd();
                        for (int j = 0; j < 10; j++) {
                            temp_return = ACAduserEnglishKellyPage.this.deviceKelly.readcmd();
                            if (temp_return == 1) {
                                break;
                            }
                        }
                        if (temp_return == 1 && ACAduserEnglishKellyPage.this.deviceKelly.ETS_RX_CMD == 77 && ACAduserEnglishKellyPage.this.deviceKelly.ETS_RX_DATA[0] == 2) {
                            if (ACAduserEnglishKellyPage.this.deviceKelly.ETS_RX_DATA[1] == 2) {
                                ACAduserEnglishKellyPage.this.showTextView(ACAduserEnglishKellyPage.this.Identify_View_Array[0], new StringBuilder().append((int) ACAduserEnglishKellyPage.this.deviceKelly.ETS_RX_DATA[2]).toString());
                                ACAduserEnglishKellyPage.this.showTextView(ACAduserEnglishKellyPage.this.Identify_View_Array[1], new StringBuilder().append((ACAduserEnglishKellyPage.this.deviceKelly.ETS_RX_DATA[3] << 8) + ACAduserEnglishKellyPage.this.deviceKelly.ETS_RX_DATA[4]).toString());
                                ACAduserEnglishKellyPage.this.Resolver_Idf_Str_doing_flag = 0;
                                ACAduserEnglishKellyPage.this.Resolver_Idf_Str_finish_flag = 1;
                                ACAduserEnglishKellyPage.this.Identify_Status = 0;
                                ACAduserEnglishKellyPage.this.showTextView(ACAduserEnglishKellyPage.this.Tips_View_Array[1], "Completely!");
                                return;
                            } else if (ACAduserEnglishKellyPage.this.deviceKelly.ETS_RX_DATA[1] == 1) {
                                ACAduserEnglishKellyPage.this.showTextView(ACAduserEnglishKellyPage.this.Tips_View_Array[1], "Running");
                                ACAduserEnglishKellyPage.this.Resolver_Idf_Str_doing_flag = 1;
                                ACAduserEnglishKellyPage.this.Identify_Status = 1;
                            } else {
                                ACAduserEnglishKellyPage.this.showTextView(ACAduserEnglishKellyPage.this.Tips_View_Array[1], "Don't Start!");
                            }
                        }
                    }
                    if (ACAduserEnglishKellyPage.this.Hall_Idf_Str_doing_flag == 1 && ACAduserEnglishKellyPage.this.Identify_Status == 2) {
                        ACAduserEnglishKellyPage.this.deviceKelly.ETS_TX_CMD = ACAduserEnglishKellyPage.ETS_GET_HALL_SEQUENCE;
                        ACAduserEnglishKellyPage.this.deviceKelly.ETS_TX_BYTES = 1;
                        ACAduserEnglishKellyPage.this.deviceKelly.ETS_TX_DATA[0] = 2;
                        ACAduserEnglishKellyPage.this.deviceKelly.sendcmd();
                        for (int j2 = 0; j2 < 10; j2++) {
                            temp_return = ACAduserEnglishKellyPage.this.deviceKelly.readcmd();
                            if (temp_return == 1) {
                                break;
                            }
                        }
                        if (temp_return == 1 && ACAduserEnglishKellyPage.this.deviceKelly.ETS_RX_CMD == 78 && ACAduserEnglishKellyPage.this.deviceKelly.ETS_RX_DATA[0] == 2) {
                            if (ACAduserEnglishKellyPage.this.deviceKelly.ETS_RX_DATA[1] == 2) {
                                for (int i = 2; i < 12; i++) {
                                    ACAduserEnglishKellyPage.this.showTextView(ACAduserEnglishKellyPage.this.Identify_View_Array[i], new StringBuilder().append((int) ACAduserEnglishKellyPage.this.deviceKelly.ETS_RX_DATA[i]).toString());
                                }
                                ACAduserEnglishKellyPage.this.Hall_Idf_Str_doing_flag = 0;
                                ACAduserEnglishKellyPage.this.Hall_Idf_Str_finish_flag = 1;
                                ACAduserEnglishKellyPage.this.Identify_Status = 0;
                                ACAduserEnglishKellyPage.this.showTextView(ACAduserEnglishKellyPage.this.Tips_View_Array[2], "Completely!");
                                return;
                            } else if (ACAduserEnglishKellyPage.this.deviceKelly.ETS_RX_DATA[1] == 1) {
                                ACAduserEnglishKellyPage.this.showTextView(ACAduserEnglishKellyPage.this.Tips_View_Array[2], "Running");
                                ACAduserEnglishKellyPage.this.Hall_Idf_Str_doing_flag = 1;
                                ACAduserEnglishKellyPage.this.Identify_Status = 2;
                            } else {
                                ACAduserEnglishKellyPage.this.showTextView(ACAduserEnglishKellyPage.this.Tips_View_Array[2], "Don't Start!");
                            }
                        }
                    }
                    Thread.sleep(1000);
                } catch (Exception e) {
                }
            }
            System.out.println(">>>>exit kelly Identify");
        }
    }

    class MonitorThread extends Thread {
        MonitorThread() {
        }

        /* JADX DEBUG: Multi-variable search result rejected for r9v101, resolved type: int[] */
        /* JADX DEBUG: Multi-variable search result rejected for r11v19, resolved type: byte[] */
        /* JADX DEBUG: Multi-variable search result rejected for r11v20, resolved type: byte */
        /* JADX WARN: Multi-variable type inference failed */
        public void run() {
            int Error_count = 0;
            while (ACAduserEnglishKellyPage.this.Monitor_Read_Enable && ACAduserEnglishKellyPage.this.tabIndex == 1) {
                try {
                    if (ACAduserEnglishKellyPage.this.tabIndex == 1) {
                        if (ACAduserEnglishKellyPage.this.Com_Status) {
                            for (int i = 0; i < 3; i++) {
                                if (i == 0) {
                                    try {
                                        ACAduserEnglishKellyPage.this.deviceKelly.ETS_TX_CMD = (byte) (i + 58);
                                    } catch (Exception e) {
                                    }
                                } else {
                                    ACAduserEnglishKellyPage.this.deviceKelly.ETS_TX_CMD = (byte) (i + 58);
                                }
                                ACAduserEnglishKellyPage.this.deviceKelly.ETS_TX_BYTES = 0;
                                ACAduserEnglishKellyPage.this.deviceKelly.sendcmd();
                                if (ACAduserEnglishKellyPage.this.deviceKelly.readcmd() == 1) {
                                    for (int j = 0; j < 16; j++) {
                                        ACAduserEnglishKellyPage.this.MonitorDataValue[(i * 16) + j] = ACAduserEnglishKellyPage.this.deviceKelly.ETS_RX_DATA[j];
                                        if (ACAduserEnglishKellyPage.this.MonitorDataValue[(i * 16) + j] < 0) {
                                            int[] iArr = ACAduserEnglishKellyPage.this.MonitorDataValue;
                                            int i2 = (i * 16) + j;
                                            iArr[i2] = iArr[i2] + 256;
                                        }
                                    }
                                    Error_count = 0;
                                } else {
                                    Error_count++;
                                    if (Error_count >= 5) {
                                        for (int j2 = 0; j2 < 48; j2++) {
                                            ACAduserEnglishKellyPage.this.MonitorDataValue[j2] = 0;
                                        }
                                        Error_count = 0;
                                    }
                                }
                            }
                        }
                        for (int i3 = 0; i3 < ACAduserEnglishSetting.Monitor_Value_Array.length; i3++) {
                            if (Integer.parseInt(ACAduserEnglishSetting.Monitor_Value_Array[i3][7]) == 1) {
                                int value_offset = Integer.parseInt(ACAduserEnglishSetting.Monitor_Value_Array[i3][0]);
                                if (Integer.parseInt(ACAduserEnglishSetting.Monitor_Value_Array[i3][1]) != 2) {
                                    int temp = Integer.parseInt(ACAduserEnglishSetting.Monitor_Value_Array[i3][0]);
                                    if (ACAduserEnglishKellyPage.this.MonitorDataValueBackup[temp] != ACAduserEnglishKellyPage.this.MonitorDataValue[temp]) {
                                        ACAduserEnglishKellyPage.this.MonitorshowStr[i3] = new StringBuilder().append(ACAduserEnglishKellyPage.this.MonitorDataValue[temp]).toString();
                                        ACAduserEnglishKellyPage.this.Monitor_View_Array[i3].postInvalidate();
                                        ACAduserEnglishKellyPage.this.MonitorDataValueBackup[temp] = ACAduserEnglishKellyPage.this.MonitorDataValue[temp];
                                    }
                                } else if (Integer.parseInt(ACAduserEnglishSetting.Monitor_Value_Array[i3][0]) == Integer.parseInt(ACAduserEnglishSetting.Monitor_Value_Array[0][0])) {
                                    if (ACAduserEnglishKellyPage.this.MonitorDataValueBackup[value_offset] != ACAduserEnglishKellyPage.this.MonitorDataValue[value_offset] || ACAduserEnglishKellyPage.this.MonitorDataValueBackup[value_offset + 1] != ACAduserEnglishKellyPage.this.MonitorDataValue[value_offset + 1]) {
                                        int Error_Struct = (ACAduserEnglishKellyPage.this.MonitorDataValue[value_offset] * 256) + ACAduserEnglishKellyPage.this.MonitorDataValue[value_offset + 1];
                                        if (Error_Struct == 0) {
                                            ACAduserEnglishKellyPage.this.MonitorshowStr[i3] = "";
                                            ACAduserEnglishKellyPage.this.viewsetBackgroundColor(ACAduserEnglishKellyPage.this.Monitor_View_Array[i3], -2894893);
                                            ACAduserEnglishKellyPage.this.Monitor_View_Array[i3].postInvalidate();
                                        } else if (Error_Struct > 0) {
                                            ACAduserEnglishKellyPage.this.MonitorshowStr[i3] = ACAduserEnglishByteUtil.InttoBinaryString(Error_Struct);
                                            ACAduserEnglishKellyPage.this.viewsetBackgroundColor(ACAduserEnglishKellyPage.this.Monitor_View_Array[i3], SupportMenu.CATEGORY_MASK);
                                            ACAduserEnglishKellyPage.this.Monitor_View_Array[i3].postInvalidate();
                                        }
                                        ACAduserEnglishKellyPage.this.MonitorDataValueBackup[value_offset] = ACAduserEnglishKellyPage.this.MonitorDataValue[value_offset];
                                        ACAduserEnglishKellyPage.this.MonitorDataValueBackup[value_offset + 1] = ACAduserEnglishKellyPage.this.MonitorDataValue[value_offset + 1];
                                    }
                                } else if (ACAduserEnglishKellyPage.this.MonitorDataValueBackup[value_offset] != ACAduserEnglishKellyPage.this.MonitorDataValue[value_offset] || ACAduserEnglishKellyPage.this.MonitorDataValueBackup[value_offset + 1] != ACAduserEnglishKellyPage.this.MonitorDataValue[value_offset + 1]) {
                                    ACAduserEnglishKellyPage.this.MonitorshowStr[i3] = new StringBuilder().append((ACAduserEnglishKellyPage.this.MonitorDataValue[value_offset] * 256) + ACAduserEnglishKellyPage.this.MonitorDataValue[value_offset + 1]).toString();
                                    ACAduserEnglishKellyPage.this.Monitor_View_Array[i3].postInvalidate();
                                    ACAduserEnglishKellyPage.this.MonitorDataValueBackup[value_offset] = ACAduserEnglishKellyPage.this.MonitorDataValue[value_offset];
                                    ACAduserEnglishKellyPage.this.MonitorDataValueBackup[value_offset + 1] = ACAduserEnglishKellyPage.this.MonitorDataValue[value_offset + 1];
                                }
                            }
                        }
                        Thread.sleep(5);
                    }
                } catch (Exception e2) {
                }
            }
            System.out.println(">>>>exit kelly monitor");
        }
    }
}
