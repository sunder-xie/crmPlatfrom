$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});
 
function loadGrid() {
	$('#dict_list')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '��ѯ���',
						height : height,
						striped : true,
						url : appUrl
								+ '/crmdictAction!getCrmDictJsonList.jspa?dictTypeId='
								+ $('#dictTypeId').val() + '&ran='
								+ Math.random(),

						loadMsg : '����Զ��������,��ȴ�...',
						singleSelect : false,
						nowrap : true,
						pagination : true,
						rownumbers : true,
						columns : [ [
								{
									field : 'ck',
									align : 'center',
									checkbox : true
								},/*
								{
									title : '���',
									field : 'itemId',
									width : 80,
									align : 'center',
									sortable : true
								},*/
								{
									field : 'itemName',
									title : '��ϸ����',
									align : 'center',
									width : 100,
									sortable : true
								},
								{
									field : 'itemValue',
									title : '��ϸ����',
									width : 80,
									align : 'center',
									sortable : true
								},

/*								{
									field : 'dictTypeId',
									title : '����ID',
									width : 80,
									hidden : true,
									align : 'center',
									sortable : true
								},*/

								/*{
									field : 'parentItemId',
									title : '����',
									width : 80,
									hidden : true,
									align : 'center',
									sortable : true
								},*/

								{
									field : 'itemDescription',
									title : '����',
									width : 180,
									align : 'center',
									sortable : true
								},
								{
									field : 'remark',
									title : '��ע',
									width : 150,
									align : 'center',
									sortable : true
								},
								{
									field : 'lastModify',
									title : '����޸�ʱ��',
									align : 'center',
									width : 140,
									sortable : true,
									formatter : function(v) {
										return utcToDate(v.replace(
												/\/Date\((\d+)\+\d+\)\//gi,
												"new Date($1)"));
									}

								},
								{
									field : 'oper',
									title : '����',
									width : 140,
									align : 'center',
									formatter : function(value, row, rec) {
										var id = row.itemId;
										return '<img style="cursor:pointer" onclick="edit('
												+ id
												+ ')" title="�޸�����" src='
												+ imgUrl
												+ '/images/actions/action_edit.png align="absMiddle"></img>';
									}
								} ] ],
						toolbar : [ "-", {
							text : '����',
							iconCls : 'icon-add',
							handler : function() {
								add();
							}
						}, "-", {
							text : 'ɾ��',
							iconCls : 'icon-remove ',
							handler : function() {
								remove();
							}
						}, "-" ]
					});
	// ��ҳ�ؼ�
	var p = $('#dict_list').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '��',
		afterPageText : 'ҳ    �� {pages} ҳ',
		displayMsg : '��ǰ��ʾ {from} - {to} ����¼   �� {total} ����¼'
	});
}

// �������ڶ���
function initMaintWindow(title, url) {
	var url = appUrl + url;
	var WWidth = 600;
	var WHeight = 300;
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
						minimizable : false,//
						maximizable : false,//
						collapsible : false,//
						draggable : true
					//
					});

	$win.window('open');
}

function add() {
	initMaintWindow('������ϸ����', '/crmdictAction!toCreateDict.jspa?dictTypeId='
			+ $('#dictTypeId').val());
}

function edit(id) {
	initMaintWindow('������ϸ�����޸�', '/crmdictAction!toUpdateDict.jspa?itemId=' + id);

}
function search() {
	$("#dict_list").datagrid('load');
}

function remove() {
	var ids = '';
	var rows = $('#dict_list').datagrid('getSelections');
	for ( var i = 0; i < rows.length; i++) {
		ids = rows[i].itemId;
	}
	if (ids == '') {
		$.messager.alert('��ʾ', 'δѡ���κ�Ȩ�޵㣡', '��ʾ');
		return;
	} else {
		var form = window.document.forms[0];
		form.action = appUrl + '/crmdict/crmdictAction!DeleteDict.jspa?itemId='
				+ ids;
		form.target = "hideFrame";
		form.submit();
	}

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
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info');
		$("#dict_list").datagrid('load');
	}
}

/*
 * document.onkeydown = function(e) { var theEvent = e || window.event; var code =
 * theEvent.keyCode || theEvent.which || theEvent.charCode; if (code == 13) {
 * search(); return false; } return true; };
 */

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