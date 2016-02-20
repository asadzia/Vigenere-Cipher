/**
 * The class for finding the message from a ciphertext in case the key is unknown.
 * @author Asad Zia
 * @Version 1.0
 */

public class CaesarCracker
{
	/* to denote the most common letter in a language */
    char mostCommon;
    
    /*
     * the constructor
     */
    public CaesarCracker() 
    {
        mostCommon = 'e';
    }
    
    /**
     * The constructor used alternatively to set the most common letter according to a particular language.
     * @param c
     */
    public CaesarCracker(char c) 
    {
        mostCommon = c;
    }
    
    /**
     * The method for counting the letters in a cipher text.
     * @param message
     * @return
     */
    public int[] countLetters(String message)
    {
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for(int k=0; k < message.length(); k++)
        {
            int dex = alph.indexOf(Character.toLowerCase(message.charAt(k)));
            if (dex != -1){
                counts[dex] += 1;
            }
        }
        return counts;
    }
    
    /**
     * The method for finding the index corresponding to the maximum count.
     * @param vals
     * @return
     */
    public int maxIndex(int[] vals)
    {
        int maxDex = 0;
        for(int k=0; k < vals.length; k++)
        {
            if (vals[k] > vals[maxDex])
            {
                maxDex = k;
            }
        }
        return maxDex;
    }

    /**
     * The method for finding the key.
     * @param encrypted
     * @return
     */
    public int getKey(String encrypted)
    {
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int mostCommonPos = mostCommon - 'a';
        int dkey = maxDex - mostCommonPos;
        if (maxDex < mostCommonPos) {
            dkey = 26 - (mostCommonPos-maxDex);
        }
        return dkey;
    }
    
    /**
     * The final method for decryption.
     * @param encrypted
     * @return
     */
    public String decrypt(String encrypted)
    {
        int key = getKey(encrypted);
        CaesarCipher cc = new CaesarCipher(key);
        return cc.decrypt(encrypted);
    }
}