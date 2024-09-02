package org.example.leetcode.classification;

import org.example.leetcode.common.Node;
import org.example.leetcode.common.ListNode;

import java.util.*;

/**
 * @author jialu.yxl
 * @date 07/02/2023 19:08
 */
public class LinkedList {

    public static void main(String[] args) {
        // Node root = new Node(4);
        // root.left = new Node(2);
        // root.left.left = new Node(1);
        // root.left.right = new Node(3);
        // root.right = new Node(5);
        // treeToDoublyList(root);

        Node node1 = new Node(7);
        node1.random = null;
        Node node2 = new Node(13);
        node1.next = node2;
        node2.random = node1;
        Node node3 = new Node(11);
        node2.next = node3;
        Node node4 = new Node(10);
        node3.next = node4;
        node4.random = node3;
        Node node5 = new Node(1);
        node4.next = node5;
        node5.random = node1;

        System.out.println(copyRandomList(node1));
    }


    /**
     * <a href="https://leetcode.cn/problems/fu-za-lian-biao-de-fu-zhi-lcof/">LCR 154. 复杂链表的复制</a>
     * 请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null。
     * 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
     * 输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
     * @param head Node.random 为空（null）或指向链表中的节点。 节点数目不超过 1000 。
     * @return
     */
    public static Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        Node dummy = head;
        while (dummy != null) {
            Node next = new Node(dummy.val);
            next.next = dummy.next;
            dummy.next = next;
            dummy = next.next;
        }

        dummy = head;
        while (dummy != null) {
            Node next = dummy.next;
            if (dummy.random != null) {
                next.random = dummy.random.next;
            }
            dummy = dummy.next.next;
        }

        Node copyHead = head.next;
        while (head != null) {
            Node next = head.next;
            if (head.next != null) {
                head.next = next.next;
            }
            head = next;
        }

