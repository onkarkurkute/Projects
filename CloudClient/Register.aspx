<%@ Page Language="C#" MasterPageFile="~/Default.master" Title="Register"
    CodeFile="Register.aspx.cs" Inherits="Register_aspx" %>

<asp:Content ID="Content1" ContentPlaceHolderID="Main" Runat="server">

    <div class="shim column"></div>

    <div class="page" id="register">
		<div id="content">
            <h3>Request an Account</h3>
            <p>Accounts will be activated pending the approval of the Administrator.</p>
            <asp:CreateUserWizard ID="CreateUserWizard1" Runat="server" 
				ContinueDestinationPageUrl="default.aspx"
                DisableCreatedUser="True"
                EmailRegularExpression="\S+@\S+\.\S+"
                EmailRegularExpressionErrorMessage="The email format is invalid." OnCreatedUser="CreateUserWizard1_CreatedUser">
            </asp:CreateUserWizard>
        </div>
    </div>

</asp:Content>