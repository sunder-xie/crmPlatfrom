var processInstanceId;
$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});
function loadGrid() {
	var toolbar = $('#datagrid')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '��ѯ���',
						url : appUrl
								+ '/distributionGoalAction!getDistributionGoalList.jspa',
						loadMsg : '����Զ��������,��ȴ�...',
						singleSelect : false,
						pagination : true,
						nowrap : true,
						height : height,
						striped : true,
						columns : [ [
								{
									field : 'ck',
									checkbox : true
									
								},
								{
									field : 'dgId',
									title : 'ID',
									align : 'center',
									hidden : true
								},
								{
									field : 'orgState',
									title : '��֯״̬',
									align : 'center',
									hidden : true
								},
								{
									field : 'checkName',
									title : '�����',
									width : setColumnWidth(0.08),
									align : 'center'
								},
								{
									field : 'trFlag',
									title : '���״̬',
									width : setColumnWidth(0.05),
									align : 'center',
									sortable : false,
									htmlincode : false,
									formatter : function(value, row, rec) {
										var s = row.trFlag;
										if (s == "3") {
											return '<span style="color:green">'
													+ "����" + '</span>';
										} else if (s == "2") {
											return '<span style="color:red">'
													+ "����" + '</span>';
										} else if (s == "1") {
											return '<span style="color:red">'
													+ "δ��" + '</span>';
										} else if (s == "4") {
											return '<span style="color:red">'
													+ "����" + '</span>';
										}
									}
								},
								{
									field : 'inputName',
									title : '������',
									width : setColumnWidth(0.06),
									align : 'center'
								},
								{
									field : 'createDate',
									title : '��������',
									width : setColumnWidth(0.06),
									align : 'center'
								},
								{
									field : 'orgRegion',
									title : '����',
									width : setColumnWidth(0.06),
									align : 'center'
								},
								{
									field : 'orgProvince',
									title : 'ʡ��',
									width : setColumnWidth(0.06),
									align : 'center'
								},
								{
									field : 'orgCity',
									title : '����',
									width : setColumnWidth(0.06),
									align : 'center'
								},
								{
									field : 'kunnrId',
									title : '�����̱��',
									width : setColumnWidth(0.06),
									align : 'center',
									sortable : true
								},
								{
									field : 'kunnrName',
									title : '����������',
									width : setColumnWidth(0.18),
									align : 'center'

								},
								{
									field : 'inputDate',
									title : 'Ŀ��������',
									widtg : setColumnWidth(0.06),
									align : 'center'

								},
								{
									field : 'firstUser',
									title : '���о���',
									widtg : setColumnWidth(0.06),
									align : 'center'

								},
								{
									field : 'secondUser',
									title : 'ʡ������',
									widtg : setColumnWidth(0.06),
									align : 'center'

								},
								{
									field : 'thirdUser',
									title : '��������',
									widtg : setColumnWidth(0.06),
									align : 'center'

								},
								
								{
									field : 'aOne',
									title : 'A1Ҭ������-80g*30',
									width : setColumnWidth(0.11),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aThree',
									title : 'A3Ҭ������װ-80g*30',
									width : setColumnWidth(0.12),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'bOne',
									title : 'B1�춹����-64g*30',
									width : setColumnWidth(0.11),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'cOne',
									title : 'C1��Բ���浥��-65g*30',
									width : setColumnWidth(0.13),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aFour',
									title : 'A4Ҭ�����װ-80g*12*8',
									width : setColumnWidth(0.13),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aFive',
									title : 'A5Ҭ�����װ-80g*8*10',
									width : setColumnWidth(0.13),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aSix',
									title : 'A6Ҭ����ͥ����װ-80g*16*6',
									width : setColumnWidth(0.15),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'cSix',
									title : 'C6��Բ�����ͥ����װ-65g*16*6',
									width : setColumnWidth(0.18),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aSeven',
									title : 'A7Ҭ����ͥ����װ-80g*15*6',
									width : setColumnWidth(0.15),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'cSeven',
									title : 'C7��Բ�����ͥ����װ-65g*15*6',
									width : setColumnWidth(0.18),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aTwo',
									title : 'A2Ҭ������װ-80g*6*6',
									width : setColumnWidth(0.12),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'aEight',
									title : 'A8Ҭ������װ-80g*3*10',
									width : setColumnWidth(0.13),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'bEight',
									title : 'B8�춹����װ-80g*3*10',
									width : setColumnWidth(0.13),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'cEight',
									title : 'C8��Բ��������װ-65g*3*10',
									width : setColumnWidth(0.15),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'hSeven',
									title : 'H7�����̲赥ƿ-400ml*15',
									width : setColumnWidth(0.14),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'dNine',
									title : 'D9��ֵ���װ',
									width : setColumnWidth(0.09),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'dTen',
									title : 'D10�������װ-15*6',
									width : setColumnWidth(0.11),
									align : 'center',
									editor : 'numberbox'
								},
								{
									field : 'eOne',
									title : 'E1�������װ-12*6',
									width : setColumnWidth(0.15),
									align : 'center',
									editor : 'numberbox'
								}/*, {
									field : 'lastDate',
									title : '����޸�ʱ��',
									width : setColumnWidth(0.06),
									align : 'center'
								}*/ ] ],
						onLoadSuccess : function(data) {
							$(".datagrid-header-check")[0].disabled = true;
							selectedFile(data.rows);
						},
						toolbar : [  "-", {
							text : '���ݵ���',
							iconCls : 'icon-download',
							handler : function() {
								exportDistributionGoal();
							}
						}, "-",{
							text : '����Ŀ��������||¼��������������Ǳ��䣡����',
							handler : function() {
							}
						}, "-" ]
					});
	// ��ҳ�ؼ�
	var p = $('#datagrid').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 50, 100 ],
		beforePageText : '��',
		afterPageText : 'ҳ    �� {pages} ҳ',
		displayMsg : '��ǰ��ʾ {from} - {to} ����¼   �� {total} ����¼'
	});
}

