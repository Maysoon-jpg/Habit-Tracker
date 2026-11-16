package src;

public class Rectangle {
    int length;
    int width;

    public Rectangle(int length, int width) {
        this.length = length;
        this.width = width;
    }

    public void area() {
        int area = length * width;
        System.out.println("Area of rectangle: " + area);
    }
}
