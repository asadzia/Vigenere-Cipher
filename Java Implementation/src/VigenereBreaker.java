/**
 * The class for breaking the vigenere cipher
 * @author Asad Zia
 * @Version 1.0
 */

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

    
    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) 
    {
        int[] key = new int[klength];
        
        return key;
    }

    public void breakVigenere () 
    {
        //WRITE YOUR CODE HERE
    }
    
    public static void main (String args [])
    {
    	VigenereBreaker cc = new VigenereBreaker();
    	System.out.println(cc.sliceString("abcdefghijklm", 0, 3));
    }
    
}
