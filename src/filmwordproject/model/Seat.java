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
public class Seat {
    
    private int idSeat;
    private String row;
    private String column;
    private boolean available;

    public Seat(int idSeat, String row, String column, boolean available) {
        this.idSeat = idSeat;
        this.row = row;
        this.column = column;
        this.available = available;
    }

    public int getIdSeat() {
        return idSeat;
    }

    public void setIdSeat(int idSeat) {
        this.idSeat = idSeat;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
