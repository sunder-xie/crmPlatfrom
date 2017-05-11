$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#order_list')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '查询结果',
						height : height,
						striped : true,
						url : appUrl
								+ '/orderNewAction!searchKunnrBalance.jspa',
                        loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						nowrap : true,
						pagination : true,
						rownumbers : true,
						columns : [ [
								{
									title : '经销商SAP编号',
									field : 'kunnr',
									sortable : true,
									width : 100,
									align : 'center'
								},
				                {
				                	title : '经销商名称',
				                	field : 'kunnrName',
				                	sortable : true,
				                	width : 200,
									align : 'center'
				                },{
				                	title : '预打款账户余额',
				                	field : 'dk_netwr',
				                	sortable : true,
				                	width : 100,
									align : 'center'
				                },{
				                	title : '费用账户余额',
				                	field : 'fy_netwr',
				                	sortable : true,
				                	width : 100,
									align : 'center'
				                }
//				                ,{
//				                	title : '  折扣账户余额',
//				                	field : 'zk_netwr',
//				                	sortable : true,
//				                	width : 100,
//									align : 'center'
//				                }
								] ]
					});
	// 分页控件
	var p = $('#order_list').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '第',
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}

function search() {
	var queryParams = $('#order_list').datagrid('options').queryParams;
	queryParams.orgId = $("#orgId").val();
	queryParams.custId = $("#custId").val();
	queryParams.custName = encodeURIComponent($("#custName").val());
	$("#order_list").datagrid('load');
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

function add() {
	initMaintWindow('充值', '/orderNewAction!toAccountPayPage.jspa', 400, 520);
}

function edit(orderId) {
	initMaintWindow('订单信息修改',
			'/orderNewAction!toUpdateOrderNew.jspa?orderId='+ orderId, 1170, 520);
}

function toPay(accountId) {
	$.ajax({
		type : "post",
		url : appUrl + "/orderNewAction!accountPay.jspa",
		data : {accountId : accountId},
		success : function(data) {
			window.open(data);
		}
	});
}

function exportOrder(){
	$.messager.progress();
	openTime();
	var form = window.document.forms[0];
	form.action = appUrl
			+ '/orderNewAction!exportOrderNew.jspa';
	form.target = "hideFrame";
	form.submit();
}

function openTime() {
	setTimeout(function() {
		var timer = setInterval(function() {
			$.ajax({
				type : "post",
				url : appUrl + "/orderNewAction!checkDownLoadOver.jspa?",
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

function selectOrgTree() {
	initMaintWindow('选择组织', '/customerAction!orgTreePage.jspa', 400, 460);
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

function printView(orderId){
	var WWidth = 1000;
	var WLeft = Math.ceil((window.screen.width - WWidth) / 2);
	window.open(appUrl + '/financialCheck/orderNewAction!toPrintOrderNew.jspa?orderId='+orderId, "订单信息",
			"left=" + WLeft + ",top=20" + ",width=" + WWidth + ",height="
			+ (window.screen.height - 100)
			+ ",toolbar=no,rolebar=no,scrollbars=yes,location=no,menubar=no,resizable=yes,titlebar=no");
}

//function remove() {
//	var ids = new Array();
//	var rows = $('#question_list').datagrid('getSelections');
//	for ( var i = 0; i < rows.length; i++) {
//		ids[i] = rows[i].qId;
//	}
////	if (ids == '') {
//	if (ids.length==0) {
//		$.messager.alert('提示', '未选中任何权限点！', '提示');
//		return;
//	} else {
//		$.messager
//				.confirm(
//						'Confirm',
//						'是否确认批量删除?',
//						function(r) {
//							if (r) {
//								var idsJson="[" + ids + "]";
//								var form = window.document.forms[0];
//								form.action = appUrl
//										+ '/question/questionAction!deleteQuestion.jspa?deleteQuestionId='
//										+ idsJson;
//								form.target = "hideFrame";
//								form.submit();
//							}
//						});
//	}
//
//}

function clearValue(){
	$("#orgName").val("");
	$("#orgId").val("");
	$("#custName").val("");
	$("#beginDate").datebox().next('span').find('input').val("");
	$("#endDate").datebox().next('span').find('input').val("");
	$('#custId').val("");
	$("#custState").combobox().next('span').find('input').val("");
	$("#contactName").val("");
	$("#userName").val("");
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