package jdk8exam;

public interface MyIf {

	void sayHello();

	default void sayDefaultHello(){
		System.out.println("hello"+this.getClass());
	}

}
