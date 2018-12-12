/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filmwordproject.helper;

import filmwordproject.model.Movie;
import filmwordproject.view.AdminMenu;

public class Separator {
    
    public void read(String cadena, int pointer){
        String[] movieStr = cadena.split(";");
        for(String m: movieStr){
            String[] movie = m.split(",");
            String number = movie[1].trim();
            Movie newMovie = new Movie(pointer +1, movie[0], Integer.valueOf(number), movie[2], movie[3], 0.0);
            AdminMenu.movies[pointer] = newMovie;
            pointer++;
        }
    
    }
    
}
