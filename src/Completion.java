package src;

public class Completion {
    private String date;
    private int duration;

    public Completion(String date, int duration) {
        this.date = date;
        this.duration = duration;
    }

    // getters - allow other classes to read the private attributes
    public String getDate() {
        return date;
    }

    public int getDuration() {
        return duration;
    }
}