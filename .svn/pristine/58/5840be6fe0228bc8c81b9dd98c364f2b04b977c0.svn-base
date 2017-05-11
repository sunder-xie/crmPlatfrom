$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);
	loadCust();
});

/**
 * 关闭页面
 */
function cencel() {
	window.parent.closeMaintWindow();
}

function submit() {
	$.messager.confirm(
			'Confirm',
			'确认提交？',
			function(r) {
				if (r) {
					var form = window.document.forms[0];
					form.action = appUrl + "/stockReport/stockReportAction!updateSkuUnit.jspa";
					form.target = "hideFrame";
					form.submit();
				}
			});
}

function loadCust(){
	$('#skuUnit').combobox({    
	    url: appUrl
		     + '/stockReport/stockReportAction!searchSkuUnitSap.jspa?skuId=' + $("#skuId").val(),    
	    valueField:'MEINH',    
	    textField:'MEINH',
	    onSelect : function(data){
	    	$("#unitCoefficient").val(data.unitCoefficient);
	    }
	}); 
}

function selectOrgTree() {
	initMaintWindow('组织选择',
			'/orgTreeAjaxAction!toOrgtreeWithSearch.jspa', 400, 500,false);
}

function selectCostType() {
	initMaintWindow('费用类型选择',
			'/purchase/purchaseAction!toCostTypeTree.jspa', 400, 500,false);
}

function returnValue(selectedId, selectedName) {
	document.getElementById('orgId').value = selectedId;
	document.getElementById('orgName').value = selectedName;
}

function returnCostTypeValue(selectedId, selectedName){
	document.getElementById('costTypeId').value = selectedId;
	document.getElementById('costTypeName').value = selectedName;
	$.ajax({
		type : "post",
		url : appUrl + "/purchase/purchaseAction!getSubject_id.jspa",
		data : {
			costTypeId : selectedId
		},
		success : function(data) {
			$("#subjectId").val(data[0].subject_id);
			$("#subjectName").val(data[0].subject_name);
		}
	});
}

function closeMaintEvent() {
	$("#maintWindow").window('close');
	loadCust();
}

function closeMaint() {
	$("#maintWindow").window('close');
}

function searcherStation(name1) {
	var queryParams = $('#stationId').combogrid("grid").datagrid('options').queryParams;
	queryParams.stationName = encodeURIComponent(name1);
	$('#stationId').combogrid("grid").datagrid('reload');
}

function searcherSku(val) {
	var queryParams = $('#skuId').combogrid("grid").datagrid('options').queryParams;
	queryParams.skuName = encodeURIComponent(val);
	$('#skuId').combogrid("grid").datagrid('reload');
}

//function searcherCategory(val) {
//	var queryParams = $('#categoryId').combogrid("grid").datagrid('options').queryParams;
//	queryParams.categoryName = encodeURIComponent(val);
//	$('#categoryId').combogrid("grid").datagrid('reload');
//
//}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.confirm('Tips', successResult, function(r) {
			if (r) {
				cencel();
				window.parent.search();
			}
		});
	}
}

/*
 * document.onkeydown = function(e) { var theEvent = e || window.event; var code =
 * theEvent.keyCode || theEvent.which || theEvent.charCode; if (code == 13) {
 * search(); return false; } return true; };
 */

function initMaintWindow(title, url,WWidth,WHeight,maximized) {
	var url = appUrl + url;
	var $win = $("#maintWindow")
			.window(
					{   maximized:maximized,
						title : title,
						width : WWidth,
						height : WHeight,
						content : '<iframe frameborder="no" width="100%" height="100%" src='
								+ url + '/>',
						shadow : true,
						modal : true,
						closed : true,// /
						closable : true,//
						minimizable : false,
						maximizable : true,
						collapsible : true,
						draggable : true
					//
					});

	$win.window('open');
}

//关闭创建页面
function closeMaintWindow() {
	$("#maintWindow").window('close');
}

function utcToDate(utcCurrTime) {
	utcCurrTime = utcCurrTime + "";
	var date = "";
	var month = new Array();
	month["Jan"] = 1;
	month["Feb"] = 2;
	month["Mar"] = 3;
	month["Apr"] = 4;
	month["May"] = 5;
	month["Jun"] = 6;
	month["Jul"] = 7;
	month["Aug"] = 8;
	month["Sep"] = 9;
	month["Oct"] = 10;
	month["Nov"] = 11;
	month["Dec"] = 12;
	var week = new Array();
	week["Mon"] = "一";
	week["Tue"] = "二";
	week["Wed"] = "三";
	week["Thu"] = "四";
	week["Fri"] = "五";
	week["Sat"] = "六";
	week["Sun"] = "日";

	str = utcCurrTime.split(" ");
	date = str[5] + "-";
	date = date + month[str[1]] + "-" + str[2] + " " + str[3];
	return date;
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}

document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		return false;
	}
	return true;
};
