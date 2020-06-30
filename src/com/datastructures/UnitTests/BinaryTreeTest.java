package com.datastructures.UnitTests;

import com.datastructures.data.trees.BinaryTree;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {


    }

    @org.junit.jupiter.api.Test
    void isLeaf_Test_EmptyTree() {
        BinaryTree<String> BT = new BinaryTree<>();
        assertEquals(true,BT.isLeaf());
    }

    @org.junit.jupiter.api.Test
    void toString_Test_EmptyTree(){
        BinaryTree<Integer> BT = new BinaryTree<>();
        assertEquals("null\n",BT.toString());
    }

    @org.junit.jupiter.api.Test
    void readBinaryTree_Test()
    {
        String fileName = "src/com/datastructures/UnitTests/preorderTestTree1.txt";
        String expectedTreeStr = "*\n" +
                " +\n" +
                "  x\n" +
                "   null\n" +
                "   null\n" +
                "  y\n" +
                "   null\n" +
                "   null\n" +
                " /\n"+
                "  a\n" +
                "   null\n" +
                "   null\n" +
                "  b\n" +
                "   null\n" +
                "   null\n";
        //BufferedReader preOrderTreeFile = new BufferedReader(new FileReader(filesName));
        //BinaryTree<String> BT = BinaryTree.readBinaryTree(preOrderTreeFile);
        BinaryTree<String> BT = loadTreeFromFile( getFilename() );
        assertEquals(expectedTreeStr,BT.toString());
    }

    @org.junit.jupiter.api.Test
    void preOrderTraversal_Test()
    {
        BinaryTree<String> BT = loadTreeFromFile( getFilename() );
        assertEquals("* + x y / a b ",BT.getExpression(BinaryTree.TraversalType.PREORDER));
    }

    @org.junit.jupiter.api.Test
    void postOrderTraversal_Test()
    {
        BinaryTree<String> BT = loadTreeFromFile( getFilename() );
        assertEquals("x y + a b / * ",BT.getExpression(BinaryTree.TraversalType.POSTORDER));
    }

    @org.junit.jupiter.api.Test
    void inOrderTraversal_Test()
    {
        BinaryTree<String> BT = loadTreeFromFile( getFilename() );
        assertEquals("x + y * a / b ",BT.getExpression(BinaryTree.TraversalType.INORDER));
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    /**
     *
     * @param fileName
     * @return
     */
    BinaryTree<String> loadTreeFromFile ( String fileName )
    {
        try {
            BufferedReader preOrderTreeFile = new BufferedReader(new FileReader(fileName));
            BinaryTree<String> BT = BinaryTree.readBinaryTree(preOrderTreeFile);
            return BT;
        }
        catch (FileNotFoundException exception){
            JOptionPane.showMessageDialog(null,"Error: File not found");
        }
        catch (IOException exception) {
            JOptionPane.showMessageDialog(null,"Error: IO Error, can't read file");
        }

        return null;
    }

    String getFilename()
    {
        String fileName = "src/com/datastructures/UnitTests/preorderTestTree1.txt";
        return fileName;
    }
}