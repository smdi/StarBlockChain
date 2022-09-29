package com.aidev.starblockchain;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.concurrent.*;

public class StarBlockChain{
        
    private LinkedHashMap<Long, ArrayList<StarBlock>> starBlocks;
    private LinkedHashMap<Long, Long> versionCount;
    private long totalTensorNetworkStrength = 0;
    private long index = 0;    
    private boolean RELATED_FALSE = false;    
    private int THREADS_PER_N_BLOCKS = 500;
    private int MIN_THREADS_PARALLEL_EXECUTION = 5;
    private int MED_THREADS_PARALLEL_EXECUTION = 10;
    private int MAX_THREADS_PARALLEL_EXECUTION = 20;    
    private int VERIFICATION_TIMEOUT_NUMBER = 1;
    private TimeUnit VERIFICATION_TIMEUNIT = TimeUnit.SECONDS;
    private boolean encryptBlockChain = false;
    private int encryptionMethod = MetaData.NO_ENCRYPTION;
    private String secretKey = "";
    private String saltValue = "";        

    public StarBlockChain(){
        try{
            starBlocks = new LinkedHashMap<Long, ArrayList<StarBlock>>();
            versionCount = new LinkedHashMap<Long, Long>();        
        }catch(Exception e){
            e.printStackTrace();
        }        
    }        
    public StarBlockChain(int encryptionMethod, String secretKey, String saltValue){
        this();
        try{            
            encryptBlockChain = true;
            this.encryptionMethod = encryptionMethod;
            if(encryptBlockChain == true && MetaData.AES == encryptionMethod){            
                Security.AES.setSecretKey(secretKey);
                Security.AES.setSaltValue(saltValue);            
            }else if(encryptBlockChain == true && MetaData.RSA == encryptionMethod){
                System.out.println(MetaData.KEYS_IN_SECRETS_FOLDER);
            } 
        }catch(Exception e){
            e.printStackTrace();
        }       
    }
    public StarBlockChain(int encryptionMethod, String secretKey){        
        this();
        try{
            encryptBlockChain = true;
            this.encryptionMethod = encryptionMethod;
            if(encryptBlockChain == true && MetaData.AES == encryptionMethod){            
                Security.AES.setSecretKey(secretKey);
            }else if(encryptBlockChain == true && MetaData.RSA == encryptionMethod){
                System.out.println(MetaData.KEYS_IN_SECRETS_FOLDER);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public StarBlockChain(int encryptionMethod){        
        this();
        try{
            encryptBlockChain = true;
            this.encryptionMethod = encryptionMethod;
            if(encryptBlockChain == true && MetaData.AES == encryptionMethod){            
                System.out.println(MetaData.PASS_KEYS_FOR_AES);
                encryptBlockChain = false; 
                this.encryptionMethod = MetaData.NO_ENCRYPTION;
                return ;
            }else if(encryptBlockChain == true && MetaData.RSA == encryptionMethod){
                System.out.println(MetaData.KEYS_IN_SECRETS_FOLDER);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }    
    public void newStarBlock(String data, ArrayList<String> userNames){
        try{
            getNewStarBlockLogic(data, RELATED_FALSE, true, -1, userNames);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void newStarBlock(String data, boolean related, ArrayList<String> userNames){
        try{
            getNewStarBlockLogic(data, related, true, -1, userNames);
        }catch(Exception e){
            e.printStackTrace();
        }
    }       
    public void newStarBlock(String data, boolean related, boolean currentIndex, long index, ArrayList<String> userNames){                
        try{
            getNewStarBlockLogic(data, related, currentIndex, index, userNames);        
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private void getNewStarBlockLogic(String data, boolean related, boolean currentIndex, long index, ArrayList<String> userNames) {        
        try{
            if(starBlocks.size() == 0){            
                getNewStarBlockAtIndex(this.index, data, false, false, userNames);            
            }else{
                if(related && currentIndex){
                    getNewStarBlockAtIndex(this.index, data, true, false, userNames);
                }else if(related && !(currentIndex)){
                    getNewStarBlockAtIndex(index, data, true, true, userNames);
                }else{
                    this.index = this.index + 1;
                    getNewStarBlockAtIndex(this.index, data, false, false, userNames);
                }
            }        
            setTotalTensorNetworkStrength(++totalTensorNetworkStrength);
        }catch(Exception e){
            e.printStackTrace();
        }        
    }
    private void getNewStarBlockAtIndex(long index, String data, boolean addNewRelated, boolean fetchHorizontalPreviousIndex, ArrayList<String> userNames) {        
        try{
            if(addNewRelated){            
                ArrayList<StarBlock> indexedStarblocks = starBlocks.get(index);
                long horizontalIndex = this.index; long verticalIndex = Long.valueOf(indexedStarblocks.size());            
                if(fetchHorizontalPreviousIndex){
                    long []indexes = getHorizontalpreviousIndexes(index, indexedStarblocks);
                    horizontalIndex = indexes[0]; verticalIndex = indexes[1];
                }
                indexedStarblocks.add(getNewStarBlock(data, 
                    getHorizontalPreviousHash_relatedFrom(index, indexedStarblocks), 
                    null, horizontalIndex, verticalIndex, userNames));
                starBlocks.put(index, indexedStarblocks);            
                updateVersionCode(index, Long.valueOf(indexedStarblocks.size()));
            }else{
                String hash = null;
                if(starBlocks.size() != 0){
                    hash = getVerticalPreviousHash_unRelatedFrom(index, starBlocks.get(index -1));
                }
                starBlocks.put(index, new ArrayList<>(Arrays.asList(getNewStarBlock(data,
                        null, 
                        hash, this.index, 0, userNames))));
                updateVersionCode(index, Long.valueOf(1));
            }
        }catch(Exception e){
            e.printStackTrace();
        }                
    }
    private StarBlock getNewStarBlock(String data, String horizontalPreviousHash_relatedFrom, 
                     String verticalPreviousHash_unrelatedFrom, long  horizontalIndex, long verticalIndex, ArrayList<String> userNames){        
        try{
            return new StarBlock(System.currentTimeMillis(), horizontalPreviousHash_relatedFrom, 
                    verticalPreviousHash_unrelatedFrom, data, userNames, 
                    horizontalIndex, verticalIndex, encryptBlockChain, encryptionMethod);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;        
    }
    private long[] getHorizontalpreviousIndexes(long index, ArrayList<StarBlock> indexedStarblocks){        
        try{
            long indexes[] = new long[2];
            int sizeOfIndexedStarblocksdex = indexedStarblocks.size();
            indexes[0] = indexedStarblocks.get(sizeOfIndexedStarblocksdex - 1).getHorizontalIndex();
            indexes[1] = indexedStarblocks.get(sizeOfIndexedStarblocksdex - 1).getVerticalIndex() + 1;
            return indexes;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    private String getHorizontalPreviousHash_relatedFrom(long index, ArrayList<StarBlock> indexedStarblocks){
        try{
            int sizeOfIndexedStarblocksdex = indexedStarblocks.size();
            return indexedStarblocks.get(sizeOfIndexedStarblocksdex - 1).getCurrentHash();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;                
    }
    private String getVerticalPreviousHash_unRelatedFrom(long index, ArrayList<StarBlock> indexedStarblocks){
        try{
            return indexedStarblocks.get(0).getCurrentHash();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }    
    private void updateVersionCode(Long index, Long count){
        try{
            versionCount.put(index, count);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public long getTotalTensorStrengthAtIndex(int index) {
        try{
            return versionCount.get(index);
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }       
    public long getTotalTensorNetworkStrength() {
        try{
            return totalTensorNetworkStrength;
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    private void setTotalTensorNetworkStrength(long totalTensorNetworkStrength) {
        try{
            this.totalTensorNetworkStrength = totalTensorNetworkStrength;
        }catch(Exception e){
            e.printStackTrace();
        }        
    }
    private String getRSADecrytion(){
        try{
            System.out.println(MetaData.PRIVATE_KEY_IN_SECRETS_FOLDER);
            return printStarBlockChainChecked();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;        
    }
    private String printStarBlockChainChecked(){
        try{
            StringBuilder blockChainInfo = new StringBuilder();
            long totalVerticalStrength = Long.valueOf(versionCount.size());                
            for(long i=0; i<totalVerticalStrength; i++ ){
                long totalHorizontalStrengthAtIndex = versionCount.get(i);            
                ArrayList<StarBlock> horizontalStarBlockAtIndex = starBlocks.get(i);
                for(int j=0; j<totalHorizontalStrengthAtIndex; j++){
                    blockChainInfo.append(horizontalStarBlockAtIndex.get(j));
                }
                blockChainInfo.append("\n");
            }
            return blockChainInfo.toString();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public String printStarBlockChain(){
        try{
            if(encryptBlockChain == false && encryptionMethod == MetaData.NO_ENCRYPTION){            
                return printStarBlockChainChecked();
            }else if(encryptBlockChain == true && encryptionMethod == MetaData.RSA){            
                return getRSADecrytion();
            }else{
                return MetaData.ENCRYPTED_PASS_KEYS;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null; 
    }
    public String toStirng(){
        try{
            return MetaData.BLOCKCHAIN;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;               
    }
    public String printStarBlockChain(String secretKey){
        try{
            if(encryptBlockChain == true && encryptionMethod == MetaData.AES){
                this.secretKey = Security.AES.getSecretKey();
                if(this.secretKey.equals(Security.Hash.calculateCurrentHash(secretKey))){
                    Security.AES.setSecretKey(secretKey);
                    return printStarBlockChainChecked();
                }else{                
                    return MetaData.INVALID_SECRET_KEY;
                } 
            }else if(encryptBlockChain == true && encryptionMethod == MetaData.RSA){
                System.out.println(MetaData.RSA_FOUND);
                return getRSADecrytion();
            }else{
                return MetaData.DATA_NOT_ENCRYPTED;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;       
    }
    public String printStarBlockChain(String secretKey, String saltValue){
        try{
            if(encryptBlockChain == true && encryptionMethod == MetaData.AES){
                this.secretKey = Security.AES.getSecretKey(); 
                this.saltValue = Security.AES.getSaltValue();
                if(this.secretKey.equals(Security.Hash.calculateCurrentHash(secretKey)) 
                    && this.saltValue.equals(Security.Hash.calculateCurrentHash(saltValue))){
                    Security.AES.setSecretKey(secretKey);
                    Security.AES.setSaltValue(saltValue);
                    return printStarBlockChainChecked();
                }else{
                    return MetaData.INVALID_SECRET_KEY_SALT_KEY;
                }
            }else if(encryptBlockChain == true && encryptionMethod == MetaData.RSA){
                System.out.println(MetaData.RSA_FOUND);
                return getRSADecrytion();
            }else{
                return MetaData.DATA_NOT_ENCRYPTED;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;        
    }
    private boolean checkCodeforFirstStarBlockValidity(StarBlock firstBlock, long index, 
                                                String relatedHash, String currentHash){
        try{
            if (index != 0)return false;        
            if (relatedHash != null)return false;                            
            if (currentHash == null || 
                !Security.Hash.calculateCurrentHash(firstBlock).equals(currentHash))return false;                                             
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    private boolean isFirstStarBlockValid(StarBlock firstBlock, boolean isHorizontal) {        
        try{
            boolean result = false;                
            if(isHorizontal){            
                result = checkCodeforFirstStarBlockValidity(firstBlock, firstBlock.getVerticalIndex(), 
                firstBlock.getHorizontalPreviousHash_relatedFrom(), firstBlock.getCurrentHash());
            }else{
                result = checkCodeforFirstStarBlockValidity(firstBlock, firstBlock.getVerticalIndex(), 
                firstBlock.getVerticalPreviousHash_unrelatedFrom(), firstBlock.getCurrentHash());
            }          
            return result;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    private boolean checkCodeforNewStarBlockValidity(StarBlock newBlock, StarBlock previousBlock,
                                                    long prevIndex, long newIndex, 
                                                    String newBlockPrevHash, String prevBlockCurrHash,
                                                    String newBlockcurrHash){        
        try{
            if (newBlock != null  &&  previousBlock != null) {            
                if (prevIndex + 1 != newIndex) {
                  return false;
                }            
                if (newBlockPrevHash == null  ||  
                  !newBlockPrevHash.equals(prevBlockCurrHash)) {
                  return false;
                }            
                if (newBlockcurrHash == null  ||  
                  !Security.Hash.calculateCurrentHash(newBlock).equals(newBlockcurrHash)) {
                  return false;
                }            
                return true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }                
        return false;
    }
    private boolean isValidNewStarBlock(StarBlock newBlock, StarBlock previousBlock, boolean isHorizontal) {
        try{
            boolean result = false;
            if(isHorizontal){
                result = checkCodeforNewStarBlockValidity(newBlock, previousBlock, previousBlock.getVerticalIndex(),
                                    newBlock.getVerticalIndex(), newBlock.getHorizontalPreviousHash_relatedFrom(),
                                    previousBlock.getCurrentHash(),
                                    newBlock.getCurrentHash());
            }else{
                result = checkCodeforNewStarBlockValidity(newBlock, previousBlock, previousBlock.getHorizontalIndex(),
                                    newBlock.getHorizontalIndex(), newBlock.getVerticalPreviousHash_unrelatedFrom(),
                                    previousBlock.getCurrentHash(),
                                    newBlock.getCurrentHash());
            }            
            return result;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    private long threadEstimator(long totalStrength){
        try{
            long result = totalStrength/THREADS_PER_N_BLOCKS;        
            return result;
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    private int threads(long threadEstimatorValue){
        try{
            int nThreads = (threadEstimatorValue <= 20)? 
                        (threadEstimatorValue <= 10? MIN_THREADS_PARALLEL_EXECUTION : MED_THREADS_PARALLEL_EXECUTION)
                        : MAX_THREADS_PARALLEL_EXECUTION;
            return nThreads;
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    private class StarBlockChainHorizontalThreadPool implements Callable<Boolean>{
        private volatile boolean result = false;                
        private int valueOfHorizontalIndexToStart = 0;
        private int valueOfHorizontalIndexToStop = 0;
        ArrayList<StarBlock> horizontalStarBlockAtIndex;        
        StarBlockChainHorizontalThreadPool(int valueOfHorizontalIndexToStart, 
                        int valueOfHorizontalIndexToStop, ArrayList<StarBlock> horizontalStarBlockAtIndex){
            try{
                this.valueOfHorizontalIndexToStart = valueOfHorizontalIndexToStart;
                this.valueOfHorizontalIndexToStop = valueOfHorizontalIndexToStop;
                this.horizontalStarBlockAtIndex = horizontalStarBlockAtIndex;
            }catch(Exception e){
                e.printStackTrace();
            }                        
        }                
        @Override
        public Boolean call() throws Exception{
            try{
                for(int j=valueOfHorizontalIndexToStart; j<valueOfHorizontalIndexToStop; j++){                
                    if(j > 0){
                        StarBlock currentBlock = horizontalStarBlockAtIndex.get(j);
                        StarBlock previousBlock = horizontalStarBlockAtIndex.get(j - 1);
                        result = isValidNewStarBlock(currentBlock, previousBlock, true);                     
                    }else{
                        result = isFirstStarBlockValid(horizontalStarBlockAtIndex.get(j), true);                                        
                    }
                    if(!(result)){break;}                        
                }                                                                
                return result;
            }catch(Exception e){
                e.printStackTrace();
            }
            return false;
        }
    }
    private class StarBlockChainVerticalThreadPool implements Callable<Boolean>{
        private volatile boolean result = false;
        private volatile boolean resultVertical = false;
        private volatile boolean resultHorizontal = false;
        private long valueOfVerticalIndexToStart = 0;
        private long valueOfVerticalIndexToStop = 0;        
        StarBlockChainVerticalThreadPool(long valueOfVerticalIndexToStart, 
                        long valueOfVerticalIndexToStop){
            try{
                this.valueOfVerticalIndexToStart = valueOfVerticalIndexToStart;
                this.valueOfVerticalIndexToStop = valueOfVerticalIndexToStop;
            }catch(Exception e){
                e.printStackTrace();
            }                        
        }                
        @Override
        public Boolean call() throws Exception{
            try{
                for(long i=valueOfVerticalIndexToStart; i<valueOfVerticalIndexToStop; i++ ){
                    long totalHorizontalStrengthAtIndex = versionCount.get(i);            
                    ArrayList<StarBlock> horizontalStarBlockAtIndex = starBlocks.get(i);
                    long threadEstimatorValue = threadEstimator(totalHorizontalStrengthAtIndex);
                    ExecutorService executorService = Executors.newFixedThreadPool(threads(threadEstimatorValue));                
                    if(totalHorizontalStrengthAtIndex > 1){                                                                               
                        resultHorizontal = loopChainVerification(threadEstimatorValue, totalHorizontalStrengthAtIndex, executorService, horizontalStarBlockAtIndex);                    
                        executorService.shutdownNow();                    
                    }
                    if(i > 0){
                        StarBlock currentBlock = starBlocks.get(i).get(0);
                        StarBlock previousBlock = starBlocks.get(i - 1).get(0);
                        resultVertical = isValidNewStarBlock(currentBlock, previousBlock, false);
                    }else{
                        resultVertical = isFirstStarBlockValid(starBlocks.get(i).get(0), false);
                    }
                    if(totalHorizontalStrengthAtIndex > 1)result = resultVertical && resultHorizontal;
                    else result = resultVertical;
                    if(!(result)){break;}                            
                }
                return result;
            }catch(Exception e){
                e.printStackTrace();
            }
            return false;
        }
    }
    private boolean loopChainVerification(long threadEstimatorValue, long totalStrength, ExecutorService executorService, ArrayList<StarBlock> horizontalStarBlockAtIndex)throws Exception{
        try{
            boolean result = false;
            long valueStart = 0, valueStop = 0;        
            if(threadEstimatorValue == 0){                        
                valueStart = 0; 
                valueStop = totalStrength; 
                Future<Boolean> futureResult;
                if(horizontalStarBlockAtIndex == null){
                    futureResult = executorService.submit(new StarBlockChainVerticalThreadPool(valueStart, valueStop));
                }else{
                    futureResult = executorService.submit(new StarBlockChainHorizontalThreadPool((int)valueStart, (int)valueStop, horizontalStarBlockAtIndex));
                }

                try {
                    result = futureResult.get(VERIFICATION_TIMEOUT_NUMBER, VERIFICATION_TIMEUNIT);                    
                } catch (TimeoutException e) {
                    result = false;
                    System.out.println(MetaData.VERIFICATION_TIMEOUT);
                }            
            }else if(threadEstimatorValue > 0){                        
                long threads = 0;            
                List<Future<Boolean>> listOfCallables = new ArrayList<Future<Boolean>>();
                do{                
                    valueStart = valueStop;
                    valueStop = valueStop + THREADS_PER_N_BLOCKS; 
                    if(horizontalStarBlockAtIndex == null){
                        listOfCallables.add(executorService.submit(new StarBlockChainVerticalThreadPool(valueStart, valueStop)));
                    }else{
                        listOfCallables.add(executorService.submit(new StarBlockChainHorizontalThreadPool((int)valueStart, (int)valueStop, horizontalStarBlockAtIndex)));
                    }                                   
                                                                                        
                    threads++;                                  
                }while(threads< threadEstimatorValue);            
                long leftOutBlocks = totalStrength - THREADS_PER_N_BLOCKS * threadEstimatorValue;
                if(leftOutBlocks > 0){                
                    valueStart = valueStop;
                    valueStop = valueStop + leftOutBlocks;                        
                    if(horizontalStarBlockAtIndex == null){
                        listOfCallables.add(executorService.submit(new StarBlockChainVerticalThreadPool(valueStart, valueStop)));
                    }else{
                        listOfCallables.add(executorService.submit(new StarBlockChainHorizontalThreadPool((int)valueStart, (int)valueStop, horizontalStarBlockAtIndex)));
                    }
                }
                for(Future<Boolean> futureResult : listOfCallables){    
                    
                    try {
                        result = futureResult.get(VERIFICATION_TIMEOUT_NUMBER, VERIFICATION_TIMEUNIT);                    
                    } catch (TimeoutException e) {
                        result = false;
                        System.out.println(MetaData.VERIFICATION_TIMEOUT);
                    } 
                                                                                
                    if(!result){break;}
                }    
            }
            return result;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }                                                            
    public boolean isStarBlockChainValid()throws Exception{
        try{
            boolean result = false;
            long totalVerticalStrength = Long.valueOf(versionCount.size());                                        
            long threadEstimatorValue = threadEstimator(totalVerticalStrength);                        
            ExecutorService executorService = Executors.newFixedThreadPool(threads(threadEstimatorValue));                             
            result = loopChainVerification(threadEstimatorValue, totalVerticalStrength, executorService, null);
            executorService.shutdownNow();                      
            return result;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }    
}