package com.example.cookio.data.utils;

import android.view.View;

public class Utils {

    public final static String DbURL =
            "https://cookio-5e0a7-default-rtdb.europe-west1.firebasedatabase.app/";
    public static int visibleOrGone(boolean isVisible) {
        return isVisible ? View.VISIBLE : View.GONE;
    }
}
