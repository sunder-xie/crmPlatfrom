$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#datagrid').datagrid({
		iconCls : 'icon-list',
		title : '物料回扣组列表',
		url : appUrl + '/sales/salesAction!getTvbotJsonList.jspa',
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
			width : setColumnWidth(0.2),
			align : 'center',
			sortable : true
		}, {
			field : 'spras',
			title : '语言代码',
			width : setColumnWidth(0.2),
			align : 'center',
			hidden:true,
			sortable : true
		}, {
			field : 'bonus',
			title : '物料回扣组',
			width : setColumnWidth(0.25),
			align : 'center',
			sortable : true
		}, {
			field : 'vtext',
			title : '描述',
			width : setColumnWidth(0.5),
			align : 'center'
		} ] ],
		toolbar : [ "-", {
			text : '同步',
			iconCls : 'icon-reload',
			handler : function() {
				synchTvbot();
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
	queryParams.bonus = encodeURIComponent($("#bonus").val());
	queryParams.vtext = encodeURIComponent($("#vtext").val());
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
function synchTvbot() {
	$.messager.confirm('Confirm', '是否确认同步?', function(r) {
		if (r) {
			$.messager.progress();
			var form = window.document.forms[0];
			form.action = appUrl + "/sales/salesAction!synchTvbot.jspa";
			form.target = "hideFrame";
			form.submit();
		}
	});
}