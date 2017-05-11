$(document).ready(function() {
	if($("#userId").val()=="88647"){
		$("#userId").val("");
	}
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '查询结果',
						height : height,
						striped : true,
						url : appUrl
								+ '/questionAction!searchDemandList.jspa?ran='
								+ Math.random(),

						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						nowrap : true,
						pagination : true,
						rownumbers : true,
						queryParams : {
							orgId : $("#orgId").val(),
							authorId : $("#userId").val()
						},
						columns : [ [
								{
									field : 'ck',
									align : 'center',
									checkbox : true
								}/*
									 * , { id : 'dictTypeId', title : '序号',
									 * field : 'dictTypeId', width :
									 * setColumnWidth(0.1), align : 'center',
									 * sortable : true }
									 */,
								{
										field : 'oper',
										title : '操作',
										width : 60,
										align : 'center',
										formatter : function(value, row, rec) {
											var id = row.demandId;
											return '<img style="cursor:pointer" onclick="edit('
												    + id
												    + ')" title="查看资料" src='
												    + imgUrl
												    + '/images/actions/action_view.png align="absMiddle"></img>';
								        }
								},
								{
									field : 'demandId',
									title : '需求编号',
									align : 'center',
									width : 100,
									sortable : true
								},
								{
									field : 'author',
									title : '提报人',
									align : 'center',
									width : 100,
									sortable : true
								},{
									field : 'title',
									title : '标题',
									width : 250,
									align : 'center',
									sortable : true
								},
								{
									field : 'orgName',
									title : '所属组织',
									width : 250,
									align : 'center',
									sortable : true
								},
									
								{
									field : 'createDate',
									title : '提报时间',
									align : 'center',
									width : 130,
									sortable : true
								},
								{
									field : 'demandType',
									title : '需求分类',
									width : 100,
									align : 'center',
									sortable : true,
									formatter : function(v) {
										if(v=='1'){
											return '人力资源';
										}else if(v=='2'){
											return '财务管理';
										}else if(v=='3'){
											return'信息管理';
										}else if(v=='4'){
											return'营销管理';
										}else if(v=='5'){
											return'供应链管理';
										}
									}
								},
								{
									field : 'demandLevel',
									title : '需求等级',
									width : 100,
									align : 'center',
									sortable : true,
									formatter : function(v) {
										if(v=='1'){
											return '既重要又紧急';
										}else if(v=='2'){
											return '重要但不紧急';
										}else if(v=='3'){
											return'不重要但紧急';
										}
									}
								},
								{
									field : 'resultDate',
									title : '完成日期',
									width : 150,
									align : 'center',
									sortable : true,
									formatter : function(v) {
										if(v!=null){
											return utcToDate(v.replace(
													/\/Date\((\d+)\+\d+\)\//gi,
											"new Date($1)"));
										}
									}
								}
								] ],
						toolbar : [ "-", {
							text : '新增',
							iconCls : 'icon-add',
							handler : function() {
								add();
							}
						}, "-", {
							text : '删除',
							iconCls : 'icon-remove ',
							handler : function() {
								remove();
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
	queryParams.demandId = $("#demandId").val();
	queryParams.author = encodeURIComponent($("#author").val());
	queryParams.orgId = $("#orgId").val();
	queryParams.title = encodeURIComponent($("#title").val());
	queryParams.startDate = $("#startDate").datebox("getValue");
	queryParams.endDate = $("#endDate").datebox("getValue");
	queryParams.demandType = $("#demandType").combobox("getValue");
	queryParams.demandLevel = $("#demandLevel").combobox("getValue");
//	queryParams.handleState = $("#handleState").combobox("getValue");
	$("#datagrid").datagrid('load');
}

// 创建窗口对象
function initMaintWindow(title, url, width, height) {
	var url = appUrl + url;
	var WWidth = width;
	var WHeight = height;
	if(WWidth==0 && WHeight==0){
		var $win1 = $("#maintWindow")
		.window(
				{
					title : title,
					fit : true,
					content : '<iframe frameborder="no" width="100%" height="100%" src='
						+ url + '/>',
						shadow : true,
						modal : true,
						closed : true,// /
						closable : true,//
						minimizable : false,
						maximizable : true,
						collapsible : true,
						draggable : true,
						left : 0,
						top : 0
						//
				});
		$win1.window('open');
	}else{
		var $win2 = $("#orgWindow")
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
		$win2.window('open');
	}

}

function add() {
	initMaintWindow('需求录入', '/questionAction!toCreateDemand.jspa', 0, 0);
}

function edit(id) {

	initMaintWindow('需求信息',
			'/questionAction!toUpdateDemand.jspa?demandId=' + id, 0, 0);
}
//function show(id) {
//
//	initMaintWindow('需求信息查看',
//			'/questionAction!toShowQuestion.jspa?qId=' + id, 1150, 530);
//}

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

function selUser() {
	if($("#loginId").val()!="admin"){
		$("#orgTreeBut").attr("href","#");
		$("#author").attr("readonly","true");
	}
}

function remove() {
	var ids = new Array();
	var rows = $('#datagrid').datagrid('getSelections');
	for ( var i = 0; i < rows.length; i++) {
		ids[i] = rows[i].demandId;
	}
//	if (ids == '') {
	if (ids.length==0) {
		$.messager.alert('提示', '未选中任何权限点！', '提示');
		return;
	} else {
		$.messager
				.confirm(
						'Confirm',
						'是否确认批量删除?',
						function(r) {
							if (r) {
								var idsJson="[" + ids + "]";
								var form = window.document.forms[0];
								form.action = appUrl
										+ '/question/questionAction!deleteDemand.jspa?deleteQuestionId='
										+ idsJson;
								form.target = "hideFrame";
								form.submit();
							}
						});
	}

}

function clearValue(){
	
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
		$.messager.confirm('Tips', successResult, function(r) {
			if (r) {
				search();
				closeMaintWindow();
			}
		});
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