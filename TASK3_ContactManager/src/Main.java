import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Contact implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String email;
    private String phoneNumber;

    public Contact(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }


}

public class Main extends Thread {
    private static final ArrayList<Contact> contacts = new ArrayList<>();
    private static final String FILE_NAME = "contacts.txt";

    public static void main(String[] args) throws InterruptedException {
        loadContacts();

        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("###############################################");
            System.out.print("Program is Starting");
            Thread.sleep(1000);
            System.out.print(".");
            Thread.sleep(1000);
            System.out.print(".");
            Thread.sleep(1000);
            System.out.println(".");
            Thread.sleep(1000);
            System.out.println("###############################################");

        } catch (Exception ignored) {}

        while (true) {
            Thread.sleep(1000);
            System.out.println("###############################################");
            System.out.println("          Welcome to contact Manager           ");
            System.out.println("###############################################");
            System.out.println("             1. Add a new contact              ");
            System.out.println("             2. View all contacts              ");
            System.out.println("             3. Edit a contact                 ");
            System.out.println("             4. Delete a contact               ");
            System.out.println("             5. Exit                           ");
            System.out.println("###############################################");

            System.out.println("===============================================");
            System.out.print("Enter a Choice : ");
            int choice = scanner.nextInt();
            System.out.println("===============================================");

            switch (choice) {
                case 1 -> addContact();
                case 2 -> viewContacts();
                case 3 -> editContact();
                case 4 -> deleteContact();
                case 5 -> {
                    System.out.println("===============================================");
                    System.out.print("Exiting");
                    Thread.sleep(500);
                    System.out.print(".");
                    Thread.sleep(500);
                    System.out.print(".");
                    Thread.sleep(500);
                    System.out.println(".");
                    Thread.sleep(500);
                    System.out.println("===============================================");
                    System.out.println("###############################################");
                    System.out.println("Thank you for using Contact Manager. Bye Alien!");
                    System.out.println("###############################################");
                    saveContacts();
                    System.exit(0);
                }
                default -> {
                    System.out.println("===============================================");
                    System.out.println("Invalid choice. Please try again.");
                    System.out.println("===============================================");
                }
            }
        }
    }

    private static void addContact() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("===============================================");
        System.out.print("Enter Name : ");
        String name = scanner.nextLine();
        System.out.print("Enter Email : ");
        String email = scanner.nextLine();
        System.out.print("Enter Phone Number : ");
        String phoneNumber = scanner.nextLine();
        Contact newContact = new Contact(name, email, phoneNumber);
        contacts.add(newContact);
        Thread.sleep(500);
        System.out.println("-----------------------------------------------");
        System.out.println("Contact added successfully.");
        System.out.println("-----------------------------------------------");
        System.out.println("===============================================");
        saveContacts();
    }

    private static void viewContacts() {
        System.out.println("===============================================");
        System.out.println("                  All Contacts                 ");
        System.out.println("===============================================");
        System.out.println("-----------------------------------------------");
        int i = 1;
        for (Contact contact : contacts) {
            System.out.println("Found Contacts");
            System.out.println("Contact " + i++);
            System.out.println("-------------");
            System.out.println("Name : " + contact.getName());
            System.out.println("Email: " + contact.getEmail());
            System.out.println("Phone: " + contact.getPhoneNumber());
            System.out.println("-----------------------------------------------");
        }
        System.out.println("===============================================");
    }

    private static void editContact() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("===============================================");
        System.out.print("Enter Name of the contact to edit : ");
        String name = scanner.nextLine();
        boolean found = false;
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                found = true;
                System.out.println("-----------------------------------------------");
                System.out.println("Current Details : ");
                System.out.println("Name : " + contact.getName());
                System.out.println("Email: " + contact.getEmail());
                System.out.println("Phone: " + contact.getPhoneNumber());
                System.out.println("-----------------------------------------------");
                System.out.println("===============================================");
                System.out.println("Enter new details:");
                System.out.print("Enter Name : ");
                contact.setName(scanner.nextLine());
                System.out.print("Enter Email : ");
                contact.setEmail(scanner.nextLine());
                System.out.print("Enter Phone Number : ");
                contact.setPhoneNumber(scanner.nextLine());
                System.out.println(" ");
                Thread.sleep(500);
                System.out.println("-----------------------------------------------");
                System.out.println("Contact updated successfully.");
                System.out.println("===============================================");
                saveContacts();
                break;
            }
        }
        if (!found) {
            System.out.println("Contact not found. Please try again.");
        }
    }

    private static void deleteContact() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("===============================================");
        System.out.print("Enter Name of the contact to delete : ");
        String name = scanner.nextLine();
        boolean found = false;
        for (int i = 0; i < contacts.size(); i++) {
            Contact contact = contacts.get(i);
            if (contact.getName().equalsIgnoreCase(name)) {
                found = true;
                contacts.remove(i);
                Thread.sleep(500);
                System.out.println("-----------------------------------------------");
                System.out.println("Contact deleted successfully.");
                System.out.println("===============================================");
                saveContacts();
                break;
            }
        }
        if (!found) {
            System.out.println("Contact not found. Please try again.");
        }
    }

    private static void saveContacts() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(contacts);
        } catch (IOException e) {
            System.out.println("Error saving contacts: " + e.getMessage());
        }
    }

    private static void loadContacts() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            contacts.clear();
            contacts.addAll((ArrayList<Contact>) ois.readObject());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No previous contacts found or error loading contacts: " + e.getMessage());
        }
    }
}