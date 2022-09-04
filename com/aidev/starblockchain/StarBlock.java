package com.aidev.starblockchain;

// import java.sql.Date;
import java.util.ArrayList;
import java.util.StringJoiner;

public final class StarBlock{
            
    private long creationTimeStamp;
    private String currentHash;    
    private String horizontalPreviousHash_relatedFrom;    
    private String verticalPreviousHash_unrelatedFrom;    
    private long horizontalIndex;
    private long verticalIndex;                      
    private String dataStoreImmutable;
    private StringBuilder uniqueCodeforEachUserName;    
    private String userNames = "";
    private boolean encryptBlockChain = false;
        
    public StarBlock(long creationTimeStamp,
            String horizontalPreviousHash_relatedFrom, 
            String verticalPreviousHash_unrelatedFrom,
            String dataStoreImmutable, ArrayList<String> userNames, 
            long horizontalIndex, long verticalIndex, boolean encryptBlockChain){        

        this.encryptBlockChain = encryptBlockChain;
        this.creationTimeStamp = creationTimeStamp;        
        this.horizontalPreviousHash_relatedFrom = horizontalPreviousHash_relatedFrom;        
        this.verticalPreviousHash_unrelatedFrom = verticalPreviousHash_unrelatedFrom;         
        this.dataStoreImmutable = (this.encryptBlockChain == true)?Security.AES.encrypt(dataStoreImmutable): dataStoreImmutable;        
        this.userNames = (this.encryptBlockChain == true)?Security.AES.encrypt(getDelimitedString(userNames)):getDelimitedString(userNames);                
        this.horizontalIndex = horizontalIndex;
        this.verticalIndex = verticalIndex;
        this.currentHash = Security.Hash.calculateCurrentHash(this);                
    }

    public String getDelimitedString(ArrayList<String> userNames){
        StringJoiner joiner = new StringJoiner(",");
        for (String userName : userNames)joiner.add(userName);
        return joiner.toString();
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
    public String str(){
        return (""+horizontalIndex) + (""+verticalIndex) + (""+creationTimeStamp) + 
                dataStoreImmutable + mineUniqueCodeforEachUserName(this.userNames);
    }
    public String toString(){
        // String dataEncDecValue = (this.encryptBlockChain == true)?Security.AES.decrypt(dataStoreImmutable): dataStoreImmutable;
        // String userNamesEncDecValue = (this.encryptBlockChain == true)?Security.AES.decrypt(userNames):userNames;
        // String starBlockInfo = String.format(
        //                         "{\"Tensor\" : \"%1$s%2$s\", \"data\" : \"%3$s\", \"userNames\" : \"%4$s\", "+
        //                          "\"creationTimeStamp\" : \"%5$s\", "+
        //                         "\"verticalPreviousHash\" : \"%6$s\", \"horizontalPreviousHash\" : \"%7$s\", " +
        //                         "\"currentHash\" : \"%8$s\"}",
        //                         horizontalIndex, verticalIndex, dataEncDecValue, userNamesEncDecValue, new Date(creationTimeStamp), 
        //                         verticalPreviousHash_unrelatedFrom, horizontalPreviousHash_relatedFrom, 
        //                         currentHash);
        // String starBlockInfo = String.format(
        //     "%1$s %2$s%3$s\t",
        //     dataStoreImmutable, horizontalIndex, verticalIndex);
        
        String starBlockInfo = String.format(
            "STARBLOCK %1$s%2$s\t",
            horizontalIndex, verticalIndex);                                        
        return starBlockInfo;
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
