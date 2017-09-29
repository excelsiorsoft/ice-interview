package com.excelsiorsoft.anagrams;



import java.io.StringReader;

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

}
