import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

public class Main {

    static DataOutputStream dataOutputStream;

    public  static class main extends JFrame implements ActionListener {
        JTextField text;
        JPanel textField;
        static Box vertical=Box.createVerticalBox();//messages to be assigned below each other
        main() {

            setLayout(null);
            setSize(450, 700);
            setLocation(200, 50);
            getContentPane().setBackground(new Color(0xF5F5F5));

            setUndecorated(true);

            //panel setup
            JPanel p1 = new JPanel();
            p1.setBounds(0, 0, 450, 70);
            p1.setLayout(null);
            p1.setBackground(new Color(0x3E4E5E));
            add(p1);

            //back image
            JButton backButton = new JButton("Back");
            backButton.setBounds(10, 20, 70, 30);
            backButton.setFocusPainted(false);
            backButton.setFont(new Font("SansSerif", Font.PLAIN, 12));
            backButton.setBackground(new Color(0x4CAF50));
            backButton.setForeground(Color.WHITE);
            backButton.setBorderPainted(false);

            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);  // closes the window
                }
            });
            p1.add(backButton);

            //contact name
            JLabel name = new JLabel("USER-1");
            name.setBounds(190, 20, 130, 40);
            name.setFont(new Font("SansSerif", Font.BOLD, 15));
            name.setForeground(Color.WHITE);
            p1.add(name);

            //text field for text messages
            textField = new JPanel();
            textField.setLayout(new BorderLayout());
            textField.setBounds(5, 75, 440, 570);
            textField.setBackground(Color.DARK_GRAY);
            add(textField);

            // Create a JScrollPane with vertical scroll
            JScrollPane scrollPane = new JScrollPane(vertical);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setBorder(null); // optional: cleaner look
            scrollPane.getViewport().setBackground(Color.DARK_GRAY); // match background

            textField.add(scrollPane, BorderLayout.CENTER);

//            textField = new JPanel();
//            textField.setBackground(Color.DARK_GRAY);
//            textField.setLayout(new BorderLayout());
//            textField.setBounds(5, 75, 440, 570);
//            add(textField);


            text = new JTextField();
            text.setBounds(5, 655, 310, 40);
            text.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
            text.setBackground(Color.WHITE);
            text.setForeground(Color.BLACK);
            add(text);

            //button
            JButton send = new JButton("SEND IT");
            send.setBackground(new Color(0x4CAF50));
            send.setForeground(Color.WHITE);
            send.setFocusPainted(false);
            send.setBorderPainted(false);
            send.setFont(new Font("SansSerif", Font.BOLD, 14));
            send.setBounds(320, 655, 123, 40);
            text.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
            send.addActionListener(this);
            add(send);


            setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String message = text.getText();
            if (message.trim().isEmpty()) return;

            JPanel messagePanel = new JPanel();
            messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));
            messagePanel.setBackground(Color.DARK_GRAY);

            JLabel output = new JLabel(message);
            output.setOpaque(true);
            output.setBackground(Color.GREEN);
            output.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
            output.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

            JPanel p2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            p2.setBackground(Color.DARK_GRAY);
            p2.add(output);

            messagePanel.add(p2);
            messagePanel.add(Box.createVerticalStrut(15));

            vertical.add(messagePanel);

            textField.add(vertical, BorderLayout.PAGE_START);
            textField.revalidate();
            textField.repaint();

            try {
                dataOutputStream.writeUTF(message);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            text.setText(""); // clear input
        }


        public static JPanel formatLabel(String message) {
            JLabel output = new JLabel(message);
            output.setOpaque(true);
            output.setBackground(new Color(0xDCF8C6));  // WhatsApp-style bubble
            output.setForeground(Color.BLACK);
            output.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
            output.setFont(new Font("SansSerif", Font.PLAIN, 14));

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setBackground(new Color(0xF5F5F5));  // match a window background
            panel.add(output);

            return panel;
        }

    }
    public static void main(String[] args) {
        main ui =new main();
        try{
            ServerSocket socket=new ServerSocket(6000);
            while (true)
            {
                Socket s=socket.accept();
                DataInputStream dataInputStream=new DataInputStream(s.getInputStream());
                dataOutputStream=new DataOutputStream(s.getOutputStream());
                while (true)
                {
                    String msg =dataInputStream.readUTF();
                    JPanel panel= main.formatLabel(msg);

                    JPanel left= new JPanel(new FlowLayout(FlowLayout.LEFT));
                    left.setBackground(new Color((0xf5f5f5)));
                    left.add(panel);

                    ui.vertical.add(left);
                    ui.vertical.add(Box.createVerticalStrut(10));

                    //refresh chat
                    SwingUtilities.invokeLater(() -> {
                        ui.vertical.revalidate();
                        ui.vertical.repaint();

                    });
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}