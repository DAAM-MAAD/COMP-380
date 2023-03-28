import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class gui implements ActionListener {

    JFrame mainFrame = new JFrame("MAAD Hotel");
    JFrame adminLoginFrame = new JFrame("MAAD Hotel: Administrator Login");
    JFrame loginFrame = new JFrame("MAAD Hotel: User Login");
    JFrame CreateAccFrame = new JFrame("MAAD Hotel: Create an account");
    JFrame userFrame = new JFrame("MAAD Hotel: Welcome.");
    JFrame adminFrame = new JFrame("MAAD Hotel: Welcome.");

    //Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    ;

    //int preferredX = (int)screenSize.getWidth();
    //int preferredY = (int)screenSize.getHeight();
    // Dimension preferred = new Dimension ((int)screenSize.getWidth(), (int)screenSize.getHeight());
    gui() {
        //JFrame mainFrame = new JFrame ("MAAD Hotel");

        mainFrame();
    }

     void mainFrame() {
        //System.out.print(preferred);

        //mainFrame.setPreferredSize(new Dimension (0,0));


        // Set GUI's dimensions
        mainFrame.setMinimumSize(new Dimension(1200, 1000));


        // Favicon and Images
        ImageIcon favicon = null;
        ImageIcon logo = null;
        java.net.URL imgURL = gui.class.getResource("favicon.png");
        java.net.URL imgURL2 = gui.class.getResource("logo.png");

        //Favicon is the logo on the top left of the window
        favicon = new ImageIcon(imgURL);

        // Logo when signing in
        logo = new ImageIcon(imgURL2);
        // Youtube video explaining how to scale https://www.youtube.com/watch?v=eZrdU3BvI4E


        //Trying to scale logo
        Image getLogo = logo.getImage();
        Image scaledLogo = getLogo.getScaledInstance(1164, 966, Image.SCALE_SMOOTH);
        logo = new ImageIcon(scaledLogo);

        //Setting Favicon
        mainFrame.setIconImage(favicon.getImage());


        // Panels

        // MainMenu Panel
        JPanel mainMenu = new JPanel(new GridLayout(3, 1));
        // mainMenu.setMaximumSize((new Dimension(40, 40)));
        JButton Login = new JButton("User Login");
        JButton Exit = new JButton("Exit");
        JButton CreateAcc = new JButton("Create an Account");
        Exit.setActionCommand("Exit");
        Exit.addActionListener(this);
        Login.setActionCommand("Login");
        Login.addActionListener(this);
        CreateAcc.setActionCommand("Create Account");
        CreateAcc.addActionListener(this);
        //  login.setPreferredSize(new Dimension(40, 40));
        mainMenu.add(CreateAcc);
        mainMenu.add(Login);
        mainMenu.add(Exit);
        //CreateAcc.setHorizontalAlignment(50);

        //Admin Panrl
        // Current Bug: Not scaling properly. We got a wide boy.
        JPanel adminPanel = new JPanel(new GridLayout(1, 2));
        JButton adminLogin = new JButton("Administrator Login");
        adminLogin.setMaximumSize(new Dimension(40, 40));
        adminLogin.setHorizontalAlignment(JButton.LEFT);
        adminLogin.setVerticalAlignment(JButton.TOP);
        adminLogin.setActionCommand("adminLogin");
        adminLogin.addActionListener(this);
        adminPanel.add(adminLogin);
        adminPanel.setMaximumSize(new Dimension(40, 40));


        // Image Panel
        // Setting dimensions
        JPanel imagePanel = new JPanel(new GridLayout(2, 3));
        imagePanel.setPreferredSize((new Dimension(500, 700)));


        //Adding Logo and Slogan
        JLabel logoLabel = new JLabel(logo);
        imagePanel.add(logoLabel, BorderLayout.CENTER);
        JLabel slogan = new JLabel("Reserve with MAAD Hotesls!");
        //slogan.setVerticalAlignment(JLabel.);
        slogan.setHorizontalAlignment(JLabel.CENTER);
        slogan.setPreferredSize(new Dimension(200, 200));
        imagePanel.add(slogan);

// Packing It, Setting to the middle, Setting it visible
        // Adding the mainPanel
        mainFrame.add(imagePanel, BorderLayout.CENTER);
        mainFrame.add(adminPanel, BorderLayout.NORTH);
        mainFrame.add(mainMenu, BorderLayout.SOUTH);


        //Pack, Locate, and Visibility
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);

        //Handling if Users Closes the window too early
        // Source: https://stackoverflow.com/questions/9093448/how-to-capture-a-jframes-close-button-click-event
        mainFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                // Tosses a prompt for confirmation
                if (JOptionPane.showConfirmDialog(mainFrame,
                        "Are you sure you want to close this window?", "Close Window?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
                // Do nothing, close option tab
                mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        });

    }


    //
    //
    // FRAMES
    //
    //
    void loginFrame() {
        // Source: https://study.com/academy/lesson/adding-jtexfields-jbuttons-tool-tips-to-a-jframe-in-java.html
//        JFrame loginFrame = new JFrame("MAAD Hotel: User Login");
        loginFrame.setMinimumSize(new Dimension(1200, 1000));
        loginFrame.show();
        mainFrame.dispose();

// INPUTS Section
        //create label for username
        JLabel userLabel = new JLabel();
        userLabel.setText("Enter Username:");      //set label value for textField1
        userLabel.setMaximumSize(new Dimension(1200, 40));

        //create text field to get username from the user
        JTextField user = new JTextField(15);    //set length of the text
        user.setMaximumSize(new Dimension(1200, 40));

        //create label for password
        JLabel passLabel = new JLabel();
        passLabel.setText("Enter Password:");      //set label value for textField2
        passLabel.setMaximumSize(new Dimension(1200, 40));

        //create text field to get password from the user
        JPasswordField password = new JPasswordField(15);    //set length for the password
        password.setMaximumSize(new Dimension(1200, 40));
        //create submit button
        JButton submit = new JButton("Login"); //set label to button
        submit.setActionCommand("Authenticate");
        submit.addActionListener(this);
        loginFrame.add(userLabel);
        loginFrame.add(user);
        loginFrame.add(passLabel);
        loginFrame.add(password);
        loginFrame.add(submit);

        // Source: https://stackoverflow.com/questions/761341/error-upon-assigning-layout-boxlayout-cant-be-shared
        loginFrame.getContentPane().setLayout(new BoxLayout(loginFrame.getContentPane(), BoxLayout.Y_AXIS));



        loginFrame.pack();
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);


        // Closing WIndows
        loginFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                // Tosses a prompt for confirmation
                if (JOptionPane.showConfirmDialog(mainFrame,
                        "Are you sure you want to close this window?", "Close Window?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
                // Do nothing, close option tab
                mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        });

    }

    void CreateAccFrame() {
        // Source: https://study.com/academy/lesson/adding-jtexfields-jbuttons-tool-tips-to-a-jframe-in-java.html
//        JFrame loginFrame = new JFrame("MAAD Hotel: User Login");
        CreateAccFrame.setMinimumSize(new Dimension(1200, 1000));
        CreateAccFrame.show();
        mainFrame.dispose();

// INPUTS Section
        //create label for username
        JLabel FirstNameLabel = new JLabel();
        FirstNameLabel.setText("Enter name:");      //set label value for textField1
        FirstNameLabel.setMaximumSize(new Dimension(1200, 40));

        //create text field to get username from the user
        JTextField FirstName = new JTextField(15);    //set length of the text
        FirstName.setMaximumSize(new Dimension(1200, 40));

        //create label for password
        JLabel LastNameLabel = new JLabel();
        LastNameLabel.setText("Enter Last name:");      //set label value for textField2
        LastNameLabel.setMaximumSize(new Dimension(1200, 40));

        //create text field to get password from the user
        JPasswordField LastName = new JPasswordField(15);    //set length for the password
        LastName.setMaximumSize(new Dimension(1200, 40));

        //create label for age
        JLabel AgeLabel = new JLabel();
        AgeLabel.setText("Enter Age:");      //set label value for textField1
        AgeLabel.setMaximumSize(new Dimension(1200, 40));

        //create text field to get age from the user
        JTextField Age = new JTextField(15);    //set length of the text
        Age.setMaximumSize(new Dimension(1200, 40));

        //create submit button
        JButton submit = new JButton("Create an Account"); //set label to button
        submit.setActionCommand("Authenticate");
        submit.addActionListener(this);
        CreateAccFrame.add(FirstNameLabel);
        CreateAccFrame.add(FirstName);
        CreateAccFrame.add(LastNameLabel);
        CreateAccFrame.add(LastName);
        CreateAccFrame.add(AgeLabel);
        CreateAccFrame.add(Age);
        CreateAccFrame.add(submit);

        // Source: https://stackoverflow.com/questions/761341/error-upon-assigning-layout-boxlayout-cant-be-shared
        CreateAccFrame.getContentPane().setLayout(new BoxLayout(CreateAccFrame.getContentPane(), BoxLayout.Y_AXIS));



        CreateAccFrame.pack();
        CreateAccFrame.setLocationRelativeTo(null);
        CreateAccFrame.setVisible(true);


        // Closing WIndows
        CreateAccFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                // Tosses a prompt for confirmation
                if (JOptionPane.showConfirmDialog(mainFrame,
                        "Are you sure you want to close this window?", "Close Window?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
                // Do nothing, close option tab
                mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        });

    }

    void adminLoginFrame() {
        // Source: https://study.com/academy/lesson/adding-jtexfields-jbuttons-tool-tips-to-a-jframe-in-java.html
        // Report Generation, Review Rooms, and something else
//        JFrame adminLoginFrame = new JFrame("MAAD Hotel: Administrator Login");
        adminLoginFrame.setMinimumSize(new Dimension(1200, 1000));
        adminLoginFrame.show();
        mainFrame.dispose();

        //create label for username
        JLabel userLabel = new JLabel();
        userLabel.setText("Enter Username:");      //set label value for textField1
        userLabel.setMaximumSize(new Dimension(1200, 40));

        //create text field to get username from the user
        JTextField user = new JTextField(15);    //set length of the text
        user.setMaximumSize(new Dimension(1200, 40));

        //create label for password
        JLabel passLabel = new JLabel();
        passLabel.setText("Enter Password:");      //set label value for textField2
        passLabel.setMaximumSize(new Dimension(1200, 40));

        //create text field to get password from the user
        JPasswordField password = new JPasswordField(15);    //set length for the password
        password.setMaximumSize(new Dimension(1200, 40));
        //create submit button
        JButton submit = new JButton("Login"); //set label to button
        submit.setActionCommand("Authenticate ");
        submit.addActionListener(this);
        adminLoginFrame.add(userLabel);
        adminLoginFrame.add(user);
        adminLoginFrame.add(passLabel);
        adminLoginFrame.add(password);
        adminLoginFrame.add(submit);

        // Source: https://stackoverflow.com/questions/761341/error-upon-assigning-layout-boxlayout-cant-be-shared
        adminLoginFrame.getContentPane().setLayout(new BoxLayout(adminLoginFrame.getContentPane(), BoxLayout.Y_AXIS));
        adminLoginFrame.pack();
        adminLoginFrame.setLocationRelativeTo(null);
        adminLoginFrame.setVisible(true);


        // Closing WIndows
        adminLoginFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                // Tosses a prompt for confirmation
                if (JOptionPane.showConfirmDialog(mainFrame,
                        "Are you sure you want to close this window?", "Close Window?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
                // Do nothing, close option tab
                mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        });

    }

    void userFrame() {
        // UserFrame
        // Tasks: Review Rooms, Edit, Cancel, and Reserve.
        //
//        JFrame userFrame = new JFrame("MAAD Hotel: Welcome.");
        userFrame.setMinimumSize(new Dimension(1200, 1000));
        userFrame.show();
        loginFrame.dispose();
        userFrame.pack();
        userFrame.setLocationRelativeTo(null);
        userFrame.setVisible(true);


        // Closing WIndows
        userFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                // Tosses a prompt for confirmation
                if (JOptionPane.showConfirmDialog(mainFrame,
                        "Are you sure you want to close this window?", "Close Window?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
                // Do nothing, close option tab
                mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        });

    }

    void adminFrame() {
        // UserFrame
        // Tasks: Review Rooms, Edit, Cancel, and Reserve.
        //
//        JFrame adminFrame = new JFrame("MAAD Hotel: Welcome.");
        adminFrame.setMinimumSize(new Dimension(1200, 1000));
        adminFrame.show();
        adminLoginFrame.dispose();


        adminFrame.pack();
        adminFrame.setLocationRelativeTo(null);
        adminFrame.setVisible(true);


        // Closing WIndows
        adminFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                // Tosses a prompt for confirmation
                if (JOptionPane.showConfirmDialog(mainFrame,
                        "Are you sure you want to close this window?", "Close Window?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
                // Do nothing, close option tab
                mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // source: https://stackoverflow.com/questions/5936261/how-to-add-action-listener-that-listens-to-multiple-buttons
        String action = e.getActionCommand().toString();


        switch (action) {
            case "Exit":
                JOptionPane.showMessageDialog(mainFrame, "Thank you visiting MAAD Hotel. Application will now close.");
                System.out.println("Exit");
                System.exit(420);
                break;
            case "Login":
                loginFrame();
                System.out.println("Entering user login. Time: "+timeLog() );
                break;

            case "Create Account":
                CreateAccFrame();
                System.out.println("Entering user Creating account. Time: "+timeLog() );
                break;

            case "Authenticate":
                // Need the account portion for this
                System.out.println("Logging in as User. Time: " +timeLog() );

                userFrame();
                break;
            case "adminLogin":
                System.out.println("Entering Admin Login. Time: " +timeLog() );
                adminLoginFrame();
                break;
            case "Authenticate ":
                System.out.println("Logging in as adminFrame"  +timeLog() );
                adminFrame();
                break;
            default:
                JOptionPane.showMessageDialog(mainFrame, "Sorry! Skill Issue");
        }
    }


    // Source: https://www.w3schools.com/java/java_date.asp
    public String timeLog(){
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        return formattedDate;
    }

}
