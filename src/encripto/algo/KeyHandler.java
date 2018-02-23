/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encripto.algo;

import java.util.Random;

/**
 *
 * @author nilanka
 */
public class KeyHandler {
    
    public String getKey() {
  
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 5;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int) 
              (random.nextFloat() * (rightLimit - leftLimit + 1));
            
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();

        return generatedString;
    }
    
    public String processKeyPermutation(String key){
        int ref = ((int)(char)key.charAt(0)) * ((int)(char)key.charAt(1)) ;
        return Integer.toBinaryString(ref);
    }
    public String processKeyBlock(String key){
        int ref = ((int)(char)key.charAt(2)) * ((int)(char)key.charAt(3)) * ((int)(char)key.charAt(4));
        return Integer.toBinaryString(ref);
    }
    
    
}
