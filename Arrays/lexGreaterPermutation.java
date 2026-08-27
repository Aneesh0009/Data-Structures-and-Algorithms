/*
 * Problem: Lexicographically Smallest Greater Permutation
 * Platform: LeetCode
 * Difficulty: Medium
 *
 * Pattern: Greedy + Frequency Array
 *
 * Approach:
 * 1. Count the frequency of each character in s.
 * 2. Traverse target from left to right while tracking used characters.
 * 3. At each position, check if a character greater than target[i] is available.
 * 4. Keep the prefix equal to target and place the smallest greater character.
 * 5. Fill the remaining positions with available characters in ascending order.
 * 6. If no greater permutation can be formed, return an empty string.
 *
 * Time Complexity: O(n × 26) = O(n)
 * Space Complexity: O(26) = O(1)
 */

class Solution {

    public String lexGreaterPermutation(String s, String target) {

        int n = s.length();

        int[] freq = new int[26];

        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        int[] used = new int[26];

        int bestPos = -1;
        int bestChar = -1;

        for (int i = 0; i < n; i++) {

            int t = target.charAt(i) - 'a';

            for (int c = t + 1; c < 26; c++) {

                if (freq[c] - used[c] > 0) {
                    bestPos = i;
                    bestChar = c;
                    break;
                }
            }

            if (freq[t] - used[t] <= 0) {
                break;
            }

            used[t]++;
        }

        if (bestPos == -1) {
            return "";
        }

        int[] remaining = freq.clone();

        StringBuilder ans = new StringBuilder(n);

        for (int i = 0; i < bestPos; i++) {

            int c = target.charAt(i) - 'a';

            ans.append(target.charAt(i));
            remaining[c]--;
        }

        ans.append((char) ('a' + bestChar));
        remaining[bestChar]--;

        for (int c = 0; c < 26; c++) {

            while (remaining[c] > 0) {
                ans.append((char) ('a' + c));
                remaining[c]--;
            }
        }

        return ans.toString();
    }
}