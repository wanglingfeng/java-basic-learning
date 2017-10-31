package com.tree.study.rb;

/**
 * 特性：
 * (1) 每个节点或者是黑色，或者是红色。
 * (2) 根节点是黑色。
 * (3) 每个叶子节点是黑色。 [注意：这里叶子节点，是指为空的叶子节点！]
 * (4) 如果一个节点是红色的，则它的子节点必须是黑色的。
 * (5) 从一个节点到该节点的子孙节点的所有路径上包含相同数目的黑节点。
 * 
 * Created by lfwang on 2017/10/24.
 */
public class RBTree<T extends Comparable<T>> {
    
    private RBTNode<T> mRoot; // 根节点
    
    private static final boolean RED = false;
    private static final boolean BLACK = true;

    public RBTree() {
        this.mRoot = null;
    }
    
    /**
     * 新建节点（key），并将其插入到红黑树中
     * 
     * @param key 插入节点的键值
     */
    public void insert(T key) {
        insert(new RBTNode<T>(key));
    }

    public void remove(T key) {
        RBTNode<T> node;

        if ((node = search(key)) != null) {
            remove(node);
        }
    }
    
    public void clear() {
        destory(this.mRoot);
        this.mRoot = null;
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        preOrder(this.mRoot);
    }
    
    private void preOrder(RBTNode<T> node) {
        if (null != node) {
            System.out.print(node.key + " ");
            preOrder(node.left);
            preOrder(node.right);
        } 
    }

    /**
     * 中序遍历
     */
    public void inOrder() {
        inOrder(this.mRoot);
    }

    private void inOrder(RBTNode<T> node) {
        if (null != node) {
            preOrder(node.left);
            System.out.print(node.key + " ");
            preOrder(node.right);
        }
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        postOrder(this.mRoot);
    }

