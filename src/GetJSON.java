import org.json.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class GetJSON {

    public static JSONArray init(String Url, String key)
    {
        String line;
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(Url);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
            while ((line = rd.readLine()) != null)
            {
                result.append(line);
            }
            rd.close();
        }catch (Exception e) {
            System.out.println("Error in getting details : " + e);
        }
        JSONObject received1 = new JSONObject(result.toString());
        return new JSONArray(received1.get(key).toString());
    }
}
