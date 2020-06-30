package com.datastructures.data.trees;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;

/**
 * Class for a binary tree that stores type E objects
 * @param <E>
 */
public class BinaryTree<E> implements Serializable {

    /** The root of the binary tree */
    protected Node<E> root;

    public static enum TraversalType
    {
        INORDER,
        PREORDER,
        POSTORDER;
    }

    //Constructors

    /**
     * No argument constructor
     * Creates an empty tree
     */
    public BinaryTree()
    {
        root = null;
    }

    /**
     * Contructs a tree with and preexisting tree/node
     * @param root
     */
    protected BinaryTree(Node<E> root)
    {
        this.root = root;
    }

    /**
     * Constructs a new binary tree with data in it root, leftTree as its left subtree (child)
     * and rightTree as its right subtree (child)
     * @param data
     * @param leftTree
     * @param rightTree
     */
    public BinaryTree( E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree )
    {
        root = new Node<>(data);
        if(leftTree != null)
        {
            root.left = leftTree.root;
        }
        else
        {
            root.left = null;
        }

        if(rightTree != null)
        {
            root.right = rightTree.root;
        }
        else
        {
            root.right = null;
        }
    }
    //Methods

    /**
     * Determine is this tree is a leaf
     * @return true is the root has no children
     * @throws NullPointerException
     */
    public boolean isLeaf()
    {
        if ( root != null )
        {
            return (root.left == null && root.right == null);
        }
        return true;
    }

    /**
     * Return a  reference to the left subtree as a new BinaryTree
     * @return A new BinaryTree subtree using the left child as the root
     * return null if either the root or the root.left is empty/null
     */
    public BinaryTree<E> getLeftSubtree()
    {
        if (root != null && root.left != null)
        {
            return new BinaryTree<E>(root.left);
        }
        return null;
    }

    /**
     * Return a reference to the right subtree as a new BinaryTree
     * @return A new BinaryTree subtree using the right child as the root
     * return null if either root ot root.right is empty
     */
    public BinaryTree<E> getRightSubtree()
    {
        if (root != null && root.right != null)
        {
            return new BinaryTree<E>(root.right);
        }
        return null;
    }

    /**
     * Perform preorder traversal recursively
     * @param node  The local root
     * @param depth The current depth in to tree
     * @param sb The string buffer to accumulate the output
     */
    private void preOrderTraverse (Node<E> node, int depth, StringBuilder sb)
    {
        for (int i = 1; i < depth; i++)
        {
            sb.append(" ");
        }
        if (node == null)
        {
            sb.append("null\n");
        }
        else
        {
            sb.append(node.toString());
            sb.append("\n");
            preOrderTraverse(node.left, depth + 1, sb);
            preOrderTraverse(node.right, depth + 1, sb);
        }
    }

    /**
     * Perform preorder traversal recursively
     * @param node  The local root
     * @param sb The string buffer to accumulate the output
     */
    private void preOrderExpression (Node<E> node, StringBuilder sb)
    {
        if(node != null) {
            sb.append(node.toString());
            sb.append(" ");
            preOrderExpression(node.left, sb);
            preOrderExpression(node.right, sb);
        }
    }

    /**
     * Perform inorder traversal recursively
     * @param node  The local root
     * @param sb The string buffer to accumulate the output
     */
    private void inOrderExpression (Node<E> node, StringBuilder sb)
    {
        if(node != null) {
            inOrderExpression(node.left, sb);
            sb.append(node.toString());
            sb.append(" ");
            inOrderExpression(node.right, sb);
        }
    }

    /**
     * Perform post order traversal recursively
     * @param node  The local root
     * @param sb The string buffer to accumulate the output
     */
    private void postOrderExpression (Node<E> node, StringBuilder sb)
    {
        if(node != null) {
            postOrderExpression(node.left, sb);
            postOrderExpression(node.right, sb);
            sb.append(node.toString());
            sb.append(" ");
        }
    }

    /**
     * Build a string representing the BinaryTree using the preorder
     * traversal algorithm
     * @return The string presenting preorder traversal of the tree
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        preOrderTraverse(root, 1, sb);
        return sb.toString();
    }

    public String getExpression (TraversalType traversalType)
    {
        StringBuilder sb = new StringBuilder();

        switch (traversalType)
        {
            case INORDER -> {
                inOrderExpression(root,sb);
            }
            case PREORDER -> {
                preOrderExpression(root, sb);
            }
            case POSTORDER -> {
                postOrderExpression(root,sb);
            }
        }
        return sb.toString();
    }

    /**
     * Method to load a binary tree from a file
     * pre: The input consists of a preorder traversal
     *      of a binary tree. The line "null" indicates a null tree
     * @param bR The input file
     * @return The binary tree
     * @throws IOException if there is an input error
     */
    public static BinaryTree<String> readBinaryTree(BufferedReader bR)
            throws IOException
    {
        //Read a line an trim leading an trailing spaces
        String data = bR.readLine().trim();
        if(data.equals("null"))
        {
            return null;
        }
        else
        {
            BinaryTree<String> leftTree = readBinaryTree(bR);
            BinaryTree<String> rightTree = readBinaryTree(bR);
            return new BinaryTree<>(data, leftTree, rightTree);
        }
    }

    //------------------------------------I N N E R   C L A S S-------------------------------------------------//
    /**
     * Class to encapsulate a tree node
     * @param <E>
     */
    protected static class Node<E> implements Serializable
    {
        /* The information stored in this node */
        protected E data;
        /* Reference to the left child (sub-tree) */
        Node<E> left;

        /* Reference to the right child (sub-tree) */
        Node<E> right;

        /**
         * Constructor - Constructs a node with given data
         * and no children i.e. leaf node
         * @param data The data for this node
         */
        public Node(E data )
        {
            this.data = data;
            left = null;
            right = null;
        }

        //Methods

        /**
         * Return a string representing the node
         * @return A string representation of the data fields
         */
        public String toString()
        {
            return data.toString();
        }
    }// end inner class Node

}
