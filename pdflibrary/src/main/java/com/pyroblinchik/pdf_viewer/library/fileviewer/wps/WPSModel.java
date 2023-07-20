package com.pyroblinchik.pdf_viewer.library.fileviewer.wps;

class WPSModel {
    public static final String OPEN_MODE = "OpenMode";
    public static final String SEND_SAVE_BROAD = "SendSaveBroad";// Whether to send a broadcast when the file is saved.
    public static final String SEND_CLOSE_BROAD = "SendCloseBroad";// Whether to send a broadcast when the file is close.
    public static final String THIRD_PACKAGE = "ThirdPackage";
    public static final String CLEAR_BUFFER = "ClearBuffer";// Clear buffer after file close.
    public static final String CLEAR_TRACE = "ClearTrace";// Clear trace after file close.
    public static final String CLEAR_FILE = "ClearFile";
    public static final String VIEW_PROGRESS = "ViewProgress";// Show progress
    public static final String AUTO_JUMP = "AutoJump";
    public static final String SAVE_PATH = "SavePath";
    public static final String VIEW_SCALE = "ViewScale";// The scale of the view at which the file was last viewed.
    public static final String VIEW_SCALE_X = "ViewScrollX";// The X coordinate of the view where the file was last viewed.
    public static final String VIEW_SCALE_Y = "ViewScrollY";// The Y coordinate of the view where the file was last viewed.
    public static final String USER_NAME = "UserName";
    public static final String HOMEKEY_DOWN = "HomeKeyDown";
    public static final String BACKKEY_DOWN = "BackKeyDown";
    public static final String ENTER_REVISE_MODE = "EnterReviseMode";// Open the document in revision mode
    public static final String CACHE_FILE_INVISIBLE = "CacheFileInvisible";

    public class OpenMode {
        public static final String NORMAL = "Normal";// normal mode
        public static final String READ_ONLY = "ReadOnly";// read-only mode
        public static final String READ_MODE = "ReadMode";
        // Only Word and TXT documents are supported
        public static final String SAVE_ONLY = "SaveOnly";
        // Only Word and TXT documents are supported
    }

    public class ClassName {
        public static final String NORMAL = "cn.wps.moffice.documentmanager.PreStartActivity2";// regular version
        public static final String ENGLISH = "cn.wps.moffice.documentmanager.PreStartActivity2";// english version
        public static final String ENTERPRISE = "cn.wps.moffice.documentmanager.PreStartActivity2";
    }

    public class PackageName {
        public static final String NORMAL = "cn.wps.moffice_eng";
        public static final String ENGLISH = "cn.wps.moffice_eng";
    }

    public class Reciver {
        public static final String ACTION_BACK = "com.kingsoft.writer.back.key.down";
        public static final String ACTION_HOME = "com.kingsoft.writer.home.key.down";
        public static final String ACTION_SAVE = "cn.wps.moffice.file.save";
        public static final String ACTION_CLOSE = "cn.wps.moffice.file.close";
    }

}
