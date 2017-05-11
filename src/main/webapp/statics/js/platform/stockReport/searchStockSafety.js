$(document).ready(function() {
	loadGrid();
	loadCust();
	$('#hideFrame').bind('load', promgtMsg);
});

var myDate = new Date();
var year = [];
var y = myDate.getFullYear()-2;

for ( var i = 0; i < 5; i++) {
	year.push({
		'txt' : y,
		'value' : y
	});
	y++;
}

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '库存列表',		
						url : appUrl + '/stockReport/stockReportAction!searchStockSafetyList.jspa',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						rownumbers : true,
						pageSize : 20,
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
						frozenColumns :[[ {
							field : 'ck',
							align : 'center',
							checkbox : true
						},{
							field : 'stockId',
							hidden : true
						},{
							field : 'orgId',
							title : '组织编号',
							width : 60,
							align : 'center'
						}, {
							field : 'orgName',
							title : '组织',
							width : 80,
							align : 'center'
						}, {
							field : 'year',
							title : '自然年',
							width : 60,
							align : 'center'
						}]],
						columns : [[{
							title : '1月',
							width : 240,
							align : 'center',
							colspan : 4
						}, {
							title : '2月',
							width : 240,
							align : 'center',
							colspan : 4
						}, {
							title : '3月',
							width : 240,
							align : 'center',
							colspan : 4
						}, {
							title : '4月',
							width : 240,
							align : 'center',
							colspan : 4
						}, {
							title : '5月',
							width : 240,
							align : 'center',
							colspan : 4
						}, {
							title : '6月',
							width : 240,
							align : 'center',
							colspan : 4
						}, {
							title : '7月',
							width : 240,
							align : 'center',
							colspan : 4
						}, {
							title : '8月',
							width : 240,
							align : 'center',
							colspan : 4
						}, {
							title : '9月',
							width : 240,
							align : 'center',
							colspan : 4
						}, {
							title : '10月',
							width : 240,
							align : 'center',
							colspan : 4
						}, {
							title : '11月',
							width : 240,
							align : 'center',
							colspan : 4
						}, {
							title : '12月',
							width : 240,
							align : 'center',
							colspan : 4
						}, 
						{
							field : 'userName',
							title : '最后修改人',
							width : 60,
							align : 'center',
							rowspan : 4
					}],
					[{
						field : 'quantityL1',
						title : '上限天数',
						width : 80,
						align : 'center',
						editor : {
							type : 'numberbox',
							options : { precision : 2 }
						}
					}, {
						field : 'goalL1',
						title : '上限箱数',
						width : 80,
						align : 'center'
					}, {
						field : 'quantity1',
						title : '下限天数',
						width : 80,
						align : 'center',
						editor : {
							type : 'numberbox',
							options : { precision : 2 }
						}
					}, {
						field : 'goal1',
						title : '下限箱数',
						width : 80,
						align : 'center'
					}, {
						field : 'quantityL2',
						title : '上限天数',
						width : 80,
						align : 'center',
						editor : {
							type : 'numberbox',
							options : { precision : 2 }
						}
					}, {
						field : 'goalL2',
						title : '上限箱数',
						width : 80,
						align : 'center'
					}, {
						field : 'quantity2',
						title : '下限天数',
						width : 80,
						align : 'center',
						editor : {
							type : 'numberbox',
							options : { precision : 2 }
						}
					}, {
						field : 'goal2',
						title : '下限箱数',
						width : 80,
						align : 'center'
					}, {
						field : 'quantityL3',
						title : '上限天数',
						width : 80,
						align : 'center',
						editor : {
							type : 'numberbox',
							options : { precision : 2 }
						}
					}, {
						field : 'goalL3',
						title : '上限箱数',
						width : 80,
						align : 'center'
					}, {
						field : 'quantity3',
						title : '下限天数',
						width : 80,
						align : 'center',
						editor : {
							type : 'numberbox',
							options : { precision : 2 }
						}
					}, {
						field : 'goal3',
						title : '下限箱数',
						width : 80,
						align : 'center'
					}, {
						field : 'quantityL4',
						title : '上限天数',
						width : 80,
						align : 'center',
						editor : {
							type : 'numberbox',
							options : { precision : 2 }
						}
					}, {
						field : 'goalL4',
						title : '上限箱数',
						width : 80,
						align : 'center'
					}, {
						field : 'quantity4',
						title : '下限天数',
						width : 80,
						align : 'center',
						editor : {
							type : 'numberbox',
							options : { precision : 2 }
						}
					}, {
						field : 'goal4',
						title : '下限箱数',
						width : 80,
						align : 'center'
					}, {
						field : 'quantityL5',
						title : '上限天数',
						width : 80,
						align : 'center',
						editor : {
							type : 'numberbox',
							options : { precision : 2 }
						}
					}, {
						field : 'goalL5',
						title : '上限箱数',
						width : 80,
						align : 'center'
					}, {
						field : 'quantity5',
						title : '下限天数',
						width : 80,
						align : 'center',
						editor : {
							type : 'numberbox',
							options : { precision : 2 }
						}
					}, {
						field : 'goal5',
						title : '下限箱数',
						width : 80,
						align : 'center'
					}, {
						field : 'quantityL6',
						title : '上限天数',
						width : 80,
						align : 'center',
						editor : {
							type : 'numberbox',
							options : { precision : 2 }
						}
					}, {
						field : 'goalL6',
						title : '上限箱数',
						width : 80,
						align : 'center'
					}, {
						field : 'quantity6',
						title : '下限天数',
						width : 80,
						align : 'center',
						editor : {
							type : 'numberbox',
							options : { precision : 2 }
						}
					}, {
						field : 'goal6',
						title : '下限箱数',
						width : 80,
						align : 'center'
					}, {
						field : 'quantityL7',
						title : '上限天数',
						width : 80,
						align : 'center',
						editor : {
							type : 'numberbox',
							options : { precision : 2 }
						}
					}, {
						field : 'goalL7',
						title : '上限箱数',
						width : 80,
						align : 'center'
					}, {
						field : 'quantity7',
						title : '下限天数',
						width : 80,
						align : 'center',
						editor : {
							type : 'numberbox',
							options : { precision : 2 }
						}
					}, {
						field : 'goal7',
						title : '下限箱数',
						width : 80,
						align : 'center'
					}, {
						field : 'quantityL8',
						title : '上限天数',
						width : 80,
						align : 'center',
						editor : {
							type : 'numberbox',
							options : { precision : 2 }
						}
					}, {
						field : 'goalL8',
						title : '上限箱数',
						width : 80,
						align : 'center'
					}, {
						field : 'quantity8',
						title : '下限天数',
						width : 80,
						align : 'center',
						editor : {
							type : 'numberbox',
							options : { precision : 2 }
						}
					}, {
						field : 'goal8',
						title : '下限箱数',
						width : 80,
						align : 'center'
					}, {
						field : 'quantityL9',
						title : '上限天数',
						width : 80,
						align : 'center',
						editor : {
							type : 'numberbox',
							options : { precision : 2 }
						}
					}, {
						field : 'goalL9',
						title : '上限箱数',
						width : 80,
						align : 'center'
					}, {
						field : 'quantity9',
						title : '下限天数',
						width : 80,
						align : 'center',
						editor : {
							type : 'numberbox',
							options : { precision : 2 }
						}
					}, {
						field : 'goal9',
						title : '下限箱数',
						width : 80,
						align : 'center'
					}, {
						field : 'quantityL10',
						title : '上限天数',
						width : 80,
						align : 'center',
						editor : {
							type : 'numberbox',
							options : { precision : 2 }
						}
					}, {
						field : 'goalL10',
						title : '上限箱数',
						width : 80,
						align : 'center'
					}, {
						field : 'quantity10',
						title : '下限天数',
						width : 80,
						align : 'center',
						editor : {
							type : 'numberbox',
							options : { precision : 2 }
						}
					}, {
						field : 'goal10',
						title : '下限箱数',
						width : 80,
						align : 'center'
					}, {
						field : 'quantityL11',
						title : '上限天数',
						width : 80,
						align : 'center',
						editor : {
							type : 'numberbox',
							options : { precision : 2 }
						}
					}, {
						field : 'goalL11',
						title : '上限箱数',
						width : 80,
						align : 'center'
					}, {
						field : 'quantity11',
						title : '下限天数',
						width : 80,
						align : 'center',
						editor : {
							type : 'numberbox',
							options : { precision : 2 }
						}
					}, {
						field : 'goal11',
						title : '下限箱数',
						width : 80,
						align : 'center'
					}, {
						field : 'quantityL12',
						title : '上限天数',
						width : 80,
						align : 'center',
						editor : {
							type : 'numberbox',
							options : { precision : 2 }
						}
					}, {
						field : 'goalL12',
						title : '上限箱数',
						width : 80,
						align : 'center'
					}, {
						field : 'quantity12',
						title : '下限天数',
						width : 80,
						align : 'center',
						editor : {
							type : 'numberbox',
							options : { precision : 2 }
						}
					}, {
							field : 'goal12',
							title : '下限箱数',
							width : 80,
							align : 'center'
						}]], 
						toolbar : [ "-",{
							text : '省区编号对照表',
							iconCls : 'icon-excel',
							handler : function() {
								exportForExcel(); 
							}
						},"-",{
							text : '批量导入',
							iconCls : 'icon-add',
							handler : function() {
								importCsv(); 
							}
						},"-",{
							text : '批量删除',
							iconCls : 'icon-remove',
							handler : function() {
								remove(); 
							}
						}, "-",{
							text : '批量修改',
							iconCls : 'icon-save',
							handler : function() {
								editeBegin();
							}
						}, "-",{
							text : '保存',
							iconCls : 'icon-save',
							handler : function() {
								editeEnd();
							}
						}, "-",{
							text : '提交',
							iconCls : 'icon-save',
							handler : function() {
								editeEnd();
							}
						}, "-",{
							text : '<font color="red">单位：标箱</font>'
						}, "-",{
							text : '<font color="green">安全库存原则：1、上限必须>下限>=0；2、支持下限为0，及上下限均为0，不支持上限为0</font>'
						}]
					});
	// 分页控件
	var p = $('#datagrid').datagrid('getPager');
	$(p).pagination({
		pageSize : 20,
		pageList : [ 10, 20, 30 ],
		beforePageText : '第',
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
	
//	$('#skuId').combogrid(
//			{
//				panelHeight :250,
//				panelWidth : 380,
//				pagination : true,
//				method : 'post',
//				singleSelect : false,
//				multiple : false,
//				url : appUrl
//						+ '/stockReportAction!searchSku.jspa',
//				idField : 'skuId',
//				textField : 'skuName',
//				columns : [
////				           [ { field : 'ck', checkbox : true } ],
//				           [ {
//					field : 'skuId',
//					title : '品项编号',
//					width : 60
//				}, {
//					field : 'skuName',
//					title : '品项名称',
//					width : 200
//				} ] ],
//				toolbar : '#toolbarSku'
//			});
	
//	$('#categoryId').combogrid(
//			{
//				panelHeight :250,
//				panelWidth : 380,
//				pagination : true,
//				method : 'post',
//				singleSelect : false,
//				multiple : false,
//				url : appUrl
//						+ '/stockReportAction!searchCategory.jspa',
//				idField : 'categoryId',
//				textField : 'categoryName',
//				columns : [
////				           [ { field : 'ck', checkbox : true } ],
//				           [ {
//					field : 'categoryId',
//					title : '品类编号',
//					width : 60
//				}, {
//					field : 'categoryName',
//					title : '品类名称',
//					width : 200
//				} ] ],
//				toolbar : '#toolbarCategory'
//			});
	
}

function loadCust(){
//	$('#kunnr').combogrid({
//		panelHeight : 250,
//		panelWidth : 380,
//		pagination : true,
//		method : 'post',
//		singleSelect : true,
//		multiple : false,
//		url : appUrl + '/kunnrAction!kunnrSearch.jspa?orgId='+$('#orgId').val()+'&bhxjFlag='+'C',
//		idField : 'kunnr',
//		textField : 'name1',
//		// multiple:true,
//		columns : [ 
//					 [ {
//			field : 'kunnr',
//			title : '经销商编号',
//			width : 120
//		}, {
//			field : 'name1',
//			title : '名称',
//			width : 200
//		} ] ],
//		toolbar : '#toolbarKonzs'
//	});
	
	$("#year").combobox({
		data : year,
		valueField : 'txt',
		textField : 'value',
		editable : false
	});
	
}

function updateActions(index) {
	$('#datagrid').datagrid('updateRow', {
		index : index,
		row : {}
	});
}

var indexList = new Array();
var indexNum = 0;

function editeBegin(){
	$('#datagrid').datagrid({
			onDblClickRow : function(rowIndex,field,value){
				if(field.orgId!=null){
					editrow(rowIndex);
					indexList[indexNum] = rowIndex;
					indexNum++;
				}
			}
	});
}

function editeEnd(){
	for(i in indexList){
		$('#datagrid').datagrid('endEdit', indexList[i]);
	}
	var rows = $('#datagrid').propertygrid('getChanges');
	if(rows.length==0){
		$.messager.alert('Tips', '无已修改数据！', 'warning');
		return;
	}
	var update = new Object();
	update = JSON.stringify(rows);
	$.ajax({
		type : "post",
		url : appUrl + "/stockReportAction!updateStockSafety.jspa",
		data : {
			update : encodeURIComponent(update)
		},
		success : function(executeResult) {
			if(executeResult == ""){
				$.messager.alert('Tips','修改成功！','info',function(){
					search();
				});
			}else{
				$.messager.alert('Tips', executeResult, 'warning', function(){
					search();
				});
			}
		}
	});
}

function getRowIndex(target) {
	var tr = $(target).closest('tr.datagrid-row');
	return parseInt(tr.attr('datagrid-row-index'));
}

function editrow(index) {
	$('#datagrid').datagrid('beginEdit', index);
}

function remove() {
	var ids = new Array();
	var rows = $('#datagrid').datagrid('getSelections');
	var update = new Object();
	update = JSON.stringify(rows);
	if(rows.length==0){
		$.messager.alert('提示', '未选中任何权限点！', '提示');
		return;
	} else {
		$.messager
				.confirm(
						'Confirm',
						'是否确认批量删除?',
						function(r) {
							if (r) {
								$.ajax({
									type : "post",
									url : appUrl + "/stockReportAction!deleteStockSafety.jspa",
									async : false,
									data : {
										update : encodeURIComponent(update)
									},
									success : function(executeResult) {
										if(1 == executeResult){
											$.messager.alert('Tips','删除成功！','info',function(){
												search();
											});
										}else{
											$.messager.alert('Tips', '删除失败，请联系管理员！', 'warning', function(){
												search();
											});
										}
									}
								});
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
					+ '/stockReportAction!exportStockSafetyCsv.jspa';
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
													+ "/stockReportAction!importStockSafetyCsv.jspa";
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
	queryParams.orgId = $('#orgId').val();
	queryParams.year = $('#year').combobox("getValue");
	$("#datagrid").datagrid('load');
}
function exportForExcel() {
	$.messager.progress();
	openTime();
	var form = window.document.forms[0];
	form.action = appUrl
			+ '/stockReport/stockReportAction!exportForExcelOrgProvince.jspa';
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

function clearValue(){
	$('#categoryId').combobox('clear');
	$('#year').combobox('clear');
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
		editeEnd();
		return false;
	}
	return true;
};