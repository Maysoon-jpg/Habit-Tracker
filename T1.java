import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

//PLAN
//*
// Add habits
// View Habits
// Log completion dates
// View habit statistics
// *//
public class T1 {

    public static void main(String[] args){

        // Add habits
        String[] habits;
        int size;
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many habits would you like to add?");
        size = scanner.nextInt();
        habits = new String[size];
        scanner.nextLine();

        for(int i = 0; i< habits.length; i++){
            System.out.print("Enter a habit:");
            habits[i] = scanner.nextLine();
        }


       //Log completion dates
        System.out.println("Which habit would you like to add completion dates to out of: ");

        for(String habit : habits) {
            System.out.print(habit +", ");

        }





    }



}
