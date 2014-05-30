package jdk8exam;

import java.util.Comparator;

public class MyIfImpl implements MyIf {

	@Override
	public void sayHello() {
		System.out.println(this.getClass());
	}

}
