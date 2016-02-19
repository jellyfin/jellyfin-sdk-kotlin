package mediabrowser.apiinteraction.android.mediabrowser.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.XmlResourceParser;
import android.os.Process;
import android.util.Base64;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import mediabrowser.model.logging.ILogger;

/**
 * Validates that the calling package is authorized to browse a
 * {@link android.service.media.MediaBrowserService}.
 *
 * The list of allowed signing certificates and their corresponding package names is defined in
 * res/xml/allowed_media_browser_callers.xml.
 */
public class PackageValidator {

    private ILogger logger;

    public PackageValidator(Context ctx, ILogger logger) {
        this.logger = logger;
    }

    private Map<String, ArrayList<CallerInfo>> readValidCertificates(XmlResourceParser parser) {
        HashMap<String, ArrayList<CallerInfo>> validCertificates = new HashMap<String, ArrayList<CallerInfo>>();
        try {
            int eventType = parser.next();
            while (eventType != XmlResourceParser.END_DOCUMENT) {
                if (eventType == XmlResourceParser.START_TAG
                        && parser.getName().equals("signing_certificate")) {

                    String name = parser.getAttributeValue(null, "name");
                    String packageName = parser.getAttributeValue(null, "package");
                    boolean isRelease = parser.getAttributeBooleanValue(null, "release", false);
                    String certificate = parser.nextText().replaceAll("\\s|\\n", "");

                    CallerInfo info = new CallerInfo(name, packageName, isRelease, certificate);

                    ArrayList<CallerInfo> infos = validCertificates.get(certificate);
                    if (infos == null) {
                        infos = new ArrayList<CallerInfo>();
                        validCertificates.put(certificate, infos);
                    }
                    logger.Debug("Adding allowed caller: ", info.name,
                            " package=", info.packageName, " release=", info.release,
                            " certificate=", certificate);
                    infos.add(info);
                }
                eventType = parser.next();
            }
        } catch (XmlPullParserException e) {
            logger.ErrorException("Could not read allowed callers from XML.", e);
        } catch (IOException e) {
            logger.ErrorException("Could not read allowed callers from XML.", e);
        }
        return validCertificates;
    }

    /**
     * @return false if the caller is not authorized to get data from this MediaBrowserService
     */
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean isCallerAllowed(Context context, String callingPackage, int callingUid) {
        // Always allow calls from the framework, self app or development environment.
        if (Process.SYSTEM_UID == callingUid || Process.myUid() == callingUid) {
            return true;
        }
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo;
        try {
            packageInfo = packageManager.getPackageInfo(
                    callingPackage, PackageManager.GET_SIGNATURES);
        } catch (PackageManager.NameNotFoundException e) {
            logger.ErrorException("Package manager can't find package: %s", e, callingPackage);
            return false;
        }

        return true;
    }

    private final static class CallerInfo {
        final String name;
        final String packageName;
        final boolean release;
        final String signingCertificate;

        public CallerInfo(String name, String packageName, boolean release,
                          String signingCertificate) {
            this.name = name;
            this.packageName = packageName;
            this.release = release;
            this.signingCertificate = signingCertificate;
        }
    }
}