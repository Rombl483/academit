package karakalchev.temperature_conversion.program;

import karakalchev.temperature_conversion.controller.ControllerTemperatureConversion;
import karakalchev.temperature_conversion.model.*;
import karakalchev.temperature_conversion.view.ViewTemperatureConversion;

import javax.swing.*;

public class ApplicationTemperatureConversion {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TemperatureConversion modelTemperatureConversion = new TemperatureConversion();
            ViewTemperatureConversion viewTemperatureConversion = new ViewTemperatureConversion("Перевод температур");
            ControllerTemperatureConversion controllerTemperatureConversion = new ControllerTemperatureConversion(modelTemperatureConversion, viewTemperatureConversion);
            controllerTemperatureConversion.initializationController();
        });
    }
}

