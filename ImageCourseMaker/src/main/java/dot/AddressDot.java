package dot;

import java.util.Objects;

public class AddressDot extends BaseDot {
    double longitude;
    double latitude;

    public AddressDot(double x, double y,
                       double normalizeX, double normalizeY) {
        super(normalizeX, normalizeY);
        this.latitude = y;
        this.longitude = x;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressDot that = (AddressDot) o;
        return Double.compare(that.longitude, longitude) == 0 && Double.compare(that.latitude, latitude) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(longitude, latitude);
    }

    @Override
    public String toString() {
        return "AddressDot{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
