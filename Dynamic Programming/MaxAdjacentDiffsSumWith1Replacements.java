/*
 * Problem: Max Adjacent Diffs Sum with 1 Replacements
 * Platform: GeeksforGeeks
 * Difficulty: Medium
 *
 * Pattern: Dynamic Programming - State Optimization
 *
 * Problem:
 * Given an integer array arr[], we are allowed to replace any number
 * of elements with 1. Find the maximum possible sum of absolute
 * differences between consecutive elements after the replacements.
 *
 * Example:
 * Input:  [3, 2, 1, 4, 5]
 * Output: 8
 *
 *
 * ============================================================
 * 1. RECURSION
 * ============================================================
 *
 * At every index, there are two choices:
 *
 * 1. Keep arr[i]
 * 2. Replace arr[i] with 1
 *
 * The contribution of the current element depends on the value
 * selected for the previous element.
 *
 * Therefore, define:
 *
 * solve(i, prevState)
 *
 * where:
 *
 * i = current index
 *
 * prevState:
 * 0 -> previous element was replaced with 1
 * 1 -> previous element was kept as arr[i - 1]
 *
 * For the current element, we have two choices.
 *
 * Choice 1: Replace arr[i] with 1
 *
 * If the previous element was replaced:
 *     contribution = |1 - 1| = 0
 *
 * If the previous element was kept:
 *     contribution = |arr[i - 1] - 1|
 *
 * Choice 2: Keep arr[i]
 *
 * If the previous element was replaced:
 *     contribution = |1 - arr[i]|
 *
 * If the previous element was kept:
 *     contribution = |arr[i - 1] - arr[i]|
 *
 * The maximum of these choices is taken.
 *
 * Base Case:
 * When i == n, there are no more adjacent pairs.
 * Therefore, return 0.
 *
 * Time Complexity: O(2^n)
 * Space Complexity: O(n)
 *
 *
 * ============================================================
 * 2. RECURSIVE SOLUTION
 * ============================================================
 */

class Solution {

    int[] arr;
    int n;

    int solve(int i, int prevState) {

        if (i == n) {
            return 0;
        }

        int prev = (prevState == 0) ? 1 : arr[i - 1];

        int replace = Math.abs(prev - 1)
                    + solve(i + 1, 0);

        int keep = Math.abs(prev - arr[i])
                  + solve(i + 1, 1);

        return Math.max(replace, keep);
    }

    public int maxSum(int[] arr) {

        this.arr = arr;
        this.n = arr.length;

        if (n <= 1) {
            return 0;
        }

        return solve(1, 1);
    }
}


/*
 * ============================================================
 * 3. MEMOIZATION
 * ============================================================
 *
 * The recursive solution contains overlapping subproblems.
 *
 * The same state:
 *
 *     solve(i, prevState)
 *
 * can be reached through different sequences of choices.
 *
 * Therefore, store the result of every state in:
 *
 *     dp[i][prevState]
 *
 * State meaning:
 *
 * dp[i][0] = maximum sum from index i onward when the previous
 *            element was replaced with 1.
 *
 * dp[i][1] = maximum sum from index i onward when the previous
 *            element was kept as arr[i - 1].
 *
 * There are only:
 *
 *     n * 2
 *
 * possible states.
 *
 * Each state performs constant work.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 *
 * Space is O(n) because of the memoization table and recursion
 * stack.
 */


/*
 * Memoization Solution
 *
 * class Solution {
 *
 *     int[] arr;
 *     int n;
 *     int[][] dp;
 *
 *     int solve(int i, int prevState) {
 *
 *         if (i == n) {
 *             return 0;
 *         }
 *
 *         if (dp[i][prevState] != -1) {
 *             return dp[i][prevState];
 *         }
 *
 *         int prev = (prevState == 0) ? 1 : arr[i - 1];
 *
 *         int replace = Math.abs(prev - 1)
 *                     + solve(i + 1, 0);
 *
 *         int keep = Math.abs(prev - arr[i])
 *                  + solve(i + 1, 1);
 *
 *         return dp[i][prevState] =
 *                 Math.max(replace, keep);
 *     }
 *
 *     public int maxSum(int[] arr) {
 *
 *         this.arr = arr;
 *         this.n = arr.length;
 *
 *         if (n <= 1) {
 *             return 0;
 *         }
 *
 *         dp = new int[n][2];
 *
 *         for (int i = 0; i < n; i++) {
 *             dp[i][0] = -1;
 *             dp[i][1] = -1;
 *         }
 *
 *         return solve(1, 1);
 *     }
 * }
 */


