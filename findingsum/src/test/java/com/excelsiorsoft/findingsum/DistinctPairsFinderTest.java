package com.excelsiorsoft.findingsum;

import static com.excelsiorsoft.findingsum.DistinctPairsFinder.findSummingPairs;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

import com.google.common.base.Stopwatch;

public class DistinctPairsFinderTest {

	
	@Test
	public void testSinglePresentPair() {
		int[] array = {16,9,7,1};
		int sum = 8;
		findSummingPairs(array, sum);
	}
	
	
	
	@Test
	public void testMissingPairs() {
		int[] array = {1,2,3,9};
		int sum = 8;
		findSummingPairs(array, sum);
	}
	
	
	@Test
	public void testMultiplePresentPair() {
		int[] array = {1,2,3,4,5,6};
		int sum = 7;
		findSummingPairs(array, sum);
	}
	
	
	@Test
	public void testLargerMultiplePresentPairGauss() {
		int[] array = {1,2,3,4,5,6,7,8,9,10};
		int sum = 11;
		findSummingPairs(array, sum);
	}
	
	
	@Test
	public void testLargerOddMultiplePresentPairGauss() {
		int[] array = {1,2,3,4,5,6,7,8,9,10,11};
		shuffle(array);
		int sum = 12;
		findSummingPairs(array, sum);
	}
	
	
	@Test
	public void testLargerOddMultiplePresentPairSumInsideTheRange() {
		int[] array = {1,2,3,4,5,6,7,8,9,10,11};
		shuffle(array);
		int sum = 7;
		findSummingPairs(array, sum);
	}
	
	
	@Test
	public void testLargerOddMultiplePresentPairSumOutsideTheRange() {
		int[] array = {1,2,3,4,5,6,7,8,9,10,11};
		shuffle(array);
		int sum = 15;
		findSummingPairs(array, sum);
	}
	
	
	/*@Test
	public void testLargerOddMultiplePresentPairSumFarOutsideTheRange() {
		int[] array = {1,2,3,4,5,6,7,8,9,10,11};
		shuffle(array);
		int sum = 22;
		findSummingPairs(array, sum);
	}*/
	
	
	@Test
	public void testMaxSumValue() {
		int[] array = {1,2,3,4,5,6,7,8,9,10,11};
		int sum = array[array.length-1]+array[array.length-2];
		shuffle(array);
		findSummingPairs(array, sum);
	}
	
	@Test
	public void testOneMoreThanMaxSumValue() {
		int[] array = {1,2,3,4,5,6,7,8,9,10,11};
		int sum = array[array.length-1]+array[array.length-2]+1;
		shuffle(array);
		Throwable thrown = catchThrowable(() -> {
			findSummingPairs(array, sum);
		});
		assertThat(thrown).isInstanceOf(IllegalArgumentException.class).hasNoCause()
				.hasStackTraceContaining("Sought for sum is too large.  Won't even look...");
	}
	
	
	@Test
	public void testMinSumValue() {
		int[] array = {1,2,3,4,5,6,7,8,9,10,11};
		int sum = array[0]+array[1];
		shuffle(array);
		findSummingPairs(array, sum);
	}
	
	@Test
	public void testOneLessThanMinSumValue() {
		int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
		int sum = array[0] + array[1] - 1;
		shuffle(array);
		Throwable thrown = catchThrowable(() -> {
			findSummingPairs(array, sum);
		});
		assertThat(thrown).isInstanceOf(IllegalArgumentException.class).hasNoCause()
				.hasStackTraceContaining("Sought for sum is too small.  Won't even look...");
		
	}
	
	
	@Test
	public void testLargerEvenMultiplePresentPair() {
		int[] array = {1,2,3,4,5,6,7,8,9,10};
		shuffle(array);
		int sum = 11;
		findSummingPairs(array, sum);
	}
	
	
	@Test
	public void testRandomCombination() {
		int[] array = new Random().ints(100, 0, 1000).toArray();
		//shuffle(array);
		int sum = 305;
		findSummingPairs(array, sum);
	}
	
	@Test
	public void testRandomCombinationWithNegatives() {
		int[] array = new Random().ints(100, -1000, 1000).toArray();
		Arrays.stream(array).forEach(e -> System.out.print(e + " "));
		//shuffle(array);
		int sum = -305;
		findSummingPairs(array, sum);
		
	}
	
	
	@Test
	public void testLargeRandomCombination() {
		Random random = new Random();
		int[] array = random.ints(10_000_000, Integer.MIN_VALUE, Integer.MAX_VALUE).toArray();
		int[] sorted = array.clone();
		Arrays.sort(sorted);
		long first = sorted[0];
		long second = sorted[1];
		long nextToLast = sorted[sorted.length-2];
		long last = sorted[sorted.length-1];
		long min = first+second;
		long max = nextToLast+last;
		
		long sum = min + 
                (long)(random.nextDouble()*(max - min));
		
		//int sum = 18274;
		Stopwatch stopwatch = Stopwatch.createStarted();
		findSummingPairs(array, (int)sum);
		System.out.println("\nRandom expected sum="+sum);
		System.out.println("\nTook "+stopwatch.elapsed().getSeconds()+" seconds");

	}
	
	
	/**
	 * Simple array shuffle implementation
	 * 
	 *  @see <a href="https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle#The_modern_algorithm">https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle#The_modern_algorithm</a>
	 *  @param array
	 */
	private static void shuffle(int[] array) {
	    Random rnd = new Random();
	    for (int i = array.length - 1; i > 0; i--)
	    {
	      int index = rnd.nextInt(i + 1);
	      //swap
	      int a = array[index];
	      array[index] = array[i];
	      array[i] = a;
	    }
	  }
	

}
