import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

public class GetMotionInterval {

    private final ArrayList<Motion> listMotion = new ArrayList<>();

    public GetMotionInterval(String ip, int startingInterval, int endingInterval) throws JsonProcessingException {
        JSONArray received = GetJSON.init("http://" + ip + ":8080/ords/papabergh/demo/motion?limit=120&beginsample=" +(startingInterval) + "&lastsample=" + endingInterval, "items");
        for(int i = 0; i < received.length(); i++)
        {
            JSONObject current_object = new JSONObject(received.get(i).toString());
            Motion motion_temp = new ObjectMapper().readValue(current_object.toString(), Motion.class);
            listMotion.add(motion_temp);
        }

    }
    public Motion get(int index)
    {
        return this.listMotion.get(index);
    }
    public int getSize() {return listMotion.size();}
}

