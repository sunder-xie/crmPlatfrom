$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	//$.messager.alert('提示', '未选中任何权限点！', '提示');
//	alert("2");
	$('#order_list')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '查询结果',
						height : height,
						striped : true,
						url : appUrl
								+ '/orderNewAction!searchOrderNew.jspa?orgId='
								+ $("#orgId").val(),

						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						nowrap : true,
						pagination : true,
						rownumbers : true,
						columns : [ [
//								{
//									field : 'ck',
//									align : 'center',
//									checkbox : true
//								},
								/*
									 * , { id : 'dictTypeId', title : '序号',
									 * field : 'dictTypeId', width :
									 * setColumnWidth(0.1), align : 'center',
									 * sortable : true }
									 */
								{
										field : 'oper',
										title : '操作',
										width : 100,
										align : 'center',
										formatter : function(value, row, rec) {
											var orderId = row.orderId;
											var orderFundsStatus = row.orderFundsStatus;
											var linkA = '<img style="cursor:pointer" onclick="edit('
												        + orderId
												        + ')" title="查看资料" src='
												        + imgUrl
												        + '/images/actions/action_view.png align="absMiddle"></img>';
											var linkB = '<a href="javascript:printView('
												        + orderId
												        + ')">打印</a>';
											var linkC = '<a href="javascript:toPay('
												        + orderId
												        + ')">支付</a>';
											if(orderFundsStatus=='Y'){
												return linkA + '&nbsp;&nbsp;' + linkB;
											}else{
												return linkA + '&nbsp;&nbsp;' + linkB + '&nbsp;&nbsp;' + linkC;
											}
								        }
								},
								{
									title : '订单号',
									field : 'orderId',
									sortable : true,
									width : 100,
									align : 'center'
								},
				                {
				                	title : '客户名称',
				                	field : 'kunnrName',
				                	sortable : true,
				                	width : 170,
									align : 'center'
				                },{
				                	title : '下单人',
				                	field : 'userName',
				                	sortable : true,
				                	width : 130,
									align : 'center'
				                },{
				                	title : '下单人组织',
				                	field : 'orgName',
				                	sortable : true,
				                	width : 120,
									align : 'center'
				                },{
				                	title : '订单状态',
				                	field : 'status',
				                	width :70,
									align : 'center',
									formatter : function(value) {
				                		if(value=="U"){
				                			return "正常";
				                		}else if(value=="D"){
				                			return "作废";
				                		}
				                		
				                	}
				                },{
				                	title : '是否送货',
				                	field : 'orderStatus',
				                	sortable : true,
				                	width :80,
									align : 'center',
									formatter : function(value) {
				                		if(value=="Y"){
				                			return "已送";
				                		}else if(value=="N"){
				                			return "未送";
				                		}
				                		
				                	}
				                },{
				                	title : '是否收款',
				                	field : 'orderFundsStatus',
				                	sortable : true,
				                	width :80,
									align : 'center',
									formatter : function(value) {
				                		if(value=="Y"){
				                			return "已打款";
				                		}else if(value=="N"){
				                			return "未打款";
				                		}
				                		
				                	}
				                },{
				                	title : '订单描述',
				                	field : 'orderDesc',
				                	sortable : true,
				                	width :150,
									align : 'center'
				                },
								{
				                	title : '订单创建时间',
				                	field : 'createDate',
									width : 220,
									sortable : true,
									align : 'center'
//									formatter : function(value) {
//										return utcToDate(value.replace(
//											       /\/Date\((\d+)\+\d+\)\//gi,
//									               "new Date($1)"));
//									}
								}
								] ],
						toolbar : [ "-", {
							text : '新增',
							iconCls : 'icon-add',
							handler : function() {
								if($("#loginId").val()=="admin" || $("#isOffice").val()!=null){
									add();
								}
							}
						}, "-" 
						]
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
	queryParams.kunnr = $("#kunnr").val();
	queryParams.kunnrName = encodeURIComponent($("#kunnrName").val());
	queryParams.beginDate = $("#beginDate").datebox("getValue");
	queryParams.endDate = $("#endDate").datebox("getValue");
	queryParams.name2 = encodeURIComponent($("#name2").val());
	queryParams.userName = encodeURIComponent($("#userName").val());
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
	initMaintWindow('创建订单', '/orderNewAction!toCreateOrderNew.jspa', 1170, 540);
}

function edit(orderId) {
	initMaintWindow('订单信息修改',
			'/orderNewAction!toUpdateOrderNew.jspa?orderId='+ orderId, 1170, 520);
}

function toPay(orderId) {
	initMaintWindow('支付信息',
			'/orderNewAction!toPay.jspa?orderId='+ orderId, 400, 520);
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