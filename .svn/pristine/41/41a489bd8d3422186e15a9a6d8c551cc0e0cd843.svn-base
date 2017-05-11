$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	//$.messager.alert('提示', '未选中任何权限点！', '提示');
//	alert("2");
	$('#orderInfo_list')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '查询结果',
						height : height,
						striped : true,
						url : appUrl
								+ '/orderNewAction!searchOrderNewInfo.jspa',

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
									title : '经销商编号',
									field : 'kunnr',
									sortable : true,
									width : 70,
									align : 'center'
								},
				                {
				                	title : '经销商名称',
				                	field : 'kunnrName',
				                	sortable : true,
				                	width : 250,
									align : 'center',
				                },{
				                	title : '产品名称',
				                	field : 'skuName',
				                	sortable : true,
				                	width : 130,
									align : 'center'
				                },{
				                	title : '单位',
				                	field : 'unitDesc',
				                	sortable : true,
				                	width : 70,
									align : 'center'
				                },{
				                	title : '订单数量',
				                	field : 'quantity',
				                	sortable : true,
				                	width : 70,
									align : 'center'
				                },{
				                	title : '实际数量',
				                	field : 'realQuantity',
				                	sortable : true,
				                	width : 70,
									align : 'center'
				                },{
				                	title : '金额',
				                	field : 'totalPrice',
				                	sortable : true,
				                	width :70,
									align : 'center'
				                }
								] ],
						toolbar : [ "-", {
							text : '批量导出',
							iconCls : 'icon-download ',
							handler : function() {
								exportOrder();
							}
						}
						, "-" 
						]
					});
	// 分页控件
	var p = $('#orderInfo_list').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '第',
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
	
	var kunnrs=new Array();
	if($('#kunnrs').val()!=""){
		kunnrs=$('#kunnrs').val().split(",");
	};
	
	$('#custKunnr').combogrid({
		panelHeight : 250,
		panelWidth : 380,
		pagination : true,
		method : 'post',
		singleSelect : true,
		multiple : false,
		url : appUrl + '/kunnrAction!kunnrSearch.jspa?orgId='+$('#orgId').val()+'&bhxjFlag='+'C&kunnrId='+$('#isOffice').val()+'&kunnrs='+kunnrs,
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
	
	$('#skuId').combogrid(
			{
				panelHeight :250,
				panelWidth : 380,
				pagination : true,
				method : 'post',
				singleSelect : false,
				multiple : false,
				url : appUrl
						+ '/orderNewAction!getSkuName.jspa',
				idField : 'skuId',
				textField : 'skuName',
				columns : [
//				           [ { field : 'ck', checkbox : true } ],
				           [ {
					field : 'skuId',
					title : '品项编号',
					width : 60
				}, {
					field : 'skuName',
					title : '品项名称',
					width : 200
				} ] ],
				toolbar : '#toolbarSku'
			});
}

function search() {
	var queryParams = $('#orderInfo_list').datagrid('options').queryParams;
	queryParams.kunnr = $('#custKunnr').combobox("getValue");
	queryParams.skuId = $('#skuId').combobox("getValue");
	queryParams.startTime = $('#startTime').datebox("getValue");
	queryParams.endTime = $('#endTime').datebox("getValue");
	$("#orderInfo_list").datagrid('load');
}

function searcherKonzs(name1) {
	var queryParams = $('#custKunnr').combogrid("grid").datagrid('options').queryParams;
	queryParams.name1 = encodeURIComponent(name1);
	$('#custKunnr').combogrid("grid").datagrid('reload');
}

function searcherSku(val) {
	var queryParams = $('#skuId').combogrid("grid").datagrid('options').queryParams;
	queryParams.skuName = encodeURIComponent(val);
	$('#skuId').combogrid("grid").datagrid('reload');

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

//function clearValue(){
//	$("#orgName").val("");
//	$("#orgId").val("");
//	$("#custName").val("");
//	$("#beginDate").datebox().next('span').find('input').val("");
//	$("#endDate").datebox().next('span').find('input').val("");
//	$('#custId').val("");
//	$("#custState").combobox().next('span').find('input').val("");
//	$("#contactName").val("");
//	$("#userName").val("");
//}

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