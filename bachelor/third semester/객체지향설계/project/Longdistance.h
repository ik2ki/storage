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

#ifndef __LONGDISTANCE__H
class Longdistance:public Ship{
		private: 
			char shape[3];	//글자 두개로는 안되겠네요.
			char *name;
		public:
		Longdistance();
		Longdistance(int lx, int ly, int ab_x, int ab_y);	//절대경로로 받아서 생성되는 장거리포객체
		void special_skill(){
			this->attack_length=20;
		}
		char *setName(){ return name; }
		bool attacked(int x, int y);
		void OnTimer(int id);
		void position_move(int x, int y);	//배의 절대 위치가 움직이는 것을 계산해주는 것을 기대하는 메소드
		void myshape();	//나의 형태를 그려주세요.
		void myshape(int , int);	//나의 형태를 그려주세요.
		void attack_draw(int y_at_x,int y_at_y);	//배의 공격을 그려줍니다.
		void attack_erase(int y_at_x, int y_at_y);
		void shade_myshape();	//나의 형태를 지워주세요.
		void shade_myshape(int,int);	//나의 형태를 지워주세요.
};
#define __LONGDISTANCE__H
#endif
