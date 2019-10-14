public class Main {
    public static void main(String[] args) {
        Range range = new Range(1, 5);

        System.out.printf("Длина интревала %.2f%n", range.getLength());

        if (range.isInside(3)) {
            System.out.println("Число принадлежит заданному интревалу");
        } else {
            System.out.println("Число не принадлежит заданному интревалу");
        }
    }
}
