//importing libraries
import java.io.BufferedReader; //Reads text from a file, one line at a time
import java.io.FileReader; //Opens an existing file for reading
import java.io.FileWriter; //Creates or writes to a file
import java.io.IOException; // Handles errors that could occur during file operations
import java.util.ArrayList; // Recieves user input from the console
import java.util.Scanner;

/* 

 PLAN
 Add habits
 Delete all habits
 Remove habits
 View habits
 Log completion dates
 View completion dates
 View habit statistics

*/ 

// Main class called T1 
public class T1 {
// Java starts executing from here when the program is ran
    public static void main(String[] args) { 
        /* Public: java can access this method from outside the class, 
        Static: java can run this method without creating an instance of the class, 
        Void: this method does not return any value, 
        Main: the name of the method, 
        String[] args: an array of strings that can be passed as command line arguments */
     
        while (true) {
            displayMenu();
        }
        //repeatedly display the menu until the user chooses to exit using while loop using displayMenu() method
        //without this while loop, the program would only display the menu once and then exit after the user makes a selection
    }

    static void displayMenu() { 
        
        /* Method called displayMenu()
        static: can be called without creating an instance of the class
        void: it doesn't return any value
        displayMenu: the name of the method */

        Scanner scanner = new Scanner(System.in); //creates a scanner object
        System.out.println("Pick an option (type the number): \n(1) Add habits \n(2) View habits \n(3) Delete all habits \n(4) Log completion dates \n(5) View completion dates\n(6) View habit statistics\n(7) Exit");
        int option = scanner.nextInt(); //reads the user's choice
        scanner.nextLine(); // consumes the newline character left in the input buffer after reading the integer

        // Check the user's choice and call the corresponding method
        if (option == 1) {
            System.out.println("You selected option 1: Add habits");
            addHabits();
        } else if (option == 2) {
            System.out.println("You selected option 2: View habits");
            viewHabits();
        } else if (option == 3) {
            System.out.println("You selected option 3: Delete all habits");
            removeHabits();
        } else if (option == 4) {
            // viewHabitStatistics();
            System.out.println("You selected option 4: Log completion dates");
            logCompletionDates();
        } else if (option == 5) {
            System.out.println("You selected option 5: View completion dates");
            viewCompletionDates();
        } else if (option == 6) {
            System.out.println("You selected option 6: View habit statistics");
            viewHabitStatistics();
        } else if (option == 7) {
            System.out.println("Exiting...");
            System.exit(0);

        } else {
            System.out.println("Invalid option. Please try again.");
        }

      
    }
    // Add habits - this method allows the user to add habits to a text file called "habits.txt"
    static void addHabits() {
        
        Scanner scanner = new Scanner(System.in); //creates a scanner object, creating a new scanner here because its separate from displayMenu() method and needs its own scanner object to read user input
        String[] habits; //declares an array called habits that can store multiple strings
        int size; // declaring integer variable called size that will be used to store the number of habits the user wants to add

        System.out.println("How many habits would you like to add?");
        size = scanner.nextInt(); // size of the array
        habits = new String[size]; //creates a new array of strings with the specified size to store the habits
        scanner.nextLine(); // consumes the newline character left in the input buffer after reading the integer

        for (int i = 0; i < habits.length; i++) { //for loop,  repeats for the number of times the user inputted, allowing the user to add multiple habits in one go
            System.out.print("Enter a habit:");
            habits[i] = scanner.nextLine(); // stores the user's input in the habits array at the current index i

            // Write habits to a file
            try { //try block contains code that might produce an error or exception
                FileWriter writer = new FileWriter("habits.txt", true); //creates filewriter object
                /*
                FileWriter: The class responsible for writing text to files
                writer: name of the object
                habits.txt: the file to which the habits will be written
                true: adds new content to the file instead of overwriting */
                writer.write(habits[i] + "\n"); //this writes current habit into the file
                writer.close(); //closes the filewriter object to free up system resources and ensure that all data is written to the file
            } catch (IOException e) { //This handles errors that occur during the file-writing operation
                System.out.println("An error occurred while writing to the file.");
                e.printStackTrace(); //prints info about the error and where it occured
            }
        }
    }
    // Viewing habits
    static void viewHabits() {
        String filepath = "habits.txt"; //file I want to access

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            String line;
            System.out.println("Your habits:");
            while ((line = reader.readLine()) != null) {
                System.out.println("- " + line);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }

    }

    static void removeHabits() {
      
           try {
        FileWriter writer = new FileWriter("habits.txt", false);
        writer.close();

        System.out.println("All habits deleted.");

    } catch (IOException e) {
        System.out.println("Error deleting habits.");
    }
    }

//method to log completion dates for habits, allowing the user to select a habit and enter multiple completion dates for that habit, which are then saved to a file called "completion_dates.txt"
   static void logCompletionDates() {

    ArrayList<String> habits = loadHabits();

    if (habits.isEmpty()) {
        System.out.println("You haven't added any habits yet.");
        return;
    }

    System.out.println("Which habit would you like to log?");

    for (int i = 0; i < habits.size(); i++) {
        System.out.println((i + 1) + ". " + habits.get(i));
    }

    Scanner scanner = new Scanner(System.in);

    int choice = scanner.nextInt();
    scanner.nextLine();

    if (choice < 1 || choice > habits.size()) {
        System.out.println("Invalid habit selection.");
        return;
    }

    String habitToLog = habits.get(choice - 1);

    System.out.println("How many completion dates would you like to log?");
    int numDates = scanner.nextInt();
    scanner.nextLine();

    for (int i = 0; i < numDates; i++) {

        System.out.println("Enter completion date (YYYY-MM-DD):");
        String date = scanner.nextLine();

        try {
            FileWriter writer = new FileWriter("completion_dates.txt", true);
            writer.write(habitToLog + ": " + date + "\n");
            writer.close();

        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }
    }

    System.out.println("Completion dates successfully recorded.");
}

//method to view completion dates for habits, reading from the "completion_dates.txt" file and displaying the logged completion dates to the user
    static void viewCompletionDates() {
        String filepath = "completion_dates.txt"; //file I want to access

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            String line;
            System.out.println("Your completion dates:");
            while ((line = reader.readLine()) != null) {
                System.out.println("- " + line);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }

    // loading habits from the file into an ArrayList for further processing or analysis

    static ArrayList<String> loadHabits() { //ArrayList<String> is similar to an array, but its size can change dynamically.
    ArrayList<String> habits = new ArrayList<>();

    try {
        BufferedReader reader = new BufferedReader(new FileReader("habits.txt"));
        String line;

        while ((line = reader.readLine()) != null) {
            habits.add(line); //adds an element to the arraylist
        }

        reader.close();

    } catch (IOException e) {
        System.out.println("Error reading habits.");
    }

    return habits;
}


    static void viewHabitStatistics() {
        System.out.println("Viewing habit statistics is not yet implemented.");
    }

}
