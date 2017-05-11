function exportActivityPlanInfo() {
	var form = window.document.forms[0];
	form.action = appUrl
			+ '/complaintReport/complaintReportAction!getWorkShop.jspa';
	form.target = "hideFrame";
	form.submit();
}

function download(){
	var form = window.document.forms[0];
	form.action = appUrl
			+ '/complaintReport/complaintReportAction!download.jspa';
	form.target = "hideFrame";
	form.submit();
}

function loadFile(){
//	$.ajax({
//		type:"post",
//		url: appUrl + '/complaintReport/complaintReportAction!createGoal.jspa',
//		data: {file:$("#file")},
//		success:function(data){
//			
//		}
//	});
	$.messager.progress();
	openTime();
	var form = window.document.forms[0];
	form.action = appUrl
			+ '/complaintReport/complaintReportAction!createGoal.jspa';
	form.target = "hideFrame";
	form.submit();
}

function openTime() {
	setTimeout(function() {
		var timer = setInterval(function() {
			$.ajax({
				type : "post",
				url : appUrl + "/complaintReportAction!checkDownLoadOver.jspa?",
				success : function(data) {
					if (data == 'Yes') {
						clearInterval(timer);
						$.messager.progress('close');
						$.messager.alert('Tips','导入成功', 'warning');
					}
				}
			});
		}, 100);
	}, 500);
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	alert(successResult);
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'warning');
	}
}