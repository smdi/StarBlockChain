import com.aidev.starblockchain.StarBlockChain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class StarBlockChainTest{

    public static void main(String []args){

        long startTime = System.nanoTime();
        
        ArrayList<String> userNames = new ArrayList<>(Arrays.asList("Imran"));
        StarBlockChain starBlockChain = new StarBlockChain(userNames);
        starBlockChain.newStarBlock("Confidential data v1", false);
        starBlockChain.newStarBlock("Confidential data v2", true);        

        starBlockChain.newStarBlock("Music Notes v1", false);
        starBlockChain.newStarBlock("Music Notes v2", true);
        starBlockChain.newStarBlock("Music Notes v3", true);

        starBlockChain.newStarBlock("Confidential data v3", true, false, 0);

        starBlockChain.newStarBlock("Music Notes v4", true);

        starBlockChain.newStarBlock("Home Loan v1", false);
        starBlockChain.newStarBlock("Home Loan v2", true);
        
        // for(int i=0; i<1000; i++){
        //     starBlockChain.newStarBlock("Home Loan v"+(3+i), true);
        // }
        
        starBlockChain.newStarBlock("Confidential data v4", true, false, 0);
        
        // for(int i=0; i<1000; i++){
        //     starBlockChain.newStarBlock("Music Notes v"+(4+i), true, false, 1);
        // }

        System.out.println(starBlockChain);
        
        System.out.println("Is StarBlockChain Valid? "+starBlockChain.isStarBlockChainValid());
        long endTime   = System.nanoTime();
        long totalTime = TimeUnit.NANOSECONDS.toSeconds(endTime - startTime);
        System.out.println(totalTime);                
        
    }

}