#include "Bridge.h"

extern Player p;
extern Enermy e;

Bridge::Bridge(){
}

void Bridge::all_decision(){
	p.copy_init();
	e.copy_init();
	for(int i=0;i<10;i++){
		e.can_attack(p.copy_top());
		p.can_attack(e.copy_top());
		p.copy_pop();
		e.copy_pop();
	}
}
