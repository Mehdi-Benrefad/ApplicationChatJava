package message;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.GridLayout;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.text.DefaultCaret;

import com.formdev.flatlaf.FlatLightLaf;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.util.*;
import static javax.swing.JOptionPane.*;


public class Client{
	
public static java.awt.Color tableCol []= new Color[10];
protected static String uname;
protected static boolean i;

	
    static {
    	
    	tableCol[0]=Color.BLACK;
		tableCol[1]=Color.BLUE;
		tableCol[2]=Color.RED;
		tableCol[3]=Color.GRAY;
		tableCol[4]=Color.GREEN;
		tableCol[5]=Color.ORANGE;
		tableCol[6]=Color.YELLOW;
		tableCol[7]=Color.PINK;
		tableCol[8]=Color.DARK_GRAY;
		tableCol[9]=Color.magenta;
		
    }
    /**
     * @param args the command line arguments
     */
    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    
    public static void main(String[] args) throws Exception  {
    	

    
    	try {
    	    UIManager.setLookAndFeel( new FlatLightLaf() );
    	} catch( Exception ex ) {
    	    System.err.println( "Failed to initialize LaF" );
    	}     
    	
    		
        Socket soc = new Socket("localhost",9081);
        Socket sp = new Socket("localhost",8000);
        Scanner sc = new Scanner(System.in);
        
        
        BufferedReader nis = new BufferedReader(new InputStreamReader(soc.getInputStream()));
        
        BufferedReader nip = new BufferedReader(new InputStreamReader(sp.getInputStream()));

        PrintWriter nos = new PrintWriter(new BufferedWriter(new OutputStreamWriter(soc.getOutputStream())),true);
       
        PrintWriter nop = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sp.getOutputStream())),true);

        
        JFrame frame = new JFrame("Get Username");
        String uname = new String();
        uname = JOptionPane.showInputDialog(frame, "Enter your Username :");
        if (uname == null) {
            uname = "Unknown";
        }
        
        Client.uname = uname;
        
        JFrame f1 = new JFrame(uname);
        JButton b1 = new JButton("Send");
        JTextArea  ta = new JTextArea ();
        DefaultCaret caret = (DefaultCaret)ta.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        
        int min = 0;
        int max = 9;
        ta.setForeground(Client.tableCol[(int)Math.floor(Math.random()*(max-min+1)+min)]);
        
        ta.setEditable(false);
        JTextField tf = new JTextField(20);
        
        ///////////////////////////////////////
         JPanel py = new JPanel();
         JPanel pp = new JPanel();
         
         JButton btncme = new JButton("Select Image");
     
         pp.add(BorderLayout.CENTER,btncme);

         
         btncme.addActionListener(new ActionListener() {


			@Override
			public void actionPerformed(ActionEvent arg0) {
		         BufferedImage bi;
		         JButton btn = null;
		         JFrame jf = new JFrame("Send Image "+Client.uname);
		         

				JFileChooser choose = new JFileChooser(
		                 FileSystemView
		                 .getFileSystemView()
		                 .getHomeDirectory()
		             );
		         
		         choose.setDialogTitle("Selectionnez une image");
		         choose.setAcceptAllFileFilterUsed(false);
		         FileNameExtensionFilter filter = new FileNameExtensionFilter("Images JPG et GIF et PNG", "jpg", "gif","png");
		         choose.addChoosableFileFilter(filter);
		         int res = choose.showOpenDialog(null);
		         if (res == JFileChooser.APPROVE_OPTION) {
		           System.out.println(choose.getSelectedFile().getPath());
		           choose.setVisible(false);
		         }
		         
		         py.add(choose,BorderLayout.CENTER);
		         f1.add(BorderLayout.NORTH,py);
		         
		         try {
		        	 
					bi= ImageIO.read(new File(choose.getSelectedFile().getPath()));
					
					jf.add(btn = new JButton("Send"), BorderLayout.NORTH);
			        jf.add(new JLabel(new ImageIcon(bi)), BorderLayout.CENTER);
			        jf.setSize(450, 200);
			        jf.setVisible(true);
			        //jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
			        jf.setLocationRelativeTo(null);
			        
			        btn.addActionListener(new ActionListener() {
			             @Override
			             public void actionPerformed(ActionEvent arg0) {
			            	 
			            	 jf.setVisible(false);
		                	
			            	
			                 try {
			                	 
			                	 System.out.println("*********before writing**********");
			                	 
			                	 
			             
				                     ImageIO.write(bi, "PNG", sp.getOutputStream());
				     	    		// nos.println(Client.uname+" : "+"Image Sent from me see the result ");
				             

			                	 System.out.println("*********after writing**********");

			                		 
			                        


			                
			                     
			                     //Client.i = true;
			                     
			                     //System.out.println(soc.getInputStream().toString());
			                    
			           
			                 } catch (IOException e) {
			                     // TODO Auto-generated catch block
			                     e.printStackTrace();
			                 }
			             }
			         });
			        
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		         
		         
		       
		         


			}
        	 
         });
         
         f1.add(BorderLayout.NORTH,pp);

        ///////////////////////////////////////
        ////////////////////////////////////////
        JPanel px = new JPanel();
        JScrollPane pane = new JScrollPane(px);
        px.add(ta);
        px.setLayout(new GridLayout(0,1));
        f1.add(pane);
        
        ///////////////////////////////////////
        JPanel p1 = new JPanel();
        p1.add(tf);
        p1.add(b1);
       // f1.add(ta);
        f1.add(BorderLayout.SOUTH,p1);
        ChatListener l1 = new ChatListener(tf,nos,uname,f1,soc,ta);
        b1.addActionListener(l1);
        tf.addActionListener(l1);
        f1.setSize(400,400);
        //f1.setLocationRelativeTo(null);
        f1.setVisible(true);
        
        ////////////
        /*JScrollPane pane = new JScrollPane(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        f1.setContentPane(pane);*/
        ///////////
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    
        	new Thread(new Runnable() {
				
				@Override
				public void run() {
					
					while(true) {
						
						try {
							
						
						//if(nip.readLine().equals("1")) {

			        		try {
			    				
								System.out.println("befoooooooooooooooooooore READ CLIENT");

			    				JFrame jlb = new JFrame("received for "+Client.uname);

			    		        BufferedImage image;
			    		        
			    				image = ImageIO.read(sp.getInputStream());
			    				
								System.out.println("Afteeeeeeeeeeeeeeeeeeeeer READ CLIENT");

			    			    
			    				if(image!=null) {
			    					
			    					jlb.setSize(300, 200);
				    	            jlb.add(new JLabel(new ImageIcon(image)), BorderLayout.CENTER);
				    	            jlb.setVisible(true);
				    	            jlb.setLocationRelativeTo(f1);
				    	            
				    	            JButton jbtn = new JButton("Save Image");
				    	            
				    	            jlb.add(BorderLayout.NORTH,jbtn);
				    	            
				    	            jbtn.addActionListener(new ActionListener() {
				    	               
				    					@Override
				    					public void actionPerformed(ActionEvent arg0) {
				    						
				    		                      /*  Container content = jlb.getContentPane();
				    		                        BufferedImage img = new BufferedImage(content.getWidth(), content.getHeight(), BufferedImage.TYPE_INT_RGB);
				    		                        Graphics2D g2d = image.createGraphics();
				    		                        content.printAll(g2d);
				    		                        g2d.dispose();*/

				    		                        try {
				    		                        	JFileChooser chooser = new JFileChooser(); 
				    		                        	    chooser.setCurrentDirectory(new java.io.File("."));
				    		                        	    chooser.setDialogTitle("Select root directory to save");
				    		                        	    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				    		                        	    chooser.setLocation(jlb.getLocation().x, jlb.getLocation().y);
				    		                        	 

				    		                        	    //
				    		                        	    // disable the "All files" option.
				    		                        	    //
				    		                        	    chooser.setAcceptAllFileFilterUsed(false);
				    	            	     		         int res = chooser.showOpenDialog(null);

				    		                        	    if (res == JFileChooser.APPROVE_OPTION) { 
				    		                        	      //System.out.println("getCurrentDirectory(): " 
				    		                        	       //  +  chooser.getCurrentDirectory().getAbsolutePath());
				    		                        	     // System.out.println(chooser.getSelectedFile().getPath());
				    		                        	      System.out.println(chooser.getSelectedFile().getAbsolutePath());

				    		                        	      String location = JOptionPane.showInputDialog(jlb, "Enter your Picture Name:");
				    		  	                            
				    		                        	      
				    		  	                            ImageIO.write(image, "png", new File(chooser.getSelectedFile().getAbsolutePath()+"\\"+location+".png"));
				    		  	                			showMessageDialog(jlb, "Download Image", "Image Downloaded Successfully", INFORMATION_MESSAGE);
				    		                        	      }
				    		           		         
				    		                        	

				    		                        } catch (Exception ex) {
				    		                            ex.printStackTrace();
				    		                        }
				    		                   
				    						
				    					}
				    	            });
			    				}
			    				
			    		

			    			}catch(Exception e) {
			    				e.printStackTrace();
			    			}
			    	            
			        	//}
						
						}catch(Exception e){
							e.printStackTrace();
							
						}
					}
					
				}
			}).start();
        	
        		
        	
        	new Thread(new Runnable() {
				
				@Override
				public void run() {
					
					while(true) {
						
						try {
							//if(nis.readLine().equals("0")) {
								   String str = nis.readLine();
					                
							        
					    	        while(!str.equals("End")){
					    	        	
					    	        		if(!str.contains("cls")) {
					    	        			
					    	        			if(!str.contains("0")) {
					    	        				
					    	        	      		 ta.append(str + "\n" );

					    	        			}
					    	        		
					    	        			
					    	        		}
					    	    
					    	        	
					    	            str = nis.readLine();
			
					    	        }
					    	        
							//}
						}catch(Exception e) {
							e.printStackTrace();
						}
			    	        
					} 
					
					
		        		
					
				}
			}).start();
           	 
             
        
        	
	
    
	        
        	
     
	
	        

	    
        
        /* ta.append("Client Signing Off");
        Thread.sleep(1000);
        System.exit(0);*/
    }
    
    
}

class ChatListener implements ActionListener {
	

	
   JTextField tf ;
   PrintWriter nos;
   String uname;
   JFrame f;
   Socket soc;
   JTextArea tt;
   
    public ChatListener(JTextField tf,PrintWriter nos,String uname,JFrame f,Socket sc,JTextArea ta){
        this.tf = tf;
        this.nos = nos;
        this.uname = uname;
        this.f = f;
        this.soc = sc;
        this.tt = ta;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	
    	
        String str  = tf.getText();
        
        if(str.equals("cls")) {
        	tt.setText(null);
        }
      
		if(str.equals("")) {
			
			
			showMessageDialog(f, "Empty message not allowed", "Warning Message", ERROR_MESSAGE);
			
         
 
    
    	}else {
    		

    		nos.println(uname+" : "+str);
            tf.setText("");
            
		
           
            
           
    	}
        	
        
    }
    

}
