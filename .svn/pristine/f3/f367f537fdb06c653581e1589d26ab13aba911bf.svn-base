$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '物料列表',
						url : appUrl
								+ '/master!getMaterielJsonList.jspa',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						rownumbers : true,
						height : height,
						width : 'auto',
						columns : [ [
								{
									field : 'matnr',
									title : '物料编号',
									align : 'center',
									width : setColumnWidth(0.15),
									sortable : true
								},{
									field : 'maktx',
									title : '物料短文本',
									width : setColumnWidth(0.2),
									align : 'center'
								},{
									field : 'meins',
									title : '物料单位',
									width : setColumnWidth(0.06),
									align : 'center'
								},
								{
									field : 'matkl',
									title : '物料组',
									width : setColumnWidth(0.06),
									align : 'center'
								},
								{
									field : 'wgbez',
									title : '物料组描述',
									width : setColumnWidth(0.2),
									align : 'center'
								},
								{
									field : 'mvgr1',
									title : '预算口径代码',
									width : setColumnWidth(0.08),
									align : 'center'
								},{
									field : 'bezei',
									title : '预算口径',
									width : setColumnWidth(0.2),
									align : 'center'
								}] ],
						toolbar : [ "-", {
							text : '同步',
							iconCls : 'icon-reload',
							handler : function() {
								synchMateriel();
							}
						} ]
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
	queryParams.matnr = $("#matnr").val();
	queryParams.maktx = encodeURIComponent($("#maktx").val());
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
		$.messager.alert('Tips', successResult, 'info');
		search();
	}
}
function synchMateriel() {
	$.messager.progress(); 
	var form = window.document.forms[0];
	form.action = appUrl
			+ "/master!synchMateriel.jspa";
	form.target = "hideFrame";
	form.submit();
}