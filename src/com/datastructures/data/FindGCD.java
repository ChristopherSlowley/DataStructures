package com.datastructures.data;

import javax.swing.*;
import java.util.*;

public class FindGCD implements DataStructureOutput {

    public static Set<Integer> getFactors( int number )
    {
        Set<Integer> factors = new HashSet<>();
        for(int div= 1; div <= number/2; div++)
        {
            if(number % div == 0)
            {
                factors.add(div);
            }
        }
        return factors;
    }

    public int findGCD(int num, int [] arr)
    {
        int currGcd =1;
        int largestGcd=1;

        Set<Integer> allFactors = new HashSet<>();
        //Create a set of all factors
        for (int number: arr) {
            allFactors.addAll(getFactors(number));
        }

        //find the largest factor that is common to all numbers
        Object[]allFactArr = allFactors.toArray();

        if (allFactArr.length > 0)
        {
            largestGcd = (Integer) allFactArr[0]; //assume first is the CGD
            for (int idx = 1; idx < allFactArr.length; idx++)
            {
                currGcd = (Integer) allFactArr[idx];
                //Is a common factor
                boolean isCommon = true;
                for(int number: arr)
                {
                    if(number % currGcd != 0) {
                        isCommon = false;
                    }
                }

                if(isCommon && currGcd > largestGcd)
                {
                    largestGcd = currGcd;
                }
            }
        }
        return largestGcd;
    }

    public Set<Integer> loadArray(int [] array)
    {
        Set<Integer> arrayContainer = new HashSet<>();

        for (int number: array)
        {
            arrayContainer.add(number);
        }
        return arrayContainer;
    }

    public void  doAction (JTextArea textArea)
    {
        int [] array = {2,4,6,8,10};
        Set<Integer> arrayContainer = loadArray(array);

        textArea.setText( arrayContainer + " -> GCD: " + findGCD(5, array) + "\n" );

        int [] array2 = {100,5,10,50,55,20};
        arrayContainer = loadArray(array2);

        textArea.append( arrayContainer + "-> GCD: " + findGCD(array2.length, array2) );

    }
}
