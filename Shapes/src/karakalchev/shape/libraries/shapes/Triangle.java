package karakalchev.shape.libraries.shapes;

import karakalchev.shape.libraries.Shape;

public class Triangle implements Shape {
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double x3;
    private double y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    private double getMax(double a, double b, double c) {
        return Math.max(a, Math.max(b, c));
    }

    private double getMin(double a, double b, double c) {
        return Math.min(a, Math.min(b, c));
    }

    private static double getSideLength(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    @Override
    public double getWidth() {
        return getMax(x1, x2, x3) - getMin(x1, x2, x3);
    }

    @Override
    public double getHeight() {
        return getMax(y1, y2, y3) - getMin(y1, y2, y3);
    }

    @Override
    public double getPerimeter() {
        return getSideLength(x1, y1, x2, y2) +
                getSideLength(x2, y2, x3, y3) +
                getSideLength(x1, y1, x3, y3);
    }

    @Override
    public double getArea() {
        double triangleHalfPerimeter = this.getPerimeter() / 2;

        return Math.sqrt(triangleHalfPerimeter *
                (triangleHalfPerimeter - getSideLength(x1, y1, x2, y2)) *
                (triangleHalfPerimeter - getSideLength(x2, y2, x3, y3)) *
                (triangleHalfPerimeter - getSideLength(x1, y1, x3, y3)));
    }

    @Override
    public String toString() {
        return String.format("Треугольник с координатами вершин: (%.2f; %.2f) (%.2f; %.2f) (%.2f; %.2f)", x1, y1, x2, y2, x3, y3);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        Triangle p = (Triangle) o;

        return x1 == p.x1 && y1 == p.y1 && x2 == p.x2 && y2 == p.y2 && x3 == p.x3 && y3 == p.y3;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        hash = prime * hash + Double.hashCode(x1);
        hash = prime * hash + Double.hashCode(y1);
        hash = prime * hash + Double.hashCode(x2);
        hash = prime * hash + Double.hashCode(y2);
        hash = prime * hash + Double.hashCode(x3);
        hash = prime * hash + Double.hashCode(y3);

        return hash;
    }
}
