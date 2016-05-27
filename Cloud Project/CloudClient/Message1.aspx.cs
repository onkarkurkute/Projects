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

public partial class Message : System.Web.UI.Page
{
    string s;
    protected void Page_Load(object sender, EventArgs e)
    {
        s = (string)Session["message"];
        Label1.Text = (string)Session["message"];
        //Response.Redirect("~//Message.aspx");
    }
    protected void LinkButton1_Click(object sender, EventArgs e)
    {
        

    }
}
