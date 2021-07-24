package data;

public class RangeMapInfo {
    private final double startLatitude;
    private final double startLongitude;
    private final double endLatitude;
    private final double endLongitude;

    private RangeMapInfo(double startLatitude,
                         double startLongitude,
                         double endLatitude,
                         double endLongitude) {
        this.startLatitude = startLatitude;
        this.startLongitude = startLongitude;
        this.endLatitude = endLatitude;
        this.endLongitude = endLongitude;
    }

    public double getEndLatitude() {
        return endLatitude;
    }

    public double getEndLongitude() {
        return endLongitude;
    }

    public double getStartLatitude() {
        return startLatitude;
    }

    public double getStartLongitude() {
        return startLongitude;
    }

    public double getStartX() {
        return startLongitude;
    }

    public double getStartY() {
        return startLatitude;
    }

    public double getEndX() {
        return endLongitude;
    }

    public double getEndY() {
        return endLatitude;
    }

    public double getWidth() {
        return getEndX() - getStartX();
    }

    public double getHeight() {
        return getEndY() - getStartY();
    }

    public static class Builder {
        double startLatitude;
        double startLongitude;
        double endLatitude;
        double endLongitude;

        public Builder() {
            startLatitude = -1;
            startLongitude = -1;
            endLatitude = -1;
            endLongitude = -1;
        }

        public void setStartLatitude(double startLatitude) {
            this.startLatitude = startLatitude;
        }

        public void setStartLongitude(double startLongitude) {
            this.startLongitude = startLongitude;
        }

        public void setEndLatitude(double endLatitude) {
            this.endLatitude = endLatitude;
        }

        public void setEndLongitude(double endLongitude) {
            this.endLongitude = endLongitude;
        }

        public RangeMapInfo build() throws IllegalArgumentException {
            if (startLatitude == -1
                    || startLongitude == -1
                    || endLongitude == -1
                    || endLatitude == -1) {
                throw new IllegalArgumentException();
            }

            if (startLatitude > endLatitude
                    || startLongitude > endLongitude) {
                throw new IllegalArgumentException();
            }

            return new RangeMapInfo(
                    startLatitude,
                    startLongitude,
                    endLatitude,
                    endLongitude
            );
        }
    }
}
