$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#list')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '查询结果',
						height : height,
						striped : true,
						url : appUrl
								+ '/stockReportAction!searchSkuPercentList.jspa',

						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						nowrap : true,
						pagination : true,
						rownumbers : true,
						onBeforeEdit : function(index, row) {
							row.editing = true;
							updateActions(index);
						},
						onAfterEdit : function(index, row) {
							row.editing = false;
							updateActions(index);
						},
						onCancelEdit : function(index, row) {
							row.editing = false;
							updateActions(index);
						},
						columns : [ [
						        {
							        field : 'skuId',
							        title : 'SKU编号',
							        width : 50,
									align : 'center'
						        },
						        {
							        field : 'cgId',
							        title : '品类编号',
									hidden : true
						        },
								{
									field : 'skuName',
									title : 'SKU',
									width : 100,
									align : 'center'
								},
								{
									field : 'cgName',
									title : '所属品类',
									width : 100,
									align : 'center'
								},
								{
									field : 'skuPercent',
									title : '百分比(%)',
									width : 100,
									align : 'center',
									editor : {
										type : 'text',
										options : {}
									}
								},
								{
									field : 'oper',
									title : '操作',
									width : 60,
									align : 'center',
									formatter : function(value, row, index) {
											if(row.editing){ 
												var s = '<a href="#" onclick="saverow(this)">保存</a>';
												var c = '<a href="#" onclick="cancelrow(this)">取消</a>';
												return s+'&nbsp;'+c;
											}else{
												return '<img style="cursor:pointer" onclick="editrow(this)" title="修改" src='
												+ imgUrl
												+ '/images/actions/action_edit.png align="absMiddle"></img>';
											}
									}
							}
								] ]
					});
	// 分页控件
	var p = $('#list').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '第',
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}

function updateActions(index) {
	$('#list').datagrid('updateRow', {
		index : index,
		row : {}
	});
}

function getRowIndex(target) {
	var tr = $(target).closest('tr.datagrid-row');
	return parseInt(tr.attr('datagrid-row-index'));
}

function editrow(target) {
	$('#list').datagrid('beginEdit', getRowIndex(target));
}

function cancelrow(target){
	$('#list').datagrid('cancelEdit', getRowIndex(target));
}

function saverow(target) {
	var rowIndex = getRowIndex(target);
	$('#list').datagrid('endEdit', getRowIndex(target));
	var rows = $('#list').propertygrid('getChanges');
	if(rows.length==0){
		return;
	}
	
	var sum=-1;
	$.ajax({
		type : "post",
		url : appUrl + "/stockReportAction!searchSkuPercentSum.jspa",
		data : {
			categoryId : rows[0].cgId,
			skuId : rows[0].skuId
		},
		success : function(data) {
				sum=data;
				if((parseFloat(sum)+parseFloat(rows[0].skuPercent))>100){
					$.messager.alert('Tips', "该品类总百分比超过100%,请重新填写！", 'error');
					loadGrid();
					return;
				}
				
				$.ajax({
					type : "post",
					url : appUrl + "/stockReportAction!updateSkuPercent.jspa",
					data : {
						skuId : rows[0].skuId,
						percent : rows[0].skuPercent
					},
					success : function(executeResult) {
						loadGrid();
					}
				});
		}
	});
}

function search() {
	var queryParams = $('#list').datagrid('options').queryParams;
	queryParams.skuName = encodeURIComponent($("#skuName").val());
	queryParams.categoryName = encodeURIComponent($("#categoryName").val());
	$("#list").datagrid('load');
}

// 创建窗口对象
function initMaintWindow(title, url, width, height) {
	var url = appUrl + url;
	var WWidth = width;
	var WHeight = height;
	var $win = $("#maintWindow")
			.window(
					{
						title : title,
						width : WWidth,
						height : WHeight,
						content : '<iframe frameborder="no" width="100%" height="100%" src='
								+ url + '/>',
						shadow : true,
						modal : true,
						closed : true,// /
						closable : true,//
						minimizable : false,
						maximizable : true,
						collapsible : true,
						draggable : true
					//
					});

	$win.window('open');
}

function selectOrgTree() {
	initMaintWindow('选择组织', '/questionAction!orgTreePage.jspa', 400, 460);
}
function returnValue(selectedId, selectedName) {
	document.getElementById('orgId').value = selectedId;
	document.getElementById('orgName').value = selectedName;
}

function closeOrgTree() {
	closeDtPlan();
}
function closeDtPlan() {
	$("#maintWindow").window('close');
}

function clearValue(){
	
}

// 关闭创建页面
function closeMaintWindow() {
	$("#maintWindow").window('close');
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info');
		search();
	}
}

/*
 * document.onkeydown = function(e) { var theEvent = e || window.event; var code =
 * theEvent.keyCode || theEvent.which || theEvent.charCode; if (code == 13) {
 * search(); return false; } return true; };
 */

function utcToDate(utcCurrTime) {
	utcCurrTime = utcCurrTime + "";
	var date = "";
	var month = new Array();
	month["Jan"] = 1;
	month["Feb"] = 2;
	month["Mar"] = 3;
	month["Apr"] = 4;
	month["May"] = 5;
	month["Jun"] = 6;
	month["Jul"] = 7;
	month["Aug"] = 8;
	month["Sep"] = 9;
	month["Oct"] = 10;
	month["Nov"] = 11;
	month["Dec"] = 12;
	var week = new Array();
	week["Mon"] = "一";
	week["Tue"] = "二";
	week["Wed"] = "三";
	week["Thu"] = "四";
	week["Fri"] = "五";
	week["Sat"] = "六";
	week["Sun"] = "日";

	str = utcCurrTime.split(" ");
	date = str[5] + "-";
	date = date + month[str[1]] + "-" + str[2] + " " + str[3];
	return date;
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
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