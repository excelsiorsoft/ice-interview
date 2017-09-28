/**
 * 
 */
package com.excelsiorsoft.findingsum;

import static java.util.Arrays.sort;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Simeon
 *
 */
public final class DistinctPairsFinder {
	
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
			result = prime * result + a;
			result = prime * result + b;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) return true;
			if (obj == null || getClass() != obj.getClass()) return false;
			
			Pair other = (Pair) obj;
			if (a != other.a) return false;
			if (b != other.b) return false;
			return true;
		}
		
		@Override
		public String toString() {
			return "Pair[a=" + a + ", b=" + b + "] ";
		}
	}
	
	public static void findSummingPairs(int[] data, int sum){
		int[] sorted = data.clone();
		sort(sorted);
		validate(sorted, sum);
		
		Set<Pair> collected = new HashSet<>();
		
		int lo = 0;
		int hi = sorted.length-1;
		
		while(lo != hi) {
		System.out.println("low="+lo +", hi="+hi+"; sorted[lo]+sorted[hi]="+(sorted[lo]+sorted[hi]));
		
		if(sorted[lo]+sorted[hi]>sum) {
			hi -= 1;
			//System.out.println("low="+lo +", hi="+hi);
		}else if(sorted[lo]+sorted[hi]<sum) {
			lo += 1;
			//System.out.println("low="+lo +", hi="+hi);
		}else if(sorted[lo]+sorted[hi]==sum) {
			collected.add(new Pair(sorted[lo], sorted[hi]));
			hi -= 1;
		}
		
		
		}
		
		if(collected.size()>0) {
			int size = collected.size();
			System.out.println("Found "+size+(size>1?" pairs":" pair"));
		collected.stream().forEach(System.out::print);
		}else {System.out.println("Couldn't find any pairs...  Try another set of inputs.");}
		//Arrays.stream(sorted).forEach(e -> System.out.print(e + " "));
		
}

	private static void validate(int[] sorted, int sum) {
		long min = 0;
		long max = 0;
		
		int first = sorted[0];
		int second = sorted[1];
		
		int last = sorted[sorted.length-1];
		int nextToLast = sorted[sorted.length-2];
		
		min = first+second;
		max = nextToLast+last;
		
		if(sum<min)throw new IllegalArgumentException("Sought for sum is too small.  Won't even look...");
		if(sum>max)throw new IllegalArgumentException("Sought for sum is too large.  Won't even look...");
		
	}

}
