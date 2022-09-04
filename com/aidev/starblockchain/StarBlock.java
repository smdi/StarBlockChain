package com.aidev.starblockchain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
// import java.sql.Date;
import java.util.ArrayList;

public class StarBlock {
            
    private long creationTimeStamp;
    private String currentHash;    
    private String horizontalPreviousHash_relatedFrom;    
    private String verticalPreviousHash_unrelatedFrom;    
    private long horizontalIndex;
    private long verticalIndex;                      
    private String dataStoreImmutable;
    private StringBuilder uniqueCodeforEachUserName;    
    private String userNames = "";
    private static String HASHING_ALGORITHM = "SHA-256";

    public StarBlock(){}
    public StarBlock(long creationTimeStamp,
            String horizontalPreviousHash_relatedFrom, 
            String verticalPreviousHash_unrelatedFrom,
            String dataStoreImmutable, ArrayList<String> userNames, long horizontalIndex, long verticalIndex) {        
        
        this.creationTimeStamp = creationTimeStamp;        
        this.horizontalPreviousHash_relatedFrom = horizontalPreviousHash_relatedFrom;        
        this.verticalPreviousHash_unrelatedFrom = verticalPreviousHash_unrelatedFrom;         
        this.dataStoreImmutable = dataStoreImmutable;
        for (String userName : userNames)
        {
            this.userNames += userName;
        }        
        this.horizontalIndex = horizontalIndex;
        this.verticalIndex = verticalIndex;
        this.currentHash = StarBlock.calculateCurrentHash(this);                
    }  

    public long getCreationTimeStamp() {
        return creationTimeStamp;
    }    
    public String getCurrentHash() {
        return currentHash;
    }    
    public long getVerticalIndex() {
        return verticalIndex;
    }   
    public long getHorizontalIndex() {
        return horizontalIndex;
    }   
    public String getHorizontalPreviousHash_relatedFrom() {
        return horizontalPreviousHash_relatedFrom;
    }            
    public String getVerticalPreviousHash_unrelatedFrom() {
        return verticalPreviousHash_unrelatedFrom;
    }       
    public String getDataStoreImmutable() {
        return dataStoreImmutable;
    }          
    public String getUserNames() {
        return userNames;
    }       
    private String str(){
        return (""+horizontalIndex) + (""+verticalIndex) + (""+creationTimeStamp) + 
                dataStoreImmutable + mineUniqueCodeforEachUserName(this.userNames);
    }
    public String toString(){
        // String starBlockInfo = String.format(
        //                         "STARBLOCK %1$s%2$s \ndata %3$s \nuserName %4$s \ncreationTimeStamp %5$s \n"+
        //                         "verticalPreviousHash %6$s \nhorizontalPreviousHash %7$s \n" +
        //                         "currentHash %8$s\n",
        //                         horizontalIndex, verticalIndex, dataStoreImmutable, userName, new Date(creationTimeStamp), 
        //                         verticalPreviousHash_unrelatedFrom,horizontalPreviousHash_relatedFrom, 
        //                         currentHash);
        // String starBlockInfo = String.format(
        //     "%1$s %2$s%3$s\t",
        //     dataStoreImmutable, horizontalIndex, verticalIndex);
        String starBlockInfo = String.format(
            "STARBLOCK %1$s%2$s\t",
            horizontalIndex, verticalIndex);                                        
        return starBlockInfo;
    }
    protected static String calculateCurrentHash(StarBlock starBlock){
        String hash = null;
        try {
            MessageDigest instanceSHA256 = MessageDigest.getInstance(HASHING_ALGORITHM);
            String textUniquetoBlock = starBlock.str();            
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
    private String mineUniqueCodeforEachUserName(String userNames){ 
        uniqueCodeforEachUserName = new StringBuilder();                   
        byte[] byteArrray = userNames.getBytes();
        for(byte each: byteArrray){
            uniqueCodeforEachUserName.append(each);
        }                
        return uniqueCodeforEachUserName.toString();
    } 
    
}
