package org.example.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * <a href="https://leetcode.cn/problems/serialize-and-deserialize-binary-tree/">297. 二叉树的序列化与反序列化</a>
 * @author jialu.yxl
 * @date 02/02/2023 17:50
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder result = new StringBuilder();
        if (root == null) {
            return result.toString();
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.val == Integer.MIN_VALUE) {
                result.append("null").append(" ");
            } else {
                result.append(node.val).append(" ");
                queue.offer(node.left != null ? node.left : new TreeNode(Integer.MIN_VALUE));
                queue.offer(node.right != null ? node.right : new TreeNode(Integer.MIN_VALUE));
            }
        }

        return result.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.length() == 0) {
            return null;
        }
        String[] nodes = data.split(" ");
        TreeNode head = new TreeNode(Integer.parseInt(nodes[0]));
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(head);
        int index = 1;
        while (!queue.isEmpty()) {
            String val = nodes[index];
            if (val.length() == 0) {
                continue;
            }

            TreeNode node = queue.poll();
            if (!"null".equals(val)) {
                node.left = new TreeNode(Integer.parseInt(val));
                queue.offer(node.left);
            }

            index++;
            while (index < nodes.length && nodes[index].length() == 0) {
                index++;
            }
            if (index >= nodes.length) {
                break;
            }

            val = nodes[index];
            if (!"null".equals(val)) {
                node.right = new TreeNode(Integer.parseInt(val));
                queue.offer(node.right);
            }
            index++;

        }
        return head;
    }

    public static void main(String[] args) {
        TreeNode root = TreeNode.buildTree(Arrays.asList(1, 2, 3, null, null, 4, 5));


        Codec ser = new Codec();
        Codec deser = new Codec();
        TreeNode ans = deser.deserialize(ser.serialize(root));
    }
}


// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));