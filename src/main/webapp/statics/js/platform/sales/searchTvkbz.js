$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#datagrid').datagrid({
		iconCls : 'icon-list',
		title : '���۷�Χ�б�',
		url : appUrl + '/sales/salesAction!getTvkbzJsonList.jspa',
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
			width : setColumnWidth(0.15),
			align : 'center'
		}, {
			field : 'vkorg',
			title : '������֯',
			width : setColumnWidth(0.2),
			align : 'center'
		}, {
			field : 'vtweg',
			title : '��������',
			width : setColumnWidth(0.2),
			align : 'center'
		}, {
			field : 'spart',
			title : '��Ʒ��',
			width : setColumnWidth(0.2),
			align : 'center'
		}, {
			field : 'vkbur',
			title : '���۲���',
			width : setColumnWidth(0.2),
			align : 'center'
		} ] ],
		toolbar : [ "-", {
			text : 'ͬ��',
			iconCls : 'icon-reload',
			handler : function() {
				synchTvkbz();
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
	queryParams.vkorg = encodeURIComponent($("#vkorg").val());
	queryParams.vtweg = encodeURIComponent($("#vtweg").val());
	queryParams.spart = encodeURIComponent($("#spart").val());
	//queryParams.vkbur = encodeURIComponent($("#vkbur").val());
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
function synchTvkbz() {
	$.messager.confirm('Confirm', '�Ƿ�ȷ��ͬ��?', function(r) {
		if (r) {
			$.messager.progress();
			var form = window.document.forms[0];
			form.action = appUrl + "/sales/salesAction!synchTvkbz.jspa";
			form.target = "hideFrame";
			form.submit();
		}
	});
}