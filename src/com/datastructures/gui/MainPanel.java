package com.datastructures.gui;

import com.datastructures.data.*;
import com.datastructures.data.trees.BinaryTree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MainPanel {
    public JPanel getMainPanel_pnl() {
        return mainPanel_pnl;
    }

    private JButton LoadTree_btn;
    private JPanel mainPanel_pnl;
    private JButton ShowTree_btn;
    private JTextArea TextDisplayArea_txa;
    private JScrollPane TextDisplayScroller_scr;
    private JPanel SouthButtonPanel_pnl;
    private JButton ShowLeftSubTree_btn;
    private JButton ShowRightSubTree_btn;
    private JPanel LeftSide_SouthButton_pnl;
    private JPanel RightSide_SouthButton_pnl;
    private JButton Inorder_btn;
    private JButton Preorder_btn;
    private JButton PostOrder_btn;
    private JPanel NorthButton_pnl;
    private JButton HashSet_btn;
    private JButton TreeSetExample_btn;
    private JButton HashMapExample_btn;
    private JButton TreeMapExample_btn;
    private JButton WordIndexBuilder_btn;
    private JButton PalindromeFinder_btn;
    private JButton FindGCD_btn;
    private JButton FindSmallestMissingInt_btn;
    private JButton TestContainer_btn;
    private BinaryTree<String> BT;
    private final String BINARY_TREE_FILE_NAME = "DataFiles/BinaryTrees/preorderBinaryTree1.txt";

    public MainPanel() {


        LoadTree_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TextDisplayArea_txa.setText(""); //clear area
                loadTreeFromFile();
                if (BT != null)
                {
                    TextDisplayArea_txa.append(BT.toString());
                    Component [] subPanels = SouthButtonPanel_pnl.getComponents();

                    //Enable all the buttons on the south button panel
                    for(Component panel: subPanels)
                    {
                        Component [] comps = ((JPanel)panel).getComponents();
                        for(Component com: comps ) {
                            if(com instanceof JButton) {
                                TextDisplayArea_txa.append("\n" + ((JButton)com).getText() );
                                ((JButton)com).setEnabled(true);
                            }
                        }
                    }
                }
                else
                {
                    TextDisplayArea_txa.append("Error loading tree from " + BINARY_TREE_FILE_NAME );
                }
            }
        });

        ShowTree_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                TextDisplayArea_txa.setText(""); //clear area
                if(BT != null) {
                    TextDisplayArea_txa.append(BT.toString());
                }
            }
        });
        ShowLeftSubTree_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TextDisplayArea_txa.setText(""); //clear area
                if(BT != null) {
                    TextDisplayArea_txa.append(BT.getLeftSubtree().toString());
                }
            }
        });
        ShowRightSubTree_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TextDisplayArea_txa.setText(""); //clear area
                if(BT != null) {
                    TextDisplayArea_txa.append(BT.getRightSubtree().toString());
                }
            }
        });

        Inorder_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TextDisplayArea_txa.setText(""); //clear area
                if( BT != null ) {
                    TextDisplayArea_txa.append(BT.getExpression(BinaryTree.TraversalType.INORDER));
                }
            }
        });

        Preorder_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TextDisplayArea_txa.setText(""); //clear area
                if( BT != null ) {
                    TextDisplayArea_txa.append(BT.getExpression(BinaryTree.TraversalType.PREORDER));
                }
            }
        });
        PostOrder_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TextDisplayArea_txa.setText(""); //clear area
                if( BT != null ) {
                    TextDisplayArea_txa.append(BT.getExpression(BinaryTree.TraversalType.POSTORDER));
                }
            }
        });

        //---------------------------H A S H S E T   E X A M P L E-----------------------------------------------

        HashSet_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HashSetExample().doAction(TextDisplayArea_txa);
            }
        });

        //---------------------------T R E E S E T   E X A M P L E-----------------------------------------------

        TreeSetExample_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TreeSetExample().doAction(TextDisplayArea_txa);
            }
        });

        //---------------------------H A S H M A P   E X A M P L E-----------------------------------------------
        HashMapExample_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HashMapExample().doAction(TextDisplayArea_txa);
            }
        });

        TreeMapExample_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TreeMapExample().doAction(TextDisplayArea_txa);
            }
        });
        WordIndexBuilder_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new WordIndexBuilder_HashMap().doAction(TextDisplayArea_txa);
            }
        });

        PalindromeFinder_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PalindromeFinder_Stack("").doAction(TextDisplayArea_txa);
            }
        });
        FindGCD_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FindGCD().doAction(TextDisplayArea_txa);
            }
        });

        FindSmallestMissingInt_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FindSmallestMissingInt().doAction(TextDisplayArea_txa);
            }
        });
        TestContainer_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TestContainer().doAction(TextDisplayArea_txa);
            }
        });
    }

    private void loadTreeFromFile ()
    {
        BT = null;
        try {
            BufferedReader preOrderTreeFile = new BufferedReader(new FileReader(BINARY_TREE_FILE_NAME));
            BT = BinaryTree.readBinaryTree(preOrderTreeFile);
        }
        catch (FileNotFoundException exception){
            JOptionPane.showMessageDialog(null,"Error: File not found");
        }
        catch (IOException exception) {
            JOptionPane.showMessageDialog(null,"Error: IO Error, can't read file");
        }
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
