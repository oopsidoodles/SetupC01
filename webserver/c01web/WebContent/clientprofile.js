// elements
var unique_identifier;
var unique_identifier_value;
var date_of_birth;
var phone_number;
var has_email_address;
var email_address;
var street_address;

//connection info
var server = "c01web/"
var page = "ClientProfile";

function SubmitData()
{
	var request = new XMLHttpRequest();
	request.open("POST", "http://localhost:8080/" + server + page, true);
	request.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

	request.onreadystatechange = function()
	{
		if(request.readyState == 4)
		{
			alert(request.status + "\n" + request.responseText);
		}
	}

	//Add element to request
	var data = "";
	data += AddParam("unique_identifier", unique_identifier.value);
	data += AddParam("unique_identifier_value", unique_identifier_value.value);
	data += AddParam("date_of_birth", date_of_birth.value);
	data += AddParam("phone_number", phone_number.value);
	if (has_email_address[0].checked)
	{
		data += AddParam("has_email_address", "1");
	}
	else
	{
		data += AddParam("has_email_address", "0");
	}
	data += AddParam("email_address", email_address.value);
	data += AddParam("street_address", street_address.value);
	
	data = data.substring(0, data.length - 1);

	request.send(data);
}

function AddParam(key, value)
{
	return key + "=" + value + "&";
}

function FindFields()
{
	// Find them here on document load
	unique_identifier = document.getElementById("unique_identifier");
	unique_identifier_value = document.getElementById("unique_identifier_value");
	date_of_birth = document.getElementById("date_of_birth");
	phone_number = document.getElementById("phone_number");
	has_email_address = document.getElementsByName("has_email_address");
	has_email_address[1].checked = true;
	email_address = document.getElementById("email_address");
	street_address = document.getElementById("street_address");
}