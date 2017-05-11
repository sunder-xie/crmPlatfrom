$(document).ready(function() {
	
	loadGrid();
	if($("#kunnrId").val()==""){
		loadKunnr();
	}
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '�������б�',		
						url : appUrl + '/stockReportAction!searchOrderCheck.jspa',
						loadMsg : '����Զ��������,��ȴ�...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						rownumbers : true,
						height : 420,
						width : 'auto',
						queryParams : {
							kunnr : $("#kunnr").val(),
							orgIds:$('#orgId').val()
						},
						onBeforeEdit : function(index, row) {
							row.editing = true;
							updateActions(index);
						},
						onAfterEdit : function(index, row) {
							row.editing = false;
							updateActions(index);
						},
						onCancelEdit : function(index, row) {
							row.editing = false;
							updateActions(index);
						},
						columns :[[ {
							field : 'orgRegion',
							title : '����',
							width : 50,
							align : 'center'
						}, {
							field : 'orgProvince',
							title : 'ʡ��',
							width : 50,
							align : 'center'
						}, {
							field : 'orgCity',
							title : '����',
							width : 50,
							align : 'center'
						}, {
							field : 'kunnr',
							title : '�����̱��',
							width : 80,
							align : 'center'
						}, {
							field : 'kunnrName',
							title : '����������',
							width : 150,
							align : 'center'
						}, {
							field : 'checkYear',
							title : '�˵����',
							width : 80,
							align : 'center'
						}, {
							field : 'checkMonth',
							title : '�˵��·�',
							width : 80,
							align : 'center'
						}, {
							field : 'lastCheckDate',
							title : '�������鿴ʱ��',
							width : 150,
							align : 'center'
						}, {
							field : 'state',
							title : '�������',
							width : 100,
							align : 'center',
							formatter : function(value) {
								if(value=="A"){
									return "ȷ������";
								}else if(value=="B"){
									return "ȷ���в���";
								}else if(value=="C"){
									return "�������޷�ȷ��";
								}
							}
						}, {
							field : 'checkDesc',
							title : '��ϸ����',
							width : 150,
							align : 'center'
						},{
							field : 'kunnrStatus',
							title : '�ͻ�״̬',
							width : 80,
							align : 'center',
							formatter : function(value, row, index) {
								if (value == 1) {
									return '�����͑�';
								} else if (value == 2) {
									return '<font color="red">�ѹؿ͑�</font>';
								} else if (value == 3) {
									return '<font color="red">�ػ���</font>';
								}
							}
						}, {
							field : 'createDate',
							title : '�ύ����',
							width : 150,
							align : 'center'
						}
//						, {
//							field : 'oper',
//							title : '����',
//							width : 60,
//							align : 'center',
//							formatter : function(value, row, index) {
//									if(row.editing){ 
//										var s = '<a href="#" onclick="saverow(this)">����</a>';
//										var c = '<a href="#" onclick="cancelrow(this)">ȡ��</a>';
//										return s+'&nbsp;'+c;
//									}else{
//										var a='<img style="cursor:pointer" onclick="editrow(this)" title="�޸�" src='
//											+ imgUrl
//											+ '/images/actions/action_edit.png align="absMiddle"></img>';
//									    return a;
//									}
//							}
//					    }
						]], 
						toolbar : [ "-",{
							text : '��������',
							iconCls : 'icon-download',
							handler : function() {
								exportForExcel(); 
							}
						}, "-"]
					});
	// ��ҳ�ؼ�
	var p = $('#datagrid').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '��',
		afterPageText : 'ҳ    �� {pages} ҳ',
		displayMsg : '��ǰ��ʾ {from} - {to} ����¼   �� {total} ����¼'
	});
	
	
}

function updateActions(index) {
	$('#datagrid').datagrid('updateRow', {
		index : index,
		row : {}
	});
}

function getRowIndex(target) {
	var tr = $(target).closest('tr.datagrid-row');
	return parseInt(tr.attr('datagrid-row-index'));
}

function editrow(target) {
	$('#datagrid').datagrid('beginEdit', getRowIndex(target));
}

function cancelrow(target){
	$('#datagrid').datagrid('cancelEdit', getRowIndex(target));
}

function saverow(target) {
	var rowIndex = getRowIndex(target);
	$('#datagrid').datagrid('endEdit', getRowIndex(target));
	var rows = $('#datagrid').propertygrid('getChanges');
	if(rows.length==0){
		return;
	}
	$.ajax({
		type : "post",
		url : appUrl + "",
		data : {
			businessId : rows[0].businessId,
			businessEndDate : rows[0].businessEndDate
		},
		success : function(executeResult) {
//			if("success" == executeResult.code){
				search();
//			}else{
//				$.messager.alert('Tips', '����ʧ��!', 'warning', function(){
////					loadContentAndPage();
//				});
//			}
		}
	});
}

