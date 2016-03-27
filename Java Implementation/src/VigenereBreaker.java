/**
 * The class for breaking the vigenere cipher
 * @author Asad Zia
 * @Version 1.0
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class VigenereBreaker 
{
	/**
	 * This method returns a String consisting of every totalSlices-th character from message, starting at the whichSlice-th character.
	 * @param message
	 * @param whichSlice
	 * @param totalSlices
	 * @return cc
	 */
    public String sliceString(String message, int whichSlice, int totalSlices) 
    {
    	StringBuilder cc = new StringBuilder();
    	int index = 0;
    	
    	if (whichSlice < totalSlices)
    	{
	    	for (char x: message.toCharArray())
	    	{
	    		int idx = index % totalSlices;
	    		
	    		if (idx == whichSlice)
	    		{
	    			cc.append(x);
	    		}
	    		++index;
	    	}
    	}
        return cc.toString();
    }

    /**
     * This method makes use of the CaesarCracker class, as well as
     * the sliceString method, to find the shift for each index in the key. 
     * You should fill in the key (which is an array of integers) and return it.
     * @param encrypted
     * @param klength
     * @param mostCommon
     * @return
     */
    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) 
    {
       int[] key = new int[klength];
       CaesarCracker cc = new CaesarCracker(mostCommon);
       
       int i = 0;
       
       while (i < klength)
       {
    	   String myString = sliceString(encrypted, i, klength);
    	   int result = cc.getKey(myString);
    	   key[i] = result;
    	   ++i;
       }
       return key;
    }
    
    /**
     * The function for reading the string content from the file.
     * @param filename
     * @return
     */
    public String readString (String filename)
    {
    	try 
    	{
    		StringBuilder sb = new StringBuilder();
			@SuppressWarnings("resource")
			BufferedReader encrypted = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
			String line;
			
			try 
			{
				while ((line = encrypted.readLine()) != null)
				{
					sb.append(line);
					sb.append("\n");
				}
			} 
			catch (IOException e) 
			{

				e.printStackTrace();
			}
			
			return sb.toString();
		}
    	catch (FileNotFoundException e) 
    	{

			e.printStackTrace();
		}
		return null;
    }

    /**
     * The main method for breaking the Vigenere cipher
     */
    public void breakVigenere (String fileName, int klength, char mostCommon) 
    {
    	VigenereBreaker cc = new VigenereBreaker();
    	String encrypted = cc.readString(fileName);
    	VigenereCipher myCipher = new VigenereCipher (cc.tryKeyLength(encrypted, klength, mostCommon));
    	String result = myCipher.decrypt(encrypted);
    	System.out.println(result);
    }
    
    /**
     * The driver method for the class
     * @param args
     */
    public static void main (String args [])
    {
    	VigenereBreaker cc = new VigenereBreaker();
    	cc.breakVigenere("messages/athens_keyflute.txt", 5, 'e');
    }   
}
