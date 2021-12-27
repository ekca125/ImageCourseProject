package dot;

import java.awt.*;

public class ImageDot extends BaseDot {
    private final int x;
    private final int y;
    private final Color color;

    public ImageDot(int x, int y, int width, int height, Color color) {
        super((double) x / width, 1 - (double) y / height);
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
