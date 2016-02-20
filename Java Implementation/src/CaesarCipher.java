/**
 * The class for depicting the Caeser Cipher class for encryption and decryption.
 * @author Asad Zia
 * @Version 1.0
 */

public class CaesarCipher 
{
	/* to denote the entire alphabets in the english language */
    private String alphabet;
    
    /* to denote the shifted alphabet*/
    private String shiftedAlphabet;
    
    /* the variable to denote the key */
    private int theKey;
    
    /*
     * the constructor
     */
    public CaesarCipher(int key) 
    {
        theKey = key;
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) +
                            alphabet.substring(0,key);
        alphabet = alphabet + alphabet.toLowerCase();
        shiftedAlphabet = shiftedAlphabet + shiftedAlphabet.toLowerCase();
    }
    
    /**
     * The function which shift the letter. This function is 
     * used as a helper for encryption
     * @param c
     * @param from
     * @param to
     * @return
     */
    private char transformLetter(char c, String from, String to) 
    {
        int idx = from.indexOf(c);
        if (idx != -1)
        {
            return to.charAt(idx);
        }
        return c;
    }
    
    /**
     * The method for encrypting a single character.
     * @param c
     * @return
     */
    public char encryptLetter(char c)
    {
        return transformLetter(c, alphabet, shiftedAlphabet);
    }
    
    /**
     * The method for decrypting a single character.
     * @param c
     * @return
     */
    public char decryptLetter(char c) 
    {
        return transformLetter(c, shiftedAlphabet, alphabet);
    }
    
    /**
     * A helper method for shifting an input string.
     * @param input
     * @param from
     * @param to
     * @return
     */
    private String transform(String input, String from, String to)
    {
        StringBuilder sb = new StringBuilder(input);
        for (int i = 0; i < sb.length(); i++) 
        {
            char c = sb.charAt(i);
            c = transformLetter(c, from, to);
            sb.setCharAt(i, c);
        }
        return sb.toString();
    }
    
    /**
     * The method for encrypting a string
     * @param input
     * @return
     */
    public String encrypt(String input)
    {
        return transform(input, alphabet, shiftedAlphabet);
    }
    
    /**
     * The method for decrypting a string.
     * @param input
     * @return
     */
    public String decrypt(String input)
    {
        return transform(input, shiftedAlphabet, alphabet);
    }
    
    /**
     * The method for returning the key string.
     */
    public String toString() 
    {
        return "" + theKey;
    }
}