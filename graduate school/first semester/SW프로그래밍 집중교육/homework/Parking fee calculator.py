#######################################################################
#
#   [과제1] 주차요금 계산기
#   <입력값 1> 입차월
#   <입력값 2> 입차일(무조건 월요일)
#   <입력값 3> 출차월
#   <입력값 4> 출차일 
#   
#   (조건1) 짝수달은 31일, 홀수달은 30일로 계산한다.
#   (조건2) 첫날은 무조건 월요일이다.
#   (조건3) 요금은 평일 300원, 토요일 500원, 일요일 700원이다.
#   (조건4) 300일 이상 주차시 201일부터 무조건 할인 요금(200원)을 받는다.
#   
#   (제한1) 입력값에 오류는 없다 가정한다.
#   (제한2) 조건문, 반복문 등을 사용하지 않는다.
#   (제한3) 입차년도를 입력받지 않기 때문에 주차 기간이 1년을 넘어갈 수는 없다.
#
#   변수 설명
#   enterMonth: 입차월을 받아들이는 변수
#   enterDay:   입차일을 받아들이는 변수
#   exitMonth:  출차월을 받아들이는 변수
#   exitDay:    출차일을 받아들이는 변수
#   intEnterMonth:  입차월을 받아들인 변수를 계산이 가능하도록 정수형으로 변환하여 저장하는 변수
#   intEnterDay:    입차일을 받아들인 변수를 계산이 가능하도록 정수형으로 변환하여 저장하는 변수
#   intExitMonth:   출차월을 받아들인 변수를 계산이 가능하도록 정수형으로 변환하여 저장하는 변수
#   intExitDay:     출차일을 받아들인 변수를 계산이 가능하도록 정수형으로 변환하여 저장하는 변수
#   startday:       입차한 날짜를 1월 1일을 기준으로 얼마나 지났는지 계산한 변수
#   endday:         출차한 날짜를 1월 1일을 기준으로 얼마나 지났는지 계산한 변수
#   totalDay:       출차한 날짜에서 입차한 날짜를 빼서 총 주차한 날짜를 저장한 변수
#   anotherPriceLimit:  200일을 나누었을 때 몫을 계산하여 200일을 초과하는지 판단하는 변수
#   anotherPriceDay:    totalDay에서 200을 뺀 후 위에 anotherPriceLimit를 곱하여 초과 했을 시에는 양수로 음수일 경우 0을 곱하여
#                       초과한 날을 저장하는 변수
#   regularPriceDay:    주차한 날짜에서 200일을 초과한 날을 제외하고 정상요금을 내는 날짜를 계산하는 변수
#                       만약 200일이 지나지 않았을 경우에 할인요금을 내는 날은 0일로 계산 된다.
#   timeofSunday:       정상요금으로 계산하는 날짜를 7로 나누었을 때 목을 계산하여 몇주를 주차했는지 저장하는 변수
#   surplusDay:         정상요금을 계산하는 주를 제외하고 남은 날짜를 계산하기 위해 7을 나눈 나머지를 저장하는 변수
#   surplusDaySat:      정상요금을 일요일을 제외한 토요일에는 요금이 다르기 때문에 나머지 날 중 6을 나눈 몫을 계산하는 변수
#   surplusWeekday:     정상요금 남은 날짜와 토요일을 빼서 남은 평일을 계산하여 저장하는 변수
#   surplusPrice:       일주일보다 남은 요일을 토요일에 500을 곱하고 평일에 300을 곱하여 저장하는 변수
#   totalParkingPrice:  정상요금 주간에 평일(300*5) 토요일 500 일요일 700을 더한 27000을 곱하고
#                       정상요금을 내는 7일 미만의 요금을 더한 후 할인요금 200을 내는 기간을 더해 총 주차요금을 저장하는 변수
#   
#   프로그램 알고리즘
#   1) 입차월일 출차월일을 입력 받는다.
#   2) 입차월일을 1월 1일을 기준으로 얼마나 지났는지 계산한다.
#   3) 출차월일을 1월 1일을 기준으로 얼마나 지났는지 계산한다.
#   4) 출차월일에 입차월일을 뺀 수를 주자한 기간을 계산한다.
#   5) 주차한 기간을 200으로 나누었을 때 몫을 정수로 계산한 값을 통해 200일이 초과했는지 계산한다.
#   6) 200일을 넘은 할인 요금을 계산하는 날을 계산하여 할인요금을 내지 않는다면 0을 곱해서 초과한 날이 없도록 계산한다.
#   7) 정상요금을 계산하는 날을 총 주차한 날에서 할인요금을 내는 날을 빼서 계산한다.
#   8) 정상요금을 내는 기간을 7로 나눈 목의 정수 값을 총해서 주간 요금을 계산할 수 있게 한다.
#   9) 정상요금을 내는 기간을 7로 나눈 너머지를 계산하여 7일 미만의 요금을 계산할 수 있게 한다.
#   10)7일 미만 기간을 6을 나눈 목의 정수 값의 계산을 통해 토요일 요금을 계산할 수 있도록 한다.
#   11)7일 미만의 기간 중 토요일을 빼서 평일의 기간을 계산 할 수 있도록 한다.
#   12)정상요금 7일 미만의 기간을 토요일 500을 곱하고 평일 300을 곱하여 계산한다.
#   13)총요금은 정상요금의 내는 주간에 주간요금 2700을 곱하고 7일 미만에 기간을 더한 후 
#      할인 요금을 내는 기간에 200을 곱하여 계산한다.
#   14)총 주차요금을 출력한다.
#
#   테스트 값
#
#   1)최소값
#   입차월: 1
#   입차일: 1
#   출차월: 1
#   출차일: 1
#   총 주차 요금은 : 300 입니다.
#
#   2) 경계값 - 1
#   입차월: 1
#   입차일: 1
#   출차월: 7
#   출차일: 16
#   총 주차 요금은 : 76500 입니다.
#
#   3) 경계값
#   입차월: 1
#   입차일: 1
#   출차월: 7
#   출차일: 17
#   총 주차 요금은 : 76800 입니다.
#
#   4) 경계값 + 1
#   입차월: 1
#   입차일: 1
#   출차월: 7
#   출차일: 18
#   총 주차 요금은 : 77000 입니다.
#
#   5) 최대값 - 1
#   입차월: 1
#   입차일: 1
#   출차월: 12
#   출차일: 30
#   총 주차 요금은 : 109800 입니다.
#   
#   6) 최대값
#   입차월: 1
#   입차일: 1
#   출차월: 12
#   출차일: 31
#   총 주차 요금은 : 110000 입니다.
#   
#   7) 마지막 값에서 경계값 + 1
#   입차월: 6
#   입차일: 14
#   출차월: 12
#   출차일: 31
#   총 주차 요금은 : 77000 입니다.
#
#   8) 마지막 값에서 경계값
#   입차월: 6
#   입차일: 15
#   츨차월: 12
#   출차일: 31
#   총 주차 요금은 : 76800 입니다. 
#
#   프로그래밍시 각 변수 값을 확인 할 수 있도록 한 프린트 문은
#   추가 변경 사항이 생길 것이라 가정하여 주석으로 남겨둡니다.
#
###########################################################################

