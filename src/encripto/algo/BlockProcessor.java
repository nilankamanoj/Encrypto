/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package encripto.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author nilanka
 */
public class BlockProcessor {
    private int blockSize=64;
    private int interBlockSize = 8;
    
    public String enBlock(String key, String text){
        String out ="";
        String [] keyParts = getParts(key,7);
        
        String [] blocks = getParts(text,blockSize);
        int blkCount = blocks.length;
        String keyPart1 = keyParts[0];
                
        int keyLength = keyPart1.length();
        int i =0;
        
        while(i<blkCount){
            String ref = blocks[i];            
            int position = i%keyLength;
            String positionStr = String.valueOf(keyPart1.charAt(position));
            int shift = Integer.parseInt(positionStr);
            
            if(shift ==1){
            int newIndex = i+ 1;
            if(newIndex<blkCount){
                
            }
            else{
                newIndex = newIndex - blkCount;
            }
            String temp = blocks[i];
            blocks[i] = blocks[newIndex];
            blocks[newIndex] = temp;
            }
            i++;
        }
        
        for(String part : blocks){
            out+=part;
        }
        return out;
        
    }
    
    
    public String deBlock(String key, String text){
        String out ="";
        String [] keyParts = getParts(key,7);
        String [] blocks = getParts(text,blockSize);
        int blkCount = blocks.length;
        String keyPart1 = keyParts[0];
        
        int keyLength = keyPart1.length();
        int i =0;
        
        while(i<blkCount){
            int position = (blkCount-1-i)%keyLength;
            String positionStr = String.valueOf(keyPart1.charAt(position));
            int shift = Integer.parseInt(positionStr);
            
            if(shift ==1){
            int newIndex = (blkCount-1-i)+1;
            if(newIndex<blkCount){
                
            }
            else{
                newIndex = newIndex - blkCount;
            }
            String temp = blocks[(blkCount-1-i)];
            blocks[(blkCount-1-i)] = blocks[newIndex];
            blocks[newIndex] = temp;
            }
            i++;
        }
        
        for(String part : blocks){
            out+=part;
        }
        return out;
        
    }
    
    
    
    
    public String[] getParts(String text, int size){
        
           return text.split("(?<=\\G.{"+ size +"})");

    }
   

}
