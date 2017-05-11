var nextUser;
var processInstanceId;
var addressCount = 1;
if ($('#xmlId').val() == '' || $('#bCustomerTargetListSize').val() == 0) {
	addressCount = 1;
} else {
	addressCount = $('#bCustomerTargetListSize').val();
}

var brandCount = 1;

var acountCount = 1;
if ($('#xmlId').val() == '' || $('#acountListSize').val() == 0) {
	acountCount = 1;
} else {
	acountCount = $('#acountListSize').val();
}
// 2;
var areaCount = 0; // 1
var goalCount = 1;
var userRole = '';
var month = [];
// var kunnrSalesAreaList = [];
var kunnrAddressList = [];
$(document)
		.ready(

				function() {
					if($('#konzsTxt').val()==''){
					initHide();}else{
						show();
					}
					$('.goalFlag').hide();
					$('#hideFrame').bind('load', promgtMsg);
				
					// 行政区划
					function getRegion(level) {
						$('#region' + level)
								.combobox(
										{
											editable : false,
											multiple : false,
											url : appUrl
													+ '/salesMsgAction!searchRegion.jspa?level='
													+ level + '&pid='
													+ $('#region').val(),
											textField : 'text',
											valueField : 'id',
											onLoadSuccess : function() {
												if ($('#xmlId').val() != ''
														&& undefined != $(
																'#xmlId').val()&&$('#bgXzAddress').val()!='') {
													$('#region')
															.val(
																	$(
																			'#region'
																					+ level)
																			.combobox(
																					'getValue'));
													if (level == 1) {
														$('#bgXzProvince')
																.val(
																		$(
																				'#region'
																						+ level)
																				.combobox(
																						'getText'));
													} else if (level == 2) {
														$('#bgXzCity')
																.val(
																		$(
																				'#region'
																						+ level)
																				.combobox(
																						'getText'));
													} else if (level == 3) {
														$('#bgXzArea')
																.val(
																		$(
																				'#region'
																						+ level)
																				.combobox(
																						'getText'));
													} else if (level == 4) {
														$('#bgXzTown')
																.val(
																		$(
																				'#region'
																						+ level)
																				.combobox(
																						'getText'));
													}
													++level;
													getRegion(level);
												} else {
													$('#region' + level)
															.combobox(
																	"setText",
																	'请选择');
													++level;
												}

											},
											onSelect : function(re) {
												$('#region').val(re.id);
												if (level == 2) {
													$('#bgXzProvince').val(
															re.text);
												} else if (level == 3) {
													$('#bgXzCity').val(re.text);
												} else if (level == 4) {
													$('#bgXzArea').val(re.text);
												} else if (level == 5) {
													$('#bgXzTown').val(re.text);
												}
												$('#bgXzAddress')
														.val(
																$(
																		'#bgXzProvince')
																		.val()
																		+ $(
																				'#bgXzCity')
																				.val()
																		+ $(
																				'#bgXzArea')
																				.val()
																		+ $(
																				'#bgXzTown')
																				.val());
												getRegion(level);
											}

										});

					}
					;
					getRegion(1);
					$('#brands').combobox({
						valueField : 'value',
						textField : 'text',
						multiple : true,
						editable : false,
						data : [ {
							value : 'X',
							text : '香飘飘'
						}, {
							value : 'L',
							text : '兰芳园'
						}, {
							value : 'M',
							text : 'MECO'
						}],
						onLoadSuccess : function() {
							$('#brands').combobox('setText', $('#brandsC').val());
							$('#brands').combobox('setValues', $('#brandsDM').val());
						}
					});
					// 性别
					$('#sex')
							.combobox(
									{
										valueField : 'value',
										textField : 'text',
										data : [ {
											value : '',
											text : '请选择'
										}, {
											value : 'male',
											text : '男'
										}, {
											value : 'female',
											text : '女'
										} ],
										multiple : false,
										onLoadSuccess : function() {
											if ($('#xmlId').val() == ''
													|| $('#xmlId').val() == undefined) {
												$('#sex')
														.combobox(
																'setValue',
																$('#sex')
																		.combobox(
																				'getData')[0].value);
											}
										}
									});

					// 纳税人类型
					$('#kverm')
							.combobox(
									{
										valueField : 'text',
										textField : 'text',
										data : [ {
											value : '一般纳税人',
											text : '一般纳税人'
										}, {
											value : '小规模纳税人',
											text : '小规模纳税人'
										} ],
										multiple : false,
										onLoadSuccess : function() {
											if ($('#xmlId').val() == '') {
												$('#kverm')
														.combobox(
																'setValue',
																$('#kverm')
																		.combobox(
																				'getData')[0].value);
											}
											refreshLicense();
										},
										onSelect : function(r) {
											refreshLicense();
										}
									});

					// 客户分类 渠道
					$('#channelId')
							.combobox(
									{
										multiple : false,
										url : appUrl
												+ '/kunnrAction!getChannel.jspa?channelName='
												+ 'K',
										valueField : 'channelId',
										textField : 'channelName',
										onLoadSuccess : function() {
										},
										onSelect : function(record) {
											var str = record.channelName;
											var kukla = 'Z2';
											if ('特约经销商' == str) {
												kukla = 'Z3';
											} else if ('KA直营' == str
													|| '其他直营' == str
													|| '电商直营' == str) {
												kukla = 'Z4';
											}
											$('#channelName').val(str);
											$('#kukla').val(kukla);
										}
									});

					// 客户科目组
					$('#ktokd')
							.combobox(
									{
										url : appUrl
												+ '/kunnrAction!getCrmDictList.jspa?dictTypeName='
												+ 'KHKM',
										valueField : 'itemValue',
										textField : 'itemValue',
										multiple : false,
										onLoadSuccess : function() {
											$('#ktokd')
													.combobox(
															'setValue',
															$('#ktokd')
																	.combobox(
																			'getData')[0].itemValue);
										}
									});

					// 上级经销商查询
					$('#konzs')
							.combogrid(
									{
										panelHeight : 280,
										panelWidth : 420,
										pagination : true,
										multiple : false,
										method : 'post',
										singleSelect : true,
										url : appUrl
												+ '/kunnrAction!kunnrSearch.jspa?orgId='
												+ $('#orgId').val()
												+ '&bhxjFlag=' + 'C',
										idField : 'kunnr',
										textField : 'name1',
										columns : [ [ {
											field : 'kunnr',
											title : '经销商编号',
											width : 120
										}, {
											field : 'name1',
											title : '名称',
											width : 250
										} ] ],
										toolbar : '#toolbarKonzs',
										onSelect : function(index, record) {
											$('#konzsTxt').val(record.name1);
											$("#konzsId").val(record.kunnr);
											if ($('#konzs')
													.combobox("getValue")) {
												show();
											} else {
												hide();
											}
										}
									});
					$('#konzs').combogrid('setValue', $("#konzsId").val());
					$('#konzs').combogrid('setText', $("#konzsTxt").val());
					
					// 主户经销商查询
					$('#kunag')
							.combogrid(
									{
										panelHeight : 280,
										panelWidth : 420,
										pagination : true,
										multiple : false,
										method : 'post',
										singleSelect : true,
										url : appUrl
												+ '/kunnrAction!kunnrSearch.jspa?orgId='
												+ $('#orgId').val()
												+ '&bhxjFlag=' + 'C',
										idField : 'kunnr',
										textField : 'name1',
										columns : [ [ {
											field : 'kunnr',
											title : '经销商编号',
											width : 120
										}, {
											field : 'name1',
											title : '名称',
											width : 250
										} ] ],
										toolbar : '#toolbarKunag',
										onSelect : function(index, record) {
											$('#kunagTxt').val(record.name1);
											$("#kunagId").val(record.kunnr);
										}
									});
					$('#kunag').combogrid('setValue', $("#kunagId").val());
					$('#kunag').combogrid('setText', $("#kunagTxt").val());
					// 装运条件
					$('#vsbed_1')
							.combobox(
									{
										valueField : 'itemName',
										textField : 'itemValue',
										multiple : false,
										url : appUrl
												+ '/kunnrAction!getCrmDictList.jspa?dictTypeValue='
												+ 'zyc'
									});

					// 最大可通行车型
					$('#maximum02')
							.combobox(
									{
										valueField : 'itemName',
										textField : 'itemValue',
										multiple : false,
										url : appUrl
												+ '/kunnrAction!getCrmDictList.jspa?dictTypeValue='
												+ 'carType'
									});

					for ( var i = 1; i <= addressCount; i++) {
						$('#maximum_' + i)
								.combobox(
										{
											valueField : 'itemName',
											textField : 'itemValue',
											url : appUrl
													+ '/kunnrAction!getCrmDictList.jspa?dictTypeValue='
													+ 'carType'
										});
					}
					// 销售大区
					$('#vkgrp')
							.combogrid(
									{
										panelHeight : 280,
										panelWidth : 420,
										pagination : true,
										method : 'post',
										singleSelect : true,
										multiple : false,
										url : appUrl
												+ '/salesMsgAction!getT171tJsonList.jspa?rows=100',
										idField : 'bzirk',
										textField : 'bztxt',
										columns : [ [ {
											field : 'bzirk',
											title : '大区编号',
											width : 100
										}, {
											field : 'bztxt',
											title : '名称',
											width : 120
										} ] ],
										toolbar : '#toolbarVkgrp',
										onSelect : function(index, record) {
											if ($('#bzirk').combogrid(
													"getValue")) {
												var preCode = (record.bzirk)
														.substring(1, 3)
														+ $('#bzirk')
																.combogrid(
																		"getValue")
																.substring(1, 3);
											/*	var code = preCode
														+ $("#kunnrCode").val();
												$("#kunnr").val(code);*/
												$("#codeSales").val(preCode);

											}
											$('#bzirk').combogrid('setValue',
													'');
											$('#vkbur')
													.combobox('setValue', '');
											$('#bzirk').combogrid({
												disabled : false
											});
											loadBzirk(record.bzirk);
										}
									});

					// 销售省份
					var bzirk = $('#bzirk').combogrid("getValue");
					loadBzirk(bzirk);
					if (bzirk == '') {
						$('#bzirk').combogrid({
							disabled : true
						});
						$('#vkbur').combobox({
							disabled : true
						});
					}

					function loadBzirk(bzirk) {
						$('#bzirk')
								.combogrid(
										{
											panelHeight : 280,
											panelWidth : 420,
											pagination : false,
											multiple : false,
											method : 'post',
											singleSelect : true,
											url : appUrl
													+ '/salesMsgAction!getTvgrtListByZdqsf.jspa?bzirk='
													+ $('#vkgrp').combogrid(
															'getValue'),
											idField : 'vkgrp',
											textField : 'bezei',
											columns : [ [ {
												field : 'vkgrp',
												title : '省份编号',
												width : 100
											}, {
												field : 'bezei',
												title : '名称',
												width : 120
											} ] ],
											toolbar : '#toolbarBzirk',
											onSelect : function(index, record) {
												if ($('#vkgrp').combobox(
														"getValue")) {
													var preCode = $('#vkgrp')
															.combobox(
																	"getValue")
															.substring(1, 3)
															+ (record.vkgrp)
																	.substring(
																			1,
																			3);
												/*	var code = preCode
															+ $("#kunnrCode")
																	.val();
													$("#kunnr").val(code);*/
													$("#codeSales").val(preCode);

												}
												$('#vkbur').combobox(
														'setValue', '');
												$('#vkbur').combobox({
													disabled : false
												});
												loadVkbur(vkbur);
											}
										});
					}
					var vkbur = $('#bzirk').combogrid('getValue');
					if (vkbur == '') {
						$('#vkbur').combobox({
							disabled : true
						});
					}
					// 销售部门
					function loadVkbur(vkbur) {
						$('#vkbur')
								.combobox(
										{
											multiple : false,
											url : appUrl
													+ '/salesMsgAction!getTvkbtJsonListByTvbvk.jspa?vkgrp='
													+ $('#bzirk').combogrid(
															'getValue'),
											valueField : 'vkbur',
											textField : 'bezei'
										});
					}

					// 公司
					$('#bukrs')
							.combobox(
									{
										multiple : false,
										url : appUrl
												+ '/salesMsgAction!getTvkoJsonList.jspa?flag='
												+ 'Z',
										valueField : 'bukrs',
										textField : 'bukrs',
										onSelect : function(record) {
											// $("#vkorg").val(record.vkorg);
										},
										disabled : false,
										onLoadSuccess : function() {
											$('#bukrs').combobox('setValue',
													1000);
										}
									});

					// 交货优先权 :01—KA客户，02-非KA
					$('#lprio').combobox({
						data : [ {
							'id' : '01',
							'txt' : '01-KA客户'
						}, {
							'id' : '02',
							'txt' : '02-非KA'
						} ],
						valueField : 'id',
						textField : 'txt',
						multiple : false,
						onLoadSuccess : function() {
						}
					});

					// 工厂
					$('#werks_1')
							.combobox(
									{
										multiple : false,
										url : appUrl
												+ '/salesMsgAction!getT001wJsonList.jspa',
										valueField : 'werks',
										textField : 'name1',
										onSelect : function(title, index) {
											var werks = title.werks;
											if (werks == '6000') {
												$.messager.alert('Tips',
														"此项不能选,请重新操作！",
														'warning');
												$('#werks_' + areaCount)
														.combobox('setValue',
																'');
												return;
											} else {
												var str = werks.substr(0, 2);
												$('#vsbed_1').combobox(
														'setValue', str);
											}
										}
									});
					// 物料回扣
					if (acountCount > 0) {
						for ( var i = 1; i <= acountCount; i++) {
							$('#bonus_' + i)
									.combobox(
											{
												url : appUrl
														+ '/salesMsgAction!getTvbotJsonList.jspa',
												valueField : 'bonus',
												textField : 'vtext'
											});
						}
					} else {
					$('#bonus_1').combobox({
						multiple : false,
						url : appUrl + '/salesMsgAction!getTvbotJsonList.jspa',
						valueField : 'bonus',
						textField : 'vtext'
					});}
					// 证照
//					$('#tabs2').tabs('disableTab', 0);
//					$("#tabs2").tabs({
//						onSelect : function(title, index) {
//							if (title != '证照上传') {
//								var license = $("input[name='license']");
//								var file = license[i].value;
//								var currTab = $('#tabs').tabs('getTab', title);
//												/*if ($('#kverm').combobox(
//														'getValue') != ''
//														&& $('#kverm')
//																.combobox(
//																		'getValue') != undefined) {*/
////								refreshLicense(currTab);
//												//}
//							}
//						}
//					});
				});

