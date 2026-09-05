/*
 * Problem: Longest Subsequence with Adjacent Difference as 1
 * Platform: GeeksforGeeks
 * Difficulty: Medium
 *
 * Pattern: Dynamic Programming
 *
 * Approach:
 * 1. Recursion:
 *    For each element, recursively find the longest valid subsequence
 *    starting from that element by choosing the next element whose
 *    absolute difference is 1.
 *
 * 2. Memoization:
 *    Store the result for each index to avoid recomputing the same
 *    recursive states.
 *
 * 3. Tabulation:
 *    Maintain dp[x] as the longest valid subsequence ending with value x.
 *    For each x, extend the best subsequence ending at x - 1 or x + 1.
 *
 * Why it works:
 * A valid adjacent element must differ from the current element by exactly 1.
 * Therefore, a subsequence can be extended only from values x - 1 or x + 1.
 *
 * Time Complexity:
 * Recursion: O(2^n)
 * Memoization: O(n^2)
 * Tabulation: O(n)
 *
 * Space Complexity:
 * Recursion: O(n)
 * Memoization: O(n)
 * Tabulation: O(maxValue)
 *
 * Edge Cases:
 * - Array contains only one element.
 * - Multiple occurrences of the same value.
 * - No adjacent values differ by 1.
 */

class Solution {

    // Recursion
    private int recursion(int[] arr, int index) {

        int n = arr.length;
        int ans = 1;

        for (int i = index + 1; i < n; i++) {

            if (Math.abs(arr[i] - arr[index]) == 1) {
                ans = Math.max(
                    ans,
                    1 + recursion(arr, i)
                );
            }
        }

        return ans;
    }

    // Memoization
    private int memoization(int[] arr, int index, int[] dp) {

        if (dp[index] != 0) {
            return dp[index];
        }

        int ans = 1;

        for (int i = index + 1; i < arr.length; i++) {

            if (Math.abs(arr[i] - arr[index]) == 1) {
                ans = Math.max(
                    ans,
                    1 + memoization(arr, i, dp)
                );
            }
        }

        return dp[index] = ans;
    }

    public int longestSubseq(int[] arr) {

        int n = arr.length;

        if (n == 0) {
            return 0;
        }

        /*
         * Recursion:
         *
         * int ans = 0;
         * for (int i = 0; i < n; i++) {
         *     ans = Math.max(ans, recursion(arr, i));
         * }
         *
         * return ans;
         */

        /*
         * Memoization:
         *
         * int[] dp = new int[n];
         * int ans = 0;
         *
         * for (int i = 0; i < n; i++) {
         *     ans = Math.max(
         *         ans,
         *         memoization(arr, i, dp)
         *     );
         * }
         *
         * return ans;
         */

        // Tabulation / Optimized DP
        int maxVal = 1000000;
        int[] dp = new int[maxVal + 2];

        int ans = 0;

        for (int x : arr) {

            dp[x] = Math.max(
                dp[x],
                Math.max(dp[x - 1], dp[x + 1]) + 1
            );

            ans = Math.max(ans, dp[x]);
        }

        return ans;
    }
}