#ifndef __IOSTREAM__
#include <iostream>
#define __IOSTREAM__
#endif

#ifndef __SHIP__H
#include "Ship.h"
#define __SHIP__H
#endif

#ifndef __TIMER__H
#include "timer.h"
#define __TIMER__H
#endif

#ifndef __SHIPLIST__H

extern Timer the_timer;
using namespace std;
class Stack{
	private:
		class Link{
			public:
				Ship *item;
				Link *next;
				Link(Ship *i, Link *n):item(i),next(n){}
		};
		Link *sp;
		Link *temp;
	public:
		Stack(){ sp=0; }
		void add(Ship *item){
			the_timer.settimer(item,10,1);
			the_timer.settimer(item,40,2);
			the_timer.settimer(item,30,4);
			Link *l = new Link(item,sp);
			sp=l;
			temp=l;
		}
		Ship *top(){
			return sp->item;
		}
		Ship *copy_top(){
			return temp->item;	
		}
		void copy_pop(){
			if(temp) temp=temp->next;
		}
		void copy_init(){
			temp=sp;
		}
		bool is_empty(){
			return sp==0;
		}
		void pop(){
			if(sp) sp=sp->next;
		}
		virtual void position_move(){}	//배의 절대 위치가 움직이는 것을 계산해주는 것을 기대하는 가상의 메소드
		virtual void position_move(int,int){}	//배의 절대 위치가 움직이는 것을 계산해주는 것을 기대하는 가상의 메소드
		void can_attack(Ship *center);
		void move(int, int);
};
#define __SHIPLIST__H
#endif
