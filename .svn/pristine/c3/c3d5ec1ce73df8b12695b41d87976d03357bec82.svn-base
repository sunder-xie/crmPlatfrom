var smsList = [];
var smsList2 = [];
var D_M_flag = '';
$(document)
		.ready(
				function() {
					$('#hideFrame').bind('load', promgtMsg);
					$('#isOffice').combobox({
						multiple : true,
						valueField : 'status',
						textField : 'text',
						data : [ {
							status : 'Y',
							text : '员工'
						}, {
							status : 'N',
							text : '经销商'
						} ],
						multiple : false,
						editable : false,
						required : true,
						panelHeight : 'auto',
						onLoadSuccess : function() {
							$('#isOffice').combobox("setValue", 'Y');
						}

					});

					$('#stationIds')
							.combogrid(
									{
										panelWidth : 480,
										idField : 'stationId',
										textField : 'stationName',
										pagination : true,//
										rownumbers : true,//
										collapsible : false,//
										fit : true,//
										method : 'post',
										multiple : false,
										editable : false,
										url : appUrl
												+ '/smsAction!getSelectedStationsJSON.jspa?orgId='
												+ $('#orgId').val(),
										columns : [ [ {
											field : 'id',
											title : 'POSID',
											width : 100,
											hidden : true
										}, {
											field : 'stationId',
											title : '岗位ID',
											width : 60
										}, {
											field : 'stationName',
											title : '岗位名称',
											width : 150
										}, {
											field : 'orgName',
											title : '公司名',
											width : 120
										} ] ],
										toolbar : '#toolbar'

									});
					var q = $('#stationIds').combogrid("grid").datagrid();
					$('#stationIds').combo({
						multiple : true
					});
					$(q).pagination({
						pageSize : 10,
						pageList : [ 10, 20, 30 ],
						beforePageText : '第',
						afterPageText : '页    共 {pages} 页',
						displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
					});

					// 我的个人编组
					$('#priGroups')
							.combogrid(
									{
										panelWidth : 480,
										// idField:'stationId',
										textField : 'groupName',
										// pagination : true,//
										rownumbers : true,//
										collapsible : false,//
										fit : true,//
										method : 'post',
										multiple : false,
										editable : false,
										url : appUrl
												+ '/smsAction!getGroups.jspa?isPubGroup=N',
										columns : [ [ {
											field : 'id',
											title : 'POSID',
											width : 100,
											hidden : true
										}, {
											field : 'groupName',
											title : '编组名称',
											width : 300
										}, ] ],
										onSelect : function(rowIndex, rowData) {
											// $('div.datagrid-toolbar
											// a').eq(6).show();
											// $("#groupName").val(rowData.groupName);
											$.messager.progress();
											var groupName = encodeURI(encodeURI(rowData.groupName));
											$
													.ajax({
														type : "post",
														data : "groupName="
																+ groupName,
														url : appUrl
																+ "/smsAction!getSelectedGroupInfo.jspa?isPubGroup=N",
														success : function(data) {
															addSmsList2(data);
															$('#pubGroups')
																	.combogrid(
																			"clear");
															$('#myPubGroups')
																	.combogrid(
																			"clear");
														}
													});
											// var pubGroups =
											// $('#pubGroups').combogrid("getText");
											// var myPubGroups =
											// $('#myPubGroups').combogrid("getText");
											// if(pubGroups||myPubGroups){
											// // $('div.datagrid-toolbar
											// a').eq(6).hide();
											// $("#groupName").val('');
											// }
										}
									// toolbar:'#toolbar'

									});
					// var q = $('#priGroups').combogrid("grid").datagrid();
					// $('#priGroups').combo({
					// multiple:true
					// });
					// $(q).pagination({
					// pageSize : 10,
					// pageList : [ 10, 20, 30 ],
					// beforePageText : '第',
					// afterPageText : '页 共 {pages} 页',
					// displayMsg : '当前显示 {from} - {to} 条记录 共 {total} 条记录'
					// });

					// 我的公共编组
					$('#myPubGroups')
							.combogrid(
									{
										panelWidth : 480,
										// idField:'stationId',
										textField : 'groupName',
										// pagination : true,//
										rownumbers : true,//
										collapsible : false,//
										fit : true,//
										method : 'post',
										multiple : false,
										editable : false,
										url : appUrl
												+ '/smsAction!getGroups.jspa?isPubGroup=MY',
										columns : [ [ {
											field : 'id',
											title : 'POSID',
											width : 100,
											hidden : true
										}, {
											field : 'groupName',
											title : '编组名称',
											width : 300
										}, ] ],
										onSelect : function(rowIndex, rowData) {
											// $('div.datagrid-toolbar
											// a').eq(6).show();
											// $("#groupName").val(rowData.groupName);
											$.messager.progress();
											var groupName = encodeURI(encodeURI(rowData.groupName));
											$
													.ajax({
														type : "post",
														data : "groupName="
																+ groupName,
														url : appUrl
																+ "/smsAction!getSelectedGroupInfo.jspa?isPubGroup=MY",
														success : function(data) {
															addSmsList2(data);
															$('#priGroups')
																	.combogrid(
																			"clear");
															$('#pubGroups')
																	.combogrid(
																			"clear");
														}
													});
											// var priGroups =
											// $('#priGroups').combogrid("getText");
											// var pubGroups =
											// $('#pubGroups').combogrid("getText");
											// if(priGroups||pubGroups){
											// // $('div.datagrid-toolbar
											// a').eq(6).hide();
											// $("#groupName").val('');
											// }
										}
									// toolbar:'#toolbar'

									});
					// var q = $('#myPubGroups').combogrid("grid").datagrid();
					// $('#myPubGroups').combo({
					// multiple:true
					// });
					// $(q).pagination({
					// pageSize : 10,
					// pageList : [ 10, 20, 30 ],
					// beforePageText : '第',
					// afterPageText : '页 共 {pages} 页',
					// displayMsg : '当前显示 {from} - {to} 条记录 共 {total} 条记录'
					// });

					// 所有公共编组
					$('#pubGroups')
							.combogrid(
									{
										panelWidth : 480,
										// idField:'stationId',
										textField : 'groupName',
										// pagination : true,//
										rownumbers : true,//
										collapsible : false,//
										fit : true,//
										method : 'post',
										multiple : false,
										editable : false,
										url : appUrl
												+ '/smsAction!getGroups.jspa?isPubGroup=Y',
										columns : [ [ {
											field : 'id',
											title : 'POSID',
											width : 100,
											hidden : true
										}, {
											field : 'groupName',
											title : '编组名称',
											width : 300
										}, ] ],
										onSelect : function(rowIndex, rowData) {
											// $('div.datagrid-toolbar
											// a').eq(6).show();
											// $("#groupName").val(rowData.groupName);
											var groupName = encodeURI(encodeURI(rowData.groupName));
											$.messager.progress();
											$
													.ajax({
														type : "post",
														data : "groupName="
																+ groupName,
														url : appUrl
																+ "/smsAction!getSelectedGroupInfo.jspa?isPubGroup=Y",
														success : function(data) {
															addSmsList2(data);
															$('#priGroups')
																	.combogrid(
																			"clear");
															$('#myPubGroups')
																	.combogrid(
																			"clear");
														}
													});
											// var priGroups =
											// $('#priGroups').combogrid("getText");
											// var myPubGroups =
											// $('#myPubGroups').combogrid("getText");
											// if(priGroups||myPubGroups){
											// // $('div.datagrid-toolbar
											// a').eq(6).hide();
											// $("#groupName").val('');
											// }
										}
									// toolbar:'#toolbar'

									});
					// var q = $('#pubGroups').combogrid("grid").datagrid();
					// $('#pubGroups').combo({
					// multiple:true
					// });
					// $(q).pagination({
					// pageSize : 10,
					// pageList : [ 10, 20, 30 ],
					// beforePageText : '第',
					// afterPageText : '页 共 {pages} 页',
					// displayMsg : '当前显示 {from} - {to} 条记录 共 {total} 条记录'
					// });

					/**
					 * datagrid
					 */

					$('#datagrid1').datagrid({
						iconCls : 'icon-list',
						title : '',
						url : appUrl + '/smsAction!searchEmpInfo.jspa',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						rownumbers : true,
						height : '385',
						width : 'auto',
						queryParams : {
							orgId : '50919',
							bhxjFlag : 'C'
						},
						columns : [ [ {
							field : 'ck',
							checkbox : true
						}, {
							field : 'id',
							title : '编号ID',
							width : 60,
							align : 'center',
							sortable : true,
							hidden : true
						}, {
							field : 'userId',
							title : '员工ID',
							width : 60,
							align : 'center',
							sortable : true,
							hidden : true
						}, {
							field : 'kunnr',
							title : '经销商编号',
							width : 60,
							align : 'center',
							sortable : true,
							hidden : true
						}, {
							field : 'userName',
							title : '名称',
							width : 100,
							align : 'center',
							sortable : true
						}, {
							field : 'mobile',
							title : '电话号码',
							width : 100,
							align : 'center',
							sortable : true
						}, {
							field : 'orgName',
							title : '组织',
							width : 80,
							align : 'center',
							sortable : true
						}, {
							field : 'orgId',
							title : '组织Id',
							width : 80,
							align : 'center',
							hidden : true,
							sortable : true
						}, {
							field : 'stationName',
							title : '岗位',
							width : 100,
							align : 'center',
							sortable : true
						}, {
							field : 'stationId',
							title : '岗位Id',
							width : 100,
							align : 'center',
							hidden : true,
							sortable : true
						}, {
							field : 'isOffice',
							title : '人员类别',
							width : 80,
							align : 'center',
							sortable : true
						} ] ],
						toolbar : [ "-", {
							text : '添加',
							iconCls : 'icon-save',
							handler : function() {
								check();
							}
						}, "-", {
							text : '显示全部',
							handler : function() {
								$('#datagrid1').datagrid({
									pagination : false
								});
							}
						}, "-" ]
					/*
					 * , toolbar : [ "-", { text : '修改', iconCls : 'icon-redo',
					 * handler : function() { check(); } } ]
					 */
					});

					// 分页控件
					var p = $('#datagrid1').datagrid('getPager');
					$(p).pagination({
						pageSize : 10,
						pageList : [ 10, 20, 30 ],
						beforePageText : '第',
						afterPageText : '页    共 {pages} 页',
						displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
					});

					$('#datagrid2').datagrid({
						iconCls : 'icon-list',
						title : '',
						// data:areaList,
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						// pagination : true,
						nowrap : true,
						rownumbers : true,
						height : '333',
						width : 'auto',
						columns : [ [ {
							field : 'ck',
							checkbox : true
						}, {
							field : 'id',
							title : '编号ID',
							width : 60,
							align : 'center',
							sortable : true,
							hidden : true
						}, {
							field : 'userId',
							title : '员工ID',
							width : 60,
							align : 'center',
							sortable : true,
							hidden : true
						}, {
							field : 'kunnr',
							title : '经销商编号',
							width : 60,
							align : 'center',
							sortable : true,
							hidden : true
						}, {
							field : 'userName',
							title : '名称',
							width : 100,
							align : 'center',
							sortable : true
						}, {
							field : 'mobile',
							title : '电话号码',
							width : 100,
							align : 'center',
							sortable : true
						}, {
							field : 'orgName',
							title : '组织',
							width : 80,
							align : 'center',
							sortable : true
						}, {
							field : 'orgId',
							title : '组织Id',
							width : 80,
							align : 'center',
							hidden : true,
							sortable : true
						}, {
							field : 'stationName',
							title : '岗位',
							width : 100,
							align : 'center',
							sortable : true
						}, {
							field : 'stationId',
							title : '岗位Id',
							width : 100,
							align : 'center',
							hidden : true,
							sortable : true
						}, {
							field : 'isOffice',
							title : '人员类别',
							width : 80,
							align : 'center',
							sortable : true
						} ] ],
						toolbar : [ "-", {
							text : '确认发送',
							iconCls : 'icon-save',
							handler : function() {
								save();
							}
						}, "-", {
							text : '删除',
							iconCls : 'icon-remove',
							handler : function() {
								removeRow();
							}
						}, "-", {
							text : '新增到个人编组',
							handler : function() {
								saveGroup('N');
							}
						}, "-", {
							text : '新增到公共编组',
							handler : function() {
								saveGroup('Y');
							}
						}, "-", {
							text : '保存到编组',
							handler : function() {
								D_M_flag = 'M'
								selectGroup();
							}
						}, "-", {
							text : '删除编组',
							handler : function() {
								D_M_flag = 'D'
								selectGroup();
							}
						}, ],
						onLoadSuccess : function(data) {
							$.messager.progress('close');
						}
					});

					// $('div.datagrid-toolbar a').eq(6).hide();
					// $('div.datagrid-toolbar a').eq(7).hide();

					// 分页控件
					/*
					 * var p = $('#datagrid1').datagrid('getPager');
					 * $(p).pagination({ pageSize : 50, pageList : [ 10, 20, 30 ],
					 * beforePageText : '第', afterPageText : '页 共 {pages} 页',
					 * displayMsg : '当前显示 {from} - {to} 条记录 共 {total} 条记录' });
					 */
				});

