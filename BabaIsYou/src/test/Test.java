package test;

import java.util.ArrayList;

public class Test {
	public static void main(String[] args) {
		
		ArrayList<Integer[]> a = new ArrayList<>();
		
		Integer[] b = {1,2};
		a.add(b);
		System.out.println(a.get(0)[1]);
		
	}

}
