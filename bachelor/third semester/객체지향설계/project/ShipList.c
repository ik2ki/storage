#include "ShipList.h"

void Stack::can_attack(Ship *center){
		//앞에 있는거 공격 앞에 공격은 절대위치에서 이웃에 있는 객체의 사이즈를 더해주어서 공격이 가능한지 알아봐야한다. 공격후에 계속 숫자를 늘려가면서 그 앞에 있는 배도 공격이 가능하면 계속 공격해야 한다.
		Ship *temp;
		for(Link *tpl=sp;tpl;tpl=tpl->next){	//배객체 temp는 처음의 tpl의 Ship객체를 받으며 tpl이 null일 띠까지 다음 배객체를 받아 들인다.
			temp=tpl->item;
			if(temp!=center){	//temp에 들어 있는 배 객체와 center에 들어 있는 배 객체가 같은 객체가 아닐 경우에 실행된다.
				for(int t1=0;t1<center->length_return();t1++){
					for(int t2=0;t2<center->length_return();t2++){
						if(temp->attacked(center->point_x_return()+t1,center->point_y_return()+t2)||temp->attacked(center->point_x_return()-t1,center->point_y_return()-t2)||temp->attacked(center->point_x_return()+t1,center->point_y_return()-t2)||temp->attacked(center->point_x_return()-t1,center->point_y_return()+t2)){
							center->attack_draw(temp->point_x_return(),temp->point_y_return());
							temp->damege(center->attack());
							//center->attack_erase(temp->point_x_return(),temp->point_y_return());
							}
						}
					}
				}
		}
}

void Stack::move(int x, int y){
	for(Link *tpl=sp;tpl;tpl=tpl->next){
		Ship *temp=tpl->item;
		if(x<0){
			temp->position_move(-1,0);
		}

		else if(x>0){
			temp->position_move(1,0);
		}

		else if(y<0){
			temp->position_move(0,-1);
		}

		else if(y>0){
			temp->position_move(0,1);
		}
	}
}


