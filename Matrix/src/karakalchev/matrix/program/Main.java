package karakalchev.matrix.program;

import karakalchev.matrix.libraries.Matrix;
import karakalchev.matrix.libraries.Vector;

public class Main {
    public static void main(String[] args) {
        System.out.println("----------------------------------------------------------------------");
        System.out.println("----------------------| Задача \"Матрица\" |----------------------------");
        System.out.println("----------------------------------------------------------------------");

        Matrix matrix1 = new Matrix(2,3);
        System.out.println(matrix1);
        System.out.printf("Размер матрицы = %d X %d%n", matrix1.getRowCount(), matrix1.getColumnCount());
        System.out.println();

        double[][] array = new double[][]{
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        Matrix matrix2 = new Matrix(array);
        System.out.println(matrix2);
        System.out.printf("Размер матрицы = %d X %d%n", matrix2.getRowCount(), matrix2.getColumnCount());
        System.out.println();

        Matrix matrix3 = new Matrix(matrix1);
        System.out.println(matrix3);
        System.out.printf("Размер матрицы = %d X %d%n", matrix3.getRowCount(), matrix3.getColumnCount());
        System.out.println();

        Vector[] vectors = new Vector[]{
                new Vector(1),
                new Vector(4),
                new Vector(3)
        };

        Matrix matrix4 = new Matrix(vectors);
        System.out.println(matrix4);
        System.out.printf("Размер матрицы = %d X %d%n", matrix4.getRowCount(), matrix4.getColumnCount());
        System.out.println();
    }
}
