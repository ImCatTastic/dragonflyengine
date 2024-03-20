package temp.learnBot.tmp;

import engine.util.math.IVec2;
import engine.util.math.Vec2;
import temp.learnBot.Direction;
import temp.learnBot.World;

import java.awt.Point;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

/**
 * A {@link MazeGenerator} generates a maze using a maze generation algorithm and ensures that every field is reachable.
 */
public final class MazeGenerator {
    private MazeGenerator() {
    }

    /**
     * Generates a maze using a maze generation algorithm and ensures that every field is reachable.
     * Walls are placed using World.placeHorizontalWall and World.placeVerticalWall.
     */
    public static void generateMaze()
    {
        // Create a 2D array to keep track of the walls
        final WallBlock[][] walls = new WallBlock[World.getWidth()][World.getHeight()];
        for (var x = 0; x < World.getWidth(); x++)
        {
            for (var y = 0; y < World.getHeight(); y++)
            {
                walls[x][y] = new WallBlock(Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT);
            }
        }

        // Create a 2D array to keep track of visited fields
        final var visited = new boolean[World.getWidth()][World.getHeight()];

        // Create a stack to track the visited cells
        final var stack = new ArrayDeque<IVec2>();

        // Start the maze generation from the top-left corner (0, 0)
        var current = new IVec2(0, 0);
        visited[current.x][current.y] = true;
        stack.push(current);

        int seed = 576415;
        Random random = new Random(seed);

        // Continue until all cells have been visited
        while (!stack.isEmpty())
        {
            // Get the unvisited neighboring cells of the current cell
            final var neighbours = getNeighbours(current)
                    .stream()
                    .filter(p -> !visited[p.x][p.y])
                    .toList();

            if (!neighbours.isEmpty())
            {
                // Choose a random neighboring cell
                final var next = neighbours.get(random.nextInt(neighbours.size()));
                visited[next.x][next.y] = true;

                walls[current.x][current.y].removeWall(Utils.getRelativeDirection(current, next));
                walls[next.x][next.y].removeWall(Utils.getRelativeDirection(next, current));

                stack.push(next);
                current = next;
            }

            else
            {
                // Backtrack to the previous cell
                current = stack.pop();
            }
        }

        // Place the walls
        for (var x = 0; x < World.getWidth(); x++) {
            for (var y = 0; y < World.getHeight(); y++) {
                final var wallBlock = walls[x][y];
                if (wallBlock != null) {
                    for (final var wall : wallBlock.getWalls())
                    {
                        switch (wall)
                        {
                            case UP -> {
                                if(y != World.getHeight() - 1)
                                    World.placeWall(x, y, true);
                            }
                            case DOWN, LEFT -> { } // Not needed, will lead to an exception
                            case RIGHT -> {
                                if(x != World.getWidth() - 1)
                                    World.placeWall(x, y, false);
                            }
                            default -> throw new IllegalStateException("Unexpected value: " + wall);
                        }
                    }
                }
            }
        }
    }

    /**
     * Returns a list of neighboring cells for the given cell.
     *
     * @param p The cell coordinates
     * @return List of neighboring cells
     */
    private static List<IVec2> getNeighbours(final IVec2 p) {
        return Stream.iterate(new IVec2(1, 0), p2 -> new IVec2(-p2.y, p2.x))
                     .limit(4)
                     .map(p2 -> new IVec2(p.x + p2.x, p.y + p2.y))
                     .filter(p2 -> Utils.isValidCoordinate(p2.x, p2.y))
                     .toList();
    }

}
