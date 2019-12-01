package karakalchev.shape.program;

import java.util.Comparator;

import karakalchev.shape.libraries.Shape;

public class ShapePerimeterComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape shape1, Shape shape2) {
        return Double.compare(shape2.getPerimeter(),shape1.getPerimeter());
    }
}
