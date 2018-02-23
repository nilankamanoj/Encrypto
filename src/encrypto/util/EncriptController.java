/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encrypto.util;

import encripto.algo.BlockProcessor;
import encripto.algo.KeyHandler;
import encripto.algo.permutator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author nilanka
 */
public class EncriptController {
    private int blockSize=64;
    public String[] encrypt(String text){
        text= prepare(text);
        String[] out = new String[2];
        KeyHandler keyHandler = new KeyHandler();
        permutator permutator = new permutator();
        BlockProcessor blockProcessor = new BlockProcessor();
        String key = keyHandler.getKey();
        String keyPermutate = keyHandler.processKeyPermutation(key);
        String keyBlocker = keyHandler.processKeyBlock(key);
        for(int i = 0; i<16; i++){
            text = blockProcessor.enBlock(keyBlocker, text);
            text = permutator.permutate(keyPermutate, text);
        }
            
            
                       
       
        
        out[0] = key;
        out[1] = text;
        return out;
        
    }
    
    public String decrypt(String text, String key){
        
        KeyHandler keyHandler = new KeyHandler();
        permutator permutator = new permutator();
        BlockProcessor blockProcessor = new BlockProcessor();
        String keyPermutate = keyHandler.processKeyPermutation(key);
        String keyBlocker = keyHandler.processKeyBlock(key);
        for(int i = 0; i<16; i++){
            text = permutator.dePermutate(keyPermutate, text); 
            text = blockProcessor.deBlock(keyBlocker, text);
        }
                

            
            
       
        
        
        return text;
        
    }
    public String prepare(String text){
       return text + repeat(" ", blockSize-text.length()%blockSize) ;
    }
    public String repeat(String s, int n) {
        return Stream.generate(() -> s).limit(n).collect(Collectors.joining(""));
    }
}
