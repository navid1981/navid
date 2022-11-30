package pattern.adaptor;

public class UStoEUCarAdaptor extends USCar{
    USCar car;
    public UStoEUCarAdaptor(USCar car){
        this.car=car;
    }

    @Override
    public double getSpeed() {
        return convert(car.getSpeed());
    }

    private double convert(double speed) {
        return speed * 1.60934;
    }
}
