package com.com380;
import java.io.*;
import java.text.ParseException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {

        DatabaseH db = new DatabaseH();

        Customer first = new Customer("Sulejman Adrian", 39, "238 Union Court Miami, FL 33125", "jdgesq@symplysliphair.com");
        Customer second = new Customer("Tintin Jareth", 55, "23 Hamilton Street Kaukauna, WI 54130", "nitzrr@chantellegribbon.com");
        Customer third = new Customer("Jareth Morticia", 23, "84 East Piper Dr Hoffman Estates, IL 60169", "256281@mailtrail.xyz");

        /*
        Account one = new Account(10, "two", first.customerToString());
        Account two = new Account(11, "two", second.customerToString());
        Account three = new Account(12, "two", third.customerToString());
         */
        db.makeAccount(10, "two", first);
        db.makeAccount(11, "two", second);
        db.makeAccount(12, "two", third);

        //db.makeReservation(10, 109, 4);
        db.cancelReservation(626);
        db.writeToAccountFile();
        db.writeToReservationFile();
        db.writeToRoomFile();

        db.displayAccounts();


/*
        Database db = new Database();
        // sample accounts
        Account yohannes = new Account(1, "one", "Yohannes");
        Account mat = new Account(2, "one", "Mat");
        Account ruth = new Account(3, "one", "Ruth");
        Account petros = new Account(4, "one", "Petros");
        Account henok = new Account(5, "one", "Henok");

        db.addAccount(yohannes);
        db.addAccount(mat);
        db.addAccount(ruth);
        db.addAccount(petros);
        db.addAccount(henok);
*/
        //DatabaseH db = new DatabaseH();
       /* Account katie = new Account(6, "one",
                "Kathryn 29 5315 Harmony Ave North Hollywood" +
                        " CA 91601 kathryn@gmail.com");*/
        /*
        Customer cus = new Customer("Kathryn", 29,
                "5315 Harmony Ave North Hollywood CA 91601",
                "kathryn@gmail.com");
        */
        //db.makeAccount(6, "two", cus);
        //db.writeToRoomFile();
        //db.writeToAccountFile();
        //db.addAccountToRoom(106, 6);
        //db.removeAccount(6);
        //db.displayAccounts();
        //db.removeAccountFromRoom(104, 2);
        //db.printDB();
        //db.displayReservations();
        //db.displayDB();
        //db.cancelReservation(999);
        //db.displayDB();
        //db.writeToReservationFile();
       // db.displayReservations();
        //db.writeToReservationFile();
        //db.writeToRoomFile();

        // Overload email confirmations
        //db.sendEmailConfirmation(1);
        //db.sendEmailConfirmationResID(282);
//        db.displayDB();
//        db.changeRoom(103, 110);
//        db.displayDB();
//        db.displayReservations();


    }
}
