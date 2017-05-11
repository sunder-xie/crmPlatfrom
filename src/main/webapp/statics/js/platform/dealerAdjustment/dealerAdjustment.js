//��ȡ��ǰʱ�䲢��ֵ����Ӧ���ֶ�
var date = new Date();
var Month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0" + (date.getMonth() + 1);
//jQuery��������������
$(document).ready(function() {
	$('#startDate').val(date.getFullYear()+'��'+Month+'��');
	$('#endDate').val(date.getFullYear()+'��'+Month+'��');
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
	$('#eventType').combobox({
		valueField : 'id',
		textField : 'text',
		data : [ {
			id : 'A',
			text : 'Э��Ŀ�����ᱨ',
			value : 'A'	
		}, {
			id : 'B',
			text : 'Э��Ŀ��������',
			value : 'B'
		}],
		multiple : false,
		editable : false,
		panelHeight : 'auto'
	});
});
/**
 * ����������
 */
function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '��ѯ���',
						url : appUrl
								+ '/kunnrBusinessContact/kunnrBusinessAction!getDealerAdjustmentJsonList.jspa',
						queryParams : {
							startDate : encodeURIComponent($("#startDate").val()),
							endDate : encodeURIComponent($("#endDate").val()),
							eventStatus : $('#eventStatus').combobox('getValue')
						},
						loadMsg : '����Զ��������,��ȴ�...',
						singleSelect : true,
						pagination : true,
						nowrap : true,
						striped : true,
						height : height * 0.95,
						columns : [ [
								{
									field : 'ck',
									checkbox : true
								},
								{
									field : 'id',
									title : 'ID',
									align : 'center',
									hidden : true
								},
								{
									field : 'eventId',
									title : '������',
									width : setColumnWidth(0.06),
									align : 'center',
									formatter : function(value, row,index) {
										var id = row.eventId;
										if (id != undefined) {
											var linkA = '<a href="#" class="easyui-linkbutton" data-options="plain:true" onclick="toEvent('
												+id+')">'
												+id+'</a>';
											return linkA;
											
										}
									}
								},
								{
									field : 'eventType',
									title : '��������',
									width : setColumnWidth(0.1),
									align : 'center',
									formatter : function(value,row,index) {
										if (value == 'A') {
											return 'Э��Ŀ�����ᱨ';
										} else if (value == 'B') {
											return 'Э��Ŀ��������';
										} else {
											return '';
										}
									}
								},
								{
									field : 'eventTitle',
									title : '�������',
									width : setColumnWidth(0.25),
									align : 'center'

								},
								{
									field : 'applyYear',
									title : '������',
									width : setColumnWidth(0.1),
									align : 'center'
								},
								{
									field : 'applyMonth',
									title : '������',
									width : setColumnWidth(0.1),
									align : 'center'
								},
								{
									field : 'applyUser',
									title : '������',
									width : setColumnWidth(0.06),
									align : 'center'
								},
								{
									field : 'eventStatus',
									title : '����״̬',
									width : setColumnWidth(0.06),
									align : 'center',
									formatter : function(value,row,index) {
										if (value == 'D') {
											return '�����';
										} else if (value == 'Y') {
											return '������';
										} else if (value == 'N') {
											return 'δ���'
										} else if (value == 'S'){
											return 'ȡ��';
										}
									}
								},
								{
									field : 'operation',
									title : '����',
									width : setColumnWidth(0.06),
									align : 'center',
									formatter : function(value,row,index){
										var id=row.id;
										if(row.eventStatus=='N'){
											returnStr =
												'<img style="cursor:pointer"  title="�ύδ�������" onclick="dealerAdjustmentSub('
												+id+')" src='
													+ imgUrl
													+ '/images/actions/icon_ok.gif align="absMiddle"></img>';
										}else if(row.eventStatus=='Y'){
											returnStr =
												'<img style="cursor:pointer"  title="�鿴δ�������" onclick="dealerAdjustmentView('
												+id+')" src='
													+ imgUrl
													+ '/images/actions/action_view.png align="absMiddle"></img>';
										}else if(row.eventStatus=='D'){
											returnStr =
												'<img style="cursor:pointer"  title="�鿴���������" onclick="dealerAdjustmentView('
												+id+')" src='
													+ imgUrl
													+ '/images/actions/action_view.png align="absMiddle"></img>';
										}else{
											
											returnStr='';
										}
										return returnStr;
									}
								}] ],
						toolbar : ["-", {
							text : '��������',
							iconCls : 'icon-add',
							handler : function() {
								addNewDealerAdjustment();
							}
						}, "-", {
							text : '��������',
							iconCls : 'icon-excel',
							handler : function() {
								exportData();
							}
						} ],
						onBeforeEdit : function(index, row) {
							row.editing = true;
							updateActions(index);
						},
						onAfterEdit : function(index, row) {
							row.editing = false;
							updateActions(index);
						},
						onCancelEdit : function(index, row) {
							row.editing = false;
							updateActions(index);
						},
						onLoadSuccess : function(data) {
							$(".datagrid-header-check")[0].disabled = true;
							$(".datagrid-header-check input").unbind('click');
							selectedFile(data.rows);
						},
						onClickRow : function(rowIndex, rowData) {
							$('#datagrid').datagrid('unselectRow', rowIndex);
						}
					});

	// ��ҳ�ؼ�
	var p = $('#datagrid').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '��',
		afterPageText : 'ҳ    �� {pages} ҳ',
		displayMsg : '��ǰ��ʾ {from} - {to} ����¼   �� {total} ����¼'
	});
}
/**
 * http://exp.zjxpp.com:8186/basisPlatform/wfe/eventAction!toProcessEvent.jspa?event_id=
 * http://exptest.zjxpp.com:7186/...
 * ע�⣬�ύ����ʽ��ʱ����������޸Ĺ���
 */
