$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '人员列表',
						url : appUrl
								+ '/treeAction!getEmpListByOrgId.jspa',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : true,
						pagination : false,
						nowrap : true,
						remoteSort : true,
						height : 380,
						onDblClickRow : function(rowIndex, rowData) {
							// $("#source").append("<div class='drag-item'
							// userId='"+rowData.userId+"'>"+rowData.userName+"</div>");
							$("#source")
									.append(
											'<div class="drag-item" userId="'
													+ rowData.userId
													+ '"><img src="'
													+ imgUrl
													+ '/images/actions/action_roles.png" align="absMiddle"></img>'
													+ rowData.userName
													+ '</div>');
							reloadST();
						},
						columns : [ [  {
							field : 'userId',
							title : '人员ID',
							align : 'center',
							hidden : true
						},  {
							field : 'posName',
							title : '岗位名称',
							width : 110,
							align : 'center'
						},{
							field : 'userName',
							title : '人员姓名',
							width : 90,
							align : 'center'
						}] ]
					});
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}


$(document)
		.ready(
				function() {
					$('#orgTree').tree({
						onContextMenu : function(e, node) {
							e.preventDefault();
							$('#treeMenu').menu('show', {
								left : e.pageX,
								top : e.pageY
							});
						}

					});
					$('#orgTree')
							.tree(
									{
										method : 'post',
										animate : true,
										url : appUrl
												+ '/treeAction!getOrgTreeListByAjax.jspa?node=0',
										onBeforeExpand : function(node, param) {
											$('#orgTree').tree('options').url = appUrl
													+ "/treeAction!getOrgTreeListByAjax.jspa?node="
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

// function select(selectedId, selectedName) {
// document.getElementById("orgId").value = selectedId;
// document.getElementById("orgName").value = selectedName;
// window.parent.returnValue(selectedId, selectedName);
// window.parent.closeOrgTree();
// }

function search(id) {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	// queryParams.orgId = encodeURIComponent($("#orgId").val());
	queryParams.orgId = encodeURIComponent(id);
	$("#datagrid").datagrid('load');
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'error');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info', function() {
			window.close();
		});
	}
}

// ///
function reloadST() {
	$('.drag-item').draggable({
		revert : true,
		deltaX : 0,
		deltaY : 0,
		proxy : 'clone',
		revert : true,
		cursor : 'auto',
		onStartDrag : function() {
			$(this).draggable('options').cursor = 'not-allowed';
			$(this).draggable('proxy').addClass('dp');
		},
		onStopDrag : function() {
			$(this).draggable('options').cursor = 'auto';
		}
	}).droppable({
		onDragOver : function(e, source) {
			$(source).draggable('options').cursor = 'auto';
			$(source).draggable('proxy').css('border', '1px solid red');
			$(this).addClass('over');
		},
		onDragLeave : function(e, source) {
			$(source).draggable('options').cursor = 'not-allowed';
			$(source).draggable('proxy').css('border', '1px solid #ccc');
			$(this).removeClass('over');
		},
		onDrop : function(e, source) {
			$(source).insertAfter(this);
			$(source).removeClass('over');
		}
	});
	$('#target,#source').droppable({
		onDragEnter : function(e, source) {
			$(source).draggable('options').cursor = 'auto';
			$(source).draggable('proxy').css('border', '1px solid red');
			$(this).addClass('over');
		},
		onDragLeave : function(e, source) {
			$(source).draggable('options').cursor = 'not-allowed';
			$(source).draggable('proxy').css('border', '1px solid #ccc');
			$(this).removeClass('over');
		},
		onDrop : function(e, source) {
			$(this).append(source);
			$(this).removeClass('over');
		}
	});
}
function save() {
	var context = "";
	$("#source div").each(function() {
		if (context.length > 0) {
			context = context + "," + $(this).attr("userId");
		} else {
			context = $(this).attr("userId");
		}
	});
	if ("" == context) {
		alert("请选择处理人列表！");
		return;
	} else {
		window.parent.document.getElementById("userList").value = context;
		close();
	}
}
function close() {
	window.parent.closeMaintEvent();
}

function serachPerson(name) {
	var url = appUrl + '/treeAction!getEmpListByUserName.jspa';
	// 下个处理人
	$.ajax({
		type : "post",
		async : false,
		url : url,
		data : {
			userName1: encodeURIComponent(name)
		},
		success : function(obj) {
			console.info(obj);
			$('#datagrid').datagrid('loadData', obj);
		}
	});
	
}