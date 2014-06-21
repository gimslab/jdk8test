package java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class StreamExam02 {
	public static void main(String[] args) {
		new StreamExam02().test01();
		new StreamExam02().test02();
		new StreamExam02().test03();
	}

	private void test01() {
		List<String> words = Arrays.asList(new String[] { "aa", "bbb", "cc" });

		long count1 = words.stream().filter(w -> w.length() > 2).count();
		System.out.println(count1);

		long count2 = words.parallelStream().filter(w -> w.startsWith("b"))
				.count();
		System.out.println(count2);
	}

	private void test02() {
		Stream<String> words1 = Stream.of("hello", "book", "boy");
		words1.forEach(w -> System.out.println(w));

		Stream<String> words2 = Stream.empty();
		words2.forEach(w -> System.out.println(w));

		// Stream<String> words3 = Stream.generate(() -> "hi");
		// words3.forEach(w -> System.out.println(w));

		// Stream<Double> words4 = Stream.generate(Math::random);
		// words4.forEach(w -> System.out.println(w));

		// Stream<Integer> ints = Stream.iterate(1, n -> n + 2)
		// .filter(n -> n < 10);
		// ints.forEach(n -> System.out.println(n));

	}

	private void test03() {
		Stream<String> words1 = Stream.of("Hello", "BOOK", "GIRL");

		Stream<String> lowercaseWords1 = words1.map(String::toLowerCase);
		lowercaseWords1.forEach(s -> System.out.println(s));

		Stream<String> words2 = Stream.of("Hello", "BOOK", "GIRL");
		Function mapper1 = new Function<String, String>() {
			@Override
			public String apply(String t) {
				return t.toLowerCase();
			}
		};
		Stream<String> lowercaseWords2 = words2.map(mapper1);
		lowercaseWords2.forEach(s -> System.out.println(s));

		Stream<String> words3 = Stream.of("Hello", "BOOK", "GURU");
		Stream<String> lowercaseWords3 = words3.map(s -> s.toLowerCase());

		Stream<String> words4 = Stream.of("Hello", "BOOK", "GURU");
		Stream<Integer> lengths1 = words4.map(String::length);
		lengths1.forEach(s -> System.out.println(s));

		Stream<String> words5 = Stream.of("Hello", "BOOK", "GURUtest123");
		Stream<Integer> lengths2 = words5.map(s -> s.length());
		lengths2.forEach(s -> System.out.println(s));

	}
}
