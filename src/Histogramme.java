import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Histogramme extends JFrame {

    private final ChartPanel cp;
    private final HistogramDataset ds;
    private GetMotionInterval motion;
    private final JFreeChart jfc;
    private ArrayList<Float> listSlow;
    private ArrayList<Float> listNormal;
    private ArrayList<Float> listAgressive;

    public Histogramme()
    {
        /*Je pense qu'il faudrait créer un histogramme pour chaque variable accx, accy, ...
        et pour chaque histogramme avoir la moyenne des valeurs pour les catégories de conducteur
        càd pour l'histo de accx, il faudrait 3 batonnets pour la moyenne de accx pour chaque type de conducteur
        */
        ds = new HistogramDataset();
        jfc = ChartFactory.createHistogram("test", "Temps","Vitesse", ds, PlotOrientation.VERTICAL,true, true, true);
        cp = new ChartPanel(jfc);
        getContentPane().add(cp, BorderLayout.CENTER);

        /*GetMotionInterval motion = new GetMotionInterval("192.168.0.20", 0,  10000);//obtenir toutes les valeurs*/

        


    }
}
