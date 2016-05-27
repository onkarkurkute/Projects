
import java.io.*;
import java.net.*;


// Sending File From Backup to Cloud

public class BackupDownload  extends Thread
{
    int portno;
    String folderName;

    public BackupDownload(int portno, String folderName) 
	{
        this.portno = portno;
        this.folderName = folderName;
    }

    
    
    
    
    public void run()
    {
        try
        {
            
            ServerSocket ss = new ServerSocket (portno);
            Socket tcp;
            ObjectInputStream din=null;
                ObjectOutputStream dout=null;
				ObjectInputStream fin=null;
            java.util.ArrayList<BlockDetails> newBd=null;
			while ( true )
            {
                
                System.out.println("Server Started");                
                tcp = ss.accept();            
                din = new ObjectInputStream(tcp.getInputStream());
                dout = new ObjectOutputStream(tcp.getOutputStream());
                BlockDetails bd = (BlockDetails)din.readObject();
                System.out.println( " Recvd Request " + bd.getUserName() + " " + bd.getFileName() + " " + bd.getBlockNo() );                
                String path =folderName + "//" + bd.getUserName() + "//"+bd.getFileName();             
                System.out.println(path);
				try
				{
				   fin = new ObjectInputStream(new FileInputStream(path));
                   newBd = (java.util.ArrayList<BlockDetails>)fin.readObject();	
				    fin.close();
				}
				catch (Exception e)
				{
				}
				
                dout.writeObject(newBd);
                dout.flush();
                dout.close();
                tcp.close();             
                System.out.println("success");
                
            }


                    
            
            
            
        }catch(Exception e)
        {
            e.printStackTrace();
        }
		finally
		{
			
			
		}
        
    }
}
