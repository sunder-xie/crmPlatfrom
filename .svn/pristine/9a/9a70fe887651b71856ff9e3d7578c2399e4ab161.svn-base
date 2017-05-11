$(document).ready(function() {
	loadGrid();
	init();
	$('#hideFrame').bind('load', promgtMsg);
	$('#eventType').combobox({
		valueField : 'id',
		textField : 'text',
		data : [ {
			id : 'A',
			text : '目标量提报',
			value : 'A'	
		}, {
			id : 'B',
			text : '目标量调整',
			value : 'B'
		}],
		multiple : false,
		editable : false,
		panelHeight : 'auto'
	});
});
var submitFlag=true;
function loadGrid() {
	var toolbar = $('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '提交结果',
						url : appUrl + '/kunnrBusinessAction!getAddDealerAdjustmentJsonList.jspa?id='+$('#id').val(),
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
									field : 'id',
									title : 'ID',
									width : setColumnWidth(0.01),
									align : 'center',
									hidden : true
								},
								{
									field : 'detalId',
									title : '调整明细Id',
									width : setColumnWidth(0.01),
									align : 'center',
									hidden : true
								},
								{
									field : 'adjustId',
									title : '调整Id',
									width : setColumnWidth(0.01),
									align : 'center',
									hidden : true
								},
								{
									field : 'orgId',
									title : '组织id',
									width : setColumnWidth(0.01),
									align : 'center',
									hidden : true
								},
								{
									field : 'orgName',
									title : '调整组织',
									width : setColumnWidth(0.08),
									align : 'center'
								},
								{
									field : 'kunnrId',
									title : '调整经销商代码',
									width : setColumnWidth(0.1),
									align : 'center'
								},
								{
									field : 'kunnrName',
									title : '调整经销商名称',
									width : setColumnWidth(0.16),
									align : 'center'
								},
								{
									field : 'applyYear',
									title : '目标年',
									width : setColumnWidth(0.05),
									align : 'center'
								},
								{
									field : 'applyMonth',
									title : '目标月',
									width : setColumnWidth(0.05),
									align : 'center'
								},
								{
									field : 'matter',
									title : '品项',
									width : setColumnWidth(0.02),
									align : 'center',
									hidden : true
								},
								{
									field : 'matterName',
									title : '品项',
									width : setColumnWidth(0.15),
									align : 'center'
								},
								{
									field : 'nowTarget',
									title : '现有销售目标量',
									width : setColumnWidth(0.1),
									align : 'center'
								},
								{
									field : 'nowDealerTarget',
									title : '现有协议目标量',
									width : setColumnWidth(0.1),
									align : 'center'
								},
								{
									field : 'adjustTarget',
									title : '调整至协议目标量(标箱)',
									width : setColumnWidth(0.12),
									align : 'center'
								}
								 ] ],
						toolbar : [
								"-",
								{
									text : '提报调整模版下载',
									iconCls : 'icon-excel',
									handler : function() {
										exportMould();
									}
								},
								"-",
								{
									text : '提报调整模版excel导入',
									iconCls : 'icon-add',
									handler : function() {
										importDealerAdjustment();
									}
								},
								"-",
								{
									text : '品项列表下载（*参照表）',
									iconCls : 'icon-download',
									handler : function() {
										exportDealerAdjustmentMaterCsv();
									}
								},
								"-",
								{
									text : '删除选择行',
									iconCls : 'icon-remove',
									handler : function() {
										deleteChageRow();
									}
								},
								"-"],
					});
	// 分页控件
	var p = $('#datagrid').datagrid('getPager');
	$(p).pagination({
		pageSize : 1,
		pageList : [1],
		beforePageText : '第',
		afterPageText : '页    共 1 页',
		displayMsg : '当前显示   共 {total} 条记录'
	});
}
var editIndex = undefined;
function endEditing(){
	if (editIndex == undefined){return true}
	if ($('#datagrid').datagrid('validateRow', editIndex)){
		$('#datagrid').datagrid('getRows')[editIndex]['applyYear'] = applyYear;
		$('#datagrid').datagrid('getRows')[editIndex]['applyMonth'] = applyMonth;
		$('#datagrid').datagrid('endEdit', editIndex);
		editIndex = undefined;
		return true;
	} else {
		return false;
	}
}
function onClickRow(index){
	if (editIndex != index){
		if (endEditing()){
			$('#datagrid').datagrid('selectRow', index)
					.datagrid('beginEdit', index);
			editIndex = index;
		} else {
			$('#datagrid').datagrid('selectRow', editIndex);
		}
	}
}
function accept(){
	if (endEditing()){
		$('#datagrid').datagrid('acceptChanges');
	}
}
/**
 * 取消
 */
function cencel() {
	window.parent.closeMaintWindow();
}
function closeMaintWindow() {
	$("#maintWindow").window('close');
}
/**
 * 保存（临时保存）
 */
