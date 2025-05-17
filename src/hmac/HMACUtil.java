package hmac;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class HMACUtil {
    private static final String HMAC_SHA256 = "HmacSHA256";

    private static final String SECRET_KEY = "supersecretkey123";
    public static String generateHMAC(String message) {
        try {
            Mac mac = Mac.getInstance(HMAC_SHA256);
            SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), HMAC_SHA256);
            mac.init(secretKeySpec);

            byte[] hmacBytes = mac.doFinal(message.getBytes());
            return Base64.getEncoder().encodeToString(hmacBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error generating HMAC", e);
        }
    }
    public static boolean verifyHMAC(String message, String receivedHmac) {
        String calculatedHmac = generateHMAC(message);
        return calculatedHmac.equals(receivedHmac);
    }
}


