import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class FileDelete extends Thread
{
    
        ArrayList<ArrayList<BlockDetails>> blocks = new ArrayList<ArrayList<BlockDetails>>();
        public static final long DELAY = 5000;
        
        public void deleteFile( String userName , String fileName , long fileLength )throws Exception
        {
        
            blocks.clear();
            int size =(int) fileLength;
            int n = CloudServerFrame.bs.size();            
            
            //java.util.ArrayList<BlockDetails> blocks = new ArrayList<BlockDetails>();
            for (int i = 0; i < n; i++)
            {                    
                    String s = CloudServerFrame.bs.get(i);
                    String p[] = s.split(":");
                    String ip = p[0];
                    int port =6666;
                    System.out.println( "Sending Request To " + ip + " Port " + port );
                    try
                    {
                        Socket tcp = new Socket ( ip,port);
                        ObjectOutputStream out = new ObjectOutputStream(tcp.getOutputStream());
                        ObjectInputStream in = new ObjectInputStream ( tcp.getInputStream());
                        BlockDetails bd = new BlockDetails();
                        bd.setBlockNo(i+1);                     
                        bd.setFileName(fileName.substring( 0,fileName.lastIndexOf(".")));
                        bd.setUserName(userName);
                        out.writeObject(bd);
                        out.flush();
                        Thread.sleep(1000);
                        
                        String de=in.readUTF();
                        in.close();
                        out.close();
                        tcp.close();
                        if(de.equals("yes"))
						{
							System.out.println( "Recvd Block No " + i + "From " + ip );
						}
                    }
                    catch(Exception e)
                    {
                        System.out.println( " Block No " + i + "Not Present" );
                        e.printStackTrace();
                    }
            }
        }
    
        public void run()
        {
        try
        {
            
            while (true )
            {
                File dir = new File ( "d://delete//");
                String fileNames[] = dir.list();
                for ( String fileName : fileNames )
                {
                        File file = new File (dir.getAbsolutePath() + "//" + fileName);
                        BufferedReader fr = new BufferedReader ( new FileReader ( dir.getAbsolutePath() + "//" + fileName ));
                        String username = fr.readLine();
                        String filename = fr.readLine();
                        int fileSize = Integer.parseInt(fr.readLine());
                        fr.close();
                        System.out.println( "Deleting File " + " UserName " + username + " FileName " + filename );
                        deleteFile(username, filename, fileSize);
                        System.out.println(  username + " " + filename + "  Deleted Successfully!!");
                        file.delete();
                        
                }
                Thread.sleep(DELAY);
  
                
         }
            
            
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
}
