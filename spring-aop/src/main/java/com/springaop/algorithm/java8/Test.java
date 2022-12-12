package com.springaop.algorithm.java8;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Test {
//	static Function<Integer, String> intToString = Object::toString;
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>(Arrays.asList((Integer)1, (Integer)9, (Integer)15, (Integer)14));
		Double a = sum(list);
		System.out.println(a);
		
		List<Student> students = new ArrayList<Student>(Arrays.asList(new Student(1, "a", 10), new Student(2, "b", 15), new Student(3, "c", 25)));
		for (ListIterator<Student> it = students.listIterator(); it.hasNext();) {
			if(it.next().getAge() == 15) {
//				it.remove();
			}
		}
		LocalDateTime date = LocalDateTime.now();
//		System.out.println(date.format(DateTimeFormatter.ISO_DATE_TIME));
		
		Function<Integer, String> intToString = Object::toString;
		Function<String, String> quote = s -> "'" + s + "'";
		Function<Integer, String> quoteIT = quote.compose(intToString);
		List<String> strings = list.stream()
									.map(i -> quoteIT.apply(i))
									.collect(Collectors.toList());
		System.out.println(strings);
	}
	
	public static Double sum(List<? extends Number> list) {
//		DoubleSummaryStatistics sumStatistic = list.stream()
//												.collect(Collectors.summarizingDouble(s -> s.doubleValue()));
//		return sumStatistic.getSum();
//		OptionalDouble result = list.stream()
//					.mapToDouble(i -> i.doubleValue())
//					.max();
//		return result.isPresent() ? result.getAsDouble() : 0;
		return list.stream()
					.mapToDouble(i -> i.doubleValue())
					.sum();
	}
	
}
