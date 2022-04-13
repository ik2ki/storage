#include "AttackLine.h"

extern Option op;
extern Player p;
extern Enermy e;

AttackLine::AttackLine(){
}

int AttackLine::select(){
	//전열을 선택해 볼까요 ? 전열을 선택하고 이제부터 생기는 리스트의 포지션 삽입 값은 전부 선택한 것으로 된다.
	Allmessage *am2=new Allmessage(60,16,30,14);
	am2->draw();
	Allmessage *am2_text= new Allmessage(71,22,9,3);
	am2_text->draw();
	am2_text->setText(72,23,"사   각");
	Allmessage *am3=new Allmessage(30,35,30,14);
	am3->draw();
	Allmessage *am3_text= new Allmessage(41,41,9,3);
	am3_text->draw();
	am3_text->setText(42,42,"삼   각");
	Allmessage *am4=new Allmessage(90,35,30,14);
	am4->draw();
	Allmessage *am4_text = new Allmessage(101,41,9,3);
	am4_text->draw();
	am4_text->setText(102,42,"선   형");
	int x, y;
	x=75;
	y=23;
	cc.moveto(x,y);
	char c;
	while((c=getch())!='.'){
		switch(c){
			case KEY_UP:
				if(x==105&&y==42){
					x-=30;
					y-=19;
					break;
				}
				else if(x==45&&y==42){
					x+=30;
					y-=19;
					break;
				}
			case KEY_DOWN:
					break;
			case KEY_RIGHT:
				if(x==75&&y==23){
					x+=30;
					y+=19;
					break;
				}
				else if(x==105&&y==42){
					x-=60;
					break;
				}
				else if(x==45&&y==42){
					x+=60;
					break;
				}
			case KEY_LEFT:
				if(x==75&&y==23){
					x-=30;
					y+=19;
					break;
				}
				else if(x==105&&y==42){
					x-=60;
					break;
				}
				else if(x==45&&y==42){
					x+=60;
					break;
				}
			case '\r':
				if(am2_text->included(x,y)) return 1; 
				if(am3_text->included(x,y)) return 2;
				if(am4_text->included(x,y)) return 3; 
				break;
		}
		cc.moveto(x,y);
		refresh();
	}
}

