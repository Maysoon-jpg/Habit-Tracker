package src;

import java.util.ArrayList; // needed so Java knows what ArrayList is

public class HabitManager {

    private ArrayList<Habit> habits;

    public HabitManager() {
        habits = new ArrayList<>();
    }

    public void addHabit(Habit habit) {
        habits.add(habit);
    }

    public void removeHabit(int index) {
        habits.remove(index);
    }

    public ArrayList<Habit> getHabits() {
        return new ArrayList<>(habits);
    }

}