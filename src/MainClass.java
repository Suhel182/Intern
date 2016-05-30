/**
 * Created by Dell on 5/27/2016.
 */
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

public class MainClass {
    public static void main(String[] arg) throws IOException, JSONException {
        JsonParser jsonParser=new JsonParser();
        Httpcall httpcall=new Httpcall();

        ArrayList<Carrier> list = jsonParser.get();
        for(Carrier car : list){
            FileHandler fh = new FileHandler(jsonParser.getSrcName(list.indexOf(car)));
            String jsonData = httpcall.postData(car.getUrl(),car.getData());
            fh.write(jsonData);
        }
    }
}
