import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.image.*;
import java.lang.*;
import javax.imageio.*;
import java.io.*;

public class gui {
    public static final int PADDING = 4;
    gui() {
        // Creating windows frame, titling it, asnd making it visible

        JFrame main = new JFrame("MAAD Hotel");
    ImageIcon favicon = null;

    java.net.URL imgURL = gui.class.getResource("favicon.png");

    favicon = new ImageIcon(imgURL);
    main.setIconImage(favicon.getImage());

        Dimension preferred = main.getPreferredSize();
        main.setPreferredSize(preferred);
        main.setMinimumSize(new Dimension(500, 300));
        //main.setLayout(null);




//        //Creates Buttons
//        main.setAlwaysOnTop(false);
//        JButton Reserve = new JButton("Reserve Room");
//        Reserve.setBounds(0, 20, 150, 40);

        // PANEL
        JPanel mainMenu = new JPanel(new GridLayout(0,2,PADDING, PADDING));
        JPanel imagePanel = new JPanel(new GridLayout(1, 1, PADDING, PADDING));
        imagePanel.add(new JLabel(favicon));
        imagePanel.add(new JLabel ("Reserve with MAAD!"));

        mainMenu.setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));
        imagePanel.setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));
        mainMenu.add( new JButton("Login"));
        mainMenu.add( new JButton("Exit"));



        //main.add(logo, BorderLayout.CENTER);
        main.add(imagePanel, BorderLayout.NORTH);
        main.add(mainMenu, BorderLayout.CENTER);

        //main.setLocationRelativeTo(null);
        main.pack();
        main.setVisible(true);


    }


}