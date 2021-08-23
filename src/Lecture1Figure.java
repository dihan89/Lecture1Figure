import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.StringBuilder;
import java.nio.file.Files;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

abstract class Figure {
    private String name;
    public String getName(){
        return name;
    } 
    abstract public double getPerimeter();
    abstract public double getSquare();
    protected Figure(String name) {
        this.name = name;
    }
}

abstract class Polygon extends Figure {
    private int nAngles;
    public int getNAngles() {
        return nAngles;
    }
    public int getSumAngles() {
        return (nAngles - 2) * 180;
    }
    protected Polygon(String name, int nAngles) {
        super(name);
        this.nAngles = nAngles;
    }

}

class Rectangle extends Polygon {
    private double side1, side2;

    @Override
    public double getPerimeter() {
        return 2 * (side1 + side2);
    }
    @Override
    public double getSquare() {
        return side1 * side2;
    }
    public Rectangle (double side1, double side2) {
        super("Rectangle", 4);
        this.side1 = side1;
        this.side2 = side2;
    }
    protected Rectangle(String name, double side1, double side2) {
        super(name, 4);
        this.side1 = side1;
        this.side2 = side2;
    }
}

class Square extends Rectangle  {
    Square(double side) {
        super("Square", side, side);
    }
}

class Triangle extends Polygon {
    private double side1, side2, side3;

    public double getPerimeter() {
        return side1 + side2 + side3;
    }

    public double getSquare() {
        double semiPerimetr = (side1 + side2 + side3)/2;
        return Math.sqrt(semiPerimetr *  
                        (semiPerimetr - side1) * 
                        (semiPerimetr - side2) * 
                        (semiPerimetr - side3));
    }

    Triangle(double side1, double side2, double side3) throws  InputMismatchException {
        super("Triangle", 3);
        if (side1 >= side2 + side3 || side2 >= side1 + side3 || side3 >= side1 + side2) {
            throw new InputMismatchException("Incorrect sides values! Triangle inequality violated.") ;
        }
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }
}

class Circle extends Figure {

    private double radius;

    public double getSquare() {
        return Math.PI * radius * radius;
    }
    public double getPerimeter() {
        return 2 * Math.PI * radius; 
    }
    Circle(double radius) {
        super("Circle");
        this.radius = radius;
    }
}

public class Lecture1Figure {


    public static void  main(String[] args) {
    
        ArrayList<Figure> figures = new ArrayList<>(){{
            add(new Rectangle(4.0, 8.0));
            add(new Square(3d));
            add(new Circle(10));
            add(new Triangle(5d, 3d, 4d));
            add(new Rectangle(5.0, 6.0));
        }};

        for(Figure figure : figures) {
            System.out.print(String.format("%s: P= %.2f;  S =%.2f ", figure.getName(), figure.getPerimeter() ,figure.getSquare()));
            if (figure instanceof Polygon) {
                System.out.println("   sumAngles: "+ ((Polygon)figure).getSumAngles());
            } else  System.out.println();
        }
    }
}