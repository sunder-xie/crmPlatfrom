$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});
var submitFlag=true;
function loadGrid() {
	var toolbar = $('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '提交结果',
						//url : appUrl + '/kunnrBusinessAction!getAddDealerAdjustmentJsonList.jspa',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						height : height,
						striped : true,
						columns : [ [
								{
									field : 'ck',
									checkbox : true
								},
								{
									field : 'id',
									title : 'ID',
									align : 'center',
									hidden : true
								},
								{
									field : 'qualityCheckingId',
									title : '质检报告编号',
									width : setColumnWidth(0.06),
									align : 'center'
								},
								{
									field : 'dateOfManufacture',
									title : '生产日期',
									width : setColumnWidth(0.06),
									align : 'center'
								},
								{
									field : 'productionTimeStart',
									title : '时间从',
									width : setColumnWidth(0.06),
									align : 'center'
								},
								{
									field : 'productionTimeEnd',
									title : '时间至',
									width : setColumnWidth(0.06),
									align : 'center'
								},
								{
									field : 'materielId',
									title : '物料',
									width : setColumnWidth(0.06),
									align : 'center'
								},
								{
									field : 'materielName',
									title : '物料描述',
									width : setColumnWidth(0.20),
									align : 'center'
								}
								 ] ],
						toolbar : [
								{
									text : '删除选择行',
									iconCls : 'icon-remove',
									handler : function() {
										deleteChageRow();
									}
								},
								"-"],
					});
	// 分页控件
	var p = $('#datagrid').datagrid('getPager');
	$(p).pagination({
		pageSize : 1,
		pageList : [1],
		beforePageText : '第',
		afterPageText : '页    共 1 页',
		displayMsg : '当前显示   共 {total} 条记录'
	});
}
/**
 * 保存（临时保存）
 */
function saveToCreate(){
	var dealerAdujstDetailList=[];
	var rows = $('#datagrid').datagrid("getRows")
	if (rows == null || rows == '') {
		$.messager.progress('close');
		$.messager.confirm('Confirm', '请导入明细！');
		return;
	}
	for(var i = 0; i < rows.length; i++){
		dealerAdujstDetailList.push({
			"qualityCheckingId":rows[i].qualityCheckingId,
			"dateOfManufacture":rows[i].dateOfManufacture,
			"productionTimeStart":rows[i].productionTimeStart,
			"productionTimeEnd":rows[i].productionTimeEnd,
			"materielId":rows[i].materielId
		});
	}
	
	var form = window.document.forms[0];
	form.action = appUrl + "/qualityChecking/qualityCheckingAction!saveQualityChecking.jspa";
	form.target = "hideFrame";
	form.submit();
}

/**
 * 提报调整模版excel导入
 */
function qualityCheckingImport() {
	html = '<form id="fileForm" method="post" enctype="multipart/form-data" >'
			+ '&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'
			+ '<table><tr>'
			+ '<td class="head" noWrap>选择文件上传：</td><td>'
			+ '<input type="file" name="uploadFile" id="uploadFile"  style="width:200px"/>'
			+ '</tb></tr>' + '<table></form>';
	openImportDialog('excel导入', html);
}
var mask_;
/**
 * 打开导入项目Excel对话框 
 */
function openImportDialog(t, html) {
	if ($('#excelDialog').length < 1) {
		$(
				'<div/>',
				{
					id : 'excelDialog',
					title : '批量导入',
					html : "<div id='import'>"
							+ "</br>"
							+ "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
							+ html + "</div>" + "</div>"
				}).appendTo('body');
	}
	$('#excelDialog')
			.dialog(
					{
						modal : true,
						resizable : false,
						dragable : false,
						closable : false,
						open : function() {
							$('#excelDialog').css('padding', '0.8em');
							$('#excelDialog .ui-accordion-content').css(
									'padding', '0.4em').height(
									$('#excelDialog').height() - 100);
						},
						buttons : [
								{
									text : '确定',
									handler : function() {
										var file = document.getElementById('uploadFile').value;
										if (/^.+\.(xls|XSL)$/.test(file)) {
											$.messager.progress();
											var action = '';
											action = appUrl
													+ "/qualityChecking/qualityCheckingAction!qualityCheckingImportCsv.jspa";
											var form = document.getElementById('fileForm');

											form.action = action;
											form.target = "hideFrame";
											form.submit();
										} else {
											$.messager.alert("提示", "请导入xls文件");
										}

									}
								},
								{
									text : '取消',
									handler : function() {
										window.location.href = window.location.href;
										$('#excelDialog').dialog('close');

									}
								} ],

						width : document.documentElement.clientWidth * 0.28,
						height : document.documentElement.clientHeight * 0.50
					});
}
/**
 * 删除选择行
 */
function deleteChageRow() {
	var rows = $('#datagrid').datagrid('getSelections');
	console.info(rows);
	if (rows == null || rows.length == 0) {
		$.messager.alert('Tips', '选中菜单栏无数据!');
		return;
	}
	var index = $('#datagrid').datagrid('getRowIndex', $("#datagrid").datagrid('getSelected'));
	$('#datagrid').datagrid('deleteRow',index);

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
/**
 * excelDialog数据
 */
function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	$.messager.progress('close');
	if (failResult) {
		submitFlag=true;
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.confirm('Tips', successResult, function(r) {
			if (r) {
				$('#excelDialog').dialog('close');
			}
			$('#datagrid').datagrid({
				url : appUrl + '/qualityChecking/qualityCheckingAction!getAddQualityCheckingJsonList.jspa?'
			});
			if ($('#nextUserDialog').length > 0) {
				$('#nextUserDialog').dialog('close');
			}
		});
	}
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
