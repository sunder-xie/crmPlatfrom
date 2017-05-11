$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);
	loadGrid();
});

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '���ʱ��',		
						url : appUrl + '/stockReport/stockReportAction!searchStockDateList.jspa',
						loadMsg : '����Զ��������,��ȴ�...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						rownumbers : true,
						height : 250,
						width : 'auto',
						columns :[[ {
							field : 'id',
							title : '����',
							width : 80,
							align : 'center',
							formatter : function(value, row, index) {
								if(value=="6"){
									return "�¿��";
								}else{
									return value;
								}
							}
						}, {
							field : 'checkTime',
							title : '�����������',
							width : 80,
							align : 'center'
						}, {
							field : 'startDateStr',
							title : '��ʼʱ��',
							width : 200,
							align : 'center'
						}, {
							field : 'endDateStr',
							title : '����ʱ��',
							width : 200,
							align : 'center'
						}]]
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

function loadData(){
	for(var i=1;i<=6;i++){
		if(i==5){
			continue;
		}
		$("#startDate"+i).datetimebox("setValue",curentTime()+"00:00:00");
		$("#endDate"+i).datetimebox("setValue",curentTime()+"23:59:59");
	}
}

function curentTime(){ 
    var now = new Date();
   
    var year = now.getFullYear();       //��
    var month = now.getMonth() + 1;     //��
    var day = now.getDate();            //��
   
    var clock = year + "-";
   
    if(month < 10)
        clock += "0";
   
    clock += month + "-";
   
    if(day < 10)
        clock += "0";
       
    clock += day + " ";
   
    return clock; 
} 

function submit(){
	var form = window.document.forms[0];
	form.action = appUrl + "/stockReportAction!updateStockDate.jspa";
	form.target = "hideFrame";
	form.submit();
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.confirm('Tips', successResult, function(r) {
			if (r) {
				loadGrid();
			}
		});
	}
}