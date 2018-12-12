/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filmwordproject.model;

import java.util.Date;

/**
 *
 * @author Omar
 */
public class TimeTable {
    
    private int idTimeTable;
    private Date init;
    private Date finish;

    public TimeTable(int idTimeTable, Date init, Date finish) {
        this.idTimeTable = idTimeTable;
        this.init = init;
        this.finish = finish;
    }

    public TimeTable() {
    }
    

    public int getIdTimeTable() {
        return idTimeTable;
    }

    public void setIdTimeTable(int idTimeTable) {
        this.idTimeTable = idTimeTable;
    }

    public Date getInit() {
        return init;
    }

    public void setInit(Date init) {
        this.init = init;
    }

    public Date getFinish() {
        return finish;
    }

    public void setFinish(Date finish) {
        this.finish = finish;
    }
 
}
