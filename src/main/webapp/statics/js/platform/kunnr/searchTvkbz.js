var list=[];
$(document).ready(function() {
	loadGrid();
	//search();
	//$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#datagrid').datagrid({
		iconCls : 'icon-list',
		title : '���۷�Χ�б�',
		url : appUrl + '/salesMsgAction!getTvkbzJsonList.jspa?vkorg = '+encodeURIComponent($("#vkorg").val()),
		loadMsg : '����Զ��������,��ȴ�...',
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
			title : '������֯',
			align : 'center'
		},
		{
			field : 'vkorgTxt',
			title : '������֯����',
			align : 'center'
		}, {
			field : 'vtweg',
			title : '��������',
			align : 'center'
		}, {
			field : 'vtwegTxt',
			title : '������������',
			align : 'center'
		}, {
			field : 'spart',
			title : '��Ʒ��',
			align : 'center'
		},  {
			field : 'spartTxt',
			title : '��Ʒ������',
			align : 'center',
			width:200
		} ] ],
		toolbar : [ "-", {
			text : 'ȷ��',
			iconCls : 'icon-save',
			handler : function() {
				save();
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
		$.messager.alert('Tips', '  û���б�ѡ��!');
		return;
	}
	for ( var i = 0; i < rows.length; i++) {
		var vkorg=rows[i].vkorg;          //������֯
		var vkorgTxt=rows[i].vkorgTxt;          //������֯
		var vtweg=rows[i].vtweg;          //��������
		var vtwegTxt=rows[i].vtwegTxt;          //��������
		var spart=rows[i].spart;          //��Ʒ��
		var spartTxt=rows[i].spartTxt;          //��Ʒ��
		var vkbur=rows[i].vkbur;          //���۲���
		var vkburTxt=rows[i].vkburTxt;          //���۲���
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