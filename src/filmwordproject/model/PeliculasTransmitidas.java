/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filmwordproject.model;

public class PeliculasTransmitidas {
    
    private Sala sala;
    private Movie[] movies;

    public PeliculasTransmitidas(Sala sala, Movie[] movies) {
        this.sala = sala;
        this.movies = movies;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Movie[] getMovies() {
        return movies;
    }

    public void setMovies(Movie[] movies) {
        this.movies = movies;
    }
}
