$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {

	var state=[{'value':'A','text':'在用'},{'value':'B','text':'停用'}];
	$('#list')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '查询结果',
						height : height,
						striped : true,
						url : appUrl
								+ '/busPhoneAction!getEmpList.jspa',

						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						nowrap : true,
						pagination : true,
						rownumbers : true,
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
						columns : [ [
						        {
							        field : 'userId',
							        title : '用户编号',
							        hidden:true
						        },
								{
									field : 'orgRegion',
									title : '大区',
									align : 'center',
									width : 100
								},
								{
									field : 'orgProvince',
									title : '省份',
									align : 'center',
									width : 100
								},
								{
									field : 'orgName',
									title : '城市',
									align : 'center',
									width : 100
								},
								{
									field : 'userName',
									title : '姓名',
									align : 'center',
									width : 100
								},
								{
									field : 'userIdCard',
									title : '身份证',
									align : 'center',
									width : 110
								},{
									field : 'userStation',
									title : '职务',
									width : 120,
									align : 'center'
								},
								{
									field : 'userStartDate',
									title : '入职时间',
									width : 100,
									align : 'center'
								},
									
								{
									field : 'userEndDate',
									title : '离职时间',
									align : 'center',
									width : 100
								},
								{
									field : 'userState',
									title : '人事状态',
									width : 100,
									align : 'center',
									formatter : function(v) {
										if(v=='Y'){
											return '在职';
										}else if(v=='N'){
											return '离职';
										}else if(v=='S'){
											return'试用期';
										}else if(v=='W'){
											return'待入职';
										}else if(v=='R'){
											return'人才库';
										}else if(v=='D'){
											return'删除';
										}
									}
								},
								{
									field : 'busPhone',
									title : '公务手机号',
									width : 100,
									align : 'center',
									editor : {
										type : 'text',
										options : {}
									}
								},
								{
									field : 'busPhoneSimple',
									title : '公务手机短号',
									width : 100,
									align : 'center',
									editor : {
										type : 'text',
										options : {}
									}
								},
								{
									field : 'phoneStartDate',
									title : '开始使用日期',
									width : 100,
									align : 'center',
									editor : {
										type : 'datebox',
										options : {editable:true}
									}
								},
								{
									field : 'phoneEndDate',
									title : '结束使用日期',
									width : 100,
									align : 'center',
									editor : {
										type : 'datebox',
										options : {editable:true}
									}
								},
								{
									field : 'phoneState',
									title : '使用状态',
									width : 100,
									align : 'center',
									formatter : function(v) {
										if(v=='A'){
											return '在用';
										}else if(v=='B'){
											return '停用';
										}
									},
									editor : {
										type : 'combobox',
										options : {
											data : state,
											valueField : 'value',
											textField : 'text'
										}
									}
								},
								{
									field : 'phoneRemark',
									title : '备注',
									width : 100,
									align : 'center',
									editor : {
										type : 'text',
										options : {}
									}
								},
								{
									field : 'oper',
									title : '操作',
									width : 60,
									align : 'center',
									formatter : function(value, row, index) {
										var loginId=$("#loginId").val();
										if(loginId=="admin"){
											if(row.editing){ 
												var s = '<a href="#" onclick="saverow(this)">保存</a>';
												var c = '<a href="#" onclick="cancelrow(this)">取消</a>';
												return s+'&nbsp;'+c;
											}else{
												var a='<img style="cursor:pointer" onclick="editrow(this)" title="修改" src='
													+ imgUrl
													+ '/images/actions/action_edit.png align="absMiddle"></img>';
												var b='<a href="javascript:clear('+row.userId+')">清空</a>';
											    return b+"&nbsp;"+a;
											}
										}
									}
							}
								] ], 
								toolbar : [ "-",{
									text : '批量导出',
									iconCls : 'icon-download',
									handler : function() {
										exportForExcel(); 
									}
								}, "-" ]
					});
	// 分页控件
	var p = $('#list').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '第',
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}

function clear(userId){
	$.messager.confirm('Confirm','是否删除?',function(r) {
		if (r) {
			$.ajax({
				type : "post",
				url : appUrl + "/busPhoneAction!deleteEmp.jspa",
				data : {
					userId : userId
				},
				success : function(executeResult) {
					loadGrid();
				}
			});
		}
	});
}

function updateActions(index) {
	$('#list').datagrid('updateRow', {
		index : index,
		row : {}
	});
}

function getRowIndex(target) {
	var tr = $(target).closest('tr.datagrid-row');
	return parseInt(tr.attr('datagrid-row-index'));
}

function editrow(target) {
	$('#list').datagrid('beginEdit', getRowIndex(target));
}

function cancelrow(target){
	$('#list').datagrid('cancelEdit', getRowIndex(target));
}

function saverow(target) {
	var rowIndex = getRowIndex(target);
	$('#list').datagrid('endEdit', getRowIndex(target));
	var rows = $('#list').propertygrid('getChanges');
	if(rows.length==0){
		return;
	}
	$.ajax({
		type : "post",
		url : appUrl + "/busPhoneAction!updateEmp.jspa",
		data : {
			userId : rows[0].userId,
			busPhone : rows[0].busPhone,
			busPhoneSimple : rows[0].busPhoneSimple,
			phoneStartDate : rows[0].phoneStartDate,
			phoneEndDate : rows[0].phoneEndDate,
			phoneState : rows[0].phoneState,
			phoneRemark : encodeURIComponent(rows[0].phoneRemark)
		},
		success : function(executeResult) {
//			if("success" == executeResult.code){
				loadGrid();
//			}else{
//				$.messager.alert('Tips', '操作失败!', 'warning', function(){
////					loadContentAndPage();
//				});
//			}
		}
	});
}

function search() {
	var queryParams = $('#list').datagrid('options').queryParams;
	queryParams.orgId = $("#orgId").val();
	queryParams.busPhone = $("#busPhone").val();
	queryParams.userName = encodeURIComponent($("#userName").val());
	queryParams.startDate = $("#startDate").datebox("getValue");
	queryParams.endDate = $("#endDate").datebox("getValue");
	queryParams.userState = $("#userState").combobox("getValue");
	queryParams.phoneState = $("#phoneState").combobox("getValue");
	$("#list").datagrid('load');
}

function exportForExcel() {
	$.messager.progress();
	openTime();
	var form = window.document.forms[0];
	form.action = appUrl
			+ '/busPhoneAction!exportEmpToExcel.jspa';
	form.target = "hideFrame";
	form.submit();
}
function openTime() {
	setTimeout(function() {
		var timer = setInterval(function() {
			$.ajax({
				type : "post",
				url : appUrl + "/busPhoneAction!checkDownLoadOver.jspa?",
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