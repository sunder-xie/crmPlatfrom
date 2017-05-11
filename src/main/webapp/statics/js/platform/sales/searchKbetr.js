$(document).ready(function() {
			loadGrid();
			$('#hideFrame').bind('load', promgtMsg);
		});

function loadGrid() {
	$('#datagrid').datagrid({
		iconCls : 'icon-list',
		title : '公司代码列表',
		url : appUrl + '/sales/salesAction!getKbetrJsonList.jspa',
		loadMsg : '数据远程载入中,请等待...',
		singleSelect : true,
		pagination : true,
		nowrap : true,
		rownumbers : true,
		striped : true,
		height : height,
		columns : [[{
					field : 'vkorg',
					title : '销售组织',
					width : setColumnWidth(0.15),
					align : 'center'
				}, {
					field : 'kondm',
					title : '物料定价组',
					width : setColumnWidth(0.2),
					align : 'center'
				}, {
					field : 'kbetr',
					title : '物料组价格',
					width : setColumnWidth(0.2),
					align : 'center'
				}, {
					field : 'datab',
					title : '有效起始日期',
					width : setColumnWidth(0.2),
					align : 'center',
					formatter : function(v) {
						return utcToDate(v.replace(/\/Date\((\d+)\+\d+\)\//gi,
								"new Date($1)"));
					}
				}, {
					field : 'datbi',
					title : '有效截止日期',
					width : setColumnWidth(0.2),
					align : 'center',
					formatter : function(v) {
						return utcToDate(v.replace(/\/Date\((\d+)\+\d+\)\//gi,
								"new Date($1)"));
					}
				}]],
		toolbar : ["-", {
					text : '同步',
					iconCls : 'icon-reload',
					handler : function() {
						synchKbetr();
					}
				}, "-"]
	});

	// 分页控件
	var p = $('#datagrid').datagrid('getPager');
	$(p).pagination({
				pageSize : 10,
				pageList : [10, 20, 30],
				beforePageText : '第',
				afterPageText : '页    共 {pages} 页',
				displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
			});
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}
function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.kondm = encodeURIComponent($("#kondm").val());
	$("#datagrid").datagrid('load');
}
function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	$.messager.progress('close');
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info', function() {
					search();
				});
	}
}
function synchKbetr() {
	$.messager.confirm('Confirm', '是否确认同步?', function(r) {
				if (r) {
					$.messager.progress();
					var form = window.document.forms[0];
					form.action = appUrl + "/sales/salesAction!synchKbetr.jspa";
					form.target = "hideFrame";
					form.submit();
				}
			});
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
	date = str[5] + "-" + month[str[1]] + "-" + str[2] + " " + str[3];
	return date;
}