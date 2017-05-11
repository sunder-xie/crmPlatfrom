var processInstanceId;
$(document).ready(function() {
	loadGrid();
	loadGrid1();
	loadGrid2();
	divHide($('#mark').combobox('getValue'));
	/*$('#year').combobox({
		editable : false,
		onSelect : function(d) {
			$('#year').val(d.itemName);
		}
	});

	$('#month').combobox({
		editable : false,
		onSelect : function(d) {
			$('#month').val(d.value);
		}
	});*/
	$('#mark').combobox({
		editable : false,
		onSelect : function(d) {
			$('#mark').val(d.value);
			divHide(d.value);
			search();
		}
	});
	if ($("#flagTemp").val() != "Y") {
		document.getElementById("approve").style.display = "none";
		document.getElementById("approveYear").style.display = "none";
		document.getElementById("reject").style.display = "none";
	}
	getGoalCount();
	$('#hideFrame').bind('load', promgtMsg);
});
function divHide(value){
	if('N'==value){
		$('#datagrid').datagrid('hideColumn','custNameZH');
		$('#datagrid').datagrid('hideColumn','kunnrGoalType');
		$('#datagrid').datagrid('showColumn','boxD');
		$('#datagrid').datagrid('showColumn','targetNumD');
	}else{
		$('#datagrid').datagrid('hideColumn','boxD');
		$('#datagrid').datagrid('hideColumn','targetNumD');
		$('#datagrid').datagrid('showColumn','custNameZH');
		$('#datagrid').datagrid('showColumn','kunnrGoalType');
	}
}
function loadGrid() {
	var toolbar = $('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '查询结果',
						url : appUrl + '/goal/goalAction!getGoalList.jspa?kunnrGoalType='+'B',
						queryParams : {
							orgId : $("#orgId").val(),
							year : $("#year").val(),
							mark : $("#mark").val()
						},
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
									field : 'ctId',
									title : '目标ID',
									width : setColumnWidth(0.04),
									align : 'center',
									sortable : true
								},
								{
									field : 'custNameZH',
									title : '经销商',
									width : setColumnWidth(0.125),
									align : 'center',
									formatter : function(value, row, rec) {
										var s = row.mark;
										if (s == "N") {
											return '<span style="color:red">'
													+ "组织目标无特定经销商" + '</span>';
										} else {
											return '<span style="color:black">'
													+ row.custNameZH
													+ '</span>';
										}
									}
								},
								{
									field : 'orgName',
									title : '组织名',
									width : setColumnWidth(0.125),
									align : 'center'
								},
								{
									field : 'kunnrGoalType',
									title : '经销商目标类型',
									width : setColumnWidth(0.08),
									align : 'center',
									hidden:true,
									formatter : function(value, row, rec) {
										var s = row.mark;
										var t=row.kunnrGoalType;
										var type='';
										if (s == "N") {
											return '';
										} else {
											
											if(t=='B'){
												type='预算';
											}
											if(t=='C'){
												type='合同';
											}
											return type;
										}
									}
								},
								{
									field : 'theYear',
									title : '目标年',
									width : setColumnWidth(0.06),
									align : 'center'
								},
								{
									field : 'theMonth',
									title : '目标月',
									width : setColumnWidth(0.06),
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
									field : 'bezei',
									title : '品项or四级科目(SKU)',
									width : setColumnWidth(0.1),
									align : 'center'
								},{
									field : 'box',
									title : '目标箱数',
									width : setColumnWidth(0.06),
									align : 'center',
									formatter : function(value, row, rec) {
										var s = row.box*1;
										if (s == ''||s==undefined) {
											return '0.00';
										}
										return s.toFixed(2);
										}
								},
								{
									field : 'targetNum',
									title : '目标金额(元)',
									width : setColumnWidth(0.08),
									align : 'center',
									formatter : function(value, row, rec) {
										var s = row.targetNum*1;
										if (s == ''||s==undefined) {
											return '0.00';
										}
										return s.toFixed(2);
										}
								},
								{
									field : 'boxD',
									title : '待开目标箱数',
									width : setColumnWidth(0.06),
									align : 'center',
									formatter : function(value, row, rec) {
										var s = row.boxD*1;
										if (s == ''||s==undefined) {
											return '0.00';
										}
										return s.toFixed(2);
										}
								},
								{
									field : 'targetNumD',
									title : '待开目标金额(元)',
									width : setColumnWidth(0.1),
									align : 'center',
									formatter : function(value, row, rec) {
										var s = row.targetNumD*1;
										if (s == ''||s==undefined) {
											return '0.00';
										}
										return s.toFixed(2);
										}
								},
								{
									field : 'opName',
									title : '提交人',
									width : setColumnWidth(0.06),
									align : 'center'
								},
								{
									field : 'checkOpName',
									title : '审核人',
									width : setColumnWidth(0.06),
									align : 'center'
								},
								{
									field : 'modifyDate',
									title : '修改时间',
									width : setColumnWidth(0.06),
									align : 'center'
								},
								{
									field : 'trFlag',
									title : '审核状态',
									width : setColumnWidth(0.08),
									align : 'center',
									formatter : function(value, row, rec) {
										var s = row.trFlag;
										if (s == "T") {
											return '<span style="color:green">'
													+ "已审" + '</span>';
										} else if (s == "B") {
											return '<span style="color:red">'
													+ "驳回" + '</span>';
										} else {
											return '<span style="color:red">'
													+ "未审" + '</span>';
										}
									}
								},
								{
									field : 'price',
									title : '操作',
									width : setColumnWidth(0.06),
									align : 'center',
									formatter : function(value, row, index) {
										var id = row.ctId;
										var type = row.mark;
										var trFlag = row.trFlag;
//										if (trFlag == "S" || trFlag == "B") {//财务任瑞梅要求所有的都可以改
											if (type == "Y") {
												return '<img style="cursor:pointer" onclick="updateGoal('
														+ id
														+ ')" title="修改经销商目标" src='
														+ imgUrl
														+ '/images/actions/action_edit.png align="absMiddle"></img>';
											} else {
												return '<img style="cursor:pointer" onclick="updateGoal('
														+ id
														+ ')" title="修改组织目标" src='
														+ imgUrl
														+ '/images/actions/action_edit.png align="absMiddle"></img>';
											}
//										} else {
//											return '';
//										}
									}
								} ] ],
						toolbar : "#toolbar"
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
	// search();
}

