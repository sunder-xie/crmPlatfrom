/*var nextUser;
var processInstanceId;
var addressCount = 1;
var brandCount = 1;*/
$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	//�Ա�
	$('#contactSex').combobox({
		data:[{'id':'M','text':'��'},{'id':'F','text':'Ů'},{'id':'','text':''}],
		valueField : 'id',
		textField : 'text'
	});
	//�ŵ���Ҫ��
	$('#customerImportance').combobox({
		data:[{'id':'��','text':'��'},
		      {'id':'��','text':'��'},
		      {'id':'��','text':'��'}],
		valueField : 'id',
		textField : 'text'
	});
	// ����
	var type = $('#type').val();
	if (type == '') {
		type = 'Z';
	}
	$('#channelId').combobox({
		url : appUrl + '/kunnrAction!getChannel.jspa?channelName=' + type,
		valueField : 'channelId',
		textField : 'channelName'
	});
	// �ݷ�����
	$('#visitDays').combobox({
		multiple : true,
		url : appUrl + '/customerAction!getCmsTbDictList.jspa',
		valueField : 'itemValue',
		textField : 'itemName'
	});
	$('#visitDays').combobox('setValues', $("#days").val().split(", "));
	// ����ϵͳ
	$('#custSystem').combogrid(
			{
				panelHeight : 250,
				panelWidth : 380,
				pagination : true,
				method : 'post',
				singleSelect : true,
				url : appUrl
						+ '/crmdictAction!getDictJsonList.jspa?dictTypeValue='
						+ 'system@customer',
				idField : 'itemId',
				textField : 'itemName',
				columns : [ [ {
					field : 'itemId',
					title : 'ϵͳ���',
					width : 60
				}, {
					field : 'itemName',
					title : 'ϵͳ����',
					width : 200
				}, {
					field : 'itemValue',
					title : '����',
					width : 200
				} ] ],
				toolbar : '#toolbarSys'
			});
	$('#custSystem').combogrid('setValue', $("#custSystemId").val());
	$('#custSystem').combogrid('setText', $("#custSystemName").val());
	// �ϼ������̲�ѯ
	$('#custKunnr').combogrid({
		panelHeight : 250,
		panelWidth : 380,
		pagination : true,
		method : 'post',
		singleSelect : true,
		url : appUrl + '/kunnrAction!kunnrSearch.jspa?orgId='+$('#orgId').val()+'&bhxjFlag='+'C',
		idField : 'kunnr',
		textField : 'name1',
		mode:'loacl',
		// value:'name1',
		multiple : true,
		columns : [ [ {
			field : 'ck',
			checkbox : true
		}, {
			field : 'kunnr',
			title : '�����̱��',
			width : 120
		}, {
			field : 'name1',
			title : '����',
			width : 200
		} ] ],
		toolbar : '#toolbarKonzs',
	    onLoadSuccess:function(data){
	    }
	});
	//$('#custKunnr').combogrid('setValues',$('#custKunnrId').val().split(", "));
	$('#custKunnr').combogrid('setText',$('#custKunnrName').val());
	// ���׿ͻ�
	if (type == 'Z') {
		$('#custParent').combogrid({
			panelHeight : 250,
			panelWidth : 380,
			pagination : true,
			method : 'post',
			singleSelect : true,
			url : appUrl + '/customerAction!getTwoLevelCustomer.jspa?orgId='+$('#orgId').val(),
			idField : 'custId',
			textField : 'custName',
			multiple : true,
			columns : [ [ {
				field : 'ck',
				checkbox : true
			}, {
				field : 'custId',
				title : '�ͻ����',
				width : 120
			}, {
				field : 'custName',
				title : '�ͻ�����',
				width : 200
			} ] ],
			toolbar : '#toolbarKonzs1'
		});
		//$('#custParent').combogrid('setValues',$('#custParentId').val().split(", "));
		$('#custParent').combogrid('setText',$('#custParentName').val());
	}
	
	/**
	 * ����������
	 */
	// ��������
	function getRegion(level) {
		$('#city' + level).combobox(
				{
					editable : false,
					url : appUrl
							+ '/sales/salesAction!searchRegion.jspa?level='
							+ level + '&pid=' + $('#parent_city_id').val(),
					textField : 'text',
					valueField : 'id',
					onLoadSuccess : function() {
						$('#parent_city_id').val(
								$('#city' + level).combobox('getValue'));
						++level;
						getRegion(level);
					},
					onSelect : function(re) {
						$('#parent_city_id').val(re.id);
						getRegion(level);
					}

				});

	}
	;
	getRegion(1);
}
function searcherKonzs(name1) {
	var queryParams = $('#custKunnr').combogrid("grid").datagrid('options').queryParams;
	queryParams.name1 = encodeURIComponent(name1);
	$('#custKunnr').combogrid("grid").datagrid('reload');
}
function searcherKonzs1(name1) {
	var queryParams = $('#custParent').combogrid("grid").datagrid('options').queryParams;
	queryParams.search = encodeURIComponent(name1);
	$('#custParent').combogrid("grid").datagrid('reload');
}
function searcherSys(itemName) {
	var queryParams = $('#custSystem').combogrid("grid").datagrid('options').queryParams;
	queryParams.itemName = encodeURIComponent(itemName);
	$('#custSystem').combogrid("grid").datagrid('reload');
}
// �ύ����
function submit() {
	if(($('#custKunnr').combogrid('getValue')==''||$('#custKunnr').combogrid('getValue')==undefined)&&$('#custKunnrId').val()!=''){
	$('#custKunnr').combogrid('setValues',$('#custKunnrId').val().split(", "));}
	
	if($('#type').val()=='Z'){
	if(($('#custParent').combogrid('getValue')==''||$('#custParent').combogrid('getValue')==undefined)&&$('#custParentId').val()!=''){
	$('#custParent').combogrid('setValues',$('#custParentId').val().split(", "));}
	}
	var isValid = $('#ff').form('validate');
	// $('#custReceive').val() == 13485
	/*if ($('#type') == 'Z') {
		var selectIndex = document.getElementById("custReceive").selectedIndex;// ����ǵڼ�����ѡ����
		var selectText = document.getElementById("custReceive").options[selectIndex].text;
		if (selectText == '�ܲ�����'
				&& ($('#custKunnr').combogrid('getValue') != null
						&& $('#custKunnr').combogrid('getValue') != '' && undefined != $(
						'#custKunnr').combogrid('getValue'))) {
			$.messager.alert('Tips', '�ܲ����Ϳͻ�����Ҫѡ������/�����̣�ϵͳ�����������/��������Ϣ��',
					'warning');
			$('#custKunnr').combogrid('setValue', '');
			return;

		} else if (selectText != '�ܲ�����'
				&& ($('#custKunnr').combogrid('getValue') == null || $(
						'#custKunnr').combogrid('getValue') == ''
						&& undefined == $('#custKunnr').combogrid('getValue'))) {
			$.messager.alert('Tips', '��ѡ��������/�����̣�', 'warning');
			return;
		}
	}*/
	/*
	 * �����˿�������˾ҵ��Ҳ�����Ǿ����̸�����
	 */
	if ($('#stationUserId').val() == '' && $('#kunnrUser').val() == '') {
		$.messager.alert('Tips', '��˾ҵ�������˺;�����ҵ��������������������дһ����', 'warning');
		return;
	}
	/*
	 * ��ϵ�˵绰�ֻ�������һ��
	 */
	if ($('#contactMobile').val() == '' && $('#contadctPhone').val() == '') {
		$.messager.alert('Tips', '��ϵ�绰���ֻ���������������дһ����', 'warning');
		return;
	}
	if($('#customerImportance').combobox('getValue')==''){
		$.messager.alert('Tips', '��ѡ���ŵ���Ҫ�ȣ�', 'warning');
		return;
	}
	if (isValid) {
		$.messager.confirm('Confirm', '��˶��ն��ŵ���Ϣ,ȷ���ύ?', function(r) {
			if (r) {
				var form = window.document.forms[0];
				form.action = appUrl + '/customerAction!updateCustomer.jspa';
				form.target = "hideFrame";
				form.submit();
			}
		});
	}
}

