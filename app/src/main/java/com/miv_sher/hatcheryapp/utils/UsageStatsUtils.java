package com.miv_sher.hatcheryapp.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AppOpsManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;

import androidx.annotation.NonNull;

import com.miv_sher.hatcheryapp.ApplicationLoader;
import com.miv_sher.hatcheryapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.miv_sher.hatcheryapp.utils.Utils.getAppName;

public class UsageStatsUtils {
    public static final String TAG = UsageStatsUtils.class.getSimpleName();
    public static final String[] WHITELISTED_PACKAGES = {"com.google", "com.miui", "com.qualcomm", "com.qti", "com.google", "com.xiaomi", "com.mi",
            "com.fingerprints", "com.quicinc", "org.aurora", "org.codeaurora", "org.codeaurora", "com.lbe", "com.android", "com.meizu", "com.miv_sher"};
    public static final String[] BLACKLISTED_PACKAGES = {"com.android.settings", "com.google.android.gms"};
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    @SuppressWarnings("ResourceType")
    private static UsageStatsManager getUsageStatsManager(Context context) {
        UsageStatsManager usm = (UsageStatsManager) context.getSystemService("usagestats");
        return usm;
    }

    public static List<UsageStats> getUsageStatsList(long startTime) {
        UsageStatsManager usm = getUsageStatsManager(ApplicationLoader.getContext());
        Calendar calendar = Calendar.getInstance();
        long endTime = calendar.getTimeInMillis();

        // Log.d(TAG, "Range start:" + dateFormat.format(startTime));
        // Log.d(TAG, "Range end:" + dateFormat.format(endTime));

        List<UsageStats> usageStatsList = usm.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, startTime, endTime);
        return usageStatsList;
    }


    public static String getUsageStatsString(long startTime) {
        List<UsageStats> usageStatsList = getUsageStatsList(startTime);
        String result = "";
        //"Range start: " + dateFormat.format(startTime) + "\n" + "Range end:" + dateFormat.format(Calendar.getInstance().getTimeInMillis())  + "\n\n";
        for (UsageStats u : usageStatsList) {
           /* Log.d(TAG, "Pkg: " + u.getPackageName() + "\t" + "ForegroundTime: "
                    + u.getTotalTimeInForeground() + "\t" + "LastUsedTime: "
                    + dateFormat.format(u.getLastTimeUsed()));*/

            if (isPackageWhiteListed(u, startTime))
                continue;

            result += getAppName(u.getPackageName()) + ": " + dateFormat.format(u.getLastTimeUsed()) + " for "
                    + TimeUnit.MILLISECONDS.toSeconds(u.getTotalTimeInForeground()) + " seconds\n\n";

        }
        return result;
    }

    private static boolean isPackageWhiteListed(UsageStats usageStats, long startTime) {
        //for background services, which launch without user actions
        if (usageStats.getTotalTimeInForeground() == 0)
            return true;
        //for apps usage before egg put in hatchibator
        if (usageStats.getLastTimeUsed() < startTime)
            return true;
        //if user goes to settings - egg dies
        for (int i = 0; i < BLACKLISTED_PACKAGES.length; i++) {
            if (usageStats.getPackageName().contains(BLACKLISTED_PACKAGES[i]))
                return false;
        }
        //for different system processes, which should not kill an egg
        for (int i = 0; i < WHITELISTED_PACKAGES.length; i++) {
            if (usageStats.getPackageName().contains(WHITELISTED_PACKAGES[i]))
                return true;
        }
        return false;
    }

    public static void checkAndAskPermission(Activity activity) {
        if (!hasPermission(activity)) {
            final AlertDialog dialog = new AlertDialog.Builder(activity)
                    .setTitle(ApplicationLoader.getContext().getString(R.string.app_name))
                    .setMessage(activity.getString(R.string.usage_permission_ask))
                    .setPositiveButton(activity.getString(R.string.go_to_settings), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            ApplicationLoader.getContext().startActivity(intent);
                            dialog.cancel();
                        }
                    })
                    .setNegativeButton(activity.getString(R.string.cancel), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .create();
            dialog.setCanceledOnTouchOutside(true);
            dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface arg0) {
                    dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(ApplicationLoader.getContext().getResources().getColor(R.color.colorPrimary));
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ApplicationLoader.getContext().getResources().getColor(R.color.colorPrimary));
                }
            });
            dialog.show();
        }
    }

    private static boolean hasPermission(@NonNull final Context context) {
        // Usage Stats is theoretically available on API v19+, but official/reliable support starts with API v21.
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return false;
        }

        final AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);

        if (appOpsManager == null) {
            return false;
        }

        final int mode = appOpsManager.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS, android.os.Process.myUid(), context.getPackageName());
        if (mode != AppOpsManager.MODE_ALLOWED) {
            return false;
        }

        // Verify that access is possible. Some devices "lie" and return MODE_ALLOWED even when it's not.
        Date date = new Date();
        final List<UsageStats> stats = getUsageStatsList(date.getTime() - 10 * 1000 * 60);
        return (stats != null && !stats.isEmpty());
    }

}
