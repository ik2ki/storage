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

#ifndef __MOTHER__H
class Mother:public Ship{
	private:
		char shape[6]; //글자 3개로는 안되겠네요
		char *name;
	public:
		Mother();
		Mother(int lx, int ly, int ab_x, int ab_y);
		void special_skill(){
		}
		char *setName(){ return name; }
		bool attacked(int x, int y);
		void OnTimer(int id);
		void position_move(int x, int y);	//배의 절대 위치가 움직이는 것을 계산해주는 것을 기대하는 메소드
		void attack_draw(int y_at_x,int y_at_y);	//배의 공격을 그려줍니다.
		void attack_erase(int y_at_x, int y_at_y);
		bool can_demage(); //배가 공격을 받았는지 리턴해 줄걸로 기대하는 메소드
		void myshape();	//나의 형태를 그려주세요.
		void myshape(int , int);	//나의 형태를 그려주세요.
		void shade_myshape();	//나의 형태를 지워주세요.
		void shade_myshape(int,int);	//나의 형태를 지워주세요.
};
#define __MOTHER__H
#endif
