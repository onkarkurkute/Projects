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

public partial class FileDownload : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {

    }
    protected void LinkButton1_Click(object sender, EventArgs e)
    {

    }
    protected void Button2_Click(object sender, EventArgs e)
    {
        String username = (string)Session["username"];        
        string sql = "Select ID,UploadDate,FileName,SizeInBytes From Files Where UserName = '"+username+"'";
        string con = "Data Source=.\\SQLEXPRESS;AttachDbFilename=D:\\CloudClient\\App_Data\\Database.mdf;Integrated Security=True;User Instance=True";
        System.Data.SqlClient.SqlDataAdapter d = new System.Data.SqlClient.SqlDataAdapter(sql, con);
        System.Data.DataSet ds = new DataSet();
        d.Fill(ds);
        GridView1.DataSource = ds;
        GridView1.DataBind();
        
        
    }
    protected void Button1_Click(object sender, EventArgs e)
    {
        Database db = new Database();
        db.Open();
        String username = (String)Session["username"];
        String sql = "Select FileName,SizeInBytes From Files Where ID = " + int.Parse(TextBox1.Text);
        System.Data.SqlClient.SqlDataReader dr = db.ExecuteReader(sql);
        dr.Read();
        String filename = dr.GetString(0);
        int size = dr.GetInt32(1);
        db.Close();
        System.IO.StreamWriter sw = System.IO.File.CreateText("D://Download//" + TextBox1.Text + ".txt");
        sw.WriteLine(username);
        sw.WriteLine(filename );
        sw.WriteLine(size);
        sw.Close();
       
        Response.Redirect("~//Download.aspx");


    }
    
       
    protected void TextBox1_TextChanged(object sender, EventArgs e)
    {

    }
}
