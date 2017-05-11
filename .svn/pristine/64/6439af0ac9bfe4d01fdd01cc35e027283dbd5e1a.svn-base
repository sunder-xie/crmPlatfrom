$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);
	loadGrid();
});

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '库存时间',		
						url : appUrl + '/stockReport/stockReportAction!searchKunnrManagerDateList.jspa',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						rownumbers : true,
						height : 500,
						width : 'auto',
						columns :[[ {
							field : 'id',
							title : '月份',
							width : 80,
							align : 'center'
						}, {
							field : 'checkTime',
							title : '截止日期',
							width : 80,
							align : 'center'
						}]]
					});
	 //分页控件
	var p = $('#datagrid').datagrid('getPager');
	$(p).pagination({
		pageSize : 20,
		pageList : [ 20 ],
		beforePageText : '第',
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
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
   
    var year = now.getFullYear();       //年
    var month = now.getMonth() + 1;     //月
    var day = now.getDate();            //日
   
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
	form.action = appUrl + "/stockReportAction!updateKunnrManagerDate.jspa";
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