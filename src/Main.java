import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class Main {

    public  static class main extends JFrame implements ActionListener {
        main(){
            setLayout(null);
            setSize(450,700);
            setLocation(200,50);
            getContentPane().setBackground(Color.black);

            //pannel setup
            JPanel p1=new JPanel();
            p1.setBackground(Color.gray);
            p1.setBounds(0,0,450,70);
            p1.setLayout(null);
            add(p1);

            //back image
            JLabel back=new JLabel(createScaledIcon("images/back.png",25,25));
            back.setBounds(5,20,20,20);
            p1.add(back);
            //adding action event on back icon
            back.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.exit(0);
                }
            });

            //profile image
            JLabel profile=new JLabel(createScaledIcon("images/profile.png",40,40));
            profile.setBounds(70,20,40,40);
            p1.add(profile);

            //contact name
            JLabel name=new JLabel("Camavinga");
            name.setBounds(120,20,100,30);
            name.setFont(new Font("SansSerif", Font.BOLD, 15));
            p1.add(name);






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

        }
    }
    public static void main(String[] args) {
         new main();
    }
}