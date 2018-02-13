package ca.casualt.snakes.basicbattlesnake.utilities.movers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import ca.casualt.snakes.basicbattlesnake.types.Move;
import ca.casualt.snakes.basicbattlesnake.types.MoveRequest;
import ca.casualt.snakes.basicbattlesnake.types.Point;
import ca.casualt.snakes.basicbattlesnake.types.Snake;
import ca.casualt.snakes.basicbattlesnake.utilities.BoardDerivations;
import ca.casualt.snakes.basicbattlesnake.utilities.PathingDerivations;

/**
 * Moves randomly, and only considers not running into snakes/edges.<br>
 * *no food consideration
 *
 * @author Tony
 *
 */
public final class RandomMover implements Mover {

	private final Random random;

	public RandomMover() {
		this.random = new Random();
	}

	@Override
	public Move getMove(final MoveRequest moveRequest) {
		List<Move> possibleMoves = getPossibleMoves(moveRequest, moveRequest.getYou().getHead());
		return getRandomMove(possibleMoves);
	}

	/**
	 * From a list of possible moves, choose a random one.
	 *
	 * @param possibleMoves
	 * @return
	 */
	public Move getRandomMove(final List<Move> possibleMoves) {
		if (possibleMoves.isEmpty()) {
			return Move.down; // We're doomed no matter what we do.
		} else {
			return possibleMoves.get(random.nextInt(possibleMoves.size()));
		}
	}

	/**
	 * For re-use, allows possible non-immediate-death directions.
	 *
	 * @param moveRequest
	 * @param snakeHead
	 * @return
	 */
	public static List<Move> getPossibleMoves(final MoveRequest moveRequest, final Point snakeHead) {
		final List<Move> occupiedDirections = deriveOccupiedDirections(moveRequest, snakeHead);
		// System.out.println("occupied directions: " + occupiedDirections);
		final List<Move> values = new ArrayList<>(Arrays.asList(Move.values()));
		values.removeAll(occupiedDirections);
		// System.out.println("possible directions: " + values);
		return values;
	}

	/**
	 *
	 * @param moveRequest
	 * @param snakeHead
	 * @return
	 */
	public static List<Move> deriveOccupiedDirections(final MoveRequest moveRequest, final Point snakeHead) {
		// System.out.println("snake's head: " + snakeHead);
		List<Point> allSnakePoints = moveRequest.getSnakes().stream().map(snake -> snake.getBody())
				.flatMap(List::stream).collect(Collectors.toList());
		// TODO: double check this logic...is it where they are that matters? or
		// where they end up? (not sure order of moves applied).
		List<Point> smallerSnakeHeads = moveRequest.getSnakes().stream()
				.filter(x -> x.getLength() < moveRequest.getYou().getLength()).map(Snake::getBody).map(x -> x.get(0))
				.collect(Collectors.toList());
		List<Point> allBorderPoints = BoardDerivations.generateBorderPoints(moveRequest);

		// System.out.println("snakepoints: " + allSnakePoints);
		// System.out.println("smallersnakeheadpoints: " + smallerSnakeHeads);
		// System.out.println("borderpoints: " + allBorderPoints);

		Set<Point> allOffLimitsPoints = new HashSet<>();
		allOffLimitsPoints.addAll(allSnakePoints);
		allOffLimitsPoints.addAll(allBorderPoints);
		allOffLimitsPoints.removeAll(smallerSnakeHeads);

		// System.out.println("offlimits: " + allOffLimitsPoints);
		return allOffLimitsPoints.stream().filter(PathingDerivations.isAdjacent(snakeHead)).map(point -> {
			// System.out.println("adjacent offlimits: " + point);
			return PathingDerivations.directionTo(snakeHead, point);
		}).collect(Collectors.toList());
	}

}
