$(document).ready(function() {
	loadGrid();
	loadGrid2();
	init();
	$('#hideFrame').bind('load', promgtMsg);
});
var submitFlag=true;
function loadGrid() {
	var toolbar = $('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '提交结果',
//						url : appUrl + '/kunnrBusinessAction!getAddDealerAdjustmentJsonList.jspa?id='+$('#id').val(),
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
									field : 'matnr01',
									title : '品牌编码',
									width : setColumnWidth(0.02),
									align : 'center',
									hidden : true
								},
								{
									field : 'maktx01',
									title : '品牌',
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
									title : '品项or四级科目(SKU)',
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
								/*"-",
								{
									text : '提报调整模版excel导入',
									iconCls : 'icon-add',
									handler : function() {
										importDealerAdjustment();
									}
								},*/
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
function loadGrid2() {
	$('#kunnrId').combogrid({
		panelWidth : 400,
		panelHight : 600,
		idField : 'kunnr',
		textField : 'name1',
		pagination : true,// 是否分页
		collapsible : false,// 是否可折叠的
		method : 'post',
		url : appUrl + '/kunnrBusinessContact/kunnrBusinessAction!getKunner.jspa',
		queryParams : {
			orgId : $('#orgId').val()
		},
		columns : [ [ {
			field : 'ck',
			checkbox : true,
			hidden : true
		}, {
			field : 'id',
			title : '经销商ID',
			hidden : true,
			width : 60
		}, {
			field : 'kunnr',
			title : '经销商编号',
			align : 'center',
			width : 120
		}, {
			field : 'status',
			title : '经销商状态',
			align : 'center',
			width : 100,
			formatter : function(value, row, index) {
				if (value == 1) {
					return '正常客戶';
				} else if (value == 2) {
					return '<font color="red">已关客戶</font>';
				}
			}
		},{
			field : 'name1',
			title : '经销商名称',
			align : 'center',
			width : 100
		},  {
			field : 'mobNumber',
			title : '经销商手机',
			align : 'center',
			width : 150
		} ] ],
		toolbar : '#toolbar2'
	});

}
/**
 * 搜索经销商的easy之searcher搜索插件
 * @param val 输入值
 */
function searcher(val) {
	val = encodeURIComponent(val);
	$('#kunnrId').combogrid({
		url : appUrl + '/kunnrBusinessContact/kunnrBusinessAction!getKunner.jspa?value=' + val
	});
	$('#kunnrId').combogrid("grid").datagrid('reload');

}
/**
 * 选择组织插件
 */
function choseOrgUser() {
	initMaintAccount(false, '400', '400','组织选择', '//visitInfo/visitInfoAction!orgTreePage.jspa',353,70);
}
//返回只处理
function returnValue(selectedId, selectedName) {
	$("#orgId").val(selectedId);
	$("#orgName").val(selectedName);
	$('#kunnrId').combogrid({//搜索完成之后同时加载
		url : appUrl + '/kunnrBusinessContact/kunnrBusinessAction!getKunner.jspa',
		queryParams : {
			orgId : $('#orgId').val()
		}
	});
}
function closeOrgTree() {
	$("#maintWindow").window('close');
}
function reloadDatagrid() {
	self.location.reload(true);
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
	if($("#eventType").val()==""){
		$.messager.alert('Confirm', '申请事务类型不能为空！');
		return;
	}
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
	var orgId=$("#orgId").val();
	//alert(orgId);
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
	if($("#eventType").val()==""){
		$.messager.alert('Confirm', '申请事务类型不能为空！');
		return;
	}
	if($("#kunnrId").combobox("getValue")==""){
		$.messager.alert('Confirm', '请选择提报的经销商！');
		return;
	}
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
								+ userUtil.processInstanceId+"&orgId="+orgId;
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
											        		   + userUtil.processInstanceId+"&orgId="+orgId;;
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
	var orgId=$("#orgId").val();
	var kunnrId=$("#kunnrId").combobox("getValue");
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
													+ "/kunnrBusinessContact/kunnrBusinessAction!importDealerAdjustmentCsv.jspa?orgId="
													+orgId+"&kunnrId="+kunnrId;
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
	var kunnrId=$("#kunnrId").combobox("getValue");
	console.info(kunnrId);
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
		submitFlag=true;
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		/*$.messager.alert('Tips', successResult, 'info');
		$('#datagrid').datagrid({
			url : appUrl + '/kunnrBusinessAction!getAddDealerAdjustmentJsonList.jspa?'
		});
		if ($('#nextUserDialog').length > 0) {
			$('#nextUserDialog').dialog('close');
		}*/
		$.messager.confirm('Tips', successResult, function(r) {
			if (r) {
				$('#excelDialog').dialog('close');
			}
		$('#datagrid').datagrid({
			url : appUrl + '/kunnrBusinessAction!getAddDealerAdjustmentJsonList.jspa?'
		});
		if ($('#nextUserDialog').length > 0) {
			$('#nextUserDialog').dialog('close');
		}
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
document.onkeydown = function(e) {// 这个事件在用户按下任何键盘键（包括系统按钮，如箭头键和功能键）时发生
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		search();
		return false;
	}
	return true;
};