void AttackLine::gather_line(){
	//네모 선택시 상자 UI를 출력하고 위치와 배의 타입을 입력받아서 리스트에 삽입한다.
	Allmessage *squre1=new Allmessage(38,19,14,14);
	squre1->draw();
	Allmessage *squre2=new Allmessage(52,19,14,14);
	squre2->draw();
	Allmessage *squre3=new Allmessage(66,19,14,14);
	squre3->draw();
	Allmessage *squre4=new Allmessage(80,19,14,14);
	squre4->draw();
	Allmessage *squre5=new Allmessage(94,19,14,14);
	squre5->draw();
	Allmessage *squre6=new Allmessage(38,33,14,14);
	squre6->draw();
	Allmessage *squre7=new Allmessage(52,33,14,14);
	squre7->draw();
	Allmessage *squre8=new Allmessage(66,33,14,14);
	squre8->draw();
	Allmessage *squre9=new Allmessage(80,33,14,14);
	squre9->draw();
	Allmessage *squre10=new Allmessage(94,33,14,14);
	squre10->draw();
	Allmessage *small=new Allmessage(158,0,30,9);
	small->draw();
	small->setText(159,1,"소형함");
	small->setText(159,2,"빠른 속도를 가지고 있습니다.");
	small->setText(159,3,"시야가 짧으며 공격범위도");
	small->setText(159,4,"짧습니다.");
	Allmessage *middle=new Allmessage(158,9,30,9);
	middle->draw();
	middle->setText(159,10,"중형함");
	middle->setText(159,11,"보통의 속도를 가지고");
	middle->setText(159,12,"있습니다.");
	middle->setText(159,13,"시야,공격속도 평균입니다.");
	Allmessage *large=new Allmessage(158,18,30,9);
	large->draw();
	large->setText(159,19,"대형함");
	large->setText(159,20,"느린 속도를 가지고 있습니다.");
	large->setText(159,21,"시야가 넓으며");
	large->setText(159,22,"넓은 공격범위를 가지고 있다.");
	Allmessage *longdistance=new Allmessage(158,27,30,9);
	longdistance->draw();
	longdistance->setText(159,28,"장거리함");
	longdistance->setText(159,29,"느린 속도를 가지고있습니다.");
	longdistance->setText(159,30,"시야가 넓으면 더 긴");
	longdistance->setText(159,31,"사정거리를 가지고 있습니다.");
	Allmessage *mother=new Allmessage(158,36,30,9);
	mother->draw();
	mother->setText(159,37,"모선");
	mother->setText(159,38,"느린 속도를 가지고 있습니다.");
	mother->setText(159,39,"시야는 중간이고,");
	mother->setText(159,40,"공격력도 중간입니다.");
	Allmessage *patrol=new Allmessage(158,45,30,9);
	patrol->draw();
	patrol->setText(159,46,"순찰기");
	patrol->setText(159,47,"빠른 속도를 가지고 있습니다.");
	patrol->setText(159,48,"정찰만이 가능합니다.");
	patrol->setText(159,48,"넓은 시야를 가지고 있습니다.");
	Allmessage *approach=new Allmessage(158,54,30,9);
	approach->draw();
	approach->setText(159,55,"근거리함");
	approach->setText(159,56,"빠른속도를 가지고 있습니다.");
	approach->setText(159,57,"근접공격에서 탁월합니다.");
	approach->setText(159,58,"사정거리가 짧습니다.");
	int x=174; 
	int y=5;
	int count=0;
	cc.moveto(x,y);
	char c;
	while(count<10){
		c=getch();
			switch(c){
				case KEY_UP:
				if(y<=5){
					y+=54;
				}
				else{
					y-=9;
				}
				break;
				case KEY_DOWN:
				if(y>=59){
					y-=54;
				}
				else{
					y+=9;
				}
				break;
				case KEY_RIGHT:
				break;
				case KEY_LEFT:
				break;
				case '\r':
					if(small->included(x,y)){
						int ship_point_x;
						int ship_point_y;
						if(count<5){
							ship_point_x=45;
							ship_point_x+=(14*count);
							ship_point_y=26;
						}
						else{
							ship_point_x=45;
							ship_point_x+=(14*(count%5));
							ship_point_y=40;
						}
						p.add(new Small(ship_point_x,ship_point_y,ship_point_x,ship_point_y));
						count++;
					}
					else if(middle->included(x,y)){
						int ship_point_x;
						int ship_point_y;
						if(count<5){
							ship_point_x=45;
							ship_point_x+=(14*count);
							ship_point_y=26;
						}
						else{
							ship_point_x=45;
							ship_point_x+=(14*(count%5));
							ship_point_y=40;
						}
						p.add(new Middle(ship_point_x,ship_point_y,ship_point_x,ship_point_y));
						count++;
					}
					else if(large->included(x,y)){
						int ship_point_x;
						int ship_point_y;
						if(count<5){
							ship_point_x=45;
							ship_point_x+=(14*count);
							ship_point_y=26;
						}
						else{
							ship_point_x=45;
							ship_point_x+=(14*(count%5));
							ship_point_y=40;
						}
						p.add(new Large(ship_point_x,ship_point_y,ship_point_x,ship_point_y));

						count++;
					}
					else if(longdistance->included(x,y)){
						int ship_point_x;
						int ship_point_y;
						if(count<5){
							ship_point_x=45;
							ship_point_x+=(14*count);
							ship_point_y=26;
						}
						else{
							ship_point_x=45;
							ship_point_x+=(14*(count%5));
							ship_point_y=40;
						}
						p.add(new Longdistance(ship_point_x,ship_point_y,ship_point_x,ship_point_y));

						count++;
					}
					else if(mother->included(x,y)){
						int ship_point_x;
						int ship_point_y;
						if(count<5){
							ship_point_x=45;
							ship_point_x+=(14*count);
							ship_point_y=26;
						}
						else{
							ship_point_x=45;
							ship_point_x+=(14*(count%5));
							ship_point_y=40;
						}
						p.add(new Mother(ship_point_x,ship_point_y,ship_point_x,ship_point_y));

						count++;
					}	
					else if(patrol->included(x,y)){
						int ship_point_x;
						int ship_point_y;
						if(count<5){
							ship_point_x=45;
							ship_point_x+=(14*count);
							ship_point_y=26;
						}
						else{
							ship_point_x=45;
							ship_point_x+=(14*(count%5));
							ship_point_y=40;
						}
						p.add(new Patrol(ship_point_x,ship_point_y,ship_point_x,ship_point_y));

						count++;
					}
					else if(approach->included(x,y)){
						int ship_point_x;
						int ship_point_y;
						if(count<5){
							ship_point_x=45;
							ship_point_x+=(14*count);
							ship_point_y=26;
						}
						else{
							ship_point_x=45;
							ship_point_x+=(14*(count%5));
							ship_point_y=40;
						}
						p.add(new Approach(ship_point_x,ship_point_y,ship_point_x,ship_point_y));
						count++;
					}
					break;
				}
				cc.moveto(x,y);
				refresh();
			}
			e.add(new Longdistance(20,30,20,30));
			e.add(new Patrol(0,66,0,66));
			e.add(new Small(10,30,10,30));
			e.add(new Middle(40,45,40,45));
			e.add(new Longdistance(30,56,30,56));
			e.add(new Mother(70,23,70,23));
			e.add(new Approach(30,22,30,22));
			e.add(new Longdistance(50,8,50,8));
			e.add(new Small(34,23,34,23));
			e.add(new Middle(55,8,55,8));
			e.add(new Longdistance(10,40,10,40));
			e.add(new Patrol(7,16,7,16));
}

