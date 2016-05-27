import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class FileDistributor  extends Thread
{
    
        ArrayList<ArrayList<BlockDetails>> blocks = new ArrayList<ArrayList<BlockDetails>>();
        public static final long DELAY = 5000;
    
        public void writeBlocks ( String filePath , String fileName , String userName , int newrdata[] , int div)throws Exception
        {
                    int n = CloudServerFrame.bs.size();                        
                    int blocksize = newrdata.length / n ;                                
                    for ( int i2 = 1 , k  = 0 ; i2 <= n ; i2++ )
                    {                                        
                        int  block[] = new int [blocksize];                    
                        for ( int j1 = 0 ; j1 < blocksize ; j1++  )
                        block[j1] = newrdata[k++];             
                    
                        BlockDetails bd = new BlockDetails();
                        bd.setData(block);
                        bd.setFileName(fileName);
                        bd.setUserName(userName);
                        bd.setBlockNo(i2);
                        bd.setDivNo(div);
                        
                        blocks.get(i2-1).add(bd);
                    
                        

                    }
            
            
        }
        
        public void DistributeFile(File f)throws Exception
        {
            
            blocks.clear();
            for ( int i = 0 ; i < CloudServerFrame.bs.size(); i++)
                blocks.add(new ArrayList<BlockDetails>());
            
            String filePath = f.getAbsolutePath();
            String fileName = filePath.substring( filePath.lastIndexOf("\\")+1 , filePath.lastIndexOf("."));                                             
            String userName = f.getParentFile().getName();
            
            int size =(int) f.length();
            int n = CloudServerFrame.bs.size();                        
            FileInputStream fin = new FileInputStream(f);
            int data[] = new int [ fin.available()];
            int d = 0 , x  = 0  ;
            while ( ( d = fin.read())!=-1)
                data[x++] = d;               
            fin.close();
            
            
            int div = size/127;
            int rem = size%127;
                
            int j = 0 ;
            for ( int  i = 0   ; i < div ; i++ )
            {
                    int rdata[] = new int[127*2];
                    
                    for ( int i1 = 0  ; i1 < 127 ; i1++ )
                    rdata[i1] = data[j++];              
                    
                    RsEncode enc = new RsEncode(127);
                    enc.encode(rdata);
                    
                    int newrdatalength = rdata.length + rdata.length%n;
                    int newrdata[] = new int[newrdatalength];
                    for ( int x1 = 0 ; x1 < rdata.length ; x1++)
                        newrdata[x1]=rdata[x1];
                    for (int x1 = rdata.length ;  x1 < newrdatalength ; x1++)
                        newrdata[x1] = 0;
                    
                    writeBlocks(filePath, fileName, userName, newrdata,i);                    
                    
                    
            }
            
            if ( rem > 0 )
            {
                    int rdata[] = new int[rem*2];
                    
                    for ( int i1 = 0  ; i1 < rem ; i1++ )
                    rdata[i1] = data[j++];              
                    
                    RsEncode enc = new RsEncode(rem);
                    enc.encode(rdata);
                    
                    int newrdatalength = rdata.length + rdata.length%n;
                    int newrdata[] = new int[newrdatalength];
                    for ( int x1 = 0 ; x1 < rdata.length ; x1++)
                        newrdata[x1]=rdata[x1];
                    for (int x1 = rdata.length ;  x1 < newrdatalength ; x1++)
                        newrdata[x1] = 0;
                    
                    writeBlocks(filePath, fileName, userName, newrdata,div+1);                    
                
            }
            for ( int i2 = 1; i2 <= n ; i2++ )
            {                                        
            
                        ArrayList<BlockDetails> bd = blocks.get(i2-1);
                        String str = CloudServerFrame.bs.get(i2-1);
                        String tp[] = str.split(":");
                        String ip = tp[0];
                        int port = Integer.parseInt(tp[1]);
                        System.err.println("Test");
                        System.err.println("Ip :"+ip);
                        System.err.println("Ip :"+port);
                        Socket s = new Socket (ip,port);
                        ObjectOutputStream fout = new ObjectOutputStream (s.getOutputStream());
                        fout.writeObject(bd);
                        fout.flush();
                        Thread.sleep(2000);
                        fout.close();                                   
                
            }
                                
            
             
            
           
            
            
            
            
        }
        
        public void run()
        {
            try
            {
                System.out.println("Distribution Started");
                
                while ( true )
                {
                    File d1=  new File ( "d://Data");                
                    String subdirNames[] = d1.list();                
                    for ( String str  : subdirNames)
                    {
                        File d2 = new File ( d1.getAbsolutePath() + "//" + str );
                        //System.out.println("Checking Folder" + d2.getAbsolutePath());
                        if ( d2.isDirectory())
                        {
                            String fileNames[] = d2.list();

                            for ( String fileName : fileNames)
                            {
                                File d3 = new File (d2.getAbsolutePath()+"//"+fileName);
                                System.out.println("Distributing File" + d3.getAbsolutePath());
                                DistributeFile(d3);
                                System.out.println(d3.getAbsoluteFile() + "Distributed Successfully!!");
                                d3.delete();
                            }

                        }
                    }
                    Thread.sleep(DELAY);
                }
                
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    
}
