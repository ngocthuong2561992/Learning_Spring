package com.springaop.algorithm.java8;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Java8Demo {
	public static void main(String[] args) {
		List<Student> students = Arrays.asList(
								new Student(1, "student a", 25),
								new Student(2, "student b", 27),
								new Student(3, "student ac", 23),
								new Student(4, "student d", 24));
		
		students.sort((o1, o2) -> o1.getAge() - o2.getAge());
		students.forEach(System.out::println);
		
		List<String> names = students.stream()
									.filter(s -> s.getAge() > 24)
									.map(Student::getName)
									.map(String::toUpperCase)
									.collect(Collectors.toList());
		
		Student student = students.stream()
								.filter(s -> s.getName().contains("student a") && s.getAge() < 25)
								.findFirst()
								.orElse(null);
		
		Map<Integer, Student> maps = students.stream()
											.filter(s -> s.getAge() > 24)
											.collect(Collectors.toMap(s -> {return s.getId()*2;}, s -> s));
		maps.replaceAll((key, oldValue) -> {
			if(key == 2) return new Student(2, "xyz", 25);
			return oldValue; 
		});
		maps.forEach((Integer k, Student v) -> {
			System.out.println(k + ": " + v.getName() + ", " + v.getAge());
		});
		double sumAge = maps.entrySet().stream()
										.mapToInt(s -> s.getValue().getAge())
										.sum();
		System.out.println(sumAge);
		
		List<Student> newStudents = maps.entrySet()
										.stream()
										.map(s -> s.getValue())
										.collect(Collectors.toCollection(ArrayList::new));
		newStudents.forEach(System.out::println);
		
		String studentName = students.stream()
									.map(s -> {
										return Integer.toString(s.getAge());
									})
									.collect(Collectors.joining(","));
		System.out.println(studentName);
		DoubleSummaryStatistics averageAge = students.stream()
									.collect(Collectors.summarizingDouble(Student::getAge));
		System.out.println(averageAge);
		
		Optional<Student> max = students.stream()
							.collect(Collectors.maxBy(Comparator.comparing(Student::getAge)));
		Student obj = max.get();
		System.out.println(max.get());
		
		Optional<String> opt = Optional.ofNullable("a b c");
		opt.ifPresent(name -> System.out.println(name.length()));
		
		Optional<String> nameStr = maps.entrySet().stream()
				.filter(s -> s.getValue().getAge() == 23)
				.map(s -> s.getValue())
				.map(Student::getName)
				.findFirst();
//		System.out.println(nameStr.get());
		
	}
}
