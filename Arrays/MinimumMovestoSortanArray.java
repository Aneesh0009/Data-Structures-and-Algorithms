/*
 * Problem: Minimum Moves to Sort an Array
 * Platform: GeeksforGeeks
 * Difficulty: Medium
 *
 * Approach:
 * Find the longest consecutive sequence of elements in sorted order
 * that already appears in the correct order in the original array.
 * Move all remaining elements to the beginning or end.
 *
 * Time Complexity: O(n log n)
 * Space Complexity: O(n)
 */

import java.util.Arrays;
class Solution {

    public int minMoves(int[] arr) {
        int n = arr.length;

        int[] sorted = arr.clone();
        Arrays.sort(sorted);

        int[] pos = new int[n + 1];

        for (int i = 0; i < n; i++) {
            pos[arr[i]] = i;
        }

        int longest = 1;
        int current = 1;

        for (int i = 1; i < n; i++) {
            if (pos[sorted[i - 1]] < pos[sorted[i]]) {
                current++;
            } else {
                current = 1;
            }

            longest = Math.max(longest, current);
        }

        return n - longest;
    }
}