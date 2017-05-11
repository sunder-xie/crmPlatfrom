var flag;
var orgflag=1;
$(document).ready(function() {
	
	loadGrid();
	loadCustKunnr();
	$('#hideFrame').bind('load', promgtMsg);
	// �ͻ����� ����
	var type = $('#type').val();
	if (type == '') {
		type = 'Z';
	}
	$('#channelId').combobox({
		url : appUrl + '/kunnrAction!getChannel.jspa',
		valueField : 'channelId',
		textField : 'channelName'
	});
	
	$('#allchannel').combobox({
		valueField : 'allChannelName',
		textField : 'allChannelName',
		width : 130,
		multiple : false,
		editable : false,
		required : false,
		panelHeight : 'auto',
			url : appUrl + '/kunnrAction!getAllChannel.jspa',
		onLoadSuccess : function(data){
			
		},
	onChange : function(newValue, oldValue){
	
		
		$('#channelId').combobox('setValue','');
		var url = appUrl + '/kunnrAction!getChannel.jspa?allChannelName='+encodeURIComponent(newValue) ;
//		var url = appUrl + '/kunnrAction!getChannel.jspa?allChannelName='+newValue ;
		$('#channelId').combobox('reload', url);  
		
	}
	});

});
/**
 * ����������������
 */
