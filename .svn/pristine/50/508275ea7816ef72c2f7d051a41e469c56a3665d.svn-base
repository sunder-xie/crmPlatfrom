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
						title : '列表',		
						url : appUrl + '/stockReport/stockReportAction!searchCustomerSalesByDateList.jspa',
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
							align : 'center',
							rowspan : 2
						}, {
							field : 'orgProvince',
							title : '省份',
							width : 80,
							align : 'center',
							rowspan : 2
						}, {
							field : 'orgCity',
							title : '城市',
							width : 80,
							align : 'center',
							rowspan : 2
						}, {
							field : 'custId',
							title : '门店编号',
							width : 80,
							align : 'center',
							rowspan : 2
						}, {
							field : 'custName',
							title : '门店名称',
							width : 120,
							align : 'center',
							rowspan : 2
						}, {
							field : 'kunnrName',
							title : '所属经销商',
							width : 150,
							align : 'center',
							rowspan : 2
						}, {
							field : 'channelName',
							title : '所属渠道',
							width : 80,
							align : 'center',
							rowspan : 2
						}, {
							field : 'custAddress',
							title : '门店地址',
							width : 80,
							align : 'center',
							rowspan : 2
						}, {
							field : 'custSystem',
							title : '门店系统',
							width : 80,
							align : 'center',
							rowspan : 2
						}, {
							field : 'skuName',
							title : '品项',
							width : 150,
							align : 'center',
							rowspan : 2
						}, {
							field : 'unitDesc',
							title : '单位',
							width : 50,
							align : 'center',
							rowspan : 2
						}, {
							field : 'daySum',
							title : '合计',
							width : 80,
							align : 'center',
							rowspan : 2
						}, {
							title : '每日销量统计',
							colspan : 31
						}], 
						[{
							field : 'day1',
							title : '1日',
							width : 50,
							align : 'center'
						}, {
							field : 'day2',
							title : '2日',
							width : 50,
							align : 'center'
						}, {
							field : 'day3',
							title : '3日',
							width : 50,
							align : 'center'
						}, {
							field : 'day4',
							title : '4日',
							width : 50,
							align : 'center'
						}, {
							field : 'day5',
							title : '5日',
							width : 50,
							align : 'center'
						}, {
							field : 'day6',
							title : '6日',
							width : 50,
							align : 'center'
						}, {
							field : 'day7',
							title : '7日',
							width : 50,
							align : 'center'
						}, {
							field : 'day8',
							title : '8日',
							width : 50,
							align : 'center'
						}, {
							field : 'day9',
							title : '9日',
							width : 50,
							align : 'center'
						}, {
							field : 'day10',
							title : '10日',
							width : 50,
							align : 'center'
						}, {
							field : 'day11',
							title : '11日',
							width : 50,
							align : 'center'
						}, {
							field : 'day12',
							title : '12日',
							width : 50,
							align : 'center'
						}, {
							field : 'day13',
							title : '13日',
							width : 50,
							align : 'center'
						}, {
							field : 'day14',
							title : '14日',
							width : 50,
							align : 'center'
						}, {
							field : 'day15',
							title : '15日',
							width : 50,
							align : 'center'
						}, {
							field : 'day16',
							title : '16日',
							width : 50,
							align : 'center'
						}, {
							field : 'day17',
							title : '17日',
							width : 50,
							align : 'center'
						}, {
							field : 'day18',
							title : '18日',
							width : 50,
							align : 'center'
						}, {
							field : 'day19',
							title : '19日',
							width : 50,
							align : 'center'
						}, {
							field : 'day20',
							title : '20日',
							width : 50,
							align : 'center'
						}, {
							field : 'day21',
							title : '21日',
							width : 50,
							align : 'center'
						}, {
							field : 'day22',
							title : '22日',
							width : 50,
							align : 'center'
						}, {
							field : 'day23',
							title : '23日',
							width : 50,
							align : 'center'
						}, {
							field : 'day24',
							title : '24日',
							width : 50,
							align : 'center'
						}, {
							field : 'day25',
							title : '25日',
							width : 50,
							align : 'center'
						}, {
							field : 'day26',
							title : '26日',
							width : 50,
							align : 'center'
						}, {
							field : 'day27',
							title : '27日',
							width : 50,
							align : 'center'
						}, {
							field : 'day28',
							title : '28日',
							width : 50,
							align : 'center'
						}, {
							field : 'day29',
							title : '29日',
							width : 50,
							align : 'center'
						}, {
							field : 'day30',
							title : '30日',
							width : 50,
							align : 'center'
						}, {
							field : 'day31',
							title : '31日',
							width : 50,
							align : 'center'
						} ]],
						toolbar : [ "-",{
							text : '批量导出',
							iconCls : 'icon-download',
							handler : function() {
								exportForExcel(); 
							}
						}, "-" ]
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

