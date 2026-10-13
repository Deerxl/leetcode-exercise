package org.example.leetcode_sg.classic;

import org.example.leetcode_sg.common.ListNode;
import org.example.leetcode_sg.common.Node;

import java.util.List;

public class ListNodeProblems {

    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        head.next = new ListNode(1);
        partition(head, 2);
    }


    /**
     * <a href="https://leetcode.com/problems/partition-list/?envType=study-plan-v2&envId=top-interview-150">86. Partition List</a>
     * @param head
     * @param x
     * @return
     */
    public static ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode pre = new ListNode();
        pre.next = head;

        ListNode greaterPre = new ListNode();
        boolean findGreatPre = false;
        ListNode lessPre = new ListNode();
        ListNode lessTail = null;
        ListNode tmp = pre;
        while (tmp.next != null) {
            if (tmp.next.val >= x) {
                if (!findGreatPre) {
                    findGreatPre = true;
                    greaterPre.next = tmp.next;
                }
                tmp = tmp.next;
            } else {
                if (lessTail == null) {
                    lessTail = tmp.next;
                    lessPre.next = lessTail;
                    tmp.next = tmp.next.next;
                    lessTail.next = null;
                } else {
                    lessTail.next = tmp.next;
                    lessTail = lessTail.next;
                    tmp.next = tmp.next.next;
                    lessTail.next = null;
                }
            }
        }

        if (lessTail != null) {
            pre.next = lessPre.next;
            lessTail.next = greaterPre.next;
        }
        return pre.next;
    }

    /**
     * <a href="https://leetcode.com/problems/rotate-list/?envType=study-plan-v2&envId=top-interview-150">61. Rotate List</a>
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode tmp = head;
        int count = 0;
        while (tmp != null) {
            count++;
            tmp = tmp.next;
        }
        k = k % count;
        if (k == 0) {
            return head;
        }

        ListNode pre = new ListNode();
        pre.next = head;
        tmp = pre;

        for (int i = 0; i < count - k; i++) {
            tmp = tmp.next;
        }
        ListNode copyHead = head;
        pre.next = tmp.next;
        tmp.next = null;
        tmp = pre.next;
        while (tmp.next != null) {
            tmp = tmp.next;
        }
        tmp.next = copyHead;

        return pre.next;

    }

    /**
     * <a href="https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/description/?envType=study-plan-v2&envId=top-interview-150">82. Remove Duplicates from Sorted List II</a>
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode pre = new ListNode();
        pre.next = head;
        ListNode preCopy = pre;
        ListNode cur = head;
        int curVal = cur.val;
        while (cur != null && cur.next != null) {
            curVal = cur.val;
            if (curVal == cur.next.val) {
                do {
                    cur = cur.next;
                } while (cur != null && cur.val == curVal);
                preCopy.next = cur;
                cur = preCopy.next;
            } else {
                preCopy = preCopy.next;
                cur = preCopy.next;
            }
        }

        return pre.next;
    }

    /**
     * <a href="https://leetcode.com/problems/remove-nth-node-from-end-of-list/?envType=study-plan-v2&envId=top-interview-150">19. Remove Nth Node From End of List</a>
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode copyHead = head;
        int count = 0;
        while (copyHead != null) {
            count++;
            copyHead = copyHead.next;
        }
        ListNode pre = new ListNode();
        pre.next = head;
        copyHead = pre;
        for (int i = 0; i < count - n; i++) {
            copyHead = copyHead.next;
        }
        copyHead.next = copyHead.next.next;
        return pre.next;
    }

    /**
     * <a href="https://leetcode.com/problems/reverse-nodes-in-k-group/description/?envType=study-plan-v2&envId=top-interview-150">25. Reverse Nodes in k-Group</a>
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1) {
            return head;
        }

        ListNode pre = new ListNode();
        pre.next = head;
        ListNode dummy = pre;

        while (pre.next != null) {
            ListNode tmpNode = pre;
            boolean canReverse = true;
            for (int i = 0; i < k; i++) {
                tmpNode = tmpNode.next;
                if (tmpNode == null) {
                    canReverse = false;
                    break;
                }
            }
            if (!canReverse) {
                break;
            }

            ListNode post = tmpNode.next;
            tmpNode.next = null;

            ListNode tail = pre.next;
            ListNode tailNext;
            ListNode tailPost;
            for (int i = 1; i < k; i++) {
                tailNext = tail.next;
                tailPost = tailNext.next;
                tail.next = tailPost;
                tailNext.next = pre.next;
                pre.next = tailNext;
            }
            pre = tail;
            pre.next = post;
        }

        return dummy.next;
    }

    /**
     * <a href="https://leetcode.com/problems/reverse-linked-list-ii/submissions/2170962098/?envType=study-plan-v2&envId=top-interview-150">92. Reverse Linked List II</a>
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left >= right || head == null) {
            return head;
        }

        ListNode pre = new ListNode();
        pre.next = head;
        ListNode dummyHead = pre;
        for (int i = 1; i < left; i++) {
            pre = pre.next;
        }
        ListNode tail = pre.next;
        ListNode curFirst = tail;
        ListNode tailNext;
        ListNode post;
        for (int i = 0; i < right - left; i++) {
            tailNext = tail.next;
            post = tailNext.next;
            tail.next = post;
            tailNext.next = curFirst;
            curFirst = tailNext;
        }
        pre.next = curFirst;

        return dummyHead.next;
    }

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
