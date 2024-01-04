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

public class GUI extends JFrame{
    private JPanel panel1;

    public GUI()
    {
        //JFreeChart
        DefaultCategoryDataset ds = new DefaultCategoryDataset();
        ds.addValue(100, "test", "test1");
        ds.addValue(20, "test", "test2");
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
        inputPanel.add(buttonimporter);
        inputPanel.add(buttonscreenshot);


        getContentPane().add(inputPanel, BorderLayout.SOUTH);
        buttonimporter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    if(parseInt(timestamp2.getText()) - parseInt(timestamp1.getText()) == 120)
                    {
                        GetMotionInterval motion = new GetMotionInterval("192.168.0.18", parseInt(timestamp1.getText()),  parseInt(timestamp2.getText()));
                        for (int i = 0; i < motion.getSize(); i++) {
                            Motion temp = motion.get(i);
                            ds.addValue(temp.getAccX(), "AccX", Integer.toString(temp.getTimestamp()));
                        }
                    }
                    else
                        JOptionPane.showMessageDialog(GUI.this, "Valeur aberrante frérot");
                } catch (JsonProcessingException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }


}
