$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);
	loadGrid();
	loadGrid1();
	loadGrid2();
	
	$('#beginDate').datebox({
		onSelect : function(d) {
				$('#beginDate').val(utcToDate(d));
		}
	});
	$('#endDate').datebox({
		onSelect : function(d) {
				$('#endDate').val(utcToDate(d));
		}
	});
	
	
});


function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else {
		$.messager.alert('Tips', successResult, 'info');
		var type = $("#deleteType").val();
		if(type == 'model'){
			search_1();
		}else if(type == 'att'){
			search_2();
		}
	}
}

function loadGrid() {
	$('#datagrid_1').datagrid(
					{
						iconCls : 'icon-list',
						title : '��ѯ���',
						url : appUrl + '/stock/instockAction!getOutStockList.jspa',
//						queryParams: {
//                    		endDate: $("#endDate").val(),
//		                    beginDate:$("#beginDate").val(),
//	                    	custId : $("#id").combobox("getValue")
//	                          },
						loadMsg : '����Զ��������,��ȴ�...',
						singleSelect : true,
						pagination : true,
						nowrap : true,
						remoteSort : true,
						striped : true,
						height : height,
						columns : [[{
					field : 'instock_id',
					title : '���к�',
					width : setColumnWidth(0.035),
					align : 'center',
					sortable : true
				}, {
					field : 'instock_send_place',
					title : '�����ص�',
					width : setColumnWidth(0.05),
					align : 'center'
					
				},{
					field : 'instock_total_id',
					title : '����',
					width : setColumnWidth(0.045),
					align : 'center'
					
				},{
					field : 'instock_good_place',
					title : '���ص�',
					width : setColumnWidth(0.06),
					align : 'center',
					formatter : function(value,row,rec){
									var s = row.instock_good_place;
									if(s=='1'){
									return "��������";
									}
									}
				}, 
					{
					field : 'instock_flag',
					title : '�������',
					width : setColumnWidth(0.07),
					align : 'center',
					formatter : function(value,row,rec){
						var s = row.instock_flag;
									if(s=='F'){
									return "��ͨ����";
									}else{
								     return "�̵����";
					}
					}
					},
				{
					field : 'instock_provide_id',
					title : '������ID',
					width : setColumnWidth(0.07),
					align : 'center'
				}, {
					field : 'instock_provide_name',
					title : '����������',
					width : setColumnWidth(0.1),
					align : 'center'
				},{
					field : 'price',
					title : '����',
					width : setColumnWidth(0.04),
					align : 'center',
					formatter : function(value, row, index) {
						var id = row.instock_total_id
							return '<img style="cursor:pointer" onclick="updateInstock('
									+ id
									+ ')" title="�޸Ŀ�浥" src='
									+ imgUrl
									+ '/images/actions/action_edit.png align="absMiddle"></img>';			
					}
				}]],
						 onClickRow:function(rowIndex, rowData){
						 	    $("#instock_id").val(rowData.instock_id);
						 		$("#instock_total_id").val(rowData.instock_total_id);
						 		$("#instock_provide_id").val(rowData.instock_provide_id);
						 		$("#instock_good_place").val(rowData.instock_good_place);
	
    				            search_2();
    				            var options = $("#datagrid_2").datagrid("options");
        						options.title = "�ܵ���"+rowData.instock_id + " -- ��ϸ";
        						$("#datagrid_2").datagrid(options);
   							},
   						toolbar : [ "-", {
							text : '����',
							iconCls : 'icon-add',
							handler : function() {
								createInStock();
							}
						}, "-" ]
					});

	// ��ҳ�ؼ�
	var p = $('#datagrid_1').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '��',
		afterPageText : 'ҳ    �� {pages} ҳ',
		displayMsg : '��ǰ��ʾ {from} - {to} ����¼   �� {total} ����¼'
	});
	$('#datagrid_2').datagrid(
					{
						iconCls : 'icon-list',
						title : '��ѯ���',
						url : appUrl + '/stock/instockAction!getInStockDetailListByTotalId1.jspa?type='+'O',
						loadMsg : '����Զ��������,��ȴ�...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						remoteSort : true,
						striped : true,
						height : height,
						columns : [ [
								{
									field : 'instockdet_id',
									title : 'ID',
									width : setColumnWidth(0.048),
									align : 'center',
									sortable : true
								},{   
								   field : 'bezei',
									title : 'Ʒ��',
									width : setColumnWidth(0.11),
									align : 'center'
								},
								{   
								   field : 'matnr',
									title : 'Ʒ��',
									width : setColumnWidth(0.08),
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
									title : '����',
									hidden: false,
									align : 'center',
									width : setColumnWidth(0.08)
								},
								{
									field : 'instockdet_number',
									title : '�ܵ�����',
									hidden: false,
									align : 'center',
									width : setColumnWidth(0.08),
									align : 'center'
								},
								{   
								   field : 'instockdet_unumber',
									title : 'δ������',
									width : setColumnWidth(0.08),
									align : 'center'
								},{   
								   field : 'instockdet_anumber',
									title : '��������',
									width : setColumnWidth(0.07),
									align : 'center'
								}] ],
						toolbar : [ "-", {
							text : '����',
							iconCls : 'icon-add',
							handler : function() {
								createInStockdet();
							}
						},"-"]
					});
	// ��ҳ�ؼ�
	var r = $('#datagrid_2').datagrid('getPager');
	$(r).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '��',
		afterPageText : 'ҳ    �� {pages} ҳ',
		displayMsg : '�� {total} ����¼'
	});
}

