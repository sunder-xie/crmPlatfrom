$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {

	var state=[{'value':'A','text':'����'},{'value':'B','text':'ͣ��'}];
	$('#list')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '��ѯ���',
						height : height,
						striped : true,
						url : appUrl
								+ '/busPhoneAction!getEmpList.jspa',

						loadMsg : '����Զ��������,��ȴ�...',
						singleSelect : false,
						nowrap : true,
						pagination : true,
						rownumbers : true,
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
						columns : [ [
						        {
							        field : 'userId',
							        title : '�û����',
							        hidden:true
						        },
								{
									field : 'orgRegion',
									title : '����',
									align : 'center',
									width : 100
								},
								{
									field : 'orgProvince',
									title : 'ʡ��',
									align : 'center',
									width : 100
								},
								{
									field : 'orgName',
									title : '����',
									align : 'center',
									width : 100
								},
								{
									field : 'userName',
									title : '����',
									align : 'center',
									width : 100
								},
								{
									field : 'userIdCard',
									title : '���֤',
									align : 'center',
									width : 110
								},{
									field : 'userStation',
									title : 'ְ��',
									width : 120,
									align : 'center'
								},
								{
									field : 'userStartDate',
									title : '��ְʱ��',
									width : 100,
									align : 'center'
								},
									
								{
									field : 'userEndDate',
									title : '��ְʱ��',
									align : 'center',
									width : 100
								},
								{
									field : 'userState',
									title : '����״̬',
									width : 100,
									align : 'center',
									formatter : function(v) {
										if(v=='Y'){
											return '��ְ';
										}else if(v=='N'){
											return '��ְ';
										}else if(v=='S'){
											return'������';
										}else if(v=='W'){
											return'����ְ';
										}else if(v=='R'){
											return'�˲ſ�';
										}else if(v=='D'){
											return'ɾ��';
										}
									}
								},
								{
									field : 'busPhone',
									title : '�����ֻ���',
									width : 100,
									align : 'center',
									editor : {
										type : 'text',
										options : {}
									}
								},
								{
									field : 'busPhoneSimple',
									title : '�����ֻ��̺�',
									width : 100,
									align : 'center',
									editor : {
										type : 'text',
										options : {}
									}
								},
								{
									field : 'phoneStartDate',
									title : '��ʼʹ������',
									width : 100,
									align : 'center',
									editor : {
										type : 'datebox',
										options : {editable:true}
									}
								},
								{
									field : 'phoneEndDate',
									title : '����ʹ������',
									width : 100,
									align : 'center',
									editor : {
										type : 'datebox',
										options : {editable:true}
									}
								},
								{
									field : 'phoneState',
									title : 'ʹ��״̬',
									width : 100,
									align : 'center',
									formatter : function(v) {
										if(v=='A'){
											return '����';
										}else if(v=='B'){
											return 'ͣ��';
										}
									},
									editor : {
										type : 'combobox',
										options : {
											data : state,
											valueField : 'value',
											textField : 'text'
										}
									}
								},
								{
									field : 'phoneRemark',
									title : '��ע',
									width : 100,
									align : 'center',
									editor : {
										type : 'text',
										options : {}
									}
								},
								{
									field : 'oper',
									title : '����',
									width : 60,
									align : 'center',
									formatter : function(value, row, index) {
										var loginId=$("#loginId").val();
										if(loginId=="admin"){
											if(row.editing){ 
												var s = '<a href="#" onclick="saverow(this)">����</a>';
												var c = '<a href="#" onclick="cancelrow(this)">ȡ��</a>';
												return s+'&nbsp;'+c;
											}else{
												var a='<img style="cursor:pointer" onclick="editrow(this)" title="�޸�" src='
													+ imgUrl
													+ '/images/actions/action_edit.png align="absMiddle"></img>';
												var b='<a href="javascript:clear('+row.userId+')">���</a>';
											    return b+"&nbsp;"+a;
											}
										}
									}
							}
								] ], 
								toolbar : [ "-",{
									text : '��������',
									iconCls : 'icon-download',
									handler : function() {
										exportForExcel(); 
									}
								}, "-" ]
					});
	// ��ҳ�ؼ�
	var p = $('#list').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '��',
		afterPageText : 'ҳ    �� {pages} ҳ',
		displayMsg : '��ǰ��ʾ {from} - {to} ����¼   �� {total} ����¼'
	});
}

function clear(userId){
	$.messager.confirm('Confirm','�Ƿ�ɾ��?',function(r) {
		if (r) {
			$.ajax({
				type : "post",
				url : appUrl + "/busPhoneAction!deleteEmp.jspa",
				data : {
					userId : userId
				},
				success : function(executeResult) {
					loadGrid();
				}
			});
		}
	});
}

function updateActions(index) {
	$('#list').datagrid('updateRow', {
		index : index,
		row : {}
	});
}

function getRowIndex(target) {
	var tr = $(target).closest('tr.datagrid-row');
	return parseInt(tr.attr('datagrid-row-index'));
}

function editrow(target) {
	$('#list').datagrid('beginEdit', getRowIndex(target));
}

function cancelrow(target){
	$('#list').datagrid('cancelEdit', getRowIndex(target));
}

function saverow(target) {
	var rowIndex = getRowIndex(target);
	$('#list').datagrid('endEdit', getRowIndex(target));
	var rows = $('#list').propertygrid('getChanges');
	if(rows.length==0){
		return;
	}
	$.ajax({
		type : "post",
		url : appUrl + "/busPhoneAction!updateEmp.jspa",
		data : {
			userId : rows[0].userId,
			busPhone : rows[0].busPhone,
			busPhoneSimple : rows[0].busPhoneSimple,
			phoneStartDate : rows[0].phoneStartDate,
			phoneEndDate : rows[0].phoneEndDate,
			phoneState : rows[0].phoneState,
			phoneRemark : encodeURIComponent(rows[0].phoneRemark)
		},
		success : function(executeResult) {
//			if("success" == executeResult.code){
				loadGrid();
//			}else{
//				$.messager.alert('Tips', '����ʧ��!', 'warning', function(){
////					loadContentAndPage();
//				});
//			}
		}
	});
}

function search() {
	var queryParams = $('#list').datagrid('options').queryParams;
	queryParams.orgId = $("#orgId").val();
	queryParams.busPhone = $("#busPhone").val();
	queryParams.userName = encodeURIComponent($("#userName").val());
	queryParams.startDate = $("#startDate").datebox("getValue");
	queryParams.endDate = $("#endDate").datebox("getValue");
	queryParams.userState = $("#userState").combobox("getValue");
	queryParams.phoneState = $("#phoneState").combobox("getValue");
	$("#list").datagrid('load');
}

function exportForExcel() {
	$.messager.progress();
	openTime();
	var form = window.document.forms[0];
	form.action = appUrl
			+ '/busPhoneAction!exportEmpToExcel.jspa';
	form.target = "hideFrame";
	form.submit();
}
function openTime() {
	setTimeout(function() {
		var timer = setInterval(function() {
			$.ajax({
				type : "post",
				url : appUrl + "/busPhoneAction!checkDownLoadOver.jspa?",
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

function selectOrgTree() {
	initMaintWindow('ѡ����֯', '/questionAction!orgTreePage.jspa', 400, 460);
}
function returnValue(selectedId, selectedName) {
	document.getElementById('orgId').value = selectedId;
	document.getElementById('orgName').value = selectedName;
}

function closeOrgTree() {
	closeDtPlan();
}
function closeDtPlan() {
	$("#maintWindow").window('close');
}

function clearValue(){
	
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