package com.aidev.starblockchain;

import javax.crypto.Cipher;  
import javax.crypto.SecretKey;  
import javax.crypto.SecretKeyFactory;  
import javax.crypto.spec.IvParameterSpec;  
import javax.crypto.spec.PBEKeySpec;  
import javax.crypto.spec.SecretKeySpec;  
import java.nio.charset.StandardCharsets;  
import java.security.InvalidAlgorithmParameterException;  
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;  
import java.security.spec.InvalidKeySpecException;  
import java.security.spec.KeySpec;  
import java.util.Base64;  
import javax.crypto.BadPaddingException;  
import javax.crypto.IllegalBlockSizeException;  
import javax.crypto.NoSuchPaddingException;

public class Security{

    static class Hash{

        private static String hashingAlgorithm = "SHA-256";

        public static String getHashingAlgorithm(){
            return hashingAlgorithm;
        }
        public static void setHashingAlgorithm(String hashingAlgorithm){
            Hash.hashingAlgorithm = hashingAlgorithm;
        }
        private static String calculateCurrentHash(boolean isStarBlock, StarBlock starBlock, String confidentialData){            
            String hash = null;
            try {
                MessageDigest instanceSHA256 = MessageDigest.getInstance(hashingAlgorithm);
                String textUniquetoBlock = (isStarBlock == true)?starBlock.str():confidentialData;            
                byte hashBytes[] = instanceSHA256.digest(textUniquetoBlock.getBytes());
                StringBuilder generatedHash = new StringBuilder();
                for (byte b : hashBytes) {
                    generatedHash.append(String.format("%02X", b));
                }
                hash = generatedHash.toString();            
            } catch (NoSuchAlgorithmException e) {            
                e.printStackTrace();
            }  
            return hash;

        }
        public static String calculateCurrentHash(StarBlock starBlock){
            return calculateCurrentHash(true, starBlock, null);
        }
        public static String calculateCurrentHash(String confidentialData){
            return calculateCurrentHash(false, null, confidentialData);
        }

    }    
    static class AES{

        private static String secretKey = "9176094515EE1ABC68FF1E325A9D646267B4BB49502D9BA215ECF852EF8CF95F";  
        private static String saltValue = "42BCE8F62FF281D43E39D9355253F1CF5D20A7EF3D612A46B61BA88027F3E527";

        public static String getSecretKey(){
            return secretKey;
        }        
        public static void setSecretKey(String secretKey){
            AES.secretKey = Hash.calculateCurrentHash(secretKey);            
        }
        public static String getSaltValue(){
            return saltValue;
        }
        public static void setSaltValue(String saltValue){
            AES.saltValue = Hash.calculateCurrentHash(saltValue);
        }

        public static String encrypt(String strToEncrypt){             
            try{                
                byte[] iv = {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0};  
                IvParameterSpec ivspec = new IvParameterSpec(iv);                    
                SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");               
                KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), saltValue.getBytes(), 65536, 256);  
                SecretKey tmp = factory.generateSecret(spec);  
                SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");  
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");  
                cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);                
                return Base64.getEncoder()  
                                .encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));  
            }   
            catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e){  
                System.out.println("Error occured during encryption: " + e.toString());  
            }  
            return null;  
        }
        public static String decrypt(String strToDecrypt){  
            try{        
                byte[] iv = {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0};  
                IvParameterSpec ivspec = new IvParameterSpec(iv);              
                SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");              
                KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), saltValue.getBytes(), 65536, 256);  
                SecretKey tmp = factory.generateSecret(spec);  
                SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");  
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");  
                cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);              
                return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));  
            }   
            catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e){  
                System.out.println("Error occured during decryption: " + e.toString());  
            }  
            return null;  
        }
    }                        
    public static void main(String[] args)   
    {                                  
        String originalval = "I'm STAR BlockChain";          
        String encryptedval = AES.encrypt(originalval);          
        String decryptedval = AES.decrypt(encryptedval);  
        
        System.out.println("Original value: " + originalval);  
        System.out.println("Encrypted value: " + encryptedval);  
        System.out.println("Decrypted value: " + decryptedval);  
    }  
}  