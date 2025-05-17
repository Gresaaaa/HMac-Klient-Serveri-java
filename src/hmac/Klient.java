package hmac;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Logger;

public class Klient {


    private static final Logger logger = Logger.getLogger(Klient.class.getName());

    public static void main(String[] args) {
        String host = "localhost";
        int port = 1235;


        try (Socket socket = new Socket(host, port);
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {


            System.out.print("Shkruaj mesazhin për t'u dërguar: ");
            String message = scanner.nextLine();


            if (message.isEmpty()) {
                System.out.println("❌ Mesazhi nuk mund të jetë bosh!");
                return;
            }

    
            String hmac = HMACUtil.generateHMAC(message);
            String toSend = message + "||" + hmac;


            logger.info("📤 Dërgohet mesazhi me HMAC: " + toSend);
            output.println(toSend);


            String response = input.readLine();
            if (response != null) {
                logger.info("📥 Përgjigja nga serveri: " + response);
                System.out.println("Përgjigja nga serveri: " + response);
            } else {
                logger.warning("⚠️ Serveri nuk ktheu asnjë përgjigje.");
                System.out.println("⚠️ Serveri nuk u përgjigj. Provo përsëri më vonë.");
            }

        } catch (Exception e) {
            logger.severe("❌ Gabim gjatë komunikimit me serverin: " + e.getMessage());
            System.out.println("❌ Gabim gjatë komunikimit me serverin. Provo përsëri më vonë.");
        }
    }
}