/*
 * ============================================================
 * 4. BOTTOM-UP DP
 * ============================================================
 *
 * The memoized solution can be converted into iterative DP.
 *
 * We only need the states of the previous element.
 *
 * dp0:
 * Previous element was replaced with 1.
 *
 * dp1:
 * Previous element was kept as arr[i - 1].
 *
 *
 * ------------------------------------------------------------
 * Transition 1: Replace arr[i]
 * ------------------------------------------------------------
 *
 * Current element becomes 1.
 *
 * If previous element was replaced:
 *
 *     |1 - 1| = 0
 *
 * Result:
 *
 *     dp0
 *
 * If previous element was kept:
 *
 *     |arr[i - 1] - 1|
 *
 * Result:
 *
 *     dp1 + |arr[i - 1] - 1|
 *
 * Therefore:
 *
 *     newDp0 = max(
 *         dp0,
 *         dp1 + |arr[i - 1] - 1|
 *     )
 *
 *
 * ------------------------------------------------------------
 * Transition 2: Keep arr[i]
 * ------------------------------------------------------------
 *
 * Current element remains arr[i].
 *
 * If previous element was replaced:
 *
 *     |1 - arr[i]|
 *
 * Result:
 *
 *     dp0 + |arr[i] - 1|
 *
 * If previous element was kept:
 *
 *     |arr[i - 1] - arr[i]|
 *
 * Result:
 *
 *     dp1 + |arr[i] - arr[i - 1]|
 *
 * Therefore:
 *
 *     newDp1 = max(
 *         dp0 + |arr[i] - 1|,
 *         dp1 + |arr[i] - arr[i - 1]|
 *     )
 *
 *
 * Only the previous states are required to calculate the
 * current states, so the DP table can be compressed into
 * two variables.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 *
 * ============================================================
 * 5. FINAL OPTIMIZED SOLUTION
 * ============================================================
 */

class Solution {

    public int maxSum(int[] arr) {

        int n = arr.length;

        if (n <= 1) {
            return 0;
        }

        int dp0 = 0;
        int dp1 = 0;

        for (int i = 1; i < n; i++) {

            int newDp0 = Math.max(
                dp0,
                dp1 + Math.abs(arr[i - 1] - 1)
            );

            int newDp1 = Math.max(
                dp0 + Math.abs(arr[i] - 1),
                dp1 + Math.abs(arr[i] - arr[i - 1])
            );

            dp0 = newDp0;
            dp1 = newDp1;
        }

        return Math.max(dp0, dp1);
    }
}


/*
 * ============================================================
 * 6. COMPLEXITY COMPARISON
 * ============================================================
 *
 * Approach        Time        Space
 * -----------------------------------
 * Recursion       O(2^n)      O(n)
 * Memoization     O(n)        O(n)
 * Bottom-Up DP    O(n)        O(1)
 *
 *
 * ============================================================
 * 7. KEY INTERVIEW TAKEAWAY
 * ============================================================
 *
 * The important observation is that each element has only two
 * possible values:
 *
 *     arr[i]
 *     1
 *
 * The contribution of the current element depends only on the
 * value chosen for the previous element.
 *
 * Therefore, the complete history of previous choices is not
 * required.
 *
 * We only need two states:
 *
 *     0 -> previous element was replaced
 *     1 -> previous element was kept
 *
 * This reduces the problem from an exponential recursion tree
 * to O(n) DP states.
 *
 * Since only the previous DP states are needed, the final
 * solution uses O(1) extra space.
 *
 *
 * ============================================================
 * 8. EDGE CASES
 * ============================================================
 *
 * 1. Empty array:
 *    No adjacent elements exist, so return 0.
 *
 * 2. Single element:
 *    No adjacent pair exists, so return 0.
 *
 * 3. Elements equal to 1:
 *    Replacing them does not change their value.
 *
 * 4. Replacing an element is not always optimal.
 *    The DP evaluates both keeping and replacing each element.
 */


/*
 * GitHub:
 *
 * File:
 * DynamicProgramming/MaxAdjacentDiffsSumWith1Replacements.java
 *
 * Commit:
 * feat(dsa): solve max adjacent differences with dp
 */