package karakalchev.temperature_conversion.model;

public class TemperatureConversion {
    private TemperatureMeasure temperatureMeasureFrom;
    private TemperatureMeasure temperatureMeasureTo;
    private double temperatureValueFrom;
    private double temperatureValueTo;

    public TemperatureConversion() {
        temperatureMeasureFrom = TemperatureMeasure.CELSIUS;
        temperatureValueFrom = 0.0f;
        temperatureMeasureTo = TemperatureMeasure.KELVIN;
        convertTo();
    }

    public TemperatureConversion(TemperatureMeasure temperatureMeasureFrom, TemperatureMeasure temperatureMeasureTo, double temperatureValue) {
        this.temperatureMeasureFrom = temperatureMeasureFrom;
        this.temperatureMeasureTo = temperatureMeasureTo;
        temperatureValueFrom = temperatureValue;
        convertTo();
    }

    public TemperatureMeasure getTemperatureMeasureFrom() {
        return temperatureMeasureFrom;
    }

    public void setTemperatureMeasureFrom(TemperatureMeasure temperatureMeasureFrom) {
        this.temperatureMeasureFrom = temperatureMeasureFrom;
    }

    public double getTemperatureValueFrom() {
        return temperatureValueFrom;
    }

    public void setTemperatureValueFrom(double temperatureValueFrom) {
        this.temperatureValueFrom = temperatureValueFrom;
    }

    public TemperatureMeasure getTemperatureMeasureTo() {
        return temperatureMeasureTo;
    }

    public void setTemperatureMeasureTo(TemperatureMeasure temperatureMeasureTo) {
        this.temperatureMeasureTo = temperatureMeasureTo;
    }

    public double getTemperatureValueTo() {
        return temperatureValueTo;
    }

    public void setTemperatureValueTo(double temperatureValueTo) {
        this.temperatureValueTo = temperatureValueTo;
    }

    private double getTemperatureCelsius() {
        switch (temperatureMeasureFrom) {
            case KELVIN:
                return temperatureValueFrom - 273.15;
            case FAHRENHEIT:
                return (temperatureValueFrom - 32) / 1.8;
            default:
                return temperatureValueFrom;
        }
    }

    private double getTemperatureKelvin() {
        return getTemperatureCelsius() + 273.15;
    }

    private double getTemperatureFahrenheit() {
        return getTemperatureCelsius() * 1.8 + 32.0;
    }

    public void convertTo() {
        if (temperatureMeasureTo == temperatureMeasureFrom) {
            setTemperatureValueTo(temperatureValueFrom);
            return;
        }

        switch (temperatureMeasureTo) {
            case KELVIN:
                setTemperatureValueTo(getTemperatureKelvin());
                break;
            case FAHRENHEIT:
                setTemperatureValueTo(getTemperatureFahrenheit());
                break;
            default:
                setTemperatureValueTo(getTemperatureCelsius());
        }
    }
}

