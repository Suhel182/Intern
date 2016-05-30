import org.json.JSONException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class SecondMain {
    public static void main(String[] arg) throws IOException, JSONException {
        JsonParser jp = new JsonParser();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    Httpcall call = new Httpcall();
                    ArrayList<Carrier> list = jp.get();
                    for (Carrier car : list) {
                        FileHandler fh = new FileHandler(jp.getSrcName(list.indexOf(car)));
                        fh.write(call.postData(car.getUrl(), car.getData()));
                    }
                } catch (Exception e) {
                }
            }
        }, 0, jp.getInterval());
    }
}
