package fpt.CapstoneSU24.util;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

@Component
public class Crypto {
    private static final String SECRET_KEY_1 = "ssdkF$HUy2A#D%kd";
    private static final String SECRET_KEY_2 = "weJiSEvR5yAC5ftB";

    private IvParameterSpec ivParameterSpec;
    private SecretKeySpec secretKeySpec;
    private Cipher cipher;

    public Crypto() {
        try {
            EncryptDecrypt();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void EncryptDecrypt() throws UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException {
        ivParameterSpec = new IvParameterSpec(SECRET_KEY_1.getBytes("UTF-8"));
        secretKeySpec = new SecretKeySpec(SECRET_KEY_2.getBytes("UTF-8"), "AES");
        cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
    }


    /**
     * Encrypt the string with this internal algorithm.
     *
     * @param toBeEncrypt string object to be encrypt.
     * @return returns encrypted string.
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidAlgorithmParameterException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public String encrypt(String toBeEncrypt) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] encrypted = cipher.doFinal(toBeEncrypt.getBytes());
        return Base64.encodeBase64String(encrypted);
    }
    /**
     * Decrypt this string with the internal algorithm. The passed argument should be encrypted using
     * {@link #encrypt(String) encrypt} method of this class.
     *
     * @param encrypted encrypted string that was encrypted using {@link #encrypt(String) encrypt} method.
     * @return decrypted string.
     * @throws InvalidAlgorithmParameterException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public String decrypt(String encrypted) throws InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] decryptedBytes = cipher.doFinal(Base64.decodeBase64(encrypted));
        return new String(decryptedBytes);
    }
}
