/*
 * Problem: Next Permutation
 * Platform: GeeksforGeeks / LeetCode
 * Difficulty: Medium
 *
 * Pattern: Greedy / Two Pointer
 *
 * Approach:
 * 1. Find the first index i from the right where arr[i] < arr[i + 1].
 * 2. Find the rightmost element greater than arr[i] and swap them.
 * 3. Reverse the suffix from i + 1 to the end.
 *
 * Why it works:
 * The suffix after the pivot is in non-increasing order.
 * Swapping the pivot with the smallest greater element and reversing
 * the suffix produces the smallest permutation greater than the current one.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * Edge Cases:
 * - Array is already in descending order → reverse the entire array.
 * - Array contains duplicate elements.
 * - Array has only one element.
 */

class Solution {
    void nextPermutation(int[] arr) {
        int n = arr.length;

        int i = n - 2;

        // Find the pivot.
        while (i >= 0 && arr[i] >= arr[i + 1]) {
            i--;
        }

        if (i >= 0) {
            // Find the rightmost element greater than the pivot.
            int j = n - 1;

            while (arr[j] <= arr[i]) {
                j--;
            }

            swap(arr, i, j);
        }

        // Reverse the suffix.
        reverse(arr, i + 1, n - 1);
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void reverse(int[] arr, int l, int r) {
        while (l <= r) {
            swap(arr, l, r);
            l++;
            r--;
        }
    }
}