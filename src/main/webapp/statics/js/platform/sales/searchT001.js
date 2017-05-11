$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#datagrid').datagrid({
		iconCls : 'icon-list',
		title : '��˾�����б�',
		url : appUrl + '/sales/salesAction!getT001JsonList.jspa',
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
			width : setColumnWidth(0.2),
			align : 'center'
		}, {
			field : 'bukrs',
			title : '��˾����',
			width : setColumnWidth(0.3),
			align : 'center'
		}, {
			field : 'butxt',
			title : '��˾���������',
			width : setColumnWidth(0.45),
			align : 'center',
			sortable : true
		} ] ],
		toolbar : [ "-", {
			text : 'ͬ��',
			iconCls : 'icon-reload',
			handler : function() {
				synchT001();
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
	queryParams.bukrs = encodeURIComponent($("#bukrs").val());
	queryParams.butxt = encodeURIComponent($("#butxt").val());
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
function synchT001() {
	$.messager.confirm('Confirm', '�Ƿ�ȷ��ͬ��?', function(r) {
		if (r) {
			$.messager.progress();
			var form = window.document.forms[0];
			form.action = appUrl + "/sales/salesAction!synchT001.jspa";
			form.target = "hideFrame";
			form.submit();
		}
	});
}