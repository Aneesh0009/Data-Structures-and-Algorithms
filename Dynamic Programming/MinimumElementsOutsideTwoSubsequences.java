/*

* Problem: Minimum Elements Outside Two Subsequences
* Platform: GeeksforGeeks
* Difficulty: Hard
*
* Pattern: Dynamic Programming / Memoization
*
* Approach:
* 1. At each index, either skip the element, add it to the increasing
* subsequence, or add it to the decreasing subsequence.
* 2. Track the last selected element of both subsequences as the DP state.
* 3. Memoize the maximum number of elements that can be selected.
* 4. The answer is n minus the maximum number of selected elements.
*
* Why it works:
* Each element has exactly three choices: remain unused, join the increasing
* subsequence, or join the decreasing subsequence. The last selected values
* determine whether the current element can be added while preserving the
* strictly increasing/decreasing conditions.
*
* Time Complexity: O(n * 102 * 102)
* Space Complexity: O(n * 102 * 102)
*
* Edge Cases:
* * Empty array
* * Single-element array
* * Elements that cannot be added to either subsequence
    */

class Solution {

    public int minCount(int[] arr) {
        int n = arr.length;

        int[][][] dp = new int[n][102][102];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 102; j++) {
                java.util.Arrays.fill(dp[i][j], -1);
            }
        }

        int maxSelected = solve(arr, 0, 0, 101, dp);

        return n - maxSelected;
    }

    public int solve(int[] arr, int i, int incLast, int decLast, int[][][] dp) {
        if (i == arr.length) {
            return 0;
        }

        if (dp[i][incLast][decLast] != -1) {
            return dp[i][incLast][decLast];
        }

        int notTake = solve(arr, i + 1, incLast, decLast, dp);

        int takeInc = 0;
        if (arr[i] > incLast) {
            takeInc = 1 + solve(arr, i + 1, arr[i], decLast, dp);
        }

        int takeDec = 0;
        if (arr[i] < decLast) {
            takeDec = 1 + solve(arr, i + 1, incLast, arr[i], dp);
        }

        return dp[i][incLast][decLast] = Math.max(notTake, Math.max(takeInc, takeDec));
    }

}
