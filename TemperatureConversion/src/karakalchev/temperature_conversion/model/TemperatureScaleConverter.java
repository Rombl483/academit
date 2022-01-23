package karakalchev.temperature_conversion.model;

import java.util.ArrayList;

public class TemperatureScaleConverter {
    private final ArrayList<TemperatureScale> temperatureScales;

    public TemperatureScaleConverter() {
        temperatureScales = new ArrayList<>();

        temperatureScales.add(new CelsiusTemperatureScale());
        temperatureScales.add(new KelvinTemperatureScale());
        temperatureScales.add(new FahrenheitTemperatureScale());
    }

    public ArrayList<TemperatureScale> getTemperatureScales() {
        return temperatureScales;
    }

    public void convertTemperatureScales(TemperatureScale temperatureScaleFrom, TemperatureScale temperatureScaleTo) {
        temperatureScaleTo.convert(temperatureScaleFrom);
    }
}
