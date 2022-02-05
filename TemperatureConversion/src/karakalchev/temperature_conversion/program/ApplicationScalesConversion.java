package karakalchev.temperature_conversion.program;

import karakalchev.temperature_conversion.controller.Controller;
import karakalchev.temperature_conversion.controller.ControllerTemperatureConversion;
import karakalchev.temperature_conversion.model.*;
import karakalchev.temperature_conversion.view.View;
import karakalchev.temperature_conversion.view.ViewTemperatureConversion;

public class ApplicationScalesConversion {
    public static void main(String[] args) {
        TemperatureScaleConverter model = new TemperatureScaleConverter();
        View view = new View("Перевод температур");
        Controller controller = new Controller(model, view);
        controller.initializationController();
    }
}
