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

#ifndef __CURSES__H
#include "curses.h"
#define __CURSES__H
#endif

#ifndef __SHIP__H

class Ship{
	protected:
		char attack_pic;	//공격 모습
		bool alive;	//생존 여부 
		int point_x;	//보이는 맵에 위치하는 x좌표 이걸로
		int point_y;	//보이는 맵에 위치하는 y좌표 이걸로
		int at_point_x;	//지도상에 위치하는 절대 위치 x좌표 이걸로 공격 가능성을 계산함
		int at_point_y;	//지도상에 위치하는 절대 위치 y좌표	이걸로 공격 가능성을 계산함
		int move;	//이동 가능 범위
		int sight;	//시야 
		int size;	//배의 크기
		int health_point;	//배의 생존 포인트
		int depence_point;	//배의 방어력
		int attack_point;	//배의 공격력
		int basic_move;		//기본 이동력
		int attack_length;	//공격 가능 범위
		int damege_count;	//피격 개수
	public:
		Ship();
		Ship(int at_x,int at_y):at_point_x(at_x),at_point_y(at_y){}
		Ship(int x, int y, int lx, int ly);	//배의 위치와 상대 위치 절대 위치를 받아서 생성되는 생성자
		int at_x_return(){	//절대좌표x 리턴
			return at_point_x;
		}
		int at_y_return(){	//절대좌표y 리턴
			return at_point_y;
		}
		int size_return(){	//배의 크기 size리턴
			return size;
		}
		int length_return(){	//배의 공격범위 리턴
			return attack_length;
		}
		int hp_return(){
			return health_point;
		}
		int attack(){
			return attack_point;
		}
		void damege(int attack_point){
			health_point=health_point-attack_point;
			if(health_point<0){
				alive=false;
			}
		}
		int count(){
			return damege_count;
		}
		int move_return(){
			return move;
		}
		int point_x_return(){
			return point_x;
		}
		int point_y_return(){
			return point_y;
		}
		void set_count_zero(){
			damege_count=0;
		}
		void change_count(){
			damege_count=damege_count+1;
		}
		virtual void special_skill(){}
		virtual bool attacked(int x, int y){}
		virtual void OnTimer(int id){}
		virtual void myshape(){}
		virtual void myshape(int , int ){}
		virtual void shade_myshape(){}
		virtual void shade_myshape(int, int ){}
		virtual void position_move(){}	//배의 절대 위치가 움직이는 것을 계산해주는 것을 기대하는 가상의 메소드
		virtual void position_move(int,int){}	//배의 절대 위치가 움직이는 것을 계산해주는 것을 기대하는 가상의 메소드
		virtual void attack_draw(int y_at_x,int y_at_y){}	//배의 공격을 그려줍니다.
		virtual void attack_erase(int y_at_x, int y_at_y){}
};
#define __SHIP__H
#endif