function loadGrid2() {
	$('#id').combogrid({
		panelWidth : 400,
		panelHight : 600,
		idField : 'kunnr',
		textField : 'name1',
		pagination : true,// �Ƿ��ҳ
		// rownumbers : true,// ���
		collapsible : false,// �Ƿ���۵���
		// fit : true,// �Զ���С
		method : 'post',
		// multiple : true,
		url : appUrl + '/goal/goalAction!getKunner.jspa',
		columns : [ [ {
			field : 'ck',
			checkbox : true,
			hidden : true
		},  {
			field : 'id',
			title : '�ͻ�ID',
			hidden : true,
			width : 60
		},
			{
			field : 'kunnr',
			title : '�ͻ����',
			align : 'center',
			width : 120
		}, {
			field : 'name1',
			title : '�ͻ�����',
			align : 'center',
			width : 100
		}, {
			field : 'mobNumber',
			title : '�ֻ�',
			align : 'center',
			width : 150
		}] ],
		toolbar : '#toolbar2'
	});

}

function loadGrid1() {
	$('#wid').combogrid({
		panelWidth : 400,
		panelHight : 600,
		idField : 'matnr',
		textField : 'bezei',
		pagination : true,// �Ƿ��ҳ
		// rownumbers : true,// ���
		collapsible : false,// �Ƿ���۵���
		// fit : true,// �Զ���С
		method : 'post',
		// multiple : true,
		url : appUrl + '/goal/goalAction!getMatList.jspa',
		columns : [ [ {
			field : 'ck',
			checkbox : true,
			hidden : true
		},  {
			field : 'matnr',
			title : '���Ϻ�',
			width : 120
		},
			{
			field : 'mvgr1',
			title : '������',
			align : 'center',
			width : 120
		}, {
			field : 'bezei',
			title : '����������',
			align : 'center',
			width : 100
		}] ],
		toolbar : '#toolbar1'
	});
}
function setColumnWidth(percent) {
	return $(this).width() * percent;
}

function search_1() {
	var queryParams = $('#datagrid_1').datagrid('options').queryParams;
    queryParams.beginDate= encodeURIComponent($("#beginDate").val());
	queryParams.custId =encodeURIComponent( $("#id").combobox("getValue"));
    queryParams.endDate= encodeURIComponent($("#endDate").val());
	$("#datagrid_1").datagrid('reload');
}

function clearValue_1(){
	$('#beginDate').val("");
    $('#endDate').val("");
	$("#beginDate").datebox("setValue", '');
	$("#endDate").datebox("setValue", '');
	$("#id").combobox("setValue", '');
}