function check() {
	smsList = [];
	var rows = $('#datagrid1').datagrid('getSelections');
	if (rows == '') {
		$.messager.alert('Tips', '请先在左边列表里选择需添加的数据!', 'warning');
		return;
	}
	smsList = smsList2;
	for (var i = 0; i < rows.length; i++) {
		smsList.push({
			"userId" : rows[i].userId,
			"kunnr" : rows[i].kunnr,
			"userName" : rows[i].userName,
			"mobile" : rows[i].mobile,
			"orgName" : rows[i].orgName,
			"orgId" : rows[i].orgId,
			"stationName" : rows[i].stationName,
			"stationId" : rows[i].stationId,
			"isOffice" : rows[i].isOffice,
		});
	}
	for (var i = 0; i < smsList.length; i++) // 外循环是循环的次数
	{
		for (var j = smsList.length - 1; j > i; j--) // 内循环是 外循环一次比较的次数
		{

			if (smsList[i].mobile == smsList[j].mobile) {
				smsList.splice(j, 1);
			}

		}
	}
	smsList2 = smsList;
	$('#smsList').val($.toJSON(smsList));
	$('#datagrid2').datagrid({
		data : smsList
	});
}

function addSmsList2(rows) {
	smsList = [];
	// smsList = smsList2;
	for (var i = 0; i < rows.length; i++) {
		smsList.push({
			"userId" : rows[i].userId,
			"kunnr" : rows[i].kunnr,
			"userName" : rows[i].userName,
			"mobile" : rows[i].mobile,
			"orgName" : rows[i].orgName,
			"orgId" : rows[i].orgId,
			"stationName" : rows[i].stationName,
			"stationId" : rows[i].stationId,
			"isOffice" : rows[i].isOffice,
		});
	}
	for (var i = 0; i < smsList.length; i++) // 外循环是循环的次数
	{
		for (var j = smsList.length - 1; j > i; j--) // 内循环是 外循环一次比较的次数
		{

			if (smsList[i].mobile == smsList[j].mobile) {
				smsList.splice(j, 1);
			}

		}
	}
	smsList2 = smsList;
	$('#smsList').val($.toJSON(smsList));
	$('#datagrid2').datagrid({
		data : smsList
	});
}

