package play.java8.lambda;

/**
 * @author inhee kim
 */
public class AsyncHelloWorld {

    private static void sayHelloWorld() {
        System.out.println("Say Hello World");
    }

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("Hello World");
        });

        //new Thread(() -> sayHelloWorld()).start();
    }
}
