package karakalchev.program;

import karakalchev.libraries.Shape;
import karakalchev.libraries.Square;
import karakalchev.libraries.Triangle;
import karakalchev.libraries.Rectangle;
import karakalchev.libraries.Circle;


public class Main {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[4];

        shapes[0] = new Square(10);
        shapes[1] = new Triangle(1,2,4,7,7,2);
        shapes[2] = new Rectangle(10,5);
        shapes[3] = new Circle(5);

        System.out.printf("Площадь квадрата = %.2f%n", shapes[0].getArea());
        System.out.printf("Площадь треугольника = %.2f%n", shapes[1].getArea());
        System.out.printf("Площадь прямоугольника = %.2f%n", shapes[2].getArea());
        System.out.printf("Площадь круга = %.2f%n", shapes[3].getArea());
        System.out.printf("Площадь круга = %s%n", shapes[3].toString());
        System.out.printf("Double.hashCode(7.0) = %d%n",Double.hashCode(7.0));
        System.out.printf("Double.hashCode(7.5) = %d%n",Double.hashCode(7.5));
    }
}
