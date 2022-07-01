package com.aidev.starblockchain;

import java.util.List;

public class StarBlockChain extends StarBlock{
    
    private List<String> userNames;
    private List<StarBlock> starBlocks;

    public StarBlockChain(List<String> userNames){
        setUserNames(userNames);
    }
    public List<String> getUserNames() {
        return userNames;
    }
    private void setUserNames(List<String> userNames) {
        this.userNames = userNames;
    }        

}
