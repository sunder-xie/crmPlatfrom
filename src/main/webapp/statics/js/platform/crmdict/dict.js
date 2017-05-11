$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#dict_type_list')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '��ѯ���',
						height : height,
						striped : true,
						url : appUrl
								+ '/crmdictAction!getCrmDictTypeJsonList.jspa?ran='
								+ Math.random(),

						loadMsg : '����Զ��������,��ȴ�...',
						singleSelect : true,
						nowrap : true,
						pagination : true,
						rownumbers : true,
						columns : [ [
								{
									field : 'ck',
									align : 'center',
									checkbox : true
								}/*
									 * , { id : 'dictTypeId', title : '���',
									 * field : 'dictTypeId', width :
									 * setColumnWidth(0.1), align : 'center',
									 * sortable : true }
									 */,
								{
									field : 'dictTypeName',
									title : '��������',
									align : 'center',
									width : setColumnWidth(0.2)
								// formatter : function(value, row, index) {
								// /* var id=row.stationName; */
								// var cc = encodeURIComponent(row.dictTypeId);
								// return '<a href="#"
								// onclick=javascript:search_dict("'
								// + cc
								// + '") > ' + value + '</a>';
								//									
								// }
								},
								{
									field : 'dictTypeValue',
									title : '��������',
									width : setColumnWidth(0.2),
									align : 'center',
									sortable : true
								},
								{
									field : 'remark',
									title : '��ע',
									width : setColumnWidth(0.2),
									align : 'center',
									sortable : true
								},
								{
									field : 'lastModify',
									title : '����޸�ʱ��',
									align : 'center',
									width : setColumnWidth(0.1),
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
									width : setColumnWidth(0.1),
									align : 'center',
									formatter : function(value, row, rec) {
										var id = row.dictTypeId;
										return '<img style="cursor:pointer" onclick="edit('
												+ id
												+ ')" title="�޸�����" src='
												+ imgUrl
												+ '/images/actions/action_edit.png align="absMiddle"></img>  ';
										// + '&nbsp;&nbsp;<img
										// style="cursor:pointer"
										// onclick=search_dict('
										// + id
										// + ') title="�鿴����������ϸ" src='
										// + imgUrl
										// + '/images/actions/action_view.png
										// align="absMiddle"></img>';
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
	var p = $('#dict_type_list').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '��',
		afterPageText : 'ҳ    �� {pages} ҳ',
		displayMsg : '��ǰ��ʾ {from} - {to} ����¼   �� {total} ����¼'
	});
}

function search() {
	var queryParams = $('#dict_type_list').datagrid('options').queryParams;
	queryParams.dictTypeName = encodeURIComponent($("#dictTypeName").val());
	queryParams.dictTypeValue = encodeURIComponent($("#dictTypeValue").val());
	queryParams.remark = encodeURIComponent($("#remark").val());
	$("#dict_type_list").datagrid('load');
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

function add() {
	initMaintWindow('�������ʹ���', '/crmdictAction!toCreateDictType.jspa', 600, 200);
}

function edit(id) {

	initMaintWindow('���������޸�',
			'/crmdictAction!toUpdateDictType.jspa?dictTypeId=' + id, 600, 200);

}

function remove() {
	var ids = '';
	var rows = $('#dict_type_list').datagrid('getSelections');
	for ( var i = 0; i < rows.length; i++) {
		ids = rows[i].dictTypeId;
	}
	if (ids == '') {
		$.messager.alert('��ʾ', 'δѡ���κ�Ȩ�޵㣡', '��ʾ');
		return;
	} else {
		$.messager
				.confirm(
						'Confirm',
						'�Ƿ�ȷ������ɾ��?',
						function(r) {
							if (r) {
								var form = window.document.forms[0];
								form.action = appUrl
										+ '/crmdict/crmdictAction!deleteDictType.jspa?dictTypeId='
										+ ids;
								form.target = "hideFrame";
								form.submit();
							}
						});
	}

}

function search_dict(id, name) {
	initMaintWindow('������ϸ��ѯ',
			'/crmdict/crmdictAction!searchCrmDict.jspa?dictTypeId=' + id, 1000,
			400);
	// var WWidth = 860;
	// var WLeft = Math.ceil((window.screen.width - WWidth) / 2);
	// window.open(appUrl +
	// '/crmdict/crmdictAction!searchCrmDict.jspa?dictTypeId=' +
	// id+'&&dictTypeName='+name+'&&dictTypeValue='+val, "searchEventDetail",
	// "left=" + WLeft + ",top=20"
	// + ",width=" + WWidth + ",height=" + (window.screen.height - 100)
	// +
	// ",toolbar=no,rolebar=no,scrollbars=yes,location=no,menubar=no,resizable=yes,titlebar=no");
	//			
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
		search();
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

function setColumnWidth(percent) {
	return $(this).width() * percent;
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