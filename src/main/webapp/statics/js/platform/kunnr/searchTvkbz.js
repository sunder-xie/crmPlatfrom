var list=[];
$(document).ready(function() {
	loadGrid();
	//search();
	//$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#datagrid').datagrid({
		iconCls : 'icon-list',
		title : '销售范围列表',
		url : appUrl + '/salesMsgAction!getTvkbzJsonList.jspa?vkorg = '+encodeURIComponent($("#vkorg").val()),
		loadMsg : '数据远程载入中,请等待...',
		singleSelect : false,
		pagination : true,
		nowrap : true,
		rownumbers : true,
		striped : true,
		height : height,
		columns : [ [{
			field : 'ck',
			checkbox : true
		}, {
			field : 'vkorg',
			title : '销售组织',
			align : 'center'
		},
		{
			field : 'vkorgTxt',
			title : '销售组织描述',
			align : 'center'
		}, {
			field : 'vtweg',
			title : '分销渠道',
			align : 'center'
		}, {
			field : 'vtwegTxt',
			title : '分销渠道描述',
			align : 'center'
		}, {
			field : 'spart',
			title : '产品组',
			align : 'center'
		},  {
			field : 'spartTxt',
			title : '产品组描述',
			align : 'center',
			width:200
		} ] ],
		toolbar : [ "-", {
			text : '确定',
			iconCls : 'icon-save',
			handler : function() {
				save();
			}
		}, "-" ]
	});

	// 分页控件
	var p = $('#datagrid').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '第',
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}
function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	//queryParams.vkorg = encodeURIComponent($("#vkorg").val());
	queryParams.vtweg = encodeURIComponent($("#vtweg").val());
	queryParams.spart = encodeURIComponent($("#spart").val());
	queryParams.vkbur = encodeURIComponent($("#vkbur").val());
	$("#datagrid").datagrid('load');
}
function save() {
	var rows = $('#datagrid').datagrid('getSelections');
	if (rows == '') {
		$.messager.alert('Tips', '  没有行被选中!');
		return;
	}
	for ( var i = 0; i < rows.length; i++) {
		var vkorg=rows[i].vkorg;          //销售组织
		var vkorgTxt=rows[i].vkorgTxt;          //销售组织
		var vtweg=rows[i].vtweg;          //销售渠道
		var vtwegTxt=rows[i].vtwegTxt;          //销售渠道
		var spart=rows[i].spart;          //产品组
		var spartTxt=rows[i].spartTxt;          //产品组
		var vkbur=rows[i].vkbur;          //销售部门
		var vkburTxt=rows[i].vkburTxt;          //销售部门
		list.push({
			'vkorg':vkorg,
			'vkorgTxt':vkorgTxt,
			'vtweg':vtweg,
			'vtwegTxt':vtwegTxt,
			'spart':spart,
			'spartTxt':spartTxt,
			'vkbur':vkbur,
			'vkburTxt':vkburTxt
		});
		
		/*var num=$('#num').val();
		if(''==$('#num').val()){
		window.parent.returnTvkbz(vkorg,vkorgTxt,vtweg,vtwegTxt,spart,spartTxt,vkbur,vkburTxt);
		}
		else{
			window.parent.returnTvkbz(vkorg,vkorgTxt,vtweg,vtwegTxt,spart,spartTxt,vkbur,vkburTxt,num);
		}*/
	}
	window.parent.returnTvkbzList(list);
	window.parent.closeOrgTree();
}