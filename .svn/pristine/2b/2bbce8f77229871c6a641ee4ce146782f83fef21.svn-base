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
								+ '/posAction!getPosList.jspa',
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
									field : 'pId',
									title : 'ID',
									width : setColumnWidth(0.04),
									align : 'center',
									hidden : true
								},
//								{
//									field : 'price',
//									title : '操作',
//									width : setColumnWidth(0.06),
//									align : 'center',
//									formatter : function(value, row, index) {
//										var createDate = row.createDate;
//										var thisSysdate = row.thisSysdate;
//										if(createDate == thisSysdate){
//										    if (row.editing) {
//											    var s = '<a href="#" onclick="saverow(this)">保存</a> ';
//											    var c = '<a href="#" onclick="cancelrow(this)">取消</a>';
//											    return s + c;
//										    } else {
//											    var e = '<img style="cursor:pointer" onclick="updaterow(this)" title="修改" src='
//													+ imgUrl
//													+ '/images/actions/action_edit.png align="absMiddle"></img>&nbsp&nbsp'
//											    return e;
//										    }
//										}
//									}
//								},
								{
									field : 'inputName',
									title : '创建人',
									width : setColumnWidth(0.06),
									align : 'center'
								},
//								{
//									field : 'createDate',
//									title : '创建日期',
//									width : setColumnWidth(0.06),
//									align : 'center'
//								},
//								{
//									field : 'orgRegion',
//									title : '大区',
//									width : setColumnWidth(0.06),
//									align : 'center'
//								},
//								{
//									field : 'orgProvince',
//									title : '省份',
//									width : setColumnWidth(0.06),
//									align : 'center'
//								},
								{
									field : 'orgCity',
									title : '城市',
									width : setColumnWidth(0.06),
									align : 'center'
								},
								{
									field : 'systemId',
									title : '系统编号',
									width : setColumnWidth(0.06),
									align : 'center',
									sortable : true
								},
								{
									field : 'systemName',
									title : '系统名称',
									width : setColumnWidth(0.06),
									align : 'center'

								},
								{
									field : 'aOneXY',
									title : '香芋',
									width : setColumnWidth(0.06),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aOneQKL',
									title : '巧克力',
									width : setColumnWidth(0.06),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aOneYW',
									title : '原味',
									width : setColumnWidth(0.06),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aOneMX',
									title : '麦香',
									width : setColumnWidth(0.06),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aOneKF',
									title : '咖啡',
									width : setColumnWidth(0.06),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aOneCM',
									title : '草莓',
									width : setColumnWidth(0.06),
									align : 'center',
									editor : 'numberbox'
								},	
								{
									field : 'aOneLC',
									title : '绿茶',
									width : setColumnWidth(0.06),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'bOne',
									title : '红豆',
									width : setColumnWidth(0.06),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'cOne',
									title : '桂圆红枣',
									width : setColumnWidth(0.08),
									align : 'center',
									editor : 'numberbox'
								},								
								{
									field : 'aEightYW',
									title : '原味三联杯',
									width : setColumnWidth(0.16),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aEightXY',
									title : '香芋三联杯',
									width : setColumnWidth(0.16),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'bEight',
									title : '红豆三连杯',
									width : setColumnWidth(0.16),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'cEight',
									title : '桂圆红枣三连杯',
									width : setColumnWidth(0.16),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'dOne',
									title : '椰果家庭分享装',
									width : setColumnWidth(0.16),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'dTwo',
									title : '红豆家庭分享装',
									width : setColumnWidth(0.16),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'dThree',
									title : '香飘飘礼袋椰果奶茶',
									width : setColumnWidth(0.16),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'ygSale',
									title : '单杯销量',
									width : setColumnWidth(0.08),
									align : 'center',
									editor : 'numberbox'
								},								
								{
									field : 'totalSale',
									title : '总销量',
									width : setColumnWidth(0.08),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'totalMoney',
									title : '总销额',
									width : setColumnWidth(0.08),
									align : 'center',
									editor : 'numberbox'
								}
//								{
//									field : 'lastDate',
//									title : '最后修改时间',
//									width : setColumnWidth(0.06),
//									align : 'center',
//									hidden : true
//								},
//								{
//									field : 'thisSysdate',
//									title : '系统时间',
//									align : 'center',
//									hidden : true
//								}
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
										deletePos();
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
										importPos();
									}
								},
								"-",
								{
									text : '数据导出',
									iconCls : 'icon-download',
									handler : function() {
										exportPos();
									}
								},"-" ]
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
function exportPos() {
	var form = window.document.forms[0];
	form.action = appUrl
			+ '/posAction!exportPos.jspa';
	form.submit();
	$.messager.alert('Tips', '正在导出,请稍候!', 'warning');
}

function closeDtPlan() {
	$("#MaintPos").window('close');
}

function initMaintPos(title, url, WWidth, WHeight) {
	var url = appUrl + url;
	var $win = $("#MaintPos")
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
//		kunnrId : $('#kunnrId').val(),
		orgName : encodeURIComponent($('#orgName').val()),
//		kunnrName : encodeURIComponent($("#kunnrName").val()),
		startDate : document.getElementsByName("startDate")[0].value,
		endDate : document.getElementsByName('endDate')[0].value
	});
}

function updatePos(ctId) {//
	var ctId = encodeURIComponent(ctId);
	var mark = encodeURIComponent(mark);
	initMaintPos('目标数修改', '/goalAction!updateDataPrepare.jspa?ctId=' + ctId
			+ '&mark' + mark, 490, 400);
}

function importPos() {
	html = '<form id="fileForm" method="post" enctype="multipart/form-data" >'
			+ '&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'
			+ '<table><tr>'
			+ '<td class="head" noWrap>选择文件上传：</td><td>'
			+ '<input type="file" name="uploadFile" id="uploadFile"  style="width:200px"/>'
			+ '</tb></tr>' + '<table></form>';
	importPosCsv('导入目标', html);
}
var mask_;

//批量导入POS信息
function importPosCsv(t, html) {
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
											var form = document
													.getElementById('fileForm');
											form.action = appUrl
													+ "/posAction!importPosCsv.jspa";
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
	initMaintPos('选择组织',
			'/posAction!orgTreePage.jspa', 400, 460);
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
			+ '/posAction!importPosModel.jspa';
	form.target = "hideFrame";
	form.submit();
}

function deletePos() {
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
	form.action = "/posAction!deletePos.jspa";
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
	form.action = "/posAction!updatePos.jspa";
	form.target = "hideFrame";
	form.submit();
}
function cancelrow(target) {
	$('#datagrid').datagrid('cancelEdit', getRowIndex(target));
}

function exportMould() {
	var form = window.document.forms[0];
	form.action = appUrl
			+ '/posAction!exportMouldCsv.jspa';
	form.target = "hideFrame";
	form.submit();
}

function clear() {
	$('#systemId').val("");
	$('#systemName').val("");
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