// 刷新证照页面
function refreshLicense(currTab) {
	$('#panel').panel({    
	    href : appUrl
	           + '/kunnrAction!displayLicensePre.jspa?dictTypeName='
		       + $("#kverm").combobox('getValue')    
	});
//	if($('#xmlId').val()==''||$('#xmlId').val()==undefined){
//	$('#tabs2')
//			.tabs(
//					'update',
//					{
//						tab : currTab ? currTab : $('#tabs2').tabs('getTab',
//								'证照上传'),
//						options : {
//							href : appUrl
//									+ '/kunnrAction!displayLicensePre.jspa?dictTypeName='
//									+ $("#kverm").combobox('getValue')
//						}
//					});
//	}else{
//		$('#tabs2')
//		.tabs(
//				'update',
//				{
//					tab : currTab ? currTab : $('#tabs2').tabs('getTab',
//							'证照列表'),
//					options : {
//						href : appUrl
//								+ '/kunnrAction!displayLicensePre.jspa?dictTypeName='
//								+ $("#kverm").combobox('getValue')
//					}
//				});				
//					
//	}
}
// 二级返一级 检查是否有上级经销商
function checkKonzs(count) {
	if (!$('#konzs').combogrid('getValue')) {
		$.messager.alert('Tips', '没有选择上级经销商,因此不能二级返一级', 'warning');
		$("#flag_" + count).attr("checked", false);
	}
}

