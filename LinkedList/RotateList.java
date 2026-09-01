/*
 * Problem: Rotate List
 * Platform: LeetCode
 * Difficulty: Medium
 *
 * Approach:
 * Find the length of the linked list and reduce k using k %= length.
 * Find the node at position length - k and split the list there.
 * Connect the second part to the original head and return the new head.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }
        ListNode temp = head;
        int ct = 1;
        while (temp.next != null) {
            ct++;
            temp = temp.next;
        }
        k %= ct;
        if (k == 0) {
            return head;
        }
        int idx = 1;
        temp = head;
        ListNode f_half = head;
        while (idx < ct - k) {
            temp = temp.next;
            idx++;
        }
        ListNode newHead = temp.next;
        temp.next = null;
        ListNode curr = newHead;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = f_half;
        return newHead;
    }
}