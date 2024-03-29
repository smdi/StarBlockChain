package com.aidev.starblockchain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.StringJoiner;

public class StarBlock{
            
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
    private int encryptionMethod = MetaData.NO_ENCRYPTION;
        
    public StarBlock(long creationTimeStamp,
            String horizontalPreviousHash_relatedFrom, 
            String verticalPreviousHash_unrelatedFrom,
            String dataStoreImmutable, ArrayList<String> userNames, 
            long horizontalIndex, long verticalIndex, boolean encryptBlockChain, int encryptionMethod){        

        try{
            this.encryptBlockChain = encryptBlockChain;
            this.encryptionMethod = encryptionMethod;
            this.creationTimeStamp = creationTimeStamp;        
            this.horizontalPreviousHash_relatedFrom = horizontalPreviousHash_relatedFrom;        
            this.verticalPreviousHash_unrelatedFrom = verticalPreviousHash_unrelatedFrom; 
            if(encryptBlockChain == true && encryptionMethod == MetaData.AES){
                this.dataStoreImmutable = Security.AES.encrypt(dataStoreImmutable);
                this.userNames = Security.AES.encrypt(getDelimitedString(userNames));
            }else if(encryptBlockChain == true && encryptionMethod == MetaData.RSA){
                if(horizontalIndex == 0 && verticalIndex == 0){
                    Security.RSA.generateStoreKeyPair();
                }else{
                    Security.RSA.loadStoreKeyPair();
                }
                this.dataStoreImmutable = Security.RSA.encrypt(dataStoreImmutable);
                this.userNames = Security.RSA.encrypt(getDelimitedString(userNames));
            }else{
                this.dataStoreImmutable = dataStoreImmutable;
                this.userNames = getDelimitedString(userNames);
            }                        
            this.horizontalIndex = horizontalIndex;
            this.verticalIndex = verticalIndex;
            this.currentHash = Security.Hash.calculateCurrentHash(this);                
        }catch(Exception e){
            e.printStackTrace();
        }                
    }

    public String getDelimitedString(ArrayList<String> userNames){
        try{
            StringJoiner joiner = new StringJoiner(",");
            for (String userName : userNames)joiner.add(userName);
            return joiner.toString();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public long getCreationTimeStamp() {
        try{
            return creationTimeStamp;
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }    
    public String getCurrentHash() {
        try{
            return currentHash;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }    
    public long getVerticalIndex() {
        try{
            return verticalIndex;
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }   
    public long getHorizontalIndex() {
        try{
            return horizontalIndex;
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }   
    public String getHorizontalPreviousHash_relatedFrom() {
        try{
            return horizontalPreviousHash_relatedFrom;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }            
    public String getVerticalPreviousHash_unrelatedFrom() {
        try{
            return verticalPreviousHash_unrelatedFrom;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }       
    public String getDataStoreImmutable() {
        try{
            return dataStoreImmutable;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }          
    public String getUserNames() {
        try{
            return userNames;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }       
    public String str(){
        try{
            return (""+horizontalIndex) + (""+verticalIndex) + (""+creationTimeStamp) + 
                dataStoreImmutable + mineUniqueCodeforEachUserName(this.userNames);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public String toString(){
        try{
            String dataEncDecValue = dataStoreImmutable;
            String userNamesEncDecValue = userNames;
            if(encryptBlockChain == true && encryptionMethod == MetaData.AES){
                dataEncDecValue = Security.AES.decrypt(dataStoreImmutable);
                userNamesEncDecValue = Security.AES.decrypt(userNames);
            }else if(encryptBlockChain == true && encryptionMethod == MetaData.RSA){
                Security.RSA.loadStoreKeyPair();
                dataEncDecValue = Security.RSA.decrypt(dataStoreImmutable);
                userNamesEncDecValue = Security.RSA.decrypt(userNames);
            }

            String starBlockInfo = String.format(
                                    "{\"tensor\" : \"%1$s%2$s\", \"data\" : \"%3$s\", \"userNames\" : \"%4$s\", "+
                                    "\"creationTimeStamp\" : \"%5$s\", "+
                                    "\"verticalPreviousHash\" : \"%6$s\", \"horizontalPreviousHash\" : \"%7$s\", " +
                                    "\"currentHash\" : \"%8$s\"}",
                                    horizontalIndex, verticalIndex, dataEncDecValue, userNamesEncDecValue, new Date(creationTimeStamp), 
                                    verticalPreviousHash_unrelatedFrom, horizontalPreviousHash_relatedFrom, 
                                    currentHash);
            // String starBlockInfo = String.format(
            //     "%1$s %2$s%3$s\t",
            //     dataStoreImmutable, horizontalIndex, verticalIndex);
            
            // String starBlockInfo = String.format(
            //     "STARBLOCK %1$s%2$s\t",
            //     horizontalIndex, verticalIndex);                                        
            return starBlockInfo;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }        
    private String mineUniqueCodeforEachUserName(String userNames){ 
        try{
            uniqueCodeforEachUserName = new StringBuilder();                   
            byte[] byteArrray = userNames.getBytes();
            for(byte each: byteArrray){
                uniqueCodeforEachUserName.append(each);
            }                
            return uniqueCodeforEachUserName.toString();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }     
}
