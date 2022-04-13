#include "Small.h"


Small::Small(){
	this->shape='+';
	this->health_point=1000;
	this->depence_point=10;
	this->attack_point=100;
	this->size =1;
	this->basic_move=1;	//기본 이동 1
	this->move=2;		//소형선의 이동 3
	this->sight=1;		//소형선의 기본 시야 1
	this->alive=true;
	this->attack_pic='#';
	this->attack_length=2;
	name="소형함";
}

Small::Small(int lx, int ly, int ab_x, int ab_y){
	this->shape='+';
	this->point_x=lx;
	this->point_y=ly;
	this->at_point_x=ab_x;
	this->at_point_y=ab_y;
	this->health_point=1000;
	this->depence_point=10;
	this->attack_point=100;
	this->size =1;
	this->basic_move=1;
	this->move=2;		
	this->sight=1;
	alive=true;
	this->attack_pic='#';
	this->attack_length=2;
	name="소형함";
}

void Small::shade_myshape(){	//나의 형태를 그려주세요.
	Allmessage *am;
	am->setText(point_x,point_y,' ');
}

void Small::shade_myshape(int point_x, int point_y){	//나의 형태를 그려주세요.
	Allmessage *am;
	am->setText(point_x,point_y,' ');
}
void Small::position_move(int x, int y){	//배의 절대 위치가 움직이는 것을 계산해주는 것을 기대하는 메소드
	if(x==1){
		shade_myshape();
			if(point_x+2<157){
				this->point_x+=2;		//함대의 상대적 위치는 2가 올라가게 된다. 화면에 표현되는
			}
			this->at_point_x+=3;	//절대 위치는 3이 올라가고
								//나 그려줘
	}
	else if(x==-1){
		shade_myshape();
		this->at_point_x-=3;
		if(point_x-2>0){
			this->point_x-=2;
		}
	}
	if(y==1){
		shade_myshape();
		if(point_y+2<64){
			this->point_y+=2;
		}
		this->at_point_y+=3;
	}
	else if(y==-1){
		shade_myshape();
		if(point_y-2>0){
			this->point_y-=2;
		}
		this->at_point_y-=3;
	}
}


bool Small::can_demage(int x,int y){ //배가 공격을 받았는지 리턴해 줄걸로 기대하는 메소드
	if(x==point_x&&y==point_y){
		return true;
	}
	else{
		return false;
	}
}

void Small::myshape(){	//나의 형태를 그려주세요.
	if(alive){
		Allmessage *am;
		am->setText(point_x,point_y,this->shape);
	}
}

void Small::myshape(int point_x, int point_y){	//나의 형태를 그려주세요.
	if(alive){
		Allmessage *am;
		if(point_y<0){
			am->setText(this->point_x,this->point_y,this->shape);
		}
		if(point_y>0){
			am->setText(this->point_x,this->point_y,this->shape);
		}
		if(point_x<0){
			am->setText(this->point_x,this->point_y,this->shape);
		}
		if(point_x>0){
			am->setText(this->point_x,this->point_y,this->shape);
		}
	}
}

void Small::OnTimer(int id){
	if(id==1){
		myshape();
	}
	else if(id==2){
		shade_myshape();
	}
	else if(id==3){
		op.battle_clean();
	}
	else if(id==4){
	}
}

bool Small::attacked(int x,int y){		
	if(x==point_x&&y==point_y){
		return true;
	}
	else{
		return false;
	}
}


void Small::attack_draw(int y_at_x,int y_at_y){	//배의 공격을 그려줍니다.
	Allmessage *am;
	if(alive){
	if(y_at_x>this->point_x){
		if(y_at_y>this->point_y){
			int x_increase;
			for(x_increase=this->point_x;x_increase<=y_at_x;x_increase++){
					am->setText(x_increase,this->point_y+this->size,this->attack_pic);
			}
			x_increase-=1;
			for(int y_increase=this->point_y+size+1;y_increase<y_at_y;y_increase++){
					am->setText(x_increase,y_increase,this->attack_pic);
			}
		}
		else if(y_at_y<this->point_y){
			int x_increase;
			for(x_increase=this->point_x;x_increase<y_at_x;x_increase++){
					am->setText(x_increase,this->point_y+this->size,this->attack_pic);
			}
			for(int y_increase=this->point_y+size;y_increase>y_at_y;y_increase--){
					am->setText(x_increase,y_increase,this->attack_pic);
			}
		}
		else if(y_at_y==this->point_y){
			int x_increase;
			for(x_increase=this->point_x;x_increase+1<y_at_x;x_increase++){
					am->setText(x_increase+1,this->point_y,this->attack_pic);
			}
		}
	}
	else if(y_at_x<this->point_x){
		if(y_at_y>this->point_y){
		int x_increase;
		for(x_increase=this->point_x;x_increase>=y_at_x;x_increase--){
				am->setText(x_increase,this->point_y+this->size,this->attack_pic);
		}
		x_increase+=1;
		for(int y_increase=this->point_y+size+1;y_increase<y_at_y;y_increase++){
				am->setText(x_increase,y_increase,this->attack_pic);
		}
	}
	else if(y_at_y<this->point_y){
		int x_increase;
			for(x_increase=this->point_x;x_increase>=y_at_x;x_increase--){
					am->setText(x_increase,this->point_y+this->size,this->attack_pic);
			}
			x_increase+=1;
			for(int y_increase=this->point_y+size;y_increase>y_at_y;y_increase--){
					am->setText(x_increase,y_increase,this->attack_pic);
			}
		}
	}
	}
}
void Small::attack_erase(int y_at_x, int y_at_y){
	Allmessage *am;
	if(y_at_x>this->point_x){
		if(y_at_y>this->point_y){
			int x_increase;
			for(x_increase=this->point_x;x_increase<=y_at_x;x_increase++){
					am->setText(x_increase,this->point_y+this->size,' ');
			}
			x_increase-=1;
			for(int y_increase=this->point_y+size+1;y_increase<y_at_y;y_increase++){
					am->setText(x_increase,y_increase,' ');
			}
		}
		else if(y_at_y<this->point_y){
			int x_increase;
			for(x_increase=this->point_x;x_increase<y_at_x;x_increase++){
					am->setText(x_increase,this->point_y+this->size,' ');
			}
			for(int y_increase=this->point_y+size;y_increase>y_at_y;y_increase--){
					am->setText(x_increase,y_increase,' ');
			}
		}
		else if(y_at_y==this->point_y){
			int x_increase;
			for(x_increase=this->point_x;x_increase+1<y_at_x;x_increase++){
					am->setText(x_increase+1,this->point_y,' ');
			}
		}
	}
	else if(y_at_x<this->point_x){
		if(y_at_y>this->point_y){
			int x_increase;
			for(x_increase=this->point_x;x_increase>=y_at_x;x_increase--){
					am->setText(x_increase,this->point_y+this->size,' ');
			}
			x_increase+=1;
			for(int y_increase=this->point_y+size+1;y_increase<y_at_y;y_increase++){
					am->setText(x_increase,y_increase,' ');
			}
		}
		else if(y_at_y<this->point_y){
			int x_increase;
			for(x_increase=this->point_x;x_increase>=y_at_x;x_increase--){
					am->setText(x_increase,this->point_y+this->size,' ');
			}
			x_increase+=1;
			for(int y_increase=this->point_y+size;y_increase>y_at_y;y_increase--){
					am->setText(x_increase,y_increase,' ');
			}
		}
	}
}