function loadGrid2() {
	$('#id').combogrid({
		panelWidth : 400,
		panelHight : 600,
		idField : 'kunnr',
		textField : 'name1',
		pagination : true,// 是否分页
		// rownumbers : true,// 序号
		collapsible : false,// 是否可折叠的
		// fit : true,// 自动大小
		method : 'post',
		// multiple : true,
		url : appUrl + '/goal/goalAction!getKunner.jspa',
		columns : [ [ {
			field : 'ck',
			checkbox : true,
			hidden : true
		}, {
			field : 'id',
			title : '客户ID',
			hidden : true,
			width : 60
		}, {
			field : 'kunnr',
			title : '客户编号',
			align : 'center',
			width : 120
		}, {
			field : 'status',
			title : '客户状态',
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
			title : '客户名称',
			align : 'center',
			width : 100
		},  {
			field : 'mobNumber',
			title : '手机',
			align : 'center',
			width : 150
		} ] ],
		toolbar : '#toolbar2'
	});

}
function loadGrid1() {
	$('#brand').combobox({
		onChange : function(newValue,oldValue){
			var queryParams = $('#wid').combogrid("grid").datagrid('options').queryParams;
			queryParams.brand = newValue;
			$('#wid').combogrid("grid").datagrid('reload');
		}
	});
	$('#wid').combogrid({
		panelWidth : 400,
		panelHight : 600,
		idField : 'mvgr1',
		textField : 'bezei',
		pagination : true,// 是否分页
		collapsible : false,// 是否可折叠的
		method : 'post',
		url : appUrl + '/goal/goalAction!getMaterielList.jspa',
		columns : [ [ {
			field : 'ck',
			checkbox : true,
			hidden : true
		}, {
			field : 'mvgr1',
			title : '品项or四级科目（SKU）ID',
			align : 'center',
			width : 120
		}, {
			field : 'bezei',
			title : '品项or四级科目（SKU）',
			align : 'center',
			width : 250
		} ] ],
		toolbar : '#toolbar1'
	});
}
function closeDtPlan() {
	$("#MaintGoal").window('close');
}

