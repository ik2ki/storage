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

#ifndef __STDIO__H
#include <stdio.h>
#define __STDIO__H
#endif

#define FREE 1
#define EMPIRE 2

#ifndef __SELECT_UNITE__H
class SelectUnite{
	private:
		char *Free[43];
		char *Empire[43];
	public:
		SelectUnite();
		bool select();	//진영을 선택해 볼까요?
};
#define __SELECT_UNITE__H
#endif
