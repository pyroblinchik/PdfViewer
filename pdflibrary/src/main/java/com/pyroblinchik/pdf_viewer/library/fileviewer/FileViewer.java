package com.pyroblinchik.pdf_viewer.library.fileviewer;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.pyroblinchik.pdf_viewer.library.utils.FileViewerUtils;

import java.io.File;

public class FileViewer {

    private static final String DOT = ".";
    private static final String FORMAT_UNKNOWN = "unknown";
    private static final String FORMAT_PDF = "pdf";
    private static final String TAG = "FileViewUtils";
    private static final String[] FORMAT_TBS = new String[]{
            "doc", "docx",
            "ppt", "pptx",
            "xls", "xlsx",
            "txt",
            "pdf",
            "epub",
    };
    private static final String[] FORMAT_IMAGE = new String[]{
            "png", "jpg",
            "jpeg", "gif",
            "bmp",
    };

    public static OpenResult viewFile(Context context, String filePath) {
        if (context == null) {
            log("Context null ViewFile!");
            return OpenResult.failed("Context null");
        }

        if (TextUtils.isEmpty(filePath)) {
            return OpenResult.failed("FilePath:" + filePath + " is Empty!");
        }

        File file = new File(filePath);
        if (!file.isFile()) {
            return OpenResult.failed("File doesn't exist" + filePath);
        }

        String format = parseFormat(filePath);
        if (FORMAT_UNKNOWN.equals(format)) {
            return OpenResult.failed("Unsupported file format：" + format);
        }

        if (isPDF(format)) {
            viewPDFWithMuPDFByPath(context, filePath);
            return OpenResult.success();
        }

        FileViewerUtils.viewFile4_4(context, filePath);
        return OpenResult.success();
    }

    private static void log(String msg) {
        Log.e(TAG, msg);
    }

    public static void viewPDFWithMuPDFByPath(Context context, String filePath) {
        Uri uri = Uri.fromFile(new File(filePath));
        startMuPDFActivityByUri(context, uri);
    }

    public static void startMuPDFActivityByUri(Context context, Uri documentUri) {
//        Intent intent = new Intent(context, DocumentActivity.class);
//        intent.setAction(Intent.ACTION_VIEW);
//        intent.setData(documentUri);
//        context.startActivity(intent);
    }

    private static boolean isPDF(String format) {
        if (FORMAT_PDF.equalsIgnoreCase(format)) {
            return true;
        }
        return false;
    }

    private static String parseFormat(String fileName) {
        if (!fileName.contains(DOT)) {
            return FORMAT_UNKNOWN;
        }
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public static class OpenResult {
        boolean isSuccess = true;
        String failedResult = "";

        OpenResult(boolean isSuccess, String failedResult) {
            this.isSuccess = isSuccess;
            this.failedResult = failedResult;
        }

        static OpenResult success() {
            return new OpenResult(true, "");
        }

        static OpenResult failed(String result) {
            return new OpenResult(false, result);
        }

        public boolean isSuccess() {
            return isSuccess;
        }

        public String getFailedResult() {
            return failedResult;
        }
    }

}
