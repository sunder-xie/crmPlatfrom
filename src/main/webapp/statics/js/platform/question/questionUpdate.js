var typeData;
var typeData0=[{
	label: '请选择',
	value: '',
	selected:true //默认选中项
}, {
	label: '系统缺陷',
	value: 'p101'
}, {
	label: '业务需求',
	value: 'p102'
}, {
	label: '数据错误',
	value: 'p103'
}, {
	label: '操作指导',
	value: 'p104'
}, {
	label: '权限',
	value: 'p105'
}, {
	label: '硬件',
	value: 'p201'
}, {
	label: '打印复印故障',
	value: 'p202'
}, {
	label: '网络、电话故障',
	value: 'p203'
}, {
	label: '电脑运行慢',
	value: 'p204'
}, {
	label: '操作培训',
	value: 'p205'
}, {
	label: '一卡通、监控',
	value: 'p206'
}];
var typeData1=[{
	label: '请选择',
	value: '',
	selected:true //默认选中项
}, {
	label: '系统缺陷',
	value: 'p101'
}, {
	label: '业务需求',
	value: 'p102'
}, {
	label: '数据错误',
	value: 'p103'
}, {
	label: '操作指导',
	value: 'p104'
}, {
	label: '权限',
	value: 'p105'
}];
var typeData2=[{
	label: '请选择',
	value: '',
	selected:true //默认选中项
}, {
	label: '硬件',
	value: 'p201'
}, {
	label: '打印复印故障',
	value: 'p202'
}, {
	label: '网络、电话故障',
	value: 'p203'
}, {
	label: '电脑运行慢',
	value: 'p204'
}, {
	label: '操作培训',
	value: 'p205'
}, {
	label: '一卡通、监控',
	value: 'p206'
}];
$(document).ready(function() {
	typeData=typeData0;
	init();
	selTemp();
	loadData();
	$('#hideFrame').bind('load', promgtMsg);
});

function init(){
//	if($('#qTemp2Val').val()!=null){
//		selTemp2($('#qTempVal').val());
//	}
//	$("#qTemp").change(function () {
//		selTemp2($('#qTemp').val());
//	});
//	var qTemp2=$('#qTemp2');
	var qLevel=$('#qLevel');
	var qType=$('#qType');
	var qTemp=$('#qTemp');
	var handleState=$('#handleState');
	qTemp.get(0).value = $('#qTempVal').val();
	qType.get(0).value = $('#qTypeVal').val();
	qLevel.get(0).value = $('#qLevelVal').val();
	handleState.get(0).value = $('#handleStateVal').val();
//	qTemp2.get(0).value = $('#qTemp2Val').val();
	
}
function selTemp(){
	$("#qTemp").combobox({
		valueField: 'value',
		textField: 'label',
		width:120,
		editable:false,
		panelHeight:"auto",
		data: [{
			label: '请选择',
			value: '',
			selected:true //默认选中项
		}, {
			label: '软件系统',
			value: 't100'
		}, {
			label: '硬件网络',
			value: 't200'
		}
		],
		onChange : function(newValue, oldValue) {
			
			if(newValue == "t100") {
				typeData=typeData1;
				$("#qType").combobox('loadData',typeData);
			}else if(newValue == "t200"){
				typeData=typeData2;
				$("#qType").combobox('loadData',typeData);
			}else{
				typeData=typeData0;
				$("#qType").combobox('loadData',typeData);
			}
		}
	});
	$("#qType").combobox({
		valueField: 'value',
		textField: 'label',
		width:120,
		editable:false,
		panelHeight:"auto",
		data : typeData
	});
}
function selTemp2(qTemp){
	var datas = { "": [""],"t100": ["z101", "z102", "z103"], "t200": ["z201"], 
			"t300": ["z301"], "t400": ["z401","z402","z403"],"t500":["z501","z502","z503"] };
	
	$("#qTemp2 option").remove(); // 清空列表 
	$(datas[qTemp]).each(function () {
		var val;
		if(this=='z101'){
			val='邮箱';
		}else if(this=='z102'){
			val='硬件';
		}else if(this=='z103'){
			val='网络';
		}else if(this=='z201'){
			val='SAP';
		}else if(this=='z301'){
			val='人事';
		}else if(this=='z401'){
			val='市场费用管理';
		}else if(this=='z402'){
			val='客户关系管理';
		}else if(this=='z403'){
			val='报表';
		}else if(this=='z501'){
			val='电子报销系统';
		}else if(this=='z502'){
			val='客诉管理';
		}else if(this=='z503'){
			val='事务管理';
		}
		var Temp2option = $("<option value=" + this + ">" + val + "</option>");  
		$("#qTemp2").append(Temp2option);  
	});
	
}

