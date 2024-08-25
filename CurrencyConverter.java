import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CurrencyConverter extends JFrame implements ActionListener {
    private JTextField amountField;
    private JComboBox<String> fromCurrencyCombo, toCurrencyCombo;
    private JLabel resultLabel;
    private JButton convertButton;
    private final double USD_TO_EUR = 0.85;
    private final double USD_TO_GBP = 0.75;
    private final double USD_TO_JPY = 110.0;

    public CurrencyConverter() {
        setTitle("Currency Converter");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(45, 45, 45)); // Dark background color
        setLayout(new GridLayout(6, 2, 10, 10));

        // Amount Field
        amountField = new JTextField();
        amountField.setHorizontalAlignment(JTextField.CENTER);
        amountField.setBackground(new Color(30, 30, 30)); // Dark input field
        amountField.setForeground(Color.WHITE);
        add(new JLabel("Amount:", SwingConstants.RIGHT));
        add(amountField);

        // From Currency ComboBox
        String[] currencies = {"USD", "EUR", "GBP", "JPY"};
        fromCurrencyCombo = new JComboBox<>(currencies);
        fromCurrencyCombo.setBackground(new Color(30, 30, 30)); // Dark combo box background
        fromCurrencyCombo.setForeground(Color.WHITE);
        add(new JLabel("From Currency:", SwingConstants.RIGHT));
        add(fromCurrencyCombo);

        // To Currency ComboBox
        toCurrencyCombo = new JComboBox<>(currencies);
        toCurrencyCombo.setBackground(new Color(30, 30, 30)); // Dark combo box background
        toCurrencyCombo.setForeground(Color.WHITE);
        add(new JLabel("To Currency:", SwingConstants.RIGHT));
        add(toCurrencyCombo);

        // Convert Button
        convertButton = new JButton("Convert");
        convertButton.addActionListener(this);
        convertButton.setBackground(new Color(60, 60, 60)); // Dark button background
        convertButton.setForeground(Color.WHITE);
        add(convertButton);

        // Result Label
        resultLabel = new JLabel("", SwingConstants.CENTER);
        resultLabel.setForeground(Color.WHITE);
        add(resultLabel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double amount = Double.parseDouble(amountField.getText());
            String fromCurrency = (String) fromCurrencyCombo.getSelectedItem();
            String toCurrency = (String) toCurrencyCombo.getSelectedItem();

            if (fromCurrency.equals(toCurrency)) {
                resultLabel.setText("Please select different currencies.");
                return;
            }

            double convertedAmount = convertCurrency(amount, fromCurrency, toCurrency);
            resultLabel.setText(String.format("Converted Amount: %.2f %s", convertedAmount, toCurrency));
        } catch (NumberFormatException ex) {
            resultLabel.setText("Please enter a valid amount.");
        }
    }

    private double convertCurrency(double amount, String fromCurrency, String toCurrency) {
        double rate = 1.0;

        if (fromCurrency.equals("USD")) {
            if (toCurrency.equals("EUR")) rate = USD_TO_EUR;
            if (toCurrency.equals("GBP")) rate = USD_TO_GBP;
            if (toCurrency.equals("JPY")) rate = USD_TO_JPY;
        } else if (fromCurrency.equals("EUR")) {
            if (toCurrency.equals("USD")) rate = 1 / USD_TO_EUR;
            if (toCurrency.equals("GBP")) rate = USD_TO_GBP / USD_TO_EUR;
            if (toCurrency.equals("JPY")) rate = USD_TO_JPY / USD_TO_EUR;
        } else if (fromCurrency.equals("GBP")) {
            if (toCurrency.equals("USD")) rate = 1 / USD_TO_GBP;
            if (toCurrency.equals("EUR")) rate = USD_TO_EUR / USD_TO_GBP;
            if (toCurrency.equals("JPY")) rate = USD_TO_JPY / USD_TO_GBP;
        } else if (fromCurrency.equals("JPY")) {
            if (toCurrency.equals("USD")) rate = 1 / USD_TO_JPY;
            if (toCurrency.equals("EUR")) rate = USD_TO_EUR / USD_TO_JPY;
            if (toCurrency.equals("GBP")) rate = USD_TO_GBP / USD_TO_JPY;
        }

        return amount * rate;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CurrencyConverter::new);
    }
}
