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
			field : 'ck',
			checkbox : true
		},{
			field : 'zwl00',
			title : '���Ҵ���',
			width : setColumnWidth(0.08),
			align : 'center',
			sortable : true,
			hidden:true
		}, {
			field : 'zwl01',
			title : 'ʡ����',
			width : setColumnWidth(0.08),
			align : 'center',
			sortable : true/*,
			hidden:true*/
		}, {
			field : 'zwl02',
			title : '�д���',
			width : setColumnWidth(0.08),
			align : 'center',
			sortable : true/*,
			hidden:true*/
		}, {
			field : 'zwl03',
			title : '������',
			width : setColumnWidth(0.08),
			align : 'center',
			sortable : true/*,
			hidden:true*/
		},{
			field : 'zwl04',
			title : '��/�����',
			width : setColumnWidth(0.08),
			align : 'center',
			sortable : true/*,
			hidden:true*/
		}, {
			field : 'zwl00t',
			title : '����',
			width : setColumnWidth(0.1),
			align : 'center',
			sortable : true,
			hidden:true
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
		} ] ],
		toolbar : [ "-", {
			text : '����',
			iconCls : 'icon-save',
			handler : function() {
				save();
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
function save() {
	var rows = $('#datagrid').datagrid('getSelections');
	if (rows == '') {
		$.messager.alert('Tips', '  û���б�ѡ��!');
		return;
	}
	var zwl01 = rows[0].zwl01; // ʡ����
	var zwl02 = rows[0].zwl02; // �д���
	var zwl03 = rows[0].zwl03; // ������
	var zwl04 = rows[0].zwl04; // �����
	
	var zwl01t = rows[0].zwl01t; // ʡ
	var zwl02t = rows[0].zwl02t; // ��
	var zwl03t = rows[0].zwl03t; // ��
	var zwl04t = rows[0].zwl04t; // ��
	var num = $('#num').val();
	if ('' == $('#num').val()||0==num) {
		window.parent.returnAddress(zwl01,zwl02,zwl03,zwl04,zwl01t, zwl02t, zwl03t, zwl04t);
	} else {
		window.parent.returnAddress(zwl01,zwl02,zwl03,zwl04,zwl01t, zwl02t, zwl03t, zwl04t, num);
	}
	window.parent.closeOrgTree();
}