// 上级经销商查询框
function searcherKonzs(name1) {
	var queryParams = $('#konzs').combogrid("grid").datagrid('options').queryParams;
	queryParams.name1 = encodeURIComponent(name1);
	$('#konzs').combogrid("grid").datagrid('reload');
}

//主户经销商查询框
function searcherKunag(name1) {
	var queryParams = $('#kunag').combogrid("grid").datagrid('options').queryParams;
	queryParams.name1 = encodeURIComponent(name1);
	$('#kunag').combogrid("grid").datagrid('reload');
}

// 上级经销商清除
function clearKonzs() {
	$('#konzs').combogrid('setValue', '');
	for ( var i = 1; i <= acountCount; i++) {
		if (i % 2 != 0) {
			// 控制是否选中
			if (document.getElementById("flag_" + i).checked) {
				document.getElementById("flag_" + i).checked = false;// boolea
				document.getElementById('type2A_' + i).value = '';
				document.getElementById('type2B_' + i).value = '';
				document.getElementById('type2C_' + i).value = '';
			}
		}

	}
	hide();
}

//主户经销商清除
function clearKunag() {
	$('#kunag').combogrid('setValue', '');
	for ( var i = 1; i <= acountCount; i++) {
		if (i % 2 != 0) {
			// 控制是否选中
			if (document.getElementById("flag_" + i).checked) {
				document.getElementById("flag_" + i).checked = false;// boolea
				document.getElementById('type2A_' + i).value = '';
				document.getElementById('type2B_' + i).value = '';
				document.getElementById('type2C_' + i).value = '';
			}
		}
	}
	hide();
}

// 销售组织查询框
function searcherVkorg(vkorg) {
	var queryParams = $('#vkorg').combogrid("grid").datagrid('options').queryParams;
	queryParams.vkorg = encodeURIComponent(vkorg);
	$('#vkorg').combogrid("grid").datagrid('reload');
}
// 大区
function searcherVkgrp(vkgrp) {
	var queryParams = $('#vkgrp').combogrid("grid").datagrid('options').queryParams;
	queryParams.bztxt = encodeURIComponent(vkgrp);
	$('#vkgrp').combogrid("grid").datagrid('reload');
}
// 省
function searcherBzirk(bztxt) {
	var queryParams = $('#bzirk').combogrid("grid").datagrid('options').queryParams;
	queryParams.bezei = encodeURIComponent(bztxt);
	$('#bzirk').combogrid("grid").datagrid('reload');
}

// 城市
function searcherKvgr1(bezei) {
	var queryParams = $('#kvgr1').combogrid("grid").datagrid('options').queryParams;
	queryParams.bezei = encodeURIComponent(bezei);
	$('#kvgr1').combogrid("grid").datagrid('reload');
}

// 区域
function searcherKvgr2(bezei) {
	var queryParams = $('#kvgr2').combogrid("grid").datagrid('options').queryParams;
	queryParams.bezei = encodeURIComponent(bezei);
	$('#kvgr2').combogrid("grid").datagrid('reload');
}

// 公司
function searcherBukrs(butxt) {
	var queryParams = $('#bukrs').combogrid("grid").datagrid('options').queryParams;
	queryParams.butxt = encodeURIComponent(butxt);
	$('#bukrs').combogrid("grid").datagrid('reload');
}

// 品项
function searcherCategories(value) {
	var val = encodeURIComponent(value);
	$('#categories').combogrid({
		url : appUrl + '/goal/goalAction!getMatJsonList.jspa?value=' + val
	});
	$('#categories').combogrid("grid").datagrid('reload');
}
// 对应物料

function searcher1(val) {
	val = encodeURIComponent(val);
	$('#wid_1').combogrid({
		url : appUrl + '/goal/goalAction!getMaterielList.jspa?value=' + val
	});
	$('#wid_1').combogrid("grid").datagrid('reload');

}

function exportModel() {
	var form = window.document.forms[0];
	form.action = appUrl + '/goal/goalAction!exportModel.jspa';
	form.target = "hideFrame";
	form.submit();
}

function importMatterModel() {
	var form = window.document.forms[0];
	form.action = appUrl + '/goal/goalAction!importMatterModel.jspa';
	form.target = "hideFrame";
	form.submit();
}
function importModel() {
	html = '<form id="fileForm" method="post" enctype="multipart/form-data" >'
			+ '&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'
			+ '<table><tr>'
			+ '<td class="head" noWrap>批量导入</td>'
			+ '<td>'
			+ '<input type="file" name="uploadFile" id="uploadFile"  style="width:200px"/>'
			+ '</tb></tr><table></form>';
	openImportDialog('导入目标', html);
}

/* 打开导入项目Excel对话框 */
function openImportDialog(t, html) {
	if ($('#excelDialog').length < 1) {
		$(
				'<div/>',
				{
					id : 'excelDialog',
					title : '选择上传文件',
					html : "<div id='import'>"
							+ "</br>"
							+ "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
							+ html + "</div>" + "</div>"
				}).appendTo('body');
	} else {
		// $('#import').html(html);
	}
	$('#excelDialog')
			.dialog(
					{
						modal : true,
						resizable : false,
						dragable : false,
						closable : false,
						open : function() {
							$('#excelDialog').css('padding', '0.8em');
							$('#excelDialog .ui-accordion-content').css(
									'padding', '0.4em').height(
									$('#excelDialog').height() - 100);
						},
						buttons : [
								{
									text : '确定',
									handler : function() {
										var file = document
												.getElementById('uploadFile').value;
										if (/^.+\.(csv|CSV)$/.test(file)) {
											$.messager.progress();
											var form = document
													.getElementById('fileForm');
											form.action = appUrl
													+ "/kunnrAction!importModel.jspa?orgId="
													+ $('#orgId').val()
													+ '&kunnrCode='
													+ $('#kunnrCode').val();
											// "eventId="+processInstanceId;
											form.target = "hideFrame";
											form.submit();
										} else {
											$.messager.alert("提示", "请导入csv文件");
										}

									}
								}, {
									text : '取消',
									handler : function() {
										$('#excelDialog').dialog('close');
									}
								} ],

						width : document.documentElement.clientWidth * 0.28,
						height : document.documentElement.clientHeight * 0.50
					});
}

function importSalesGoalModel() {
	html = '<form id="fileForm" method="post" enctype="multipart/form-data" >'
			+ '&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'
			+ '<table><tr>'
			+ '<td class="head" noWrap>批量导入</td>'
			+ '<td>'
			+ '<input type="file" name="uploadFile" id="uploadFile"  style="width:200px"/>'
			+ '</tb></tr><table></form>';
	openSalesGoalImportDialog('导入目标', html);
}

