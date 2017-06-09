import java.net.*;
import java.io.*;

public class LittleServer {

    private static final int PortNumber = 8086;
    static ChitChat chitchat;

    LittleServer(ChitChat cc){
        chitchat = cc;
    }

    public static void listen () {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ServerSocket ss = new ServerSocket(PortNumber);
            System.out.println("Listening on port: " + PortNumber);

            while (true) {
                Socket clientSocket = ss.accept();
                String hostName = clientSocket.getInetAddress().getHostName();

                OutputStream out = clientSocket.getOutputStream();
                InputStream in = clientSocket.getInputStream();

                int nextByte = in.read();
                while (nextByte != '\n') {
                    baos.write(nextByte);
                    System.out.write(nextByte);
                    nextByte = in.read();
                }

                String Sender = baos.toString();
                baos.reset();

                System.out.print("CONVERTED STRING: " + Sender + "\n");

                nextByte = in.read();
                while (nextByte != -1) {
                    baos.write(nextByte);
                    System.out.write(nextByte);
                    nextByte = in.read();
                }

                String baosString = baos.toString();

                baos.reset();

                System.out.print("CONVERTED STRING: " + baosString + "\n");

                //build string
                String newMessage = new String("From:" + Sender + "\n" + baosString);

                chitchat.addAMessage(newMessage);
                System.out.println(hostName + " disconnecting");

                if (in != null)
                   in.close();
                if (out != null)
                  out.close();
                if (clientSocket != null)
                   clientSocket.close();
            }
        }
        catch (Exception e) {
            System.out.println("Error in RemoteConnection thread:" + e);
        }
    }
}