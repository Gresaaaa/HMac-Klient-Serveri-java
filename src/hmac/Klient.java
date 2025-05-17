package hmac;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Logger;

public class Klient {

    private static Logger logger = LoggerUtil.getLogger();

    public static void main(String[] args) {
        String host = "localhost";
        int port = 1235;

        try (Socket socket = new Socket(host, port);
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            System.out.print("Shkruaj mesazhin për t'u dërguar: ");
            String message = scanner.nextLine();

            String hmac = HMACUtil.generateHMAC(message);
            String toSend = message + "||" + hmac;

            logger.info("Dërgohet mesazhi me HMAC: " + toSend);
            output.println(toSend);

            String response = input.readLine();
            logger.info("Përgjigja nga serveri: " + response);
            System.out.println("Përgjigja nga serveri: " + response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
