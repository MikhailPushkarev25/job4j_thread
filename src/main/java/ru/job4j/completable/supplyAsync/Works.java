package ru.job4j.completable.supplyAsync;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static ru.job4j.completable.FutureWork.goToTrash;
import static ru.job4j.completable.FutureWork.iWork;

public class Works {

    public static CompletableFuture<String> buyProduct(String product) {
        return CompletableFuture.supplyAsync(
                () -> {
                    System.out.println("Сын: Мам/Пап, я пошел в магазин");
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Сын: Мам/Пап, я купил " + product);
                    return product;
                }
        );
    }

    public static void thenAcceptExample() throws InterruptedException, ExecutionException {
        CompletableFuture<String> bm = buyProduct("Молоко");
        bm.thenAccept((product) -> System.out.println("Сын: Я убрал " + product + " в холодильник"));
        iWork();
        System.out.println("Куплено: " + bm.get());
    }

    public static void supplyAsyncExample() throws InterruptedException, ExecutionException {
        CompletableFuture<String> bm = buyProduct("Молоко");
        iWork();
        System.out.println("Куплено: " + bm.get());
    }

    public static void thenApplyExample() throws ExecutionException, InterruptedException {
        CompletableFuture<String> bm = buyProduct("Молоко")
                .thenApply((product) -> "Сын: я налил тебе в кружку " + product + ". Держи");
        iWork();
        System.out.println(bm.get());
    }

    public static void theComposeExample() throws InterruptedException {
        CompletableFuture<Void> result = buyProduct("Молоко").thenCompose(a -> goToTrash());
        iWork();
    }

    public static void thenCombineExample() throws InterruptedException, ExecutionException {
        CompletableFuture<String> result = buyProduct("Молоко")
                .thenCombine(buyProduct("Хлеб"), (r1, r2) -> "Куплены " + r1 + " и " + r2);
        iWork();
        System.out.println(result.get());
    }

    public static CompletableFuture<Void> washHands(String name) {
        return CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + ", моет руки");
        });
    }

    public static void allOfExample() throws InterruptedException {
        CompletableFuture<Void> all = CompletableFuture.allOf(
             washHands("Папа"),  washHands("Мама"),
                washHands("Ваня"), washHands("Боря")
        );
        TimeUnit.SECONDS.sleep(3);
    }

    public static CompletableFuture<String> whoWashHandle(String name) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return name + ", моет руки";
        });
    }

    public static void anyOfExample() throws ExecutionException, InterruptedException {
        CompletableFuture<Object> first = CompletableFuture.anyOf(
                whoWashHandle("Папа"), whoWashHandle("Мама"),
                whoWashHandle("Ваня"), whoWashHandle("Боря")
        );
        System.out.println("Кто сейчас моет руки?");
        TimeUnit.SECONDS.sleep(1);
        System.out.println(first.get());
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
       anyOfExample();
    }
}
