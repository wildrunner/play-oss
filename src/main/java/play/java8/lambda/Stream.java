package play.java8.lambda;

import java.util.stream.IntStream;

/**
 * @author inhee kim
 */
public class Stream {
    public static void main(String[] args) {
        IntStream.range(1, 100)
                .filter(n -> n % 2 == 0)
                .map(n -> n * n)
                /*.forEach(System.out::println);*/
                .skip(10)
                .limit(10)
                .reduce(0, Integer::sum);
    }
}
