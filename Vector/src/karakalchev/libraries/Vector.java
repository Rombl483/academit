package karakalchev.libraries;

public class Vector {
    private double[] components;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть > 0");
        }

        components = new double[n];
    }

    public Vector(Vector vector) {
        this.components = new double[vector.components.length];
        System.arraycopy(vector.components, 0, this.components, 0, vector.components.length);
    }

    public Vector(double[] components) {
        if (components.length < 1) {
            throw new IllegalArgumentException("Размерность вектора должна быть > 0");
        }

        this.components = new double[components.length];
        System.arraycopy(components, 0, this.components, 0, components.length);
    }

    public Vector(int n, double[] components) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть > 0");
        }

        this.components = new double[n];
        int length = Math.min(n, components.length);
        System.arraycopy(components, 0, this.components, 0, length);
    }

    public int getSize() {
        return components.length;
    }

    public double getComponentByIndex(int i) {
        if (i < 0 || i >= getSize()) {
            throw new IndexOutOfBoundsException(String.format("Индекс компоненты должен быть >= 0 и < %d", getSize()));
        }

        return components[i];
    }

    public void setComponentByIndex(int i, double component) {
        if (i < 0 || i >= getSize()) {
            throw new IndexOutOfBoundsException(String.format("Индекс компоненты должен быть >= 0 и < %d", getSize()));
        }

        components[i] = component;
    }

    public void addition(Vector vector) {
        int n = Math.min(getSize(),vector.getSize());

        for (int i = 0; i < n; i++) {
            this.components[i] += vector.components[i];
        }
    }

    public void difference(Vector vector) {
        int n = Math.min(getSize(), vector.getSize());

        for (int i = 0; i < n; i++) {
            this.components[i] -= vector.components[i];
        }
    }

    public void multiplicationByScalar(double scalar) {
        for (int i = 0; i < getSize(); i++) {
            components[i] *= scalar;
        }
    }

    public void reverse() {
        for (int i = 0; i < getSize(); i++) {
            components[i] *= -1.0;
        }
    }

    public double length() {
        double result = 0.0;

        for (double e : components) {
            result += e * e;
        }

        return Math.pow(result, 0.5);
    }

    @Override
    public String toString() {
        String result = "{";

        for (double e : components) {
            result = result.concat(String.format("%.2f; ", e));
        }

        result += "}";

        return result.replace("; }", "}");
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        for (double e : components) {
            hash = prime * hash + Double.hashCode(e);
        }

        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        Vector v = (Vector) o;

        if (this.getSize() == v.getSize()) {
            for (int i = 0; i < this.getSize(); i++) {
                if (this.components[i] != v.components[i]) {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    public static Vector vectorsAddition(Vector vector1, Vector vector2) {
        Vector vectorResult;

        if (vector1.getSize() >= vector2.getSize()) {
            vectorResult = new Vector(vector1);
            vectorResult.addition(vector2);
        } else {
            vectorResult = new Vector(vector2);
            vectorResult.addition(vector1);
        }

        return vectorResult;
    }

    public static Vector vectorsDifference(Vector vector1, Vector vector2) {
        Vector vectorResult;

        if (vector1.getSize() >= vector2.getSize()) {
            vectorResult = new Vector(vector1);
            vectorResult.difference(vector2);
        } else {
            vectorResult = new Vector(vector2);
            vectorResult.difference(vector1);
        }

        return vectorResult;
    }

    public static Vector vectorsMultiplication(Vector vector1, Vector vector2) {
        int n = Math.max(vector1.getSize(), vector2.getSize());
        double[] vectorComponents = new double[n];

        int i = 0;

        while (i < vector1.getSize() && i< vector2.getSize()) {
            vectorComponents[i] = vector1.components[i] * vector2.components[i];
            i++;
        }

        return new Vector(vectorComponents);
    }
}
