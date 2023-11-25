import learnBot.World;

public class Main
{
    public static void main(String[] args)
    {
        World.createWorld(10, 10, Main::entry);
    }

    public static void entry()
    {

    }
}