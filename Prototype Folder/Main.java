/*
import javax.swing.plaf.nimbus.State;
import java.io.*;
import java.sql.ResultSet;
import java.text.ParseException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        DatabaseH db = new DatabaseH();

        db.displayAccounts();
        db.displayDB();
        db.displayReservations();

        db.writeToRoomFile();
        db.writeToReservationFile();
        db.writeToAccountFile();

        String sql;
        String sqlRooms = "rooms1";
        String sqlAccounts = "accounts1";
        String sqlReservations = "reservations1";

        //JDBC db = new JDBC();

        // Create SQL ROOMS
        /*sql = "CREATE TABLE IF NOT EXISTS `" + sqlRooms + "` " +
                "(\n" +
                "  `RoomNumber` int NOT NULL,\n" +
                "  `Vacant` tinyint DEFAULT NULL,\n" +
                "  `Occupancy` int DEFAULT NULL,\n" +
                "  `RoomPrice` double DEFAULT NULL,\n" +
                "  `RoomType` varchar(45) DEFAULT NULL,\n" +
                "  `Amenities` varchar(45) DEFAULT NULL,\n" +
                "  `AccountID` int DEFAULT NULL,\n" +
                "  PRIMARY KEY (`RoomNumber`)\n" +
                ");";*/

        // Create SQL Accounts
/*        sql = "CREATE TABLE IF NOT EXISTS `" + sqlAccounts + "` " +
                "(\n" +
                "  `AccountID` int NOT NULL,\n" +
                "  `UserName` varchar(45) DEFAULT NULL,\n" +
                "  `Password` varchar(45) DEFAULT NULL,\n" +
                "  `CustomerInfo` varchar(45) DEFAULT NULL,\n" +
                "  `Email` varchar(45) DEFAULT NULL,\n" +
                "  PRIMARY KEY (`AccountID`)\n" +
                ");";*/

        // Create SQL Reservation
/*        sql = "CREATE TABLE IF NOT EXISTS `" + sqlReservations + "` " +
                "(\n" +
                "  `ReservationID` int NOT NULL,\n" +
                "  `RoomNumber` int DEFAULT NULL,\n" +
                "  `NumberOfGuest` int DEFAULT NULL,\n" +
                "  `RoomPrice` double DEFAULT NULL,\n" +
                "  `StayLength` int DEFAULT NULL,\n" +
                "  `ReservationDate` date DEFAULT NULL,\n" +
                "  `ReservationMade` date DEFAULT NULL,\n" +
                "  `AccountID` int DEFAULT NULL,\n" +
                "  `Cancelled` tinyint DEFAULT NULL,\n" +
                "  PRIMARY KEY (`ReservationID`)\n" +
                ");";*/

        //db.execute(sql);
        //db.closeConnection();
    }
}

*/
