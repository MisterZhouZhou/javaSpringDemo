package com.zw.learning.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class BigDataController {
    public static void main(String[] args) throws IOException {
        // 读取文本内容
        StringBuffer sb = readFile();
        // 取出单词和单词出现的次数存入map
        Map<String,Integer> map = getWord(sb);
        // 根据value对map进行排序
        List<Map.Entry<String , Integer>> list = sortValue(map);
        for (Map.Entry<String , Integer> entry: list){
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
    }


    /**
     * 根据map的value对map进行排序
     * @param map   key：单词；value：出现的次数
     * @return      按倒叙方式排好序的list
     */
    private static List<Map.Entry<String , Integer>> sortValue(Map<String , Integer> map){
        List<Map.Entry<String ,Integer>> list = new ArrayList<>(map.entrySet());
        /**
         * Comparator（接口）是匿名内部类，compare是创建匿名内部类要实现的抽象方法
         * Comparator可看作一个排序器
         */
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            /**
             * 对list进行排序；o1和o2谁在compareTo之前，谁就从list第一位开始取，在compateTo之后的从第二位开始取
             * 当o2小于o1时（也就是返回值为-1时），交换o2和o1的位置
             * @param o1    list从第二位开始取
             * @param o2    list从第一位开始取
             * @return      返回0和1时位置不变，返回-1时交换当前o1和o2的位置
             */
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        return list;
    }

    /**
     * 将StringBuffer中的单词单个取出存入map中，单词作为key，出现的次数作为value
     * @param sb 文本中取出来的内容
     * @return  将内容中的单词作为key，出现次数作为value存好的map
     */
     private static Map<String, Integer> getWord(StringBuffer sb) {
         // 用TreeMap 存入后key就是有序的
         Map<String, Integer> map = new TreeMap<>();
         StringBuffer word = new StringBuffer();
         for (int i=0;i<sb.length();i++) {
             char c = sb.charAt(i);
             if(c != ' '){
                 word.append(c);
             }else{
                 String str = word.toString();
                 if(map.containsKey(str)){
                     Integer value = map.get(str);
                     map.put(str, ++value);
                 }else{
                     map.put(str, 1);
                 }
                 word = new StringBuffer();
             }
         }
         return map;
     }

    /**
     * 从文件中读内容，存入StringBuffer
     * @return 存好内容的StringBuffer
     * @throws IOException
     */
    private static StringBuffer readFile() throws IOException {
        FileReader fis = new FileReader("/Users/zhouwei/Desktop/zw/java/learning/src/main/resources/static/txt/word.txt");
        BufferedReader br = new BufferedReader(fis);
        StringBuffer sb = new StringBuffer();
        String it = br.readLine();
        while (it != null) {
            sb.append(it);
            sb.append(" ");
            it = br.readLine();
        }
        return sb;
    }
}

