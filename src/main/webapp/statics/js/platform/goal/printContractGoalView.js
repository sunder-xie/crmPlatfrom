var tot = new Array(13); 
function print(){
	var yearFlag=$('#yearFlag').val();
	var custId=$('#custId').val();
	var WWidth = 950;
	var WLeft = Math.ceil((window.screen.width - WWidth) / 2);
	var win = window.open(appUrl + '/goal/goalAction!printContractGoal.jspa?yearFlag='+yearFlag+'&custId='+custId,"eventPrint","left=" + WLeft + ",top=20" + ",width=" + WWidth + 
",height="
			+ (window.screen.height - 100)
			+ ",toolbar=no,rolebar=no,scrollbars=yes,location=no,menubar=no,resizable=yes,titlebar=no");
}

var LODOP;
//<input type="button" onClick="Preview()" value="��ӡԤ�� " name="button_show"> 
function Preview() {
	CreatePrintPage2();
	LODOP.PREVIEW();
//	Preview2();
};
function Preview2(){
	CreatePrintPage();
	LODOP.PREVIEW();
}

function Print(){
	CreatePrintPage2();
	LODOP.PRINT();
//	CreatePrintPage();
//	LODOP.PRINT();
	window.parent.close();
};

function Setup() {		
	CreatePrintPage();
	LODOP.PRINT_SETUP();		
};
function Design() {		
	CreatePrintPage();
	LODOP.PRINT_DESIGN();		
};
//<img id = 'log' align='right' border='0' width='5%' height = '5%' src='$!{env.imgUrl}/images/xpp/log.png' />
var url = appUrl+"/images/xpp/log.png";
function CreatePrintPage1() {
	LODOP=getLodop();  
	LODOP.PRINT_INITA(10,10,775,460,"");
//	LODOP. ADD_PRINT_SETUP_BKIMG("<img border='0' src='"+imgUrl+ "/images/actions/1.png' style='z-index: -1'/>");
//	LODOP.SET_SHOW_MODE("BKIMG_IN_PREVIEW",1);
//	LODOP.SET_SHOW_MODE("BKIMG_PRINT",1);
//	LODOP.SET_SHOW_MODE("BKIMG_LEFT",10);
//	LODOP.SET_SHOW_MODE("BKIMG_TOP",10);
	LODOP.SET_PRINT_MODE("PRINT_PAGE_PERCENT","Full-Width");
	LODOP.SET_PRINT_PAGESIZE(2, 2100, 2950,"��ӡ");
	LODOP.SET_PRINT_STYLEA(1,"Stretch",2);
	LODOP.SET_PRINT_STYLEA(1,"ItemType",1);
	LODOP.SET_PRINT_STYLEA(1,"HOrient",3);
	LODOP.SET_PRINT_STYLEA(1,"VOrient",3);
	LODOP.ADD_PRINT_HTM(65,20,700,340,document.getElementById("event").innerHTML);
	LODOP.SET_PRINT_STYLEA(2,"FontSize",15);
	LODOP.SET_PRINT_STYLEA(2,"ItemType",4);
	LODOP.SET_PRINT_STYLEA(2,"HOrient",3);
	LODOP.SET_PRINT_STYLEA(2,"VOrient",3);
//	LODOP.ADD_PRINT_TEXT(1080,576,144,22,"ҳ�ţ���#ҳ/��&ҳ");
//	LODOP.SET_PRINT_STYLEA(3,"ItemType",2);
//	LODOP.SET_PRINT_STYLEA(3,"HOrient",1);
};	

