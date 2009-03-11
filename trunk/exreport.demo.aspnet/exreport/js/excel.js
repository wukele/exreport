
/**
 * * this script defines most functions about the methods provided by excel. 
 * 
 * @author qinjinwei(kimmking.cn@google.com) 
 * @version 3.2 
 * 
 *	created on 2007-10-21 09:22:04 
 *	lastmodified on 2008-01-23 10:57:34 
 *	modified for 1.12: remove message and alert.
 *	modified for 1.13: hide the commandbars("web")
 *	modified for 2.0 : use the new VBA macro that uniting various cross report(unlimited classes). can be paging if the count of columns are more than 256
 *	modified for 2.1 : add the support about creating and reversing normal-report.
 *	modified for 2.2 : solve the bug that the close-method here is overriding the window.close-method.
 *	modified for 3.0 : this is a milestone.It makes output the data into excel-datasheet only a little milliseconds(200-300ms).
 *	modified for 3.1 : add the function to zoom in and zoom out in the dsoframer.
 *	modified for 3.2 : add the style to load the report page and add the new way to support the big data for the report..
 *          
 *	the version for develop and always download the template files. *
 */
/* const */
var exceldata = "excel_data_";
var exceldataarrays = "DTO_DATA_ARRAYS";
var ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
var metadataarrays = "META_DATA_ARRAYS";
var metafields = "REPORT_DTO_FIELDS_KEY";
var exceldatalength = "EXCEL_DATA_LENGTH";
var fso				= new ActiveXObject("Scripting.FileSystemObject");
var filehelper  	= new ActiveXObject("filehelper.filedown");
var dsofilename = "";
var preprocess = true;
var isrevers = false;
var zoom = 100;
var zipext = ".zip";

var zipfilepath = "zipfilepath";
var unzipfolder = "unzipfolder";

// common function
function createReport(tmfile1) {

	writeReport("正在下载模板...");
	openfile(tmfile1);
	drawReport();
}
function drawReport() {
	var edata = bean[exceldataarrays];
	var mdata = bean[metadataarrays];
	var fields = bean[metafields];
	var i;
	// var start = new Date().getTime();
	var cols = fields.split(",");
	var vbas = new Array(cols.length);
	var xlDoc = oframe.ActiveDocument;
	xlDoc.Application.Run("showbar");

	// end = start;
	// end = new Date().getTime() - end;
	// alert(end);//32ms
	// end = new Date().getTime();
	var xlSht3 = xlDoc.Sheets(3);
	// xlSht3.UsedRange.Clear();
	for (j = 0; j < mdata.length; j++) {
		xlSht3.Cells(1 + j, 1) = mdata[j];
		xlSht3.Cells(1 + j, 2) = bean[mdata[j]];
	}

	if (isdowndata) {

		writeReport("正在下载报表数据...");
		var zipfile = downloaddata();

		xlSht3.Cells(1 + mdata.length, 1) = zipfilepath;
		xlSht3.Cells(1 + mdata.length, 2) = zipfile;

		xlSht3.Cells(2 + mdata.length, 1) = unzipfolder;
		xlSht3.Cells(2 + mdata.length, 2) = zipfile.substring(0,zipfile.indexOf(".")) + "\\";

		writeReport("正在导入数据...");
		executeMacro("unZipAndImport");

	} else {

		writeReport("正在导入数据...");
		// end = new Date().getTime() - end;
		// alert(end );//15ms
		// end = new Date().getTime();
		var xlSht = xlDoc.Sheets(2);
		// xlSht.UsedRange.Clear();
		var oCellStr = new Array(cols.length);
		var dataRow = bean[exceldatalength];
		for (j = 0; j < cols.length; j++) {
			// oCellStr[j] = "";
			xlSht.Cells(1, 1 + j) = cols[j];
		}

		// end = new Date().getTime() - end;
		// alert(end);//16ms
		// end = new Date().getTime();
		if (preprocess) {

			// for (i = 0; i < dataRow; i++) {
			// var objData = edata[i];
			// for (j = 0; j < cols.length; j++) {
			// var value = objData[cols[j]];
			// oCellStr[j] = oCellStr[j] + "" + value;
			// if (i < dataRow - 1) {
			// oCellStr[j] = oCellStr[j] + "@j@";
			// }
			// }
			// }// for

			// end = new Date().getTime() - end;
			// alert(end);//9422ms
			// end = new Date().getTime();
			store(edata);

			// end = new Date().getTime() - end;
			// alert(end);//0ms
			// end = new Date().getTime();
		}
		for (j = 0; j < cols.length; j++) {

			// if(preprocess){
			var vba = CreateVBArray(j);
			vbas[j] = vba;
			// }
			// debugger;
			var startC = convert(2, 1 + j);
			var endC = convert(dataRow + 1, 1 + j);
			var range = xlSht.Range(startC + ":" + endC);
			range.Value = vbas[j];
		}

		// end = new Date().getTime() - end;
		// alert(end);//282ms
		// end = new Date().getTime();

		// if(preprocess){
		// unstore(oCellStr.length);
		// }
		preprocess = false;
	}
	showReport();
	window.setTimeout("draw()", 300); // ---------------------- all

	// end = new Date().getTime() - end;
	// alert(end);//0ms

	// msg("create report last " + (tend - start) + " ms");
	oframe.Activate();
	// xlDoc.ActiveSheet.Cells(1, 1).select;
}
function draw() {
	if (reporttype == 0) {
		oframe.ActiveDocument.Application.Run("createNormalReport");
	} else {
		// if( !isrevers )
		oframe.ActiveDocument.Application.Run("analyze");
	}
}
function openfile(filename) {
	dsofilename = filename;
	if(typeof(oframe)=="undefined")
	{
	    oframe = document.all.oframe;
	 }
	oframe.Open(filename);
	oframe.ActiveDocument.Application.CommandBars("Web").Visible = false;
}
function closefile() {
	oframe.close();
}
function save(filename) {
	oframe.Save(filename, true);
}
function msg(message) {
	if (warn != undefined) {
		warn.innerHTML = "<font color=\"red\">" + message + "</font>";
	}
}
function store(data) {
	var datacount = data.length;
	var i;
	for (i = 0; i < datacount; i++) {
		var text = document.createElement("input");
		text.type = "hidden";
		text.id = exceldata + i;
		text.value = data[i];
		document.body.appendChild(text);
	}
}
function unstore(datacount) {
	var i;
	for (i = 0; i < datacount; i++) {
		var text = document.getElementById(exceldata + i);
		document.body.removeChild(text);
	}
}
function convert(rowIndex, colIndex) {
	// assert(colIndex, 1, 255);
	var s1 = "";
	var t2 = colIndex % 26;
	if (colIndex > 26) {
		var t1 = Math.floor(colIndex / 26);
		s1 = ALPHA.charAt(t1 - 1);
	}
	if (t2 == 0) {
		t2 = 26;
	}
	var s2 = ALPHA.charAt(t2 - 1);
	return s1 + s2 + rowIndex;
}
function downloaddata() {
	var thistempfile = templatename + zipext;
	var tempdir = filehelper.GetTempPath();
	var d = new Date();
	var tempfile = tempdir + d.getFullYear() + d.getMonth() + d.getDate()
			+ d.getHours() + d.getMinutes() + d.getSeconds()
			+ d.getMilliseconds() + zipext;
	if (!isExistFile(tempfile)) {
	} else {
		fso.DeleteFile(tempfile);
	}
	// alert(tempfile);
	filehelper.DownloadFile(serverurl +  downservlet, tempfile);
	return tempfile;
}
function downloadtemplate() {
	var thistempfile = templatename + extensename;
	var tempdir = filehelper.GetTempPath();
	var tempfile = tempdir + templatename + "_" + fileid + extensename;
	if (!isExistFile(tempfile)) {
	} else {
		fso.DeleteFile(tempfile);
	}
	// alert(tempfile);
	filehelper.DownloadFile(serverurl + contextpath + "/" + thistempfile,
			tempfile);
	return tempfile;
}
function Assert(v, sup, sub) {
	if (v > sub || v < sup) {
		alert("The given value must be between " + sub + " and  " + sup + " .");
	}
}
function isExistFile(filename) {
	try {
		if (fso.FileExists(filename)) {
			return true;
		} else {
			return false;
		}
		fso = null;
	} catch (e) {
		alert(e.description);
	}
}
function active1() {
	oframe.Activate();
}
function inteval() {
	window.setInterval("active1()", 3000);
}

