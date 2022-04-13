#ifndef __ALLMESSAGE__H
#include "Allmessage.h"
#define __ALLMESSAGE__H
#endif

#ifndef __IOSTREAM__
#include <iostream>
#define __IOSTREAM__
#endif

#ifndef __STDLIB__H
#include <stdlib.h>
#define __STDLIB__H
#endif

#ifndef __CCURSES__H
#include "Ccurses.h"
#define __CCURSES__H
#endif

#ifndef __OPTION__H
#include "Option.h"
#define __OPTION__H
#endif

#ifndef __PLAYER__H
#include "Player.h"
#define __PLAYER__H
#endif

#ifndef __ENERMY__H
#include "Enermy.h"
#define __ENERMY__H
#endif

#ifndef __SHIPLIST__H
#include "ShipList.h"
#define __SHIPLIST__H
#endif

#ifndef __ATTACKLINE__H
class AttackLine{
	public:
		AttackLine();	//전열 객체의 생성자
		int select();	//전열을 선택해볼까요?
		void gather_line();	//네모 선택시 상자UI출력
		void spread_line(); //선 선택시 상자UI출력
		void pass_line();	//세모 선택시 상자UI출력
};
#define __ATTACKLINE__H
#endif
