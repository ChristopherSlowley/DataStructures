package com.datastructures.UnitTests;

import com.datastructures.data.PalindromeFinder_Stack;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PalindromeFinder_StackTest {

    @Test
    void isPalindrome() {
        String palindromeStr = "Able was I ere I saw Elba";
        PalindromeFinder_Stack palindromeFinder = new PalindromeFinder_Stack(palindromeStr);

        assertTrue(palindromeFinder.isPalindrome());

        String nonPalindromeStr = "Hello World";
        palindromeFinder = new PalindromeFinder_Stack(nonPalindromeStr);

        assertFalse(palindromeFinder.isPalindrome());
    }
}