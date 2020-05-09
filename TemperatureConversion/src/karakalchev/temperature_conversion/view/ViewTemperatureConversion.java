package karakalchev.temperature_conversion.view;

import karakalchev.temperature_conversion.model.TemperatureMeasure;

import javax.swing.*;

import static javax.swing.GroupLayout.Alignment.*;
import static javax.swing.GroupLayout.Alignment.CENTER;

public class ViewTemperatureConversion {
    private JFrame frame;
    private JComboBox<TemperatureMeasure> comboBoxTemperatureMeasureFrom;
    private JComboBox<TemperatureMeasure> comboBoxTemperatureMeasureTo;
    private JTextField textFieldValueFrom;
    private JTextField textFieldValueTo;
    private JButton buttonConvertTo;

    public ViewTemperatureConversion(String title) {
        frame = new JFrame(title);
        frame.setSize(400, 170);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        TemperatureMeasure[] itemsMeasure = {
                TemperatureMeasure.CELSIUS,
                TemperatureMeasure.FAHRENHEIT,
                TemperatureMeasure.KELVIN
        };

        JLabel labelTemperatureMeasureFrom = new JLabel("Шкала измерения");
        JLabel labelTemperatureMeasureTo = new JLabel("Шкала измерения");
        comboBoxTemperatureMeasureFrom = new JComboBox<>(itemsMeasure);
        comboBoxTemperatureMeasureTo = new JComboBox<>(itemsMeasure);
        JLabel labelTextFieldValueFrom = new JLabel("Значение температуры");
        JLabel labelTextFieldValueTo = new JLabel("Значение температуры");
        textFieldValueFrom = new JTextField("0.0");
        textFieldValueTo = new JTextField("0.0");
        textFieldValueTo.setEditable(false);
        buttonConvertTo = new JButton("=>");

        JPanel panel = new JPanel();
        frame.add(panel);

        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(LEADING)
                        .addComponent(labelTemperatureMeasureFrom)
                        .addComponent(comboBoxTemperatureMeasureFrom)
                        .addComponent(labelTextFieldValueFrom)
                        .addComponent(textFieldValueFrom))
                .addComponent(buttonConvertTo)
                .addGroup(layout.createParallelGroup(LEADING)
                        .addComponent(labelTemperatureMeasureTo)
                        .addComponent(comboBoxTemperatureMeasureTo)
                        .addComponent(labelTextFieldValueTo)
                        .addComponent(textFieldValueTo))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(labelTemperatureMeasureFrom)
                        .addComponent(labelTemperatureMeasureTo))
                .addGroup(layout.createParallelGroup(LEADING)
                        .addComponent(comboBoxTemperatureMeasureFrom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboBoxTemperatureMeasureTo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(LEADING)
                        .addComponent(labelTextFieldValueFrom)
                        .addComponent(labelTextFieldValueTo))
                .addGroup(layout.createParallelGroup(LEADING)
                        .addComponent(textFieldValueFrom, CENTER, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonConvertTo)
                        .addComponent(textFieldValueTo, CENTER, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
    }

    public void showMessage() {
        JOptionPane.showMessageDialog(frame, "Введите число.", "информация", JOptionPane.WARNING_MESSAGE);
    }

    public JComboBox<TemperatureMeasure> getComboBoxTemperatureMeasureFrom() {
        return comboBoxTemperatureMeasureFrom;
    }

    public void setComboBoxTemperatureMeasureFrom(JComboBox<TemperatureMeasure> comboBoxTemperatureMeasureFrom) {
        this.comboBoxTemperatureMeasureFrom = comboBoxTemperatureMeasureFrom;
    }

    public JComboBox<TemperatureMeasure> getComboBoxTemperatureMeasureTo() {
        return comboBoxTemperatureMeasureTo;
    }

    public void setComboBoxTemperatureMeasureTo(JComboBox<TemperatureMeasure> comboBoxTemperatureMeasureTo) {
        this.comboBoxTemperatureMeasureTo = comboBoxTemperatureMeasureTo;
    }

    public JTextField getTextFieldValueFrom() {
        return textFieldValueFrom;
    }

    public void setLabelTextFieldValueFrom(JTextField textFieldValueFrom) {
        this.textFieldValueFrom = textFieldValueFrom;
    }

    public JTextField getTextFieldValueTo() {
        return textFieldValueTo;
    }

    public void setLabelTextFieldValueTo(JTextField textFieldValueTo) {
        this.textFieldValueTo = textFieldValueTo;
    }

    public JButton getButtonConvertTo() {
        return buttonConvertTo;
    }

    public void setButtonConvertTo(JButton buttonConvertTo) {
        this.buttonConvertTo = buttonConvertTo;
    }
}

