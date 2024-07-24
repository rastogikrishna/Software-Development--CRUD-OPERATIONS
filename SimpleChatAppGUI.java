import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class SimpleChatAppGUI {
    private static Queue<String> messages = new LinkedList<>();
    private static JTextArea chatArea;
    private static JTextField inputField;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Simple Chat Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);

        inputField = new JTextField();
        inputField.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        JButton receiveButton = new JButton("Receive Messages");
        receiveButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                receiveMessages();
            }
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(inputField, BorderLayout.CENTER);
        panel.add(sendButton, BorderLayout.EAST);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(panel, BorderLayout.SOUTH);
        frame.add(receiveButton, BorderLayout.NORTH);

        frame.setVisible(true);
    }

    private static void sendMessage() {
        String message = inputField.getText();
        if (!message.isEmpty()) {
            messages.offer(message);
            chatArea.append("Sent: " + message + "\n");
            inputField.setText("");
        }
    }

    private static void receiveMessages() {
        chatArea.append("Received Messages:\n");
        while (!messages.isEmpty()) {
            chatArea.append(messages.poll() + "\n");
        }
    }
}
