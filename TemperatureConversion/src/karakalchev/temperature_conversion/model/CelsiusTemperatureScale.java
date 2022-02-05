package karakalchev.temperature_conversion.model;

public class CelsiusTemperatureScale extends TemperatureScale {
    public CelsiusTemperatureScale() {
        super("ЦЕЛЬСИЙ", 0.0);
    }

    @Override
    public void convert(TemperatureScale temperatureScaleFrom) {
        setValue(temperatureScaleFrom.getCelsius());
    }

    @Override
    public double getCelsius() {
        return getValue();
    }
}
