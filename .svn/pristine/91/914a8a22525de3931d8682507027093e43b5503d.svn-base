var groupName='';
var isPubGroup='';

$(document)
		.ready(
				function() {
					//我的个人编组
					$('#priGroups').combogrid({
						panelWidth:350,
//						idField:'stationId',
						textField:'groupName',
//						pagination : true,//
		                rownumbers:true,//
		                collapsible:false,//
		                fit: true,//
		                method:'post',  
		                multiple:false,
		                editable:false,
						url:appUrl + '/smsAction!getGroups.jspa?isPubGroup=N',  
						columns:[[
//							  {field:'isPubGroup',title:'是否为公共编组',width:100,hidden:true},  
		                    {field:'groupName',title:'编组名称',width:300},  
						]],
						onSelect: function (rowIndex, rowData){
							groupName = rowData.groupName
							isPubGroup = 'N'
							$('#myPubGroups').combogrid('clear');
						}
//						toolbar:'#toolbar'
						

					});
//					var q = $('#priGroups').combogrid("grid").datagrid();
//					$('#priGroups').combo({  
//						multiple:true  
//					});
//					$(q).pagination({
//						pageSize : 10,
//						pageList : [ 10, 20, 30 ],
//						beforePageText : '第',
//						afterPageText : '页    共 {pages} 页',
//						displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
//					});	
					
					
					
					//我的公共编组
					$('#myPubGroups').combogrid({
						panelWidth:350,
//						idField:'stationId',
						textField:'groupName',
//						pagination : true,//
		                rownumbers:true,//
		                collapsible:false,//
		                fit: true,//
		                method:'post',  
		                multiple:false,
		                editable:false,
						url:appUrl + '/smsAction!getGroups.jspa?isPubGroup=MY',  
						columns:[[
//							  {field:'id',title:'POSID',width:100,hidden:true},  
		                    {field:'groupName',title:'编组名称',width:300},  
						]],
						onSelect: function (rowIndex, rowData){
							groupName = rowData.groupName
							isPubGroup = 'Y'
							$('#priGroups').combogrid('clear');
						}
//						toolbar:'#toolbar'
						

					});
				});
					
//					var q = $('#myPubGroups').combogrid("grid").datagrid();
//					$('#myPubGroups').combo({  
//						multiple:true  
//					});
//					$(q).pagination({
//						pageSize : 10,
//						pageList : [ 10, 20, 30 ],
//						beforePageText : '第',
//						afterPageText : '页    共 {pages} 页',
//						displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
//					});

function saveGroupName() {
	if ("" == groupName) {
		$.messager.alert('Error', '请选择编组！', 'error');
		return;
	}
	window.parent.document.getElementById("selectGroupName").value = groupName;
	window.parent.document.getElementById("selectIsPubGroup").value = isPubGroup;
	window.parent.returnGroup();
}

function close() {
	window.parent.closeWindow();
}

function cancel(){
	window.location.reload();
}
