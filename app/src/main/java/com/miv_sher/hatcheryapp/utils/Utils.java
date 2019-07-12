package com.miv_sher.hatcheryapp.utils;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.miv_sher.hatcheryapp.ApplicationLoader;

import java.util.Date;


public class Utils {

    public static String getAppName(String packageName) {
        final PackageManager pm = ApplicationLoader.getContext().getPackageManager();
        ApplicationInfo ai;
        try {
            ai = pm.getApplicationInfo(packageName, 0);
        } catch (final PackageManager.NameNotFoundException e) {
            ai = null;
        }
        return (String) (ai != null ? pm.getApplicationLabel(ai) : packageName);
    }

    public static void setScaledImage(ImageView imageView, final int resId, boolean needTintTiWhite) {
        //Handler handler = new Handler(Looper.getMainLooper());
        final ImageView iv = imageView;
        // handler.post(new Runnable() {
        //   @Override
        //   public void run() {
        ViewTreeObserver viewTreeObserver = iv.getViewTreeObserver();
        viewTreeObserver.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                iv.getViewTreeObserver().removeOnPreDrawListener(this);
                int imageViewHeight = iv.getMeasuredHeight();
                int imageViewWidth = iv.getMeasuredWidth();
                Bitmap bitmap = decodeSampledBitmapFromResource(iv.getContext().getResources(),
                        resId, imageViewWidth, imageViewHeight);
                if (needTintTiWhite) {
                    bitmap = tintImageToWhite(bitmap);
                }
                iv.setImageBitmap(bitmap);
                return true;
                //          }
                //       });
            }
        });

    }

    private static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                          int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds = true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static Bitmap tintImageToWhite(Bitmap bitmap) {
        Paint paint = new Paint();
        paint.setColorFilter(new PorterDuffColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN));
        Bitmap bitmapResult = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapResult);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        return bitmapResult;
    }

    private static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {

        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Date getDatePlusMinutes(Date date, int minutes) {
        return new Date(date.getTime() + minutes * 60 * 1000);
    }

}
