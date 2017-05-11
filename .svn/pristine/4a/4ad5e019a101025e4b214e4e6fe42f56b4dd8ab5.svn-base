$(document).ready(function() {
	
	loadGrid();
	if($("#kunnrId").val()==""){
		loadKunnr();
	}
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '经销商列表',		
						url : appUrl + '/stockReportAction!searchOrderCheck.jspa',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						rownumbers : true,
						height : 420,
						width : 'auto',
						queryParams : {
							kunnr : $("#kunnr").val(),
							orgIds:$('#orgId').val()
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
						columns :[[ {
							field : 'orgRegion',
							title : '大区',
							width : 50,
							align : 'center'
						}, {
							field : 'orgProvince',
							title : '省份',
							width : 50,
							align : 'center'
						}, {
							field : 'orgCity',
							title : '城市',
							width : 50,
							align : 'center'
						}, {
							field : 'kunnr',
							title : '经销商编号',
							width : 80,
							align : 'center'
						}, {
							field : 'kunnrName',
							title : '经销商名称',
							width : 150,
							align : 'center'
						}, {
							field : 'checkYear',
							title : '账单年份',
							width : 80,
							align : 'center'
						}, {
							field : 'checkMonth',
							title : '账单月份',
							width : 80,
							align : 'center'
						}, {
							field : 'lastCheckDate',
							title : '当月最后查看时间',
							width : 150,
							align : 'center'
						}, {
							field : 'state',
							title : '反馈意见',
							width : 100,
							align : 'center',
							formatter : function(value) {
								if(value=="A"){
									return "确认无误";
								}else if(value=="B"){
									return "确认有差异";
								}else if(value=="C"){
									return "看不懂无法确认";
								}
							}
						}, {
							field : 'checkDesc',
							title : '详细描述',
							width : 150,
							align : 'center'
						},{
							field : 'kunnrStatus',
							title : '客户状态',
							width : 80,
							align : 'center',
							formatter : function(value, row, index) {
								if (value == 1) {
									return '正常客戶';
								} else if (value == 2) {
									return '<font color="red">已关客戶</font>';
								} else if (value == 3) {
									return '<font color="red">关户中</font>';
								}
							}
						}, {
							field : 'createDate',
							title : '提交日期',
							width : 150,
							align : 'center'
						}
//						, {
//							field : 'oper',
//							title : '操作',
//							width : 60,
//							align : 'center',
//							formatter : function(value, row, index) {
//									if(row.editing){ 
//										var s = '<a href="#" onclick="saverow(this)">保存</a>';
//										var c = '<a href="#" onclick="cancelrow(this)">取消</a>';
//										return s+'&nbsp;'+c;
//									}else{
//										var a='<img style="cursor:pointer" onclick="editrow(this)" title="修改" src='
//											+ imgUrl
//											+ '/images/actions/action_edit.png align="absMiddle"></img>';
//									    return a;
//									}
//							}
//					    }
						]], 
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
		url : appUrl + "",
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

function save(){
	if($("#startDate").val()==""){
		$.messager.alert('提示', '请选择账单年月！', '提示');
		return;
	}
	if($("#create_state").combobox("getValue")==""){
		$.messager.alert('提示', '请选择反馈意见！', '提示');
		return;
	}
	if($("#create_state").combobox("getValue")=="B" && $("#create_checkDesc").val()==""){
		$.messager.alert('提示', '请填写详细描述！', '提示');
		return;
	}
	$('#otherIp').val(returnCitySN["cip"])

	$.ajax({
		type : "post",
		url : appUrl + '/stockReportAction!searchOrderCheckLastCheckDate.jspa',
		data : {
			kunnr : $('#kunnr').val(),
			startDate : $('#startDate').val()
		},
		success : function(result) {
			if(result.length>0){
				var form = window.document.forms[0];
				form.action = appUrl + "/stockReportAction!createOrderCheck.jspa";
				form.target = "hideFrame";
				form.submit();
				search();
			}else{
				$.messager.alert('提示', '当月尚未查看报表！', '提示');
			}
		}
	});
}

function loadKunnr(){

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

/**
 * 所属经分销商下拉查询
 * 
 * @param name1
 */
function searcherKonzs(name1) {
	var queryParams = $('#kunnr').combogrid("grid").datagrid('options').queryParams;
	queryParams.name1 = encodeURIComponent(name1);
	queryParams.orgId= $('#orgId').val();//刷新参数orgId的值
	$('#kunnr').combogrid("grid").datagrid('reload');
}

function exportForExcel() {
	$.messager.progress();
	openTime();
	var form = window.document.forms[0];
	form.action = appUrl
			+ '/stockReport/stockReportAction!exportForExcelOrderCheck.jspa';
	form.target = "hideFrame";
	form.submit();
}
function openTime() {
	setTimeout(function() {
		var timer = setInterval(function() {
			$.ajax({
				type : "post",
				url : appUrl + "/stockReport/stockReportAction!checkDownLoadOver.jspa",
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

function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.kunnr = $('#kunnr').combobox("getValue");
	queryParams.state = $('#state').combobox("getValue");
	queryParams.flag = $('#flag').combobox("getValue");
	queryParams.orgIds = $('#orgId').val();
	queryParams.startDate = $('#startDate').val();
	queryParams.endDate = $('#endDate').val();
	$("#datagrid").datagrid('load');
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

function clearValue(){
	$('#skuId').combo("setValue","");
	$('#custId').combobox("setValue","");
	$('#custKunnr').combobox("setValue","");
	$('#startDate').val("");
	$('#endDate').val("");
}

function choseOrg() {
//	initMaintAccount(false, '400', '400','组织选择', '/question/questionAction!orgTreePage.jspa',353,70);
	initMaintAccount(false, '400', '400','组织选择', '/newOrgAction!newOrgTree.jspa',353,70);

//	initMaintWindowForOrg('选择组织', '/newOrgAction!newOrgTree.jspa');
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