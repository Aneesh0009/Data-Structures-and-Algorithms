/*
 * Problem: Longest Increasing Path in a Matrix
 * Platform: GeeksforGeeks
 * Difficulty: Medium
 *
 * Approach:
 * Use DFS with memoization to find the longest increasing path
 * starting from each cell. Move only to adjacent cells having
 * a strictly greater value. Store the result for each cell in
 * dp to avoid recomputing the same subproblem.
 *
 * Time Complexity: O(n * m)
 * Space Complexity: O(n * m)
 */

class Solution {

    public int dfs(int row, int col, int[][] mat, int[][] dp, int n, int m) {

        if (dp[row][col] != 0) {
            return dp[row][col];
        }

        int left = 0;
        int right = 0;
        int top = 0;
        int bottom = 0;

        if (col - 1 >= 0 && mat[row][col - 1] > mat[row][col]) {
            left = dfs(row, col - 1, mat, dp, n, m);
        }

        if (col + 1 < m && mat[row][col + 1] > mat[row][col]) {
            right = dfs(row, col + 1, mat, dp, n, m);
        }

        if (row - 1 >= 0 && mat[row - 1][col] > mat[row][col]) {
            top = dfs(row - 1, col, mat, dp, n, m);
        }

        if (row + 1 < n && mat[row + 1][col] > mat[row][col]) {
            bottom = dfs(row + 1, col, mat, dp, n, m);
        }

        dp[row][col] = 1 + Math.max(
                left,
                Math.max(right, Math.max(top, bottom))
        );

        return dp[row][col];
    }

    public int longIncPath(int[][] mat, int n, int m) {

        if (n == 0 || m == 0) {
            return 0;
        }

        int[][] dp = new int[n][m];

        int ans = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ans = Math.max(ans, dfs(i, j, mat, dp, n, m));
            }
        }

        return ans;
    }
}