/* 打开导入项目Excel对话框 */
function openSalesGoalImportDialog(t, html) {
	if ($('#excelDialog').length < 1) {
		$(
				'<div/>',
				{
					id : 'excelDialog',
					title : '选择上传文件',
					html : "<div id='import'>"
							+ "</br>"
							+ "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
							+ html + "</div>" + "</div>"
				}).appendTo('body');
	} else {
		// $('#import').html(html);
	}
	$('#excelDialog')
			.dialog(
					{
						modal : true,
						resizable : false,
						dragable : false,
						closable : false,
						open : function() {
							$('#excelDialog').css('padding', '0.8em');
							$('#excelDialog .ui-accordion-content').css(
									'padding', '0.4em').height(
									$('#excelDialog').height() - 100);
						},
						buttons : [
								{
									text : '确定',
									handler : function() {
										var file = document
												.getElementById('uploadFile').value;
										if (/^.+\.(csv|CSV)$/.test(file)) {
											$.messager.progress();
											var form = document
													.getElementById('fileForm');
											form.action = appUrl
													+ "/stockReportAction!importSalesGoalCGApplyCsv.jspa?orgId="
													+ $('#orgId').val()
													+ '&kunnr='
													+ $('#kunnrCode').val();
											// "eventId="+processInstanceId;
											form.target = "hideFrame";
											form.submit();
										} else {
											$.messager.alert("提示", "请导入csv文件");
										}

									}
								}, {
									text : '取消',
									handler : function() {
										$('#excelDialog').dialog('close');
									}
								} ],

						width : document.documentElement.clientWidth * 0.28,
						height : document.documentElement.clientHeight * 0.50
					});
}

