$(document).ready(function() {
	$.ajax({
		type : "post",
		url : appUrl + "/orderNewAction!getBalance.jspa",
		success : function(data) {
			var total;
			$("#dk_netwr").append(data.dk_netwr);
			$("#fy_netwr").append(data.fy_netwr);
//			$("#zk_netwr").append(data.zk_netwr);
//			total=accAdd(accAdd(data.dk_netwr,data.fy_netwr),data.zk_netwr);
//			$("#total_netwr").append(total);
		}
	});
	
	
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function accAdd(arg1,arg2){//加法
	  var r1,r2,m;
	  try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}
	  try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}
	  m=Math.pow(10,Math.max(r1,r2))
	  return (arg1*m+arg2*m)/m
}

function loadGrid() {
	$('#order_list')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '查询结果',
						height : height,
						striped : true,
						url : appUrl
								+ '/orderNewAction!searchAccount.jspa',
                        loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						nowrap : true,
						pagination : true,
						rownumbers : true,
						columns : [ [
								{
									field : 'ck',
									align : 'center',
									checkbox : true
								},
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
											var status = row.status;
											var accountId = row.accountId;
											var totalPrice = accAdd(row.price,row.fee);
//											var linkA = '<img style="cursor:pointer" onclick="edit('
//												        + orderId
//												        + ')" title="查看资料" src='
//												        + imgUrl
//												        + '/images/actions/action_view.png align="absMiddle"></img>';
//											var linkB = '<a href="javascript:printView('
//												        + orderId
//												        + ')">打印</a>';
											var linkC = '<a href="javascript:toPay('
												        + accountId
												        + ')">继续支付</a>';
											var linkD = '<a href="javascript:toRefund('
										        + accountId+','+totalPrice
										        + ')">退款</a>';
											if(status=='N'){
												return linkC;
											}else if (status=="Y"){
												if($("#isOffice").val()==""){
													return linkD;							
												}
											}
								        }
								},
								{
									title : '支付号',
									field : 'accountId',
									width : 120,
									align : 'center'
								},
				                {
				                	title : '客户名称',
				                	field : 'kunnrName',
				                	width : 170,
									align : 'center'
				                },{
				                	title : '下单人',
				                	field : 'userName',
				                	width : 130,
									align : 'center'
				                },{
				                	title : '金额',
				                	field : 'price',
				                	width : 80,
									align : 'center'
				                },{
				                	title : '手续费',
				                	field : 'fee',
				                	width : 80,
									align : 'center'
				                },{
				                	title : '支付类型',
				                	field : 'payType',
				                	width :70,
									align : 'center',
									formatter : function(value) {
										if(value=="A"){
				                			return "B2C";
				                		}else if(value=="B"){
				                			return "B2B";
				                		}
				                	}
				                },{
				                	title : '卡片类型',
				                	field : 'cardType',
				                	width :70,
									align : 'center',
									formatter : function(value) {
										if(value=="1"){
				                			return "储蓄卡";
				                		}else if(value=="3"){
				                			return "信用卡";
				                		}else if(value=="6"){
				                			return "农行外网银";
				                		}
				                	}
				                },{
				                	title : '支付结果',
				                	field : 'status',
				                	width :70,
									align : 'center',
									formatter : function(value) {
										if(value=="Y"){
				                			return "成功";
				                		}else if(value=="N"){
				                			return "支付中";
				                		}else if(value=="F"){
				                			return "失败";
				                		}else if(value=="R"){
				                			return "已退款";
				                		}
				                	}
				                }
				                ,{
				                	title : '会计凭证',
				                	field : 'inSap',
				                	width :220,
									align : 'center',
									formatter : function(value,row,rec) {
										if(row.status=="Y"){
				                			if(value==null){
				                				return "获取会计凭证失败，请联系财务";
				                			}else{
				                				return value;
				                			}
				                		}
				                	}
				                }
				                ,{
				                	title : '充值时间',
				                	field : 'createDate',
				                	width : 220,
				                	align : 'center'
//									formatter : function(value) {
//										return utcToDate(value.replace(
//											       /\/Date\((\d+)\+\d+\)\//gi,
//									               "new Date($1)"));
//									}
				                },{
				                	title : '描述',
				                	field : 'accountDesc',
				                	width :150,
				                	align : 'center'
				                },{
				                	title : '银行流水号',
				                	field : 'successNum',
				                	width :100,
				                	align : 'center'
//				                	title : '数据录入',
//				                	field : 'inSap',
//				                	width :70,
//									align : 'center',
//									formatter : function(value) {
//										if(value=="Y"){
//				                			return "已录入";
//				                		}else if(value=="N"){
//				                			return "未录入";
//				                		}
//				                	}
								}
								] ],
						toolbar : [ "-", {
							text : '充值',
							iconCls : 'icon-add',
							handler : function() {
								if($("#isOffice").val()!=""){
									add();									
								}
							}
						}, "-" ,{
							text : '合并打印',
							iconCls : 'icon-print',
							handler : function() {
								accountPrint();
							}
						}, "-" ,
						{
							text : '<font color="red">账户余额会在支付成功后1-3个工作日内更新</font>'
						}
						],
						onLoadSuccess : function(data) {
							$(".datagrid-header-check")[0].disabled = true;
							$(".datagrid-header-check input").unbind('click');
							selectedFile(data.rows);
						}
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

function selectedFile(rows) {
	for ( var j = 0; j < rows.length; j++) {
		if (rows[j]['status'] != 'Y') {
			$(".datagrid-row[datagrid-row-index=" + j
					+ "] input[type='checkbox']")[0].disabled = true;
		}
	}
}

function search() {
	var queryParams = $('#order_list').datagrid('options').queryParams;
	queryParams.status = $("#status").combobox("getValue");
	queryParams.startTime = $("#startTime").datebox("getValue");
	queryParams.endTime = $("#endTime").datebox("getValue");
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

function toRefund(accountId,totalPrice) {
	$.messager.confirm(
			'Confirm',
			'是否确认退款?',
			function(r) {
				if (r) {
					var form = window.document.forms[0];
					form.action = appUrl
					+ '/orderNewAction!refundAccount.jspa?accountId='+accountId+'&totalPrice='+totalPrice;
					form.target = "hideFrame";
					form.submit();
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

function accountPrint(accountId){
	var ids = [];
	var rows = $('#order_list').datagrid('getSelections');
	if (rows == '') {
		$.messager.alert('Tips', '请选择数据!', 'warning');
		return;
	}
	for ( var i = 0; i < rows.length; i++) {
		ids.push(rows[i].accountId);
	}
	var WWidth = 1000;
	var WLeft = Math.ceil((window.screen.width - WWidth) / 2);
	window.open(appUrl + '/orderNewAction!toPrintAccount.jspa?accountIds='+ids, "经销商支付打印",
			"left=" + WLeft + ",top=20" + ",width=" + WWidth + ",height="
			+ (window.screen.height - 100)
			+ ",toolbar=no,rolebar=no,scrollbars=yes,location=no,menubar=no,resizable=yes,titlebar=no");
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