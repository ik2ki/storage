#ifndef __CCURSES__H
#include "Ccurses.h"
#define __CCURSES__H
#endif

#ifndef __IOSTREAM__
#include <iostream>
#define __IOSTREAM__
#endif

#ifndef __STDLIB__H
#include <stdlib.h>
#define __STDLIB__H
#endif

#ifndef __TIME__H
#include <time.h>
#define __TIME__H
#endif

#ifndef __OPTION__H
#include "Option.h"
#define __OPTION__H
#endif

#ifndef __ALLMESSAGE__H
#include "Allmessage.h"
#define __ALLMESSAGE__H
#endif

#ifndef __OPENING__H


class Opening{
	private:
			 char *game_start; //게임시작 문자열
			 char *start_screen[18]; //게임 오프닝출력메세지
			 char *game_end; //게임 종료 문자열
			 int in_roof,out_roof; //별출력시 반복문
			 int startx,starty; //별출력시 좌표
			 int select_y,select_x;	//게임 시작 종료를 판단할
			 int roof_title; //타이틀 화면 출력의 반복문 제어 변수
	public:
			 Opening(); //오프닝객체의 생성자
			 ~Opening(); //오프닝 객체의 소멸자 
			 void start_message();	//게임 시작 메세지 출력
			 void end_message(); //게임종료 메세지 출력
			 void title();	//제목출력
			 void print_star(); //별출력
			 int select(); //오프닝창의 선택을 알려줄 select함수
			 void end();
			 void window_close();
			 void clean();
}; //객체정의

#define __OPENING__H
#endif
