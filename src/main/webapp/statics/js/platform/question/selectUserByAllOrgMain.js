$(document).ready(function() {
	loadGrid();
	
	$('#orgTree').tree({
		onContextMenu : function(e, node) {
			e.preventDefault();
			$('#treeMenu').menu('show', {
				left : e.pageX,
				top : e.pageY
			});
		}
	});

	$('#orgTree').tree({
		method : 'post',
		animate : true,
		url : appUrl + '/questionAction!getAllOrgTreeByAjax.jspa',
		onBeforeExpand : function(node, param) {
			$('#orgTree').tree('options').url = appUrl + "/orgTreeAjaxAction!getOrgTreeListByAjax.jspa?node=" + node.id;
		},
		onClick : function(node) {// �����¼�
			$(this).tree('toggle', node.target);
			if (!node.state) {
				add(node.text, node.attributes);
			}
			search(node.id, node.text);
		}
	});
});

function loadGrid() {
	$('#datagrid').datagrid(
					{
						iconCls : 'icon-list',
						title : '��Ա�б�',
						striped : true,
						url : appUrl + '/questionAction!getEmpListByOrgId.jspa',
						loadMsg : '����Զ��������,��ȴ�...',
						singleSelect : true,
						pagination : false,
						nowrap : true,
						remoteSort : true,
						height : height,
						columns : [ [
								{
									field : 'ck',
									checkbox : true
								},
								{
									field : 'userId',
									title : '��ԱID',
									width : setColumnWidth(0.1),
									align : 'center',
									hidden: true
								},
								{
									field : 'posName',
									title : '��λ����',
									width : 150,
									align : 'center'
								},
								{
									field : 'userName',
									title : '��Ա����',
									width : 120,
									align : 'center'
								},
								{
									field : 'orgId',
									title : '����ID',
									align : 'center',
									hidden: true
								}
								 ] ],
						toolbar : [ "-", {
							text : 'ȷ��',
							iconCls : 'icon-save',
							handler : function() {
								selectUser();
							}
						}, "-" ]
					});
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}

function selectUser(){
	var rows = $('#datagrid').datagrid('getSelections');
	if(rows == ''){
		$.messager.alert('Tips', '��ѡ������!', 'warning');
		return;
	}
	var x = new Array();
	x[0] = rows[0].userId;
	x[1] = rows[0].userName;
	x[2] = rows[0].orgId;
	x[3] = $("#orgName").val();
	window.parent.saveUser(x);
}

function search(id, text) {
	$("#orgName").val(text);
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.orgId =  encodeURIComponent(id);
	$("#datagrid").datagrid('load');
}
