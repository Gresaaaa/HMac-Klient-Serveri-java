package hmac;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveri {

    public static void main(String[] args) {
        int port = 1234;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Serveri u startua dhe po pret mesazhe në portin " + port + "...");

            while (true) {
                Socket socket = serverSocket.accept();

                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

                String received = input.readLine(); // prit mesazhin me format: message||hmac
                if (received == null) continue;

                String[] parts = received.split("\\|\\|");
                if (parts.length != 2) {
                    output.println("Error: Format i mesazhit i gabuar");
                    socket.close();
                    continue;
                }

                String message = parts[0];
                String receivedHmac = parts[1];

                System.out.println("Mesazhi pranuar: " + message);
                System.out.println("HMAC i marrë: " + receivedHmac);

                LoggerUtil.log("Received message: " + message);
                LoggerUtil.log("Received HMAC: " + receivedHmac);

                if (HMACUtil.verifyHMAC(message, receivedHmac)) {
                    LoggerUtil.log("HMAC verified successfully for message: " + message);
                    System.out.println("Mesazhi i verifikuar me sukses.");
                    output.println("Valid HMAC: Mesazhi i verifikuar me sukses");
                } else {
                    LoggerUtil.log("HMAC verification failed for message: " + message);
                    System.out.println("Verifikimi i HMAC dështoi.");
                    output.println("Invalid HMAC: Mesazhi është manipuluar");
                }

                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
