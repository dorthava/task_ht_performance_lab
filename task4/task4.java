import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class task4 {
    public static int findCount(String filename) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        try (FileReader fileReader = new FileReader(filename);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            while (bufferedReader.ready()) {
                try {
                    Integer number = Integer.valueOf(bufferedReader.readLine());
                    arrayList.add(number);
                } catch (NumberFormatException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return getResult(arrayList);
    }

    private static int getResult(ArrayList<Integer> arrayList) {
        if (arrayList.isEmpty()) {
            return 0;
        }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i != arrayList.size(); ++i) {
            Integer number = arrayList.get(i);
            max = Math.max(max, number);
            min = Math.min(min, number);
        }
        int middle = (min + max) / 2;

        int result = 0;
        for (int i = 0; i != arrayList.size(); ++i) {
            result += Math.abs(arrayList.get(i) - middle);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(findCount(args[0]));
    }
}