function closeDtPlan() {
	$("#MaintDistributionGoal").window('close');
}

function initMaintDistributionGoal(title, url, WWidth, WHeight) {
	var url = appUrl + url;
	var $win = $("#MaintDistributionGoal")
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

function search() {
	$("#datagrid").datagrid('reload', {
		orgId : $('#orgId').val(),
		kunnrId : $('#kunnrId').val(),
		orgName : encodeURIComponent($('#orgName').val()),
		kunnrName : encodeURIComponent($("#kunnrName").val()),
		trFlag : $('#trFlag').combobox('getValue'),
		startDate : encodeURIComponent(document.getElementsByName("startDate")[0].value),
		endDate : encodeURIComponent(document.getElementsByName('endDate')[0].value)
	});
}

function selectOrgTree() {
	initMaintDistributionGoal('ѡ����֯',
			'/distributionGoalAction!orgTreePage.jspa', 400, 460);
}
function returnValue(selectedId, selectedName) {
	document.getElementById('orgId').value = selectedId;
	document.getElementById('orgName').value = selectedName;
}

function closeOrgTree() {
	closeDtPlan();
}

function exportDistributionGoal() {
	$.messager.progress();
	openTime();
	var form = window.document.forms[0];
	form.action = 'distributionGoalAction!searchDistributionGoaldownLoad.jspa';
	form.target = "hideFrame";
	form.submit();
}
function openTime() {
	setTimeout(function() {
		var timer = setInterval(function() {
			$.ajax({
				type : "post",
				url : appUrl + "/distributionGoalAction!downLoadOver.jspa?",
				success : function(data) {
					if (data == 'Yes') {
						clearInterval(timer);
						$.messager.progress('close');
					}
				}
			});
		}, 100);
	}, 500);
}

function clear() {
	$('#kunnrId').val("");
	$('#kunnrName').val("");
	$("#startDate").datebox('setValue', '');
	$("#endDate").datebox('setValue', '');
	$("#orgId").val("");
	$("#orgName").val("");
	$("#trFlag").combobox('setValue', '');
}

function selectedFile(rows) {
	for ( var j = 0; j < rows.length; j++) {
		if (rows[j]['trFlag'] == 3 ) {
			$(".datagrid-row[datagrid-row-index=" + j
					+ "] input[type='checkbox']")[0].disabled = true;
		}
	}
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
				$('#excelDialog').dialog('close');
				search();
			}

		});
	}
}

function change() {
	$('#orgId').val('');
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
	date = date + month[str[1]] + "-" + str[2];
	return date;
}
document.onkeydown = function(e) {// ����¼����û������κμ��̼�������ϵͳ��ť�����ͷ���͹��ܼ���ʱ����
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		search();
		return false;
	}
	return true;
};
