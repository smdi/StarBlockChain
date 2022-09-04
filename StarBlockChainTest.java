import com.aidev.starblockchain.StarBlockChain;

import java.util.ArrayList;
import java.util.Arrays;

public class StarBlockChainTest{

    public static void main(String []args){

        long startTime = System.nanoTime();
        
        ArrayList<String> userNames = new ArrayList<>(Arrays.asList("Imran"));
        StarBlockChain starBlockChain = new StarBlockChain();
        starBlockChain.newStarBlock("Confidential data v1", userNames);
        starBlockChain.newStarBlock("Confidential data v2", true, userNames);        

        starBlockChain.newStarBlock("Music Notes v1", userNames);
        starBlockChain.newStarBlock("Music Notes v2", true, userNames);
        starBlockChain.newStarBlock("Music Notes v3", true, userNames);

        starBlockChain.newStarBlock("Confidential data v3", true, false, 0, userNames);

        starBlockChain.newStarBlock("Music Notes v4", true, userNames);

        starBlockChain.newStarBlock("Home Loan v1", userNames);
        starBlockChain.newStarBlock("Home Loan v2", true, userNames);
        
        for(int i=0; i<500; i++){
            starBlockChain.newStarBlock("Home Loan v"+(3+i), true, userNames);
        }
        
        // starBlockChain.newStarBlock("Confidential data v4", true, false, 0);
        
        // for(int i=0; i<1000; i++){
        //     starBlockChain.newStarBlock("Music Notes v"+(4+i), true, false, 1);
        // }

        // starblock chain without versioning
        for(int i=0; i<500; i++){
            starBlockChain.newStarBlock("Data v"+i, userNames);
        }

        // System.out.println(starBlockChain);
        try{
            boolean isValid = starBlockChain.isStarBlockChainValid();
            System.out.println("Is StarBlockChain Valid? "+isValid);
        }catch(Exception e){
            e.printStackTrace();
        }
                
        long endTime   = System.nanoTime();  
        double duration = (double)(endTime - startTime)/1000000000;      
        System.out.println(duration);                
        System.out.println("Total network strength "+starBlockChain.getTotalTensorNetworkStrength());
    }

}