function saveToCreate(){
	var dealerAdujstDetailList=[];
	var rows = $('#datagrid').datagrid("getRows")
	if (rows == null || rows == '') {
		$.messager.progress('close');
		$.messager.confirm('Confirm', '请导入明细！');
		return;
	}
	for(var i = 0; i < rows.length; i++){
		dealerAdujstDetailList.push({
			"orgName":rows[i].orgName,
			"kunnrId":rows[i].kunnrId,
			"kunnrName":rows[i].kunnrName,
			"applyYear":rows[i].applyYear,
			"applyMonth":rows[i].applyMonth,
			"matter":rows[i].matter,
			"matterName":rows[i].matterName,
			"nowTarget":rows[i].nowTarget,
			"nowDealerTarget":rows[i].nowDealerTarget,
			"adjustTarget":rows[i].adjustTarget
		});
	}
	$('#dealerAdujstDetailList').val($.toJSON(dealerAdujstDetailList));
	if($("#dealerAdujstDetailList").val()==""){
		$.messager.alert('Confirm', '请选择导入文件！');
		return;
	}
	if($("#eventTitle").val()==""){
		$.messager.alert('Confirm', '标题不能为空！');
		return;
	}
	if($("#applyUser").val()==""){
		$.messager.alert('Confirm', '申请人不能为空！');
		return;
	}
/*	if($("#eventType").val()==""){
		$.messager.alert('Confirm', '申请事务类型不能为空！');
		return;
	}*/
	$.messager.progress();
	var form = window.document.forms[0];
	form.action = appUrl + "/kunnrBusinessContact/kunnrBusinessAction!saveToCreateDealerAdjustDetail.jspa";
	form.target = "hideFrame";
	form.submit();
}
/**
 * 提交提交事务并保存
 */
function saveToSubmit(){
	var dealerAdujstDetailList=[];
	var rows = $('#datagrid').datagrid("getRows")
	if (rows == null || rows == '') {
		$.messager.progress('close');
		$.messager.confirm('Confirm', '请导入明细！');
		return;
	}
	for(var i = 0; i < rows.length; i++){
		dealerAdujstDetailList.push({
			"orgName":rows[i].orgName,
			"kunnrId":rows[i].kunnrId,
			"kunnrName":rows[i].kunnrName,
			"applyYear":rows[i].applyYear,
			"applyMonth":rows[i].applyMonth,
			"matter":rows[i].matter,
			"matterName":rows[i].matterName,
			"nowTarget":rows[i].nowTarget,
			"nowDealerTarget":rows[i].nowDealerTarget,
			"adjustTarget":rows[i].adjustTarget
		});
	}
	$('#dealerAdujstDetailList').val($.toJSON(dealerAdujstDetailList));
	if($("#dealerAdujstDetailList").val()==""){
		$.messager.alert('Confirm', '请选择导入文件！');
		return;
	}
	if($("#eventTitle").val()==""){
		$.messager.alert('Confirm', '标题不能为空！');
		return;
	}
	if($("#applyUser").val()==""){
		$.messager.alert('Confirm', '申请人不能为空！');
		return;
	}
/*	if($("#eventType").val()==""){
		$.messager.alert('Confirm', '申请事务类型不能为空！');
		return;
	}*/
	$.messager.confirm('Confirm','确认提交？',function(r) {
		if (r) {
			if(submitFlag==true){
				$.messager.progress();
				$.ajax({
					type : "post",
					url : appUrl+ "/kunnrBusinessContact/kunnrBusinessAction!selectNextUserDealerAdjust.jspa",
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
								+ "/kunnrBusinessContact/kunnrBusinessAction!createDealerAdjust.jspa?eventId="
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
											        		   + "/kunnrBusinessContact/kunnrBusinessAction!createDealerAdjust.jspa?eventId="
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
							$.messager.alert('Tips', "流程出错！请联系管理员",'error');
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
/**
 * 提报调整模版excel导入
 */
function importDealerAdjustment() {
	html = '<form id="fileForm" method="post" enctype="multipart/form-data" >'
			+ '&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'
			+ '<table><tr>'
			+ '<td class="head" noWrap>选择文件上传：</td><td>'
			+ '<input type="file" name="uploadFile" id="uploadFile"  style="width:200px"/>'
			+ '</tb></tr>' + '<table></form>';
	openImportDialog('excel导入', html);
}
var mask_;
/**
 * 打开导入项目Excel对话框 
 */
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
										var file = document.getElementById('uploadFile').value;
										if (/^.+\.(xls|XSL)$/.test(file)) {
											$.messager.progress();
											var action = '';
											action = appUrl
													+ "/kunnrBusinessContact/kunnrBusinessAction!importDealerAdjustmentCsv.jspa";
											var form = document.getElementById('fileForm');

											form.action = action;
											form.target = "hideFrame";
											form.submit();
										} else {
											$.messager.alert("提示", "请导入xls文件");
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
/**
 * 删除选择行
 */
function deleteChageRow() {
	var rows = $('#datagrid').datagrid('getSelections');
	console.info(rows);
	if (rows == null || rows.length == 0) {
		$.messager.alert('Tips', '选中菜单栏无数据!');
		return;
	}
	var index = $('#datagrid').datagrid('getRowIndex', $("#datagrid").datagrid('getSelected'));
	$('#datagrid').datagrid('deleteRow',index);

}
/**
 * 模板下载
 */
function exportMould() {
	var form = window.document.forms[0];
	form.action = appUrl
			+ '/kunnrBusinessContact/kunnrBusinessAction!exportMouldCsv.jspa';
	form.target = "hideFrame";
	form.submit();
}
/**
 * 品项列表下载
 */
function exportDealerAdjustmentMaterCsv(){
	var form = window.document.forms[0];
	form.action = appUrl
			+ '/kunnrBusinessContact/kunnrBusinessAction!exportDealerAdjustmentMaterCsv.jspa';
	form.target = "hideFrame";
	form.submit();
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
/**
 * excelDialog数据
 */
function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	$.messager.progress('close');
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		/*$.messager.alert('Tips', successResult, 'info');
		$('#datagrid').datagrid({
			url : appUrl + '/goal/goalAction!searchGoalSalesChangeList.jspa'
		});
		if ($('#nextUserDialog').length > 0) {
			$('#nextUserDialog').dialog('close');
		}*/
		$.messager.confirm('Tips', successResult, function(r) {
			if (r) {
				$('#excelDialog').dialog('close');
			}
			window.location.reload();
		});
	}
}
function init(){
	if($("#eventStatus").val()!="N"){
		$("#submitBtn").hide();
	}
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
