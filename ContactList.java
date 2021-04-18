import java.io.*;
import java.util.*;

public class ContactList {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Welcome to your contact list. Please enter the contact list file name (including .txt): ");
        String fileName = input.next();

        File inputFile = new File(fileName);
        TreeMap<String, ContactObject> contactItems = new TreeMap<String, ContactObject>();

        if (!inputFile.exists()) {
            System.out.println("File does not exist. Exiting program.");
            System.exit(1);
        } else {
            System.out.println("File found");
            System.out.println();
        }

        System.out.print("What would you like to do? Press 1 to add a contact, 2 to delete a contact,\n3 to display the list or 4 to exit the program: ");
        int userResponse = input.nextInt();

        // while loop to prevent further execution if the user input was not valid
        while (userResponse < 1 || userResponse > 4 ) {
            System.out.print("Invalid response. Press 1 to add a contact, 2 to delete a contact,\n3 to display the list or 4 to exit the program: ");
            userResponse = input.nextInt();
        }

        System.out.println();

        while (userResponse != 4) {
            if (userResponse == 1) {
                addToTreeMap(fileName, contactItems, input);
            } else if (userResponse == 2) {
                deleteFromTreeMap(fileName, contactItems, input);
            } else if (userResponse == 3) {
                readFromContactList(fileName);
            }
            System.out.print("What would you like to do? Press 1 to add a contact, 2 to delete a contact,\n3 to display the list or 4 to exit the program: ");
            userResponse = input.nextInt();
            System.out.println();
        }

        System.out.println("Exited program");

    }

    public static void addToTreeMap(String fileName, TreeMap<String, ContactObject> contactItems, Scanner scanner) {
        try {

            System.out.print("Please enter the contact's last name: ");
            String lastName = scanner.next();

            System.out.print("Please enter the contact's first name: ");
            String firstName = scanner.next();

            System.out.print("Please enter the contact's phone number: ");
            String phoneNumber = scanner.next();

            System.out.print("Please enter the contact's email address: ");
            String emailAddress = scanner.next();

            ContactObject newContact = new ContactObject(firstName, lastName, phoneNumber, emailAddress);

            contactItems.put(newContact.getLastName(), newContact);

            PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
            String bodyFormat = "%-15s %-15s %-19s %-18s %n";

            for(Map.Entry contactItem : contactItems.entrySet()) {
                ContactObject item = contactItems.get(contactItem.getKey());
                output.printf(bodyFormat, contactItem.getKey(), item.getFirstName(), item.getPhoneNumber(), item.getEmailAddress());
            }

            output.close();

            System.out.println("Added!");
        
            System.out.println();
        } catch (IOException e) {
            System.out.println("I/O Exception: " + e.getMessage());
        }
    }

    public static void readFromContactList(String fileName) {
        try {
            BufferedReader input = new BufferedReader(new FileReader(fileName));

            // set the record variable to null to prevent an endless loop
            String record = null;

            String headerFormat = "%-15s %-15s %-19s %-30s %n";
            System.out.printf(headerFormat, "Last Name", "First Name", "Phone Number", "Email Address");
            System.out.printf(headerFormat, "---------", "----------", "------------", "-------------");

            // while loop to output each line of the text file
            while( (record = input.readLine() ) != null) {
                System.out.println(record);
            }
            input.close();

            System.out.println();
        } catch (IOException e) {
            System.out.println("I/O Exception: " + e.getMessage());
        }
    }

    public static void deleteFromTreeMap(String fileName, TreeMap<String, ContactObject> contactItems, Scanner scanner) {
        try {
            System.out.print("Enter the last name of the person you want to delete from the list: ");
            String nameToDelete = scanner.next();

            while((contactItems.get(nameToDelete) == null)) {
                System.out.print("Name not found. Please try again: ");
                nameToDelete = scanner.next();
            }

            contactItems.remove(nameToDelete);

            PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
            String bodyFormat = "%-15s %-15s %-19s %-18s %n";

            // output.printf(bodyFormat, lastName, firstName, phoneNumber, emailAddress);

            for(Map.Entry contactItem : contactItems.entrySet()) {
                ContactObject item = contactItems.get(contactItem.getKey());
                output.printf(bodyFormat, contactItem.getKey(), item.getFirstName(), item.getPhoneNumber(), item.getEmailAddress());
            }

            output.close();

            System.out.println("Deleted!");
        } catch (IOException e) {
            System.out.println("I/O Exception: " + e.getMessage());
        }
    }
}