function search_2() {
	var queryParams = $('#datagrid_2').datagrid('options').queryParams;
	
	queryParams.instock_total_id = $("#instock_total_id").val();
	queryParams.instock_provide_id = $("#instock_provide_id").val();
	queryParams.instockdetBatch = $("#instockdetBatch").val();
	queryParams.matnr =encodeURIComponent( $("#wid").combobox("getValue"));
	$("#datagrid_2").datagrid('reload');
}

function clearValue_2(){
	$("#instockdetBatch").val("");
	$("#wid").combobox("setValue", '');
//	$("#mprAttDescript").val("");
}

function searcher(val) {
	val = encodeURIComponent(val);
	$('#id').combogrid({
		url : appUrl + '/goal/goalAction!getKunnerJsonList.jspa?value=' + val
	});
	$('#id').combogrid("grid").datagrid('reload');

}

function searcher1(val) {
	val = encodeURIComponent(val);
	$('#wid').combogrid({
		url : appUrl + '/goal/goalAction!getMatJsonList.jspa?value=' + val
	});
	$('#wid').combogrid("grid").datagrid('reload');

}


function createInStock(){
	initMaintModelAtt('�����ܵ�', '/instockAction!createStockPrepareO.jspa', 450, 350);
}

function updateInstock(id){
initMaintModelAtt('�޸��ܵ�', '/instockAction!updateStockPrepareO.jspa?instock_total_id='+ id, 450, 400);
}

function createInStockdet(){
	var instock_total_id = $("#instock_total_id").val();
	var instock_id = $("#instock_id").val();
	var instock_provide_id=$("#instock_provide_id").val();
	var instock_good_place= $("#instock_good_place").val();
	var type = "O";
	if(instock_id=="" || instock_id.length==0){
		$.messager.alert('Tips', "��ѡ��һ���ܵ�!", 'warning');
		return;
	}
	initMaintModelAtt('�����ϸ', '/instockAction!createStockdtPrepareO.jspa?instock_total_id='+ instock_total_id+
	"&instock_provide_id="+instock_provide_id+"&instock_good_place="+instock_good_place+"&instock_id="+instock_id, 450, 400);
}

//function updateMprAtt(rid){
//	initMaintModelAtt('�޸��ܵ�', '/instockAction!updateStockPrepare1.jspa?instock_total_id='+ instock_total_id, 450, 400);
//}

function closeMaintModelAtt(){
	$("#maintModelAtt").window('close');
}

function deleteMprModel(rid){
	$("#deleteType").val("model");
	$("#ids").val(rid);
	var form = window.document.forms[0];
	form.action = appUrl + '/activityPlan/miaModelAction!deleteMprModel.jspa';
	form.target = "hideFrame";
	form.submit();	
}

function deleteMprAtt(){
	$.messager.confirm('Confirm', '�Ƿ�ɾ��ģ������?', function(r) {
		if (r) {
			var rows = $('#datagrid_2').datagrid('getSelections');
			if (rows == '') {
				$.messager.alert('Tips', '��ѡ������!', 'warning');
				return;
			}
			$("#deleteType").val("att");
			var ids = [];
			for ( var i = 0; i < rows.length; i++) {
				ids.push(rows[i].mprAttId);
			}
			$("#ids").val(ids);
			var form = window.document.forms[0];
			form.action = appUrl + '/activityPlan/miaModelAction!deleteMprAttribute.jspa';
			form.target = "hideFrame";
			form.submit();
		}
	});		
}

//�������ڶ���
function initMaintModelAtt(title, url, WWidth, WHeight) {
	var url = appUrl + url;
	var $win = $("#maintModelAtt")
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
						minimizable : false,
						maximizable : false,
						collapsible : false,
						draggable : true
					});
				
	$win.window('open');
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
	week["Mon"] = "һ";
	week["Tue"] = "��";
	week["Wed"] = "��";
	week["Thu"] = "��";
	week["Fri"] = "��";
	week["Sat"] = "��";
	week["Sun"] = "��";

	str = utcCurrTime.split(" ");
	date = str[5] + "-";
	date = date + month[str[1]] + "-" + str[2] ;
	return date;
}
