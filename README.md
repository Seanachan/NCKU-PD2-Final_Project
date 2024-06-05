# 股票大進擊

## 動機

今日台股的表現讓投資人充滿了信心，包括我們在內，也都一邊看著code，一邊看著股票上上下下。適逢期末，我們想結合這個共同的興趣作為idea，使用java做出一個有趣的小遊戲。

## 內容

以瑪利歐的遊戲作為基礎，我們除了做出外觀包裝上的更動外，也支援同時多人遊玩。

# 實作

## 背景

實現近似於股市k線圖的背景，隨著散戶奔跑而起伏。

### 深色背景

使用:`https://www.formdev.com/flatlaf`  FlatLaf 實作深色模式。

該套件可以使用mavan central 或是gradle Groovy DSL。在build.gradle新增下行，即可使用。

```gradle
implementation 'com.formdev:flatlaf:3.4.1'
```

### 格線


## 遊戲內容
