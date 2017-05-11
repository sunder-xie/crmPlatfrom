$(document).ready(function() {
	loadGrid();
	loadKunnr();
});

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '查询结果',
						url : appUrl + '/kunnrBusinessAction!searchKunnrBusinessReport.jspa',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						height : height,
						striped : true,
						queryParams : {
							orgId : $("#orgId").val()
						},
						columns : [ [	
								{
									field : 'kunnr',
									title : 'SAP代码',
									width : 100,
									align : 'center',
									styler : function(value, record, index) {
										return "background:pink";
									}
								},
								{
									field : 'kunnrName',
									title : '经分销商名称',
									width : 150,
									align : 'center',
									sortable:true
								},
								{
									field : 'vkgrp',
									title : '大区',
									width : 80,
									align : 'center',
									sortable:true
								},
								{
									field : 'bzirk',
									title : '省份',
									width : 80,
									align : 'center',
									sortable:true
								},
								{
									field : 'vkbur',
									title : '城市',
									width : 80,
									align : 'center',
									sortable:true
								},
								{
									field : 'businessManager',
									title : '高级经理',
									width : 80,
									align : 'center',
									sortable:true
								},
								{
									field : 'businessHead',
									title : '客户经理',
									width : 200,
									align : 'center',
									sortable:true
								},
								{
									field : 'businessAgent',
									title : '业代',
									width : 200,
									align : 'center',
									sortable:true
								}] ],
								toolbar : [ "-",{
									text : '批量导出',
									iconCls : 'icon-download',
									handler : function() {
										exportForExcel(); 
									}
								}, "-"]
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

function search() {
	var queryParams =$('#datagrid').datagrid('options').queryParams;
	queryParams.kunnrId = $("#kunnrId").combobox("getValue");
	queryParams.orgId=$("#orgId").val();
	queryParams.businessEndDate=$("#businessEndDate").datebox("getValue");
	$("#datagrid").datagrid('load');
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}

function loadKunnr(){

	$('#kunnrId').combogrid({
		panelHeight : 250,
		panelWidth : 380,
		pagination : true,
		method : 'post',
		singleSelect : true,
		multiple : false,
		url : appUrl + '/kunnrAction!kunnrSearch.jspa?orgId='+$('#orgId').val()+'&bhxjFlag='+'C',
		idField : 'kunnr',
		textField : 'name1',
		// multiple:true,
		columns : [ 
					 [ {
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
}
/**
 * 所属经分销商下拉查询
 * 
 * @param name1
 */
function searcherKonzs(name1) {
	var queryParams = $('#kunnrId').combogrid("grid").datagrid('options').queryParams;
	queryParams.name1 = encodeURIComponent(name1);
	$('#kunnrId').combogrid("grid").datagrid('reload');
}

function exportForExcel() {
	$.messager.progress();
	openTime();
	var form = window.document.forms[0];
	form.action = appUrl
			+ '/kunnrBusinessAction!exportForExcelReport.jspa';
	form.target = "hideFrame";
	form.submit();
}
function openTime() {
	setTimeout(function() {
		var timer = setInterval(function() {
			$.ajax({
				type : "post",
				url : appUrl + "/stockReport/stockReportAction!checkDownLoadOver.jspa?",
				success : function(data) {
					if (data == 'Yes') {
						clearInterval(timer);
						$.messager.progress('close');
					}
				}
			});
		}, 100);
	}, 500);
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		search();
		$.messager.alert('Tips', successResult, 'info');

	}
}

// 创建窗口对象
function openWindow(title, url, WWidth, WHeight) {
	var url = appUrl + url;
	var $win = $("#hiddenWin")
			.window(
					{
						title : title,
						width : WWidth,
						height : WHeight,
						content : '<iframe frameborder="no" width="100%" height="100%" src='
								+ url + '/>',
						shadow : true,
						modal : true,
						closed : true,
						closable : true,
						minimizable : false,
						maximizable : true,
						collapsible : true,
						draggable : true
					});

	$win.window('open');

}

// 关闭窗口
function closeWindow() {
	$("#hiddenWin").window('close');
}

	/**
	 * 打开组织树
	 */
	function selectOrgTree() {
		openWindow('选择组织', '/customerAction!orgTreePage.jspa', 400, 460);
	}
	/**
	 * 组织树选组织返回到输入框
	 * 
	 * @param selectedId
	 * @param selectedName
	 */
	function returnValue(selectedId, selectedName) {
		document.getElementById('orgId').value = selectedId;
		document.getElementById('orgName').value = selectedName;
	}
	/**
	 * 组织树选择完之后关闭
	 */
	function closeOrgTree() {
		closeWindow();
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
