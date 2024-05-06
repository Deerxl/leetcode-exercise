package org.example.leetcode;

import org.apache.commons.lang3.AnnotationUtils;

import java.util.*;

/**
 * @author jialu.yxl
 * @date 07/02/2023 19:08
 */
public class LinkedList {

    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);
        sortList(head);
        // System.out.println(reorderList(head));
    }

    /**
     * <a href="https://leetcode.cn/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/description/">LCR 140. 训练计划 II</a>
     * 给定一个头节点为 head 的链表用于记录一系列核心肌群训练项目编号，请查找并返回倒数第 cnt 个训练项目编号。
     * 示例 1：
     * 输入：head = [2,4,7,8], cnt = 1
     * 输出：8
     * @param head
     * @param cnt
     * @return
     */
    public ListNode trainingPlan(ListNode head, int cnt) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode fast = head;
        ListNode slow = head;
        for (int i = 0; i < cnt; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        return slow;
    }

    /**
     * <a href="https://leetcode.cn/problems/add-two-numbers/">2. 两数相加</a>
     * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
     *
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     *
     * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode();
        ListNode dummyHead = pre;

        int sum = 0;
        int add = 0;
        while (l1 != null || l2 != null) {
            sum = add;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            if (sum >= 10) {
                add = 1;
                sum = sum % 10;
            } else {
                add = 0;
            }
            dummyHead.next = new ListNode(sum);
            dummyHead = dummyHead.next;
        }

        if (add == 1) {
            dummyHead.next = new ListNode(1);
        }

        return pre.next;
    }

    /**
     * <a href="https://leetcode.cn/problems/sort-list/description/">148. 排序链表</a>
     * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
     * @param head
     * @return
     */
    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode i = head;
        ListNode j = head.next;
        while (j != null && j.next != null) {
            i = i.next;
            j = j.next.next;
        }

        ListNode rightHead = sortList(i.next);
        i.next = null;
        ListNode leftHead = sortList(head);
        return sortListMerge(leftHead, rightHead);
    }

    private static ListNode sortListMerge(ListNode leftHead, ListNode rightHead) {
        ListNode pre = new ListNode();
        ListNode dummyHead = pre;
        while (leftHead != null && rightHead != null) {
            if (leftHead.val <= rightHead.val) {
                dummyHead.next = leftHead;
                leftHead = leftHead.next;
            } else {
                dummyHead.next = rightHead;
                rightHead = rightHead.next;
            }
            dummyHead = dummyHead.next;
        }

        if (leftHead != null) {
            dummyHead.next = leftHead;
        }

        if (rightHead != null) {
            dummyHead.next = rightHead;
        }

        return pre.next;
    }

    /**
     * <a href="https://leetcode.cn/problems/remove-duplicates-from-sorted-list-ii/">82. 删除排序链表中的重复元素 II</a>
     *给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表 。
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode pre = new ListNode();
        pre.next = head;

        ListNode dummyHead = pre;
        ListNode next;
        while (dummyHead.next != null) {
            next = dummyHead.next.next;
            if (next != null && next.val == dummyHead.next.val) {
                while (next != null && next.val == dummyHead.next.val) {
                    next = next.next;
                }
                dummyHead.next = next;
            } else {
                dummyHead = dummyHead.next;
            }
        }

        return pre.next;
    }

    /**
     * <a href="https://leetcode.cn/problems/remove-nth-node-from-end-of-list/">19. 删除链表的倒数第 N 个结点</a>
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode();
        dummy.next = head;

        ListNode i = dummy;
        ListNode j = dummy;

        while (n > 0) {
            i = i.next;
            n--;
        }

        while (i.next != null) {
            i = i.next;
            j = j.next;
        }

        j.next = j.next.next;

        return dummy.next;
    }

    /**
     * <a href="https://leetcode.cn/problems/linked-list-cycle-ii/description/">142. 环形链表 II</a>
     * 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
     * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
     * 不允许修改 链表。
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }

        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (set.contains(head)) {
                return head;
            } else {
                set.add(head);
                head = head.next;
            }
        }
        return null;
    }


    /**
     * <a href="https://leetcode.cn/problems/reorder-list/">143. 重排链表</a>
     * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
     * L0 → L1 → … → Ln - 1 → Ln
     * 请将其重新排列后变为：
     * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
     * 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     * @param head
     */
    public static void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }

        ListNode i = head;
        ListNode j = head;
        ListNode preI = i;
        while (j != null && j.next != null) {
            preI = i;
            i = i.next;
            j = j.next.next;
        }
        preI.next = null;

        ListNode next = i.next;
        ListNode dummyHead = i;
        while (next != null) {
            i.next = next.next;
            next.next = dummyHead;
            dummyHead = next;
            next = i.next;
        }

        ListNode next1, next2;
        while (head != null) {
            next1 = head.next;
            head.next = dummyHead;
            next2 = dummyHead.next;
            if (next1 != null) {
                dummyHead.next = next1;
                dummyHead = next2;
            }
            head = next1;
        }
    }

    /**
     * <a href="https://leetcode.cn/problems/intersection-of-two-linked-lists/">160. 相交链表</a>
     * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        Set<ListNode> set = new HashSet<>();
        while (headA != null || headB != null) {
            if (headA != null) {
                if (set.contains(headA)) {
                    return headA;
                } else {
                    set.add(headA);
                }
                headA = headA.next;
            }
            if (headB != null) {
                if (set.contains(headB)) {
                    return headB;
                } else {
                    set.add(headB);
                }
                headB = headB.next;
            }
        }
        return null;
    }

    /**
     * <a href="https://leetcode.cn/problems/merge-k-sorted-lists/">23. 合并 K 个升序链表</a>
     * 给你一个链表数组，每个链表都已经按升序排列。
     *
     * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        Queue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(l -> l.val));
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                queue.offer(lists[i]);
            }
        }

        ListNode head = new ListNode();
        ListNode dummyHead = head;
        while (!queue.isEmpty()) {
            ListNode node = queue.poll();
            head.next = node;
            head = head.next;
            node = node.next;
            if (node != null) {
                queue.offer(node);
            }
        }
        return dummyHead.next;
    }

    /**
     * <a href="https://leetcode.cn/problems/reverse-linked-list-ii/solutions/">92. 反转链表 II</a>
     * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == right) {
            return head;
        }

        ListNode pre = new ListNode();
        pre.next = head;

        for (int i = 1; i < left; i++) {
            pre = pre.next;
        }
        ListNode curHead = reverseBetween(pre.next, right - left + 1);
        pre.next = curHead;

        return left == 1 ? curHead : head;
    }

    private ListNode reverseBetween(ListNode head, int length) {
        ListNode dummyHead = head;

        for (int i = 1; i < length; i++) {
            ListNode next = dummyHead.next;
            dummyHead.next = next.next;
            next.next = head;
            head = next;
        }

        return head;
    }

    /**
     * <a href="https://leetcode.cn/problems/linked-list-cycle/">141. 环形链表</a>
     * 给你一个链表的头节点 head ，判断链表中是否有环。
     *
     * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。注意：pos 不作为参数进行传递 。仅仅是为了标识链表的实际情况。
     *
     * 如果链表中存在环 ，则返回 true 。 否则，返回 false 。
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode i = head;
        ListNode j = head.next;
        while (true) {
            if (i == j) {
                return true;
            }

            if (i.next != null) {
                i = i.next;
            } else {
                break;
            }

            if (j.next != null && j.next.next != null) {
                j = j.next.next;
            } else {
                break;
            }
        }
        return false;
    }


    /**
     * <a href="https://leetcode.cn/problems/merge-two-sorted-lists/">21. 合并两个有序链表</a>
     *
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null && list2 == null) {
            return null;
        }
        ListNode pre = new ListNode();
        ListNode dummy = pre;
        while (list1 != null || list2 != null) {
            if (list1 != null && list2 != null) {
                if (list1.val <= list2.val) {
                    dummy.next = new ListNode(list1.val);
                    list1 = list1.next;
                } else {
                    dummy.next = new ListNode(list2.val);
                    list2 = list2.next;
                }
            } else if (list1 != null) {
                dummy.next = new ListNode(list1.val);
                list1 = list1.next;
            } else {
                dummy.next = new ListNode(list2.val);
                list2 = list2.next;
            }
            dummy = dummy.next;
        }

        return pre.next;
    }

    /**
     * <a href="https://leetcode.cn/problems/reverse-nodes-in-k-group/">25. K 个一组翻转链表</a>
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k <= 1 || head == null || head.next == null) {
            return head;
        }

        ListNode dummyHead = head;
        int count = 0;
        while (dummyHead != null) {
            count++;
            dummyHead = dummyHead.next;
        }

        if (count < k) {
            return head;
        }

        ListNode afterReverseHead = myReverse(head, k);
        ListNode resultHead = afterReverseHead;

        int reverseCount = count / k - 1;
        ListNode afterReserveTail = head.next;
        for (int i = 0; i < reverseCount; i++) {
            afterReverseHead = myReverse(afterReserveTail, k);
            head.next = afterReverseHead;
            head = afterReserveTail;
            afterReserveTail = head.next;
        }

        return resultHead;
    }

    private ListNode myReverse(ListNode curHead, int k) {
        ListNode next;
        ListNode afterReverseHead = curHead;
        for (int i = 1; i < k; i++) {
            next = curHead.next;
            curHead.next = next.next;
            next.next = afterReverseHead;
            afterReverseHead = next;
        }
        return afterReverseHead;
    }

    /**
     * <a href="https://leetcode.cn/problems/reverse-linked-list/description/">206. 反转链表</a>
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode pre = new ListNode();
        pre.next = head;
        ListNode dummyHead = head;
        while (dummyHead.next != null) {
            ListNode next = dummyHead.next;
            head = pre.next;
            pre.next = next;
            dummyHead.next = next.next;
            next.next = head;

        }

        return pre.next;
    }

    /**
     * <a href="https://leetcode.cn/problems/odd-even-linked-list/">328. 奇偶链表</a>
     * @param head 给定单链表的头节点 head ，将所有索引为奇数的节点和索引为偶数的节点分别组合在一起，然后返回重新排序的列表。
     *
     * 第一个节点的索引被认为是 奇数 ， 第二个节点的索引为 偶数 ，以此类推。
     *
     * 请注意，偶数组和奇数组内部的相对顺序应该与输入时保持一致。
     *
     * 你必须在 O(1) 的额外空间复杂度和 O(n) 的时间复杂度下解决这个问题。
     * @return
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null || head.next.next == null) {
            return head;
        }

        ListNode pre = new ListNode();
        pre.next = head;
        ListNode evenHead = head.next;
        ListNode evenTail = head.next;
        ListNode nextOdd = evenTail.next;
        ListNode post = nextOdd.next;
        while (nextOdd != null) {
            head.next = nextOdd;
            nextOdd.next = evenHead;
            evenTail.next = post;

            head = head.next;
            evenTail = evenTail.next;
            if (evenTail != null) {
                nextOdd = evenTail.next;
            } else {
                nextOdd = null;
            }
            if (nextOdd != null) {
                post = nextOdd.next;
            } else {
                post = null;
            }
        }

        return pre.next;
    }

    /**
     * <a href="https://leetcode.cn/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof/">剑指 Offer 36. 二叉搜索树与双向链表</a>
     * @param root
     * @return
     */
    public static Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }

        inOrder(root);
        head.left = pre;
        pre.right = head;

        return head;
    }

    static Node pre, head;
    public static void inOrder(Node root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);

        if (pre != null) {
            pre.right = root;
        } else {
            head = root;
        }
        root.left = pre;
        pre = root;

        inOrder(root.right);

    }
}
