package karakalchev.matrix.libraries;

public class Matrix {
    private Vector[] vectors;

    public Matrix(int n, int m) {
        vectors = new Vector[n];

        for (int i = 0; i < n; i++) {
            vectors[i] = new Vector(m);
        }
    }

    public Matrix(Matrix matrix) {
        vectors = new Vector[matrix.vectors.length];

        for (int i = 0; i < matrix.vectors.length; i++) {
            vectors[i] = new Vector(matrix.vectors[i]);
        }
    }

    public Matrix(double[][] array) {
        vectors = new Vector[array.length];

        for (int i = 0; i < array.length; i++) {
            vectors[i] = new Vector(array[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        int maxColumnCount = 1;

        for (Vector e : vectors) {
            if (maxColumnCount < e.getSize()) {
                maxColumnCount = e.getSize();
            }
        }

        this.vectors = new Vector[vectors.length];

        for (int i = 0; i < vectors.length; i++) {
            this.vectors[i] = new Vector(maxColumnCount);
            this.vectors[i].addition(vectors[i]);
        }
    }

    public int getRowCount() {
        return vectors.length;
    }

    public int getColumnCount() {
        return vectors[0].getSize();
    }

    public void setVectorRow(int index, Vector vector) {
        for (int i = 0; i < vector.getSize(); i++) {
            vectors[index].setComponent(i, vector.getComponent(i));
        }
    }

    public Vector getVectorRow(int index) {
        return vectors[index];
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("{");

        for (Vector e : vectors) {
            result.append(e.toString() + ", ");
        }

        result.delete(result.lastIndexOf(", "), result.length());
        result.append("}");

        return result.toString();
    }
}