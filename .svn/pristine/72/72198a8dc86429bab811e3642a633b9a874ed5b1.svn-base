$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '下单列表',		
						url : appUrl + '/visitInfo/visitInfoAction!searchOrderInfo.jspa',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						rownumbers : true,
						height : 420,
						width : 'auto',
						frozenColumns :[[ {
							field : 'orgName',
							title : '组织',
							width : 60,
							align : 'center'
						}, {
							field : 'kunnrName',
							title : '经销商',
							width : 200,
							align : 'center'
						}, {
							field : 'kunnr',
							title : '经销商编号',
							width : 80,
							align : 'center',
							sortable : true
						}, {
							field : 'empCount',
							title : '雇员人数',
							width : 80,
							align : 'center'
						}]], 
						columns :[[{
							title : '下单门店数量',
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
	
	$('#custKunnr').combogrid({
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
	var queryParams = $('#custKunnr').combogrid("grid").datagrid('options').queryParams;
	queryParams.name1 = encodeURIComponent(name1);
	$('#custKunnr').combogrid("grid").datagrid('reload');
}


function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.orgId = $('#orgId').val();
	queryParams.month = $('#month').val();
	queryParams.userName = encodeURIComponent($('#userName').val());
	queryParams.kunnr = $('#custKunnr').combobox("getValue");
	$("#datagrid").datagrid('load');
}
function exportForExcel() {
	$.messager.progress();
	openTime();
	var form = window.document.forms[0];
	form.action = appUrl
			+ '/visitInfo/visitInfoAction!exportOrderInfo.jspa';
	form.target = "hideFrame";
	form.submit();
}
function openTime() {
	setTimeout(function() {
		var timer = setInterval(function() {
			$.ajax({
				type : "post",
				url : appUrl + "/visitInfo/visitInfoAction!checkDownLoadOver.jspa?",
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