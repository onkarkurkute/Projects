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

public partial class FileUpload : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {

    }
    protected void LinkButton1_Click(object sender, EventArgs e)
    {

    }
    protected void Button2_Click(object sender, EventArgs e)
    {
        String filename = FileUpload1.FileName;
        Database db = new Database();
        db.Open();
        String username = (String)Session["username"];
        string sql = "Select * From files Where UserName = '" + username + "' And FileName='" +filename+ "'";
        System.Data.SqlClient.SqlDataReader dr = db.ExecuteReader(sql);
        if (dr.Read())
        {
            Session.Add("message","You have alraedy stored file with this name plese change filename");
            dr.Close();
            db.Close();
            Response.Redirect("~//Message1.aspx");
        }
        dr.Close();
        sql = "Insert Into Files Values ( '" + Session["username"] + "' , '" + DateTime.Now  + "' , '" + filename + "', '" + FileUpload1.FileBytes.Length + "')";
        db.Execute(sql);
        db.Close();
        FileUpload1.SaveAs("d://data//" + Session["username"] + "//" + FileUpload1.FileName);
        Session.Add("message", "File Uploaded  Successfully!!");
        Response.Redirect("~//Message.aspx");
    }
}
