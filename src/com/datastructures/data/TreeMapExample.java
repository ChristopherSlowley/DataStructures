package com.datastructures.data;

import javax.swing.*;
import java.util.Map;
import java.util.TreeMap;

public class TreeMapExample implements DataStructureOutput {

    protected Map<String,String> aMap;

    public TreeMapExample ()
    {
        aMap = new TreeMap<>();
    }

    @Override
    public void doAction(JTextArea textArea) {
        final String [][]KeyValuePairs = {
                {"J", "Jane"},
                {"B","Bill"},
                {"S","Sam"},
                {"B1","Bob"},
                {"B2","Bill"}
        };

        for(String[] aPair: KeyValuePairs)
        {
            aMap.put(aPair[0],aPair[1]);
        }

        StringBuffer SB = new StringBuffer("Key->Value pairs: \n");
        for(String  key: aMap.keySet())
        {
            SB.append(key + "->"+ aMap.get(key) + "\n");
        }

        textArea.setText (SB.toString());

    }
}
