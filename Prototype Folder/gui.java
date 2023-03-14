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
    public static final int PADDING = 4;

    gui() {
        // Creating windows frame, titling it, asnd making it visible

        //Creating main frame for main menu
        JFrame main = new JFrame("MAAD Hotel");

        // Favicon
        ImageIcon favicon = null;
        java.net.URL imgURL = gui.class.getResource("favicon.png");
        favicon = new ImageIcon(imgURL);
        main.setIconImage(favicon.getImage());

        // Setting frame dimensions
        Dimension preferred = main.getPreferredSize();
        main.setPreferredSize(preferred);
        main.setMinimumSize(new Dimension(1000, 500));


        // PANEL: Main Menu and Image
        JPanel mainMenu = new JPanel(new GridLayout(0, 2));
        JPanel imagePanel = new JPanel(new GridLayout(1, 1));

        mainMenu.setMaximumSize((new Dimension(40, 40)));
        imagePanel.setPreferredSize((new Dimension(500, 430)));

        imagePanel.add(new JLabel(favicon));
        imagePanel.add(new JLabel("Reserve with MAAD!"), BorderLayout.SOUTH);

        //Login and Exit Buttons
        JButton login = new JButton("Login");
        login.setPreferredSize(new Dimension(40, 60));
        JButton Exit = new JButton("Exit");
        Exit.addActionListener(this);
        login.setPreferredSize(new Dimension(40, 40));
        mainMenu.add(login);
        mainMenu.add(Exit);


        main.add(imagePanel, BorderLayout.NORTH);
        main.add(mainMenu, BorderLayout.SOUTH);


        main.pack();
        main.setVisible(true);



    }

    //Listener for exit key
    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(99);
    }
}


