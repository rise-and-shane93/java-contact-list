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
            addToTreeMap(fileName, contactItems, true, input);
            System.out.println();
        }
        // BufferedReader fileReaderStart = new BufferedReader(new FileReader(inputFile));

        System.out.print("What would you like to do? Press 1 to add a contact, 2 to delete a contact,\n3 to display the list or 4 to exit the program:\n");
        int userResponse = input.nextInt();

        // while loop to prevent further execution if the user input was not valid
        while (userResponse < 1 || userResponse > 4 ) {
            System.out.print("Invalid response. Press 1 to add a contact, 2 to delete a contact,\n3 to display the list or 4 to exit the program:\n");
            userResponse = input.nextInt();
        }

        while (userResponse != 4) {
            if (userResponse == 1) {
                addToTreeMap(fileName, contactItems, false, input);
            } else if (userResponse == 2) {
                System.out.println("Delete a contact.");
            } else if (userResponse == 3) {
                readFromContactList2(fileName);
            }
            System.out.print("What would you like to do? Press 1 to add a contact, 2 to delete a contact,\n3 to display the list or 4 to exit the program:\n");
            userResponse = input.nextInt();
        }

        // ContactObject shaneHarper = new ContactObject("Shane", "Harper", "4789556045", "shane.harper@jhu.edu");
        // ContactObject jamalSimms = new ContactObject("Jamal", "Simms", "4105168775", "jamal.simms@jhu.edu");
        // ContactObject bobbyGe = new ContactObject("Bobby", "Ge", "4108675309", "bobby.ge@jhu.edu");

        // String headerFormat = "%-15s %-15s %-19s %-18s %n";
        
        // contactItems.put(bobbyGe.getLastName(), bobbyGe);
        // contactItems.put(jamalSimms.getLastName(), jamalSimms);
        // contactItems.put(shaneHarper.getLastName(), shaneHarper);

        // System.out.println();

        // System.out.printf(headerFormat, "Last Name", "First Name", "Phone Number", "Email Address");
        // System.out.printf(headerFormat, "----------", "---------", "------------", "-------------");

        // for(Map.Entry contactItem : contactItems.entrySet()) {
        //     ContactObject item = contactItems.get(contactItem.getKey());
        //     System.out.printf(headerFormat, contactItem.getKey(), item.getFirstName(), item.getPhoneNumber(), item.getEmailAddress());
        // }

        // System.out.println();

    }

    public static void addToTreeMap(String fileName, TreeMap<String, ContactObject> contactItems, boolean isStartOfProgram, Scanner scanner) {
        try {

            if (isStartOfProgram) {
                BufferedReader input = new BufferedReader(new FileReader(fileName));

                // set the record variable to null to prevent an endless loop
                String record = null;
    
                String headerFormat = "%-15s %-15s %-19s %-18s %n";
    
                // while loop to output each line of the text file
                while( (record = input.readLine() ) != null) {
                    String lastName = record.substring(0,15);
                    String firstName = record.substring(16,30);
                    String phoneNumber = record.substring(31,49);
                    String emailAddress = record.substring(50,72);
                    // System.out.println(record);
                    
                    ContactObject newContact = new ContactObject(firstName.trim(), lastName.trim(), phoneNumber.trim(), emailAddress.trim());
    
                    contactItems.put(newContact.getLastName(), newContact);
                }
                input.close();
            } else {
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

                String headerFormat = "%-15s %-15s %-19s %-18s %n";

                PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
                String bodyFormat = "%-15s %-15s %-19s %-18s %n";

                // output.printf(bodyFormat, lastName, firstName, phoneNumber, emailAddress);

                for(Map.Entry contactItem : contactItems.entrySet()) {
                    ContactObject item = contactItems.get(contactItem.getKey());
                    output.printf(headerFormat, contactItem.getKey(), item.getFirstName(), item.getPhoneNumber(), item.getEmailAddress());
                }

                output.close();

                System.out.println("Added!");
            }
            

            System.out.println();

            // ContactObject shaneHarper = new ContactObject("Shane", "Harper", "4789556045", "shane.harper@jhu.edu");
            // ContactObject jamalSimms = new ContactObject("Jamal", "Simms", "4105168775", "jamal.simms@jhu.edu");
            // ContactObject bobbyGe = new ContactObject("Bobby", "Ge", "4108675309", "bobby.ge@jhu.edu");

            // String headerFormat = "%-15s %-15s %-19s %-18s %n";
            
            // contactItems.put(bobbyGe.getLastName(), bobbyGe);
            // contactItems.put(jamalSimms.getLastName(), jamalSimms);
            // contactItems.put(shaneHarper.getLastName(), shaneHarper);
        } catch (IOException e) {
            System.out.println("I/O Exception: " + e.getMessage());
        }
    }

    public static void readFromContactList2(String fileName) {
        try {
            BufferedReader input = new BufferedReader(new FileReader(fileName));

            // set the record variable to null to prevent an endless loop
            String record = null;

            String headerFormat = "%-16s %-14s %-19s %-18s %n";
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
}