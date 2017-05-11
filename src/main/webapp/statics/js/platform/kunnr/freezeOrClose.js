var nextUser;
var processInstanceId;
$(document).ready(function() {
	$('#hideFrame').bind('load', promgtMsg);

});
// 提交
function submit() {
	if ($('#noticeFile1').val() != '' || $('#noticeFile2').val() != ''
			|| $('#noticeFile3').val() != ''|| $('#noticeFile4').val() != '') {
		if(!($('#noticeFile1').val() != '' && $('#noticeFile2').val() != ''
			&& $('#noticeFile3').val() != ''&& $('#noticeFile4').val() != '')){
		$.messager.alert('Tips', "请上传四张整改通知书！", 'error');
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
						'确定提报该申请?',
						function(r) {
							if (r) {
								// 下个处理人
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
											title : '下个处理人',
											modal : true,
											resizable : false,
											dragable : false,
											closable : false,
											width : 300,
											height : 160,
											content : nextUser,
											buttons : [
													{
														text : '确定',
														handler : function() {
															if (!$("#nextUsers").val()) {
																$.messager.alert(
																				'Tips',
																				"没有下个处理人，请维护！",
																				'error');
																return;
															}
															// 下个处理人ID
															$("#nextUserId").val(
																			$("#nextUsers").val());
															if ($("#freezeOrClose").val() == 'freeze')
																handelAction('kunnrFreezeApply');
															else
																handelAction('kunnrCloseApply');
														}
													},
													{
														text : '取消',
														handler : function() {
															handelAction('cancelNextUser');
														}
													} ]
										});

							}
						});
	} else {
		$.messager.alert('Tips', '请按提示正确填写表单信息!', 'warning');
	}
}

// 取消关闭
function close() {
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
	var form = window.document.forms[0];
	form.action = appUrl + "/kunnrAction!" + action + ".jspa?eventId="
			+ processInstanceId;
	form.target = "hideFrame";
	form.submit();
}

// 返回信息
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