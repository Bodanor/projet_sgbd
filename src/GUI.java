import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame{
    private final JButton buttonimporter;
    private final JTextField timestamp1;
    private final JTextField timestamp2;
    private final JTextField expertiseText;
    private final JButton buttonscreenshot;
    private final JButton buttonsimulation;
    private final JButton buttonhisto;
    private final ChartPanel cp;
    private final DefaultCategoryDataset ds;
    private GetMotionInterval motion;
    private final JFreeChart jfc;
    public GUI()
    {
        //JFreeChart
        ds = new DefaultCategoryDataset();
        jfc = ChartFactory.createLineChart ("test", "Temps","Vitesse", ds, PlotOrientation.VERTICAL,true, true, true);
        cp = new ChartPanel(jfc);
        getContentPane().add(cp, BorderLayout.CENTER);

        //Partie bouton...
        buttonimporter = new JButton("Importer");
        timestamp1 = new JTextField(10);
        timestamp2 = new JTextField(10);

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Debut : "));
        inputPanel.add(timestamp1);
        inputPanel.add(new JLabel("Faim: "));
        inputPanel.add(timestamp2);
        buttonscreenshot = new JButton("Screenshot");
        buttonsimulation = new JButton(">>");
        buttonhisto = new JButton("Voir Histogramme");
        expertiseText = new JTextField(10);
        inputPanel.add(buttonimporter);
        inputPanel.add(buttonscreenshot);
        inputPanel.add(buttonsimulation);
        inputPanel.add(expertiseText);
        inputPanel.add(buttonhisto);

        // Ajout des actions sur les boutons pour le controleur
        buttonscreenshot.setActionCommand("Screenshot");
        buttonsimulation.setActionCommand("Simulation");
        buttonimporter.setActionCommand("Importer");
        buttonhisto.setActionCommand("Histogramme");

        getContentPane().add(inputPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 600);
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
    public void setTimestamp2(String text) {timestamp2.setText(text);}

    public DefaultCategoryDataset getDataset(){ return this.ds;}
    public void setMotion(GetMotionInterval interval) {this.motion = interval;}
    public String getTortDroit(){return expertiseText.getText();}
    public JFreeChart getJfc() { return this.jfc;}
    public ChartPanel getChartPanel() {return this.cp;}
    public GetMotionInterval getMotions() {return this.motion;}
}
