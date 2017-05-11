var typeData;
var typeData0=[{
	label: '��ѡ��',
	value: '',
	selected:true //Ĭ��ѡ����
}, {
	label: 'ϵͳȱ��',
	value: 'p101'
}, {
	label: 'ҵ������',
	value: 'p102'
}, {
	label: '���ݴ���',
	value: 'p103'
}, {
	label: '����ָ��',
	value: 'p104'
}, {
	label: 'Ȩ��',
	value: 'p105'
}, {
	label: 'Ӳ��',
	value: 'p201'
}, {
	label: '��ӡ��ӡ����',
	value: 'p202'
}, {
	label: '���硢�绰����',
	value: 'p203'
}, {
	label: '����������',
	value: 'p204'
}, {
	label: '������ѵ',
	value: 'p205'
}, {
	label: 'һ��ͨ�����',
	value: 'p206'
}];
var typeData1=[{
	label: '��ѡ��',
	value: '',
	selected:true //Ĭ��ѡ����
}, {
	label: 'ϵͳȱ��',
	value: 'p101'
}, {
	label: 'ҵ������',
	value: 'p102'
}, {
	label: '���ݴ���',
	value: 'p103'
}, {
	label: '����ָ��',
	value: 'p104'
}, {
	label: 'Ȩ��',
	value: 'p105'
}];
var typeData2=[{
	label: '��ѡ��',
	value: '',
	selected:true //Ĭ��ѡ����
}, {
	label: 'Ӳ��',
	value: 'p201'
}, {
	label: '��ӡ��ӡ����',
	value: 'p202'
}, {
	label: '���硢�绰����',
	value: 'p203'
}, {
	label: '����������',
	value: 'p204'
}, {
	label: '������ѵ',
	value: 'p205'
}, {
	label: 'һ��ͨ�����',
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
			label: '��ѡ��',
			value: '',
			selected:true //Ĭ��ѡ����
		}, {
			label: '���ϵͳ',
			value: 't100'
		}, {
			label: 'Ӳ������',
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
	
	$("#qTemp2 option").remove(); // ����б� 
	$(datas[qTemp]).each(function () {
		var val;
		if(this=='z101'){
			val='����';
		}else if(this=='z102'){
			val='Ӳ��';
		}else if(this=='z103'){
			val='����';
		}else if(this=='z201'){
			val='SAP';
		}else if(this=='z301'){
			val='����';
		}else if(this=='z401'){
			val='�г����ù���';
		}else if(this=='z402'){
			val='�ͻ���ϵ����';
		}else if(this=='z403'){
			val='����';
		}else if(this=='z501'){
			val='���ӱ���ϵͳ';
		}else if(this=='z502'){
			val='���߹���';
		}else if(this=='z503'){
			val='�������';
		}
		var Temp2option = $("<option value=" + this + ">" + val + "</option>");  
		$("#qTemp2").append(Temp2option);  
	});
	
}

/**
 * �ر�ҳ��
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
						                   "<td class='head2' algin='center' width='10%'>�ظ��ˣ�</td>" +
						                   "<td class='even3' algin='center' width='10%'>"+val.author+"</td>" +
						                   "<td class='head2' algin='center' width='10%'>���ݣ�</td>" +
						                   "<td class='even3' algin='center' width='45%'>"+val.content+"</td>" +
						                   "<td class='head2' algin='center' width='10%'>�ظ�ʱ�䣺</td>" +
						                   "<td class='even3' algin='center' width='15%'>"+time+"</td>" +
						                   "</tr>"
						                   );
			});
		}
	});		
}

function chooseAnswerAuthor(){
	initMaintWindow('ѡ������',
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
		$.messager.alert('Tips', '��ѡ������!', 'warning');
		return;
	}
	$.ajax({
		type : "post",
		url : appUrl + "/question/questionAction!sendEmail.jspa",
		data : { answerAuthorId : $("#answerAuthorId").val() , qId : $("#qId").val() , answerAuthorEmail : $("#answerAuthorEmail").val() ,
			     orgName : encodeURIComponent($("#orgName").val()) , author : encodeURIComponent($("#author").val()) },
		success : function(data){
			if(data==true){
			    $.messager.alert('Tips', '���ͳɹ�!', 'info');
			}else{
				$.messager.alert('Tips', '����ʧ�ܣ�����ϵ����Ա!', 'info');
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

//�رմ���ҳ��
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
	week["Mon"] = "һ";
	week["Tue"] = "��";
	week["Wed"] = "��";
	week["Thu"] = "��";
	week["Fri"] = "��";
	week["Sat"] = "��";
	week["Sun"] = "��";

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
