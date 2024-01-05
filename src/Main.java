import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;

public class Main {
    public static void main(String[] args) throws IOException {
       GUI test = new GUI();
       ControlleurGUI controleur = new ControlleurGUI(test);
       test.setControleur(controleur);
    }
}
