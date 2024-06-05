# 股票大進擊

## 動機

今日台股的表現讓投資人充滿了信心，包括我們在內，也都一邊看著code，一邊看著股票上上下下。適逢期末，我們想結合這個共同的興趣作為idea，使用java做出一個有趣的小遊戲。

## 內容

以瑪利歐的遊戲作為基礎，我們除了做出外觀包裝上的更動外，也支援同時多人遊玩。

# 實作

## 背景

Goal: 實現近似於股市k線圖的背景，隨著散戶奔跑而起伏。
History: 歷經使用VScode手刻code之後發現過於繁雜，改使用Eclipse的windowBuidler作為開發工具。

### 深色背景

使用:`https://www.formdev.com/flatlaf`  FlatLaf 實作深色模式。

該套件可以使用mavan central 或是gradle Groovy DSL。在build.gradle新增下行，即可使用。

```gradle
implementation 'com.formdev:flatlaf:3.4.1'
```

### 格線
override`drawComponent(Graphics g)`，使格線畫在JPanel上。
使用`g.drawLine(x1,y1,x2,y2)`，for迴圈迭代畫出格線。

k線會隨著時間移動，而k線是固定在格線上的，因此要讓格線隨著時間往左移動。(TO-DO)

## 遊戲內容
退後5pixel股價跌1%

往前5pixel股價漲1%

如果停止超過兩秒每秒跌2%

碰到壞蘑菇股價跌10%

吃到好蘑菇漲5%

往後>1120pixel 跌停
