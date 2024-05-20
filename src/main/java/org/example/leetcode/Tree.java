package org.example.leetcode;

import java.lang.management.LockInfo;
import java.util.*;

/**
 * @author jialu.yxl
 * @date 08/02/2023 16:52
 */
public class Tree {

    public static void main(String[] args) {

        TreeNode root = TreeNode.buildTree(Arrays.asList(3,9,20,null,null,15,7));
        System.out.println(postorderTraversal1(root));
        // deleteNode(root, 3);
    }

    /**
     * <a href="https://leetcode.cn/problems/delete-node-in-a-bst/">450. 删除二叉搜索树中的节点</a>
     * Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.
     * Basically, the deletion can be divided into two stages:
     * Search for a node to remove.
     * If the node is found, delete the node.
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        if (root.val == key) {
            return deleteNode(root);
        }

        TreeNode dump = root;
        while (dump != null) {
            if (dump.left != null && dump.left.val == key) {
                dump.left = deleteNode(dump.left);
                break;
            }
            if (dump.right != null && dump.right.val == key) {
                dump.right = deleteNode(dump.right);
                break;
            }
            if (dump.val < key) {
                dump = dump.right;
            } else if (dump.val > key) {
                dump = dump.left;
            }
        }

        return root;
    }



    public TreeNode deleteNode(TreeNode root) {
        TreeNode left = root.left;
        TreeNode right = root.right;
        if (left == null) {
            return right;
        } else if (right == null) {
            return left;
        } else {
            if (right.left == null) {
                right.left = left;
                return right;
            } else {
                root = right;
                TreeNode pre = root;
                while (root.left != null) {
                    root = root.left;
                    if (root.left != null) {
                        pre = root;
                    }
                }
                pre.left = null;
                root.left = left;
                pre = root;
                while (pre.right != null) {
                    pre = pre.right;
                }
                pre.right = right;

                return root;
            }
        }
    }

    /**
     * <a href="https://leetcode.cn/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof/">LCR 174. 寻找二叉搜索树中的目标节点</a>
     * 某公司组织架构以二叉搜索树形式记录，节点值为处于该职位的员工编号。请返回第 cnt 大的员工编号。
     * @param root
     * @param cnt
     * @return
     */
    public int findTargetNode(TreeNode root, int cnt) {
        findTargetNodeCnt = cnt;
        findTargetNodeInOrder(root);
        return findTargetNodeResult;
    }

    int findTargetNodeResult, findTargetNodeCnt;
    private void findTargetNodeInOrder(TreeNode root) {
        if (root == null || findTargetNodeCnt == 0) {
            return;
        }

        findTargetNodeInOrder(root.right);
        findTargetNodeCnt--;
        if (findTargetNodeCnt == 0) {
            findTargetNodeResult = root.val;
            return;
        }
        findTargetNodeInOrder(root.left);
    }

