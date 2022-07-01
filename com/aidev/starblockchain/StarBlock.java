package com.aidev.starblockchain;

public class StarBlock {
            
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
    private String dataStoreImmutable;
    private int nonceUserValue;    
    private String userName;

    public StarBlock(){}
    public StarBlock(long creationTimeStamp,
            String horizontalPreviousHash_relatedFrom, String horizontalNextHash_relatedTo,
            String verticalPreviousHash_unrelatedFrom, String verticalNextHash_unrelatedTo,
            long defaultVersionNumberRelated, long currentVersionNumberRelated, long latestVersionNumberRelated,
            long defaultVersionNumberUnrelated, long currentVersionNumberUnrelated, long latestVersionNumberUnrelated,
            String dataStoreImmutable, int nonceUserValue, String UserName) {        
        setCreationTimeStamp(creationTimeStamp);        
        setHorizontalPreviousHash_relatedFrom(horizontalPreviousHash_relatedFrom);
        setHorizontalNextHash_relatedTo(horizontalNextHash_relatedTo);
        setVerticalPreviousHash_unrelatedFrom(verticalPreviousHash_unrelatedFrom);
        setVerticalNextHash_unrelatedTo(verticalNextHash_unrelatedTo);
        setDefaultVersionNumberRelated(defaultVersionNumberRelated);
        setCurrentVersionNumberRelated(currentVersionNumberRelated);
        setLatestVersionNumberRelated(latestVersionNumberRelated);
        setDefaultVersionNumberUnrelated(defaultVersionNumberUnrelated);
        setCurrentVersionNumberUnrelated(currentVersionNumberUnrelated);
        setLatestVersionNumberUnrelated(latestVersionNumberUnrelated);        
        setDataStoreImmutable(dataStoreImmutable);
        setUserName(userName);        
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
    public long getDefaultVersionNumberRelated() {
        return defaultVersionNumberRelated;
    }
    private void setDefaultVersionNumberRelated(long defaultVersionNumberRelated) {
        this.defaultVersionNumberRelated = defaultVersionNumberRelated;
    }
    public long getCurrentVersionNumberRelated() {
        return currentVersionNumberRelated;
    }
    private void setCurrentVersionNumberRelated(long currentVersionNumberRelated) {
        this.currentVersionNumberRelated = currentVersionNumberRelated;
    }
    public long getLatestVersionNumberRelated() {
        return latestVersionNumberRelated;
    }
    protected void setLatestVersionNumberRelated(long latestVersionNumberRelated) {
        this.latestVersionNumberRelated = latestVersionNumberRelated;
    }
    public long getDefaultVersionNumberUnrelated() {
        return defaultVersionNumberUnrelated;
    }
    private void setDefaultVersionNumberUnrelated(long defaultVersionNumberUnrelated) {
        this.defaultVersionNumberUnrelated = defaultVersionNumberUnrelated;
    }
    public long getCurrentVersionNumberUnrelated() {
        return currentVersionNumberUnrelated;
    }
    private void setCurrentVersionNumberUnrelated(long currentVersionNumberUnrelated) {
        this.currentVersionNumberUnrelated = currentVersionNumberUnrelated;
    }
    public long getLatestVersionNumberUnrelated() {
        return latestVersionNumberUnrelated;
    }
    protected void setLatestVersionNumberUnrelated(long latestVersionNumberUnrelated) {
        this.latestVersionNumberUnrelated = latestVersionNumberUnrelated;
    }    
    public String getDataStoreImmutable() {
        return dataStoreImmutable;
    }
    private void setDataStoreImmutable(String dataStoreImmutable) {
        this.dataStoreImmutable = dataStoreImmutable;
    }
    private int getNonceUserValue() {
        return nonceUserValue;
    }
    private void setNonceUserValue(int nonceUserValue) {
        this.nonceUserValue = nonceUserValue;
    }
    public String getUserName() {
        return userName;
    }
    private void setUserName(String userName) {
        this.userName = userName;
    }
    private String str(){
        return null;
    }
    public String toString(){
        return null;
    }
    protected static String calculateCurrentHash(StarBlock starBlock){
        return null;
    }
    protected void mineStarBlock(){

    } 
}
