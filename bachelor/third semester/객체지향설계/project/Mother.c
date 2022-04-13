#include "Mother.h"

extern Option op;

Mother::Mother(int lx, int ly, int ab_x, int ab_y){
	this->shape[0]='+';
	this->shape[1]='=';
	this->shape[2]='+';
	this->shape[3]='+';
	this->shape[4]='=';
	this->shape[5]='+';
	this->point_x=lx;
	this->point_y=ly;
	this->sight=5;	//전 시야가5입니다.
	this->size=6;	//전 크기가 6입니다.
	this->move=1;	//함대의 기본 이동은 1입니다.
	this->basic_move=1;	//전함대의 기본 이동은 1입니다.
	this->at_point_x=ab_x;
	this->at_point_y=ab_y;
	this->health_point=2000;
	this->depence_point=20;
	this->attack_point=100;
	this->attack_pic='#';
	this->attack_length=3;	//전 시야가3입니다.
	this->alive=true;
	name="모함";
}

Mother::Mother(){
	this->shape[0]='+';
	this->shape[1]='=';
	this->shape[2]='+';
	this->shape[3]='+';
	this->shape[4]='=';
	this->shape[5]='+';
	this->sight=5;	//전 시야가5입니다.
	this->size=6;	//전 크기가 6입니다.
	this->move=1;	//함대의 기본 이동은 1입니다.
	this->basic_move=1;	//전함대의 기본 이동은 1입니다.
	this->health_point=2000;
	this->depence_point=20;
	this->attack_point=100;
	this->attack_pic='#';
	this->attack_length=3;	//전 시야가3입니다.
	this->alive=true;
	name="모함";
}

void Mother::OnTimer(int id){
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

bool Mother::attacked(int x,int y)
{		
	if(x==point_x&&y==point_y){
		return true;
	}
	else{
		return false;
	}
}

void Mother::position_move(int x, int y){	//배의 절대 위치가 움직이는 것을 계산해주는 것을 기대하는 메소드
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


void Mother::myshape(){	//나의 형태를 그려주세요. 어 그런데 전 크기가 6이네요
	Allmessage *am;
	if(alive){
	for(int temp=0;temp<size;temp++){
		am->setText(point_x,point_y+temp,this->shape[temp]);
	}
	}
}

void Mother::myshape(int point_x, int point_y){	//나의 형태를 그려주세요. 어 그런데 전 크기가 2 이네요.
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
void Mother::shade_myshape(){	//나의 형태를 그려주세요. 어 그런데 전 크기가 6이네요
	Allmessage *am;
	for(int temp=0;temp<size;temp++){
		am->setText(point_x,point_y+temp,' ');
	}
}

void Mother::shade_myshape(int point_x, int point_y){	//나의 형태를 그려주세요. 어 그런데 전 크기가 6 이네요.
	Allmessage *am;
	int temp;
	for(temp=0;temp<size;temp++){
		am->setText(point_x,point_y+temp,' ');
	}
}

void Mother::attack_draw(int y_at_x,int y_at_y){	//배의 공격을 그려줍니다.
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
void Mother::attack_erase(int y_at_x, int y_at_y){
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
