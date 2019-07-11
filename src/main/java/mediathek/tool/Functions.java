package mediathek.tool;

import mediathek.config.Const;
import org.apache.commons.lang3.ArchUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.security.CodeSource;
import java.util.ResourceBundle;

public class Functions {

    private static final String RBVERSION = "version";
    private static final Logger logger = LogManager.getLogger(Functions.class);

    public static String textLaenge(int max, String text, boolean mitte, boolean addVorne) {
        if (text.length() > max) {
            if (mitte) {
                text = text.substring(0, 25) + " .... " + text.substring(text.length() - (max - 31));
            } else {
                text = text.substring(0, max - 1);
            }
        }
        StringBuilder textBuilder = new StringBuilder(text);
        while (textBuilder.length() < max) {
            if (addVorne) {
                textBuilder.insert(0, ' ');
            } else {
                textBuilder.append(' ');
            }
        }
        text = textBuilder.toString();
        return text;
    }

    /**
     * Detect and return the currently used operating system.
     *
     * @return The enum for supported Operating Systems.
     */
    public static OperatingSystemType getOs() {
        OperatingSystemType os = OperatingSystemType.UNKNOWN;

        if (SystemUtils.IS_OS_WINDOWS) {
            if (ArchUtils.getProcessor().is64Bit())
                os = OperatingSystemType.WIN64;
            else
                os = OperatingSystemType.WIN32;
        } else if (SystemUtils.IS_OS_LINUX || SystemUtils.IS_OS_FREE_BSD)
            os = OperatingSystemType.LINUX; //This is a hack...
        else if (SystemUtils.IS_OS_MAC_OSX) {
            os = OperatingSystemType.MAC;
        }
        return os;
    }

    public static String getOsString() {
        return getOs().toString();
    }

    public static String getPathJar() {
        // liefert den Pfad der Programmdatei mit File.separator am Schluss
        String pFilePath = "version.properties";
        File propFile = new File(pFilePath);
        if (!propFile.exists()) {
            try {
                CodeSource cS = Const.class.getProtectionDomain().getCodeSource();
                File jarFile = new File(cS.getLocation().toURI().getPath());
                String jarDir = jarFile.getParentFile().getPath();
                propFile = new File(jarDir + File.separator + pFilePath);
            } catch (Exception ignored) {
            }
        } else {
            logger.debug("getPathJar() propFile does exist");
        }
        String s = StringUtils.replace(propFile.getAbsolutePath(), pFilePath, "");
        if (!s.endsWith(File.separator)) {
            s += File.separator;
        }
        if (s.endsWith("/lib/")) {
            // dann sind wir in der msearch-lib
            s = StringUtils.replace(s, "/lib/", "");
        }
        return s;
    }

    public static String[] getJavaVersion() {
        String[] ret = new String[4];
        ret[0] = "Vendor: " + System.getProperty("java.vendor");
        ret[1] = "VMname: " + System.getProperty("java.vm.name");
        ret[2] = "Version: " + System.getProperty("java.version");
        ret[3] = "Runtimeversion: " + System.getProperty("java.runtime.version");
        return ret;
    }

    public static String getCompileDate() {
        String propToken = "DATE";
        String msg = "";
        try {
            ResourceBundle.clearCache();
            ResourceBundle rb = ResourceBundle.getBundle(RBVERSION);
            if (rb.containsKey(propToken)) {
                msg = rb.getString(propToken);
            }
        } catch (Exception e) {
            Log.errorLog(807293847, e);
        }
        return msg;
    }

    @Deprecated
    public static String getBuildNr() {
        String TOKEN_VERSION = "VERSION";
        try {
            ResourceBundle.clearCache();
            ResourceBundle rb = ResourceBundle.getBundle(RBVERSION);
            if (rb.containsKey(TOKEN_VERSION)) {
                return new Version(rb.getString(TOKEN_VERSION)).toString();
            }
        } catch (Exception e) {
            Log.errorLog(134679898, e);
        }
        return new Version("").toString();
    }

    public enum OperatingSystemType {

        UNKNOWN(""), WIN32("Windows"), WIN64("Windows"), LINUX("Linux"), MAC("Mac");
        private final String name;

        OperatingSystemType(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
