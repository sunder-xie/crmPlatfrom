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
								+ '/distributionGoalAction!getDistributionGoalList.jspa',
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
									align : 'center',
									hidden : true
								},
								{
									field : 'orgState',
									title : '组织状态',
									align : 'center',
									hidden : true
								},
								{
									field : 'price',
									title : '操作',
									width : setColumnWidth(0.09),
									align : 'center',
									formatter : function(value, row, index) {

										var dgId = row.dgId;
										var trFlag = row.trFlag;

										if (trFlag == "1") {
											return '<img style="cursor:pointer" onclick="checkDistributionGoal('
													+ dgId
													+ ',2)" title="初审通过" src='
													+ imgUrl
													+ '/images/actions/icon_ok.gif align="absMiddle"></img>&nbsp&nbsp'
													+ '<img style="cursor:pointer" onclick="checkDistributionGoal('
													+ dgId
													+ ',4)" title="驳回" src='
													+ imgUrl
													+ '/images/actions/undo.png align="absMiddle"></img>';
										} else {
											return '<img title="初审" src='
													+ imgUrl
													+ '/images/actions/icon_ok_h.png align="absMiddle"></img>';
										}

									}
								},
								{
									field : 'checkName',
									title : '审核人',
									width : setColumnWidth(0.08),
									align : 'center'
								},
								{
									field : 'trFlag',
									title : '审核状态',
									width : setColumnWidth(0.05),
									align : 'center',
									sortable : false,
									htmlincode : false,
									formatter : function(value, row, rec) {
										var s = row.trFlag;
										if (s == "3") {
											return '<span style="color:green">'
													+ "已审" + '</span>';
										} else if (s == "2") {
											return '<span style="color:red">'
													+ "初审" + '</span>';
										} else if (s == "1") {
											return '<span style="color:red">'
													+ "未审" + '</span>';
										} else if (s == "4") {
											return '<span style="color:red">'
													+ "驳回" + '</span>';
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
									title : '目标量日期',
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
								}/*, {
									field : 'lastDate',
									title : '最后修改时间',
									width : setColumnWidth(0.06),
									align : 'center'
								}*/ ] ],
						onLoadSuccess : function(data) {
							$(".datagrid-header-check")[0].disabled = true;
							selectedFile(data.rows);
						},
						toolbar : [ /*"-", {
							text : '删除',
							iconCls : 'icon-remove',
							handler : function() {
								deleteDistributionGoal();
							}
						},*/ "-", {
							text : '数据导出',
							iconCls : 'icon-download',
							handler : function() {
								exportDistributionGoal();
							}
						}, "-",{
							text : '1、分销目标量数据||录入的是箱数，不是标箱！！！ 2、暂无批量审批，后期会补上，目前操作单个审批',
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

function closeDtPlan() {
	$("#MaintDistributionGoal").window('close');
}

function initMaintDistributionGoal(title, url, WWidth, WHeight) {
	var url = appUrl + url;
	var $win = $("#MaintDistributionGoal")
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
		trFlag : $('#trFlag').combobox('getValue'),
		startDate : encodeURIComponent(document.getElementsByName("startDate")[0].value),
		endDate : encodeURIComponent(document.getElementsByName('endDate')[0].value)
	});
}

function importDistributionGoal() {
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
													+ "/distributionGoal/distributionGoalAction!importDistributionGoalCsv.jspa";
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

function selectOrgTree() {
	initMaintDistributionGoal('选择组织',
			'/distributionGoalAction!orgTreePage.jspa', 400, 460);
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
			+ '/distributionGoal/distributionGoalAction!importDistributionGoalModel.jspa';
	form.target = "hideFrame";
	form.submit();
}

function deleteDistributionGoal() {
	var rows = $('#datagrid').datagrid('getSelections');
	// console.info(rows);
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
	form.action = "distributionGoalAction!deleteDistributionGoal.jspa";
	form.target = "hideFrame";
	form.submit();
}
function checkDistributionGoal(dgId, state) {
	$("#state").val(state);
	$("#dgId").val(dgId);
	var form = window.document.forms[0];
	form.action = "distributionGoalAction!checkDistributionGoal.jspa";
	form.target = "hideFrame";
	form.submit();
}
function exportMould() {
	var form = window.document.forms[0];
	form.action = appUrl
			+ '/distributionGoal/distributionGoalAction!exportMouldCsv.jspa';
	form.target = "hideFrame";
	form.submit();
}

function exportDistributionGoal() {
	$.messager.progress();
	openTime();
	var form = window.document.forms[0];
	form.action = 'distributionGoalAction!searchDistributionGoaldownLoad.jspa';
	form.target = "hideFrame";
	form.submit();
}
function openTime() {
	setTimeout(function() {
		var timer = setInterval(function() {
			$.ajax({
				type : "post",
				url : appUrl + "/distributionGoalAction!downLoadOver.jspa?",
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

function clear() {
	$('#kunnrId').val("");
	$('#kunnrName').val("");
	$("#startDate").datebox('setValue', '');
	$("#endDate").datebox('setValue', '');
	$("#orgId").val("");
	$("#orgName").val("");
	$("#trFlag").combobox('setValue', '');
}

function selectedFile(rows) {
	for ( var j = 0; j < rows.length; j++) {
		if (rows[j]['trFlag'] == 2 || rows[j]['trFlag'] == 3) {
			$(".datagrid-row[datagrid-row-index=" + j
					+ "] input[type='checkbox']")[0].disabled = true;
		}
	}
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
