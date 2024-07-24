
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleCalculatorGUI extends JFrame {
    private JTextField num1Field, num2Field, resultField;
    private JButton addButton, subtractButton, multiplyButton, divideButton;

    public SimpleCalculatorGUI() {
        createView();

        setTitle("Simple Calculator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void createView() {
        JPanel panel = new JPanel();
        getContentPane().add(panel);

        panel.setLayout(new GridLayout(5, 2));

        JLabel num1Label = new JLabel("First Number:");
        panel.add(num1Label);

        num1Field = new JTextField();
        panel.add(num1Field);

        JLabel num2Label = new JLabel("Second Number:");
        panel.add(num2Label);

        num2Field = new JTextField();
        panel.add(num2Field);

        JLabel resultLabel = new JLabel("Result:");
        panel.add(resultLabel);

        resultField = new JTextField();
        resultField.setEditable(false);
        panel.add(resultField);

        addButton = new JButton("Add");
        addButton.addActionListener(new OperationListener("addition"));
        panel.add(addButton);

        subtractButton = new JButton("Subtract");
        subtractButton.addActionListener(new OperationListener("subtraction"));
        panel.add(subtractButton);

        multiplyButton = new JButton("Multiply");
        multiplyButton.addActionListener(new OperationListener("multiplication"));
        panel.add(multiplyButton);

        divideButton = new JButton("Divide");
        divideButton.addActionListener(new OperationListener("division"));
        panel.add(divideButton);
    }

    private class OperationListener implements ActionListener {
        private String operation;

        public OperationListener(String operation) {
            this.operation = operation;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double num1 = Double.parseDouble(num1Field.getText());
                double num2 = Double.parseDouble(num2Field.getText());
                double result = 0;

                switch (operation) {
                    case "addition":
                        result = num1 + num2;
                        break;
                    case "subtraction":
                        result = num1 - num2;
                        break;
                    case "multiplication":
                        result = num1 * num2;
                        break;
                    case "division":
                        if (num2 != 0) {
                            result = num1 / num2;
                        } else {
                            JOptionPane.showMessageDialog(null, "Error: Division by zero");
                            return;
                        }
                        break;
                }
                resultField.setText(String.valueOf(result));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Error: Invalid input");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SimpleCalculatorGUI().setVisible(true);
            }
        });
    }
}
