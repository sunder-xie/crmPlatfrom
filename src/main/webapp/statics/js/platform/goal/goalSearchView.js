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
						title : '��ѯ���',
						url : appUrl + '/goal/goalAction!getGoalList.jspa?kunnrGoalType='+'B',
						queryParams : {
							orgId : $("#orgId").val(),
							mark : $("#mark").val()
						},
						loadMsg : '����Զ��������,��ȴ�...',
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
									title : 'Ŀ��ID',
									width : setColumnWidth(0.04),
									align : 'center',
									sortable : true
								},
								{
									field : 'orgName',
									title : '��֯��',
									width : setColumnWidth(0.125),
									align : 'center'
								},
								{
									field : 'custNameZH',
									title : '������',
									width : setColumnWidth(0.125),
									align : 'center',
									formatter : function(value, row, rec) {
										var s = row.mark;
										if (s == "N") {
											return '<span style="color:red">'
											+ "��֯Ŀ�����ض�������" + '</span>';
										} else {
											return '<span style="color:black">'
											+ row.custNameZH
											+ '</span>';
										}
									}
								},
								{
									field : 'kunnrGoalType',
									title : '������Ŀ������',
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
												type='Ԥ��';
											}
											if(t=='C'){
												type='��ͬ';
											}
											return type;
										}
									}
								},
								{
									field : 'theYear',
									title : 'Ŀ����',
									width : setColumnWidth(0.06),
									align : 'center'
								},
								{
									field : 'theMonth',
									title : 'Ŀ����',
									width : setColumnWidth(0.06),
									align : 'center'
								},
								{
									field : 'bezei',
									title : 'Ʒ��or�ļ���Ŀ��SKU��',
									width : setColumnWidth(0.1),
									align : 'center'
								},{
									field : 'box',
									title : 'Ŀ������',
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
									title : 'Ŀ����(Ԫ)',
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
									title : '����Ŀ������',
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
									title : '����Ŀ����(Ԫ)',
									width : setColumnWidth(0.1),
									align : 'center',
									formatter : function(value, row, rec) {
										var s = row.targetNumD*1;
										if (s == ''||s==undefined) {
											return '0.00';
										}
										return s.toFixed(2);
										}
								}] ],
						toolbar : "#toolbar"
					});
	// ��ҳ�ؼ�
	var p = $('#datagrid').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 50, 100 ],
		beforePageText : '��',
		afterPageText : 'ҳ    �� {pages} ҳ',
		displayMsg : '��ǰ��ʾ {from} - {to} ����¼   �� {total} ����¼'
	});
	// search();
}

