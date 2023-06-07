package Utilities;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class CustomHashMethods {

    public static String hash (String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = digest.digest(input.getBytes());
            BigInteger bigInt = new BigInteger(1, bytes);
            return bigInt.toString(16);
        } catch (NoSuchAlgorithmException error) {
            error.printStackTrace();
            return null;
        }
    }

     public static boolean checkPassword(String input, String hashedValue) {
         return Objects.equals(hash(input), hashedValue);
     }
}
