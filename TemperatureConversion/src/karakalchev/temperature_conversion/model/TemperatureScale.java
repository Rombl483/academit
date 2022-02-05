package karakalchev.temperature_conversion.model;

public abstract class TemperatureScale {
    private final String name;
    private double value;

    protected TemperatureScale(String name, double value) {
        this.name = name;
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public abstract void convert(TemperatureScale temperatureScaleFrom);

    public abstract double getCelsius();

    @Override
    public String toString() {
        return name;
    }
}
