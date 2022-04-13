#include "Large.h"

extern Option op;

Large::Large(int lx, int ly, int ab_x, int ab_y){
	this->shape[0]='+';
	this->shape[1]='+';
	this->shape[2]='+';
	this->point_x=lx;
	this->point_y=ly;
	this->sight=5;	//전 시야가 5입니다.
	this->size=3;	//전 크기가 3입니다.
	this->move=1;	//전 1정도 밖에 이동을 하지 못합니다.
	this->basic_move=1;	//기본 이동이 1이다.
	this->at_point_x=ab_x;
	this->at_point_y=ab_y;
	this->health_point=1500;
	this->depence_point=15;
	this->attack_point=150;
	this->attack_pic='#';
	this->attack_length=5;
	this->alive=true;
	name="대형함";
}

Large::Large(){
	this->shape[0]='+';
	this->shape[1]='+';
	this->shape[2]='+';
	this->sight=5;	//전 시야가 5입니다.
	this->size=3;	//전 크기가 3입니다.
	this->move=1;	//전 1정도 밖에 이동을 하지 못합니다.
	this->basic_move=1;	//기본 이동이 1이다.
	this->health_point=1500;
	this->depence_point=15;
	this->attack_point=150;
	this->attack_pic='#';
	this->attack_length=5;
	this->alive=true;
	name="대형함";
}

void Large::position_move(int x, int y){	
	if(x==1){
		shade_myshape();
		this->at_point_x+=move;
		this->point_x+=(move-1);
	}
	else if(x==-1){
		shade_myshape();
		this->at_point_x-=move;
		this->point_x-=(move-1);
	}
	if(y==1){
		shade_myshape();
		this->at_point_y+=move;
		this->point_y+=(move-1);
	}
	else if(y==-1){
		shade_myshape();
		this->at_point_y-=move;
		this->point_y-=(move-1);
	}
	//배의 절대 위치가 움직이는 것을 계산해주는 것을 기대하는 메소드
}

void Large::myshape(){	//나의 형태를 그려주세요. 어 그런데 전 크기가 3이네요
	Allmessage *am;
	if(alive){
		for(int temp=0;temp<size;temp++){
			am->setText(point_x,point_y+temp,this->shape[temp]);
		}
	}
}

void Large::OnTimer(int id){
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
		move=1;
	}
}

bool Large::attacked(int x,int y)
{		
	if(x==point_x&&y==point_y){
		return true;
	}
	else{
		return false;
	}
}
void Large::myshape(int point_x, int point_y){	//나의 형태를 그려주세요. 어 그런데 전 크기가 2 이네요.
	Allmessage *am;
	if(alive){
		if(point_y<0){
			for(int temp=0;temp<size;temp++){
				am->setText(this->point_x,this->point_y+temp,this->shape[temp]);
			}
		}
		if(point_y>0){
			for(int temp=0;temp<size;temp++){
				am->setText(this->point_x,this->point_y+temp,this->shape[temp]);
			}
		}
		if(point_x<0){
			for(int temp=0;temp<size;temp++){
				am->setText(this->point_x,this->point_y+temp,this->shape[temp]);
			}
		}
		if(point_x>0){
			for(int temp=0;temp<size;temp++){
				am->setText(this->point_x,this->point_y+temp,this->shape[temp]);
			}
		}
	}
}
void Large::shade_myshape(){	//나의 형태를 그려주세요.
	Allmessage *am;
	for(int temp=0;temp<size;temp++){
		am->setText(point_x,point_y+temp,' ');
	}
}

void Large::shade_myshape(int point_x, int point_y){	//나의 형태를 그려주세요.
	Allmessage *am;
	for(int temp=0;temp<size;temp++){
		am->setText(point_x,point_y+temp,' ');
	}
}

void Large::attack_draw(int y_at_x,int y_at_y){	//배의 공격을 그려줍니다.
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
void Large::attack_erase(int y_at_x, int y_at_y){
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
