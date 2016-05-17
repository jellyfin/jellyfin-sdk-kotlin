package mediabrowser.model.extensions;

/**
 * Created by Eric on 5/17/2016.
 */
public class CodecHelper {

    public static String friendlyName(String codec) {
        if (codec == null) return "";

        switch (codec.toLowerCase()) {
            case "ac3":
                return "Dolby Digital";
            case "dca":
                return "DTS";
            default:
                return codec.toUpperCase();
        }
    }
}
