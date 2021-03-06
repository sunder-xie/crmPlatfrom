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
								+ '/distributionDataAction!getDistributionDataList.jspa',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						height : height,
						striped : true,
						columns : [ [
								{
									field : 'ck',
									checkbox : true
								},
								{
									field : 'dgId',
									title : 'ID',
									width : setColumnWidth(0.04),
									align : 'center',
									hidden : true
								},
								{
									field : 'price',
									title : '操作',
									width : setColumnWidth(0.06),
									align : 'center',
									formatter : function(value, row, index) {
										var createDate = row.createDate;
										var thisSysdate = row.thisSysdate;
										if(createDate == thisSysdate){
										    if (row.editing) {
											    var s = '<a href="#" onclick="saverow(this)">保存</a> ';
											    var c = '<a href="#" onclick="cancelrow(this)">取消</a>';
											    return s + c;
										    } else {
											    var e = '<img style="cursor:pointer" onclick="updaterow(this)" title="修改" src='
													+ imgUrl
													+ '/images/actions/action_edit.png align="absMiddle"></img>&nbsp&nbsp'
											    return e;
										    }
										}
									}
								},
								{
									field : 'inputName',
									title : '创建人',
									width : setColumnWidth(0.06),
									align : 'center'
								},
								{
									field : 'createDate',
									title : '创建日期',
									width : setColumnWidth(0.06),
									align : 'center'
								},
								{
									field : 'orgRegion',
									title : '大区',
									width : setColumnWidth(0.06),
									align : 'center'
								},
								{
									field : 'orgProvince',
									title : '省份',
									width : setColumnWidth(0.06),
									align : 'center'
								},
								{
									field : 'orgCity',
									title : '城市',
									width : setColumnWidth(0.06),
									align : 'center'
								},
								{
									field : 'kunnrId',
									title : '经销商编号',
									width : setColumnWidth(0.06),
									align : 'center',
									sortable : true
								},
								{
									field : 'kunnrName',
									title : '经销商名称',
									width : setColumnWidth(0.18),
									align : 'center'

								},
								{
									field : 'inputDate',
									title : '分销日期',
									widtg : setColumnWidth(0.06),
									align : 'center'

								},
								{
									field : 'firstUser',
									title : '城市经理',
									widtg : setColumnWidth(0.06),
									align : 'center'

								},
								{
									field : 'secondUser',
									title : '省级经理',
									widtg : setColumnWidth(0.06),
									align : 'center'

								},
								{
									field : 'thirdUser',
									title : '大区经理',
									widtg : setColumnWidth(0.06),
									align : 'center'

								},
								{
									field : 'aOneYW',
									title : 'A1椰果单杯(原味)-80g*30',
									width : setColumnWidth(0.14),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aOneXY',
									title : 'A1椰果单杯(香芋)-80g*30',
									width : setColumnWidth(0.14),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aOneMX',
									title : 'A1椰果单杯(麦香)-80g*30',
									width : setColumnWidth(0.14),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aOneCM',
									title : 'A1椰果单杯(草莓)-80g*30',
									width : setColumnWidth(0.14),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aOneQKL',
									title : 'A1椰果单杯(巧克力)-80g*30',
									width : setColumnWidth(0.15),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aOneKF',
									title : 'A1椰果单杯(咖啡)-80g*30',
									width : setColumnWidth(0.14),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aOneLC',
									title : 'A1椰果单杯(绿茶)-80g*30',
									width : setColumnWidth(0.14),
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
									field : 'aTwoQKL',
									title : 'A2椰果联杯装(巧克力)-80g*6*6',
									width : setColumnWidth(0.16),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aTwoXY',
									title : 'A2椰果联杯装(香芋)-80g*6*6',
									width : setColumnWidth(0.15),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aTwoYW',
									title : 'A2椰果联杯装(原味)-80g*6*6',
									width : setColumnWidth(0.15),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aEightYW',
									title : 'A8椰果联杯装(原味)-80g*3*10',
									width : setColumnWidth(0.16),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aEightXY',
									title : 'A8椰果联杯装(香芋)-80g*3*10',
									width : setColumnWidth(0.16),
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
									field : 'dNine9',
									title : 'D9超值组合装(全家福混合经典电商专供1*9)',
									width : setColumnWidth(0.22),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'dNine12',
									title : 'D9超值组合装(俏佳人红豆桂圆红枣电商专供1*12)',
									width : setColumnWidth(0.25),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'dNine20',
									title : 'D9超值组合装(好滋味缤纷椰果系列电商专供1*20)',
									width : setColumnWidth(0.25),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'dNine30',
									title : 'D9超值组合装(超值组合装1*30)',
									width : setColumnWidth(0.16),
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
									width : setColumnWidth(0.16),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'lastDate',
									title : '最后修改时间',
									width : setColumnWidth(0.06),
									align : 'center',
									hidden : true
								},
								{
									field : 'thisSysdate',
									title : '系统时间',
									align : 'center',
									hidden : true
								}
								/*
								 * { field : 'resign_date', title : '业务人员离职日期',
								 * width : setColumnWidth(0.10), align :
								 * 'center' },
								 */
								 ] ],
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
									text : '删除',
									iconCls : 'icon-remove',
									handler : function() {
										deleteDistributionData();
									}
								},
								"-",
								{
									text : '模版下载',
									iconCls : 'icon-excel',
									handler : function() {
										exportMould();
									}
								},
								"-",
								{
									text : '批量导入',
									iconCls : 'icon-add',
									handler : function() {
										importDistributionData();
									}
								},
								"-",
								{
									text : '数据导出',
									iconCls : 'icon-download',
									handler : function() {
										exportDistributionData();
									}
								},
								"-",
								{
									text : '1.录入的是箱数，不是标箱；2.城市经理，省级经理为必填项，没有则填无|大区经理为非必填项',
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
function exportDistributionData() {
	var form = window.document.forms[0];
	form.action = appUrl
			+ '/distributionDataAction!exportDistributionData.jspa';
	form.submit();
	$.messager.alert('Tips', '正在导出,请稍候!', 'warning');
}

function closeDtPlan() {
	$("#MaintDistributionData").window('close');
}

function initMaintDistributionData(title, url, WWidth, WHeight) {
	var url = appUrl + url;
	var $win = $("#MaintDistributionData")
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
		startDate : document.getElementsByName("startDate")[0].value,
		endDate : document.getElementsByName('endDate')[0].value
	});
}

