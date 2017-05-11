$(document).ready(function() {
	loadGrid();
	loadGrid1();
	loadGrid2();
	$('#hideFrame').bind('load', promgtMsg);
    changeFlag = 'N';
});
function loadGrid() {	
	$('#datagrid')
			.datagrid(
					  {    
						iconCls : 'icon-list',
						title : '入库单明细',
						url :appUrl + '/stock/instockAction!getInStockDetailListByTotalId.jspa',
						
						loadMsg : '数据远程载入中,请等待...',
						singleSelect : false,
						pagination : false,
						nowrap : false,
						remoteSort : true,
						height : 310,
						columns : [ [
								{
									field : 'ck',
									checkbox : false
								},
								{
									field : 'instockdet_id',
									title : 'ID',
									width : setColumnWidth(0.04),
									align : 'center',
									sortable : true
								},{   
								   field : 'bezei',
									title : '品项',
									width : setColumnWidth(0.2),
									align : 'center'
								},
								{   
								   field : 'matnr',
									title : '品项',
									width : setColumnWidth(0.2),
									align : 'center',
									hidden:  true,
								    editor : {
										type : 'text',
										options : {
											valueField : 'wid',
											textField : 'wid',
											required : true
										}
									}
								},
								{
									field : 'instockdetBatch',
									title : '批号',
									hidden: false,
									align : 'center',
									width : setColumnWidth(0.15)
								},
								{
									field : 'instockdet_number',
									title : '单数量',
									hidden: false,
									align : 'center',
									width : setColumnWidth(0.14),
									align : 'center'
								},
								{   
								   field : 'instockdet_unumber',
									title : '未收数量',
									width : setColumnWidth(0.13),
									align : 'center'
								},{   
								   field : 'instockdet_anumber',
									title : '现收数量',
									width : setColumnWidth(0.13),
									align : 'center'
								},{
									field : 'modifyDate',
									title : '操作',
									width : setColumnWidth(0.13),
									align : 'center',
								formatter : function(value, row, rec) {
										var id = row.INSTOCKDET_ID;
										var s = row.modifyDate;
//										var rec = $('#datagrid').datagrid('getRowIndex',row);
//										if(id !=''){
//										return  utcToDate(s);
//										}else if(id==''){
											return '<img style="cursor:pointer" onclick="deleteIndex('
												+rec+
												')" title="删除此行" src='
												+ imgUrl
												+ '/images/actions/action_del.png align="absMiddle"></img>';
//										}
									}
								}]],
						onClickRow:function(rowIndex,rowData){			
							 var id = rowData.detailId;
							 var md = rowData.modifier;
                         if (!md){
                             $('#datagrid').datagrid('beginEdit', rowIndex);
                            }
                            lastIndex=rowIndex;
                      }, 
						toolbar : [ "-", {
							text : '新增',
							iconCls : 'icon-add',
							handler : function() {
								addDW();
							}
						},   "-" ]								
					});
	var p = $('#datagrid').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '第',
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	});
}
function loadGrid1(){
	$('#wid').combogrid({
		panelWidth : 400,
		panelHight : 600,
		idField : 'matnr',
		textField : 'bezei',
		pagination : true,// 是否分页
		// rownumbers : true,// 序号
		collapsible : false,// 是否可折叠的
		// fit : true,// 自动大小
		method : 'post',
		// multiple : true,
		url : appUrl + '/goal/goalAction!getMatList.jspa',
		columns : [ [ {
			field : 'ck',
			checkbox : true,
			hidden : true
		},  {
			field : 'matnr',
			title : '物料号',
			width : 120
		},
			{
			field : 'mvgr1',
			title : '物料组',
			align : 'center',
			width : 120
		}, {
			field : 'bezei',
			title : '物料组描述',
			align : 'center',
			width : 100
		}] ],
		toolbar : '#toolbar1'
	});
}
function loadGrid2() {
	$('#id').combogrid({
		panelWidth : 400,
		panelHight : 600,
		idField : 'kunnr',
		textField : 'name1',
		pagination : true,// 是否分页
		// rownumbers : true,// 序号
		collapsible : false,// 是否可折叠的
		// fit : true,// 自动大小
		method : 'post',
		// multiple : true,
		url : appUrl + '/goal/goalAction!getKunner.jspa',
		columns : [ [ {
			field : 'ck',
			checkbox : true,
			hidden : true
		},  {
			field : 'id',
			title : '客户ID',
			hidden : true,
			width : 60
		},
			{
			field : 'kunnr',
			title : '客户编号',
			align : 'center',
			width : 120
		}, {
			field : 'name1',
			title : '客户名称',
			align : 'center',
			width : 100
		}, {
			field : 'mobNumber',
			title : '手机',
			align : 'center',
			width : 150
		}] ],
		toolbar : '#toolbar2'
	});

}

