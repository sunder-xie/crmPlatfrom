$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '分销目标列表',		
						url : appUrl + '/stockReportAction!searchSalesGoalCGList.jspa?goalType='+$("#goalType").val(),
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						rownumbers : true,
						height : 420,
						queryParams : {
							orgId : $("#orgId").val()
						},
						width : 'auto',
						columns :[[ {
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
							align : 'center',
							formatter : function(v) {
								if(v=="00000000"){
									return "待开";
								}else{
									return v;
								}
							}
						}, {
							field : 'kunnrName',
							title : '经销商名称',
							width : 150,
							align : 'center'
						}, {
							field : 'orgName',
							title : '待开组织',
							width : 80,
							align : 'center'
						}, {
							field : 'goalYear',
							title : '财年',
							width : 80,
							align : 'center'
						}, {
							field : 'goalMonth',
							title : '月份',
							width : 80,
							align : 'center'
						}, {
							field : 'userId',
							title : '提报人账号',
							width : 80,
							align : 'center'
						}, {
							field : 'createDate',
							title : '提报日期',
							width : 80,
							align : 'center'
						}, {
							field : 'cgA1',
							title : '椰果单杯-30',
							width : 100,
							align : 'center'
						}, {
							field : 'cgA2',
							title : '椰果联杯装-6*6',
							width : 100,
							align : 'center'
						}, {
							field : 'cgA3',
							title : '椰果经典装-30',
							width : 100,
							align : 'center'
						}, {
							field : 'cgA4',
							title : '椰果礼盒装-12',
							width : 100,
							align : 'center'
						}, {
							field : 'cgA5',
							title : '椰果礼盒装-10',
							width : 100,
							align : 'center'
						}, {
							field : 'cgA6',
							title : '椰果家庭分享装-16',
							width : 100,
							align : 'center'
						}, {
							field : 'cgA7',
							title : '椰果家庭分享装-15',
							width : 100,
							align : 'center'
						}, {
							field : 'cgA8',
							title : '椰果联杯装-4*10',
							width : 100,
							align : 'center'
						}, {
							field : 'cgB1',
							title : '红豆单杯-30',
							width : 100,
							align : 'center'
						}, {
							field : 'cgB3',
							title : '红豆、椰果经典装-30',
							width : 100,
							align : 'center'
						}, {
							field : 'cgB6',
							title : '红豆家庭分享装-16',
							width : 100,
							align : 'center'
						}, {
							field : 'cgB8',
							title : '红豆联杯装-3*10',
							width : 100,
							align : 'center'
						}, {
							field : 'cgC1',
							title : '桂圆红枣单杯-30',
							width : 100,
							align : 'center'
						}, {
							field : 'cgC6',
							title : '红豆、桂圆红枣分享装-16',
							width : 100,
							align : 'center'
						}, {
							field : 'cgC7',
							title : '红豆、桂圆红枣分享装-15',
							width : 100,
							align : 'center'
						}, {
							field : 'cgC8',
							title : '桂圆红枣联杯装-3*10',
							width : 100,
							align : 'center'
						}, {
							field : 'cgD10',
							title : '豪华礼盒装15*6',
							width : 100,
							align : 'center'
						}, {
							field : 'cgD9',
							title : '超值组合装',
							width : 100,
							align : 'center'
						}, {
							field : 'cgE1',
							title : '豪华礼盒装6*12',
							width : 100,
							align : 'center'
						}, {
							field : 'cgH7',
							title : '功夫奶茶单瓶-400ml*15',
							width : 100,
							align : 'center'
						}, {
							field : 'cgF1',
							title : '燕麦单杯-82g*30',
							width : 100,
							align : 'center'
						}, {
							field : 'cgF8',
							title : '燕麦三连杯装-82g*3*10',
							width : 100,
							align : 'center'
						}, {
							field : 'cgG1',
							title : '黑米单杯-82g*30',
							width : 100,
							align : 'center'
						}, {
							field : 'cgG8',
							title : '黑米三连杯装-82g*3*10',
							width : 100,
							align : 'center'
						}, {
							field : 'cgJ1',
							title : '仙草单杯-82g*30',
							width : 100,
							align : 'center'
						}, {
							field : 'cgJ8',
							title : '仙草三连杯装-82g*3*10',
							width : 100,
							align : 'center'
						}, {
							field : 'cgK1',
							title : '红豆单杯-64g*30-14版 ',
							width : 100,
							align : 'center'
						}, {
							field : 'cgK8',
							title : '红豆三连杯装-64g*3*10-14版',
							width : 100,
							align : 'center'
						}, {
							field : 'cgD3',
							title : '美味组合装1*30',
							width : 100,
							align : 'center'
						}]],
					toolbar : [ "-", {
						text : '导入分销目标',
						iconCls : 'icon-excel',
						handler : function() {
							importCsv();
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
			+ '<td><a style="color:blue" onclick=javascript:exportGoalCsv();> 1、下载模版</a></td></tr>'
			+ '<tr><td class="head" noWrap>批量导入</td>'
			+ '<td><input type="file" name="uploadFile" id="uploadFile"  style="width:200px"/>'
			+ '</tb></tr></table></form>';
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

//批量导入终端门店信息
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


function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.orgId = $('#orgId').val();
	queryParams.kunnr = $('#kunnr').val();
	$("#datagrid").datagrid('load');
}
function exportForExcel() {
	$.messager.progress();
	openTime();
	var form = window.document.forms[0];
	form.action = appUrl
			+ '/stockReport/stockReportAction!exportStockForExcel.jspa';
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
		$.messager.alert('Tips', successResult, 'info');
		$('#excelDialog').dialog('close');
		$('#datagrid').datagrid('reload');
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
function closeMaintActivityPlanRepoty() {
	$("#maintActivityPlanInfo").window('close');
}

function clearValue(){
	$('#skuId').combo("setValue","");
	$('#custId').combobox("setValue","");
	$('#custKunnr').combobox("setValue","");
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