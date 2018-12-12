/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filmwordproject.view;

import filmwordproject.helper.Clock;
import filmwordproject.helper.RandomNumber;
import filmwordproject.helper.Report;
import filmwordproject.helper.Separator;
import filmwordproject.model.Movie;
import filmwordproject.model.MoviePerRoom;
import filmwordproject.model.PeliculasTransmitidas;
import filmwordproject.model.Sala;
import filmwordproject.model.Seat;
import filmwordproject.model.TimeTable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class AdminMenu {
    
    Scanner scan = new Scanner(System.in);
    public static Movie[] movies = new Movie[100];
    public static MoviePerRoom[] assigned = new MoviePerRoom[100];
    public static MoviePerRoom[] transmitidas = new MoviePerRoom[100];
    Login login;
    int option, time,correct,count;
    String movieName, producer, language, cadena;       
    Clock clock = new Clock();
    boolean exists = false;
    Seat[][] seats=null;
    Separator separator = new Separator();
    Movie currentMovie, movieCurr=new Movie();
    MoviePerRoom assign;
    Sala currentSala,salaCurr =new Sala();
    RandomNumber random = new RandomNumber();
    TimeTable timeTable,horario=new TimeTable();
    Report report = new Report();
    
    
    public void menu(){
        System.out.println("Bienvenido Administrador");
        System.out.println("Por favor, elija la opcion que desea realizar: ");
        System.out.println("1. Ver hora actual");
        System.out.println("2. Ingresa pelicula");
        System.out.println("3. Asignacion de peliculas a salas");
        System.out.println("4. Simulacion de peliculas");
        System.out.println("5. Reportes");
        System.out.println("6. Cerrar sesion");
        System.out.println("7. Salir");
        try{option = scan.nextInt();}catch(Exception e){}
        switch(option){
            case 1:
                currentTime();
                 menu();
                break;
            case 2:
                enterMovie();
                break;
            case 3:
                assignMovie();
                break;
            case 4:
                simulate();
                menu();
                break;
            case 5:
                report();
                break;
            case 6:
                login = new Login();
                login.login();
                break;
            case 7:
                System.exit(0);
            break;
        }   
    
    }
    
    public void currentTime(){
        System.out.println("La hora actual es: "+clock.getCurrentTime());
    }
    
    public void enterMovie(){
        System.out.println("*****INGRESAR PELICULA*****");
        System.out.println("1. Ingreso de pelicula manual");
        System.out.println("2. Ingreso de pelicula por lote");
        System.out.println("3. Atras");
        try{option = scan.nextInt();}catch(Exception e){}
        switch(option){
            case 1:
                movieManual();
                break;
            case 2:
                movieAutomatic();
                break;
            case 3:
                menu();
                break;
            default:
                
        }
    }
    public void movieAutomatic(){
        System.out.println("*****INGRESAR PELICULA*****");
        System.out.println("-------------------------------------");
        System.out.println("Ingrese la cadena de caracteres");
        cadena = scan.nextLine();
        cadena = scan.nextLine();
        System.out.println("Ingresando pelicula(s)...");
        try{separator.read(cadena, moviePointer());
            System.out.println("Pelicula(s) ingresadas correctamente");
            enterMovie();
        }catch(Exception e){
            System.out.println("Hubo un error al intentar ingresar la pelicula,"
                    + " por favor, intentelo de nuevo");
            enterMovie();
        }
    }
    
    
    public void movieManual(){
        System.out.println("*****INGRESAR PELICULA*****");
        System.out.println("-------------------------------------");
        movieName = scan.nextLine();
        System.out.println("Ingrese el nombre de la pelicula");
        movieName = scan.nextLine();
        System.out.println("Ingrese el tiempo de duracion de la pelicula");
        try{time = Integer.valueOf(scan.nextLine());}catch(NumberFormatException e){}
        System.out.println("Ingrese el productor de la pelicula");
        producer = scan.nextLine();
        System.out.println("Ingrese el lenguaje de la pelicula");
        language = scan.nextLine();
        System.out.println("Ingresando pelicula...");
        try{
            Movie movie = new Movie(moviePointer()+1, movieName, time, producer, language,0.0);
            movies[moviePointer()] = movie;
            System.out.println("Pelicula ingresada correctamente");
            enterMovie();
        }catch(Exception e){
            System.out.println("Hubo un error al intentar ingresar la pelicula,"
                    + " por favor, intentelo de nuevo");
            enterMovie();
        }
    }
    
    public void assignMovie(){
        System.out.println("Asignacion de peliculas por sala");
        System.out.println("1. Asignacion manual");
        System.out.println("2. Asignacion automatica");
        try{option = scan.nextInt();}catch(Exception e){}
        switch(option){
            case 1:
                assignManual();
                menu();
                break;
            case 2:
                assignAutomatic();
                break;
            default:
                
        }
    }
    
    public void generateReport(){
        System.out.println("********** REPORTE 3 **********");
        System.out.println("Peliculas que se pasaron en cada sala");
        System.out.println("Por favor, ingrese el numero de la sala: ");
        try{option = scan.nextInt();}catch(Exception e){}
        if(serchSala(option)!=null){
            report.report3(serchSala(option));
        }else{
            System.out.println("********** SALA INVALIDA **********");
            menu();
        }
    }
    
    public Sala serchSala(int id){
        for(Sala sala: Login.salas){
            if(sala!=null){
                if(sala.getIdSala()==id){
                    return sala;
                }
            }
        }return null;
    }
    
    public Movie generateRandomMovie(){
        return movies[random.generateRandomID(moviePointer())];
    }
    
    public TimeTable generateRandomTimeTable(){
        return Login.horarios[random.generateRandomID(Login.horarios.length)];
    }
    
    public Sala generateRandomSala(){
        return Login.salas[random.generateRandomID(Login.salas.length)];
    }
    
    public boolean movieExists(MoviePerRoom movie){
        for(MoviePerRoom assignMov: assigned){
            if(assignMov!=null){
                if(movie.getSala()==assignMov.getSala()
                        && movie.getTimeTable()==assignMov.getTimeTable()){
                    return true;
                }
            }
        }
     return false;
    }
    
    public void simulate(){
        currentTime();
        System.out.println("Se transmitirán las peliculas del siguiente horario...");
        try{
            simuHorary(clock.convertToDate(clock.getCurrentTime()));
            clock.changeTime();
            System.out.println("Pelicula(s) transimitadas correctamente");
        }catch(Exception e){
            System.out.println("Hubo un error al intentar transmitir las peliculas");
        }
        
    }
    
    public void simuHorary(Date init){
       for(MoviePerRoom asgn: assigned){
            if(asgn!=null){
                if(clock.convertToString(asgn.getTimeTable().getInit()).equals(clock.convertToString(init))){ 
                    transmitidas[transmitidasPointer()] = asgn; 
                    assigned[asgn.getIdMoviePerRoom()-1] = null;
                }
            }
        }
    }
    
    
    public void assignAutomatic(){ 
           while(correct < moviePointer()){
               try{
                    movieCurr = generateRandomMovie();
                   salaCurr = generateRandomSala();
                   horario = generateRandomTimeTable();                 
                   MoviePerRoom movie = new MoviePerRoom(moviePerRoomPointer()+1, movieCurr,salaCurr,horario);
                    if(!movieExists(movie)){
                        assigned[moviePerRoomPointer()] = movie;
                        correct++;   
                    }
                }catch(Exception e){
                    System.out.println("OCURRIO UN ERROR AL INTENTAR ASIGNAR LAS PELICULAS");
                    menu();
                }
           }   
           System.out.println("Peliculas asignadas correctamente");
            menu();
    }
    
    public void assignManual(){
       
        printMovies();
        System.out.println("Por favor, ingrese el numero de la pelicula que desea asignar");
        try{option = scan.nextInt();
            currentMovie = selectedMovie(option);
        }catch(Exception e){
            System.out.println("Ocurrio un problema con la pelicula seleccionada");
        }
        System.out.println("Por favor, ingrese el numero de la sala de desea asignar");
        printSala();
        try{option = scan.nextInt();
            currentSala=selectedSala(option);
        }catch(Exception e){
            System.out.println("Ocurrio un problema con la sala seleccionada");
        }
        System.out.println("Por favor, ingrese el numero del horario que desea asignar");
        printHorary();
        try{option = scan.nextInt();
            timeTable = selectedHorary(option);
        }catch(Exception e){
            System.out.println("Ocurrio un problema con el horario seleccionado");
        }
        System.out.println("Realizando asignacion...");
        assign = new MoviePerRoom(moviePerRoomPointer()+1,currentMovie, currentSala, timeTable);
        assigned[moviePerRoomPointer()] = assign;
        System.out.println("Asignacioln realizada correctamente");
    }
    
    
    public TimeTable selectedHorary(int selected){
        for(TimeTable horary: Login.horarios){
            if(horary!=null){
                if(horary.getIdTimeTable()==selected){
                    return horary;
                }
            }
        }return null;
    }
    
    public void report(){
        System.out.println("A continuacion se muestran los reportes de cada una de las peliculas");
        System.out.println("********** PELICULA CON MAS GANANCIAS **********");
        report.report1();
        System.out.println("********** SALAS CON MAS ASIENTOS VENDIDOS ********** ");
        report.report2();
        generateReport();
        menu();
    
    }
    
    public Sala selectedSala(int selected){
        for(Sala sala: Login.salas){
            if(sala!=null){
                if(sala.getIdSala()==selected){
                    return sala;
                }
            }
        }
    return null;
    }
    
    public void printAssigned(){
        for(MoviePerRoom asg: assigned){
            if(asg !=null){
                System.out.println(asg.getIdMoviePerRoom()+". Pelicula: "+asg.getMovie().getName()+"\n"
                       + "Horario: de "+getCurrentTime(asg.getTimeTable().getInit())+" a "+ getCurrentTime(asg.getTimeTable().getFinish())
               + "En: "+asg.getMovie().getLanguage());           
            }
        }   
    }
    
    public boolean verifyHorary(TimeTable horary, Sala sala){
        for(MoviePerRoom as:assigned){
            if(as!=null){
                if(as.getSala()==sala){
                    if(as.getTimeTable()==horary){
                    return true;
                    }
                }
            }
        }    
        return false;
    }
    
    public String getCurrentTime(Date date){
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        return hourFormat.format(date);
    }
    
    public void printHorary(){
        for(TimeTable horary: Login.horarios){
            if(horary!=null){
                if(!verifyHorary(horary,currentSala)){
                System.out.println(horary.getIdTimeTable()+". Horario de: " +getCurrentTime(horary.getInit())+ " a "+
                        getCurrentTime(horary.getFinish()));
                }
            }
        }  
    }
    
    public Movie selectedMovie(int selected){
        for(Movie movie: movies){
            if(movie!=null){
                if(movie.getIdMovie()==selected){
                    return movie;
                }
            }
        
        }
        return null;
    }
    
    public void printMovies(){
        for(Movie movie: movies){
            if(movie!=null){
                System.out.println(movie.getIdMovie()+". Pelicula:"+movie.getName()+"\n"
                        +"Duracion: "+movie.getTime() +" minutos"+"\n"
                                +"Lenguaje: "+movie.getLanguage()+"\n"
                                +"Productor: "+movie.getProducer()+" \n");
            }
        }
    }
    
    public void printSala(){
        for(Sala sala:Login.salas){
            if(sala!=null){
                System.out.println(sala.getIdSala()+ ". Sala: "+sala.getName());
            }
        }
    }
    
    int transmitidasPointer(){
        int count = 0;
        for(MoviePerRoom transmitida: transmitidas){
            if(transmitida!=null){
                count++;
            }
        }
        return count;
    }
    
    int moviePointer(){
        int count = 0;
        for(Movie movie: movies){
            if(movie!=null){
                count++;
            }
        }
    return count;
    }
    
    int moviePerRoomPointer(){
        int count = 0;
        for(MoviePerRoom asgn: assigned){
            if(asgn!=null){
                count++;
            }
        }
    return count;
    }
    
}
