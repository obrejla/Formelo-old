package cz.brejla.pjj.web.graph.presenters;

import cz.brejla.pjj.web.graph.*;
import cz.brejla.pjj.web.graph.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class CircleServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException,
			IOException {
		String widthString = req.getParameter("width");
		String heightString = req.getParameter("height");

		int width;
		int height;

		try {
			width = Integer.parseInt(widthString);
		} catch (Exception e) {
			width = 300;
		}

		try {
			height = Integer.parseInt(heightString);
		} catch (Exception e) {
			height = 300;
		}

		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                GraphComponent panel = new GraphComponent();
                panel.setBounds(0, 0, image.getWidth(), image.getHeight());

                try {
                    int numbers = Integer.parseInt(req.getSession().getAttribute("numbers").toString());
                    int[] values = new int[numbers];
                    for (int i = 0; i < numbers; i++) {
                        values[i] = Integer.parseInt(req.getSession().getAttribute("number" + i).toString());
                    }

                    panel.setValues(values);
                } catch (NumberFormatException ex) {
                    
                }

                panel.paint(image.getGraphics());

		resp.setContentType("image/png");
		OutputStream os = resp.getOutputStream();
		ImageIO.write(image, "png", os);
		os.close();
	}



}
