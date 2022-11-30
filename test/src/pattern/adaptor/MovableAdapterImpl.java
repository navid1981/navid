package pattern.adaptor;

public class MovableAdapterImpl implements MovableAdapter{

    public Movable movable;

    public MovableAdapterImpl(Movable movable){
        this.movable=movable;
    }
    @Override
    public double getSpeed() {
        return convert(movable.getSpeed());
    }

    private double convert(double speed) {
        return speed * 1.60934;
    }
}
