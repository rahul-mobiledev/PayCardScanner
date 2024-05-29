package com.rahul.paycardscanner.ndk;

import androidx.annotation.IntRange;

import com.rahul.paycardscanner.ndk.RecognitionConstants.WorkAreaOrientation;

public interface DisplayConfiguration {
    @WorkAreaOrientation
    int getNativeDisplayRotation();

    @IntRange(from = 0, to = 360)
    int getPreprocessFrameRotation(int width, int height);
}
