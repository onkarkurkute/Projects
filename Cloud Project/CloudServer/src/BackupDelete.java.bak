import java.io.*;
import java.net.*;


// Sending File From Backup to Cloud

public class BackupDelete  extends Thread
{
    int portno;
    String folderName;

    public BackupDelete(int portno, String folderName) 
	{
        this.portno = portno;
        this.folderName = folderName;
    }
  
    public void run()
    {
        try
        {
            
            ServerSocket ss = new ServerSocket (portno);
            
            while ( true )
            {
                
                System.out.println("Server Started");                
                Socket tcp = ss.accept();            
                ObjectInputStream din = new ObjectInputStream(tcp.getInputStream());
                ObjectOutputStream dout = new ObjectOutputStream(tcp.getOutputStream());
                BlockDetails bd = (BlockDetails)din.readObject();
                System.out.println( " Recvd Request " + bd.getUserName() + " " + bd.getFileName() + " " + bd.getBlockNo() );                
                String path = folderName + "//" + bd.getUserName() + "//"+bd.getFileName();             
                File fin=new File(path);
                fin.delete();
				dout.writeUTF("yes");
                dout.flush();
                dout.close();
                tcp.close();             
                System.out.println("success");
                
            }


                    
            
            
            
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }
}
