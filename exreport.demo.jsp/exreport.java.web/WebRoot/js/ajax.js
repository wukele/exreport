
function createxmlhttp() {
	var xmlhttp;
	if (window.ActiveXObject) {
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	} else {
		if (window.XMLHttpRequest) {
			xmlhttp = new XMLHttpRequest();
		}
	};
	return xmlhttp;
}

function invoke(param)
{
	xmlhttp = null;
	xmlhttp = createxmlhttp();
    var url = server;
     xmlhttp.open("POST", url, true);
     xmlhttp.onreadystatechange = handleStateChange;
     xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded;");
     xmlhttp.send(param);
   }


   function handleStateChange() {
     if (xmlhttp.readyState == 4) {
       if (xmlhttp.status == 200) {
         
			proccess(xmlhttp.responseText);

       }
     }
   }