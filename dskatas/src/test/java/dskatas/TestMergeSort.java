package dskatas;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

public class TestMergeSort {

    //technically, the List implementation may override equals and break the testing semantics of equality here
    //these tests generally assume that a traditional equality of List implementation exists

    @Test
    public void integerListIsSorted() {
        List<Integer> unsorted = Arrays.asList(5,3,1,2,4,9);
        List<Integer> sorted = MergeSort.sort(unsorted);
        assertEquals(Arrays.asList(1,2,3,4,5,9), sorted);
    }

    @Test
    public void stringListIsSorted() {
        List<String> unsorted = Arrays.asList("beta", "alpha", "gamma", "kappa", "1");
        List<String> sorted = MergeSort.sort(unsorted);
        assertEquals(Arrays.asList("1", "alpha", "beta", "gamma", "kappa"), sorted);
    }

    @Test
    public void stringListIsSortedByLength() {
        assertEquals(Arrays.asList("a", "aa", "aaa"), MergeSort.sort(Arrays.asList("aa", "a", "aaa")));
    }

    @Test
    public void testBigListAgainstCollections() {
        int bigNumber = 1_000_000;  //make me bigger after off the stack
        List<Integer> bigList = new ArrayList<>(bigNumber);
        Random random = new Random();
        for(int i = 0; i < bigNumber; i++) {
            bigList.add(random.nextInt());
        }

        List<Integer> collectionsSorted = new ArrayList<>(bigList);
        Collections.sort(collectionsSorted);

        List<Integer> mergeSorted = MergeSort.sort(bigList);
        assertEquals(collectionsSorted, mergeSorted);
    }

}
