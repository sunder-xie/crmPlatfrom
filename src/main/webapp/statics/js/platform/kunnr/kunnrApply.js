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
				
					// ��������
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
																	'��ѡ��');
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
							text : '��ƮƮ'
						}, {
							value : 'L',
							text : '����԰'
						}, {
							value : 'M',
							text : 'MECO'
						}],
						onLoadSuccess : function() {
							$('#brands').combobox('setText', $('#brandsC').val());
							$('#brands').combobox('setValues', $('#brandsDM').val());
						}
					});
					// �Ա�
					$('#sex')
							.combobox(
									{
										valueField : 'value',
										textField : 'text',
										data : [ {
											value : '',
											text : '��ѡ��'
										}, {
											value : 'male',
											text : '��'
										}, {
											value : 'female',
											text : 'Ů'
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

					// ��˰������
					$('#kverm')
							.combobox(
									{
										valueField : 'text',
										textField : 'text',
										data : [ {
											value : 'һ����˰��',
											text : 'һ����˰��'
										}, {
											value : 'С��ģ��˰��',
											text : 'С��ģ��˰��'
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

					// �ͻ����� ����
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
											if ('��Լ������' == str) {
												kukla = 'Z3';
											} else if ('KAֱӪ' == str
													|| '����ֱӪ' == str
													|| '����ֱӪ' == str) {
												kukla = 'Z4';
											}
											$('#channelName').val(str);
											$('#kukla').val(kukla);
										}
									});

					// �ͻ���Ŀ��
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

					// �ϼ������̲�ѯ
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
											title : '�����̱��',
											width : 120
										}, {
											field : 'name1',
											title : '����',
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
					
					// ���������̲�ѯ
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
											title : '�����̱��',
											width : 120
										}, {
											field : 'name1',
											title : '����',
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
					// װ������
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

					// ����ͨ�г���
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
					// ���۴���
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
											title : '�������',
											width : 100
										}, {
											field : 'bztxt',
											title : '����',
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

					// ����ʡ��
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
												title : 'ʡ�ݱ��',
												width : 100
											}, {
												field : 'bezei',
												title : '����',
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
					// ���۲���
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

					// ��˾
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

					// ��������Ȩ :01��KA�ͻ���02-��KA
					$('#lprio').combobox({
						data : [ {
							'id' : '01',
							'txt' : '01-KA�ͻ�'
						}, {
							'id' : '02',
							'txt' : '02-��KA'
						} ],
						valueField : 'id',
						textField : 'txt',
						multiple : false,
						onLoadSuccess : function() {
						}
					});

					// ����
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
														"�����ѡ,�����²�����",
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
					// ���ϻؿ�
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
					// ֤��
//					$('#tabs2').tabs('disableTab', 0);
//					$("#tabs2").tabs({
//						onSelect : function(title, index) {
//							if (title != '֤���ϴ�') {
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

// ˢ��֤��ҳ��
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
//								'֤���ϴ�'),
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
//							'֤���б�'),
//					options : {
//						href : appUrl
//								+ '/kunnrAction!displayLicensePre.jspa?dictTypeName='
//								+ $("#kverm").combobox('getValue')
//					}
//				});				
//					
//	}
}
// ������һ�� ����Ƿ����ϼ�������
function checkKonzs(count) {
	if (!$('#konzs').combogrid('getValue')) {
		$.messager.alert('Tips', 'û��ѡ���ϼ�������,��˲��ܶ�����һ��', 'warning');
		$("#flag_" + count).attr("checked", false);
	}
}

// �ϼ������̲�ѯ��
function searcherKonzs(name1) {
	var queryParams = $('#konzs').combogrid("grid").datagrid('options').queryParams;
	queryParams.name1 = encodeURIComponent(name1);
	$('#konzs').combogrid("grid").datagrid('reload');
}

//���������̲�ѯ��
function searcherKunag(name1) {
	var queryParams = $('#kunag').combogrid("grid").datagrid('options').queryParams;
	queryParams.name1 = encodeURIComponent(name1);
	$('#kunag').combogrid("grid").datagrid('reload');
}

// �ϼ����������
function clearKonzs() {
	$('#konzs').combogrid('setValue', '');
	for ( var i = 1; i <= acountCount; i++) {
		if (i % 2 != 0) {
			// �����Ƿ�ѡ��
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

//�������������
function clearKunag() {
	$('#kunag').combogrid('setValue', '');
	for ( var i = 1; i <= acountCount; i++) {
		if (i % 2 != 0) {
			// �����Ƿ�ѡ��
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

// ������֯��ѯ��
function searcherVkorg(vkorg) {
	var queryParams = $('#vkorg').combogrid("grid").datagrid('options').queryParams;
	queryParams.vkorg = encodeURIComponent(vkorg);
	$('#vkorg').combogrid("grid").datagrid('reload');
}
// ����
function searcherVkgrp(vkgrp) {
	var queryParams = $('#vkgrp').combogrid("grid").datagrid('options').queryParams;
	queryParams.bztxt = encodeURIComponent(vkgrp);
	$('#vkgrp').combogrid("grid").datagrid('reload');
}
// ʡ
function searcherBzirk(bztxt) {
	var queryParams = $('#bzirk').combogrid("grid").datagrid('options').queryParams;
	queryParams.bezei = encodeURIComponent(bztxt);
	$('#bzirk').combogrid("grid").datagrid('reload');
}

// ����
function searcherKvgr1(bezei) {
	var queryParams = $('#kvgr1').combogrid("grid").datagrid('options').queryParams;
	queryParams.bezei = encodeURIComponent(bezei);
	$('#kvgr1').combogrid("grid").datagrid('reload');
}

// ����
function searcherKvgr2(bezei) {
	var queryParams = $('#kvgr2').combogrid("grid").datagrid('options').queryParams;
	queryParams.bezei = encodeURIComponent(bezei);
	$('#kvgr2').combogrid("grid").datagrid('reload');
}

// ��˾
function searcherBukrs(butxt) {
	var queryParams = $('#bukrs').combogrid("grid").datagrid('options').queryParams;
	queryParams.butxt = encodeURIComponent(butxt);
	$('#bukrs').combogrid("grid").datagrid('reload');
}

// Ʒ��
function searcherCategories(value) {
	var val = encodeURIComponent(value);
	$('#categories').combogrid({
		url : appUrl + '/goal/goalAction!getMatJsonList.jspa?value=' + val
	});
	$('#categories').combogrid("grid").datagrid('reload');
}
// ��Ӧ����

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
			+ '<td class="head" noWrap>��������</td>'
			+ '<td>'
			+ '<input type="file" name="uploadFile" id="uploadFile"  style="width:200px"/>'
			+ '</tb></tr><table></form>';
	openImportDialog('����Ŀ��', html);
}

/* �򿪵�����ĿExcel�Ի��� */
function openImportDialog(t, html) {
	if ($('#excelDialog').length < 1) {
		$(
				'<div/>',
				{
					id : 'excelDialog',
					title : 'ѡ���ϴ��ļ�',
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
									text : 'ȷ��',
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
											$.messager.alert("��ʾ", "�뵼��csv�ļ�");
										}

									}
								}, {
									text : 'ȡ��',
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
			+ '<td class="head" noWrap>��������</td>'
			+ '<td>'
			+ '<input type="file" name="uploadFile" id="uploadFile"  style="width:200px"/>'
			+ '</tb></tr><table></form>';
	openSalesGoalImportDialog('����Ŀ��', html);
}

/* �򿪵�����ĿExcel�Ի��� */
function openSalesGoalImportDialog(t, html) {
	if ($('#excelDialog').length < 1) {
		$(
				'<div/>',
				{
					id : 'excelDialog',
					title : 'ѡ���ϴ��ļ�',
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
									text : 'ȷ��',
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
											$.messager.alert("��ʾ", "�뵼��csv�ļ�");
										}

									}
								}, {
									text : 'ȡ��',
									handler : function() {
										$('#excelDialog').dialog('close');
									}
								} ],

						width : document.documentElement.clientWidth * 0.28,
						height : document.documentElement.clientHeight * 0.50
					});
}

// �ύ����
function submit() {
	// ��������
	if ($('#brands').combobox('getValues').toString()=='') {
		$.messager.alert('Tips', "��ӪƷ��δ��!", 'error');
		return;
	}
	if (!$("#name1").validatebox('isValid')) {
		$.messager.alert('Tips', "�ͻ�����δ����߸�ʽ����ȷ��", 'error');
		return;
	}
	if (!$("#channelId").combobox('isValid')) {
		$.messager.alert('Tips', "�ͻ�����δ��ѡ��", 'error');
		return;
	}
	if (!$("#name3").validatebox('isValid')) {
		$.messager.alert('Tips', "���˴���δ�", 'error');
		return;
	}
	/*
	 * if (!$("#age").numberbox('isValid')) { $.messager.alert('Tips',
	 * "��������δ�", 'error'); return; }
	 */
	if (!$("#mobNumber").numberbox('isValid')) {
		$.messager.alert('Tips', "�����ֻ�δ����߸�ʽ����ȷ��", 'error');
		return;
	}
	if (!$("#natureEnterprise").validatebox('isValid')) {
		$.messager.alert('Tips', "��ҵ����δ����߸�ʽ����ȷ��", 'error');
		return;
	}
	if (!$("#healthNumber").validatebox('isValid')) {
		$.messager.alert('Tips', "ʳƷ���֤δ����߸�ʽ����ȷ��", 'error');
		return;
	}
	if (!$("#businessLicense").validatebox('isValid')) {
		$.messager.alert('Tips', "Ӫҵִ��δ����߸�ʽ����ȷ��", 'error');
		return;
	}

	/*if (!$("#telNumber").validatebox('isValid')) {
		$.messager.alert('Tips', "��˾��ϵ�绰δ����߸�ʽ����ȷ��", 'error');
		return;
	}*/
	if (!$("#faxNumber").validatebox('isValid')) {
		$.messager.alert('Tips', "����δ����߸�ʽ����ȷ��", 'error');
		return;
	}
	if (!$("#warehouseArea").validatebox('isValid')) {
		$.messager.alert('Tips', "�ֿ����δ����߸�ʽ����ȷ��", 'error');
		return;
	}
	var region4 = $('#region4').combobox('getValue');
	if (region4 == '') {
		$.messager.alert('Tips', "�칫���ڵ�δѡ��", 'error');
		return;
	}
	if (!$("#street1").validatebox('isValid')) {
		$.messager.alert('Tips', "����ο�λ��δ����߸�ʽ����ȷ��", 'error');
		return;
	}
	if (!$("#createReason").validatebox('isValid')) {
		$.messager.alert('Tips', "����ԭ��δ����߸�ʽ����ȷ��", 'error');
		return;
	}
	// ������Ϣ��֤
	if (!$("#bank").validatebox('isValid')) {
		$.messager.alert('Tips', "��������δ����߸�ʽ����ȷ��", 'error');
		return;
	}
	if (!$("#bankAccount").validatebox('isValid')) {
		$.messager.alert('Tips', "�����˺�δ����߸�ʽ����ȷ��", 'error');
		return;
	}
	var len1=$("#bank").val().length;
	var len2=$("#bankAccount").val().length;
	var len=len1+len2;
	if(len>48){
		$.messager.alert('Tips', "���������������˺ų���֮�ͳ��ȱ�����48���ڣ�", 'error');
		return;
	}
	if (!$("#stceg").validatebox('isValid')) {
		$.messager.alert('Tips', "˰��ǼǺ�δ����߸�ʽ����ȷ��", 'error');
		return;
	}
	if (!$("#kpPhone").validatebox('isValid')) {
		$.messager.alert('Tips', "��Ʊ�绰δ����߸�ʽ����ȷ��", 'error');
		return;
	}
	if (!$("#fpRecipient").validatebox('isValid')) {
		$.messager.alert('Tips', "��Ʊ�ռ���δ����߸�ʽ����ȷ��", 'error');
		return;
	}
	if (!$("#fpContactPhone").validatebox('isValid')) {
		$.messager.alert('Tips', "��Ʊ��ϵ�绰δ����߸�ʽ����ȷ��", 'error');
		return;
	}
	if (!$("#zcAddress").validatebox('isValid')) {
		$.messager.alert('Tips', "ע���ַδ����߸�ʽ����ȷ��", 'error');
		return;
	}
	if (!$("#fpAddress").validatebox('isValid')) {
		$.messager.alert('Tips', "��Ʊ���͵�ַδ����߸�ʽ����ȷ��", 'error');
		return;
	}
	// ��ϸ����
	// ��ϸ������ı���������֤
	if ($("#businessManager").val() == ''
			&& $("#businessCompetent").val() == '') {
		$.messager.alert('Tips', "���о��������������дһ�", 'error');
		return;
	}

	var vkgrp = $('#vkgrp').combobox('getValue');
	var bzirk = $('#bzirk').combobox('getValue');
	var vkbur = $('#vkbur').combobox('getValue');
	if (!vkgrp) {
		$.messager.alert('Tips', '������ͼ������۴���δ�', 'warning');
		return;
	} else if (!bzirk) {
		$.messager.alert('Tips', '������ͼ�������ʡ��δ�', 'warning');
		return;
	} else if (!vkbur) {
		$.messager.alert('Tips', '������ͼ��ĳ��а�δ�', 'warning');
		return;
	}
	var lprio = $('#lprio').combobox('getValue');
	if (!lprio) {
		$.messager.alert('Tips', '������ͼ��Ľ�������Ȩδ�', 'warning');
		return;
	}

	// ��Լ�����̱���ѡ�ϼ�������
	if ($('#channelId').combobox('getValue')) {
		var selectText = $('#channelName').val();
		if (selectText == '��Լ������'
				&& ($('#konzs').combogrid('getValue') == null
						|| $('#konzs').combogrid('getValue') == '' || undefined == $(
						'#konzs').combogrid('getValue'))) {
			$.messager.alert('Tips', '�ͻ�Ϊ��Լ������,��ѡ��ѡ�ϼ�������,��ά��������һ������ҵ�ۿ�!',
					'warning');
			return;
		}
	}

	array2json();
	var matchFlag = true;
	var isValid = $('#ff').form('validate');
	// ����δѡ
	/* var region4 = $('#region4').combobox('getValue'); */
	var goalFlag = true;
//	var goalFlag = false;
//	if ($('#importFlag').val() == 'Y'||($('#bCustomerTargetListSize').val()>0)) {
//		goalFlag = true;
//	} else {
//		$.messager.alert('Tips', 'Ԥ��Ŀ����δ����!', 'warning');
//		return;
//	}
	if ($('#street').val() == '' || undefined == $('#street').val()) {
		$.messager.alert('Tips', '��ѡ���ջ�������ַ!', 'warning');
		return;
	}
	//ȡ������Ŀ���� by cg.jiang21061129
	/**
	if($('#importGoalSalesFlag').val() != 'Y'){
		$.messager.alert('Tips', '����Ŀ����δ����!', 'warning');
		return;
	}
*/
	for ( var i = 0; i < kunnrAddressList.length; i++) {
		if (kunnrAddressList[i].xzAddress == '') {
			var n = i + 2;
			$.messager.alert('Tips', '��' + n + '����ѡ���ʹ﷽������ַ!', 'warning');
			return;
		}
	}
	if (isValid) {
		$.messager
				.confirm(
						'Confirm',
						'��˶Ծ�����������,�ύ?',
						function(r) {
							if (r) {
								var text=$('#name1').val()+'_�����̿��������ᱨ';
								$('#title').val(text);
								var license = $("input[name='license']");
								if(license.length==0){
									$.messager.alert('Tips', "֤��δ�ϴ���",
									'error');
							         return;
								}else{
								for ( var i = 0; i < license.length; i++) {
									var file = license[i].value;
									if (file) {
										matchFlag = matchImage(file,
												"֤�ո�ʽ����,���ϴ���ȷ��ͼƬ��ʽ!");
										if (!matchFlag)
											break;
									} else {
										var n=i+1;
										if($('#xmlId').val()==''||($('#oldLice_'+n).val()==''||undefined==$('#oldLice_'+n).val())){
										$.messager.alert('Tips', "֤��δ�ϴ���",
												'error');
										return;
										}
									}
								}}
								if (matchFlag) {
									// ��֤������Ŀ������Ч�ԣ����ܳ�����֯Ŀ����
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
										// �¸�������
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
																			if (!$(
																					"#nextUsers")
																					.val()) {
																				$.messager
																						.alert(
																								'Tips',
																								"û���¸������ˣ���ά����",
																								'error');
																				return;
																			}
																			// �¸�������ID
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
																		text : 'ȡ��',
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
		$.messager.alert('Tips', "���ϴ�֤�գ�",
		'error');
        return;
	}else{
	for ( var i = 0; i < license.length; i++) {
		var file = license[i].value;
		if (file) {
			matchFlag = matchImage(file,
					"֤�ո�ʽ����,���ϴ���ȷ��ͼƬ��ʽ!");
			if (!matchFlag)
				break;
		} else {
			var n=i+1;
			if($('#xmlId').val()==''||($('#oldLice_'+n).val()==''||undefined==$('#oldLice_'+n).val())){
			$.messager.alert('Tips', "֤��δ�ϴ���",
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
// ȡ��
function cancel() {
	self.location.reload(true);
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

// ��ӵ�ַһ��
function addAddress() {
	addressCount++;
	var html = '';
	html += '<tr id="addressTr_' + addressCount + '">';
	html += '<td class="head" noWrap><a href="javascript:removeAddress('
			+ addressCount
			+ ')"><img align="absMiddle" src="'
			+ imgUrl
			+ '/images/actions/action_del.png"/></a>&nbsp;&nbsp;&nbsp;<font color="red">*</font>(�ʹ﷽)��ַ:</td>';
	html += '<td><a class="easyui-linkbutton" style="width:44px"  href="javascript:selectAddress('
			+ addressCount + ')">ѡ��</a></td>';
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
	html += '<td class="head" noWrap>�ջ���:';
	html += '<input class="easyui-validatebox" style="width:80px" validType="length[0,20]" id="name_'
			+ addressCount + '">';
	html += '<font class="head" noWrap>�ջ���ϵ�绰:';
	html += '<input class="easyui-validatebox" style="width:90px" validType="telephone" id="telephone_'
			+ addressCount + '"></font></td>';
	html += '<td class="head" noWrap>�ջ���ϵ�ֻ�:</td>';
	html += '<td><input class="easyui-numberbox" style="width:90px" validType="length[11,11]" invalidMessage="��������ȷ��11λ�ֻ���" id="mobile_'
			+ addressCount + '"></td>';
	html += '<td class="head" noWrap>����ͨ�г���:</td>';
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
// �h����ַһ��
function removeAddress(count) {
	if ($("#addressTr_" + count)) {
		$("#addressTr_" + count).remove();
	}
}

// ���Ʒ��һ��
function addBrand() {
	brandCount++;
	var html = '';
	html += '<tr id="brandTr_' + brandCount + '">';
	html += '<td class="head" noWrap><a href="javascript:removeBrand('
			+ brandCount
			+ ')"><img align="absMiddle" src="'
			+ imgUrl
			+ '/images/actions/action_del.png"/></a>&nbsp;&nbsp;&nbsp;Ʒ������:</td>';
	html += '<td><input class="easyui-validatebox" id="brand_' + brandCount
			+ '"></td>';
	html += '<td class="head" noWrap>���۶�:</td>';
	html += '<td colspan="3"><input class="easyui-validatebox" id="sales_'
			+ brandCount + '">(��Ԫ)</td>';
	html += '</tr>';
	$('#brandTable').append(html);
}

// �h��Ʒ��һ��
function removeBrand(count) {
	if ($("#brandTr_" + count)) {
		$("#brandTr_" + count).remove();
	}
}

// ����ۿ�һ��
function addAcount() {
	acountCount++;
	var html = '';
	html += '<tr id="salesAcountTr_' + acountCount + '">';
	html += '<td class="head" noWrap><a href="javascript:removeAcount('
			+ acountCount
			+ ')"><img align="absMiddle" src="'
			+ imgUrl
			+ '/images/actions/action_del.png"/></a>&nbsp;<font color="red">*</font>&nbsp;���ϻؿ���:';
	html += '<input class="easyui-combobox" id="bonus_' + acountCount
			+ '"></td>';
	html += '<td class="head" noWrap>';
	html += '�·�(Ԫ):<input class="easyui-numberbox" style="width:60px" id="typeA_'
			+ acountCount + '">';
	html += '&nbsp;����(Ԫ):<input class="easyui-numberbox" style="width:60px" id="typeB_'
			+ acountCount + '">';
	html += '&nbsp;�귵(Ԫ):<input class="easyui-numberbox" style="width:60px" id="typeC_'
			+ acountCount
			+ '">'
			+ '&nbsp;Э���(Ԫ):<input class="easyui-numberbox" id="typeMoney_'
			+ acountCount + '"'
			/*
			 * +' onchange="changeRow(' + acountCount + ')" '
			 */
			+ 'style="width:60px"></td>';

	if ($('#konzs').combogrid('getValue')) {
		html += '<td class="head" noWrap><div class="type2J">';
		html += '<label><font color="red">������һ��</font><input type="checkbox" id="flag_'
				+ acountCount
				+ '" value="Y" onclick="checkKonzs('
				+ acountCount + ')"></label>';
		html += '&nbsp;�·�(Ԫ):<input class="easyui-numberbox" style="width:60px" id="type2A_'
				+ acountCount
				+ '" onclick="checkKonzs('
				+ acountCount
				+ ')">';
		html += '&nbsp;����(Ԫ):<input class="easyui-numberbox" style="width:60px" id="type2B_'
				+ acountCount
				+ '" onclick="checkKonzs('
				+ acountCount
				+ ')">';
		html += '&nbsp;�귵(Ԫ):<input class="easyui-numberbox" style="width:60px" id="type2C_'
				+ acountCount
				+ '" onclick="checkKonzs('
				+ acountCount
				+ ')">'
				+ '</div></td>';

	} else {
		html += '<td class="head" noWrap><div class="type2J" style="display:none;">';
		html += '<label><font color="red">������һ��</font><input type="checkbox" id="flag_'
				+ acountCount
				+ '" value="Y" onclick="checkKonzs('
				+ acountCount + ')"></label>';
		html += '&nbsp;�·�(Ԫ):<input class="easyui-numberbox" style="width:60px" id="type2A_'
				+ acountCount
				+ '" onclick="checkKonzs('
				+ acountCount
				+ ')">';
		html += '&nbsp;����(Ԫ):<input class="easyui-numberbox" style="width:60px" id="type2B_'
				+ acountCount
				+ '" onclick="checkKonzs('
				+ acountCount
				+ ')">';
		html += '&nbsp;�귵(Ԫ):<input class="easyui-numberbox" style="width:60px" id="type2C_'
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

	// ���ϻؿ�

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
// ɾ���ۿ�һ��
function removeAcount(count) {
	if ($("#salesAcountTr_" + count)) {
		// var n = count + 1;
		$("#salesAcountTr_" + count).remove();
		// $("#salesAcountTr_" + n).remove();
	}
}
// ������۷�Χһ��
function addArea(obj, areaCount) {
	// areaCount++;
	var html = '';
	html += '<tr id="saleAreaTr_' + areaCount + '">';
	// html += '<td class="head" noWrap></td>'; //&nbsp;&nbsp;���۷�Χ: <a
	// class="easyui-linkbutton" href="javascript:selectTvkbz('
	// + areaCount + ')">ѡ��</a>
	html += '<td class="head" noWrap><a href="javascript:removeArea('
			+ areaCount + ')"><img align="absMiddle" src="' + imgUrl
			+ '/images/actions/action_del.png"/></a> &nbsp;&nbsp;������֯:</td>';
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
	html += '<td class="head" noWrap>��������:</td>';
	html += '<td><input  type="hidden" id="vtweg_' + areaCount + '" value="'
			+ obj.vtweg + '" readonly>'
			+ '<input  class="easyui-validatebox" id="vtwegTxt_' + areaCount
			+ '"  value="' + obj.vtwegTxt
			+ '" data-options="required:true" readonly></td>';
	html += '<td class="head" noWrap>��Ʒ��:</td>';
	html += ' <td><input  type="hidden" id="spart_' + areaCount + '" value="'
			+ obj.spart + '" readonly></input>'
			+ '<input  class="easyui-validatebox" id="spartTxt_' + areaCount
			+ '" value="' + obj.spartTxt
			+ '" data-options="required:true" readonly></input></td>';
	html += '<td class="head" noWrap>��������:</td>';
	html += '<td><input class="easyui-combobox" id="werks_' + areaCount
			+ '"  data-options="required:true,editable:false"></td>';
	html += '<td class="head" noWrap>װ������:</td>';
	html += '<td><input class="easyui-combobox" id="vsbed_'
			+ areaCount
			+ '" data-options="required:true" style="width:60px" validType="length[2,2]"></td>';
	html += '</tr>';
	$('#saleAreaTable').append(html);
	$.parser.parse($('#saleAreaTr_' + areaCount));
	// ����
	var areaCounts = areaCount;
	$('#werks_' + areaCount).combobox({
		url : appUrl + '/salesMsgAction!getT001wJsonList.jspa',
		valueField : 'werks',
		textField : 'name1',
		onSelect : function(title, index) {
			var werks = title.werks;
			if (werks == '6000') {
				$.messager.alert('Tips', "�����ѡ,�����²�����", 'warning');
				$('#werks_' + areaCounts).combobox('setValue', '');
				$('#vsbed_' + areaCounts).combobox('setValue', '');
				return;
			} else {
				var str = werks.substr(0, 2);
				$('#vsbed_' + areaCounts).combobox('setValue', str);
			}
		}
	});
	// װ������
	$('#vsbed_' + areaCounts).combobox(
			{
				valueField : 'itemName',
				textField : 'itemValue',
				url : appUrl
						+ '/kunnrAction!getCrmDictList.jspa?dictTypeValue='
						+ 'zyc'
			});
}
// �h�����۷�Χһ��
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
// ���Ŀ����һ��
function addGoal() {
	goalCount++;
	var html = '';
	html += '<tr id="goalTr_' + goalCount + '">';
	html += '<td class="head" noWrap>&nbsp;&nbsp;<a href="javascript:removeGoal('
			+ goalCount
			+ ')"><img align="absMiddle" src="'
			+ imgUrl
			+ '/images/actions/action_del.png"/></a>&nbsp;&nbsp;��� </td>';
	html += ' <td><input class="easyui-combobox" id="year_'
			+ goalCount
			+ '" style="width:60px" data-options="editable:false" readonly>'
			+ '<font class="head">&nbsp;/&nbsp;�·� <input class="easyui-combobox" id="month_'
			+ goalCount
			+ '" style="width:60px" data-options="editable:false" name="theMonth"></td>';
	html += ' <td class="head" noWrap>��Ӧ����</td>';
	html += '<td><input class="easyui-cmobogrid" id="wid_' + goalCount
			+ '"/></td>' + ' <div id="toolbar_' + goalCount
			+ '" class="datagrid-toolbar" >' + ' <input id="search_'
			+ goalCount
			+ '" class="easyui-searchbox" style="width:300px"></input>'
			+ '</div>';
	html += '<td class="head" noWrap>Ŀ������(����)</td>';
	html += '<td><input class="easyui-numberbox" style="width:60px" id="box_'
			+ goalCount + '"  data-options="precision:2,min:0,fix"""/>'
			+ '</td>';
	html += ' <td class="head" noWrap>Ŀ����(Ԫ)</td>';
	html += '<td> <input class="easyui-numberbox" id="targetNum_' + goalCount
			+ '" data-options="precision:2,min:0,fix:"""/></td>';
	html += '</tr>';
	$('#goalTable').append(html);
	$.parser.parse($('#goalTr_' + areaCount));
	// �·�
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
	// ���
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
		prompt : '����д���ϺŻ�������'
	});

	// ��Ӧ����
	$('#wid_' + goalCount).combogrid({
		panelWidth : 400,
		panelHight : 600,
		idField : 'mvgr1',
		textField : 'bezei',
		pagination : true,// �Ƿ��ҳ collapsible : false,//
		// �Ƿ���۵���
		method : 'post',
		url : appUrl + '/goal/goalAction!getMaterielList.jspa',
		columns : [ [ {
			field : 'ck',
			checkbox : true,
			hidden : true
		}, {
			field : 'mvgr1',
			title : '������',
			align : 'center',
			width : 120
		}, {
			field : 'bezei',
			title : '����������',
			align : 'center',
			width : 200
		} ] ],
		toolbar : '#toolbar_' + goalCount
	});

}
// ��Ӧ����toolbar

function search(value, index) {
	value = encodeURIComponent(value);
	$('#wid_' + index).combogrid({
		url : appUrl + '/goal/goalAction!getMaterielList.jspa?value=' + value
	});
	$('#wid_' + index).combogrid("grid").datagrid('reload');

}
// �h��Ŀ����һ��
function removeGoal(count) {
	if ($("#goalTr_" + count)) {
		$("#goalTr_" + count).remove();
	}
}
// ��ַƷ��array->json
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

// ������Ϣ
function promgtMsg() {
	var hideFrame = document.getElementById("hideFrame");
	var failResult = hideFrame.contentWindow.failResult;
	var successResult = hideFrame.contentWindow.successResult;
	$.messager.progress('close');
	if (failResult) {
		$.messager.alert('Tips', failResult, 'warning');
	} else if (successResult == "ok") {
		$('#nextUserDialog').dialog('close');
	} else if (successResult == "����ɹ�!") {
		/*
		 * $('#goalTable') .datagrid({data:$('#bCustomerTargetList').val()});
		 */
		$.messager.alert('Tips', successResult, 'info', function() {
			$('#excelDialog').dialog('close');
			$('.goalFlag').show();
			$('#importFlag').val('Y');
		});
	} else if (successResult == "����Ŀ��������ɹ�!") {
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

// ��ʼ��ʱһ��Ҫ��һ��
function initHide() {
	$('.type2J').hide();
}

// ����
function hide() {
	$('.type2J').hide();
}

// ����
function show() {
	$('.type2J').show();
}
/**
 * ����֯��
 */
function selectOrgTree() {
	initMaintWindow4Post('ѡ����֯', '/customerAction!orgTreePage.jspa', 400, 460);
}
/**
 * ��֯��ѡ��֯���ص������
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
 * ��֯��ѡ����֮��ر�
 */
function closeOrgTree() {
	$("#nextUserDialog").window('close');
}
/*
 * ����Աѡ����
 */
function selectManager() {
	userRole = 'M';
	initMaintWindow4Post('ѡ����о���', '/batChangeAction!selectOrgTreeUser.jspa',
			550, 350);
}
/*
 * ����Աѡ����
 */
function selectDirectory() {
	userRole = 'D';
	initMaintWindow4Post('ѡ��ͻ�����', '/batChangeAction!selectOrgTreeUser.jspa',
			550, 350);
}
/*
 * ���о�������ѡ�񷵻�ֵ
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
 * �����۷�Χ
 */
function selectTvkbz(num) {
	initMaintWindow4Post('ѡ�����۷�Χ', '/salesMsgAction!toSearchTvkbz.jspa?vkorg='
			+ $('#bukrs').combobox('getValue') + '&num=' + num, 700, 350);
}
/*
 * ���۷�Χѡ�񷵻�ֵ vkorg������֯,vtweg��������,spart��Ʒ��,vkbur���۲���
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
 * �򿪶�Ӧ����
 */
function selectGoalWid(num) {
	initMaintWindow4Post('ѡ������', '/kunnrAction!searchGoalWid.jspa?num=' + num,
			700, 550);
}
/*
 * ��Ӧ����ѡ�񷵻�ֵmatnr���ϱ�ţ�maktx���ı�������mvgr1Ԥ��ھ�����bezeiԤ��ھ�
 */
function returnGoalWid(matnr, maktx, mvgr1, bezei, num) {
	document.getElementById('wid_' + num).value = matnr;
	document.getElementById('wid_name_' + num).value = maktx;
}

/*
 * ѡ���ַ
 */
function selectAddress(num) {
	if (undefined == num) {
		initMaintWindow4Post('ѡ���ַ', '/kunnrAction!searchAddress.jspa', 750,
				600);
	} else {
		initMaintWindow4Post('ѡ���ַ', '/kunnrAction!searchAddress.jspa?num='
				+ num, 750, 600);
	}
}
/*
 * ��ַѡ�񷵻�ֵzwl01tʡ��zwl02t�У�zwl03t��zwl04t��
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
 * ��������
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
 * Ŀ������Ϣ����
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