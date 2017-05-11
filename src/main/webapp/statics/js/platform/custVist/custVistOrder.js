$(document).ready(function() {

	loadGrid(); 

	$('#hideFrame').bind('load', promgtMsg);
});
var x;
function loadGrid() {
$('#dict_type_list').datagrid(
		{
			iconCls : 'icon-list',
			title : '��ѯ���',
			height : 350,
			queryParams: {
				orgId: 'firsttime'
			},
			width:$(this).width(),		
			url : appUrl + '/custVistAction!getCustVistOrderJsonList.jspa',
			loadMsg : '����Զ��������,��ȴ�...',
			singleSelect : true,
			fitColumns : true,
			nowrap : true,
			//pagination : true,//��ҳ
			rownumbers : true,//�к���
			columns : [ [
					{
						field : 'custDetailId',						
						align : 'center',
						checkbox : true
					},
					{
						field : 'visitOrder',
						title : '�ݷô���',
						align : 'center',
						sortable : true
					},
					{
						field : 'custOrgName',
						title : '�ͻ���֯',
						align : 'center',
						
						sortable : true
					},
					{
						field : 'empName',
						title : 'ҵ������',
						
						align : 'center',
						sortable : true
					},
					{
						field : 'itemName',
						title : '�ݷ�Ƶ��',
						
						align : 'center',
						sortable : true
					},
					{
						field : 'weekDay',
						title : '�ݷ��ճ�',
						
						align : 'center',
						sortable : true
					},
					{
						field : 'custNumber',
						title : '�ͻ�����',
						
						align : 'center',
						sortable : true
					},
					{
						field : 'custNameZH',
						title : '�ͻ�����',
						
						align : 'center',
						sortable : true
					},
					{
						field : 'custAddress',
						title : '�ͻ���ַ',
						
						align : 'center',
						sortable : true
					},
					{
						field : 'contactName',
						title : '��ϵ��',
						
						align : 'center',
						sortable : true
					},
					{
						field : 'contactPhone',
						title : '��ϵ�绰',
						
						align : 'center',
						sortable : true
					},
					{
						field : 'custLevelName',
						title : '�ͻ��ȼ�',
						
						align : 'center',
						sortable : true
					},
					{
						field : 'channelName',
						title : '��������',
						
						align : 'center',
						sortable : true
					},
					{
						field : 'posName',
						title : 'ְλ����',
						
						align : 'center',
						sortable : true
					},
					{
						field : 'visitTime',
						title : '�ݷ�ʱ��',
						
						align : 'center',
						sortable : true
					}] ],
					onCheck : function(rowindex, rowdata){
						x = rowindex;
					},
					toolbar : [ "-", {
						text : '��������',
						iconCls : 'icon-ok',
						handler : function() {
							orderUp();
						}
					}, "-", {
						text : '��������',
						iconCls : 'icon-ok',
						handler : function() {
							orderDown();
						}
					}, "-"]
		});
		
//			// ��ҳ�ؼ�
//			var p = $('#dict_type_list').datagrid('getPager');
//			$(p).pagination({
//			pageSize : 10,
//			pageList : [ 10, 20, 30 ],
//			beforePageText : '��',
//			afterPageText : 'ҳ    �� {pages} ҳ',
//			displayMsg : '��ǰ��ʾ {from} - {to} ����¼   �� {total} ����¼'
//			});

}
/**
 * ��֯��
 * 
 * @param {}
 *            title
 * @param {}
 *            url
 */

function choseOrg() {
	var url = appUrl + '/custVistAction!toOrgTree.jspa';
	var WWidth = 300;
	var WHeight = 300;
	var $win = $("#maintStaff")
			.window(
					{
						title : '��֯ѡ��',
						width : WWidth,
						height : WHeight,
						content : '<iframe frameborder="no" width="100%" height="100%" src='
								+ url + '/>',
						shadow : true,
						modal : true,
						closed : true,
						closable : true,
						minimizable : false,
						maximizable : false,
						collapsible : false,
						draggable : false
					});
	$win.window('open');
}



function closeMaintEvent() {
	$("#maintStaff").window('close');
}

function returnValue(selectedId, selectedName) {
	$("#orgId").val(selectedId);
	$("#orgName").val(selectedName);
}
function selectpotId(){
	var url = appUrl + '/custVistAction!selectOrgTree4Station.jspa';
	var WWidth = 550;
	var WHeight = 350;
	var $win = $("#maintStaff")
			.window(
					{
						title : 'ѡ��ҵ�����',
						width : WWidth,
						height : WHeight,
						content : '<iframe frameborder="no" width="100%" height="100%" src='
								+ url + '/>',
						shadow : true,
						modal : true,
						closed : true,
						closable : true,
						minimizable : false,
						maximizable : false,
						collapsible : false,
						draggable : false
					});
	$win.window('open');
}
function search() {
	if ($("#orgId").val() == "") {
		alert("����ѡ����֯.");
		return;
	}
	if ($("#weekDay").combobox('getValue') == "") {
		alert("����ѡ��ݷ��ճ�.");
		return;
	}	if ($("#posId").val() == "") {
		alert("����ѡ��ҵ��.");
		return;
	}
	var queryParams = $('#dict_type_list').datagrid('options').queryParams;
	queryParams.weekDay = encodeURIComponent($("#weekDay").combobox('getValue'));

	queryParams.posId = encodeURIComponent($("#posId").val());
	
	queryParams.orgId = encodeURIComponent($("#orgId").val());

	$("#dict_type_list").datagrid('reload');

}

