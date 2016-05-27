/**
 * Created by Dell on 5/27/2016.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Httpcall {
    InputStream inputStream;
    StringBuilder sb;
    public String postData(String url,String data) throws ProtocolException, IOException{
        try {
            sb = new StringBuilder();
            HttpURLConnection connection=(HttpURLConnection) new URL(url).openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            OutputStream os=connection.getOutputStream();
            os.write(data.getBytes());
            os.flush();

            BufferedReader br=new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String output;
            while((output=br.readLine())!=null){
                sb.append(output);
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(Httpcall.class.getName()).log(Level.SEVERE, null, ex);
        }
        return(sb.toString());
    }
}