    /**
     * <a href="https://leetcode.cn/problems/binary-tree-postorder-traversal/">145. Binary Tree Postorder Traversal</a>
     * Given the root of a binary tree, return the postorder traversal of its nodes' values
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();
            if (root.right == null || pre == root.right) {
                result.add(root.val);
                pre = root;
                root = null;
            } else {
                stack.push(root);
                root = root.right;
            }
        }

        return result;
    }

    /**
     * <a href="https://leetcode.cn/problems/check-completeness-of-a-binary-tree/">958. Check Completeness of a Binary Tree</a>
     * Given the root of a binary tree, determine if it is a complete binary tree.
     * In a complete binary tree, every level, except possibly the last, is completely filled,
     * and all nodes in the last level are as far left as possible.
     * It can have between 1 and 2h nodes inclusive at the last level h.
     * @param root
     * @return
     */
    public boolean isCompleteTree(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        root.val = 1;
        queue.offer(root);
        int index = 1;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.val != index) {
                return false;
            }
            index++;
            if (node.left != null) {
                node.left.val = node.val * 2;
                queue.offer(node.left);
            }
            if (node.right != null) {
                node.right.val = node.val * 2 + 1;
                queue.offer(node.right);
            }
        }
        return true;
    }

    /**
     * <a href="https://leetcode.cn/problems/invert-binary-tree/">226. 翻转二叉树</a>
     * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = right;
        root.right = left;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    /**
     * <a href="https://leetcode.cn/problems/maximum-width-of-binary-tree/">662. 二叉树最大宽度</a>
     * 给你一棵二叉树的根节点 root ，返回树的 最大宽度 。
     * 树的 最大宽度 是所有层中最大的 宽度 。
     * 每一层的 宽度 被定义为该层最左和最右的非空节点（即，两个端点）之间的长度。
     * 将这个二叉树视作与满二叉树结构相同，两端点间会出现一些延伸到这一层的 null 节点，这些 null 节点也计入长度。
     * 题目数据保证答案将会在  32 位 带符号整数范围内。
     * @param root
     * @return
     */
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        root.val = 1;
        queue.offer(root);
        int nextLevelCount = 0;
        int curLevelCount = 1;
        int result = 0;
        int tmpResult = 0;
        while (!queue.isEmpty()) {
            for (int i = 0; i < curLevelCount; i++) {
                TreeNode node = queue.poll();
                if (i == 0) {
                    tmpResult = node.val;
                }
                if (i == curLevelCount - 1) {
                    tmpResult = node.val - tmpResult + 1;
                    result = Math.max(result, tmpResult);
                }
                if (node.left != null) {
                    node.left.val = 2 * node.val;
                    queue.offer(node.left);
                    nextLevelCount++;
                }
                if (node.right != null) {
                    node.right.val = 2 * node.val + 1;
                    queue.offer(node.right);
                    nextLevelCount++;
                }
            }

            curLevelCount = nextLevelCount;
            nextLevelCount = 0;
        }
        return result;
    }

    /**
     * <a href="https://leetcode.cn/problems/path-sum/">112. 路径总和</a>
     * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum 。
     * 判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。如果存在，返回 true ；否则，返回 false 。
     * 叶子节点 是指没有子节点的节点。
     * @param root
     * @param targetSum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        if (root.val == targetSum && root.left == null && root.right == null) {
            return true;
        }

        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    /**
     * <a href="https://leetcode.cn/problems/path-sum-ii/">113. 路径总和 II</a>
     * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
     * 叶子节点 是指没有子节点的节点。
     * @param root
     * @param targetSum
     * @return
     */
    List<List<Integer>> pathSumResult = new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        pathSum(root, targetSum, new ArrayList<>());

        return pathSumResult;
    }

    private void pathSum(TreeNode root, int targetSum, List<Integer> list) {
        if (root == null) {
            return;
        }

        List<Integer> newList = new ArrayList<>(list);
        newList.add(root.val);
        if (root.val == targetSum && root.left == null && root.right == null) {
            pathSumResult.add(newList);
            return;
        }

        if (root.left != null) {
            pathSum(root.left, targetSum - root.val, newList);
        }

        if (root.right != null) {
            pathSum(root.right, targetSum - root.val, newList);
        }
    }

    /**
     * <a href="https://leetcode.cn/problems/validate-binary-search-tree/">98. 验证二叉搜索树</a>
     * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
     * 有效 二叉搜索树定义如下：
     * 节点的左子树只包含 小于 当前节点的数。
     * 节点的右子树只包含 大于 当前节点的数。
     * 所有左子树和右子树自身必须也是二叉搜索树。
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
        // return isValidBST(root.left, null, root.val) && isValidBST(root.right, root.val, null);
    }

    public boolean isValidBST(TreeNode root, Integer minVal, Integer maxVal) {
        if (root == null) {
            return true;
        }
        if (maxVal != null && root.val >= maxVal) {
            return false;
        }
        if (minVal != null && root.val <= minVal) {
            return false;
        }

        return isValidBST(root.left, minVal, root.val) && isValidBST(root.right, root.val, maxVal);
    }

    /**
     * <a href="https://leetcode.cn/problems/diameter-of-binary-tree/">543. 二叉树的直径</a>
     * 给你一棵二叉树的根节点，返回该树的 直径 。
     * 二叉树的 直径 是指树中任意两个节点之间最长路径的 长度 。这条路径可能经过也可能不经过根节点 root 。
     * 两节点之间路径的 长度 由它们之间边数表示。
     * @param root
     * @return
     */
    int diameterOfBinaryTreeResult = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        diameterOfBinaryTreeFunc(root);
        return diameterOfBinaryTreeResult;
    }

    public int diameterOfBinaryTreeFunc(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return 0;
        }

        int left = 0, right = 0;
        if (root.left != null) {
            left = diameterOfBinaryTreeFunc(root.left) + 1;
        }
        if (root.right != null) {
            right = diameterOfBinaryTreeFunc(root.right) + 1;
        }

        if (left > 0 && right > 0) {
            diameterOfBinaryTreeResult = Math.max(diameterOfBinaryTreeResult, left + right);
        }

        int result = Math.max(left, right);
        diameterOfBinaryTreeResult = Math.max(diameterOfBinaryTreeResult, result);

        return result;
    }

    /**
     * <a href="https://leetcode.cn/problems/binary-tree-preorder-traversal/">144. 二叉树的前序遍历</a>
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }

        return result;
    }

    /**
     * <a href="https://leetcode.cn/problems/balanced-binary-tree/">110. 平衡二叉树</a>
     * 给定一个二叉树，判断它是否是平衡二叉树
     * 平衡二叉树 是指该树所有节点的左右子树的深度相差不超过 1。
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        return isBalancedHeight(root) >= 0;
    }

    private int isBalancedHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = isBalancedHeight(root.left);
        int rightHeight = isBalancedHeight(root.right);

        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }

        return Math.max(leftHeight, rightHeight) + 1;
    }


    public boolean isBalancedTopToBottom(TreeNode root) {
        if (root == null) {
            return true;
        }

        if (Math.abs(isBalancedHeightTopToBottom(root.left, 0) - isBalancedHeightTopToBottom(root.right, 0)) > 1) {
            return false;
        }

        return isBalancedTopToBottom(root.left) && isBalancedTopToBottom(root.right);
    }

    private int isBalancedHeightTopToBottom(TreeNode root, int curHeight) {
        if (root == null) {
            return curHeight;
        }

        return Math.max(isBalancedHeightTopToBottom(root.left, curHeight + 1),
                isBalancedHeightTopToBottom(root.right, curHeight + 1));
    }

    /**
     * <a href="https://leetcode.cn/problems/symmetric-tree/">101. 对称二叉树</a>
     * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode left, TreeNode right) {
        if (right == null && left == null) {
            return true;
        }
        if (right == null || left == null) {
            return false;
        }

        if (left.val != right.val) {
            return false;
        }

        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }

    /**
     * <a href="https://leetcode.cn/problems/maximum-depth-of-binary-tree/">104. 二叉树的最大深度</a>
     * 给定一个二叉树 root ，返回其最大深度。
     *
     * 二叉树的 最大深度 是指从根节点到最远叶子节点的最长路径上的节点数。
     * @param root
     * @return
     */
    int maxDepthResult = 0;
    public int maxDepth(TreeNode root) {
        maxDepth(root, 0);
        return maxDepthResult;
    }

    private void maxDepth(TreeNode root, int curDepth) {
        if (root == null) {
            maxDepthResult = Math.max(maxDepthResult, curDepth);
            return;
        }

        maxDepth(root.left, curDepth + 1);
        maxDepth(root.right, curDepth + 1);
    }

    /**
     * <a href="https://leetcode.cn/problems/sum-root-to-leaf-numbers/">129. 求根节点到叶节点数字之和</a>
     * 给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
     * 每条从根节点到叶节点的路径都代表一个数字：
     *
     * 例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
     * 计算从根节点到叶节点生成的 所有数字之和 。
     *
     * 叶节点 是指没有子节点的节点。
     * @param root
     * @return
     */
    List<Integer> sumNumbersList = new ArrayList<>();
    public int sumNumbers(TreeNode root) {
        List<Integer> nums = new ArrayList<>();
        nums.add(0);
        sumNumbers(root, nums);

        int result = 0;
        for (Integer num : sumNumbersList) {
            result += num;
        }

        return result;
    }

    private void sumNumbers(TreeNode root, List<Integer> nums) {
        List<Integer> newNums = new ArrayList<>();
        for (Integer num : nums) {
            newNums.add(num * 10 + root.val);
        }

        if (root.left == null && root.right == null) {
            sumNumbersList.addAll(newNums);
        }

        if (root.left != null) {
            sumNumbers(root.left, newNums);
        }

        if (root.right != null) {
            sumNumbers(root.right, newNums);
        }
    }

    /**
     * <a href="https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/">105. 从前序与中序遍历序列构造二叉树</a>
     * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
     * preorder 和 inorder 均 无重复 元素
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTreeNew(int[] preorder, int[] inorder) {
        return buildTreeNew(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    private TreeNode buildTreeNew(int[] preorder, int[] inorder, int preStart, int preEnd, int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        int leftCount = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (root.val == inorder[i]) {
                break;
            }
            leftCount++;
        }

        root.left = buildTreeNew(preorder, inorder, preStart + 1, preStart + leftCount, inStart, inStart + leftCount - 1);

        root.right = buildTreeNew(preorder, inorder, preStart + leftCount + 1, preEnd, inStart + leftCount + 1, inEnd);
        return root;
    }

    /**
     * <a href="https://leetcode.cn/problems/binary-tree-right-side-view/">199. 二叉树的右视图</a>
     * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
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
        int nextLevelCount = 1;
        int curLevelCount = 0;
        while (!queue.isEmpty()) {
            curLevelCount = nextLevelCount;
            nextLevelCount = 0;
            while (curLevelCount-- > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    nextLevelCount++;
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    nextLevelCount++;
                    queue.offer(node.right);
                }
                if (curLevelCount == 0) {
                    result.add(node.val);
                }
            }
        }

        return result;
    }


    /**
     * <a href="https://leetcode.cn/problems/binary-tree-inorder-traversal/">94. 二叉树的中序遍历</a>
     * @param root
     * @return
     */
    List<Integer> inorderTraversalResult = new ArrayList<>();

    /**
     * Morris 中序遍历
     * @param root
     * @return
     */
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();

        TreeNode pre;
        while (root != null) {
            if (root.left != null) {
                pre = root.left;
                while (pre.right != null && pre.right != root) {
                    pre = pre.right;
                }

                if (pre.right == null) {
                    pre.right = root;
                    root = root.left;
                } else {
                    pre.right = null;
                    res.add(root.val);
                    root = root.right;
                }
            } else {
                res.add(root.val);
                root = root.right;
            }
        }

        return res;
    }

    /**
     * 中序遍历-迭代
     * @param root
     * @return
     */
    public List<Integer> inorderTraversalStack(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Stack<TreeNode> stack = new Stack<>();

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();
            result.add(root.val);

            root = root.right;
        }

        return result;
    }

    /**
     * 中序遍历-递归
     * @param root
     */
    public void inorderTraversalExecute(TreeNode root) {
        if (root == null) {
            return;
        }

        if (root.left != null) {
            inorderTraversal(root.left);
        }

        inorderTraversalResult.add(root.val);

        if (root.right != null) {
            inorderTraversal(root.right);
        }
    }

    /**
     * <a href="https://leetcode.cn/problems/binary-tree-maximum-path-sum/">124. 二叉树中的最大路径和</a>
     * 二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
     * 路径和 是路径中各节点值的总和。
     * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
     * @param root
     * @return
     */
    public int maxPathSum(TreeNode root) {
        maxPathSumExecute(root);
        return maxPathSumMaxResult;
    }

    int maxPathSumMaxResult = Integer.MIN_VALUE;
    private int maxPathSumExecute(TreeNode root) {
        int leftMax = 0, rightMax = 0;
        if (root.left != null) {
            leftMax = Math.max(0, maxPathSumExecute(root.left));
        }
        if (root.right != null) {
            rightMax = Math.max(0, maxPathSumExecute(root.right));
        }

        int tempMax = root.val + leftMax + rightMax;
        maxPathSumMaxResult = Math.max(tempMax, maxPathSumMaxResult);

        return root.val + Math.max(leftMax, rightMax);
    }


    /**
     * <a href="https://leetcode.cn/problems/binary-tree-zigzag-level-order-traversal/">103. 二叉树的锯齿形层序遍历</a>
     * @param root
     * @return
     */
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        boolean leftToRight = true;
        List<TreeNode> curLevelList = new ArrayList<>();
        curLevelList.add(root);
        List<TreeNode> nextLevelList = new ArrayList<>();
        int curLevelSize = curLevelList.size();
        List<Integer> tempResult = new ArrayList<>();
        while (curLevelSize > 0) {
            for (int i = 0; i < curLevelSize; i++) {
                TreeNode node = curLevelList.get(i);

                if (leftToRight) {
                    tempResult.add(node.val);
                } else {
                    tempResult.add(0, node.val);
                }

                if (node.left != null) {
                    nextLevelList.add(node.left);
                }
                if (node.right != null) {
                    nextLevelList.add(node.right);
                }
            }
            leftToRight = !leftToRight;
            curLevelList = nextLevelList;
            curLevelSize = curLevelList.size();
            result.add(tempResult);
            tempResult = new ArrayList<>();
            nextLevelList = new ArrayList<>();
        }

        return result;
    }


    /**
     * <a href="https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/">236. 二叉树的最近公共祖先</a>
     * 对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == p || root == q) {
            return root;
        }

        if (root == null) {
            return null;
        }

        if (lowestCommonAncestorExist(root.left, p, q) && lowestCommonAncestorExist(root.right, p, q)) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        if (left != null) {
            return left;
        }

        return lowestCommonAncestor(root.right, p, q);
    }

    public boolean lowestCommonAncestorExist(TreeNode root, TreeNode p, TreeNode q) {
        if (root == p || root == q) {
            return true;
        }

        if (root == null) {
            return false;
        }

        return lowestCommonAncestorExist(root.left, p, q) || lowestCommonAncestorExist(root.right, p, q);
    }


    /**
     * <a href="https://leetcode.cn/problems/binary-tree-level-order-traversal/">102. 二叉树的层序遍历</a>
     * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            List<Integer> lineList = new ArrayList<>();
            Queue<TreeNode> tempQueue = new ArrayDeque<>();
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                lineList.add(node.val);

                if (node.left != null) {
                    tempQueue.add(node.left);
                }
                if (node.right != null) {
                    tempQueue.add(node.right);
                }
            }

            result.add(lineList);
            queue.addAll(tempQueue);
        }

        return result;
    }

    /**
     * <a href="https://leetcode.cn/problems/zhong-jian-er-cha-shu-lcof/description/">剑指 Offer 07. 重建二叉树</a>
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    public TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        if (preStart > preEnd) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[preStart]);
        int inOrderLeftEnd = inStart;
        while (inorder[inOrderLeftEnd] != preorder[preStart]) {
            inOrderLeftEnd++;
        }
        inOrderLeftEnd--;
        int leftCount = inOrderLeftEnd - inStart + 1;
        if (leftCount > 0) {
            root.left = buildTree(preorder, preStart + 1, preStart + leftCount, inorder, inStart, inOrderLeftEnd);
        }

        root.right = buildTree(preorder, preStart + leftCount + 1, preEnd, inorder, inOrderLeftEnd + 2, inEnd);

        return root;
    }

    /**
     * <a href="https://leetcode.cn/problems/subtree-of-another-tree/">572. 另一棵树的子树</a>
     * @param root
     * @param subRoot
     * @return
     */
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        return isSubtree(root, subRoot, false);
    }

    private boolean isSubtree(TreeNode root, TreeNode subRoot, boolean complete) {
        if (root == null) {
            return subRoot == null;
        } else {
            if (subRoot == null) {
                return false;
            }
        }
        if (complete) {
            return root.val == subRoot.val
                    && isSubtree(root.left, subRoot.left, true)
                    && isSubtree(root.right, subRoot.right, true);
        } else {
            if (root.val == subRoot.val
                    && isSubtree(root.left, subRoot.left, true)
                    && isSubtree(root.right, subRoot.right, true)) {
                return true;
            }
            return isSubtree(root.left, subRoot, false) || isSubtree(root.right, subRoot, false);
        }
    }


    /**
     * <a href="https://leetcode.cn/problems/kth-smallest-element-in-a-bst/">230. 二叉搜索树中第K小的元素</a>
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        inOrderFindMin(root, k);
        return smallestK;
    }

    private void inOrderFindMin(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        inOrderFindMin(root.left, k);

        curIndex++;
        if (curIndex == k) {
            smallestK = root.val;
            return;
        }

        inOrderFindMin(root.right, k);
    }

    int curIndex = 0;
    int smallestK = -1;

    /**
     * <a href="https://leetcode.cn/problems/delete-node-in-a-bst/">450. 删除二叉搜索树中的节点</a>
     * @param root
     * @param key
     * @return
     */
    public static TreeNode deleteNode1(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        if (root.val > key) {
            root.left = deleteNode1(root.left, key);
            return root;
        } else if (root.val < key) {
            root.right = deleteNode1(root.right, key);
            return root;
        }

        if (root.left == null) {
            return root.right;
        } else if (root.right == null) {
            return root.left;
        }

        TreeNode right = root.right;
        while (right.left != null) {
            right = right.left;
        }
        root.right = deleteNode1(root.right, right.val);
        right.right = root.right;
        right.left = root.left;

        return right;
    }


    static List<Integer> posOrderList = new ArrayList<>();
    public static List<Integer> postorderTraversal1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.empty()) {
            TreeNode cur = stack.lastElement();
            result.add(cur.val);
            stack.removeElementAt(stack.size() - 1);

            if (cur.left != null) {
                stack.add(cur.left);
            }
            if (cur.right != null) {
                stack.add(cur.right);
            }
        }
        Collections.reverse(result);
        return result;
    }

    static void postOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        posOrderList.add(root.val);
    }

    /**
     * <a href="https://leetcode.cn/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof/">剑指 Offer 54. 二叉搜索树的第k大节点</a>
     * @param root
     * @param k
     * @return
     */
    public int kthLargest(TreeNode root, int k) {
        inOrder(root);
        return list.get(list.size() - k);
    }

    List<Integer> list = new ArrayList<>();

    void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        list.add(root.val);
        inOrder(root.right);
    }
}
