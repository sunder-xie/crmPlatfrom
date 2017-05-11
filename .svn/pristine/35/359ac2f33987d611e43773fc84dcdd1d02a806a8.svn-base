$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '分销量列表',		
						url : appUrl + '/stockReport/stockReportAction!searchStockReportSalesList.jspa',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						rownumbers : true,
						height : 420,
						width : 'auto',
						columns :[[ {
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
							width : 80,
							align : 'center'
						}, {
							field : 'kunnrName',
							title : '经销商',
							width : 150,
							align : 'center'
						}, {
							field : 'checkTime',
							title : '所属日期',
							width : 80,
							align : 'center'
						}, {
							field : 'unitDesc',
							title : '单位',
							width : 80,
							align : 'center'
						}, {
							field : 'sku1',
							title : '椰果单杯原味',
							width : 80,
							align : 'center'
						}, {
							field : 'sku2',
							title : '椰果单杯香芋味',
							width : 80,
							align : 'center'
						}, {
							field : 'sku3',
							title : '椰果单杯麦香味',
							width : 80,
							align : 'center'
						}, {
							field : 'sku4',
							title : '椰果单杯草莓味',
							width : 80,
							align : 'center'
						}, {
							field : 'sku5',
							title : '椰果单杯巧克力味',
							width : 80,
							align : 'center'
						}, {
							field : 'sku6',
							title : '椰果单杯咖啡味',
							width : 80,
							align : 'center'
						}, {
							field : 'sku7',
							title : '椰果单杯绿茶味',
							width : 80,
							align : 'center'
						}, {
							field : 'sku8',
							title : '椰果经典组合',
							width : 80,
							align : 'center'
						}, {
							field : 'sku9',
							title : '16杯椰果家庭装',
							width : 80,
							align : 'center'
						}, {
							field : 'sku10',
							title : '15杯椰果家庭装',
							width : 80,
							align : 'center'
						}, {
							field : 'sku11',
							title : '椰果礼盒',
							width : 80,
							align : 'center'
						}, {
							field : 'sku12',
							title : '红豆单杯',
							width : 80,
							align : 'center'
						}, {
							field : 'sku13',
							title : '16杯红豆家庭装',
							width : 80,
							align : 'center'
						}, {
							field : 'sku14',
							title : '桂圆红枣单杯',
							width : 80,
							align : 'center'
						}, {
							field : 'sku15',
							title : '15杯红豆家庭装',
							width : 80,
							align : 'center'
						}, {
							field : 'sku16',
							title : '功夫奶茶',
							width : 80,
							align : 'center'
						}, {
							field : 'sku17',
							title : '三连杯椰果原味',
							width : 80,
							align : 'center'
						}, {
							field : 'sku18',
							title : '三连杯椰果香芋',
							width : 80,
							align : 'center'
						}, {
							field : 'sku19',
							title : '三连杯红豆',
							width : 80,
							align : 'center'
						}, {
							field : 'sku20',
							title : '三连杯桂圆红枣',
							width : 80,
							align : 'center'
						}]]
					});
	
	var p = $('#datagrid').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '第',
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
	
	$('#kunnr').combogrid({
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


function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.orgId = $('#orgId').val();
	queryParams.kunnr = $('#kunnr').combobox("getValue");
	queryParams.productionStartDate = $('#productionStartDate').val();
	queryParams.productionEndDate = $('#productionEndDate').val();
	$("#datagrid").datagrid('load');
}
function exportForExcel() {
	$.messager.progress();
	openTime();
	var form = window.document.forms[0];
	form.action = appUrl
			+ '/stockReport/stockReportAction!exportStockForExcel.jspa';
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
		$.messager.alert('Tips', successResult, 'info');
		$('#datagrid').datagrid('reload');
		// search();
	}
}

// 创建窗口对象
function initMaintAccount(ffit, wWidth, wHeight, title, url, l, t) {
	var url = appUrl + url;
	var WWidth = wWidth;
	var WHeight = wHeight;
	var Ffit = ffit;
	var $win = $("#maintActivityPlanInfo")
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
function closeMaintActivityPlanRepoty() {
	$("#maintActivityPlanInfo").window('close');
}

function clearValue(){
	$('#skuId').combo("setValue","");
	$('#custId').combobox("setValue","");
	$('#custKunnr').combobox("setValue","");
	$('#startDate').val("");
	$('#endDate').val("");
}

function choseOrg() {
	initMaintAccount(false, '400', '400','组织选择', '/visitInfo/visitInfoAction!orgTreePage.jspa',353,70);
}
function returnValue(selectedId, selectedName) {
	$("#orgId").val(selectedId);
	$("#orgName").val(selectedName);
}

function closeOrgTree() {
	$("#maintActivityPlanInfo").window('close');
}
function reloadDatagrid() {
	self.location.reload(true);
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
		search();
		return false;
	}
	return true;
};