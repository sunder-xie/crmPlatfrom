$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);

});
	var flag = $("#flagTemp").val();

function loadGrid() {
	$('#datagrid').datagrid(
					{
						iconCls : 'icon-list',
						title : '��Ա�б�',
						url : appUrl + '/batChangeAction!getStationListByOrgId.jspa',
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
									field : 'posId',
									title : '��λID(����)',
									width : setColumnWidth(0.1),
									align : 'center',
									hidden: true
								},
								
								{
									field : 'userName',
									title : '��Ա����',
									width : 90,
									align : 'center'
								},{
									field : 'stationName',
									title : '��λ����',
									width : 100,
									align : 'center'
								}
								 ] ],
						toolbar : [ "-", {
							text : 'ȷ��',
							iconCls : 'icon-save',
							handler : function() {
							createAuthorization();
							}
						}, "-" ]
					});

	// ��ҳ�ؼ�
//	var p = $('#datagrid').datagrid('getPager');
//	$(p).pagination({
//		pageSize : 10,
//		pageList : [ 10, 20, 30 ],
//		beforePageText : '��',
//		afterPageText : 'ҳ    �� {pages} ҳ',
//		displayMsg : '��ǰ��ʾ {from} - {to} ����¼   �� {total} ����¼'
//	});
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}
function searcherUser(name) {
	var url = appUrl + '/batChangeAction!getEmpListByUserName.jspa?userName='
			+ encodeURIComponent(name);
	$.post(url, '', function(data) {
		$('#datagrid').datagrid('loadData', data);
	}, 'json');
}
function createAuthorization(){
	var rows = $('#datagrid').datagrid('getSelections');
	if(rows == ''){
		$.messager.alert('Tips', 'û������Ŀ��ѡ��!');
				return;
	}
	if(rows.length > 1){
			$.messager.alert('Tips', 'ֻ��ѡ��һ��!', 'warning');
			return;
		}
		var nextUserName = rows[0].userName;
		var nextUserId = rows[0].posId;
		if(flag){
        	window.parent.transfer(rows[0].userId,rows[0].userName);
	        window.parent.closeDtPlan();
		}else{
		   window.parent.document.getElementById("userId").value = nextUserId;
	       window.parent.document.getElementById("userName").value = nextUserName;
	       var cc=$("#fiyId").val();
	       if(!(cc=="")){
	        window.parent.uploaddt()
	       }
	       window.parent.uploadyd();
		   window.parent.closeDtPlan();
		}
		
}

//var selectedId;
//var selectedName;
$(document)
		.ready(
				function() {
					$('#orgTree').tree({
						onContextMenu : function(e, node) {
							e.preventDefault();
//							selectedId = node.id;
//							selectedName = node.text;
							$('#treeMenu').menu('show', {
								left : e.pageX,
								top : e.pageY
							});
						}

					});
					var orgUrl= appUrl+'/orgTreeAjaxAction!getUserOrgTreeByAjax.jspa?node=0';
					if('B'==$('#orgCity').val()){
						orgUrl=appUrl+'/orgTreeAjaxAction!getOrgTreeListByAjax.jspa?node=0';
					}
					$('#orgTree')
							.tree(
									{
										method : 'post',
										animate : true,
										url : orgUrl,
										onBeforeExpand : function(node, param) {
											$('#orgTree').tree('options').url = appUrl
												+ "/batChangeAction!getOrgTreeListByAjax.jspa?node="
													+ node.id;
										},
										onClick : function(node) {// �����¼�
											$(this).tree('toggle', node.target);
											if (!node.state) {
												add(node.text, node.attributes);
											}
											search(node.id);
											
										}
									});
				});
				

function search(id) {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	//queryParams.orgId = encodeURIComponent($("#orgId").val());
	queryParams.orgId =  encodeURIComponent(id);
	$("#datagrid").datagrid('load');
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult != "") {
		$.messager.alert('Tips', failResult, 'error');
	} else {
		$.messager.alert('Tips', successResult, 'info', function() {
				window.parent.closeWindow();
				window.parent.search();
			});
	}
}