$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {

	$('#con_list')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '��ѯ���',
						height : 390,
						width : $(this).width(),
						striped : true,
						url : appUrl
								+ '/channelTreeAction!getChannelList.jspa?ran='
								+ Math.random(),
						loadMsg : '����Զ��������,��ȴ�...',
						singleSelect : false,
						nowrap : true,
						pagination : true,
						rownumbers : true,
						fitColumns : true,
						columns : [ [
								{
									field : 'ck',
									align : 'center',
									checkbox : true
								},
								{
									id : 'dictTypeId',
									title : '����ID',
									field : 'channelId',
									width : $(this).width() * 0.1,
									align : 'center',
									sortable : true
								},
								{
									title : '��������',
									field : 'channelName',
									width : $(this).width() * 0.14,
									align : 'center'
								},
								{
									title : '��������ID',
									field : 'channelParentId',
									width : $(this).width() * 0.1,
									align : 'center'
								},
								{
									title : '��������',
									field : 'parentChannelName',
									width : $(this).width() * 0.1,
									align : 'center'
								},
								{
									title : '��ע',
									field : 'remark',
									width : $(this).width() * 0.1,
									align : 'center'
								},
								{
									title : '������ʱ��',
									field : 'lastModify',
									width : $(this).width() * 0.14,
									align : 'center',
									formatter : function(v) {
										if (v)
											return utcToDate(v.replace(
													/\/Date\((\d+)\+\d+\)\//gi,
													"new Date($1)"));
									}
								},
								{
									field : 'edit',
									title : '�޸�',
									width : $(this).width() * 0.1,
									align : 'center',
									formatter : function(value, row, rec) {
										var id = row.channelId;
										var state = row.channelState;
										if (state == 'Y') {
											return '<img style="cursor:pointer" onclick="edit('
													+ id
													+ ')" title="�޸���Ϣ" src='
													+ imgUrl
													+ '/images/actions/action_edit.png align="absMiddle"></img>';
										}
									}
								},
								{
									title : '״̬',
									field : 'channelState',
									width : $(this).width() * 0.1,
									align : 'center',
									formatter : function(value, row, rec) {
										var state = row.channelState;
										if (state == 'Y') {
											return "<font color='green'>����</font>";
										} else {
											return "<font color='red'>����</font>";
										}
									}
								},
								{
									field : 'oper',
									title : '����״̬����',
									width : $(this).width() * 0.1,
									align : 'center',
									formatter : function(value, row, rec) {
										var id = row.channelId;
										var state = row.channelState;
										if (state == 'Y') {
											return '<img style="cursor:pointer" onclick=forbidden("'
													+ id
													+ '") title="��������" src='
													+ imgUrl
													+ '/images/actions/action_del.png align="absMiddle"></img>';
										} else {
											return '<img style="cursor:pointer" onclick=startup("'
													+ id
													+ '") title="��������" src='
													+ imgUrl
													+ '/images/actions/icon_ok.gif align="absMiddle"></img>';
										}
									}
								} ] ],
						toolbar : [ "-", {
							text : '����',
							iconCls : 'icon-add',
							handler : function() {
								add();
							}
						} ]
					});
	// ��ҳ�ؼ�
	var p = $('#con_list').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '��',
		afterPageText : 'ҳ    �� {pages} ҳ',
		displayMsg : '��ǰ��ʾ {from} - {to} ����¼   �� {total} ����¼'
	});
	search();
}

function search() {
	var queryParams = $('#con_list').datagrid('options').queryParams;
	queryParams.channelName = encodeURIComponent($("#channelName").val());
	queryParams.channelState = encodeURIComponent($("#channelState").val());
	queryParams.channelParentId = encodeURIComponent($("#channelParentId")
			.val());
	$("#con_list").datagrid('load');
}

// �������ڶ���
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

function selectChannelListTree() {
	initMaintWindow('ѡ������', '/channelTreeAction!channelTreePage.jspa', 450, 320);
}

/**
 * ��������
 */
function add() {
	initMaintWindow('��������', '/channelTreeAction!toCreateChannel.jspa', 600, 400);
}

/**
 * �޸�������Ϣ
 */
function edit(id) {
	initMaintWindow('�����޸�',
			'/channelTreeAction!toUpdateChannelInfo.jspa?channelId=' + id, 600,
			400);

}

/**
 * ����֯��
 */
function selectOrgTree() {
	initMaintWindowForOrg('ѡ����֯', '/orgAction!orgTreePage.jspa');
}

// �رմ���ҳ��
function closeMaintWindow() {
	$("#maintWindow").window('close');
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('��ʾ', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('��ʾ', successResult, 'info');
		search();
	}
}
/**
 * ��֯���ķ���ֵ����
 * 
 * @param {}
 *            selectedId
 * @param {}
 *            selectedName
 */
function returnValue(selectedId, selectedName) {
	document.getElementById('channelParentId').value = selectedId;
	document.getElementById('channelParentName').value = selectedName;
}
function closeOrgTree() {
	$("#maintWindow").window('close');
}

function forbidden(id) {
	$.messager.confirm("��ʾ", "ȷ��Ҫ���ø�����ô��", function(data) {
		if (data) {
			var form = window.document.forms[0];
			form.action = appUrl
					+ '/channelTreeAction!forbidden.jspa?channelId=' + id;
			form.target = "hideFrame";
			form.submit();
		} else {
			return;
		}
	});
}

function startup(id) {
	$.messager.confirm("��ʾ", "ȷ��Ҫ���ø�����ô��", function(data) {
		if (data) {
			var form = window.document.forms[0];
			form.action = appUrl + '/channelTreeAction!startup.jspa?channelId='
					+ id;
			form.target = "hideFrame";
			form.submit();
		} else {
			return;
		}
	});
}

/**
 * �س���Ĭ��ʱ���
 * 
 * @param {}
 *            e
 * @return {Boolean}
 */
document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		search();
		return false;
	}
	return true;
};
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
	week["Mon"] = "һ";
	week["Tue"] = "��";
	week["Wed"] = "��";
	week["Thu"] = "��";
	week["Fri"] = "��";
	week["Sat"] = "��";
	week["Sun"] = "��";

	str = utcCurrTime.split(" ");
	date = str[5] + "-";
	date = date + month[str[1]] + "-" + str[2] + " " + str[3];
	return date;
}
$(window).resize(function() {
	$('#datagrid').datagrid('resize', {
		width : $(".f_main").width()
	});
});
