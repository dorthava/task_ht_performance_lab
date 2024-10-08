import java.util.ArrayList;
import java.util.Arrays;

public class task1 {
    public static int[] findWay(int n, int m) {
        if (n <= 0 || m <= 0) {
            return null;
        }

        int[] array = new int[n];
        for (int i = 1; i <= n; ++i) {
            array[i - 1] = i;
        }

        ArrayList<Integer> resultArrayList = new ArrayList<>();
        boolean[] visited = new boolean[n];
        int i = 0;
        while (!visited[i]) {
            resultArrayList.add(array[i]);
            visited[i] = true;
            i = (i + m - 1) % n;
        }

        int[] result = new int[resultArrayList.size()];
        for (int j = 0; j != result.length; ++j) {
            result[j] = resultArrayList.get(j);
        }
        return result;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);
        System.out.println(Arrays.toString(findWay(n, m)));
    }
}
