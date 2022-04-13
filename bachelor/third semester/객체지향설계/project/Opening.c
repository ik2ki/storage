#include "Opening.h"

extern Allmessage am;
extern Ccurses cc;
extern Option op;

Opening::Opening(){
	start_screen[0]=" GGGGG           ||               Y     Y";
	start_screen[1]="G     G          ||                Y   Y";
	start_screen[2]="G          aaa   ||   aaa    x  x   Y Y";
	start_screen[3]="G  GGGG   a   a  ||  a   a    xx     Y";
	start_screen[4]="G     G   a   a  ||  a   a    xx     Y";
	start_screen[5]=" GGG  G    aaaaa ||   aaaaa  x  x    Y";
	start_screen[6]="H     H";
	start_screen[7]="H     H";
	start_screen[8]="H     H   eee  |  / ooo";
	start_screen[9]="HHHHHHH  eeeee | / o   o";
	start_screen[10]="H     H  e     |/  o   o";
	start_screen[11]="H     H   eee  |    ooo";
	start_screen[12]="L              GGGGG                d";
	start_screen[13]="L             G     G               d";
	start_screen[14]="L        eee  G        eee  nnn     d";
	start_screen[15]="L       eeeee G  GGGG eeeee n  n dddd";
	start_screen[16]="L       e     G     G e     n  n d  d";
	start_screen[17]="LLLLLLL  eee   GGG  G  eee  n  n dddd"; //오프닝 내용입력
	game_start="게임시작";
	game_end="게임종료";
}

Opening::~Opening(){
	/*for(int line=1;line<18;line++){
		if(start_screen[line]){
			free(start_screen[line]);
		}
	}
	if(game_end){
		free(game_end);
	}*/
}

int Opening::select(){ //게임 시작과 게임끝을 선택하는 창을 결정하는 select함수
	Allmessage *start_box = new Allmessage(75,40,10,3);	//Allmessage객체를 통해서 게임시작메세지를 담는 상자를 출력흐는 startbox를 만들었다.
	Allmessage *end_box = new Allmessage(75,43,10,3); //Allmessage객체를 통해서 게임종료메세지를 담는 상자를 출력하는 endbox선언
	select_x=76;
	select_y=41;
	cc.moveto(select_x,select_y);
	char c;
	while((c=getch())!=EOF){
		switch(c){
		case KEY_UP: //위를 계속 누르면 종료쪽으로 콘솔이 움직이게된다.
			if(select_y>41){ 
			select_y-=3; break; 
			}
			else{
			select_y+=3; break;
			}
		case KEY_DOWN: //아래로 두번 누르면 시작쪽으로 콘솔이 움직이게된다.
			if(select_y<44){ 
			select_y+=3; break;
			}
			else{
			select_y-=3; break;
			}
		case '\r':
			if(start_box->included(select_x,select_y)) return 1;//게임이 시작되었다. opening story를 보여준다.
			if(end_box->included(select_x,select_y)) return 0;//종료 선택시 종료
		}
		cc.moveto(select_x,select_y);
		refresh();
	}
}

void Opening::title(){
	op.hid_cur(); //option 객체의 마우스 커서 숨기기
	int roof_name;
	for(roof_name=0;roof_name<6;roof_name++){	//처음 오프닝 게임이름 출력
		am.setText(58,10+roof_name,start_screen[roof_name]);
	}
	for(roof_name=6;roof_name<12;roof_name++){
		am.setText(66,14+roof_name,start_screen[roof_name]);
	}
	for(roof_name=12;roof_name<18;roof_name++){
		am.setText(60,18+roof_name,start_screen[roof_name]);
	}
}

void Opening::start_message(){ //게임 시작 메세지를 출력하는 함수
	Allmessage *start_box = new Allmessage(75,40,10,3);	//Allmessage객체를 통해서 게임시작메세지를 담는 상자를 출력흐는 startbox를 만들었다.
	start_box -> setText(76,41,game_start); //게임시작메세지를 출력
	start_box -> draw(); //게임시작상자를 출력
}

void Opening::end_message(){ //게임종료 메세지를 출력하는 함수
	Allmessage *end_box = new Allmessage(75,43,10,3); //Allmessage객체를 통해서 게임종료메세지를 담는 상자를 출력하는 endbox선언
	end_box -> setText(76,44,game_end); //게임 종료 메세지를 출력
	end_box -> draw();	//종료상자를 출력*/
	op.see_cur();
}

void Opening::print_star(){
	op.hid_cur();
	srand(time(NULL));
	for(out_roof=0;out_roof<5;out_roof++){	//별뿌리기 
		for(in_roof=0;in_roof<100;in_roof++){
				startx=random()%157;
				starty=random()%40;
				am.setText(startx,starty,"*");
		}
		//op.wait(1);
	}	
}

void Opening::end(){
	exit(0);
}

void Opening::window_close(){
	endwin();
}

void Opening::clean(){
	op.clean();
}
