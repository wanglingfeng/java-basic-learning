package com.sort.study;

/**
 * 基本思想：选择一个基准元素,通常选择第一个元素或者最后一个元素,通过一趟扫描，
 * 将待排序列分成两部分,一部分比基准元素小,一部分大于等于基准元素,
 * 此时基准元素在其排好序后的正确位置,然后再用同样的方法递归地排序划分的两部分。
 * 
 * Created by lfwang on 2017/10/26.
 */
public class BubbleSortTest {
    
    public static void main(String... args) {
        int[] arr = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 1, 8};
        System.out.println("排序之前：");
        for (int i : arr) {
            System.out.print(i + " ");
        }

        System.out.println();
        bubbleSort(arr);

        System.out.println("排序之后：");
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
    
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
    }
}
