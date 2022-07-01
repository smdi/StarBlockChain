package com.aidev.starblockchain;

import java.util.List;

public class StarBlockChain extends StarBlock{
    
    private List<String> userNames;
    private List<StarBlock> starBlocks;
    private long horizontalIndex;
    private long verticalIndex;
    private long totalTensorNetworkStrength;

    public StarBlockChain(List<String> userNames){
        setUserNames(userNames);
    }
    public List<String> getUserNames() {
        return userNames;
    }
    private void setUserNames(List<String> userNames) {
        this.userNames = userNames;
    }
    public long getHorizontalIndex() {
        return horizontalIndex;
    }
    private void setHorizontalIndex(long horizontalIndex) {
        this.horizontalIndex = horizontalIndex;
    }
    public long getVerticalIndex() {
        return verticalIndex;
    }
    private void setVerticalIndex(long verticalIndex) {
        this.verticalIndex = verticalIndex;
    }
    public long getTotalTensorNetworkStrength() {
        return totalTensorNetworkStrength;
    }
    private void setTotalTensorNetworkStrength(long totalTensorNetworkStrength) {
        this.totalTensorNetworkStrength = totalTensorNetworkStrength;
    }
            
}
