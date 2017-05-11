var processInstanceId;
$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});
function loadGrid() {
	var toolbar = $('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '查询结果',
						url : appUrl
								+ '/distributionDataRepMonAction!getDistributionDataRepMonList.jspa',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						height : height,
						striped : true,
						columns : [ [
								{
									field : 'ck',
									checkbox : true,
									rowspan : 2
								},
								{
									field : 'dgId',
									title : 'ID',
									width : setColumnWidth(0.04),
									align : 'center',
									hidden : true,
									rowspan : 2
								},
								
								{
									field : 'orgRegion',
									title : '大区',
									width : setColumnWidth(0.06),
									align : 'center',
									rowspan : 2
								},
								{
									field : 'orgProvince',
									title : '省份',
									width : setColumnWidth(0.06),
									align : 'center',
									rowspan : 2
								},
								{
									field : 'orgCity',
									title : '城市',
									width : setColumnWidth(0.06),
									align : 'center',
									rowspan : 2
								},
								{
									field : 'kunnrId',
									title : '经销商编号',
									width : setColumnWidth(0.06),
									align : 'center',
									sortable : true,
									rowspan : 2
								},
								{
									field : 'kunnrName',
									title : '经销商名称',
									width : setColumnWidth(0.18),
									align : 'center',
									rowspan : 2

								},
								{
									field : 'startDate',
									title : '开始日期',
									widtg : setColumnWidth(0.06),
									align : 'center',
									rowspan : 2

								},
								{
									field : 'endDate',
									title : '结束日期',
									widtg : setColumnWidth(0.06),
									align : 'center',
									rowspan : 2

								},
								{
									field : 'inputDate',
									title : '统计月份',
									widtg : setColumnWidth(0.06),
									align : 'center',
									rowspan : 2

								},
								{
									field : 'firstUser',
									title : '城市经理',
									widtg : setColumnWidth(0.06),
									align : 'center',
									rowspan : 2

								},
								{
									field : 'secondUser',
									title : '省级经理',
									widtg : setColumnWidth(0.06),
									align : 'center',
									rowspan : 2

								},
								{
									field : 'thirdUser',
									title : '大区经理',
									widtg : setColumnWidth(0.06),
									align : 'center',
									rowspan : 2

								},
								{
									title : '城市经理分销数据',
									colspan : 18
								},
								{
									title : '城市经理目标量数据',
									colspan : 18
								},
								
								{
									field : 'itemJLTotal',
									title : '达成合计标箱（除去功夫奶茶）',
									width : setColumnWidth(0.15),
									align : 'center',
									editor : 'numberbox',
									rowspan : 2
								},
								{
									field : 'itemGoalTotal',
									title : '目标合计标箱（除去功夫奶茶）',
									width : setColumnWidth(0.15),
									align : 'center',
									editor : 'numberbox',
									rowspan : 2
								},
								
								{
									field : 'dcl',
									title : '目标达成率（除去功夫奶茶）',
									width : setColumnWidth(0.21),
									align : 'center',
									editor : 'numberbox',
									rowspan : 2
								}],
						           
								[{
									field : 'aOne',
									title : 'A1椰果单杯-80g*30',
									width : setColumnWidth(0.11),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aThree',
									title : 'A3椰果经典装-80g*30',
									width : setColumnWidth(0.12),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'bOne',
									title : 'B1红豆单杯-64g*30',
									width : setColumnWidth(0.11),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'cOne',
									title : 'C1桂圆红枣单杯-65g*30',
									width : setColumnWidth(0.13),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aFour',
									title : 'A4椰果礼盒装-80g*12*8',
									width : setColumnWidth(0.13),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aFive',
									title : 'A5椰果礼盒装-80g*8*10',
									width : setColumnWidth(0.13),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aSix',
									title : 'A6椰果家庭分享装-80g*16*6',
									width : setColumnWidth(0.15),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'cSix',
									title : 'C6桂圆红枣家庭分享装-65g*16*6',
									width : setColumnWidth(0.18),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aSeven',
									title : 'A7椰果家庭分享装-80g*15*6',
									width : setColumnWidth(0.15),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'cSeven',
									title : 'C7桂圆红枣家庭分享装-65g*15*6',
									width : setColumnWidth(0.18),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aTwo',
									title : 'A2椰果联杯装-80g*6*6',
									width : setColumnWidth(0.12),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aEight',
									title : 'A8椰果联杯装-80g*3*10',
									width : setColumnWidth(0.13),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'bEight',
									title : 'B8红豆联杯装-80g*3*10',
									width : setColumnWidth(0.13),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'cEight',
									title : 'C8桂圆红枣联杯装-65g*3*10',
									width : setColumnWidth(0.15),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'hSeven',
									title : 'H7功夫奶茶单瓶-400ml*15',
									width : setColumnWidth(0.14),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'dNine',
									title : 'D9超值组合装',
									width : setColumnWidth(0.09),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'dTen',
									title : 'D10豪华礼盒装-15*6',
									width : setColumnWidth(0.11),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'eOne',
									title : 'E1豪华礼盒装-12*6',
									width : setColumnWidth(0.15),
									align : 'center',
									editor : 'numberbox'
								},
								
								{
									field : 'aOneM',
									title : 'A1椰果单杯-80g*30',
									width : setColumnWidth(0.11),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aThreeM',
									title : 'A3椰果经典装-80g*30',
									width : setColumnWidth(0.12),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'bOneM',
									title : 'B1红豆单杯-64g*30',
									width : setColumnWidth(0.11),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'cOneM',
									title : 'C1桂圆红枣单杯-65g*30',
									width : setColumnWidth(0.13),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aFourM',
									title : 'A4椰果礼盒装-80g*12*8',
									width : setColumnWidth(0.13),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aFiveM',
									title : 'A5椰果礼盒装-80g*8*10',
									width : setColumnWidth(0.13),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aSixM',
									title : 'A6椰果家庭分享装-80g*16*6',
									width : setColumnWidth(0.15),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'cSixM',
									title : 'C6桂圆红枣家庭分享装-65g*16*6',
									width : setColumnWidth(0.18),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aSevenM',
									title : 'A7椰果家庭分享装-80g*15*6',
									width : setColumnWidth(0.15),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'cSevenM',
									title : 'C7桂圆红枣家庭分享装-65g*15*6',
									width : setColumnWidth(0.18),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aTwoM',
									title : 'A2椰果联杯装-80g*6*6',
									width : setColumnWidth(0.12),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aEightM',
									title : 'A8椰果联杯装-80g*3*10',
									width : setColumnWidth(0.13),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'bEightM',
									title : 'B8红豆联杯装-80g*3*10',
									width : setColumnWidth(0.13),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'cEightM',
									title : 'C8桂圆红枣联杯装-65g*3*10',
									width : setColumnWidth(0.15),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'hSevenM',
									title : 'H7功夫奶茶单瓶-400ml*15',
									width : setColumnWidth(0.14),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'dNineM',
									title : 'D9超值组合装',
									width : setColumnWidth(0.09),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'dTenM',
									title : 'D10豪华礼盒装-15*6',
									width : setColumnWidth(0.11),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'eOneM',
									title : 'E1豪华礼盒装-12*6',
									width : setColumnWidth(0.15),
									align : 'center',
									editor : 'numberbox'
								} ] ],
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
						toolbar : [
								"-",
								{
									text : '数据导出',
									iconCls : 'icon-download',
									handler : function() {
										exportDistributionDataRepMon();
									}
								},
								"-",
								{
									text : '显示的是标箱',
									handler : function() {
									}
								}, "-" ]
					});
	// 分页控件
	var p = $('#datagrid').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 50, 100 ],
		beforePageText : '第',
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}

