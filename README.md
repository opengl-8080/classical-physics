# classical-physics
##必要な環境
- Java 1.8.0_51 以上

##動かし方
###jar を作って動かす
```bash:
$ gradlew jar

$ java -jar build/libs/classical-physics.jar
```

###eclipse に取り込んで動かす
```bash:
$ gradlew eclipse
```

Eclipse に「既存プロジェクト」として取り込んで、 `gl8080.phsics.Main` を実行。

##動かすデモを切り替える
`Main` クラスにメソッド単位でコメントアウトしてます。

```java
public class Main extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }
    
    private ExecutorService service = Executors.newSingleThreadExecutor();
    private Time time;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setResizable(false);
        
        Parent parent = this.firstLawOfMotion(); // 等速直線運動
//        Parent parent = this.secondLawOfMotion(); // 放物線運動
//        Parent parent = this.circle(); // 円運動
//        Parent parent = this.tension(); // 振り子
//        Parent parent = this.universalGravitation1(); // 万有引力（冥王星 - カロン）
//        Parent parent = this.universalGravitation2(); // 万有引力（彗星）
//        Parent parent = this.universalGravitation3(); // 万有引力（恒星 - 惑星 - 衛星）
        
        Scene root = new Scene(parent);
        primaryStage.setScene(root);
        primaryStage.show();
        
        primaryStage.setOnCloseRequest(e -> {
            this.time.stop();
            this.service.shutdown();
        });
    }
```

動かしたい行のコメントだけを外して実行すれば、デモを切り替えられます。
