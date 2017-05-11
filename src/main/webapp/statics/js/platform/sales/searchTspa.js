$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#datagrid').datagrid({
		iconCls : 'icon-list',
		title : '��Ʒ���б�',
		url : appUrl + '/sales/salesAction!getTspaJsonList.jspa',
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
			field : 'spart',
			title : '��Ʒ��',
			width : setColumnWidth(0.3),
			align : 'center'
		}, {
			field : 'vtext',
			title : '����',
			width : setColumnWidth(0.35),
			align : 'center'
		} ] ],
		toolbar : [ "-", {
			text : 'ͬ��',
			iconCls : 'icon-reload',
			handler : function() {
				synchTspa();
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
	queryParams.spart = encodeURIComponent($("#spart").val());
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
function synchTspa() {
	$.messager.confirm('Confirm', '�Ƿ�ȷ��ͬ��?', function(r) {
		if (r) {
			$.messager.progress();
			var form = window.document.forms[0];
			form.action = appUrl + "/sales/salesAction!synchTspa.jspa";
			form.target = "hideFrame";
			form.submit();
		}
	});
}