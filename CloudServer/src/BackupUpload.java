import java.io.*;
import java.net.*;



// Receiving File From Cloud
public class BackupUpload extends Thread
{
    
    int portno;
    String folderName;

    public BackupUpload(int portno, String folderName) 
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
                java.util.ArrayList<BlockDetails> bd = (java.util.ArrayList<BlockDetails>)din.readObject();
                din.close();
                tcp.close();                
                System.out.println( " Recvd " + bd.get(0).userName +" " + bd.get(0).fileName + " " + bd.get(0).blockNo + " " + bd.size());                
                String path = folderName + "//" + bd.get(0).userName;                
                if ( !new File(path).exists())
                    new File(path).mkdir();
                ObjectOutputStream dout = new ObjectOutputStream(new FileOutputStream(path+"//"+bd.get(0).fileName));
                dout.writeObject(bd);
                dout.flush();
                dout.close();
                System.out.println("Success");
            }                    
            
            
            
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }
    
}
