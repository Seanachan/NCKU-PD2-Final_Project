# 股票大進擊

## 動機

身為資工系，一入學的時候充滿了抱負，想要廣泛的學習，增進自己的實力，然後應用所學在有興趣的事情上。

我們設計了一個遊戲來描述資工系的一生。

初出茅廬，必須學習好程式語言，才能進行應用。

中期，要學習一些程式以外的知識，才能和寫程式的專長結合。

後期，開始更深入和程式奮鬥，又曾幾何時感到挫折，常常變成韭菜。

祝我們都能在未來各自的領域上發光發熱。

## 內容

以瑪利歐的遊戲作為基礎，我們除了做出外觀包裝上的更動外，也支援同時多人遊玩。

# 實作

## CISE Snake Game

### 蒐集“System.out.println();” 來完成任務。

## Flappy Doge

### 概念

用股市的k線圖當作背景，實現flapy bird的遊戲。其中，使用 Doge Coin的圖片作為上下移動的主要標的。隨著過關斬將通過一根根的韭菜，分數持續增加，而k線圖也會追蹤Doge Coin的位置而上下起伏。借助Eclipse的開發工具，加速介面的開發速度。

#### 以contentPane 為基底，增加JPanel

JPanel 的部分有：infoBar, Grids, 格線右邊的數字

#### 遊戲流程

```mermaid
graph LR
id1(enter) --> id2[Pop Game Instructions] --Close Tab--> id4[Game Start]--> id6[Bird start dropping]

```

#### 格線

override`drawComponent(Graphics g)`，使格線畫在JPanel上。
使用`g.drawLine(x1,y1,x2,y2)`，for迴圈迭代畫出格線。

#### K線

由於畫面佔滿K線之後要將先前畫上去的K線刪除，因此用Deque儲存KLine的object, 只要size超過一定數量就pop front。

每次新增新的KLine後，就要repaint()這個JPanel，然後paintComponent()，因為某些原因repaint()應該要可以call paintComponent() 的，但結果是把整個JPanel上畫好的都刪掉而已。

因此流程改為，每 [timeFrame] ms 新增新的KLine到KLineGraph，然後每[timeFrame/3] ms，call repaint() 刷新JPanel，然後[timeFrame*2] ms後，call paintComponent()。

```mermaid
graph LR
id1{KLineGraph full?} --> YES
id1{KLineGraph full?} --> NO
NO --> KLine_Generated
YES --> removeFirst --> KLine_Generated

KLine_Generated --timeFrame ms--> KLineGraph
KLineGraph --timeFrame/3--> repaint
KLineGraph --timeFrame/3--> repaint
KLineGraph --timeFrame/3--> repaint
repaint --timeFrame*2--> paintComponent
```

#### Class Diagram for Doge Game

```mermaid
classDiagram
graph LR
    class FlappyBird {
        -int BOARD_WIDTH
        -int BOARD_HEIGHT
        -Image backgroundImg
        -Image birdImg
        -Image topPipeImg
        -Image bottomImg
        -Image bottomPipeImg
        -int birdX
        -int birdY
        -final int birdWidth
        -final int birdHeight
        -boolean gameOver
        -int gameTime
        -int score
        -Bird bird
        -int velocityX
        -int velocityY
        -int gravity
        -List~Pipe~ pipes
        -Random random
        -static Timer gameLoop
        -static Timer placesPipesTimer
        +FlappyBird()
        +startAllTimer()
        +placePipes()
        +paintComponent(Graphics g)
        +draw(Graphics g)
        +move()
        +boolean collision(Bird bird, Pipe pipe)
        +void actionPerformed(ActionEvent e)
        +void keyPressed(KeyEvent e)
        +void keyTyped(KeyEvent e)
        +void keyReleased(KeyEvent e)
    }

    class Bird {
        -int x
        -int y
        -int width
        -int height
        -Image img
        +Bird(Image img)
    }

    class Pipe {
        -int x
        -int y
        -int width
        -int height
        -Image img
        -boolean passed
        +Pipe(Image img)
    }

    class Background {
        +Background(FlappyBird game)
        +void update()
    }

    class DogeGame {
        -static JFrame frame
        -static boolean isComplete
        -static int score
        +void launch()
        +static void main(String[] args)
    }

    class OsUtils {
        +static boolean isWindows()
    }

    class KLineGraph {
        +static void setKLine(int y)
        +static void draw(Graphics g)
        +static Timer gameLoop
    }

    FlappyBird --> Bird : contains
    FlappyBird --> Pipe : contains
    FlappyBird --> Background : uses
    DogeGame --> FlappyBird : contains
    FlappyBird --> KLineGraph : uses
    FlappyBird --> OsUtils : uses

```

#### Class Diagram for dynamic Background

```mermaid
classDiagram
Background <|--JPanel :extends
Grids <|--JPanel  :extends
KLineGraph <|--JPanel :extends
KLineGraph --|> ActionListener :implements
KLineGraph ..|> KLine
Main --|> Background
class Main{
  final BOARD_HEIGHT
  final BOARD_WIDTH
}
class Background{
  +String GRids
  -JPanel contentPane
  static Integer dealNumInteger
  static Double startPriceDouble
  static Double highestPriceDouble
  static Double lowestPriceDouble
  static Double endPriceDouble
  static Double diffPriceDouble

  static JLabel startPrice
  static JLabel endPrice 
  static JLabel highestPrice
  static JLabel lowestPrice
  static JLabel diffPrice
  static JLabel dealAmount
  Background()
  +static updateInfo()
}
class Grids{
  +Grids()
  -int width
  -int height
  -int rows
  -int columns

  # paintComponent()
}
class KLine{
  -double  highest
  -double  lowest
  -double  startPrice
  -double  endPrice
  +KLine(highest, lowest, startPrice, endPrice)
}

class KLineGraph {
  
  Deque<KLine> KLineDeque
  Timer updateLineTimer
  Timer gameLoop
  int timeFrame
  +KLineGraph()
  + setKLine()
  + paintComponent(g)
  + draw()
  +actionPerformed(ActionEvent e)
 
}
  

```

方型圓邊：起/始點
正方形: process
菱形:decisiion
平行四邊形: I/O

```mermaid
graph LR
  id1([開始]) --> id2[Start Menu]--> id3[Load Map] --> Start
  id12[End Page]
```

## 韭菜瑪利歐

### 概念

為了運用所學，必須征服一個個的程式語言(敵人)，來增加分數。如果禁止不動的話會變成韭菜，死掉的話會變成韭菜水餃。
