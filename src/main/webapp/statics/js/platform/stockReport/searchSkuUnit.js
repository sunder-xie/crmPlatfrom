$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {

	$('#list')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '查询结果',
						height : height,
						striped : true,
						url : appUrl
								+ '/stockReport/stockReportAction!searchSkuUnitList.jspa',

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
						columns :[[ {
							field : 'skuId',
							title : 'sku编号',
							width : 80,
							align : 'center'
						}, {
							field : 'skuName',
							title : 'sku名称',
							width : 150,
							align : 'center'
						}, {
							field : 'cgId',
							title : '品类编号',
							width : 80,
							align : 'center'
						}, {
							field : 'categoryName',
							title : '品类名称',
							width : 150,
							align : 'center'
						}, {
							field : 'matterNum',
							title : '物料号',
							width : 150,
							align : 'center'
						}, {
							field : 'skuUnit',
							title : '单位',
							width : 80,
							align : 'center'
						}, {
							field : 'unitCoefficient',
							title : '系数',
							width : 150,
							align : 'center'
						},
						{
							field : 'oper',
							title : '操作',
							width : 60,
							align : 'center',
							formatter : function(value, row, index) {
								var a='<img style="cursor:pointer" onclick="update('+row.skuId+')" title="修改" src='
									+ imgUrl
									+ '/images/actions/action_edit.png align="absMiddle"></img>';
								return a;
							}
						}
						] ]
						, 
						toolbar : [ "-",{
							text : '同步',
							iconCls : 'icon-reload',
							handler : function() {
								reload(); 
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

function reload() {
	var form = window.document.forms[0];
	form.action = appUrl
			+ "/stockReport/stockReportAction!createSkuUnit.jspa";
	form.target = "hideFrame";
	form.submit();
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

function update(id) {
	initMaintWindow('修改信息', '/stockReport/stockReportAction!toUpdateSkuUnit.jspa?skuId='+id, 500, 300);
}

function search() {
	var queryParams = $('#list').datagrid('options').queryParams;
	queryParams.skuId = $('#skuId').val();
	queryParams.skuName = encodeURIComponent($('#skuName').val());
	queryParams.categoryId = $('#categoryId').val();
	queryParams.categoryName = encodeURIComponent($('#categoryName').val());
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