package temp.learnBot.visual;

public enum ZDistribution
{
    BOARD(0),
    FIELD(1),
    COIN(2),
    ROBOT(3),
    WALL(20);

    private final int index;
    ZDistribution(int index)
    {
        this.index = index;
    }
    public int get()
    {
        return index;
    }
}