// 提交申请
function submit() {
	// 基本资料
	if ($('#brands').combobox('getValues').toString()=='') {
		$.messager.alert('Tips', "经营品牌未填!", 'error');
		return;
	}
	if (!$("#name1").validatebox('isValid')) {
		$.messager.alert('Tips', "客户名称未填或者格式不正确！", 'error');
		return;
	}
	if (!$("#channelId").combobox('isValid')) {
		$.messager.alert('Tips', "客户分类未填选择！", 'error');
		return;
	}
	if (!$("#name3").validatebox('isValid')) {
		$.messager.alert('Tips', "法人代表未填！", 'error');
		return;
	}
	/*
	 * if (!$("#age").numberbox('isValid')) { $.messager.alert('Tips',
	 * "法人年龄未填！", 'error'); return; }
	 */
	if (!$("#mobNumber").numberbox('isValid')) {
		$.messager.alert('Tips', "法人手机未填或者格式不正确！", 'error');
		return;
	}
	if (!$("#natureEnterprise").validatebox('isValid')) {
		$.messager.alert('Tips', "企业性质未填或者格式不正确！", 'error');
		return;
	}
	if (!$("#healthNumber").validatebox('isValid')) {
		$.messager.alert('Tips', "食品许可证未填或者格式不正确！", 'error');
		return;
	}
	if (!$("#businessLicense").validatebox('isValid')) {
		$.messager.alert('Tips', "营业执照未填或者格式不正确！", 'error');
		return;
	}

	/*if (!$("#telNumber").validatebox('isValid')) {
		$.messager.alert('Tips', "公司联系电话未填或者格式不正确！", 'error');
		return;
	}*/
	if (!$("#faxNumber").validatebox('isValid')) {
		$.messager.alert('Tips', "传真未填或者格式不正确！", 'error');
		return;
	}
	if (!$("#warehouseArea").validatebox('isValid')) {
		$.messager.alert('Tips', "仓库面积未填或者格式不正确！", 'error');
		return;
	}
	var region4 = $('#region4').combobox('getValue');
	if (region4 == '') {
		$.messager.alert('Tips', "办公所在地未选择！", 'error');
		return;
	}
	if (!$("#street1").validatebox('isValid')) {
		$.messager.alert('Tips', "具体参考位置未填或者格式不正确！", 'error');
		return;
	}
	if (!$("#createReason").validatebox('isValid')) {
		$.messager.alert('Tips', "开户原因未填或者格式不正确！", 'error');
		return;
	}
	// 财务信息验证
	if (!$("#bank").validatebox('isValid')) {
		$.messager.alert('Tips', "开户银行未填或者格式不正确！", 'error');
		return;
	}
	if (!$("#bankAccount").validatebox('isValid')) {
		$.messager.alert('Tips', "银行账号未填或者格式不正确！", 'error');
		return;
	}
	var len1=$("#bank").val().length;
	var len2=$("#bankAccount").val().length;
	var len=len1+len2;
	if(len>48){
		$.messager.alert('Tips', "开户银行与银行账号长度之和长度必须在48以内！", 'error');
		return;
	}
	if (!$("#stceg").validatebox('isValid')) {
		$.messager.alert('Tips', "税务登记号未填或者格式不正确！", 'error');
		return;
	}
	if (!$("#kpPhone").validatebox('isValid')) {
		$.messager.alert('Tips', "开票电话未填或者格式不正确！", 'error');
		return;
	}
	if (!$("#fpRecipient").validatebox('isValid')) {
		$.messager.alert('Tips', "发票收件人未填或者格式不正确！", 'error');
		return;
	}
	if (!$("#fpContactPhone").validatebox('isValid')) {
		$.messager.alert('Tips', "发票联系电话未填或者格式不正确！", 'error');
		return;
	}
	if (!$("#zcAddress").validatebox('isValid')) {
		$.messager.alert('Tips', "注册地址未填或者格式不正确！", 'error');
		return;
	}
	if (!$("#fpAddress").validatebox('isValid')) {
		$.messager.alert('Tips', "发票寄送地址未填或者格式不正确！", 'error');
		return;
	}
	// 详细资料
	// 详细资料里的必填数据验证
	if ($("#businessManager").val() == ''
			&& $("#businessCompetent").val() == '') {
		$.messager.alert('Tips', "城市经理和主管至少填写一项！", 'error');
		return;
	}

	var vkgrp = $('#vkgrp').combobox('getValue');
	var bzirk = $('#bzirk').combobox('getValue');
	var vkbur = $('#vkbur').combobox('getValue');
	if (!vkgrp) {
		$.messager.alert('Tips', '销售视图里的销售大区未填！', 'warning');
		return;
	} else if (!bzirk) {
		$.messager.alert('Tips', '销售视图里的销售省份未填！', 'warning');
		return;
	} else if (!vkbur) {
		$.messager.alert('Tips', '销售视图里的城市办未填！', 'warning');
		return;
	}
	var lprio = $('#lprio').combobox('getValue');
	if (!lprio) {
		$.messager.alert('Tips', '销售视图里的交货优先权未填！', 'warning');
		return;
	}

	// 特约经销商必须选上级经销商
	if ($('#channelId').combobox('getValue')) {
		var selectText = $('#channelName').val();
		if (selectText == '特约经销商'
				&& ($('#konzs').combogrid('getValue') == null
						|| $('#konzs').combogrid('getValue') == '' || undefined == $(
						'#konzs').combogrid('getValue'))) {
			$.messager.alert('Tips', '客户为特约经销商,请选择选上级经销商,并维护二级返一级的商业折扣!',
					'warning');
			return;
		}
	}

	array2json();
	var matchFlag = true;
	var isValid = $('#ff').form('validate');
	// 乡镇未选
	/* var region4 = $('#region4').combobox('getValue'); */
	var goalFlag = true;
//	var goalFlag = false;
//	if ($('#importFlag').val() == 'Y'||($('#bCustomerTargetListSize').val()>0)) {
//		goalFlag = true;
//	} else {
//		$.messager.alert('Tips', '预算目标量未导入!', 'warning');
//		return;
//	}
	if ($('#street').val() == '' || undefined == $('#street').val()) {
		$.messager.alert('Tips', '请选择收货行政地址!', 'warning');
		return;
	}
	//取消导入目标量 by cg.jiang21061129
	/**
	if($('#importGoalSalesFlag').val() != 'Y'){
		$.messager.alert('Tips', '分销目标量未导入!', 'warning');
		return;
	}
*/
	for ( var i = 0; i < kunnrAddressList.length; i++) {
		if (kunnrAddressList[i].xzAddress == '') {
			var n = i + 2;
			$.messager.alert('Tips', '第' + n + '行请选择送达方行政地址!', 'warning');
			return;
		}
	}
	if (isValid) {
		$.messager
				.confirm(
						'Confirm',
						'请核对经分销商资料,提交?',
						function(r) {
							if (r) {
								var text=$('#name1').val()+'_经销商开户申请提报';
								$('#title').val(text);
								var license = $("input[name='license']");
								if(license.length==0){
									$.messager.alert('Tips', "证照未上传！",
									'error');
							         return;
								}else{
								for ( var i = 0; i < license.length; i++) {
									var file = license[i].value;
									if (file) {
										matchFlag = matchImage(file,
												"证照格式错误,请上传正确的图片格式!");
										if (!matchFlag)
											break;
									} else {
										var n=i+1;
										if($('#xmlId').val()==''||($('#oldLice_'+n).val()==''||undefined==$('#oldLice_'+n).val())){
										$.messager.alert('Tips', "证照未上传！",
												'error');
										return;
										}
									}
								}}
								if (matchFlag) {
									// 验证经销商目标量有效性，不能超过组织目标量
									// var goalFlag = true;

									/*
									 * var str = ''; $ .ajax({ type : "post",
									 * async : false, url : appUrl +
									 * "/kunnrAction!checkKunnrGoal.jspa", data : {
									 * bCustomerTargetList : $(
									 * "#bCustomerTargetList") .val(), orgId :
									 * $('#orgId').val() }, success :
									 * function(resultJson) { if ('success' !=
									 * resultJson) { goalFlag = false; str =
									 * resultJson; } }
									 * 
									 * });
									 */
									if (goalFlag) {
										// 下个处理人
										$
												.ajax({
													type : "post",
													async : false,
													url : appUrl
															+ "/kunnrAction!selectNexUser.jspa?time="
															+ new Date(),
													data : {
														userId : $("#userId")
																.val()
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
																			if (!$(
																					"#nextUsers")
																					.val()) {
																				$.messager
																						.alert(
																								'Tips',
																								"没有下个处理人，请维护！",
																								'error');
																				return;
																			}
																			// 下个处理人ID
																			$(
																					"#nextUserId")
																					.val(
																							$(
																									"#nextUsers")
																									.val());
																			$.messager
																					.progress();
																			handelAction('kunnrApply');
																		}
																	},
																	{
																		text : '取消',
																		handler : function() {
																			handelAction('cancelNextUser');
																		}
																	} ]
														});
									} else {
										$.messager
												.alert('Tips', str, 'warning');
										return;
									}
								}
							}
						});
	}
}
function save() {
	array2json();
	var matchFlag=true;
	var license = $("input[name='license']");
	if(license.length==0){
		$.messager.alert('Tips', "请上传证照！",
		'error');
        return;
	}else{
	for ( var i = 0; i < license.length; i++) {
		var file = license[i].value;
		if (file) {
			matchFlag = matchImage(file,
					"证照格式错误,请上传正确的图片格式!");
			if (!matchFlag)
				break;
		} else {
			var n=i+1;
			if($('#xmlId').val()==''||($('#oldLice_'+n).val()==''||undefined==$('#oldLice_'+n).val())){
			$.messager.alert('Tips', "证照未上传！",
					'error');
			return;
			}
		}
	}}
	var form = window.document.forms[0];
	form.action = appUrl + "/kunnrAction!kunnrApplySave.jspa";
	form.target = "hideFrame";
	form.submit();
}
// 取消
function cancel() {
	self.location.reload(true);
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

// 添加地址一行
function addAddress() {
	addressCount++;
	var html = '';
	html += '<tr id="addressTr_' + addressCount + '">';
	html += '<td class="head" noWrap><a href="javascript:removeAddress('
			+ addressCount
			+ ')"><img align="absMiddle" src="'
			+ imgUrl
			+ '/images/actions/action_del.png"/></a>&nbsp;&nbsp;&nbsp;<font color="red">*</font>(送达方)地址:</td>';
	html += '<td><a class="easyui-linkbutton" style="width:44px"  href="javascript:selectAddress('
			+ addressCount + ')">选择</a></td>';
	html += '<td>'
			+ '<input type="hidden" id="sdProvince_'
			+ addressCount
			+ '"><input type="hidden" id="sdCity_'
			+ addressCount
			+ '" ><input type="hidden" id="sdArea_'
			+ addressCount
			+ '" ><input type="hidden" id="sdTown_'
			+ addressCount
			+ '" >'
			+ '<input class="easyui-validatebox" style="width:300px" validType="length[0,50]" id="address_'
			+ addressCount
			+ '" readonly></td><td><input class="easyui-validatebox" id="address01_'
			+ addressCount + '" validType="length[0,50]" style="width:300px"></td>';
	html += '<td class="head" noWrap>收货人:';
	html += '<input class="easyui-validatebox" style="width:80px" validType="length[0,20]" id="name_'
			+ addressCount + '">';
	html += '<font class="head" noWrap>收货联系电话:';
	html += '<input class="easyui-validatebox" style="width:90px" validType="telephone" id="telephone_'
			+ addressCount + '"></font></td>';
	html += '<td class="head" noWrap>收货联系手机:</td>';
	html += '<td><input class="easyui-numberbox" style="width:90px" validType="length[11,11]" invalidMessage="请输入正确的11位手机号" id="mobile_'
			+ addressCount + '"></td>';
	html += '<td class="head" noWrap>最大可通行车型:</td>';
	html += '<td><input class="easyui-combobox" id="maximum_'
			+ addressCount
			+ '" style="width:80px"  validType="length[0,20]" data-options="required:false,editable:false"></td>';
	html += '</tr>';
	$('#addressTable').append(html);
	$.parser.parse($('#addressTr_' + addressCount));
	$('#maximum_' + addressCount).combobox(
			{
				valueField : 'itemName',
				textField : 'itemValue',
				url : appUrl
						+ '/kunnrAction!getCrmDictList.jspa?dictTypeValue='
						+ 'carType'/*
									 * , onLoadSuccess : function() {
									 * $('#maximum_' +
									 * addressCount).combobox('setValue', 'C0'); }
									 */
			});
}
// 刪除地址一行
function removeAddress(count) {
	if ($("#addressTr_" + count)) {
		$("#addressTr_" + count).remove();
	}
}

// 添加品牌一行
function addBrand() {
	brandCount++;
	var html = '';
	html += '<tr id="brandTr_' + brandCount + '">';
	html += '<td class="head" noWrap><a href="javascript:removeBrand('
			+ brandCount
			+ ')"><img align="absMiddle" src="'
			+ imgUrl
			+ '/images/actions/action_del.png"/></a>&nbsp;&nbsp;&nbsp;品牌名称:</td>';
	html += '<td><input class="easyui-validatebox" id="brand_' + brandCount
			+ '"></td>';
	html += '<td class="head" noWrap>销售额:</td>';
	html += '<td colspan="3"><input class="easyui-validatebox" id="sales_'
			+ brandCount + '">(万元)</td>';
	html += '</tr>';
	$('#brandTable').append(html);
}

// 刪除品牌一行
function removeBrand(count) {
	if ($("#brandTr_" + count)) {
		$("#brandTr_" + count).remove();
	}
}

// 添加折扣一行
function addAcount() {
	acountCount++;
	var html = '';
	html += '<tr id="salesAcountTr_' + acountCount + '">';
	html += '<td class="head" noWrap><a href="javascript:removeAcount('
			+ acountCount
			+ ')"><img align="absMiddle" src="'
			+ imgUrl
			+ '/images/actions/action_del.png"/></a>&nbsp;<font color="red">*</font>&nbsp;物料回扣组:';
	html += '<input class="easyui-combobox" id="bonus_' + acountCount
			+ '"></td>';
	html += '<td class="head" noWrap>';
	html += '月返(元):<input class="easyui-numberbox" style="width:60px" id="typeA_'
			+ acountCount + '">';
	html += '&nbsp;季返(元):<input class="easyui-numberbox" style="width:60px" id="typeB_'
			+ acountCount + '">';
	html += '&nbsp;年返(元):<input class="easyui-numberbox" style="width:60px" id="typeC_'
			+ acountCount
			+ '">'
			+ '&nbsp;协议价(元):<input class="easyui-numberbox" id="typeMoney_'
			+ acountCount + '"'
			/*
			 * +' onchange="changeRow(' + acountCount + ')" '
			 */
			+ 'style="width:60px"></td>';

	if ($('#konzs').combogrid('getValue')) {
		html += '<td class="head" noWrap><div class="type2J">';
		html += '<label><font color="red">二级返一级</font><input type="checkbox" id="flag_'
				+ acountCount
				+ '" value="Y" onclick="checkKonzs('
				+ acountCount + ')"></label>';
		html += '&nbsp;月返(元):<input class="easyui-numberbox" style="width:60px" id="type2A_'
				+ acountCount
				+ '" onclick="checkKonzs('
				+ acountCount
				+ ')">';
		html += '&nbsp;季返(元):<input class="easyui-numberbox" style="width:60px" id="type2B_'
				+ acountCount
				+ '" onclick="checkKonzs('
				+ acountCount
				+ ')">';
		html += '&nbsp;年返(元):<input class="easyui-numberbox" style="width:60px" id="type2C_'
				+ acountCount
				+ '" onclick="checkKonzs('
				+ acountCount
				+ ')">'
				+ '</div></td>';

	} else {
		html += '<td class="head" noWrap><div class="type2J" style="display:none;">';
		html += '<label><font color="red">二级返一级</font><input type="checkbox" id="flag_'
				+ acountCount
				+ '" value="Y" onclick="checkKonzs('
				+ acountCount + ')"></label>';
		html += '&nbsp;月返(元):<input class="easyui-numberbox" style="width:60px" id="type2A_'
				+ acountCount
				+ '" onclick="checkKonzs('
				+ acountCount
				+ ')">';
		html += '&nbsp;季返(元):<input class="easyui-numberbox" style="width:60px" id="type2B_'
				+ acountCount
				+ '" onclick="checkKonzs('
				+ acountCount
				+ ')">';
		html += '&nbsp;年返(元):<input class="easyui-numberbox" style="width:60px" id="type2C_'
				+ acountCount
				+ '" onclick="checkKonzs('
				+ acountCount
				+ ')">'
				+ '</div></td>';
	}
	html += '<td width="20%">&nbsp;</td><td width="20%">&nbsp;</td><td width="20%">&nbsp;</td>';
	html += '</tr>';
	var trCount = acountCount;
	$('#salesAcountTable').append(html);
	$.parser.parse($('#salesAcountTr_' + acountCount));

	// 物料回扣

	$('#bonus_' + trCount).combobox({
		url : appUrl + '/salesMsgAction!getTvbotJsonList.jspa',
		valueField : 'bonus',
		textField : 'vtext'
	});
}
/*
 * function change() { if ($('#konzs').combogrid('getValue')) { if
 * ($('#typeMoney_1').val()) { var num = $('#typeMoney_1').val() * 1;
 * $('#type2Money_1').numberbox('setValue', num); } else {
 * $('#typeMoney_1').numberbox('setValue', 0); var num = $('#typeMoney_1').val() *
 * 1; $('#type2Money_1').numberbox('setValue', num); } } }
 */
/*
 * function changeRow(count) { if ($('#konzs').combogrid('getValue')) { if
 * ($('#typeMoney_' + count).val()) { var num = $('#typeMoney_' + count).val() *
 * 1; $('#type2Money_' + count).numberbox('setValue', num); } else {
 * $('#typeMoney_' + count).numberbox('setValue', 0); var num = $('#typeMoney_' +
 * count).val() * 1; $('#type2Money_' + count).numberbox('setValue', num); } } }
 */
// 删除折扣一行
function removeAcount(count) {
	if ($("#salesAcountTr_" + count)) {
		// var n = count + 1;
		$("#salesAcountTr_" + count).remove();
		// $("#salesAcountTr_" + n).remove();
	}
}
// 添加销售范围一行
function addArea(obj, areaCount) {
	// areaCount++;
	var html = '';
	html += '<tr id="saleAreaTr_' + areaCount + '">';
	// html += '<td class="head" noWrap></td>'; //&nbsp;&nbsp;销售范围: <a
	// class="easyui-linkbutton" href="javascript:selectTvkbz('
	// + areaCount + ')">选择</a>
	html += '<td class="head" noWrap><a href="javascript:removeArea('
			+ areaCount + ')"><img align="absMiddle" src="' + imgUrl
			+ '/images/actions/action_del.png"/></a> &nbsp;&nbsp;销售组织:</td>';
	html += '<td><input type="hidden" id="vkorg_'
			+ areaCount
			+ '" value="'
			+ obj.vkorg
			+ '" style="width:40px" readonly ></input>'
			+ '<input class="easyui-validatebox" id="vkorgTxt_'
			+ areaCount
			+ '" value="'
			+ obj.vkorgTxt
			+ '" data-options="required:true" style="width:40px" readonly ></input></td>';
	html += '<td class="head" noWrap>分销渠道:</td>';
	html += '<td><input  type="hidden" id="vtweg_' + areaCount + '" value="'
			+ obj.vtweg + '" readonly>'
			+ '<input  class="easyui-validatebox" id="vtwegTxt_' + areaCount
			+ '"  value="' + obj.vtwegTxt
			+ '" data-options="required:true" readonly></td>';
	html += '<td class="head" noWrap>产品组:</td>';
	html += ' <td><input  type="hidden" id="spart_' + areaCount + '" value="'
			+ obj.spart + '" readonly></input>'
			+ '<input  class="easyui-validatebox" id="spartTxt_' + areaCount
			+ '" value="' + obj.spartTxt
			+ '" data-options="required:true" readonly></input></td>';
	html += '<td class="head" noWrap>交货工厂:</td>';
	html += '<td><input class="easyui-combobox" id="werks_' + areaCount
			+ '"  data-options="required:true,editable:false"></td>';
	html += '<td class="head" noWrap>装运条件:</td>';
	html += '<td><input class="easyui-combobox" id="vsbed_'
			+ areaCount
			+ '" data-options="required:true" style="width:60px" validType="length[2,2]"></td>';
	html += '</tr>';
	$('#saleAreaTable').append(html);
	$.parser.parse($('#saleAreaTr_' + areaCount));
	// 工厂
	var areaCounts = areaCount;
	$('#werks_' + areaCount).combobox({
		url : appUrl + '/salesMsgAction!getT001wJsonList.jspa',
		valueField : 'werks',
		textField : 'name1',
		onSelect : function(title, index) {
			var werks = title.werks;
			if (werks == '6000') {
				$.messager.alert('Tips', "此项不能选,请重新操作！", 'warning');
				$('#werks_' + areaCounts).combobox('setValue', '');
				$('#vsbed_' + areaCounts).combobox('setValue', '');
				return;
			} else {
				var str = werks.substr(0, 2);
				$('#vsbed_' + areaCounts).combobox('setValue', str);
			}
		}
	});
	// 装运条件
	$('#vsbed_' + areaCounts).combobox(
			{
				valueField : 'itemName',
				textField : 'itemValue',
				url : appUrl
						+ '/kunnrAction!getCrmDictList.jspa?dictTypeValue='
						+ 'zyc'
			});
}
// 刪除销售范围一行
function removeArea(count) {
	if ($("#saleAreaTr_" + count)) {
		$("#saleAreaTr_" + count).remove();
	}
}

// ------------------------------------------

function saveGoal() {

}
function deleteIndex(rec) {
	$('#goalTable').datagrid('deleteRow', rec);
}
// ------------------------------------------
// 添加目标量一行
function addGoal() {
	goalCount++;
	var html = '';
	html += '<tr id="goalTr_' + goalCount + '">';
	html += '<td class="head" noWrap>&nbsp;&nbsp;<a href="javascript:removeGoal('
			+ goalCount
			+ ')"><img align="absMiddle" src="'
			+ imgUrl
			+ '/images/actions/action_del.png"/></a>&nbsp;&nbsp;年份 </td>';
	html += ' <td><input class="easyui-combobox" id="year_'
			+ goalCount
			+ '" style="width:60px" data-options="editable:false" readonly>'
			+ '<font class="head">&nbsp;/&nbsp;月份 <input class="easyui-combobox" id="month_'
			+ goalCount
			+ '" style="width:60px" data-options="editable:false" name="theMonth"></td>';
	html += ' <td class="head" noWrap>对应物料</td>';
	html += '<td><input class="easyui-cmobogrid" id="wid_' + goalCount
			+ '"/></td>' + ' <div id="toolbar_' + goalCount
			+ '" class="datagrid-toolbar" >' + ' <input id="search_'
			+ goalCount
			+ '" class="easyui-searchbox" style="width:300px"></input>'
			+ '</div>';
	html += '<td class="head" noWrap>目标箱数(标箱)</td>';
	html += '<td><input class="easyui-numberbox" style="width:60px" id="box_'
			+ goalCount + '"  data-options="precision:2,min:0,fix"""/>'
			+ '</td>';
	html += ' <td class="head" noWrap>目标金额(元)</td>';
	html += '<td> <input class="easyui-numberbox" id="targetNum_' + goalCount
			+ '" data-options="precision:2,min:0,fix:"""/></td>';
	html += '</tr>';
	$('#goalTable').append(html);
	$.parser.parse($('#goalTr_' + areaCount));
	// 月份
	$('#month_' + goalCount).combobox(
			{
				data : month,
				valueField : 'txt',
				textField : 'value',
				onLoadSuccess : function() {
					$('#month_' + goalCount).combobox("setValue",
							new Date().getMonth() + 1);
				}
			});
	// 年份
	$('#year_' + goalCount).combobox(
			{
				url : appUrl + '/goalAction!getDictTypeList.jspa',
				textField : 'itemName',
				valueField : 'itemName',
				onLoadSuccess : function() {
					$('#year_' + goalCount).combobox("setValue",
							new Date().getFullYear());
				}
			});

	$('#search_' + goalCount).searchbox({
		searcher : function(value, name) {
			search(value, goalCount);
		},
		prompt : '请填写物料号或物料名'
	});

	// 对应物料
	$('#wid_' + goalCount).combogrid({
		panelWidth : 400,
		panelHight : 600,
		idField : 'mvgr1',
		textField : 'bezei',
		pagination : true,// 是否分页 collapsible : false,//
		// 是否可折叠的
		method : 'post',
		url : appUrl + '/goal/goalAction!getMaterielList.jspa',
		columns : [ [ {
			field : 'ck',
			checkbox : true,
			hidden : true
		}, {
			field : 'mvgr1',
			title : '物料组',
			align : 'center',
			width : 120
		}, {
			field : 'bezei',
			title : '物料组描述',
			align : 'center',
			width : 200
		} ] ],
		toolbar : '#toolbar_' + goalCount
	});

}
// 对应物料toolbar

function search(value, index) {
	value = encodeURIComponent(value);
	$('#wid_' + index).combogrid({
		url : appUrl + '/goal/goalAction!getMaterielList.jspa?value=' + value
	});
	$('#wid_' + index).combogrid("grid").datagrid('reload');

}
// 刪除目标量一行
function removeGoal(count) {
	if ($("#goalTr_" + count)) {
		$("#goalTr_" + count).remove();
	}
}
// 地址品牌array->json
function array2json() {
	kunnrAddressList = [];
	var kunnrBrandList = [];
	var kunnrAcountList = [];
	// kunnrSalesAreaList = [];
	var bCustomerTargetList = [];
	for ( var i = 1; i <= addressCount; i++) {
		if (document.getElementById("addressTr_" + i)) {
			kunnrAddressList.push({
				"province" : $("#sdProvince_" + i).val(),
				"city" : $("#sdCity_" + i).val(),
				"area" : $("#sdArea_" + i).val(),
				"town" : $("#sdTown_" + i).val(),
				"xzAddress" : $("#address_" + i).val(),
				"address" : $("#address01_" + i).val(),
				"name" : $("#name_" + i).val(),
				"telephone" : $("#telephone_" + i).val(),
				"mobile" : $("#mobile_" + i).val(),
				"maximum" : $("#maximum_" + i).combobox('getValue')
			});
		}
	}
	for ( var i = 1; i <= brandCount; i++) {
		if (document.getElementById("brandTr_" + i)) {
			kunnrBrandList.push({
				"brand" : $("#brand_" + i).val(),
				"sales" : $("#sales_" + i).val()
			});
		}
	}
	for ( var i = 1; i <= acountCount; i++) {
		var flag;
		var typeA = '';
		var typeB = '';
		var typeC = '';
		var typeMoney = '';
		var type2A = '';
		var type2B = '';
		var type2C = '';
		var bonus = '';
		var vtext = '';
		if (document.getElementById("salesAcountTr_" + i)) {
			if ($("#bonus_" + i).combobox("getValue")) {
				if ($("#flag_" + i).attr("checked")) {
					flag = 'Y';
				} else {
					flag = 'N';
				}
				typeA = $("#typeA_" + i).val();
				typeB = $("#typeB_" + i).val();
				typeC = $("#typeC_" + i).val();
				type2A = $("#type2A_" + i).val();
				type2B = $("#type2B_" + i).val();
				type2C = $("#type2C_" + i).val();
				bonus = $("#bonus_" + i).combobox("getValue");
				vtext = $("#vtext_" + i).val();
				typeMoney = $("#typeMoney_" + i).val();
				kunnrAcountList.push({
					"typeA" : typeA,
					"typeB" : typeB,
					"typeC" : typeC,
					"typeMoney" : typeMoney,
					"type2A" : type2A,
					"type2B" : type2B,
					"type2C" : type2C,
					"bonus" : bonus,
					"vtext" : vtext,
					"flag" : flag
				});
			}
		}
	}

	
	$("#kunnrAddressList").val($.toJSON(kunnrAddressList));
	$("#kunnrAcountList").val($.toJSON(kunnrAcountList));

}

// 返回信息
function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	$.messager.progress('close');
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult == "ok") {
		$('#nextUserDialog').dialog('close');
	} else if (successResult == "导入成功!") {
		/*
		 * $('#goalTable') .datagrid({data:$('#bCustomerTargetList').val()});
		 */
		$.messager.alert('Tips', successResult, 'info', function() {
			$('#excelDialog').dialog('close');
			$('.goalFlag').show();
			$('#importFlag').val('Y');
		});
	} else if (successResult == "分销目标量导入成功!") {
		/*
		 * $('#goalTable') .datagrid({data:$('#bCustomerTargetList').val()});
		 */
		$.messager.alert('Tips', successResult, 'info', function() {
			$('#excelDialog').dialog('close');
			$('#importGoalSalesFlag').val('Y');
		});
	} else if (successResult) {
		$.messager.alert('Tips', successResult, 'info', function() {
			window.location.reload();
			if($('#xmlId').val()!=''&&undefined!=$('#xmlId').val()){
				window.close();
				window.parent.opener.search();
			}
		});
	}
}

