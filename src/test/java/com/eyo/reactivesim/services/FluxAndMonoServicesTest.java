package com.eyo.reactivesim.services;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import reactor.tools.agent.ReactorDebugAgent;

class FluxAndMonoServicesTest {

    FluxAndMonoServices fluxAndMonoServices = new FluxAndMonoServices();

    @Test
    void fruitsFlux() {
        var fruitsFlux = fluxAndMonoServices.fruitsFlux();
        StepVerifier.create(fruitsFlux)
                .expectNext("Pineapple", "Melons", "Oranges")
                .verifyComplete();
    }

    @Test
    void fruitsMono() {
        var fruitsMono = fluxAndMonoServices.fruitsMono();
        StepVerifier.create(fruitsMono)
                .expectNext( "Oranges")
                .verifyComplete();
    }

    @Test
    void fruitsFluxMap() {
        var fruitsFlux = fluxAndMonoServices.fruitsFluxMap();
        StepVerifier.create(fruitsFlux)
                .expectNext("PINEAPPLE", "MELONS", "ORANGES")
                .verifyComplete();
    }

    @Test
    void fruitsFluxFilter() {
        var fruitsFlux = fluxAndMonoServices.fruitsFluxFilter(5);
        StepVerifier.create(fruitsFlux)
                .expectNext("Pineapple", "Melons", "Oranges")
                .verifyComplete();
    }

    @Test
    void fruitsFluxFilterMap() {
        var fruitsFlux = fluxAndMonoServices.fruitsFluxFilterMap(5);
        StepVerifier.create(fruitsFlux)
                .expectNext("PINEAPPLE", "MELONS", "ORANGES")
                .verifyComplete();
    }

    @Test
    void fruitsFluxFlatMap() {
        var fruitsFlux = fluxAndMonoServices.fruitsFluxFlatMap();
        StepVerifier.create(fruitsFlux)
                .expectNextCount(22)
                .verifyComplete();
    }

    @Test
    void fruitsFluxFlatMapAsync() {
        var fruitsFlux = fluxAndMonoServices.fruitsFluxFlatMapAsync();
        StepVerifier.create(fruitsFlux)
                .expectNextCount(22)
                .verifyComplete();
    }

    @Test
    void fruitsFluxConcatMap() {
        var fruitsFlux = fluxAndMonoServices.fruitsFluxConcatMap();
        StepVerifier.create(fruitsFlux)
                .expectNextCount(22)
                .verifyComplete();
    }

