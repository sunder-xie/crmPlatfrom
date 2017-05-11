$(document).ready(function() {

	loadGrid(); 

	$('#hideFrame').bind('load', promgtMsg);
});
var x;
function loadGrid() {
$('#dict_type_list').datagrid(
		{
			iconCls : 'icon-list',
			title : '查询结果',
			height : 350,
			queryParams: {
				orgId: 'firsttime'
			},
			width:$(this).width(),		
			url : appUrl + '/custVistAction!getCustVistOrderJsonList.jspa',
			loadMsg : '数据远程载入中,请等待...',
			singleSelect : true,
			fitColumns : true,
			nowrap : true,
			//pagination : true,//分页
			rownumbers : true,//行号列
			columns : [ [
					{
						field : 'custDetailId',						
						align : 'center',
						checkbox : true
					},
					{
						field : 'visitOrder',
						title : '拜访次序',
						align : 'center',
						sortable : true
					},
					{
						field : 'custOrgName',
						title : '客户组织',
						align : 'center',
						
						sortable : true
					},
					{
						field : 'empName',
						title : '业代姓名',
						
						align : 'center',
						sortable : true
					},
					{
						field : 'itemName',
						title : '拜访频次',
						
						align : 'center',
						sortable : true
					},
					{
						field : 'weekDay',
						title : '拜访日程',
						
						align : 'center',
						sortable : true
					},
					{
						field : 'custNumber',
						title : '客户代码',
						
						align : 'center',
						sortable : true
					},
					{
						field : 'custNameZH',
						title : '客户名称',
						
						align : 'center',
						sortable : true
					},
					{
						field : 'custAddress',
						title : '客户地址',
						
						align : 'center',
						sortable : true
					},
					{
						field : 'contactName',
						title : '联系人',
						
						align : 'center',
						sortable : true
					},
					{
						field : 'contactPhone',
						title : '联系电话',
						
						align : 'center',
						sortable : true
					},
					{
						field : 'custLevelName',
						title : '客户等级',
						
						align : 'center',
						sortable : true
					},
					{
						field : 'channelName',
						title : '渠道名称',
						
						align : 'center',
						sortable : true
					},
					{
						field : 'posName',
						title : '职位名称',
						
						align : 'center',
						sortable : true
					},
					{
						field : 'visitTime',
						title : '拜访时间',
						
						align : 'center',
						sortable : true
					}] ],
					onCheck : function(rowindex, rowdata){
						x = rowindex;
					},
					toolbar : [ "-", {
						text : '次序上移',
						iconCls : 'icon-ok',
						handler : function() {
							orderUp();
						}
					}, "-", {
						text : '次序下移',
						iconCls : 'icon-ok',
						handler : function() {
							orderDown();
						}
					}, "-"]
		});
		
//			// 分页控件
//			var p = $('#dict_type_list').datagrid('getPager');
//			$(p).pagination({
//			pageSize : 10,
//			pageList : [ 10, 20, 30 ],
//			beforePageText : '第',
//			afterPageText : '页    共 {pages} 页',
//			displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
//			});

}
/**
 * 组织树
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
						title : '组织选择',
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
						title : '选择业务代表',
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
		alert("请先选择组织.");
		return;
	}
	if ($("#weekDay").combobox('getValue') == "") {
		alert("请先选择拜访日程.");
		return;
	}	if ($("#posId").val() == "") {
		alert("请先选择业代.");
		return;
	}
	var queryParams = $('#dict_type_list').datagrid('options').queryParams;
	queryParams.weekDay = encodeURIComponent($("#weekDay").combobox('getValue'));

	queryParams.posId = encodeURIComponent($("#posId").val());
	
	queryParams.orgId = encodeURIComponent($("#orgId").val());

	$("#dict_type_list").datagrid('reload');

}

// 上移
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
			$.messager.alert('提示', '请先选择行数据.', 'warning');
	}
}
// 下移
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
		$.messager.alert('提示', '请先选择行数据.', 'warning');
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
				alert("保存失败");
			},
			success:function(data){
				search();
				$.messager.alert('提示', '保存成功', 'info');
				
			}	
		});
		
		x=-1;
	} else {
		alert("没有数据可以保存.");
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
//			$.messager.alert('提示','保存失败', 'info');
//		},
//		success:function(data){
//			search();
//			
//			
//		}	
//	});
//		
if ($("#orgId").val() == "") {
	alert("请先选择组织.");
	return;
}
if ($("#weekDay").combobox('getValue') == "") {
	alert("请先选择拜访日程.");
	return;
}	if ($("#posId").val() == "") {
	alert("请先选择业代.");
	return;
}
	
	
	$.messager
	.confirm(
			'Confirm',
			'是否导出拜访次序?',
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
		$.messager.alert('提示', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('提示', successResult, 'info');
		search();
	}
}
function clearValuedate() {
	document.forms[0].reset();
	x = -1;
}