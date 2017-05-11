$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#datagrid').datagrid({
		iconCls : 'icon-list',
		title : '销售省份与销售大区关系列表',
		url : appUrl + '/sales/salesAction!getZdqsfJsonList.jspa',
		loadMsg : '数据远程载入中,请等待...',
		singleSelect : true,
		pagination : true,
		nowrap : true,
		rownumbers : true,
		striped : true,
		height : height,
		columns : [ [ {
			field : 'mandt',
			title : '客户端',
			width : setColumnWidth(0.15),
			align : 'center'
		}, {
			field : 'vkgrp',
			title : '销售省份',
			width : setColumnWidth(0.2),
			align : 'center'
		},{
			field : 'bzirk',
			title : '销售大区',
			width : setColumnWidth(0.2),
			align : 'center'
		},{
			field : 'bztxt',
			title : '区名',
			width : setColumnWidth(0.2),
			align : 'center'
		},  {
			field : 'bezei',
			title : '描述',
			width : setColumnWidth(0.2),
			align : 'center'
		} ] ],
		toolbar : [ "-", {
			text : '同步',
			iconCls : 'icon-reload',
			handler : function() {
				synchZdqsf();
			}
		}, "-" ]
	});

	// 分页控件
	var p = $('#datagrid').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
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
	queryParams.vkgrp = encodeURIComponent($("#vkgrp").val());
	queryParams.bzirk = encodeURIComponent($("#bzirk").val());
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
function synchZdqsf() {
	$.messager.confirm('Confirm', '是否确认同步?', function(r) {
		if (r) {
			$.messager.progress();
			var form = window.document.forms[0];
			form.action = appUrl + "/sales/salesAction!synchZdqsf.jspa";
			form.target = "hideFrame";
			form.submit();
		}
	});
}