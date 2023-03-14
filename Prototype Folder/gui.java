import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.image.*;
import java.lang.*;
import javax.imageio.*;
import java.io.*;

public class gui {
    public static final int PADDING = 50;
    gui() {
        // Creating windows frame, titling it, asnd making it visible
        JFrame main = new JFrame("MAAD");
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
        mainMenu.setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));
        mainMenu.add( new JButton("Login"));
        mainMenu.add( new JButton("Exit"));
//        BufferedImage Logo = ImageIO.read(new File("Logo Cropped.png"));
//        JLabel logoLabel = new JLabel(new ImageIcon(Logo));
//        JPanel logo = new JPanel(new GridLayout(0, 1, PADDING, PADDING));
//        logo.setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));
//        logo.add(logoLabel, BorderLayout.CENTER);
//        ImageIcon logo = new ImageIcon("Logo Cropped.png");
//        main.add(new JLabel(logo), BorderLayout.CENTER);


      //main.add(logo, BorderLayout.CENTER);
       main.add(mainMenu, BorderLayout.CENTER);

        //main.setLocationRelativeTo(null);
        main.pack();
        main.setVisible(true);


    }


}


<<<<<<< HEAD
public class gui{
	
   gui() {
      JFrame main = new JFrame("Dababy");
      Dimension preferred = main.getPreferredSize();
      main.setPreferredSize(preferred);
      main.setLayout(null);
      main.setVisible(true);
   
      main.setLocationRelativeTo(null);
      main.pack();
      main.setExtendedState (java.awt.Frame.MAXIMIZED_BOTH);
      int height = java.awt.Frame.MAXIMIZED_VERT;
      int width = java.awt.Frame.MAXIMIZED_HORIZ;
      JButton Reserve = new JButton ("Reserve Room");
      Reserve.setBounds( width/4, height/4, 150, 40 ) ;
      main.add(Reserve);
   	
   }
	
}
=======
>>>>>>> 0e31fa68701708563ddcb33d2846d09e20c31619
