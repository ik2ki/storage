#include <curses.h>
#include <stdio.h>
#ifndef __TIMER_H
#define __TIMER_H

#include <sys/time.h>
#include <sys/types.h>
#include <unistd.h>
#include <signal.h>
#include <stdlib.h>

#include "Ship.h"
#include "TimeList.h"

void timerHandler(int n);  // forward declaration

class Timer{
  private:
	T_Stack tlist;
	unsigned int counter;
    struct itimerval timerVal;
	int basic_interval;
	bool running;
  public:
	void handler();
	void settimer(Ship *, int ticks, int timerid);
	void killtimer(Ship *, int id);
	Timer(int interval);
};

#endif
