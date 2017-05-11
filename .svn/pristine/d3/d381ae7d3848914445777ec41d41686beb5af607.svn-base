var nextUser;
var processInstanceId;
var addressCount = 1;
var brandCount = 1;

$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);
	if($('#type').val()==''){
		$('#type').val('Z');
	}
	$('#up_list')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '查询结果',
						url : appUrl + '/custBatchModifyAction!searchCustlistJson.jspa',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : false,
						nowrap : true,
						height : height,
						striped : true,
						columns : [ [
								{
									field : 'custNumber',
									title : '客户编号',
									width : setColumnWidth(0.1),
									align : 'center'
								},
								{
									field : 'custName',
									title : '客户名称',
									width : setColumnWidth(0.2),
									align : 'center'
								},
								{
									field : 'custKunnr',
									title : '经销商sap编号',
									width : setColumnWidth(0.1),
									align : 'center'
								},
								{
									field : 'remark',
									title : '经销商名称',
									width : setColumnWidth(0.2)
								},{
									field : 'custParent',
									title : '上游客户编号',
									width : setColumnWidth(0.1),
									align : 'center'
								},
								{
									field : 'custParentName',
									title : '上游客户名称',
									width : setColumnWidth(0.2)
								}] ]
					});
		$('#qd_list')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '查询结果',
						url : appUrl + '/custBatchModifyAction!searchqdCustlistJson.jspa',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : false,
						nowrap : true,
						height : height,
						striped : true,
						columns : [ [
								{
									field : 'custNumber',
									title : '客户编号',
									width : setColumnWidth(0.2),
									align : 'center'
								},
								{
									field : 'custName',
									title : '客户名称',
									width : setColumnWidth(0.2),
									align : 'center'
								},
								{
									field : 'channelId',
									title : '渠道id',
									width : setColumnWidth(0.2),
									align : 'center'
								},
								{
									field : 'remark',
									title : '渠道名称',
									width : setColumnWidth(0.2)
								}] ]
					});
//	// 分页控件
//	var p = $('#qd_list').datagrid('getPager');
//	$(p).pagination({
//		beforePageText : '第',
//		afterPageText : '页    共 {pages} 页',
//		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
//	});
//	var p = $('#up_list').datagrid('getPager');
//	$(p).pagination({
//		beforePageText : '第',
//		afterPageText : '页    共 {pages} 页',
//		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
//	});
	
});

// 提交申请
function submit() {
	
}

// 取消
function cancel() {
	self.location.reload(true);
}

function search() {
	var queryParams = $('#up_list').datagrid('options').queryParams;
	queryParams.userId=0;
	$("#up_list").datagrid('load');
}
function searchqd() {
	var queryParams = $('#qd_list').datagrid('options').queryParams;
	queryParams.userId=0;
	$("#qd_list").datagrid('load');
}
function search() {
	var queryParams = $('#up_list').datagrid('options').queryParams;
	queryParams.userId=0;
	$("#up_list").datagrid('load');
}
function importGoal(){
	$.messager
			.confirm(
					'Confirm',
					'是否提交模版信息?',
					function(r) {
						if (r) {
                                                        $.messager.progress();
							var form = window.document.forms[0];
							form.action = appUrl
									+ '/custBatchModifyAction!importCsvUp.jspa';
							form.target = "hideFrame";
							form.submit();

						}
					});
}
function importGoaldb(){
	$.messager
			.confirm(
					'Confirm',
					'是否提交导入的模版信息?',
					function(r) {
						if (r) {
                                                        $.messager.progress();
							var form = window.document.forms[0];
							form.action = appUrl
									+ '/custBatchModifyAction!importCsvUpDb.jspa';
							form.target = "hideFrame";
							form.submit();

						}
					});
}
function importQd(){
	$.messager
			.confirm(
					'Confirm',
					'是否提交渠道模版信息?',
					function(r) {
						if (r) {
                                                        $.messager.progress();
							var form = window.document.forms[0];
							form.action = appUrl
									+ '/custBatchModifyAction!importCsvQd.jspa';
							form.target = "hideFrame";
							form.submit();
						}
					});
}
function importQdDb(){
	$.messager
			.confirm(
					'Confirm',
					'是否提交导入的渠道模版信息?',
					function(r) {
						if (r) {
                                                        $.messager.progress();
							var form = window.document.forms[0];
							form.action = appUrl
									+ '/custBatchModifyAction!importCsvQdDb.jspa';
							form.target = "hideFrame";
							form.submit();
						}
					});
}

