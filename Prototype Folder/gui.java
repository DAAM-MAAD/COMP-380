import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

public class gui implements ActionListener {
    // GLOBALS
    JTextField userName;
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
    JFrame adminFrame = new JFrame("MAAD Hotel: Welcome.");
    JFrame HomePageFrame= new JFrame("MAAD Hotel: Welcome " + User);
    JFrame PaymentFrame = new JFrame("MAAD Hotel: Payment");
    //JFrame cancelFrame = new JFrame("MAAD Hotel: Cancel Reservation ");
    JFrame cancelF = new JFrame("Cancel reservation");

    JFrame reservationFrame = new JFrame("MAAD Hotel: Reservation");
    /**
     * Database is activated when gui runs. This will enable the use of the Database
     * within this class
     */
    DatabaseH db = new DatabaseH();
    Customer guest = new Customer();
    Account guestAccount = new Account();


    // Favicon and Images
    java.net.URL imgURL = gui.class.getResource("favicon.png");
    java.net.URL imgURL2 = gui.class.getResource("logo.png");

    java.net.URL imgURL4 = gui.class.getResource("image.png");

    //Favicon is the logo on the top left of the window
    ImageIcon favicon = new ImageIcon(imgURL);
    ImageIcon reserve = new ImageIcon(imgURL4);

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
    gui() throws FileNotFoundException, ParseException,SQLException {
        //JFrame mainFrame = new JFrame ("MAAD Hotel");
        mainFrame();
    }

