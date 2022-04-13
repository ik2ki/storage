#ifndef __CCURSES__H
#include "Ccurses.h"
#define __CCURSES__H
#endif

#ifndef __IOSTREAM__
#include <iostream>
#define __IOSTREAM__
#endif

#ifndef __ALLMESSAGE__H
#include "Allmessage.h"
#define __ALLMESSAGE__H
#endif

#ifndef __SHIP__H
#include "Ship.h"
#define __SHIP__H
#endif

#ifndef __OPTION__H
#include "Option.h"
#define __OPTION__H
#endif

#ifndef __MIDDLE__H
class Middle:public Ship{
	private:
		char shape[2];	//글자 하나로는 안되겠네요.
		char *name;
	public:
		Middle();
		Middle(int lx, int ly, int ab_x, int ab_y);	//절대경로를 받아서 생성되는 중형선 객체
		void special_skill(){
			depence_point=depence_point+200;
		}
		char *setName(){ return name; }
		void OnTimer(int id);
		bool attacked(int x, int y);
		void attack_draw(int y_at_x,int y_at_y);	//배의 공격을 그려줍니다.
		void attack_erase(int y_at_x, int y_at_y);
		void position_move(int x, int y);	//배의 절대 위치가 움직이는 것을 계산해주는 것을 기대하는 메소드
		void myshape();		//나의 형태를 그려주세요.
		void myshape(int, int);	//나의 형태를 그려주세요
		void shade_myshape();	//나의 형태를 지워주세요.
		void shade_myshape(int,int);	//나의 형태를 지워주세요.
};
#define __MIDDLE__H
#endif
