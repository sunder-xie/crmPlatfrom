$(document).ready(function() {
	init();
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function init(){
	var nowDate=new Date();
	var nowYear=nowDate.getFullYear();
	var nowMonth=nowDate.getMonth()+1;
	if(nowMonth<10){
		nowMonth='0'+nowMonth;
	}
	$("#startDate").val(nowYear+'-'+nowMonth);
	$("#endDate").val(nowYear+'-'+nowMonth);
}

function loadGrid() {
	$('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '��ѯ���',
						url : appUrl
								+ '/goal/goalAction!searchGoalFXChangeListJson.jspa',
						loadMsg : '����Զ��������,��ȴ�...',
						singleSelect : false,
						rownumbers:true,
						pagination : true,
						nowrap : true,
						striped : true,
						height : height,
						queryParams : {
							startDate : $('#startDate').val(),
							endDate : $('#endDate').val(),
							eventState : $('#eventState').combobox('getValue')
						},
						columns : [ [
						        {
									field : 'changeId',
									hidden : true
								}, {
									field : 'eventId',
									title : '������',
									width : 80,
									align : 'center'
								}, {
									field : 'userName',
									title : '�ᱨ��',
									width : 80,
									align : 'center'

								}, {
									field : 'title',
									title : '�������',
									width : 200,
									align : 'center'

								}, {
									field : 'createDate',
									title : '����ʱ��',
									width : 80,
									align : 'center'

								}, {
									field : 'eventState',
									title : '����״̬',
									width : 80,
									align : 'center',
									formatter : function(v) {
										if (v=="T") {
											return "������";
										}else if (v=="D") {
											return "�����";
										}else if (v=="S") {
											return "��ȡ��";
										}else if (v=="N"){
											return "δ�ύ";
										}
									}
								}, {
									field : 'curUserName',
									title : '��ǰ������',
									width : 80,
									align : 'center'
								}, {
									field : 'oper',
									title : '����',
									width : '80',
									align : 'center',
									formatter : function(value,row,index){
										var id=row.changeId;
										var linkA = '<img style="cursor:pointer" onclick="look('
											+ id
											+ ')" title="�鿴����" src='
											+ imgUrl
											+ '/images/actions/action_view.png align="absMiddle"></img>';
										return linkA;
									}
								} ] ],
								toolbar : [ "-", {
									text : '����',
									iconCls : 'icon-add',
									handler : function() {
										add();
									}
								}, "-", {
									text : 'Excel����',
									iconCls : 'icon-excel',
									handler : function() {
										excel();
									}
								}, "-" ]
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

function search() {
	var queryParams = $('#datagrid').datagrid('options').queryParams;
	queryParams.userName = encodeURIComponent($('#userName').val());
	queryParams.eventId = $("#eventId").val();
	queryParams.startDate = $('#startDate').val();
	queryParams.endDate = $('#endDate').val();
	queryParams.eventTitle = $('#eventTitle').val();
	queryParams.eventState = $('#eventState').combobox('getValue');
	$("#datagrid").datagrid('load');
}

function openTime() {
	var timer = setInterval(function() {
		$.ajax({
			type : "post",
			url : appUrl + "/reimburse/reimburseAction!checkDownLoadOver.jspa",
			success : function(data) {
				if (data == 'Yes') {
					clearInterval(timer);
					$.messager.progress('close');
				}
			}
		});
	}, 500);
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	$.messager.progress('close');
	if (failResult) {
		$.messager.alert('Tips', failResult, 'error');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info');
	}
}

function toEvent(eventId) {
	initMaintWindow('������Ϣ',
			basisUrl + '/wfe/eventAction!toProcessEvent.jspa?event_id='
					+ eventId, 'maintWindow', '1000', '450', true);
}

function look(id){
	initMaintWindow('������Ϣ',
			appUrl + '/goal/goalAction!toGoalDistributionChangeView.jspa?changeId='
					+ id, 'maintWindow', '1000', '450', true);
}

function add(){
	initMaintWindow('������Ϣ',
			appUrl + '/goal/goalAction!toDistributionGoalChange.jspa', 
			         'maintWindow', '1000', '450', true);
}

//excel����
function excel() {
	var form = window.document.forms[0];
	form.action = appUrl + '/goal/goalAction!exportForExcelGoalFXChange.jspa';
	form.target = "hideFrame";
	form.submit();
}

function closeMaintWindow() {
	$("#maintWindow").window('close');
}

function clearValue(){
	$("#eventId").val("");
	$("#eventState").combobox("select","");
	$("#userName").val("");
	init();
}

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

function utcToDate(utcCurrTime, type) {
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