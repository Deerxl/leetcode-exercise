package org.example.leetcode_sg.classic;

import org.example.leetcode_sg.common.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

public class Trees {



    /**
     * <a href="https://leetcode.com/problems/count-complete-tree-nodes/?envType=study-plan-v2&envId=top-interview-150">222. Count Complete Tree Nodes</a>
     *
     * Given the root of a complete binary tree, return the number of the nodes in the tree.
     *
     * According to Wikipedia, every level, except possibly the last, is completely filled in a complete binary tree, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
     *
     * Design an algorithm that runs in less than O(n) time complexity.
     * @param root
     * @return
     */
    public static int countNodes(TreeNode root) {
        int height = calTreeHeight(root);
        if (height == 0) {
            return 0;
        }
        int rightHeight = calTreeHeight(root.right);
        if (height - 1 == rightHeight) {
            return (1 << (height - 1)) + countNodes(root.right);
        } else {
            return (1 << (height - 2)) + countNodes(root.left);
        }
    }


    public static int calTreeHeight(TreeNode root) {
        int count = 0;
        while (root != null) {
            count++;
            root = root.left;
        }
        return count;
    }

    /**
     * <a href="https://leetcode.com/problems/binary-tree-maximum-path-sum/?envType=study-plan-v2&envId=top-interview-150">124. Binary Tree Maximum Path Sum</a>
     *
     * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.
     *
     * The path sum of a path is the sum of the node's values in the path.
     *
     * Given the root of a binary tree, return the maximum path sum of any non-empty path.
     * @param root
     * @return
     */
    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return root.val;
        }
        maxPathSumSingleNode(root);
        return maxPathSum;
    }

    int maxPathSum = Integer.MIN_VALUE;

    public int maxPathSumSingleNode(TreeNode root) {
        if (root.left == null && root.right == null) {
            maxPathSum = Math.max(maxPathSum, root.val);
            return root.val;
        }
        int leftNodeMax = 0, rightNodeMax = 0;
        if (root.left != null) {
            leftNodeMax = maxPathSumSingleNode(root.left);
        }
        if (root.right != null) {
            rightNodeMax = maxPathSumSingleNode(root.right);
        }
        int rootNodeMax = Math.max(root.val, root.val + Math.max(leftNodeMax, rightNodeMax));
        maxPathSum = Math.max(maxPathSum, Math.max(rootNodeMax, leftNodeMax + rightNodeMax + root.val));
        return rootNodeMax;
    }



    /**
     * <a href="https://leetcode.com/problems/sum-root-to-leaf-numbers/?envType=study-plan-v2&envId=top-interview-150">129. Sum Root to Leaf Numbers</a>
     * @param root
     * @return
     */
    public int sumNumbers(TreeNode root) {
        return sumNumbers(root, 0);
    }

    int sumNumbers(TreeNode root, int curSum) {
        if (root == null) {
            return curSum;
        }
        if (root.left == null && root.right == null) {
            return curSum * 10 + root.val;
        }
        if (root.left == null) {
            return sumNumbers(root.right, curSum * 10 + root.val);
        }
        if (root.right == null) {
            return sumNumbers(root.left, curSum * 10 + root.val);
        }
        return sumNumbers(root.left, curSum * 10 + root.val) + sumNumbers(root.right, curSum * 10 + root.val);
    }

    /**
     * <a href="https://leetcode.com/problems/path-sum/?envType=study-plan-v2&envId=top-interview-150">112. Path Sum</a>
     * @param root
     * @param targetSum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return root.val == targetSum;
        }
        if (root.left == null) {
            return hasPathSum(root.right, targetSum - root.val);
        }
        if (root.right == null) {
            return hasPathSum(root.left, targetSum - root.val);
        }
        return hasPathSum(root.right, targetSum - root.val) || hasPathSum(root.left, targetSum - root.val);
    }

    /**
     * <a href="https://leetcode.com/problems/flatten-binary-tree-to-linked-list/?envType=study-plan-v2&envId=top-interview-150">114. Flatten Binary Tree to Linked List</a>
     * @param root
     */
    public void flatten(TreeNode root) {
        TreeNode tmp = root;
        while (tmp != null) {
            if (tmp.left != null) {
                TreeNode rightHead = tmp.right;
                TreeNode leftTail = tmp.left;
                while (leftTail.right != null) {
                    leftTail = leftTail.right;
                }
                tmp.right = tmp.left;
                tmp.left = null;
                leftTail.right = rightHead;

            }
            tmp = tmp.right;
        }
    }

    /**
     * <a href="https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/?envType=study-plan-v2&envId=top-interview-150">117. Populating Next Right Pointers in Each Node II</a>
     * @param root
     * @return
     */
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Queue<Node> levelNodes = new ArrayDeque<>();
        levelNodes.offer(root);
        while (!levelNodes.isEmpty()) {
            int curLevelCount = levelNodes.size();
            for (int i = 0; i < curLevelCount; i++) {
                Node node = levelNodes.poll();
                if (i < curLevelCount - 1) {
                    node.next = levelNodes.peek();
                }
                if (node.left != null) {
                    levelNodes.offer(node.left);
                }
                if (node.right != null) {
                    levelNodes.offer(node.right);
                }
            }
        }
        return root;
    }

    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    /**
     * <a href="https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/?envType=study-plan-v2&envId=top-interview-150">106. Construct Binary Tree from Inorder and Postorder Traversal</a>
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTreeFromInAndPostOrder(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    public TreeNode buildTreeFromInAndPostOrder(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd) {
        if (inEnd < inStart || postEnd < postStart) {
            return null;
        }
        int rootVal = postorder[postEnd];
        TreeNode root = new TreeNode(rootVal);
        int inOrderLeftEndIndex = inStart;
        while (inOrderLeftEndIndex <= inEnd && inorder[inOrderLeftEndIndex] != rootVal) {
            inOrderLeftEndIndex++;
        }
        inOrderLeftEndIndex--;
        int leftLen = inOrderLeftEndIndex - inStart + 1;

        if (leftLen <= 0) {
            root.left = null;
        } else {
            root.left = buildTreeFromInAndPostOrder(inorder, inStart, inOrderLeftEndIndex, postorder, postStart, postStart + leftLen - 1);
        }

        int inOrderRightStart = inOrderLeftEndIndex + 2;
        if (inOrderRightStart > inEnd) {
            root.right = null;
        } else {
            root.right = buildTreeFromInAndPostOrder(inorder, inOrderRightStart, inEnd, postorder, postStart + leftLen, postEnd - 1);
        }
        return root;
    }

    /**
     * <a href="https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/?envType=study-plan-v2&envId=top-interview-150">105. Construct Binary Tree from Preorder and Inorder Traversal</a>
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTreeFromPreAndInOrder(int[] preorder, int[] inorder) {
        return buildTreeFromPreAndInOrder(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    public TreeNode buildTreeFromPreAndInOrder(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        if (inStart > inEnd || preEnd > preStart) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        int inOrderLeftEnd = inStart;
        while (inOrderLeftEnd <= inEnd && inorder[inOrderLeftEnd] != root.val) {
            inOrderLeftEnd++;
        }
        inOrderLeftEnd -= 1;
        int leftLen = inOrderLeftEnd - inStart + 1;
        if (leftLen <= 0) {
            root.left = null;
        } else {
            root.left = buildTreeFromPreAndInOrder(preorder, preStart + 1, preStart + leftLen - 1, inorder, inStart, inOrderLeftEnd);
        }

        int inOrderRightStart = inOrderLeftEnd + 2;
        if (inOrderRightStart > inEnd) {
            root.right = null;
        } else {
            root.right = buildTreeFromPreAndInOrder(preorder, preStart + leftLen, preEnd, inorder, inOrderRightStart, inEnd);
        }

        return root;
    }

    /**
     * <a href="https://leetcode.com/problems/symmetric-tree/?envType=study-plan-v2&envId=top-interview-150">101. Symmetric Tree</a>
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetric(root.left, root.right);
    }

    public boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }

    /**
     * <a href="https://leetcode.com/problems/invert-binary-tree/?envType=study-plan-v2&envId=top-interview-150">226. Invert Binary Tree</a>
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = invertTree(right);
        root.right = invertTree(left);
        return root;
    }

    /**
     * <a href="https://leetcode.com/problems/same-tree/?envType=study-plan-v2&envId=top-interview-150">100. Same Tree</a>
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null) {
            return false;
        }
        if (q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }


    /**
     * <a href="https://leetcode.com/problems/maximum-depth-of-binary-tree/?envType=study-plan-v2&envId=top-interview-150">104. Maximum Depth of Binary Tree</a>
     * Given the root of a binary tree, return its maximum depth.
     *
     * A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return 1 + Math.max(leftDepth, rightDepth);
    }

    /**
     * <a href="https://leetcode.com/problems/leaf-similar-trees/">872. Leaf-Similar Trees</a>
     * @param root1 Consider all the leaves of a binary tree, from left to right order, the values of those leaves form a leaf value sequence.
     * @param root2 Two binary trees are considered leaf-similar if their leaf value sequence is the same.
     * @return Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.
     *
     * time complexity: O(n), space complexity: O(n)
     */
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();
        getLeafArr(root1, s1);
        getLeafArr(root2, s2);
        return s1.toString().contentEquals(s2);
    }

    public void getLeafArr(TreeNode root, StringBuilder stringBuilder) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            stringBuilder.append(root.val).append(",");
        }
        getLeafArr(root.left, stringBuilder);
        getLeafArr(root.right, stringBuilder);
    }
}
