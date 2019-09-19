package calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Front extends JFrame {
    private JButton button = new JButton("Вычисление");
    private JTextField input = new JTextField("", 100);

    private Front() {
        super("Калькулятор");
        int x = Toolkit.getDefaultToolkit().getScreenSize().width/3;
        int y = Toolkit.getDefaultToolkit().getScreenSize().height/3;
        this.setBounds(x, y, x, y);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(3,2,2,2));
        JLabel label = new JLabel("Введите выражение:");
        container.add(label);
        container.add(input);

        ButtonGroup group = new ButtonGroup();
        button.addActionListener(new ButtonEventListener());
        container.add(button);
    }

    class ButtonEventListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String message;
            try {
                message = Back.evaluate(input.getText()).toString();
            } catch (Exception exc){
                message = "Ошибка ввода.";
            }
            JOptionPane.showMessageDialog(null,
                    message,
                    "Ответ",
                    JOptionPane.PLAIN_MESSAGE);
        }
    }

    public static void main(String[] args) {
        Front app = new Front();
        app.setVisible(true);
    }
}
