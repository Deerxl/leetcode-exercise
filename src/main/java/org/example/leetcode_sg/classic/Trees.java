package org.example.leetcode_sg.classic;

import org.example.leetcode_sg.common.TreeNode;

import java.util.*;

public class Trees {

    /**
     * <a href="https://leetcode.com/problems/validate-binary-search-tree/?envType=study-plan-v2&envId=top-interview-150">98. Validate Binary Search Tree</a>
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isValidBST(TreeNode root, int minValue, int maxValue) {
        if (root == null) {
            return true;
        }
        if (minValue > maxValue || root.val < minValue || root.val > maxValue) {
            return false;
        }

        if (root.left == null && root.right == null) {
            return true;
        }

        if (root.left != null && root.val == Integer.MIN_VALUE) {
            return false;
        }

        if (root.right != null && root.val == Integer.MAX_VALUE) {
            return false;
        }

        return isValidBST(root.right, root.val + 1, maxValue) && isValidBST(root.left, minValue, root.val - 1);
    }

    /**
     * <a href="https://leetcode.com/problems/kth-smallest-element-in-a-bst/description/?envType=study-plan-v2&envId=top-interview-150">230. Kth Smallest Element in a BST</a>
     * Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed) of all the values of the nodes in the tree.
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (root.left != null) {
            stack.push(root.left);
            root = root.left;
        }
        int index = 0;

        while (!stack.isEmpty()) {
            stack.peek();
            TreeNode node = stack.pop();
            index++;
            if (index == k) {
                return node.val;
            }

            if (node.right != null) {
                stack.push(node.right);
                node = node.right;
                while (node.left != null) {
                    stack.push(node.left);
                    node = node.left;
                }
            }
        }
        return -1;
    }

    /**
     * <a href="https://leetcode.com/problems/minimum-absolute-difference-in-bst/?envType=study-plan-v2&envId=top-interview-150">530. Minimum Absolute Difference in BST</a>
     * Given the root of a Binary Search Tree (BST), return the minimum absolute difference between the values of any two different nodes in the tree.
     * @param root The number of nodes in the tree is in the range [2, 104].
     * @return
     */
    public int getMinimumDifference(TreeNode root) {
        List<Integer> inOrder = new ArrayList<>();
        inOrder(root, inOrder);
        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i < inOrder.size() - 1; i++) {
            minDiff = Math.min(minDiff, inOrder.get(i + 1) - inOrder.get(i));
            if (minDiff == 1) {
                return 1;
            }
        }
        return minDiff;
    }

    public void inOrder(TreeNode root, List<Integer> inOrderList) {
        if (root == null) {
            return;
        }
        inOrder(root.left, inOrderList);
        inOrderList.add(root.val);
        inOrder(root.right, inOrderList);
    }

    /**
     * <a href="https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/description/?envType=study-plan-v2&envId=top-interview-150">103. Binary Tree Zigzag Level Order Traversal</a>
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int levelSize = 1;
        boolean reverse = false;
        while (!queue.isEmpty()) {
            levelSize = queue.size();
            List<Integer> levelNodeVal = new ArrayList<>();
            for (int i = 0; i < levelSize; i++) {
                if (reverse) {
                    TreeNode node = queue.pollLast();
                    levelNodeVal.add(node.val);
                    if (node.right != null) {
                        queue.addFirst(node.right);
                    }
                    if (node.left != null) {
                        queue.addFirst(node.left);
                    }
                } else {
                    TreeNode node = queue.pollFirst();
                    levelNodeVal.add(node.val);
                    if (node.left != null) {
                        queue.addLast(node.left);
                    }
                    if (node.right != null) {
                        queue.addLast(node.right);
                    }
                }
            }
            reverse = !reverse;
            result.add(levelNodeVal);
        }
        return result;
    }

    /**
     * <a href="https://leetcode.com/problems/binary-tree-level-order-traversal/?envType=study-plan-v2&envId=top-interview-150">102. Binary Tree Level Order Traversal</a>
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int currentSize = 0, nextSize = 1;
        while (!queue.isEmpty()) {
            currentSize = nextSize;
            nextSize = 0;
            List<Integer> levelNodes = new ArrayList<>();
            for (int i = 0; i < currentSize; i++) {
                TreeNode node = queue.poll();
                levelNodes.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                    nextSize++;
                }
                if (node.right != null) {
                    queue.offer(node.right);
                    nextSize++;
                }
            }
            result.add(levelNodes);
        }
        return result;
    }

    /**
     * <a href="https://leetcode.com/problems/average-of-levels-in-binary-tree/?envType=study-plan-v2&envId=top-interview-150">637. Average of Levels in Binary Tree</a>
     * @param root
     * @return
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int currentLevelSize = 0, nextLevelSize = 1;
        while (!queue.isEmpty()) {
            currentLevelSize = nextLevelSize;
            nextLevelSize = 0;
            long sum = 0;
            for (int i = 0; i < currentLevelSize; i++) {
                TreeNode node = queue.poll();
                sum += node.val;
                if (node.left != null) {
                    nextLevelSize++;
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    nextLevelSize++;
                    queue.offer(node.right);
                }
            }
            result.add((double) sum / currentLevelSize);
        }

        return result;
    }


    /**
     * <a href="https://leetcode.com/problems/binary-tree-right-side-view/?envType=study-plan-v2&envId=top-interview-150">199. Binary Tree Right Side View</a>
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int currentLevelSize = 0, nextLevelSize = 1;
        while (!queue.isEmpty()) {
            currentLevelSize = nextLevelSize;
            nextLevelSize = 0;
            for (int i = 0; i < currentLevelSize; i++) {
                TreeNode node = queue.poll();
                if (i == currentLevelSize - 1) {
                    result.add(node.val);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                    nextLevelSize += 1;
                }
                if (node.right != null) {
                    queue.offer(node.right);
                    nextLevelSize += 1;
                }
            }
        }
        return result;
    }

    /**
     * <a href="https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/?envType=study-plan-v2&envId=top-interview-150">236. Lowest Common Ancestor of a Binary Tree</a>
     * @param root All Node.val are unique.
     * p != q
     * p and q will exist in the tree.
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == root || q == root) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        if (right != null) {
            return right;
        }
        return left;
    }

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
