$(document).ready(function() {
	loadGrid();
	loadCust();
	$('#hideFrame').bind('load', promgtMsg);
}); 

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '数据列表',		
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						rownumbers : true,
						pageSize : 20,
						height : 420,
						width : 'auto',
						queryParams : {
							orgIds : $("#orgId").val()
						},
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
						columns : [[{
							title : '',
							align : 'center',
							colspan : 8
						},{
							title : '本周补货模块',
							align : 'center',
							colspan : 14
						},{
							title : '下周补货预估',
							align : 'center',
							colspan : 4
						},{
							title : '日预警追踪',
							align : 'center',
							colspan : 2
						}],
						[{
							field : 'skuId',
							hidden : true
						},{
							field : 'orgRegion',
							title : '大区',
							width : 80,
							align : 'center'
						}, {
							field : 'orgProvince',
							title : '省份',
							width : 80,
							align : 'center'
						}, {
							field : 'orgCity',
							title : '城市',
							width : 80,
							align : 'center'
						}, {
							field : 'kunnr',
							title : '经销商编号',
							width : 100,
							align : 'center'
						}, {
							field : 'kunnrName',
							title : '经销商',
							width : 150,
							align : 'center'
						}, {
							field : 'skuName',
							title : '品项',
							width : 150,
							align : 'center'
						}, {
							field : 'skuVolume',
							title : '单箱体积',
							width : 80,
							align : 'center'
						}, {
							field : 'skuPrice',
							title : '单价',
							width : 80,
							align : 'center'
						}, {
							field : 'isImportantSku',
							title : '是否重点品项',
							width : 80,
							align : 'center'
						}, {
							field : 'stockWarning',
							title : '预警库存',
							width : 80,
							align : 'center'
						}, {
							field : 'salesPlanNextWeek',
							title : '未来一周分销预估',
							width : 80,
							align : 'center'
						}, {
							field : 'stockStandard',
							title : '标准库存量',
							width : 80,
							align : 'center'
						}, {
							field : 'estimateStock',
							title : '实时库存',
							width : 80,
							align : 'center'
						}, {
							field : 'orderOnWay',
							title : '在途订单',
							width : 80,
							align : 'center'
						}, {
							field : 'putNotTakeOrder',
							title : '已排未发',
							width : 80,
							align : 'center'
						}, {
							field : 'stockTotal',
							title : '库存小计',
							width : 80,
							align : 'center'
						}, {
							field : 'productNeed',
							title : '补货需求订单',
							width : 80,
							align : 'center'
						}, {
							field : 'productNeedVolume',
							title : '补货需求订单体积',
							width : 80,
							align : 'center'
						}, {
							field : 'productSum',
							title : '凑车后订单',
							width : 80,
							align : 'center'
						}, {
							field : 'productSumVolume',
							title : '凑车后体积',
							width : 80,
							align : 'center'
						}, {
							field : 'notPutOrder',
							title : '未排订单',
							width : 80,
							align : 'center'
						}, {
							field : 'takeOrder',
							title : '需上单量',
							width : 80,
							align : 'center'
						}, {
							field : 'takeOrderPrice',
							title : '需补款金额',
							width : 80,
							align : 'center'
						}, {
							field : 'stockStandardNextWeek',
							title : '下周标准库存量',
							width : 80,
							align : 'center'
						}, {
							field : 'productNeedNextWeek',
							title : '补货量',
							width : 80,
							align : 'center'
						}, {
							field : 'productSumNextWeek',
							title : '凑整车后计划',
							width : 80,
							align : 'center'
						}, {
							field : 'takeOrderPriceNextWeek',
							title : '补货金额',
							width : 80,
							align : 'center'
						}, {
							field : 'stockWarningTwoWeek',
							title : '库存预警（14天）',
							width : 80,
							align : 'center'
						}, {
							field : 'takeOrderFirst',
							title : '需求优先发货量',
							width : 80,
							align : 'center'
						}]], 
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
		pageSize : 20,
		pageList : [ 10, 20, 30 ],
		beforePageText : '第',
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
	
}

