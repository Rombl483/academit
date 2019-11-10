package karakalchev.libraries;

public class Circle implements Shape{
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double getWidth() {
        return 2 * radius;
    }

    @Override
    public double getHeight() {
        return 2 * radius;
    }

    @Override
    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString() {
        return String.format("Окружность: радиус = %.2f", radius);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        Circle p = (Circle) o;

        return radius == p.radius;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        hash = prime * hash + Double.hashCode(radius);

        return hash;
    }
}
