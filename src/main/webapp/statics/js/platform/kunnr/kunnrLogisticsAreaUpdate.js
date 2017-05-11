var areaList = [];
$(document)
		.ready(
				function() {
					$('#hideFrame').bind('load', promgtMsg);
					// 销售大区
					$('#vkgrp_1').combogrid({
						panelHeight : 280,
						panelWidth : 420,
						pagination : true,
						method : 'post',
						singleSelect : true,
						url : appUrl + '/salesMsgAction!getT171tJsonList.jspa',
						idField : 'bzirk',
						textField : 'bztxt',
						columns : [ [ {
							field : 'bzirk',
							title : '大区编号',
							width : 100
						}, {
							field : 'bztxt',
							title : '名称',
							width : 120
						} ] ],
						toolbar : '#toolbarVkgrp_1',
						onSelect : function(index, record) {
							$('#bzirk_1').combogrid('setValue', '');
							$('#vkbur_1').combobox('setValue', '');
							$('#bzirk_1').combogrid({
								disabled : false
							});
							loadBzirk1(record.bzirk);
						}
					});

					// 销售省份
					var bzirk = $('#bzirk_1').combogrid("getValue");
					loadBzirk1(bzirk);
					if (bzirk == '') {
						$('#bzirk_1').combogrid({
							disabled : true
						});
						$('#vkbur_1').combobox({
							disabled : true
						});
					}

					function loadBzirk1(bzirk) {
						$('#bzirk_1')
								.combogrid(
										{
											panelHeight : 280,
											panelWidth : 420,
											pagination : true,
											method : 'post',
											singleSelect : true,
											url : appUrl
													+ '/salesMsgAction!getTvgrtListByZdqsf.jspa?bzirk='
													+ $('#vkgrp_1').combogrid(
															'getValue'),
											idField : 'vkgrp',
											textField : 'bezei',
											columns : [ [ {
												field : 'vkgrp',
												title : '省份编号',
												width : 100
											}, {
												field : 'bezei',
												title : '名称',
												width : 120
											} ] ],
											toolbar : '#toolbarBzirk_1',
											onSelect : function(index, record) {
												$('#vkbur_1').combobox(
														'setValue', '');
												$('#vkbur_1').combobox({
													disabled : false
												});
												loadVkbur1(vkbur);
											}
										});
					}
					var vkbur = $('#bzirk_1').combogrid('getValue');
					if (vkbur == '') {
						$('#vkbur_1').combobox({
							disabled : true
						});
					}
					// 销售部门
					function loadVkbur1(vkbur) {
						$('#vkbur_1')
								.combobox(
										{
											url : appUrl
													+ '/salesMsgAction!getTvkbtJsonListByTvbvk.jspa?vkgrp='
													+ $('#bzirk_1').combogrid(
															'getValue'),
											valueField : 'vkbur',
											textField : 'bezei'
										});
					}
					$('#vkgrpSearch_1').searchbox({
						searcher : function(value, name) {
							searchVkgrp(value, 1);
						},
						prompt : '请输入大区名称查询'
					});
					$('#bzirkSearch_1').searchbox({
						searcher : function(value, name) {
							searchBzirk(value, 1);
						},
						prompt : '请输入省份名称查询'
					});

					/**
					 * div 2
					 */
					$('#vkgrp_2').combogrid({
						panelHeight : 280,
						panelWidth : 420,
						pagination : true,
						method : 'post',
						singleSelect : true,
						url : appUrl + '/salesMsgAction!getT171tJsonList.jspa',
						idField : 'bzirk',
						textField : 'bztxt',
						columns : [ [ {
							field : 'bzirk',
							title : '大区编号',
							width : 100
						}, {
							field : 'bztxt',
							title : '名称',
							width : 120
						} ] ],
						toolbar : '#toolbarVkgrp_2',
						onSelect : function(index, record) {
							$('#bzirk_2').combogrid('setValue', '');
							$('#vkbur_2').combobox('setValue', '');
							$('#bzirk_2').combogrid({
								disabled : false
							});
							loadBzirk(record.bzirk);
						}
					});

					// 销售省份
					var bzirk = $('#bzirk_2').combogrid("getValue");
					loadBzirk(bzirk);
					if (bzirk == '') {
						$('#bzirk_2').combogrid({
							disabled : true
						});
						$('#vkbur_2').combobox({
							disabled : true
						});
					}

					function loadBzirk(bzirk) {
						$('#bzirk_2')
								.combogrid(
										{
											panelHeight : 280,
											panelWidth : 420,
											pagination : true,
											method : 'post',
											singleSelect : true,
											url : appUrl
													+ '/salesMsgAction!getTvgrtListByZdqsf.jspa?bzirk='
													+ $('#vkgrp_2').combogrid(
															'getValue'),
											idField : 'vkgrp',
											textField : 'bezei',
											columns : [ [ {
												field : 'vkgrp',
												title : '省份编号',
												width : 100
											}, {
												field : 'bezei',
												title : '名称',
												width : 120
											} ] ],
											toolbar : '#toolbarBzirk_2',
											onSelect : function(index, record) {
												$('#vkbur_2').combobox(
														'setValue', '');
												$('#vkbur_2').combobox({
													disabled : false
												});
												loadVkbur(vkbur);
											}
										});
					}
					var vkbur = $('#bzirk_2').combogrid('getValue');
					if (vkbur == '') {
						$('#vkbur_2').combobox({
							disabled : true
						});
					}
					// 销售部门
					function loadVkbur(vkbur) {
						$('#vkbur_2')
								.combobox(
										{
											url : appUrl
													+ '/salesMsgAction!getTvkbtJsonListByTvbvk.jspa?vkgrp='
													+ $('#bzirk_2').combogrid(
															'getValue'),
											valueField : 'vkbur',
											textField : 'bezei'
										});
					}
					$('#vkgrpSearch_2').searchbox({
						searcher : function(value, name) {
							searchVkgrp(value, 2);
						},
						prompt : '请输入大区名称查询'
					});
					$('#bzirkSearch_2').searchbox({
						searcher : function(value, name) {
							searchBzirk(value, 2);
						},
						prompt : '请输入省份名称查询'
					});
					/**
					 * datagrid
					 */

					$('#datagrid1').datagrid({
						iconCls : 'icon-list',
						title : '经销商原物流区域信息',
						url : appUrl + '/kunnrAction!searchLogisticsArea.jspa',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						rownumbers : true,
						height : '385',
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
							field : 'kunnr',
							title : '客户SAP代码',
							width : 100,
							align : 'center',
							sortable : true
						}, {
							field : 'vkgrp',
							title : '销售大区',
							width : 100,
							hidden : true,
							align : 'center',
							sortable : true
						}, {
							field : 'vkgrpTxt',
							title : '销售大区',
							width : 100,
							align : 'center',
							sortable : true
						}, {
							field : 'bzirk',
							title : '销售省份',
							width : 80,
							align : 'center',
							hidden : true,
							sortable : true
						}, {
							field : 'bzirkTxt',
							title : '销售省份',
							width : 100,
							align : 'center',
							sortable : true
						}, {
							field : 'vkbur',
							title : '销售部门',
							width : 100,
							align : 'center',
							hidden : true,
							sortable : true
						}, {
							field : 'vkburTxt',
							title : '销售部门',
							width : 100,
							align : 'center',
							sortable : true
						} ] ]
					/*
					 * , toolbar : [ "-", { text : '修改', iconCls : 'icon-redo',
					 * handler : function() { check(); } } ]
					 */
					});

					// 分页控件
					var p = $('#datagrid1').datagrid('getPager');
					$(p).pagination({
						pageSize : 50,
						pageList : [ 10, 20, 30 ],
						beforePageText : '第',
						afterPageText : '页    共 {pages} 页',
						displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
					});

					$('#datagrid2').datagrid({
						iconCls : 'icon-list',
						title : '经销商销售区域信息修改',
						// data:areaList,
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						// pagination : true,
						nowrap : true,
						rownumbers : true,
						height : '385',
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
							field : 'kunnr',
							title : '客户SAP代码',
							width : 100,
							align : 'center',
							sortable : true
						}, {
							field : 'vkgrp',
							title : '销售大区',
							width : 100,
							hidden : true,
							align : 'center',
							sortable : true
						}, {
							field : 'vkgrpTxt',
							title : '销售大区',
							width : 100,
							align : 'center',
							sortable : true
						}, {
							field : 'bzirk',
							title : '销售省份',
							width : 100,
							align : 'center',
							hidden : true,
							sortable : true
						}, {
							field : 'bzirkTxt',
							title : '销售省份',
							width : 100,
							align : 'center',
							sortable : true
						}, {
							field : 'vkbur',
							title : '销售部门',
							width : 100,
							align : 'center',
							hidden : true,
							sortable : true
						}, {
							field : 'vkburTxt',
							title : '销售部门',
							width : 100,
							align : 'center',
							sortable : true
						} ] ],
						toolbar : [ "-", {
							text : '确认',
							iconCls : 'icon-save',
							handler : function() {
								save();
							}
						}, "-"/*
								 * , { text : '删除', iconCls : 'icon-remove',
								 * handler : function() { removeRow(); } }, "-"
								 */]
					});

					// 分页控件
					/*
					 * var p = $('#datagrid1').datagrid('getPager');
					 * $(p).pagination({ pageSize : 50, pageList : [ 10, 20, 30 ],
					 * beforePageText : '第', afterPageText : '页 共 {pages} 页',
					 * displayMsg : '当前显示 {from} - {to} 条记录 共 {total} 条记录' });
					 */
				});
