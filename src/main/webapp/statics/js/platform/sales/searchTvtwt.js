$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#datagrid').datagrid({
		iconCls : 'icon-list',
		title : '���������б�',
		url : appUrl + '/sales/salesAction!getTvtwtJsonList.jspa',
		loadMsg : '����Զ��������,��ȴ�...',
		singleSelect : true,
		pagination : true,
		nowrap : true,
		rownumbers : true,
		striped : true,
//		collapsible:true,
		height : height,
		columns : [ [ {
			field : 'mandt',
			title : '�ͻ���',
			width : setColumnWidth(0.3),
			align : 'center'
		}, {
			field : 'spras',
			title : '���Դ���',
			width : setColumnWidth(0.2),
			align : 'center',
			hidden:true
		}, {
			field : 'vtweg',
			title : '��������',
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
				synchTvtwt();
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
	queryParams.vtweg = encodeURIComponent($("#vtweg").val());
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
function synchTvtwt() {
	$.messager.confirm('Confirm', '�Ƿ�ȷ��ͬ��?', function(r) {
		if (r) {
			$.messager.progress();
			var form = window.document.forms[0];
			form.action = appUrl + "/sales/salesAction!synchTvtwt.jspa";
			form.target = "hideFrame";
			form.submit();
		}
	});
}