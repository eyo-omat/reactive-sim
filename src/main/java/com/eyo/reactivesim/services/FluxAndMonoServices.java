package com.eyo.reactivesim.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class FluxAndMonoServices {

    public Flux<String> fruitsFlux() {
        return Flux.fromIterable(List.of("Pineapple", "Melons", "Oranges")).log();
    }

    public Flux<String> fruitsFluxMap() {
        return Flux.fromIterable(List.of("Pineapple", "Melons", "Oranges"))
                .map(String::toUpperCase)
                .log();
    }

    public Flux<String> fruitsFluxFilter(int number) {
        return Flux.fromIterable(List.of("Pineapple", "Melons", "Oranges"))
                .filter(s -> s.length() > number)
                .log();
    }

    public Flux<String> fruitsFluxFilterMap(int number) {
        return Flux.fromIterable(List.of("Pineapple", "Melons", "Oranges"))
                .filter(s -> s.length() > number)
                .map(String::toUpperCase)
                .log();
    }

    public Flux<String> fruitsFluxFlatMap() {
        return Flux.fromIterable(List.of("Pineapple", "Melons", "Oranges"))
                .flatMap(s -> Flux.just(s.split("")))
                .log();
    }

    public Flux<String> fruitsFluxFlatMapAsync() {
        return Flux.fromIterable(List.of("Pineapple", "Melons", "Oranges"))
                .flatMap(s -> Flux.just(s.split("")))
                .delayElements(Duration.ofMillis(new Random().nextInt(1000)))
                .log();
    }

    public Flux<String> fruitsFluxConcatMap() {
        return Flux.fromIterable(List.of("Pineapple", "Melons", "Oranges"))
                .concatMap(s -> Flux.just(s.split("")))
                .delayElements(Duration.ofMillis(new Random().nextInt(1000)))
                .log();
    }

    public Flux<String> fruitsFluxTransform(int number) {
        Function<Flux<String>, Flux<String>> filterData = data -> data.filter(s -> s.length() > number);

        return Flux.fromIterable(List.of("Pineapple", "Melons", "Oranges"))
                .transform(filterData)
                //.filter(s -> s.length() > number)
                .log();
    }

    public Flux<String> fruitsFluxTransformDefaultIf(int number) {
        Function<Flux<String>, Flux<String>> filterData = data -> data.filter(s -> s.length() > number);

        return Flux.fromIterable(List.of("Pineapple", "Melons", "Oranges"))
                .transform(filterData)
                .defaultIfEmpty("Default")
                .log();
    }

    public Flux<String> fruitsFluxTransformSwitchIfEmpty(int number) {
        Function<Flux<String>, Flux<String>> filterData = data -> data.filter(s -> s.length() > number);

        return Flux.fromIterable(List.of("Pineapple", "Melons", "Oranges"))
                .transform(filterData)
                .switchIfEmpty(Flux.just("Apple Juice", "Joker fruit")
                .transform(filterData))
                .log();
    }

    public Flux<String> fruitsFluxConcat() {
        var fruits = Flux.just("Orange", "Mango");
        var veggies = Flux.just("Tomato", "Cabbage");

        return Flux.concat(fruits, veggies);
    }

    public Flux<String> fruitsFluxConcatWith() {
        var fruits = Flux.just("Orange", "Mango");
        var veggies = Flux.just("Tomato", "Cabbage");

        return fruits.concatWith(veggies);
    }

    public Flux<String> fruitsMonoConcatWith() {
        var fruits = Mono.just("Orange");
        var veggies = Mono.just("Tomato");

        return fruits.concatWith(veggies);
    }

    public Flux<String> fruitsFluxMerge() {
        var fruits = Flux.just("Orange", "Mango")
                .delayElements(Duration.ofMillis(50));
        var veggies = Flux.just("Tomato", "Cabbage")
                .delayElements(Duration.ofMillis(75));

        return Flux.merge(fruits, veggies);
    }

    public Flux<String> fruitsFluxMergeWith() {
        var fruits = Flux.just("Orange", "Mango")
                .delayElements(Duration.ofMillis(50));
        var veggies = Flux.just("Tomato", "Cabbage")
                .delayElements(Duration.ofMillis(75));

        return fruits.mergeWith(veggies);
    }

    public Flux<String> fruitsFluxMergeWithSequential() {
        var fruits = Flux.just("Orange", "Mango")
                .delayElements(Duration.ofMillis(50));
        var veggies = Flux.just("Tomato", "Cabbage")
                .delayElements(Duration.ofMillis(75));

        return Flux.mergeSequential(fruits, veggies);
    }

    public Flux<String> fruitsFluxZip() {
        var fruits = Flux.just("Orange", "Mango");
        var veggies = Flux.just("Tomato", "Cabbage");

        return Flux.zip(fruits, veggies,
                (first,second) -> first+second).log();
    }

    public Flux<String> fruitsFluxZipWith() {
        var fruits = Flux.just("Orange", "Mango");
        var veggies = Flux.just("Tomato", "Cabbage");

        return fruits.zipWith(veggies,
                (first,second) -> first+second).log();
    }

    public Flux<String> fruitsFluxZipTuple() {
        var fruits = Flux.just("Orange", "Mango");
        var veggies = Flux.just("Tomato", "Cabbage");
        var moreVeggies = Flux.just("Pepper", "Onions");

        return Flux.zip(fruits, veggies, moreVeggies)
                .map(objects -> objects.getT1()+objects.getT2()+objects.getT3()).log();
    }

    public Mono<String> fruitsMonoZipWith() {
        var fruits = Mono.just("Orange");
        var veggies = Mono.just("Tomato");

        return fruits.zipWith(veggies,(first,second) -> first+second).log();
    }

    public Flux<String> fruitsFluxFilterDoOn(int number) {
        return Flux.fromIterable(List.of("Pineapple", "Melons", "Oranges"))
                .filter(s -> s.length() > number)
                .doOnNext(s -> {
                    System.out.println("s = " + s);
                })
                .doOnSubscribe(subscription -> {
                    System.out.println("subscription.toString() = " + subscription);
                })
                .doOnComplete(() -> System.out.println("Completed!!!"))
                .log();
    }

    public Flux<String> fruitsFluxOnErrorReturn() {
        return Flux.just("Pineapple", "Melons", "Oranges")
                .concatWith(Flux.error(
                        new RuntimeException("Exception Occurred")
                ))
                .onErrorReturn("Apple")
                .log();
    }

    public Flux<String> fruitsFluxOnErrorContinue() {
        return Flux.just("Pineapple", "Melons", "Oranges")
                .map(s -> {
                    if (s.equalsIgnoreCase("Melons"))
                        throw new RuntimeException("Exception Occurred");
                    return s.toUpperCase();
                })
                .onErrorContinue((e,f) -> {
                    System.out.println("e = " + e);
                    System.out.println("f = " + f);
                })
                .log();
    }

    public Flux<String> fruitsFluxOnErrorMap() {
        return Flux.just("Pineapple", "Melons", "Oranges")
                .map(s -> {
                    if (s.equalsIgnoreCase("Melons"))
                        throw new RuntimeException("Exception Occurred");
                    return s.toUpperCase();
                })
                .onErrorMap(throwable-> {
                    System.out.println("e = " + throwable);
                    return new IllegalStateException("From onError Map");
                })
                .log();
    }

    public Flux<String> fruitsFluxDoOnError() {
        return Flux.just("Pineapple", "Melons", "Oranges")
                .map(s -> {
                    if (s.equalsIgnoreCase("Melons"))
                        throw new RuntimeException("Exception Occurred");
                    return s.toUpperCase();
                })
                .doOnError(throwable-> {
                    System.out.println("e = " + throwable);
                })
                .log();
    }

    public Mono<String> fruitsMono() {
        return Mono.just("Oranges").log();
    }

    public Mono<List<String>> fruitsMonoFlatMap() {
        return Mono.just("Oranges")
                .flatMap(s -> Mono.just(List.of(s.split(""))))
                .log();
    }

    public Flux<String> fruitsMonoFlatMapMany() {
        return Mono.just("Oranges")
                .flatMapMany(s -> Flux.just(s.split("")))
                .log();
    }

    public static void main(String[] args) {
        FluxAndMonoServices fluxAndMonoServices = new FluxAndMonoServices();
        fluxAndMonoServices.fruitsFlux().subscribe(
                s -> {
                    System.out.println("s = " + s);
                }
        );

        fluxAndMonoServices.fruitsMono().subscribe(
                s -> {
                    System.out.println("Mono -> s = " + s);
                }
        );
    }
}
