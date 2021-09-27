package com.arpitech.solutions.reactive;

import io.reactivex.Flowable;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;
import java.util.stream.IntStream;

import static java.util.concurrent.TimeUnit.SECONDS;

@SpringBootApplication
public class ReactiveApplication {

	public static void main(String[] args) throws InterruptedException {
		Flowable.interval(1, 1, SECONDS)
				.map(ReactiveApplication::transform)
				.subscribe(ReactiveApplication::process, ReactiveApplication::dealWithError);
		SECONDS.sleep(10);

		/*IntStream.of(1,2,3)
				.forEach(System.out::println);*/
	}

	public static void dealWithError(Throwable throwable) {
		throwable.printStackTrace();
	}

	public static void process(Long number) {
		System.out.println("Receive number " + number);
	}

	public static Long transform(Long number) {
		if(new Random().nextDouble() < 0.3) throw new RuntimeException("Ops!");
		return number * 2;
	}

}