function CreatePrintPage2() {
	var obj1 = eval($('#pcgInfoJson').val());
	var year = $('#yearFlag').val()
	LODOP = getLodop();
	LODOP.PRINT_INITA(0,0,1134,800,"");
	LODOP.SET_PRINT_PAGESIZE(2,2100,2950,"");
	LODOP.ADD_PRINT_SETUP_BKIMG("<img border='0' src='"+imgUrl+ "/images/brand/xpp.png' style='z-index: -1'/>");
	LODOP.SET_SHOW_MODE("BKIMG_LEFT",330);
	LODOP.SET_SHOW_MODE("BKIMG_TOP",330);
	LODOP.SET_SHOW_MODE("BKIMG_PRINT",true);
	LODOP.ADD_PRINT_TEXT(123,345,365,33,$("#yearFlag").val()+"�꾭��Ʒ��¶�Э������Ŀ����");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	LODOP.SET_PRINT_STYLEA(0,"Bold",1);
	LODOP.ADD_PRINT_LINE(172,57,171,1002,0,1);
	LODOP.ADD_PRINT_LINE(247,57,246,1002,0,1);
	LODOP.ADD_PRINT_LINE(281,57,280,1002,0,1);
	LODOP.ADD_PRINT_LINE(315,57,314,1002,0,1);
	LODOP.ADD_PRINT_LINE(349,57,348,1002,0,1);
	LODOP.ADD_PRINT_LINE(383,57,382,1002,0,1);
	LODOP.ADD_PRINT_LINE(417,57,416,1002,0,1);
	LODOP.ADD_PRINT_LINE(451,57,450,1002,0,1);
	LODOP.ADD_PRINT_LINE(485,57,484,1002,0,1);
	LODOP.ADD_PRINT_LINE(519,57,518,1002,0,1);
	LODOP.ADD_PRINT_LINE(553,57,552,1002,0,1);
	LODOP.ADD_PRINT_LINE(587,57,586,1002,0,1);
	LODOP.ADD_PRINT_LINE(621,57,620,1002,0,1);
	LODOP.ADD_PRINT_LINE(655,57,654,1002,0,1);
	//LODOP.ADD_PRINT_LINE(689,57,688,1002,0,1);
	LODOP.ADD_PRINT_LINE(722,57,723,1002,0,1);
	LODOP.ADD_PRINT_LINE(171,57,722,58,0,1);
	LODOP.ADD_PRINT_LINE(171,144,722,145,0,1);
	LODOP.ADD_PRINT_LINE(171,198,722,199,0,1);
	LODOP.ADD_PRINT_LINE(171,252,722,253,0,1);
	LODOP.ADD_PRINT_LINE(171,306,722,307,0,1);
	LODOP.ADD_PRINT_LINE(171,360,722,361,0,1);
	LODOP.ADD_PRINT_LINE(171,416,722,417,0,1);
	LODOP.ADD_PRINT_LINE(171,474,722,475,0,1);
	LODOP.ADD_PRINT_LINE(171,528,722,529,0,1);
	LODOP.ADD_PRINT_LINE(171,582,722,583,0,1);
	LODOP.ADD_PRINT_LINE(171,637,722,638,0,1);
	LODOP.ADD_PRINT_LINE(171,696,722,697,0,1);
	LODOP.ADD_PRINT_LINE(171,750,722,751,0,1);
	LODOP.ADD_PRINT_LINE(171,804,722,805,0,1);
	LODOP.ADD_PRINT_LINE(171,862,722,863,0,1);
	LODOP.ADD_PRINT_LINE(171,924,722,925,0,1);
	LODOP.ADD_PRINT_LINE(171,1002,722,1003,0,1);
	LODOP.ADD_PRINT_TEXT(145,920,78,25,"(��λ:����)");
	LODOP.ADD_PRINT_TEXT(733,464,100,20,"1/2");
	LODOP.ADD_PRINT_TEXT(733,920,70,20,"����ҳ");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	LODOP.ADD_PRINT_LINE(171,57,246,144,0,1);
	LODOP.ADD_PRINT_TEXT(183,95,40,25,"��Ʒ");
	LODOP.ADD_PRINT_TEXT(220,64,40,20,"����");
	LODOP.ADD_PRINT_TEXT(190,204,35,40,"�춹����");
	LODOP.ADD_PRINT_TEXT(190,155,35,40,"Ҭ������");
	LODOP.ADD_PRINT_TEXT(190,257,45,40,"֥ʿ���󵥱�");
	LODOP.ADD_PRINT_TEXT(190,311,45,40,"�����ɲݵ���");
	LODOP.ADD_PRINT_TEXT(190,366,45,40,"â����������");
	LODOP.ADD_PRINT_TEXT(190,428,35,40,"��ݮ����");
	LODOP.ADD_PRINT_TEXT(190,480,45,40,"ѩŴҬ������");
	LODOP.ADD_PRINT_TEXT(190,533,45,40," Ҭ��\n������");
	LODOP.ADD_PRINT_TEXT(190,588,45,40," �춹\n������");
	LODOP.ADD_PRINT_TEXT(179,643,45,60,"֥ʿ\n����\n������");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	LODOP.ADD_PRINT_TEXT(180,701,45,60,"����\n�ɲ�\n������");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	LODOP.ADD_PRINT_TEXT(181,755,45,60,"â��\n����\n������");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	LODOP.ADD_PRINT_TEXT(192,812,45,40,"��ݮ\n������");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	LODOP.ADD_PRINT_TEXT(190,864,60,40,"ѩŴҬ��������");
	LODOP.ADD_PRINT_TEXT(200,928,65,22,"С������");
	LODOP.ADD_PRINT_TEXT(254,68,70,20,$("#yearFlag").val()+'��'+7+'��');
	LODOP.ADD_PRINT_TEXT(288,68,70,20,$("#yearFlag").val()+'��'+8+'��');
	LODOP.ADD_PRINT_TEXT(322,68,70,20,$("#yearFlag").val()+'��'+9+'��');
	LODOP.ADD_PRINT_TEXT(356,68,70,20,$("#yearFlag").val()+'��'+10+'��');
	LODOP.ADD_PRINT_TEXT(390,68,70,20,$("#yearFlag").val()+'��'+11+'��');
	LODOP.ADD_PRINT_TEXT(424,68,70,20,$("#yearFlag").val()+'��'+12+'��');
	LODOP.ADD_PRINT_TEXT(458,68,70,20,(parseInt($("#yearFlag").val())+1)+'��'+1+'��');
	LODOP.ADD_PRINT_TEXT(492,68,70,20,(parseInt($("#yearFlag").val())+1)+'��'+2+'��');
	LODOP.ADD_PRINT_TEXT(526,68,70,20,(parseInt($("#yearFlag").val())+1)+'��'+3+'��');
	LODOP.ADD_PRINT_TEXT(560,68,70,20,(parseInt($("#yearFlag").val())+1)+'��'+4+'��');
	LODOP.ADD_PRINT_TEXT(594,68,70,20,(parseInt($("#yearFlag").val())+1)+'��'+5+'��');
	LODOP.ADD_PRINT_TEXT(628,68,70,20,(parseInt($("#yearFlag").val())+1)+'��'+6+'��');
	LODOP.ADD_PRINT_TEXT(662,68,60,20,"С������(����)");
	var index = 0;
	var total = 0;
	var m1 = 0 ;
	var m2 = 0 ; 
	var m3 = 0 ;
	var m4 = 0 ; 
	var m5 = 0 ;
	var m6 = 0 ; 
	var m7 = 0 ;
	var m8 = 0 ; 
	var m9 = 0 ;
	var m10 = 0 ; 
	var m11 = 0 ;
	var m12 = 0 ; 
	var m13 = 0 ;
	var m14 = 0 ; 
	for(var pcgInfo in obj1){ //�ڶ���ѭ��ȡlist�еĶ��� 
		while(index<12){
			if((parseInt(obj1[pcgInfo].theMonth)-parseInt(index))==7||(parseInt(index)-parseInt(obj1[pcgInfo].theMonth))==5){
				LODOP.ADD_PRINT_TEXT(254+index*34,147,50,20,obj1[pcgInfo].matter1);
				LODOP.ADD_PRINT_TEXT(254+index*34,200,50,20,obj1[pcgInfo].matter2);
				LODOP.ADD_PRINT_TEXT(254+index*34,255,50,20,obj1[pcgInfo].matter3);
				LODOP.ADD_PRINT_TEXT(254+index*34,308,50,20,obj1[pcgInfo].matter4);
				LODOP.ADD_PRINT_TEXT(254+index*34,363,50,20,obj1[pcgInfo].matter5);
				LODOP.ADD_PRINT_TEXT(254+index*34,420,50,20,obj1[pcgInfo].matter6);
				LODOP.ADD_PRINT_TEXT(254+index*34,476,50,20,obj1[pcgInfo].matter7);
				LODOP.ADD_PRINT_TEXT(254+index*34,531,50,20,obj1[pcgInfo].matter8);
				LODOP.ADD_PRINT_TEXT(254+index*34,584,50,20,obj1[pcgInfo].matter9);
				LODOP.ADD_PRINT_TEXT(254+index*34,640,50,20,obj1[pcgInfo].matter10);
				LODOP.ADD_PRINT_TEXT(254+index*34,699,50,20,obj1[pcgInfo].matter11);
				LODOP.ADD_PRINT_TEXT(254+index*34,753,50,20,obj1[pcgInfo].matter12);
				LODOP.ADD_PRINT_TEXT(254+index*34,807,50,20,obj1[pcgInfo].matter13);
				LODOP.ADD_PRINT_TEXT(254+index*34,866,50,20,obj1[pcgInfo].matter14);
				total = parseFloat(obj1[pcgInfo].matter1)+
				parseFloat(obj1[pcgInfo].matter2)+
				parseFloat(obj1[pcgInfo].matter3)+
				parseFloat(obj1[pcgInfo].matter4)+
				parseFloat(obj1[pcgInfo].matter5)+
				parseFloat(obj1[pcgInfo].matter6)+
				parseFloat(obj1[pcgInfo].matter7)+
				parseFloat(obj1[pcgInfo].matter8)+
				parseFloat(obj1[pcgInfo].matter9)+
				parseFloat(obj1[pcgInfo].matter10)+
				parseFloat(obj1[pcgInfo].matter11)+
				parseFloat(obj1[pcgInfo].matter12)+
				parseFloat(obj1[pcgInfo].matter13)+
				parseFloat(obj1[pcgInfo].matter14);
				LODOP.ADD_PRINT_TEXT(254+index*34,931,55,20,total);
				tot[index] = total;
				m1 += parseFloat(obj1[pcgInfo].matter1);
				m2 += parseFloat(obj1[pcgInfo].matter2); 
				m3 += parseFloat(obj1[pcgInfo].matter3);
				m4 += parseFloat(obj1[pcgInfo].matter4); 
				m5 += parseFloat(obj1[pcgInfo].matter5);
				m6 += parseFloat(obj1[pcgInfo].matter6); 
				m7 += parseFloat(obj1[pcgInfo].matter7);
				m8 += parseFloat(obj1[pcgInfo].matter8); 
				m9 += parseFloat(obj1[pcgInfo].matter9);
				m10 += parseFloat(obj1[pcgInfo].matter10); 
				m11 += parseFloat(obj1[pcgInfo].matter11);
				m12 += parseFloat(obj1[pcgInfo].matter12); 
				m13 += parseFloat(obj1[pcgInfo].matter13);
				m14 += parseFloat(obj1[pcgInfo].matter14); 
				index++;
				break;
			}else{
				LODOP.ADD_PRINT_TEXT(254+index*34,147,50,20,"");
				LODOP.ADD_PRINT_TEXT(254+index*34,200,50,20,"");
				LODOP.ADD_PRINT_TEXT(254+index*34,255,50,20,"");
				LODOP.ADD_PRINT_TEXT(254+index*34,308,50,20,"");
				LODOP.ADD_PRINT_TEXT(254+index*34,363,50,20,"");
				LODOP.ADD_PRINT_TEXT(254+index*34,420,50,20,"");
				LODOP.ADD_PRINT_TEXT(254+index*34,476,50,20,"");
				LODOP.ADD_PRINT_TEXT(254+index*34,531,50,20,"");
				LODOP.ADD_PRINT_TEXT(254+index*34,584,50,20,"");
				LODOP.ADD_PRINT_TEXT(254+index*34,640,50,20,"");
				LODOP.ADD_PRINT_TEXT(254+index*34,699,50,20,"");
				LODOP.ADD_PRINT_TEXT(254+index*34,753,50,20,"");
				LODOP.ADD_PRINT_TEXT(254+index*34,807,50,20,"");
				LODOP.ADD_PRINT_TEXT(254+index*34,866,50,20,"");
				LODOP.ADD_PRINT_TEXT(254+index*34,931,55,20,"0");
				tot[index] = 0;
				index++;
			}
		}
	} 
	while(index<12){
		LODOP.ADD_PRINT_TEXT(254+index*34,147,50,20,"");
		LODOP.ADD_PRINT_TEXT(254+index*34,200,50,20,"");
		LODOP.ADD_PRINT_TEXT(254+index*34,255,50,20,"");
		LODOP.ADD_PRINT_TEXT(254+index*34,308,50,20,"");
		LODOP.ADD_PRINT_TEXT(254+index*34,363,50,20,"");
		LODOP.ADD_PRINT_TEXT(254+index*34,420,50,20,"");
		LODOP.ADD_PRINT_TEXT(254+index*34,476,50,20,"");
		LODOP.ADD_PRINT_TEXT(254+index*34,531,50,20,"");
		LODOP.ADD_PRINT_TEXT(254+index*34,584,50,20,"");
		LODOP.ADD_PRINT_TEXT(254+index*34,640,50,20,"");
		LODOP.ADD_PRINT_TEXT(254+index*34,699,50,20,"");
		LODOP.ADD_PRINT_TEXT(254+index*34,753,50,20,"");
		LODOP.ADD_PRINT_TEXT(254+index*34,807,50,20,"");
		LODOP.ADD_PRINT_TEXT(254+index*34,866,50,20,"");
		LODOP.ADD_PRINT_TEXT(254+index*34,931,55,20,"0");
		tot[index] = 0;
		index++;
	}
	LODOP.ADD_PRINT_TEXT(254+index*34,147,50,20,m1);
	LODOP.ADD_PRINT_TEXT(254+index*34,200,50,20,m2);
	LODOP.ADD_PRINT_TEXT(254+index*34,255,50,20,m3);
	LODOP.ADD_PRINT_TEXT(254+index*34,308,50,20,m4);
	LODOP.ADD_PRINT_TEXT(254+index*34,363,50,20,m5);
	LODOP.ADD_PRINT_TEXT(254+index*34,420,50,20,m6);
	LODOP.ADD_PRINT_TEXT(254+index*34,476,50,20,m7);
	LODOP.ADD_PRINT_TEXT(254+index*34,531,50,20,m8);
	LODOP.ADD_PRINT_TEXT(254+index*34,584,50,20,m9);
	LODOP.ADD_PRINT_TEXT(254+index*34,640,50,20,m10);
	LODOP.ADD_PRINT_TEXT(254+index*34,699,50,20,m11);
	LODOP.ADD_PRINT_TEXT(254+index*34,753,50,20,m12);
	LODOP.ADD_PRINT_TEXT(254+index*34,807,50,20,m13);
	LODOP.ADD_PRINT_TEXT(254+index*34,866,50,20,m14);
	total = m1+m2+m3+m4+m5+m6+m7+m8+m9+m10+m11+m12+m13+m14;
	LODOP.ADD_PRINT_TEXT(254+index*34,931,55,20,total);
	tot[index] = total;
	var obj2 = eval($('#pcgInfoJson2').val());
	var year = $('#yearFlag').val()
//	LODOP = getLodop(document.getElementById('LODOP'), document
//			.getElementById('LODOP_EM'));
//	
//	LODOP.PRINT_INITA(0,0,1134,800,"");
	LODOP.NewPageA();//��ҳ
	LODOP.SET_PRINT_PAGESIZE(2,2100,2950,"");
	LODOP.ADD_PRINT_SETUP_BKIMG("<img border='0' src='"+imgUrl+ "/images/actions/22.png' style='z-index: -1'/>");
	LODOP.SET_SHOW_MODE("BKIMG_LEFT",330);
	LODOP.SET_SHOW_MODE("BKIMG_TOP",300);
	LODOP.SET_SHOW_MODE("BKIMG_PRINT",true);
	LODOP.ADD_PRINT_LINE(96,57,94,1002,0,1);
	LODOP.ADD_PRINT_LINE(170,57,169,1002,0,1);
	LODOP.ADD_PRINT_LINE(204,57,203,1002,0,1);
	LODOP.ADD_PRINT_LINE(238,57,237,1002,0,1);
	LODOP.ADD_PRINT_LINE(272,57,271,1002,0,1);
	LODOP.ADD_PRINT_LINE(306,57,305,1002,0,1);
	LODOP.ADD_PRINT_LINE(340,57,339,1002,0,1);
	LODOP.ADD_PRINT_LINE(374,57,373,1002,0,1);
	LODOP.ADD_PRINT_LINE(408,57,407,1002,0,1);
	LODOP.ADD_PRINT_LINE(442,57,441,1002,0,1);
	LODOP.ADD_PRINT_LINE(476,57,475,1002,0,1);
	LODOP.ADD_PRINT_LINE(510,57,509,1002,0,1);
	LODOP.ADD_PRINT_LINE(544,57,543,1002,0,1);
	LODOP.ADD_PRINT_LINE(578,57,577,1002,0,1);
	LODOP.ADD_PRINT_LINE(645,57,646,1002,0,1);
	LODOP.ADD_PRINT_LINE(94,57,645,58,0,1);
	LODOP.ADD_PRINT_LINE(94,144,645,145,0,1);
	LODOP.ADD_PRINT_LINE(94,198,645,199,0,1);
	LODOP.ADD_PRINT_LINE(94,252,645,253,0,1);
	LODOP.ADD_PRINT_LINE(94,306,645,307,0,1);
	LODOP.ADD_PRINT_LINE(94,360,645,361,0,1);
	LODOP.ADD_PRINT_LINE(94,416,645,417,0,1);
	LODOP.ADD_PRINT_LINE(94,474,645,475,0,1);
	LODOP.ADD_PRINT_LINE(94,528,645,529,0,1);
	LODOP.ADD_PRINT_LINE(94,582,645,583,0,1);
	LODOP.ADD_PRINT_LINE(94,637,645,638,0,1);
	LODOP.ADD_PRINT_LINE(94,696,645,697,0,1);
	LODOP.ADD_PRINT_LINE(94,750,645,751,0,1);
	LODOP.ADD_PRINT_LINE(94,804,645,805,0,1);
	LODOP.ADD_PRINT_LINE(94,862,645,863,0,1);
	LODOP.ADD_PRINT_LINE(94,924,645,925,0,1);
	LODOP.ADD_PRINT_LINE(94,1002,645,1003,0,1);
	LODOP.ADD_PRINT_TEXT(733,464,100,20,"2/2");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	LODOP.ADD_PRINT_LINE(94,57,169,144,0,1);
	LODOP.ADD_PRINT_TEXT(106,95,40,25,"��Ʒ");
	LODOP.ADD_PRINT_TEXT(143,64,40,20,"����");
	LODOP.ADD_PRINT_TEXT(103,202,46,60,"24��Ҭ����ͥ����װ");
	LODOP.ADD_PRINT_TEXT(103,150,45,60,"15��Ҭ����ͥ����װ");
	LODOP.ADD_PRINT_TEXT(103,257,45,60,"15���춹��ͥ����װ");
	LODOP.ADD_PRINT_TEXT(103,310,45,60,"20����ζ��ͥ����װ");
	LODOP.ADD_PRINT_TEXT(113,366,45,40,"12��Ҭ�����");
	LODOP.ADD_PRINT_TEXT(103,424,45,60,"12����ζŨ�����");
	LODOP.ADD_PRINT_TEXT(113,480,45,40,"Ҭ�����װ");
	LODOP.ADD_PRINT_TEXT(113,533,45,40,"����Ҭ�����װ");
	LODOP.ADD_PRINT_TEXT(113,588,45,40,"����ζ���װ");
	LODOP.ADD_PRINT_TEXT(122,864,57,25,"С������");
	LODOP.ADD_PRINT_TEXT(122,940,40,22,"�ܼ�");
	LODOP.ADD_PRINT_TEXT(177,68,70,20,$("#yearFlag").val()+'��'+7+'��');
	LODOP.ADD_PRINT_TEXT(211,68,70,20,$("#yearFlag").val()+'��'+8+'��');
	LODOP.ADD_PRINT_TEXT(245,68,70,20,$("#yearFlag").val()+'��'+9+'��');
	LODOP.ADD_PRINT_TEXT(279,68,70,20,$("#yearFlag").val()+'��'+10+'��');
	LODOP.ADD_PRINT_TEXT(313,68,70,20,$("#yearFlag").val()+'��'+11+'��');
	LODOP.ADD_PRINT_TEXT(347,68,70,20,$("#yearFlag").val()+'��'+12+'��');
	LODOP.ADD_PRINT_TEXT(381,68,70,20,(parseInt($("#yearFlag").val())+1)+'��'+1+'��');
	LODOP.ADD_PRINT_TEXT(415,68,70,20,(parseInt($("#yearFlag").val())+1)+'��'+2+'��');
	LODOP.ADD_PRINT_TEXT(449,68,70,20,(parseInt($("#yearFlag").val())+1)+'��'+3+'��');
	LODOP.ADD_PRINT_TEXT(483,68,70,20,(parseInt($("#yearFlag").val())+1)+'��'+4+'��');
	LODOP.ADD_PRINT_TEXT(517,68,70,20,(parseInt($("#yearFlag").val())+1)+'��'+5+'��');
	LODOP.ADD_PRINT_TEXT(551,68,70,20,(parseInt($("#yearFlag").val())+1)+'��'+6+'��');
	LODOP.ADD_PRINT_TEXT(585,68,60,20,"С������(����)");
	var index = 0;
	var total = 0;
	var m1 = 0 ;
	var m2 = 0 ; 
	var m3 = 0 ;
	var m4 = 0 ; 
	var m5 = 0 ;
	var m6 = 0 ; 
	var m7 = 0 ;
	var m8 = 0 ; 
	var m9 = 0 ;
	for(var pcgInfo in obj2){ //�ڶ���ѭ��ȡlist�еĶ��� 
		while(index<12){
			if((parseInt(obj2[pcgInfo].theMonth)-parseInt(index))==7||(parseInt(index)-parseInt(obj2[pcgInfo].theMonth))==5){
				LODOP.ADD_PRINT_TEXT(177+index*34,147,50,20,obj2[pcgInfo].matter1);
				LODOP.ADD_PRINT_TEXT(177+index*34,200,50,20,obj2[pcgInfo].matter2);
				LODOP.ADD_PRINT_TEXT(177+index*34,255,50,20,obj2[pcgInfo].matter3);
				LODOP.ADD_PRINT_TEXT(177+index*34,308,50,20,obj2[pcgInfo].matter4);
				LODOP.ADD_PRINT_TEXT(177+index*34,363,50,20,obj2[pcgInfo].matter5);
				LODOP.ADD_PRINT_TEXT(177+index*34,420,50,20,obj2[pcgInfo].matter6);
				LODOP.ADD_PRINT_TEXT(177+index*34,476,50,20,obj2[pcgInfo].matter7);
				LODOP.ADD_PRINT_TEXT(177+index*34,584,50,20,obj2[pcgInfo].matter8);
				total = parseFloat(obj2[pcgInfo].matter1)+
				parseFloat(obj2[pcgInfo].matter2)+
				parseFloat(obj2[pcgInfo].matter3)+
				parseFloat(obj2[pcgInfo].matter4)+
				parseFloat(obj2[pcgInfo].matter5)+
				parseFloat(obj2[pcgInfo].matter6)+
				parseFloat(obj2[pcgInfo].matter7)+
				parseFloat(obj2[pcgInfo].matter8);
				
				LODOP.ADD_PRINT_TEXT(177+index*34,866,50,20,total);
				total +=tot[index];
				LODOP.ADD_PRINT_TEXT(177+index*34,931,55,20,total);
				m1 += parseFloat(obj2[pcgInfo].matter1);
				m2 += parseFloat(obj2[pcgInfo].matter2); 
				m3 += parseFloat(obj2[pcgInfo].matter3);
				m4 += parseFloat(obj2[pcgInfo].matter4); 
				m5 += parseFloat(obj2[pcgInfo].matter5);
				m6 += parseFloat(obj2[pcgInfo].matter6); 
				m7 += parseFloat(obj2[pcgInfo].matter7);
				m8 += parseFloat(obj2[pcgInfo].matter8);
				index++;
				break;
			}else{
				LODOP.ADD_PRINT_TEXT(177+index*34,866,50,20,"0");
				LODOP.ADD_PRINT_TEXT(177+index*34,931,55,20,tot[index]);
				index++;
			}
		}
	} 
	while(index<12){
		LODOP.ADD_PRINT_TEXT(177+index*34,866,50,20,"0");
		LODOP.ADD_PRINT_TEXT(177+index*34,931,55,20,tot[index]);
		index++;
	}
	LODOP.ADD_PRINT_TEXT(177+index*34,147,50,20,m1);
	LODOP.ADD_PRINT_TEXT(177+index*34,200,50,20,m2);
	LODOP.ADD_PRINT_TEXT(177+index*34,255,50,20,m3);
	LODOP.ADD_PRINT_TEXT(177+index*34,308,50,20,m4);
	LODOP.ADD_PRINT_TEXT(177+index*34,363,50,20,m5);
	LODOP.ADD_PRINT_TEXT(177+index*34,420,50,20,m6);
	LODOP.ADD_PRINT_TEXT(177+index*34,476,50,20,m7);
	LODOP.ADD_PRINT_TEXT(177+index*34,584,50,20,m8);
	total = m1+m2+m3+m4+m5+m6+m7+m8;
	LODOP.ADD_PRINT_TEXT(177+index*34,866,55,20,total);
	total += tot[index];
	LODOP.ADD_PRINT_TEXT(177+index*34,931,55,20,total);
	
	
	
	LODOP.ADD_PRINT_TEXT(651,462,100,20,"�ҷ�ǩ��:");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	LODOP.ADD_PRINT_TEXT(678,465,100,20,"����:");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	LODOP.ADD_PRINT_TEXT(706,466,100,20,"����:");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
}

