package hmac;

import java.io.IOException;
import java.util.logging.*;

public class LoggerUtil {
    private static Logger logger = Logger.getLogger("HMACLogger");

    static {
        try {
            FileHandler fh = new FileHandler("log.txt", true);
            fh.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);
            logger.setUseParentHandlers(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Logger getLogger() {
        return logger;
    }
}
