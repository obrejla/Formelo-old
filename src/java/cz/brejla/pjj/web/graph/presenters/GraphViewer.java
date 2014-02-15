
package cz.brejla.pjj.web.graph.presenters;

import cz.brejla.java.web.formelo.application.Component;
import cz.brejla.java.web.formelo.application.FormeloApplication;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import javax.imageio.ImageIO;

/**
 *
 * @author warden
 */
public class GraphViewer extends Component {

    private int width;

    private int height;

    public GraphViewer(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void generate() {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        GraphComponent panel = new GraphComponent();
        panel.setBounds(0, 0, image.getWidth(), image.getHeight());

        try {
            int numbers = Integer.parseInt(FormeloApplication.getSession().getAttribute("numbers").toString());
            int[] values = new int[numbers];
            for (int i = 0; i < numbers; i++) {
                values[i] = Integer.parseInt(FormeloApplication.getSession().getAttribute("number" + i).toString());
            }

            panel.setValues(values);
        } catch (NumberFormatException ex) {

        }

        panel.paint(image.getGraphics());

        FormeloApplication.getResponse().setContentType("image/png");
        try {
            OutputStream os = FormeloApplication.getResponse().getOutputStream();
            ImageIO.write(image, "png", os);
            os.close();
        } catch (IOException ex) {
            throw new IllegalArgumentException(ex);
        }
    }

}
