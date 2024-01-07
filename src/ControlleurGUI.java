import com.fasterxml.jackson.core.JsonProcessingException;
import org.jfree.chart.ChartUtils;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Base64;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.lang.Integer.parseInt;

public class ControlleurGUI extends Component implements ActionListener, WindowListener {
    private final GUI fenetre;
    private ThreadStart th;
    private Thread th1;
    private final AtomicBoolean isRunning;

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals("Screenshot")) {
            try {
                File fi = new File("screenshot.jpeg");
                ChartUtils.saveChartAsJPEG(fi, fenetre.getJfc(), fenetre.getChartPanel().getWidth(), fenetre.getChartPanel().getHeight());
                byte[] byteArray = Files.readAllBytes(fi.toPath());
                String encoded = Base64.getEncoder().encodeToString(byteArray);
                JSONObject json = new JSONObject();
                json.put("image", encoded);
                json.put("expertise", fenetre.getTortDroit());
                json.put("timestampaccident", fenetre.getTimeStamp1());
                String urlParameters = json.toString();
                byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8 );
                int postDataLength = postData.length;
                URL url = new URL("http://192.168.0.23:8080/ords/papabergh/demo/motion");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                conn.setInstanceFollowRedirects(false);
                conn.setRequestMethod("POST"); //Verbe POST
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("charset", "utf-8");
                conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
                conn.setUseCaches(false);
                DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
                wr.write(postData);
                BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = rd.readLine()) != null) {
                    System.out.println(line);
                }
                rd.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }
        if (e.getActionCommand().equals("Simulation")) {
            if (th.getMutex().isLocked()) {
                th.getMutex().unlock();
            }
            else
                th.getMutex().lock();
        }
        if (e.getActionCommand().equals("Importer")) {
            fenetre.getDataset().clear();
            try {
                    fenetre.setTimestamp2(Integer.toString(parseInt(fenetre.getTimeStamp1()) + 120));
                    GetMotionInterval motions = new GetMotionInterval("192.168.0.23", parseInt(fenetre.getTimeStamp1()),  parseInt(fenetre.getTimeStamp2()));
                    if (motions.getSize() == 0) {
                        JOptionPane.showMessageDialog(fenetre, "Aucune donnée présente !");
                    }
                    else {
                        fenetre.setMotion(motions);
                        th = new ThreadStart(500, fenetre, fenetre.getDataset());
                        th1 = new Thread(th);
                        th1.start();
                    }

            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(fenetre, "Valeur aberrante frérot");
            }
        }
        if(e.getActionCommand().equals("Histogramme")){

            new Histogramme();
        }

    }
    public ControlleurGUI(GUI fenetre) {

        this.fenetre = fenetre;
        this.isRunning = new AtomicBoolean(false);
    }


    @Override
    public void windowOpened(WindowEvent e) {}
    @Override
    public void windowClosing(WindowEvent e) {}
    @Override
    public void windowClosed(WindowEvent e) {}
    @Override
    public void windowIconified(WindowEvent e) {}
    @Override
    public void windowDeiconified(WindowEvent e) {}
    @Override
    public void windowActivated(WindowEvent e) {}
    @Override
    public void windowDeactivated(WindowEvent e) {}
}
