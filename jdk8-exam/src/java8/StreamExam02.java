package java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamExam02 {
	public static void main(String[] args) {
		StreamExam02 exam = new StreamExam02();
		//		exam.test01();
		//		exam.test02();
		//		exam.test03();
		//		exam.text04();
		//		exam.test05();
		//		exam.test06();
		//		exam.test07();
		//		exam.test08();
		//		exam.reduce();
		//		exam.reduce2();
		//		exam.collect();
//		exam.collect2();
//		exam.forEach();
//		exam.map();
		exam.map2();
	}

	private void map2() {
		Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
		Map<String, Set<String>> countryLanguageSets = locales.collect(Collectors.toMap(
			l -> l.getDisplayCountry(),
			l -> Collections.singleton(l.getDisplayLanguage()),
			(a, b) -> {
				Set<String> r = new HashSet<>(a);
				r.addAll(b);
				return r;
			}
		));
	}

	private void map() {
		Stream<Person> people = Stream.of(new Person(3, "abc"), new Person(8, "ddd"), new Person(10, "rrr"));
		Map<Integer, String> map = people.collect(Collectors.toMap(Person::getId, Person::getName));
		people = Stream.of(new Person(3, "abc"), new Person(8, "ddd"), new Person(10, "rrr"));
		Map<Integer, Person> map2 = people.collect(Collectors.toMap(Person::getId, Function.identity()));
	}
	
	class Person{
		int id;
		String name;
		public Person(int id, String name) {
			this.id = id;
			this.name = name;
		}
		int getId(){
			return id;
		}
		String getName(){
			return name;
		}
	}

	private void forEach() {
		Stream<String> words = Stream.of("abc", "de", "aaaa");
//		words.forEach(System.out::println);
		words.forEach(s->System.out.println(s));
	}

	private void collect2() {
		Stream<String> words = Stream.of("abc", "de", "aaaa");
		IntSummaryStatistics summary = words.collect(Collectors.summarizingInt(String::length));
		System.out.println(summary.getMax());
	}

	private void collect() {
		Stream<String> words = Stream.of("abc", "de", "aaaa");
		//		List<String> list = words.collect(Collectors.toList());
		//		Set<String> set = words.collect(Collectors.toSet());
		//		TreeSet<String> treeSet = words.collect(Collectors.toCollection(TreeSet::new));
		String result = words.collect(Collectors.joining(","));
		System.out.println(result);
	}

	private void reduce2() {
		Stream<String> words = Stream.of("abc", "de", "aaaa");
		Integer i = words.reduce(0, (total, word) -> total + word.length(), (total1, total2) -> total1 + total2);
		System.out.println(i);

		words = Stream.of("abc", "de", "aaaa");
		IntStream ints = words.mapToInt(s -> s.length());
		System.out.println(ints.sum());
	}

	private void reduce() {
		Stream<Integer> values = Stream.of(1, 2, 3, 4, 5);
		Optional<Integer> sum = values.reduce((x, y) -> x + y);
		System.out.println(sum.orElse(-1));

		values = Stream.of(1, 2, 3, 4, 5);
		Integer sub = values.reduce(0, (x, y) -> x - y);
		System.out.println(sub);
	}

	private void test08() {
		Optional o = Optional.of(3);
		if (o.isPresent())
			System.out.println(o.get());
		o = Optional.empty();
		System.out.println(o.isPresent());
		String s = null;
		o = Optional.ofNullable(s);
		System.out.println(o.orElse("NULL"));
		s = "a";
		o = Optional.ofNullable(s);
		System.out.println(o.orElse("NULL"));
	}

	private void test07() {
		Stream<String> words = Stream.of("abc", "def", "Xhijkl", "mno", "Xopqrstu").distinct();
		Optional<String> word = words.filter(s -> s.endsWith("L")).findFirst();
		String w = word.orElse("NOT_EXISTS");
		System.out.println(w);
		w = word.orElseGet(() -> "default value");
		System.out.println(w);
	}

	private void test06() {
		Stream<String> words = Stream.of("abc", "def", "Xhijkl", "mno", "Xopqrstu").distinct();
		Optional<String> x = words.filter(s -> s.indexOf('o') > -1).findAny();
		Optional<Object> l = x.map(v -> v.length());
		System.out.println(l.get());
	}

	private void test05() {
		Stream<String> words = Stream.of("abc", "def", "Xhijkl", "mno", "Xopqrstu").distinct();
		Stream<String> filtered = words.filter(s -> s.startsWith("X"));
		Optional<String> result = filtered.findFirst();
		if (result.isPresent())
			System.out.println("result is " + result.get());

		words = Stream.of("abc", "def", "Xhijkl", "mno", "Xopqrstu").distinct();
		filtered = words.parallel().filter(s -> s.startsWith("X"));
		result = filtered.findAny();
		if (result.isPresent())
			System.out.println("result is " + result.get());

		words = Stream.of("abc", "def", "Xhijkl", "mno", "Xopqrstu").distinct();
		boolean exists = words.parallel().anyMatch(s -> s.endsWith("f"));
		System.out.println("exists = " + exists);
	}

	private void text04() {
		Stream<String> words = Stream.of("abc", "def", "Xhijkl", "mno", "opqrstu").distinct();
		Optional<String> largest = words.max(String::compareToIgnoreCase);
		System.out.println(largest.isPresent());
		System.out.println(largest);
	}

	private void test01() {
		List<String> words = Arrays.asList(new String[] {"aa", "bbb", "cc"});

		long count1 = words.stream().filter(w -> w.length() > 2).count();
		System.out.println(count1);

		long count2 = words.parallelStream().filter(w -> w.startsWith("b")).count();
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
