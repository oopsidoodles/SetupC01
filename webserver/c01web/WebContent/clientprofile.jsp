<html>
	<head>
		<script type="text/javascript" src="clientprofile.js"></script>
		<!--other script and also external css included over here-->
	</head>
	<body onload="FindFields()">
		<span>Unique Identifier: </span><textarea id="unique_identifier" rows="1" cols="35"></textarea><br />
		<span>Unique Identifier Value: </span><textarea id="unique_identifier_value" rows="1" cols="35"></textarea><br />
		<span>Date of Birth: </span><textarea id="date_of_birth" rows="1" cols="35"></textarea><br />
		<span>Phone Number: </span><textarea id="phone_number" rows="1" cols="35"></textarea><br />
		<span>Has Email Address: </span> <br />
			<div id="has_email_address">
			<input type="radio" name="has_email_address" value="True"> Yes<br>
			<input type="radio" name="has_email_address" value="False"> No<br>
			</div>
		<span>Email Address: </span><textarea id="email_address" rows="1" cols="35"></textarea><br />
		<span>Street Number: </span><textarea id="street_number" rows="1" cols="35"></textarea><br />
		
		
		
		<input type="submit" onclick="SubmitData()">
	</body>
</html>
