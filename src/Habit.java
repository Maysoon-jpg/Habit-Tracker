package src;

import java.util.ArrayList; // needed so Java knows what ArrayList is

public class Habit {
       
            //habits attributes
             private String name;    
             private String description; 
             private ArrayList<String> completionDates; //composition - a habit has a list of completion dates
             //composition is when one object contains or owns other objects as part of its structure
             //every habit object now has a list of completion dates

          
        
           public Habit(String name, String description) {
                 this.name = name;
                 this.description = description;
                 this.completionDates = new ArrayList<>();
             }

           // getters - allow other classes to read the private attributes (encapsulation)
           public String getName() {
                 return name;
             }

           public String getDescription() {
                 return description;
             }

           public ArrayList<String> getCompletionDates() {
                 return new ArrayList<>(completionDates);
             }

          public void addCompletionDate(String date) {
                 completionDates.add(date);
             }

}

