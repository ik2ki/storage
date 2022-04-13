#include "Ccurses.h"

Ccurses::Ccurses(){ 
	initscr(); cbreak(); noecho();
	nonl();
	intrflush(stdscr, FALSE);
	keypad(stdscr, TRUE);
	//curs_set(0);
	start_color();
	init_pair(COLOR_WHITE, COLOR_WHITE, COLOR_BLACK);
	init_pair(COLOR_RED, COLOR_RED, COLOR_BLACK);
	init_pair(COLOR_YELLOW, COLOR_YELLOW, COLOR_BLACK);
	init_pair(COLOR_BLUE, COLOR_BLUE, COLOR_BLACK);
	init_pair(COLOR_MAGENTA, COLOR_MAGENTA, COLOR_BLACK);
	init_pair(COLOR_CYAN, COLOR_CYAN, COLOR_BLACK);
	init_pair(COLOR_GREEN, COLOR_GREEN, COLOR_BLACK);
}

Ccurses::~Ccurses(){
	endwin();
}

void Ccurses::refresh(){ ::refresh(); }
void Ccurses::moveto(int x, int y){ ::move(y, x); }

void Ccurses::drawText(int x, int y, char *s){
	::move(y,x); addstr(s);
}

void Ccurses::drawText(int x, int y , char c){
	::move(y,x); 
	printw("%c",c);
	refresh();
}

void Ccurses::drawText(char *s){
	addstr(s);
	refresh();
}

void Ccurses::drawHLine(int x, int y, int len){
	::move(y,x);
	for ( ; len>0; len--) addch('-');
	refresh();
}

void Ccurses::drawVLine(int x, int y, int len){
	int ty;
	for (ty= y; ty < y + len  ; ty++){
		move(ty, x);
		addch('|');
	}
	refresh();
}

void Ccurses::setColor(int c){
	attron(COLOR_PAIR(c));
}