function toEvent(id) {
	initMaintWindow('������Ϣ',
			'http://exptest.zjxpp.com:7186/basisPlatform/wfe/eventAction!toProcessEvent.jspa?event_id='
			+ id, 'maintWindow', '700', '400', true);
}
function setColumnWidth(percent) {
	return $(this).width() * percent;
}
function selectedFile(rows) {
	for ( var j = 0; j < rows.length; j++) {
		if ((rows[j]['documentNumber'] != undefined && rows[j]['documentNumber'] != '')) {
			$(".datagrid-row[datagrid-row-index=" + j
					+ "] input[type='checkbox']")[0].disabled = true;
		}
	}
}
/**
 * ����������ѯ
 * queryParams.startDate = encodeURIComponent($("#startDate").val());
 * queryParams.endDate = encodeURIComponent($("#endDate").val());
 */
function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.eventId = $("#eventId").val();
	queryParams.applyUser = encodeURIComponent($("#applyUser").val());
	/*queryParams.applyYear = $("#applyYear").val();
	queryParams.applyMonth = $("#applyMonth").val();*/
	queryParams.startDate = encodeURIComponent($("#startDate").val());
	queryParams.endDate = encodeURIComponent($("#endDate").val());
	queryParams.eventStatus = $('#eventStatus').combobox('getValue');
	queryParams.eventType = $('#eventType').combobox('getValue');
	$("#datagrid").datagrid('load');
}
/**
 * ��������
 */
function addNewDealerAdjustment(){
	initMaintWindow('Э��Ŀ������������', appUrl
			+ '/kunnrBusinessContact/kunnrBusinessAction!toAddDealerAdjustment.jspa?orgId='+$('#orgId').val(),
			'maintWindow', '1000', '400', true);
}
/**
 * �ύδ��ɵ���
 * @param id
 */
function dealerAdjustmentSub(id){
	initMaintWindow('Э��Ŀ������������', appUrl
			+ '/kunnrBusinessContact/kunnrBusinessAction!toDealerAdjustmentSub.jspa?id='+id,
			'maintWindow', '1000', '400', true);
}
/**
 * �鿴����ɻ����������еĵ���
 * @param id
 */
function dealerAdjustmentView(id){
	initMaintWindow('Э��Ŀ������������', appUrl
			+ '/kunnrBusinessContact/kunnrBusinessAction!toDealerAdjustmentView.jspa?id='+id,
			'maintWindow', '1000', '400', true);
}
/**
 * ��������
 */
function exportData(){
	$.messager.confirm('Confirm','�Ƿ񵼳�����?',function(r) {
		if (r) {
			var form = window.document.forms[0];
			form.action = appUrl
					+ '/kunnrBusinessContact/kunnrBusinessAction!exportDealerAdjustData.jspa';
			form.target = "hideFrame";
			form.submit();
		}
	});
}

/**
 * ��ʼ���µĴ���
 * @param title ����
 * @param url ����
 * @param id id
 * @param width ���
 * @param height �߶�
 * @param flag ����
 */
function initMaintWindow(title, url, id, width, height, flag) {
	var WWidth = width;
	var WHeight = height;
	var $win = $("#" + id)
			.window(
					{
						title : title,
						width : WWidth,
						height : WHeight,
						content : '<iframe frameborder="no" width="100%" height="100%" src='
								+ url + '/>',
						shadow : true,
						modal : true,
						draggable : true,
						closed : true,
						closable : true,
						minimizable : false,
						maximizable : true,
						collapsible : true,
						fit : flag,
						draggable : true
					});

	$win.window('open');
}
/**
 * ��ʾ��Ϣ����
 */
function promgtMsg() {
	$.messager.progress('close');
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning', function() {
			search();
		});
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info', function() {
			search();
		});
	}
}
function windowClose(){
	$("#maintWindow").window('close');
}
/**
 * ʱ���ʽ������
 * @param utcCurrTime
 * @returns {String}
 */
function utcToDate(utcCurrTime) {
	if (utcCurrTime.indexOf(" ") < 0) {
		return utcCurrTime;
	} else {
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
		date = date + month[str[1]] + "-" + str[2];
		return date;
	}
}