$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);

});

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	$.messager.progress('close');
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info', close);
		window.parent.reloadDatagrid();
	}
}
function downloadExcel(){
	var form = window.document.forms[0];
	form.action = appUrl + '/goal/goalAction!exportToCSV.jspa';
	form.target = "hideFrame";
	form.submit();
}

function submit() {
	var epath = $('#fileContent').val();
	if(epath==""){
		$.messager.alert('Tips', '导入文件不能为空!', 'warning');
		return;
	}
	
	if(epath.substring(epath.lastIndexOf(".") + 1).toLowerCase() != 'csv'){
		$.messager.alert('Tips', '导入文件必须为csv格式!', 'warning');
		return;
	}
	$.messager.progress();
	var form = window.document.forms[0];
	form.action = appUrl + "/goalAction!saveImportBoList.jspa";
	form.target = "hideFrame";
	form.submit();
}

function close() {
	window.parent.closeMain();
}

document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		submit();
		return false;
	}
	return true;
};
