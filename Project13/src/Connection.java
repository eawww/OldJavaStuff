import java.net.*;
import java.net.URLConnection;
import java.io.*;

public class Connection {
    public static void main (String argv[] ) throws IOException {
        String webPage = "http://localhost:8086";

        try {
            URL url = new URL(webPage);
            URLConnection connection = url.openConnection();

            System.out.println("Content type: " + connection.getContentType());
            System.out.println("Content length: " + connection.getContentLength());


            InputStream in = connection.getInputStream();
            BufferedReader reader = new BufferedReader( new InputStreamReader(in));

            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            in.close();
        }
        catch(Exception e){
            System.out.println("Ex: " + e);
        }
    }
}