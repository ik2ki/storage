#include "Option.h"

void Option::wait(int n){
	sleep(n);
}

void Option::hid_cur(){
	curs_set(0);
}

void Option::see_cur(){
	curs_set(1);
}

void Option::clean(){
	clear();	
}

void Option::battle_clean(){
	Allmessage *am;
	for(int battle_x=1;battle_x<156;battle_x++){
		for(int battle_y=1;battle_y<65;battle_y++){
			am->setText(battle_x,battle_y,' ');
		}
	}
}

void Option::state_clean(){
	Allmessage *am;
	for(int battle_x=158;battle_x<198;battle_x++){
		for(int battle_y=0;battle_y<65;battle_y++){
			am->setText(battle_x,battle_y,' ');
		}
	}

}
