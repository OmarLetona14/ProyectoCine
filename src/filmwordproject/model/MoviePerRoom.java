/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filmwordproject.model;

/**
 *
 * @author Omar
 */
public class MoviePerRoom {
    
    private int idMoviePerRoom;
    private Movie movie;
    private Sala sala;
    private TimeTable timeTable;

    public MoviePerRoom(int idMoviePerRoom, Movie movie, Sala sala, TimeTable timeTable) {
        this.idMoviePerRoom = idMoviePerRoom;
        this.movie = movie;
        this.sala = sala;
        this.timeTable = timeTable;
    }

    public MoviePerRoom() {
    }
    
    

    public int getIdMoviePerRoom() {
        return idMoviePerRoom;
    }

    public void setIdMoviePerRoom(int idMoviePerRoom) {
        this.idMoviePerRoom = idMoviePerRoom;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public TimeTable getTimeTable() {
        return timeTable;
    }

    public void setTimeTable(TimeTable timeTable) {
        this.timeTable = timeTable;
    }
}
