package jdk8exam;

import java.util.OptionalInt;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public class Test2 {

	public static void main(String[] args) throws Exception {
		Test2 t = new Test2();
//		t.test1();
//		t.test2();
		t.test3();
	}

	private void test3() {
		OptionalInt x = IntStream.range(1, 11)
//		.reduce(new IntBinaryOperator() {
//			
//			@Override
//			public int applyAsInt(int left, int right) {
//				System.out.println("left = " + left + ", right = " + right);
//				return left + right;
//			}
//		});
//			.reduce((l, r) -> l + r);
		.reduce((l, r) -> {
			System.out.println("l = " + l + ", r = " + r);
			return l + r;
		});
		System.out.println("sum = " + x.getAsInt());
	}

	private void test2() {
		int sum = IntStream.range(1,  100)
			.filter(n->n%2==0)
			.map(n->n*2)
			.skip(10)
			.limit(10)
			.reduce(0, Integer::sum)
		;
		System.out.println("sum = "+sum);
	}

	private void test1() {
		IntStream i = IntStream.range(1, 10);
//		i.forEach(new IntConsumer() {
//
//			@Override
//			public void accept(int value) {
//				System.out.println(value);
//			}
//		});
		i.forEach(n -> {System.out.println(n);});
	}
}
