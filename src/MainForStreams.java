import java.util.Random;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {

        public static double method(long size) {
            Random random = new Random();
            IntStream stream = random.ints(size, 0, 10_000);
            double count = stream.filter(element -> element % 2 != 0)
                    .count();
            double answer = count / size * 100;

            return answer;
        }

        public static double oddNumbersRatio(long streamSize) {
            Random random = new Random();
            IntStream stream = random.ints(streamSize);
            double answer = stream
                    .filter(num -> num % 2 != 0)
                    .count();
            return answer / streamSize;
        }

    }
}