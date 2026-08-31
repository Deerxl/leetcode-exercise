package org.example.leetcode_sg.classic;

import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class dfs {

    public static void main(String[] args) {
        // System.out.println(mostBooked(3, new int[][]{{3, 7},{12, 19},{16, 17},{1, 17},{5, 6}}));
        List<List<String>> list = Lists.newArrayList(Lists.newArrayList("A", "bce"), Lists.newArrayList("B", "ace"), Lists.newArrayList("C", "abc%B%"));
        System.out.println(applySubstitutions(list, "%A%_%B%_%C%"));
    }

    /**
     * <a href="https://github.com/doocs/leetcode/blob/main/solution/3400-3499/3481.Apply%20Substitutions/README_EN.md#3481-apply-substitutions-">3481. Apply Substitutions 🔒</a>
     *
     * @param replacements  a replacements mapping
     * @param text a text string that may contain placeholders formatted as %var%, where each var corresponds to a key in the replacements mapping. Each replacement value may itself contain one or more such placeholders. Each placeholder is replaced by the value associated with its corresponding replacement key.
     * @return Return the fully substituted text string which does not contain any placeholders.
     */
    public static Map<String, String> replacementMap = new HashMap<>();

    public static String applySubstitutions(List<List<String>> replacements, String text) {
        for (List<String> item : replacements) {
            replacementMap.put(item.get(0), item.get(1));
        }
        return dfsApplySubstitutions(text);
    }

    public static String dfsApplySubstitutions(String text) {
        int i = text.indexOf("%");
        if (i == -1) {
            return text;
        }
        int j = text.indexOf("%", i + 1);
        if (j == -1) {
            return text;
        }

        return text.substring(0, i) + dfsApplySubstitutions(replacementMap.getOrDefault(text.substring(i + 1, j), ""))
                + dfsApplySubstitutions(text.substring(j + 1));
    }
}
