package karakalchev.temperature_conversion.controller;

import karakalchev.temperature_conversion.model.*;
import karakalchev.temperature_conversion.view.View;

public class Controller {
    private final TemperatureScaleConverter model;
    private final View view;

    public Controller(TemperatureScaleConverter model, View view) {
        this.model = model;
        this.view = view;
    }

    public void initializationController() {
        view.initializeView(model.getTemperatureScales());
        view.getButtonConvertTo().addActionListener(e -> scalesConversion());
    }

    private void scalesConversion() {
        TemperatureScale temperatureScaleFrom = view.getScaleFrom();
        TemperatureScale temperatureScaleTo = view.getScaleTo();
        String scaleValueFrom = view.getScaleValueFrom();

        if (scaleValueFrom != null && !scaleValueFrom.isEmpty()) {
            try {
                temperatureScaleFrom.setValue(Double.parseDouble(scaleValueFrom.replace(",", ".")));
                model.convertTemperatureScales(temperatureScaleFrom, temperatureScaleTo);
                view.refresh();
            } catch (IllegalArgumentException e) {
                view.showMessage();
            }
        } else {
            view.showMessage();
        }
    }
}
