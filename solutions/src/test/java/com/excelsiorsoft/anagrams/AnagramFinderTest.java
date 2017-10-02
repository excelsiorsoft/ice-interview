package com.excelsiorsoft.anagrams;



import static org.assertj.core.api.Assertions.assertThat;

import java.io.BufferedReader;
import java.io.StringReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import com.excelsiorsoft.anagrams.AnagramFinder.Word;

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
		String text = "abc cab tat aaa orchestra\natt tat bbb\ntta\ncabr\nrbac cab crab cabrc cabr carthorse";
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
		String text = "salt last one orchestra  eon plod barc \ncrab carthorse";
		
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
	
	@Test
	public void test8() {
		
		System.out.println( calculateProduct(capitalize("Pneumonoultramicroscopicsilicovolcanoconiosis")) );
	}
	
	@Test public void test9() {
		
		assertThat(calculateProduct(capitalize("carthorse"))).isEqualTo(calculateProduct(capitalize("orchestra")));
		
	}
	
	
	private char[] capitalize(String word) {
		return word.toUpperCase().toCharArray();
	}
	
	private BigInteger calculateProduct(char[] letters) {
		
		/*int[] primes = new int[] { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31,
		        37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103,
		        107, 109, 113 };*/
		
		int OFFSET = 65;
		
		BigInteger[] bigPrimes = Arrays.stream(
				new int[] { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31,
		        37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89,
		        97, 101, 103, 107, 109, 113 })
                .mapToObj(BigInteger::valueOf)
                .toArray(BigInteger[]::new);
		
		
	    BigInteger result = BigInteger.ONE;
	    for (char c : letters) {
	    	System.out.println(c+"="+(int)c);
	        if (c < OFFSET) {
	            return new BigInteger("-1");
	        }
	        int pos = /*(int)*/c - OFFSET;
	        result = result.multiply(bigPrimes[pos]);
	    }
	    return result;
	}
	
	@Test
	public void test10() {
		//String text = "salt last one orchestra  eon plod barc \ncrab carthorse";
		
		String text = "abc cab tat aaa orchestra\natt tat bbb\ntta\ncabr\nrbac cab crab cabrc cabr carthorse";
		/*Map<BigInteger, List<Word>>  result = AnagramFinder.find(new StringReader(text));
		result.entrySet().stream().forEach(System.out::println);*/
		
		AnagramFinder.find(new StringReader(text));
		System.out.println("----------------------");
	}
	
}
