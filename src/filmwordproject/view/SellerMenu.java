/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filmwordproject.view;

import filmwordproject.helper.Clock;
import filmwordproject.model.MoviePerRoom;
import filmwordproject.model.Sale;
import filmwordproject.model.Seat;
import static filmwordproject.view.AdminMenu.assigned;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class SellerMenu {
    
    Scanner reader = new Scanner(System.in);
    MoviePerRoom currentMovie;
    Seat[][] asientos;
    int option, seatCount;
    double pay, change;
    String seatRange;
    Clock clock = new Clock();
    Sale sale;
    public static Sale[] sales = new Sale[100];
    
    public void menu(){
        System.out.println("Bienvenido vendedor");
        System.out.println("Por favor, introduzca el numero de la opcion que desea realizar");
        System.out.println("1. Hora actual");
        System.out.println("2. Vender asientos");
        System.out.println("3. Cerrar sesion");
        System.out.println("4. Salir");
        try{option = reader.nextInt();}catch(Exception e){}
        switch(option){
            case 1:
                currentTime();
                 menu();
                break;
            case 2:
                if(AdminMenu.assigned.length>=1){
                    sell();
                }else{
                    System.out.println("NO HAY NINGUNA PELICULA ASIGNADA");
                }
                break;
            case 3:
                Login login = new Login();
                login.login();
                break;
            case 4: 
                System.exit(0);
                break;
        }
        
    }
    
    public void currentTime(){
        System.out.println("La hora actual es: "+clock.getCurrentTime());
    }
    
    public void sell(){
        System.out.println("-------------Venta de boletos----------------");
        System.out.println("Por favor, seleccione el numero de la pelicula");
        printAssigned();
        try{option = reader.nextInt();}catch(Exception e){}
        currentMovie = selectedMovie(option);
        System.out.println("Pelicula seleccionada correctamente ");
        System.out.println("Asientos disponibles");
        makeSala();
        System.out.println("Por favor, indique los asientos que desee");
        seatRange = reader.nextLine();
        seatRange = reader.nextLine();
        if(seats(seatRange)){
            payment();
        }        
    }
    
    public void payment(){
    double total = 30*seatCount;
            System.out.println("Total: "+total);
            System.out.println("Pago: ");
            pay = reader.nextDouble();
            int payInt = (int) pay;
            String payStr = String.valueOf(payInt);
            if(validPay(payStr)){
                if(pay>total){
                change  = pay-total;
                System.out.println("Pelicula: "+currentMovie.getMovie().getName());
                System.out.println(currentMovie.getSala().getName());
                System.out.println("Asiento(s): " +seatRange);
                System.out.println("Total: "+total);
                System.out.println("Pago en efectivo: "+pay);
                System.out.println("Cambio: "+change);
            }else if(pay==total){
                System.out.println("Pelicula: "+currentMovie.getMovie().getName());
                System.out.println(currentMovie.getSala().getName());
                System.out.println("Asiento(s): " +seatRange);
                System.out.println("Total: "+total);
                System.out.println("Pago en efectivo: "+pay);
                System.out.println("Cambio: "+0.0);
            }else if(pay<total){
                System.out.println("*********Pago incompleto*************");
                payment();
            }
            currentMovie.getMovie().setTotal(currentMovie.getMovie().getTotal()+total);    
            currentMovie.getSala().setSeatsSaled(currentMovie.getSala().getSeatsSaled()+seatCount);
            sale = new Sale(salePointer()+1, currentMovie, seatRange, total);
            sales[salePointer()] = sale;
            System.out.println("----------Venta realizada correctamente-----------");
            seatCount = 0;
            menu(); 
            
            }else{
                System.out.println("Pago invalido");
                payment();}
               
    
    }
    
    int salePointer(){
        int count = 0;
        for(Sale s: sales){
            if(s!=null){
                count++;
            }
        }
        return count;
    }
    
    public boolean seats(String choose){
        String[] chooseStr = choose.split("-");
        String init = chooseStr[0];
        String fina="";
        if(chooseStr.length>=2){
            fina = chooseStr[1];
        }
        for(int x=0; x<=7;x++){
                if(asientos[1][x].getRow().equals(init.substring(0, 1))){
                    for(int y=0; y<=7;y++){
                        String seat = asientos[y][x].getRow()+asientos[y][x].getColumn();
                        if(chooseStr.length>=2){
                            if(seat.equals(fina)){
                            asientos[y][x].setAvailable(false);
                            seatCount++;
                            return true;
                            }else{
                                asientos[y][x].setAvailable(false);
                                seatCount++;
                            }
                        }else{
                            if(seat.equals(init)){
                                asientos[y][x].setAvailable(false);
                                seatCount++;
                                return true;
                            }
                        }
                    }  
                }
            }       
        return false;
    }
    
    public MoviePerRoom selectedMovie(int selected){
        for(MoviePerRoom asg: assigned){
            if(asg!=null){
                if(asg.getIdMoviePerRoom()==selected){
                    return asg;
                }
            }
        }
        return null;
    }
    
    public void makeSala(){
        String line = "";
        asientos = null;
        asientos = currentMovie.getSala().getSeatList();
        for(int x = 0; x<=7; x++){
            for(int y = 0; y<=4;y++){
                if(asientos[y][x].isAvailable()){
                    if(y==4){
                        line += "|"+asientos[y][x].getRow()+asientos[y][x].getColumn()+"|";
                        System.out.println("|"+line + "|");
                        line = "";
                    }else{
                        line += "|"+asientos[y][x].getRow()+asientos[y][x].getColumn()+"|";}
                }else{
                    if(y==4){
                        line += "|"+"* "+"|";
                        System.out.println("|"+line+"|");
                        line = "";
                    }else{
                        line += "|"+"* "+"|";
                    }  
                }
            }
        }
    }
    
    public boolean validPay(String pay){
        return pay.endsWith("0") || pay.endsWith("5");
    }
    
    public void printAssigned(){
        for(MoviePerRoom asg: assigned){
            if(asg !=null){ 
                System.out.println(asg.getIdMoviePerRoom()+". Pelicula: "+asg.getMovie().getName()+"\n"
                       + "Horario: de "+getCurrentTime(asg.getTimeTable().getInit())+" a "+ getCurrentTime(asg.getTimeTable().getFinish()) + "\n"
                               + asg.getSala().getName() + "\n"
               + "En: "+asg.getMovie().getLanguage());           
            }
        }   
    }
    
    public String getCurrentTime(Date date){
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        return hourFormat.format(date);
    }
    
}
