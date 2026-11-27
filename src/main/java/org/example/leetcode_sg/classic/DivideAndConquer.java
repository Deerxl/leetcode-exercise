package org.example.leetcode_sg.classic;

public class DivideAndConquer {


    /**
     * <a href="https://leetcode.com/problems/construct-quad-tree/description/?envType=study-plan-v2&envId=top-interview-150">427. Construct Quad Tree</a>
     * Given a n * n matrix grid of 0's and 1's only. We want to represent grid with a Quad-Tree.
     *
     * Return the root of the Quad-Tree representing grid.
     *
     * A Quad-Tree is a tree data structure in which each internal node has exactly four children. Besides, each node has two attributes:
     *
     * val: True if the node represents a grid of 1's or False if the node represents a grid of 0's. Notice that you can assign the val to True or False when isLeaf is False, and both are accepted in the answer.
     * isLeaf: True if the node is a leaf node on the tree or False if the node has four children.
     *
     * We can construct a Quad-Tree from a two-dimensional area using the following steps:
     *
     * If the current grid has the same value (i.e all 1's or all 0's) set isLeaf True and set val to the value of the grid and set the four children to Null and stop.
     * If the current grid has different values, set isLeaf to False and set val to any value and divide the current grid into four sub-grids as shown in the photo.
     * Recurse for each of the children with the proper sub-grid.
     *
     * @param grid n == grid.length == grid[i].length
     *      * n == 2x where 0 <= x <= 6
     * @return
     */
    public Node construct(int[][] grid) {
        int n = grid.length;
        return constructDivide(grid, 0, n - 1, 0, n - 1);
    }


    Node constructDivide(int[][] grid, int iStart, int iEnd, int jStart, int jEnd) {
        if (iStart == iEnd) {
            return new Node(grid[iStart][jStart] == 1, true);
        }

        int iMid = (iStart + iEnd) / 2;
        int jMid = (jStart + jEnd) / 2;
        Node topLeft = constructDivide(grid, iStart, iMid, jStart, jMid);
        Node topRight = constructDivide(grid, iStart, iMid, jMid + 1, jEnd);
        Node bottomLeft = constructDivide(grid, iMid + 1, iEnd, jStart, jMid);
        Node bottomRight = constructDivide(grid, iMid + 1, iEnd, jMid + 1, jEnd);

        boolean allOne = topLeft.val && topRight.val && bottomLeft.val && bottomRight.val;
        boolean allZero = !topLeft.val && !topRight.val && !bottomLeft.val && !bottomRight.val;
        if ((allOne || allZero) && topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf) {
            return new Node(!allZero, true);
        }
        return new Node(true, false, topLeft, topRight, bottomLeft, bottomRight);
    }


    static class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;


        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }
}
