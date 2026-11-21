
package src;

public class Habit {
       
            //habits attributes
             private String name;    
             private String description; 
             //ArrayList<String> completionDates;

          
        
           public Habit(String name, String description) {
                 this.name = name;
                 this.description = description;
                 //this.completionDates = new ArrayList<String>();
             }

           // getters - allow other classes to read the private attributes
           public String getName() {
                 return name;
             }

           public String getDescription() {
                 return description;
             }


}

