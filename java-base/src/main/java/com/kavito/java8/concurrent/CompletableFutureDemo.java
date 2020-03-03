package com.kavito.java8.concurrent;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Description: CompletableFuture 异步编程
 * https://www.cnblogs.com/fingerboy/p/9948736.html
 *
 * 创建CompletableFuture对象:
 *      四个静态方法用来为一段异步执行的代码创建CompletableFuture对象，方法的参数类型都是函数式接口，
 *      所以可以使用lambda表达式实现异步任务。
 *      runAsync方法：它以Runnabel函数式接口类型为参数，所以CompletableFuture的计算结果为空。
 *      supplyAsync方法以Supplier<U>函数式接口类型为参数，CompletableFuture的计算结果类型为U。
 *
 *          public static CompletableFuture<Void> runAsync(Runnable runnable)
 *          public static CompletableFuture<Void> runAsync(Runnable runnable, Executor executor)
 *          public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier)
 *          public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier, Executor executor)
 * 参考：
 * 好：https://www.imooc.com/article/details/id/21655
 * 好：https://segmentfault.com/a/1190000013452165?utm_source=index-hottest
 * https://www.cnblogs.com/happyliu/archive/2018/08/12/9462703.html
 * https://www.jianshu.com/p/6bac52527ca4
 * @Author: kavito
 * @Date: 2019/12/24 2:26 下午
 */
public class CompletableFutureDemo {


    /**
     * whenComplete 和 whenCompleteAsync 的区别：
     * whenComplete：是执行当前任务的线程执行继续执行 whenComplete 的任务。
     * whenCompleteAsync：是执行把 whenCompleteAsync 这个任务继续提交给线程池来进行执行。
     *
     *
     *
     * 作者：jijs
     * 链接：https://www.jianshu.com/p/6bac52527ca4
     * 来源：简书
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     *  1、Consumer将会同步执行，所以我们无需在返回的CompletableFuture上执行join操作
     *     说明：Async结尾的方法都是可以异步执行的，如果指定了线程池，会在指定的线程池中执行，
     *     如果没有指定，默认会在ForkJoinPool.commonPool()中执行。
     *
     *     public <U> CompletionStage<U> thenApply(Function<? super T,? extends U> fn);//当前线程继续执行
     *     public <U> CompletionStage<U> thenApplyAsync(Function<? super T,? extends U> fn);//默认使用ForkJoinPool
     *     public <U> CompletionStage<U> thenApplyAsync(Function<? super T,? extends U> fn, Executor executor);//使用指定的线程池
     */
    @Test
    public void thenApplyAsyncTest() {
        String result = CompletableFuture.supplyAsync(()->{return "hello ";})
                .thenApplyAsync(x-> x + "world!")
                .thenApplyAsync(String::toUpperCase)
                .join();
        System.out.println(result);
    }


    /**
     * 2、thenAccept()是只会对计算结果进行消费而不会返回任何结果的方法。
     *
     * public CompletionStage<Void> thenAccept(Consumer<? super T> action);
     * public CompletionStage<Void> thenAcceptAsync(Consumer<? super T> action);
     * public CompletionStage<Void> thenAcceptAsync(Consumer<? super T> action,Executor executor);
     *
     */
    @Test
    public void thenAcceptAsyncTest() {
        CompletableFuture.supplyAsync(()->{return "hello ";}).thenAcceptAsync(x->{
            System.out.println(x+"world!");
        });

    }


    /**
     * 3、thenCompose可以用于组合多个CompletableFuture，将前一个结果作为下一个计算的参数，它们之间存在着先后顺序。
     */
   @Test
   public void thenComposeTest(){
       CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello")
               .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " World"));
       try {
           System.out.println(future.get());
       } catch (InterruptedException e) {
           e.printStackTrace();
       } catch (ExecutionException e) {
           e.printStackTrace();
       }
   }
   // 多次调用thenCompose
   @Test
   public void thenComposeTest2(){
       CompletableFuture<Double> future = CompletableFuture.supplyAsync(() -> "100")
               .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + "100"))
               .thenCompose(s -> CompletableFuture.supplyAsync(() -> Double.parseDouble(s)));

       try {
           System.out.println(future.get());
       } catch (InterruptedException e) {
           e.printStackTrace();
       } catch (ExecutionException e) {
           e.printStackTrace();
       }
   }

    /**
     * 4、使用thenCombine()之后future1、future2之间是并行执行的，最后再将结果汇总。这一点跟thenCompose()不同。
     *
     * public <U,V> CompletionStage<V> thenCombine(CompletionStage<? extends U> other,BiFunction<? super T,? super U,? extends V> fn);
     * public <U,V> CompletionStage<V> thenCombineAsync(CompletionStage<? extends U> other,BiFunction<? super T,? super U,? extends V> fn);
     * public <U,V> CompletionStage<V> thenCombineAsync(CompletionStage<? extends U> other,BiFunction<? super T,? super U,? extends V> fn,Executor executor);
     */

    @Test
    public void thenCombineTest() {

        String result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("第一步计算就绪，结果是：hello");
            return "hello";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("第二步计算就绪，结果是：world!");
            return "world!";
        }), (s1, s2) -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("合并结果："+s1 + " " + s2);
            return s1 + " " + s2;
        }).join();
        System.out.println(result);
    }

    @Test
    public void thenCombineTest1(){
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "100");
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> 100);

        CompletableFuture<Double> future = future1.thenCombine(future2, (s, i) -> Double.parseDouble(s + i));

        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 5、thenAcceptBoth跟thenCombine类似，但是返回CompletableFuture<Void>类型。
     *
     */
    @Test
    public void thenAcceptBothTest(){
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "100");
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> 100);

        CompletableFuture<Void> future = future1.thenAcceptBoth(future2, (s, i) -> System.out.println(Double.parseDouble(s + i)));

        try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


    /**
     * 6、当CompletableFuture完成计算结果时对结果进行处理，或者当CompletableFuture产生异常的时候对异常进行处理。
     */
    @Test
    public void whenCompleteTest(){
        CompletableFuture.supplyAsync(() -> "Hello")
                .thenApply(s->s+" World")
                .thenApply(s->s+ "\nThis is CompletableFuture demo")
                .thenApply(String::toLowerCase)
                .whenComplete((result, throwable) -> System.out.println(result));
    }
    /**
     * 7、当CompletableFuture完成计算结果或者抛出异常的时候，执行提供的fn
     * handle(BiFunction<? super T, Throwable, ? extends U> fn)
     */
    @Test
    public void handleTest(){
        CompletableFuture<Double> future = CompletableFuture.supplyAsync(() -> "100")
                .thenApply(s->s+"100")
                .handle((s, t) -> s != null ? Double.parseDouble(s) : 0);

        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void completedFutureExample(){
        CompletableFuture cf = CompletableFuture.completedFuture("message");
    }

}
