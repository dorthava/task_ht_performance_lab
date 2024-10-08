import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

public class task2 {
    public static final int LIES_ON_A_CIRCLE = 0;
    public static final int THE_POINT_INSIDE = 1;
    public static final int THE_POINT_IS_OUTSIDE = 2;

    private static void calculateThePosition(String filenameDescriptionCircle, String filenameCoordinates) throws IOException {
        BigDecimal circleCoordinatesX, circleCoordinatesY, circleRadius;
        ArrayList<BigDecimal> coordinates = new ArrayList<>();
        try (FileReader fileReader1 = new FileReader(filenameDescriptionCircle);
             BufferedReader bufferedReader1 = new BufferedReader(fileReader1);
             FileReader fileReader2 = new FileReader(filenameCoordinates);
             BufferedReader bufferedReader2 = new BufferedReader(fileReader2)) {

            String[] coordinatesCircle = bufferedReader1.readLine().split(" ");
            circleCoordinatesX = new BigDecimal(coordinatesCircle[0]);
            circleCoordinatesY = new BigDecimal(coordinatesCircle[1]);

            String radius = bufferedReader1.readLine();
            circleRadius = new BigDecimal(radius);
            while (bufferedReader2.ready()) {
                String line = bufferedReader2.readLine();
                try {
                    String[] splitCoordinates = line.split(" ");
                    BigDecimal firstNumber = new BigDecimal(splitCoordinates[0]);
                    BigDecimal secondNumber = new BigDecimal(splitCoordinates[1]);
                    coordinates.add(firstNumber);
                    coordinates.add(secondNumber);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid coordinate format: " + line);
                }
            }
        }

        BigDecimal resultRadius = circleRadius.pow(2);
        for (int i = 0; i < coordinates.size(); ++i) {
            BigDecimal firstValue = coordinates.get(i++).subtract(circleCoordinatesX).pow(2);
            BigDecimal secondValue = coordinates.get(i).subtract(circleCoordinatesY).pow(2);

            BigDecimal resultValue = firstValue.add(secondValue);
            if (resultValue.compareTo(resultRadius) < 0) {
                System.out.println(THE_POINT_INSIDE);
            } else if (resultValue.compareTo(resultRadius) > 0) {
                System.out.println(THE_POINT_IS_OUTSIDE);
            } else {
                System.out.println(LIES_ON_A_CIRCLE);
            }
        }
    }

    public static void main(String[] args) {
        try {
            calculateThePosition(args[0], args[1]);
        } catch (NumberFormatException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
