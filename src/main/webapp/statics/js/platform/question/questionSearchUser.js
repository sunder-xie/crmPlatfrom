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
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	//$.messager.alert('��ʾ', 'δѡ���κ�Ȩ�޵㣡', '��ʾ');
//	alert("2");
	selTemp();
	$('#question_list')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '��ѯ���',
						height : height,
						striped : true,
						url : appUrl
								+ '/questionAction!getQuestionJsonListUser.jspa?ran='
								+ Math.random(),

						loadMsg : '����Զ��������,��ȴ�...',
						singleSelect : false,
						nowrap : true,
						pagination : true,
						rownumbers : true,
						columns : [ [
								{
									field : 'ck',
									align : 'center',
									checkbox : true
								}/*
									 * , { id : 'dictTypeId', title : '���',
									 * field : 'dictTypeId', width :
									 * setColumnWidth(0.1), align : 'center',
									 * sortable : true }
									 */,
								{
										field : 'oper',
										title : '����',
										width : 60,
										align : 'center',
										formatter : function(value, row, rec) {
											var id = row.qId;
											return '<img style="cursor:pointer" onclick="edit('
												    + id
												    + ')" title="�鿴����" src='
												    + imgUrl
												    + '/images/actions/action_view.png align="absMiddle"></img>';
								        }
								},
								{
											field : 'qId',
											title : '������',
											align : 'center',
											width : 100,
											sortable : true
								},
								{
									field : 'author',
									title : '�ᱨ��',
									align : 'center',
									width : 100,
									sortable : true
								// formatter : function(value, row, index) {
								// /* var id=row.stationName; */
								// var cc = encodeURIComponent(row.dictTypeId);
								// return '<a href="#"
								// onclick=javascript:search_dict("'
								// + cc
								// + '") > ' + value + '</a>';
								//									
								// }
								},{
									field : 'qTitle',
									title : '����',
									width : 250,
									align : 'center',
									sortable : true
								},
								{
									field : 'orgName',
									title : '������֯',
									width : 250,
									align : 'center',
									sortable : true
									
									
								},
								{
									field : 'qTime',
									title : '�ᱨʱ��',
									align : 'center',
									width : 100,
									sortable : true
//									formatter : function(v) {
//										var year=v.substr(0,4);
//										var month=v.substr(4,2);
//										var day=v.substr(6,2);
//										return year + '-' + month + '-' + day;
//									}
								},
								{
									field : 'qTemp',
									title : 'ϵͳ����',
									width : 100,
									align : 'center',
									sortable : true,
									formatter : function(v) {
										if(v=='t100'){
											return '���ϵͳ';
										}else if(v=='t200'){
											return 'Ӳ������';
										}else if(v=='t300'){
											return'����';
										}else if(v=='t400'){
											return'Ӫ��ϵͳ';
										}else if(v=='t500'){
											return'Эͬ';
										}else if(v=='t600'){
											return '���ϵͳ';
										}else if(v=='t700'){
											return'���ϵͳ';
										}else if(v=='t800'){
											return'���ϵͳ';
										}else if(v=='t900'){
											return'���ϵͳ';
										}
									}
								},
								{
									field : 'qType',
									title : '�������',
									width : 100,
									align : 'center',
									sortable : true,
									formatter : function(v) {
										if(v=='p101'){
											return 'ϵͳȱ��';
										}else if(v=='p102'){
											return 'ҵ������';
										}else if(v=='p103'){
											return'���ݴ���';
										}else if(v=='p104'){
											return'����ָ��';
										}else if(v=='p105'){
											return'Ȩ��';
										}else if(v=='p201'){
											return 'Ӳ��';
										}else if(v=='p202'){
											return'��ӡ��ӡ����';
										}else if(v=='p203'){
											return'���硢�绰����';
										}else if(v=='p204'){
											return'����������';
										}else if(v=='p205'){
											return'������ѵ';
										}else if(v=='p206'){
											return'һ��ͨ�����';
										}
									}
								},
								{
									field : 'handleState',
									title : '����״̬',
									width : 100,
									align : 'center',
									sortable : true,
									formatter : function(v) {
										if(v=='s101'){
											return 'δ����';
										}else if(v=='s102'){
											return '������';
										}else if(v=='s103'){
											return '�����';
										}
									}
								},
								{
									field : 'qLevel',
									title : '����ȼ�',
									width : 100,
									align : 'center',
									sortable : true,
									formatter : function(v) {
										if(v=='l101'){
											return '�ǳ�����';
										}else if(v=='l102'){
											return '����';
										}else if(v=='l103'){
											return'һ��';
										}
									}
								},
								{
									field : 'answerAuthor',
									title : '������',
									width : 100,
									align : 'center',
									sortable : true
								},
								{
									field : 'lastDate',
									title : '�����ֹ����',
									width : 100,
									align : 'center',
									sortable : true
								}
								] ],
						toolbar : [ "-", {
							text : '����',
							iconCls : 'icon-add',
							handler : function() {
								add();
							}
						}, "-", {
							text : 'ɾ��',
							iconCls : 'icon-remove ',
							handler : function() {
								remove();
							}
						}, "-" ]
					});
	// ��ҳ�ؼ�
	var p = $('#question_list').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '��',
		afterPageText : 'ҳ    �� {pages} ҳ',
		displayMsg : '��ǰ��ʾ {from} - {to} ����¼   �� {total} ����¼'
	});
}

