package karakalchev.vector.libraries;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть > 0");
        }

        components = new double[n];
    }

    public Vector(Vector vector) {
        components = Arrays.copyOf(vector.components, vector.getSize());
    }

    public Vector(double[] components) {
        if (components.length < 1) {
            throw new IllegalArgumentException("Размерность вектора должна быть > 0");
        }

        this.components = Arrays.copyOf(components, components.length);
    }

    public Vector(int n, double[] components) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть > 0");
        }

        this.components = Arrays.copyOf(components, n);
    }

    public int getSize() {
        return components.length;
    }

    public double getComponent(int index) {
        if (index < 0 || index >= getSize()) {
            throw new IndexOutOfBoundsException(String.format("Индекс компоненты должен быть >= 0 и < %d", getSize()));
        }

        return components[index];
    }

    public void setComponent(int index, double component) {
        if (index < 0 || index >= getSize()) {
            throw new IndexOutOfBoundsException(String.format("Индекс компоненты должен быть >= 0 и < %d", getSize()));
        }

        components[index] = component;
    }

    public void addAddition(Vector vector) {
        if (getSize() < vector.getSize()) {
            components = Arrays.copyOf(components, vector.getSize());
        }

        for (int i = 0; i < vector.getSize(); i++) {
            components[i] += vector.components[i];
        }
    }

    public void addDifference(Vector vector) {
        if (getSize() < vector.getSize()) {
            components = Arrays.copyOf(components, vector.getSize());
        }

        for (int i = 0; i < vector.getSize(); i++) {
            components[i] -= vector.components[i];
        }
    }

    public void addMultiplicationByScalar(double scalar) {
        for (int i = 0; i < getSize(); i++) {
            components[i] *= scalar;
        }
    }

    public void addReverse() {
        addMultiplicationByScalar(-1.0);
    }

    public double getLength() {
        double result = 0.0;

        for (double e : components) {
            result += e * e;
        }

        return Math.sqrt(result);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("{");

        for (double e : components) {
            result.append(String.format("%.2f; ", e));
        }

        result.delete(result.lastIndexOf("; "), result.length());
        result.append("}");

        return result.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Arrays.hashCode(components);
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

        if (getSize() != v.getSize()) {
            return false;
        }

        for (int i = 0; i < getSize(); i++) {
            if (components[i] != v.components[i]) {
                return false;
            }
        }

        return true;
    }

    public static Vector getAddition(Vector vector1, Vector vector2) {
        Vector result = new Vector(vector1);
        result.addAddition(vector2);
        return result;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        Vector result = new Vector(vector1);
        result.addDifference(vector2);
        return result;
    }

    public static double getScalarMultiplication(Vector vector1, Vector vector2) {
        double result = 0.0;
        int length = Math.min(vector1.getSize(), vector2.getSize());

        for (int i = 0; i < length; i++) {
            result += vector1.components[i] * vector2.components[i];
        }

        return result;
    }
}
