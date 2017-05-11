$(document).ready(function() {

	// 性别
	$('#sex').combobox({
		valueField : 'value',
		textField : 'text',
		data : [ {
			value : '',
			text : '请选择'
		} ,{
			value : 'male',
			text : '男'
		}, {
			value : 'female',
			text : '女'
		} ]
	});
	// 客户分类 渠道
	$('#channelId').combobox({
		url : appUrl + '/kunnrAction!getChannel.jspa?channelName='+'K',
		valueField : 'channelId',
		textField : 'channelName'
	});

	// 上级经销商查询
	$('#konzs').combogrid({
		panelHeight : 280,
		panelWidth : 420,
		pagination : true,
		method : 'post',
		singleSelect : true,
		url : appUrl + '/kunnrAction!kunnrSearch.jspa?orgId='+$('#orgId').val()+'&bhxjFlag='+'C',
		idField : 'kunnr',
		textField : 'name1',
		columns : [ [ {
			field : 'kunnr',
			title : '经销商编号',
			width : 120
		}, {
			field : 'name1',
			title : '名称',
			width : 250
		} ] ]
	});

	// 销售范围
	$('#vkorg').combogrid({
		panelHeight : 280,
		panelWidth : 420,
		pagination : true,
		method : 'post',
		singleSelect : true,
		url : appUrl + '/sales/salesAction!getTvkbzJsonList.jspa',
		idField : 'vkorg',
		textField : 'vkorg',
		columns : [ [ {
			field : 'vkorg',
			title : '销售组织',
			width : 100
		}, {
			field : 'spart',
			title : '产品组',
			width : 80
		}, {
			field : 'vtweg',
			title : '分销渠道',
			width : 100
		} ] ]
	});

	// 销售大区
	$('#vkgrp').combobox({
		url : appUrl + '/sales/salesAction!getTvkgrJsonList.jspa',
		valueField : 'vkgrp',
		textField : 'vkgrp'
	});

	// 销售省份
	$('#bzirk').combogrid({
		panelHeight : 280,
		panelWidth : 420,
		pagination : false,
		method : 'post',
		singleSelect : true,
		url : appUrl + '/sales/salesAction!getT171tJsonList.jspa',
		idField : 'bzirk',
		textField : 'bztxt',
		columns : [ [ {
			field : 'bzirk',
			title : '省份编号',
			width : 100
		}, {
			field : 'bztxt',
			title : '名称',
			width : 120
		} ] ]
	});

	// 销售部门
	$('#vkbur').combobox({
		url : appUrl + '/sales/salesAction!getTvkbtJsonList.jspa',
		valueField : 'vkbur',
		textField : 'bezei'
	});

	// 城市
	$('#kvgr1').combogrid({
		panelHeight : 280,
		panelWidth : 420,
		pagination : true,
		method : 'post',
		singleSelect : true,
		url : appUrl + '/sales/salesAction!getTvv1tJsonList.jspa',
		idField : 'kvgr1',
		textField : 'kvgr1',
		columns : [ [ {
			field : 'kvgr1',
			title : '城市编号',
			width : 100
		}, {
			field : 'bezei',
			title : '名称',
			width : 120
		} ] ]

	});

	// 区域
	$('#kvgr2').combogrid({
		panelHeight : 280,
		panelWidth : 420,
		pagination : true,
		method : 'post',
		singleSelect : true,
		url : appUrl + '/sales/salesAction!getTvv2tJsonList.jspa',
		idField : 'kvgr2',
		textField : 'kvgr2',
		columns : [ [ {
			field : 'kvgr2',
			title : '区域编号',
			width : 100
		}, {
			field : 'bezei',
			title : '名称',
			width : 120
		} ] ]
	});

	// 公司
	$('#bukrs').combogrid({
		panelHeight : 280,
		panelWidth : 420,
		pagination : true,
		method : 'post',
		singleSelect : true,
		url : appUrl + '/sales/salesAction!getT001JsonList.jspa',
		idField : 'bukrs',
		textField : 'butxt',
		columns : [ [ {
			field : 'bukrs',
			title : '公司编号',
			width : 100
		}, {
			field : 'butxt',
			title : '名称',
			width : 120
		} ] ]
	});

	// 工厂
	/*
	 * $('#werks').combobox({ url : appUrl +
	 * '/sales/salesAction!getT001wJsonList.jspa', valueField : 'werks',
	 * textField : 'name1' });
	 */
});