$(document).ready(function() {
	loadGrid();
	init();
	$('#hideFrame').bind('load', promgtMsg);
});

var submitFlag=true;

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '调整列表',		
						url : appUrl + '/goal/goalAction!searchGoalFXChangeDetailList.jspa',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						rownumbers : true,
						height : 320,
						width : 'auto',
						queryParams : {
							changeId : $("#changeId").val()
						},
						columns :[[{
							field : 'ck',
							align : 'center',
							checkbox : true,
							rowspan : 2
						}, {
							title : '调出经销商',
							align : 'center',
							colspan : 4
						}, {
							title : '调入经销商',
							align : 'center',
							colspan : 4
						}, {
							field : 'maktx01',
							title : '品牌',
							width :  80,
							align : 'center',
							rowspan : 2
						}, {
							field : 'matterName',
							title : '品项or四级科目(SKU)名称',
							width : 150,
							align : 'center',
							rowspan : 2
						}, {
							field : 'quantity',
							title : '标箱',
							width : 80,
							align : 'center',
							rowspan : 2
						}, {
							field : 'orgId',
							hidden : true
						}, {
							field : 'orgIdTo',
							hidden : true
						}, {
							field : 'ctId',
							hidden : true
						}],
						[{
							field : 'orgName',
							title : '组织',
							width : 80,
							align : 'center'
						}, {
							field : 'kunnr',
							title : '经销商编号',
							width : 80,
							align : 'center'
						}, {
							field : 'kunnrName',
							title : '经销商名称',
							width : 170,
							align : 'center'
						}, {
							field : 'yearMonth',
							title : '年月',
							width : 80,
							align : 'center'
						}, {
							field : 'orgNameTo',
							title : '组织',
							width : 80,
							align : 'center'
						}, {
							field : 'kunnrTo',
							title : '经销商编号',
							width : 80,
							align : 'center'
						}, {
							field : 'kunnrNameTo',
							title : '经销商名称',
							width : 170,
							align : 'center',
							formatter : function(value){
								if(value=="" || value==undefined){
									return "待开经销商";
								}else{
									return value;
								}
							}
						}, {
							field : 'yearMonthTo',
							title : '年月',
							width : 80,
							align : 'center'
						}]] 
//						toolbar : [ "-" ,{
//							text : '删除',
//							iconCls : 'icon-remove ',
//							handler : function() {
//								remove();
//							}
//						}, "-"]
					});
	// 分页控件
	var p = $('#datagrid').datagrid('getPager');
	$(p).pagination({
		pageSize : 'total',
		pageList: [99999],
		showPageList : false,
		beforePageText : '第',
		afterPageText : '页    共 {pages} 页',
		displayMsg : '共 {total} 条记录'
	});
}

function init(){
	if($("#eventState").val()!="N"){
		$("#submitBtn").hide();
	}
}

function remove() {
	if($('#kunnr').combogrid('getValue')!="" || 
			($('#kunnrTo').combogrid('getValue')!="" || $('#changeOrgId').val()!="")){
		$.messager.alert('Tips', '批量调整无法删除明细！', 'warning');
		return;
	}else{
		var ids = new Array();
		var rows = $('#datagrid').datagrid('getSelections');
		for ( var i = 0; i < rows.length; i++) {
			var index=$('#datagrid').datagrid('getRowIndex',rows[i]);
			$('#datagrid').datagrid('deleteRow', index);
		}
	}
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
	$.messager.progress('close');
	if (failResult) {
		submitFlag=true;
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info');
		$('#datagrid').datagrid({
			url : appUrl + '/goal/goalAction!searchGoalSalesChangeList.jspa'
		});
		// search();
	}
}

function save(){
	var goalSalesDetailList = [];
	var rows = $('#datagrid').datagrid("getRows")
	if (rows == null || rows == '') {
		$.messager.progress('close');
		$.messager.confirm('Confirm', '请导入明细！');
		return;
	}
	for (var i = 0; i < rows.length; i++) {
		goalSalesDetailList.push({
			"orgId" : rows[i].orgId,
 			"kunnr" : rows[i].kunnr,
			"yearMonth" : rows[i].yearMonth,
			"orgIdTo" : rows[i].orgIdTo,
			"kunnrTo" : rows[i].kunnrTo,
			"yearMonthTo" : rows[i].yearMonthTo,
			"matter" : rows[i].matter,
			"quantity" : rows[i].quantity
		});
	}
	$('#goalSalesDetailList').val($.toJSON(goalSalesDetailList));

	if($("#goalSalesDetailList").val()==""){
		$.messager.alert('Confirm', '请选择导入文件！');
		return;
	}
	if($("#title").val()==""){
		$.messager.alert('Confirm', '标题不能为空！');
		return;
	}
	if(submitFlag==true){
		submitFlag=false;
		var form = window.document.forms[0];
		form.action = appUrl
		+ "/goal/goalAction!saveGoalSales.jspa";
		form.target = "hideFrame";
		form.submit();
	}
}

