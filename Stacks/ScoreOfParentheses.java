/*
 * Problem: Score of Parentheses
 * Platform: LeetCode
 * Difficulty: Medium
 *
 * Approach:
 * Use a stack to store the score of each nested parentheses level.
 * Push 0 for every '(' and process the completed group on ')'.
 * "()" contributes 1, while "(A)" contributes 2 * score(A).
 * Add the completed group's score to its parent level.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

class Solution {
    public int scoreOfParentheses(String s) {
        Stack<Integer> st = new Stack<>();
        st.push(0);

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                st.push(0);
            } else {
                int inside = st.pop();

                if (inside == 0) {
                    inside = 1;
                } else {
                    inside = inside * 2;
                }

                int parent = st.pop();
                st.push(parent + inside);
            }
        }

        return st.pop();
    }
}