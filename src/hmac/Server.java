package hmac;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class Server {
    private static Logger logger = LoggerUtil.getLogger();
    public static void main(String[] args) {
        int port = 1235;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Serveri u startua dhe po pret mesazhe në portin " + port + "...");

            while (true) {
                Socket socket = serverSocket.accept();

                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

                String received = input.readLine();
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

                if (HMACUtil.verifyHMAC(message, receivedHmac)) {
                    System.out.println("HMAC u verifikua me sukses!");
                    output.println("Valid HMAC: Mesazhi i verifikuar me sukses");
                } else {
                    System.out.println("HMAC nuk përputhet!");
                    output.println("Invalid HMAC: Mesazhi është manipuluar");
                }

                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
