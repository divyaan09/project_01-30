import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PasswordStrengthTester extends JFrame implements ActionListener {
    private JTextField passwordField;
    private JLabel resultLabel;

    public PasswordStrengthTester() {
        // Frame setup
        setTitle("Password Strength Tester");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Center the window

        // UI Components
        JLabel label = new JLabel("Enter Password:");
        passwordField = new JTextField(20);
        JButton checkButton = new JButton("Check Strength");
        resultLabel = new JLabel("");

        // Layout
        setLayout(new GridLayout(4, 1));
        add(label);
        add(passwordField);
        add(checkButton);
        add(resultLabel);

        // Event
        checkButton.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String password = passwordField.getText();
        String strength = checkPasswordStrength(password);
        resultLabel.setText("Strength: " + strength);
    }

    public static String checkPasswordStrength(String password) {
        int score = 0;

        if (password.length() >= 8) score++;
        if (password.matches(".*[A-Z].*")) score++;
        if (password.matches(".*[a-z].*")) score++;
        if (password.matches(".*\\d.*")) score++;
        if (password.matches(".*[!@#$%^&*()_+=\\-\\[\\]{};:'\"\\\\|,.<>/?].*")) score++;

        String[] commonPasswords = {
            "123456", "password", "123456789", "12345678", "qwerty", "abc123"
        };
        for (String common : commonPasswords) {
            if (password.equalsIgnoreCase(common)) return "Very Weak (Common Password)";
        }

        switch (score) {
            case 5: return "Very Strong 🔐";
            case 4: return "Strong 🔒";
            case 3: return "Moderate 🛡️";
            case 2: return "Weak ⚠️";
            default: return "Very Weak ❌";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PasswordStrengthTester());
    }
}
