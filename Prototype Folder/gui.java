import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.awt.*;
import java.awt.image.*;
import java.lang.*;
import javax.imageio.*;
import javax.swing.border.Border;
import java.io.*;


public class gui implements ActionListener {
    // Screen Resolution Variables
    int horizontalMax=0;
    int vericalMax=0;

    gui() {
        // Creating windows frame, titling it, asnd making it visible

        //Creating main frame for main menu
        JFrame main = new JFrame("MAAD Hotel");


        // Favicon
        ImageIcon favicon = null;
        ImageIcon logo = null;
        java.net.URL imgURL = gui.class.getResource("favicon.png");
        java.net.URL imgURL2 = gui.class.getResource("test.png");
        favicon = new ImageIcon(imgURL);
        logo = new ImageIcon(imgURL2);
        // Youtube video explaining how to scale https://www.youtube.com/watch?v=eZrdU3BvI4E
        Image getLogo = logo.getImage();
        Image scaledLogo = getLogo.getScaledInstance(1164, 966, Image.SCALE_SMOOTH);

        logo = new ImageIcon(scaledLogo);
        main.setIconImage(favicon.getImage());

        // Setting frame dimensions
        Dimension preferred = main.getPreferredSize();
        System.out.print(preferred);

        main.setPreferredSize(preferred);
        main.setMinimumSize(new Dimension(1000, 500));


        // PANEL: Main Menu and Image
        JPanel mainMenu = new JPanel(new GridLayout(0, 2));
        JPanel imagePanel = new JPanel(new GridLayout(2, 3));

        mainMenu.setMaximumSize((new Dimension(40, 40)));
        imagePanel.setPreferredSize((new Dimension(500, 430)));

        imagePanel.add(new JLabel(logo), BorderLayout.CENTER);
        JLabel slogan = new JLabel("Reserve with MAAD Hotesls!") ;
        slogan.setVerticalAlignment(JLabel.BOTTOM);
        slogan.setHorizontalAlignment(JLabel.CENTER);
        slogan.setPreferredSize(new Dimension(200, 200));
        imagePanel.add(slogan);

        //Login and Exit Buttons
        JButton login = new JButton("Login");
        login.setPreferredSize(new Dimension(40, 60));
        JButton Exit = new JButton("Exit");
        Exit.addActionListener(this);
        login.setPreferredSize(new Dimension(40, 40));
        mainMenu.add(login);
        mainMenu.add(Exit);


        main.add(imagePanel, BorderLayout.CENTER);
        main.add(mainMenu, BorderLayout.SOUTH);


        main.pack();
        main.setLocationRelativeTo(null);
        main.setVisible(true);



    }

    //Listener for exit key
    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(99);
    }

    //@Override
    public Dimension getPreferredSize() {
        Dimension screenSize= Toolkit.getDefaultToolkit().getScreenSize();;
        return (new Dimension ((int)screenSize.getWidth(), (int)screenSize.getHeight()));
    }
}


