package jdk8exam;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Test1 {

	public static void main(String[] args) throws Exception {
//				MyIf mif = new MyIfImpl();
//				mif.sayHello();
//				mif.sayDefaultHello();

		//		Runnable r;

		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
		//		numbers.forEach(new Consumer<Integer>() {
		//			@Override
		//			public void accept(Integer t) {
		//				System.out.println(t);
		//			}
		//		});

		//		numbers.forEach((Integer value) -> System.out.println(value));
		//		numbers.forEach(value -> System.out.println(value));
		//		numbers.forEach(System.out::println);

		Test1 test1 = new Test1();
		//		test1.test1(numbers);
		//		test1.test2(numbers);
		//		test1.testjava7();
		//		test1.test3();
		//		test1.test4();
		//		test1.test5();
		//		test1.test6();
		//		test1.test7();
		test1.test8();
	}

	private void test8() {
		Map<Integer, Book> map = getBookMap();
		map.forEach((key, book) -> System.out.println("key = " + key + ", value=" + book));

		map.merge(2, new Book("pink", 99), (book1, book2) -> {
			Book merged = new Book(book1.color + "+" + book2.color, book1.weight + book2.weight);
			return merged;
		});

		System.out.println("----");
		map.forEach((key, book)->System.out.println("key = "+key+", value="+book));
		
		Book book = map.getOrDefault(2, new Book("white", 88));
		System.out.println(book);
		
		Book book2 = map.getOrDefault(12, new Book("white", 88));
		System.out.println(book2);
		
	}

	private void test7() {
		Map<Integer, Book> map = getBookMap();
		map.forEach((key, book) -> System.out.println("key = " + key + ", value=" + book));
		map.replace(2, new Book("black", 33));
		map.forEach((key, book) -> System.out.println("key = " + key + ", value=" + book));
	}

	private Map<Integer, Book> getBookMap() {
		Map<Integer, Book> map = new HashMap<>();
		map.put(1, new Book("red", 10));
		map.put(2, new Book("yellow", 20));
		map.put(3, new Book("red", 30));
		return map;
	}

	private void test6() {
		List<Book> books = getBooks();
		int sum = books.stream().filter(b -> b.color.equals("red")).mapToInt(b -> b.weight).sum();
		System.out.println("sum = " + sum);

		StringBuffer sb = new StringBuffer();
		books.forEach(b -> sb.append(b.color).append(", "));
		System.out.println(sb);

		System.out.println(books.size());
		books.removeIf(b -> b.weight > 20);
		System.out.println(books.size());

		books = getBooks();
		books.forEach(b -> System.out.println(b.weight + ","));
		books.sort((x, y) -> y.weight - x.weight);
		books.forEach(b -> System.out.println(b.weight + ","));
	}

	private List<Book> getBooks() {
		List<Book> list = new ArrayList<>();
		list.add(new Book("red", 10));
		list.add(new Book("yellow", 20));
		list.add(new Book("red", 30));
		return list;
	}

	class Book {

		public String color;
		public int weight;

		public Book(String color, int weight) {
			this.color = color;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return color + "," + weight;
		}
	}

	private void test5() {
		func(x -> parseInt(x) * 2);
		func(x -> parseInt(x) - 2);
		pred(x -> x % 2 == 0);
		consm(x -> System.out.println("**" + x + "**"));
		supp(() -> "444");
		bin((x, y) -> x + y);
		bin((x, y) -> x * y);
	}

	private void bin(BinaryOperator<Integer> bo) {
		System.out.println(bo.apply(3, 5));
	}

	private void supp(Supplier<String> s) {
		System.out.println(s.get());
	}

	private void consm(Consumer<String> c) {
		c.accept("AAA");
	}

	private void pred(Predicate<Integer> p) {
		System.out.println(p.test(3));
	}

	private void func(Function<String, Integer> f) {
		System.out.println(f.apply("3"));
	}

	private void test4() {
		Runnable r = () -> {
			System.out.println("running");
		};
		Thread th = new Thread(r);
		th.start();
	}

	private void test3() throws Exception {
		mustClosed(sb -> sb.append(" abc"));
		mustClosed(sb -> sb.append(" def"));
	}

	private void mustClosed(Consumer<StringBuffer> c) {
		StringBuffer sb = new StringBuffer("hello");
		c.accept(sb);
		System.out.println(sb);
	}

	private void testjava7() throws Exception {
		//		MyBufferedReader br = new MyBufferedReader("a\nb\nccc\nddd\nddd");
		//			String line;
		//			while((line = br.readLine())!=null){
		//				System.out.println("+++"+line);
		//			}
		try (MyBufferedReader br = new MyBufferedReader("a\nb\nccc\nddd\nddd")) {

			String line;
			while ((line = br.readLine()) != null) {
				System.out.println("+++" + line);
			}
		}
		Thread.sleep(5000);
		System.out.println("FFFFFFFFF");
	}

	class MyBufferedReader extends BufferedReader {

		public MyBufferedReader(String string) {
			this(new StringReader(string));
		}

		public MyBufferedReader(StringReader stringReader) {
			super(stringReader);
		}

		@Override
		public void close() throws IOException {
			super.close();
			System.out.println("CLOSED");
		}

	}

	public static boolean isEven(Integer i) {
		System.out.println("isEven " + i);
		return i % 2 == 0;
	}

	public static Integer doubleIt(Integer i) {
		return i * 2;
	}

	public static boolean isGreaterThan5(Integer i) {
		return i > 5;
	}

	private void test2(List<Integer> numbers) {
		System.out.println(numbers.stream().filter(Test1::isEven).map(Test1::doubleIt).filter(Test1::isGreaterThan5).findFirst().get());
	}

	private void test1(List<Integer> numbers) {
		System.out.println(sumAll(numbers, n -> true));
		System.out.println(sumAll(numbers, n -> n < 3));
	}

	private int sumAll(List<Integer> numbers, Predicate<Integer> p) {
		int total = 0;
		for (int num : numbers) {
			if (p.test(num))
				total += num;
		}
		return total;
	}
}
