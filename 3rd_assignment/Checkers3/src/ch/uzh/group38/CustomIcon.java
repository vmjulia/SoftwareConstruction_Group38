package ch.uzh.group38;

import javax.swing.*;
import java.awt.*;

public abstract class CustomIcon implements Icon {
    private Color color;
    private boolean isKing;

    public CustomIcon(Color color, boolean isKing) {
        this.color = color;
        this.isKing = isKing;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        int w = c.getWidth();
        int h = c.getHeight();
        int d = Math.min(h, w);
        d -= d/3;

        g.setColor(color);
        g.fillOval(w/2 - d/2, h/2 - d/2, d, d);

        g.setColor(new Color(0, 0, 0, 70));
        g.fillOval(w/2 - d/2, h/2 - d/2, d, d);

        g.setColor(color);
        g.fillOval(w/2 - 2*d/5, h/2 - 2*d/5, 4*d/5, 4*d/5);

        g.setColor(new Color(0, 0, 0, 20));
        g.drawOval(w/2 - 2*d/5, h/2 - 2*d/5, 4*d/5, 4*d/5);

        g.setColor(new Color(255, 255, 255, 50));
        g.drawOval(w/2 - d/2, h/2 - d/2, d, d);

        if (isKing) {
            int m = 2*d/7;
            int[] xPoints = {w/2-m, w/2-m/2, w/2+m/2, w/2+m, w/2};
            int[] yPoints = {h/2-m/6, h/2+2*m/3, h/2+2*m/3, h/2-m/6, h/2-2*m/3};
            Polygon crown = new Polygon(xPoints, yPoints, 5);

            g.setColor(new Color(0, 0, 0, 120));
            g.fillPolygon(crown);

            g.setColor(new Color(0, 0, 0, 100));
            g.drawPolygon(crown);
        }
    }

    @Override
    public int getIconWidth() {
        return 0;
    }

    @Override
    public int getIconHeight() {
        return 0;
    }
}

class RedKing extends CustomIcon {
    public RedKing() {
        super(new Color(150, 10, 10), true);
    }
}

class RedPawn extends CustomIcon {
    public RedPawn() {
        super(new Color(150, 10, 10), false);
    }
}

class WhiteKing extends CustomIcon {
    public WhiteKing() {
        super(Color.white, true);
    }
}

class WhitePawn extends CustomIcon {
    public WhitePawn() {
        super(Color.white, false);
    }
}