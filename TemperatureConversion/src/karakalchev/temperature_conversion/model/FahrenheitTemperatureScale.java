package karakalchev.temperature_conversion.model;

public class FahrenheitTemperatureScale extends TemperatureScale {
    public FahrenheitTemperatureScale() {
        super("", 32.0);
    }

    public FahrenheitTemperatureScale(double value) {
        super("", value);
    }

    @Override
    public void convert(TemperatureScale temperatureScaleFrom) {
        setValue(temperatureScaleFrom.getCelsius() * 1.8 + 32.0);
    }

    @Override
    public double getCelsius() {
        return (getValue() - 32) / 1.8;
    }
}
