var acountCount = $("#acountListSize").val();
var areaCount = $("#kunnrSalesAreaListSize").val();
var goalCount = $("#bCustomerTargetListSize").val();
var addressCount = $("#addressListSize").val();
var month=[];
$(document)
		.ready(
				function() {
					if($('#konzs').val()){
						$('.type2J').show();
					}else{
						$('.type2J').hide();
					}
					// ����ͨ�г���
					$('#maximum02')
							.combobox(
									{
										valueField : 'itemName',
										textField : 'itemValue',
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

					// ��������
					function getRegion(level) {
						$('#region' + level)
								.combobox(
										{
											editable : false,
											url : appUrl
													+ '/sales/salesAction!searchRegion.jspa?level='
													+ level + '&pid='
													+ $('#region').val(),
											textField : 'text',
											valueField : 'id',
											onLoadSuccess : function() {
												$('#region')
														.val(
																$(
																		'#region'
																				+ level)
																		.combobox(
																				'getValue'));
												++level;
												getRegion(level);
											},
											onSelect : function(re) {
												$('#region').val(re.id);
												getRegion(level);
											}

										});

					}
					;
					getRegion(1);

					// �Ա�
					$('#sex').combobox({
						valueField : 'value',
						textField : 'text',
						data : [ {
							value : '',
							text : '��ѡ��'
						} ,{
							value : 'male',
							text : '��'
						}, {
							value : 'female',
							text : 'Ů'
						} ]
					});
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
						}]
					});
					// �ͻ����� ����
					$('#channelId').combobox({
						url : appUrl + '/kunnrAction!getChannel.jspa?channelName='+'K',
						valueField : 'channelId',
						textField : 'channelName'
					});

					// �ϼ������̲�ѯ
					//$('#konzs').combogrid('setValue',$('#konzsHidde').val());
				/*	$('#konzs').combogrid({
						panelHeight : 280,
						panelWidth : 420,
						pagination : true,
						method : 'post',
						singleSelect : true,
						url : appUrl + '/kunnrAction!kunnrSearch.jspa',
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
							if ($('#konzs').combogrid("getValue")) {
								show();
							} else {
								hide();
							}
						}
					});*/
					

					/*// ��Ʒ��
					$('#spart')
							.combobox(
									{
										url : appUrl
												+ '/sales/salesAction!getTspaJsonList.jspa',
										valueField : 'spart',
										textField : 'vtext',
										multiple : true
									});
					$('#spart').combobox('setValues',
							$("#spartHide").val().split(","));

					// ��������
					$('#vtweg')
							.combobox(
									{
										url : appUrl
												+ '/sales/salesAction!getTvtwtJsonList.jspa',
										valueField : 'vtweg',
										textField : 'vtext',
										multiple : true
									});
					$('#vtweg').combobox('setValues',
							$("#vtwegHide").val().split(","));
*/
					// ���۴���
					$('#vkgrp')
							.combogrid(
									{
										panelHeight : 280,
										panelWidth : 420,
										pagination : true,
										method : 'post',
										singleSelect : true,
										url : appUrl
												+ '/salesMsgAction!getT171tJsonList.jspa',
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
					} else {
						loadVkbur(vkbur);
					}
					// ���۲���
					function loadVkbur(vkbur) {
						$('#vkbur')
								.combobox(
										{
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
										url : appUrl
												+ '/salesMsgAction!getTvkoJsonList.jspa',
										valueField : 'bukrs',
										textField : 'bukrs',
										onSelect : function(record) {
											$("#vkorg").val(record.vkorg);
										}
									});

					// Ʒ��
					/*$('#categories').combogrid({
						panelWidth : 400,
						panelHight : 280,
						idField : 'matnr',
						textField : 'maktx',
						pagination : true,// �Ƿ��ҳ
						collapsible : false,// �Ƿ���۵���
						method : 'post',
						multiple : true,
						singleSelect : false,
						url : appUrl + '/goal/goalAction!getMatList.jspa',
						columns : [ [ {
							field : 'matnr',
							title : '���Ϻ�',
							width : 120
						}, {
							field : 'maktx',
							title : '��������',
							align : 'center',
							width : 120
						}, {
							field : 'bezei',
							title : '����������',
							align : 'center',
							width : 100
						} ] ]
					});
					$('#categories').combogrid('setValues',
							$("#categoriesHide").val().split(","));
*/
					for ( var i = 1; i <= areaCount; i++) {
						// ����
						$('#werks_' + i)
								.combobox(
										{
											url : appUrl
													+ '/salesMsgAction!getT001wJsonList.jspa',
											valueField : 'werks',
											textField : 'name1'
										});

						// װ������
						$('#vsbed_' + i)
								.combobox(
										{
											valueField : 'itemName',
											textField : 'itemValue',
											url : appUrl
													+ '/kunnrAction!getCrmDictList.jspa?dictTypeValue='
													+ 'zyc'
										});
						var werks=$('#werks_'+i).combobox('getValue');
						if(werks!='6000'){
					    var str= werks.substr(0, 2); 
				        $('#vsbed_'+i).combobox('setValue',str);
				        }
					}
					// ��������Ȩ :01��KA�ͻ���02-��KA
					$('#lprio').combobox({
						data : [ {
							'id' : '01',
							'txt' : '01��KA�ͻ�'
						}, {
							'id' : '02',
							'txt' : '02-��KA'
						} ],
						valueField : 'id',
						textField : 'txt'
					});
					for ( var i = 1; i <= acountCount; i++) {
						// ���ϻؿ�
						$('#bonus_' + i)
								.combobox(
										{
											url : appUrl
													+ '/salesMsgAction!getTvbotJsonList.jspa',
											valueField : 'bonus',
											textField : 'vtext'
										});
					}
						/*$('#bonus_1')
								.combobox(
										{
											url : appUrl
													+ '/salesMsgAction!getTvbotJsonList.jspa',
											valueField : 'bonus',
											textField : 'vtext'
										});*/
						// Ŀ�����·�
						month = [ {
							'txt' : '01',
							'value' : '1��'
						}, {
							'txt' : '02',
							'value' : '2��'
						}, {
							'txt' : '03',
							'value' : '3��'
						}, {
							'txt' : '04',
							'value' : '4��'
						}, {
							'txt' : '05',
							'value' : '5��'
						}, {
							'txt' : '06',
							'value' : '6��'
						}, {
							'txt' : '07',
							'value' : '7��'
						}, {
							'txt' : '08',
							'value' : '8��'
						}, {
							'txt' : '09',
							'value' : '9��'
						}, {
							'txt' : '10',
							'value' : '10��'
						}, {
							'txt' : '11',
							'value' : '11��'
						}, {
							'txt' : '12',
							'value' : '12��'
						} ];

						for ( var i = 1; i <= goalCount; i++) {

							// �·�
							$('#month_' + i).combobox({
								data : month,
								valueField : 'txt',
								textField : 'value'
							});
							// ���
							$('#year_' + i).combobox({
								url : appUrl + '/goalAction!getDictTypeList.jspa',
								textField : 'itemName',
								valueField : 'itemName'
							});

							// ��Ӧ����
							$('#wid_' + i).combogrid({
								panelWidth : 400,
					            panelHight : 600,
					            idField : 'mvgr1',
					            textField : 'bezei',
					            pagination : true,// �Ƿ��ҳ collapsible : false,//
					            // �Ƿ���۵���
					            method : 'post',
					            url:appUrl+'/goal/goalAction!getMaterielList.jspa',
					            columns : [ [ {
					              field : 'ck',
					              checkbox : true,
					              hidden : true
					            },{
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
								toolbar : '#toolbar_' + i
							});
							$('#search_' + i).searchbox({
								searcher : function(value, name) {
									search(value, i);
								},
								prompt : '����д���ϺŻ�������'
							});
						}
					});
	// ��Ӧ����toolbar

	function search(value, index) {
		value = encodeURIComponent(value);
		$('#wid_' + index).combogrid({
			url : appUrl + '/goal/goalAction!getMaterielList.jspa?value=' + value
		});
		$('#wid_' + index).combogrid("grid").datagrid('reload');

	}
	// �ϼ������̲�ѯ��
	function searcherKonzs(name1) {
		var queryParams = $('#konzs').combogrid("grid").datagrid('options').queryParams;
		queryParams.name1 = encodeURIComponent(name1);
		$('#konzs').combogrid("grid").datagrid('reload');
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
	 * Ŀ������Ϣ����
	 */
	function exportGoals() {
		var form = window.document.forms[0];
		form.action = appUrl + '/kunnrAction!exportGoals.jspa';
		form.target = "hideFrame";
		form.submit();
	}