function loadGrid2() {
	$('#id').combogrid({
		panelWidth : 400,
		panelHight : 600,
		idField : 'kunnr',
		textField : 'name1',
		pagination : true,// �Ƿ��ҳ
		// rownumbers : true,// ���
		collapsible : false,// �Ƿ���۵���
		// fit : true,// �Զ���С
		method : 'post',
		// multiple : true,
		url : appUrl + '/goal/goalAction!getKunner.jspa',
		columns : [ [ {
			field : 'ck',
			checkbox : true,
			hidden : true
		}, {
			field : 'id',
			title : '�ͻ�ID',
			hidden : true,
			width : 60
		}, {
			field : 'kunnr',
			title : '�ͻ����',
			align : 'center',
			width : 120
		}, {
			field : 'status',
			title : '�ͻ�״̬',
			align : 'center',
			width : 100,
			formatter : function(value, row, index) {
				if (value == 1) {
					return '�����͑�';
				} else if (value == 2) {
					return '<font color="red">�ѹؿ͑�</font>';
				}
			}
		},{
			field : 'name1',
			title : '�ͻ�����',
			align : 'center',
			width : 100
		},  {
			field : 'mobNumber',
			title : '�ֻ�',
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
		pagination : true,// �Ƿ��ҳ
		collapsible : false,// �Ƿ���۵���
		method : 'post',
		url : appUrl + '/goal/goalAction!getMaterielList.jspa',
		columns : [ [ {
			field : 'ck',
			checkbox : true,
			hidden : true
		}, {
			field : 'mvgr1',
			title : 'Ʒ��or�ļ���Ŀ��SKU��ID',
			align : 'center',
			width : 120
		}, {
			field : 'bezei',
			title : 'Ʒ��or�ļ���Ŀ��SKU��',
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
	initMaintGoal('������Ŀ��������', '/goalAction!createGoalPrepare.jspa?mark='+'Y'+'&kunnrGoalType='+'B', 490, 400);
}
function createOrgGoal() {
	initMaintGoal('��֯Ŀ��������', '/goalAction!createOrgGoalPrepare.jspa?mark='+'N', 490, 400);
}
function updateGoal(ctId) {
	var ctId = encodeURIComponent(ctId);
	var mark = encodeURIComponent(mark);
	initMaintGoal('Ŀ�����޸�', '/goalAction!updateGoalPrepare.jspa?ctId=' + ctId
			+ '&mark' + mark, 490, 400);
}

function importMould() {
	html = '<form id="fileForm" method="post" enctype="multipart/form-data" >'
			+ '&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'
			+ '<table><tr>'
			+ '<td class="head" noWrap>��������</td>'
			+ '<td><input type="hidden" id="flag" value="add"/><input type="hidden" name="kunnrGoalType" id="kunnrGoalType" value="B"  style="width:200px"/><input type="file" name="uploadFile" id="uploadFile"  style="width:200px"/>'
			+ '</tb></tr>' + '<tr><td class="head" noWrap>��������</td>'
			+ '<td><select id="type" name="type" style="width:130px">'
			+ '<option value="N">--��֯Ŀ��--</option>'
			+ '<option value="Y" selected>--������Ŀ��--</option>' + '</select>'
			+ '</td></tr><table></form>';
	openImportDialog('����Ŀ��', html);
}
var mask_;
/* �򿪵�����ĿExcel�Ի��� */
function openImportDialog(t, html) {
	if ($('#excelDialog').length < 1) {
		$(
				'<div/>',
				{
					id : 'excelDialog',
					title : 'ѡ���ϴ��ļ�',
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
									text : 'ȷ��',
									handler : function() {
										var file = document
												.getElementById('uploadFile').value;
										var type = document
												.getElementById('type').value;
										if (/^.+\.(csv|CSV)$/.test(file)) {
											$("#type").val($("#type").val());
											var flag=document
											.getElementById('flag').value;
											$.messager.progress();
											var action='';
											if ("add"==flag) {
												action=appUrl
												+ "/goal/goalAction!importGoalCsv.jspa";
											}
											if("update"==flag){
												action=appUrl
												+ "/goal/goalAction!importGoalListCsv.jspa";;
											}
											var form = document
													.getElementById('fileForm');
											
											form.action = action;
											// "eventId="+processInstanceId;
											form.target = "hideFrame";
											form.submit();
										} else {
											$.messager.alert("��ʾ", "�뵼��csv�ļ�");
										}

									}
								}, {
									text : 'ȡ��',
									handler : function() {
										window.location.href =window.location.href; 
										$('#excelDialog').dialog('close');
										
									}
								} ],

						width : document.documentElement.clientWidth * 0.28,
						height : document.documentElement.clientHeight * 0.50
					});
}

/* ���û�������ĿExcel���м�� */
function importCheck() {
	var file = document.getElementById('projectImportFile').value;
	if (/^.+\.(xls|xlsx)$/.test(file)) {
		document.getElementById('fileForm').submit();
		$('#import').dialog('close');
		mask_ = new mask({
			tip : '��ȴ��������...'
		});
		mask_.show();
		window.setTimeout(getImportStateInfo, 2000);
	} else {
		$.messager.alert("��ʾ", "�뵼��Excel�ļ�");
	}
}
function importContractMould(){
	html = '<form id="fileForm" method="post" enctype="multipart/form-data" >'
		+ '&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'
		+ '<table><tr>'
		+ '<td class="head" noWrap>��������</td>'
		+ '<td><input type="hidden" id="flag" value="add"/><input type="hidden" name="type" id="type" value="Y"  style="width:200px"/><input type="hidden" name="kunnrGoalType" id="kunnrGoalType" value="C"  style="width:200px"/>'
		 +'   <input type="file" name="uploadFile" id="uploadFile"  style="width:200px"/>'
		+ '</tb></tr><table></form>';
     openImportDialog('����Ŀ��', html);
}
function selectOrgTree() {
	initMaintGoal('ѡ����֯', '/goalAction!orgTreePage.jspa', 400, 460);
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
 * ���һ��ģ�������
 */
function approveYearGoal(){
	$.messager.confirm('Confirm', '�Ƿ�ȷ����˵�ǰ�����Ŀ����?', function(r) {
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
	$.messager.confirm('Confirm', '�Ƿ���������Ŀ��?', function(r) {
		if (r) {
			var rows = $('#datagrid').datagrid('getSelections');
			if (rows == '') {
				$.messager.alert('Tips', '  û���б�ѡ��!');
				return;
			}
			// for (var i = 0; i < rows.length; i++) {
			// if (rows[i].trFlag == 't') {
			// $.messager.alert('Tips', ' ѡ����������Ŀ���������ܲ���!');
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
		$.messager.alert('Tips', '  û���б�ѡ��!');
		return;
	}
	$.messager.confirm('Confirm', '�Ƿ�����ɾ��Ŀ��?', function(r) {
		if (r) {
			
			/*for ( var i = 0; i < rows.length; i++) {
				if (rows[i].trFlag == 'T') {
					$.messager.alert('Tips', '  ѡ����������Ŀ����������ɾ��!');
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
	$.messager.confirm('Confirm', '�Ƿ���������Ŀ��?', function(r) {
		if (r) {
			var rows = $('#datagrid').datagrid('getSelections');
			if (rows == '') {
				$.messager.alert('Tips', '  û���б�ѡ��!');
				return;
			}
			for ( var i = 0; i < rows.length; i++) {
				if (rows[i].trFlag == 'B' || rows[i].trFlag == 'S') {
					$.messager.alert('Tips', 'ֻ��������Ŀ����ܲ���!');
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

function exportMould() {
	var form = window.document.forms[0];
	form.action = appUrl + '/goal/goalAction!exportMouldCsv.jspa';
	form.target = "hideFrame";
	form.submit();
}
//excel����
function exportData() {
	var form = window.document.forms[0];
	form.action = appUrl + '/goalAction!exportData.jspa';
	form.submit();
	$.messager.alert('Tips', '���ڵ���,���Ժ�!',
	'warning');
}
function clear() {
	//$('#month').val("");
	$('#mark').val("");
	//$('#month').combobox('setValue', '');
	$("#id").combobox('setValue', '');
	$("#wid").combobox('setValue', '');
	$("#mark").combobox('setValue', 'N');
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
	queryParams.orgName = encodeURIComponent($("#orgName").val());
	queryParams.brand = $("#brand").combobox("getValue");
	$("#datagrid").datagrid('reload');
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
// + "<tr><td class='head' noWrap>�������б�</td>"
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

function updateGoalList() {
	html = '<form id="fileForm" method="post" enctype="multipart/form-data" >'
			+ '&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'
			+ '<table><tr>'
			+ '<td class="head" noWrap>�����޸ĵ���</td>'
			+ '<td><input type="hidden" id="flag" value="update"/><input type="hidden" name="kunnrGoalType" id="kunnrGoalType" value="B"  style="width:200px"/><input type="file" name="uploadFile" id="uploadFile"  style="width:200px"/>'
			+ '</tb></tr>' + '<tr><td class="head" noWrap>��������</td>'
			+ '<td><select id="type" name="type" style="width:130px">'
			+ '<option value="N">--��֯Ŀ��--</option>'
			+ '<option value="Y" selected>--������Ŀ��--</option>' + '</select>'
			+ '</td></tr><table></form>';
	openImportDialog('�����޸�Ŀ��', html);
}
function change(){
		$('#orgId').val('');
}