function save() {
	var sendInfoList = [];
	var rows = $('#datagrid2').datagrid('getSelections');
	if (rows == '') {
		$.messager.alert('Tips', '请选择数据!', 'warning');
		return;
	}
	if (trim($('#content').val()) == '') {
		$.messager.alert('Tips', '发送内容不可为空!', 'warning');
		return;
	}
	$.messager.confirm('Confirm', '是否确认发送短信?', function(r) {
		if (r) {
			$.messager.progress();
			for (var i = 0; i < rows.length; i++) {
				sendInfoList.push({
					"mobile" : rows[i].mobile,
					// "mobile" : "18698560692",
					"content" : $('#content').val(),
				});
			}
			$('#sendInfoList').val($.toJSON(sendInfoList));
			var form = window.document.forms[0];
			form.action = appUrl + '/smsAction!sendMessage.jspa';
			form.target = "hideFrame";
			form.submit();
		}
	});
}

function saveGroup(isPubGroup) {
	var groupList = [];
	var rows = $('#datagrid2').datagrid('getSelections');
	if (rows == '') {
		$.messager.alert('Tips', '请选择数据!', 'warning');
		return;
	}
	if (trim($('#groupName').val()) == '') {
		$.messager.alert('Tips', '新增编组名不可为空!', 'warning');
		return;
	}
	$.messager.confirm('Confirm', '是否确认保存分组?', function(r) {
		if (r) {
			$.messager.progress();
			for (var i = 0; i < rows.length; i++) {
				groupList.push({
					"userId" : rows[i].userId,
					"kunnr" : rows[i].kunnr,
					"userName" : rows[i].userName,
					"isPubGroup" : isPubGroup,
					"opUserId" : $("#opUserId").val(),
					"groupName" : $("#groupName").val()
				});
			}
			$('#groupList').val($.toJSON(groupList));
			var form = window.document.forms[0];
			form.action = appUrl + '/smsAction!saveGroup.jspa?isPubGroup='
					+ isPubGroup;
			form.target = "hideFrame";
			form.submit();
		}
	});
}

