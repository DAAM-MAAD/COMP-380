import javax.swing.*;
import java.awt.*;

public class gui{
	
	gui() {
 		JFrame main = new JFrame("Dababy");
      Dimension preferred = main.getPreferredSize();
 		main.setPreferredSize(preferred);
 		main.setLayout(null);
 		main.setVisible(true);
//  Dimension d = new Dimension();
//     JFrame jframe = new JFrame("JFrame Size Example");
//     jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
// 
//     // set the jframe size and location, and make it visible
//     jframe.setMinimumSize(test);
//     jframe.setSize(1000, 1000);
     main.setLocationRelativeTo(null);
     main.pack();
//     jframe.setVisible(true);
    main.setExtendedState (java.awt.Frame.MAXIMIZED_BOTH);
    int height = java.awt.Frame.MAXIMIZED_VERT;
    int width = java.awt.Frame.MAXIMIZED_HORIZ;
    JButton Reserve = new JButton ("Reserve Room");
    Reserve.setBounds( width/4, height/4, 150, 40 ) ;
   main.add(Reserve);
		
	}
	
}