package karakalchev.temperature_conversion.controller;

import karakalchev.temperature_conversion.model.TemperatureConversion;
import karakalchev.temperature_conversion.model.TemperatureMeasure;
import karakalchev.temperature_conversion.view.ViewTemperatureConversion;

public class ControllerTemperatureConversion {
    private TemperatureConversion model;
    private ViewTemperatureConversion view;

    public ControllerTemperatureConversion(TemperatureConversion model, ViewTemperatureConversion view) {
        this.model = model;
        this.view = view;
        initializationView();
    }

    private void initializationView() {
        view.getComboBoxTemperatureMeasureFrom().setSelectedItem(TemperatureMeasure.CELSIUS);
        view.getComboBoxTemperatureMeasureTo().setSelectedItem(TemperatureMeasure.KELVIN);
        view.getTextFieldValueFrom().setText(String.format("%.3f", model.getTemperatureValueFrom()));
        view.getTextFieldValueTo().setText(String.format("%.3f", model.getTemperatureValueTo()));
    }

    public void initializationController() {
        view.getButtonConvertTo().addActionListener(e -> doConvert());
    }

    private void doConvert() {
        model.setTemperatureMeasureFrom((TemperatureMeasure) view.getComboBoxTemperatureMeasureFrom().getSelectedItem());
        model.setTemperatureMeasureTo((TemperatureMeasure) view.getComboBoxTemperatureMeasureTo().getSelectedItem());

        String temperatureValueFrom = view.getTextFieldValueFrom().getText();

        if (!isNumber(temperatureValueFrom)) {
            view.getTextFieldValueTo().setText("");
            view.showMessage();
            return;
        }

        model.setTemperatureValueFrom(Double.parseDouble(temperatureValueFrom.replace(",", ".")));
        model.convertTo();
        view.getTextFieldValueTo().setText(String.format("%.3f", model.getTemperatureValueTo()));
    }

    private boolean isNumber(String string) {
        if (string == null || string.isEmpty()) {
            return false;
        }

        string = string.replace(",", ".");
        int commaCount = 0;

        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == '.') {
                commaCount++;
            }

            if ((!Character.isDigit(string.charAt(i)) && string.charAt(i) != '.' && string.charAt(i) != '-' && string.charAt(i) != '+')
                    || (commaCount > 1)) {
                return false;
            }
        }

        return true;
    }
}