function CreatePrintPage(){
	
	
}
/*
function getLodop(oOBJECT,oEMBED){
	/**************************
	  ������������������;��������ĸ�������Ϊ�ؼ�ʵ����
	  IEϵ�С�IE�ں�ϵ�е����������oOBJECT��
	  ���������(Firefoxϵ�С�Chromeϵ�С�Operaϵ�С�Safariϵ�е�)����oEMBED��
	**************************//*
	    var LODOP=oOBJECT;
	    
		try{		     
		     if (navigator.appVersion.indexOf("MSIE")>=0) LODOP=oOBJECT;

		     if ((LODOP==null)||(typeof(LODOP.VERSION)=="undefined")) {
			 if (navigator.userAgent.indexOf('Firefox')>=0)
	  	         document.documentElement.innerHTML=strHtml3+document.documentElement.innerHTML;
			 if (navigator.appVersion.indexOf("MSIE")>=0) document.write(strHtml1); else
			 document.documentElement.innerHTML=strHtml1+document.documentElement.innerHTML;
		     } else if (LODOP.VERSION<"6.0.1.0") {
			 if (navigator.appVersion.indexOf("MSIE")>=0) document.write(strHtml2); else
			 document.documentElement.innerHTML=strHtml2+document.documentElement.innerHTML; 
		     }
		     //*****���¿հ�λ���ʺϵ���ͳһ����:*********	     
		     //*******************************************
		     return LODOP; 
		}catch(err){
		     document.documentElement.innerHTML="Error:"+strHtml1+document.documentElement.innerHTML;
		     return LODOP; 
		}
	}*/
