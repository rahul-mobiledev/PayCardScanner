package com.rahul.library.utils;

import androidx.annotation.RestrictTo;

import com.rahul.library.BuildConfig;

@RestrictTo(RestrictTo.Scope.LIBRARY)
public class Constants {

    public static final boolean DEBUG = BuildConfig.DEBUG;
    public static final String ASSETS_DIR = "cardrecognizer";
    public static final String MODEL_DIR = "cardrecognizer/model";
    public static final int NEURO_DATA_VERSION = 9;
    public static final String PAYCARDS_URL = "https://pay.cards";

    private Constants() {
    }
}
