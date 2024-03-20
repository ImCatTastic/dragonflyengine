package temp.learnBot;

import engine.core.*;
import engine.identification.IDRegistry;
import engine.identification.Identifier;
import engine.rendering.ShapeRenderer;
import engine.spriteBuilder.RectangleShape;
import engine.spriteBuilder.SpriteBuilder;
import engine.ui.*;
import engine.ui.Direction;
import engine.ui.shape.*;
import engine.util.Config;
import engine.util.FitMode;
import engine.util.PivotPoint;
import engine.util.Tuple;
import engine.util.math.Vec2;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import org.w3c.dom.Text;
import temp.learnBot.ui.InputField;
import temp.learnBot.visual.Colors;
import temp.learnBot.visual.FieldGameObject;
import temp.learnBot.visual.UIBuilder;
import temp.learnBot.visual.VisualConstants;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;


public class WorldManager extends GameManager
{
    //public final static Namespace NAMESPACE = new Namespace("world_engine");
    //public final static LoggerIdentifier LOGGER_ID = new LoggerIdentifier(NAMESPACE, "logger");
    //private final Logger logger = new Logger(LOGGER_ID);

    public final static Identifier COIN_SPRITE_ID = new Identifier("coin.png");
    private final static Identifier PICKACHU_SPRITE_ID = new Identifier("pickachu.jpg");
    public final static Identifier COIN_PICKUP_SOUND_ID = new Identifier("collect_coin.mp3");
    public final static Identifier LEVEL_COMPLETE_SOUND_ID = new Identifier("success.wav");
    private final int width;
    private final int height;
    private final Runnable main;
    private static WorldManager instance;
    public static WorldManager getInstance()
    {
        return instance;
    }
    public double speed = 1;
    public static void setSpeed(double value)
    {
        instance.speed = value;
    }
    public static double getSpeed()
    {
        return instance.speed;
    }
    public final static double speedLimit = 5;
    public static WorldManager create(int width, int height, Runnable main)
    {
        if(instance == null)
            instance = new WorldManager(width, height, main);

        return instance;
    }
    private final double totalWidth;
    private final double totalHeight;
    private final GameObject board = new GameObject();
    private final HashMap<Vec2, FieldGameObject> fields = new HashMap<>();
    private WorldManager(int width, int height, Runnable main)
    {
        this.width = width;
        this.height = height;
        this.main = main;

        totalWidth = width + VisualConstants.BORDER_SIZE * (width + 1);
        totalHeight = height + VisualConstants.BORDER_SIZE * (height + 1);
    }
    @Override
    public void init(Config config)
    {
        config.windowTitle = "FOPBot";
        config.windowWidth = 880;
        config.windowHeight = 660;
        config.windowBackgroundColor = Color.rgb(27, 29, 39);

        IDRegistry.register(IDRegistry.SPRITE, PICKACHU_SPRITE_ID);
        IDRegistry.register(IDRegistry.SPRITE, COIN_SPRITE_ID);
        //AudioManager.registerSoundEffect(COIN_PICKUP_SOUND_ID);
        //AudioManager.registerSoundEffect(LEVEL_COMPLETE_SOUND_ID);
    }

    private Camera primaryCamera;

