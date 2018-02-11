package ca.casualt.snakes.basicbattlesnake.utilities.movers;

import java.util.List;

import ca.casualt.snakes.basicbattlesnake.types.Move;
import ca.casualt.snakes.basicbattlesnake.types.MoveRequest;

/**
 * Will move randomly to avoid immediate obstacles, but always towards food if
 * possible.
 *
 * @author Tony
 *
 */
public class HealthyRandomMover implements Mover {

	private final RandomMover randomMover;
	private final HealthyMover healthMover;

	/**
	 * Default Constructor.
	 */
	public HealthyRandomMover() {
		this.randomMover = new RandomMover();
		this.healthMover = new HealthyMover();
	}

	@Override
	public Move getMove(final MoveRequest moveRequest) {
		final List<Move> possibleMoves = RandomMover.getPossibleMoves(moveRequest, moveRequest.getYou().getHead());
		final Move healthyMove = healthMover.getMove(moveRequest);
		if (possibleMoves.contains(healthyMove)) {
			return healthyMove;
		} else {
			return randomMover.getRandomMove(possibleMoves);
		}
	}

}
