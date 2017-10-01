/**
 * 
 */
package com.excelsiorsoft.anagrams;

import java.io.BufferedReader;
import java.io.Reader;
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
	
	public void find(Reader text) {
		
		
	}

}
