package com.datastructures.UnitTests;

import com.datastructures.data.FindGCD;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FindGCDTest {

    @Test
    void findGCD() {
        int [] array = {6,12,24,36};
        int [] array2 = {100,5,10,50,55,20};
        int [] array3 = {100,5,10,50,3,20};

        int expectedResult = 6;
        int actualResult = new FindGCD().findGCD(array.length, array) ;
        assertEquals(expectedResult,actualResult);

        expectedResult = 5;
        actualResult = new FindGCD().findGCD(array2.length, array2) ;
        assertEquals(expectedResult,actualResult);

        expectedResult = 1;
        actualResult = new FindGCD().findGCD(array3.length, array3) ;
        assertEquals(expectedResult,actualResult);

    }
}