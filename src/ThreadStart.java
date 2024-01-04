import org.jfree.data.category.DefaultCategoryDataset;

import java.util.ArrayList;

public class ThreadStart implements Runnable{

    private int duree;
    private GetMotionInterval motions;
    private DefaultCategoryDataset ds;


    public ThreadStart(int d, GetMotionInterval motions, DefaultCategoryDataset ds)
    {
        duree = d;
        this.motions = motions;
        this.ds = ds;

    }

    @Override
    public void run()
    {
        for (int nboucle = 0; nboucle < 4; nboucle++) {
            ds.clear();
            for (int j = 20 + (nboucle * 20); j <= 40 + (nboucle * 20); j++) {
                Motion temp = motions.get(j);
                ds.addValue(temp.getAccX(), "AccX", Integer.toString(temp.getTimestamp()));
                ds.addValue(temp.getAccY(), "AccY", Integer.toString(temp.getTimestamp()));
            }
            try {
                Thread.sleep(duree);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
