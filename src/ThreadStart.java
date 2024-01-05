import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

public class ThreadStart extends Thread{
    private final Mutex mutex;
    private final int duree;
    private final GUI mainWindow;
    private final DefaultCategoryDataset ds;


    public ThreadStart(int d, GUI mainWindow, DefaultCategoryDataset ds)
    {
        mutex = new Mutex(true);
        duree = d;
        this.mainWindow = mainWindow;
        this.ds = ds;
    }

    @Override
    public void run() {
        while (true) {
            for (int nboucle = 0; nboucle < 4; nboucle++) {
                if (!isInterrupted()) {
                    mutex.step();
                    ds.clear();
                    for (int j = 20 + (nboucle * 20); j <= 40 + (nboucle * 20); j++) {
                        Motion temp = mainWindow.getMotions().get(j);
                        ds.addValue(temp.getAccX(), "AccX", Integer.toString(temp.getTimestamp()));
                        ds.addValue(temp.getAccY(), "AccY", Integer.toString(temp.getTimestamp()));
                        ds.addValue(temp.getAccZ(), "AccZ", Integer.toString(temp.getTimestamp()));
                        ds.addValue(temp.getGyroX(), "GyroX", Integer.toString(temp.getTimestamp()));
                        ds.addValue(temp.getGyroY(), "GyroY", Integer.toString(temp.getTimestamp()));
                        ds.addValue(temp.getGyroZ(), "GyroZ", Integer.toString(temp.getTimestamp()));
                    }
                    try {
                        Thread.sleep(duree);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        }
    }
    public Mutex getMutex() {
        return this.mutex;
    }

}