    private void postOrder(RBTNode<T> node) {
        if (null != node) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.key + " ");
        }
    }

    public T minimum() {
        RBTNode<T> p = minimum(mRoot);
        if (p != null)
            return p.key;

        return null;
    }

    /* 
     * 查找最小结点：返回tree为根结点的红黑树的最小结点。
     */
    private RBTNode<T> minimum(RBTNode<T> tree) {
        if (tree == null)
            return null;

        while(tree.left != null)
            tree = tree.left;
        return tree;
    }

    public T maximum() {
        RBTNode<T> p = maximum(mRoot);
        if (p != null)
            return p.key;

        return null;
    }
    
    /* 
     * 查找最大结点：返回tree为根结点的红黑树的最大结点。
     */
    private RBTNode<T> maximum(RBTNode<T> tree) {
        if (tree == null)
            return null;

        while(tree.right != null)
            tree = tree.right;
        return tree;
    }

    public void print() {
        if (null != this.mRoot) print(this.mRoot, this.mRoot.key, 0);
    }

    /**
     * 打印红黑树
     * 
     * @param node
     * @param key 节点的键值
     * @param direction 0，表示该节点是根节点;
     *                  -1，表示该节点是它的父结点的左孩子;
     *                  1，表示该节点是它的父结点的右孩子
     */
    private void print(RBTNode<T> node, T key, int direction) {
        if (null == node) return;
        
        if (direction == 0) { // tree是根节点
            System.out.printf("%2d(B) is root\n", node.key);
        } else { // tree是分支节点
            System.out.printf("%2d(%s) is %2d's %6s child\n", node.key, isRed(node) ? "R" : "B", 
                    key, direction == 1 ? "right" : "left");
        }

        print(node.left, node.key, -1);
        print(node.right,node.key,  1);
    }
    
    private void insert(RBTNode<T> node) {
        int cmp;
        RBTNode<T> currentNode = null;
        RBTNode<T> treeNode = this.mRoot;
        
        // 1. 将红黑树当作一颗二叉查找树，将节点添加到二叉查找树中
        while (null != treeNode) {
            currentNode = treeNode;
            cmp = node.key.compareTo(treeNode.key);
            
            if (cmp < 0) {
                treeNode = treeNode.left;
            } else {
                treeNode = treeNode.right;
            }
        }
        
        node.parent = currentNode;
        
        if (null != currentNode) {
            cmp = node.key.compareTo(currentNode.key);
            
            if (cmp < 0) {
                currentNode.left = node;
            } else {
                currentNode.right = node;
            }
        } else {
            this.mRoot = node;
        }
        
        // 2. 将它重新修正为一颗二叉查找树
        insertFixUp(node);
    }

    private void remove(RBTNode<T> node) {
        RBTNode<T> child, parent;
        boolean color;

        // 被删除节点的"左右孩子都不为空"的情况。
        if ( (node.left!=null) && (node.right!=null) ) {
            // 被删节点的后继节点。(称为"取代节点")
            // 用它来取代"被删节点"的位置，然后再将"被删节点"去掉。
            RBTNode<T> replace = node;

            // 获取后继节点
            replace = replace.right;
            while (replace.left != null)
                replace = replace.left;

            // "node节点"不是根节点(只有根节点不存在父节点)
            if (parentOf(node)!=null) {
                if (parentOf(node).left == node)
                    parentOf(node).left = replace;
                else
                    parentOf(node).right = replace;
            } else {
                // "node节点"是根节点，更新根节点。
                this.mRoot = replace;
            }

            // child是"取代节点"的右孩子，也是需要"调整的节点"。
            // "取代节点"肯定不存在左孩子！因为它是一个后继节点。
            child = replace.right;
            parent = parentOf(replace);
            // 保存"取代节点"的颜色
            color = colorOf(replace);

            // "被删除节点"是"它的后继节点的父节点"
            if (parent == node) {
                parent = replace;
            } else {
                // child不为空
                if (child!=null)
                    setParent(child, parent);
                parent.left = child;

                replace.right = node.right;
                setParent(node.right, replace);
            }

            replace.parent = node.parent;
            replace.color = node.color;
            replace.left = node.left;
            node.left.parent = replace;

            if (color == BLACK)
                removeFixUp(child, parent);

            node = null;
            return ;
        }

        if (node.left !=null) {
            child = node.left;
        } else {
            child = node.right;
        }

        parent = node.parent;
        // 保存"取代节点"的颜色
        color = node.color;

        if (child!=null)
            child.parent = parent;

        // "node节点"不是根节点
        if (parent!=null) {
            if (parent.left == node)
                parent.left = child;
            else
                parent.right = child;
        } else {
            this.mRoot = child;
        }

        if (color == BLACK)
            removeFixUp(child, parent);
        node = null;
    }

    /**
     * 红黑树插入修正函数
     * 在向红黑树中插入节点之后（失去平衡），再调用该函数，目的是将它重新塑造成一颗红黑树。
     * 
     * @param node 插入的节点
     */
    private void insertFixUp(RBTNode<T> node) {
        RBTNode<T> parent, gparent;

        // 若“父节点存在，并且父节点的颜色是红色”
        while (((parent = parentOf(node))!=null) && isRed(parent)) {
            gparent = parentOf(parent);

            //若“父节点”是“祖父节点的左孩子”
            if (parent == gparent.left) {
                // Case 1条件：叔叔节点是红色
                RBTNode<T> uncle = gparent.right;
                if ((uncle!=null) && isRed(uncle)) {
                    setBlack(uncle);
                    setBlack(parent);
                    setRed(gparent);
                    node = gparent;
                    continue;
                }

                // Case 2条件：叔叔是黑色，且当前节点是右孩子
                if (parent.right == node) {
                    RBTNode<T> tmp;
                    leftRotate(parent);
                    tmp = parent;
                    parent = node;
                    node = tmp;
                }

                // Case 3条件：叔叔是黑色，且当前节点是左孩子。
                setBlack(parent);
                setRed(gparent);
                rightRotate(gparent);
            } else {    //若“z的父节点”是“z的祖父节点的右孩子”
                // Case 1条件：叔叔节点是红色
                RBTNode<T> uncle = gparent.left;
                if ((uncle!=null) && isRed(uncle)) {
                    setBlack(uncle);
                    setBlack(parent);
                    setRed(gparent);
                    node = gparent;
                    continue;
                }

                // Case 2条件：叔叔是黑色，且当前节点是左孩子
                if (parent.left == node) {
                    RBTNode<T> tmp;
                    rightRotate(parent);
                    tmp = parent;
                    parent = node;
                    node = tmp;
                }

                // Case 3条件：叔叔是黑色，且当前节点是右孩子。
                setBlack(parent);
                setRed(gparent);
                leftRotate(gparent);
            }
        }

        // 将根节点设为黑色
        setBlack(this.mRoot);
    }
    
    /**
     * 红黑树插入修正函数
     * 在向红黑树中删除节点之后（失去平衡），再调用该函数，目的是将它重新塑造成一颗红黑树。
     * 
     * @param node 带修正的节点
     */
    private void removeFixUp(RBTNode<T> node, RBTNode<T> parent) {
        RBTNode<T> other;

        while ((node==null || isBlack(node)) && (node != this.mRoot)) {
            if (parent.left == node) {
                other = parent.right;
                if (isRed(other)) {
                    // Case 1: x的兄弟w是红色的  
                    setBlack(other);
                    setRed(parent);
                    leftRotate(parent);
                    other = parent.right;
                }

                if ((other.left==null || isBlack(other.left)) &&
                        (other.right==null || isBlack(other.right))) {
                    // Case 2: x的兄弟w是黑色，且w的俩个孩子也都是黑色的  
                    setRed(other);
                    node = parent;
                    parent = parentOf(node);
                } else {

                    if (other.right==null || isBlack(other.right)) {
                        // Case 3: x的兄弟w是黑色的，并且w的左孩子是红色，右孩子为黑色。  
                        setBlack(other.left);
                        setRed(other);
                        rightRotate(other);
                        other = parent.right;
                    }
                    // Case 4: x的兄弟w是黑色的；并且w的右孩子是红色的，左孩子任意颜色。
                    setColor(other, colorOf(parent));
                    setBlack(parent);
                    setBlack(other.right);
                    leftRotate(parent);
                    node = this.mRoot;
                    break;
                }
            } else {

                other = parent.left;
                if (isRed(other)) {
                    // Case 1: x的兄弟w是红色的  
                    setBlack(other);
                    setRed(parent);
                    rightRotate(parent);
                    other = parent.left;
                }

                if ((other.left==null || isBlack(other.left)) &&
                        (other.right==null || isBlack(other.right))) {
                    // Case 2: x的兄弟w是黑色，且w的俩个孩子也都是黑色的  
                    setRed(other);
                    node = parent;
                    parent = parentOf(node);
                } else {

                    if (other.left==null || isBlack(other.left)) {
                        // Case 3: x的兄弟w是黑色的，并且w的左孩子是红色，右孩子为黑色。  
                        setBlack(other.right);
                        setRed(other);
                        leftRotate(other);
                        other = parent.left;
                    }

                    // Case 4: x的兄弟w是黑色的；并且w的右孩子是红色的，左孩子任意颜色。
                    setColor(other, colorOf(parent));
                    setBlack(parent);
                    setBlack(other.left);
                    rightRotate(parent);
                    node = this.mRoot;
                    break;
                }
            }
        }

        if (node!=null)
            setBlack(node);
    }
    
    public RBTNode<T> search(T key) {
        return search(this.mRoot , key);
    }
    
    private RBTNode<T> search(RBTNode<T> node, T key) {
        if(null == node) return null;
        
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return search(node.left, key);
        } else if (cmp > 0) {
            return search(node.right, key);
        } else {
            return node;
        }
    }
    
    private void destory(RBTNode<T> node) {
        if (null == node) return;
        
        if (null != node.left) {
            destory(node.left);
        }
        if (null != node.right) {
            destory(node.right);
        }
        
        node = null;
    }
    
    private RBTNode<T> parentOf(RBTNode<T> node) {
        return null == node ? null : node.parent;
    }


    private boolean colorOf(RBTNode<T> node) {
        return null == node ? BLACK : node.color;
    }

    private boolean isRed(RBTNode<T> node) {
        return null != node && !node.color;
    }
    
    private boolean isBlack(RBTNode<T> node) {
        return !isRed(node);
    }
    
    private void setBlack(RBTNode<T> node) {
        if (null != node) node.color = BLACK;
    }
    
    private void setRed(RBTNode<T> node) {
        if (null != node) node.color = RED;
    }

    private void setParent(RBTNode<T> node, RBTNode<T> parent) {
        if (node!=null) node.parent = parent;
    }
    private void setColor(RBTNode<T> node, boolean color) {
        if (node!=null) node.color = color;
    }
    
    /**
    * 对红黑树的节点(x)进行左旋转
    *
    * 左旋示意图(对节点x进行左旋)：
    *      root                  root
    *      /                      /
    *    xn                     yn                
    *   /  \     --(左旋)--     / \     
    *  lx  yn                 xn  ry     
    *     /  \               /  \
    *    ly  ry             lx  ly  
    */
    private void leftRotate(RBTNode<T> xn) {
        // 取出xn的右孩子yn
        RBTNode<T> yn = xn.right;
        
        // xn的右孩子设为yn的左孩子
        xn.right = yn.left;
        // 如果yn的左孩子非空，将yn的左孩子的父亲设置为xn
        if (null != yn.left) {
            yn.left.parent = xn;
        }

        // 将xn的父亲设置为yn的父亲
        yn.parent = xn.parent;
        if (null == xn.parent) { // 如果xn的父亲是空节点，则将yn设为根节点
            this.mRoot = yn;
        } else {
            if (xn.parent.left == xn) { // 如果xn是他父亲的左孩子，则将yn设为xn的父亲的左孩子
                xn.parent.left = yn;
            } else { // 如果xn是他父亲的右孩子，则将yn设为xn的父亲的右孩子
                xn.parent.right = yn;
            }
        }

        yn.left = xn; // yn的左孩子设置为xn
        xn.parent = yn; // xn的父亲设置为yn
    }

    /**
    * 对红黑树的节点(y)进行右旋转
    *
    * 右旋示意图(对节点y进行左旋)：
    *        root                  root
    *         /                     /
    *        yn                    xn                  
    *      /  \    --(右旋)--     /  \               
    *     xn  ry               lx   yn  
    *    / \                        / \         
    *  lx  rx                     rx  ry
    */
    private void rightRotate(RBTNode<T> yn) {
        // 取出yn的左孩子xn
        RBTNode<T> xn = yn.left;
        
        // yn的左孩子设为xn的右孩子
        yn.left = xn.right;
        // xn的右孩子非空，将xn的右孩子的父亲设置为yn
        if (null != xn.right) {
            xn.right.parent = yn;
        }

        // 将yn的父亲设置为xn的父亲
        xn.parent = yn.parent;
        if (null == yn.parent) { // 如果yn的父亲是空节点，则将xn设为根节点
            this.mRoot = xn;
        } else {
            if (yn.parent.right == yn) { // 如果yn是他父亲的右孩子，则将xn设为yn父亲的右孩子
                yn.parent.right = xn;
            } else {  // 如果yn是他父亲的左孩子，则将xn设为yn父亲的左孩子
                yn.parent.left = xn;
            }
        }

        xn.right = yn; // xn的右孩子设置为yn
        yn.parent = xn; // yn的父亲设置为xn
    }
    
    public static class RBTNode<T extends Comparable<T>> {
        
        boolean color;
        T key;
        RBTNode<T> parent;
        RBTNode<T> left;
        RBTNode<T> right;

        public RBTNode(T key) {
            this.key = key;
            this.color = RED;
        }
        
        public RBTNode(T key, boolean color, RBTNode<T> parent, RBTNode<T> left, RBTNode<T> right) {
            this.key = key;
            this.color = color;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }
    }
}
