package org.example.leetcode_sg.classic;

import org.example.leetcode_sg.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Trees {


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
