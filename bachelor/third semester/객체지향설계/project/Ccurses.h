#ifndef __CURSES__H
#include <curses.h>
#define __CURSES__H
#endif

#ifndef __CCURSES_H

class Ccurses{
	public:
		static const int RED=COLOR_RED;
		static const int GREEN=COLOR_GREEN;
		static const int WHITE=COLOR_WHITE;
		static const int YELLOW=COLOR_YELLOW;
		static const int BLUE=COLOR_BLUE;
		static const int CYAN=COLOR_CYAN;
		Ccurses();
		~Ccurses();
		void moveto(int x, int y);
		void drawText(int x, int y, char *s);
		void drawText(int x, int y, char  c);
		void drawText(char *s);
		void drawHLine(int x, int y, int len);
		void drawVLine(int x, int y, int len);
		void setColor(int c);
		void refresh();
};

#define __CCURSES_H
#endif
