package com.cat.TestTree;

import java.util.Stack;

/**
 * author: 牛虻.
 * time:2017/12/12 0012
 * email:pettygadfly@gmail.com
 * doc:
 * 二叉树的插入，查找，删除，翻转，遍历
 */
public class TestTree {
    //            8
    //         3          10
    //       1  4      9       14
    //            6          13
    public static void main(String[] args) {
        Tree tree = getTree();
        System.out.println(tree.find(4));
        tree.delete(6);

        tree.delete(14);
        tree.delete(4);

        tree.delete(3);
        tree.delete(10);
    }

    public static Tree getTree() {
        Tree tree = new Tree();
        Node node = new Node();
        node.iData = 8;
        node.dData = 8;
        tree.insert(node);

        node = new Node();
        node.iData = 3;
        node.dData = 3;
        tree.insert(node);

        node = new Node();
        node.iData = 10;
        node.dData = 10;
        tree.insert(node);

        node = new Node();
        node.iData = 4;
        node.dData = 4;
        tree.insert(node);

        node = new Node();
        node.iData = 1;
        node.dData = 1;
        tree.insert(node);

        node = new Node();
        node.iData = 6;
        node.dData = 6;
        tree.insert(node);

        node = new Node();
        node.iData = 14;
        node.dData = 14;
        tree.insert(node);

        node = new Node();
        node.iData = 13;
        node.dData = 13;
        tree.insert(node);

        node = new Node();
        node.iData = 9;
        node.dData = 9;
        tree.insert(node);
        return tree;
    }
}


class Node {
    public int iData;//id
    public int dData;//data
    public Node leftNode;
    public Node rightNode;

    public void display() {
        System.out.print("{");
        System.out.print(iData);
        System.out.print(",");
        System.out.print(dData);
        System.out.print("}");
    }
}

class Tree {
    private Node root;

    public Tree() {
        this.root = null;
    }

    /**
     * @param key
     * @return
     */
    public Node find(int key) {
        if (this.root == null) return null;
        Node current = root;
        while (true) {
            if (current.iData == key) return current;
            if (current.iData > key) {
                current = current.leftNode;
            } else {
                current = current.rightNode;
            }
            if (current == null) return null;
        }
    }

    /**
     * 插入节点只会插入叶子节点
     *
     * @param newNode
     */
    public void insert(Node newNode) {
        if (root == null) root = newNode;
        else {
            Node current = root;
            Node parent;
            while (true) {
                parent = current;
                if (current.iData > newNode.iData) {
                    current = current.leftNode;
                    if (current == null) {
                        parent.leftNode = newNode;
                        return;
                    }
                } else {
                    current = current.rightNode;
                    if (current == null) {
                        parent.rightNode = newNode;
                        return;
                    }
                }
            }
        }
    }

    /**
     * 删除情况
     * 1.删除叶子节点
     * 2.删除的节点有1个子节点
     * 3.删除的节点有2个子节点
     *
     * @param key
     * @return
     */
    public void delete(int key) {
        if (this.root == null) return;
        if (this.root.iData == key) {
            this.root = null;
        }
        Node current = root;
        Node parent = current;
        Node left;
        Node right;
        boolean isLeft = false;
        while (true) {
            left = current.leftNode;
            right = current.rightNode;
            if (current.iData == key) {
                current = null;
                if (left == null && right == null) { //删除叶子节点
                    if (isLeft)
                        parent.leftNode = current;
                    else
                        parent.rightNode = current;
                } else if (!(left != null && right != null)) { //有一个子节点
                    if (isLeft)
                        parent.leftNode = left;
                    else
                        parent.rightNode = left;

                } else { //2个子节点获取后继节点

                }
            } else if (current.iData > key) {
                parent = current;
                current = left;
                isLeft = true;
            } else {
                parent = current;
                current = right;
                isLeft = false;
            }
            if (current == null) return;
        }
    }

    /**
     * 获取后继节点
     * 删除有2个子节点的节点
     *
     * @return Node
     */
    public Node getSucceedNode(Node delNode) {
        Node successorParent = delNode;
        Node successor = delNode;
        Node current = delNode.rightNode;   // 寻找右节点
        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.leftNode;      // 寻找左节点
        }
        if (successor != delNode.rightNode) {
            successorParent.leftNode = successor.rightNode;
            successor.rightNode = delNode.rightNode;
        }
        return successor;
    }

    public void displayTree() {
        Stack gloabStack = new Stack();
        gloabStack.push(root);
        int nBlank = 32;
        boolean isRowEmpty = false;
        System.out.println("---------------->");
        while (isRowEmpty == false) {
            Stack localStack = new Stack();
            isRowEmpty = true;
            for (int i = 0; i < nBlank; i++) {
                System.out.println(' ');
            }
            while (gloabStack.isEmpty() == false) {
                Node temp = (Node) gloabStack.pop();
                if (temp != null) {
                    System.out.println(temp.iData);
                    localStack.push(temp.leftNode);
                    localStack.push(temp.rightNode);
                    if (temp.leftNode != null || temp.rightNode != null) {
                        isRowEmpty = false;
                    }
                } else {
                    System.out.println("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int i = 0; i < nBlank * 2 - 2; i++)
                    System.out.println(' ');
            }
            System.out.println();
            nBlank /= 2;
            while (gloabStack.isEmpty() == false)
                gloabStack.push(localStack.pop());
        }
        System.out.println("---------------->");
    }
}
