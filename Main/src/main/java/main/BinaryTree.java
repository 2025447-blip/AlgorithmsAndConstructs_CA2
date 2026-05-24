/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;
import java.util.Queue;
import java.util.LinkedList;
/**
 *
 * @author Nicolas
 */
public class BinaryTree {
    private TreeNode root;
    
    public BinaryTree() {
        root = null;
    }
    
    /**
     * Inserts a new employee using level-order (BFS) insertion.
     * Uses a queue to find the first node with an empty child.
     * If root is null, the new node becomes root.
     */
    public void insert(Employee emp) {
        TreeNode newNode = new TreeNode(emp);
        if (root == null) {
            root = newNode;
            return;
        }
        // Queue for level-order traversal
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode current = queue.remove();
            // Insert at first empty left child
            if (current.left == null) {
                current.left = newNode;
                return;
            } else {
                queue.add(current.left);
            }
            // Insert at first empty right child
            if (current.right == null) {
                current.right = newNode;
                return;
            } else {
                queue.add(current.right);
            }
        }
    }
    
    /**
     * Displays all nodes in level-order (BFS).
     * Same technique used for insertion — traverse level by level using a queue.
     */
    public void levelOrderTraversal() {
        if (root == null) return;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode current = queue.remove();
            System.out.println(current.data);
            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }
    }
}

