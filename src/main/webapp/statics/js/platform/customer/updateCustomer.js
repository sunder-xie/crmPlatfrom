/*var nextUser;
var processInstanceId;
var addressCount = 1;
var brandCount = 1;*/
$(document).ready(function() {
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	//性别
	$('#contactSex').combobox({
		data:[{'id':'M','text':'男'},{'id':'F','text':'女'},{'id':'','text':''}],
		valueField : 'id',
		textField : 'text'
	});
	//门店重要度
	$('#customerImportance').combobox({
		data:[{'id':'甲','text':'甲'},
		      {'id':'乙','text':'乙'},
		      {'id':'丙','text':'丙'}],
		valueField : 'id',
		textField : 'text'
	});
	// 渠道
	var type = $('#type').val();
	if (type == '') {
		type = 'Z';
	}
	$('#channelId').combobox({
		url : appUrl + '/kunnrAction!getChannel.jspa?channelName=' + type,
		valueField : 'channelId',
		textField : 'channelName'
	});
	// 拜访日期
	$('#visitDays').combobox({
		multiple : true,
		url : appUrl + '/customerAction!getCmsTbDictList.jspa',
		valueField : 'itemValue',
		textField : 'itemName'
	});
	$('#visitDays').combobox('setValues', $("#days").val().split(", "));
	// 所属系统
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
					title : '系统编号',
					width : 60
				}, {
					field : 'itemName',
					title : '系统名称',
					width : 200
				}, {
					field : 'itemValue',
					title : '描述',
					width : 200
				} ] ],
				toolbar : '#toolbarSys'
			});
	$('#custSystem').combogrid('setValue', $("#custSystemId").val());
	$('#custSystem').combogrid('setText', $("#custSystemName").val());
	// 上级经销商查询
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
			title : '经销商编号',
			width : 120
		}, {
			field : 'name1',
			title : '名称',
			width : 200
		} ] ],
		toolbar : '#toolbarKonzs',
	    onLoadSuccess:function(data){
	    }
	});
	//$('#custKunnr').combogrid('setValues',$('#custKunnrId').val().split(", "));
	$('#custKunnr').combogrid('setText',$('#custKunnrName').val());
	// 二阶客户
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
				title : '客户编号',
				width : 120
			}, {
				field : 'custName',
				title : '客户名称',
				width : 200
			} ] ],
			toolbar : '#toolbarKonzs1'
		});
		//$('#custParent').combogrid('setValues',$('#custParentId').val().split(", "));
		$('#custParent').combogrid('setText',$('#custParentName').val());
	}
	
	/**
	 * 行政区域级联
	 */
	// 行政区划
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
// 提交申请
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
		var selectIndex = document.getElementById("custReceive").selectedIndex;// 获得是第几个被选中了
		var selectText = document.getElementById("custReceive").options[selectIndex].text;
		if (selectText == '总仓配送'
				&& ($('#custKunnr').combogrid('getValue') != null
						&& $('#custKunnr').combogrid('getValue') != '' && undefined != $(
						'#custKunnr').combogrid('getValue'))) {
			$.messager.alert('Tips', '总仓配送客户不需要选所属经/分销商，系统将清空所属经/分销商信息！',
					'warning');
			$('#custKunnr').combogrid('setValue', '');
			return;

		} else if (selectText != '总仓配送'
				&& ($('#custKunnr').combogrid('getValue') == null || $(
						'#custKunnr').combogrid('getValue') == ''
						&& undefined == $('#custKunnr').combogrid('getValue'))) {
			$.messager.alert('Tips', '请选择所属经/分销商！', 'warning');
			return;
		}
	}*/
	/*
	 * 负责人可能是我司业代也可能是经销商负责人
	 */
	if ($('#stationUserId').val() == '' && $('#kunnrUser').val() == '') {
		$.messager.alert('Tips', '我司业代负责人和经销商业代负责人请至少任意填写一个！', 'warning');
		return;
	}
	/*
	 * 联系人电话手机至少填一个
	 */
	if ($('#contactMobile').val() == '' && $('#contadctPhone').val() == '') {
		$.messager.alert('Tips', '联系电话和手机号请至少任意填写一个！', 'warning');
		return;
	}
	if($('#customerImportance').combobox('getValue')==''){
		$.messager.alert('Tips', '请选择门店重要度！', 'warning');
		return;
	}
	if (isValid) {
		$.messager.confirm('Confirm', '请核对终端门店信息,确认提交?', function(r) {
			if (r) {
				var form = window.document.forms[0];
				form.action = appUrl + '/customerAction!updateCustomer.jspa';
				form.target = "hideFrame";
				form.submit();
			}
		});
	}
}

// 取消
function cancel() {
	window.parent.closeWindow();

}
// 列表html拼接
function nextUserHtml(obj) {
	nextUser = "<table border='0' cellpadding='0' cellspacing='1'>"
			+ "<tr><td class='head' noWrap>处理人列表</td>"
			+ "<td><select id='nextUsers'>";
	$.each(obj.result, function(i, v) {
		nextUser += "<option value='" + v.userId + "'>" + v.userName + "----"
				+ v.stationName + "</option>";
	});
	nextUser += "</select></td></tr></table>";
}
// 处理人列表按钮操作
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
	initMaintWindow4Post('选择业务代表',
			'/batChangeAction!selectOrgTree4Station.jspa', 550, 350);
}

function closeOrgTree() {
	$("#maintWindow").window('close');
}
/**
 * 打开组织树
 */
function selectOrgTree() {
	initMaintWindow4Post('选择组织', '/customerAction!orgTreePage.jspa', 400, 460);
}
/**
 * 组织树选组织返回到输入框
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
 * 组织树选择完之后关闭
 */
function closeMaintEvent() {
	$("#maintWindow").window('close');
}

// 返回信息
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
