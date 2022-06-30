package com.aidev.starblockchain;

public class StarBlock {

    private long horizontalIndex;
    private long verticalIndex;        
    private long creationTimeStamp;
    private String currentHash;
    private String horizontalPreviousHash_relatedFrom;
    private String horizontalNextHash_relatedTo;
    private String verticalPreviousHash_unrelatedFrom;
    private String verticalNextHash_unrelatedTo;
    private long defaultVersionNumberRelated;
    private long currentVersionNumberRelated;    
    private long latestVersionNumberRelated;
    private long defaultVersionNumberUnrelated;
    private long currentVersionNumberUnrelated;    
    private long latestVersionNumberUnrelated;        
    private long totalTensorNetworkStrength;    
    private String dataStoreImmutable;
    private int nonceUserValue;
}
