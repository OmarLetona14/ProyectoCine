/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filmwordproject.model;

public class Sala {
    
    private int idSala;
    private String name;
    private Seat[][] seatList;
    private int seatsSaled;

    public Sala(int idSala, String name, Seat[][] seatList, int seatsSaled) {
        this.idSala = idSala;
        this.name = name;
        this.seatList = seatList;
        this.seatsSaled = seatsSaled;
    }

    public Sala() {
    }

    public int getIdSala() {
        return idSala;
    }

    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Seat[][] getSeatList() {
        return seatList;
    }

    public void setSeatList(Seat[][] seatList) {
        this.seatList = seatList;
    }

    public int getSeatsSaled() {
        return seatsSaled;
    }

    public void setSeatsSaled(int seatsSaled) {
        this.seatsSaled = seatsSaled;
    }

    
}