function updateDistributionData(ctId) {//
	var ctId = encodeURIComponent(ctId);
	var mark = encodeURIComponent(mark);
	initMaintData('目标数修改', '/goalAction!updateDataPrepare.jspa?ctId=' + ctId
			+ '&mark' + mark, 490, 400);
}

function importDistributionData() {
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
													+ "/distributionData/distributionDataAction!importDistributionDataCsv.jspa";
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
	initMaintDistributionData('选择组织',
			'/distributionDataAction!orgTreePage.jspa', 400, 460);
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
			+ '/distributionData/distributionDataAction!importDistributionDataModel.jspa';
	form.target = "hideFrame";
	form.submit();
}

function deleteDistributionData() {
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
	form.action = "distributionDataAction!deleteDistributionData.jspa";
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
	var aOneYW = rowss[0].aOneYW;
	var aOneXY = rowss[0].aOneXY;
	var aOneMX = rowss[0].aOneMX;
	var aOneCM = rowss[0].aOneCM;
	var aOneQKL = rowss[0].aOneQKL;
	var aOneKF = rowss[0].aOneKF;
	var aOneLC = rowss[0].aOneLC;
	var aTwoQKL = rowss[0].aTwoQKL;
	var aTwoXY = rowss[0].aTwoXY;
	var aTwoYW = rowss[0].aTwoYW;
	var aThree = rowss[0].aThree;
	var aFour = rowss[0].aFour;
	var aFive = rowss[0].aFive;
	var aSix = rowss[0].aSix;
	var aSeven = rowss[0].aSeven;
	var aEightYW = rowss[0].aEightYW;
	var aEightXY = rowss[0].aEightXY;
	var bOne = rowss[0].bOne;
	var bSix = rowss[0].bSix;
	var bEight = rowss[0].bEight;
	var cOne = rowss[0].cOne;
	var cSix = rowss[0].cSix;
	var cSeven = rowss[0].cSeven;
	var cEight = rowss[0].cEight;
	var dNine9 = rowss[0].dNine9;
	var dNine12 = rowss[0].dNine12;
	var dNine20 = rowss[0].dNine20;
	var dNine30 = rowss[0].dNine30;
	var dTen = rowss[0].dTen;
	var eOne = rowss[0].eOne;
	var hSeven = rowss[0].hSeven;

	$("#dgId").val(dgId);
	$("#aOneYW").val(aOneYW);
	$("#aOneXY").val(aOneXY);
	$("#aOneMX").val(aOneMX);
	$("#aOneCM").val(aOneCM);
	$("#aOneQKL").val(aOneQKL);
	$("#aOneKF").val(aOneKF);
	$("#aOneLC").val(aOneLC);
	$("#aTwoQKL").val(aTwoQKL);
	$("#aTwoXY").val(aTwoXY);
	$("#aTwoYW").val(aTwoYW);
	$("#aThree").val(aThree);
	$("#aFour").val(aFour);
	$("#aFive").val(aFive);
	$("#aSix").val(aSix);
	$("#aSeven").val(aSeven);
	$("#aEightYW").val(aEightYW);
	$("#aEightXY").val(aEightXY);
	$("#bOne").val(bOne);
	$("#bSix").val(bSix);
	$("#bEight").val(bEight);
	$("#cOne").val(cOne);
	$("#cSix").val(cSix);
	$("#cSeven").val(cSeven);
	$("#cEight").val(cEight);
	$("#dNine9").val(dNine9);
	$("#dNine12").val(dNine12);
	$("#dNine20").val(dNine20);
	$("#dNine30").val(dNine30);
	$("#dTen").val(dTen);
	$("#eOne").val(eOne);
	$("#hSeven").val(hSeven);

	var form = window.document.forms[0];
	form.action = "distributionDataAction!updateDistributionData.jspa";
	form.target = "hideFrame";
	form.submit();
}
function cancelrow(target) {
	$('#datagrid').datagrid('cancelEdit', getRowIndex(target));
}

function exportMould() {
	var form = window.document.forms[0];
	form.action = appUrl
			+ '/distributionData/distributionDataAction!exportMouldCsv.jspa';
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
