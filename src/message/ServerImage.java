package message;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;

public class ServerImage {
	

	  
	  //public static ArrayList<PrintWriter> al = new ArrayList<>();
	  public static ArrayList<OutputStream> ol = new ArrayList<>();
	  
	    public static void main(String[] args) throws Exception  {

	    
	        System.out.println("Server Image signing On");
	        ServerSocket ss = new ServerSocket(8000);
	        for ( int i = 0; i< 10; i++) {
	            Socket soc = ss.accept();
	            CONN c = new CONN(soc);
	            c.start();
	        }
	        System.out.println("Server Image signing Off");
	    }
	        
	}


	class CONN extends Thread {

	    Socket soc;
	    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

	    public CONN(Socket soc) {
	        this.soc = soc;
	    }

	    @Override
	    public void run() {
	        System.out.println("Conversation Image thread  "+ 
	                                        Thread.currentThread().getName() + 
	                                         "   signing On");
	          
	    
	        	
            BufferedImage image = null;

	     
            
            
            try {
                ServerImage.ol.add(soc.getOutputStream());


                image = ImageIO.read(soc.getInputStream());
                

            } catch (IOException ex) {
            }
            
              try {
            
            	 
            	  
            	 
                       while(true) {
                    	   
                    	   System.out.println("sizzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzze ===> "+ServerImage.ol.size());
                          for(OutputStream o : ServerImage.ol){
                        	  /*PrintWriter nos = new PrintWriter(
                                      new BufferedWriter(
                                          new OutputStreamWriter(
                                              o
                                          )
                                      ), true
                                  );
                        	  
                        	  nos.println("1");*/
                        	  
                        	  if(soc.getOutputStream() != o) {
                        		  
  			    				  if(image!=null)
                                  ImageIO.write(image, "PNG", o);
                                  

                        	  }

                          }
                          
          					System.out.println("befoooooooooooooooooooore READ SERV IMG");


                            image = ImageIO.read(soc.getInputStream());
                          
                          
          					System.out.println("afteeeeeeeeeeeeeeeeeeeeer READ SERV IMG");


                          
                       }
                          

                           
            
        } catch (IOException ex) {
        }
	            
	     
	        	 
	        
	        

	    
	    
	     System.out.println("Conversation thread  "+
	                                     Thread.currentThread().getName() +
	                                      "   signing Off");
	    }
	    
	


	   
}
