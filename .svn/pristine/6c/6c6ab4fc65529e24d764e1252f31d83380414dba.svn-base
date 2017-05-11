$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);

});

function loadGrid() {
	$('#datagrid').datagrid(
					{
						iconCls : 'icon-list',
						title : '人员列表',
						url : appUrl + '/batChangeAction!getStationListByOrgId.jspa?posId='+$("#posId").val(),
						loadMsg : '数据远程载入中,请等待...',
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
									title : '岗位ID(编制)',
									width : setColumnWidth(0.1),
									align : 'center',
									hidden: true
								},
								{
									field : 'userId',
									title : '人员ID',
									width : setColumnWidth(0.1),
									align : 'center',
									hidden: true
								},
								
								{
									field : 'userName',
									title : '人员姓名',
									width : 90,
									align : 'center'
								},{
									field : 'stationName',
									title : '岗位名称',
									width : 100,
									align : 'center'
								}
								 ] ],
						toolbar : [ "-", {
							text : '确定',
							iconCls : 'icon-save',
							handler : function() {
							createAuthorization();
							}
						}, "-" ]
					});

}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}

function createAuthorization(){
	var rows = $('#datagrid').datagrid('getSelections');
	if(rows == ''){
		$.messager.alert('Tips', '没有行项目被选中!');
				return;
	}
	if(rows.length > 1){
			$.messager.alert('Tips', '只能选择一行!', 'warning');
			return;
		}
		var nextUserName = rows[0].userName;
		var nextUserId = rows[0].userId;
		var nextUserMob=rows[0].busMobilephone;
		window.parent.returnUser(nextUserId,nextUserName,nextUserMob);
	    window.parent.closeOrgTree();
		
}

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
				/*	var nd=$("#node").val();
					var name=encodeURIComponent($("#orgName").val());
					var url=appUrl+ '/batChangeAction!getOrgTreeListByAjax.jspa?node=0';
					if(nd!=null&&nd!=''&&nd!=undefined){
						url=appUrl+ '/batChangeAction!getOrgTreeByParentOrg.jspa?node='+nd+'&orgName='+name;
					}*/
					$('#orgTree')
							.tree(
									{
										method : 'post',
										animate : true,
										url :appUrl+'/orgTreeAjaxAction!getUserOrgTreeByAjax.jspa?node=0'
											/*url*/ /*appUrl
												+ '/batChangeAction!getOrgTreeListByAjax.jspa?node=0'*/,
										onBeforeExpand : function(node, param) {
											$('#orgTree').tree('options').url = appUrl
												+ "/batChangeAction!getOrgTreeListByAjax.jspa?node="
													+ node.id;
										},
										onClick : function(node) {// 单击事件
											$(this).tree('toggle', node.target);
											if (!node.state) {
												add(node.text, node.attributes);
											}
											search(node.id);
											
										}
									});
				});
				
function searcherUser(name) {
	var url = appUrl + '/batChangeAction!getEmpListByUserName.jspa?userName='
			+ encodeURIComponent(name)+'&&posId='+$("#posId").val();
	$.post(url, '', function(data) {
		$('#datagrid').datagrid('loadData', data);
	}, 'json');
}
function search(id) {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
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