// 初始化时一定要隐一列
function initHide() {
	$('.type2J').hide();
}

// 隐列
function hide() {
	$('.type2J').hide();
}

// 显列
function show() {
	$('.type2J').show();
}
/**
 * 打开组织树
 */
function selectOrgTree() {
	initMaintWindow4Post('选择组织', '/customerAction!orgTreePage.jspa', 400, 460);
}
/**
 * 组织树选组织返回到输入框
 * 
 * @param selectedId
 * @param selectedName
 */
function returnValue(selectedId, selectedName) {
	document.getElementById('orgId').value = selectedId;
	document.getElementById('orgName').value = selectedName;
	checkOnly();
	$('#konzs').combogrid(
			{
				url : appUrl + '/kunnrAction!kunnrSearch.jspa?orgId='
						+ $('#orgId').val() + '&bhxjFlag=' + 'C'
			});
	$('#kunag').combogrid(
			{
				url : appUrl + '/kunnrAction!kunnrSearch.jspa?orgId='
						+ $('#orgId').val() + '&bhxjFlag=' + 'C'
			});
}

function checkOnly(){
	if($('#orgId').val()==null||$('#orgId').val()==''){
		return;
	}
	$.ajax({
		type : "post",
		async : false,
		url : appUrl + '/kunnrAction!checkonly.jspa?&random=' + Math.random(),
		data : {
			orgId : $('#orgId').val()
		},
		success : function(obj) {
			if (obj.result == null ||obj.result=='' ) {
				
			} else{
				$.messager.alert('Tips', obj.result, 'warning');
				document.getElementById('orgName').value = '';
			}
		}

	});


}
/**
 * 组织树选择完之后关闭
 */
