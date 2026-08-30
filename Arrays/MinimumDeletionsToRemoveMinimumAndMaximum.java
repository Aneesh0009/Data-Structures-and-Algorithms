/*
 * Problem: Minimum Deletions to Remove Minimum and Maximum
 * Platform: LeetCode
 * Difficulty: Medium
 *
 * Approach:
 * Find the indices of the minimum and maximum elements.
 * Let i be the smaller index and j be the larger index.
 * Consider three possibilities:
 * 1. Remove both elements from the front.
 * 2. Remove both elements from the back.
 * 3. Remove the smaller-index element from the front and the other from the back.
 * Return the minimum of these three cases.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

class Solution {

    public int minimumDeletions(int[] nums) {

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int min_idx = 0;
        int max_idx = 0;
        int n = nums.length;

        if (n == 1) return 1;

        for (int i = 0; i < n; i++) {
            if (nums[i] > max) {
                max_idx = i;
                max = nums[i];
            }
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] < min) {
                min_idx = i;
                min = nums[i];
            }
        }

        int i = Math.min(min_idx, max_idx);
        int j = Math.max(min_idx, max_idx);

        int front = j + 1;
        int back = n - i;
        int both = (i + 1) + (n - j);

        return Math.min(front, Math.min(back, both));
    }
}