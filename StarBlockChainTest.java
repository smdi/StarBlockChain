import com.aidev.starblockchain.StarBlockChain;

import java.util.ArrayList;
import java.util.Arrays;

public class StarBlockChainTest{

    public static void main(String []args){

        long startTime = System.nanoTime();
        
        ArrayList<String> userNames = new ArrayList<>(Arrays.asList("Imran"));
        StarBlockChain starBlockChain = new StarBlockChain(userNames);
        // starBlockChain.newStarBlock("Confidential data v1");
        // starBlockChain.newStarBlock("Confidential data v2", true);        

        // starBlockChain.newStarBlock("Music Notes v1");
        // starBlockChain.newStarBlock("Music Notes v2", true);
        // starBlockChain.newStarBlock("Music Notes v3", true);

        // starBlockChain.newStarBlock("Confidential data v3", true, false, 0);

        // starBlockChain.newStarBlock("Music Notes v4", true);

        // starBlockChain.newStarBlock("Home Loan v1");
        // starBlockChain.newStarBlock("Home Loan v2", true);
        
        // for(int i=0; i<1000; i++){
        //     starBlockChain.newStarBlock("Home Loan v"+(3+i), true);
        // }
        
        // starBlockChain.newStarBlock("Confidential data v4", true, false, 0);
        
        // for(int i=0; i<1000; i++){
        //     starBlockChain.newStarBlock("Music Notes v"+(4+i), true, false, 1);
        // }

        //starblock chain without versioning
        for(int i=0; i<200; i++){
            starBlockChain.newStarBlock("Confidential data v"+i);
        }

        System.out.println(starBlockChain);
        boolean isValid = starBlockChain.isStarBlockChainValid();
        System.out.println("Is StarBlockChain Valid? "+isValid);
        long endTime   = System.nanoTime();  
        double duration = (double)(endTime - startTime)/1000000000;      
        System.out.println(duration);                
        System.out.println("Total network strength "+starBlockChain.getTotalTensorNetworkStrength());
    }

}