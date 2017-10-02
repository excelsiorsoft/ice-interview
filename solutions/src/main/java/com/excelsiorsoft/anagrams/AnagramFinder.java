/**
 * 
 */
package com.excelsiorsoft.anagrams;

import java.io.BufferedReader;
import java.io.Reader;
import java.io.StringReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Simeon
 *
 */
public class AnagramFinder {
	
	static class Word{
		
		/*@Override
		public String toString() {
			return "Word [index=" + index + ", contents=" + contents + "]";
		}*/
		
		@Override
		public String toString() {
			return this.contents;
		}

		private BigInteger index;
		private String contents;
		
		
		public Word(String contents) {
			this.contents = contents;
			this.index = calculateIndex(capitalize(contents));
		}
		
		public BigInteger getIndex() {
			return index;
		}
		
		/*public void setIndex(BigInteger index) {
			this.index = index;
		}*/
		public String getContents() {
			return contents;
		}
		/*public void setContents(String contents) {
			this.contents = contents;
		}*/
		
		private char[] capitalize(String word) {
			return word.toUpperCase().toCharArray();
		}
		
		private BigInteger calculateIndex(char[] letters) {
			
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
		    	//System.out.println(c+"="+(int)c);
		        if (c < OFFSET) {
		            return new BigInteger("-1");
		        }
		        int pos = /*(int)*/c - OFFSET;
		        result = result.multiply(bigPrimes[pos]);
		    }
		    return result;
		}
		
		
	}

	public static List<Set<String>> findAsList(Reader text) {

		Map<String, Set<String>> preliminaries = new BufferedReader(text).lines()
				.flatMap(Pattern.compile("\\W+")::splitAsStream).distinct().collect(Collectors.groupingBy(
						s -> s.codePoints().sorted().collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString()
						/*s -> Stream.of(s.split("")).sorted().collect(Collectors.joining())*/, Collectors.toSet()));

		return preliminaries.values().stream().filter(list -> list.size() > 1).collect(Collectors.toList());
	}
	
	public static Map<String, Set<String>> findAsMap(Reader text) {

		Map<String, List<String>> preliminaries = new BufferedReader(text).lines()
				.flatMap(Pattern.compile("\\W+")::splitAsStream).distinct()
				.collect(Collectors.groupingBy(/*s -> Stream.of(s.split("")).sorted().collect(Collectors.joining())*/
						s -> s.codePoints().sorted().collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString()));

		return preliminaries.values().stream().filter(list -> list.size() > 1)
				.collect(Collectors.toMap(list -> list.get(0), list -> new TreeSet<>(list.subList(1, list.size()))));
	}
	
	public static void  find(Reader text) {
		Stream<Word> words = new BufferedReader(text).lines()
		.flatMap(Pattern.compile("\\W+")::splitAsStream).distinct().map(w -> new Word(w));
		
		 words.collect(Collectors.groupingBy(Word::getIndex))
		.values().stream().filter(list -> list.size() > 1).forEach(System.out::println);;
		
		
		//.forEach(System.out::print);;
		
	}

}