function loadCust(){
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
	
	$('#custId').combogrid({
		panelHeight : 250,
		panelWidth : 380,
		pagination : true,
		method : 'post',
		singleSelect : true,
		multiple : false,
		url : appUrl + '/customerAction!customerSearch.jspa?orgId='+$('#orgId').val(),
		idField : 'custId',
		textField : 'custName',
		// multiple:true,
		columns : [ 
					 [ {
			field : 'custId',
			title : '门店编号',
			width : 120
		}, {
			field : 'custName',
			title : '门店名称',
			width : 200
		} ] ],
		toolbar : '#toolbarCust'
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
	
	$('#categoryId').combogrid(
			{
				panelHeight :250,
				panelWidth : 380,
				pagination : true,
				method : 'post',
				singleSelect : false,
				multiple : false,
				url : appUrl
						+ '/stockReportAction!searchCategory.jspa',
				idField : 'categoryId',
				textField : 'categoryName',
				columns : [
//				           [ { field : 'ck', checkbox : true } ],
				           [ {
					field : 'categoryId',
					title : '品类编号',
					width : 60
				}, {
					field : 'categoryName',
					title : '品类名称',
					width : 200
				} ] ],
				toolbar : '#toolbarCategory'
			});
	
	$('#channelId').combobox({
		url : appUrl + '/kunnrAction!getChannel.jspa',
		valueField : 'channelId',
		textField : 'channelName'
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

function searcherSku(val) {
	var queryParams = $('#skuId').combogrid("grid").datagrid('options').queryParams;
	queryParams.skuName = encodeURIComponent(val);
	$('#skuId').combogrid("grid").datagrid('reload');

}

function searcherCategory(val) {
	var queryParams = $('#categoryId').combogrid("grid").datagrid('options').queryParams;
	queryParams.categoryName = encodeURIComponent(val);
	$('#categoryId').combogrid("grid").datagrid('reload');

}

function searcherCust(val) {
	var queryParams = $('#custId').combogrid("grid").datagrid('options').queryParams;
	queryParams.custName = encodeURIComponent(val);
	$('#custId').combogrid("grid").datagrid('reload');
}

function search() {
	$('#flag').val("");
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.orgId = $('#orgId').val();
	queryParams.kunnr = $('#kunnr').combobox("getValue");
	queryParams.skuId = $('#skuId').combobox("getValue");
	queryParams.categoryId = $('#categoryId').combobox("getValue");
	queryParams.custId = $('#custId').combobox("getValue");
	queryParams.channelId = $('#channelId').combobox("getValue");
	queryParams.month = $('#month').val();
	queryParams.flag = $('#flag').val();
	$("#datagrid").datagrid('load');
}

function searchWithDesc() {
	$('#flag').val("Q");
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.orgId = $('#orgId').val();
	queryParams.kunnr = $('#kunnr').combobox("getValue");
	queryParams.skuId = $('#skuId').combobox("getValue");
	queryParams.categoryId = $('#categoryId').combobox("getValue");
	queryParams.custId = $('#custId').combobox("getValue");
	queryParams.channelId = $('#channelId').combobox("getValue");
	queryParams.month = $('#month').val();
	queryParams.flag = $('#flag').val();
	$("#datagrid").datagrid('load');
}

function exportForExcel() {
	$.messager.progress();
	openTime();
	var form = window.document.forms[0];
	form.action = appUrl
			+ '/stockReport/stockReportAction!exportForCustomerSalesByDate.jspa';
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

function choseOrg() {
	initMaintAccount(false, '400', '400','组织选择', '/visitInfo/visitInfoAction!orgTreePage.jspa',353,70);
}
function returnValue(selectedId, selectedName) {
	$("#orgId").val(selectedId);
	$("#orgName").val(selectedName);
}

function closeOrgTree() {
	$("#maintWindow").window('close');
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