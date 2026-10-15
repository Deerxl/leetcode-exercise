package org.example.leetcode_sg.classic;

import org.example.leetcode_sg.common.TreeNode;

public class Trees {


    /**
     * <a href="https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/?envType=study-plan-v2&envId=top-interview-150">105. Construct Binary Tree from Preorder and Inorder Traversal</a>
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
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