function loadCust(){
	$('#kunnr').combogrid({
		panelHeight : 250,
		panelWidth : 380,
		pagination : true,
		method : 'post',
		singleSelect : true,
		multiple : false,
		url : appUrl + '/kunnrAction!kunnrSearch.jspa?orgIds='+$('#orgId').val()+'&bhxjFlag='+'C',
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
						+ '/stockReportAction!searchSku.jspa',
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

/**
 * 经销商下拉查询
 * 
 * @param name1
 */
function searcherKonzs(name1) {
	var regx = /^\d+$/;
	if(regx.test(name1)){
		var queryParams = $('#kunnr').combogrid("grid").datagrid('options').queryParams;
		queryParams.kunnrId = name1;
		queryParams.name1 = null;
		$('#kunnr').combogrid("grid").datagrid('reload');
	}else{
		var queryParams = $('#kunnr').combogrid("grid").datagrid('options').queryParams;
		queryParams.kunnrId = null;
		queryParams.name1 = encodeURIComponent(name1);
		$('#kunnr').combogrid("grid").datagrid('reload');
	}
}

function searcherSku(val) {
	var queryParams = $('#skuId').combogrid("grid").datagrid('options').queryParams;
	queryParams.skuName = encodeURIComponent(val);
	$('#skuId').combogrid("grid").datagrid('reload');
}

function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.orgIds = $('#orgId').val();
	queryParams.startDate = $('#startDate').val();
	queryParams.endDate = $('#endDate').val();
	queryParams.kunnr = $('#kunnr').combobox("getValue");
	queryParams.skuId = $('#skuId').combobox("getValue");
	queryParams.isImportant = $('#isImportant').combobox('getValue');
	$("#datagrid").datagrid({
		url : appUrl + '/kunnrManager/kunnrManagerAction!searchProductionNeedList.jspa'
	});
}
function exportForExcel() {
	$.messager.progress();
	openTime();
	var form = window.document.forms[0];
	form.action = appUrl
			+ '/kunnrManager/kunnrManagerAction!exportForExcelStockSafety.jspa';
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
//		$.messager.alert('Tips', successResult, 'info');
//		$('#datagrid').datagrid('reload');
		$.messager.confirm('Tips', successResult, function(r) {
			if (r) {
				$('#excelDialog').dialog('close');
				$('#excelProductionPlanDialog').dialog('close');
				search();
			}
		});
	}
}

//创建窗口对象
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

//创建窗口对象
function initMaintAccount(ffit, wWidth, wHeight, title, url, l, t) {
	var url = appUrl + url;
	var WWidth = wWidth;
	var WHeight = wHeight;
	var Ffit = ffit;
	var $win = $("#maintWindow")
			.window(
					{
						title : title,
						width : WWidth,
						height : WHeight,
						content : '<iframe frameborder="no" width="100%" height="100%" src='
								+ url + '/>',
						shadow : true,
						fit : Ffit,
						modal : true,
						closed : true,
						closable : true,
						minimizable : false,
						maximizable : false,
						collapsible : false,
						draggable : true,
						left : l,
						top : t
					});

	$win.window('open');

}

function closeMaintWindow() {
	$("#maintWindow").window('close');
}

function choseOrg() {
//	initMaintAccount(false, '400', '400','组织选择', '/visitInfo/visitInfoAction!orgTreePage.jspa',353,70);
	initMaintAccount(false, '400', '400','组织选择', '/newOrgAction!newOrgTree.jspa',353,70);
}
function returnValue(selectedId, selectedName) {
	$("#orgId").val(selectedId);
	$("#orgName").val(selectedName);
	loadCust();
}

function closeOrgTree() {
	$("#maintWindow").window('close');
}
function reloadDatagrid() {
	self.location.reload(true);
}
function clearValue(){
	$('#kunnr').combobox("clear");
	$('#skuId').combobox("clear");
	$('#startDate').val("");
	$('#endDate').val("");
}
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
	date = date + month[str[1]] + "-" + str[2];
	return date;
}

document.onkeydown = function(e) {// 这个事件在用户按下任何键盘键（包括系统按钮，如箭头键和功能键）时发生
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		editeEnd();
		return false;
	}
	return true;
};