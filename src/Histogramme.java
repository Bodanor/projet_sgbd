import com.fasterxml.jackson.core.JsonProcessingException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.DefaultDrawingSupplier;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.xy.IntervalXYDataset;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Histogramme extends JFrame {

    private final ChartPanel cp;
    private final HistogramDataset ds;
    private GetMotionInterval motion;
    private final JFreeChart jfc;
    private ArrayList<Float> listSlow;
    private ArrayList<Float> listNormal;
    private ArrayList<Float> listAgressive;

    public Histogramme() throws JsonProcessingException {

        super("Histogramme");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        listSlow = new ArrayList<Motion>();
        listNormal = new ArrayList<Motion>();
        listAgressive = new ArrayList<Motion>();

        /* Get all elements */
        for (int i = 818922; i < 3600000; i += 10000) {
            motion = new GetMotionInterval("192.168.0.17", i, i + 10000);
            if (motion.getSize() > 0) {
                for (int j = 0; j < motion.getSize(); j++) {
                    if (Objects.equals(motion.get(j).type, "AGGRESSIVE"))
                        listAgressive.add(motion.get(j));
                    else if (Objects.equals(motion.get(j).type, "NORMAL")) {
                        listNormal.add(motion.get(j));
                    } else {
                        listSlow.add(motion.get(j));
                    }
                }
            }
        }

        /*Je pense qu'il faudrait créer un histogramme pour chaque variable accx, accy, ...
        et pour chaque histogramme avoir la moyenne des valeurs pour les catégories de conducteur
        càd pour l'histo de accx, il faudrait 3 batonnets pour la moyenne de accx pour chaque type de conducteur
        */

    }

    private void ShowHistogrammAccX() {

        double[] aggressiveData = getAccX(listAgressive);
        double[] normalData = getAccX(listNormal);
        double[] slowData = getAccX(listSlow);

        IntervalXYDataset test = createDataset(aggressiveData, normalData,slowData);
        JFreeChart jfreechart = ChartFactory.createHistogram("Acceleration X", "Valeurs", "Nombre occurences", test, PlotOrientation.VERTICAL, true, true, true);
        XYPlot xyplot = (XYPlot) jfreechart.getPlot();
        xyplot.setForegroundAlpha(0.85F);
        XYBarRenderer xybarrenderer = (XYBarRenderer) xyplot.getRenderer();
        xybarrenderer.setDrawBarOutline(false);
        JPanel jpanel = new ChartPanel(jfreechart);
        //jpanel.setPreferredSize(new Dimension(600, 600));
        add(jpanel);

    }

    private void ShowHistogrammAccY() {

        double[] aggressiveData = getAccY(listAgressive);
        double[] normalData = getAccY(listNormal);
        double[] slowData = getAccY(listSlow);

        IntervalXYDataset test = createDataset(aggressiveData, normalData,slowData);
        JFreeChart jfreechart = ChartFactory.createHistogram("Acceleration Y", "Valeurs", "Nombre occurences", test, PlotOrientation.VERTICAL, true, true, true);
        XYPlot xyplot = (XYPlot) jfreechart.getPlot();
        xyplot.setForegroundAlpha(0.85F);
        XYBarRenderer xybarrenderer = (XYBarRenderer) xyplot.getRenderer();
        xybarrenderer.setDrawBarOutline(false);
        JPanel jpanel = new ChartPanel(jfreechart);
        //jpanel.setPreferredSize(new Dimension(600, 600));
        add(jpanel);
    }

    private void ShowHistogrammAccZ() {

        double[] aggressiveData = getAccZ(listAgressive);
        double[] normalData = getAccZ(listNormal);
        double[] slowData = getAccZ(listSlow);

        IntervalXYDataset test = createDataset(aggressiveData, normalData,slowData);
        JFreeChart jfreechart = ChartFactory.createHistogram("Acceleration Z", "Valeurs", "Nombre occurences", test, PlotOrientation.VERTICAL, true, true, true);
        XYPlot xyplot = (XYPlot) jfreechart.getPlot();
        xyplot.setForegroundAlpha(0.85F);
        XYBarRenderer xybarrenderer = (XYBarRenderer) xyplot.getRenderer();
        xybarrenderer.setDrawBarOutline(false);
        JPanel jpanel = new ChartPanel(jfreechart);
        //jpanel.setPreferredSize(new Dimension(600, 600));
        add(jpanel);

    }

    private void ShowHistogrammGyroX() {

        double[] aggressiveData = getGyroX(listAgressive);
        double[] normalData = getGyroX(listNormal);
        double[] slowData = getGyroX(listSlow);

        IntervalXYDataset test = createDataset(aggressiveData, normalData,slowData);
        JFreeChart jfreechart = ChartFactory.createHistogram("Gyro Z", "Valeurs", "Nombre occurences", test, PlotOrientation.VERTICAL, true, true, true);
        XYPlot xyplot = (XYPlot) jfreechart.getPlot();
        xyplot.setForegroundAlpha(0.85F);
        XYBarRenderer xybarrenderer = (XYBarRenderer) xyplot.getRenderer();
        xybarrenderer.setDrawBarOutline(false);
        JPanel jpanel = new ChartPanel(jfreechart);
        //jpanel.setPreferredSize(new Dimension(600, 600));
        add(jpanel);

    }

    private void ShowHistogrammGyroY() {

        double[] aggressiveData = getGyroY(listAgressive);
        double[] normalData = getGyroY(listNormal);
        double[] slowData = getGyroY(listSlow);

        IntervalXYDataset test = createDataset(aggressiveData, normalData,slowData);
        JFreeChart jfreechart = ChartFactory.createHistogram("Gyro Y", "Valeurs", "Nombre occurences", test, PlotOrientation.VERTICAL, true, true, true);
        XYPlot xyplot = (XYPlot) jfreechart.getPlot();
        xyplot.setForegroundAlpha(0.85F);
        XYBarRenderer xybarrenderer = (XYBarRenderer) xyplot.getRenderer();
        xybarrenderer.setDrawBarOutline(false);
        JPanel jpanel = new ChartPanel(jfreechart);
        //jpanel.setPreferredSize(new Dimension(600, 600));
        add(jpanel);
    }

    private void ShowHistogrammGyroZ() {

        double[] aggressiveData = getGyroZ(listAgressive);
        double[] normalData = getGyroZ(listNormal);
        double[] slowData = getGyroZ(listSlow);

        IntervalXYDataset test = createDataset(aggressiveData, normalData,slowData);
        JFreeChart jfreechart = ChartFactory.createHistogram("Gyro Z", "Valeurs", "Nombre occurences", test, PlotOrientation.VERTICAL, true, true, true);
        XYPlot xyplot = (XYPlot) jfreechart.getPlot();
        xyplot.setForegroundAlpha(0.85F);
        XYBarRenderer xybarrenderer = (XYBarRenderer) xyplot.getRenderer();
        xybarrenderer.setDrawBarOutline(false);
        JPanel jpanel = new ChartPanel(jfreechart);
        //jpanel.setPreferredSize(new Dimension(600, 600));
        add(jpanel);

    }

    public void displayHisto() {

        ShowHistogrammAccX();
        ShowHistogrammAccY();
        ShowHistogrammAccZ();
        ShowHistogrammGyroX();
        ShowHistogrammGyroY();
        ShowHistogrammGyroZ();
        scrollPane = new JScrollPane(getContentPane());
        scrollPane.setPreferredSize(new Dimension(600, 400));
        setContentPane(scrollPane);
        setTitle("Histograms per axis");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public double[]getAccX(ArrayList<Motion> list){
        double[]array = new double[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i).AccX;
        }

        return array;
    }
    public double[]getAccY(ArrayList<Motion> list) {
        double[]array = new double[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i).AccY;
        }
        return array;
    }
    public double[]getAccZ(ArrayList<Motion> list) {
        double[]array = new double[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i).AccZ;
        }
        return array;
    }

    public double[]getGyroX(ArrayList<Motion> list) {
        double[]array = new double[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i).GyroX;
        }
        return array;
    }

    public double[]getGyroY(ArrayList<Motion> list) {
        double[]array = new double[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i).GyroY;
        }
        return array;
    }

    public double[]getGyroZ(ArrayList<Motion> list) {
        double[]array = new double[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i).GyroZ;
        }
        return array;
    }