function formatD(date){
return new Date(date).getFullYear()+"-"+ (new Date(date).getMonth()+1)+"-"+  new Date(date).getDate();
}

//function formatS(str){
//return str.substring(0, 10);
//}

function searcher1(val) {
	val = encodeURIComponent(val);
	$('#wid').combogrid({
		url : appUrl + '/goal/goalAction!getMatJsonList.jspa?value=' + val
	});
	$('#wid').combogrid("grid").datagrid('reload');
}
function searcher(val) {
	val = encodeURIComponent(val);
	$('#id').combogrid({
		url : appUrl + '/goal/goalAction!getKunnerJsonList.jspa?value=' + val
	});
	$('#id').combogrid("grid").datagrid('reload');

}
function initDtPlan(title, url, WWidth, WHeight) {
	var url = appUrl + url;
	var $win = $("#DtPlan")
			.window(
					{
						title : title,
						width : WWidth,
						height : WHeight,
						content : '<iframe frameborder="no" width="100%" height="100%" src='
								+ url + '/>',
						shadow : true,
						modal : true,
						closed : true,
						closable : true,
						minimizable : true,
						maximizable : false,
						collapsible : true,
						draggable : true
					});
	$win.window('open');
}

function addDW(){
		initDtPlan('入库单创建', '/instockAction!createStockPrepare.jspa', 490, 350);
//	   $('#datagrid').datagrid('appendRow',{
//		   INSTOCKDET_BATCH:'',
//		   wid:'',
//		   INSTOCKDET_UNUMBER1:'',
//		   INSTOCKDET_UNUMBER:'',
//		   INSTOCKDET_ANUMBER:''
//		   });
} 

function deleteIndex(rec) {
	 $('#datagrid').datagrid('deleteRow',rec);
}
//
//function deleteIndex(rec) {
//	 $('#datagrid').datagrid('deleteRow',rec);
//}

//function deleteDt(){
//$.messager.confirm('Confirm', '是否批量删除?', function(r) {
//		if (r) {
//			var rows = $('#datagrid').datagrid('getSelections');
//			if (rows == '') {
//				$.messager.alert('Tips', '  没有行被选中!');
//				return;
//			}
//			var detailList = [];
//			for ( var i = 0; i < rows.length; i++){
//				if(rows[i].modifier){
//               	$.messager.alert('Tips', '该里程碑已反馈不能删除!');
//				return;
//			 }	if(!rows[i].detailId){
//				$.messager.alert('Tips', '该里程碑还未保存!');
//				return;
//				}
//				    detailList.push({
//					"memo" : rows[i].memo,
//					"userId" : rows[i].userId,
//					"month" :$("#month").val(),
//				    "year" :$("#year").val(), 
//				    "detailId" :rows[i].detailId,
//				    "type" : 'D'
//				});
//			}
//			$("#detailList").val($.toJSON(detailList));
//			var form = window.document.forms[0];
//			form.action = "workPlan!dissolutionWorkPlan.jspa?changeFlag="+changeFlag+"&totalId=" + totalId;
//			form.target = "hideFrame";
//			form.submit();
//		}
//	});
//}
function save() {
	var pa = $("#instock_total_id").numberbox('isValid');
	var ft = $("#instock_send_place").validatebox('isValid');
	var aw=  $("#instock_good_place").combobox('isValid');
	var as=  $("#id").combobox('isValid');
	if (! (pa && ft &&  aw  && as)) {
		return;
	}
	$.messager.confirm('Confirm', '是否提交?', function(r) {
		if (r) {
			var rows = $('#datagrid').datagrid('getData').rows;
			var detailList= [] ;	
			for ( var i = 0; i < rows.length; i++) {
				    detailList.push({
				    "instock_total_id":$("#instock_total_id").val(),
				    "instock_send_place":$("#instock_send_place").val(),
				    "instock_good_place":$("#instock_good_place").val(),
				    "instock_provide_id":$("#id").combobox("getValue"),
				    "instock_provide_name":$("#id").combobox("getText"),
				    "instockdetBatch":rows[i].instockdetBatch,
					"matnr" : rows[i].matnr,
					"instockdet_number":rows[i].instockdet_number,
					"instockdet_unumber" : rows[i].instockdet_unumber,
					"instockdet_anumber" : rows[i].instockdet_anumber
				});
			}
			$("#detailList").val($.toJSON(detailList));
			var form = window.document.forms[0];
			form.action = "instockAction!createInstock.jspa";
			form.target = "hideFrame";
			form.submit();
		}
	});
}
function setColumnWidth(percent) {
	return $(this).width() * percent;
}
function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.spanFlag = encodeURIComponent($("#spanFlag").val());
    queryParams.detailId = encodeURIComponent($("#detailId").val());
    queryParams.memo = encodeURIComponent($("#memo").val());
  //  alert(($("#finishTime1").val()));
	$("#datagrid").datagrid('load');
}

