import javax.swing.*;
import java.awt.*;

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
        JPanel mainMenu = new JPanel(new GridLayout(0,1,PADDING, PADDING));
        mainMenu.setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));
        mainMenu.add( new JButton("Login"));
        mainMenu.add( new JButton("Exit"));
        main.add(mainMenu, BorderLayout.CENTER);

        main.setLocationRelativeTo(null);
        main.pack();
        main.setVisible(true);


    }


}


