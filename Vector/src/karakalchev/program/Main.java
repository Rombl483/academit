package karakalchev.program;

import karakalchev.libraries.Vector;

public class Main {
    private static void printVectorInformation(Vector vector) {
        System.out.println(vector);
        System.out.println("getSize = " + vector.getSize());
        System.out.printf("Длина вектора = %.3f%n", vector.length());
    }

    public static void main(String[] args){
        try {
            Vector vector1 = new Vector(1);

            System.out.println("vector1: ");
            printVectorInformation(vector1);
            System.out.println();

            double[] v = {1, 2, 3, 4, 5};

            Vector vector2 = new Vector(3, v);
            vector2.addition(vector1);

            System.out.println("vector2: ");
            printVectorInformation(vector2);
            System.out.println();

            Vector vector3 = new Vector(vector1);

            System.out.println("vector3: ");
            printVectorInformation(vector3);
            System.out.println();

            Vector vector4 = new Vector(v);
            vector4.difference(vector2);
            vector4.reverse();

            System.out.println("vector4: ");
            printVectorInformation(vector4);
            System.out.println();

            Vector vector5 = Vector.vectorsAddition(vector2,vector4);

            System.out.println("vector5: ");
            printVectorInformation(vector5);
            System.out.println();

            Vector vector6 = Vector.vectorsDifference(vector2,vector4);

            System.out.println("vector6: ");
            printVectorInformation(vector6);
            System.out.println();

            Vector vector7 = Vector.vectorsMultiplication(vector2,vector5);

            System.out.println("vector7: ");
            printVectorInformation(vector7);
            System.out.println();
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка инициализации вектора: " + e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Ошибка обращения к компоненте вектора: " + e.getMessage());
        }
    }
}
