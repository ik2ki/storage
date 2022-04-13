#include "Patrol.h"

extern Option op;

Patrol::Patrol(int lx, int ly, int ab_x, int ab_y){
	this->shape='=';
	this->point_x=lx;
	this->point_y=ly;
	this->at_point_x=ab_x;
	this->at_point_y=ab_y;
	this->sight=8; //전 시야가 8입니다
	this->size=1;	//전 크기가 1입니다.
	this->move=1;	//전 이동을 1를 할수가 있습니다.
	this->health_point=1000;
	this->depence_point=50;
	this->attack_point=0;
	this->attack_pic=' ';
	this->attack_length=0;
	this->alive=true;
	name="정찰함";
}

Patrol::Patrol(){
	this->shape='=';
	this->sight=8; //전 시야가 8입니다
	this->size=1;	//전 크기가 2입니다.
	this->move=1;	//전 이동을 1를 할수가 있습니다.
	this->health_point=1000;
	this->depence_point=50;
	this->attack_point=0;
	this->attack_pic=' ';
	this->attack_length=0;
	this->alive=true;
	name="정찰함";
}

void Patrol::position_move(int x, int y){	//배의 절대 위치가 움직이는 것을 계산해주는 것을 기대하는 메소드
	if(x==1){
		shade_myshape();
		this->at_point_x+=1;
		this->point_x+=0;
	}
	else if(x==-1){
		shade_myshape();
		this->at_point_x-=1;
		this->point_x-=0;
	}
	if(y==1){
		shade_myshape();
		this->at_point_y+=1;
		this->point_y+=0;
	}
	else if(y==-1){
		shade_myshape();
		this->at_point_y-=1;
		this->point_y-=0;
	}
}
bool Patrol::attacked(int x,int y)
{		
	if(x==point_x&&y==point_y){
		return true;
	}
	else{
		return false;
	}
}

void Patrol::OnTimer(int id){
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

void Patrol::myshape(){	//나의 형태를 그려주세요. 어 그런데 전 크기가 6이네요
	Allmessage *am;
	if(alive){
	am->setText(point_x,point_y,this->shape);
	}
}

void Patrol::myshape(int point_x, int point_y){	//나의 형태를 그려주세요.
	Allmessage *am;
	if(alive){
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

void Patrol::shade_myshape(){	//나의 형태를 그려주세요.
	Allmessage *am;
	am->setText(point_x,point_y,' ');
}

void Patrol::shade_myshape(int point_x, int point_y){	//나의 형태를 그려주세요.
	Allmessage *am;
	am->setText(point_x,point_y,' ');
}

void Patrol::attack_draw(int y_at_x,int y_at_y){	//배의 공격을 그려줍니다.
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
void Patrol::attack_erase(int y_at_x, int y_at_y){
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
