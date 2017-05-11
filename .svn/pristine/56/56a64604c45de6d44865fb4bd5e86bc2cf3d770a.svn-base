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
						title : '数据列表',		
						url : appUrl + '/stockReport/stockReportAction!searchKunnrManagerSkuList.jspa',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						rownumbers : true,
						height : 'auto',
						width : 'auto',
						queryParams : {
							orgId : $("#orgId").val()
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
						frozenColumns : [[{
							field : 'ck',
							align : 'center',
							checkbox : true
						},{
							field : 'orgRegion',
							title : '大区',
							width : 30,
							align : 'center'
						}, {
							field : 'orgProvince',
							title : '省份',
							width : 30,
							align : 'center'
						}, {
							field : 'orgCity',
							title : '城市',
							width : 40,
							align : 'center'
						}, {
							field : 'kunnr',
							title : '经销商编号',
							width : 60,
							align : 'center'
						}, {
							field : 'kunnrName',
							title : '经销商',
							width : 100,
							align : 'center'
						}, {
							field : 'skuName',
							title : '经销商',
							width : 100,
							align : 'center'
						}, {
							field : 'categoryName',
							title : '经销商',
							width : 100,
							align : 'center'
						}, {
							field : 'year',
							title : '年',
							width : 40,
							align : 'center'
						}, {
							field : 'month',
							title : '月',
							width : 40,
							align : 'center'
						}]],
						columns :[[{
							title : '目标提报',
							width : 330,
							align : 'center',
							colspan : 3
						}, {
							title : '要货计划',
							width : 320,
							align : 'center',
							colspan : 3
						}, {
							title : '安全库存',
							width : 240,
							align : 'center',
							colspan : 4
						}, {
							title : '预估库存',
							width : 80,
							align : 'center'
						}, {
							title : '同期分销',
							width : 240,
							align : 'center',
							colspan : 3
						}, {
							title : '年累计分销',
							width : 340,
							align : 'center',
							colspan : 3
						}], [{
							field : 'salesPlan',
							title : '当月分销预估(终版)',
							width : 110,
							align : 'center'
						}, {
							field : 'salesPlanNext',
							title : '次月分销预估(初版)',
							width : 110,
							align : 'center'
						}, {
							field : 'salesPlanLast',
							title : '当月分销预估(初版展示)',
							width : 110,
							align : 'center'
						}, {
							field : 'productionPlan',
							title : '当月（终板）',
							width : 80,
							align : 'center'
						}, {
							field : 'productionPlanNext',
							title : '次月（初版）',
							width : 80,
							align : 'center'
						}, {
							field : 'productionPlanLast',
							title : '当月（初版展示）',
							width : 80,
							align : 'center'
						}, {
							field : 'stockSafetyLimit',
							title : '本月上限',
							width : 80,
							align : 'center'
						}, {
							field : 'stockSafety',
							title : '本月下限',
							width : 80,
							align : 'center'
						}, {
							field : 'stockSafetyNextLimit',
							title : '次月上限',
							width : 80,
							align : 'center'
						}, {
							field : 'stockSafetyNext',
							title : '次月下限',
							width : 80,
							align : 'center'
						}, {
							field : 'lastEstimateStock',
							title : '预估上月底库存',
							width : 80,
							align : 'center'
						}, {
							field : 'salesLastYearLast',
							title : '同期上一个月实际分销',
							width : 80,
							align : 'center'
						}, {
							field : 'salesLastYear',
							title : '同期当月实际分销',
							width : 80,
							align : 'center'
						}, {
							field : 'salesLastYearNext',
							title : '同期次月实际分销',
							width : 80,
							align : 'center'
						}, {
							field : 'salesYear',
							title : '年累计分销量',
							width : 80,
							align : 'center'
						}, {
							field : 'salesYearGoalEstimate',
							title : '年累计分销目标(滚动终版目标)',
							width : 80,
							align : 'center'
						}, {
							field : 'salesYearPercent',
							title : '年累计达成%',
							width : 100,
							align : 'center'
						}
//						,{
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
//					}
					]], 
						toolbar : [ "-",{
							text : '批量导出',
							iconCls : 'icon-excel ',
							handler : function() {
								exportForExcel();
							}
						}, "-",{
							text : '<font color="red">单位：自然箱</font>'
						}]
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
					+ '/stockReportAction!exportKunnrManagerCsv.jspa';
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
													+ "/stockReportAction!importKunnrManagerCsv.jspa";
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


function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.startDate = $('#startDate').val();
	queryParams.endDate = $('#endDate').val();
	queryParams.orgId = $('#orgId').val();
	queryParams.kunnr = $('#kunnr').combobox("getValue");
	queryParams.skuId = $('#skuId').combobox("getValue");
	queryParams.categoryId = $('#categoryId').combobox("getValue");
	$("#datagrid").datagrid('load');
}
function exportForExcel() {
	$.messager.progress();
	openTime();
	var form = window.document.forms[0];
	form.action = appUrl
			+ '/stockReport/stockReportAction!exportForExcel.jspa';
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
function clearValue(){
	$('#kunnr').combobox("clear");
	$('#categoryId').combobox("clear");
	$('#skuId').combobox("clear");
	$('#startDate').val("");
	$('#endDate').val("");
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