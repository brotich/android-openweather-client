package local.openweather.util;

import android.util.Log;

/**
 * Created by brian on 10/17/2015.
 */
public class LogUtils {

    private static final String LOG_TAG = "Open";

    private static final boolean debug = true;

    public static void LOGD(String msg) {
        LOGD(LOG_TAG, msg);
    }

    public static void LOGD(String TAG, String msg) {
        if (Log.isLoggable(TAG, Log.DEBUG) && debug)
            Log.d(TAG, msg);
    }

    public static void LOGD(String TAG, String msg, Throwable thr) {
        if (Log.isLoggable(TAG, Log.DEBUG) && debug) {
            Log.d(TAG, msg, thr);
        }
    }

    public static void LOGE(String msg) {
        LOGE(LOG_TAG, msg);
    }

    public static void LOGE(String TAG, String msg) {
        if (Log.isLoggable(TAG, Log.ERROR) && debug) {
            Log.e(TAG, msg);
        }
    }

    public static void LOGE(String TAG, String msg, Throwable thr) {
        if (Log.isLoggable(TAG, Log.ERROR) && debug) {
            Log.e(TAG, msg, thr);
        }
    }


}
