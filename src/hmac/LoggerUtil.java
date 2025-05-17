package hmac;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

public class LoggerUtil {
    private static final Logger logger = Logger.getLogger("HMACLogger");

    static {
        try {

            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String fileName = "log-" + date + ".txt";


            FileHandler fileHandler = new FileHandler(fileName, true);


            fileHandler.setFormatter(new Formatter() {
                @Override
                public String format(LogRecord record) {
                    return String.format("[%s] [%s] %s: %s%n",
                            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(record.getMillis())),
                            record.getLevel().getName(),
                            record.getSourceClassName(),
                            record.getMessage());
                }
            });


            logger.addHandler(fileHandler);
            logger.setUseParentHandlers(false);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Logger getLogger() {
        return logger;
    }
}
