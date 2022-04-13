#include "SelectUnite.h"

extern Ccurses cc;
extern Option op;

SelectUnite::SelectUnite(){
	Free[0]="999XX5XzGGGeeeeeE9EEEGEEEGGeeGeeeeeeeeeeeeeeeeeeeGGEEE9E9#z#E9#9EE99E9999EE";
	Free[1]="E#z5u  eeGEGGE9#zz99EEGeeeeeeeEWWu        u5z9GeeeeeeeGEEGeG9EGG9#EEEEE9EEE";
	Free[2]="DzD5WWEGeGGeeGzz9EGeeeeeGK                        uXGeeeeeE#9GE9#eEEEEEEEEE";
	Free[3]="X9DWK. yeeeezKX9eeeee5         .  .u    ......        KEeeeeeGGGEEEGGEEGGEE";
	Free[4]="#9XXyu WeezyKDeeeeX       ..u...u..  uuu.      .    .     #eeeEGeGEEEEEGGGE";
	Free[5]="zzDD5W. u  u#eeG             .uuuu.             WW  u.u.    5eeeGGEEGGeGEEE";
	Free[6]="E9#zzzDyyzGee#    .  ...   ...uu..   .KWWu   uKWKu  .  .  uK  KGeeeEEEE9eeE";
	Free[7]="#E##9E9zEeeeW      ..   u.                    . uyK.  uWK.  W.  9eeeeeeEGG9";
	Free[8]="999###9eeeK  u  .uuuu.         uuKKWWWyWKKWK..      .W5yuKXW uu   eeeG9GeEE";
	Free[9]="9###9Eee9.  u    uyuuuu.  .KKKKWWWKKKuWWWWyWKWyW5uu  ..W55WK   .u  DeeeEGGE";
	Free[10]="E9#EGeee     u     KW.  uyWWWKKKKKKKKKKKuuWWKKWyW uKKu Kyy.  .  ..u 5EeeGeE";
	Free[11]="E#EGee9     uK   .    KWWKWWKKKKKKKKWKKWWKKKWKW5.   .KW     uyDD5 uu.u#eeeE";
	Free[12]="9Eeeee  ..uKKKuuKu  KWyyWWKKW5X5yyyWKWWWWWWWWy5X .   u.Ku  uEX55X5  DDu#eeE";
	Free[13]="9eee#. u.   u5Xyu .5X5KuKy5XXW.uWyXX5WWWWyyWWy5u uu... KXXu yWKu  .uD#z eeE";
	Free[14]="G9e9u .          .XWKWyXX5XK      uWDX55yKKyyX5  u.... X55yK Ku   uKWXGuDeG";
	Free[15]="Gee  . .WKKKu   W5yyy5XXX.    .u.    WXDD5yX5yW Ku.uu uDXXzD.   .uXe#y5e.GG";
	Free[16]="ee#  K WyWW55uuK55WD#z5.       u ...   KXz##XWu u.uuu DD55DWyyuyGeeee#ueWzG";
	Free[17]="Ge  uKuyXDXW. K5DzEXu          W.uuuuu   .y9zK ..uKuu.zyuWD.uGX5GeXKEeXDED9";
	Free[18]="ee uu. . uWyu zzD5             W  . ....   .WyWu K.  yXXEeeuKeDu .uuWy5KezE";
	Free[19]="e# Wu        u#y               K .u.uu  ..    uX W  WGGeee5 9eGD.uWKu.Ky9#E";
	Free[20]="e Kz5uD .    XXu  .u.          W .        uyXD#euWuDeeeee9  eee9.5eXeeeK#9E";
	Free[21]="e 5zyyezezu. 9zK   uuuuu.            WDzzzE9z#9Eu5KzeEGeE5 DeGe9uEeGeeeXD9E";
	Free[22]="e X95Keeeeeeueee#..yKuuKWWKWK  XXGeeeG###z#zD9Gyu Xee9Ge5.KeGEeE.yeeEzeWX9E";
	Free[23]="e 59y u   .. eeeEX.WKWyWuuWu  EeeDW55D5XDXDXz#zuKu9eGEe#u.zeEGe9 .W..K5uD9G";
	Free[24]="e uEzyyuu.uuueeeee uWKuKWu  u9eXzDyyyXXXX5Xy9z5K.#Ge9Ee. WeG9eG#uK.KuK5W9#G";
	Free[25]="e5 G#yWyeeXK.zeGeeX KWWKu  Deez#D#zWyy55555X#XKu eGEEe9 Kze#Ee#5uKEe#yXyEDE";
	Free[26]="ee EzW5yezyWKyeeeGe .WWu .DGEz9Xzz##yKyyyyK9XWu 5eEEGe. zeE#eeXKKu#EXyyuEzE";
	Free[27]="Ee ##X5KKuWWK 9GeGeKuWu .Ge9DzXXDX559WWWKWWGWuuueE99Ee K9e9#eGW.KuK W5yWeEE";
	Free[28]="EeXWDEWyyu  u K9eee9   yEezDDDDXXXXXXD5WKKD#Ku.XeEEGeK 9EE##eX   KWKKKuzXGG";
	Free[29]="9ee5zGX.zyGeeG yEeEeu.WEEX55XXX555yWKyyWWy#uu..eG#9G9 KeEz#e5 K9# .5yWyE#eE";
	Free[30]="#Eez55e eeGe#eu yEee99#zX5X5yyyWWWWW5XXz##DK u5e9zEzW e##Ee9 ueeey   KXyeeE";
	Free[31]="#GEeDKEXyeeeXW    zeeGEGEEEEEGeEEGeeeyy55W u yE#5zEy  e#GEW  9EzEeeyyXDGeGE";
	Free[32]="#G9eGDueW..u  .eGu WeeGEzG9999#z#9##G     u uX9XDEX  #EeD  K.     K.5DDeE9E";
	Free[33]="#EEEGe5 eX.u. .eeee .zeez99#zDzXDDzG. uu.uu zzzXyeK Kee.  ze5W     yz9ee9#E";
	Free[34]="D#9#EeG5KE#KueeeK G    WeeG#z#XDDXD#  uKu.  zzXX#Wuy5K  ueeGEXE9.KXGeGeE##9";
	Free[35]="#9##EeeeWuDE.KK      55  K#eeeGzXE9u       zE##ee#W   e   #eeezuu5#eeGE#E9E";
	Free[36]="zzzDz#EGeEyWEW u. ueeeee      KEee#KuWKW55Xe9D5    #eeX5.   5  5E#eeE99zzX#";
	Free[37]="zDz####9Eee#WzXW.Keeeee.  DeGy.    W5Wu       .  . #eezDeE  .yD9eGG9zz#z#X9";
	Free[38]="DXXDDDXDDD9Ge#D#DW   Ge   ee#Ge ...   zeee9 Kee.     eee5..#E9eeEGz#zzDXzy9";
	Free[39]="zzzzDDXXXXXD9GGE###y     EeGGeD u. .u eee9Xu Xeeze#u    uX9eeeE9zDXDDXXX#y9";
	Free[40]="DDDDDXXXXXXXDz9EGG9##zXK    ue5 WWKW. Eee    uXeXX. uW9#GeeeE#DX555555XXXK9";
	Free[41]="DXDXXXXXXXX5yy5Dz9eeeEE9EXWu       .u uWK u.     K59eEeeeE#z5yWWWW5Wyy5yy.#";
	Free[42]="XDXXXXXX5555y555XXDzEeeeeGGzD#X#E9DX5KKuKyyz#99zeeeeeeG#zDXyWyWWWKyWWWyKW.z";
	Empire[0]="EEE9EEE9EGDzGE9EEEEEEEEEEGEEGGEGeeeeeeeeeeeeeeeeGEEEGGE9#EEGE#EEEGE9GEEEEEE9";
	Empire[1]="9###99E9EGzzE9EEEEE9E9EEEEEEGGeG5DDWW  5K .yXX9zeEeGEE9eeGGEE9EGEG99GEEEEEE9";
	Empire[2]="###9#zzDEEEEEEEEEEE9999EEEEEEEeE.      u W..  K.eEeE#9#99EEGGEEGEEEEeG9EGEG9";
	Empire[3]="zzz##9#z#zE#99E99999999EEEEEee#EEeeeeDE.yeeeeeeGG9eeEE9eeGEEEEGGEEEEGGEEGEGE";
	Empire[4]="#XXDzEEE#D9z99#####9999EE9EE##GGeeeeeEe WeeeeeeGEGGeeGEEEeGEEEEEGEGEEGeeGEGE";
	Empire[5]="z##zzDz99#eGGE9##9#E9EEEEEEE9#eGGeeeeWy  yzeeeeeGe5Dz9e#DE9eGeE#EEeeeeeGGEEE";
	Empire[6]="zE9#zDD9XyG#eEG9999E9EEEEEEEGGeeeeD    e#   XEeeee9EEEG999#eeeE#GeeeD95#eEE9";
	Empire[7]="#DD#EeGe    eeeeGE9EEEEEEEEEGeee   .Xeeeee9     eeeED#9EGeGE9EGeeez#   XeeE9";
	Empire[8]="9#9e95K5eeW.  9eeeeeGGEEEEEeee   WeeeeeeeeeeDz  5 eG###EEDzeeeee5K  DEe9 5#G";
	Empire[9]="#eG9ee95  u5eX  WGGeeeeeGGeeW9  9EeGGeGeeeeeee.K  e9eGGGeeeee#K   E9X. ueeee";
	Empire[10]="Gz9e#  ueeyK  u      GGeeeee u  eeeeeEEEE99#eeGe  Wyeeeeey5       K.yDeX   9";
	Empire[11]="#e9yXX5W  5XWW   u.W u uW5Ge uD5eE9Ee9E#9#EeEG#e X WeDK u   .uu K.z5K  uX#5e";
	Empire[12]="XeeeGu  uuWK  uu u.WuWKWu.W# y#XezX#E9E#999##zGeDG  X   . u..       55X5uXye";
	Empire[13]="##zz5     .KuKuu yWWuuu5Xzeeyz.Wee9##z9z###D9G#e u##e9D u.KWu   WKDW9y   .ue";
	Empire[14]="#GEeeeeeeeyD u.u.5XyW5XGGeee#e WeGG99D#XDDD5GeEe .eEeee5#  .KWK.u K G#eeeeee";
	Empire[15]="zGEeGzyuX#uyuKWWu5yWuXXeeeEGGe K zeeG#E5D#9GEeu 5WeEEGeEe y KWu u.WuDy#Xy###";
	Empire[16]="GE9G#Ku yD.WKWKu XDW e9eGEEEGeGe.K EeEG##z9eW 5We#GEEEeGeyE KWu WKWuXWDyuK.#";
	Empire[17]="DEEeEKu Ky u.KWyWX55ue9eGEEGEGeeGX X5G##9E95u eeee#EGE#Ee#e 5WWuu.K.KuXWWy.9";
	Empire[18]="uWWzXuWuD#KyKKuWyyKXueGeGGEEEeee5.   eGEE9D   zXee#z#9EEe#e WKKuu.5K5W#XKXWE";
	Empire[19]="D55D5KK.55y5WyWW.Xy5.eEeGGEeeeWy Kzu e#eeDX W5. z5eG#999eDe uuu WK5K9D9XuDXe";
	Empire[20]="eGGe9y  u.DXuWyu XXDWeGeeeeezE WEeeX eXeeyD.Ge#E  e#E9#EeyE Kuu yyW.Gzyy.zze";
	Empire[21]="GEGeG9K Wu9z.KWu K.DyDDGGeee5DWXeeeX GDeGuXyGeGe  DDGEGEe y.u ..yWyuEzuyu#9G";
	Empire[22]="E9EeGeX5K.zXDXWy5. Xyuu5XzXzWy#zeee#WEXeeuXD9GzG WXXD9e#e .uu uyyKDX#DKXXEGE";
	Empire[23]="9GE9Ee#E. WKe#55XKKKuyKWKKKKyyEz5DGz5EzeeKD#9EEeee#zyXEK5 .uKW5XXy#zWyWDEGe9";
	Empire[24]="EGG#Eeee5X.uyXEzDWy  yyXD#E9eE5yWy5Wu#DGEyD#EGEGGeGe9D5  uu u55XzD#zWyX#GGe#";
	Empire[25]="GEEEEGGeGeD9yXDz#Xzy55XzzDyuzzEEEEED5zDy5yD##9#9yX.u uWyy5yKKWXz9##DEEeeE999";
	Empire[26]="#eeeE#EEeeGeyW .KXDXDuu  uyWXyXyKDEGeDXE9.WDuu.uuWuK.u u 5XE9#D5Xy9#eeGE99#9";
	Empire[27]=" .uX#GeGGEeeeeDX55XEGE#yu   uKXyK5X9e5WeeuDe5yy5Wy.uKy5EEE#z#E#X#zeeE999##z9";
	Empire[28]=" ezD.. Ky##E#z9eGEXXyXz9EzDKW     Wy#eX5eeuXeKuuu   .55Xz#DDzXyDEee9#eG999#z";
	Empire[29]=" eeeGEzyu  y5eeeeeGe.K WzEe9Eyy   WDGe5yG9uze5u    yXeEEzDyKW59XWuuXX5X9EEE#";
	Empire[30]=" XeeeeeE9EE5y  .XEeeeeWu   5zGeE#.DEGeWKeEWzeDX#9eeeee95K.DDG#Xyy..  u. uyXW";
	Empire[31]=" y   W#eeeeGee9y    #eeeeD     XXD##GeXDeG5ze9EzDyW    K#9eeGGeEE#9DD ..    ";
	Empire[32]=" eeGX5uKW##eeeeeee#D   W9eeeeyu  5W.9e9#DzEEGzzuu.u5D9GeeeDX##z##9EEeeeG#5XW";
	Empire[33]=" eeeee95K. Wy##9Eeeeee#D5yyXzeeeeee#DyEEEEEzW#GeeeeeeGEE#9##9999###99#9#9#E#";
	Empire[34]=" 9#99EGGeEEy5uW5XX##GEeGE9#DzKK5XE999zGEEEE95#9EE9EEEE9#DD#####zz#z99999#z#z";
	Empire[35]=" eEG9E99EEE#9z5.uuyy##eeeeeEEzzWy5yy5yDX##9#D9999##zz##9EEEE#9######99999#9#";
	Empire[36]=" EEGEE9####EEeeeG9X5uuuKyX#Geee#9####zDXzzD9EGEE9E9E99#z##99###999999##9####";
	Empire[37]=" 9#9#####9####9EEGEe#9XXWWKWWW5z9GzyyWXX9zKWKXX9#eEeEEE9GG99####99999##9#9##";
	Empire[38]=" EEE#############9EGEE##zzDDDzzyyXz#GeDD#E9#yKu  WK9EeeeGGGGGEEE99999999####";
	Empire[39]=" z9#EEE99#######99EEEEEE999#9DDDzXD5eeeeEEuXzDz55uu..uWXEEGEeEGE99#9999999##";
	Empire[40]=" yXX#z##zzz#z##9999999######9zzE9yK X9EGEEuXz#99Ezz5yKu.uuXXE9GEE9#E9E99999#";
	Empire[41]=" WWWy5XDD#z9z#######z#zzzzz###9##DD5DX##9ED#z##99EGGE9zXKK..KWXDzEEeEGE99E9#";
	Empire[42]=" XyyKKKyyXXzDzz#####zzzzzzzzzzzDz9EG#X9#z#Dzzzz##99EEGGGEEzzyWKu.yyXX9#9EE9#";
}

