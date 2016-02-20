/**
 * The class for denoting the specifics of the Vigenere Cipher.
 * @author Asad Zia
 * @Version 1.0 
 */

import java.util.*;

public class VigenereCipher 
{
    CaesarCipher[] ciphers;
    
    /**
     *  the constructor, which takes a key, which is an array of integers and initializes
     *  the field ciphers, which is an array of CaesarCipher objects.
     * @param key
     */
    public VigenereCipher(int[] key) 
    {
        ciphers = new CaesarCipher[key.length];
        for (int i = 0; i < key.length; i++)
        {
            ciphers[i] = new CaesarCipher(key[i]);
        }
    }
    
    /**
     * a method that encrypts the String passed in and returns the encrypted message.
     * @param input
     * @return answer
     */
    public String encrypt(String input) 
    {
        StringBuilder answer = new StringBuilder();
        int i = 0;
        for (char c : input.toCharArray()) 
        {
            int cipherIndex = i % ciphers.length;
            CaesarCipher thisCipher = ciphers[cipherIndex];
            answer.append(thisCipher.encryptLetter(c));
            i++;
        }
        return answer.toString();
    }
    	
    /**
     * a method that decrypts the String passed in and returns the decrypted message.
     * @param input
     * @return answer
     */
    public String decrypt(String input) 
    {
        StringBuilder answer = new StringBuilder();
        int i = 0;
        
        for (char c : input.toCharArray()) 
        {
            int cipherIndex = i % ciphers.length;
            CaesarCipher thisCipher = ciphers[cipherIndex];
            answer.append(thisCipher.decryptLetter(c));
            i++;
        }
        
        return answer.toString();
    }
    
    /**
     * returns a String representing the key for this cipher.
     */
    public String toString() 
    {
        return Arrays.toString(ciphers);
    }
}