/*
<div align="center"><h2 style="text-align:center;font-family:����;" >$!{yearFlag}�꾭����Ʒ��¶�Э��Ŀ����</h2></div>
<table width="100%" cellpadding="0" cellspacing="0px" color="#000000">
		<tr style="height:30px">
				<td style="text-align:center;font-size:12px;font-family:����;color:#000000;border:#000000 solid;border-width:thin thin thin 0 " >
					����/��Ʒ
				</td>
				<td style="text-align:center;font-size:12px;font-family:����;color:#000000;border:#000000 solid;border-width:thin thin thin thin" >
					Ҭ������
				</td>
				<td style="text-align:center;font-size:12px;font-family:����;color:#000000;border:#000000 solid;border-width:thin thin thin 0 " >
					�춹����
				</td>
				<td style="text-align:center;font-size:12px;font-family:����;color:#000000;border:#000000 solid;border-width:thin thin thin 0 " >
					֥ʿ���󵥱�
				</td>
				<td style="text-align:center;font-size:12px;font-family:����;color:#000000;border:#000000 solid;border-width:thin thin thin 0 " >
					�����ɲݵ���
				</td>
				<td style="text-align:center;font-size:12px;font-family:����;color:#000000;border:#000000 solid;border-width:thin thin thin 0 " >
					â����������
				</td>
				<td style="text-align:center;font-size:12px;font-family:����;color:#000000;border:#000000 solid;border-width:thin thin thin 0 " >
					��ݮ����
				</td>
				<td style="text-align:center;font-size:12px;font-family:����;color:#000000;border:#000000 solid;border-width:thin thin thin 0 " >
					ѩŴҬ������
				</td>
				<td style="text-align:center;font-size:12px;font-family:����;color:#000000;border:#000000 solid;border-width:thin thin thin 0 " >
					Ҭ��������
				</td>
				<td style="text-align:center;font-size:12px;font-family:����;color:#000000;border:#000000 solid;border-width:thin thin thin 0" >
					�춹������
				</td>
				<td style="text-align:center;font-size:12px;font-family:����;color:#000000;border:#000000 solid;border-width:thin thin thin 0 " >
					֥ʿ����������
				</td>
				<td style="text-align:center;font-size:12px;font-family:����;color:#000000;border:#000000 solid;border-width:thin thin thin 0 " >
					�����ɲ�������
				</td>
				<td style="text-align:center;font-size:12px;font-family:����;color:#000000;border:#000000 solid;border-width:thin thin thin 0 " >
					â������������
				</td>
				<td style="text-align:center;font-size:12px;font-family:����;color:#000000;border:#000000 solid;border-width:thin thin thin 0 " >
					��ݮ������
				</td>
				<td style="text-align:center;font-size:12px;font-family:����;color:#000000;border:#000000 solid;border-width:thin thin thin 0 " >
					ѩŴҬ��������
				</td>
				<td style="text-align:center;font-size:12px;font-family:����;color:#000000;border:#000000 solid;border-width:thin thin thin 0 " >
					matter6
				</td>
			</tr>
		#foreach($pcgInfo in $pcgInfoList)
		#set($num = $velocityCount%2+1)
		#set($total = $total+1)
		<input type="hidden" id="detailId_$velocityCount" value="" />
		<tr id="tr_$velocityCount" style="height:30px" class="even$num">
			<td style="text-align: center;font-size:12px;font-family:����;color:#000000;border:#000000 solid;border-width:0 thin thin 0">
				$!{pcgInfo.theYear}��$!{pcgInfo.theMonth}��
			</td>
			<td style="text-align: center;font-size:12px;font-family:����;color:#000000;border:#000000 solid;border-width:0 thin thin thin">
				$!{pcgInfo.matter1}
			</td>
			<td style="text-align: center;font-size:12px;font-family:����;color:#000000;border:#000000 solid;border-width:0 thin thin 0">
				$!{pcgInfo.matter2}
			</td>
			<td style="text-align: center;font-size:12px;font-family:����;color:#000000;border:#000000 solid;border-width:0 thin thin 0">
				$!{pcgInfo.matter3}
			</td>
			<td style="text-align: center;font-size:12px;font-family:����;color:#000000;border:#000000 solid;border-width:0 thin thin 0">
				$!{pcgInfo.matter4}
			</td>
			<td style="text-align: center;font-size:12px;font-family:����;color:#000000;border:#000000 solid;border-width:0 thin thin 0">
				$!{pcgInfo.matter5}
			</td>
			<td style="text-align: center;font-size:12px;font-family:����;color:#000000;border:#000000 solid;border-width:0 thin thin 0">
				$!{pcgInfo.matter6}
			</td>
			<td style="text-align: center;font-size:12px;font-family:����;color:#000000;border:#000000 solid;border-width:0 thin thin 0">
				$!{pcgInfo.matter7}
			</td>
			<td style="text-align: center;font-size:12px;font-family:����;color:#000000;border:#000000 solid;border-width:0 thin thin 0">
				$!{pcgInfo.matter8}
			</td>
			<td style="text-align: center;font-size:12px;font-family:����;color:#000000;border:#000000 solid;border-width:0 thin thin 0">
				$!{pcgInfo.matter9}
			</td>
			<td style="text-align: center;font-size:12px;font-family:����;color:#000000;border:#000000 solid;border-width:0 thin thin 0">
				$!{pcgInfo.matter10}
			</td>
			<td style="text-align: center;font-size:12px;font-family:����;color:#000000;border:#000000 solid;border-width:0 thin thin 0">
				$!{pcgInfo.matter11}
			</td>
			<td style="text-align: center;font-size:12px;font-family:����;color:#000000;border:#000000 solid;border-width:0 thin thin 0">
				$!{pcgInfo.matter12}
			</td>
			<td style="text-align: center;font-size:12px;font-family:����;color:#000000;border:#000000 solid;border-width:0 thin thin 0">
				$!{pcgInfo.matter13}
			</td>
			<td style="text-align: center;font-size:12px;font-family:����;color:#000000;border:#000000 solid;border-width:0 thin thin 0">
				$!{pcgInfo.matter14}
			</td>
			<td style="text-align: center;font-size:12px;font-family:����;color:#000000;border:#000000 solid;border-width:0 thin thin 0">
				$!{pcgInfo.matter6}
			</td>
		</tr>
		#end
</table> 
</div>

*/