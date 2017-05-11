$(document)
		.ready(
				function() {

					// 行政区划
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
														"setText", '请选择');
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
		title : '行政区划列表',
		url : appUrl + '/sales/salesAction!getZwlqyJsonList.jspa',
		loadMsg : '数据远程载入中,请等待...',
		singleSelect : true,
		pagination : true,
		nowrap : true,
		rownumbers : true,
		striped : true,
		height : height,
		columns : [ [ {
			field : 'zwl00',
			title : '国家代码',
			width : setColumnWidth(0.08),
			align : 'center',
			sortable : true
		}, {
			field : 'zwl01',
			title : '省代码',
			width : setColumnWidth(0.08),
			align : 'center',
			sortable : true
		}, {
			field : 'zwl02',
			title : '市代码',
			width : setColumnWidth(0.08),
			align : 'center',
			sortable : true
		}, {
			field : 'zwl03',
			title : '区代码',
			width : setColumnWidth(0.08),
			align : 'center',
			sortable : true
		},{
			field : 'zwl04',
			title : '镇/乡代码',
			width : setColumnWidth(0.08),
			align : 'center',
			sortable : true
		}, {
			field : 'zwl00t',
			title : '国家',
			width : setColumnWidth(0.1),
			align : 'center',
			sortable : true
		},{
			field : 'zwl01t',
			title : '省',
			width : setColumnWidth(0.1),
			align : 'center',
			sortable : true
		}, {
			field : 'zwl02t',
			title : '市',
			width : setColumnWidth(0.11),
			align : 'center',
			sortable : true
		},  {
			field : 'zwl03t',
			title : '区',
			width : setColumnWidth(0.11),
			align : 'center',
			sortable : true
		},  {
			field : 'zwl04t',
			title : '镇/乡',
			width : setColumnWidth(0.12),
			align : 'center',
			sortable : true
		} ] ]
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