function search() {
	var queryParams = $('#datagrid1').datagrid('options').queryParams;
	queryParams.stationId = $('#stationIds').combogrid("getValue");
	queryParams.isOffice = $("#isOffice").combobox("getValue");
	queryParams.orgId = $("#orgId").val();
	queryParams.userName = encodeURIComponent($("#userName").val());
	if (document.getElementById("bhxjFlag").checked) {
		queryParams.bhxjFlag = $("#bhxjFlag").val();
	} else {
		queryParams.bhxjFlag = '';
	}
	$('#datagrid1').datagrid({
		pagination : true
	});
	// $("#datagrid1").datagrid('load');
}

function searcher(val) {
	val = encodeURIComponent(val);
	$('#stationIds').combogrid(
			{
				url : appUrl
						+ '/smsAction!getSelectedStationsJSON.jspa?stationId='
						+ val + "&orgId=" + $('#orgId').val()
			});
	$('#stationIds').combogrid("grid").datagrid('reload');

}

function removeRow() {
	var rows = $('#datagrid2').datagrid('getSelections');
	if (rows == '') {
		$.messager.alert('Tips', '请选择数据!', 'warning');
		return;
	}
	for (var i = 0; i < smsList2.length; i++) // 外循环是循环的次数
	{
		for (var j = 0; j < rows.length; j++) // 内循环是 外循环一次比较的次数
		{

			if (smsList[i].mobile == rows[j].mobile) {
				smsList2.splice(i, 1);
			}

		}
	}
	$('#smsList').val(smsList2);
	$('#datagrid2').datagrid({
		data : smsList2
	});
}

