#ifndef __ALLMESSAGE__H
#include "Allmessage.h"
#define __ALLMESSAGE__H
#endif

#ifndef __STORY__H

#include "Option.h"

class Story{
	private:
		char *intro[22];//char 포인터 배열의 스토리 내용
		int startx;//별출력할때 사용하는star_x star_y in_roof out_roof
		int starty;
		int in_roof;
		int out_roof;
	public:
		Story(); //스토리 객체의 생성자
		void story_message();	//오프닝 스토리메세지를 출력
		void skip_story();	//오프닝 스토리를 넘어가는 함수
		void print_story_star(); //별출력
		void clean();
};
#define __STORY__H

#endif
