/**
 * An interface that declares methods related to using and breaking rotation ciphers.
 *
 * @author Patrick Totzke
 */
public interface RotationCipher {
    
    /** 
     * Rotates a given string s by shift n.
     *
     * @param s the text to be encrypted/decrypted
     * @param n the secret key (or its inverse for decryption)
     * @return the string resulting from encrypting (or decrypting) s with the key k
     */
    public String rotate(String s, int n);

    /**
     * Determines the plain text string for a given cipher text.
     *
     * @param s the cipher text to decode
     * @return the plain text original
     */
    public String decipher(String s);
}
