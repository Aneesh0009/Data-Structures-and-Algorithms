/*
 * Problem: Nodes Between Critical Points
 * Platform: LeetCode
 * Difficulty: Medium
 *
 * Approach:
 * Traverse the linked list while maintaining previous, current, and next nodes.
 * A node is a critical point if it is either a local maximum or local minimum.
 * Track the first and last critical points and calculate the minimum distance
 * between consecutive critical points and the maximum distance overall.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

class Solution {

    public int[] nodesBetweenCriticalPoints(ListNode head) {

        ListNode prev = head;
        ListNode curr = head.next;
        ListNode next = curr.next;

        int index = 1;
        int first = -1;
        int last = -1;
        int min = Integer.MAX_VALUE;

        while (next != null) {

            index++;

            if ((curr.val > prev.val && curr.val > next.val) ||
                (curr.val < prev.val && curr.val < next.val)) {

                if (first == -1) {
                    first = index;
                } else {
                    min = Math.min(min, index - last);
                }

                last = index;
            }

            prev = curr;
            curr = next;
            next = next.next;
        }

        if (first == last) {
            return new int[] {-1, -1};
        }

        int max = last - first;

        return new int[] {min, max};
    }
}

//Why: Uses a single traversal with constant extra space to identify critical points and calculate minimum/maximum distances.