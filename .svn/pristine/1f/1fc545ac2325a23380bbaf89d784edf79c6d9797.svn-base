$(document).ready(function() {
	loadGrid();
	loadGrid1();
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
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	$('#id').combogrid(
			{
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
				url : appUrl + '/goal/goalAction!getKunner.jspa?orgId='
						+ $('#orgId').val(),
				columns : [ [ {
					field : 'ck',
					checkbox : true,
					hidden : true
				}, {
					field : 'id',
					title : '客户ID',
					hidden : true,
					width : 60
				}, {
					field : 'kunnr',
					title : '客户编号',
					align : 'center',
					width : 80
				}, {
					field : 'name1',
					title : '客户名称',
					align : 'center',
					width : 100
				}, {
					field : 'mobNumber',
					title : '手机',
					align : 'center',
					width : 80
				},{
					field : 'orgId',
					title : '组织ID',
					align : 'center',
					width : 80,
					hidden:true
				},{
					field : 'orgName',
					title : '组织',
					align : 'center',
					width : 150
				} ] ],
				toolbar : '#toolbar',
				onSelect : function(index,recode) {
					$('#orgId').val(recode.orgId);
				}
			});
	$('#id').combogrid('setValue',$('#custNumber').val());
	$('#id').combogrid('setText',$('#custNameZh').val());
	// var q = $('#id').combogrid();
}

function initOpenGoal(title, url, WWidth, WHeight) {
	var url = appUrl + url;
	var $win = $("#OpenGoal")
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
function loadGrid1() {
	$('#wid').combogrid({
		panelWidth : 400,
		panelHight : 600,
		idField : 'mvgr1',
		textField : 'bezei',
		pagination : true,// 是否分页
		// rownumbers : true,// 序号
		collapsible : false,// 是否可折叠的
		// fit : true,// 自动大小
		method : 'post',
		// multiple : true,
		// '/goal/goalAction!getMatList.jspa'
		url : appUrl + '/goal/goalAction!getMaterielViewList.jspa?createDate='+$('#createDate').val(),
		columns : [ [ {
			field : 'ck',
			checkbox : true,
			hidden : true
		}, /*
			 * { field : 'matnr', title : '物料号', width : 120 },
			 */
		{
			field : 'mvgr1',
			title : '品项or四级科目（SKU）ID',
			align : 'center',
			width : 120
		}, {
			field : 'bezei',
			title : '品项or四级科目(SKU)',
			align : 'center',
			width : 200
		},{
			field : 'kbetr',
			title : '价格(元)',
			align : 'center',
			width : 80
		} ] ],
		toolbar : '#toolbar1',
		onSelect : function(index,recode) {
			var num=recode.kbetr*1*$('#box').val();
			$('#targetNum').numberbox('setValue',num);
			$('#kbetr').val(recode.kbetr*1);
		}
	});
	$('#wid').combogrid('setValue',$('#matterId').val());
	$('#wid').combogrid('setText',$('#matterName').val());
}
function change(){
	if($('#wid').combogrid('getValue')){
		var num=$('#kbetr').val()*1*$('#box').val();
		$('#targetNum').numberbox('setValue',num);
	}
}
function close() {
	window.parent.closeDtPlan();
}
function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info', function() {
			close();
			window.parent.search();
		});
	}
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
		url : appUrl + '/goal/goalAction!getMaterielViewList.jspa?value=' + val
	});
	$('#wid').combogrid("grid").datagrid('reload');

}

function selectOrgTree() {
	initOpenGoal('选择组织', '/goalAction!orgTreePage.jspa', 400, 350);
}
function returnValue(selectedId, selectedName) {
	document.getElementById('orgId').value = selectedId;
	document.getElementById('orgName').value = selectedName;
	if ('Y' == $('#mark').val()) {
		loadGrid();
	}
}

function closeOrgTree() {
	$("#OpenGoal").window('close');
}
function submit() {
	var si = $("#theYear").combobox('isValid');
	var pa = $("#theYear").combobox('isValid');
	var bt = $("#targetNum").numberbox('getValue');
	var ft = $("#box").numberbox('getValue');
	var ps = $("#id").combobox('isValid');
	var pq = $("#wid").combobox('isValid');
	var pp = $("#orgId").val();
	if (!(si && pa && bt && ft && ps && pq && pp)) {
		return;
	}
	$.messager.confirm('Confirm', '是否确认提交?', function(r) {
		if (r) {
			var form = window.document.forms[0];
			form.action = appUrl + "/goal/goalAction!updateGoal.jspa";
			form.target = "hideFrame";
			form.submit();
		}
	});
}

function submit1() {
	var si = $("#theYear").combobox('isValid');
	var pa = $("#theYear").combobox('isValid');
	var bt = $("#targetNum").numberbox('getValue');
	var ft = $("#box").numberbox('getValue');
	var ps = $("#orgId").val();
	var pq = $("#wid").combobox('isValid');
	if (!(si && pa && bt && ft && ps && pq)) {
		return;
	}
	$.messager.confirm('Confirm', '是否确认提交?', function(r) {
		if (r) {
			var form = window.document.forms[0];
			form.action = appUrl + "/goal/goalAction!updateGoal.jspa";
			form.target = "hideFrame";
			form.submit();
		}
	});
}
function closeDtPlan() {
	$("#WorkPlan").window('close');
}
document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		submit();
		return false;
	}
	return true;
}