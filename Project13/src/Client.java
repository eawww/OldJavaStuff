import java.net.*;
import java.io.*;

// Connect to: http://sce.umkc.edu/BIT/burrise/cs349/lessons/networking/
public class Client {
    Client(){
        System.out.print("Client created. It's pretty nice.\n");
    }
    public static void sendMessage (String server, String message) throws IOException {
        String serverName = server;
        int serverPort = 8086;

        try {
            Socket socketToServer = new Socket(serverName, serverPort);
            System.out.println("SocketToServer: " + socketToServer);

            OutputStream out = socketToServer.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));

            InputStream in = socketToServer.getInputStream();
            BufferedReader reader = new BufferedReader( new InputStreamReader(in));

            bw.write(message + "\n");
            // Machine hosts multiple domains on the same IP address
            //bw.write("Host:" + serverName + "\n\n");
            bw.flush();

            //String line = null;
            //while ((line = reader.readLine()) != null) {
            //    System.out.println(line);
            //}

            bw.close();
            in.close();
            out.close();
            socketToServer.close();
        }
        catch(Exception e){
            System.out.println("Ex: " + e);
        }
    }
}