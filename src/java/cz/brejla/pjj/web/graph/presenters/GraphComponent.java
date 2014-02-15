package cz.brejla.pjj.web.graph.presenters;

import cz.brejla.pjj.web.graph.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

/**
 * Komponenta u�ivatelsk�ho rozhran� vykresluj�c� kol��ov� graf s
 * popiskami hodnot.
 */
public class GraphComponent extends JPanel {

	/**
	 * Hodnoty zobrazovan� v kol��ov�m grafu.
	 */
	private int[] values;

	/**
	 * Barvy jednotliv�ch v�se�� kol��ov�ho grafu.
	 */
	private Color[] colors;

        public GraphComponent() {
            super();

            setBackground(Color.WHITE);
        }

        public void setValues(int[] values) {
            this.values = values;

            Random generator = new Random();
            colors = new Color[values.length];
            for (int i = 0; i < colors.length; i++) {
                    colors[i] = new Color(generator.nextInt() & 0xFFFFFF);
            }
        }

	/**
	 * Pomocn� metoda, kter� vr�t� nejvhodn�j�� barvu pro dan�
	 * pozad�. Pokud je pozad� sv�tl�, vr�t� �ernou barvu. Pokud je
	 * pozad� tmav�, vr�t� b�lou barvu.
	 * 
	 * @param background
	 *            barva pozad�.
	 * @return nejvhodn�j�� barva textu pro danou barvu pozad�.
	 */
	private Color getTextColorForBackground(Color background) {
		double y =
				0.3 * background.getRed() + 0.59
						* background.getGreen() + 0.11
						* background.getBlue();
		if (y > 127) {
			return Color.BLACK;
		} else {
			return Color.WHITE;
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		int size = getWidth();
		if (size > getHeight())
			size = getHeight();
		int x = (getWidth() - size) / 2;
		int y = (getHeight() - size) / 2;

                g.setFont(new Font("serif", Font.BOLD, 16));

                if (values != null) {
                    double angle = 0;

                    int sum = 0;
                    for (int i = 0; i < values.length; i++) {
                            sum += values[i];
                    }


                    for (int i = 0; i < values.length; i++) {
                            g.setColor(colors[i]);
                            double value = (values[i] * 360.0) / sum;
                            int valueInt = (int) Math.round(value);
                            int angleInt = (int) Math.round(angle);
                            g.fillArc(x, y, size, size, angleInt, valueInt);
                            g.setColor(getTextColorForBackground(g.getColor()));
                            drawText(g, "" + values[i], angleInt + valueInt / 2,
                                            size / 2 - 20, x + size / 2, y + size / 2);
                            angle += value;
                    }
                } else {System.out.println("ANO\n");
                    g.setColor(getTextColorForBackground(getBackground()));
                    g.drawString("Neplatná data", 20, 20);
                }
	}

	/**
	 * Pomocn� metoda, kter� vykresl� text na pozici dan� pol�rn�mi
	 * sou�adnicemi. Sou�adnice ur�uj� st�ed textu.
	 * 
	 * @param g
	 *            grafick� kontext, pomoc� kter�ho se kresl�.
	 * @param text
	 *            kreslen� text.
	 * @param angle
	 *            pol�rn� sou�adnice - �hel.
	 * @param r
	 *            pol�rn� sou�adnice - polom�r.
	 * @param cX
	 *            sou�adnice st�edu.
	 * @param cY
	 *            sou�adnice st�edu.
	 */
	private static void drawText(Graphics g, String text, int angle,
			int r, int cX, int cY) {
		int centerX =
				(int) Math
						.round(r * Math.cos(Math.PI * angle / 180));
		int centerY =
				(int) -Math.round(r
						* Math.sin(Math.PI * angle / 180));
		FontMetrics fm = g.getFontMetrics();
		int leftX = centerX - fm.stringWidth(text) / 2;
		int baselineY =
				centerY - fm.getHeight() / 2 + fm.getLeading()
						+ fm.getAscent();
		g.drawString(text, cX + leftX, cY + baselineY);
	}

}
