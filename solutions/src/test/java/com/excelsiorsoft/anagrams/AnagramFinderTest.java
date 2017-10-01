package com.excelsiorsoft.anagrams;



import java.io.BufferedReader;
import java.io.StringReader;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class AnagramFinderTest {
	
	@Test
	public void test1() {
		
		String text = "eat, tea, tan, ate, nat, bat, eat";
		AnagramFinder.findAsMap(new StringReader(text)).entrySet().forEach(System.out::println);
		System.out.println("-------------------");
		AnagramFinder.findAsList(new StringReader(text)).stream().forEach(System.out::println);
		
		
	}
	
	@Test
	public void test2() {
		String text = "abc cab tat aaa\natt tat bbb\ntta\ncabr\nrbac cab crab cabrc cabr";
		AnagramFinder.findAsList(new StringReader(text)).stream().forEach(System.out::println);
	}
	
	@Test
	public void test3() {
		String text = "cat, dog, tac, god,   act, gdo";
		AnagramFinder.findAsList(new StringReader(text)).stream().forEach(System.out::println);
		System.out.println("-------------------");
		AnagramFinder.findAsMap(new StringReader(text)).entrySet().forEach(System.out::println);
		
	}

	@Test
	public void test4() {
		String text = "“apt”, “tap” and “pat”";
		AnagramFinder.findAsList(new StringReader(text)).stream().forEach(System.out::println);
		System.out.println("-------------------");
		AnagramFinder.findAsMap(new StringReader(text)).entrySet().forEach(System.out::println);
		
	}
	
	@Test
	public void test5() {
		String text = "\"salt\" \"last\" \"one\" \"eon\" \"plod\"";
		AnagramFinder.findAsList(new StringReader(text)).stream().forEach(System.out::println);
		System.out.println("-------------------");
		AnagramFinder.findAsMap(new StringReader(text)).entrySet().forEach(System.out::println);
		
	}
	
	@Test
	public void test6() {
		String text = "salt last one eon plod barc \ncrab";
		
		new BufferedReader(new StringReader(text)).lines()
		.flatMap(Pattern.compile("\\W+")::splitAsStream).distinct()
		.forEach(System.out::println);
		
		System.out.println("----------------------");
		
		new BufferedReader(new StringReader(text)).lines()
		.flatMap(Pattern.compile("\\W+")::splitAsStream).distinct()
		.collect(Collectors.groupingBy(s  -> Stream.of(s.split(""))))
		.values().stream().forEach(System.out::println);
		
		System.out.println("----------------------");
		
		new BufferedReader(new StringReader(text)).lines()
		.flatMap(Pattern.compile("\\W+")::splitAsStream).distinct()
		.collect(Collectors.groupingBy(s -> Stream.of(s.split("")).sorted().collect(Collectors.joining())))
		.values().stream().forEach(System.out::println);
		
	}
	
	@Test
	public void test7() {
		
		System.out.println(Stream.of("salt".split("")).sorted().collect(Collectors.joining()));
		
		//forEach(System.out::print);
		
		System.out.println("salt".codePoints().sorted().collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString());
	}
	
	
	
	
	
}