        return copyHead;
    }

    /**
     * <a href="https://leetcode.cn/problems/kth-node-from-end-of-list-lcci/">面试题 02.02. Kth Node From End of List LCCI</a>
     *  Implement an algorithm to find the kth to last element of a singly linked list. Return the value of the element.
     *
     * Note: This problem is slightly different from the original one in the book.
     *
     * Example:
     *
     * Input:  1->2->3->4->5 和 k = 2
     * Output:  4
     * @param head
     * @param k
     * @return
     */
    public int kthToLast(ListNode head, int k) {
        int len = 0;
        ListNode dummy = head;
        while (dummy != null) {
            len++;
            dummy = dummy.next;
        }

        dummy = head;
        for (int i = 0; i < len - k; i++) {
            dummy = dummy.next;
        }
        return dummy.val;
    }


    /**
     * <a href="https://leetcode.cn/problems/remove-zero-sum-consecutive-nodes-from-linked-list/description/">1171. Remove Zero Sum Consecutive Nodes from Linked List</a>
     * Given the head of a linked list, we repeatedly delete consecutive sequences of nodes that sum to 0 until there are no such sequences.
     * After doing so, return the head of the final linked list.  You may return any such answer.
     * (Note that in the examples below, all sequences are serializations of ListNode objects.)
     * @param head The given linked list will contain between 1 and 1000 nodes. -1000 <= node.val <= 1000
     * @return
     */
    public ListNode removeZeroSumSublists(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        Map<Integer, ListNode> map = new HashMap<>();
        int prefixSum = 0;
        while (dummy != null) {
            prefixSum += dummy.val;
            map.put(prefixSum, dummy);
            dummy = dummy.next;
        }

        dummy = new ListNode();
        dummy.next = head;
        ListNode pre = dummy;

        prefixSum = 0;
        while (dummy != null) {
            prefixSum += dummy.val;
            dummy.next = map.get(prefixSum).next;
            dummy = dummy.next;
        }

        return pre.next;
    }

    /**
     * <a href="https://leetcode.cn/problems/partition-list/description/">86. Partition List</a>
     * Given the head of a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
     *
     * You should preserve the original relative order of the nodes in each of the two partitions.
     * Input: head = [1,4,3,2,5,2], x = 3
     * Output: [1,2,2,4,3,5]
     * @param head
     * @param x
     * @return
     */
    public static ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode pre = new ListNode(0);

        ListNode cur = pre;
        ListNode dummy = head;
        while (dummy != null) {
            if (dummy.val < x) {
                cur.next = new ListNode(dummy.val);
                cur = cur.next;
            }
            dummy = dummy.next;
        }

        dummy = head;
        while (dummy != null) {
            if (dummy.val >= x) {
                cur.next = new ListNode(dummy.val);
                cur = cur.next;
            }
            dummy = dummy.next;
        }
        return pre.next;
    }

    /**
     * <a href="https://leetcode.cn/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof/">LCR 171. 训练计划 V</a>
     * 某教练同时带教两位学员，分别以链表 l1、l2 记录了两套核心肌群训练计划，节点值为训练项目编号。两套计划仅有前半部分热身项目不同，后续正式训练项目相同。请设计一个程序找出并返回第一个正式训练项目编号。如果两个链表不存在相交节点，返回 null 。
     * @param headA
     * @param headB
     * @return
     */
    ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode pA = headA;
        ListNode pB = headB;
        boolean flagA = true;
        boolean flagB = true;
        while (pA != pB) {
            if (pA == null) {
                if (flagA) {
                    flagA = false;
                } else {
                    return null;
                }
                pA = headB;
            } else {
                pA = pA.next;
            }

            if (pB == null) {
                if (flagB) {
                    flagB = false;
                } else {
                    return null;
                }
                pB = headA;
            } else {
                pB = pB.next;
            }
        }
        return pA;
    }

    /**
     * <a href="https://leetcode.cn/problems/odd-even-linked-list/">328. Odd Even Linked List</a>
     * Given the head of a singly linked list, group all the nodes with odd indices together followed by the nodes with even indices, and return the reordered list.
     * The first node is considered odd, and the second node is even, and so on.
     * Note that the relative order inside both the even and odd groups should remain as it was in the input.
     * You must solve the problem in O(1) extra space complexity and O(n) time complexity.
     * @param head The number of nodes in the linked list is in the range [0, 104].
     * @return
     */
    public static ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        ListNode oddTail = head;
        ListNode evenHead = head.next;
        ListNode evenTail = head.next;
        ListNode next = evenTail.next;
        evenTail.next = null;
        while (next != null) {
            oddTail.next = next;
            oddTail = oddTail.next;
            next = next.next;
            if (next != null) {
                evenTail.next = next;
                evenTail = evenTail.next;
                next = next.next;
                evenTail.next = null;
            }
        }
        oddTail.next = evenHead;

        return head;
    }

    /**
     * <a href="https://leetcode.cn/problems/add-two-numbers-ii/">445. Add Two Numbers II</a>
     * You are given two non-empty linked lists representing two non-negative integers.
     * The most significant digit comes first and each of their nodes contains a single digit.
     * Add the two numbers and return the sum as a linked list.
     * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
     * Example 1:
     * Input: l1 = [7,2,4,3], l2 = [5,6,4]
     * Output: [7,8,0,7]
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1 = addTwoNumbersReverse(l1);
        l2 = addTwoNumbersReverse(l2);
        ListNode pre = new ListNode();
        ListNode dummy = pre;
        int add = 0, sum = 0;
        while (l1 != null || l2 != null) {
            sum += add;

            if (l1 != null) {
                sum += l1.val;
            }
            if (l2 != null) {
                sum += l2.val;
            }

            if (sum >= 10) {
                add = 1;
                sum = sum % 10;
            } else {
                add = 0;
            }

            dummy.next = new ListNode(sum);
            dummy = dummy.next;

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
            sum = 0;
        }

        if (add == 1) {
            dummy.next = new ListNode(1);
        }

        return addTwoNumbersReverse(pre.next);
    }

    private ListNode addTwoNumbersReverse(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode next = head.next, tail;
        head.next = null;
        while (next != null) {
            tail = next.next;
            next.next = head;
            head = next;
            next = tail;
        }
        return head;
    }

    /**
     * <a href="https://leetcode.cn/problems/rotate-list/">61. Rotate List</a>
     * Given the head of a linked list, rotate the list to the right by k places.
     * Input: head = [1,2,3,4,5], k = 2
     * Output: [4,5,1,2,3]
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }

        int count = 0;
        ListNode dummy = head;
        while (dummy != null) {
            count++;
            dummy = dummy.next;
        }

        k = k % count;
        if (k == 0) {
            return head;
        }

        int cut = count - k - 1;
        dummy = head;
        while (cut > 0) {
            dummy = dummy.next;
            cut--;
        }

        ListNode newHead = dummy.next;
        dummy.next = null;

        dummy = head;
        ListNode dummy2 = newHead;
        while (dummy2.next != null) {
            dummy2 = dummy2.next;
        }
        dummy2.next = dummy;

        return newHead;
    }

    /**
     * <a href="https://leetcode.cn/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof/">LCR 155. 将二叉搜索树转化为排序的双向链表</a>
     * 将一个 二叉搜索树 就地转化为一个 已排序的双向循环链表 。
     * 对于双向循环列表，你可以将左右孩子指针作为双向循环链表的前驱和后继指针，第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。
     * 特别地，我们希望可以 就地 完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。还需要返回链表中最小元素的指针。
     * @param root
     * @return
     */
    public static Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }

        treeToDoublyListInOrder(root);

        head.left = pre;
        pre.right = head;
        return head;
    }

    static Node pre, head;
    private static void treeToDoublyListInOrder(Node root) {
        if (root == null) {
            return;
        }

        treeToDoublyListInOrder(root.left);
        if (pre == null) {
            head = root;
            pre = root;
        } else {
            pre.right = root;
            root.left = pre;
            pre = root;
        }
        treeToDoublyListInOrder(root.right);
    }

    /**
     * <a href="https://leetcode.cn/problems/copy-list-with-random-pointer/">138. Copy List with Random Pointer</a>
     * A linked list of length n is given such that each node contains an additional random pointer,
     * which could point to any node in the list, or null.
     * Construct a deep copy of the list.
     * The deep copy should consist of exactly n brand new nodes,
     * where each new node has its value set to the value of its corresponding original node.
     * Both the next and random pointer of the new nodes should point to new nodes in the copied list
     * such that the pointers in the original list and copied list represent the same list state.
     * None of the pointers in the new list should point to nodes in the original list.
     * For example, if there are two nodes X and Y in the original list, where X.random --> Y,
     * then for the corresponding two nodes x and y in the copied list, x.random --> y.
     * Return the head of the copied linked list.
     * @param head
     * @return
     */
    public Node copyRandomList138(Node head) {
        if (head == null) {
            return null;
        }

        Node dummy = head;
        Map<Node, Integer> oldNodeMap = new HashMap<>();
        List<Node> newNodeList = new ArrayList<>();
        int count = 0;

        Node pre = new Node(0);
        Node newDummy = pre;

        while (dummy != null) {
            oldNodeMap.put(dummy, count++);

            newDummy.next = new Node(dummy.val);
            newDummy = newDummy.next;
            newNodeList.add(newDummy);

            dummy = dummy.next;
        }

        for (Map.Entry<Node, Integer> entry : oldNodeMap.entrySet()) {
            Node node = entry.getKey();
            Integer index = entry.getValue();

            Integer randomIndex = oldNodeMap.get(node.random);
            if (randomIndex != null) {
                newNodeList.get(index).random = newNodeList.get(randomIndex);
            }
        }

        return pre.next;
    }



    /**
     * <a href="https://leetcode.cn/problems/swap-nodes-in-pairs/">24. 两两交换链表中的节点</a>
     * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode pre = new ListNode();
        pre.next = head;

        ListNode dummy = pre;
        ListNode first, second, tail;
        while (dummy.next != null && dummy.next.next != null) {
            first = dummy.next;
            second = dummy.next.next;
            tail = dummy.next.next.next;
            dummy.next = second;
            first.next = tail;
            second.next = first;
            dummy = first;
        }

        return pre.next;
    }

    /**
     * <a href="https://leetcode.cn/problems/remove-duplicates-from-sorted-list/">83. 删除排序链表中的重复元素</a>
     * 给定一个已排序的链表的头 head ， 删除所有重复的元素，使每个元素只出现一次 。返回 已排序的链表 。
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummyHead = head;
        while (dummyHead.next != null) {
            if (dummyHead.val == dummyHead.next.val) {
                dummyHead.next = dummyHead.next.next;
            } else {
                dummyHead = dummyHead.next;
            }
        }

        return head;
    }

    /**
     * <a href="https://leetcode.cn/problems/palindrome-linked-list/">234. 回文链表</a>
     * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
     * 进阶：你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        ListNode i = head;
        ListNode j = head.next;
        StringBuilder sb1 = new StringBuilder();
        sb1.append(i.val);
        while (j != null && j.next != null) {
            i = i.next;
            j = j.next.next;
            sb1.append(i.val);
        }

        StringBuilder sb2 = new StringBuilder();
        while (i.next != null) {
            sb2.append(i.next.val);
            i = i.next;
        }

        if (sb1.length() > sb2.length()) {
            sb1.deleteCharAt(sb1.length() - 1);
        }

        return sb1.reverse().toString().contentEquals(sb2);
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
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
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
    public ListNode getIntersectionNode160(ListNode headA, ListNode headB) {
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
    public ListNode oddEvenList1(ListNode head) {
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
}
