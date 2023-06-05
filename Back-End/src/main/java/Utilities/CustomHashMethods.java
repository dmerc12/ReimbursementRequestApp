package Utilities;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class CustomHashMethods {
    public static void main(String[] args) {
        String input = "password123";
        String hashedValue = hash(input);
        System.out.println("Input: " + input);
        System.out.println("Hashed Value: " + hashedValue);

        assert hashedValue != null;
        boolean isMatched = checkPassword("password123", hashedValue);
        System.out.println("Password Match: " + isMatched);
    }

    private static byte[] concatenateBytes(byte[] input1, byte[] input2) {
        byte[] combined = new byte[input1.length + input2.length];
        System.arraycopy(input1, 0, combined, 0, input1.length);
        System.arraycopy(input2, 0, combined, input1.length, input2.length);
        return combined;
    }

    private static byte[] hexStringToByteArray(String hexString) {
        int len = hexString.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4) +
                    Character.digit(hexString.charAt(i + 1), 16));
        }
        return data;
    }

    public static String hash (String input) {
        try {
            // Generate random salt
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);

            // Create a MessageDigest instance with the desired algorithm
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Apply the salt to the input string
            byte[] saltedInput = concatenateBytes(input.getBytes(StandardCharsets.UTF_8), salt);

            // Compute the hash value
            byte[] encodeHash = digest.digest(saltedInput);

            // Convert the byte array to a hexadecimal string representation
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodeHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException error) {
            error.printStackTrace();
            return null;
        }
    }

    public static String hash (String input, byte[] salt) {
        try {
            // Create a MessageDigest instance with the desired algorithm
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Apply the salt to the input string
            byte[] saltedInput = concatenateBytes(input.getBytes(StandardCharsets.UTF_8), salt);

            // Compute the hash value
            byte[] encodeHash = digest.digest(saltedInput);

            // Convert the byte array to a hexadecimal string representation
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodeHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException error) {
            error.printStackTrace();
            return null;
        }
    }

    public static boolean checkPassword(String input, String hashedValue) {
        // Extract the salt from the hashed value
        byte[] salt = hexStringToByteArray(hashedValue.substring(0, 32));

        // Recompute the hash value using the extracted salt
        String rehashedValue = hash(input, salt);

        // Compare the stored hashed value with the recomputed one
        return hashedValue.equals(rehashedValue);
    }
}
