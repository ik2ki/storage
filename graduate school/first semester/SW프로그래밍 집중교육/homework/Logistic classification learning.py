import numpy as np

x1_data = np.array([1, 3, 5, 7, 9 ,11, 13, 15, 17, 19]).reshape(10, 1)
x2_data = np.array([4, 11, 6, 5, 7, 16, 8, 3, 7, 9]).reshape(10, 1)
t_data = np.array([0, 0, 0, 0, 0, 1, 1, 1, 1, 1]).reshape(10, 1)

W1 = np.random.rand(1, 1)
W2 = np.random.rand(1, 1)
b = np.random.rand(1)

def sigmoid(x):
    return 1 / (1 + np.exp(-x))

def numberic_Deriveration(f, x):
    coefficient = np.zeros_like(x)
    it = np.nditer(x, flags=["multi_index"], op_flags=["readwrite"])
    while not it.finished:
        idx = it.multi_index
        deltaX = 1e-4
        tmp_val = x[idx]
        x[idx] = tmp_val + deltaX
        fx1 = f(x)
        x[idx] = tmp_val - deltaX
        fx2 = f(x)
        coefficient[idx] = (fx1 - fx2) / (2*deltaX)
        x[idx] = tmp_val
        it.iternext()
    return coefficient

def loss_func(x, t, W):
    delta = 1e-7
    z = np.dot(x, W) + b
    y = sigmoid(z)

    return -np.sum( t*np.log(y+delta) + (1-t)*np.log((1 -y)+delta))

def predict(x1, x2):
    W = np.array([W1, W2]).reshape(1, 2)
    x = np.array([x1, x2]).reshape(2, 1)
    z = np.dot(W, x) + b
    y = sigmoid(z)

    if y >= 0.5:
        result = 1 # True
    else:
        result = 0 # False
    return y, result

f1 = lambda x : loss_func(x1_data, t_data, W1)
f2 = lambda x : loss_func(x2_data, t_data, W2)
learning_rate = 1e-2
epochs = 10001

for epoch in range(epochs):
    W1 -= learning_rate * numberic_Deriveration(f1, W1)
    W2 -= learning_rate * numberic_Deriveration(f2, W2)
    b -= learning_rate * numberic_Deriveration(f1, b)
    if epoch % 200 == 0:
        print("epoch:", epoch, "W1:", W1, "W2:", W2, "b:", b)

# 사용자로부터 예측할 값을 입력받는다.
input_data1 = input("첫번 째 값을 입력해주세요: ")
input_data2 = input("두번 째 값을 입력해주세요: ")

(real_val, logical_val) = predict(int(input_data1), int(input_data2))

#print(real_val, logical_val)

print("계산 값은: ", real_val[0, 0],"예측한 값은: ",logical_val,"입니다.")