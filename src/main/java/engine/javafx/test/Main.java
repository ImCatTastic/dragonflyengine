package engine.javafx.test;

import engine.core.Config;
import engine.identification.Identifier;
import engine.identification.IDRegistry;
import engine.javafx.GameManager;
import engine.javafx.GameObject;
import engine.javafx.GameScene;

public class Main
{
    //public final static Identifier PICKACHU_SPRITE_ID = new Identifier("pickachu.jpg");
    public static void main(String[] args)
    {
        //new MyManager().run();
    }

    public static class MyManager extends GameManager
    {
        private GameObject pickachu;
        private GameObject pickachu1;
        private GameObject pickachu2;
        @Override
        public void init(Config config)
        {
            config.windowTitle = "FX-Engine";
            config.unitSize = 64;
            config.windowWidth = 660;
            config.windowHeight = 660;

            //IDRegistry.register(IDRegistry.SPRITE, PICKACHU_SPRITE_ID);
        }
        @Override
        public void start()
        {
            /*
            var primaryScene = new GameScene(20);
            //var primaryCamera = new Camera(0,0,660,660);

            pickachu = new Pickachu(3, 0, 0);
            pickachu1 = new Pickachu(2, 0, 0);
            pickachu2 = new Pickachu(2, 0, 0);

            //primaryScene.addGameObject(pickachu);
            pickachu1.getTransform().setParent(pickachu.getTransform());
            pickachu2.getTransform().setParent(pickachu1.getTransform());

             */

            //pickachu.getTransform().setScale(0.75, 0.75);
            //pickachu1.getTransform().setScale(0.75, 0.75);
            //pickachu2.getTransform().setScale(0.75, 0.75);
        }
        double i = 0;
        @Override
        public void update()
        {
            double x = i += 0.6d % 360d;
            double y = 360d - x;

            pickachu.getTransform().setRotation(x);
            pickachu1.getTransform().setRotation(y * 2);
            pickachu2.getTransform().setRotation(x * 2);
        }
    }
}