// common
function print() {
	oframe.PrintOut(true);
}
function preview() {
	oframe.PrintPreview();
}
function output() {
	oframe.ShowDialog(3);
}
function zoomin() {
	if (zoom < 500) {
		zoom = Math.round(zoom * 1.2);
		oframe.ActiveDocument.Application.ActiveWindow.Zoom = zoom;
	}
}
function zoomout() {
	if (zoom > 20) {
		zoom = Math.round(zoom / 1.2);
		oframe.ActiveDocument.Application.ActiveWindow.Zoom = zoom;
	}
}
function executeMacro(macroname) {
	oframe.ActiveDocument.Application.Run(macroname);
}
function revers() {
	isrevers = !isrevers;
	if (reporttype == 0) {
		executeMacro("NormalRevers");
	} else {
		closefile();
		openfile(dsofilename);
		if (isrevers) {
			executeMacro("revers");
		}
		drawReport();
	}
}

// test mode: if the excel file has been filled with the data.
function createReport1(tmfile1) {
	openfile(tmfile1);
	if (reporttype == 0) {
		oframe.ActiveDocument.Application.Run("createNormalReport");
	} else {
		oframe.ActiveDocument.Application.Run("analyze");
	}
}
function revers1() {
	if (reporttype == 0) {
		executeMacro("NormalRevers");
	} else {
		closefile();
		openfile(dsofilename);
		if (isrevers) {
			executeMacro("revers");
		}
		executeMacro("analyze");
	}
	isrevers = !isrevers;
}

function showReport() {

	if (parent != undefined && parent.document.all.logo != undefined)
		parent.document.all.logo.style.display = "none";
	// parent.document.all.content.style.display = "block";

}

function writeReport(msg) {

	if (parent != undefined && parent.document.all.tip != undefined)
		parent.document.all.tip.innerText = msg;

}