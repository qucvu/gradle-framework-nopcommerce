package commons;

import java.nio.file.Paths;

public class GlobalConstants {

    public final static String PROJECT_PATH = System.getProperty("user.dir");
    private final static String USER_PROFILE = System.getProperty("user.home");
    public static final String OS_NAME = System.getProperty("os.name");
    public final static int LONG_TIMEOUT = 30;
    public final static int SHORT_TIMEOUT = 3;
    public final static String OPERA_LAUNCHER_EXE_LOCATION = Paths.get(USER_PROFILE, "AppData", "Local", "Programs", "Opera", "opera.exe").toString();
    public final static String UPLOAD_FILE_FOLDER = PROJECT_PATH + "/uploadFiles";
    public static final String DATA_RECORD = PROJECT_PATH + "/dataRecord";


}
