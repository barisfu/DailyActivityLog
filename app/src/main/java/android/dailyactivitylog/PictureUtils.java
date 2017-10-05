package android.dailyactivitylog;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;

/**
 * Created by Mack on 05-Oct-17.
 */

public class PictureUtils {
    public static Bitmap getScaledBitmap(String path, int destWidth, int destHeight) {
        //Reads in the dimensions of the image
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        float srcWidth = options.outWidth;
        float srcHeight = options.outHeight;

        //Figures out how much to scale the image down
        int inSampleSize = 1;
        if(srcHeight > destHeight || srcWidth > destWidth) {
            if (srcWidth > srcHeight) {
                inSampleSize = Math.round(srcHeight / destHeight);
            } else {
                inSampleSize = Math.round(srcWidth / destWidth);
            }
        }

        options = new BitmapFactory.Options();
        options.inSampleSize = inSampleSize;

        //Reads in and creates the final bitmap
        return BitmapFactory.decodeFile(path, options);
    }

    /**
     * This method checks to see how big the screen is and
     * then scales the image down to that size.
     *
     * @param path
     * @param activiy
     * @return
     */
    public static Bitmap getScaledBitmap(String path, Activity activiy) {
        Point size = new Point();
        activiy.getWindowManager().getDefaultDisplay().getSize(size);

        return getScaledBitmap(path, size.x, size.y);
    }
}
