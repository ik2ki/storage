#include "timer.h"
void Timer::handler(){
	T_Stack::iterator i;
	for (i=tlist.begin(); i != tlist.end(); ++i){
		if (counter % (*i)->interval == 0){
			(*i)->item->OnTimer((*i)->tm_id);
		}
	}
#undef DEBUG
#ifdef DEBUG
	tlist.print();
#endif
	counter++;
}
void Timer::settimer(Ship *o, int ticks, int timerID){
	tlist.add(o, ticks, timerID);
	if (!running){
		struct itimerval tv;
		tv.it_interval.tv_sec = 0;
		tv.it_interval.tv_usec = basic_interval; 
		tv.it_value.tv_sec = 0;
		tv.it_value.tv_usec = basic_interval;
		setitimer(ITIMER_REAL, &tv, &tv);
		signal(SIGALRM, timerHandler);
		running = true;
	}
}
void Timer::killtimer(Ship *o, int tmid){
	tlist.remove(o, tmid);
	if (tlist.isempty()){
		signal(SIGALRM, SIG_DFL);
		running = false;
	}
}
Timer::Timer(int interval){
	counter = 0; 
	running = false;
	this->basic_interval = interval;
}