void AttackLine::spread_line(){
	Allmessage *squre1=new Allmessage(9,33,14,14);
	squre1->draw();
	Allmessage *squre2=new Allmessage(15+8,33,14,14);
	squre2->draw();
	Allmessage *squre3=new Allmessage(29+8,33,14,14);
	squre3->draw();
	Allmessage *squre4=new Allmessage(43+8,33,14,14);
	squre4->draw();
	Allmessage *squre5=new Allmessage(57+8,33,14,14);
	squre5->draw();
	Allmessage *squre6=new Allmessage(71+8,33,14,14);
	squre6->draw();
	Allmessage *squre7=new Allmessage(85+8,33,14,14);
	squre7->draw();
	Allmessage *squre8=new Allmessage(99+8,33,14,14);
	squre8->draw();
	Allmessage *squre9=new Allmessage(113+8,33,14,14);
	squre9->draw();
	Allmessage *squre10=new Allmessage(127+8,33,14,14);
	squre10->draw();
	Allmessage *small=new Allmessage(158,0,30,9);
	small->draw();
	small->setText(159,1,"소형함");
	small->setText(159,2,"빠른 속도를 가지고 있습니다.");
	small->setText(159,3,"시야가 짧으며 공격범위도");
	small->setText(159,4,"짧습니다.");
	Allmessage *middle=new Allmessage(158,9,30,9);
	middle->draw();
	middle->setText(159,10,"중형함");
	middle->setText(159,11,"보통의 속도를 가지고");
	middle->setText(159,12,"있습니다.");
	middle->setText(159,13,"시야,공격속도 평균입니다.");
	Allmessage *large=new Allmessage(158,18,30,9);
	large->draw();
	large->setText(159,19,"대형함");
	large->setText(159,20,"느린 속도를 가지고 있습니다.");
	large->setText(159,21,"시야가 넓으며");
	large->setText(159,22,"넓은 공격범위를 가지고 있다.");
	Allmessage *longdistance=new Allmessage(158,27,30,9);
	longdistance->draw();
	longdistance->setText(159,28,"장거리함");
	longdistance->setText(159,29,"느린 속도를 가지고있습니다.");
	longdistance->setText(159,30,"시야가 넓으면 더 긴");
	longdistance->setText(159,31,"사정거리를 가지고 있습니다.");
	Allmessage *mother=new Allmessage(158,36,30,9);
	mother->draw();
	mother->setText(159,37,"모선");
	mother->setText(159,38,"느린 속도를 가지고 있습니다.");
	mother->setText(159,39,"시야는 중간이고,");
	mother->setText(159,40,"공격력도 중간입니다.");
	Allmessage *patrol=new Allmessage(158,45,30,9);
	patrol->draw();
	patrol->setText(159,46,"순찰기");
	patrol->setText(159,47,"빠른 속도를 가지고 있습니다.");
	patrol->setText(159,48,"정찰만이 가능합니다.");
	patrol->setText(159,48,"넓은 시야를 가지고 있습니다.");
	patrol->setText(159,49,"광역시야가 가능합니다.");
	Allmessage *approach=new Allmessage(158,54,30,9);
	approach->draw();
	approach->setText(159,55,"근거리함");
	approach->setText(159,56,"빠른속도를 가지고 있습니다.");
	approach->setText(159,57,"근접공격에서 탁월합니다.");
	approach->setText(159,58,"사정거리가 짧습니다.");
	int count=0;
	int x=174;
	int y=5;
	cc.moveto(x,y);
	char c;
	while(count<10){
		c=getch();
				switch(c){
					case KEY_UP:
						if(y<=5){
							y+=54;
						}
						else{
							y-=9;
						}
					break;
					case KEY_DOWN:
						if(y>=59){
							y-=54;
						}
						else{
							y+=9;
						} 
						break;
					case KEY_RIGHT:
					break;
					case KEY_LEFT:
					break;
					case '\r':
					if(small->included(x,y)){
						int ship_point_x,ship_point_y;
						ship_point_x=16;
						ship_point_y=40;
						for(int upper=0;upper<count;upper++){
							ship_point_x+=14;
						}
						p.add(new Small(ship_point_x,ship_point_y,ship_point_x,ship_point_y));
						count++;
					}
					else if(middle->included(x,y)){
						int ship_point_x,ship_point_y;
						ship_point_x=16;
						ship_point_y=40;
						for(int upper=0;upper<count;upper++){
							ship_point_x+=14;
						}
						p.add(new Middle(ship_point_x,ship_point_y,ship_point_x,ship_point_y));
						count++;
					}
					else if(large->included(x,y)){
						int ship_point_x,ship_point_y;
						ship_point_x=16;
						ship_point_y=40;
						for(int upper=0;upper<count;upper++){
							ship_point_x+=14;
						}
						p.add(new Large(ship_point_x,ship_point_y,ship_point_x,ship_point_y));
						count++;
					}
					else if(longdistance->included(x,y)){
						int ship_point_x,ship_point_y;
						ship_point_x=16;
						ship_point_y=40;
						for(int upper=0;upper<count;upper++){
							ship_point_x+=14;
						}
						p.add(new Longdistance(ship_point_x,ship_point_y,ship_point_x,ship_point_y));
						count++;

					}
					else if(mother->included(x,y)){
						int ship_point_x,ship_point_y;
						ship_point_x=16;
						ship_point_y=40;
						for(int upper=0;upper<count;upper++){
							ship_point_x+=14;
						}
						p.add(new Mother(ship_point_x,ship_point_y,ship_point_x,ship_point_y));
						count++;

					}	
					else if(patrol->included(x,y)){
						int ship_point_x,ship_point_y;
						ship_point_x=16;
						ship_point_y=40;
						for(int upper=0;upper<count;upper++){
							ship_point_x+=14;
						}
						p.add(new Patrol(ship_point_x,ship_point_y,ship_point_x,ship_point_y));
						count++;

					}
					else if(approach->included(x,y)){
						int ship_point_x,ship_point_y;
						ship_point_x=16;
						ship_point_y=40;
						for(int upper=0;upper<count;upper++){
							ship_point_x+=14;
						}
						p.add(new Approach(ship_point_x,ship_point_y,ship_point_x,ship_point_y));
						count++;
					}
				}
				cc.moveto(x,y);
				refresh();
		}
		e.add(new Longdistance(20,30,20,30));
		e.add(new Patrol(0,66,0,66));
		e.add(new Small(10,30,10,30));
		e.add(new Middle(40,45,40,45));
		e.add(new Longdistance(30,56,30,56));
		e.add(new Mother(70,23,70,23));
		e.add(new Approach(30,22,30,22));
		e.add(new Longdistance(50,8,50,8));
		e.add(new Small(34,23,34,23));
		e.add(new Middle(55,8,55,8));
		e.add(new Longdistance(10,40,10,40));
		e.add(new Patrol(7,16,7,16));

}

