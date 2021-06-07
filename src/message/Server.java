package message;

import java.io.*;

import java.net.*;
import java.util.*;

import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;



public class Server {
	


	
		

    public static ArrayList<PrintWriter> al = new ArrayList<>();
    public static void main(String[] args) throws Exception  {

    	try {
    	    UIManager.setLookAndFeel( new FlatLightLaf() );
    	} catch( Exception ex ) {
    	    System.err.println( "Failed to initialize LaF" );
    	}     
    
        System.out.println("Server signing On");
        ServerSocket ss = new ServerSocket(9081);
        for ( int i = 0; i< 10; i++) {
            Socket soc = ss.accept();
            Conversation c = new Conversation(soc);
            c.start();
        }
        System.out.println("Server signing Off");
    }
        
}
class Conversation extends Thread {

    Socket soc;
    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    public Conversation(Socket soc) {
        this.soc = soc;
    }

    @Override
    public void run() {
        System.out.println("Conversation thread  "+ 
                                        Thread.currentThread().getName() + 
                                         "   signing On");
          
    
        	
       
            try {

            BufferedReader nis = new BufferedReader(
                new InputStreamReader(
                    soc.getInputStream()
                )
            );
            
           PrintWriter nos = new PrintWriter(
                new BufferedWriter(
                    new OutputStreamWriter(
                        soc.getOutputStream()
                    )
                ), true
            );
           
           
            Server.al.add(nos);
            
            System.out.println("Taille dyal   "+Server.al.size());
            String str = nis.readLine();
            while(!str.equals("End")){
                for(PrintWriter o : Server.al){
                	//o.println("0");
                    o.println(str);
             }
                str = nis.readLine();
            }
            nos.println("End");            
        }
        catch(Exception e){
            System.out.println("Client Seems to have abruptly closed the connection");
        }
          
            
     
        	 
        
        

    
    
     System.out.println("Conversation thread  "+
                                     Thread.currentThread().getName() +
                                      "   signing Off");
    }
    

}
