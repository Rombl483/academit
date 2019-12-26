package karakalchev.matrix.program;

import karakalchev.matrix.libraries.Matrix;
import karakalchev.vector.libraries.Vector;

public class Main {
    private static void printMatrixInformation(Matrix matrix, String message) {
        System.out.println(message);
        System.out.println(matrix);
        System.out.printf("Размер матрицы = %d X %d%n", matrix.getRowsCount(), matrix.getColumnsCount());
        System.out.println();
    }

    public static void main(String[] args) {
        try {
            System.out.println("----------------------------------------------------------------------");
            System.out.println("----------------------| Задача \"Матрица\" |----------------------------");
            System.out.println("----------------------------------------------------------------------");

            double[][] array1 = new double[][]{
                    {1, 2, 3, 1},
                    {4, 5, 6, 2},
                    {7, 8, 9, 3}
            };

            double[][] array2 = new double[][]{
                    {9, 8, 7, 9},
                    {6, 5, 4, 8},
                    {3, 2, 1, 7}
            };

            double[][] array3 = new double[][]{
                    {1, -1},
                    {2, 0},
                    {3, 5}
            };

            double[][] array4 = new double[][]{
                    {1, 1},
                    {2, 1}
            };

            double[][] array5 = {
                    {5, 3, 6, 7, 8},
                    {-1, 8, 9, 4, 5},
                    {1, 4, 2, 1, 3},
                    {3, 7, 9, 0, 4},
                    {1, 4, 8, 7, 5}
            };

            double[][] array6 = {
                    {1, 2, 3},
                    {-1, 5, 8},
                    {7, 8, 9}
            };

            Vector[] vectors = new Vector[]{
                    new Vector(1),
                    new Vector(4),
                    new Vector(3)
            };

            Matrix matrix1 = new Matrix(3, 3);
            Matrix matrix2 = new Matrix(array1);
            Matrix matrix3 = new Matrix(matrix1);
            Matrix matrix4 = new Matrix(vectors);
            Matrix matrix5 = new Matrix(array2);
            Matrix matrix6 = new Matrix(array3);
            Matrix matrix7 = new Matrix(array4);
            Matrix matrix8 = new Matrix(array6);

            printMatrixInformation(matrix1, "matrix1:");
            printMatrixInformation(matrix2, "matrix2:");
            printMatrixInformation(matrix3, "matrix3:");
            printMatrixInformation(matrix4, "matrix4:");
            printMatrixInformation(matrix5, "matrix5:");
            printMatrixInformation(matrix6, "matrix6:");
            printMatrixInformation(matrix7, "matrix7:");
            printMatrixInformation(matrix8, "matrix8:");

            System.out.println("matrix5.add(matrix2):");
            matrix5.add(matrix2);
            System.out.println(matrix5);
            System.out.println();

            System.out.println("matrix5.subtract(matrix2):");
            matrix5.subtract(matrix2);
            System.out.println(matrix5);
            System.out.println();

            System.out.println("matrix5.multiplyByScalar(2):");
            matrix5.multiplyByScalar(2);
            System.out.println(matrix5);
            System.out.println();

            matrix2.setColumn(1, matrix1.getColumn(1));
            matrix2.setRow(1, matrix4.getRow(1));
            printMatrixInformation(matrix2, "matrix2:");

            System.out.println("matrix6 X matrix7:");
            System.out.println(matrix6);
            System.out.println(" X ");
            System.out.println(matrix7);
            System.out.println(" = ");
            System.out.println(Matrix.getMultiplication(matrix6, matrix7));
            System.out.println();

            System.out.println("matrix6.transpose():");
            matrix6.transpose();
            System.out.println(matrix6);
            System.out.println();

            System.out.printf("matrix8.getDeterminant() = %.3f%n", matrix8.getDeterminant());
        } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }
}
