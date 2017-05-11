var LODOP;

function Preview() {
	CreatePrintPage();
	LODOP.PREVIEW();	
};

function Print(){
	CreatePrintPage();
	LODOP.PRINT();
}

function Setup() {		
	CreatePrintPage();
	LODOP.PRINT_SETUP();		
};
	
function Design() {		
	CreatePrintPage();
	LODOP.PRINT_DESIGN();		
};

function CreatePrintPage() {
	LODOP=getLodop(document.getElementById('LODOP'),document.getElementById('LODOP_EM'));  
	LODOP.PRINT_INITA(10,10,775,460,"支付信息");	
	LODOP.ADD_PRINT_TEXT(30,576,144,22,"页号：第#页/共&页");
	LODOP.SET_PRINT_STYLEA(1,"ItemType",2);
	LODOP.SET_PRINT_STYLEA(1,"HOrient",1);
	LODOP.ADD_PRINT_HTM(65,20,700,335,document.getElementById("contentForm").innerHTML);
	LODOP.SET_PRINT_STYLEA(2,"FontSize",15);
	LODOP.SET_PRINT_STYLEA(2,"ItemType",4);
	LODOP.SET_PRINT_STYLEA(2,"HOrient",3);
	LODOP.SET_PRINT_STYLEA(2,"VOrient",3);
	LODOP.ADD_PRINT_LINE(53,23,52,725,0,1);
	LODOP.SET_PRINT_STYLEA(3,"ItemType",1);
	LODOP.SET_PRINT_STYLEA(3,"HOrient",3);
	LODOP.ADD_PRINT_LINE(414,23,413,725,0,1);
	LODOP.SET_PRINT_STYLEA(4,"ItemType",1);
	LODOP.SET_PRINT_STYLEA(4,"HOrient",3);
	LODOP.SET_PRINT_STYLEA(4,"VOrient",1);
	LODOP.ADD_PRINT_TEXT(30,33,200,22,"支付信息");
	LODOP.SET_PRINT_STYLEA(5,"ItemType",1);
};	