function searchProjectMan(rec) {
	$('#datagrid').datagrid('unselectAll');
	$('#datagrid').datagrid('unselectRow', rec);
	var rec = encodeURIComponent(rec);
	initDtPlan('项目成员', '/workPlan!searchProjectMan.jspa?totalId='+totalId,
			 340, 430);
   window.index = rec
}


function chosePerson(rec){
	var rec = encodeURIComponent(rec);
	initDtPlan('选择处理人','/workPlan!toAuthorizeAddMain.jspa?rec='+rec,600,400);
	window.index = rec
}
function closeDtPlan() {
/*	var row = $('#datagrid').datagrid('selectRow',window.index);
	row.userName=$('#userName').val();
	row.userId = $('#userId').val();
    var rows = $('#datagrid').datagrid('getSelections');
	for ( var i = 0; i < rows.length; i++) {
			var rowIdx = $('#datagrid').datagrid('getRowIndex',rows[i]);
			$('#datagrid').datagrid('endEdit', rowIdx);
			var rows = $('#datagrid').propertygrid('getChanges');
	}*/
	$("#DtPlan").window('close');	
}
function close(){
	window.parent.closeDtPlan();
	var form = window.document.forms[0];
	form.action = "instockAction!deleteList.jspa";
	form.target = "hideFrame";
	form.submit();	
}

document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		search();
		return false;
	}
	return true;
};
function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if(successResult) {
		$.messager.confirm('Tips', successResult, function(r) {
			if (r) {
				search();
				window.parent.search();
				}
			});
		//$.messager.alert('Tips', successResult, 'info');
		
	}
}
function transfer(userId,userName)	{
			var rows = $('#datagrid').datagrid('getSelected'); //编辑时 选择这一行
			rows.userName=userName;
			rows.userId = userId;
			var rowIdx = $('#datagrid').datagrid('getRowIndex',rows);
			$('#datagrid').datagrid('endEdit', rowIdx);
			var rows = $('#datagrid').propertygrid('getChanges');
}
function utcToDate(utcCurrTime) {
	utcCurrTime = utcCurrTime + "";
	var date = "";
	var month = new Array();
	month["Jan"] = 1;
	month["Feb"] = 2;
	month["Mar"] = 3;
	month["Apr"] = 4;
	month["May"] = 5;
	month["Jun"] = 6;
	month["Jul"] = 7;
	month["Aug"] = 8;
	month["Sep"] = 9;
	month["Oct"] = 10;
	month["Nov"] = 11;
	month["Dec"] = 12;
	var week = new Array();
	week["Mon"] = "一";
	week["Tue"] = "二";
	week["Wed"] = "三";
	week["Thu"] = "四";
	week["Fri"] = "五";
	week["Sat"] = "六";
	week["Sun"] = "日";

	str = utcCurrTime.split(" ");
	date = str[5] + "-";
	date = date + month[str[1]] + "-" + str[2] ;
	return date;
}
