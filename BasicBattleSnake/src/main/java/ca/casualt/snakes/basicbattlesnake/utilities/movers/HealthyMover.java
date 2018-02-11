package ca.casualt.snakes.basicbattlesnake.utilities.movers;

import java.util.List;
import java.util.stream.Collectors;

import ca.casualt.snakes.basicbattlesnake.types.Move;
import ca.casualt.snakes.basicbattlesnake.types.MoveRequest;
import ca.casualt.snakes.basicbattlesnake.types.Point;
import ca.casualt.snakes.basicbattlesnake.utilities.PathingDerivations;

/**
 * Only concerned with heading to the closest food.
 *
 * @author Tony
 *
 */
public class HealthyMover implements Mover {

	@Override
	public Move getMove(final MoveRequest moveRequest) {
		Point myHead = moveRequest.getMe().getHead();
		List<PointWithDistance> pointsWithDistance = moveRequest.getFoodAsPoints().stream().map(point -> {
			final int distance = PathingDerivations.distanceTo(myHead, point);
			return new PointWithDistance(point, distance);
		}).collect(Collectors.toList());
		// Shortest distance first in list.
		pointsWithDistance.sort((p1, p2) -> {
			return p1.getDistance() - p2.getDistance();
		});
		return PathingDerivations.directionTo(myHead, pointsWithDistance.get(0).getPoint());
	}

	public static class PointWithDistance {
		private final Point point;
		private final int distance;

		public PointWithDistance(final Point pointIn, final int distanceIn) {
			this.point = pointIn;
			this.distance = distanceIn;
		}

		public Point getPoint() {
			return point;
		}

		public int getDistance() {
			return distance;
		}
	}
}
