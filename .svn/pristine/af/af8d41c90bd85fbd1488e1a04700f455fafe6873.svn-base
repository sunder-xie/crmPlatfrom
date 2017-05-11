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
	loadGrid();
	$('#hideFrame').bind('load', promgtMsg);
});

function loadGrid() {
	//$.messager.alert('提示', '未选中任何权限点！', '提示');
//	alert("2");
	selTemp();
	$('#question_list')
			.datagrid(
					{
						iconCls : 'icon-list',
						title : '查询结果',
						height : height,
						striped : true,
						url : appUrl
								+ '/questionAction!getQuestionJsonListUser.jspa?ran='
								+ Math.random(),

						loadMsg : '数据远程载入中,请等待...',
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
									 * , { id : 'dictTypeId', title : '序号',
									 * field : 'dictTypeId', width :
									 * setColumnWidth(0.1), align : 'center',
									 * sortable : true }
									 */,
								{
										field : 'oper',
										title : '操作',
										width : 60,
										align : 'center',
										formatter : function(value, row, rec) {
											var id = row.qId;
											return '<img style="cursor:pointer" onclick="edit('
												    + id
												    + ')" title="查看资料" src='
												    + imgUrl
												    + '/images/actions/action_view.png align="absMiddle"></img>';
								        }
								},
								{
											field : 'qId',
											title : '问题编号',
											align : 'center',
											width : 100,
											sortable : true
								},
								{
									field : 'author',
									title : '提报人',
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
									title : '标题',
									width : 250,
									align : 'center',
									sortable : true
								},
								{
									field : 'orgName',
									title : '所属组织',
									width : 250,
									align : 'center',
									sortable : true
									
									
								},
								{
									field : 'qTime',
									title : '提报时间',
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
									title : '系统分类',
									width : 100,
									align : 'center',
									sortable : true,
									formatter : function(v) {
										if(v=='t100'){
											return '软件系统';
										}else if(v=='t200'){
											return '硬件网络';
										}else if(v=='t300'){
											return'人事';
										}else if(v=='t400'){
											return'营销系统';
										}else if(v=='t500'){
											return'协同';
										}else if(v=='t600'){
											return '软件系统';
										}else if(v=='t700'){
											return'软件系统';
										}else if(v=='t800'){
											return'软件系统';
										}else if(v=='t900'){
											return'软件系统';
										}
									}
								},
								{
									field : 'qType',
									title : '问题分类',
									width : 100,
									align : 'center',
									sortable : true,
									formatter : function(v) {
										if(v=='p101'){
											return '系统缺陷';
										}else if(v=='p102'){
											return '业务需求';
										}else if(v=='p103'){
											return'数据错误';
										}else if(v=='p104'){
											return'操作指导';
										}else if(v=='p105'){
											return'权限';
										}else if(v=='p201'){
											return '硬件';
										}else if(v=='p202'){
											return'打印复印故障';
										}else if(v=='p203'){
											return'网络、电话故障';
										}else if(v=='p204'){
											return'电脑运行慢';
										}else if(v=='p205'){
											return'操作培训';
										}else if(v=='p206'){
											return'一卡通、监控';
										}
									}
								},
								{
									field : 'handleState',
									title : '处理状态',
									width : 100,
									align : 'center',
									sortable : true,
									formatter : function(v) {
										if(v=='s101'){
											return '未处理';
										}else if(v=='s102'){
											return '进行中';
										}else if(v=='s103'){
											return '已完成';
										}
									}
								},
								{
									field : 'qLevel',
									title : '问题等级',
									width : 100,
									align : 'center',
									sortable : true,
									formatter : function(v) {
										if(v=='l101'){
											return '非常紧急';
										}else if(v=='l102'){
											return '紧急';
										}else if(v=='l103'){
											return'一般';
										}
									}
								},
								{
									field : 'answerAuthor',
									title : '处理人',
									width : 100,
									align : 'center',
									sortable : true
								},
								{
									field : 'lastDate',
									title : '处理截止日期',
									width : 100,
									align : 'center',
									sortable : true
								}
								] ],
						toolbar : [ "-", {
							text : '新增',
							iconCls : 'icon-add',
							handler : function() {
								add();
							}
						}, "-", {
							text : '删除',
							iconCls : 'icon-remove ',
							handler : function() {
								remove();
							}
						}, "-" ]
					});
	// 分页控件
	var p = $('#question_list').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,
		pageList : [ 10, 20, 30 ],
		beforePageText : '第',
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'
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