bool SelectUnite::select(){
	Allmessage *am=new Allmessage(0,0,157,66);
	am->draw();
	Allmessage *free= new Allmessage(1,1,77,64);
	for(int scr_y=0;scr_y<42;scr_y++){
		free->setText(2,2+scr_y,Free[scr_y]);
	}
	free->draw();
	Allmessage *free_select= new Allmessage(33,52,15,3);
	free_select->setText(34,53,"자유 혹성연맹");
	free_select->draw();
	Allmessage *empire= new Allmessage(78,1,78,64);
	for(int scr_y=0;scr_y<42;scr_y++){
		free->setText(79,2+scr_y,Empire[scr_y]);
	}
	empire->draw();
	Allmessage *empire_select= new Allmessage(111,52,13,3);
	empire_select->setText(112,53,"은하 제국군");
	empire_select->draw();
	int x,y;
	x=38;
	y=53;
	cc.moveto(x,y);
	char c;
	while((c=getch())!='.'){
		switch(c){
			case KEY_UP:
			break;
			case KEY_DOWN:
			break;
			case KEY_RIGHT:
				if(x==38&&y==53){
					x+=78;
				}
				else if(x==116&&y==53){
					x-=78;
				}
			break;
			case KEY_LEFT:
				if(x==38&&y==53){
					x+=78;
				}
				else if(x==116&&y==53){
					x-=78;
				}
			break;
			case '\r':
				if(free_select->included(x,y)) return true;
				if(empire_select->included(x,y)) return false;
				break;
		}
		cc.moveto(x,y);
		refresh();
	}
}