function submit() {
	var goalSalesDetailList = [];
	var rows = $('#datagrid').datagrid("getRows")
	if (rows == null || rows == '') {
		$.messager.progress('close');
		$.messager.confirm('Confirm', '请导入明细！');
		return;
	}
	for (var i = 0; i < rows.length; i++) {
		goalSalesDetailList.push({
			"orgId" : rows[i].orgId,
 			"kunnr" : rows[i].kunnr,
			"yearMonth" : rows[i].yearMonth,
			"orgIdTo" : rows[i].orgIdTo,
			"kunnrTo" : rows[i].kunnrTo,
			"yearMonthTo" : rows[i].yearMonthTo,
			"matter" : rows[i].matter,
			"quantity" : rows[i].quantity
		});
	}
	$('#goalSalesDetailList').val($.toJSON(goalSalesDetailList));

	if($("#goalSalesDetailList").val()==""){
		$.messager.alert('Confirm', '请选择导入文件！');
		return;
	}
	if($("#title").val()==""){
		$.messager.alert('Confirm', '标题不能为空！');
		return;
	}
	
	$.messager.confirm('Confirm','确认提交？',function(r) {
				if (r) {
					if(submitFlag==true){
						$.messager.progress();
						$.ajax({
							type : "post",
							url : appUrl+ "/goal/goalAction!selectNextUserGoalSales.jspa",
							success : function(userUtil) {
								$.messager.progress('close');
								if (userUtil == null || userUtil == "") {
									$.messager.alert('Tips', "没有下个处理人，请维护！", 'error');
									return;
								}
								if (userUtil != null && userUtil.processInstanceId != '-2'
									&& userUtil.processInstanceId != undefined) {
									var nextUser1 = "";
									var total = 0;
									$.each(userUtil.result, function(i, v) {
										total = i + 1;
										nextUser1 = v.userId;
									});
									if (total == 1) {
										submitFlag=false;
										$("#nextUserId").val(nextUser1);
										var form = window.document.forms[0];
										form.action = appUrl
										+ "/goal/goalAction!createGoalSales.jspa?eventId="
										+ userUtil.processInstanceId;
										form.target = "hideFrame";
										form.submit();
									} else if (total == 0) {
										$.messager.alert('Tips', "没有维护下个处理人！请联系管理员",
										'error');
										return;
									} else {
										if (userUtil.processInstanceId == "-1") {
											$.messager.alert('Tips', "没有维护下个处理人！请联系管理员",
											'error');
											return;
										}
										var positionHtml = "<div class='easyui-panel' title='下个处理' data-options='collapsible:true'>"
											+ "<table width='100%' border='0' cellpadding='0' cellspacing='1'>"
											+ "<tr><td class='head' noWrap>处理人</td>"
											+ "<td><select id='nextUserId1' name='nextUserId1'>";
										$.each(userUtil.result, function(i, v) {
											positionHtml += "<option value='" + v.userId
											+ "'>" + v.userName + "----"
											+ v.stationName + "</option>";
										});
										positionHtml += "</select></td></tr></table></div>";
										if ($('#nextUserDialog').length < 1) {
											$(
													'<div/>',
													{
														id : 'nextUserDialog',
														title : '选择下个处理人',
														html : "<div id='nextUser'>"
															+ positionHtml + "</div>"
															+ "</div>"
													}).appendTo('body');
										} else {
											$('#nextUser').html(positionHtml);
										}
										$('#nextUserDialog')
										.dialog(
												{
													modal : true,
													resizable : false,
													dragable : false,
													closable : false,
													open : function() {
														$('#nextUserDialog').css(
																'padding', '0.4em');
														$('#nextUserDialog .ui-accordion-content').css('padding','0.4em').height(
																$('#nextUserDialog').height() - 75);
													},
													buttons : [
													           {
													        	   text : '确定',
													        	   handler : function() {
													        		   if ($("#nextUserId1").val() == ""|| $("#nextUserId1").val() == null) {
													        			   $.messager.alert('Tips',"没有下个处理人，请维护！",'error');
													        			   return;
													        		   }
													        		   submitFlag=false;
													        		   $("#nextUserId").val($("#nextUserId1").val());
													        		   var form = window.document.forms[0];
													        		   form.action = appUrl
													        		   + "/goal/goalAction!createGoalSales.jspa?eventId="
													        		   + userUtil.processInstanceId;
													        		   form.target = "hideFrame";
													        		   form.submit();
													        	   }
													           },
													           {
													        	   text : '取消',
													        	   handler : function() {
													        		   $('#nextUserDialog').dialog('close');
													        	   }
													           } ],
													           width : document.documentElement.clientWidth * 0.50,
													           height : document.documentElement.clientHeight * 0.40
												});
									}
								} else {
									$.messager.alert('Tips', "流程出错！请联系管理员",
									'error');
								}
							},
							error : function(XMLHttpRequest, textStatus, errorThrown) {
								alert(textStatus);
							}
						});
					}
				}
			});
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
	$('#skuId').combo("setValue","");
	$('#custId').combobox("setValue","");
	$('#custKunnr').combobox("setValue","");
	$('#startDate').val("");
	$('#endDate').val("");
}

function choseOrg() {
	initMaintAccount(false, '400', '400','组织选择', '//visitInfo/visitInfoAction!orgTreePage.jspa',353,70);
}
function returnValue(selectedId, selectedName) {
	$("#changeOrgId").val(selectedId);
	$("#changeOrgName").val(selectedName);
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