package com.aidev.starblockchain;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

public class StarBlockChain {
    
    private List<String> userNames;
    private LinkedHashMap<Long, ArrayList<StarBlock>> starBlocks;
    private LinkedHashMap<Long, Long> versionCount;
    private long totalTensorNetworkStrength = 0;
    private long index = 0;

    public StarBlockChain(List<String> userNames){
        starBlocks = new LinkedHashMap<Long, ArrayList<StarBlock>>();
        versionCount = new LinkedHashMap<Long, Long>();
        setUserNames(userNames);
    }
    public void newStarBlock(String data, boolean related){
        getNewStarBlockLogic(data, related, true, -1);
    }       
    public void newStarBlock(String data, boolean related, boolean currentIndex, long index){                
        getNewStarBlockLogic(data, related, currentIndex, index);        
    }
    private void getNewStarBlockLogic(String data, boolean related, boolean currentIndex, long index) {
        if(starBlocks.size() == 0){            
            getNewStarBlockAtIndex(this.index, data, false, false);            
        }else{
            if(related && currentIndex){
                getNewStarBlockAtIndex(this.index, data, true, false);
            }else if(related && !(currentIndex)){
                getNewStarBlockAtIndex(index, data, true, true);
            }else{
                this.index = this.index + 1;
                getNewStarBlockAtIndex(this.index, data, false, false);
            }
        }        
        setTotalTensorNetworkStrength(++totalTensorNetworkStrength);
    }
    private void getNewStarBlockAtIndex(long index, String data, boolean addNewRelated, boolean fetchHorizontalPreviousIndex) {        
        if(addNewRelated){
            ArrayList<StarBlock> indexedStarblocks = starBlocks.get(index);
            long horizontalIndex = this.index; long verticalIndex = Long.valueOf(indexedStarblocks.size());            
            if(fetchHorizontalPreviousIndex){
                long []indexes = getHorizontalpreviousIndexes(index, indexedStarblocks);
                horizontalIndex = indexes[0]; verticalIndex = indexes[1];
            }
            indexedStarblocks.add(getNewStarBlock(data, 
                getHorizontalPreviousHash_relatedFrom(index, indexedStarblocks), 
                null, horizontalIndex, verticalIndex));
            starBlocks.put(index, indexedStarblocks);            
            updateVersionCode(index, Long.valueOf(indexedStarblocks.size()));
        }else{
            String hash = null;
            if(starBlocks.size() != 0){
                hash = getVerticalPreviousHash_unRelatedFrom(index, starBlocks.get(index -1));
            }
            starBlocks.put(index, new ArrayList<>(Arrays.asList(getNewStarBlock(data,
                    null, 
                    hash, this.index, 0))));
            updateVersionCode(index, Long.valueOf(1));
        }        
    }
    private StarBlock getNewStarBlock(String data, String horizontalPreviousHash_relatedFrom, 
                     String verticalPreviousHash_unrelatedFrom, long  horizontalIndex, long verticalIndex){
        
        return new StarBlock(System.currentTimeMillis(), horizontalPreviousHash_relatedFrom, 
                    verticalPreviousHash_unrelatedFrom, data, getRandoUserName(userNames), 
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
    private String getRandoUserName(List<String> userNames){
        int index = (int)(Math.random() * userNames.size());
        return userNames.get(index);        
    }
    private void updateVersionCode(Long index, Long count){
        versionCount.put(index, count);
    }
    public List<String> getUserNames() {
        return userNames;
    }
    private void setUserNames(List<String> userNames) {
        this.userNames = new ArrayList<>(userNames);
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
    
}
