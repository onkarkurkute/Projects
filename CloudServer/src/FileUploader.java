import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class FileUploader extends Thread
{
    
        ArrayList<ArrayList<BlockDetails>> blocks = new ArrayList<ArrayList<BlockDetails>>();
        public static final long DELAY = 5000;
        
        public void retrieveFile( String userName , String fileName , long fileLength )throws Exception
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
                    int port = Integer.parseInt(p[2]);
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
                        
                        ArrayList<BlockDetails> newBd = (ArrayList<BlockDetails>)in.readObject();
                        if(newBd!=null)
							blocks.add(newBd);
                        in.close();
                        out.close();
                        tcp.close();
                        System.out.println( "Recvd Block No " + i + "From " + ip );
                    }
                    catch(Exception e)
                    {
                        System.out.println( " Block No " + i + "Not Present" );
                        e.printStackTrace();
                    }
            }
            
            
            int actualData[] = new int [ size ];
            int a = 0;
            int div = size/127;
            int rem = size%127;
            
            for ( int i = 0 ; i < div ; i++ )
            {
                int encdata[] = new int [ 127*2 ];
                int k = 0 ;
                for ( ArrayList<BlockDetails> bd : blocks )
                {
                        BlockDetails t = bd.get(i);
                        for ( int j = 0 ; j < t.data.length ; j++)
                            encdata[k++] = t.data[j];                        
                }
                RsDecode dec = new RsDecode(127);
                dec.decode(encdata);
                for ( int j = 0 ; j < 127 ; j++ )
                    actualData[a++] = encdata[j];                
            }
            if ( rem > 0 )
            {
                int encdata[] = new int [ rem*2 ];
                int k = 0 ;
                for ( ArrayList<BlockDetails> bd : blocks )
                {
                        BlockDetails t = bd.get(bd.size()-1);
                        for ( int j = 0 ; j < t.data.length ; j++)
                            encdata[k++] = t.data[j];                        
                }
                RsDecode dec = new RsDecode(rem*2); 
               dec.decode(encdata);
                for ( int j = 0 ; j < rem ; j++ )
                    actualData[a++] = encdata[j];                
                
                
            }
            
            
            
            File file = new File ( "d://data1//"+userName+"//"+fileName);
            FileOutputStream fout = new FileOutputStream(file);
            for(int i=0;i<actualData.length;i++)
            fout.write(actualData[i]);
            fout.close();
            
            
            
            
        }
    
        public void run()
        {
        try
        {
            
            while (true )
            {
                File dir = new File ( "d://download//");
                String fileNames[] = dir.list();
                for ( String fileName : fileNames )
                {
                        File file = new File (dir.getAbsolutePath() + "//" + fileName);
                        BufferedReader fr = new BufferedReader ( new FileReader ( dir.getAbsolutePath() + "//" + fileName ));
                        String username = fr.readLine();
                        String filename = fr.readLine();
                        int fileSize = Integer.parseInt(fr.readLine());
                        fr.close();
                        System.out.println( "Retrieving File " + " UserName " + username + " FileName " + filename );
                        retrieveFile(username, filename, fileSize);
                        System.out.println(  username + " " + filename + " Retrived Successfully!!");
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
