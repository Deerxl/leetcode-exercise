package org.example.leetcode_sg.classic;

import org.example.leetcode_sg.common.ListNode;
import org.example.leetcode_sg.common.Node;

public class ListNodeProblems {

    /**
     * <a href="https://leetcode.com/problems/copy-list-with-random-pointer/?envType=study-plan-v2&envId=top-interview-150">138. Copy List with Random Pointer</a>
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Node dummy = head;
        while (dummy != null) {
            Node node = new Node(dummy.val);
            node.next = dummy.next;
            dummy.next = node;
            dummy = dummy.next.next;
        }

        dummy = head;
        while (dummy != null) {
            if (dummy.random != null) {
                dummy.next.random = dummy.random.next;
            }
            dummy = dummy.next.next;
        }
        dummy = head;
        Node next = dummy.next;
        Node headCopy = next;
        while (dummy != null) {
            dummy.next = next.next;
            dummy = dummy.next;
            if (dummy != null) {
                next.next = dummy.next;
                next = next.next;
            }
        }

        return headCopy;
    }

    /**
     * <a href="https://leetcode.com/problems/merge-two-sorted-lists/?envType=study-plan-v2&envId=top-interview-150">21. Merge Two Sorted Lists</a>
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = new ListNode();
        ListNode dump = head;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                dump.next = new ListNode(list1.val);
                list1 = list1.next;
            } else {
                dump.next = new ListNode(list2.val);
                list2 = list2.next;
            }
            dump = dump.next;
        }

        if (list1 != null) {
            dump.next = list1;
        }

        if (list2 != null) {
            dump.next = list2;
        }

        return head.next;
    }

    /**
     * <a href="https://leetcode.com/problems/add-two-numbers/?envType=study-plan-v2&envId=top-interview-150">2. Add Two Numbers</a>
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int add = 0;
        ListNode head = new ListNode();
        ListNode dump = head;
        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val + add;
            if (sum >= 10) {
                add = 1;
                sum = sum % 10;
            } else {
                add = 0;
            }
            dump.next = new ListNode(sum);
            dump = dump.next;
            l1 = l1.next;
            l2 = l2.next;
        }

        while (l1 != null) {
            int sum = l1.val + add;
            if (sum >= 10) {
                add = 1;
                sum = sum % 10;
            } else {
                add = 0;
            }
            dump.next = new ListNode(sum);
            dump = dump.next;
            l1 = l1.next;
        }

        while (l2 != null) {
            int sum = l2.val + add;
            if (sum >= 10) {
                add = 1;
                sum = sum % 10;
            } else {
                add = 0;
            }
            dump.next = new ListNode(sum);
            dump = dump.next;
            l2 = l2.next;
        }

        if (add == 1) {
            dump.next = new ListNode(1);
        }

        return head.next;
    }

    /**
     * <a href="https://leetcode.com/problems/linked-list-cycle/submissions/?envType=study-plan-v2&envId=top-interview-150">141. Linked List Cycle</a>
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast && fast != null && slow != null) {
            slow = slow.next;
            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
            }
        }
        return slow == fast;
    }
}
