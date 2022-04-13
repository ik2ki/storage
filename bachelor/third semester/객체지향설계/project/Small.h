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

#ifndef __TIMER__H
#include "timer.h"
#define __TIMER__H
#endif

#ifndef __OPTION__H
#include "Option.h"
#define __OPTION__H
#endif

#ifndef __SMALL__H

extern Ccurses cc;
extern Timer the_timer;
extern Option op;

class Small:public Ship{
	private:
		char shape;	//작아서 글짜 하나로 생성된데요.
		char *name;
	public:
		Small();
		Small(int lx, int ly, int ab_x, int ab_y);	//절대경로를 받아서 생성되는 소형선 객체
		~Small(){
			the_timer.killtimer(this,-1);
		}
		void special_skill(){
			Allmessage *am;
			am->setText(point_x,point_y,"*");
			am->setText(point_x-1,point_y-1,"***\n***\n***\n");
			health_point=-1;
			alive=false;
			refresh();
		}
		char *setName(){ return name; }
		bool attacked(int x,int y);
		bool can_demage(int, int);
		void OnTimer(int id);
		void shade_myshape();	//나의 형태를 지워주세요.
		void shade_myshape(int,int);	//나의 형태를 지워주세요.
		void position_move(int x, int y);	//배의 절대 위치가 움직이는 것을 계산해주는 것을 기대하는 메소드
		void set_x_position(int);
		void set_y_position(int);
		void attack_draw(int y_at_x,int y_at_y);	//배의 공격을 그려줍니다.
		void attack_erase(int y_at_x, int y_at_y);
		bool can_demage(); //배가 공격을 받았는지 리턴해 줄걸로 기대하는 메소드
		void myshape();	//나의 형태를 그려주세요.
		void myshape(int , int);	//나의 형태를 그려주세요.
};
#define __SMALL__H
#endif
