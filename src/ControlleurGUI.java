import com.fasterxml.jackson.core.JsonProcessingException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import static java.lang.Integer.parseInt;

public class ControlleurGUI extends Component implements ActionListener, WindowListener {
    private final GUI fenetre;

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals("Screenshot")) {

        }
        if (e.getActionCommand().equals("Simulation")) {
            ThreadStart th = new ThreadStart(500, fenetre.getMotions(), fenetre.getDataset());
            Thread th1 = new Thread(th);
            th1.start();

        }
        if (e.getActionCommand().equals("Importer")) {
            fenetre.getDataset().clear();
            try {
                if(fenetre.getTimeStamp1() != null && parseInt(fenetre.getTimeStamp1()) != 0)
                {
                    fenetre.setTimestamp2(Integer.toString(parseInt(fenetre.getTimeStamp1()) + 120));
                    GetMotionInterval motions = new GetMotionInterval("192.168.0.18", parseInt(fenetre.getTimeStamp1()),  parseInt(fenetre.getTimeStamp2()));
                    if (motions.getSize() == 0) {
                        JOptionPane.showMessageDialog(this, "Aucune donnée présente !");
                    }
                    else {
                        fenetre.setMotion(motions);
                        for (int i = 0; i < 20; i++) {
                            Motion temp = fenetre.getMotions().get(i);
                            fenetre.getDataset().addValue(temp.getAccX(), "AccX", Integer.toString(temp.getTimestamp()));
                            fenetre.getDataset().addValue(temp.getAccY(), "AccY", Integer.toString(temp.getTimestamp()));
                        }
                    }
                }
                else
                    JOptionPane.showMessageDialog(this, "Valeur aberrante frérot");
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
        }

    }
    public ControlleurGUI(GUI fenetre) throws IOException {
        this.fenetre = fenetre;
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
