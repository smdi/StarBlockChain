package com.aidev.starblockchain;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.concurrent.*;

public class StarBlockChain extends StarBlock{
        
    private LinkedHashMap<Long, ArrayList<StarBlock>> starBlocks;
    private LinkedHashMap<Long, Long> versionCount;
    private long totalTensorNetworkStrength = 0;
    private long index = 0;    
    private boolean RELATED_FALSE = false;    
    private int THREADS_PER_N_BLOCKS = 500;
    private int MIN_THREADS_PARALLEL_EXECUTION = 5;
    private int MED_THREADS_PARALLEL_EXECUTION = 10;
    private int MAX_THREADS_PARALLEL_EXECUTION = 20;
    private String VERIFICATION_TIMEOUT = "Verification timeout, try after some time!";
    private int VERIFICATION_TIMEOUT_NUMBER = 1;
    private TimeUnit VERIFICATION_TIMEUNIT = TimeUnit.SECONDS;    

    public StarBlockChain(){
        starBlocks = new LinkedHashMap<Long, ArrayList<StarBlock>>();
        versionCount = new LinkedHashMap<Long, Long>();        
    }
    public void newStarBlock(String data, ArrayList<String> userNames){
        getNewStarBlockLogic(data, RELATED_FALSE, true, -1, userNames);
    }
    public void newStarBlock(String data, boolean related, ArrayList<String> userNames){
        getNewStarBlockLogic(data, related, true, -1, userNames);
    }       
    public void newStarBlock(String data, boolean related, boolean currentIndex, long index, ArrayList<String> userNames){                
        getNewStarBlockLogic(data, related, currentIndex, index, userNames);        
    }
    private void getNewStarBlockLogic(String data, boolean related, boolean currentIndex, long index, ArrayList<String> userNames) {        
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
    }
    private void getNewStarBlockAtIndex(long index, String data, boolean addNewRelated, boolean fetchHorizontalPreviousIndex, ArrayList<String> userNames) {        
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
    }
    private StarBlock getNewStarBlock(String data, String horizontalPreviousHash_relatedFrom, 
                     String verticalPreviousHash_unrelatedFrom, long  horizontalIndex, long verticalIndex, ArrayList<String> userNames){
        
        return new StarBlock(System.currentTimeMillis(), horizontalPreviousHash_relatedFrom, 
                    verticalPreviousHash_unrelatedFrom, data, userNames, 
                    horizontalIndex, verticalIndex);
    }
    private long[] getHorizontalpreviousIndexes(long index, ArrayList<StarBlock> indexedStarblocks){
        long indexes[] = new long[2];
        int sizeOfIndexedStarblocksdex = indexedStarblocks.size();
        indexes[0] = indexedStarblocks.get(sizeOfIndexedStarblocksdex - 1).getHorizontalIndex();
        indexes[1] = indexedStarblocks.get(sizeOfIndexedStarblocksdex - 1).getVerticalIndex() + 1;
        return indexes;
    }
    private String getHorizontalPreviousHash_relatedFrom(long index, ArrayList<StarBlock> indexedStarblocks){
        int sizeOfIndexedStarblocksdex = indexedStarblocks.size();
        return indexedStarblocks.get(sizeOfIndexedStarblocksdex - 1).getCurrentHash();        
    }
    private String getVerticalPreviousHash_unRelatedFrom(long index, ArrayList<StarBlock> indexedStarblocks){
        return indexedStarblocks.get(0).getCurrentHash();
    }
    // private String getRandoUserName(List<String> userNames){
    //     int index = (int)(Math.random() * userNames.size());
    //     return userNames.get(index);        
    // }
    private void updateVersionCode(Long index, Long count){
        versionCount.put(index, count);
    }       
    public long getTotalTensorNetworkStrength() {
        return totalTensorNetworkStrength;
    }
    private void setTotalTensorNetworkStrength(long totalTensorNetworkStrength) {
        this.totalTensorNetworkStrength = totalTensorNetworkStrength;
    }
    public String toString(){
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
    }
    private boolean checkCodeforFirstStarBlockValidity(StarBlock firstBlock, long index, 
                                                String relatedHash, String currentHash){
        if (index != 0)return false;        
        if (relatedHash != null)return false;                            
        if (currentHash == null || 
              !StarBlock.calculateCurrentHash(firstBlock).equals(currentHash))return false;                                             
        return true;
    }
    private boolean isFirstStarBlockValid(StarBlock firstBlock, boolean isHorizontal) {        
        boolean result = false;                
        if(isHorizontal){            
            result = checkCodeforFirstStarBlockValidity(firstBlock, firstBlock.getVerticalIndex(), 
            firstBlock.getHorizontalPreviousHash_relatedFrom(), firstBlock.getCurrentHash());
        }else{
            result = checkCodeforFirstStarBlockValidity(firstBlock, firstBlock.getVerticalIndex(), 
            firstBlock.getVerticalPreviousHash_unrelatedFrom(), firstBlock.getCurrentHash());
        }          
        return result;
    }
    private boolean checkCodeforNewStarBlockValidity(StarBlock newBlock, StarBlock previousBlock,
                                                    long prevIndex, long newIndex, 
                                                    String newBlockPrevHash, String prevBlockCurrHash,
                                                    String newBlockcurrHash){
        if (newBlock != null  &&  previousBlock != null) {            
            if (prevIndex + 1 != newIndex) {
              return false;
            }            
            if (newBlockPrevHash == null  ||  
              !newBlockPrevHash.equals(prevBlockCurrHash)) {
              return false;
            }            
            if (newBlockcurrHash == null  ||  
              !StarBlock.calculateCurrentHash(newBlock).equals(newBlockcurrHash)) {
              return false;
            }            
            return true;
        }        
        return false;
    }
    private boolean isValidNewStarBlock(StarBlock newBlock, StarBlock previousBlock, boolean isHorizontal) {
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
    }
    private long threadEstimator(long totalStrength){
        long result = totalStrength/THREADS_PER_N_BLOCKS;        
        return result;
    }
    private int threads(long threadEstimatorValue){
        int nThreads = (threadEstimatorValue <= 20)? 
                        (threadEstimatorValue <= 10? MIN_THREADS_PARALLEL_EXECUTION : MED_THREADS_PARALLEL_EXECUTION)
                        : MAX_THREADS_PARALLEL_EXECUTION;
        return nThreads;
    }
    private class StarBlockChainHorizontalThreadPool implements Callable<Boolean>{
        private volatile boolean result = false;                
        private int valueOfHorizontalIndexToStart = 0;
        private int valueOfHorizontalIndexToStop = 0;
        ArrayList<StarBlock> horizontalStarBlockAtIndex;        
        StarBlockChainHorizontalThreadPool(int valueOfHorizontalIndexToStart, 
                        int valueOfHorizontalIndexToStop, ArrayList<StarBlock> horizontalStarBlockAtIndex){
            this.valueOfHorizontalIndexToStart = valueOfHorizontalIndexToStart;
            this.valueOfHorizontalIndexToStop = valueOfHorizontalIndexToStop;
            this.horizontalStarBlockAtIndex = horizontalStarBlockAtIndex;            
        }                
        @Override
        public Boolean call() throws Exception{
            
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
            this.valueOfVerticalIndexToStart = valueOfVerticalIndexToStart;
            this.valueOfVerticalIndexToStop = valueOfVerticalIndexToStop;            
        }                
        @Override
        public Boolean call() throws Exception{
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
        }
    }
    private boolean loopChainVerification(long threadEstimatorValue, long totalStrength, ExecutorService executorService, ArrayList<StarBlock> horizontalStarBlockAtIndex)throws Exception{
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
                System.out.println(VERIFICATION_TIMEOUT);
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
                    System.out.println(VERIFICATION_TIMEOUT);
                } 
                                                                            
                if(!result){break;}
            }    
        }
        return result;
    }                                                            
    public boolean isStarBlockChainValid()throws Exception{
        boolean result = false;
        long totalVerticalStrength = Long.valueOf(versionCount.size());                                        
        long threadEstimatorValue = threadEstimator(totalVerticalStrength);                        
        ExecutorService executorService = Executors.newFixedThreadPool(threads(threadEstimatorValue));                             
        result = loopChainVerification(threadEstimatorValue, totalVerticalStrength, executorService, null);
        executorService.shutdownNow();                      
        return result;
    }    
}