// 创建窗口对象
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
	initMaintWindow('系统问题录入', '/questionAction!toCreateQuestion.jspa', 900, 530);
}

function edit(id) {

	initMaintWindow('问题信息修改',
			'/questionAction!toUpdateQuestion.jspa?qId=' + id, 900, 530);
}
//function show(id) {
//
//	initMaintWindow('问题信息查看',
//			'/questionAction!toShowQuestion.jspa?qId=' + id, 1150, 530);
//}

function selectOrgTree() {
	initMaintWindow('选择组织', '/questionAction!orgTreePage.jspa', 400, 460);
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
//        	 val='报表';
//         }else if(key=='t400'){
//        	 val='邮箱';
//         }else if(key==''){
//        	 val='请选择';
//         }
//       var option = $("<option value=" + key + ">" + val + "</option>");  
//       $("#qTemp").append(option);
//     });  
 
//    $("#qTemp").change(function () {  
//       var selectTemp = $("#qTemp").val(); //获取当前Temp
//       $("#qTemp2 option").remove(); //清空列表  
// 
//       if (selectTemp == "") {
//    	   $("#qTemp2").append("<option value=''>请选择</option>");
//           return;  
//       }  
// 
//       $(datas[selectTemp]).each(function () {
//    	 var val;
//    	   if(this=='z101'){
//      	     val='人事管理';
//           }else if(this=='z102'){
//          	 val='客户关系管理';
//           }else if(this=='z103'){
//          	 val='市场费用管理';
//           }else if(this=='z104'){
//          	 val='电子报销系统';
//           }else if(this=='z105'){
//          	 val='客诉管理';
//           }else if(this=='z201'){
//          	 val='SAP';
//           }else if(this=='z301'){
//          	 val='生产中心';
//           }else if(this=='z302'){
//          	 val='工厂财务中心';
//           }else if(this=='z303'){
//          	 val='营销公司财务中心';
//           }else if(this=='z304'){
//          	 val='供应链中心';
//           }else if(this=='z305'){
//          	 val='营运计划';
//           }else if(this=='z306'){
//          	 val='销售管理部';
//           }else if(this=='z401'){
//          	 val='邮箱';
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
				label: '请选择',
				value: '',
				selected:true //默认选中项
			}, {
				label: '邮箱',
				value: 'z101'
			}, {
				label: '硬件',
				value: 'z102'
			}, {
				label: '网络',
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
				label: '请选择',
				value: '',
				selected:true //默认选中项
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
				label: '请选择',
				value: '',
				selected:true //默认选中项
			}, {
				label: '人事',
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
				label: '请选择',
				value: '',
				selected:true //默认选中项
			}, {
				label: '市场费用管理',
				value: 'z401'
			}, {
				label: '客户关系管理',
				value: 'z402'
			}, {
				label: '报表',
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
				label: '请选择',
				value: '',
				selected:true //默认选中项
			}, {
				label: '电子报销系统',
				value: 'z501'
			}, {
				label: '客诉管理',
				value: 'z502'
			}, {
				label: '事务管理',
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
				label: '请选择',
				value: '',
				selected:true //默认选中项
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
		$.messager.alert('提示', '未选中任何权限点！', '提示');
		return;
	} else {
		$.messager
				.confirm(
						'Confirm',
						'是否确认批量删除?',
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

// 关闭创建页面
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
		search();
		return false;
	}
	return true;
};