function initMaintGoal(title, url, WWidth, WHeight) {
	var url = appUrl + url;
	var $win = $("#MaintGoal")
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

function searcher(val) {
	var queryParams = $('#id').combogrid("grid").datagrid('options').queryParams;
	queryParams.value = encodeURIComponent(val);
	$('#id').combogrid("grid").datagrid('reload');
}

function searcher1(val) {
	var queryParams = $('#wid').combogrid("grid").datagrid('options').queryParams;
	queryParams.value = encodeURIComponent(val);
	$('#wid').combogrid("grid").datagrid('reload');
}
function createGoal() {
	initMaintGoal('经销商目标数创建', '/goalAction!createGoalPrepare.jspa?mark='+'Y'+'&kunnrGoalType='+'B', 490, 400);
}
function createOrgGoal() {
	initMaintGoal('组织目标数创建', '/goalAction!createOrgGoalPrepare.jspa?mark='+'N', 490, 400);
}
function updateGoal(ctId) {
	var ctId = encodeURIComponent(ctId);
	var mark = encodeURIComponent(mark);
	initMaintGoal('目标数修改', '/goalAction!updateGoalPrepare.jspa?ctId=' + ctId
			+ '&mark' + mark, 490, 400);
}
/**
 * 预算新增导入
 */
function importMould() {
	html = '<form id="fileForm" method="post" enctype="multipart/form-data" >'
			+ '&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'
			+ '<table><tr>'
			+ '<td class="head" noWrap>批量导入</td>'
			+ '<td><input type="hidden" id="flag" value="add"/><input type="hidden" name="kunnrGoalType" id="kunnrGoalType" value="B"  style="width:200px"/><input type="file" name="uploadFile" id="uploadFile"  style="width:200px"/>'
			+ '</tb></tr>' + '<tr><td class="head" noWrap>导入类型</td>'
			+ '<td><select id="type" name="type" style="width:130px">'
			+ '<option value="N">--组织目标--</option>'
			+ '<option value="Y" selected>--经销商目标--</option>' + '</select>'
			+ '</td></tr><table></form>';
	openImportDialog('导入目标', html);
}
/**
 * 批量修改
 */
function updateGoalList() {
	html = '<form id="fileForm" method="post" enctype="multipart/form-data" >'
			+ '&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'
			+ '<table><tr>'
			+ '<td class="head" noWrap>批量修改导入</td>'
			+ '<td><input type="hidden" id="flag" value="update"/><input type="hidden" name="kunnrGoalType" id="kunnrGoalType" value="B"  style="width:200px"/><input type="file" name="uploadFile" id="uploadFile"  style="width:200px"/>'
			+ '</tb></tr>' + '<tr><td class="head" noWrap>导入类型</td>'
			+ '<td><select id="type" name="type" style="width:130px">'
			+ '<option value="N">--组织目标--</option>'
			+ '<option value="Y" selected>--经销商目标--</option>' + '</select>'
			+ '</td></tr><table></form>';
	openImportDialog('导入修改目标', html);
}

var mask_;

/**
 * 打开导入项目Excel对话框 
 * @param t
 * @param html
 */
function openImportDialog(t, html) {
	if ($('#excelDialog').length < 1) {
		$(
				'<div/>',
				{
					id : 'excelDialog',
					title : '选择上传文件',
					html : "<div id='import'>"
							+ "</br>"
							+ "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
							+ html + "</div>" + "</div>"
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
										var type = document
												.getElementById('type').value;
										if (/^.+\.(csv|CSV)$/.test(file)) {
											$("#type").val($("#type").val());
											var flag=document.getElementById('flag').value;
											checkPercent();
											var action='';
											if ("add"==flag) {
												action=appUrl
												+ "/goal/goalAction!importGoalCsv.jspa";
											}
											if("update"==flag){
												action=appUrl
												+ "/goal/goalAction!importGoalListCsv.jspa";
											}
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
								}, {
									text : '取消',
									handler : function() {
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
//没有用到的方法
function importContractMould(){
	html = '<form id="fileForm" method="post" enctype="multipart/form-data" >'
		+ '&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'
		+ '<table><tr>'
		+ '<td class="head" noWrap>批量导入</td>'
		+ '<td><input type="hidden" id="flag" value="add"/><input type="hidden" name="type" id="type" value="Y"  style="width:200px"/><input type="hidden" name="kunnrGoalType" id="kunnrGoalType" value="C"  style="width:200px"/>'
		 +'   <input type="file" name="uploadFile" id="uploadFile"  style="width:200px"/>'
		+ '</tb></tr><table></form>';
     openImportDialog('导入目标', html);
}
function selectOrgTree() {
	initMaintGoal('选择组织', '/goalAction!orgTreePage.jspa', 400, 460);
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
	form.action = appUrl + '/goal/goalAction!importMatterModel.jspa';
	form.target = "hideFrame";
	form.submit();
}
/**
 * 审核一年的（财务审）
 */
function approveYearGoal(){
	$.messager.confirm('Confirm', '是否确认审核当前财年的目标量?', function(r) {
		if (r) {
			$.messager.progress();
			var form = window.document.forms[0];
			form.action = "goalAction!approveYearGoal.jspa";
			form.target = "hideFrame";
			form.submit();
		}
	});

}
function approveGoal() {
	$.messager.confirm('Confirm', '是否批量审批目标?', function(r) {
		if (r) {
			var rows = $('#datagrid').datagrid('getSelections');
			if (rows == '') {
				$.messager.alert('Tips', '  没有行被选中!');
				return;
			}
			// for (var i = 0; i < rows.length; i++) {
			// if (rows[i].trFlag == 't') {
			// $.messager.alert('Tips', ' 选中行内有项目已审批不能驳回!');
			// return;
			// }
			// }
			$.messager.progress();
			var ids = [];
			for ( var i = 0; i < rows.length; i++) {
				if (rows[i].trFlag == 'S' || rows[i].trFlag == 'B') {
					ids.push(rows[i].ctId);
				}
			}
			$("#ids").val(ids);
			var form = window.document.forms[0];
			form.action = "goalAction!approveGoal.jspa";
			form.target = "hideFrame";
			form.submit();
		}
	});
}

function deleteGoal() {
	var rows = $('#datagrid').datagrid('getSelections');
	if (rows == '') {
		$.messager.alert('Tips', '  没有行被选中!');
		return;
	}
	$.messager.confirm('Confirm', '是否批量删除目标?', function(r) {
		if (r) {
			
			/*for ( var i = 0; i < rows.length; i++) {
				if (rows[i].trFlag == 'T') {
					$.messager.alert('Tips', '  选中行内有项目已审批不能删除!');
					return;
				}
			}*/
			var ids = [];
			for ( var i = 0; i < rows.length; i++) {
				ids.push(rows[i].ctId);
			}
			$("#ids").val(ids);
			var form = window.document.forms[0];
			form.action = "goalAction!deleteGoal.jspa";
			form.target = "hideFrame";
			form.submit();
		}
	});
}

function rejectGoal() {
	$.messager.confirm('Confirm', '是否批量驳回目标?', function(r) {
		if (r) {
			var rows = $('#datagrid').datagrid('getSelections');
			if (rows == '') {
				$.messager.alert('Tips', '  没有行被选中!');
				return;
			}
			for ( var i = 0; i < rows.length; i++) {
				if (rows[i].trFlag == 'B' || rows[i].trFlag == 'S') {
					$.messager.alert('Tips', '只有审核完的目标才能驳回!');
					return;
				}
			}
			var ids = [];
			for ( var i = 0; i < rows.length; i++) {
				if (rows[i].trFlag == 'T') {
					ids.push(rows[i].ctId);
				}
			}
			$("#ids").val(ids);
			var form = window.document.forms[0];
			form.action = "goalAction!rejectGoal.jspa";
			form.target = "hideFrame";
			form.submit();
		}
	});
}
/**
 * 导出模板
 */
function exportMould() {
	var form = window.document.forms[0];
	form.action = appUrl + '/goal/goalAction!exportMouldCsv.jspa';
	form.target = "hideFrame";
	form.submit();
}
/**
 * excel导出
 */
function exportData() {
	//导出数据链接
	$.messager.confirm('Confirm','是否导出数据?',function(r) {
		if (r) {
			var form = window.document.forms[0];
			form.action = appUrl + '/goalAction!exportData.jspa';
			form.submit();
			$.messager.alert('Tips', '正在导出,请稍候!','warning');
		}
	});
}
function clear() {
	//$('#month').val("");
	$('#mark').val("");
	//$('#month').combobox('setValue', '');
	$("#id").combobox('setValue', '');
	$("#wid").combobox('setValue', '');
	$("#mark").combobox('setValue', 'N');
	$("#trFlag").combobox('setValue', '');
}
function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	//queryParams.year = encodeURIComponent($("#year").val());
	//queryParams.month = encodeURIComponent($("#month").val());
	queryParams.startDate = encodeURIComponent($("#startDate").val());
	queryParams.endDate = encodeURIComponent($("#endDate").val());
	queryParams.orgId = encodeURIComponent($("#orgId").val());
	queryParams.custId = encodeURIComponent($("#id").combobox("getValue"));
	queryParams.matter = encodeURIComponent($("#wid").combobox("getValue"));
	queryParams.mark = encodeURIComponent($("#mark").val());
	queryParams.trFlag = encodeURIComponent($("#trFlag").combobox("getValue"));
	queryParams.orgName = encodeURIComponent($("#orgName").val());
	queryParams.brand = $("#brand").combobox("getValue");
	$("#datagrid").datagrid('reload');
	getGoalCount();
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
// function nextUserHtml(obj) {
// nextUser = "<table border='0' cellpadding='0' cellspacing='1'>"
// + "<tr><td class='head' noWrap>处理人列表</td>"
// + "<td><select id='nextUsers'>";
// $.each(obj.result, function(i, v) {
// nextUser += "<option value='" + v.userId + "'>" + v.userName + "----"
// + v.stationName + "</option>";
// });
// nextUser += "</select></td></tr></table>";
// }

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	$.messager.progress('close');
	$('#percentDialog').window('close');
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

document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		search();
		return false;
	}
	return true;
};

function change(){
		$('#orgId').val('');
}

function getGoalCount(){
	$
	.ajax({
		type : "post",
		async : false,
		url : appUrl
				+ "/goalAction!getGoalCountNum.jspa?time="
				+ new Date(),
		data : {
			startDate : encodeURIComponent($("#startDate").val()),
			endDate : encodeURIComponent($("#endDate").val()),
			orgId : encodeURIComponent($("#orgId").val()),
			custId : encodeURIComponent($("#id").combobox("getValue")),
			matter : encodeURIComponent($("#wid").combobox("getValue")),
			mark : encodeURIComponent($("#mark").val()),
			trFlag : encodeURIComponent($("#trFlag").combobox("getValue")),
			orgName : encodeURIComponent($("#orgName").val()),
			kunnrGoalType:$('#kunnrGoalType').val(),
			brand : $("#brand").combobox("getValue")
		},
		success : function(obj) {
			$('#countGoal').val(obj);
		}

	});
}
/**
 * 百分比进度条
 */
function checkPercent() {
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