function loadCustKunnr() {
	// ���׿ͻ�
	$('#custParent').combogrid({
		panelHeight : 250,
		panelWidth : 380,
		pagination : true,
		method : 'post',
		singleSelect : true,
		url : appUrl + '/customerAction!getTwoLevelCustomer.jspa?orgId='+$('#orgId').val(),
		idField : 'custId',
		textField : 'custName',
		multiple : true,
		columns : [ [ {
			field : 'ck',
			checkbox : true
		}, {
			field : 'custId',
			title : '�ͻ����',
			width : 120
		}, {
			field : 'custName',
			title : '�ͻ�����',
			width : 200
		} ] ],
		toolbar : '#toolbarParent'
	});
	$('#custKunnr').combogrid({
		panelHeight : 250,
		panelWidth : 380,
		pagination : true,
		method : 'post',
		singleSelect : true,
		url : appUrl + '/kunnrAction!kunnrSearch.jspa?orgId='+$('#orgId').val()+'&bhxjFlag='+'C',
		idField : 'kunnr',
		textField : 'name1',
		// multiple:true,
		columns : [ /*
					 * [ { field : 'ck', checkbox : true } ],
					 */[ {
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

/*******************************************************************************
 * �������������б�
 */
function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '��ѯ���',
						queryParams : {custState : '1'},
						url : appUrl + '/customerAction!customerSearch.jspa',
						loadMsg : '����Զ��������,��ȴ�...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						height : height,
						striped : true,
						frozenColumns : [[
											{
												field : 'price',
												title : '����',
												width : setColumnWidth(0.08),
												align : 'center',
												formatter : function(value, row, index) {
													var id = row.custNumber;
													var state = row.custState;
													var type = row.custType;
													if (type == '') {
														type = 'Z';
													}
													var edit = "  <a href='#' onclick=editInfo('"
															+ id
															+ "','"
															+ state
															+ "','"
															+ type + "') >�޸�</a>|";
													var freeze = "  <a href='#' onclick=freezeKunnr('"
															+ id
															+ "','"
															+ state
															+ "') >�ػ�</a>";
													if (state == '4') {
														return '';
													} else {
														return edit + freeze;
													}
												}
											}, {
												field : 'custNumber',
												title : '�ͻ����',
												width : setColumnWidth(0.06),
												align : 'center',
												sortable : true
											}, {
												field : 'custName',
												title : '�ͻ�����',
												width : setColumnWidth(0.1),
												align : 'center',
												sortable : true
											}]],
						columns : [ [
								 {
									field : 'orgName',
									title : '������֯',
									width : setColumnWidth(0.1),
									align : 'center',
									sortable : true
								},{
									field : 'channelName',
									title : '����',
									width : setColumnWidth(0.06),
									sortable : true
								}, {
									field : 'stationUserId',
									title : '��λID',
									width : setColumnWidth(0.06),
									hidden : false,
									sortable : true
								}, {
									field : 'stationName',
									title : '��λ����',
									width : setColumnWidth(0.06),
									align : 'center',
									hidden : true
								}, {
									field : 'empName',
									title : 'ҵ������',
									width : setColumnWidth(0.06),
									align : 'center',
									sortable : true
								}, {
									field : 'contactName',
									title : '��ϵ��',
									width : setColumnWidth(0.06),
									sortable : true
								}, {
									field : 'contactMobile',
									title : '��ϵ�˺���',
									width : setColumnWidth(0.06),
									sortable : true
								}, {
									field : 'custProvince',
									title : '��������',
									width : setColumnWidth(0.2),
									align : 'left',
									sortable : true
								}, {
									field : 'custTown',
									title : '��/����',
									width : setColumnWidth(0.06),
									align : 'center',
									sortable : true
								}, {
									field : 'custStreet',
									title : '·/��/��/��/Ū',
									width : setColumnWidth(0.06),
									align : 'left',
									sortable : true
								}, {
									field : 'custHouserNumber',
									title : '���ƺ�',
									width : setColumnWidth(0.06),
									align : 'center',
									sortable : true
								}, {
									field : 'custAddress',
									title : '�ο�λ��',
									width : setColumnWidth(0.1),
									align : 'center',
									hidden : false
								},{
									field : 'longitude',
									title : '����',
									width : setColumnWidth(0.08),
									align : 'center',
									hidden : false
								},{
									field : 'latitude',
									title : 'γ��',
									width : setColumnWidth(0.08),
									align : 'center',
									hidden : false
								},{
									field : 'customerImportance',
									title : '�ŵ���Ҫ��',
									width : setColumnWidth(0.08),
									align : 'center',
									hidden : false
								},{
									field : 'customerAnnualSale',
									title : '�ŵ������۶�',
									width : setColumnWidth(0.08),
									align : 'center',
									hidden : false
								},{
									field : 'custType',
									title : '����',
									width : setColumnWidth(0.06),
									align : 'center',
									formatter : function(value, row, index) {
										var state = row.custType;
										var stateName = '';
										if (state == 'T') {
											stateName = '���׿ͻ�';
										}
										if (state == 'Z') {
											stateName = '�ն��ŵ�';
										}
										return stateName;
									},
									sortable : true
								}, {
									field : 'custState',
									title : '�ͻ�״̬',
									width : setColumnWidth(0.06),
									align : 'center',
									formatter : function(value, row, index) {
										var state = row.custState;
										var stateName = '';
										if (state == '0') {
											stateName = '�¿ͻ�';
										}
										if (state == '1') {
											stateName = '����';
										}
										if (state == '2') {
											stateName = '��Ъ';
										}
										if (state == '3') {
											stateName = '����';
										}
										if (state == '4') {
											stateName = '�ѹػ�';
										}
										return stateName;
									},
									sortable : true
								}, {
									field : 'custReceive',
									title : '���ͷ�ʽ',
									width : setColumnWidth(0.06),
									align : 'center',
									formatter : function(value, row, index) {
										var s = row.custReceive;
										var name = '';
										if (s == '0') {
											name = '����������';
										}
										if (s == '1') {
											name = '��������';
										}
										if (s == '2') {
											name = '�ܲ�����';
										}
										return name;
									},
									sortable : true
								}, {
									field : 'visitFreq',
									title : '�ݷ�Ƶ��',
									width : setColumnWidth(0.06),
									align : 'center',
									formatter : function(value, row, index) {
										var s = row.visitFreq;
										var name = '';
										if (s == '1') {
											name = 'һ��һ��';
										}
										if (s == '2') {
											name = 'һ������';
										}
										if (s == '3') {
											name = 'һ������';
										}
										if (s == '4') {
											name = '����һ��';
										}
										return name;
									},
									sortable : true
								}, {
									field : 'visitDays',
									title : '�ݷ�����',
									width : setColumnWidth(0.06),
									align : 'center',
									sortable : true
								}, {
									field : 'corporateDeputy',
									title : '���˴���',
									width : setColumnWidth(0.06),
									align : 'center',
									hidden : true
								}, {
									field : 'createDate',
									title : '��������',
									width : setColumnWidth(0.06),
									align : 'center',
									hidden : true
								}, {
									field : 'lastModify',
									title : '�޸�����',
									width : setColumnWidth(0.06),
									align : 'center',
									sortable : true
								}, {
									field : 'modifyUser',
									title : '������',
									width : setColumnWidth(0.06),
									align : 'center',
									sortable : true
								}, {
									field : 'custKunnrName',
									title : '���ξ�����',
									width : setColumnWidth(0.2),
									align : 'left',
									hidden : false,
									sortable : true
								} ] ],
						toolbar : [ "-", {
							text : '�����ͻ�����',
							iconCls : 'icon-excel',
							handler : function() {
								excel();
							}
						},
						"-", {
							text : '��������ͻ�����',
							iconCls : 'icon-excel',
							handler : function() {
								importCsv();
							}
						}, "-", {
							text : '���ؿͻ�����ĸ�����Ϣ����',
							iconCls : 'icon-excel',
							handler : function() {
								exportCustomerHelps();
							}
						}, "-" ]

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

/**
 * ģ������
 */
function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.custNumber = $("#custNumber").val();
	queryParams.custName = encodeURIComponent($("#custName").val());
	queryParams.allChannelName = encodeURIComponent($('#allchannel').combobox("getValue"));
	queryParams.channelId = $('#channelId').combobox("getValue");
	queryParams.orgId = $("#orgId").val();
	queryParams.stationUserId = $("#stationUserId").val();
	queryParams.contactName = encodeURIComponent($("#contactName").val());
	queryParams.custState = $("#custState").val();// $("#custState").combobox("getValue");
	queryParams.custKunnr = $("#custKunnr").combogrid("getValue");
	queryParams.custParent = $("#custParent").combogrid("getValue");
	queryParams.customerImportance = encodeURIComponent($("#customerImportance").combobox("getValue"));
	queryParams.custType = $("#custType").val();
	queryParams.createOrgId = $("#createOrgId").val();
	queryParams.createDateStart = $("#createDateStart").datebox("getValue");
	queryParams.createDateEnd = $("#createDateEnd").datebox("getValue");
	$("#datagrid").datagrid('reload');
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}

function viewInfo(id) {
	openWindow("�ն��ŵ���Ϣ�鿴", "/customerAction!viewCustomer.jspa?custId=" + id,
			1000, 480);
}
/**
 * �ͻ���Ϣ�޸�
 * @param id 
 * @param state
 * @param type
 */
function editInfo(id, state, type) {
	openWindow("�ͻ���Ϣ�޸�", "/customerAction!toUpdateCustomer.jspa?custId=" + id
			+ '&type=' + type, 1100, 480);
}
/**
 * �ر��ն��ŵ�
 * 
 * @param id
 */
function freezeKunnr(id, state) {
	var ids = [];
	ids.push(id);
	$("#ids").val(ids);
	if (state == "�ѹػ�") {
		$.messager.alert('Tips', '�ͻ�״̬Ϊ�ѹر�״̬�������ظ�������', 'warning');
		return;
	}
	$.messager.confirm('Confirm', '�Ƿ�ȷ���ر��ն��ŵ�?', function(r) {
		if (r) {
			var form = window.document.forms[0];
			form.action = appUrl
					+ '/customerAction!closeCustomer.jspa?custState1=' + '4';
			form.target = "hideFrame";
			form.submit();

		}
	});
}

/**
 * ������������������ѯ
 * 
 * @param name1
 */
function searcherKonzs(name1) {
	var queryParams = $('#custKunnr').combogrid("grid").datagrid('options').queryParams;
	queryParams.name1 = encodeURIComponent(name1);
	$('#custKunnr').combogrid("grid").datagrid('reload');
}
// ���׿ͻ�������ѯ
function searcherParent(name1) {
	var queryParams = $('#custParent').combogrid("grid").datagrid('options').queryParams;
	queryParams.search = encodeURIComponent(name1);
	$('#custParent').combogrid("grid").datagrid('reload');
}
// �������ڶ���
function openWindow(title, url, WWidth, WHeight) {
	var url = appUrl + url;
	var $win = $("#hiddenWin")
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
// �رմ���
function closeWindow() {
	$("#hiddenWin").window('close');
}
/**
 * ����֯��
 */
function selectOrgTree() {
	orgflag=1;
	openWindow('ѡ����֯', '/customerAction!orgTreePage.jspa', 400, 460);
}
/**
 * ����֯��
 */
function selectOrgTree0() {
	orgflag=0;
	openWindow('ѡ����֯', '/customerAction!orgTreePage.jspa', 400, 460);
}
/**
 * ��֯��ѡ��֯���ص������
 * 
 * @param selectedId
 * @param selectedName
 */
function returnValue(selectedId, selectedName) {
	if (flag == 1) {
		document.getElementById('orgId01').value = selectedId;
		document.getElementById('orgName01').value = selectedName;
	} else {
		if (orgflag==1){
		    document.getElementById('orgId').value = selectedId;
		    document.getElementById('orgName').value = selectedName;
		}else{
		    document.getElementById('createOrgId').value = selectedId;
		    document.getElementById('createOrgName').value = selectedName;
		}
	}
	$('#custKunnr').combogrid(
			{
				url : appUrl + '/kunnrAction!kunnrSearch.jspa?orgId='
						+ $('#orgId').val() + '&bhxjFlag=' + 'C'
			});
	
		$('#custParent').combogrid(
				{
					url : appUrl
							+ '/customerAction!getTwoLevelCustomer.jspa?orgId='
							+ $('#orgId').val()
				});
}
/**
 * ��֯��ѡ����֮��ر�
 */
function closeMaintEvent() {
	closeWindow();
}
/**
 * ѡ��ҵ��
 */
function selectOrgTree4Station() {
	var node = $('#orgId').val();
	var orgName = encodeURIComponent($('#orgName').val());
	openWindow('ѡ��ҵ�����', '/batChangeAction!selectOrgTree4Station.jspa?node='
			+ node + '&orgName=' + orgName, 520, 460);
}
/**
 * ҵ��ѡ��֮��ر�
 */
function closeOrgTree() {
	closeWindow();
}
/**
 * ����֯��
 */
function selectOrgTree1() {
	flag = 1;
	openWindow('ѡ����֯', '/customerAction!orgTreePage.jspa', 400, 460);
}

/*
 * ����Աѡ����
 */
function selectUser() {
	openWindow('ѡ��ҵ����Ա', '/batChangeAction!selectOrgTreeUser.jspa', 550, 350);
}
/*
 * ���о�������ѡ�񷵻�ֵ
 */
function returnUser(userId, userName) {
	document.getElementById('stationUserId01').value = userId;
	document.getElementById('stationUserName01').value = userName;
}
/**
 * excel�����ն˿ͻ�����
 */
function excel() {
	//�������ݿ�֮����
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.custNumber = $("#custNumber").val();
	queryParams.custName = encodeURIComponent($("#custName").val());
	queryParams.allChannelName = encodeURIComponent($('#allchannel').combobox("getValue"));
	queryParams.channelId = $('#channelId').combobox("getValue");
	queryParams.orgId = $("#orgId").val();
	queryParams.stationUserId = $("#stationUserId").val();
	queryParams.contactName = encodeURIComponent($("#contactName").val());
	queryParams.custState = $("#custState").val();
	queryParams.custKunnr = $("#custKunnr").combogrid("getValue");
	queryParams.custParent = $("#custParent").combogrid("getValue");
	queryParams.customerImportance = encodeURIComponent($("#customerImportance").combobox("getValue"));
	queryParams.custType = $("#custType").val();
	queryParams.createOrgId = $("#createOrgId").val();
	queryParams.createDateStart = $("#createDateStart").datebox("getValue");
	queryParams.createDateEnd = $("#createDateEnd").datebox("getValue");
	//��������֮����
	$.messager.confirm('Confirm', '�Ƿ�ȷ������?', function(r) {
		if (r) {
			var form = window.document.forms[0];
			form.action = appUrl + '/customerAction!customerExport.jspa';
			form.submit();
			$.messager.alert('Tips', '���ڵ���,���Ժ�!',
			'warning');
		}
	});
}
function importCsv() {
	html = '<form id="fileForm" method="post" enctype="multipart/form-data" >'
			+ '<table><tr>'
			+ '<td class="head" noWrap>����ѡ��</td>'
			+ '<td><select id="type" name="type" style="width:130px">'
			+ '<option value="Z" checked>--�ն��ŵ�--</option>'
			+ '<option value="T" selected>--���׿ͻ�--</option>'
			+ '</select></td></tr>'
			+ '<tr><td class="head" noWrap>ģ������</td>'
			+ '<td><a style="color:blue"   onclick=javascript:exportCustomerMouldCsv();> 1������ģ��</a></td></tr>'
			/*
			 * + '<tr><td></td><td><a class="easyui-linkbutton"
			 * style="color:blue" data-options="plain:true,iconCls:"icon-excel""
			 * onclick=javascript:exportCustomerHelps();>2�����ؿͻ�����ĸ�����Ϣ����</a></td></tr>' + '<tr><td class="head" noWrap>������֯</td><td>' + '<input
			 * class="easyui-validatebox" id="orgName01" name="orgName01"
			 * readonly/>' + '<button onclick="javascript:selectOrgTree1()">ѡ��</button>' + '<input
			 * type="hidden" id="orgId01" name="orgId01"/></td></tr>' + '<tr><td class="head" noWrap>��˾ҵ������</td><td>' + '<input
			 * class="easyui-validatebox" id="stationUserName01"
			 * name="stationUserName01" readonly/>' + '<button
			 * onclick="javascript:selectUser()">ѡ��</button>' + '<input
			 * type="hidden" id="stationUserId01" name="stationUserId01"/></td></tr>'
			 */
			+ '<tr><td class="head" noWrap>��������</td>'
			+ '<td><input type="file" name="uploadFile" id="uploadFile"  style="width:200px"/>'
			+ '</tb></tr></table></form>';
	importCustomerCsv('��������', html);
}
// csv�����ն��ŵ굼��ģ��
function exportCustomerMouldCsv() {
	$.messager.confirm('Confirm', '�Ƿ�ȷ������ģ��?', function(r) {
		if (r) {
			var form = /* window.document.forms[0] */document
					.getElementById("fileForm");
			form.action = appUrl
					+ '/customerAction!exportCustomerMouldCsv.jspa';
			form.submit();
		}
	});

}
// excel�����ն��ŵ굼�븨����Ϣ
function exportCustomerHelps() {
	$.messager.confirm('Confirm', '�Ƿ�ȷ�������ظ�����Ϣ?', function(r) {
		if (r) {
			var form = window.document.forms[0];
			form.action = appUrl + '/customerAction!exportCustomerHelps.jspa';
			form.submit();
		}
	});
}
// ���������ն��ŵ���Ϣ
function importCustomerCsv(t, html) {
	if ($('#excelDialog').length < 1) {
		$(
				'<div/>',
				{
					id : 'excelDialog',
					title : 'ѡ���ϴ��ļ�',
					html : "<div id='import'>"
					// + "</br>"
					+ "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
							+ html + "</div>" // +
							// "</div>"
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
										/*
										 * if ($('#orgId01').val() == '' ||
										 * $('#orgId01').val() == undefined) {
										 * $.messager.alert("��ʾ", "��ѡ��������֯");
										 * return; } if
										 * ($('#stationUserId01').val() == '' ||
										 * $('#stationUserId01').val() ==
										 * undefined) { $.messager.alert("��ʾ",
										 * "��ѡ��ҵ������"); return; }
										 */
										var file = document
												.getElementById('uploadFile').value;
										if (/^.+\.(csv|CSV)$/.test(file)) {
											$.messager.progress();
											var form = document
													.getElementById('fileForm');
											form.action = appUrl
													+ "/customerAction!importCustomerCsv.jspa";
											form.target = "hideFrame";
											form.submit();
										} else {
											$.messager.alert("��ʾ", "�뵼��csv�ļ�");
										}

									}
								}, {
									text : 'ȡ��',
									handler : function() {
										$('#excelDialog').dialog('close');
									}
								} ],

						width : document.documentElement.clientWidth * 0.35,
						height : document.documentElement.clientHeight * 0.55
					});
}

/*
 * function importCustomerCsv(){ $.messager.confirm('Confirm', '�Ƿ�ȷ�����������ն��ŵ�?',
 * function(r) { if (r) { var form = window.document.forms[0]; form.action =
 * appUrl + '/customerAction!importCustomerCsv.jspa'; form.submit(); } }); }
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
function clearFormValue() {
	// document.forms[0].reset();
	clearValue();
	$('#custParent').combogrid('setValue', '');
	$('#custKunnr').combogrid('setValue', '');
	$('#channelId').combobox('setValue', '');
	$('#custState').val('1');
	$('#createOrgId').val('');
	$('#createDateStart').datebox('setValue','');
	$('#createDateEnd').datebox('setValue','');
}
/**
 * ҳ�淵����ʾ��Ϣ
 */
function promgtMsg() {
	$.messager.progress('close');
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		// search();
		$.messager.alert('Tips', successResult, 'info');
		$('#datagrid').datagrid('reload');

	}
}
