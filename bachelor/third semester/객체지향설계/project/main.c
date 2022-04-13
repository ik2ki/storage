#include "Ccurses.h"
#include "Opening.h"
#include "Story.h"
#include "Ship.h"
#include "Small.h"
#include "Middle.h"
#include "Large.h"
#include "Approach.h"
#include "Longdistance.h"
#include "Mother.h"
#include "Patrol.h"
#include "TimeList.h"
#include "ShipList.h"
#include "Player.h"
#include "Enermy.h"
#include "timer.h"
#include "AttackLine.h"
#include "SelectUnite.h"
#include "Bridge.h"
#include <iostream>
#include <stdlib.h>
#include <time.h>
#define END 0

Timer the_timer(50000);

Player p;
Enermy e;
Ccurses cc;
Option op;
Allmessage am;

void timerHandler(int n){
	the_timer.handler();
}

int main(void){
	Opening *opening = new Opening();	//Opeing 포인터 타입의 opening생성
	opening->print_star();		//별출력
	opening->title();			//게임 제목 출력
	opening->start_message();	//게임 시작과 시작 창 출력
	opening->end_message();		//게임 종료와 종료창 출력
	if(opening->select()==END){
		opening->window_close();	//window닫음
		opening->end();	//종료
	}
	else{
		opening->clean();			//오프닝이 끝난후 창을깨끗이 지운다
		delete opening;				
		Story *story = new Story();	//Story 포인터 타입의 story 생성
		story->print_story_star();	//story의 별출력을 실행한다.
		story->story_message();		//오프닝 스토리 내용을 실행한다.
		story->skip_story();		//s키를누르면 다음으로 넘어간다.
		story->clean();	//게임창을 깨끗이 한다.
	}
	SelectUnite *su=new SelectUnite();
	if(su->select()==true){
		op.battle_clean();
		AttackLine *al;
		int select_line=al->select();
		if(select_line==1){
			op.battle_clean();
			al->gather_line();
		}
		else if(select_line==2){
			op.battle_clean();
			al->pass_line();
		}
		else if(select_line==3){
			op.battle_clean();
			al->spread_line();
		}
		op.state_clean();
		op.battle_clean();
		op.hid_cur();
	}
	else{ 
		op.battle_clean();
		AttackLine *al;
		int select_line=al->select();
		if(select_line==1){
			op.battle_clean();
			al->gather_line();
		}
		else if(select_line==2){
			op.battle_clean();
			al->pass_line();
		}
		else if(select_line==3){
			op.battle_clean();
			al->spread_line();
		}
		op.state_clean();
		op.battle_clean();
		op.hid_cur();
	}

	int tim=0;
	Bridge b;
	int x,y;
	int c;
	x=y=0;
	srand(time(NULL));
	cc.moveto(0,0);
	while((c=getch())!='.'){
		switch(c){
			case KEY_UP: 
			p.move(0,-1);
			if(rand()%2==0){
				e.move(0,1);
			}
			else if(rand()%2==1){
				e.move(0,-1);
			}
			break;
			case KEY_DOWN: 
			p.move(0,1);
			if(rand()%2==0){
				e.move(0,1);
			}
			else if(rand()%2==1){
				e.move(0,-1);
			}
			break;
			case KEY_LEFT: 
			p.move(-1,0);
			if(rand()%2==0){
				e.move(1,0);
			}
			else if(rand()%2==1){
				e.move(-1,0);
			}
			break;
			case KEY_RIGHT: 
			p.move(1,0);
			if(rand()%2==0){
				e.move(1,0);
			}
			else if(rand()%2==1){
				e.move(-1,0);
			}
			break;
			case 's':
				p.copy_init();
				for(int i=0;i<10;i++){
					Ship *temp=p.copy_top();
					temp->special_skill();
					p.copy_pop();
				}
			break;
			case 'm':
			break;
			case 'e':
			op.battle_clean();
			break;
		}
		b.all_decision();
		move(y,x);
		refresh();
		if(tim%2==0){
			op.battle_clean();
		}
		tim++;
	}
	getch();
}
