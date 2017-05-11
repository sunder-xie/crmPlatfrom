$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);
	// ����ͨ�г���
	$('#maximum')
			.combobox(
					{
						valueField : 'itemName',
						textField : 'itemValue',
						url : appUrl
								+ '/kunnrAction!getCrmDictList.jspa?dictTypeValue='
								+ 'carType'
					});
});

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	$.messager.progress('close');
	if (failResult) {
		$.messager.alert('��ʾ', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('��ʾ', successResult, 'info', function() {
			close();
			window.parent.search();
		});
	}
}

function close() {
	window.parent.closeMaintKunnrAddress();
}

function submit() {
	$.messager.confirm('Confirm', '�Ƿ�ȷ���ύ?', function(r) {
		if (r) {
			var isValid = $('#form1').form('validate');
			if (isValid) {
				$.messager.progress();
				var form = window.document.forms[0];
				form.action = appUrl
						+ "/kunnrAddressAction!updateKunnrAddress.jspa";
				form.target = "hideFrame";
				form.submit();
			} else {
				$.messager.alert('Tips', '�밴��ʾ��ȷ��д����Ϣ!', 'warning');
				return;
			}
		}
	});
}
/*
 * ѡ���ַ
 */
function selectAddress() {
	initMaintWindow('ѡ���ַ', '/kunnrAction!searchAddress.jspa', 600,
				400);
}
/*
 * ��ַѡ�񷵻�ֵzwl01tʡ��zwl02t�У�zwl03t��zwl04t��
 */

function returnAddress(zwl01,zwl02,zwl03,zwl04,zwl01t, zwl02t, zwl03t, zwl04t) {
		document.getElementById('sdProvince').value=zwl01;
		document.getElementById('sdCity').value=zwl02;
		document.getElementById('sdArea').value=zwl03;
		document.getElementById('sdTown').value=zwl04;
		document.getElementById('street').value = zwl01t + zwl02t
				+ zwl03t + zwl04t;
}
/**
 * ѡ����֮��رնԻ���
 */
function closeOrgTree() {
	$("#maintWindow").window('close');
}
function initMaintWindow(title, url, width, height) {
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
						minimizable : false,
						maximizable : true,
						collapsible : true,
						draggable : true
						
					});

	$win.window('open');
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