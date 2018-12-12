/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filmwordproject.view;

import filmwordproject.helper.Clock;
import filmwordproject.model.Sala;
import filmwordproject.model.Seat;
import filmwordproject.model.SystemUser;
import filmwordproject.model.TimeTable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Login {
    
    Scanner reader = new Scanner(System.in);
    public static Sala[] salas = new Sala[5];
    public static SystemUser[] users = new SystemUser[2];
    public static TimeTable[] horarios = new TimeTable[6];
    Seat[][] seats =null;
    AdminMenu admin = new AdminMenu();
    SellerMenu seller = new SellerMenu();
    Clock clock = new Clock();
    String userName, password;
    
    public void login(){
        generateSala();
        createUser();
        generateTimeTable();
        clock.initTime();
        System.out.println("BIENVENIDO AL SISTEMA DE FILM WORD");
        System.out.println("Por favor, ingrese sus credenciales");
        System.out.println("Nombre de usuario: ");
        userName = reader.nextLine();
        System.out.println("Contraseña: ");
        password = reader.nextLine();
        if(verifyLogin(userName, password)){
            if(verifyAdmin(userName)){
                admin.menu();
            }else{
                seller.menu();
            }
        }else{
            System.out.println("------------------Usuario y/o contraseña incorrectos--------------");
            login();
        }
    }
    
    
    public void generateSala(){
        Sala sala;
        for(int i = 0; i<=4; i++){
            generateSeats();
            sala = new Sala(i+1, "Sala " + (i+1), seats,0);
            salas[i] = sala;
        }
    }
    
    public void generateSeats(){
        seats = new Seat[5][8];
        Seat seat;
        int count = 1;
        for(int x = 0; x<=7; x++){
            for(int y = 0; y<=4;y++){
                seat = new Seat(count,generateRowName(x),String.valueOf(y),true);
                seats[y][x] = seat;
                count++;
            }
        }
    }
    
    
    
    public void generateTimeTable(){
        int init = 13, fin = 15;
        String timeStrInit, timeStrFinal;
        TimeTable horary;
        for(int i=0; i<=5;i++ ){
            timeStrInit = init+":00:00";
            timeStrFinal = fin+":00:00";
            Date initTime = clock.convertToDate(timeStrInit);
            Date finalTime = clock.convertToDate(timeStrFinal);
            horary = new TimeTable(i+1,initTime,finalTime);
            init +=2;
            fin += 2;
            horarios[i] = horary;
        }
    
    }
    
    public String generateRowName(int x){
        switch(x){
            case 0:
                return "a";
            case 1:
                return "b";
            case 2:
                return "c";
            case 3:
                return "d";
            case 4:
                return "e";
            case 5:
                return "f";
            case 6:
                return "g";
            case 7:
                return "h";   
        }
        return "";
    }
    
    public void createUser(){
        SystemUser admin = new SystemUser(1, "ADMIN","Administrador","201700377" );
        SystemUser seller = new SystemUser(2, "VENDEDOR","Vendedor", "201700377");
        users[0] = admin;
        users[1] = seller;
    }
    
    public boolean verifyAdmin(String userName){
        return userName.equals("ADMIN");
    }
    
    public boolean verifyLogin(String user, String password){
        for(SystemUser systemUser: users){
            if(systemUser.getName().equals(user)){
                return systemUser.getPassword().equals(password);
            }
        }
        return false;
    }
    
}
