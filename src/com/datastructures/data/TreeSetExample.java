package com.datastructures.data;

import javax.swing.*;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class TreeSetExample implements DataStructureOutput{
    private Set<String> setA;
    private Set<String> setACopy;
    private Set<String> setB;

    enum NameListNames
    {
        LIST_A,
        LIST_B
    }

    public TreeSetExample()
    {
        setA = new TreeSet<>();
        setACopy = new TreeSet<>();
        setB = new TreeSet<>();

    }




    @Override
    public void doAction(JTextArea textArea)
    {
        String [][]nameList = {
                {"Ann", "Sally", "Jill", "Sally"},
                {"Bob", "Bill", "Ann", "Jill"}
        };

        for( String name: nameList[HashSetExample.NameListNames.LIST_A.ordinal()])
        {
            setA.add(name);
            setACopy.add(name);
        }

        for( String name: nameList[HashSetExample.NameListNames.LIST_B.ordinal()])
        {
            setB.add(name);
        }

        textArea.setText(""); //clear
        textArea.append("The 2 sets are:" +
                " \n" +
                setA +
                "\n" +
                setB );
        //Display the union and intersection
        setA.addAll(setB);          //set union
        setACopy.retainAll(setB);   //set intersection
        textArea.append("\n"+
                "Items in set union are: " +
                setA );

        textArea.append("\n"+
                "Items in set intersection are: " +
                setACopy );

    }


}
