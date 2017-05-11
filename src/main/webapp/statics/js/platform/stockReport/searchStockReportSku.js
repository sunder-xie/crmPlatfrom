$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '库存列表',		
						url : appUrl + '/stockReport/stockReportAction!searchStockReportSkuList.jspa',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						rownumbers : true,
						height : 420,
						width : 'auto',
						queryParams : {
							orgId : $("#orgId").val()
						},
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
							align : 'center',
							formatter : function(v) {
								return '标箱';
							}
						}, {
							field : 'sku1',
							title : '椰果原味奶茶1*30',
							width : 80,
							align : 'center'
						}, {
							field : 'sku2',
							title : '椰果香芋奶茶1*30',
							width : 80,
							align : 'center'
						}, {
							field : 'sku3',
							title : '椰果麦香奶茶1*30',
							width : 80,
							align : 'center'
						}, {
							field : 'sku4',
							title : '椰果草莓奶茶1*30',
							width : 80,
							align : 'center'
						}, {
							field : 'sku5',
							title : '椰果巧克力奶茶1*30',
							width : 80,
							align : 'center'
						}, {
							field : 'sku6',
							title : '椰果咖啡奶茶1*30',
							width : 80,
							align : 'center'
						}, {
							field : 'sku7',
							title : '椰果绿茶奶茶1*30',
							width : 80,
							align : 'center'
						}, {
							field : 'sku8',
							title : '经典组合装奶茶(手提)1*30',
							width : 80,
							align : 'center'
						}, {
							field : 'sku9',
							title : '椰果家庭分享装1*96',
							width : 80,
							align : 'center'
						}, {
							field : 'sku10',
							title : '椰果家庭分享装1*90',
							width : 80,
							align : 'center'
						}, {
							field : 'sku11',
							title : '奶茶礼盒大箱1*8',
							width : 80,
							align : 'center'
						}, {
							field : 'sku12',
							title : '红豆奶茶1*30',
							width : 80,
							align : 'center'
						}, {
							field : 'sku37',
							title : '红豆奶茶1*30-14',
							width : 80,
							align : 'center'
						}, {
							field : 'sku13',
							title : '红豆家庭分享装1*96',
							width : 80,
							align : 'center'
						}, {
							field : 'sku14',
							title : '桂圆红枣奶茶1*30',
							width : 80,
							align : 'center'
						}, {
							field : 'sku15',
							title : '15杯红豆家庭装',
							width : 80,
							align : 'center'
						}, {
							field : 'sku16',
							title : '功夫奶茶-400ML*15',
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
							field : 'sku38',
							title : '三连杯红豆-14',
							width : 80,
							align : 'center'
						}, {
							field : 'sku20',
							title : '三连杯桂圆红枣',
							width : 80,
							align : 'center'
						}, {
							field : 'sku21',
							title : '红豆、桂圆红枣家庭分享装1*90',
							width : 80,
							align : 'center'
						}, {
							field : 'sku22',
							title : '红豆、桂圆红枣家庭分享装1*96',
							width : 80,
							align : 'center'
						}, {
							field : 'sku23',
							title : '红豆、椰果经典组合装1*30',
							width : 80,
							align : 'center'
						}, {
							field : 'sku24',
							title : '椰果原味六连杯奶茶1*36',
							width : 80,
							align : 'center'
						}, {
							field : 'sku25',
							title : '椰果香芋六连杯奶茶1*36',
							width : 80,
							align : 'center'
						}, {
							field : 'sku26',
							title : '椰果巧克力六连杯奶茶1*36',
							width : 80,
							align : 'center'
						}, {
							field : 'sku27',
							title : '超值组合装1*30',
							width : 80,
							align : 'center'
						}, {
							field : 'sku28',
							title : '奶茶礼盒大箱1*12',
							width : 80,
							align : 'center'
						}, {
							field : 'sku29',
							title : '豪华礼盒装6*12',
							width : 80,
							align : 'center'
						}, {
							field : 'sku30',
							title : '豪华礼盒装6*15',
							width : 80,
							align : 'center'
						}, {
							field : 'sku31',
							title : '芝士燕麦奶茶1*30',
							width : 80,
							align : 'center'
						}, {
							field : 'sku32',
							title : '黑米椰浆奶茶1*30',
							width : 80,
							align : 'center'
						}, {
							field : 'sku33',
							title : '焦糖仙草奶茶1*30',
							width : 80,
							align : 'center'
						}, {
							field : 'sku34',
							title : '芝士燕麦三连杯奶茶10*3',
							width : 80,
							align : 'center'
						}, {
							field : 'sku35',
							title : '黑米椰浆三连杯奶茶10*3',
							width : 80,
							align : 'center'
						}, {
							field : 'sku36',
							title : '焦糖仙草三连杯奶茶10*3',
							width : 80,
							align : 'center'
						}, {
							field : 'sku39',
							title : '椰果草莓三连杯奶茶10*3-14',
							width : 80,
							align : 'center'
						}, {
							field : 'sku40',
							title : '椰果麦香三连杯奶茶10*3-14',
							width : 80,
							align : 'center'
						}, {
							field : 'sku41',
							title : '椰果原味奶茶1*30-14',
							width : 80,
							align : 'center'
						}, {
							field : 'sku42',
							title : '椰果麦香奶茶1*30-14',
							width : 80,
							align : 'center'
						}, {
							field : 'sku43',
							title : '椰果香芋奶茶1*30-14',
							width : 80,
							align : 'center'
						}, {
							field : 'sku44',
							title : '椰果草莓奶茶1*30-14',
							width : 80,
							align : 'center'
						}, {
							field : 'sku45',
							title : '椰果巧克力奶茶1*30-14',
							width : 80,
							align : 'center'
						}, {
							field : 'sku46',
							title : '椰果咖啡奶茶1*30-14',
							width : 80,
							align : 'center'
						}, {
							field : 'sku47',
							title : '椰果原味三连杯奶茶10*3-14',
							width : 80,
							align : 'center'
						}, {
							field : 'sku48',
							title : '椰果香芋三连杯奶茶10*3-14',
							width : 80,
							align : 'center'
						}, {
							field : 'sku49',
							title : '美味组合装1*30',
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
	var queryParams = $('#kunnr').combogrid("grid").datagrid('options').queryParams;
	queryParams.name1 = encodeURIComponent(name1);
	$('#kunnr').combogrid("grid").datagrid('reload');
}


function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.kunnr = $('#kunnr').combobox("getValue");
	queryParams.orgId = $('#orgId').val();
	queryParams.productionStartDate = $('#productionStartDate').val();
	queryParams.productionEndDate = $('#productionEndDate').val();
	$("#datagrid").datagrid('load');
}
function exportForExcel() {
	$.messager.progress();
	openTime();
	var form = window.document.forms[0];
	form.action = appUrl
			+ '/stockReport/stockReportAction!exportForExcelSku.jspa';
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