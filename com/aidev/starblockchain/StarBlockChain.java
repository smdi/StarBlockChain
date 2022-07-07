package com.aidev.starblockchain;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class StarBlockChain extends StarBlock{
    
    private List<String> userNames;
    private LinkedHashMap<Integer, ArrayList<StarBlock>> starBlocks 
                        = new LinkedHashMap<Integer, ArrayList<StarBlock>>();    
    private LinkedHashMap<Integer, Integer> versionCount 
                        = new LinkedHashMap<Integer, Integer>();
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
    public long getTotalTensorNetworkStrength() {
        return totalTensorNetworkStrength;
    }
    private void setTotalTensorNetworkStrength(long totalTensorNetworkStrength) {
        this.totalTensorNetworkStrength = totalTensorNetworkStrength;
    }
            
}