function save(){
	if($("#startDate").val()==""){
		$.messager.alert('��ʾ', '��ѡ���˵����£�', '��ʾ');
		return;
	}
	if($("#create_state").combobox("getValue")==""){
		$.messager.alert('��ʾ', '��ѡ���������', '��ʾ');
		return;
	}
	if($("#create_state").combobox("getValue")=="B" && $("#create_checkDesc").val()==""){
		$.messager.alert('��ʾ', '����д��ϸ������', '��ʾ');
		return;
	}
	$('#otherIp').val(returnCitySN["cip"])

	$.ajax({
		type : "post",
		url : appUrl + '/stockReportAction!searchOrderCheckLastCheckDate.jspa',
		data : {
			kunnr : $('#kunnr').val(),
			startDate : $('#startDate').val()
		},
		success : function(result) {
			if(result.length>0){
				var form = window.document.forms[0];
				form.action = appUrl + "/stockReportAction!createOrderCheck.jspa";
				form.target = "hideFrame";
				form.submit();
				search();
			}else{
				$.messager.alert('��ʾ', '������δ�鿴����', '��ʾ');
			}
		}
	});
}

function loadKunnr(){

	$('#kunnr').combogrid({
		panelHeight : 250,
		panelWidth : 380,
		pagination : true,
		method : 'post',
		singleSelect : true,
		multiple : false,
		url : appUrl + '/kunnrAction!kunnrSearch.jspa?orgId='+$('#orgId').val()+'&bhxjFlag='+'C',
		idField : 'kunnr',
		textField : 'name1',
		// multiple:true,
		columns : [ 
					 [ {
			field : 'kunnr',
			title : '�����̱��',
			width : 120
		}, {
			field : 'name1',
			title : '����',
			width : 200
		} ] ],
		toolbar : '#toolbarKonzs'
	});
}

function importCsv() {
	html = '<form id="fileForm" method="post" enctype="multipart/form-data" >'
			+ '<table>'
			+ '<tr><td class="head" noWrap>ģ������</td>'
			+ '<td><a style="color:blue"   onclick=javascript:exportMouldCsv();> 1������ģ��</a></td></tr>'
			+ '<tr><td class="head" noWrap>��������</td>'
			+ '<td><input type="file" name="uploadFile" id="uploadFile"  style="width:200px"/>'
			+ '</tb></tr></table></form>';
	importGoalCsv('��������', html);
}

/**
 * ������������������ѯ
 * 
 * @param name1
 */
function searcherKonzs(name1) {
	var queryParams = $('#kunnr').combogrid("grid").datagrid('options').queryParams;
	queryParams.name1 = encodeURIComponent(name1);
	queryParams.orgId= $('#orgId').val();//ˢ�²���orgId��ֵ
	$('#kunnr').combogrid("grid").datagrid('reload');
}

function exportForExcel() {
	$.messager.progress();
	openTime();
	var form = window.document.forms[0];
	form.action = appUrl
			+ '/stockReport/stockReportAction!exportForExcelOrderCheck.jspa';
	form.target = "hideFrame";
	form.submit();
}
function openTime() {
	setTimeout(function() {
		var timer = setInterval(function() {
			$.ajax({
				type : "post",
				url : appUrl + "/stockReport/stockReportAction!checkDownLoadOver.jspa",
				success : function(data) {
					if (data == 'Yes') {
						clearInterval(timer);
						$.messager.progress('close');
					}
				}
			});
		}, 100);
	}, 500);
}

function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.kunnr = $('#kunnr').combobox("getValue");
	queryParams.state = $('#state').combobox("getValue");
	queryParams.flag = $('#flag').combobox("getValue");
	queryParams.orgIds = $('#orgId').val();
	queryParams.startDate = $('#startDate').val();
	queryParams.endDate = $('#endDate').val();
	$("#datagrid").datagrid('load');
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info');
		$('#excelDialog').dialog('close');
		$('#datagrid').datagrid('reload');
		// search();
	}
}

// �������ڶ���
function initMaintAccount(ffit, wWidth, wHeight, title, url, l, t) {
	var url = appUrl + url;
	var WWidth = wWidth;
	var WHeight = wHeight;
	var Ffit = ffit;
	var $win = $("#maintActivityPlanInfo")
			.window(
					{
						title : title,
						width : WWidth,
						height : WHeight,
						content : '<iframe frameborder="no" width="100%" height="100%" src='
								+ url + '/>',
						shadow : true,
						fit : Ffit,
						modal : true,
						closed : true,
						closable : true,
						minimizable : false,
						maximizable : false,
						collapsible : false,
						draggable : true,
						left : l,
						top : t
					});

	$win.window('open');

}

function clearValue(){
	$('#skuId').combo("setValue","");
	$('#custId').combobox("setValue","");
	$('#custKunnr').combobox("setValue","");
	$('#startDate').val("");
	$('#endDate').val("");
}

function choseOrg() {
//	initMaintAccount(false, '400', '400','��֯ѡ��', '/question/questionAction!orgTreePage.jspa',353,70);
	initMaintAccount(false, '400', '400','��֯ѡ��', '/newOrgAction!newOrgTree.jspa',353,70);

//	initMaintWindowForOrg('ѡ����֯', '/newOrgAction!newOrgTree.jspa');
}
function returnValue(selectedId, selectedName) {
	$("#orgId").val(selectedId);
	$("#orgName").val(selectedName);
}

function closeOrgTree() {
	$("#maintActivityPlanInfo").window('close');
}
function reloadDatagrid() {
	self.location.reload(true);
}
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
	date = date + month[str[1]] + "-" + str[2];
	return date;
}

document.onkeydown = function(e) {// ����¼����û������κμ��̼�������ϵͳ��ť�����ͷ���͹��ܼ���ʱ����
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		search();
		return false;
	}
	return true;
};