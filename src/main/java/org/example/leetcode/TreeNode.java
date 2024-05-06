package org.example.leetcode;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

/**
 * @author jialu.yxl
 * @date 02/02/2023 14:48
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    static TreeNode buildTree(List<Integer> list) {
        TreeNode root = new TreeNode(list.get(0));
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int index = 0;
        while (!queue.isEmpty() && index < list.size() - 1) {
            TreeNode node = queue.poll();
            Integer left = list.get(++index);
            if (left == null) {
                node.left = null;
            } else {
                node.left = new TreeNode(left);
                queue.offer(node.left);
            }

            if (index >= list.size()) {
                break;
            }
            Integer right = list.get(++index);
            if (right == null) {
                node.right = null;
            } else {
                node.right = new TreeNode(right);
                queue.offer(node.right);
            }
        }


        return root;
    }
}
