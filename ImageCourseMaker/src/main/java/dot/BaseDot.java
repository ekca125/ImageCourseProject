package dot;

public class BaseDot {
    private final double normalizeX;
    private final double normalizeY;

    public BaseDot(double normalizeX, double normalizeY) {
        this.normalizeX = normalizeX;
        this.normalizeY = normalizeY;
    }

    public double getNormalizeX() {
        return normalizeX;
    }

    public double getNormalizeY() {
        return normalizeY;
    }

    public double getCost(BaseDot baseDot) {
        return Math.sqrt(Math.pow(normalizeX - baseDot.normalizeX, 2) + Math.pow(normalizeY - baseDot.normalizeY, 2));
    }
}
