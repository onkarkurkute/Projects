import java.io.*;
import java.net.*;
import java.util.Scanner;


public class BackupServer 
{
    public static void main(String a[])
    {
                    
            java.util.Scanner sin = new Scanner(System.in);                    
            System.out.println ( "Enter backup folder");
            String folderName =sin.next();            
            System.out.println("Enter PortNo For Download ");
            int downloadportno = sin.nextInt();            
            System.out.println("Enter PortNo For Upload ");
            int uploadportno = sin.nextInt();            
	    System.out.println("Enter PortNo For Delete ");
            int deleteportno = sin.nextInt(); 
                    //6666;            
            BackupDownload d1 = new BackupDownload(downloadportno, folderName);
            BackupUpload d2 = new BackupUpload(uploadportno, folderName);            
	    BackupDelete d3 = new BackupDelete(deleteportno, folderName);            
            d1.start();
            d2.start();
			d3.start();
            
            
            
    
        
    }
    
}