//    private void SortListsAccx(ArrayList<Motion> list) {
//        Collections.sort(list, new Comparator<Motion>() {
//            @Override
//            public int compare(Motion o1, Motion o2) {
//                return Float.compare(o1.getAccX(), o2.getAccX());
//            }
//        });
//    }
//
//    private void SortListsAccy(ArrayList<Motion> list) {
//        Collections.sort(list, new Comparator<Motion>() {
//            @Override
//            public int compare(Motion o1, Motion o2) {
//                return Float.compare(o1.getAccY(), o2.getAccY());
//            }
//        });
//    }
//
//    private void SortListsAccz(ArrayList<Motion> list) {
//        Collections.sort(list, new Comparator<Motion>() {
//            @Override
//            public int compare(Motion o1, Motion o2) {
//                return Float.compare(o1.getAccZ(), o2.getAccZ());
//            }
//        });
//    }
//
//    private void SortListsGyroX(ArrayList<Motion> list) {
//        Collections.sort(list, new Comparator<Motion>() {
//            @Override
//            public int compare(Motion o1, Motion o2) {
//                return Float.compare(o1.getGyroX(), o2.getGyroX());
//            }
//        });
//    }
//
//    private void SortListsGyroY(ArrayList<Motion> list) {
//        Collections.sort(list, new Comparator<Motion>() {
//            @Override
//            public int compare(Motion o1, Motion o2) {
//                return Float.compare(o1.getGyroY(), o2.getGyroY());
//            }
//        });
//    }
//
//    private void SortListsGyroZ(ArrayList<Motion> list) {
//        Collections.sort(list, new Comparator<Motion>() {
//            @Override
//            public int compare(Motion o1, Motion o2) {
//                return Float.compare(o1.getGyroZ(), o2.getGyroZ());
//            }
//        });
//    }

