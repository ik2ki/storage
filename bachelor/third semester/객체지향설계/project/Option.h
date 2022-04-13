#ifndef __CURSES__H
#include <curses.h>
#define __CURSES__H
#endif

#ifndef __IOSTREAM__
#include <iostream>
#define __IOSTREAM__
#endif

#ifndef __ALLMESSAGE__H
#include "Allmessage.h"
#define __ALLMESSAGE__H
#endif

#ifndef __OPTION__H

#include <iostream>

class Option{
	public:
		void wait(int n);	//sleep n 초만큼 침묵
		void hid_cur();		//curs_set(0) 커서가 안보여요
		void see_cur();		//curs_set(1) 커서가 보이네요
		void clean();		//화면을 청소해 볼까요
		void battle_clean();
		void state_clean();
};
#define __OPTION__H
#endif
