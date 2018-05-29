package com.zfwhub.algorithm.acm.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * http://poj.org/problem?id=3666
 * https://en.wikipedia.org/wiki/Isotonic_regression
 */
public class MakingTheGrade {

    public static GradeResult makeGrade(int[] roads) {
        if (roads.length == 1) {
            GradeResult gr = new GradeResult();
            gr.upIntList.add(roads[0]);
            gr.downIntList.add(roads[0]);
            return gr;
        }
        if (roads.length == 2) {
            GradeResult gr = new GradeResult();
            if (roads[0] <= roads[1]) {
                gr.upIntList.add(roads[0]);
                gr.upIntList.add(roads[1]);
                gr.downIntList.add(roads[1]);
                gr.downIntList.add(roads[1]);
            } else {
                gr.upIntList.add(roads[1]);
                gr.upIntList.add(roads[1]);
                gr.downIntList.add(roads[0]);
                gr.downIntList.add(roads[1]);
            }
            return gr;
        }
        int lastNum = roads[roads.length - 1];
        int[] subRoads = Arrays.copyOfRange(roads, 0, roads.length - 1);
        GradeResult gr = makeGrade(subRoads);
        //Check upIntList
        //list1 try to use upIntList last number
        List<Integer> list1 = new ArrayList<Integer>();
        for (int i = 0; i < gr.upIntList.size(); i++) {
            list1.add(gr.upIntList.get(i));
        }
        list1.add(gr.upIntList.get(gr.upIntList.size() - 1));
        //list2 close to roads last number
        List<Integer> list2 = new ArrayList<Integer>();
        for (int i = 0; i < gr.upIntList.size(); i++) {
            if (gr.upIntList.get(i) >= lastNum) {
                list2.add(lastNum);
            } else {
                list2.add(gr.upIntList.get(i));
            }
        }
        list2.add(lastNum);
        if (getGap(roads, list1) >= getGap(roads, list2)) {
            gr.upIntList = list2;
        } else {
            gr.upIntList = list1;
        }
        //check downIntList
        List<Integer> list3 = new ArrayList<Integer>();
        for (int i = 0; i < gr.downIntList.size(); i++) {
            list3.add(gr.downIntList.get(i));
        }
        list3.add(gr.downIntList.get(gr.downIntList.size() - 1));
        List<Integer> list4 = new ArrayList<Integer>();
        for (int i = 0; i < gr.downIntList.size(); i++) {
            if (gr.downIntList.get(i) < lastNum) {
                list4.add(lastNum);
            } else {
                list4.add(gr.downIntList.get(i));
            }
        }
        list4.add(lastNum);
        if (getGap(roads, list3) >= getGap(roads, list4)) {
            gr.downIntList = list4;
        } else {
            gr.downIntList = list3;
        }
        return gr;
    }

    public static int getGap(int[] roads, List<Integer> grades) {
        int gap = 0;
        for (int i = 0; i < grades.size(); i++) {
            gap += Math.abs(grades.get(i) - roads[i]);
        }
        return gap;
    }

    public static void main(String[] args) {
        int[] roads = new int[] { 95, 94, 94, 95, 95, 95, 95, 95, 97, 97, 97, 95, 97, 98, 99, 95, 95, 95, 95, 95 };
        GradeResult gr = makeGrade(roads);
        System.out.println(gr.downIntList);
        System.out.println(gr.upIntList);
        System.out.println(Math.min(getGap(roads, gr.downIntList), getGap(roads, gr.upIntList)));
    }

    private static class GradeResult {
        public List<Integer> upIntList = new ArrayList<Integer>();//your grading up list
        public List<Integer> downIntList = new ArrayList<Integer>();//your grading down list
    }

}
