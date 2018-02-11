package ca.casualt.snakes.basicbattlesnake.utilities.movers;

import java.util.List;
import java.util.stream.Collectors;

import ca.casualt.snakes.basicbattlesnake.types.Move;
import ca.casualt.snakes.basicbattlesnake.types.MoveRequest;
import ca.casualt.snakes.basicbattlesnake.types.Point;
import ca.casualt.snakes.basicbattlesnake.utilities.PathingDerivations;

/**
 * Concerned with getting to closest open-path food.
 *
 * @author Tony
 *
 */
public class HealthySmartMover implements Mover {

	/**
	 * Default Constructor.
	 */
	public HealthySmartMover() {
	}

	@Override
	public Move getMove(final MoveRequest moveRequest) {
		final Point myHead = moveRequest.getYou().getHead();
		final List<List<Point>> pointsWithDistance = moveRequest.getFood().stream().map(point -> {
			final List<Point> result = PathingDerivations.moveToViaShortestOpenPath(moveRequest, point);
			return result;
		}).collect(Collectors.toList());
		pointsWithDistance.sort((p1, p2) -> {
			return p1.size() - p2.size();
		});
		// should be adjacent
		final Point firstStep = pointsWithDistance.get(0).get(0);
		return PathingDerivations.directionTo(myHead, firstStep);
	}
}