enterMonth = input("입차월: ")
enterDay = input("입차일: ")
exitMonth = input("출차월: ")
exitDay = input("출차일: ")

intEnterMonth = int(enterMonth)
intEnterDay = int(enterDay)
intExitMonth = int(exitMonth)
intExitDay = int(exitDay)

startday = 30 * (intEnterMonth - 1)
startday += (1 * (( intEnterMonth - 1 )//2))
startday += intEnterDay
#print("startday: ", startday)

endday = 30 * (intExitMonth - 1)
endday += (1 * ( ( intExitMonth - 1 )//2))
endday += intExitDay
#print("endday: ", endday)

totalDay = (endday - startday) + 1
anotherPriceLimit = totalDay // 200
#print("totalDay: ", totalDay)

anotherPriceDay = anotherPriceLimit * ( totalDay - 200 )
#print("anotherPriceDay: ", anotherPriceDay)

regularPriceDay = totalDay - anotherPriceDay
timeofSunday = regularPriceDay // 7
#print("timeofSunday: ", timeofSunday)

surplusDay = regularPriceDay % 7
#print("surplusDay: ", surplusDay)

surplusDaySat = surplusDay // 6
#print("surplusDaySat: ", surplusDaySat)

surplusWeekday = surplusDay - surplusDaySat
#print("surplusWeekday: ", surplusWeekday)

surplusPrice = surplusDaySat * 500 + surplusWeekday * 300
#print("surplusPrice: ", surplusPrice)

totalParkingPrice = 2700 * timeofSunday + surplusPrice + anotherPriceDay * 200
print("총 주차 요금은 :", totalParkingPrice, "입니다")