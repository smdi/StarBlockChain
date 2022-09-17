package com.aidev.starblockchain;


public class MetaData{

    public static String BLOCKCHAIN = "StarBlockChain";
    public static String VERIFICATION_TIMEOUT = "Verification timeout, try after some time!";
    public static String ENCRYPTED_PASS_KEYS = "Encrypted data, pass secretKey, saltValue(optional)!";
    public static String INVALID_SECRET_KEY = "Invalid secretkey!";
    public static String INVALID_SECRET_KEY_SALT_KEY = "Invalid secretkey or saltvalue!";
    public static String DATA_NOT_ENCRYPTED = "Secretkey not required, data is not in encrypted format!";
    public static String KEYS_IN_SECRETS_FOLDER = "Note : Public and private keys were generated in public & secrets folder!";
    public static String PRIVATE_KEY_IN_SECRETS_FOLDER = "Note : Please make sure, private key is in secrets folder for decryption of data!";
    public static String PASS_KEYS_FOR_AES = "Pass secretKey for AES encryption!";
    public static String RSA_FOUND = "RSA encryption found, decrypting data!";
    public static int NO_ENCRYPTION = -1;    
    public static int AES = 0;
    public static int RSA = 1; 
}