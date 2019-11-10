package karakalchev.libraries;

public class Rectangle implements Shape{
    private double heightLength;
    private double widthLength;

    public Rectangle(double heightLength, double widthLength) {
        this.heightLength = heightLength;
        this.widthLength = widthLength;
    }

    @Override
    public double getWidth() {
        return widthLength;
    }

    @Override
    public double getHeight() {
        return heightLength;
    }

    @Override
    public double getArea() {
        return heightLength * widthLength;
    }

    @Override
    public double getPerimeter() {
        return 2 * (heightLength + widthLength);
    }

    @Override
    public String toString() {
        return String.format("Прямоугольник: длина X ширина = %.2f X %.2f", heightLength, widthLength);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        Rectangle p = (Rectangle) o;

        return widthLength == p.widthLength && heightLength == p.heightLength;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        hash = prime * hash + Double.hashCode(widthLength);
        hash = prime * hash + Double.hashCode(heightLength);

        return hash;
    }

}