/**
 * 关闭页面
 */
function cencel() {
	window.parent.closeMaintWindow();
}

function downLoad(fileId) {
	var form = window.document.forms[0];
	form.action = appUrl + "/question/questionAction!downLoadFile.jspa?fileId="
			+ fileId;
	form.submit();
}

function loadData(){
	$.ajax({
		type : "post",
		url : appUrl + "/question/questionAction!getCommunitList.jspa",
		data : { qId : $("#qId").val() },
		success : function(communitList){
			$.each(communitList,function(key,val){
				var time=utcToDate(val.createTime.replace(
							       /\/Date\((\d+)\+\d+\)\//gi,
							       "new Date($1)"));
				
				$("#communit_list").append("<tr>" +
						                   "<td class='head2' algin='center' width='10%'>回复人：</td>" +
						                   "<td class='even3' algin='center' width='10%'>"+val.author+"</td>" +
						                   "<td class='head2' algin='center' width='10%'>内容：</td>" +
						                   "<td class='even3' algin='center' width='45%'>"+val.content+"</td>" +
						                   "<td class='head2' algin='center' width='10%'>回复时间：</td>" +
						                   "<td class='even3' algin='center' width='15%'>"+time+"</td>" +
						                   "</tr>"
						                   );
			});
		}
	});		
}

function chooseAnswerAuthor(){
	initMaintWindow('选择处理人',
			'/question/questionAction!selectAnswer.jspa',
			'maintWindow', 660, 430, false);
}

function saveUser(x){
	closeMaintWindow()
	$("#answerAuthor").val(x[1]);
	$("#answerAuthorId").val(x[0]);
}

function sendEmail(){
	if($("#answerAuthor").val()== ""){
		$.messager.alert('Tips', '请选择处理人!', 'warning');
		return;
	}
	$.ajax({
		type : "post",
		url : appUrl + "/question/questionAction!sendEmail.jspa",
		data : { answerAuthorId : $("#answerAuthorId").val() , qId : $("#qId").val() , answerAuthorEmail : $("#answerAuthorEmail").val() ,
			     orgName : encodeURIComponent($("#orgName").val()) , author : encodeURIComponent($("#author").val()) },
		success : function(data){
			if(data==true){
			    $.messager.alert('Tips', '发送成功!', 'info');
			}else{
				$.messager.alert('Tips', '发送失败，请联系管理员!', 'info');
			}
		}
	});
}

function updateQuestion() {
	var form = window.document.forms[0];
	form.action = appUrl + "/question/questionAction!updateQuestion.jspa";
	form.target = "hideFrame";
	form.submit();
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.confirm('Tips', successResult, function(r) {
			if (r) {
				cencel();
				window.parent.search();
			}
		});
	}
}

/*
 * document.onkeydown = function(e) { var theEvent = e || window.event; var code =
 * theEvent.keyCode || theEvent.which || theEvent.charCode; if (code == 13) {
 * search(); return false; } return true; };
 */

function initMaintWindow(title, url, id, WWidth, WHeight, fit) {
	var url = appUrl + url;
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
						closed : true,
						closable : true,
						fit : fit,
						minimizable : false,
						maximizable : false,
						collapsible : false,
						draggable : true
					});

	$win.window('open');
}

//关闭创建页面
function closeMaintWindow() {
	$("#maintWindow").window('close');
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
	week["Mon"] = "一";
	week["Tue"] = "二";
	week["Wed"] = "三";
	week["Thu"] = "四";
	week["Fri"] = "五";
	week["Sat"] = "六";
	week["Sun"] = "日";

	str = utcCurrTime.split(" ");
	date = str[5] + "-";
	date = date + month[str[1]] + "-" + str[2] + " " + str[3];
	return date;
}

function setColumnWidth(percent) {
	return $(this).width() * percent;
}
$("body").on("click",'.combo-text',function(){
	$(this).siblings("span").find("span").click();
	});
document.onkeydown = function(e) {
	var theEvent = e || window.event;
	var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
	if (code == 13) {
		return false;
	}
	return true;
};
