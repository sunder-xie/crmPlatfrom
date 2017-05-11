$(document).ready(function() {
	init();
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function init(){
	var nowDate=new Date();
	var nowYear=nowDate.getFullYear();
	var nowMonth=nowDate.getMonth()+1;
	if(nowMonth<10){
		nowMonth='0'+nowMonth;
	}
	$("#startDate").val(nowYear+'-'+nowMonth);
	$("#endDate").val(nowYear+'-'+nowMonth);
}

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '查询结果',
						url : appUrl
								+ '/goal/goalAction!searchGoalFXChangeListJson.jspa',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						rownumbers:true,
						pagination : true,
						nowrap : true,
						striped : true,
						height : height,
						queryParams : {
							startDate : $('#startDate').val(),
							endDate : $('#endDate').val(),
							eventState : $('#eventState').combobox('getValue')
						},
						columns : [ [
						        {
									field : 'changeId',
									hidden : true
								}, {
									field : 'eventId',
									title : '事务编号',
									width : 80,
									align : 'center'
								}, {
									field : 'userName',
									title : '提报人',
									width : 80,
									align : 'center'

								}, {
									field : 'title',
									title : '事务标题',
									width : 200,
									align : 'center'

								}, {
									field : 'createDate',
									title : '申请时间',
									width : 80,
									align : 'center'

								}, {
									field : 'eventState',
									title : '事务状态',
									width : 80,
									align : 'center',
									formatter : function(v) {
										if (v=="T") {
											return "处理中";
										}else if (v=="D") {
											return "已完成";
										}else if (v=="S") {
											return "已取消";
										}else if (v=="N"){
											return "未提交";
										}
									}
								}, {
									field : 'curUserName',
									title : '当前处理人',
									width : 80,
									align : 'center'
								}, {
									field : 'oper',
									title : '操作',
									width : '80',
									align : 'center',
									formatter : function(value,row,index){
										var id=row.changeId;
										var linkA = '<img style="cursor:pointer" onclick="look('
											+ id
											+ ')" title="查看内容" src='
											+ imgUrl
											+ '/images/actions/action_view.png align="absMiddle"></img>';
										return linkA;
									}
								} ] ],
								toolbar : [ "-", {
									text : '新增',
									iconCls : 'icon-add',
									handler : function() {
										add();
									}
								}, "-", {
									text : 'Excel导出',
									iconCls : 'icon-excel',
									handler : function() {
										excel();
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

function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.userName = encodeURIComponent($('#userName').val());
	queryParams.eventId = $("#eventId").val();
	queryParams.startDate = $('#startDate').val();
	queryParams.endDate = $('#endDate').val();
	queryParams.eventTitle = $('#eventTitle').val();
	queryParams.eventState = $('#eventState').combobox('getValue');
	$("#datagrid").datagrid('load');
}

function openTime() {
	var timer = setInterval(function() {
		$.ajax({
			type : "post",
			url : appUrl + "/reimburse/reimburseAction!checkDownLoadOver.jspa",
			success : function(data) {
				if (data == 'Yes') {
					clearInterval(timer);
					$.messager.progress('close');
				}
			}
		});
	}, 500);
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	$.messager.progress('close');
	if (failResult) {
		$.messager.alert('Tips', failResult, 'error');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info');
	}
}

function toEvent(eventId) {
	initMaintWindow('事务信息',
			basisUrl + '/wfe/eventAction!toProcessEvent.jspa?event_id='
					+ eventId, 'maintWindow', '1000', '450', true);
}

function look(id){
	initMaintWindow('调整信息',
			appUrl + '/goal/goalAction!toGoalDistributionChangeView.jspa?changeId='
					+ id, 'maintWindow', '1000', '450', true);
}

function add(){
	initMaintWindow('调整信息',
			appUrl + '/goal/goalAction!toDistributionGoalChange.jspa', 
			         'maintWindow', '1000', '450', true);
}

//excel导出
function excel() {
	var form = window.document.forms[0];
	form.action = appUrl + '/goal/goalAction!exportForExcelGoalFXChange.jspa';
	form.target = "hideFrame";
	form.submit();
}

function closeMaintWindow() {
	$("#maintWindow").window('close');
}

function clearValue(){
	$("#eventId").val("");
	$("#eventState").combobox("select","");
	$("#userName").val("");
	init();
}

function initMaintWindow(title, url, id, width, height, flag) {
	var WWidth = width;
	var WHeight = height;
	var $win = $("#" + id)
			.window(
					{
						title : title,
						width : WWidth,
						height : WHeight,
						content : '<iframe frameborder="no" width="100%" height="100%" src='
								+ url + '/>',
						shadow : true,
						modal : true,
						draggable : true,
						closed : true,
						closable : true,
						minimizable : false,
						maximizable : true,
						collapsible : true,
						fit : flag,
						draggable : true
					});

	$win.window('open');
}

function utcToDate(utcCurrTime, type) {
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