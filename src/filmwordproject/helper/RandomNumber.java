/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filmwordproject.helper;


public class RandomNumber {
    
    public int generateRandomID(int size){
        int valorEntero = (int) Math.floor(Math.random()*size);
        return valorEntero;
    }
    
    
}
