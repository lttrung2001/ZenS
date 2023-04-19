import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        scanner.close();
        String array[] = string.split(" ");
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            int number = Integer.parseInt(array[i]);
            if (number > max) {
                max = number;
            }
            if (number < min) {
                min = number;
            }
            sum += number;
        }
        System.out.println(String.format("%d %d", sum - max, sum - min));
    }
}