function search() {
	var queryParams = $('#question_list').datagrid('options').queryParams;
	queryParams.qId = $("#qId").val();
	queryParams.author = encodeURIComponent($("#author").val());
	queryParams.orgId = $("#orgId").val();
	queryParams.qTitle = $("#qTitle").val();
	queryParams.qTime = document.getElementsByName("qTime")[0].value;
	queryParams.qTime2 = document.getElementsByName("qTime")[1].value;
	queryParams.qTemp = $("#qTemp").combobox("getValue");
//	queryParams.qTemp2 = $("#qTemp2").combobox("getValue");
	queryParams.qLevel = $("#qLevel").combobox("getValue");
	queryParams.answerAuthor = encodeURIComponent($("#answerAuthor").val());
	queryParams.handleState = $("#handleState").combobox("getValue");
	queryParams.qType = $("#qType").combobox("getValue");
	$("#question_list").datagrid('load');
}

// �������ڶ���
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
						minimizable : false,
						maximizable : true,
						collapsible : true,
						draggable : true
					//
					});

	$win.window('open');
}

function add() {
	initMaintWindow('ϵͳ����¼��', '/questionAction!toCreateQuestion.jspa', 900, 530);
}

function edit(id) {

	initMaintWindow('������Ϣ�޸�',
			'/questionAction!toUpdateQuestion.jspa?qId=' + id, 900, 530);
}
//function show(id) {
//
//	initMaintWindow('������Ϣ�鿴',
//			'/questionAction!toShowQuestion.jspa?qId=' + id, 1150, 530);
//}

function selectOrgTree() {
	initMaintWindow('ѡ����֯', '/questionAction!orgTreePage.jspa', 400, 460);
}
function returnValue(selectedId, selectedName) {
	document.getElementById('orgId').value = selectedId;
	document.getElementById('orgName').value = selectedName;
}

function closeOrgTree() {
	closeDtPlan();
}
function closeDtPlan() {
	$("#maintWindow").window('close');
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
//	var datas = { "": [""],"t100": ["z101", "z102", "z103", "z104", "z105"], "t200": ["z201"], 
//		     "t300": ["z301", "z302", "z303", "z304", "z305", "z306"], "t400": ["z401"] };  
// 
//    $(function () {  
//     $.each(datas, function (key, value) {
//       var val;
//         if(key=='t100'){
//    	     val='EXP';
//         }else if(key=='t200'){
//        	 val='SAP';
//         }else if(key=='t300'){
//        	 val='����';
//         }else if(key=='t400'){
//        	 val='����';
//         }else if(key==''){
//        	 val='��ѡ��';
//         }
//       var option = $("<option value=" + key + ">" + val + "</option>");  
//       $("#qTemp").append(option);
//     });  
 
//    $("#qTemp").change(function () {  
//       var selectTemp = $("#qTemp").val(); //��ȡ��ǰTemp
//       $("#qTemp2 option").remove(); //����б�  
// 
//       if (selectTemp == "") {
//    	   $("#qTemp2").append("<option value=''>��ѡ��</option>");
//           return;  
//       }  
// 
//       $(datas[selectTemp]).each(function () {
//    	 var val;
//    	   if(this=='z101'){
//      	     val='���¹���';
//           }else if(this=='z102'){
//          	 val='�ͻ���ϵ����';
//           }else if(this=='z103'){
//          	 val='�г����ù���';
//           }else if(this=='z104'){
//          	 val='���ӱ���ϵͳ';
//           }else if(this=='z105'){
//          	 val='���߹���';
//           }else if(this=='z201'){
//          	 val='SAP';
//           }else if(this=='z301'){
//          	 val='��������';
//           }else if(this=='z302'){
//          	 val='������������';
//           }else if(this=='z303'){
//          	 val='Ӫ����˾��������';
//           }else if(this=='z304'){
//          	 val='��Ӧ������';
//           }else if(this=='z305'){
//          	 val='Ӫ�˼ƻ�';
//           }else if(this=='z306'){
//          	 val='���۹���';
//           }else if(this=='z401'){
//          	 val='����';
//           }
//           var Temp2option = $("<option value=" + this + ">" + val + "</option>");  
//           $("#qTemp2").append(Temp2option);  
//           });  
//       });  
//    }); 
}