// 大区toolbar

function searchVkgrp(value, index) {
	value = encodeURIComponent(value);
	$('#vkgrp_' + index).combogrid({
		url : appUrl + '/salesMsgAction!getT171tJsonList.jspa'
	});
	$('#vkgrp_' + index).combogrid("grid").datagrid('reload');

}
// 对应省份toolbar

function searchBzirk(value, index) {
	value = encodeURIComponent(value);
	$('#bzirk_' + index).combogrid(
			{
				url : appUrl
						+ '/salesMsgAction!getTvgrtListByZdqsf.jspa?bzirk='
						+ $('#vkgrp_' + index).combogrid('getValue')
			});
	$('#bzirk_' + index).combogrid("grid").datagrid('reload');

}

// 查询按钮方法
function search1() {
	var queryParams = $('#datagrid1').datagrid('options').queryParams;
	// queryParams.kunnrCode = encodeURIComponent($("#kunnr").val());
	queryParams.vkgrp = encodeURIComponent($("#vkgrp_1").combogrid('getValue'));
	queryParams.bzirk = encodeURIComponent($("#bzirk_1").combogrid('getValue'));
	queryParams.vkbur = encodeURIComponent($("#vkbur_1").combobox('getValue'));
	$("#datagrid1").datagrid('load');
}
function check() {
	areaList = [];
	var rows = $('#datagrid1').datagrid('getSelections');
	if (rows == '') {
		$.messager.alert('Tips', '请先在左边列表里选择需修改的数据!', 'warning');
		return;
	}
	if ($('#vkgrp_2').combogrid('getValue') == ''
			|| $('#bzirk_2').combogrid('getValue') == ''
			|| $('#vkbur_2').combobox('getValue') == '') {
		$.messager.alert('Tips', '请在此区域先选择销售区域!', 'warning');
		return;
	}
	for ( var i = 0; i < rows.length; i++) {
		areaList.push({
			"kunnr" : rows[i].kunnr,
			"vkgrp" : $('#vkgrp_2').combogrid('getValue'),
			"vkgrpTxt" : $('#vkgrp_2').combogrid('getText'),
			"bzirk" : $('#bzirk_2').combogrid('getValue'),
			"bzirkTxt" : $('#bzirk_2').combogrid('getText'),
			"vkbur" : $('#vkbur_2').combobox('getValue'),
			"vkburTxt" : $('#vkbur_2').combobox('getText')
		});
	}
	$('#areaList').val($.toJSON(areaList));
	$('#datagrid2').datagrid({
		data : areaList
	});
}
function save() {
	var areaLists=[];
	$.messager.confirm('Confirm', '是否确认保存修改的销售区域?', function(r) {
		if (r) {
			var rows = $('#datagrid2').datagrid('getSelections');
			if (rows == '') {
				$.messager.alert('Tips', '请选择数据!', 'warning');
				return;
			}
			$.messager.progress();
			for ( var i = 0; i < rows.length; i++) {
				areaLists.push({
					"kunnr" : rows[i].kunnr,
					"vkgrp" : $('#vkgrp_2').combogrid('getValue'),
					"vkgrpTxt" : $('#vkgrp_2').combogrid('getText'),
					"bzirk" : $('#bzirk_2').combogrid('getValue'),
					"bzirkTxt" : $('#bzirk_2').combogrid('getText'),
					"vkbur" : $('#vkbur_2').combobox('getValue'),
					"vkburTxt" : $('#vkbur_2').combobox('getText')
				});
			}
			$('#areaLists').val($.toJSON(areaLists));
			var form = window.document.forms[0];
			form.action = appUrl + '/kunnrAction!saveLogisticArea.jspa';
			form.target = "hideFrame";
			form.submit();
		}
	});
}
//返回信息
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