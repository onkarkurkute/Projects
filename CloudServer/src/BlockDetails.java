
//import java.util.ArrayList;


public class BlockDetails implements java.io.Serializable
{
    int[] data = null;
    String fileName , userName;
    int blockNo , div ;
    
    public void setData ( int x[] ) { data=x; }
    public void setFileName ( String name ) { fileName = name; }
    public void setUserName ( String name ) { userName = name;}
    public String getFileName() { return fileName;  }
    public int[] getData() {  return data; }
    public String getUserName() { return userName; }
    public int getBlockNo() { return blockNo; }
    public void setBlockNo (int n) { blockNo = n ; }
    public int getDivNo() { return div; }
    public void setDivNo (int n) { div = n ; }
 }
