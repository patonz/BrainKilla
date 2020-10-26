package brainkilla.GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.border.AbstractBorder;

/**
 *
 * @author http://forum.html.it/forum/showthread/t-1180169.html
 */
public class BordoFine extends AbstractBorder {

    private int tipo;
    private Insets insets;
    public static final int RIALZATO = 0;
    public static final int INCASSATO = 1;

    public BordoFine(int tipo) {
        this.tipo = tipo;
        insets = new Insets(0, 0, 0, 0);
    }

    public BordoFine(int tipo, Insets insets) {
        this.tipo = tipo;
        this.insets = insets;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int larghezza, int altezza) {
        if (tipo == RIALZATO) {
            disegnaBordoRialzato(c, g, x, y, larghezza, altezza);
        }
        if (tipo == INCASSATO) {
            disegnaBordoIncassato(c, g, x, y, larghezza, altezza);
        }
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return insets;
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        insets = this.insets;
        return insets;
    }

    private void disegnaBordoRialzato(Component c, Graphics g, int x, int y, int larghezza, int altezza) {
        Color old = g.getColor();

        g.setColor(getColoreChiaro(c));
        g.drawLine(0, 0, 0, c.getHeight() - 2);
        g.drawLine(1, 0, c.getWidth() - 2, 0);

        g.setColor(getColoreScuro(c));
        g.drawLine(0, c.getHeight() - 1, c.getWidth() - 1, c.getHeight() - 1);
        g.drawLine(c.getWidth() - 1, c.getHeight() - 1, c.getWidth() - 1, 0);

        g.setColor(old);
    }

    private void disegnaBordoIncassato(Component c, Graphics g, int x, int y, int larghezza, int altezza) {
        Color old = g.getColor();

        g.setColor(getColoreScuro(c));
        g.drawLine(0, 0, 0, c.getHeight() - 1);
        g.drawLine(1, 0, c.getWidth() - 1, 0);

        g.setColor(getColoreChiaro(c));
        g.drawLine(1, c.getHeight() - 1, c.getWidth() - 1, c.getHeight() - 1);
        g.drawLine(c.getWidth() - 1, 1, c.getWidth() - 1, c.getHeight() - 2);

        g.setColor(old);
    }

    private Color getColoreChiaro(Component c) {
        return c.getBackground().brighter().brighter();
    }

    private Color getColoreScuro(Component c) {
        return c.getBackground().darker().darker();
    }
}