// ����
function orderUp() {
	var record = $("#dict_type_list").datagrid('getSelected');
	if(x == 0){
		return;
	}
	if (record != undefined  && x != -1) {
		$("#dict_type_list").datagrid('deleteRow',x);
		$('#dict_type_list').datagrid('insertRow',{
			index: x-1,	// index start with 0
			row: {
				   custDetailId : record.custDetailId ,						
				   visitOrder : record.visitOrder,
				   custOrgName : record.custOrgName,
				   empName : record.empName,
				   itemName : record.itemName,
				   weekDay : record.weekDay,
				   custNumber : record.custNumber,
				   custNameZH : record.custNameZH,
				   custAddress : record.custAddress,
				   contactName : record.contactName,
				   contactPhone : record.contactPhone,
				   custLevelName : record.custLevelName,
				   channelName : record.channelName,
				   posName : record.posName,
				   visitTime : record.visitTime
			}
		});
		$("#dict_type_list").datagrid('selectRow',x-1);
		} else {
			$.messager.alert('��ʾ', '����ѡ��������.', 'warning');
	}
}
// ����
function orderDown() {
	var record = $("#dict_type_list").datagrid('getSelected');
	
	if (record != undefined && x !=-1) {
		$("#dict_type_list").datagrid('deleteRow',x);
		$('#dict_type_list').datagrid('insertRow',{
			index: x+1,	
			row: {
				   custDetailId : record.custDetailId ,						
				   visitOrder : record.visitOrder,
				   custOrgName : record.custOrgName,
				   empName : record.empName,
				   itemName : record.itemName,
				   weekDay : record.weekDay,
				   custNumber : record.custNumber,
				   custNameZH : record.custNameZH,
				   custAddress : record.custAddress,
				   contactName : record.contactName,
				   contactPhone : record.contactPhone,
				   custLevelName : record.custLevelName,
				   channelName : record.channelName,
				   posName : record.posName,
				   visitTime : record.visitTime
			}
		});
		$("#dict_type_list").datagrid('selectRow',x+1);
		} else {
		$.messager.alert('��ʾ', '����ѡ��������.', 'warning');
	}
}
function saveOrderASY() {

	if (x!=undefined && x !=-1) {
		var table = $("#dict_type_list").datagrid('getRows');
		var str="";
		for ( var int = 0; int < table.length; int++) {
			str=str+table[int]['custDetailId']+":"+(int+1)+",";
		}
		
		$.ajax({
			type: 'post',
			url: appUrl+'/custVistAction!updateCustVistOrder.jspa?updateString='+str,

			dataType:'text',
			error:function(){
				alert("����ʧ��");
			},
			success:function(data){
				search();
				$.messager.alert('��ʾ', '����ɹ�', 'info');
				
			}	
		});
		
		x=-1;
	} else {
		alert("û�����ݿ��Ա���.");
	}
}

function excelExport() {
//	alert($("#orgId").val());
//	$.ajax({
//		type: 'post',
//		url: appUrl+'/custVistAction!exportCustVisitOrder.jspa',
//		data: {weekDay : encodeURIComponent($("#weekDay").combobox('getValue')),
//			   posId :encodeURIComponent($("#posId").val()),
//			   orgId : encodeURIComponent($("#orgId").val())},
//		dataType:'text',
//		error:function(){
//			$.messager.alert('��ʾ','����ʧ��', 'info');
//		},
//		success:function(data){
//			search();
//			
//			
//		}	
//	});
//		
if ($("#orgId").val() == "") {
	alert("����ѡ����֯.");
	return;
}
if ($("#weekDay").combobox('getValue') == "") {
	alert("����ѡ��ݷ��ճ�.");
	return;
}	if ($("#posId").val() == "") {
	alert("����ѡ��ҵ��.");
	return;
}
	
	
	$.messager
	.confirm(
			'Confirm',
			'�Ƿ񵼳��ݷô���?',
			function(r) {
				if (r) {
					var form = window.document.forms[0];
					form.action = appUrl+'/custVistAction!exportCustVisitOrder.jspa?weekDay='+
					encodeURIComponent($("#weekDay").combobox('getValue'))+
					'&&posId='+encodeURIComponent($("#posId").val())+
					'&&orgId='+encodeURIComponent($("#orgId").val());
					form.target = "hideframe";
					form.submit();

				}
			});	
	
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
function clearValuedate() {
	document.forms[0].reset();
	x = -1;
}