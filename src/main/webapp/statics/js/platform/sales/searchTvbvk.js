$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#datagrid').datagrid({
		iconCls : 'icon-list',
		title : '����ʡ�������۲��Ź�ϵ�б�',
		url : appUrl + '/sales/salesAction!getTvbvkJsonList.jspa',
		loadMsg : '����Զ��������,��ȴ�...',
		singleSelect : true,
		pagination : true,
		nowrap : true,
		rownumbers : true,
		striped : true,
		height : height,
		columns : [ [ {
			field : 'mandt',
			title : '�ͻ���',
			width : setColumnWidth(0.3),
			align : 'center'
		}, {
			field : 'vkgrp',
			title : '����ʡ��',
			width : setColumnWidth(0.3),
			align : 'center'
		},{
			field : 'vkbur',
			title : '���۲���',
			width : setColumnWidth(0.35),
			align : 'center'
		} ] ],
		toolbar : [ "-", {
			text : 'ͬ��',
			iconCls : 'icon-reload',
			handler : function() {
				synchTvbvk();
			}
		}, "-" ]
	});

	// ��ҳ�ؼ�
	var p = $('#datagrid').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '��',
		afterPageText : 'ҳ    �� {pages} ҳ',
		displayMsg : '��ǰ��ʾ {from} - {to} ����¼   �� {total} ����¼'
	});
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}
function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.vkgrp = encodeURIComponent($("#vkgrp").val());
	queryParams.vkbur = encodeURIComponent($("#vkbur").val());
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
function synchTvbvk() {
	$.messager.confirm('Confirm', '�Ƿ�ȷ��ͬ��?', function(r) {
		if (r) {
			$.messager.progress();
			var form = window.document.forms[0];
			form.action = appUrl + "/sales/salesAction!synchTvbvk.jspa";
			form.target = "hideFrame";
			form.submit();
		}
	});
}