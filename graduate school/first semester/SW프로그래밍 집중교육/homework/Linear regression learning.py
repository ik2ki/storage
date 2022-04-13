import numpy as np

# 선형회귀를 구할 x 데이터, y 데이터를 5x1행렬로 바꾸어 x_data, y_data에 할당한다.
x_data = np.array([1, 2, 3, 4, 5]).reshape(5, 1)
t_data = np.array([5, 8, 11, 14, 17]).reshape(5, 1)

def numbericalDerivation(f, x):
    coefficient = np.zeros_like(x)
    deltaX = 1e-4
    it = np.nditer(x, flags=["multi_index"], op_flags=["readwrite"])
    while not it.finished:
        # itx는 iterator의 multi_index 값을 할당한다.
        idx = it.multi_index
        # tmp_val에 x[idx]를 할당함으로써 밑에 수치미분에서 값을 변화하는 것을 표현한다.
        tmp_val = x[idx]
        x[idx] = tmp_val + deltaX
        # f1은 x에서 deltaX를 더한 작은 변화량을 갖틑다. 
        f1 = f(x)
        x[idx] = tmp_val - deltaX
        # f2는 x에서 deltaX를 뺀 작은 변화량을 같는다.
        f2 = f(x)
        # f1에서 f2를 뺀 후 수치 미분을 하여 coefficient 배열에 넣는다.
        coefficient[idx] = (f1 - f2) / (2*deltaX)
        # 미분을 끝낸 후에는 원래 x값을 tmp_val에서 불러와서 할당한다.
        x[idx] = tmp_val
        # 다음 값으로 이동한다.
        it.iternext()
        # 위의 반복문이 끝나면 결과 값은 반환한다.
    return coefficient

# 손실함수를 구현한다.
def cost_func(x, t):
    y = np.dot(x, W) + b
    return ((1 / len(x)) * np.sum((t - y)**2) )
# W는 행렬곲을 위해 1x1행렬로 되어야한다.
W = np.random.rand(1,1)
# b는 덧샘을 할 것이기 때문에 스칼라 값으로 생성한다.
b = np.random.rand(1)
# 학습률을 정의한다.
learning_rate = 1e-2
# 학습을 위해 반복할 획수를 정의한다.
epochs = 7001
# 학습을 위해서 손실함수를 불로온다.
f = lambda x: cost_func(x_data, t_data)

for epoch in range(epochs):
    # 수치 미분을 통해서 W 값을 갱신한다.
    W -= learning_rate * numbericalDerivation(f, W)
    # 수치 미분을 통해서 b 값을 갱신한다.
    b -= learning_rate * numbericalDerivation(f, b)
    # 200번 마다 반복하고 있는 횟수 W값 b 갑을 출력해준다.
    if epoch % 200 == 0:
        print("epoch:", epoch, "W:", W, "b:", b)

# 사용자로부터 예측할 값을 입력받는다.
input_data = input("예측할 값을 입력해주세요: ")
# 예측한 값을 계산한다.
result = np.dot(W , int(input_data)) + b
print("예측한 값은",int(result[0,0]),"입니다.")