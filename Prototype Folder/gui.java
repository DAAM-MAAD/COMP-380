import javax.swing.*;
import java.awt.*;

public class gui {

    gui() {
        JFrame main = new JFrame("MAAD");
        Dimension preferred = main.getPreferredSize();
        main.setPreferredSize(preferred);
        main.setLayout(null);
        main.setVisible(true);

        main.setLocationRelativeTo(null);
        main.pack();

        main.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        int height = java.awt.Frame.MAXIMIZED_VERT;
        int width = java.awt.Frame.MAXIMIZED_HORIZ;
        JButton Reserve = new JButton("Reserve Room");
        Reserve.setBounds(width / 4, height / 4, 150, 40);
        main.add(Reserve);

    }


}


