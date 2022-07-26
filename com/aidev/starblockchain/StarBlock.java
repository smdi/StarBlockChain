package com.aidev.starblockchain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
// import java.sql.Date;

public class StarBlock {
            
    private long creationTimeStamp;
    private String currentHash;    
    private String horizontalPreviousHash_relatedFrom;    
    private String verticalPreviousHash_unrelatedFrom;    
    private long horizontalIndex;
    private long verticalIndex;                      
    private String dataStoreImmutable;
    private StringBuilder uniqueCodeforEachUserName;    
    private String userName;
    private static String HASHING_ALGORITHM = "SHA-256";

    public StarBlock(){}
    public StarBlock(long creationTimeStamp,
            String horizontalPreviousHash_relatedFrom, 
            String verticalPreviousHash_unrelatedFrom,
            String dataStoreImmutable, String userName, long horizontalIndex, long verticalIndex) {        
        setCreationTimeStamp(creationTimeStamp);        
        setHorizontalPreviousHash_relatedFrom(horizontalPreviousHash_relatedFrom);        
        setVerticalPreviousHash_unrelatedFrom(verticalPreviousHash_unrelatedFrom);         
        setDataStoreImmutable(dataStoreImmutable);
        setUserName(userName);
        setHorizontalIndex(horizontalIndex);
        setVerticalIndex(verticalIndex);
        setCurrentHash(StarBlock.calculateCurrentHash(this));        
    }  

    public long getCreationTimeStamp() {
        return creationTimeStamp;
    }
    private void setCreationTimeStamp(long creationTimeStamp) {
        this.creationTimeStamp = creationTimeStamp;
    }
    public String getCurrentHash() {
        return currentHash;
    }
    private void setCurrentHash(String currentHash) {
        this.currentHash = currentHash;
    }
    public long getVerticalIndex() {
        return verticalIndex;
    }
    private void setVerticalIndex(long verticalIndex) {
        this.verticalIndex = verticalIndex;
    }
    public long getHorizontalIndex() {
        return horizontalIndex;
    }
    private void setHorizontalIndex(long horizontalIndex) {
        this.horizontalIndex = horizontalIndex;
    }
    public String getHorizontalPreviousHash_relatedFrom() {
        return horizontalPreviousHash_relatedFrom;
    }
    private void setHorizontalPreviousHash_relatedFrom(String horizontalPreviousHash_relatedFrom) {
        this.horizontalPreviousHash_relatedFrom = horizontalPreviousHash_relatedFrom;
    }        
    public String getVerticalPreviousHash_unrelatedFrom() {
        return verticalPreviousHash_unrelatedFrom;
    }
    private void setVerticalPreviousHash_unrelatedFrom(String verticalPreviousHash_unrelatedFrom) {
        this.verticalPreviousHash_unrelatedFrom = verticalPreviousHash_unrelatedFrom;
    }    
    public String getDataStoreImmutable() {
        return dataStoreImmutable;
    }
    private void setDataStoreImmutable(String dataStoreImmutable) {
        this.dataStoreImmutable = dataStoreImmutable;
    }       
    public String getUserName() {
        return userName;
    }    
    private void setUserName(String userName) {
        this.userName = userName;
    }    
    private String str(){
        return (""+horizontalIndex) + (""+verticalIndex) + (""+creationTimeStamp) + 
                dataStoreImmutable + mineUniqueCodeforEachUserName(this.userName);
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
    private String mineUniqueCodeforEachUserName(String userName){ 
        uniqueCodeforEachUserName = new StringBuilder();                   
        byte[] byteArrray = userName.getBytes();
        for(byte each: byteArrray){
            uniqueCodeforEachUserName.append(each);
        }                
        return uniqueCodeforEachUserName.toString();
    } 
    
}
