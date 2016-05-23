using System;
using System.Data;
using System.Configuration;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Web.UI.WebControls.WebParts;
using System.Web.UI.HtmlControls;

public partial class Register_aspx : System.Web.UI.Page {

    protected void CreateUserWizard1_CreatedUser(object sender, EventArgs e)
    {
        Database db = new Database();
        db.Open();
        string sql = "Insert Into users Values ( '" + CreateUserWizard1.UserName  + "' , '" + CreateUserWizard1.Password  + "' , '" + CreateUserWizard1.Email  + "', '" + CreateUserWizard1.Question  + "' , '"+ CreateUserWizard1.Answer +"')";
        db.Execute(sql);
        db.Close();
        System.IO.Directory.CreateDirectory("d://data//" + CreateUserWizard1.UserName );
        System.IO.Directory.CreateDirectory("d://data1//" + CreateUserWizard1.UserName);
        

    }
}