function importCustKunnr(){
	$.messager
			.confirm(
					'Confirm',
					'确认修改?',
					function(r) {
						if (r) {
                                                        $.messager.progress();
							var form = window.document.forms[0];
							form.action = appUrl
									+ '/custBatchModifyAction!moveCustomer.jspa';
							form.target = "hideFrame";
							form.submit();
						}
					});
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}

function exportQdCsv(){
$.messager
			.confirm(
					'Confirm',
					'是否导出CSV渠道模版信息?',
					function(r) {
						if (r) {
							var form = window.document.forms[0];
							form.action = appUrl
									+ '/custBatchModifyAction!exportQdCSV.jspa';
							form.target = "hideFrame";
							form.submit();

						}
					});
}
function exportQdTypeCsv(){
$.messager
			.confirm(
					'Confirm',
					'是否导出渠道基础信息?',
					function(r) {
						if (r) {
							var form = window.document.forms[0];
							form.action = appUrl
									+ '/custBatchModifyAction!exportQdTypeCSV.jspa';
							form.target = "hideFrame";
							form.submit();

						}
					});
}
function exportModifyUpCustomer(){
$.messager
			.confirm(
					'Confirm',
					'是否导出模版信息?',
					function(r) {
						if (r) {
							var form = window.document.forms[0];
							form.action = appUrl
									+ '/custBatchModifyAction!exportModifyUpCustomer.jspa';
							form.target = "hideFrame";
							form.submit();

						}
					});
}
function exportCsv(){
$.messager
			.confirm(
					'Confirm',
					'是否导出CSV模版信息?',
					function(r) {
						if (r) {
							var form = window.document.forms[0];
							form.action = appUrl
									+ '/custBatchModifyAction!exportToCSV.jspa';
							form.target = "hideFrame";
							form.submit();

						}
					});
}
function exportqdXlsx(){
$.messager
			.confirm(
					'Confirm',
					'是否导出模版信息?',
					function(r) {
						if (r) {
							var form = window.document.forms[0];
							form.action = appUrl
									+ '/custBatchModifyAction!exportqdXlsx.jspa';
							form.target = "hideFrame";
							form.submit();

						}
					});
}
function exportXlsx(){
$.messager
			.confirm(
					'Confirm',
					'是否导出模版信息?',
					function(r) {
						if (r) {
							var form = window.document.forms[0];
							form.action = appUrl
									+ '/custBatchModifyAction!exportXlsx.jspa';
							form.target = "hideFrame";
							form.submit();

						}
					});
}

function exportModifyCustomer(){
$.messager
			.confirm(
					'Confirm',
					'是否导出模版信息?',
					function(r) {
						if (r) {

							var form = window.document.forms[0];
							form.action = appUrl
									+ '/custBatchModifyAction!exportModifyCustomer.jspa';
							form.target = "hideFrame";
							form.submit();
						}
					});
}
/**
 * 门店重要度批量修改模板下载
 * xg.chen version 1.0
 */
function exportCustomerImprotanceCsv(){
	$.messager.confirm('Confirm','是否导出CSV模版信息?',
		function(r) {
			if (r) {
				var form = window.document.forms[0];
				form.action = appUrl
						+ '/customerAction!exportCustomerImprotanceCsv.jspa';
				form.target = "hideFrame";
				form.submit();

			}
		});
}
/**
 * 门店重要度批量修改数据导入
 * xg.chen version 1.0
 */
function importCustomerImprotance(){
	$.messager.confirm('Confirm','确认修改?',
		function(r) {
			if (r) {
                $.messager.progress();
				var form = window.document.forms[0];
				form.action = appUrl
						+ '/customerAction!importCustomerImprotanceCsv.jspa';
				form.target = "hideFrame";
				form.submit();
			}
		});
}
function exportCustomerEmpCsv(){
	$.messager
				.confirm(
						'Confirm',
						'是否导出CSV模版信息?',
						function(r) {
							if (r) {
								var form = window.document.forms[0];
								form.action = appUrl
										+ '/customerAction!exportCustomerEmpCsv.jspa';
								form.target = "hideFrame";
								form.submit();

							}
						});
}

function importCustomerEmp(){
	$.messager
			.confirm(
					'Confirm',
					'确认修改?',
					function(r) {
						if (r) {
                            $.messager.progress();
							var form = window.document.forms[0];
							form.action = appUrl
									+ '/customerAction!importCustomerEmpCsv.jspa';
							form.target = "hideFrame";
							form.submit();
						}
					});
}

// 返回信息
function promgtMsg() {
        $.messager.progress('close');
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info',function(){
		search();
		searchqd();
		});
		
	}
}


