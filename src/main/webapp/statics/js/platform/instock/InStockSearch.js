var processInstanceId;
$(document).ready(function() {
			loadGrid();
			loadGrid1();
			loadGrid2();
			$('#year').combobox({
						onSelect : function(d) {
							$('#year').val(d.itemName);
						}
					});

			$('#month').combobox({
						onSelect : function(d) {
							$('#month').val(d.value);
						}
					});
		   $('#mark').combobox({
						onSelect : function(d) {
							$('#mark').val(d.value);
						}
					});		
//           $('#id').combobox({
//						onSelect : function(d) {
//							$('#id').val(d.kunnr);
//						}
//					});
//		    $('#wid').combobox({
//						onSelect : function(d) {
//							$('#wid').val(d.matnr);
//						}
//					});
			if ($("#flagTemp").val() != "Y") {
				document.getElementById("add").style.display = "none";
				document.getElementById("addOrg").style.display = "none";
				document.getElementById("approve").style.display = "none";
				document.getElementById("remove").style.display = "none";
				document.getElementById("mould").style.display = "none";
				document.getElementById("import").style.display = "none";
			}
			$('#hideFrame').bind('load', promgtMsg);
		});
function loadGrid() {
	var toolbar = $('#datagrid').datagrid({
		iconCls : 'icon-list',
		title : '��ѯ���',
		url : appUrl + '/stock/instockAction!getInStockList.jspa',
		queryParams: {
//		orgId: $("#orgId").val(),
//		year:$("#year").val(),
//		mark:$("#mark").val()
	    },
		loadMsg : '����Զ��������,��ȴ�...',
		singleSelect : false,
		pagination : true,
		nowrap : true,
		height : height,
		striped : true,
		columns : [[{
					field : 'ck',
					checkbox : true
				}, {
					field : 'instock_id',
					title : '���к�',
					width : setColumnWidth(0.1),
					align : 'center',
					sortable : true
				}, {
					field : 'instock_send_place',
					title : '�����ص�',
					width : setColumnWidth(0.12),
					align : 'center'
					
				},{
					field : 'instock_total_id',
					title : '�ܵ�Id',
					width : setColumnWidth(0.12),
					align : 'center'
					
				},{
					field : 'instock_good_place',
					title : '���ص�',
					width : setColumnWidth(0.12),
					align : 'center',
					formatter : function(value,row,rec){
									var s = row.instock_good_place;
									if(s=='1'){
									return "��������";
									}
									}
				}, 
				{
					field : 'instock_provide_id',
					title : '������ID',
					width : setColumnWidth(0.15),
					align : 'center'
				}, {
					field : 'instock_provide_name',
					title : '����������',
					width : setColumnWidth(0.15),
					align : 'center'
				},{
					field : 'instock_provide_name',
					title : '״̬',
					width : setColumnWidth(0.15),
					align : 'center'
				},{
					field : 'price',
					title : '����',
					width : setColumnWidth(0.15),
					align : 'center',
					formatter : function(value, row, index) {
						var id = row.instock_total_id
							return '<img style="cursor:pointer" onclick="updateInstock('
									+ id
									+ ')" title="�޸ľ�����Ŀ��" src='
									+ imgUrl
									+ '/images/actions/action_edit.png align="absMiddle"></img>';			
					}
				}]],
		toolbar : "#toolbar"
	});
	// ��ҳ�ؼ�
	var p = $('#datagrid').datagrid('getPager');
	$(p).pagination({
				pageSize : 10,
				pageList : [10, 20, 30],
				beforePageText : '��',
				afterPageText : 'ҳ    �� {pages} ҳ',
				displayMsg : '��ǰ��ʾ {from} - {to} ����¼   �� {total} ����¼'
			});
	//search();
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
function closeDtPlan() {
	$("#MaintGoal").window('close');
}

function initMaintGoal(title, url, WWidth, WHeight) {
	var url = appUrl + url;
	var $win = $("#MaintGoal").window({
		title : title,
		width : WWidth,
		height : WHeight,
		content : '<iframe frameborder="no" width="100%" height="100%" src='
				+ url + '/>',
		shadow : true,
		modal : true,
		closed : true,
		closable : false,
		minimizable : false,
		maximizable : false,
		collapsible : true,
		draggable : true
	});
	$win.window('open');
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
function createInstock() {
	//getWorkPlanDetailListByTotalId
	initMaintGoal('��ⵥ����', '/instockAction!searchStockPrepare.jspa', 820, 475);
}
function updateInstock(instock_total_id) {
	var instock_total_id = encodeURIComponent(instock_total_id);
	initMaintGoal('Ŀ�����޸�', '/instockAction!updateStockPrepare.jspa?instock_total_id=' + instock_total_id,
			760, 400)
}
//function updateOrgGoal(ctId) {
//	var ctId = encodeURIComponent(ctId);
//	initMaintGoal('��֯Ŀ�����޸�', '/goalAction!updateGoalPrepare.jspa?ctId=' + ctId+'&mark'+mark,
//			490, 400)
//}
function importMould() {
	
	//    $.ajax({
//					type : "post",
//					async : false,
//					url : appUrl + "/goalAction!selectNexUser.jspa?time="
//							+ new Date(),
//					data : {
//						userId : $("#userId").val()
//					},
//					success : function(obj) {
	//				nextUserHtml(obj);
					 html = '<form id="fileForm" method="post" enctype="multipart/form-data" >'
						+ '&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp' 
						+  '<table><tr>'
						+  '<td class="head" noWrap>��������</td>'
						+  '<td><input type="file" name="uploadFile" id="uploadFile"  style="width:200px"/>'
						+  '</tb></tr>'
						+ '<tr><td class="head" noWrap>��������</td>'
						+ '<td><select id="type" name="type" style="width:130px">'
						+'<option value="N">--��֯Ŀ��--</option>'
						+'<option value="Y" selected>--������Ŀ��--</option>'
                        +'</select>'
				        +'</td></tr><table></form>';
//		      		$.each(obj.result, function(i, v) {
//			  			html += '<option value="' + v.userId + '">' + v.userName + '----'+ v.stationName + '</option>';
//		     		});
//						processInstanceId = obj.processInstanceId
//					}
	//			});
				
					
	 openImportDialog('����Ŀ��', html);
}
var mask_;
/*�򿪵�����ĿExcel�Ի���*/
function openImportDialog(t, html){ 
	
	if ($('#excelDialog').length < 1) {
		$('<div/>', {
			id : 'excelDialog',
			title : 'ѡ���ϴ��ļ�',
			html :  "<div id='import'>" + "</br>"
					+ "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
					+ html + "</div>" + "</div>"					
		}).appendTo('body');
	} else {
//		$('#import').html(html);
	}
	$('#excelDialog').dialog({
		modal : true,
		resizable : false,
		dragable : false,
		closable : false,
		open : function() {
			$('#excelDialog').css('padding', '0.8em');
			$('#excelDialog .ui-accordion-content').css('padding', '0.4em')
					.height($('#excelDialog').height() - 100);
		},
		buttons : [{
					text : 'ȷ��',
					handler : function() {
						var file = document.getElementById('uploadFile').value;
						var type = document.getElementById('type').value;
						if (/^.+\.(csv|CSV)$/.test(file)) {
							$("#type").val($("#type").val());
							$.messager.progress();
							var form = document.getElementById('fileForm');			
							form.action = appUrl
									+ "/goal/goalAction!importGoalCsv.jspa" 
//											"eventId="+processInstanceId;
							form.target = "hideFrame";
							form.submit();  
						} else {
							$.messager.alert("��ʾ", "�뵼��csv�ļ�");
						}

					}
				}, {
					text : 'ȡ��',
					handler : function() {
						$('#excelDialog').dialog('close');
					}
				}],

		width : document.documentElement.clientWidth * 0.28,
		height : document.documentElement.clientHeight * 0.50
	});
}

/*���û�������ĿExcel���м��*/
function importCheck() {
	var file = document.getElementById('projectImportFile').value;
	if (/^.+\.(xls|xlsx)$/.test(file)) {
		document.getElementById('fileForm').submit();
		$('#import').dialog('close');
		mask_ = new mask({
					tip : '��ȴ��������...'
				});
		mask_.show();
		window.setTimeout(getImportStateInfo, 2000);
	} else {
		$.messager.alert("��ʾ", "�뵼��Excel�ļ�");
	}
}

function selectOrgTree() {
	initMaintGoal('ѡ����֯', '/goalAction!orgTreePage.jspa', 400, 460);
}
function returnValue(selectedId, selectedName) {
	document.getElementById('orgId').value = selectedId;
	document.getElementById('orgName').value = selectedName;
}

function closeOrgTree() {
	closeDtPlan();
}
//function approveGoal() {
//	$.messager.confirm('Confirm', '�Ƿ���������Ŀ��?', function(r) {
//				if (r) {
//					var rows = $('#datagrid').datagrid('getSelections');
//					if (rows == '') {
//						$.messager.alert('Tips', '  û���б�ѡ��!');
//						return;
//					}
////					for (var i = 0; i < rows.length; i++) {
////						if (rows[i].trFlag == 't') {
////							$.messager.alert('Tips', '  ѡ����������Ŀ���������ܲ���!');
////							return;
////						}
////					}
//					var ids = [];
//					for (var i = 0; i < rows.length; i++) {
//						if(rows[i].trFlag == 'S'){
//						ids.push(rows[i].ctId);
//						}
//					}
//					$("#ids").val(ids);
//					var form = window.document.forms[0];
//					form.action = "goalAction!approveGoal.jspa";
//					form.target = "hideFrame";
//					form.submit();
//				}
//			});
//}

function deleteGoal() {
	$.messager.confirm('Confirm', '�Ƿ�����ɾ��Ŀ��?', function(r) {
				if (r) {
					var rows = $('#datagrid').datagrid('getSelections');
					if (rows == '') {
						$.messager.alert('Tips', '  û���б�ѡ��!');
						return;
					}
					for (var i = 0; i < rows.length; i++) {
						if (rows[i].trFlag == 't') {
							$.messager.alert('Tips', '  ѡ����������Ŀ����������ɾ��!');
							return;
						}
					}
					var ids = [];
					for (var i = 0; i < rows.length; i++) {
						ids.push(rows[i].ctId);
					}
					$("#ids").val(ids);
					var form = window.document.forms[0];
					form.action = "goalAction!deleteGoal.jspa";
					form.target = "hideFrame";
					form.submit();
				}
			});
}

function rejectGoal(){
$.messager.confirm('Confirm', '�Ƿ���������Ŀ��?', function(r) {
				if (r) {
					var rows = $('#datagrid').datagrid('getSelections');
					if (rows == '') {
						$.messager.alert('Tips', '  û���б�ѡ��!');
						return;
					}
//					for (var i = 0; i < rows.length; i++) {
//						if (rows[i].trFlag == 'S') {
//							$.messager.alert('Tips', '�����ظ�����!');
//							return;
//						}
//					}
					var ids = [];
					for (var i = 0; i < rows.length; i++) {
						if(rows[i].trFlag == 't'){
						ids.push(rows[i].ctId);
						}
					}
					$("#ids").val(ids);
					var form = window.document.forms[0];
					form.action = "goalAction!rejectGoal.jspa";
					form.target = "hideFrame";
					form.submit();
				}
			});
}

function exportMould() {
	var form = window.document.forms[0];
	form.action = appUrl + '/goal/goalAction!exportMouldCsv.jspa';
	form.target = "hideFrame";
	form.submit();
}

function clear(){
			$('#month').val("");
			$('#mark').val("");
			$('#month').combobox('setValue','');
			$("#id").combobox('setValue',''); ;
			$("#wid").combobox('setValue','');	
			$("#mark").combobox('setValue','')
}
function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.beginDate = encodeURIComponent($("#beginDate").val());
	queryParams.endDate = encodeURIComponent($("#endDate").val());
	//queryParams.orgId = encodeURIComponent($("#orgId").val());
	queryParams.custId = encodeURIComponent($("#id").combobox("getValue"));
	$("#datagrid").datagrid('reload');
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}
function getWidth(percent) {
	if (jQuery.browser.safari) {
		return document.body.clientWidth * percent - 2;
	} else {
		return document.body.clientWidth * percent;
	}
}
function getHeight(percent) {
	return document.body.clientHeight * percent;
}
//function nextUserHtml(obj) {
//	nextUser = "<table border='0' cellpadding='0' cellspacing='1'>"
//			+ "<tr><td class='head' noWrap>�������б�</td>"
//			+ "<td><select id='nextUsers'>";
//	$.each(obj.result, function(i, v) {
//		nextUser += "<option value='" + v.userId + "'>" + v.userName + "----"
//				+ v.stationName + "</option>";
//	});
//	nextUser += "</select></td></tr></table>";
//}

function promgtMsg() {	
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	$.messager.progress('close');
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.confirm('Tips', successResult, function(r) {
			if (r) {
           $('#excelDialog').dialog('close')
           search();
			}

	});
}
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