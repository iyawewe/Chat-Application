import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Client {


    public  static class main extends JFrame implements ActionListener {
        JTextField text;
        JPanel textField;
        Box vertical=Box.createVerticalBox();//messages to be assigned below each other
        main(){

            setLayout(null);
            setSize(450,700);
            setLocation(200,50);
            getContentPane().setBackground(new Color(0xF5F5F5));

            setUndecorated(true);

            //pannel setup
            JPanel p1=new JPanel();
            p1.setBounds(0,0,450,70);
            p1.setLayout(null);
            p1.setBackground(new Color(0x3E4E5E));
            add(p1);

            //back button
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
            JLabel name=new JLabel("USER-2");
            name.setBounds(190,20,130,40);
            name.setFont(new Font("SansSerif", Font.BOLD, 15));
            name.setForeground(Color.WHITE);
            p1.add(name);

            //text frield for text  messages
            textField=new JPanel();
            textField.setBackground(Color.DARK_GRAY);
            textField.setLayout(new BorderLayout());
            textField.setBounds(5, 75, 440, 570);
            add(textField);


            text=new JTextField();
            text.setBounds(5,655,310,40);
            text.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
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
            send.setBounds(320,655,123,40);
            text.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
            send.addActionListener(this);
            add(send);


            setVisible(true);
        }
        public ImageIcon createScaledIcon(String path,int width,int height)
        {
            ImageIcon originalIcon=new ImageIcon(ClassLoader.getSystemResource(path));
            Image OriginalImage=originalIcon.getImage();
            Image scaledImage= originalIcon.getImage().getScaledInstance(width,height,Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
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
            panel.setBackground(new Color(0xF5F5F5));  // match window background
            panel.add(output);

            return panel;
        }

    }
    public static void main(String[] args) {
        new main();
    }
}