function closeOrgTree() {
	$("#nextUserDialog").window('close');
}
/*
 * 打开人员选择树
 */
function selectManager() {
	userRole = 'M';
	initMaintWindow4Post('选择城市经理', '/batChangeAction!selectOrgTreeUser.jspa',
			550, 350);
}
/*
 * 打开人员选择树
 */
function selectDirectory() {
	userRole = 'D';
	initMaintWindow4Post('选择客户经理', '/batChangeAction!selectOrgTreeUser.jspa',
			550, 350);
}
/*
 * 城市经理、主管选择返回值
 */
function returnUser(userId, userName,moblie) {
	if (userRole == 'M') {
		document.getElementById('businessManagerId').value = userId;
		document.getElementById('businessManager').value = userName;
		$('#managerMobile').numberbox('setValue',moblie);
		
	}
	if (userRole == 'D') {
		document.getElementById('businessCompetentId').value = userId;
		document.getElementById('businessCompetent').value = userName;
		$('#competentMobile').numberbox('setValue',moblie);
		
	}
}
/*
 * 打开销售范围
 */
function selectTvkbz(num) {
	initMaintWindow4Post('选择销售范围', '/salesMsgAction!toSearchTvkbz.jspa?vkorg='
			+ $('#bukrs').combobox('getValue') + '&num=' + num, 700, 350);
}
/*
 * 销售范围选择返回值 vkorg销售组织,vtweg销售渠道,spart产品组,vkbur销售部门
 */
