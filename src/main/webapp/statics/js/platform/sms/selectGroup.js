var groupName='';
var isPubGroup='';

$(document)
		.ready(
				function() {
					//�ҵĸ��˱���
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
//							  {field:'isPubGroup',title:'�Ƿ�Ϊ��������',width:100,hidden:true},  
		                    {field:'groupName',title:'��������',width:300},  
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
//						beforePageText : '��',
//						afterPageText : 'ҳ    �� {pages} ҳ',
//						displayMsg : '��ǰ��ʾ {from} - {to} ����¼   �� {total} ����¼'
//					});	
					
					
					
					//�ҵĹ�������
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
		                    {field:'groupName',title:'��������',width:300},  
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
//						beforePageText : '��',
//						afterPageText : 'ҳ    �� {pages} ҳ',
//						displayMsg : '��ǰ��ʾ {from} - {to} ����¼   �� {total} ����¼'
//					});

function saveGroupName() {
	if ("" == groupName) {
		$.messager.alert('Error', '��ѡ����飡', 'error');
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
