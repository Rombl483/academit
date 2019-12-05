package karakalchev.matrix.libraries;

public class Matrix {
    private Vector[] vectors;
    private static final double epsilon = 0.1E-10;


    public Matrix(int n, int m) {
        if (n <= 0 || m <= 0) {
            throw new IllegalArgumentException("Размерность матрицы должна быть > 0.");
        }

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
            this.vectors[i].add(vectors[i]);
        }
    }

    public int getRowCount() {
        return vectors.length;
    }

    public int getColumnCount() {
        return vectors[0].getSize();
    }

    public void setVectorRow(int index, Vector vector) {
        if (index < 0 || index >= getRowCount()) {
            throw new IllegalArgumentException(String.format("Индекс вектора-строки матрицы должен быть >= 0 и < %d.", getRowCount()));
        }

        for (int i = 0; i < vector.getSize(); i++) {
            vectors[index].setComponent(i, vector.getComponent(i));
        }
    }

    public void setVectorColumn(int index, Vector vector) {
        if (index < 0 || index >= getColumnCount()) {
            throw new IllegalArgumentException(String.format("Индекс вектора-столбца матрицы должен быть >= 0 и < %d.", getColumnCount()));
        }


        for (int i = 0; i < vector.getSize(); i++) {
            vectors[i].setComponent(index, vector.getComponent(i));
        }
    }

    public Vector getVectorRow(int index) {
        if (index < 0 || index >= getRowCount()) {
            throw new IllegalArgumentException(String.format("Индекс вектора-строки матрицы должен быть >= 0 и < %d.", getRowCount()));
        }

        return vectors[index];
    }

    public Vector getVectorColumn(int index) {
        if (index < 0 || index >= getColumnCount()) {
            throw new IllegalArgumentException(String.format("Индекс вектора-столбца матрицы должен быть >= 0 и < %d.", getColumnCount()));
        }

        int length = vectors.length;
        double[] components = new double[length];

        for (int i = 0; i < length; i++) {
            components[i] = vectors[i].getComponent(index);
        }

        return new Vector(components);
    }

    public Matrix getTranspose() {
        Matrix transposeMatrix = new Matrix(getColumnCount(), getRowCount());

        for (int i = 0; i < getRowCount(); i++) {
            transposeMatrix.setVectorColumn(i, vectors[i]);
        }

        return transposeMatrix;
    }

    public void add(Matrix matrix) {
        if (getRowCount() != matrix.getRowCount() || getColumnCount() != matrix.getColumnCount()) {
            throw new IllegalArgumentException("Сложение матриц с разной размерностью не возможно.");
        }

        for (int i = 0; i < getRowCount(); i++) {
            vectors[i].add(matrix.vectors[i]);
        }
    }

    public void subtract(Matrix matrix) {
        if (getRowCount() != matrix.getRowCount() || getColumnCount() != matrix.getColumnCount()) {
            throw new IllegalArgumentException("Вычитание матриц с разной размерностью не возможно.");
        }

        for (int i = 0; i < getRowCount(); i++) {
            vectors[i].subtract(matrix.vectors[i]);
        }
    }

    public void multiplyByScalar(double scalar) {
        for (int i = 0; i < getRowCount(); i++) {
            vectors[i].multiplyByScalar(scalar);
        }
    }

    public Vector getMultiplicationByVector(Vector vector) {
        if (getColumnCount() != vector.getSize()) {
            throw new IllegalArgumentException("Умножение матрицы на вектор не возможно. Количество столбцов матрицы не равно размерности вектора.");
        }

        Vector result = new Vector(getRowCount());

        for (int i = 0; i < getRowCount(); i++) {
            result.setComponent(i, Vector.getScalarMultiplication(vectors[i], vector));
        }

        return result;
    }

    public static Matrix getAddition(Matrix matrix1, Matrix matrix2) {
        Matrix result = new Matrix(matrix1);
        result.add(matrix2);
        return result;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        Matrix result = new Matrix(matrix1);
        result.subtract(matrix2);
        return result;
    }

    public static Matrix getMultiplication(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnCount() != matrix2.getRowCount()) {
            throw new IllegalArgumentException("Умножение матриц не возможно. Количество столбцов первой матрицы не равно количеству строк второй матрицы.");
        }

        Matrix result = new Matrix(matrix1.getRowCount(), matrix2.getColumnCount());

        for (int i = 0; i < result.getColumnCount(); i++) {
            result.setVectorColumn(i, matrix1.getMultiplicationByVector(matrix2.getVectorColumn(i)));
        }

        return result;
    }

    public double getDeterminant() {
        if (getRowCount() != getColumnCount()) {
            throw new IllegalArgumentException("Детерминант матрицы можно вычислить только для квадратной матрицы.");
        }

        Matrix matrix = new Matrix(this);
        double matrixDeterminant = 1;
        int matrixLength = matrix.getRowCount();

        for (int i = 0; i < matrixLength; i++) {
            int maxElementMatrixColumnIndex = i;
            for (int j = i + 1; j < matrixLength; j++) {
                if (Math.abs(matrix.vectors[j].getComponent(i)) > Math.abs(matrix.vectors[maxElementMatrixColumnIndex].getComponent(i))) {
                    maxElementMatrixColumnIndex = j;
                }
            }

            if (Math.abs(matrix.vectors[maxElementMatrixColumnIndex].getComponent(i)) <= epsilon) {
                matrixDeterminant = 0.0;
                break;
            }

            if (i != maxElementMatrixColumnIndex) {
                matrixDeterminant = -matrixDeterminant;
                Vector tempMatrixRow = matrix.vectors[i];
                matrix.setVectorRow(i, matrix.vectors[maxElementMatrixColumnIndex]);
                matrix.setVectorRow(maxElementMatrixColumnIndex, tempMatrixRow);
            }

            matrixDeterminant *= matrix.vectors[i].getComponent(i);

            for (int j = i + 1; j < matrixLength; j++) {
                if (Math.abs(matrix.vectors[j].getComponent(i)) > epsilon) {
                    double rowMultiplier = matrix.vectors[j].getComponent(i) / matrix.vectors[i].getComponent(i);
                    matrix.vectors[i].multiplyByScalar(rowMultiplier);
                    matrix.vectors[j].subtract(matrix.vectors[i]);
                }
            }
        }

        return matrixDeterminant;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("{");

        for (Vector e : vectors) {
            result.append(e.toString().concat(", "));
        }

        result.delete(result.lastIndexOf(", "), result.length());
        result.append("}");

        return result.toString();
    }
}