    @Override
    public void start()
    {
        var shMod = SpriteBuilder.getModifier();
        var primaryScene = new GameScene();
        primaryCamera = new Camera();
        primaryCamera.setHorizontalSize(10 + 8 + 0.5);
        primaryCamera.setVerticalSize(10 + 1.2 + 0.75 + 0.5);
        primaryCamera.setFitMode(FitMode.MIN);
        primaryScene.addGameObject(primaryCamera);
        View.getPrimary().setActiveCamera(primaryCamera);


        var boardRenderer = board.createRenderComponent(ShapeRenderer.class);
        var boardShape = new RectangleShape(0, 0, totalWidth * shMod, totalHeight * shMod);
        boardShape.setFill(Colors.BOARD);
        boardRenderer.addShape(boardShape);
        primaryScene.addGameObject(board);

        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                var pos = convertCoords(x, y);
                var field = new FieldGameObject(pos);
                fields.put(pos, field);
                field.setParent(board);
            }
        }

        if(true)
        {
            //TEST START:
            //TODO: remove

            var canvas = SceneManager.getActiveScene().getUiCanvas();

            var mainGrid = new Rectangle2D();
            mainGrid.getUserStyle().define("""
                anchor-horizontal: stretch;
                anchor-vertical: stretch;
                fill: blue;
                
                grid-template-columns: 4fr 10fr 4fr;
                grid-template-rows: 12fr 100fr 8fr;
                
                layout: grid;
            """);
            mainGrid.setParent(canvas);

            var paneLeft = new Rectangle2D();
            paneLeft.getUserStyle().define("""
                anchor-horizontal: stretch;
                anchor-vertical: stretch;
                width: 200px;
                height: 200px;
                corner-radius: 25px;
                fill: rgba(255, 0, 255, 0.75);
                
                insets: 25px;
                
                grid-bounds: ivec4(0, 0, 1, 1);
            """);
            paneLeft.setParent(mainGrid);

            Timer timer = new Timer();

            // Define the task to be executed
            TimerTask task = new TimerTask()
            {
                @Override
                public void run()
                {
                    var grid = mainGrid;
                    var x = paneLeft;

                    System.out.println("Function executed after 2 seconds.");
                }
            };

            timer.schedule(task, 500);



            var paneRight = new Ellipse2D();
            paneRight.getUserStyle().define("""
                anchor-horizontal: stretch;
                anchor-vertical: stretch;
                width: 200px;
                height: 200px;
                left: 25px;
                top: 0px;
                right: 25px;
                bottom: 25px;
                corner-radius: 25px;
                fill: rgb(255, 255, 0);
            """);
            //paneRight.setParent(mainGrid);

            int i = 0;

            /*
            //new UIBuilder(canvas);

            Color helperColor = null;
            var thickness = 0;

            var offset = 25;

            var paneLeft = new Rectangle2D(400, totalHeight * 100);
            paneLeft.applyStyle(new UIStyle()
                                        .setRotation(25)
                                        .setVerticalAnchor(Anchor.Vertical.STRETCH)
            );


            paneLeft.setPivotPoint(PivotPoint.CENTER_RIGHT);
            paneLeft.setFill(helperColor);
            paneLeft.setStrokeColor(Color.WHITE);
            paneLeft.setStrokeThickness(thickness);
            paneLeft.setRight(totalWidth * 50 + offset);
            canvas.add(paneLeft);

            var paneRight = new Rectangle2D(400, totalHeight * 100);
            paneRight.setFill(helperColor);
            paneRight.setStrokeColor(Color.WHITE);
            paneRight.setStrokeThickness(thickness);
            paneRight.setLeft(totalWidth * 50 + offset);
            paneRight.setPivotPoint(PivotPoint.CENTER_LEFT);
            canvas.add(paneRight);


            var paneBot = new Rectangle2D(totalWidth * 100, 75);
            paneBot.setPivotPoint(PivotPoint.TOP_CENTER);
            paneBot.setFill(helperColor);
            paneBot.setStrokeColor(Color.WHITE);
            paneBot.setStrokeThickness(thickness);
            paneBot.setTop(totalHeight * 50 + offset);
            canvas.add(paneBot);




            var robotConfig = new Rectangle2D();
            robotConfig.setWidth(400);
            robotConfig.setHeight(500);
            robotConfig.setCornersRadius(25);
            robotConfig.setFill(Color.WHITE);
            canvas.add(robotConfig);

            var rStack = new StackContainer();
            rStack.setHorizontalAnchor(Anchor.Horizontal.STRETCH);
            rStack.setVerticalAnchor(Anchor.Vertical.STRETCH);
            rStack.setTop(25);
            rStack.setLeft(25);
            rStack.setRight(25);
            rStack.setBottom(25);
            robotConfig.add(rStack);

            var name = new Text2D();
            name.setHorizontalAnchor(Anchor.Horizontal.LEFT);
            name.setText("Robot - 0");
            name.setFontSize(48);
            name.setFill(Color.BLACK);
            rStack.add(name);

            var position = new Text2D();
            position.setHorizontalAnchor(Anchor.Horizontal.LEFT);
            position.setText("Position:");
            position.setFontSize(36);
            position.setFill(Color.BLACK);
            rStack.add(position);

            var name1 = new Text2D();
            name1.setHorizontalAnchor(Anchor.Horizontal.LEFT);
            name1.setText("Direction:");
            name1.setFontSize(36);
            name1.setFill(Color.BLACK);
            rStack.add(name1);

            var coins = new Text2D();
            coins.setHorizontalAnchor(Anchor.Horizontal.LEFT);
            coins.setText("Coins:");
            coins.setFontSize(36);
            coins.setFill(Color.BLACK);
            rStack.add(coins);

            var enabled = new Text2D();
            enabled.setHorizontalAnchor(Anchor.Horizontal.LEFT);
            enabled.setText("Enabled:");
            enabled.setFontSize(36);
            enabled.setFill(Color.BLACK);
            rStack.add(enabled);

            var remove = new Text2D();
            remove.setHorizontalAnchor(Anchor.Horizontal.LEFT);
            remove.setText("Remove");
            remove.setFontSize(36);
            remove.setFill(Color.BLACK);
            rStack.add(remove);


            //TEST END:
            s = totalHeight * 100;




            var filler = new Rectangle2D();
            filler.setHorizontalAnchor(Anchor.Horizontal.STRETCH);
            filler.setVerticalAnchor(Anchor.Vertical.STRETCH);
            filler.setFill(Color.rgb(41, 45, 65));
            filler.setCornersRadius(20);
            paneBot.add(filler);

            var playPause = new Polygon2D(3, 0);
            playPause.setHeight(50);
            playPause.setWidth(50);
            playPause.setCornerRadiusAbsolute(10);
            playPause.setLeft(25);
            playPause.setHorizontalAnchor(Anchor.Horizontal.LEFT);
            playPause.setFill(Color.rgb(0, 176, 255));
            filler.add(playPause);
            //mediaContainer.add(playPause);

            var playPause2 = new Rectangle2D();
            playPause2.setHeight(10);
            playPause2.setWidth(815);
            playPause2.setHorizontalAnchor(Anchor.Horizontal.LEFT);
            playPause2.setCornersRadius(15);
            playPause2.setLeft(110);
            playPause2.setFill(Color.rgb(0, 176, 255));
            //mediaContainer.add(playPause2);
            filler.add(playPause2);

            var playSpeed = new Rectangle2D();
            playSpeed.setHorizontalAnchor(Anchor.Horizontal.RIGHT);
            playSpeed.setRight(25);
            playSpeed.setHeight(35);
            playSpeed.setWidth(80);
            playSpeed.setCornersRadius(50);
            playSpeed.setLeft(25);
            playSpeed.setFill(Color.rgb(0, 176, 255));
            //mediaContainer.add(playPause2);
            filler.add(playSpeed);

            var c = new StackContainer();
            c.setFlowDirection(Direction.RIGHT);
            c.setVerticalAnchor(Anchor.Vertical.STRETCH);
            c.setHorizontalAnchor(Anchor.Horizontal.STRETCH);
            c.setLeft(22);
            c.setTop(5);
            playSpeed.add(c);

            var text = new Text2D();
            text.setText("1");
            text.setFontSize(32);
            text.setFill(Color.WHITE);
            c.add(text);

            var symbol = new Text2D();
            symbol.setText("×");
            symbol.setFontSize(68);
            symbol.setFill(Color.WHITE);
            c.add(symbol);






            var fillerLeft = new Rectangle2D();
            fillerLeft.setHorizontalAnchor(Anchor.Horizontal.STRETCH);
            fillerLeft.setVerticalAnchor(Anchor.Vertical.STRETCH);
            fillerLeft.setFill(Color.rgb(41, 45, 65));
            fillerLeft.setCornersRadius(20);
            paneLeft.add(fillerLeft);

            var stackContainer = new StackContainer();
            stackContainer.setPivotPoint(PivotPoint.TOP_LEFT);
            stackContainer.setHorizontalAnchor(Anchor.Horizontal.STRETCH);
            stackContainer.setTop(25);
            stackContainer.setLeft(25);
            stackContainer.setRight(25);
            stackContainer.setVerticalAnchor(Anchor.Vertical.STRETCH);
            fillerLeft.add(stackContainer);

            var title = new Text2D();
            title.setText("Configuration");
            title.setFill(Color.rgb(155, 175, 235));
            title.setHorizontalAnchor(Anchor.Horizontal.LEFT);
            title.setTop(0);
            title.setFontSize(42);
            stackContainer.add(title);

            var line = new Line2D();
            line.setHorizontalAnchor(Anchor.Horizontal.STRETCH);
            line.setStrokeColor(Color.rgb(155, 175, 235));
            line.setHeight(5);
            stackContainer.add(line);

            var field1 = new InputField("Width:", 36);
            field1.setHorizontalAnchor(Anchor.Horizontal.STRETCH);
            field1.setTop(25);
            field1.setHeight(60);
            stackContainer.add(field1);

            var field2 = new InputField("Height:", 36);
            field2.setTop(25);
            field2.setHeight(60);
            field2.setHorizontalAnchor(Anchor.Horizontal.STRETCH);
            stackContainer.add(field2);

            var field3 = new InputField("Seed:", 36);
            field3.setTop(25);
            field3.setHeight(60);
            field3.setHorizontalAnchor(Anchor.Horizontal.STRETCH);
            stackContainer.add(field3);

            var export = new Rectangle2D();
            export.setVerticalAnchor(Anchor.Vertical.BOTTOM);
            export.setHorizontalAnchor(Anchor.Horizontal.CENTER);
            export.setBottom(25);
            export.setWidth(350);
            export.setCornersRadius(10);
            export.setStrokeColor(Color.rgb(0, 176, 255));
            export.setStrokeThickness(1.35);
            export.setFill(Color.rgb(41, 45, 65));
            export.setHeight(80);
            paneLeft.add(export);

            var exportLabel = new Text2D();
            exportLabel.setText("Create Replay");
            exportLabel.setTop(5);
            exportLabel.setFontSize(34);
            exportLabel.setFill(Color.WHITE);
            export.add(exportLabel);

            var export2 = new Rectangle2D();
            export2.setVerticalAnchor(Anchor.Vertical.BOTTOM);
            export2.setHorizontalAnchor(Anchor.Horizontal.CENTER);
            export2.setBottom(135);
            export2.setWidth(350);
            export2.setCornersRadius(10);
            export2.setStrokeColor(Color.rgb(0, 176, 255));
            export2.setStrokeThickness(1.35);
            export2.setFill(Color.rgb(41, 45, 65));
            export2.setHeight(80);
            paneLeft.add(export2);

            var exportLabel2 = new Text2D();
            exportLabel2.setText("Export world");
            exportLabel2.setTop(5);
            exportLabel2.setFontSize(34);
            exportLabel2.setFill(Color.WHITE);
            export2.add(exportLabel2);

            var export3 = new Rectangle2D();
            export3.setVerticalAnchor(Anchor.Vertical.BOTTOM);
            export3.setHorizontalAnchor(Anchor.Horizontal.CENTER);
            export3.setBottom(245);
            export3.setWidth(350);
            export3.setCornersRadius(10);
            export3.setStrokeColor(Color.rgb(0, 176, 255));
            export3.setStrokeThickness(1.35);
            export3.setFill(Color.rgb(41, 45, 65));
            export3.setHeight(80);
            paneLeft.add(export3);

            var exportLabel3 = new Text2D();
            exportLabel3.setText("Import world");
            exportLabel3.setTop(5);
            exportLabel3.setFontSize(34);
            exportLabel3.setFill(Color.WHITE);
            export3.add(exportLabel3);



            var containerRight = new Rectangle2D();
            containerRight.setVerticalAnchor(Anchor.Vertical.STRETCH);
            containerRight.setHorizontalAnchor(Anchor.Horizontal.STRETCH);
            containerRight.setCornersRadius(25);
            containerRight.setFill(Color.rgb(41, 45, 65));
            paneRight.add(containerRight);

            var stackRight = new StackContainer();
            stackRight.setHorizontalAnchor(Anchor.Horizontal.STRETCH);
            stackRight.setVerticalAnchor(Anchor.Vertical.STRETCH);
            stackRight.setTop(25);
            stackRight.setLeft(25);
            stackRight.setRight(25);
            stackRight.setFlowDirection(Direction.DOWN);
            containerRight.add(stackRight);

            var gridSelector1 = new Visuals.GridSelector(stackRight, 3, 3);
            gridSelector1.title.setText("ROBOTS:");

            var gridSelector2 = new Visuals.GridSelector(stackRight, 3, 1);
            gridSelector2.title.setText("OBSTACLES:");

            var gridSelector3 = new Visuals.GridSelector(stackRight, 3, 1);
            gridSelector3.title.setText("MISCELLANEOUS:");

             */
        }

        new Thread(main).start();
    }

    private double calcOffset(double v)
    {
        return unit * v + border * v;
    }

    private int elementCount = 0;
    private final double unit = 75;
    private final double border = 10;
    private final double initialOffset = 60;
    private final double fontSize = 48;
    private void createInputField(UIComponent parent, String text, double width, int x, int y)
    {
        /*
        var label = new Text2D();
        label.setPivotPoint(PivotPoint.CENTER_LEFT);
        label.setHorizontalAnchor(Anchor.Horizontal.LEFT);
        label.setVerticalAnchor(Anchor.Vertical.TOP);
        label.setText(text);
        label.setLeft(25 + calcOffset(x));
        label.setTop(initialOffset + unit * 1.5 * y);
        label.setFill(Color.WHITE);
        label.setFontSize(fontSize);
        parent.add(label);

        var field = new TextField2D();
        field.setPivotPoint(PivotPoint.CENTER_LEFT);
        field.setHorizontalAnchor(Anchor.Horizontal.LEFT);
        field.setVerticalAnchor(Anchor.Vertical.TOP);
        field.setTop(initialOffset + unit * 1.5 * y);
        field.setLeft(calcOffset(x + 2.5));
        field.setWidth(unit * width + border * (width - 1));
        field.setHeight(unit);
        field.setPaddingTop(5);
        field.setPaddingBottom(5);
        field.setPaddingLeft(6);
        field.setPaddingRight(6);
        field.setFontSize(fontSize * 1.05d);
        field.setFill(null);
        field.setStrokeColor(Color.WHITE);
        field.setStrokeThickness(2);
        field.setCornersRadius(5);
        parent.add(field);

         */
    }




    private void generateEditor(UIComponent parent)
    {
        /*
        var label = new Text2D();
        label.setHorizontalAnchor(Anchor.Horizontal.CENTER);
        label.setVerticalAnchor(Anchor.Vertical.TOP);
        label.setText("Editor");
        label.setTop(25);
        label.setFillColor(Color.WHITE);
        label.setFontSize(76);
        parent.add(label);

        var reloadButton = new Rectangle2D();
        reloadButton.setHorizontalAnchor(Anchor.Horizontal.LEFT);
        reloadButton.setVerticalAnchor(Anchor.Vertical.TOP);
        reloadButton.setPivotPoint(PivotPoint.TOP_LEFT);
        reloadButton.setWidth(100);
        reloadButton.setHeight(100);
        reloadButton.setLeft(25);
        reloadButton.setCornersRadius(10);
        reloadButton.setTop(300);
        reloadButton.setFillColor(Color.WHITE);
        parent.add(reloadButton);

         */
    }






    public static void shake()
    {

    }



    static Rectangle2D popUp;
    private static void displayEnd()
    {
        /*
        var canvas = SceneManager.getActiveScene().getUiCanvas();
        popUp = new Rectangle2D();
        popUp.setHorizontalAnchor(Anchor.Horizontal.STRETCH);
        popUp.setVerticalAnchor(Anchor.Vertical.STRETCH);
        popUp.setFill(Color.rgb(0, 0 , 0, 0.6));
        canvas.add(popUp);

        var line = new Rectangle2D();
        line.setHorizontalAnchor(Anchor.Horizontal.CENTER);
        line.setVerticalAnchor(Anchor.Vertical.CENTER);
        line.setHeight(7.5);
        line.setWidth(775);
        line.setCornersRadius(25);
        line.setFill(Color.WHITE);
        popUp.add(line);

        if(true)
            return;

        var text2 = new Text2D();
        text2.setHorizontalAnchor(Anchor.Horizontal.CENTER);
        text2.setVerticalAnchor(Anchor.Vertical.CENTER);
        text2.setPivotPoint(PivotPoint.TOP_LEFT);
        text2.setTop(-60);
        text2.setText("LEVEL COMPLETE");
        text2.setFill(Color.WHITE);
        text2.setFontSize(96);
        popUp.add(text2);

         */
    }

    public static void displayLooser()
    {
        /*
        Platform.runLater(() ->
                          {
                              displayEnd();

                              var text = new Text2D();
                              text.setHorizontalAnchor(Anchor.Horizontal.CENTER);
                              text.setVerticalAnchor(Anchor.Vertical.CENTER);
                              text.setFill(Colors.PT_NEON_RED.fx);
                              text.setTop(60);
                              text.setText("Contaminants won!");
                              text.setFontSize(64);
                              popUp.add(text);
                          });

         */
    }

    public static void displayWinner()
    {
        /*
        Platform.runLater(() ->
                          {

                              displayEnd();

                              var text = new Text2D();
                              text.setHorizontalAnchor(Anchor.Horizontal.CENTER);
                              text.setVerticalAnchor(Anchor.Vertical.CENTER);
                              text.setFill(Colors.PT_NEON_GREEN.fx);
                              text.setTop(60);
                              text.setText("Cleaning robot won!");
                              text.setFontSize(64);
                              popUp.add(text);
                          });

         */
    }

    public static double progressA = 0;
    public static double progressB = 0;


    double s = 0;
    Rectangle2D y = new Rectangle2D();
    Rectangle2D x = new Rectangle2D();
    public static Runnable runnable = () -> {};

    double i = 0;
    double z = 0;
    double freq = 2;
    @Override
    public void update()
    {
        if(true)
            return;

        Runnable action = null;
        if(Input.isKeyHeld(KeyCode.W) || Input.isKeyHeld(KeyCode.UP))
            action = () -> Temp.player.handleKeyInput(0, false, false);
        else if(Input.isKeyHeld(KeyCode.D) || Input.isKeyHeld(KeyCode.RIGHT))
            action = () -> Temp.player.handleKeyInput(1, false, false);
        else if(Input.isKeyHeld(KeyCode.S) || Input.isKeyHeld(KeyCode.DOWN))
            action = () -> Temp.player.handleKeyInput(2, false, false);
        else if(Input.isKeyHeld(KeyCode.A) || Input.isKeyHeld(KeyCode.LEFT))
            action = () -> Temp.player.handleKeyInput(3, false, false);
        else if(Input.isKeyHeld(KeyCode.SPACE))
            action = () -> Temp.player.handleKeyInput(-1, true, false);
        else if(Input.isKeyHeld(KeyCode.R))
            action = () -> Temp.player.handleKeyInput(-1, false, true);

        if(action != null)
            TasqueManager.submitIfAvailable(Temp.player, action);



        runnable.run();

        var v = (z += 1) % (totalHeight * 50);
        //y.setWidth(totalHeight * 50 * progressA);
        //x.setWidth(totalHeight * 50 * progressB);


        if(true)
            return;

        double size = 2.5;

        i += size * 0.005;
        i %= size * 2;
        double out;
        if(i > size)
        {
            out = size * 2 - i;
        }
        else
        {
            out = i;
        }

        //https://gamedev.stackexchange.com/questions/188841/how-to-smoothly-interpolate-2d-camera-with-pan-and-zoom
        primaryCamera.setZoom(1 * Math.pow(size, out / (size * 2)));
    }


    public void addObject(GameObject object)
    {
        Platform.runLater(() -> object.setParent(board));
    }
    void clearObject(GameObject object)
    {

    }
    public Vec2 convertCoords(double x, double y)
    {
        double offsetX = totalWidth * 0.5 - 0.5 - VisualConstants.BORDER_SIZE;
        double offsetY = totalHeight * 0.5 - 0.5 - VisualConstants.BORDER_SIZE;
        var mx = x * VisualConstants.FIELD_SIZE - offsetX + VisualConstants.BORDER_SIZE * x;
        var my = y * VisualConstants.FIELD_SIZE - offsetY + VisualConstants.BORDER_SIZE * y;
        return new Vec2(mx, my);
    }
}