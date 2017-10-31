package com.search.study;

/**
 * 二分查找
 * 
 * 又称折半查找，它是一种效率较高的查找方法。 
 * 要求: 必须采用顺序存储结构并有序排列
 * 
 * Created by lfwang on 2017/10/31.
 */
public class BinarySearchTest {
    
    public static void main(String... args) {
        int[] src = new int[] {0, 1, 3, 5, 7, 8, 9};
        
        System.out.println(binarySearch(src, 3));
        System.out.println(binarySearch(src, 3, 0, src.length-1));
    }

    /**
     * 二分查找
     * @param srcArray 有序数组
     * @param des 查找元素
     * @return
     */
    public static int binarySearch(int[] srcArray, int des) {
        int low = 0;
        int high = srcArray.length - 1;
        
        while (low <= high) {
            int middle = (low + high) / 2;
            
            if (des < srcArray[middle]) {
                high = middle - 1;
            } else if (des > srcArray[middle]) {
                low = middle + 1;
            } else {
                return middle;
            }
        }
        
        return -1;
    }

    /**
     * 二分查找递归
     * @param srcArray
     * @param des
     * @param start
     * @param end
     * @return
     */
    public static int binarySearch(int[] srcArray, int des, int start, int end) {
        int middle = (start + end) / 2;

        if (des < srcArray[start] || des > srcArray[end]) return -1;
        if (start > end) return -1;
        
        if (des < srcArray[middle]) {
           return binarySearch(srcArray, des, start, middle - 1);
        } else if (des > srcArray[middle]) {
            return binarySearch(srcArray, des, middle + 1, end);
        } else {
            return middle;
        }
    }
}
