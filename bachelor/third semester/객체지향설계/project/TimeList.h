#ifndef __IOSTREAM__
#include <iostream>
#define __IOSTREAM__
#endif

#ifndef __SHIP__H
#include "Ship.h"
#define __SHIP__H
#endif

#ifndef __TIMELIST__H

using namespace std;
class T_Stack{
	private:
		class Link{
			public:
				Ship *item;
				int interval; // 10ms 의 배수.
				int tm_id;  // timer id
				Link *next;
				Link(Ship *i,int iv,int id,Link *n):item(i),next(n),interval(iv),tm_id(id){}
		};
		private:
			Link *start;
	public:
		class iterator{
			private:
				Link *link;
			public:
				iterator():link(0){}
				iterator(Link *l):link(l){}
			void operator ++(){ link = link->next; }
			bool operator !=(iterator i) { return this->link != i.link; }
			Link * operator *() { return link ; }
		};

		iterator begin(){ return iterator(start->next); }
		iterator end (){ return iterator(0); }

		T_Stack(){
			start= new Link(0,0,0,0); 
		}

		void add(Ship *item, int iv, int id){
			Link *l = new Link(item, iv, id, start->next);
			start->next = l;
		}

		void remove(Ship *s, int id){
			Link *t, *temp;
			for (t=start; t->next; ){
				if (t->next->item == s && (id<=0 || t->next->tm_id == id) ){
					temp = t->next;
					t->next = t->next->next;
					//delete temp;  // 문제가 있어서 당분간 그냥 둔다.
					if (id<=0) continue;
					else break;
				} else {
					t = t->next;
				}
			}
		}
		Ship *top(){
			return start->item;
		}
		bool isempty(){
			return start->next==0;
		}
		void pop(){
			if(start) start=start->next;
		}
		virtual void position_move(){}	//배의 절대 위치가 움직이는 것을 계산해주는 것을 기대하는 가상의 메소드
		virtual void position_move(int,int){}	//배의 절대 위치가 움직이는 것을 계산해주는 것을 기대하는 가상의 메소드
		void can_attack(Ship *center);
};
#define __TIMELIST__H
#endif
