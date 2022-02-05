package karakalchev.temperature_conversion.view;

import karakalchev.temperature_conversion.model.TemperatureScale;

import javax.swing.*;

import java.util.ArrayList;

import static javax.swing.GroupLayout.Alignment.*;
import static javax.swing.GroupLayout.Alignment.CENTER;

public class View {
    private final JFrame frame;
    private final JComboBox<TemperatureScale> comboBoxScaleFrom;
    private final JComboBox<TemperatureScale> comboBoxScaleTo;
    private final JTextField textFieldValueFrom;
    private final JTextField textFieldValueTo;
    private final JButton buttonConvertTo;

    public View(String title) {
        frame = new JFrame(title);
        frame.setSize(400, 170);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        JLabel labelTemperatureMeasureFrom = new JLabel("Шкала измерения");
        JLabel labelTemperatureMeasureTo = new JLabel("Шкала измерения");
        comboBoxScaleFrom = new JComboBox<>();
        comboBoxScaleTo = new JComboBox<>();
        JLabel labelTextFieldValueFrom = new JLabel("Значение температуры");
        JLabel labelTextFieldValueTo = new JLabel("Значение температуры");
        textFieldValueFrom = new JTextField();
        textFieldValueTo = new JTextField();
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
                        .addComponent(comboBoxScaleFrom)
                        .addComponent(labelTextFieldValueFrom)
                        .addComponent(textFieldValueFrom))
                .addComponent(buttonConvertTo)
                .addGroup(layout.createParallelGroup(LEADING)
                        .addComponent(labelTemperatureMeasureTo)
                        .addComponent(comboBoxScaleTo)
                        .addComponent(labelTextFieldValueTo)
                        .addComponent(textFieldValueTo))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(labelTemperatureMeasureFrom)
                        .addComponent(labelTemperatureMeasureTo))
                .addGroup(layout.createParallelGroup(LEADING)
                        .addComponent(comboBoxScaleFrom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboBoxScaleTo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
        JOptionPane.showMessageDialog(frame, "Введите число.", "Информация", JOptionPane.WARNING_MESSAGE);
    }

    public JButton getButtonConvertTo() {
        return buttonConvertTo;
    }

    public TemperatureScale getScaleFrom() {
        return (TemperatureScale) comboBoxScaleFrom.getSelectedItem();
    }

    public TemperatureScale getScaleTo() {
        return (TemperatureScale) comboBoxScaleTo.getSelectedItem();
    }

    public String getScaleValueFrom() {
        return textFieldValueFrom.getText();
    }

    public void refresh() {
        textFieldValueFrom.setText(String.format("%.3f", getScaleFrom().getValue()));
        textFieldValueTo.setText(String.format("%.3f", getScaleTo().getValue()));
    }

    public void initializeView(ArrayList<TemperatureScale> temperatureScales) {
        addItemsComboBox(comboBoxScaleFrom, temperatureScales);
        addItemsComboBox(comboBoxScaleTo, temperatureScales);

        if (temperatureScales != null && !temperatureScales.isEmpty()) {
            comboBoxScaleFrom.setSelectedItem(temperatureScales.get(0));
            comboBoxScaleTo.setSelectedItem(temperatureScales.get(0));
            textFieldValueFrom.setText(String.format("%.3f", temperatureScales.get(0).getValue()));
            textFieldValueTo.setText(String.format("%.3f", temperatureScales.get(0).getValue()));
        }
    }

    private void addItemsComboBox(JComboBox<TemperatureScale> comboBox, ArrayList<TemperatureScale> temperatureScales) {
        if (temperatureScales != null && !temperatureScales.isEmpty()) {
            for (TemperatureScale temperatureScale : temperatureScales) {
                comboBox.addItem(temperatureScale);
            }
        }
    }
}
