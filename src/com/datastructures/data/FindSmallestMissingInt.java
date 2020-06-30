package com.datastructures.data;
import javax.swing.*;
import java.time.Instant;
import java.util.*;
import java.util.Timer;

public class FindSmallestMissingInt implements DataStructureOutput  {


    public void doAction(JTextArea displayArea)
    {
        Random generator = new Random( System.currentTimeMillis());
        int size  = generator.nextInt(100_000);

        int Arr[] = new int[size];

        for (int idx = 0; idx < Arr.length; idx++)
        {
            Arr[idx] = generator.nextInt(100_000);
        }

        Set<Integer> set = getSet(Arr);
        displayArea.setText(""+findSmallestNotIn(set));
    }

    int findSmallestNotIn (Set<Integer> set)
    {
        for( int num =1; num <= 100000; num++)
        {
            if(!set.contains(num))
            {
                return num;
            }
        }
        return 1;
    }

    Set<Integer> getSet (int [] Arr)
    {
        Set<Integer> set = new HashSet<>();

        for(int element: Arr)
        {
            set.add(element);
        }

        return set;
    }
}
