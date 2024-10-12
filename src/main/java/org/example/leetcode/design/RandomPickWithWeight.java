package org.example.leetcode.design;

import java.util.Random;

/**
 * <a href="https://leetcode.cn/problems/random-pick-with-weight/description/">528. Random Pick with Weight</a>
 * You are given a 0-indexed array of positive integers w where w[i] describes the weight of the ith index.
 *
 * You need to implement the function pickIndex(), which randomly picks an index in the range [0, w.length - 1] (inclusive) and returns it. The probability of picking an index i is w[i] / sum(w).
 *
 * For example, if w = [1, 3], the probability of picking index 0 is 1 / (1 + 3) = 0.25 (i.e., 25%), and the probability of picking index 1 is 3 / (1 + 3) = 0.75 (i.e., 75%).
 *
 * Input
 * ["Solution","pickIndex"]
 * [[[1]],[]]
 * Output
 * [null,0]
 * @author xiaolu
 * @date 2024/10/12 09:38
 */
public class RandomPickWithWeight {

    int[] arr;
    Random random;
    int sum;
    int len;

    /**
     * 采用前缀和划分区间
     * @param w 1 <= w.length <= 10^4 1 <= w[i] <= 10^5
     */
    public RandomPickWithWeight(int[] w) {
        len = w.length;
        arr = new int[len];

        arr[0] = w[0];
        for (int i = 1; i < len; i++) {
            arr[i] = arr[i - 1] + w[i];
        }

        sum = arr[len - 1];

        random = new Random();
    }

    /**
     * 用二分求target对应的区间的index
     * @return
     */
    public int pickIndex() {
        int target = random.nextInt(sum) + 1;

        int l = 0, r = len - 1;
        int mid, num;
        while (l <= r) {
            mid = (l + r) / 2;
            num = arr[mid];

            int min = (mid > 0) ? arr[mid - 1] : 0;
            int max = num;

            if (target > min && target <= max) {
                return mid;
            } else if (target <= min) {
                r = mid - 1;
            } else if (target > max) {
                l = mid + 1;
            }
        }

        return l;
    }
}


/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */