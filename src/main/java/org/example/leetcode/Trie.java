package org.example.leetcode;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jialu.yxl
 * @date 06/04/2023 19:24
 */
public class Trie {

    private Trie[] children;
    private boolean isEnd;

    public Trie() {
        children = new Trie[26];
        isEnd = false;
    }

    public void insert(String word) {
        Trie node = this;
        for (int i = 0; i < word.length(); i++) {
            char temp = word.charAt(i);
            if (node.children[temp - 'a'] == null) {
                node.children[temp - 'a'] = new Trie();
            }
            node = node.children[temp - 'a'];
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        Trie node = searchPrefix(word);
        return node != null && node.isEnd;
    }

    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    public Trie searchPrefix(String prefix) {
        Trie node = this;
        for (int i = 0; i < prefix.length(); i++) {
            char temp = prefix.charAt(i);
            if (node.children[temp - 'a'] == null) {
                return null;
            }
            node = node.children[temp - 'a'];
        }
        return node;
    }

    @Data
    public static class AreaFilterDto implements Serializable {

        private static final long serialVersionUID = -6041311879869766296L;

        private String id;

        private String name;

        private boolean include = true;

        private List<Division> divisionList;

        @Data
        public static class Division implements Serializable {

            private static final long serialVersionUID = 1491627359065227481L;

            private Long id;

            private String name;

            /**
             * COUNTRY, PROVINCE, CITY, AREA, TOWN
             */
            private String level;

            /**
             * PART,ALL
             */
            private String childDivisionsType;

            private List<Division> childDivisionList;
        }
    }

    public static void main(String[] args) {
        AreaFilterDto areaFilterDto = new AreaFilterDto();
        areaFilterDto.setId("COMMON-BSG");
        areaFilterDto.setName("北上广");

        List<AreaFilterDto.Division> divisionList = new ArrayList<>();
        areaFilterDto.setDivisionList(divisionList);

        AreaFilterDto.Division division1 = new AreaFilterDto.Division();
        division1.setId(110000L);
        division1.setName("北京");
        division1.setLevel("PROVINCE");
        division1.setChildDivisionsType("ALL");
        divisionList.add(division1);

        AreaFilterDto.Division division2 = new AreaFilterDto.Division();
        division2.setId(310000L);
        division2.setName("上海");
        division2.setLevel("PROVINCE");
        division2.setChildDivisionsType("ALL");
        divisionList.add(division2);

        AreaFilterDto.Division division3 = new AreaFilterDto.Division();
        division3.setId(440000L);
        division3.setName("广东省");
        division3.setLevel("PROVINCE");
        division3.setChildDivisionsType("PART");
        List<AreaFilterDto.Division> childDivisionList = new ArrayList<>();
        AreaFilterDto.Division division4 = new AreaFilterDto.Division();
        division4.setId(440100L);
        division4.setName("广州市");
        division4.setLevel("CITY");
        division4.setChildDivisionsType("ALL");
        childDivisionList.add(division4);
        division3.setChildDivisionList(childDivisionList);
        divisionList.add(division3);

        System.out.println(JSON.toJSONString(areaFilterDto));
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
