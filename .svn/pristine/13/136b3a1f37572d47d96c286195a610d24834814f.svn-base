$(document).ready(function() {
	loadGrid();
	loadGrid1();
	loadGrid2();
	divHide($('#type_ny').combobox('getValue'));
	$('#type_ny').combobox({
		editable : false,
		onSelect : function(d) {
			$('#type_ny').val(d.value);
			divHide(d.value);
			search();
		}
	});
	//divHide($('#conFlag').combobox('getValue'));
	$('#conFlag').combobox({
		editable : false,
		onSelect : function(d) {
			$('#conFlag').val(d.value);
			//divHide(d.value);
			search();
		}
	});
	$('#hideFrame').bind('load', promgtMsg);
});
function divHide(value){
	if('N'==value){
		$('#datagrid').datagrid('showColumn','bezei');
		$('#datagrid').datagrid('showColumn','matter');
		$('#datagrid').datagrid('showColumn','brand');
	}else{
		$('#datagrid').datagrid('hideColumn','bezei');
		$('#datagrid').datagrid('hideColumn','matter');
		$('#datagrid').datagrid('hideColumn','brand');
	}
}
function loadGrid() {
	$("#type_ny").combobox('setValue', 'N');
	$("#conFlag").combobox('setValue', 'N');
	var toolbar = $('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '查询结果',
						url : appUrl + '/goal/goalAction!getGoalReportList.jspa', //不需要kunnrGoalType 所以设为D
						queryParams : {
							orgId : $("#orgId").val(),
							type_ny:$("#type_ny").val(),
							conFlag:$("#conFlag").val()
						},
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						height : height,
						striped : true,
						rownumbers : false,
						columns : [ [
								{
									field : 'orgName',
									title : '组织名',
									width : 210,
									align : 'center'
								},
								{
									field : 'custId',
									title : '经销商ID',
									width : 60,
									align : 'center',
									formatter : function(value, row, rec) {
										var s = row.custId;
										if (s == '' || s == undefined) {
											s = '';
										}
										return '<span style="color:black">'
												+ s + '</span>';
									}
								},
								{
									field : 'custNameZH',
									title : '经销商',
									width : 210,
									align : 'center',
									formatter : function(value, row, rec) {
										var s = row.custNameZH;
										if (s == '' || s == undefined) {
											s = '';
										}
										return '<span style="color:black">'
												+ s + '</span>';
									}
								},
								{
									field : 'theYear',
									title : '目标年',
									width : 50,
									align : 'center'
								},
								{
									field : 'theMonth',
									title : '目标月',
									width : 50,
									align : 'center'
								},
								{
									field : 'brand',
									title : '品牌',
									width : 70,
									align : 'center'
								},{
									field : 'matter',
									title : '品项or四级科目(SKU)',
									width : 60,
									align : 'center'
								},
								{
									field : 'bezei',
									title : '品项or四级科目(SKU)名称',
									width : 170,
									align : 'center'
								},
								{
									field : 'box',
									title : '销售目标量(标箱)',
									width : 120,
									align : 'center',
									formatter : function(value, row, rec) {
										var s = row.box * 1;
										if (s == '' || s == undefined
												|| isNaN(s)) {
											return '0.00';
										}
										return s.toFixed(2);
									}
								},
								{
									field : 'targetNum',
									title : '销售目标量(金额)',
									width : 120,
									align : 'center',
									formatter : function(value, row, rec) {
										var s = row.targetNum * 1;
										if (s == '' || s == undefined
												|| isNaN(s)) {
											return '0.00';
										}
										return s.toFixed(2);
									}
								},
								{
									field : 'contractBox',
									title : '协议目标量(标箱)',
									width : 120,
									align : 'center',
									formatter : function(value, row, rec) {
										var s = row.contractBox * 1;
										if (s == '' || s == undefined
												|| isNaN(s)) {
											return '0.00';
										}
										return s.toFixed(2);
									}
								},
								{
									field : 'contractNum',
									title : '协议目标量(金额)',
									width : 120,
									align : 'center',
									formatter : function(value, row, rec) {
										var s = row.contractNum * 1;
										if (s == '' || s == undefined
												|| isNaN(s)) {
											return '0.00';
										}
										return s.toFixed(2);
									}
								},{
									field : 'status',
									title : '客户状态',
									width : 80,
									align : 'center'
								}, {
									field : 'zt',
									title : '状态',
									width : 80,
									align : 'center',
									formatter : function(value, row, rec) {
										var s = row.contractBox * 1; //经销商目标量
										var v = row.box * 1;       //销售目标量
										if (s == '' || s == undefined|| isNaN(s)) {
											s = '0';
										}
										if (v == '' || v == undefined|| isNaN(v)) {
											v = '0';
										}
										if(s<v){
											
											return '<a href="#" center><font color="red" >异常</font></a>';
										}else{
											return '<a href="#" center><font color="green" >正常</font></a>';
										}

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
		}, {
			field : 'name1',
			title : '客户名称',
			align : 'center',
			width : 100
		}, {
			field : 'mobNumber',
			title : '手机',
			align : 'center',
			width : 150
		} ] ],
		toolbar : '#toolbar2'
	});

}
function loadGrid1() {
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
			width : 200
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
	val = encodeURIComponent(val);
	$('#id').combogrid({
		url : appUrl + '/goal/goalAction!getKunnerJsonList.jspa?value=' + val
	});
	$('#id').combogrid("grid").datagrid('reload');

}

function searcher1(val) {
//	val = encodeURIComponent(val);
//	$('#wid').combogrid({
//		url : appUrl + '/goal/goalAction!getMaterielList.jspa?value=' + val
//	});
//	$('#wid').combogrid("grid").datagrid('reload');
	var queryParams = $('#wid').combogrid("grid").datagrid('options').queryParams;
	queryParams.value = encodeURIComponent(val);
	$('#wid').combogrid("grid").datagrid('reload');
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

// excel导出
function exportData() {
	var form = window.document.forms[0];
	form.action = appUrl + '/goalAction!exportDataContract.jspa';
	form.submit();
	$.messager.alert('Tips', '正在导出,请稍候!', 'warning');
}
function clear() {
	$('#type_ny').val("");
	$("#id").combobox('setValue', '');
	$("#wid").combobox('setValue', '');
	$("#type_ny").combobox('setValue', 'N');
	$('#conFlag').val("");
	$("#conFlag").combobox('setValue', 'N');
}
function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.startDate = encodeURIComponent($("#startDate").val());
	queryParams.endDate = encodeURIComponent($("#endDate").val());
	queryParams.orgId = encodeURIComponent($("#orgId").val());
	queryParams.custId = encodeURIComponent($("#id").combobox("getValue"));
	queryParams.matter = encodeURIComponent($("#wid").combobox("getValue"));
	queryParams.type_ny = encodeURIComponent($("#type_ny").val());
	queryParams.conFlag = encodeURIComponent($("#conFlag").val());
	queryParams.orgName = encodeURIComponent($("#orgName").val());
	queryParams.brand = $("#brand").combobox("getValue");
	$("#datagrid").datagrid('load');
	
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

document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		search();
		return false;
	}
	return true;
};

function change() {
	$('#orgId').val('');
}
