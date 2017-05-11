$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#datagrid').datagrid({
		iconCls : 'icon-list',
		title : '查询结果',
		loadMsg : '数据远程载入中,请等待...',
		singleSelect : false,
		nowrap : true,
		height : height,
		striped : true,
		columns : [ [ {
			field : 'custName',
			title : '经销商',
			width : setColumnWidth(0.15),
			align : 'center'
		}, {
			field : 'stock_stock_place',
			title : '库存地',
			width : setColumnWidth(0.1),
			align : 'center'
		}, {
			field : 'category_id',
			title : '物料编号',
			width : setColumnWidth(0.15),
			align : 'center'
		}, {
			field : 'categoryName',
			title : '物料',
			width : setColumnWidth(0.15),
			align : 'center'
		}, {
			field : 'batch',
			title : '批次',
			width : setColumnWidth(0.1),
			align : 'center'
		}, {
			field : 'stock_quantity',
			title : '库存数量',
			width : setColumnWidth(0.1),
			align : 'center',
			styler : function(value, record, index) {
				return "background:lightyellow";
			}
		}, {
			field : 'stock_pandian',
			title : '盘点数量',
			width : setColumnWidth(0.1),
			align : 'center',
			styler : function(value, record, index) {
				return "background:lightyellow";
			}
		}, {
			field : 'stock_differ',
			title : '差异',
			width : setColumnWidth(0.1),
			align : 'center',
			styler : function(value, record, index) {
				return "background:pink";
			}
		} ] ],
		toolbar : [ "-", {
			text : '下载盘点模板',
			iconCls : 'icon-excel',
			handler : function() {
				excel();
			}
		}, "-" ]

	});

}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		if (successResult == 'S') {
			$('#datagrid').datagrid({
				url : appUrl + '/stock/stockManageAction!scanData.jspa'
			});
			$('#cc').linkbutton('enable');
		} else {
			$.messager.alert('Tips', successResult, 'info', function() {
				window.location.reload();
			});
		}
	}
}

// excel模板导出
function excel() {
	var form = window.document.forms[0];
	form.action = appUrl + '/stock/stockManageAction!downLoadModule.jspa';
	form.submit();

}

// 导入预览
function importScan() {
	var form = window.document.forms[0];
	form.target = "hideFrame";
	form.action = appUrl + '/stock/stockManageAction!importScan.jspa';
	form.submit();
}

// 导入数据库
function importDb() {
	var form = window.document.forms[0];
	form.target = "hideFrame";
	form.action = appUrl + '/stock/stockManageAction!importDb.jspa';
	form.submit();

}