// excel导出
function exportDistributionDataRepMon() {
	var form = window.document.forms[0];
	form.action = appUrl
			+ '/distributionDataRepMonAction!exportDistributionDataRepMon.jspa';
	form.submit();
	$.messager.alert('Tips', '正在导出,请稍候!', 'warning');
}

function closeDtPlan() {
	$("#MaintDistributionDataRepMon").window('close');
}

function initMaintDistributionDataRepMon(title, url, WWidth, WHeight) {
	var url = appUrl + url;
	var $win = $("#MaintDistributionDataRepMon")
			.window(
					{
						title : title,
						width : WWidth,
						height : WHeight,
						content : '<iframe frameborder="no" width="100%" height="100%" src='
								+ url + '/>',
						shadow : true,
						modal : true,
						closed : true,
						closable : true,
						minimizable : true,
						maximizable : false,
						collapsible : true,
						draggable : true
					});
	$win.window('open');
}

function search() {
	$("#datagrid").datagrid('reload', {
		orgId : $('#orgId').val(),
		kunnrId : $('#kunnrId').val(),
		orgName : encodeURIComponent($('#orgName').val()),
		kunnrName : encodeURIComponent($("#kunnrName").val()),
		startDate : encodeURIComponent(document.getElementsByName("startDate")[0].value),
		endDate : encodeURIComponent(document.getElementsByName('endDate')[0].value)
	});
}

