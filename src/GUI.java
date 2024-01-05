import javax.swing.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;
import java.util.Timer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Base64;

import org.jfree.chart.ChartUtils;
import org.json.JSONObject;

public class GUI extends JFrame{
    private JPanel panel1;
    private JButton buttonimporter;
    private JTextField timestamp1;
    private JTextField timestamp2;
    private JPanel inputPanel;
    private JButton buttonscreenshot;
    private JButton buttonsimulation;
    private ChartPanel cp;
    private DefaultCategoryDataset ds;
    private GetMotionInterval motion;
    public GUI()
    {
        //JFreeChart
        ds = new DefaultCategoryDataset();
        JFreeChart jfc = ChartFactory.createLineChart ("test", "Temps","Vitesse", ds, PlotOrientation.VERTICAL,false, true, false);
        cp = new ChartPanel(jfc);
        getContentPane().add(cp, BorderLayout.CENTER);

        //Partie bouton...
        buttonimporter = new JButton("Importer");
        timestamp1 = new JTextField(10);
        timestamp2 = new JTextField(10);
        inputPanel = new JPanel();
        inputPanel.add(new JLabel("Debut : "));
        inputPanel.add(timestamp1);
        inputPanel.add(new JLabel("Faim: "));
        inputPanel.add(timestamp2);
        buttonscreenshot = new JButton("Screenshot");
        buttonsimulation = new JButton(">>");
        inputPanel.add(buttonimporter);
        inputPanel.add(buttonscreenshot);
        inputPanel.add(buttonsimulation);

        // Ajout des actions sur les boutons pour le controleur
        buttonscreenshot.setActionCommand("Screenshot");
        buttonsimulation.setActionCommand("Simulation");
        buttonimporter.setActionCommand("Importer");

        getContentPane().add(inputPanel, BorderLayout.SOUTH);
        buttonscreenshot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    File fi = new File("");
                    ChartUtils.saveChartAsJPEG(fi, jfc, cp.getWidth(), cp.getHeight());
                    byte[] byteArray = Files.readAllBytes(fi.toPath());
                    String encoded = Base64.getEncoder().encodeToString(byteArray);
                    JSONObject json = new JSONObject();
                    json.put("image", encoded);
                    String urlParameters = json.toString();
                    byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8 );
                    int postDataLength = postData.length;
                    URL url = new URL("http://localhost:8080/ords/hr/demo/blob");
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
        });
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public void setControleur(ControlleurGUI c) {
        buttonimporter.addActionListener(c);
        buttonscreenshot.addActionListener(c);
        buttonsimulation.addActionListener(c);

        this.addWindowListener(c);
    }

    public String getTimeStamp1() {return timestamp1.getText();}
    public String getTimeStamp2() {return timestamp2.getText();}
    public void setTimestamp1(String text) {timestamp1.setText(text);}
    public void setTimestamp2(String text) {timestamp2.setText(text);}

    public DefaultCategoryDataset getDataset(){ return this.ds;}
    public void setMotion(GetMotionInterval interval) {this.motion = interval;}

    public GetMotionInterval getMotions() {return this.motion;};
}
