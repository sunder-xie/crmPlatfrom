$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);
});
// 创建窗口对象
function initMaintWindow(title, url,width,height) {
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
						minimizable : false,
						maximizable : true,
						collapsible : true,
						draggable : true
					//
					});

	$win.window('open');
}

function selectChannelListTree(){
	initMaintWindow('选择渠道','/channelTreeAction!channelTreePage.jspa',450,320);
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('提示', failResult, 'warning');
	} else if (successResult) {
		$.messager.confirm('提示', successResult, function (data) {
	if (data){
		window.parent.closeOrgTree();
		window.parent.search();
	}else {
		return;
	}
	});
}
}

function returnValue(selectedId, selectedName) {
	document.getElementById('channelParentId').value = selectedId;
	document.getElementById('parentChannelName').value = selectedName;
}
function closeOrgTree() {
	$("#maintWindow").window('close');
}
function close() {
	window.parent.closeMaintWindow();
}
function submit() {
	var isValid = $('#form1').form('validate');
	if (isValid) {
	var form = window.document.forms[0];
	form.action = appUrl + "/channelTreeAction!creatChannel.jspa";
	form.target = "hideFrame";
	form.submit();}
 	else {
		$.messager.alert('Tips', '请仔细检查是否还有未填或者格式不正确的项目!', 'warning');
	}
}