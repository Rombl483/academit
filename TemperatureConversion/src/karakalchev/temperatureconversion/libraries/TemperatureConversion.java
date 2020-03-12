package karakalchev.temperatureconversion.libraries;

public class TemperatureConversion {
    private TemperatureMeasure temperatureMeasure;
    private double temperatureValue;

    public void TemperatureConversion() {
        temperatureMeasure = TemperatureMeasure.CELCIUS;
        temperatureValue = 0.0;
    }

    public void TemperatureConversion(TemperatureMeasure temperatureMeasure, double temperatureValue) {
        this.temperatureMeasure = temperatureMeasure;
        this.temperatureValue = temperatureValue;
    }

    public void setTemperatureMeasure(TemperatureMeasure temperatureMeasure) {
        this.temperatureMeasure = temperatureMeasure;
    }

    public void setTemperatureValue(double temperatureValue) {
        this.temperatureValue = temperatureValue;
    }

    public TemperatureMeasure getTemperatureMeasure() {
        return temperatureMeasure;
    }

    public double getTemperatureValue() {
        return temperatureValue;
    }

    private double getTemperatureCelcius() {
        switch (temperatureMeasure) {
            case KELVIN:
                return temperatureValue - 273.15;
            case FAHRENHEIT:
                return (temperatureValue - 32) / 1.8;
            default:
                return temperatureValue;
        }
    }

    private double getTemperatureKelvin() {
        return getTemperatureCelcius() + 273.15;
    }

    private double getTemperatureFahrenheit() {
        return getTemperatureCelcius() * 1.8 + 32.0;
    }

    public void convertTo(TemperatureMeasure newTemperatureMeasure) {
        if (temperatureMeasure != newTemperatureMeasure) {
            switch (newTemperatureMeasure) {
                case KELVIN:
                    setTemperatureValue(getTemperatureKelvin());
                    break;
                case FAHRENHEIT:
                    setTemperatureValue(getTemperatureFahrenheit());
                    break;
                default:
                    setTemperatureValue(getTemperatureCelcius());
            }

            setTemperatureMeasure(newTemperatureMeasure);
        }
    }
}
