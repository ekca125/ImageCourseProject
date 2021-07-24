package db;

public class AddressVO {
    final double latitude;
    final double longitude;

    public AddressVO(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return "AddressVO{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
