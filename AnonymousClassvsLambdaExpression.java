public class AnonymousClassvsLambdaExpression {
    public static void main(String[] args) {
        Runnable runnable= new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello fro Runnable");
            }
        };

        Thread t=new Thread(runnable);

        t.start();


        Runnable runnable1= () -> System.out.println("Hello fro Runnable");

        Thread t1=new Thread(runnable1);

        t1.start();







    }
}