function updateDistributionDataRepMon(ctId) {//
	var ctId = encodeURIComponent(ctId);
	var mark = encodeURIComponent(mark);
	initMaintData('目标数修改', '/goalAction!updateDataPrepare.jspa?ctId=' + ctId
			+ '&mark' + mark, 490, 400);
}

function importDistributionDataRepMon() {
	html = '<form id="fileForm" method="post" enctype="multipart/form-data" >'
			+ '&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'
			+ '<table><tr>'
			+ '<td class="head" noWrap>选择文件上传：</td><td>'
			+ '<input type="file" name="uploadFile" id="uploadFile"  style="width:200px"/>'
			+ '</tb></tr>' + '<table></form>';
	openImportDialog('导入目标', html);
}
var mask_;
/* 打开导入项目Excel对话框 */
function openImportDialog(t, html) {
	if ($('#excelDialog').length < 1) {
		$(
				'<div/>',
				{
					id : 'excelDialog',
					title : '批量导入',
					html : "<div id='import'>"
							+ "</br>"
							+ "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
							+ html + "</div>" + "</div>"
				}).appendTo('body');
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
											$.messager.progress();
											var action = '';
											action = appUrl
													+ "/distributionDataRepMon/distributionDataRepMonAction!importDistributionDataRepMonCsv.jspa";
											var form = document
													.getElementById('fileForm');

											form.action = action;
											// "eventId="+processInstanceId;
											form.target = "hideFrame";
											form.submit();
										} else {
											$.messager.alert("提示", "请导入csv文件");
										}

									}
								},
								{
									text : '取消',
									handler : function() {
										window.location.href = window.location.href;
										$('#excelDialog').dialog('close');

									}
								} ],

						width : document.documentElement.clientWidth * 0.28,
						height : document.documentElement.clientHeight * 0.50
					});
}

/* 对用户导入项目Excel进行检查 */
function importCheck() {
	var file = document.getElementById('projectImportFile').value;
	if (/^.+\.(xls|xlsx)$/.test(file)) {
		document.getElementById('fileForm').submit();
		$('#import').dialog('close');
		mask_ = new mask({
			tip : '请等待导入完成...'
		});
		mask_.show();
		window.setTimeout(getImportStateInfo, 2000);
	} else {
		$.messager.alert("提示", "请导入Excel文件");
	}
}
function importContractMould() {
	html = '<form id="fileForm" method="post" enctype="multipart/form-data" >'
			+ '&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'
			+ '<table><tr>'
			+ '<td class="head" noWrap>批量导入</td>'
			+ '<td><input type="hidden" id="flag" value="add"/><input type="hidden" name="type" id="type" value="Y"  style="width:200px"/>'
			+ '<input type="file" name="uploadFile" id="uploadFile"  style="width:200px"/>'
			+ '</tb></tr><table></form>';
	openImportDialog('导入目标', html);
}
function selectOrgTree() {
	initMaintDistributionDataRepMon('选择组织',
			'/distributionDataRepMonAction!orgTreePage.jspa', 400, 460);
}
function returnValue(selectedId, selectedName) {
	document.getElementById('orgId').value = selectedId;
	document.getElementById('orgName').value = selectedName;
}

function closeOrgTree() {
	closeDtPlan();
}
function importMatterModel() {
	var form = window.document.forms[0];
	form.action = appUrl
			+ '/distributionDataRepMon/distributionDataRepMonAction!importDistributionDataRepMonModel.jspa';
	form.target = "hideFrame";
	form.submit();
}

