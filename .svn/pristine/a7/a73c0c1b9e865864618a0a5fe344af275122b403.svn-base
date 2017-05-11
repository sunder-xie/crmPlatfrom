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
								+ '/distributionDataRepAction!getDistributionDataRepList.jspa',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						height : 380,
						striped : true,
						columns : [[{
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
									field : 'inputName',
									title : '创建人',
									width : setColumnWidth(0.06),
									align : 'center',
									rowspan : 2
								},
								{
									field : 'createDate',
									title : '创建日期',
									width : setColumnWidth(0.07),
									align : 'center',
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
									field : 'inputDate',
									title : '分销日期',
									widtg : setColumnWidth(0.08),
									align : 'center',
									rowspan : 2

								},
								{
									field : 'firstUser',
									title : '城市经理',
									widtg : setColumnWidth(0.08),
									align : 'center',
									rowspan : 2

								},
								{
									field : 'secondUser',
									title : '省级经理',
									widtg : setColumnWidth(0.08),
									align : 'center',
									rowspan : 2

								},
								{
									field : 'thirdUser',
									title : '大区经理',
									widtg : setColumnWidth(0.08),
									align : 'center',
									rowspan : 2

								},
								        {
											title : 'A1椰果单杯-80g*30',
											colspan : 3
										},
										{
											title : 'A3椰果经典装-80g*30',
											colspan : 3
										},
										{
											title : 'B1红豆单杯-64g*30',
											colspan : 3
										},
										{
											title : 'C1桂圆红枣单杯-65g*30',
											colspan : 3
										},
										{
											title : 'A4椰果礼盒装-80g*12*8',
											colspan : 3
										},
										{
											title : 'A5椰果礼盒装-80g*8*10',
											colspan : 3
										},
										{
											title : 'A6椰果家庭分享装-80g*16*6',
											colspan : 3
										},
										{
											title : 'C6桂圆红枣家庭分享装-65g*16*6',
											colspan : 3
										},
										{
											title : 'A7椰果家庭分享装-80g*15*6',
											colspan : 3
										},
										{
											title : 'C7桂圆红枣家庭分享装-65g*15*6',
											colspan : 3
										},
										{
											title : 'A2椰果联杯装-80g*6*6',
											colspan : 3
										},
										{
											title : 'A8椰果联杯装-80g*3*10',
											colspan : 3
										},
										{
											title : 'B8红豆联杯装-80g*3*10',
											colspan : 3
										},
										{
											title : 'C8桂圆红枣联杯装-65g*3*10',
											colspan : 3
										},
										{
											title : 'H7功夫奶茶单瓶-400ml*15',
											colspan : 3
										},
										{
											title : 'D9超值组合装',
											colspan : 3
										},
										{
											title : 'D10豪华礼盒装-15*6',
											colspan : 3
										},
										{
											title : 'E1豪华礼盒装-12*6',
											colspan : 3
										}],
							           
								[{
									field : 'aOne',
									title : '业务录入值',
									width : setColumnWidth(0.08),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aOneC',
									title : '公式计算值',
									width : setColumnWidth(0.08),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aOneX',
									title : '差值',
									width : setColumnWidth(0.08),
									align : 'center',
									editor : 'numberbox',
									formatter : function(value, row, rec) {
										var s = row.aOneX;
										if (s == 0.0) {
											return '<span style="color:green">'
													+ s + '</span>';
										} else {
											return '<span style="color:red">'
													+ s + '</span>';
										} 
									}
								},
								{
									field : 'aThree',
									title : '业务录入值',
									width : setColumnWidth(0.08),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aThreeC',
									title : '公式计算值',
									width : setColumnWidth(0.08),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aThreeX',
									title : '差值',
									width : setColumnWidth(0.08),
									align : 'center',
									editor : 'numberbox',
									formatter : function(value, row, rec) {
										var s = row.aThreeX;
										if (s == 0.0) {
											return '<span style="color:green">'
													+ s + '</span>';
										} else {
											return '<span style="color:red">'
													+ s + '</span>';
										} 
									}
								},
								{
									field : 'bOne',
									title : '业务录入值',
									width : setColumnWidth(0.08),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'bOneC',
									title : '公式计算值',
									width : setColumnWidth(0.08),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'bOneX',
									title : '差值',
									width : setColumnWidth(0.08),
									align : 'center',
									editor : 'numberbox',
									formatter : function(value, row, rec) {
										var s = row.bOneX;
										if (s == 0.0) {
											return '<span style="color:green">'
													+ s + '</span>';
										} else {
											return '<span style="color:red">'
													+ s + '</span>';
										} 
									}
								},
								{
									field : 'cOne',
									title : '业务录入值',
									width : setColumnWidth(0.08),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'cOneC',
									title : '公式计算值',
									width : setColumnWidth(0.08),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'cOneX',
									title : '差值',
									width : setColumnWidth(0.08),
									align : 'center',
									editor : 'numberbox',
									formatter : function(value, row, rec) {
										var s = row.cOneX;
										if (s == 0.0) {
											return '<span style="color:green">'
													+ s + '</span>';
										} else {
											return '<span style="color:red">'
													+ s + '</span>';
										} 
									}
								},
								{
									field : 'aFour',
									title : '业务录入值',
									width : setColumnWidth(0.08),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aFourC',
									title : '公式计算值',
									width : setColumnWidth(0.08),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aFourX',
									title : '差值',
									width : setColumnWidth(0.08),
									align : 'center',
									editor : 'numberbox',
									formatter : function(value, row, rec) {
										var s = row.aFourX;
										if (s == 0.0) {
											return '<span style="color:green">'
													+ s + '</span>';
										} else {
											return '<span style="color:red">'
													+ s + '</span>';
										} 
									}
								},
								{
									field : 'aFive',
									title : '业务录入值',
									width : setColumnWidth(0.08),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aFiveC',
									title : '公式计算值',
									width : setColumnWidth(0.08),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aFiveX',
									title : '差值',
									width : setColumnWidth(0.08),
									align : 'center',
									editor : 'numberbox',
									formatter : function(value, row, rec) {
										var s = row.aFiveX;
										if (s == 0.0) {
											return '<span style="color:green">'
													+ s + '</span>';
										} else {
											return '<span style="color:red">'
													+ s + '</span>';
										} 
									}
								},
								{
									field : 'aSix',
									title : '业务录入值',
									width : setColumnWidth(0.08),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aSixC',
									title : '公式计算值',
									width : setColumnWidth(0.08),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aSixX',
									title : '差值',
									width : setColumnWidth(0.08),
									align : 'center',
									editor : 'numberbox',
									formatter : function(value, row, rec) {
										var s = row.aSixX;
										if (s == 0.0) {
											return '<span style="color:green">'
													+ s + '</span>';
										} else {
											return '<span style="color:red">'
													+ s + '</span>';
										} 
									}
								},
								{
									field : 'cSix',
									title : '业务录入值',
									width : setColumnWidth(0.10),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'cSixC',
									title : '公式计算值',
									width : setColumnWidth(0.10),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'cSixX',
									title : '差值',
									width : setColumnWidth(0.10),
									align : 'center',
									editor : 'numberbox',
									formatter : function(value, row, rec) {
										var s = row.cSixX;
										if (s == 0.0) {
											return '<span style="color:green">'
													+ s + '</span>';
										} else {
											return '<span style="color:red">'
													+ s + '</span>';
										} 
									}
								},
								{
									field : 'aSeven',
									title : '业务录入值',
									width : setColumnWidth(0.08),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aSevenC',
									title : '公式计算值',
									width : setColumnWidth(0.08),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aSevenX',
									title : '差值',
									width : setColumnWidth(0.08),
									align : 'center',
									editor : 'numberbox',
									formatter : function(value, row, rec) {
										var s = row.aSevenX;
										if (s == 0.0) {
											return '<span style="color:green">'
													+ s + '</span>';
										} else {
											return '<span style="color:red">'
													+ s + '</span>';
										} 
									}
								},
								{
									field : 'cSeven',
									title : '业务录入值',
									width : setColumnWidth(0.10),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'cSevenC',
									title : '公式计算值',
									width : setColumnWidth(0.10),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'cSevenX',
									title : '差值',
									width : setColumnWidth(0.10),
									align : 'center',
									editor : 'numberbox',
									formatter : function(value, row, rec) {
										var s = row.cSevenX;
										if (s == 0.0) {
											return '<span style="color:green">'
													+ s + '</span>';
										} else {
											return '<span style="color:red">'
													+ s + '</span>';
										} 
									}
								},
								{
									field : 'aTwo',
									title : '业务录入值',
									width : setColumnWidth(0.08),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aTwoC',
									title : '公式计算值',
									width : setColumnWidth(0.08),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aTwoX',
									title : '差值',
									width : setColumnWidth(0.08),
									align : 'center',
									editor : 'numberbox',
									formatter : function(value, row, rec) {
										var s = row.aTwoX;
										if (s == 0.0) {
											return '<span style="color:green">'
													+ s + '</span>';
										} else {
											return '<span style="color:red">'
													+ s + '</span>';
										} 
									}
								},
								{
									field : 'aEight',
									title : '业务录入值',
									width : setColumnWidth(0.08),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aEightC',
									title : '公式计算值',
									width : setColumnWidth(0.08),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aEightX',
									title : '差值',
									width : setColumnWidth(0.08),
									align : 'center',
									editor : 'numberbox',
									formatter : function(value, row, rec) {
										var s = row.aEightX;
										if (s == 0.0) {
											return '<span style="color:green">'
													+ s + '</span>';
										} else {
											return '<span style="color:red">'
													+ s + '</span>';
										} 
									}
								},
								{
									field : 'bEight',
									title : '业务录入值',
									width : setColumnWidth(0.08),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'bEightC',
									title : '公式计算值',
									width : setColumnWidth(0.08),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'bEightX',
									title : '差值',
									width : setColumnWidth(0.08),
									align : 'center',
									editor : 'numberbox',
									formatter : function(value, row, rec) {
										var s = row.bEightX;
										if (s == 0.0) {
											return '<span style="color:green">'
													+ s + '</span>';
										} else {
											return '<span style="color:red">'
													+ s + '</span>';
										} 
									}
								},
								{
									field : 'cEight',
									title : '业务录入值',
									width : setColumnWidth(0.08),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'cEightC',
									title : '公式计算值',
									width : setColumnWidth(0.08),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'cEightX',
									title : '差值',
									width : setColumnWidth(0.08),
									align : 'center',
									editor : 'numberbox',
									formatter : function(value, row, rec) {
										var s = row.cEightX;
										if (s == 0.0) {
											return '<span style="color:green">'
													+ s + '</span>';
										} else {
											return '<span style="color:red">'
													+ s + '</span>';
										} 
									}
								},
								{
									field : 'hSeven',
									title : '业务录入值',
									width : setColumnWidth(0.08),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'hSevenC',
									title : '公式计算值',
									width : setColumnWidth(0.08),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'hSevenX',
									title : '差值',
									width : setColumnWidth(0.08),
									align : 'center',
									editor : 'numberbox',
									formatter : function(value, row, rec) {
										var s = row.hSevenX;
										if (s == 0.0) {
											return '<span style="color:green">'
													+ s + '</span>';
										} else {
											return '<span style="color:red">'
													+ s + '</span>';
										} 
									}
								},
								{
									field : 'dNine',
									title : '业务录入值',
									width : setColumnWidth(0.08),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'dNineC',
									title : '公式计算值',
									width : setColumnWidth(0.08),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'dNineX',
									title : '差值',
									width : setColumnWidth(0.08),
									align : 'center',
									editor : 'numberbox',
									formatter : function(value, row, rec) {
										var s = row.dNineX;
										if (s == 0.0) {
											return '<span style="color:green">'
													+ s + '</span>';
										} else {
											return '<span style="color:red">'
													+ s + '</span>';
										} 
									}
								},
								{
									field : 'dTen',
									title : '业务录入值',
									width : setColumnWidth(0.08),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'dTenC',
									title : '公式计算值',
									width : setColumnWidth(0.08),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'dTenX',
									title : '差值',
									width : setColumnWidth(0.08),
									align : 'center',
									editor : 'numberbox',
									formatter : function(value, row, rec) {
										var s = row.dTenX;
										if (s == 0.0) {
											return '<span style="color:green">'
													+ s + '</span>';
										} else {
											return '<span style="color:red">'
													+ s + '</span>';
										} 
									}
								},
								{
									field : 'eOne',
									title : '业务录入值',
									width : setColumnWidth(0.10),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'eOneC',
									title : '公式计算值',
									width : setColumnWidth(0.10),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'eOneX',
									title : '差值',
									width : setColumnWidth(0.10),
									align : 'center',
									editor : 'numberbox',
									formatter : function(value, row, rec) {
										var s = row.eOneX;
										if (s == 0.0) {
											return '<span style="color:green">'
													+ s + '</span>';
										} else {
											return '<span style="color:red">'
													+ s + '</span>';
										} 
									}
								}] ],
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
								/*"-",
								{
									text : '删除',
									iconCls : 'icon-remove',
									handler : function() {
										deleteDistributionDataRep();
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
										importDistributionDataRep();
									}
								},
								"-",*/
								{
									text : '数据导出',
									iconCls : 'icon-download',
									handler : function() {
										exportDistributionDataRep();
									}
								},
								"-",
								{
									text : '显示的是标箱；',
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
function exportDistributionDataRep() {
	var form = window.document.forms[0];
	form.action = appUrl
			+ '/distributionDataRepAction!exportDistributionDataRep.jspa';
	form.submit();
	$.messager.alert('Tips', '正在导出,请稍候!', 'warning');
}

function closeDtPlan() {
	$("#MaintDistributionDataRep").window('close');
}

function initMaintDistributionDataRep(title, url, WWidth, WHeight) {
	var url = appUrl + url;
	var $win = $("#MaintDistributionDataRep")
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

function updateDistributionDataRep(ctId) {//
	var ctId = encodeURIComponent(ctId);
	var mark = encodeURIComponent(mark);
	initMaintData('目标数修改', '/goalAction!updateDataPrepare.jspa?ctId=' + ctId
			+ '&mark' + mark, 490, 400);
}

function importDistributionDataRep() {
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
													+ "/distributionDataRep/distributionDataRepAction!importDistributionDataRepCsv.jspa";
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
	initMaintDistributionDataRep('选择组织',
			'/distributionDataRepAction!orgTreePage.jspa', 400, 460);
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
			+ '/distributionDataRep/distributionDataRepAction!importDistributionDataRepModel.jspa';
	form.target = "hideFrame";
	form.submit();
}

function deleteDistributionDataRep() {
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
	form.action = "distributionDataRepAction!deleteDistributionDataRep.jspa";
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
	form.action = "distributionDataActionRep!updateDistributionDataRep.jspa";
	form.target = "hideFrame";
	form.submit();
}
function cancelrow(target) {
	$('#datagrid').datagrid('cancelEdit', getRowIndex(target));
}

function exportMould() {
	var form = window.document.forms[0];
	form.action = appUrl
			+ '/distributionDataRep/distributionDataRepAction!exportMouldCsv.jspa';
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
