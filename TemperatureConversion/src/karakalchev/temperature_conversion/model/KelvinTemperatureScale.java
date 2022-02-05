package karakalchev.temperature_conversion.model;

public class KelvinTemperatureScale extends TemperatureScale {
    public KelvinTemperatureScale() {
        super("КЕЛЬВИН", 273.15);
    }

    @Override
    public void convert(TemperatureScale temperatureScaleFrom) {
        setValue(temperatureScaleFrom.getCelsius() + 273.15);
    }

    @Override
    public double getCelsius() {
        return getValue() - 273.15;
    }
}
