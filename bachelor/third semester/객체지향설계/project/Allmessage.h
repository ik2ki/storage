
#ifndef __CCURSES__H
#include "Ccurses.h"
#define __CCURSES__H
#endif

#ifndef __IOSTREAM__
#include <iostream>
#define __IOSTREAM__
#endif

#ifndef __ALLMESSAGE__H

class Allmessage{
	private:
		int loc_x, loc_y,size_x, size_y;
		int alignment;
		char *label;
	public:
		static const int LEFT=1;	//왼쪽
		static const int CENTER=2;	//중앙
		static const int RIGHT=3;	//오른쪽
		Allmessage(int x, int y , int sx, int sy);
		Allmessage();
		~Allmessage();
		bool included(int px, int py);
		void draw(); //창을 그려줄 draw함수
		void setText(char *);	//문자열 포인터를 받아서 출력을 할 setText함수
		void setText(int, int, char*); //문자열 포인터와 위치를 받아서 출력을 할 setText함수
		void setText(int, int, char);
		void align(int);// 좌우 출력 중앙출력
};

#define __ ALLMESSAGE__H
#endif
