<%@ Page Language="C#" MasterPageFile="~/Default1.master" AutoEventWireup="true" CodeFile="FileDownload.aspx.cs" Inherits="FileDownload" Title="File Download Page" %>
<asp:Content ID="Content1" ContentPlaceHolderID="Main" Runat="Server">
    <br />
    <br />
    <br />
    &nbsp;
    <asp:Label ID="Label1" runat="server" Font-Size="Medium" ForeColor="Red" Style="z-index: 100;
        left: 929px; position: absolute; top: 290px" Text="Enter ID Of File To Download" Width="242px"></asp:Label>
    &nbsp;
    <asp:Button ID="Button2" runat="server" Height="49px" Style="z-index: 101; left: 240px;
        position: absolute; top: 194px" Text="Show Uploaded Files" Width="321px" OnClick="Button2_Click" />
    &nbsp;&nbsp;&nbsp;<br />
    <br />
    <br />
    <br />
    <asp:Button ID="Button1" runat="server" OnClick="Button1_Click" Style="z-index: 105;
        left: 931px; position: absolute; top: 378px" Text="Submit" />
    <asp:TextBox ID="TextBox1" runat="server" Style="z-index: 103; left: 928px; position: absolute;
        top: 339px" ontextchanged="TextBox1_TextChanged"></asp:TextBox>
    <br />
    <br />
    <br />
    <br />
    <br />
    <asp:GridView ID="GridView1" runat="server" Height="243px" Style="z-index: 104; left: 351px;
        position: absolute; top: 280px" Width="470px">
    </asp:GridView>
    <br />
    <br />
    <br />
    &nbsp;<br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
</asp:Content>

