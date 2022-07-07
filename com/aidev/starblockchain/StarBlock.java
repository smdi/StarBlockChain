package com.aidev.starblockchain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;

public class StarBlock {
            
    private long creationTimeStamp;
    private String currentHash;    
    private String horizontalPreviousHash_relatedFrom;
    private String horizontalNextHash_relatedTo;
    private String verticalPreviousHash_unrelatedFrom;
    private String verticalNextHash_unrelatedTo;
    private long horizontalIndex;
    private long verticalIndex;                      
    private String dataStoreImmutable;
    private StringBuilder uniqueCodeforEachUserName;    
    private String userName;
    
    public StarBlock(long creationTimeStamp,
            String horizontalPreviousHash_relatedFrom, String horizontalNextHash_relatedTo,
            String verticalPreviousHash_unrelatedFrom, String verticalNextHash_unrelatedTo,
            String dataStoreImmutable, String UserName, long horizontalIndex, long verticalIndex) {        
        setCreationTimeStamp(creationTimeStamp);        
        setHorizontalPreviousHash_relatedFrom(horizontalPreviousHash_relatedFrom);
        setHorizontalNextHash_relatedTo(horizontalNextHash_relatedTo);
        setVerticalPreviousHash_unrelatedFrom(verticalPreviousHash_unrelatedFrom);
        setVerticalNextHash_unrelatedTo(verticalNextHash_unrelatedTo);      
        setDataStoreImmutable(dataStoreImmutable);
        setUserName(userName);
        setHorizontalIndex(horizontalIndex);
        setVerticalIndex(verticalIndex);
        setCurrentHash(calculateCurrentHash(this));        
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
    public String getHorizontalNextHash_relatedTo() {
        return horizontalNextHash_relatedTo;
    }
    private void setHorizontalNextHash_relatedTo(String horizontalNextHash_relatedTo) {
        this.horizontalNextHash_relatedTo = horizontalNextHash_relatedTo;
    }
    public String getVerticalPreviousHash_unrelatedFrom() {
        return verticalPreviousHash_unrelatedFrom;
    }
    private void setVerticalPreviousHash_unrelatedFrom(String verticalPreviousHash_unrelatedFrom) {
        this.verticalPreviousHash_unrelatedFrom = verticalPreviousHash_unrelatedFrom;
    }
    public String getVerticalNextHash_unrelatedTo() {
        return verticalNextHash_unrelatedTo;
    }
    private void setVerticalNextHash_unrelatedTo(String verticalNextHash_unrelatedTo) {
        this.verticalNextHash_unrelatedTo = verticalNextHash_unrelatedTo;
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
                dataStoreImmutable + mineUniqueCodeforEachUserName(userName);
    }
    public String toString(){
        String starBlockInfo = String.format(
                                "STARBLOCK %1$s%2$s \ndata %3$s \nuserName %4$s \ncreationTimeStamp %5$s \n"+
                                "verticalPreviousHash %6$s \nhorizontalPreviousHash %7$s \n" +
                                "currentHash %8$s \nhorizontalNextHash %9$s \nverticalNextHash %10$s",
                                horizontalIndex, verticalIndex, dataStoreImmutable, userName, new Date(creationTimeStamp), 
                                verticalPreviousHash_unrelatedFrom,horizontalPreviousHash_relatedFrom, 
                                currentHash, horizontalNextHash_relatedTo, verticalNextHash_unrelatedTo);        
        return starBlockInfo;
    }
    private static String calculateCurrentHash(StarBlock starBlock){
        try {
            MessageDigest instanceSHA256 = MessageDigest.getInstance("SHA-256");
            String textUniquetoBlock = starBlock.str();
            byte hashBytes[] = instanceSHA256.digest(textUniquetoBlock.getBytes());
            StringBuilder generatedHash = new StringBuilder();
            for (byte b : hashBytes) {
                generatedHash.append(String.format("%02X", b));
            }
            return generatedHash.toString();
        } catch (NoSuchAlgorithmException e) {            
            e.printStackTrace();
        }  
        return null;
    }    
    private String mineUniqueCodeforEachUserName(String userName){ 
        uniqueCodeforEachUserName = new StringBuilder();       
        byte[] byteArrray = userName.getBytes();
        uniqueCodeforEachUserName.append(byteArrray);        
        return uniqueCodeforEachUserName.toString();
    } 
    
}