function clearValue() {
	$('#orgId').val('50919');
	$('#orgName').val('香飘飘食品股份有限公司');
	$('#isOffice').combobox("setValue", 'Y');
	$('#userName').val('');
	isSelectedOrg();
}

// 返回信息
function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	$.messager.progress('close');
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info', function() {
			window.location.reload();
		});
	}
}
// 创建窗口对象
function openWindow(title, url, WWidth, WHeight) {
	var url = appUrl + url;
	var $win = $("#maintModelAtt")
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
						minimizable : false,
						maximizable : true,
						collapsible : true,
						draggable : true
					});

	$win.window('open');

}
// 关闭窗口
function closeWindow() {
	$("#maintModelAtt").window('close');
}

function selectOrgTree() {
	openWindow('选择组织', '/customerAction!orgTreePage.jspa', 400, 460);
}

function selectGroup() {
	var rows = $('#datagrid2').datagrid('getSelections');
	if (rows == '' && D_M_flag == 'M') {
		$.messager.alert('Tips', '请选择数据!', 'warning');
		return;
	}
	openWindow('选择要修改的编组', '/smsAction!selectGroup.jspa', 400, 460);
}

function isSelectedOrg() {
	$('#stationIds').combogrid('clear');
	$('#stationIds').combogrid(
			{
				url : appUrl + '/smsAction!getSelectedStationsJSON.jspa?orgId='
						+ $('#orgId').val()
			});
	$('#stationIds').combogrid("grid").datagrid('reload');
}
/**
 * 组织树选组织返回到输入框
 * 
 * @param selectedId
 * @param selectedName
 */
function returnValue(selectedId, selectedName) {
	document.getElementById('orgId').value = selectedId;
	document.getElementById('orgName').value = selectedName;
	isSelectedOrg();
}
/**
 * 组织树选择完之后关闭
 */
function closeOrgTree() {
	closeWindow();
}

/**
 * 去空格
 */
function trim(str) {
	return str.replace(/(^\s*)|(\s*$)/g, "");
}

function cancel() {
	window.location.reload();
}

function returnGroup() {
	if (D_M_flag == 'M') {
		modifyGroup();
	} else if (D_M_flag == 'D') {
		deleteGroup();
	}
}

function modifyGroup() {
	var selectGroupName = $("#selectGroupName").val()
	var selectIsPubGroup = $("#selectIsPubGroup").val()
	var groupList = [];
	$.messager.confirm('Confirm', '是否确认修改分组?', function(r) {
		if (r) {
			$.messager.progress();
			var rows = $('#datagrid2').datagrid('getSelections');
			for (var i = 0; i < rows.length; i++) {
				groupList.push({
					"userId" : rows[i].userId,
					"kunnr" : rows[i].kunnr,
					"userName" : rows[i].userName,
					"isPubGroup" : selectIsPubGroup,
					"opUserId" : $("#opUserId").val(),
					"groupName" : selectGroupName
				});
			}
			$('#groupList').val($.toJSON(groupList));
			var form = window.document.forms[0];
			form.action = appUrl + '/smsAction!modifyGroup.jspa';
			form.target = "hideFrame";
			form.submit();
		}
	});
}

function deleteGroup() {
	$.messager.confirm('Confirm', '是否确认删除分组?', function(r) {
		if (r) {
			$.messager.progress();
			var form = window.document.forms[0];
			form.action = appUrl + '/smsAction!deleteGroup.jspa';
			form.target = "hideFrame";
			form.submit();
		}
	});
}
