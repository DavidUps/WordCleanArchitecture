package com.skeleton.android.core.customviews;

import android.app.Activity;
import android.view.WindowManager;

public class ScreenUtils {

    /**
     * Returns scale factor to scale dimensions uniformly (maintain the aspect ratio) so
     * that both dimensions (width and height) will be equal to or larger than given
     * <param>dstWidth</param> and <param>dstHeight</param>.
     * @param width
     * @param height
     * @param dstWidth
     * @param dstHeight
     * @return
     */
    public static float getScaleCropFactor(int width, int height, int dstWidth, int dstHeight) {
        float scaleFactor = 1.f;

        // Let's check some things and calc scale.
        if (width != 0.f && height != 0.f && dstWidth != 0.f && dstHeight != 0.f) {
            scaleFactor = Math.max((float) dstWidth / (float) width, (float) dstHeight / (float) height);
        }
        return scaleFactor;
    }

    public static void upBrightness(Activity activity) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.screenBrightness = 1f;
        activity.getWindow().setAttributes(lp);
    }

    public static void restoreBrightness(Activity activity) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.screenBrightness = -1f;
        activity.getWindow().setAttributes(lp);
    }

}