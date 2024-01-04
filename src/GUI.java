import javax.swing.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;
import java.util.Timer;

public class GUI extends JFrame{
    private JPanel panel1;

    public GUI()
    {

        final GetMotionInterval[] motion = new GetMotionInterval[1];
        //JFreeChart
        DefaultCategoryDataset ds = new DefaultCategoryDataset();
        JFreeChart jfc = ChartFactory.createLineChart ("test", "Temps","Vitesse", ds, PlotOrientation.VERTICAL,false, true, false);
        ChartPanel cp = new ChartPanel(jfc);
        getContentPane().add(cp, BorderLayout.CENTER);

        //Partie bouton...
        JButton buttonimporter = new JButton("Importer");
        JTextField timestamp1 = new JTextField(10);
        JTextField timestamp2 = new JTextField(10);
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Debut : "));
        inputPanel.add(timestamp1);
        inputPanel.add(new JLabel("Faim: "));
        inputPanel.add(timestamp2);
        JButton buttonscreenshot = new JButton("Screenshot");
        JButton buttonsimulation = new JButton(">>");
        inputPanel.add(buttonimporter);
        inputPanel.add(buttonscreenshot);
        inputPanel.add(buttonsimulation);


        getContentPane().add(inputPanel, BorderLayout.SOUTH);
        buttonimporter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ds.clear();
                try {
                    if(timestamp1.getText() != null && parseInt(timestamp1.getText()) != 0)
                    {
                        timestamp2.setText(Integer.toString(parseInt(timestamp1.getText()) + 120));
                        motion[0] = new GetMotionInterval("192.168.0.18", parseInt(timestamp1.getText()),  parseInt(timestamp2.getText()));
                        if (motion[0].getSize() == 0) {
                            JOptionPane.showMessageDialog(GUI.this, "Aucune donnée présente !");
                        }
                        else {
                            for (int i = 0; i < 20; i++) {
                                Motion temp = motion[0].get(i);
                                ds.addValue(temp.getAccX(), "AccX", Integer.toString(temp.getTimestamp()));
                                ds.addValue(temp.getAccY(), "AccY", Integer.toString(temp.getTimestamp()));
                            }
                        }
                    }
                    else
                        JOptionPane.showMessageDialog(GUI.this, "Valeur aberrante frérot");
                } catch (JsonProcessingException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        buttonsimulation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ThreadStart th = new ThreadStart(500, motion[0], ds);
                Thread th1 = new Thread(th);
                th1.start();

            }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }


}
