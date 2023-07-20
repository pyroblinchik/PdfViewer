package com.pyroblinchik.pdf_viewer.library.fileviewer.wps;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.pyroblinchik.pdf_viewer.library.utils.AndroidUtils;
import com.pyroblinchik.pdf_viewer.library.utils.FileViewerUtils;

import java.io.File;
import java.util.List;

public class WPSOpenUtils {



    public static boolean viewOrEditFileWithWPS(@NonNull Context context, String filePath, boolean readOnly) {
        return viewOrEditFileWithWPS(new ContextStartWrapper(context), filePath, readOnly, 0);
    }

    public static boolean viewOrEditFileWithWPS(@NonNull Fragment fragment, String filePath, boolean readOnly, int requestCode) {
        return viewOrEditFileWithWPS(new FragmentStartWrapper(fragment), filePath, readOnly, requestCode);
    }

    protected static boolean viewOrEditFileWithWPS(@NonNull ActivityStarterWrapper wrapper, String filePath, boolean readOnly, int requestCode) {
        Intent intent = new Intent();
        File file = new File(filePath);
        if (!file.isFile()) {
            AndroidUtils.showToast(wrapper.getContext(), "File doesn't exist");
            return false;
        }
        intent.setDataAndType(Uri.fromFile(file), FileViewerUtils.getMimeType(file));
        return doRealWPSViewOrEdit(wrapper, intent, readOnly, requestCode);
    }

    protected static boolean doRealWPSViewOrEdit(@NonNull ActivityStarterWrapper context, @NonNull Intent intent,
                                                 boolean readOnly, int requestCode) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(WPSModel.SEND_CLOSE_BROAD, true);
        bundle.putBoolean(WPSModel.SEND_SAVE_BROAD, true);
        bundle.putString(WPSModel.THIRD_PACKAGE, context.getPackageName());
        if (readOnly) {
            bundle.putString(WPSModel.OPEN_MODE, WPSModel.OpenMode.READ_ONLY);
        } else {
            bundle.putString(WPSModel.OPEN_MODE, WPSModel.OpenMode.NORMAL);
            bundle.putBoolean(WPSModel.ENTER_REVISE_MODE, true);
        }
        bundle.putBoolean(WPSModel.CLEAR_TRACE, true);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setClassName(WPSModel.PackageName.NORMAL, WPSModel.ClassName.NORMAL);
        intent.putExtras(bundle);
        try {
            if (readOnly) {
                context.startActivity(Intent.createChooser(intent, "Please choose WPS to open the file"));
            } else {
                context.startActivityForResult(Intent.createChooser(intent, "Please select WPS to open the application"), requestCode);
            }
        } catch (ActivityNotFoundException exception) {
            Log.d("WPSOpenUtils", exception.getMessage());
            exception.printStackTrace();
            return false;
        }
        return true;
    }
    public static boolean checkWPSInstallation(Context context) {
        PackageManager packageManager1 = context.getPackageManager();
        Intent intent = new Intent();
        intent.setClassName(WPSModel.PackageName.NORMAL, WPSModel.ClassName.NORMAL);
        List<ResolveInfo> resolveInfos = packageManager1.queryIntentActivities(intent, PackageManager.MATCH_ALL);
        if (resolveInfos == null || resolveInfos.size() == 0) {
            return false;
        }
        boolean found = false;
        for (ResolveInfo info : resolveInfos) {
            if (info.activityInfo.packageName.toLowerCase().equalsIgnoreCase(WPSModel.PackageName.NORMAL)
                    || info.activityInfo.name.toLowerCase().equalsIgnoreCase(WPSModel.ClassName.NORMAL)) {
                found = true;
                break;
            }
        }
        return found;
    }

    abstract static class ActivityStarterWrapper {

        protected Context context;

        public ActivityStarterWrapper(Context context) {
            this.context = context;
        }

        abstract String getPackageName();

        abstract void startActivityForResult(Intent intent, int requestCode);

        abstract void startActivity(Intent intent);

        public Context getContext() {
            return context;
        }
    }

    protected static class ContextStartWrapper extends ActivityStarterWrapper {

        public ContextStartWrapper(Context context) {
            super(context);
        }

        @Override
        String getPackageName() {
            return context.getPackageName();
        }

        @Override
        void startActivityForResult(Intent intent, int requestCode) {
            context.startActivity(intent);
        }

        @Override
        void startActivity(Intent intent) {
            context.startActivity(intent);
        }
    }

    protected static class FragmentStartWrapper extends ActivityStarterWrapper {

        Fragment fragment;

        public FragmentStartWrapper(Fragment fragment) {
            super(fragment.getContext());
            this.fragment = fragment;
        }

        @Override
        String getPackageName() {
            return (fragment.getContext() == null) ? "" : fragment.getContext().getPackageName();
        }

        @Override
        void startActivityForResult(Intent intent, int requestCode) {
            fragment.startActivityForResult(intent, requestCode);
        }

        @Override
        void startActivity(Intent intent) {
            fragment.startActivity(intent);
        }
    }
}
