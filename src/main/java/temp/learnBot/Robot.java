package temp.learnBot;

import engine.util.math.Vec2;
import temp.learnBot.entity.CoinEntity;
import temp.learnBot.gameobjects.RobotGameObject;
import temp.learnBot.gameobjects.WorldConfig;
import org.jetbrains.annotations.NotNull;
import temp.learnBot.item.CoinItem;

import java.util.stream.IntStream;

public class Robot extends Entity<RobotGameObject>
{
    private static int robotCount = 0;
    private final int id;
    private RobotFamily robotFamily;
    private int numberOfCoins;
    private boolean off = false;
    private double speed = 1;
    private boolean printTrace;
    public Robot(int x, int y, Direction direction, int numberOfCoins, RobotFamily robotFamily)
    {
        super(x, y, direction);
        this.id = robotCount++;
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.robotFamily = robotFamily;
        this.numberOfCoins = numberOfCoins;
        this.items.addAll(IntStream.range(0, numberOfCoins).mapToObj(i -> new CoinItem()).toList());
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
    protected Robot(@NotNull Robot robot)
    {
        super(robot);
        this.id = robot.id;
        this.numberOfCoins = robot.numberOfCoins;
        this.off = robot.off;
        this.robotFamily = robot.robotFamily;
        this.speed = robot.speed;
        this.printTrace = robot.printTrace;
    }

    public void setSpeed(double speed)
    {
        //TODO: clamp local speed
        this.speed = speed;
        if(!WorldConfig.headlessModeEnabled())
            gameObject.setSpeed(speed);
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

            TasqueManager.scheduleTask(this, () -> gameObject.playMove(direction));
        }

        else
        {
            //TODO: Crash
        }
    }

    public void teleport(int x, int y)
    {
        setX(x);
        setY(y);
        TasqueManager.scheduleTask(this, () -> gameObject.playTeleport(x, y));
    }

    public void turnLeft()
    {
        var index = (getDirection().ordinal() + 3) % Direction.values().length;
        setDirection(Direction.values()[index]);
        TasqueManager.scheduleTask(this, gameObject::playTurnLeft);
    }

    public boolean isOnCoin()
    {
        return World.getField(getX(), getY()).hasEntityOfType(CoinEntity.class);
    }
    public void collectCoin()
    {
        CoinEntity coin = World.getField(getX(), getY()).getEntitiesByType(CoinEntity.class).get(0);
        numberOfCoins++;
        items.add(coin.collectCoin());
        gameObject.playCollectCoin();
    }

    public void placeCoin()
    {
        if(numberOfCoins < 1)
            throw new RuntimeException("no coins to place");

        numberOfCoins--;
        var item = getFirstItemByType(CoinItem.class);
        items.remove(item);
        World.getInstance().placeCoin(x, y, item);
    }
    public boolean hasAnyCoins()
    {
        return numberOfCoins > 0;
    }
    public int getNumberOfCoins()
    {
        return numberOfCoins;
    }
    public void setNumberOfCoins(int coins)
    {
        if(coins < 0)
            throw new RuntimeException("coins can not be negative");

        int delta = coins - numberOfCoins;
        numberOfCoins = coins;

        if(delta > 0)
            IntStream.range(0, delta).mapToObj(i -> new CoinItem()).forEach(items::add);
        else
            removeItemsByType(CoinItem.class, delta);
    }

    public boolean isFacingUp()
    {
        return direction == Direction.UP;
    }
    public boolean isFacingDown()
    {
        return direction == Direction.DOWN;
    }
    public boolean isFacingLeft()
    {
        return direction == Direction.LEFT;
    }
    public boolean isFacingRight()
    {
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
    public boolean isTurnedOff()
    {
        return off;
    }
    public boolean isTurnedOn()
    {
        return !off;
    }
    public boolean isOnAnotherRobot()
    {
        return World.getField(x, y).getEntityCount(Robot.class) > 1;
    }
    public int getId()
    {
        return id;
    }
    public RobotFamily getRobotFamily()
    {
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
    @Override
    protected RobotGameObject createGameObject(Vec2 position)
    {
        return new RobotGameObject(this);
    }
    public void setPosition(int x, int y)
    {
        World.getInstance().transferEntity(this, x, y);
    }
    public String toString()
    {
        String id = String.valueOf(this.id);
        return "Robot{id='" + id + "', at=[" + this.getX() + "/" + this.getY() + "], numberOfCoins=" + numberOfCoins + ", direction=" + this.direction + "}";
    }
}
