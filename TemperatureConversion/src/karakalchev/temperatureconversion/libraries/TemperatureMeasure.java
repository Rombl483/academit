package karakalchev.temperatureconversion.libraries;

public enum TemperatureMeasure {
    CELCIUS("ЦЕЛЬСИЙ"),
    KELVIN("КЕЛЬВИН"),
    FAHRENHEIT("ФАРЕНГЕЙТ");

    private String title;

    TemperatureMeasure(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
