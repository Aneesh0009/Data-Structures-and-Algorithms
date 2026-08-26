/*
 * Problem: Shortest Beautiful Substring
 * Platform: LeetCode
 * Difficulty: Medium
 *
 * Pattern: Sliding Window
 *
 * Approach:
 * Maintain a sliding window containing exactly k ones.
 * For every valid window, compare its length with the current answer.
 * If lengths are equal, use compareTo() to select the lexicographically smaller substring.
 *
 * Time Complexity: O(n²)
 * Space Complexity: O(n)
 */

class Solution {

    public String shortestBeautifulSubstring(String s, int k) {

        String ans = "";

        int one_ct = 0, l = 0;

        for (int r = 0; r < s.length(); r++) {

            char ch = s.charAt(r);

            if (ch == '1')
                one_ct += 1;

            while (one_ct == k) {

                String curr = s.substring(l, r + 1);

                if (ans.equals("") ||
                        curr.length() < ans.length() ||
                        (curr.length() == ans.length() &&
                                curr.compareTo(ans) < 0)) {

                    ans = curr;
                }

                char temp = s.charAt(l);

                if (temp == '1')
                    one_ct -= 1;

                l += 1;
            }
        }

        return ans;
    }
}

/*
compareTo() — what it does
compareTo() compares two strings lexicographically.
        curr.compareTo(ans) < 0

means curr comes before ans in lexicographical order.



Example:

"001".compareTo("010") < 0   // true
"010".compareTo("001") < 0   // false

So your condition:

curr.length() == ans.length() &&
curr.compareTo(ans) < 0

means : If both beautiful substrings have the same length, choose the lexicographically smaller one.
 */
