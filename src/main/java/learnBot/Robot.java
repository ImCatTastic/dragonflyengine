package learnBot;

import learnBot.visualComponent.RobotVC;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.stream.IntStream;

public class Robot extends Entity
{
    private static int robotCount = 0;

    private RobotVC vc;
    private int id;
    private boolean off = false;
    private RobotFamily robotFamily;

    public void setSpeed(double speed)
    {
        if(!Config.headlessModeEnabled())
            vc.setSpeed(speed);
    }
    public Robot(int x, int y)
    {
        this(x, y, RobotFamily.TRIANGLE_BLUE);
    }

    public Robot(int x, int y, RobotFamily robotFamily)
    {
        this(x, y, Direction.UP, 0, robotFamily);
    }

    public Robot(int x, int y, Direction direction, int numberOfCoins)
    {
        this(x, y, direction, numberOfCoins, RobotFamily.TRIANGLE_BLUE);
    }

    public Robot(int x, int y, Direction direction, int numberOfCoins, RobotFamily robotFamily)
    {
        super(x, y, direction);
        this.id = robotCount++;
        this.robotFamily = robotFamily;

        coins.addAll(IntStream.range(0, numberOfCoins).mapToObj(i -> new Coin(x, y)).toList());

        if(!Config.headlessModeEnabled())
            this.vc = new RobotVC(x, y);
    }

    protected Robot(@NotNull Robot robot)
    {
        super(robot.getX(), robot.getY(), robot.getDirection());
        this.coins = robot.coins;
        this.id = robot.id;
        this.off = robot.off;
        this.robotFamily = robot.robotFamily;
    }

    public boolean isFrontClear()
    {
        int nextX = getX() + getDirection().getDx();
        int nextY = getY() + getDirection().getDy();

        return World.positionInWorld(nextX, nextY) &&
                World.isObstacleBlockingField(nextX, nextY, this, false) == null;
    }

    public void move()
    {
        if(isFrontClear())
        {
            setX(x + direction.getDx());
            setY(y + direction.getDy());

            System.out.println(this.getX() + " : " + this.getY() + " : " + direction);

            if(!Config.headlessModeEnabled())
            {
                vc.playMove(getDirection());
                Sync.waitForSignal();
            }
        }
    }

    public void teleport(int x, int y)
    {
        setX(x);
        setY(y);
        vc.playTeleport(x, y);
        Sync.waitForSignal();
    }

    public void turnLeft()
    {
        setDirection(Direction.values()[(getDirection().ordinal() + 3) % Direction.values().length]);
        if(!Config.headlessModeEnabled())
        {
            vc.playTurnLeft();
            Sync.waitForSignal();
        }
    }


    public boolean isOnCoin()
    {
        return World.getField(getX(), getY()).hasCollectible(Coin.class);
    }
    LinkedList<Coin> coins = new LinkedList<>();
    public void collectCoin()
    {
        Coin coin = (Coin) World.getField(getX(), getY()).collect(Coin.class);

        if(coin == null)
            throw new RuntimeException("no coins to collect");

        coins.add(coin);
    }

    public void placeCoin()
    {
        if(coins.isEmpty())
            throw new RuntimeException("no coins to place");

        World.getField(getX(), getY()).addEntity(coins.pop());
    }

    public boolean hasAnyCoins()
    {
        return !coins.isEmpty();
    }
    public int getNumberOfCoins()
    {
        return coins.size();
    }

    public void setNumberOfCoins(int coins)
    {
        if(coins < 0)
            throw new RuntimeException("coins can not be negative");

        int delta = coins - this.coins.size();

        if(delta > 0)
            this.coins.addAll(IntStream.range(0, delta).mapToObj(i -> new Coin(getX(), getY())).toList());
        else
            this.coins.subList(0, Math.min(this.coins.size(), Math.abs(delta))).clear();
    }

    public boolean isFacingUp() {
        return direction == Direction.UP;
    }
    public boolean isFacingDown() {
        return direction == Direction.DOWN;
    }
    public boolean isFacingLeft() {
        return direction == Direction.LEFT;
    }
    public boolean isFacingRight() {
        return direction == Direction.RIGHT;
    }
    public void turnOff()
    {
        off = true;
    }
    protected void crash()
    {
        turnOff();
        System.err.println("Robot crashed!");
        throw new RuntimeException("Robot crashed!");
    }

    public boolean isTurnedOff() {
        return off;
    }
    public boolean isTurnedOn() {
        return !off;
    }
    public boolean isOnAnotherRobot() {
        return World.getField(x, y).getEntityCount(Robot.class) > 1;
    }

    protected void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public RobotFamily getRobotFamily() {
        return robotFamily;
    }
    public void setRobotFamily(RobotFamily robotFamily)
    {
        this.robotFamily = robotFamily;
    }

    @Override
    public void setX(int x)
    {
        super.setX(x);
    }

    @Override
    public void setY(int y)
    {
        super.setY(y);
    }

    public void setPosition(int x, int y)
    {
        World.positionInWorld(x, y);
    }
}
