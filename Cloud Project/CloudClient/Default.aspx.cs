using System;
using System.Data;
using System.Configuration;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Web.UI.WebControls.WebParts;
using System.Web.UI.HtmlControls;

public partial class Default_aspx  : System.Web.UI.Page {


    protected void Login1_Authenticate(object sender, AuthenticateEventArgs e)
    {
        Database db = new Database();
        db.Open();
        string sql = "Select * From users Where UserName = '" + Login1.UserName  + "' And Password ='" + Login1.Password  + "'";
        System.Data.SqlClient.SqlDataReader dr = db.ExecuteReader(sql);
        if (dr.Read())
        {
            Session.Add("username", Login1.UserName );
            dr.Close();
            db.Close();
            Response.Redirect("~//LoggedIn.aspx");
        }
        else
        {
            dr.Close();
            db.Close();
            Login1.FailureText = " Wrong UserName/Password ";

        }

    }
}