function returnTvkbz(vkorg, vkorgTxt, vtweg, vtwegTxt, spart, spartTxt, vkbur,
		vkburTxt, num) {
	document.getElementById('vkorg_' + num).value = vkorg;
	document.getElementById('vtweg_' + num).value = vtweg;
	document.getElementById('spart_' + num).value = spart;
	//
	// document.getElementById('bukrs').value

	document.getElementById('vkorgTxt_' + num).value = vkorgTxt;
	document.getElementById('vtwegTxt_' + num).value = vtwegTxt;
	document.getElementById('spartTxt_' + num).value = spartTxt;
}

function returnTvkbzList(list) {
	for ( var i = 0; i < list.length; i++) {
		addArea(list[i]);
	}
}

/*
 * 打开对应物料
 */
function selectGoalWid(num) {
	initMaintWindow4Post('选择物料', '/kunnrAction!searchGoalWid.jspa?num=' + num,
			700, 550);
}
/*
 * 对应物料选择返回值matnr物料编号，maktx短文本描述，mvgr1预算口径代码bezei预算口径
 */
function returnGoalWid(matnr, maktx, mvgr1, bezei, num) {
	document.getElementById('wid_' + num).value = matnr;
	document.getElementById('wid_name_' + num).value = maktx;
}

/*
 * 选择地址
 */
function selectAddress(num) {
	if (undefined == num) {
		initMaintWindow4Post('选择地址', '/kunnrAction!searchAddress.jspa', 750,
				600);
	} else {
		initMaintWindow4Post('选择地址', '/kunnrAction!searchAddress.jspa?num='
				+ num, 750, 600);
	}
}
/*
 * 地址选择返回值zwl01t省，zwl02t市，zwl03t区zwl04t镇
 */

function returnAddress(zwl01, zwl02, zwl03, zwl04, zwl01t, zwl02t, zwl03t,
		zwl04t, num) {
	if (undefined == num) {
		document.getElementById('shProvince').value = zwl01;
		document.getElementById('shCity').value = zwl02;
		document.getElementById('shArea').value = zwl03;
		document.getElementById('shTown').value = zwl04;
		document.getElementById('street').value = zwl01t + zwl02t + zwl03t
				+ zwl04t;
	} else {
		document.getElementById('sdProvince_' + num).value = zwl01;
		document.getElementById('sdCity_' + num).value = zwl02;
		document.getElementById('sdArea_' + num).value = zwl03;
		document.getElementById('sdTown_' + num).value = zwl04;
		document.getElementById('address_' + num).value = zwl01t + zwl02t
				+ zwl03t + zwl04t;
	}
}
/**
 * 弹出窗口
 * 
 * @param title
 * @param url
 * @param width
 * @param height
 */
function initMaintWindow4Post(title, url, width, height) {
	var url = appUrl + url;
	var WWidth = width;
	var WHeight = height;
	var $win = $("#nextUserDialog")
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
						minimizable : false,//
						maximizable : false,//
						collapsible : false,// 
						draggable : true
					//
					});

	$win.window('open');
}
/**
 * 目标量信息导出
 */
function exportGoals() {
	var form = window.document.forms[0];
	form.action = appUrl + '/kunnrAction!exportGoals.jspa';
	form.target = "hideFrame";
	form.submit();
}

function exportSalesGoals() {
	var form = window.document.forms[0];
	form.action = appUrl + '/stockReportAction!exportSalesGoalForKunnrApply.jspa?kunnr='+$("#kunnrCode").val();
	form.target = "hideFrame";
	form.submit();
}
$("body").on("click",'.combo-text',function(){
	$(this).siblings("span").find("span").click();
	});
function exportDoc() {
	var form = window.document.forms[0];
	form.action = appUrl + '/kunnrAction!exportDoc.jspa';
	form.target = "hideFrame";
	form.submit();
}