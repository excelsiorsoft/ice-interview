/**
 * 
 */
package com.excelsiorsoft.findingsum;

import static java.util.Arrays.sort;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Simeon
 *
 */
public final class DistinctPairsFinder {
	
	 /**
	  * This class encapsulate the resulting tuple
	  * 
	 * @author Simeon
	 *
	 */
	final static class Pair{

		int a;
		int b;
		
		Pair(int a, int b){
			this.a = a;
			this.b = b;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + a*b;
			//result = prime * result + b;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) return true;
			if (obj == null || getClass() != obj.getClass()) return false;
			
			Pair other = (Pair) obj;
			/*if (a != other.a) return false;
			if (b != other.b) return false;
			return true;*/
			if ((a == other.a) && (b == other.b)) {return true;}
			else if ((a == other.b) && (b == other.a)) {return true;}
			else {return false;}
			
		}
		
		@Override
		public String toString() {
			return "Pair[a=" + a + ", b=" + b + "] ";
		}
	}
	
	/**
	 * The workhorse method, O(NlogN) due to sorting
	 * 
	 * @param data an array of integers
	 * @param sum a sought for sum
	 */
	public static Set<Pair> findSummingPairs(int[] data, int sum){
		
		int[] sorted = validate(data, sum);
		
		Set<Pair> collected = new HashSet<>();
		
		int lo = 0;
		int hi = sorted.length-1;
		
		while (lo != hi) {
			//System.out.println("low=" + lo + ", hi=" + hi + "; sorted[lo]+sorted[hi]=" + (sorted[lo] + sorted[hi]));

			if (sorted[lo] + sorted[hi] > sum) {
				hi -= 1;
			} else if (sorted[lo] + sorted[hi] < sum) {
				lo += 1;
			} else if (sorted[lo] + sorted[hi] == sum) {
				collected.add(new Pair(sorted[lo], sorted[hi]));
				hi -= 1;
			}

		}
		
		if(collected.size()>0) {
			int size = collected.size();
			System.out.println("Found "+size+(size>1?" pairs":" pair"));
			//collected.stream().forEach(System.out::print);
		}else {System.out.println("Couldn't find any pairs...  Try another set of inputs.");}
		//Arrays.stream(sorted).forEach(e -> System.out.print(e + " "));
		return collected;
		
}

	private static int[] validate(int[] data, int sum) {
		if(data == null) throw new IllegalArgumentException("Data array is not provided - nothing to search against.  Exiting...");
		//if(sum == 0) throw new IllegalArgumentException("Requested sum value is not provided - incomplete data to start a search.  Exiting...");
		
		int[] sorted = data.clone();
		sort(sorted);
		
		long min = 0;
		long max = 0;
		
		int first = sorted[0];
		int second = sorted[1];
		
		int last = sorted[sorted.length-1];
		int nextToLast = sorted[sorted.length-2];
		
		min = first+second;
		max = nextToLast+last;
		
		if(sum < min)throw new IllegalArgumentException("Sought for sum is too small.  Won't even look...");
		if(sum > max)throw new IllegalArgumentException("Sought for sum is too large.  Won't even look...");
		
		return sorted;
	}
	
	/**
	 * Faster workhorse, O(N) via elimination of sorting at the expense of extra space 
	 * 
	 * @param data
	 * @param sum
	 * @return
	 */
	public static Set<Pair> findSummingPairsLookAhead(int[] data, int sum){
		Set<Pair> collected = new HashSet<>();
		Set<Integer> lookaheads = new HashSet<>();
		
		
		for(int i = 0; i < data.length; i++) {
			int elem = data[i];
			if(lookaheads.contains(elem)) {
				collected.add(new Pair(elem, sum - elem));
			}
			lookaheads.add(sum - elem);
		}
		
		if(collected.size()>0) {
			int size = collected.size();
			System.out.println("Found "+size+(size>1?" pairs":" pair"));
			collected.stream().forEach(System.out::print);
		}else {System.out.println("Couldn't find any pairs...  Try another set of inputs.");}
		return collected;
	}

}
