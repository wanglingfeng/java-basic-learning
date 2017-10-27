package com.sort.study;

/**
 * 基本思想：选择一个基准元素,通常选择第一个元素或者最后一个元素,
 * 通过一趟扫描，将待排序列分成两部分,一部分比基准元素小,一部分大于等于基准元素,
 * 此时基准元素在其排好序后的正确位置,然后再用同样的方法递归地排序划分的两部分。
 * 
 * 在比较有相同关键字序列的情况下，稳定的排序会将较早出现的元素排在前面，而不会是后面，所以快速排序是不稳定的排序算法
 * 
 * Created by lfwang on 2017/10/26.
 */
public class QuickSortTest {
    
    public static void main(String... args) {
        int[] arr = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 1, 8};
        System.out.println("排序之前：");
        for (int i : arr) {
            System.out.print(i + " ");
        }

        System.out.println();
        quickSort(arr, 0, arr.length - 1);

        System.out.println("排序之后：");
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
    
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int middle = partition(arr, low, high); // 将list数组进行一分为二     
            quickSort(arr, low, middle - 1); // 对低字表进行递归排序     
            quickSort(arr, middle + 1, high); // 对高字表进行递归排序     

        }
    }
    
    public static int partition(int[] arr, int low, int high) {
        int tmp = arr[low]; // 数组的第一个作为中轴

        while (low < high) {
            // 从后往前比较
            while (low < high && arr[high] >= tmp) { // 如果没有比关键值小的，比较下一个，直到有比关键值小的交换位置，然后又从前往后比较
                high--;
            }
            arr[low] = arr[high]; // 比中轴小的记录移到低端

            // 从前往后比较
            while (low < high && arr[low] <= tmp) { // 如果没有比关键值大的，比较下一个，直到有比关键值大的交换位置
                low++;
            }
            arr[high] = arr[low]; // 比中轴大的记录移到高端 
        }

        arr[low] = tmp; // 中轴记录到尾 
        return low; // 返回中轴的位置
    }
}
