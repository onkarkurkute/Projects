using System;
using System.Data;
using System.Configuration;
using System.Collections;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Web.UI.WebControls.WebParts;
using System.Web.UI.HtmlControls;
using System.IO;
public partial class Download : System.Web.UI.Page
{


    protected void Button2_Click(object sender, EventArgs e)
    {
            String username = (String)Session["username"];
            System.IO.DirectoryInfo d = new System.IO.DirectoryInfo("d://data1//" + username);
            foreach (System.IO.FileInfo FileName in d.GetFiles())
            {

                FileStream myFile = new FileStream(FileName.FullName, FileMode.Open, FileAccess.Read, FileShare.ReadWrite);
                BinaryReader _BinaryReader = new BinaryReader(myFile);
                long startBytes = 0;
                string lastUpdateTiemStamp = File.GetLastWriteTimeUtc(FileName.FullName).ToString("r");
                string _EncodedData = HttpUtility.UrlEncode(FileName.Name, System.Text.Encoding.UTF8) + lastUpdateTiemStamp;
                try
                {
                Response.Clear();
                Response.Buffer = false;
                Response.AddHeader("Accept-Ranges", "bytes");
                Response.AppendHeader("ETag", "\"" + _EncodedData + "\"");
                Response.AppendHeader("Last-Modified", lastUpdateTiemStamp);
                Response.ContentType = "application/octet-stream";
                Response.AddHeader("Content-Disposition", "attachment;filename=" + FileName.Name);
                Response.AddHeader("Content-Length", (FileName.Length - startBytes).ToString());
                Response.AddHeader("Connection", "Keep-Alive");
                Response.ContentEncoding = System.Text.Encoding.UTF8;


                _BinaryReader.BaseStream.Seek(startBytes, SeekOrigin.Begin);
                int maxCount = (int)Math.Ceiling((FileName.Length - startBytes + 0.0) / 1024);


                int i;
                for (i = 0; i < maxCount && Response.IsClientConnected; i++)
                {
                    Response.BinaryWrite(_BinaryReader.ReadBytes(1024));
                    Response.Flush();
                }
                _BinaryReader.Close();
                Response.End();
                
            }
                finally
                {
                   
                    myFile.Close();
                    File.Delete(FileName.FullName);
                    Session.Add("message", "hi");
                    //Response.Redirect("~//Message.aspx");
                }
            }
        

    }
    protected void LinkButton1_Click(object sender, EventArgs e)
    {

    }
}
