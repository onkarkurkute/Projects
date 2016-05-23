<%@	Page Language="C#" MasterPageFile="~/Default.master" Title="LogIn"
	CodeFile="Default.aspx.cs" Inherits="Default_aspx" %>

<asp:content id="Content1" contentplaceholderid="Main" runat="server">

	<div class="shim column"></div>
	
	<div class="page" id="home">
		<div id="content">
			<h3>
                <asp:Label ID="Label1" runat="server" Style="z-index: 102; left: 91px; position: absolute;
                    top: 67px" Text="Login Here"></asp:Label>
                Welcome	to MyCloud.com</h3>
			<hr	/>
			<div id="whatsnew" style="width: 426px; height: 127%"><br>
				<h4>
                    &nbsp;</h4>
                <p>
                    &nbsp;</p>
                <p>
                    &nbsp;</p>
                <p>
                    <asp:Image ID="Image1" runat="server" Height="219px" ImageUrl="~/Images/Clouddy1.jpg"
                        Style="z-index: 100; left: 305px; position: absolute; top: 82px" Width="406px" />
                    &nbsp;</p>
                <p>
                    &nbsp;</p>
                <p>
                    &nbsp;</p>
                <p>
                    &nbsp;</p>
                <p>
                    &nbsp;</p>
			</div>
			<div id="coollinks">
				<h4>
                    &nbsp;</h4>
                <p>
                    &nbsp;</p>
                <p>
                    &nbsp;</p>
                <p>
                    <asp:Login ID="Login1" runat="server" Height="177px" OnAuthenticate="Login1_Authenticate"
                        Style="z-index: 101; left: 20px; position: absolute; top: 121px" Width="237px">
                    </asp:Login>
                    &nbsp;</p>
                <p>
                    &nbsp;</p>
                <p>
                    &nbsp;</p>
                <p>
                    &nbsp;</p>
			</div>
            <br />
            <br />
            MyCloud have their own database in terabytes which is free for&nbsp; you to store
            your data including any documents, audios, videos and many more.<br />
            <br />
            <br />
            MyCloud is an online cloud storage which aims to store data persistently over the
            internet with the functionality that users can anytime modify, update and delete
            their data anytime.
            <br />
            <br />
            <br />
			<hr	/>
		</div>
	</div>

</asp:content>
