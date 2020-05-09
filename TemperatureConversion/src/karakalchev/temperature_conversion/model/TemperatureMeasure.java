package karakalchev.temperature_conversion.model;

public enum TemperatureMeasure {
    CELSIUS("ЦЕЛЬСИЙ"),
    KELVIN("КЕЛЬВИН"),
    FAHRENHEIT("ФАРЕНГЕЙТ");

    private String title;

    TemperatureMeasure(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}

