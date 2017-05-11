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
			field : 'ck',
			checkbox : true
		},{
			field : 'zwl00',
			title : '国家代码',
			width : setColumnWidth(0.08),
			align : 'center',
			sortable : true,
			hidden:true
		}, {
			field : 'zwl01',
			title : '省代码',
			width : setColumnWidth(0.08),
			align : 'center',
			sortable : true/*,
			hidden:true*/
		}, {
			field : 'zwl02',
			title : '市代码',
			width : setColumnWidth(0.08),
			align : 'center',
			sortable : true/*,
			hidden:true*/
		}, {
			field : 'zwl03',
			title : '区代码',
			width : setColumnWidth(0.08),
			align : 'center',
			sortable : true/*,
			hidden:true*/
		},{
			field : 'zwl04',
			title : '镇/乡代码',
			width : setColumnWidth(0.08),
			align : 'center',
			sortable : true/*,
			hidden:true*/
		}, {
			field : 'zwl00t',
			title : '国家',
			width : setColumnWidth(0.1),
			align : 'center',
			sortable : true,
			hidden:true
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
		} ] ],
		toolbar : [ "-", {
			text : '保存',
			iconCls : 'icon-save',
			handler : function() {
				save();
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
		$.messager.alert('Tips', '  没有行被选中!');
		return;
	}
	var zwl01 = rows[0].zwl01; // 省代码
	var zwl02 = rows[0].zwl02; // 市代码
	var zwl03 = rows[0].zwl03; // 区代码
	var zwl04 = rows[0].zwl04; // 镇代码
	
	var zwl01t = rows[0].zwl01t; // 省
	var zwl02t = rows[0].zwl02t; // 市
	var zwl03t = rows[0].zwl03t; // 区
	var zwl04t = rows[0].zwl04t; // 镇
	var num = $('#num').val();
	if ('' == $('#num').val()||0==num) {
		window.parent.returnAddress(zwl01,zwl02,zwl03,zwl04,zwl01t, zwl02t, zwl03t, zwl04t);
	} else {
		window.parent.returnAddress(zwl01,zwl02,zwl03,zwl04,zwl01t, zwl02t, zwl03t, zwl04t, num);
	}
	window.parent.closeOrgTree();
}