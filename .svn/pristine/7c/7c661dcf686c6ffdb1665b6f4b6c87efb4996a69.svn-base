$(document).ready(function() {
	loadGrid();
	loadCust();
	getSum();
	
	divHide($('#goalMark').combobox('getValue'));
	$('#goalMark').combobox({
		editable : false,
		onSelect : function(d) {
			$('#goalMark').val(d.value);
			divHide(d.value);
			search();
		}
	});
	
	$('#hideFrame').bind('load', promgtMsg);
});

function getSum(){
	$.ajax({
		type : "post",
		url : appUrl + "/stockReportAction!searchSalesGoalCGDetailListSum.jspa",
		data : {
			goalMark : $('#goalMark').val(),
			startDate : $('#startDate').val(),
		    endDate : $('#endDate').val(),
		    flag : $('#flag').combobox("getValue"),
			orgId : $('#orgId').val(),
			brand :$('#brand').combobox("getValue"),
			kunnr : $('#kunnr').combobox("getValue"),
			matterNum : $('#categoryId').combobox("getValue")
		},
		success : function(data) {
			$("#boxSum").val(data);
		}
	});
}

function divHide(value){
	if('N'==value){
		$('#datagrid').datagrid('hideColumn','kunnrName');
		$('#datagrid').datagrid('showColumn','boxNumD');
	}else{
		$('#datagrid').datagrid('showColumn','kunnrName');
		$('#datagrid').datagrid('hideColumn','boxNumD');
	}
}

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '分销目标',		
						url : appUrl + '/stockReport/stockReportAction!searchSalesGoalCGDetailList.jspa',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						rownumbers : true,
						height : 420,
						width : 'auto',
						queryParams : {
							goalMark : $('#goalMark').combobox('getValue')
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
							field : 'kunnr',
							hidden : true
						},{
							field : 'matterNum',
							hidden : true
						},{
							field : 'orgId',
							hidden : true
						},{
							field : 'goalMark',
							hidden : true
						}, {
							field : 'orgName',
							title : '组织',
							width : 80,
							align : 'center'
						}, {
							field : 'kunnrName',
							title : '经销商',
							width : 150,
							align : 'center',
							formatter : function(value) {
								if (value == null) {
									return '待开';
								} else {
									return value;
								}
							}
						}, {
							field : 'goalYear',
							title : '目标年',
							width : 80,
							align : 'center'
						}, {
							field : 'goalMonth',
							title : '目标月',
							width : 80,
							align : 'center'
						}, {
							field : 'brand',
							title : '品牌',
							width : 80,
							align : 'center'
						}, {
							field : 'matterName',
							title : '品项or四级科目(SKU)名称',
							width : 150,
							align : 'center'
						}, {
							field : 'boxNum',
							title : '箱数',
							width : 80,
							align : 'center',
							editor : {
								type : 'numberbox',
								options : { precision : 2 }
							}
						}, {
							field : 'boxNumD',
							title : '待开箱数',
							width : 80,
							align : 'center'
						}, {
							field : 'userId',
							hidden : true
						}, {
							field : 'userName',
							title : '提报人',
							width : 80,
							align : 'center'
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
//								if(row.editing){ 
//									var s = '<a href="#" onclick="saverow(this)">保存</a>';
//									var c = '<a href="#" onclick="cancelrow(this)">取消</a>';
//									return s+'&nbsp;'+c;
//								}else{
//									return '<img style="cursor:pointer" onclick="editrow(this)" title="修改" src='
//									+ imgUrl
//									+ '/images/actions/action_edit.png align="absMiddle"></img>';
//								}
//							}
//					    }
						]],
					toolbar : [ "-", {
						text : '导入分销目标',
						iconCls : 'icon-excel',
						handler : function() {
							importCsv();
						}
					}, "-", {
						text : '批量导出',
						iconCls : 'icon-excel',
						handler : function() {
							exportForExcel();
						}
					}, "-", {
						text : '批量删除',
						iconCls : 'icon-remove',
						handler : function() {
							remove();
						}
					}, "-",
					{
						text : '<font color="red">箱数单位均为标箱</font>'
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
	
	$('#categoryId').combogrid(
			{
				panelHeight :250,
				panelWidth : 380,
				pagination : true,
				method : 'post',
				singleSelect : false,
				multiple : false,
				url : appUrl + '/goal/goalAction!getMaterielList.jspa',
				idField : 'mvgr1',
				textField : 'bezei',
				columns : [ [ {
					field : 'ck',
					checkbox : true,
					hidden : true
				}, {
					field : 'mvgr1',
					title : '物料组',
					align : 'center',
					width : 120
				}, {
					field : 'bezei',
					title : '物料组描述',
					align : 'center',
					width : 200
				} ] ],
				toolbar : '#toolbarCategory'
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
		url : appUrl + '/kunnrAction!kunnrSearch.jspa',
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
		url : appUrl + "/stockReportAction!updateGoalCGDetail.jspa",
		data : {
			orgId : rows[0].orgId,
			year : rows[0].goalYear,
			month : rows[0].goalMonth,
			kunnr : rows[0].kunnr,
			matterNum : rows[0].matterNum,
			quantity : rows[0].boxNum,
			goalMark : $('#goalMark').val()
		},
		success : function(executeResult) {
			search();
		}
	});
}

function add() {
	if($("#state").val()=="1"){
		initMaintWindow('创建库存', '/stockReportAction!toCreateStockReportMonth.jspa?orgId='+$("#orgId").val(), 500, 500);
	}else{
		$.messager.alert('提示', '不是提报时间！', '提示');
	}
}
/**
 * 批量删除
 */
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
									url : appUrl + "/stockReportAction!deleteGoalCGDetail.jspa",
									async : false,
									data : {
										update : encodeURIComponent(update)
									},
									success : function(executeResult) {
										if(executeResult >= 1){
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
/**
 * 批量导入
 */
function importCsv() {
	html = '<form id="fileForm" method="post" enctype="multipart/form-data" >'
			+ '<table>'
			+ '<tr><td class="head" noWrap>模板下载</td>'
			+ '<td><a style="color:blue" onclick=javascript:exportGoalCsv();> 1、下载模版</a></td></tr>'
			+ '<tr><td class="head" noWrap>批量导入</td>'
			+ '<td><input type="file" name="uploadFile" id="uploadFile"  style="width:200px"/>'
			+ '</tb></tr>' + '<tr><td class="head" noWrap>导入类型</td>'
			+ '<td><select id="goalMark" name="goalMark" style="width:130px">'
			+ '<option value="N">--组织目标--</option>'
			+ '<option value="Y" selected>--经销商目标--</option>' + '</select>'
			+ '</td></tr><table></form>';
	importGoalCsv('批量导入', html);
}

//csv下载终端门店导入模板
function exportGoalCsv() {
	$.messager.confirm('Confirm', '是否确定下载模板?', function(r) {
		if (r) {
			var form =document.getElementById("fileForm");
			form.action = appUrl
					+ '/stockReportAction!exportGoalCGCsv.jspa';
			form.submit();
		}
	});

}
//批量导入
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
										var file = document
												.getElementById('uploadFile').value;
										if (/^.+\.(csv|CSV)$/.test(file)) {
											checkPercent();
											var form = document
													.getElementById('fileForm');
											form.action = appUrl
													+ "/stockReportAction!importSalesGoalCGCsv.jspa?goalType=A1";
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

//批量修改

function importForUpdateCsv() {
	html = '<form id="fileForm" method="post" enctype="multipart/form-data" >'
			+ '<table>'
			+ '<tr><td class="head" noWrap>批量修改</td>'
			+ '<td><input type="file" name="uploadFile" id="uploadFile"  style="width:200px"/>'
			+ '</tb></tr></table></form>';
	importGoalForUpdateCsv('批量修改', html);
}

function importGoalForUpdateCsv(t, html) {
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
													+ "/stockReportAction!importSalesGoalCGCsvForUpdate.jspa?goalType=A1";
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

function searcherCategory(val) {
	var queryParams = $('#categoryId').combogrid("grid").datagrid('options').queryParams;
	queryParams.value = encodeURIComponent(val);
	$('#categoryId').combogrid("grid").datagrid('reload');
}


function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.startDate = $('#startDate').val();
	queryParams.endDate = $('#endDate').val();
	queryParams.orgId = $('#orgId').val();
	queryParams.kunnr = $('#kunnr').combobox("getValue");
	queryParams.matterNum = $('#categoryId').combobox("getValue");
	queryParams.goalMark = $('#goalMark').combobox("getValue");
	queryParams.flag = $('#flag').combobox("getValue");
	queryParams.brand = $('#brand').combobox("getValue");
	$("#datagrid").datagrid('load');
	getSum();
}
function exportForExcel() {
	$.messager.progress();
	openTime();
	var form = window.document.forms[0];
	form.action = appUrl
			+ '/stockReport/stockReportAction!exportForExcelSalesGoalCG.jspa';
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
	$('#percentDialog').window('close');
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.confirm('Tips', successResult, function(r) {
			if (r) {
				$('#excelDialog').dialog('close');
				$('#datagrid').datagrid('reload');
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

function clearValue(){
	$('#kunnr').combogrid("clear");
	$('#categoryId').combogrid("clear");
	$('#orgId').val("");
	$('#orgName').val("");
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

function checkPercent() { //百分比进度条
	createProgressbar();
	$('#p').progressbar({
		value:0
	});
	setTimeout(function() {
		var timer = setInterval(function() {
			$.ajax({
				type : "post",
				url : appUrl + "/stockReport/stockReportAction!checkImportPercent.jspa",
				success : function(data) {
					if(data=='99'){
						$('#p').progressbar('setValue', data); 
						clearInterval(timer);
					}else if(data!=undefined && data != '') {
						$('#p').progressbar('setValue', data); 
					}
				}
			});
		}, 100);
	}, 500);
}

function createProgressbar(){
	if ($('#percentDialog').length < 1) {
		if ($('#percentDialog').length < 1) {
			$('<div/>',
					{
						id : 'percentDialog',
						html : 
							 '<div id="p" style="width:400px;left:50%;top:50%"></div>'
					}).appendTo('body');
		}
	}
	$('#percentDialog')
	.window(
			{
				title : "",
				modal : true,
				resizable : false,
				draggable : false,
				closable : false,
				collapsible : false,
				minimizable : false,
				maximizable : false,
				open : function() {
					$('#percentDialog').css('padding', '0.8em');
					$('#percentDialog .ui-accordion-content').css(
							'padding', '0.4em').height(
									$('#percentDialog').height() - 100);
				},
				width : 414,
				height : 35
			});
}