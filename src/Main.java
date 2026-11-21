package src;

//importing libraries
import java.io.BufferedReader; //Reads text from a file, one line at a time
import java.io.FileReader; //Opens an existing file for reading
import java.io.FileWriter; //Creates or writes to a file
import java.io.IOException; // Handles errors that could occur during file operations
import java.time.LocalDate; // Used to check that a completion date is a real date
import java.time.format.DateTimeParseException; // Error thrown when a date is not in YYYY-MM-DD format
import java.util.ArrayList; // A list that can grow or shrink
import java.util.Scanner; // Receives user input from the console

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

// Main class
public class Main {

    // One shared scanner for the whole program. Creating a new Scanner(System.in) in every method
    // is a bug: each scanner buffers input ahead, so typed/pasted input can get "lost" between them.
    static Scanner scanner = new Scanner(System.in);

    // Reads a whole number from the user. If they type something that isn't a number (e.g. "abc"),
    // it asks again instead of crashing with an InputMismatchException.
    static int readInt() {
        while (true) {
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a whole number:");
            }
        }
    }
// Java starts executing from here when the program is ran
    public static void main(String[] args) { 
      
        while (true) {
            displayMenu();
        }
        //repeatedly display the menu until the user chooses to exit using while loop using displayMenu() method
        //without this while loop, the program would only display the menu once and then exit after the user makes a selection
    }

    static void displayMenu() { 

        System.out.println("Pick an option (type the number): \n(1) Add habits \n(2) View habits \n(3) Delete all habits \n(4) Log completion dates \n(5) View completion dates\n(6) View habit statistics\n(7) Exit");
        int option = readInt(); //reads the user's choice (safely - won't crash on letters)

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
        
        String[] habits; //declares an array called habits that can store multiple strings
        int size; // declaring integer variable called size that will be used to store the number of habits the user wants to add

        System.out.println("How many habits would you like to add?");
        size = readInt(); // size of the array
        if (size < 1) { // a negative size would crash with NegativeArraySizeException
            System.out.println("You need to add at least 1 habit.");
            return;
        }
        habits = new String[size]; //creates a new array of strings with the specified size to store the habits

        for (int i = 0; i < habits.length; i++) { //for loop,  repeats for the number of times the user inputted, allowing the user to add multiple habits in one go
            System.out.print("Enter a habit:");
            habits[i] = scanner.nextLine(); // stores the user's input in the habits array at the current index i
            System.out.println("What is a description of your habit?");
            String description = scanner.nextLine();
            Habit habit = new Habit(habits[i], description); //creates a new habit object using the habit name entered by the user, this is done to create an instance of the Habit class for each habit added, allowing for future expansion of the Habit class to include additional attributes or methods related to habits
            System.out.println("Habit added: " + habit.getName() + " - " + habit.getDescription()); //prints the name and description of the habit that was just added to the console, confirming to the user that their input was successfully recorded
            // Write habits to a file
            try { //try block contains codgire that might produce an error or exception
                FileWriter writer = new FileWriter("data/habits.txt", true); //creates filewriter object
                writer.write(habit.getName() + " - " + habit.getDescription() + "\n"); //this writes current habit into the file
                writer.close(); //closes the filewriter object to free up system resources and ensure that all data is written to the file
            } catch (IOException e) { //This handles errors that occur during the file-writing operation
                System.out.println("An error occurred while writing to the file.");
                e.printStackTrace(); //prints info about the error and where it occured
            }
        }
    }
    // Viewing habits
    static void viewHabits() {
        String filepath = "data/habits.txt"; //file I want to access

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            String line;
            System.out.println("Your habits:");
            boolean any = false;
            while ((line = reader.readLine()) != null) {
                System.out.println("- " + line);
                any = true;
            }
            if (!any) {
                System.out.println("(none yet)");
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("No habits found yet.");
        }

    }

    static void removeHabits() {
      
           try {
        FileWriter writer = new FileWriter("data/habits.txt", false);
        writer.close();

        System.out.println("All habits deleted.");

    } catch (IOException e) {
        System.out.println("Error deleting habits.");
    }
    }

//method to log completion dates for habits, allowing the user to select a habit and enter multiple completion dates for that habit, which are then saved to a file called "completion_dates.txt"
   static void logCompletionDates() {

    ArrayList<String> habits = loadHabits(); //loads the habits from the "habits.txt" file.

    if (habits.isEmpty()) { //so that the user knows they need to add habits before logging completion dates, if the habits list is empty, it means there are no habits to log completion dates for
        System.out.println("You haven't added any habits yet.");
        return;
    }

    System.out.println("Which habit would you like to log?");

    for (int i = 0; i < habits.size(); i++) { //for loop iterating through every habit in ArrayList.
        System.out.println("(" +(i + 1) + ") " + habits.get(i));
    }

    int choice = readInt();
    //checks if the users number inputted is valid, as it has to be between 1 and the number of habits in the list.
    if (choice < 1 || choice > habits.size()) {
        System.out.println("Invalid habit selection.");
        return;
    } 
    //habitToLog variable is assigned the habit selected by the user from the habits ArrayList, using their choice (adjusted for zero-based indexing).
    //Initially had the user input the habit they wanted to log as a string, but this was changed to a selection from a list of habits to avoid errors and make it easier for the user.
    String habitToLog = habits.get(choice - 1); // subtract 1 from their selection as ArrayList starts from 0 instead of 1.

    System.out.println("How many completion dates would you like to log?");
    int numDates = readInt(); //number of completion dates inputted by user.
    if (numDates < 1) {
        System.out.println("Nothing to log.");
        return;
    }

    for (int i = 0; i < numDates; i++) { //repeats for the number of completion dates the user wants to log, allowing them to enter multiple completion dates for the selected habit.

        System.out.println("Enter completion date (YYYY-MM-DD):");
        String date = scanner.nextLine().trim(); //reads the completion date inputted by the user in the format YYYY-MM-DD.
        try {
            LocalDate.parse(date); // throws an error if the date isn't valid, e.g. "hello" or "2026-13-45"
        } catch (DateTimeParseException e) {
            System.out.println("That isn't a valid date in YYYY-MM-DD format. Try again.");
            i--; // repeat this loop step so the user still gets to enter the number of dates they asked for
            continue;
        }
        Completion completionDate = new Completion(date, 0); //creates a new Completion object with the inputted date and a default duration, this is done to create an instance of the Completion class for each completion date logged, allowing for future expansion of the Completion class to include additional attributes or methods related to completion dates.
        try { //try block contains code that might produce an error or exception, in this case, writing to the "completion_dates.txt" file.
            FileWriter writer = new FileWriter("data/completion_dates.txt", true); //set to true as want to appent information to the file instead of overwriting it.
            writer.write(habitToLog + ": " + completionDate.getDate() + " (Duration: " + completionDate.getDuration() + ")\n");
            writer.close();

        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }
    }

    System.out.println("Completion dates successfully recorded.");
}

//method to view completion dates for habits, reading from the "completion_dates.txt" file and displaying the logged completion dates to the user
    static void viewCompletionDates() {
        String filepath = "data/completion_dates.txt"; //file I want to access

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
    /*Array list may be a better option compared to an array as it is easier to
    add and delete elements due to .add() and .remove() methods and it can grow dynamically over time */

    try {
        BufferedReader reader = new BufferedReader(new FileReader("data/habits.txt"));
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
