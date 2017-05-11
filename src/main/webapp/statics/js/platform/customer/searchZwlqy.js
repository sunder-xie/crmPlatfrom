$(document)
		.ready(
				function() {

					// ��������
					function getRegion(level) {
						$('#zwl0' + level)
								.combobox(
										{
											editable : false,
											url : appUrl
													+ '/sales/salesAction!searchRegion01.jspa?level='
													+ level + '&pid='
													+ $('#zwl0').val(),
											textField : 'text',
											valueField : 'id',
											onLoadSuccess : function() {
												$('#zwl0' + level).combobox(
														"setText", '��ѡ��');
													++level;
											},
											onSelect : function(re) {
												$('#zwl0').val(re.id);
												getRegion(level);
												$('#level').val(level);
											}

										});

					}
					;
					getRegion(0);
					loadGrid();
					$('#hideFrame').bind('load', promgtMsg);
				});

function loadGrid() {
	$('#datagrid').datagrid({
		iconCls : 'icon-list',
		title : '���������б�',
		url : appUrl + '/sales/salesAction!getZwlqyJsonList.jspa',
		loadMsg : '����Զ��������,��ȴ�...',
		singleSelect : true,
		pagination : true,
		nowrap : true,
		rownumbers : true,
		striped : true,
		height : height,
		columns : [ [ {
			field : 'zwl00',
			title : '���Ҵ���',
			width : setColumnWidth(0.08),
			align : 'center',
			sortable : true
		}, {
			field : 'zwl01',
			title : 'ʡ����',
			width : setColumnWidth(0.08),
			align : 'center',
			sortable : true
		}, {
			field : 'zwl02',
			title : '�д���',
			width : setColumnWidth(0.08),
			align : 'center',
			sortable : true
		}, {
			field : 'zwl03',
			title : '������',
			width : setColumnWidth(0.08),
			align : 'center',
			sortable : true
		},{
			field : 'zwl04',
			title : '��/�����',
			width : setColumnWidth(0.08),
			align : 'center',
			sortable : true
		}, {
			field : 'zwl00t',
			title : '����',
			width : setColumnWidth(0.1),
			align : 'center',
			sortable : true
		},{
			field : 'zwl01t',
			title : 'ʡ',
			width : setColumnWidth(0.1),
			align : 'center',
			sortable : true
		}, {
			field : 'zwl02t',
			title : '��',
			width : setColumnWidth(0.11),
			align : 'center',
			sortable : true
		},  {
			field : 'zwl03t',
			title : '��',
			width : setColumnWidth(0.11),
			align : 'center',
			sortable : true
		},  {
			field : 'zwl04t',
			title : '��/��',
			width : setColumnWidth(0.12),
			align : 'center',
			sortable : true
		} ] ]
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
	queryParams.zwl0 = encodeURIComponent($("#zwl0").val());
	queryParams.level = $("#level").val();
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