    void CloseWindowListener(JFrame frame) {
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                // Tosses a prompt for confirmation
                if (JOptionPane.showConfirmDialog(frame,
                        "Are you sure you want to close this window?", "Close Window?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    try {
                        db.closeDB();
                    } catch (IOException | SQLException e) {
                        e.printStackTrace();
                    }
                    System.exit(0);
                }
                // Do nothing, close option tab
                frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        });
    }

    void HomePageFrame(){
        CreateAccFrame.dispose();
        loginFrame.dispose();
        PaymentFrame.dispose();

        HomePageFrame.setIconImage(favicon.getImage());

        JPanel HomePage = new JPanel();
        HomePage.setBackground(Color.gray);
        // mainMenu.setMaximumSize((new Dimension(40, 40)));
        JButton Reservation = new JButton("Make Reservation");

        Reservation.setBackground(Color.yellow);

        JButton Cancel = new JButton("Cancel Reservation");
        Cancel.setBackground(Color.CYAN);

        JButton Exit = new JButton("Exit");

/*        JButton RoomInfo = new JButton("Rooms and Ammenities");
        RoomInfo.setBounds(100,0,800,900);
        RoomInfo.setBackground(Color.green);*/

        Exit.setActionCommand("Exit");
        Exit.addActionListener(this);
/*        RoomInfo.setActionCommand("");
        RoomInfo.addActionListener(this);*/
        Reservation.setActionCommand("Make Reservation");
        Reservation.addActionListener(this);
        Cancel.setActionCommand("Cancel Reservation");
        Cancel.addActionListener(this);
        //  login.setPreferredSize(new Dimension(40, 40));
        HomePage.add(Reservation);
        //HomePage.add(RoomInfo);
        HomePage.add(Cancel);
        HomePage.add(Exit);

       HomePageFrame.add(HomePage, BorderLayout.NORTH);
        JPanel imagePanel = new JPanel(new GridLayout(1, 1));
        imagePanel.setPreferredSize((new Dimension(500, 700)));

        //Adding Logo and Slogan
        JLabel logoLabel = new JLabel(reserve);
        imagePanel.add(logoLabel, BorderLayout.CENTER);
        HomePageFrame.add(imagePanel, BorderLayout.CENTER);

        //Pack, Locate, and Visibility

        HomePageFrame.setMinimumSize(new Dimension(1200, 1000));
        HomePageFrame.pack();
        //HomePageFrame.setLayout(new BorderLayout(0,500));
        HomePageFrame.setVisible(true);

        //Closing Windows
        CloseWindowListener(HomePageFrame);

        //Handling if Users Closes the window too early
        // Source: https://stackoverflow.com/questions/9093448/how-to-capture-a-jframes-close-button-click-event

    }
    void mainFrame() {
        //System.out.print(preferred);
        //mainFrame.setPreferredSize(new Dimension (0,0));
        // Set GUI's dimensions
        mainFrame.setMinimumSize(new Dimension(800, 500));
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

        //Closing Window
        CloseWindowListener(mainFrame);

        //Handling if Users Closes the window too early
        // Source: https://stackoverflow.com/questions/9093448/how-to-capture-a-jframes-close-button-click-event
    }

    // FRAMES
    void loginFrame() {
        // Source: https://study.com/academy/lesson/adding-jtexfields-jbuttons-tool-tips-to-a-jframe-in-java.html
//        JFrame loginFrame = new JFrame("MAAD Hotel: User Login");
        loginFrame.setMinimumSize(new Dimension(1200, 1000));
        loginFrame.setIconImage(favicon.getImage());
    
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
                    reservationFrame.dispose();
                    adminFrame.dispose();
                    adminLoginFrame.dispose();
                    loginFrame.dispose();
                    CreateAccFrame.dispose();
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
                    JOptionPane.showMessageDialog(mainFrame, "You have logged in.");
                    guestAccount = db.getAccount(User);
                    loginFrame.dispose();
                    HomePageFrame();
                    System.out.println("Customer " + User + " login."  +timeLog()  );

                } else {
                    System.out.println("Customer " + User+ " failed to login." +timeLog() );
                    System.out.println("Customer failed login.");
                    JOptionPane.showMessageDialog(mainFrame, "Customer failed login.");
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
        CloseWindowListener(loginFrame);
    }

    void CreateAccFrame() {
        // Source: https://study.com/academy/lesson/adding-jtexfields-jbuttons-tool-tips-to-a-jframe-in-java.html
//        JFrame loginFrame = new JFrame("MAAD Hotel: User Login");
        CreateAccFrame.setMinimumSize(new Dimension(1200, 1000));
        CreateAccFrame.setIconImage(favicon.getImage());
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
        userName = new JTextField(15);
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
                    try {
                        db.makeAccount(User, newPassword, guest);
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }

                    if (e.getSource() == submit) {
                        mainFrame.getContentPane().removeAll();
                        CreateAccFrame.getContentPane().removeAll();
                        JOptionPane.showMessageDialog(null, "You have successfully created an account.");
                        CreateAccFrame.dispose();

                        HomePageFrame(); // Go to reservation page next
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
                    reservationFrame.dispose();
                    adminFrame.dispose();
                    adminLoginFrame.dispose();
                    loginFrame.dispose();
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

        // Closing Window
        CloseWindowListener(CreateAccFrame);
    }

    void adminLoginFrame() {
        // Source: https://study.com/academy/lesson/adding-jtexfields-jbuttons-tool-tips-to-a-jframe-in-java.html
        // Report Generation, Review Rooms, and something else
//        JFrame adminLoginFrame = new JFrame("MAAD Hotel: Administrator Login");
        adminLoginFrame.setMinimumSize(new Dimension(1200, 1000));
        adminLoginFrame.setIconImage(favicon.getImage());
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
        //submit.setActionCommand("Authenticate ");
        //submit.addActionListener(this);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User = user.getText();
                newPassword = String.valueOf(password.getPassword());
                Integer adminInt = Integer.parseInt(User);

                // Verify admin ID and password
                if (db.adminLogin(adminInt, newPassword)) {
                    // if verified, go to the next screen
                    System.out.println("Admin successfully login.");
                    JOptionPane.showMessageDialog(mainFrame, "Admin successful login.");
                    adminLoginFrame.dispose();
                    adminHomeFrame();
                }else{
                    if(password.getPassword().length == 0 ) {
                        // if failed, promote a message
                        System.out.println("Admin failed login.");
                        JOptionPane.showMessageDialog(mainFrame, "Please fill in the blanks, Admin failed login.");
                    }else{
                        System.out.println("Admin failed login.");
                        JOptionPane.showMessageDialog(mainFrame, "Wrong password/username try again, Admin failed login.");
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
                    adminLoginFrame.getContentPane().removeAll();
                    reservationFrame.dispose();
                    adminFrame.dispose();
                    adminLoginFrame.dispose();
                    loginFrame.dispose();
                    CreateAccFrame.dispose();
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

        // Closing Windows
        CloseWindowListener(adminLoginFrame);
    }

    void reservationFrame() {
        reservationFrame.setMinimumSize(new Dimension(1200, 1000));
        reservationFrame.setIconImage(favicon.getImage());

        HomePageFrame.dispose();

        SpinnerModel value = new SpinnerNumberModel(1,1,4,1);
/*        SpinnerModel value2 = new SpinnerNumberModel(0,0,4,1);
        SpinnerModel value3 = new SpinnerNumberModel(0,0,4,1);*/
        Date dates = new Date();
        SpinnerDateModel date = new SpinnerDateModel(dates, null, null, Calendar.DATE);
        JLabel welcomeText = new JLabel();
        welcomeText.setText("Welcome " + guest.getCustomerName() + ".");

        JLabel arrivalLabel = new JLabel();
        arrivalLabel.setText("Arrival - Date");
        arrivalLabel.setMaximumSize(new Dimension(120,40));
        JSpinner dateSpinner = new JSpinner(date);
        JSpinner.DateEditor de = new JSpinner.DateEditor(dateSpinner,"MM/dd/yyyy");
        dateSpinner.setToolTipText("Hightlight the number to change");
        dateSpinner.setEditor(de);
        dateSpinner.setMaximumSize(new Dimension(100,40));

        //  JTextField arrvial = new JTextField("mm-D-year");
        //arrvial.setMaximumSize(new Dimension(1200,40));

        JLabel stayLabel = new JLabel();
        stayLabel.setText("Length of stay");
        stayLabel.setMaximumSize(new Dimension(120,40));


        SpinnerModel staySpinner = new SpinnerNumberModel(1, 1, 7, 1);
        JSpinner staySpinner2 = new JSpinner(staySpinner);
        staySpinner2.setMaximumSize(new Dimension(50,40));
       

        JTextField stay = new JTextField(5);
        stay.setMaximumSize(new Dimension(120,40));


        JLabel Adults = new JLabel();
        Adults.setText("Number of guests");
        JSpinner numGuestSpinner = new JSpinner(value);
        numGuestSpinner.setMaximumSize(new Dimension(50,40));

        JLabel Roomselection = new JLabel();
        Roomselection.setText("Room Selection");
        Roomselection.setMaximumSize(new Dimension(120,40));
        DefaultListModel<String> l1 = new DefaultListModel<>();
        l1.addElement("Classic - $149.99 per night");
        l1.addElement("Premium - $199.99 per night");
        l1.addElement("Deluxe - $249.99 per night");
        l1.addElement("Business - $274.99 per night");
        l1.addElement("Honeymoon - $299.99 per night");
        JList<String> list = new JList<>(l1);
        list.setMaximumSize(new Dimension(260,100));

        JLabel RoomType = new JLabel();
        RoomType.setText("Room type selection");
        RoomType.setMaximumSize(new Dimension(120,40));
        DefaultListModel<String> l2 = new DefaultListModel<>();
        l2.addElement("1 king");
        l2.addElement("2 king");
        l2.addElement("1 queen");
        l2.addElement("2 queen");
        JList<String> list2 = new JList<>(l2);
        list2.setMaximumSize(new Dimension(100,80));

        JCheckBox checkbox1 = new JCheckBox("Agree to Hotel policies and regulations");

        JButton submit = new JButton("Make Reservation"); //set label to button
        submit.setActionCommand("");

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                final String[] roomClassT = new String[1];
                final String[] roomAmen = new String[1];

                try {
                    numGuestSpinner.commitEdit();
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
                Integer numGuestSpinner2 = (Integer) numGuestSpinner.getValue();

                Date dateSpinner2 = (Date) dateSpinner.getValue();
                int stay2 = (int) staySpinner2.getValue();

                if(!checkbox1.isSelected()){
                    JOptionPane.showMessageDialog(null, "can't progessive forword if you don't agree with the hotel policies ");
                }else{
                    if ((list.getSelectedIndex() != -1) && (list2.getSelectedIndex() != -1)) {
                        roomClassT[0] = list.getSelectedValue();
                        roomAmen[0] = list2.getSelectedValue();
                    }

                    String[] roomClassArr = roomClassT[0].split(" ", 2);

                    boolean exists = false;

                    for (Room r : db.getDb().keySet()) {
                        if (r.getAmenities().equalsIgnoreCase(roomAmen[0]) && r.getRoomType().equalsIgnoreCase(roomClassArr[0].toLowerCase())) {
                            exists = true;
                        }
                    }
                    if (roomClassArr[0].equalsIgnoreCase("honeymoon") && numGuestSpinner2 == 1) {
                        System.out.println("Yes");
                        JOptionPane.showMessageDialog(null, "You may not book a honeymoon for yourself. That is just sad.");
                        return;
                   }
                    else if (roomClassArr[0].equalsIgnoreCase("honeymoon") && numGuestSpinner2 > 2) {
                        JOptionPane.showMessageDialog(null, "You want to book a honeyroom room for " + numGuestSpinner2 + " people. You need Jesus!");
                        return;
                    }

                    if (!exists) {
                        JOptionPane.showMessageDialog(null, "There are no rooms that is a " + roomClassT[0] +
                                " and has " + roomAmen[0] + ". Please selection different options.");
                        return;
                    }

                    }
                    try {
                        db.reservationHelper(guestAccount.getAccountID(), dateSpinner2, stay2, numGuestSpinner2, roomClassT[0], roomAmen[0]);
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }

                    System.out.println("Reservation made.");
                    JOptionPane.showMessageDialog(mainFrame, "Reservation made.");
                    reservationFrame.getContentPane().removeAll();
                    reservationFrame.dispose();
                    adminFrame.dispose();
                    adminLoginFrame.dispose();
                    loginFrame.dispose();
                    CreateAccFrame.dispose();
                    PaymentFrame.getContentPane().removeAll();
                    PaymentFrame.dispose();

                    try {
                        PaymentFrame();
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                }

        });
        //create back button
        JButton back = new JButton("Back"); // set label to button
        // back.setActionCommand("Go back");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == back) {
                    reservationFrame.getContentPane().removeAll();

                    reservationFrame.dispose();
                    adminFrame.dispose();
                    adminLoginFrame.dispose();
                    loginFrame.dispose();
                    CreateAccFrame.dispose();

                    HomePageFrame(); // replace this with correct next frame
                }
            }
        });

        reservationFrame.add(welcomeText);
        reservationFrame.add(Adults);
        reservationFrame.add(numGuestSpinner);
        reservationFrame.add(arrivalLabel);
        reservationFrame.add(dateSpinner);
        reservationFrame.add(stayLabel);
        reservationFrame.add(staySpinner2);
        reservationFrame.add(Roomselection);
        reservationFrame.add(list);
        reservationFrame.add(RoomType);
        reservationFrame.add(list2);
        reservationFrame.add(checkbox1);
        reservationFrame.add(submit);
        reservationFrame.add(back);
        //  reservationFrame.setSize(200,200);
        reservationFrame.getContentPane().setLayout(new BoxLayout(reservationFrame.getContentPane(), BoxLayout.Y_AXIS));

        reservationFrame.pack();
        reservationFrame.setLocationRelativeTo(null);
        reservationFrame.setVisible(true);

        // Closing Windows
        CloseWindowListener(reservationFrame);
    }

    void PaymentFrame() throws ParseException {
        PaymentFrame.setMinimumSize(new Dimension(1200, 1000));
        PaymentFrame.setIconImage(favicon.getImage());
        // reservationFrame.dispose();
        Date dates = new Date();
        SpinnerDateModel date = new SpinnerDateModel(dates, null, null, Calendar.DATE);

        JLabel CardNum = new JLabel();
        CardNum.setText("Enter Debit/Credit car number");
        JTextField numberCC = new JTextField(16);
        numberCC.setMaximumSize(new Dimension(120,40));

        JLabel CardName = new JLabel("Enter name on card");
        JTextField name = new JTextField();
        name.setMaximumSize(new Dimension(120,40));

        JLabel Expiration = new JLabel("Enter Expiration date");
        JSpinner expireSpinner = new JSpinner(date);
        JSpinner.DateEditor de = new JSpinner.DateEditor(expireSpinner,"MM-yyyy");
        expireSpinner.setToolTipText("Highlight the number to change");
        expireSpinner.setEditor(de);
        expireSpinner.setMaximumSize(new Dimension(100,40));

        JLabel cVVJLabel = new JLabel("Enter the CVV");
        JPasswordField cVVField = new JPasswordField(3);
        cVVField.setMaximumSize(new Dimension(120,40));
        JButton completeButton = new JButton("Complete Payment");
        completeButton.setMaximumSize(new Dimension(150,40));
        String myCVV=String.valueOf(cVVField.getPassword());
        Object eDate = expireSpinner.getValue();
        Date eDate2 = (java.util.Date) eDate;


        completeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CreditCard userCreditCard = new CreditCard(numberCC.getText(), name.getText(), myCVV, eDate2);
                userCreditCard.printCard();
                JOptionPane.showMessageDialog(null, db.paymentCreditCard(userCreditCard));
                JOptionPane.showMessageDialog(null,"Payment has been processed, you will be redirected to the home page.");
                JOptionPane.showMessageDialog(null,"E-mail conformation has been sent to you with your reservation Id");
                mainFrame.getContentPane().removeAll();
                loginFrame.getContentPane().removeAll();
                reservationFrame.dispose();
                adminFrame.dispose();
                adminLoginFrame.dispose();
                loginFrame.dispose();
                CreateAccFrame.dispose();
                PaymentFrame.getContentPane().removeAll();
                PaymentFrame.dispose();

                HomePageFrame();
            }
        });

        PaymentFrame.add(CardNum);
        PaymentFrame.add(numberCC);
        PaymentFrame.add(CardName);
        PaymentFrame.add(name);
        PaymentFrame.add(Expiration);
        PaymentFrame.add(expireSpinner);
        PaymentFrame.add(cVVJLabel);
        PaymentFrame.add(cVVField);
        PaymentFrame.add(completeButton);

        PaymentFrame.getContentPane().setLayout(new BoxLayout(PaymentFrame.getContentPane(), BoxLayout.Y_AXIS));
        PaymentFrame.pack();
        PaymentFrame.setLocationRelativeTo(null);
        PaymentFrame.setVisible(true);

        //Closing Windows
        CloseWindowListener(PaymentFrame);
    }

    void cancelF(){

       // JFrame cancelF = new JFrame("Cancel reservation");
        int resIDtoCancel = 0;
        String cancelNum = JOptionPane.showInputDialog(cancelF, "Enter reservation ID to cancel");

        try {
            resIDtoCancel = Integer.parseInt(cancelNum);
        } catch (Exception ex) {
            System.out.println("Reservation ID was not provided for cancellation.");
        }

        for (Reservation r: db.getResList()) {
            if (resIDtoCancel == r.getReservationID()) {
                JOptionPane.showMessageDialog(null,"Reservation has been cancelled.");
                db.cancelReservation(resIDtoCancel);
            }
        }

        HomePageFrame();

    }
    void adminHomeFrame() {
        // UserFrame
        // Tasks: Review Rooms, Edit, Cancel, and Reserve.
        //
//        JFrame adminFrame = new JFrame("MAAD Hotel: Welcome.");
        adminFrame.setMinimumSize(new Dimension(1200, 1000));
        adminFrame.setIconImage(favicon.getImage());

        adminLoginFrame.dispose();
        JLabel selected_file = new JLabel();
        JPanel adminJPanel = new JPanel();
        adminJPanel.setSize(1500,500);
        JButton genReportButton = new JButton("generate Room report", favicon);
        JButton genAccReportButton = new JButton("generate Account report", favicon);
        JButton genReservReportButton = new JButton("generate Reservation report", favicon);

        //genReportButton.setMinimumSize(new Dimension(1000,10));
        //genReportButton.setHorizontalAlignment(JButton.CENTER);
        // genReportButton.setVerticalAlignment(JButton.TOP);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 1500, 900);
        JTable jTable1 = new JTable();
        JScrollPane sp = new JScrollPane(jTable1);

        panel.add(sp);
        adminFrame.add(selected_file);
        adminFrame.add(panel,BorderLayout.CENTER);
        adminJPanel.add(genReportButton);
        adminJPanel.add(genAccReportButton);
        adminJPanel.add(genReservReportButton);
        adminFrame.add(adminJPanel,BorderLayout.NORTH);

        genReportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                File inf = new File("Rooms.csv");
                // Rooms 8
                // Reservations 9
                // Accounts 4
                String fi = inf.getName();
                String filepath = inf.getPath();
                System.out.print(filepath);
                selected_file.setText(fi);
                DefaultTableModel csv_data = new DefaultTableModel();

                try {
                    int start = 0;
                    InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(filepath));
                    org.apache.commons.csv.CSVParser csvParser = CSVFormat.DEFAULT.parse(inputStreamReader);
                    for (CSVRecord csvRecord : csvParser) {
                        if (start == 0) {
                            start = 1;
                            csv_data.addColumn(csvRecord.get(0));
                            csv_data.addColumn(csvRecord.get(1));
                            csv_data.addColumn(csvRecord.get(2));
                            csv_data.addColumn(csvRecord.get(3));
                            csv_data.addColumn(csvRecord.get(4));
                            csv_data.addColumn(csvRecord.get(5));
                            csv_data.addColumn(csvRecord.get(6));
                        } else {
                            Vector row = new Vector();
                            row.add(csvRecord.get(0));
                            row.add(csvRecord.get(1));
                            row.add(csvRecord.get(2));
                            row.add(csvRecord.get(3));
                            row.add(csvRecord.get(4));
                            row.add(csvRecord.get(5));
                            row.add(csvRecord.get(6));

                            csv_data.addRow(row);
                        }
                    }
                } catch (Exception ex) {
                    System.out.println("Error in Parsing CSV File");
                }
                jTable1.setModel(csv_data);
            }
        });

        genAccReportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                File inf = new File("Accounts.csv");
                // Rooms 8
                // Reservations 9
                // Accounts 4
                String fi = inf.getName();
                String filepath = inf.getPath();
                System.out.print(filepath);
                selected_file.setText(fi);
                DefaultTableModel csv_data = new DefaultTableModel();

                try {
                    int start = 0;
                    InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(filepath));
                    org.apache.commons.csv.CSVParser csvParser = CSVFormat.DEFAULT.parse(inputStreamReader);
                    for (CSVRecord csvRecord : csvParser) {
                        if (start == 0) {
                            start = 1;
                            csv_data.addColumn(csvRecord.get(0));
                            csv_data.addColumn(csvRecord.get(1));
                            csv_data.addColumn(csvRecord.get(2));
                            csv_data.addColumn(csvRecord.get(3));
                        } else {
                            Vector row = new Vector();
                            row.add(csvRecord.get(0));
                            row.add(csvRecord.get(1));
                            row.add(csvRecord.get(2));
                            row.add(csvRecord.get(3));

                            csv_data.addRow(row);
                        }
                    }
                } catch (Exception ex) {
                    System.out.println("Error in Parsing CSV File");
                }
                jTable1.setModel(csv_data);
            }
        });

        genReservReportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                File inf = new File("Reservations.csv");
                // Rooms 8
                // Reservations 9
                // Accounts 4
                String fi = inf.getName();
                String filepath = inf.getPath();
                //System.out.println(filepath);
                selected_file.setText(fi);
                DefaultTableModel csv_data = new DefaultTableModel();

                try {
                    int start = 0;
                    InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(filepath));
                    org.apache.commons.csv.CSVParser csvParser = CSVFormat.DEFAULT.parse(inputStreamReader);
                    for (CSVRecord csvRecord : csvParser) {
                        if (start == 0) {
                            start = 1;
                            csv_data.addColumn(csvRecord.get(0));
                            csv_data.addColumn(csvRecord.get(1));
                            csv_data.addColumn(csvRecord.get(2));
                            csv_data.addColumn(csvRecord.get(3));
                            csv_data.addColumn(csvRecord.get(4));
                            csv_data.addColumn(csvRecord.get(5));
                            csv_data.addColumn(csvRecord.get(6));
                            csv_data.addColumn(csvRecord.get(7));
                            csv_data.addColumn(csvRecord.get(8));


                        } else {
                            Vector row = new Vector();
                            row.add(csvRecord.get(0));
                            row.add(csvRecord.get(1));
                            row.add(csvRecord.get(2));
                            row.add(csvRecord.get(3));
                            row.add(csvRecord.get(4));
                            row.add(csvRecord.get(5));
                            row.add(csvRecord.get(6));
                            row.add(csvRecord.get(7));
                            row.add(csvRecord.get(8));



                            csv_data.addRow(row);
                        }
                    }
                } catch (Exception ex) {
                    System.out.println("Error in Parsing CSV File");
                }
                jTable1.setModel(csv_data);
            }
        });

        // adminJPanel.add(genReportButton);

        // adminFrame.add(adminJPanel,BorderLayout.NORTH);
        adminFrame.pack();
        adminFrame.setLayout(null);
        adminFrame.setVisible(true);

        // Closing Windows
        CloseWindowListener(adminFrame);
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
                } catch (IOException | SQLException ex) {
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
                User = userName.getText();

                JOptionPane.showMessageDialog(null, "Account has been created");
                System.out.println(User + "'s Account has been created. Time: "+timeLog() );

                int dialogButton = JOptionPane.YES_NO_OPTION;
                JOptionPane.showConfirmDialog (null, "Would you like to sign in?","Confirmation", dialogButton);
                if(dialogButton == JOptionPane.YES_OPTION) {
                    HomePageFrame();
                    System.out.println(User+ "'s entered  home page. Time: "+timeLog() );

                    if(dialogButton == JOptionPane.NO_OPTION) {
                        JOptionPane.showMessageDialog(null,"Returning to main menu.");
                        mainFrame();
                    }
                }
                break;
            case "Make Reservation":
                reservationFrame();
                System.out.println("Entering user Make reservation. Time: "+timeLog() );
                break;

            case "Authenticate":
                // Need the account portion for this
                HomePageFrame();
                System.out.println("Logging in as User. Time: " +timeLog() );
                break;
            case "adminLogin":
                System.out.println("Entering Admin Login. Time: " +timeLog() );

                adminLoginFrame();
                break;
            case "Authenticate ":
                System.out.println("Logging in as Admin"  +timeLog() );
                adminHomeFrame();
                break;
            case "Cancel Reservation":
                cancelF();
                System.out.println("Entering user cancel reservation. Time: "+timeLog() );
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
