import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.*;
import java.text.ParseException;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class gui implements ActionListener {
    // GLOBALS
    String User ="";
    String address = "";
    String firstName ="";
    String lastName = "";
    String age = "";
    String emailAddress = "";
    String newPassword ="";
    JFrame mainFrame = new JFrame("MAAD Hotel");
    JFrame adminLoginFrame = new JFrame("MAAD Hotel: Administrator Login");
    JFrame loginFrame = new JFrame("MAAD Hotel: User Login");
    JFrame CreateAccFrame = new JFrame("MAAD Hotel: Create an account");
    JFrame userFrame = new JFrame("MAAD Hotel: Welcome.");
    JFrame adminFrame = new JFrame("MAAD Hotel: Welcome.");
    JFrame HomePageFrame= new JFrame("user home page");

    JFrame reservationFrame = new JFrame("MAAD Hotel: Reservation");
    /**
     * Database is activated when gui runs. This will enable the use of the Database
     * within this class
     */
    DatabaseH db = new DatabaseH();
    Customer guest = new Customer();

    int userAccountID;
    int roomNumber;
    String arrivalDate;
    int stayLength;
    int newRoomNumber;
    double minPrice;
    double maxPrice;
    String roomType;
    int reservationID;


    // Favicon and Images
    java.net.URL imgURL = gui.class.getResource("favicon.png");
    java.net.URL imgURL2 = gui.class.getResource("logo.png");

    //Favicon is the logo on the top left of the window
    ImageIcon favicon = new ImageIcon(imgURL);

    // Logo when signing in
    ImageIcon logo = new ImageIcon(imgURL2);
    // Youtube video explaining how to scale https://www.youtube.com/watch?v=eZrdU3BvI4E

    //Trying to scale logo
    Image getLogo = logo.getImage();
    Image scaledLogo = getLogo.getScaledInstance(1164, 966, Image.SCALE_SMOOTH);
    ImageIcon logoFinal = new ImageIcon(scaledLogo);


    //Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    ;
    //int preferredX = (int)screenSize.getWidth();
    //int preferredY = (int)screenSize.getHeight();
    // Dimension preferred = new Dimension ((int)screenSize.getWidth(), (int)screenSize.getHeight());
    gui() throws FileNotFoundException, ParseException {
        //JFrame mainFrame = new JFrame ("MAAD Hotel");

        mainFrame();

    }
    void HomePageFrame(){
        CreateAccFrame.dispose();
        loginFrame.dispose();

        HomePageFrame.setIconImage(favicon.getImage());

        JPanel HomePage = new JPanel();
        HomePage.setBackground(Color.gray);
        // mainMenu.setMaximumSize((new Dimension(40, 40)));
        JButton Reservation = new JButton("Make Reservation");

        Reservation.setBackground(Color.yellow);

        JButton Cancel = new JButton("Cancel Reservation");
        Cancel.setBackground(Color.CYAN);

        JButton Exit = new JButton("Exit");

        JButton RoomInfo = new JButton("Rooms and Ammenities");
        RoomInfo.setBounds(100,0,800,900);
        RoomInfo.setBackground(Color.green);

        Exit.setActionCommand("Exit");
        Exit.addActionListener(this);
        RoomInfo.setActionCommand("");
        RoomInfo.addActionListener(this);
        Reservation.setActionCommand("");
        Reservation.addActionListener(this);
        Cancel.setActionCommand("");
        Cancel.addActionListener(this);
        //  login.setPreferredSize(new Dimension(40, 40));
        HomePage.add(Reservation);
        HomePage.add(RoomInfo);
        HomePage.add(Cancel);
        HomePage.add(Exit);

        HomePageFrame.add(HomePage, BorderLayout.NORTH);

        //Pack, Locate, and Visibility

        HomePageFrame.setMinimumSize(new Dimension(1200, 1000));
        HomePageFrame.pack();
        //HomePageFrame.setLayout(new BorderLayout(0,500));
        HomePageFrame.setVisible(true);

        //Handling if Users Closes the window too early
        // Source: https://stackoverflow.com/questions/9093448/how-to-capture-a-jframes-close-button-click-event
        HomePageFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                // Tosses a prompt for confirmation
                if (JOptionPane.showConfirmDialog(mainFrame,
                        "Are you sure you want to close this window?", "Close Window?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    try {
                        db.closeDB();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.exit(0);
                }
                // Do nothing, close option tab
                mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        });
    }
    void mainFrame() {
        //System.out.print(preferred);

        //mainFrame.setPreferredSize(new Dimension (0,0));

        // Set GUI's dimensions
        mainFrame.setMinimumSize(new Dimension(800, 500));

    /*    // Favicon and Images
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
*/
        //Setting Favicon
        mainFrame.setIconImage(favicon.getImage());

        // Panels

        // MainMenu Panel
        JPanel mainMenu = new JPanel(new GridLayout(3, 0));
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
        JLabel slogan = new JLabel("Reserve with MAAD Hotels!");
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
                    try {
                        db.closeDB();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.exit(0);
                }
                // Do nothing, close option tab
                mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        });
    }

    // FRAMES
    void loginFrame() {
        // Source: https://study.com/academy/lesson/adding-jtexfields-jbuttons-tool-tips-to-a-jframe-in-java.html
//        JFrame loginFrame = new JFrame("MAAD Hotel: User Login");
        loginFrame.setMinimumSize(new Dimension(1200, 1000));
        loginFrame.setIconImage(favicon.getImage());
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
        //A


        //create back button
        JButton back = new JButton("Back"); // set label to button
        back.setActionCommand("Go back");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == back) {
                    mainFrame.getContentPane().removeAll();
                    loginFrame.getContentPane().removeAll();
                    loginFrame.dispose();
                    mainFrame(); // replace this with correct next frame
                }
            }
        });

        submit.setActionCommand("Authenticate");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User = user.getText();
                newPassword = String.valueOf(password.getPassword());

                // when verified, has access. If not, need to pop a message
                if (db.customerLogin(User, newPassword)) {
                    System.out.println("Customer login.");

                }
                else {
                    System.out.println("Customer failed to login.");
                }

            }
        });
        loginFrame.add(userLabel);
        loginFrame.add(user);
        loginFrame.add(passLabel);
        loginFrame.add(password);
        loginFrame.add(submit);
        loginFrame.add(back);

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
                    try {
                        db.closeDB();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
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
        CreateAccFrame.setIconImage(favicon.getImage());
        CreateAccFrame.show();
        mainFrame.dispose();

// INPUTS Section
        //create label for user name
        JLabel FirstNameLabel = new JLabel();
        FirstNameLabel.setText("Enter First name:");      //set label value for textField1
        FirstNameLabel.setMaximumSize(new Dimension(1200, 40));

        //create text field to get username from the user
        JTextField FirstName = new JTextField(15);    //set length of the text
        FirstName.setMaximumSize(new Dimension(1200, 40));

        //create label for lastname
        JLabel LastNameLabel = new JLabel();
        LastNameLabel.setText("Enter Last name:");      //set label value for textField2
        LastNameLabel.setMaximumSize(new Dimension(1200, 40));

        //create text field to get lastname from the user
        JTextField LastName = new JTextField(15);    //set length for the password
        LastName.setMaximumSize(new Dimension(1200, 40));

        //create label for age
        JLabel AgeLabel = new JLabel();
        AgeLabel.setText("Enter Age:");      //set label value for textField1
        AgeLabel.setMaximumSize(new Dimension(1200, 40));

        //create text field to get age from the user
        JTextField Age = new JTextField(15);    //set length of the text
        Age.setMaximumSize(new Dimension(1200, 40));

        //create text field to get address from the user
        JLabel addressLabel = new JLabel();
        addressLabel.setText("Enter Residential Address:");
        addressLabel.setMaximumSize(new Dimension(1200,40));

        //create text field to get Address from the user
        JTextField Address = new JTextField(15);
        Address.setMaximumSize(new Dimension(1200,40));

        ///AB TESTS

        //create text field to get eamil address from the user
        JLabel  EmailAddressLabel = new JLabel();
        EmailAddressLabel.setText("Enter Email Address:");
        EmailAddressLabel.setMaximumSize(new Dimension(1200,40));

        //create text field to get email Address from the user
        JTextField email = new JTextField(15);
        email.setMaximumSize(new Dimension(1200,40));

        //create label for username
        JLabel UserNameLabel = new JLabel();
        UserNameLabel.setText("Enter User Name:");
        UserNameLabel.setMaximumSize(new Dimension(1200, 40));

        //create text field to get username from the user
        JTextField userName = new JTextField(15);
        userName.setMaximumSize(new Dimension(1200,40));

        //create label for password
        JLabel NewpassLabel = new JLabel();
        NewpassLabel.setText("Create New Password:");      //set label value for textField2
        NewpassLabel.setMaximumSize(new Dimension(1200, 40));

        //create text field to get password from the user
        JPasswordField Newpassword = new JPasswordField(15);    //set length for the password
        Newpassword.setMaximumSize(new Dimension(1200, 40));

        //create submit button
        JButton submit = new JButton("Create an Account"); //set label to button
        submit.setActionCommand("Create user");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstName = FirstName.getText();
                lastName = LastName.getText();
                age = Age.getText();
                address = Address.getText();
                emailAddress = email.getText();
                User = userName.getText();
                newPassword = String.valueOf(Newpassword.getPassword());

                if(FirstName.getText().isEmpty() || LastName.getText().isEmpty() || Age.getText().isEmpty() ||
                Address.getText().isEmpty() || email.getText().isEmpty() || userName.getText().isEmpty() ||
                Newpassword.getPassword().length == 0) {
                    JOptionPane.showMessageDialog(null, "Please fill in all sections.");
                }
                else {
                    String fullName = firstName + " " + lastName;
                    int ageInt = Integer.parseInt(age);
                    guest.setCustomerName(fullName);
                    guest.setCustomerAge(ageInt);
                    guest.setCustomerAddress(address);
                    guest.setCustomerEmail(emailAddress);
                    db.makeAccount(User, newPassword, guest);

                    if (e.getSource() == submit) {
                        mainFrame.getContentPane().removeAll();
                        CreateAccFrame.getContentPane().removeAll();
                        JOptionPane.showMessageDialog(null, "You have successfully created an account.");
                        CreateAccFrame.dispose();

                        reservationFrame(); // Go to reservation page next
                    }
                }
            }
        });

        //create back button
        JButton back = new JButton("Back"); // set label to button
        back.setActionCommand("Go back");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == back) {
                    mainFrame.getContentPane().removeAll();
                    CreateAccFrame.getContentPane().removeAll();
                    CreateAccFrame.dispose();
                    mainFrame(); // replace this with correct next frame
                }
            }
        });

        CreateAccFrame.add(FirstNameLabel);
        CreateAccFrame.add(FirstName);
        CreateAccFrame.add(LastNameLabel);
        CreateAccFrame.add(LastName);
        CreateAccFrame.add(AgeLabel);
        CreateAccFrame.add(Age);
        CreateAccFrame.add(addressLabel);
        CreateAccFrame.add(Address);
        CreateAccFrame.add(EmailAddressLabel);
        CreateAccFrame.add(email);
        CreateAccFrame.add(UserNameLabel);
        CreateAccFrame.add(userName);
        CreateAccFrame.add(NewpassLabel);
        CreateAccFrame.add(Newpassword);
        CreateAccFrame.add(submit);
        CreateAccFrame.add(back);

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
                if (JOptionPane.showConfirmDialog(CreateAccFrame,
                        "Are you sure you want to close this window?", "Close Window?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    try {
                        db.closeDB();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.exit(0);
                }
                // Do nothing, close option tab
                CreateAccFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        });
    }

    void adminLoginFrame() {
        // Source: https://study.com/academy/lesson/adding-jtexfields-jbuttons-tool-tips-to-a-jframe-in-java.html
        // Report Generation, Review Rooms, and something else
//        JFrame adminLoginFrame = new JFrame("MAAD Hotel: Administrator Login");
        adminLoginFrame.setMinimumSize(new Dimension(1200, 1000));
        adminLoginFrame.setIconImage(favicon.getImage());
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
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User = user.getText();
                newPassword = String.valueOf(password.getPassword());
                int adminInt = Integer.parseInt(User);

                // Verify admin ID and password
                if (db.adminLogin(adminInt, newPassword)) {
                    // if verified, go to the next screen
                    System.out.println("Admin successfully login.");
                }
                else {
                    // if failed, promote a message
                    System.out.println("Admin failed login.");

                }
            }
        });


        //create back button
        JButton back = new JButton("Back"); // set label to button
        back.setActionCommand("Go back");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == back) {
                    mainFrame.getContentPane().removeAll();
                    adminLoginFrame.getContentPane().removeAll();
                    adminLoginFrame.dispose();
                    mainFrame(); // replace this with correct next frame
                }
            }
        });
        adminLoginFrame.add(userLabel);
        adminLoginFrame.add(user);
        adminLoginFrame.add(passLabel);
        adminLoginFrame.add(password);
        adminLoginFrame.add(submit);
        adminLoginFrame.add(back);

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
                    try {
                        db.closeDB();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.exit(0);
                }
                // Do nothing, close option tab
                mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        });
    }

    void reservationFrame() {
        reservationFrame.setMinimumSize(new Dimension(1200, 1000));
        reservationFrame.setIconImage(favicon.getImage());
        reservationFrame.show();
        mainFrame.dispose();

/*
        //create label for username
        JLabel userLabel = new JLabel();
        userLabel.setText("Enter Username:");      //set label value for textField1
        userLabel.setMaximumSize(new Dimension(1200, 40));

        //create text field to get username from the user
        JTextField user = new JTextField(15);    //set length of the text
        user.setMaximumSize(new Dimension(1200, 40));
*/
        JLabel welcomeText = new JLabel();
        welcomeText.setText("Welcome " + guest.getCustomerName() + ".");

        JLabel arrival = new JLabel();
        arrival.setText("Arrival - Date");

        JLabel stay = new JLabel();
        stay.setText("Length of stay");

        JLabel guestCount = new JLabel();
        guestCount.setText("Number of guests");

        reservationFrame.add(welcomeText);

        reservationFrame.getContentPane().setLayout(new BoxLayout(reservationFrame.getContentPane(), BoxLayout.Y_AXIS));

        reservationFrame.pack();
        reservationFrame.setLocationRelativeTo(null);
        reservationFrame.setVisible(true);

        // Closing WIndows
        reservationFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                // Tosses a prompt for confirmation
                if (JOptionPane.showConfirmDialog(reservationFrame,
                        "Are you sure you want to close this window?", "Close Window?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    try {
                        db.closeDB();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.exit(0);
                }
                // Do nothing, close option tab
                reservationFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        });

    }

