/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encripto.algo;

/**
 *
 * @author nilanka
 */
public class permutator {
    
 
    public String permutate(String key, String text){
        
        String out ="";
        String out2 = "";
        int i =0;
        
        while(i<text.length()){
            int position = i%key.length();
            String positionStr = String.valueOf(key.charAt(position));
            int shift = key.length()*Integer.parseInt(positionStr);
            char chr = (char)getValuePermutate(text.charAt(i), shift);
            out += String.valueOf(chr);
            i++;
        }
        text= out;
        i =0;
        key =new StringBuffer(key).reverse().toString();
        
        while(i<text.length()){
            int position = i%key.length();
            String positionStr = String.valueOf(key.charAt(position));
            int shift = key.length()*Integer.parseInt(positionStr);
            char chr = (char)getValuePermutate(text.charAt(i), shift);
            out2 += String.valueOf(chr);
            i++;
        }
        
        return out2;
    }
    
    public String dePermutate(String key, String text){
        String out ="";
        String out2 = "";
        int i = 0;
        while(i<text.length()){
            int position = i%key.length();
            String positionStr = String.valueOf(key.charAt(position));
            int shift = key.length()*Integer.parseInt(positionStr);
            
            char chr = (char)getValueDePermutate(text.charAt(i), shift);
            out += String.valueOf(chr);
            
            i++;
        }
        text= out;
        i =0;
        key =new StringBuffer(key).reverse().toString();
        
        while(i<text.length()){
            int position = i%key.length();
            String positionStr = String.valueOf(key.charAt(position));
            int shift = key.length()*Integer.parseInt(positionStr);
            char chr = (char)getValueDePermutate(text.charAt(i), shift);
            out2 += String.valueOf(chr);
            i++;
        }
        return out2;
    }
    
    private int getValuePermutate(char chr, int key)
            
    {
        int val = (int)chr + key;
        
        if(val < 127){
            return val;
        }
        else{
            return (val-126 + 31);
        }
    }
    
    private int getValueDePermutate(char chr, int key)
            
    {
        int val = (int)chr - key;
        
        if(val < 32){
            return val + 126 -31;
        }
        else{
            return val;
        }
    }
    
    
    
    
}
