import com.aidev.starblockchain.StarBlockChain;

import java.util.ArrayList;
import java.util.Arrays;

public class StarBlockChainTest{

    public static void main(String []args){

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
        starBlockChain.newStarBlock("Home Loan v3", true);

        starBlockChain.newStarBlock("Confidential data v4", true, false, 0);
        starBlockChain.newStarBlock("Music Notes v4", true, false, 1);

        System.out.println(starBlockChain);        

    }

}