// ȡ��
function cancel() {
	window.parent.closeWindow();

}
// �б�htmlƴ��
function nextUserHtml(obj) {
	nextUser = "<table border='0' cellpadding='0' cellspacing='1'>"
			+ "<tr><td class='head' noWrap>�������б�</td>"
			+ "<td><select id='nextUsers'>";
	$.each(obj.result, function(i, v) {
		nextUser += "<option value='" + v.userId + "'>" + v.userName + "----"
				+ v.stationName + "</option>";
	});
	nextUser += "</select></td></tr></table>";
}
// �������б�ť����
function handelAction(action) {
	// array2json();
	var form = window.document.forms[0];
	form.action = appUrl + "/customerAction!" + action + ".jspa?eventId="
			+ processInstanceId;
	form.target = "hideFrame";
	form.submit();
}

function initMaintWindow4Post(title, url, width, height) {
	var url = appUrl + url;
	var WWidth = width;
	var WHeight = height;
	var $win = $("#maintWindow")
			.window(
					{
						title : title,
						width : WWidth,
						height : WHeight,
						content : '<iframe frameborder="no" width="100%" height="100%" src='
								+ url + '/>',
						shadow : true,
						modal : true,
						closed : true,// /
						closable : true,//
						left : $(window).width() * 0.2,
						minimizable : false,//
						maximizable : false,//
						collapsible : false,// 
						draggable : true
					//
					});

	$win.window('open');
}
function selectOrgTree4Station() {
	initMaintWindow4Post('ѡ��ҵ�����',
			'/batChangeAction!selectOrgTree4Station.jspa', 550, 350);
}

function closeOrgTree() {
	$("#maintWindow").window('close');
}
/**
 * ����֯��
 */
function selectOrgTree() {
	initMaintWindow4Post('ѡ����֯', '/customerAction!orgTreePage.jspa', 400, 460);
}
/**
 * ��֯��ѡ��֯���ص������
 * 
 * @param selectedId
 * @param selectedName
 */
function returnValue(selectedId, selectedName) {
	document.getElementById('orgId').value = selectedId;
	document.getElementById('orgName').value = selectedName;
	$('#custKunnr').combogrid(
			{
				url : appUrl + '/kunnrAction!kunnrSearch.jspa?orgId='
						+ $('#orgId').val() + '&bhxjFlag=' + 'C'
			});
	var type = $('#type').val();
	if (type == 'Z') {
		$('#custParent').combogrid(
				{
					url : appUrl
							+ '/customerAction!getTwoLevelCustomer.jspa?orgId='
							+ $('#orgId').val()
				});
	}
}
/**
 * ��֯��ѡ����֮��ر�
 */
function closeMaintEvent() {
	$("#maintWindow").window('close');
}

// ������Ϣ
function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info');
		cancel();
		window.parent.search();
	}
}
