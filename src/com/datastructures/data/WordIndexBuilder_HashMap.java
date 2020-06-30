package com.datastructures.data;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WordIndexBuilder_HashMap implements DataStructureOutput {

    private final String FILE_NAME = "src/com/datastructures/UnitTests/preorderTestTree1.txt";
    Map<String, ArrayList<Integer>> index;

    public WordIndexBuilder_HashMap()
    {
        index = new HashMap<>();
    }
    @Override
    public void doAction(JTextArea textArea) {

        try {
            BufferedReader bFileReader = new BufferedReader(new FileReader(FILE_NAME));
            buildIndexAllLines(bFileReader);

            StringBuffer sBuffer = new StringBuffer("Word (count) -> [Lines number where found]:\n");
            for(String word: index.keySet())
            {
                ArrayList<Integer>  lineNumbers = index.get(word);

                sBuffer.append(
                        word +
                        "( "+ lineNumbers.size() +")" +
                        "-> " +
                        lineNumbers.toString()  +
                        "\n" );
            }

            textArea.setText(sBuffer.toString());

        } catch (FileNotFoundException exception)   {
            //exception.printStackTrace();
            JOptionPane.showMessageDialog(null,exception.getMessage(),"File Not Found Error",JOptionPane.ERROR_MESSAGE);
        }
        catch (IOException exception){
            JOptionPane.showMessageDialog(null,exception.getMessage(),"IO Exception",JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     *
     * @param bR
     */
    public void buildIndexAllLines(BufferedReader bR)
        throws IOException
    {
        int lineNum =0;
        String nextLine;

        while( (nextLine = bR.readLine()) != null )
        {
            lineNum++;
            //Create a StringTokenizer fro the current data line
            //using punctuation and white space as delimiters.
            StringTokenizer tokens = new StringTokenizer(nextLine," ,.:-!?/%");

            //Insert each token in the index - HashMap
            while (tokens.hasMoreTokens())
            {
                String word = tokens.nextToken();
                //Get the  list
                ArrayList<Integer> lines  = index.get(word);
                if (lines == null)
                {
                    lines = new ArrayList<Integer>();
                }
                lines.add(lineNum);
                index.put(word,lines);
            }
        }
    }
}
