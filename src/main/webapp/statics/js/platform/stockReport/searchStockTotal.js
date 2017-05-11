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
						title : '库存列表',		
						url : appUrl + '/stockReport/stockReportAction!searchStockTotalList.jspa',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						rownumbers : true,
						height : 420,
						width : 'auto',
						queryParams : {
							orgId : $("#orgId").val(),
							userType : $("#userType").val()
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
							field : 'ck',
							align : 'center',
							checkbox : true
						},{
							field : 'stockId',
							hidden : true
						},{
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
							field : 'userName',
							title : '提报人',
							width : 80,
							align : 'center'
						}, {
							field : 'userId',
							hidden : true
						}, {
							field : 'skuName',
							title : '品项',
							width : 150,
							align : 'center'
						}, {
							field : 'categoryName',
							title : '品类',
							width : 150,
							align : 'center'
						}, {
							field : 'quantity',
							title : '数量',
							width : 80,
							align : 'center',
							editor : {
								type : 'numberbox',
								options : { precision : 2 }
							}
						}, {
							field : 'unitDesc',
							title : '单位',
							width : 80,
							align : 'center'
						}, {
							field : 'productionDate',
							title : '货龄',
							width : 80,
							align : 'center'
						}, {
							field : 'checkTime',
							title : '库存所属日期',
							width : 80,
							align : 'center'
						}, {
							field : 'createDate',
							title : '提报日期',
							width : 200,
							align : 'center'
						}
//						{
//							field : 'oper',
//							title : '操作',
//							width : 60,
//							align : 'center',
//							formatter : function(value, row, index) {
//								var loginId=$("#loginId").val();
//								if(loginId=="88647" || (loginId==row.userId && $("#state").val()=="1" && 
//										$("#productionDate").val()==row.checkTime)){
//									if(row.editing){ 
//										var s = '<a href="#" onclick="saverow(this)">保存</a>';
//										var c = '<a href="#" onclick="cancelrow(this)">取消</a>';
//										return s+'&nbsp;'+c;
//									}else{
//										return '<img style="cursor:pointer" onclick="editrow(this)" title="修改" src='
//										+ imgUrl
//										+ '/images/actions/action_edit.png align="absMiddle"></img>';
//									}
//								}
//							}
//						}
						]], 
						toolbar : [ "-", {
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
	
}

function getSum(){
//	$.ajax({
//		type : "post",
//		url : appUrl + "/stockReportAction!getStockReportKunnrAndKunagSum.jspa",
//		data : {
//			kunnr : $('#kunnr').combobox("getValue"),
//            productionStartDate : $('#productionStartDate').val(),
//			productionEndDate : $('#productionEndDate').val(),
//			flag : 'kunnr_week'
//		},
//		success : function(data) {
//			$("#boxSum").val(data.flag);
//			$("#kunagName").val(data.kunag);
//		}
//	});
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
		url : appUrl + "/stockReportAction!updateStockReport.jspa",
		data : {
			stockId : rows[0].stockId,
			quantity : rows[0].quantity
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

function add() {
	if($("#state").val()=="1"){
		initMaintWindow('创建库存', '/stockReportAction!toCreateStockReportWeek.jspa?orgId='+$("#orgId").val()+'&&userType='+$("#userType").val(), 500, 500);
	}else{
		$.messager.alert('提示', '不是提报时间！', '提示');
	}
}

function remove() {
	var ids = new Array();
	var rows = $('#datagrid').datagrid('getSelections');
	if($("#state").val()!="1"){
		$.messager.alert('提示', '不是提报时间！', '提示');
		return;
	}
	for ( var i = 0; i < rows.length; i++) {
		ids[i] = rows[i].stockId;
		if($("#loginId").val()!="88647" && ($("#loginId").val()!=rows[i].userId || 
				$("#productionDate").val()!=rows[i].checkTime)){
			$.messager.alert('提示', '请选择正确记录！', '提示');
			return;
		}
	}
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
								var idsJson=ids;
								var form = window.document.forms[0];
								form.action = appUrl
										+ '/stockReportAction!deleteStock.jspa?deleteStockIds='
										+ idsJson;
								form.target = "hideFrame";
								form.submit();
							}
						});
	}

}

function importCsv() {
	html = '<form id="fileForm" method="post" enctype="multipart/form-data" >'
			+ '<table>'
			+ '<tr><td class="head" noWrap>模板下载</td>'
			+ '<td><a style="color:blue" onclick=javascript:exportStockCsv();> 1、下载模版</a></td></tr>'
			+ '<tr><td class="head" noWrap>批量导入</td>'
			+ '<td><input type="file" name="uploadFile" id="uploadFile"  style="width:200px"/>'
			+ '</tb></tr></table></form>';
	importStockCsv('批量导入', html);
}

//csv下载导入模板
function exportStockCsv() {
	$.messager.confirm('Confirm', '是否确定下载模板?', function(r) {
		if (r) {
			var form =document.getElementById("fileForm");
			form.action = appUrl
					+ '/stockReportAction!exportStockCsv.jspa';
			form.submit();
		}
	});

}

//批量导入信息
function importStockCsv(t, html) {
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
													+ "/stockReportAction!importStockCsv.jspa";
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


function search() {
	getSum();
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.startDate = $('#startDate').val();
	queryParams.endDate = $('#endDate').val();
	queryParams.orgId = $('#orgId').val();
	queryParams.kunnr = $('#kunnr').combobox("getValue");
	queryParams.skuId = $('#skuId').combobox("getValue");
	queryParams.categoryId = $('#categoryId').combobox("getValue");
	queryParams.productionStartDate = $('#productionStartDate').val();
	queryParams.productionEndDate = $('#productionEndDate').val();
	$("#datagrid").datagrid('load');
}
function exportForExcel() {
	$.messager.progress();
	openTime();
	var form = window.document.forms[0];
	form.action = appUrl
			+ '/stockReport/stockReportAction!exportForExcelStockTotal.jspa';
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
//		$.messager.alert('Tips', successResult, 'info');
//		$('#datagrid').datagrid('reload');
		$.messager.confirm('Tips', successResult, function(r) {
			if (r) {
				$('#excelDialog').dialog('close');
				search();
			}
		});
	}
}

//创建窗口对象
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

//创建窗口对象
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

function closeMaintWindow() {
	$("#maintWindow").window('close');
}

function choseOrg() {
	initMaintAccount(false, '400', '400','组织选择', '/visitInfo/visitInfoAction!orgTreePage.jspa',353,70);
}
function returnValue(selectedId, selectedName) {
	$("#orgId").val(selectedId);
	$("#orgName").val(selectedName);
	loadCust();
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