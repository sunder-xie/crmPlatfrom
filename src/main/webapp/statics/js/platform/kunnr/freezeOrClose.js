var nextUser;
var processInstanceId;
$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);

});
// �ύ
function submit() {
	if ($('#noticeFile1').val() != '' || $('#noticeFile2').val() != ''
			|| $('#noticeFile3').val() != ''|| $('#noticeFile4').val() != '') {
		if(!($('#noticeFile1').val() != '' && $('#noticeFile2').val() != ''
			&& $('#noticeFile3').val() != ''&& $('#noticeFile4').val() != '')){
		$.messager.alert('Tips', "���ϴ���������֪ͨ�飡", 'error');
		return;
		}else{
			$('#type').val('Y');
		}
	}
	if ($('#noticeFile1').val() == '' && $('#noticeFile2').val() == ''
		&& $('#noticeFile3').val() == ''&& $('#noticeFile4').val() != '') {
		$('#type').val('N');
	}
	var isValid = $('#createReason').val();
	if (isValid) {
		$.messager
				.confirm(
						'Confirm',
						'ȷ���ᱨ������?',
						function(r) {
							if (r) {
								// �¸�������
								$.ajax({
									type : "post",
									async : false,
									url : appUrl
											+ "/kunnrAction!selectNexUser.jspa?time="
											+ new Date(),
									data : {
										userId : $("#userId").val(),
										freezeOrClose : $("#freezeOrClose").val(),
									    type:$('#type').val(),
									    kunnrId:$('#kunnrId').val()
									},
									success : function(obj) {
										nextUserHtml(obj);
										processInstanceId = obj.processInstanceId;
									}
	
								});
						$('#nextUserDialog')
								.dialog(
										{
											title : '�¸�������',
											modal : true,
											resizable : false,
											dragable : false,
											closable : false,
											width : 300,
											height : 160,
											content : nextUser,
											buttons : [
													{
														text : 'ȷ��',
														handler : function() {
															if (!$("#nextUsers").val()) {
																$.messager.alert(
																				'Tips',
																				"û���¸������ˣ���ά����",
																				'error');
																return;
															}
															// �¸�������ID
															$("#nextUserId").val(
																			$("#nextUsers").val());
															if ($("#freezeOrClose").val() == 'freeze')
																handelAction('kunnrFreezeApply');
															else
																handelAction('kunnrCloseApply');
														}
													},
													{
														text : 'ȡ��',
														handler : function() {
															handelAction('cancelNextUser');
														}
													} ]
										});

							}
						});
	} else {
		$.messager.alert('Tips', '�밴��ʾ��ȷ��д����Ϣ!', 'warning');
	}
}

// ȡ���ر�
function close() {
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
	var form = window.document.forms[0];
	form.action = appUrl + "/kunnrAction!" + action + ".jspa?eventId="
			+ processInstanceId;
	form.target = "hideFrame";
	form.submit();
}

// ������Ϣ
function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult == "ok") {
		$('#nextUserDialog').dialog('close');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info', function() {
			window.close();
			window.parent.search();
		});
	}
}