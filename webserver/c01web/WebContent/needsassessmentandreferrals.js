// elements
var unique_identifier;

//connection info
var server = "c01web/"
var page = "NeedsAssessmentAndReferrals";

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
}