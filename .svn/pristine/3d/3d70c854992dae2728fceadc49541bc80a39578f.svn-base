var processInstanceId;
var total;
$(document).ready(function() {
	$('#IV_MATNR_NAME')
	.combogrid(
			{
				panelWidth : 450,
				panelHight : 500,
				idField : 'IV_MATNR',
				textField : 'IV_MATNR_NAME',
				pagination : true,
				rownumbers : true,
				striped : true,
				fit : true,
				method : 'post',
				editable : false,
				multiple : false,
				required : false,
				
				url : appUrl
						+ '/cuanhuoQuery/cuanhuoQueryAction!getCuanhuoSKUs.jspa',
				queryParams : {
					IV_MATNR_NAME : $('#IV_MATNR_NAME').val()
				},
				columns : [ [
						{
							field : 'SKU_ID',
							title : '物料ID',
							width : 100,
							align : 'center'
						},
						{
							field : 'IV_MATNR_NAME',
							title : '物料名称',
							width : 140,
							align : 'center'
						},
						{
							field : 'IV_MATNR',
							title : '物料编号',
							width : 160,
							align : 'center'
						} ] ],
				onLoadSuccess : function() {
					$('#IV_MATNR_NAME').combogrid("setText", $("#IV_MATNR_NAME").val());
				},
				toolbar : '#toolbar_cuanhuo',
				required: true,
				onChange : function(newValue, oldValue){
					var selection = $(this).combogrid('grid').datagrid('getSelected');			
					$('#IV_MATNR').val(selection.IV_MATNR);
					$('#IV_MATNR_NAME').val(selection.IV_MATNR_NAME);
					$('#SKU_ID').val(selection.SKU_ID);
				}
			});	
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});
function loadGrid() {
	var toolbar = $('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '查询结果',
						url : appUrl
								+ '/cuanhuoQueryAction!searchCuanhuoQueryList.jspa',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : false,
						nowrap : true,
						height : height,
						striped : true,
						columns : [ [
								{
									field : 'MAKTX',
									title : '品项',
									width : setColumnWidth(0.1),
									align : 'center'
								},
								{
									field : 'KUNAG_NAME',
									title : '客户',
									width : setColumnWidth(0.2),
									align : 'center'
								},
								{
									field : 'KUNWE_NAME',
									title : '送达方',
									width : setColumnWidth(0.26),
									align : 'center'
								},
								{
									field : 'VBELN_VA',
									title : '销售订单',
									width : setColumnWidth(0.1),
									align : 'center'
								},
								{
									field : 'PODAT',
									title : '到货日期',
									width : setColumnWidth(0.1),
									align : 'center',
									formatter : function(v) {
										if(v==""){
											return "";
										}
										return utcToDate(v
												.replace(
														/\/Date\((\d+)\+\d+\)\//gi,
														"new Date($1)"));
									}
								}
								 ] ],
							onLoadSuccess : function(date) {
								$('#total').html("结果共    "+date.total+"   条");
							}
					});
	
//	 分页控件
//	var p = $('#datagrid').datagrid('getPager');
//	$(p).pagination({
//		displayMsg : '共 {total} 条记录'
//	});
}


function search() {
	if($('#IV_WERKS').val() == "" || $('#IV_MATNR').val() == ""
	|| $('#IV_LOCCO').val() == "" || document.getElementsByName('IV_DATUM')[0].value == ""){
		$.messager.alert('Tips', "请填写全查询条件!", 'warning');
	}else{
	    $("#datagrid").datagrid('reload', {
		    IV_WERKS : $('#IV_WERKS').val(),
		    IV_MATNR : $('#IV_MATNR').val(),
		    IV_LOCCO : encodeURIComponent($('#IV_LOCCO').val()),
		    IV_DATUM : document.getElementsByName('IV_DATUM')[0].value
		});
	}
}



function clear() {
	$('#IV_WERKS').val("1000");
	$('#IV_MATNR').val("");
	$('#IV_MATNR_NAME').val("");
	$("#IV_LOCCO").val("");
	$("#IV_DATUM").val("");
}

function searcher_cuanhuo(value) {
	val = encodeURIComponent(value);
	$('#IV_MATNR_NAME').combogrid({
		url : appUrl + '/cuanhuoQuery/cuanhuoQueryAction!getCuanhuoSKUs.jspa?IV_MATNR_NAME=' + val
	});
	$('#IV_MATNR_NAME').combogrid("grid").datagrid('reload');
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	$.messager.progress('close');
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.confirm('Tips', successResult, function(r) {
			if (r) {
				$('#excelDialog').dialog('close');
				search();
			}

		});
	}
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}
function getWidth(percent) {
	if (jQuery.browser.safari) {
		return document.body.clientWidth * percent - 2;
	} else {
		return document.body.clientWidth * percent;
	}
}
function getHeight(percent) {
	return document.body.clientHeight * percent;
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
	date = date + month[str[1]] + "-" + str[2];
	return date;
}
document.onkeydown = function(e) {// 这个事件在用户按下任何键盘键（包括系统按钮，如箭头键和功能键）时发生
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		search();
		return false;
	}
	return true;
};
