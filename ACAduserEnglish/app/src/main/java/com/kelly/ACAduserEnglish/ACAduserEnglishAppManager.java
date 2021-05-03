package com.kelly.ACAduserEnglish;

import android.app.Activity;
import java.util.ArrayList;

public class ACAduserEnglishAppManager extends ArrayList {
    private static ACAduserEnglishAppManager instance = new ACAduserEnglishAppManager();

    public static ACAduserEnglishAppManager getInstance() {
        return instance;
    }

    public void exitAndroidActivity() {
        for (int i = 0; i < size(); i++) {
            ((Activity) get(i)).finish();
        }
        System.exit(0);
    }
}