//    private double[] getOccurenceforPerStepAccX(ArrayList<Motion> list) {
//        int j = 0;
//        int min = (int)list.get(0).getAccX() - 1;
//        int max = (int)list.get((list.size()-1)).getAccX() + 1;
//        double[] vector = new double[max - (min)];
//        int x = 0;
//        for (int i = min; i < max; i++) {
//            double compteur = 0.0;
//            while (j < list.size() && list.get(j).getAccX() < i + 1)
//            {
//                compteur++;
//                j++;
//            }
//            vector[x] = compteur;
//            x++;
//        }
//        return vector;
//    }
//
//    private double[] getOccurenceforPerStepAccY(ArrayList<Motion> list) {
//        int j = 0;
//        int min = (int)list.get(0).getAccY() - 1;
//        int max = (int)list.get(list.size()).getAccY() + 1;
//        double[] vector = new double[max - min];
//        for (int i = min; i < max; i++) {
//            double compteur = 0.0;
//            while (list.get(j).getAccX() < i + 1)
//            {
//                compteur++;
//                j++;
//            }
//            vector[i] = compteur;
//
//        }
//        return vector;
//    }

//    private double[] getOccurenceforPerStepAccZ(ArrayList<Motion> list) {
//        int j = 0;
//        int min = (int)list.get(0).getAccZ() - 1;
//        int max = (int)list.get(list.size()).getAccZ() + 1;
//        double[] vector = new double[max - min];
//        for (int i = min; i < max; i++) {
//            double compteur = 0.0;
//            while (list.get(j).getAccX() < i + 1)
//            {
//                compteur++;
//                j++;
//            }
//            vector[i] = compteur;
//
//        }
//        return vector;
//    }
//    private double[] getOccurenceforPerStepGyroX(ArrayList<Motion> list) {
//        int j = 0;
//        int min = (int)list.get(0).getGyroX() - 1;
//        int max = (int)list.get(list.size()).getGyroX() + 1;
//        double[] vector = new double[max - min];
//        for (int i = min; i < max; i++) {
//            double compteur = 0.0;
//            while (list.get(j).getAccX() < i + 1)
//            {
//                compteur++;
//                j++;
//            }
//            vector[i] = compteur;
//
//        }
//        return vector;
//    }
//    private double[] getOccurenceforPerStepGyroY(ArrayList<Motion> list) {
//        int j = 0;
//        int min = (int)list.get(0).getGyroY() - 1;
//        int max = (int)list.get(list.size()).getGyroY() + 1;
//        double[] vector = new double[max - min];
//        for (int i = min; i < max; i++) {
//            double compteur = 0.0;
//            while (list.get(j).getAccX() < i + 1)
//            {
//                compteur++;
//                j++;
//            }
//            vector[i] = compteur;
//
//        }
//        return vector;
//    }
//
//    private double[] getOccurenceforPerStepGyroZ(ArrayList<Motion> list) {
//        int j = 0;
//        int min = (int)list.get(0).getGyroZ() - 1;
//        int max = (int)list.get(list.size()).getGyroZ() + 1;
//        double[] vector = new double[max - min];
//        for (int i = min; i < max; i++) {
//            double compteur = 0.0;
//            while (list.get(j).getAccX() < i + 1)
//            {
//                compteur++;
//                j++;
//            }
//            vector[i] = compteur;
//
//        }
//        return vector;
//    }

    private static IntervalXYDataset createDataset(double[] aggressive, double[] normal, double[] slow) {
        HistogramDataset histogramdataset = new HistogramDataset();
        System.out.println(Arrays.toString(aggressive));
        histogramdataset.addSeries("AGGRESSIVE", aggressive , 100);
        histogramdataset.addSeries("NORMAL", normal, 100);
        histogramdataset.addSeries("SLOW", slow, 100);

        return histogramdataset;
    }
}
