package karakalchev.matrix.libraries;

import karakalchev.vector.libraries.Vector;

public class Matrix {
    private Vector[] arrayOfVectors;
    private static final double EPSILON = 0.1E-10;

    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount <= 0 || columnsCount <= 0) {
            throw new IllegalArgumentException("Размерность матрицы должна быть > 0.");
        }

        arrayOfVectors = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            arrayOfVectors[i] = new Vector(columnsCount);
        }
    }

    public Matrix(Matrix matrix) {
        arrayOfVectors = new Vector[matrix.arrayOfVectors.length];

        for (int i = 0; i < matrix.arrayOfVectors.length; i++) {
            arrayOfVectors[i] = new Vector(matrix.arrayOfVectors[i]);
        }
    }

    public Matrix(double[][] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Массив не может быть пустым.");
        }

        arrayOfVectors = new Vector[array.length];

        for (int i = 0; i < array.length; i++) {
            arrayOfVectors[i] = new Vector(array[i]);
        }
    }

    public Matrix(Vector[] arrayOfVectors) {
        if (arrayOfVectors.length == 0) {
            throw new IllegalArgumentException("Массив векторов не может быть пустым.");
        }

        int maxColumnsCount = 1;

        for (Vector e : arrayOfVectors) {
            if (maxColumnsCount < e.getSize()) {
                maxColumnsCount = e.getSize();
            }
        }

        this.arrayOfVectors = new Vector[arrayOfVectors.length];

        for (int i = 0; i < arrayOfVectors.length; i++) {
            this.arrayOfVectors[i] = new Vector(maxColumnsCount);
            this.arrayOfVectors[i].add(arrayOfVectors[i]);
        }
    }

    public int getRowsCount() {
        return arrayOfVectors.length;
    }

    public int getColumnsCount() {
        return arrayOfVectors[0].getSize();
    }

    public void setRow(int index, Vector vector) {
        if (index < 0 || index >= getRowsCount()) {
            throw new IndexOutOfBoundsException(String.format("Индекс вектора-строки матрицы должен быть >= 0 и < %d.", getRowsCount()));
        }

        if (vector.getSize() != arrayOfVectors[index].getSize()) {
            throw new IllegalArgumentException("Размерность вектора-аргумента не совпадает с размерностью вектора-строки матрицы");
        }

        arrayOfVectors[index] = new Vector(vector);
    }

    public void setColumn(int index, Vector vector) {
        if (index < 0 || index >= getColumnsCount()) {
            throw new IndexOutOfBoundsException(String.format("Индекс вектора-столбца матрицы должен быть >= 0 и < %d.", getColumnsCount()));
        }

        if (vector.getSize() != arrayOfVectors.length) {
            throw new IllegalArgumentException("Размерность вектора-аргумента не совпадает с размерностью вектора-столбца матрицы");
        }

        for (int i = 0; i < vector.getSize(); i++) {
            arrayOfVectors[i].setComponent(index, vector.getComponent(i));
        }
    }

    public Vector getRow(int index) {
        if (index < 0 || index >= getRowsCount()) {
            throw new IndexOutOfBoundsException(String.format("Индекс вектора-строки матрицы должен быть >= 0 и < %d.", getRowsCount()));
        }

        return new Vector(arrayOfVectors[index]);
    }

    public Vector getColumn(int index) {
        if (index < 0 || index >= getColumnsCount()) {
            throw new IndexOutOfBoundsException(String.format("Индекс вектора-столбца матрицы должен быть >= 0 и < %d.", getColumnsCount()));
        }

        int length = arrayOfVectors.length;
        double[] components = new double[length];

        for (int i = 0; i < length; i++) {
            components[i] = arrayOfVectors[i].getComponent(index);
        }

        return new Vector(components);
    }

    public void transpose() {
        Matrix transposeMatrix = new Matrix(getColumnsCount(), getRowsCount());

        for (int i = 0; i < getRowsCount(); i++) {
            transposeMatrix.setColumn(i, arrayOfVectors[i]);
        }

        for (int i = 0; i < getRowsCount(); i++) {
            setRow(i, transposeMatrix.getRow(i));
        }
    }

    public void add(Matrix matrix) {
        if (getRowsCount() != matrix.getRowsCount() || getColumnsCount() != matrix.getColumnsCount()) {
            throw new IllegalArgumentException("Сложение матриц с разной размерностью не возможно.");
        }

        for (int i = 0; i < getRowsCount(); i++) {
            arrayOfVectors[i].add(matrix.arrayOfVectors[i]);
        }
    }

    public void subtract(Matrix matrix) {
        if (getRowsCount() != matrix.getRowsCount() || getColumnsCount() != matrix.getColumnsCount()) {
            throw new IllegalArgumentException("Вычитание матриц с разной размерностью не возможно.");
        }

        for (int i = 0; i < getRowsCount(); i++) {
            arrayOfVectors[i].subtract(matrix.arrayOfVectors[i]);
        }
    }

    public void multiplyByScalar(double scalar) {
        for (int i = 0; i < getRowsCount(); i++) {
            arrayOfVectors[i].multiplyByScalar(scalar);
        }
    }

    public Vector getMultiplicationByVector(Vector vector) {
        if (getColumnsCount() != vector.getSize()) {
            throw new IllegalArgumentException("Умножение матрицы на вектор не возможно. Количество столбцов матрицы не равно размерности вектора.");
        }

        Vector result = new Vector(getRowsCount());

        for (int i = 0; i < getRowsCount(); i++) {
            result.setComponent(i, Vector.getScalarMultiplication(arrayOfVectors[i], vector));
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
                if (Math.abs(matrix.arrayOfVectors[j].getComponent(i)) > Math.abs(matrix.arrayOfVectors[maxElementMatrixColumnIndex].getComponent(i))) {
                    maxElementMatrixColumnIndex = j;
                }
            }

            if (Math.abs(matrix.arrayOfVectors[maxElementMatrixColumnIndex].getComponent(i)) <= EPSILON) {
                matrixDeterminant = 0.0;
                break;
            }

            if (i != maxElementMatrixColumnIndex) {
                matrixDeterminant = -matrixDeterminant;
                Vector tempMatrixRow = matrix.arrayOfVectors[i];
                matrix.setRow(i, matrix.arrayOfVectors[maxElementMatrixColumnIndex]);
                matrix.setRow(maxElementMatrixColumnIndex, tempMatrixRow);
            }

            matrixDeterminant *= matrix.arrayOfVectors[i].getComponent(i);

            for (int j = i + 1; j < matrixLength; j++) {
                if (Math.abs(matrix.arrayOfVectors[j].getComponent(i)) > EPSILON) {
                    double rowMultiplier = matrix.arrayOfVectors[j].getComponent(i) / matrix.arrayOfVectors[i].getComponent(i);
                    matrix.arrayOfVectors[i].multiplyByScalar(rowMultiplier);
                    matrix.arrayOfVectors[j].subtract(matrix.arrayOfVectors[i]);
                }
            }
        }

        return matrixDeterminant;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("{");

        for (Vector e : arrayOfVectors) {
            result.append(e.toString() + ", ");
        }

        result.delete(result.lastIndexOf(", "), result.length());
        result.append("}");

        return result.toString();
    }
}