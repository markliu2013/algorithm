package com.zfwhub.algorithm.templates.dp;

import java.util.*;

/**
 * https://www.jianshu.com/p/e200805306db
 * https://www.jianshu.com/p/9f0b3ff4fb9c
 */
public class GoldMineMain {

    /**
     * brute force, refer math, getAllCombination
     */
    public int getMaxGold3(List<GoldMine> goldMines, int maxPerson) {
        int maxAmount = 0;
        List<GoldMine> maxList = null;
        List<List<GoldMine>> goldMinesCom = getAllCombination(goldMines);
        for (Iterator<List<GoldMine>> iterator = goldMinesCom.iterator(); iterator.hasNext();) {
            List<GoldMine> list = iterator.next();
            int persons = 0;
            int amount = 0;
            for (Iterator<GoldMine> iterator2 = list.iterator(); iterator2.hasNext();) {
                GoldMine goldMine = iterator2.next();
                persons += goldMine.getPerson();
                amount += goldMine.getAmount();
            }
            if (persons <= maxPerson) {
                if (maxAmount < amount) {
                    maxAmount = amount;
                    maxList = list;
                }
            }
        }
        System.out.println(maxList);
        return maxAmount;
    }

    /**
     * Dynamic Programming, top to down, Simple Recursive Solution
     */
    public int getMaxGold(List<GoldMine> goldMines, int maxPerson) {
        if (goldMines == null || goldMines.isEmpty()) {
            return 0;
        }
        if (goldMines.size() == 1) {
            if (maxPerson < goldMines.get(0).getPerson()) {
                return 0;
            } else {
                return goldMines.get(0).getAmount();
            }
        }
        GoldMine gm = goldMines.get(goldMines.size() - 1);
        List<GoldMine> subList = goldMines.subList(0, goldMines.size() - 1);
        int lastPerson = gm.getPerson();
        int lastAmount = gm.getAmount();
        if (maxPerson < lastPerson) {
            return getMaxGold(subList, maxPerson);
        } else {
            return Math.max(getMaxGold(subList, maxPerson), getMaxGold(subList, maxPerson - lastPerson) + lastAmount);
        }
    }

    /**
     * dynamic programming, Iterative Bottom-Up Solution
     */
    public int getMaxGold2(List<GoldMine> goldMines, int maxPerson) {
        if (goldMines == null || goldMines.isEmpty()) {
            return 0;
        }
        // fill the first row
        GoldMine firstGoldMine = goldMines.get(0);
        int[] preResult = new int[maxPerson];
        for (int i = 0; i < preResult.length; i++) {
            if (i + 1 < firstGoldMine.getPerson()) {
                preResult[i] = 0;
            } else {
                preResult[i] = firstGoldMine.getAmount();
            }
        }
        int[] currentResult = new int[maxPerson];
        for (int i = 1; i < goldMines.size(); i++) {
            GoldMine goldMine = goldMines.get(i);
            currentResult = new int[maxPerson];
            for (int j = 1; j <= maxPerson; j++) {
                if (j > goldMine.getPerson()) {
                    currentResult[j - 1] = Math.max(preResult[j - 1], goldMine.getAmount() + preResult[j - goldMine.getPerson() - 1]);
                } else if (j == goldMine.getPerson()) {
                    currentResult[j - 1] = Math.max(preResult[j - 1], goldMine.getAmount());
                } else {
                    currentResult[j - 1] = preResult[j - 1];
                }
            }
            // TODO 为什么这里不用深度复制。
            preResult = currentResult;
        }
        return currentResult[maxPerson - 1];
    }

    public List<List<GoldMine>> getAllCombination(List<GoldMine> goldMines) {
        List<List<GoldMine>> list = new ArrayList<List<GoldMine>>();
        int count = new Double(Math.pow(2.0, goldMines.size())).intValue();
        for (int i = 0; i < count; i++) {
            List<GoldMine> list1 = new ArrayList<GoldMine>();
            String binaryString = String.format("%3s", Integer.toBinaryString(i)).replace(' ', '0');
            char[] chars = binaryString.toCharArray();
            for (int j = 0; j < chars.length; j++) {
                if (chars[j] == '1') {
                    list1.add(goldMines.get(j));
                }
            }
            list.add(list1);
        }
        return list;
    }

    public static void main(String[] args) {
        GoldMine goldMine1 = new GoldMine(400, 5);
        GoldMine goldMine2 = new GoldMine(500, 5);
        GoldMine goldMine3 = new GoldMine(200, 3);
        GoldMine goldMine4 = new GoldMine(300, 4);
        GoldMine goldMine5 = new GoldMine(350, 3);
        List<GoldMine> goldMines = new ArrayList<GoldMine>();
        goldMines.add(goldMine1);
        goldMines.add(goldMine2);
        goldMines.add(goldMine3);
        goldMines.add(goldMine4);
        goldMines.add(goldMine5);
        GoldMineMain gm = new GoldMineMain();
        int result = gm.getMaxGold2(goldMines, 10);
        System.out.println(result);
    }

    private static class GoldMine {

        private Integer amount;
        private Integer person;

        public GoldMine(Integer amount, Integer person) {
            this.amount = amount;
            this.person = person;
        }

        public Integer getAmount() {
            return amount;
        }

        public Integer getPerson() {
            return person;
        }

        @Override
        public String toString() {
            return "amount:" + amount + ", person:" + person;
        }
    }
}