function deleteDistributionDataRepMon() {
	var rows = $('#datagrid').datagrid('getSelections');
	console.info(rows);
	if (rows == null || rows.length == 0) {
		$.messager.alert('Tips', '选中菜单栏无数据!');
		return;
	}
	$.messager.progress();
	var ids = [];
	for ( var i = 0; i < rows.length; i++) {

		ids.push(rows[i].dgId);

	}
	$("#ids").val(ids);
	var form = window.document.forms[0];
	form.action = "distributionDataRepMonAction!deleteDistributionDataRepMon.jspa";
	form.target = "hideFrame";
	form.submit();

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
function updaterow(target) {
	$('#datagrid').datagrid('beginEdit', getRowIndex(target));
}
function saverow(target) {
	$('#datagrid').datagrid('endEdit', getRowIndex(target));
	var rowss = $('#datagrid').propertygrid('getChanges');

	var dgId = rowss[0].dgId;
	var aOne = rowss[0].aOne;
	var aTwo = rowss[0].aTwo;
	var aThree = rowss[0].aThree;
	var aFour = rowss[0].aFour;
	var aFive = rowss[0].aFive;
	var aSix = rowss[0].aSix;
	var aSeven = rowss[0].aSeven;
	var aEight = rowss[0].aEight;
	var bOne = rowss[0].bOne;
	var bSix = rowss[0].bSix;
	var bEight = rowss[0].bEight;
	var cOne = rowss[0].cOne;
	var cSix = rowss[0].cSix;
	var cSeven = rowss[0].cSeven;
	var cEight = rowss[0].cEight;
	var dNine = rowss[0].dNine;
	var dTen = rowss[0].dTen;
	var eOne = rowss[0].eOne;
	var hSeven = rowss[0].hSeven;

	$("#dgId").val(dgId);
	$("#aOne").val(aOne);
	$("#aTwo").val(aTwo);
	$("#aThree").val(aThree);
	$("#aFour").val(aFour);
	$("#aFive").val(aFive);
	$("#aSix").val(aSix);
	$("#aSeven").val(aSeven);
	$("#aEight").val(aEight);
	$("#bOne").val(bOne);
	$("#bSix").val(bSix);
	$("#bEight").val(bEight);
	$("#cOne").val(cOne);
	$("#cSix").val(cSix);
	$("#cSeven").val(cSeven);
	$("#cEight").val(cEight);
	$("#dNine").val(dNine);
	$("#dTen").val(dTen);
	$("#eOne").val(eOne);
	$("#hSeven").val(hSeven);

	var form = window.document.forms[0];
	form.action = "distributionDataRepMonAction!updateDistributionDataRepMon.jspa";
	form.target = "hideFrame";
	form.submit();
}
function cancelrow(target) {
	$('#datagrid').datagrid('cancelEdit', getRowIndex(target));
}

function exportMould() {
	var form = window.document.forms[0];
	form.action = appUrl
			+ '/distributionDataRepMon/distributionDataRepMonAction!exportMouldCsv.jspa';
	form.target = "hideFrame";
	form.submit();
}

function clear() {
	$('#kunnrId').val("");
	$('#kunnrName').val("");
	$("#startDate").datebox('setValue', '');
	$("#endDate").datebox('setValue', '');
	$("#orgId").val("");
	$("#orgName").val("");
	$("#trFlag").combobox('setValue', '');
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}
function getWidth(percent) {
	if (jQuery.browser.safari) {
		return document.body.clientWidth * percent - 2;
	} else {
		return document.body.clientWidth * percent;
	}
}
function getHeight(percent) {
	return document.body.clientHeight * percent;
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	$.messager.progress('close');
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.confirm('Tips', successResult, function(r) {
			if (r) {
				$('#excelDialog').dialog('close');
				search();
			}

		});
	}
}

function updateDataList() {
	html = '<form id="fileForm" method="post" enctype="multipart/form-data" >'
			+ '&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'
			+ '<table><tr>'
			+ '<td class="head" noWrap>批量修改导入</td>'
			+ '<td><input type="hidden" id="flag" value="update"/><input type="hidden" name="kunnrDataType" id="kunnrDataType" value="B"  style="width:200px"/><input type="file" name="uploadFile" id="uploadFile"  style="width:200px"/>'
			+ '</tb></tr>' + '<tr><td class="head" noWrap>导入类型</td>'
			+ '<td><select id="type" name="type" style="width:130px">'
			+ '<option value="N">--组织目标--</option>'
			+ '<option value="Y" selected>--经销商目标--</option>' + '</select>'
			+ '</td></tr><table></form>';
	openImportDialog('导入修改目标', html);
}
function change() {
	$('#orgId').val('');
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
