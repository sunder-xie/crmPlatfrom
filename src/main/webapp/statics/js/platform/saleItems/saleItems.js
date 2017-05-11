var flag;
$(document).ready(function() {
	loadGrid();
	loadCustKunnr();
	loadCustSystem();
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
		columns : [ 
					 [ { field : 'ck', checkbox : true } ],
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

function loadCustSystem() {
	// ����ϵͳ
	$('#custSystem').combogrid(
			{
				panelHeight : 250,
				panelWidth : 380,
				pagination : true,
				method : 'post',
				singleSelect : true,
				url : appUrl
						+ '/crmdictAction!getDictJsonList.jspa?dictTypeValue='
						+ 'system@customer',
				idField : 'itemId',
				textField : 'itemName',
				columns : [ 
				           [ { field : 'ck', checkbox : true } ],
				           [ {
					field : 'itemId',
					title : 'ϵͳ���',
					width : 60
				}, {
					field : 'itemName',
					title : 'ϵͳ����',
					width : 200
				}, {
					field : 'itemValue',
					title : '����',
					width : 200
				} ] ],
				toolbar : '#toolbarSys'
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
						url : appUrl + '/saleItemsAction!saleItemsSearch.jspa',
						loadMsg : '����Զ��������,��ȴ�...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						height : height,
						striped : true,
						rownumbers : true,
						multiple : false,
						columns : [ [
						        {
						        	field : 'ck',
						        	align : 'center',
						        	checkbox : true
						        },						        
								{
									field : 'channelName',
									title : '����',
									width : setColumnWidth(0.2),
									sortable : true
								}, {
									field : 'saleItemsKunnrName',
									title : '����������',
									width : setColumnWidth(0.2),
									align : 'left',
									hidden : false,
									sortable : true
								},{
									field : 'saleItemsSystemName',
									title : '����ϵͳ',
									width : setColumnWidth(0.2),
									align : 'left',
									hidden : false,
									sortable : true
								} ,{
									field : 'skuNames',
									title : '������',
									width : setColumnWidth(0.2),
									align : 'left',
									hidden : false,
									sortable : true
								},
								{
									field : 'oper',
									title : '����',
									width : 140,
									align : 'center',
									formatter : function(value, row, rec) {
										var id = row.saleItemsId;
										return '<img style="cursor:pointer" onclick="edit('
												+ id
												+ ')" title="�޸�����" src='
												+ imgUrl
												+ '/images/actions/action_edit.png align="absMiddle"></img>';
									}
								}
								] ],
						toolbar : [ "-", {
							text : '����',
							iconCls : 'icon-add',
							handler : function() {
								addItems();
							}
						},
						"-", {
							text : 'ɾ��',
							iconCls : 'icon-remove',
							handler : function() {
								delItems();
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
//	queryParams.custName = encodeURIComponent($("#custName").val());
	queryParams.channelId = $('#channelId').combobox("getValue");
	queryParams.orgId = $("#orgId").val();
	queryParams.custKunnr = $("#custKunnr").combogrid("getValue");
	$("#datagrid").datagrid('load');
}





function setColumnWidth(percent) {
	return $(this).width() * percent;
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

function searcherSys() {
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

//�������ڶ���
function openMaintWindow(title, url, WWidth, WHeight) {
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
	openMaintWindow('ѡ����֯', '/saleItemsAction!orgTreePage.jspa', 400, 460);
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
		document.getElementById('orgId').value = selectedId;
		document.getElementById('orgName').value = selectedName;
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
	openMaintWindow('ѡ����֯', '/saleItemsAction!orgTreePage.jspa', 400, 460);
}

function addItems(){
	openMaintWindow('��������Ʒ��', '/saleItemsAction!addSaleItems.jspa', 400, 460);
}

function edit(id){
	openMaintWindow('�޸�����Ʒ��', '/saleItemsAction!toUpdateSaleItems.jspa?saleItemsId=' + id, 400, 460);
}

function delItems(){
	var rows = $('#datagrid').datagrid('getSelections');
	if (rows == '') {
		$.messager.alert('Tips', '  û���б�ѡ��!');
		return;
	}
	$.messager.confirm('Confirm', '�Ƿ�����ɾ��Ŀ��?', function(r) {
		if (r) {
			var ids = [];
			for ( var i = 0; i < rows.length; i++) {
				ids.push(rows[i].saleItemsId);
			}
			$("#ids").val(ids);
			var form = window.document.forms[0];
			form.action = "saleItemsAction!deleteSaleItems.jspa";
			form.target = "hideFrame";
			form.submit();
		}
	});
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
function clearFormValue() {
	// document.forms[0].reset();
	clearValue();
//	$('#custParent').combogrid('setValue', '');
	$('#custKunnr').combogrid('setValue', '');
	$('#channelId').combobox('setValue', '');
	$('#orgId').combobox('setValue', '');
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
