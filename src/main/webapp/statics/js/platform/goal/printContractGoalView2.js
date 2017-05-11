//兰芳园牛如茶打印
var tot = new Array(13); 
function print(){
	var yearFlag=$('#yearFlag').val();
	var custId=$('#custId').val();
	var WWidth = 950;
	var WLeft = Math.ceil((window.screen.width - WWidth) / 2);
	var win = window.open(appUrl + '/goal/goalAction!printContractGoal2.jspa?yearFlag='+yearFlag+'&custId='+custId,"eventPrint","left=" + WLeft + ",top=20" + ",width=" + WWidth + 
",height="
			+ (window.screen.height - 100)
			+ ",toolbar=no,rolebar=no,scrollbars=yes,location=no,menubar=no,resizable=yes,titlebar=no");
}

var LODOP;
function Preview() {
	CreatePrintPage2();
	LODOP.PREVIEW();
};

function Print(){
	CreatePrintPage2();
	LODOP.PRINT();
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

function CreatePrintPage2() {
	var obj2 = eval($('#pcgInfoJson2').val());
	var year = $('#yearFlag').val();
	LODOP = getLodop();
	LODOP.PRINT_INITA(0,0,1134,800,"");
	LODOP.SET_PRINT_PAGESIZE(2,2100,2950,"");
	LODOP.ADD_PRINT_SETUP_BKIMG("<img border='0' src='"+imgUrl+ "/images/brand/lfymeco2.png' style='z-index: -1'/>");
	LODOP.SET_SHOW_MODE("BKIMG_LEFT",330);
	LODOP.SET_SHOW_MODE("BKIMG_TOP",230);
	LODOP.SET_SHOW_MODE("BKIMG_PRINT",true);
	LODOP.ADD_PRINT_TEXT(40,345,365,33,$("#yearFlag").val()+"年经销品项及月度协议销售目标量");
	LODOP.SET_PRINT_STYLEA(0,"FontSize",14);
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	LODOP.SET_PRINT_STYLEA(0,"Bold",1);
	LODOP.ADD_PRINT_LINE(94,57,94,1002,0,1);
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
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	LODOP.ADD_PRINT_LINE(94,57,169,144,0,1);
	LODOP.ADD_PRINT_TEXT(106,95,40,25,"产品");
	LODOP.ADD_PRINT_TEXT(143,64,40,20,"年月");
	LODOP.ADD_PRINT_TEXT(113,150,45,60,"丝袜奶茶1*15");
	LODOP.ADD_PRINT_TEXT(103,202,60,60,"MECO原味牛乳茶(即饮型)1*15");
//	LODOP.ADD_PRINT_TEXT(103,257,45,60,"");
//	LODOP.ADD_PRINT_TEXT(103,310,45,60,"");
//	LODOP.ADD_PRINT_TEXT(113,366,45,40,"");
//	LODOP.ADD_PRINT_TEXT(103,424,45,60,"");
//	LODOP.ADD_PRINT_TEXT(113,480,45,40,"");
//	LODOP.ADD_PRINT_TEXT(113,533,45,40,"");
//	LODOP.ADD_PRINT_TEXT(113,588,45,40,"");
//	LODOP.ADD_PRINT_TEXT(122,864,57,25,"小计数量");
	LODOP.ADD_PRINT_TEXT(122,940,40,22,"总计");
	LODOP.ADD_PRINT_TEXT(177,68,70,20,$("#yearFlag").val()+'年'+1+'月');
	LODOP.ADD_PRINT_TEXT(211,68,70,20,$("#yearFlag").val()+'年'+2+'月');
	LODOP.ADD_PRINT_TEXT(245,68,70,20,$("#yearFlag").val()+'年'+3+'月');
	LODOP.ADD_PRINT_TEXT(279,68,70,20,$("#yearFlag").val()+'年'+4+'月');
	LODOP.ADD_PRINT_TEXT(313,68,70,20,$("#yearFlag").val()+'年'+5+'月');
	LODOP.ADD_PRINT_TEXT(347,68,70,20,$("#yearFlag").val()+'年'+6+'月');
	LODOP.ADD_PRINT_TEXT(381,68,70,20,$("#yearFlag").val()+'年'+7+'月');
	LODOP.ADD_PRINT_TEXT(415,68,70,20,$("#yearFlag").val()+'年'+8+'月');
	LODOP.ADD_PRINT_TEXT(449,68,70,20,$("#yearFlag").val()+'年'+9+'月');
	LODOP.ADD_PRINT_TEXT(483,68,70,20,$("#yearFlag").val()+'年'+10+'月');
	LODOP.ADD_PRINT_TEXT(517,68,70,20,$("#yearFlag").val()+'年'+11+'月');
	LODOP.ADD_PRINT_TEXT(551,68,70,20,$("#yearFlag").val()+'年'+12+'月');
	LODOP.ADD_PRINT_TEXT(585,68,60,20,"总计销量(标箱)");
	var index = 0;
	var total = 0; //小计
	var Total = 0; //总数
	var m1 = 0 ;
	var m2 = 0 ; 
	for(var pcgInfo in obj2){ //第二层循环取list中的对象 
		while(index<12){
			if(parseInt(obj2[pcgInfo].theMonth)==(parseInt(index)+1)){
				LODOP.ADD_PRINT_TEXT(177+index*34,147,50,20,obj2[pcgInfo].matter1);
				LODOP.ADD_PRINT_TEXT(177+index*34,200,50,20,obj2[pcgInfo].matter2);
				total = parseFloat(obj2[pcgInfo].matter1)+parseFloat(obj2[pcgInfo].matter2);
				LODOP.ADD_PRINT_TEXT(177+index*34,931,50,20,total);
				m1 += parseFloat(obj2[pcgInfo].matter1);
				m2 += parseFloat(obj2[pcgInfo].matter2); 
				index++;
				break;
			}else{
				LODOP.ADD_PRINT_TEXT(177+index*34,931,50,20,"0");
				index++;
			}
		}
	} 
	while(index<12){
		LODOP.ADD_PRINT_TEXT(177+index*34,931,50,20,"0");
		index++;
	}
	LODOP.ADD_PRINT_TEXT(177+index*34,147,50,20,m1);
	LODOP.ADD_PRINT_TEXT(177+index*34,200,50,20,m2);

	total = m1+m2;
	LODOP.ADD_PRINT_TEXT(177+index*34,931,55,20,total);

	
	
	
	LODOP.ADD_PRINT_TEXT(651,462,100,20,"乙方签字:");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	LODOP.ADD_PRINT_TEXT(678,465,100,20,"盖章:");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
	LODOP.ADD_PRINT_TEXT(706,466,100,20,"日期:");
	LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
}

function CreatePrintPage(){
	
	
}
