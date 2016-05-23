using System;
using System.Data;
using System.Configuration;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Web.UI.WebControls.WebParts;
using System.Web.UI.HtmlControls;
using System.Data.SqlClient;

/// <summary>
/// Summary description for Database
/// </summary>
public class Database
{
    SqlConnection con = new SqlConnection();
    SqlCommand com = new SqlCommand();

    public void Open()
	{
        con.ConnectionString ="Data Source=.\\SQLEXPRESS;AttachDbFilename=D:\\CloudClient\\App_Data\\Database.mdf;Integrated Security=True;User Instance=True";
        con.Open();
	}
    public void Execute(string sql)
    {
        com.Connection = con;
        com.CommandText = sql;
        com.ExecuteNonQuery();
    }

    public SqlDataReader ExecuteReader(String sql)
    {
        com.CommandText = sql;
        com.Connection = con;
        return com.ExecuteReader();
    }

    public void Close()
    {
        con.Close();
    }

}
