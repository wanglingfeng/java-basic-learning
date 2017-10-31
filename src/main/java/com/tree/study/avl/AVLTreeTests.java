package com.tree.study.avl;

import java.util.Iterator;

/**
 * Created by lfwang on 2017/10/24.
 */
public class AVLTreeTests {
    
    public static void main(String... args) {
        AVLTree<Integer> tree = new AVLTree<Integer>();

        System.out.println("------ 添加 ------");
        tree.add(50);
        tree.add(66);
        
        for (int i = 0; i < 10; i++) {
            int ran = (int) (Math.random() * 100);
            tree.add(ran);
        }

        System.out.println("------ 删除 ------");
        tree.remove(50);
        tree.remove(66);

        System.out.println();

        Iterator<Integer> it = tree.iterator();
        
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
