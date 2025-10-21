/*
 * Steven Spier
 */

package utilities;

import java.util.Objects;

public class Quicksort {

    /**
     * Sorts the given data array in ascending order while keeping the urls array aligned.
     *
     * @param data the array of integers to sort
     * @param urls the array of urls associated with each integer
     */
    public static void quicksort(int[] data, String[] urls) {
        Objects.requireNonNull(data, "data must not be null");
        Objects.requireNonNull(urls, "urls must not be null");
        if (data.length != urls.length) {
            throw new IllegalArgumentException("data and urls must have the same length");
        }
        quicksort(data, urls, 0, data.length - 1);
    }

    private static void quicksort(int[] data, String[] urls, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(data, urls, left, right);
            quicksort(data, urls, left, pivotIndex - 1);
            quicksort(data, urls, pivotIndex + 1, right);
        }
    }

    private static int partition(int[] data, String[] urls, int left, int right) {
        // Median-of-three pivot selection
        int mid = left + (right - left) / 2;
        int pivot = medianOfThree(data, left, mid, right);

        int i = left - 1;
        int j = right + 1;
        while (true) {
            do { i++; } while (data[i] < pivot);
            do { j--; } while (data[j] > pivot);
            if (i >= j) {
                return j;
            }
            swap(data, urls, i, j);
        }
    }

    private static int medianOfThree(int[] data, int a, int b, int c) {
        int x = data[a], y = data[b], z = data[c];
        if ((x - y) * (z - x) >= 0) return x;
        if ((y - x) * (z - y) >= 0) return y;
        return z;
    }

    private static void swap(int[] data, String[] urls, int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;

        String tempUrl = urls[i];
        urls[i] = urls[j];
        urls[j] = tempUrl;
    }
}
