package com.excelsiorsoft.findingsum;

import static com.excelsiorsoft.findingsum.DistinctPairsFinder.findSummingPairsLookAhead;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Random;

import org.junit.Ignore;
import org.junit.Test;

import com.excelsiorsoft.findingsum.DistinctPairsFinder.Pair;
import com.google.common.base.Stopwatch;

public class DistinctPairsFinderLookAheadTest {

	@Test public void testPairs() {
		Pair _34 = new Pair(3,4);
		Pair _43 = new Pair(4,3);
		assertTrue(_34.equals(_43));
		for(int i=0;i<20;i++) {
			assertTrue(_34.hashCode()==_43.hashCode());
		}
		
	}

	
	@Test
	public void testMirrorArray() {
		int[] array = {16, 9, 7, 1, -16, -9, -7, -1 };
		int sum = 0;
		assertThat(findSummingPairsLookAhead(array, sum))
			.hasSize(4)
			.contains(new Pair(-16, 16))
			.contains(new Pair(-9, 9))
			.contains(new Pair(-1, 1))
			.contains(new Pair(-7, 7));
	}
	
	@Test
	public void testSinglePresentPair() {
		int[] array = {16,9,7,1};
		int sum = 8;
		assertThat(findSummingPairsLookAhead(array, sum)).hasSize(1).contains(new Pair(1,7));
	}
	
	
	
	@Test
	public void testMissingPairs() {
		int[] array = {1,2,3,9};
		int sum = 8;
		assertThat(findSummingPairsLookAhead(array, sum)).hasSize(0);
	}
	
	
	@Test
	public void testMultiplePresentPair() {
		int[] array = { 1, 2, 3, 4, 5, 6 };
		int sum = 7;
		assertThat(findSummingPairsLookAhead(array, sum))
			.hasSize(3)
			.contains(new Pair(3, 4))
			.contains(new Pair(2, 5))
			.contains(new Pair(1, 6));
	}
	
	@Test
	public void testMultiplePresentPairDoubled() {
		int[] array = { 1, 2, 3, 4, 5, 6, 1, 2, 3, 4, 5, 6 };
		int sum = 7;
		assertThat(findSummingPairsLookAhead(array, sum))
			.hasSize(3)
			.contains(new Pair(3, 4))
			.contains(new Pair(2, 5))
			.contains(new Pair(1, 6));
	}
	
	
	@Test
	public void testLargerMultiplePresentPairGauss() {
		int[] array = {1,2,3,4,5,6,7,8,9,10};
		int sum = 11;
		assertThat(findSummingPairsLookAhead(array, sum)).
		hasSize(5)
		.contains(new Pair(5,6))
		.contains(new Pair(4,7))
		.contains(new Pair(3,8))
		.contains(new Pair(2,9))
		.contains(new Pair(1,10));
	}
	
	
	@Test
	public void testLargerOddMultiplePresentPairGauss() {
		int[] array = {1,2,3,4,5,6,7,8,9,10,11};
		shuffle(array);
		int sum = 12;
		assertThat(findSummingPairsLookAhead(array, sum))
		.hasSize(5)
		.contains(new Pair(5,7))
		.contains(new Pair(4,8))
		.contains(new Pair(3,9))
		.contains(new Pair(2,10))
		.contains(new Pair(1,11));
	}
	
	
	@Test
	public void testLargerOddMultiplePresentPairSumInsideTheRange() {
		int[] array = {1,2,3,4,5,6,7,8,9,10,11};
		shuffle(array);
		int sum = 7;
		assertThat(findSummingPairsLookAhead(array, sum))
		.hasSize(3)
		.contains(new Pair(3,4))
		.contains(new Pair(2,5))
		.contains(new Pair(1,6));
	}
	
	
	@Test
	public void testLargerOddMultiplePresentPairSumOutsideTheRange() {
		int[] array = {1,2,3,4,5,6,7,8,9,10,11};
		shuffle(array);
		int sum = 15;
		assertThat(findSummingPairsLookAhead(array, sum))
		.hasSize(4)
		.contains(new Pair(7,8))
		.contains(new Pair(6,9))
		.contains(new Pair(5,10))
		.contains(new Pair(4,11));
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
		assertThat(findSummingPairsLookAhead(array, sum))
		.hasSize(1)
		.contains(new Pair(10,11));
	}
	
	@Test
	public void testOneMoreThanMaxSumValue() {
		int[] array = {1,2,3,4,5,6,7,8,9,10,11};
		int sum = array[array.length-1]+array[array.length-2]+1;
		shuffle(array);
		//Throwable thrown = catchThrowable(() -> {
			assertThat(findSummingPairsLookAhead(array, sum)).hasSize(0);
		//});
		/*assertThat(thrown).isInstanceOf(IllegalArgumentException.class).hasNoCause()
				.hasStackTraceContaining("Sought for sum is too large.  Won't even look...");*/
	}
	
	
	@Test
	public void testMinSumValue() {
		int[] array = {1,2,3,4,5,6,7,8,9,10,11};
		int sum = array[0]+array[1];
		shuffle(array);
		assertThat(findSummingPairsLookAhead(array, sum))
		.hasSize(1)
		.contains(new Pair(1,2));
	}
	
	@Test
	public void testOneLessThanMinSumValue() {
		int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
		int sum = array[0] + array[1] - 1;
		shuffle(array);
		///Throwable thrown = catchThrowable(() -> {
			assertThat(findSummingPairsLookAhead(array, sum)).hasSize(0);
		//});
		/*assertThat(thrown).isInstanceOf(IllegalArgumentException.class).hasNoCause()
				.hasStackTraceContaining("Sought for sum is too small.  Won't even look...");*/
		
	}
	
	
	@Test
	public void testLargerEvenMultiplePresentPair() {
		int[] array = {1,2,3,4,5,6,7,8,9,10};
		shuffle(array);
		int sum = 11;
		assertThat(findSummingPairsLookAhead(array, sum))
		.hasSize(5)
		.contains(new Pair(5,6))
		.contains(new Pair(4,7))
		.contains(new Pair(3,8))
		.contains(new Pair(2,9))
		.contains(new Pair(1,10));
	}
	
	
	@Test
	public void testRandomCombination() {
		int[] array = new Random().ints(100, 0, 1000).toArray();
		int sum = 305;
		findSummingPairsLookAhead(array, sum);
	}
	
	@Test
	public void testRandomCombinationWithNegatives() {
		int[] array = new Random().ints(100, -1000, 1000).toArray();
		Arrays.stream(array).forEach(e -> System.out.print(e + " "));
		int sum = -305;
		findSummingPairsLookAhead(array, sum);
		
	}
	
	
	@Test
	@Ignore //resource intensive, better run on its own
	public void testLargeRandomCombination() {
		Random random = new Random();
		int[] array = random.ints(10_000_000, Integer.MIN_VALUE/2, Integer.MAX_VALUE/2).toArray();
		int[] sorted = array.clone();
		Arrays.sort(sorted);
		int first = sorted[0];
		int second = sorted[1];
		int nextToLast = sorted[sorted.length-2];
		int last = sorted[sorted.length-1];
		int min = first+second;
		int max = nextToLast+last;
		
		int sum = min + 
                (random.nextInt()*(max - min));
		
		//int sum = 18274;
		Stopwatch stopwatch = Stopwatch.createStarted();
		findSummingPairsLookAhead(array, sum);
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