//     void userFrame() {
//         // UserFrame
//         // Tasks: Review Rooms, Edit, Cancel, and Reserve.
//         //
// //        JFrame userFrame = new JFrame("MAAD Hotel: Welcome.");
//         userFrame.setMinimumSize(new Dimension(1200, 1000));
//         userFrame.show();
//         loginFrame.dispose();

//         //BODY

//         userFrame.pack();
//         userFrame.setLocationRelativeTo(null);
//         userFrame.setVisible(true);


//         // Closing WIndows
//         userFrame.addWindowListener(new java.awt.event.WindowAdapter() {
//             @Override
//             public void windowClosing(java.awt.event.WindowEvent windowEvent) {
//                 // Tosses a prompt for confirmation
//                 if (JOptionPane.showConfirmDialog(mainFrame,
//                         "Are you sure you want to close this window?", "Close Window?",
//                         JOptionPane.YES_NO_OPTION,
//                         JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
//                     System.exit(0);
//                 }
//                 // Do nothing, close option tab
//                 mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
//             }
//         });

//     }

    void adminFrame() {
        // UserFrame
        // Tasks: Review Rooms, Edit, Cancel, and Reserve.
        //
//        JFrame adminFrame = new JFrame("MAAD Hotel: Welcome.");
        adminFrame.setMinimumSize(new Dimension(1200, 1000));
        adminFrame.setIconImage(favicon.getImage());
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
                    try {
                        db.closeDB();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
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
                try {
                    db.closeDB();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
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

            case "Create user":

                JOptionPane.showMessageDialog(null, "Account has been created");
                System.out.println(User + "'s Account has been created. Time: "+timeLog() );

                int dialogButton = JOptionPane.YES_NO_OPTION;
                JOptionPane.showConfirmDialog (null, "Would you like to sign in?","Confirmation", dialogButton);
                if(dialogButton == JOptionPane.YES_OPTION) {
                    HomePageFrame();
                    System.out.println(User+ "'s entererd  home page. Time: "+timeLog() );

                    if(dialogButton == JOptionPane.NO_OPTION) {
                        JOptionPane.showMessageDialog(null,"Returning to main menu.");
                        mainFrame();
                    }
                }
                break;
            case "Authenticate":
                // Need the account portion for this
                HomePageFrame();
                System.out.println("Logging in as User. Time: " +timeLog() );

                //userFrame();
                break;
            case "adminLogin":
                System.out.println("Entering Admin Login. Time: " +timeLog() );

                adminLoginFrame();
                break;
            case "Authenticate ":
                System.out.println("Logging in as adminFrame"  +timeLog() );
                adminFrame();
                break;
            case "Create ":

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

    public boolean authenticateUser(String user,char[]  passwd) {
        boolean authenticate = false;

        return authenticate;
    }
}
