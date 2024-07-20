<h1>Contact Manager</h1>

# Project Folder Structure

TASK3_ContactManager/<br>
├── out/<br>
│ └── production/<br>
│ └── TASK3_ContactManager/<br>
│ ├── Contact.class<br>
│ └── Main.class<br>
├── src/<br>
│ └── Main.java             // Main file should present code <br>
├── TASK3_ContactManager.iml<br>
├── contacts.txt<br>
└── README.md<br>
 

    
This Java program is a console-based contact manager that allows users to add, view, edit, and delete contacts. The contacts are stored in a file so they can be persisted between program runs. Let's go through the main components of the program:

### Contact Class
The `Contact` class implements `Serializable` so that its instances can be serialized and deserialized. This class has three private fields:
- `name` (String)
- `email` (String)
- `phoneNumber` (String)

It also includes:
- A constructor to initialize these fields.
- Getter and setter methods for each field.
- A `toString` method for easy string representation of a `Contact` object.

### Main Class
The `Main` class extends `Thread` (though it does not actually utilize threading features) and contains the main logic for managing contacts. It includes:
- A static `ArrayList<Contact>` named `contacts` to hold the contact objects.
- A static final `String FILE_NAME` to specify the file where contacts are stored.

### Main Method
The `main` method initializes the program by loading any existing contacts from the file and then presents a menu to the user. It repeatedly displays options until the user chooses to exit. Each choice leads to a different operation:

1. **Add a new contact**: Calls the `addContact()` method.
2. **View all contacts**: Calls the `viewContacts()` method.
3. **Edit a contact**: Calls the `editContact()` method.
4. **Delete a contact**: Calls the `deleteContact()` method.
5. **Exit**: Saves the contacts to the file and exits the program.

### Helper Methods
1. **addContact()**
   - Prompts the user to enter the name, email, and phone number for a new contact.
   - Creates a new `Contact` object and adds it to the `contacts` list.
   - Saves the updated list of contacts to the file.

2. **viewContacts()**
   - Displays all the contacts in the `contacts` list.

3. **editContact()**
   - Prompts the user to enter the name of the contact they wish to edit.
   - Searches for the contact by name.
   - If found, it displays the current details and prompts the user to enter new details.
   - Updates the contact and saves the changes.

4. **deleteContact()**
   - Prompts the user to enter the name of the contact they wish to delete.
   - Searches for the contact by name.
   - If found, it removes the contact from the list and saves the changes.

5. **saveContacts()**
   - Serializes the `contacts` list and writes it to the specified file.

6. **loadContacts()**
   - Reads the file containing serialized contact objects and deserializes them into the `contacts` list.

### User Interaction
The program uses a `Scanner` object to read user input from the console. It also includes delays (`Thread.sleep()`) to create a simple animation effect for starting and exiting the program, enhancing the user experience.

### Error Handling
The program includes basic error handling for file operations and invalid input. If the contacts file does not exist or cannot be read, it catches the exceptions and continues without loading any contacts.

Overall, the program provides a straightforward way to manage a list of contacts through a text-based interface, using serialization to maintain data between sessions.
