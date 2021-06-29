package dskatas;

import java.util.ArrayList;
import java.util.List;

public class MergeSort {

    /**
     * Iterative implementation of Merge Sort
     * @param toSort the unmodified List to sort
     * @param <T> type of List
     * @return the sorted List
     */
    public static <T extends Comparable<? super T>> List<T> sort(List<T> toSort) {
        List<T> toReturn = new ArrayList<T>(toSort);
        subSort(toReturn);
        return toReturn;
    }

    static <T extends Comparable<? super T>> void subSort(List<T> toSort) {
        int length = toSort.size();

        if(length < 2) {
            return;
        }

        int midPoint = length / 2;
        List<T> left = new ArrayList<>(toSort.subList(0, midPoint));
        List<T> right = new ArrayList<>(toSort.subList(midPoint, toSort.size()));

        subSort(left);
        subSort(right);
        merge(toSort, left, right);
    }

    static <T extends Comparable<? super T>> void merge(List<T> both, List<T> left, List<T> right) {
        int leftPointer = 0, rightPointer = 0, bothPointer = 0;
        while(leftPointer < left.size() && rightPointer < right.size()) {
            if(left.get(leftPointer).compareTo(right.get(rightPointer)) <= 0) {
                both.set(bothPointer++, left.get(leftPointer++));
            } else {
                both.set(bothPointer++, right.get(rightPointer++));
            }
        }

        //remainders
        while(leftPointer < left.size()) {
            both.set(bothPointer++, left.get(leftPointer++));
        }

        while(rightPointer < right.size()) {
            both.set(bothPointer++, right.get(rightPointer++));
        }
    }
}