    @Test
    void fruitsMonoFlatMap() {
        var fruitsFlux = fluxAndMonoServices.fruitsMonoFlatMap();
        StepVerifier.create(fruitsFlux)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void fruitsMonoFlatMapMany() {
        var fruitsFlux = fluxAndMonoServices.fruitsMonoFlatMapMany();
        StepVerifier.create(fruitsFlux)
                .expectNextCount(7)
                .verifyComplete();
    }


    @Test
    void fruitsFluxTransform() {
        var fruitsFlux = fluxAndMonoServices.fruitsFluxTransform(5);
        StepVerifier.create(fruitsFlux)
                .expectNext("Pineapple", "Melons", "Oranges")
                .verifyComplete();
    }

    @Test
    void fruitsFluxTransformDefaultIf() {
        var fruitsFlux = fluxAndMonoServices.fruitsFluxTransformDefaultIf(10);
        StepVerifier.create(fruitsFlux)
                .expectNext("Default")
                .verifyComplete();
    }

    @Test
    void fruitsFluxTransformSwitchIfEmpty() {
        var fruitsFlux = fluxAndMonoServices.fruitsFluxTransformSwitchIfEmpty(10);
        StepVerifier.create(fruitsFlux)
                .expectNext("Apple Juice", "Joker fruit")
                .verifyComplete();
    }

    @Test
    void fruitsFluxConcat() {
        var fruitsFlux = fluxAndMonoServices.fruitsFluxConcat().log();
        StepVerifier.create(fruitsFlux)
                .expectNext("Orange", "Mango","Tomato", "Cabbage")
                .verifyComplete();
    }

    @Test
    void fruitsFluxConcatWith() {
        var fruitsFlux = fluxAndMonoServices.fruitsFluxConcatWith().log();
        StepVerifier.create(fruitsFlux)
                .expectNext("Orange", "Mango","Tomato", "Cabbage")
                .verifyComplete();
    }

    @Test
    void fruitsMonoConcatWith() {
        var fruitsFlux = fluxAndMonoServices.fruitsMonoConcatWith().log();
        StepVerifier.create(fruitsFlux)
                .expectNext("Orange", "Tomato")
                .verifyComplete();
    }

    @Test
    void fruitsFluxMerge() {
        var fruitsFlux = fluxAndMonoServices.fruitsFluxMerge().log();
        StepVerifier.create(fruitsFlux)
                .expectNext("Orange", "Tomato","Mango", "Cabbage")
                .verifyComplete();
    }

    @Test
    void fruitsFluxMergeWith() {
        var fruitsFlux = fluxAndMonoServices.fruitsFluxMergeWith().log();
        StepVerifier.create(fruitsFlux)
                .expectNext("Orange", "Tomato","Mango", "Cabbage")
                .verifyComplete();
    }

    @Test
    void fruitsFluxMergeWithSequential() {
        var fruitsFlux = fluxAndMonoServices.fruitsFluxMergeWithSequential().log();
        StepVerifier.create(fruitsFlux)
                .expectNext("Orange", "Mango","Tomato", "Cabbage")
                .verifyComplete();
    }

    @Test
    void fruitsFluxZip() {
        var fruitsFlux = fluxAndMonoServices.fruitsFluxZip().log();
        StepVerifier.create(fruitsFlux)
                .expectNext("OrangeTomato","MangoCabbage")
                .verifyComplete();
    }

    @Test
    void fruitsFluxZipWith() {
        var fruitsFlux = fluxAndMonoServices.fruitsFluxZipWith().log();
        StepVerifier.create(fruitsFlux)
                .expectNext("OrangeTomato","MangoCabbage")
                .verifyComplete();
    }

    @Test
    void fruitsFluxZipTuple() {
        var fruitsFlux = fluxAndMonoServices.fruitsFluxZipTuple().log();
        StepVerifier.create(fruitsFlux)
                .expectNext("OrangeTomatoPepper","MangoCabbageOnions")
                .verifyComplete();
    }

    @Test
    void fruitsMonoZipWith() {
        var fruitsMono = fluxAndMonoServices.fruitsMonoZipWith().log();
        StepVerifier.create(fruitsMono)
                .expectNext("OrangeTomato")
                .verifyComplete();
    }

    @Test
    void fruitsFluxFilterDoOnNext() {
        var fruitsMono = fluxAndMonoServices.fruitsFluxFilterDoOn(5).log();
        StepVerifier.create(fruitsMono)
                .expectNext("Pineapple", "Melons", "Oranges")
                .verifyComplete();
    }

    @Test
    void fruitsFluxOnErrorReturn() {
        var fruitsFlux = fluxAndMonoServices.fruitsFluxOnErrorReturn().log();
        StepVerifier.create(fruitsFlux)
                .expectNext("Pineapple", "Melons", "Oranges", "Apple")
                .verifyComplete();
    }

    @Test
    void fruitsFluxOnErrorContinue() {
        var fruitsFlux = fluxAndMonoServices.fruitsFluxOnErrorContinue().log();
        StepVerifier.create(fruitsFlux)
                .expectNext("PINEAPPLE", "ORANGES")
                .verifyComplete();
    }

    @Test
    void fruitsFluxOnErrorMap() {
        //Hooks.onOperatorDebug();
        ReactorDebugAgent.init();
        ReactorDebugAgent.processExistingClasses();
        var fruitsFlux = fluxAndMonoServices.fruitsFluxOnErrorMap().log();
        StepVerifier.create(fruitsFlux)
                .expectNext("PINEAPPLE")
                .expectError(IllegalStateException.class)
                .verify();
    }

    @Test
    void fruitsFluxDoOnError() {
        var fruitsFlux = fluxAndMonoServices.fruitsFluxDoOnError().log();
        StepVerifier.create(fruitsFlux)
                .expectNext("PINEAPPLE")
                .expectError(RuntimeException.class)
                .verify();
    }
}