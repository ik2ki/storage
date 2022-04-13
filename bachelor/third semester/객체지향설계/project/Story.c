#include "Story.h"

extern Allmessage am;
extern Ccurses cc;
extern Option op;

Story::Story(){
intro[0]="이젤론요새는 변경성, 초신성 블랙홀등 위험이 득한 은하제국변경에서 동맹으로";
intro[1]="향하는 이젤론 회랑에 위치하는 요새이다.";
intro[2]="전함의 주포에도 끄떡하지 않는 3중갑을 방어력과 '토르 해머'라는 강력한";
intro[3]="요새포를 가지고있는 이젤론요새는 처음 동맹의 존재를 확인하고 원정하려는";
intro[4]="황제에게 제국경계에 통신 및 경계기능만 갖춘 전진기지만 갖추면 된다는";
intro[5]="스테판 후작의 발안으로 시작하여 '항상 동맹놈은 전쟁이론에 따라 움직이지";
intro[6]="않는다'는 류데릭 후작에게 황제가 매번 전쟁에 지는 중신의 체면을 위해 이론에";
intro[7]="입각하여 실천만 하기만 하는 기지 건설을 명한데서 이젤론 요새가 건설이된다.";
intro[8]="강력한 요새를 구축하려다가 초과 비용을 발생시켜 깐깐한 황제에게 질책받을까";
intro[9]="두려워 자살하게된 류데릭의 원한이 스민 이젤론 요새는 6차의 이젤론 공방전";
intro[10]="에 엄청난 동맹군의 시체가 떠돌만큼 매번 실패하게된다.";
intro[11]="이스타테 회전에서 대패한 동맹군은 어쨋든 적군인 제국군을 퇴각 시켰다해서";
intro[12]="승리라 포장하여 선전하였지만 동맹군 통합작전본부장인 시토레원수는 아스타테";
intro[13]="회전에서 패배에대한 은근한 사퇴의 압력을 받고있었다.";
intro[14]="그리하여 시토래 원수는 분위기 반전용으로 이젤론 요새 공략을 구상하게된다.";
intro[15]="그는 양에게 치하하며 4함대와 6함다의 잔병을 규합해 절반규모의 13함대를 편제";
intro[16]="첫임무로 이젤론 요새 공략을 명하게된다.";
intro[17]="시토레 원수는 양에게 어떤 방안이 있음을 느끼고 이젤론요새 공략을 실패하게";
intro[18]="되도 양은 함대를 무사히 후퇴시킬 수 있다고 신뢰하고 자신은 군복을 각오한것이다";
intro[19]="양은 기동의 명인 피셔를 부사령관으로 임명하고 무라이를 참모장으로";
intro[20]="근접전의 부대(로젠리터)를 배속시킨다.";;
intro[21]="과연 양은 이 이젤론 요새를 정복 할 수 있을 것인가... 다음으로 s키를 눌러주세요";//인트로 스토리 내용 
}


void Story::story_message(){
	for(int line=0,em=0;line<22;line++,em+=2){
		am.setText(41,em+line,intro[line]);
	}
}//오프닝 스토리메세지를 출력


void Story::skip_story(){
	char c;
	while((c=getch())!='s'){
	}
	op.see_cur();
}//오프닝 스토리를 넘어가는 함수


void Story::print_story_star(){
	op.hid_cur();
	srand(time(NULL));
	for(out_roof=0;out_roof<5;out_roof++){	//별뿌리기 
		for(in_roof=0;in_roof<100;in_roof++){
				startx=random()%157;
				starty=random()%64;
				am.setText(startx,starty,"*");
		}
	}
}//

void Story::clean(){
	op.clean();
}
