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
	selTemp();
	$('#hideFrame').bind('load', promgtMsg);
});

function save_question() {
	if ($("#qTitle").val()=="") {
		$.messager.alert('Tips', "请填写标题！", 'error');
		return;
	}
	if ($("#question").val()=="") {
		$.messager.alert('Tips', "请填写问题描述！", 'error');
		return;
	}
	if (!$("#authorEmail").validatebox('isValid')) {
		$.messager.alert('Tips', "邮箱未填写或者格式错误！", 'error');
		return;
	}
	var form = window.document.forms[0];
	form.action = appUrl + "/question/questionAction!createQuestion.jspa";
	form.target = "hideFrame";
	form.submit();
}

function cencel() {
	window.parent.closeMaintWindow();
}

function chooseAnswerAuthor(){
	initMaintWindow('选择处理人',
			'/question/questionAction!selectAnswer.jspa',
			'maintWindow', 660, 430, false);
}

function saveUser(x){
	closeMaintWindow();
	$("#answerAuthor").val(x[1]);
	$("#answerAuthorId").val(x[0]);
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
$("body").on("click",'.combo-text',function(){
	$(this).siblings("span").find("span").click();
	});
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
