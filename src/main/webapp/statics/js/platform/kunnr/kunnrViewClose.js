$(document).ready(function() {

	// �Ա�
	$('#sex').combobox({
		valueField : 'value',
		textField : 'text',
		data : [ {
			value : '',
			text : '��ѡ��'
		} ,{
			value : 'male',
			text : '��'
		}, {
			value : 'female',
			text : 'Ů'
		} ]
	});
	// �ͻ����� ����
	$('#channelId').combobox({
		url : appUrl + '/kunnrAction!getChannel.jspa?channelName='+'K',
		valueField : 'channelId',
		textField : 'channelName'
	});

	// �ϼ������̲�ѯ
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
			title : '�����̱��',
			width : 120
		}, {
			field : 'name1',
			title : '����',
			width : 250
		} ] ]
	});

	// ���۷�Χ
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
			title : '������֯',
			width : 100
		}, {
			field : 'spart',
			title : '��Ʒ��',
			width : 80
		}, {
			field : 'vtweg',
			title : '��������',
			width : 100
		} ] ]
	});

	// ���۴���
	$('#vkgrp').combobox({
		url : appUrl + '/sales/salesAction!getTvkgrJsonList.jspa',
		valueField : 'vkgrp',
		textField : 'vkgrp'
	});

	// ����ʡ��
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
			title : 'ʡ�ݱ��',
			width : 100
		}, {
			field : 'bztxt',
			title : '����',
			width : 120
		} ] ]
	});

	// ���۲���
	$('#vkbur').combobox({
		url : appUrl + '/sales/salesAction!getTvkbtJsonList.jspa',
		valueField : 'vkbur',
		textField : 'bezei'
	});

	// ����
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
			title : '���б��',
			width : 100
		}, {
			field : 'bezei',
			title : '����',
			width : 120
		} ] ]

	});

	// ����
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
			title : '������',
			width : 100
		}, {
			field : 'bezei',
			title : '����',
			width : 120
		} ] ]
	});

	// ��˾
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
			title : '��˾���',
			width : 100
		}, {
			field : 'butxt',
			title : '����',
			width : 120
		} ] ]
	});

	// ����
	/*
	 * $('#werks').combobox({ url : appUrl +
	 * '/sales/salesAction!getT001wJsonList.jspa', valueField : 'werks',
	 * textField : 'name1' });
	 */
});