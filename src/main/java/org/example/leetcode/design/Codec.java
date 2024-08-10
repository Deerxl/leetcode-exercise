package org.example.leetcode.design;


import org.example.leetcode.common.TreeNode;

import java.util.*;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    public static void main(String[] args) {

        System.out.println(serialize(deserialize("[1, 2, 3, null, null, 4, 5]")));
    }

    // Encodes a tree to a single string.
    public static String serialize(TreeNode root) {
        if (root == null) {
            return "[]";
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.val == Integer.MAX_VALUE) {
                list.add(null);
                continue;
            } else {
                list.add(node.val);
            }

            if (node.left == null) {
                queue.offer(new TreeNode(Integer.MAX_VALUE));
            } else {
                queue.offer(node.left);
            }

            if (node.right == null) {
                queue.offer(new TreeNode(Integer.MAX_VALUE));
            } else {
                queue.offer(node.right);
            }
        }
        return list.toString();
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        if (data == null || "[]".equals(data)) {
            return null;
        }

        data = data.substring(1, data.length() - 1);
        String[] split = data.split(", ");
        TreeNode root = new TreeNode(Integer.parseInt(split[0]));
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int index = 1;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.val == Integer.MAX_VALUE) {
                continue;
            }

            if (index < split.length && !"null".equals(split[index])) {
                node.left = new TreeNode(Integer.parseInt(split[index]));
                queue.offer(node.left);
            }
            index++;
            if (index < split.length && !"null".equals(split[index])) {
                node.right = new TreeNode(Integer.parseInt(split[index]));
                queue.offer(node.right);
            }
            index++;
        }

        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));