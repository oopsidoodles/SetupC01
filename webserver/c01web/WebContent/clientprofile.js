var first;
var last;

function SubmitData()
{
	first = document.getElementById("firstname").value;
	last = document.getElementById("lastname").value;
	
	var request = new XMLHttpRequest();
	//request.open("POST", "http://localhost:42069/server/test", true);
	request.open("POST", "http://localhost:8080/c01web/ClientProfile", true);
	request.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
	request.onreadystatechange = function()
	{
		if(request.readyState == 4)
		{
			if (request.status == 200)
			{
				alert(request.status);
			}
			else
			{
				alert(request.status + "\n" + request.responseText);
			}
		}
	}
	request.onload = function () {
		console.log('DONE', request.status);
	};
	request.send("firstname=" + first + "&" + "lastname=" + last);
}

function SentData()
{
	var out = "";
	out += "First Name: ";
	out += first;
	out += "\n";
	out += "Last Name: ";
	out += last;
	out += "\n";
	//out += "Added to DB"
	
	alert(out);
}
