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
						url : appUrl + '/stockReport/stockReportAction!searchStockDateList.jspa',
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						rownumbers : true,
						height : 250,
						width : 'auto',
						columns :[[ {
							field : 'id',
							title : '周数',
							width : 80,
							align : 'center',
							formatter : function(value, row, index) {
								if(value=="6"){
									return "月库存";
								}else{
									return value;
								}
							}
						}, {
							field : 'checkTime',
							title : '库存所属日期',
							width : 80,
							align : 'center'
						}, {
							field : 'startDateStr',
							title : '开始时间',
							width : 200,
							align : 'center'
						}, {
							field : 'endDateStr',
							title : '结束时间',
							width : 200,
							align : 'center'
						}]]
					});
	// 分页控件
//	var p = $('#datagrid').datagrid('getPager');
//	$(p).pagination({
//		pageSize : 10,
//		pageList : [ 10, 20, 30 ],
//		beforePageText : '第',
//		afterPageText : '页    共 {pages} 页',
//		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
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