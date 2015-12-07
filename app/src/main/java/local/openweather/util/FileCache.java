package local.openweather.util;

import android.content.Context;

import java.io.File;

/**
 * Created by brian on 11/26/2015.
 */
public class FileCache {

    private File cacheDir;

    public FileCache(Context context) {
        this.cacheDir = context.getCacheDir();
    }
}
