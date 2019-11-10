package karakalchev.libraries;

import java.util.Comparator;

public class ShapePerimeterComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape shape1, Shape shape2) {
        return (-1) * Double.compare(shape1.getPerimeter(),shape2.getPerimeter());
    }
}