void AttackLine::pass_line(){
	//세모 선택시 상자 UI를 출력하고 위치와 배의 타입을 입력받아서 리스트에 삽입한다
	Allmessage *squre1=new Allmessage(69,8,14,14);
	squre1->draw();
	Allmessage *squre2=new Allmessage(55,22,14,14);
	squre2->draw();
	Allmessage *squre3=new Allmessage(69,22,14,14);
	squre3->draw();
	Allmessage *squre4=new Allmessage(83,22,14,14);
	squre4->draw();
	Allmessage *squre5=new Allmessage(41+7,36,14,14);
	squre5->draw();
	Allmessage *squre6=new Allmessage(55+7,36,14,14);
	squre6->draw();
	Allmessage *squre7=new Allmessage(69+7,36,14,14);
	squre7->draw();
	Allmessage *squre8=new Allmessage(83+7,36,14,14);
	squre8->draw();
	Allmessage *squre9=new Allmessage(27+7,50,14,14);
	squre9->draw();
	Allmessage *squre10=new Allmessage(97+7,50,14,14);
	squre10->draw();
	Allmessage *small=new Allmessage(158,0,30,9);
	small->draw();
	small->setText(159,1,"소형함");
	small->setText(159,2,"빠른 속도를 가지고 있습니다.");
	small->setText(159,3,"시야가 짧으며 공격범위도");
	small->setText(159,4,"짧습니다.");
	Allmessage *middle=new Allmessage(158,9,30,9);
	middle->draw();
	middle->setText(159,10,"중형함");
	middle->setText(159,11,"보통의 속도를 가지고");
	middle->setText(159,12,"있습니다.");
	middle->setText(159,13,"시야,공격속도 평균");
	Allmessage *large=new Allmessage(158,18,30,9);
	large->draw();
	large->setText(159,19,"대형함");
	large->setText(159,20,"느린 속도를 가지고 있습니다.");
	large->setText(159,21,"시야가 넓으며");
	large->setText(159,22,"넓은 공격범위를 가지고 있다.");
	Allmessage *longdistance=new Allmessage(158,27,30,9);
	longdistance->draw();
	longdistance->setText(159,28,"장거리함");
	longdistance->setText(159,29,"느린 속도를 가지고있습니다.");
	longdistance->setText(159,30,"시야가 넓으면 더 긴");
	longdistance->setText(159,31,"사정거리를 가지고 있습니다.");
	Allmessage *mother=new Allmessage(158,36,30,9);
	mother->draw();
	mother->setText(159,37,"모선");
	mother->setText(159,38,"느린 속도를 가지고 있습니다.");
	mother->setText(159,39,"시야는 중간이고,");
	mother->setText(159,40,"공격력도 중간입니다.");
	Allmessage *patrol=new Allmessage(158,45,30,9);
	patrol->draw();
	patrol->setText(159,46,"순찰기");
	patrol->setText(159,47,"빠른 속도를 가지고 있습니다.");
	patrol->setText(159,48,"정찰만이 가능합니다.");
	patrol->setText(159,48,"넓은 시야를 가지고 있습니다.");
	Allmessage *approach=new Allmessage(158,54,30,9);
	approach->draw();
	approach->setText(159,55,"근거리함");
	approach->setText(159,56,"빠른속도를 가지고 있습니다.");
	approach->setText(159,57,"근접공격에서 탁월합니다.");
	approach->setText(159,58,"사정거리가 짧습니다..");
	int count=0;
	int x=174; 
	int y=5;
	cc.moveto(x,y);
	char c;
	while(count<10){
		c=getch();
			switch(c){
				case KEY_UP:
					if(y<=5){
						y+=54;
					}
					else{
						y-=9;
					}
				break;
				case KEY_DOWN:
					if(y>=59){
						y-=54;
					}
					else{
						y+=9;
					}

				break;
				case KEY_RIGHT:
				break;
				case KEY_LEFT:
				break;
				case '\r':
					if(small->included(x,y)){
						int ship_point_x=78;
						int ship_point_y=15;
						if(count==0){
							p.add(new Small(76,15,76,15));
						}
						else if(count==1){
							p.add(new Small(76-14,15+14,76-14,15+14));
						}
						else if(count==2){
							p.add(new Small(76,15+14,76,15+14));
						}
						else if(count==3){
							p.add(new Small(76+14,15+14,76+14,15+14));
						}
						else if(count==4){
							p.add(new Small(76-21,15+28,76-21,15+14));
						}
						else if(count==5){
							p.add(new Small(76-7,15+28,76-7,15+28));
						}
						else if(count==6){
							p.add(new Small(76+7,15+28,76+7,15+28));
						}
						else if(count==7){
							p.add(new Small(76+21,15+28,78+21,15+28));
						}
						else if(count==8){
							p.add(new Small(76-35,15+42,76-35,15+42));
						}
						else if(count==9){
							p.add(new Small(76+35,15+42,76+35,15+42));
						}
						count++;
					}
					else if(middle->included(x,y)){
						int ship_point_x=78;
						int ship_point_y=15;
						if(count==0){
							p.add(new Middle(76,15,78,15));
						}
						else if(count==1){
							p.add(new Middle(76-14,15+14,76,15));
						}
						else if(count==2){
							p.add(new Middle(76,15+14,76,15));
						}
						else if(count==3){
							p.add(new Middle(76+14,15+14,76,15));
						}
						else if(count==4){
							p.add(new Middle(76-21,15+28,76,15));
						}
						else if(count==5){
							p.add(new Middle(76-7,15+28,76,15));
						}
						else if(count==6){
							p.add(new Middle(76+7,15+28,76,15));
						}
						else if(count==7){
							p.add(new Middle(76+21,15+28,76,15));
						}
						else if(count==8){
							p.add(new Middle(76-35,15+42,76,15));
						}
						else if(count==9){
							p.add(new Middle(76+35,15+42,76,15));
						}
						count++;
					}
					else if(large->included(x,y)){
						int ship_point_x=78;
						int ship_point_y=15;
						if(count==0){
							p.add(new Large(76,15,76,15));
						}
						else if(count==1){
							p.add(new Large(76-14,15+14,76,15));
						}
						else if(count==2){
							p.add(new Large(76,15+14,76,15));
						}
						else if(count==3){
							p.add(new Large(76+14,15+14,76,15));
						}
						else if(count==4){
							p.add(new Large(76-21,15+28,76,15));
						}
						else if(count==5){
							p.add(new Large(76-7,15+28,76,15));
						}
						else if(count==6){
							p.add(new Large(76+7,15+28,76,15));
						}
						else if(count==7){
							p.add(new Large(76+21,15+28,76,15));
						}
						else if(count==8){
							p.add(new Large(76-35,15+42,76,15));
						}
						else if(count==9){
							p.add(new Large(76+35,15+42,76,15));
						}
						count++;
					}
					else if(longdistance->included(x,y)){
						int ship_point_x=78;
						int ship_point_y=15;
						if(count==0){
							p.add(new Longdistance(76,15,76,15));
						}
						else if(count==1){
							p.add(new Longdistance(76-14,15+14,76,15));
						}
						else if(count==2){
							p.add(new Longdistance(76,15+14,76,15));
						}
						else if(count==3){
							p.add(new Longdistance(76+14,15+14,76,15));
						}
						else if(count==4){
							p.add(new Longdistance(76-21,15+28,76,15));
						}
						else if(count==5){
							p.add(new Longdistance(76-7,15+28,76,15));
						}
						else if(count==6){
							p.add(new Longdistance(76+7,15+28,76,15));
						}
						else if(count==7){
							p.add(new Longdistance(76+21,15+28,76,15));
						}
						else if(count==8){
							p.add(new Longdistance(76-35,15+42,76,15));
						}
						else if(count==9){
							p.add(new Longdistance(76+35,15+42,76,15));
						}
						count++;
					}
					else if(mother->included(x,y)){
						int ship_point_x=78;
						int ship_point_y=15;
						if(count==0){
							p.add(new Mother(76,15,76,15));
						}
						else if(count==1){
							p.add(new Mother(76-14,15+14,76,15));
						}
						else if(count==2){
							p.add(new Mother(76,15+14,76,15));
						}
						else if(count==3){
							p.add(new Mother(76+14,15+14,76,15));
						}
						else if(count==4){
							p.add(new Mother(76-21,15+28,76,15));
						}
						else if(count==5){
							p.add(new Mother(76-7,15+28,76,15));
						}
						else if(count==6){
							p.add(new Mother(76+7,15+28,76,15));
						}
						else if(count==7){
							p.add(new Mother(76+21,15+28,76,15));
						}
						else if(count==8){
							p.add(new Mother(76-35,15+42,76,15));
						}
						else if(count==9){
							p.add(new Mother(76+35,15+42,76,15));
						}
						count++;
					}	
					else if(patrol->included(x,y)){
						int ship_point_x=78;
						int ship_point_y=15;
						if(count==0){
							p.add(new Patrol(76,15,76,15));
						}
						else if(count==1){
							p.add(new Patrol(76-14,15+14,76,15+14));
						}
						else if(count==2){
							p.add(new Patrol(76,15+14,76,15));
						}
						else if(count==3){
							p.add(new Patrol(76+14,15+14,76,15));
						}
						else if(count==4){
							p.add(new Patrol(76-21,15+28,76,15));
						}
						else if(count==5){
							p.add(new Patrol(76-7,15+28,76,15));
						}
						else if(count==6){
							p.add(new Patrol(76+7,15+28,76,15));
						}
						else if(count==7){
							p.add(new Patrol(76+21,15+28,76,15));
						}
						else if(count==8){
							p.add(new Patrol(76-35,15+42,76,15));
						}
						else if(count==9){
							p.add(new Patrol(76+35,15+42,76,15));
						}
						count++;
					}
					else if(approach->included(x,y)){
						int ship_point_x=78;
						int ship_point_y=15;
						if(count==0){
							p.add(new Approach(76,15,76,15));
						}
						else if(count==1){
							p.add(new Approach(76-14,15+14,76,15));
						}
						else if(count==2){
							p.add(new Approach(76,15+14,76,15));
						}
						else if(count==3){
							p.add(new Approach(76+14,15+14,76,15));
						}
						else if(count==4){
							p.add(new Approach(76-21,15+28,76,15));
						}
						else if(count==5){
							p.add(new Approach(76-7,15+28,76,15));
						}
						else if(count==6){
							p.add(new Approach(76+7,15+28,76,15));
						}
						else if(count==7){
							p.add(new Approach(76+21,15+28,76,15));
						}
						else if(count==8){
							p.add(new Approach(76-35,15+42,76,15));
						}
						else if(count==9){
							p.add(new Approach(76+35,15+42,76,15));
						}
						count++;
					}

					break;
			}
			cc.moveto(x,y);
			refresh();
		}	
		e.add(new Longdistance(20,30,20,30));
		e.add(new Patrol(0,66,0,66));
		e.add(new Small(10,30,10,30));
		e.add(new Middle(40,45,40,45));
		e.add(new Longdistance(30,56,30,56));
		e.add(new Mother(70,23,70,23));
		e.add(new Approach(30,22,30,22));
		e.add(new Longdistance(50,8,50,8));
		e.add(new Small(34,23,34,23));
		e.add(new Middle(55,8,55,8));
		e.add(new Longdistance(10,40,10,40));
		e.add(new Patrol(7,16,7,16));

}
