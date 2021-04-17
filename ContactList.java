import java.io.*;
import java.util.*;

public class ContactList {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Welcome to your contact list. Please enter the contact list file name (including .txt): ");
        String fileName = input.next();

        File inputFile = new File(fileName);

        if (!inputFile.exists()) {
            System.out.println("File does not exist. Exiting program.");
            System.exit(1);
        } else {
            System.out.println("File found");
            System.out.println();
        }

        readFromContactList(fileName);

    }

    public static void readFromContactList(String fileName) {
        try {
            BufferedReader input = new BufferedReader(new FileReader(fileName));

            // set the record variable to null to prevent an endless loop
            String record = null;

            String headerFormat = "%-15s %-15s %-19s %-18s %n";
            System.out.printf(headerFormat, "First Name", "Last Name", "Phone Number", "Email Address");
            System.out.printf(headerFormat, "----------", "---------", "------------", "-------------");

            // while loop to output each line of the text file
            while( (record = input.readLine() ) != null) {
                System.out.println(record);
            }
            input.close();
        } catch (IOException e) {
            System.out.println("I/O Exception: " + e.getMessage());
        }
    }
}