function selTemp2(val){
	if(val=='t100'){
		$("#qTemp2").combobox({
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
				label: '����',
				value: 'z101'
			}, {
				label: 'Ӳ��',
				value: 'z102'
			}, {
				label: '����',
				value: 'z103'
			}
			]
		});
	}else if(val=='t200'){
		$("#qTemp2").combobox({
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
				label: 'SAP',
				value: 'z201'
			}
			]
		});
	}else if(val=='t300'){
		$("#qTemp2").combobox({
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
				label: '����',
				value: 'z301'
			}
			]
		});
	}else if(val=='t400'){
		$("#qTemp2").combobox({
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
				label: '�г����ù���',
				value: 'z401'
			}, {
				label: '�ͻ���ϵ����',
				value: 'z402'
			}, {
				label: '����',
				value: 'z403'
			}
			]
		});
	}else if(val=='t500'){
		$("#qTemp2").combobox({
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
				label: '���ӱ���ϵͳ',
				value: 'z501'
			}, {
				label: '���߹���',
				value: 'z502'
			}, {
				label: '�������',
				value: 'z503'
			}
			]
		});
	}else if(val==''){
		$("#qTemp2").combobox({
			valueField: 'value',
			textField: 'label',
			width:120,
			editable:false,
			panelHeight:"auto",
			data: [{
				label: '��ѡ��',
				value: '',
				selected:true //Ĭ��ѡ����
			}
			]
		});
	}
}

function selUser() {
	if($("#loginId").val()!="admin"){
		$("#orgTreeBut").attr("href","#");
		$("#author").attr("readonly","true");
	}
}

function clearValue(){
	$("#author").val("");
	$("#qTime").datebox().next('span').find('input').val("");
	$("#qTime2").datebox().next('span').find('input').val("");
	$('#qTemp').combobox().next('span').find('input').val("");
	$("#qTemp2").combobox().next('span').find('input').val("");
	$("#qLevel").combobox().next('span').find('input').val("");
	$("#handleState").combobox().next('span').find('input').val("");
	$("#qType").combobox().next('span').find('input').val("");
	$("#answerAuthor").val("");
}

function remove() {
	var ids = new Array();
	var rows = $('#question_list').datagrid('getSelections');
	for ( var i = 0; i < rows.length; i++) {
		ids[i] = rows[i].qId;
	}
//	if (ids == '') {
	if (ids.length==0) {
		$.messager.alert('��ʾ', 'δѡ���κ�Ȩ�޵㣡', '��ʾ');
		return;
	} else {
		$.messager
				.confirm(
						'Confirm',
						'�Ƿ�ȷ������ɾ��?',
						function(r) {
							if (r) {
								var idsJson="[" + ids + "]";
								var form = window.document.forms[0];
								form.action = appUrl
										+ '/question/questionAction!deleteQuestion.jspa?deleteQuestionId='
										+ idsJson;
								form.target = "hideFrame";
								form.submit();
							}
						});
	}

}

// �رմ���ҳ��
function closeMaintWindow() {
	$("#maintWindow").window('close');
}

function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info');
		search();
	}
}

/*
 * document.onkeydown = function(e) { var theEvent = e || window.event; var code =
 * theEvent.keyCode || theEvent.which || theEvent.charCode; if (code == 13) {
 * search(); return false; } return true; };
 */

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
		search();
		return false;
	}
	return true;
};