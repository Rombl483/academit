package karakalchev.matrix.libraries;

import karakalchev.vector.libraries.Vector;

public class Matrix {
    private Vector[] rows;
    private static final double EPSILON = 0.1E-10;

    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount <= 0 || columnsCount <= 0) {
            throw new IllegalArgumentException("Размерность матрицы должна быть > 0.");
        }

        rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            rows[i] = new Vector(columnsCount);
        }
    }

    public Matrix(Matrix matrix) {
        rows = new Vector[matrix.rows.length];

        for (int i = 0; i < matrix.rows.length; i++) {
            rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public Matrix(double[][] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Массив не может быть пустым.");
        }

        for (int i = 1; i < array.length; i++) {
            if (array[0].length != array[i].length) {
                throw new IllegalArgumentException("Массив не может иметь разное количество столбцов.");
            }
        }

        rows = new Vector[array.length];

        for (int i = 0; i < array.length; i++) {
            rows[i] = new Vector(array[i]);
        }
    }

    public Matrix(Vector[] rows) {
        if (rows.length == 0) {
            throw new IllegalArgumentException("Массив векторов не может быть пустым.");
        }

        int maxColumnsCount = 1;

        for (Vector e : rows) {
            if (maxColumnsCount < e.getSize()) {
                maxColumnsCount = e.getSize();
            }
        }

        this.rows = new Vector[rows.length];

        for (int i = 0; i < rows.length; i++) {
            this.rows[i] = new Vector(maxColumnsCount);
            this.rows[i].add(rows[i]);
        }
    }

    public int getRowsCount() {
        return rows.length;
    }

    public int getColumnsCount() {
        return rows[0].getSize();
    }

    public void setRow(int index, Vector vector) {
        if (index < 0 || index >= getRowsCount()) {
            throw new IndexOutOfBoundsException(String.format("Индекс вектора-строки матрицы должен быть >= 0 и < %d.", getRowsCount()));
        }

        if (vector.getSize() != rows[index].getSize()) {
            throw new IllegalArgumentException("Размерность вектора-аргумента не совпадает с размерностью вектора-строки матрицы");
        }

        rows[index] = new Vector(vector);
    }

    public void setColumn(int index, Vector vector) {
        if (index < 0 || index >= getColumnsCount()) {
            throw new IndexOutOfBoundsException(String.format("Индекс вектора-столбца матрицы должен быть >= 0 и < %d.", getColumnsCount()));
        }

        if (vector.getSize() != rows.length) {
            throw new IllegalArgumentException("Размерность вектора-аргумента не совпадает с размерностью вектора-столбца матрицы");
        }

        for (int i = 0; i < vector.getSize(); i++) {
            rows[i].setComponent(index, vector.getComponent(i));
        }
    }

    public Vector getRow(int index) {
        if (index < 0 || index >= getRowsCount()) {
            throw new IndexOutOfBoundsException(String.format("Индекс вектора-строки матрицы должен быть >= 0 и < %d.", getRowsCount()));
        }

        return new Vector(rows[index]);
    }

    public Vector getColumn(int index) {
        if (index < 0 || index >= getColumnsCount()) {
            throw new IndexOutOfBoundsException(String.format("Индекс вектора-столбца матрицы должен быть >= 0 и < %d.", getColumnsCount()));
        }

        int length = rows.length;
        double[] components = new double[length];

        for (int i = 0; i < length; i++) {
            components[i] = rows[i].getComponent(index);
        }

        return new Vector(components);
    }

    public void transpose() {
        if (getColumnsCount() != getRowsCount()) {
            throw new IllegalArgumentException("Транспонирование матрицы векторов не возможно, т.к. количество векторов в матрице не совпдает с размерностью векторов матрицы.");
        }

        for (int i = 0; i < getRowsCount(); i++) {
            for (int j = i + 1; j < getColumnsCount(); j++) {
                double tempElement = rows[i].getComponent(j);
                rows[i].setComponent(j, rows[j].getComponent(i));
                rows[j].setComponent(i, tempElement);
            }
        }
    }

    public void add(Matrix matrix) {
        if (getRowsCount() != matrix.getRowsCount() || getColumnsCount() != matrix.getColumnsCount()) {
            throw new IllegalArgumentException("Сложение матриц с разной размерностью не возможно.");
        }

        for (int i = 0; i < getRowsCount(); i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        if (getRowsCount() != matrix.getRowsCount() || getColumnsCount() != matrix.getColumnsCount()) {
            throw new IllegalArgumentException("Вычитание матриц с разной размерностью не возможно.");
        }

        for (int i = 0; i < getRowsCount(); i++) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    public void multiplyByScalar(double scalar) {
        for (int i = 0; i < getRowsCount(); i++) {
            rows[i].multiplyByScalar(scalar);
        }
    }

    public Vector getMultiplicationByVector(Vector vector) {
        if (getColumnsCount() != vector.getSize()) {
            throw new IllegalArgumentException("Умножение матрицы на вектор не возможно. Количество столбцов матрицы не равно размерности вектора.");
        }

        Vector result = new Vector(getRowsCount());

        for (int i = 0; i < getRowsCount(); i++) {
            result.setComponent(i, Vector.getScalarMultiplication(rows[i], vector));
        }

        return result;
    }

    public static Matrix getAddition(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowsCount() != matrix2.getRowsCount() || matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("Сложение матриц с разной размерностью не возможно.");
        }

        Matrix result = new Matrix(matrix1);
        result.add(matrix2);
        return result;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowsCount() != matrix2.getRowsCount() || matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("Вычитание матриц с разной размерностью не возможно.");
        }

        Matrix result = new Matrix(matrix1);
        result.subtract(matrix2);
        return result;
    }

    public static Matrix getMultiplication(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsCount() != matrix2.getRowsCount()) {
            throw new IllegalArgumentException("Умножение матриц не возможно. Количество столбцов первой матрицы не равно количеству строк второй матрицы.");
        }

        Matrix result = new Matrix(matrix1.getRowsCount(), matrix2.getColumnsCount());

        for (int i = 0; i < result.getColumnsCount(); i++) {
            result.setColumn(i, matrix1.getMultiplicationByVector(matrix2.getColumn(i)));
        }

        return result;
    }

    public double getDeterminant() {
        if (getRowsCount() != getColumnsCount()) {
            throw new IllegalArgumentException("Детерминант матрицы можно вычислить только для квадратной матрицы.");
        }

        Matrix matrix = new Matrix(this);
        double matrixDeterminant = 1;
        int matrixLength = matrix.getRowsCount();

        for (int i = 0; i < matrixLength; i++) {
            int maxElementMatrixColumnIndex = i;
            for (int j = i + 1; j < matrixLength; j++) {
                if (Math.abs(matrix.rows[j].getComponent(i)) > Math.abs(matrix.rows[maxElementMatrixColumnIndex].getComponent(i))) {
                    maxElementMatrixColumnIndex = j;
                }
            }

            if (Math.abs(matrix.rows[maxElementMatrixColumnIndex].getComponent(i)) <= EPSILON) {
                matrixDeterminant = 0.0;
                break;
            }

            if (i != maxElementMatrixColumnIndex) {
                matrixDeterminant = -matrixDeterminant;
                Vector tempMatrixRow = matrix.rows[i];
                matrix.rows[i] = matrix.rows[maxElementMatrixColumnIndex];
                matrix.rows[maxElementMatrixColumnIndex] = tempMatrixRow;
            }

            matrixDeterminant *= matrix.rows[i].getComponent(i);

            for (int j = i + 1; j < matrixLength; j++) {
                if (Math.abs(matrix.rows[j].getComponent(i)) > EPSILON) {
                    double rowMultiplier = matrix.rows[j].getComponent(i) / matrix.rows[i].getComponent(i);
                    matrix.rows[i].multiplyByScalar(rowMultiplier);
                    matrix.rows[j].subtract(matrix.rows[i]);
                }
            }
        }

        return matrixDeterminant;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("{");

        for (Vector e : rows) {
            result.append(String.format("%s, ",e.toString()));
        }

        result.delete(result.lastIndexOf(", "), result.length());
        result.append("}");

        return result.toString();
    }
}