package data;

public class RangeMapInfo {
    private final double startLatitude;
    private final double startLongtitude;
    private final double endLatitude;
    private final double endLongtitude;
    private RangeMapInfo(double startLatitude,
                         double startLongtitude,
                         double endLatitude,
                         double endLongtitude) {
        this.startLatitude = startLatitude;
        this.startLongtitude = startLongtitude;
        this.endLatitude = endLatitude;
        this.endLongtitude = endLongtitude;
    }

    public double getEndLatitude() {
        return endLatitude;
    }

    public double getEndLongtitude() {
        return endLongtitude;
    }

    public double getStartLatitude() {
        return startLatitude;
    }

    public double getStartLongtitude() {
        return startLongtitude;
    }

    public double getStartX() {
        return startLongtitude;
    }

    public double getStartY() {
        return startLatitude;
    }

    public double getEndX() {
        return endLongtitude;
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
        double startLongtitude;
        double endLatitude;
        double endLongtitude;

        public Builder(){
            startLatitude=-1;
            startLongtitude=-1;
            endLatitude=-1;
            endLongtitude=-1;
        }

        public void setStartLatitude(double startLatitude) {
            this.startLatitude = startLatitude;
        }

        public void setStartLongtitude(double startLongtitude) {
            this.startLongtitude = startLongtitude;
        }

        public void setEndLatitude(double endLatitude) {
            this.endLatitude = endLatitude;
        }

        public void setEndLongtitude(double endLongtitude) {
            this.endLongtitude = endLongtitude;
        }

        public RangeMapInfo build() throws IllegalArgumentException{
            if(startLatitude==-1
            ||startLongtitude==-1
            ||endLongtitude==-1
            ||endLatitude==-1){
                throw new IllegalArgumentException();
            }

            if(startLatitude>endLatitude
            ||startLongtitude>endLongtitude){
                throw new IllegalArgumentException();
            }

            return new RangeMapInfo(
                    startLatitude,
                    startLongtitude,
                    endLatitude,
                    endLongtitude
            );
        }
    }
}
