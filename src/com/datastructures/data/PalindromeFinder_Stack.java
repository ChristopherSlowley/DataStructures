package com.datastructures.data;

import javax.swing.*;
import java.util.Stack;

/**
 * Class that checks if a string is a palindrome. It uses a
 * Stack to reverse the sting and compare it with the original
 * to see if they are the same.
 */
public class PalindromeFinder_Stack implements DataStructureOutput {

    private String inputString;

    private Stack<Character> charStack ;

    public PalindromeFinder_Stack (String str)
    {
        inputString = str;
        charStack = new Stack<>();
        fillStack();
    }

    /**
     * Fills the stack with the characters from the inputString
     */
    private void fillStack()
    {
        for (int i = 0; i < inputString.length(); i++) {
            charStack.push(inputString.charAt(i));
        }
    }

    public boolean isPalindrome()
    {
        return inputString.equalsIgnoreCase(buildReverse());
    }

    /**
     * Builds a string containing the characters in the stack
     * post: The stack is empty
     * @return The string containing the words in the stack
     */
    private String buildReverse()
    {
        StringBuilder result = new StringBuilder();
        while (!charStack.empty())
        {
            //Remove top item from stack and append it to result
            result.append(charStack.pop());
        }
        
        return result.toString();
    }

    @Override
    public void doAction(JTextArea textArea) {
        String input = JOptionPane.showInputDialog(null, "Enter the palindrome:", "Palindrome Verifier",JOptionPane.QUESTION_MESSAGE);

        if (input != null) {
            PalindromeFinder_Stack pVerifier = new PalindromeFinder_Stack(input);

            textArea.setText("Palindrome Finder: [" + input + "] " +
                    ((pVerifier.isPalindrome()) ? " is " : "is not ") +
                    "a palindrome!");
        }
    }
}
