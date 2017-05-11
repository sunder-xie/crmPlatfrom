$(document).ready(function() {
	loadGrid();
	loadKunnr();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '人员列表',		
						url : appUrl + '/kunnrBusinessAction!searchKunnrBusinessEmp.jspa',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						rownumbers : true,
						height : 420,
						width : 'auto',
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
							field : 'businessId',
							hidden : true
						}, {
							field : 'businessName',
							title : '姓名',
							width : 80,
							align : 'center'
						}, {
							field : 'businessType',
							title : '岗位',
							width : 80,
							align : 'center',
							formatter : function(value){
								if(value=='1'){
									return "客户经理";
								}else{
									return "业代";
								}
							}
						}, {
							field : 'kunnr',
							title : '所属经销商编号',
							width : 80,
							align : 'center'
						}, {
							field : 'kunnrName',
							title : '所属经销商名称',
							width : 150,
							align : 'center'
						}, {
							field : 'businessEndDate',
							title : '实际离职日期',
							width : 100,
							align : 'center',
							editor : {
								type : 'datebox',
								options : {editable:true}
							}
						}, {
							field : 'oper',
							title : '操作',
							width : 60,
							align : 'center',
							formatter : function(value, row, index) {
									if(row.editing){ 
										var s = '<a href="#" onclick="saverow(this)">保存</a>';
										var c = '<a href="#" onclick="cancelrow(this)">取消</a>';
										return s+'&nbsp;'+c;
									}else{
										var a='<img style="cursor:pointer" onclick="editrow(this)" title="修改" src='
											+ imgUrl
											+ '/images/actions/action_edit.png align="absMiddle"></img>';
									    return a;
									}
							}
					    }]]
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

function updateActions(index) {
	$('#datagrid').datagrid('updateRow', {
		index : index,
		row : {}
	});
}

function getRowIndex(target) {
	var tr = $(target).closest('tr.datagrid-row');
	return parseInt(tr.attr('datagrid-row-index'));
}

function editrow(target) {
	$('#datagrid').datagrid('beginEdit', getRowIndex(target));
}

function cancelrow(target){
	$('#datagrid').datagrid('cancelEdit', getRowIndex(target));
}

function saverow(target) {
	var rowIndex = getRowIndex(target);
	$('#datagrid').datagrid('endEdit', getRowIndex(target));
	var rows = $('#datagrid').propertygrid('getChanges');
	if(rows.length==0){
		return;
	}
	$.ajax({
		type : "post",
		url : appUrl + "/kunnrBusinessAction!updateKunnrBusinessEmp.jspa",
		data : {
			businessId : rows[0].businessId,
			businessEndDate : rows[0].businessEndDate
		},
		success : function(executeResult) {
//			if("success" == executeResult.code){
				search();
//			}else{
//				$.messager.alert('Tips', '操作失败!', 'warning', function(){
////					loadContentAndPage();
//				});
//			}
		}
	});
}

function loadKunnr(){

	$('#kunnrId').combogrid({
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

function importCsv() {
	html = '<form id="fileForm" method="post" enctype="multipart/form-data" >'
			+ '<table>'
			+ '<tr><td class="head" noWrap>模板下载</td>'
			+ '<td><a style="color:blue"   onclick=javascript:exportMouldCsv();> 1、下载模版</a></td></tr>'
			+ '<tr><td class="head" noWrap>批量导入</td>'
			+ '<td><input type="file" name="uploadFile" id="uploadFile"  style="width:200px"/>'
			+ '</tb></tr></table></form>';
	importGoalCsv('批量导入', html);
}

//csv下载终端门店导入模板
function exportMouldCsv() {
	$.messager.confirm('Confirm', '是否确定下载模板?', function(r) {
		if (r) {
			var form =document.getElementById("fileForm");
			form.action = appUrl
					+ '/stockReportAction!exportMouldCsv.jspa';
			form.submit();
		}
	});

}

//批量导入终端门店信息
function importGoalCsv(t, html) {
	if ($('#excelDialog').length < 1) {
		$(
				'<div/>',
				{
					id : 'excelDialog',
					title : '选择上传文件',
					html : "<div id='import'>"
					// + "</br>"
					+ "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
							+ html + "</div>" // +
							// "</div>"
				}).appendTo('body');
	} else {
		// $('#import').html(html);
	}
	$('#excelDialog')
			.dialog(
					{
						modal : true,
						resizable : false,
						dragable : false,
						closable : false,
						open : function() {
							$('#excelDialog').css('padding', '0.8em');
							$('#excelDialog .ui-accordion-content').css(
									'padding', '0.4em').height(
									$('#excelDialog').height() - 100);
						},
						buttons : [
								{
									text : '确定',
									handler : function() {
										/*
										 * if ($('#orgId01').val() == '' ||
										 * $('#orgId01').val() == undefined) {
										 * $.messager.alert("提示", "请选择所属组织");
										 * return; } if
										 * ($('#stationUserId01').val() == '' ||
										 * $('#stationUserId01').val() ==
										 * undefined) { $.messager.alert("提示",
										 * "请选择业务负责人"); return; }
										 */
										var file = document
												.getElementById('uploadFile').value;
										if (/^.+\.(csv|CSV)$/.test(file)) {
											$.messager.progress();
											openTime();
											var form = document
													.getElementById('fileForm');
											form.action = appUrl
													+ "/stockReportAction!importKunnrPlanCsv.jspa";
											form.target = "hideFrame";
											form.submit();
										} else {
											$.messager.alert("提示", "请导入csv文件");
										}

									}
								}, {
									text : '取消',
									handler : function() {
										$('#excelDialog').dialog('close');
									}
								} ],

						width : document.documentElement.clientWidth * 0.35,
						height : document.documentElement.clientHeight * 0.55
					});
}

/**
 * 所属经分销商下拉查询
 * 
 * @param name1
 */
function searcherKonzs(name1) {
	var queryParams = $('#kunnrId').combogrid("grid").datagrid('options').queryParams;
	queryParams.name1 = encodeURIComponent(name1);
	$('#kunnrId').combogrid("grid").datagrid('reload');
}


function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.kunnrId = $('#kunnrId').combobox("getValue");
	queryParams.businessName = encodeURIComponent($('#businessName').val());
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
		$('#excelDialog').dialog('close');
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
	initMaintAccount(false, '400', '400','组织选择', '/question/questionAction!orgTreePage.jspa',353,70);
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