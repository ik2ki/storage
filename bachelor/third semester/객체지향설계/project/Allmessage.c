#include "Allmessage.h"

extern Ccurses cc;

Allmessage::Allmessage(int lx, int ly, int sx, int sy):loc_x(lx),loc_y(ly),size_x(sx),size_y(sy){
	alignment = LEFT; // x좌표 y좌표 크기를 받아서 객체의 x좌표 y좌표 x크기 y크기를 받아서 생성하고 문자의 출력은 왼쪽으로
}

Allmessage::Allmessage(){ //아무것도 받지않고 생성되는 Allmessage
}

Allmessage::~Allmessage(){
	/*if(label){
		free(label);
	}*/
}
bool Allmessage::included(int px, int py){ //한 x점과 y점을 받아서 그 점이 Opening 상자 안에 있는지 판단하는 included함수
	return (px>=loc_x&&px<loc_x+size_x&&py>=loc_y&&py<loc_y+size_y);
}

void Allmessage::draw(){
	cc.drawText(loc_x, loc_y, "+");
	cc.drawText(loc_x+size_x-1, loc_y, "+");
	cc.drawText(loc_x, loc_y+size_y-1, "+");
	cc.drawText(loc_x+size_x-1, loc_y+size_y-1, "+");
	cc.drawHLine(loc_x+1, loc_y, size_x-2);
	cc.drawHLine(loc_x+1, loc_y+size_y-1, size_x-2); 
	cc.drawVLine(loc_x, loc_y+1, size_y-2);
	cc.drawVLine(loc_x+size_x-1, loc_y+1, size_y-2);
}


void Allmessage::align(int a){ //왼쪽 중간 오른쪽을 판단하여 alignment에 넣어주는 align함수
	if(a>0 && a<=3)
		alignment = a;
}

void Allmessage::setText(char *s){ //좌표없이 setText만이 주어졌을때에 문자형 포인터를 출력하는 setText함수
	label=s;
}

void Allmessage::setText(int lx,int ly, char *s){ //좌표가 주어졌을때에 좌표에 글자를 출력하는 setText함수
	label=s;
	cc.drawText(lx,ly,label);
}

void Allmessage::setText(int lx, int ly, char c){	//좌표와 글시가 주어졌을때 자표에 글자를 출력하는 setText함수
	cc.drawText(lx,ly,c);
}
