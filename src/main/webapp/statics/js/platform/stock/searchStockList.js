$(document).ready(function() {
	loadGrid();
});

function loadGrid() {
	$('#datagrid').datagrid({
		iconCls : 'icon-list',
		title : '查询结果',
		height : height,
		striped : true,
		url : appUrl + '/stock/stockManageAction!seachStockJsonList.jspa',
		loadMsg : '数据远程载入中,请等待...',
		singleSelect : false,
		nowrap : true,
		pagination : true,
		rownumbers : true,
		columns : [ [ {
			field : 'custName',
			title : '经销商',
			align : 'center',
			width : setColumnWidth(0.15)
		}, {
			field : 'stock_stock_place',
			title : '库存地',
			align : 'center',
			width : setColumnWidth(0.1)
		}, {
			field : 'category_id',
			title : '物料编号',
			width : setColumnWidth(0.2),
			align : 'center'
		}, {
			field : 'categoryName',
			title : '物料',
			width : setColumnWidth(0.2),
			align : 'center'
		}, {
			field : 'batch',
			title : '批次',
			width : setColumnWidth(0.15),
			align : 'center'
		}, {
			field : 'stock_quantity',
			title : '数量',
			width : setColumnWidth(0.15),
			align : 'center'
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

	// 经销商查询
	$('#custKunnr').combogrid({
		panelHeight : 250,
		panelWidth : 380,
		pagination : true,
		method : 'post',
		singleSelect : true,
		url : appUrl + '/kunnrAction!kunnrSearch.jspa',
		idField : 'kunnr',
		textField : 'name1',
		columns : [ [ {
			field : 'kunnr',
			title : '经销商编号',
			width : 120
		}, {
			field : 'name1',
			title : '名称',
			width : 200
		} ] ],
		toolbar : '#toolbarKonzs'
	});
	// 品项
	$('#categories').combogrid({
		panelWidth : 400,
		panelHight : 280,
		idField : 'matnr',
		textField : 'maktx',
		pagination : true,// 是否分页
		collapsible : false,// 是否可折叠的
		method : 'post',
		singleSelect : false,
		url : appUrl + '/goal/goalAction!getMatList.jspa',
		columns : [ [ {
			field : 'matnr',
			title : '物料号',
			width : 120
		}, {
			field : 'maktx',
			title : '物料描述',
			align : 'center',
			width : 120
		}, {
			field : 'bezei',
			title : '物料组描述',
			align : 'center',
			width : 100
		} ] ],
		toolbar : '#toolbarCategories'

	});
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}
// 查询
function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.custKunnr = encodeURIComponent($("#custKunnr").combobox(
			"getValue"));
	queryParams.categories = encodeURIComponent($("#categories").combobox(
			"getValue"));
	$("#datagrid").datagrid('reload');
}

// 经销商
function searcherKonzs(name1) {
	var queryParams = $('#custKunnr').combogrid("grid").datagrid('options').queryParams;
	queryParams.name1 = encodeURIComponent(name1);
	$('#custKunnr').combogrid("grid").datagrid('reload');
}

// 品项
function searcherCategories(value) {
	var val = encodeURIComponent(value);
	$('#categories').combogrid({
		url : appUrl + '/goal/goalAction!getMatJsonList.jspa?value=' + val
	});
	$('#categories').combogrid("grid").datagrid('reload');
}

document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		search();
		return false;
	}
	return true;
};
