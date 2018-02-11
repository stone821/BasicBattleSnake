package ca.casualt.snakes.basicbattlesnake.types;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.google.gson.annotations.SerializedName;

/**
 * A move request.
 *
 * @author Tony
 *
 */
public final class MoveRequest implements BoardSpec {

	@SerializedName("game_id")
	private String gameId;
	private int width;
	private int height;

	private String you;
	private int turn;

	private List<Snake> snakes;
	private List<List<Integer>> food;
	// TODO: left out board for now.

	/**
	 * Default constructor.
	 */
	public MoveRequest() {
	}

	/**
	 * Copy constructor. <br>
	 * (deep copy).
	 *
	 * @param toCopy
	 */
	public MoveRequest(final MoveRequest toCopy) {
		this.gameId = toCopy.gameId;
		this.width = toCopy.width;
		this.height = toCopy.height;

		this.you = toCopy.you;
		this.turn = toCopy.turn;

		this.snakes = toCopy.snakes.stream().map(snake -> {
			return new Snake(snake);
		}).collect(Collectors.toList());
		this.food = toCopy.food.stream().map(aFood -> {
			return new ArrayList<>(aFood);
		}).collect(Collectors.toList());
	}

	/**
	 *
	 * @return me snake.
	 */
	public Snake getMe() {
		Optional<Snake> me = this.getSnakes().stream().filter(snake -> snake.getId().equals(this.getYou())).findFirst();
		return me.get();
	}

	/**
	 * @return the gameId
	 */
	@Override
	public final String getGameId() {
		return gameId;
	}

	/**
	 * @param gameId
	 *            the gameId to set
	 */
	public final void setGameId(final String gameId) {
		this.gameId = gameId;
	}

	/**
	 * @return the width
	 */
	@Override
	public final int getWidth() {
		return width;
	}

	/**
	 * @param width
	 *            the width to set
	 */
	public final void setWidth(final int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	@Override
	public final int getHeight() {
		return height;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public final void setHeight(final int height) {
		this.height = height;
	}

	/**
	 * @return your snake's id.
	 */
	public final String getYou() {
		return you;
	}

	/**
	 * @param you
	 *            the you to set
	 */
	public final void setYou(final String you) {
		this.you = you;
	}

	/**
	 * @return the turn
	 */
	public final int getTurn() {
		return turn;
	}

	/**
	 * @param turn
	 *            the turn to set
	 */
	public final void setTurn(final int turn) {
		this.turn = turn;
	}

	/**
	 * @return the snakes
	 */
	public final List<Snake> getSnakes() {
		return snakes;
	}

	/**
	 * @param snakes
	 *            the snakes to set
	 */
	public final void setSnakes(final List<Snake> snakes) {
		this.snakes = snakes;
	}

	/**
	 * Convenience getter as list of points.
	 *
	 * @return
	 */
	public final List<Point> getFoodAsPoints() {
		return food.stream().map(Point::new).collect(Collectors.toList());
	}

	/**
	 * @return the food
	 */
	public final List<List<Integer>> getFood() {
		return food;
	}

	/**
	 * @param food
	 *            the food to set
	 */
	public final void setFood(final List<List<Integer>> food) {
		this.food = food;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MoveRequest [gameId=" + gameId + ", width=" + width + ", height=" + height + ", you=" + you + ", turn="
				+ turn + ", snakes=" + snakes + ", food=" + food + "]";
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((food == null) ? 0 : food.hashCode());
		result = prime * result + ((gameId == null) ? 0 : gameId.hashCode());
		result = prime * result + height;
		result = prime * result + ((snakes == null) ? 0 : snakes.hashCode());
		result = prime * result + turn;
		result = prime * result + width;
		result = prime * result + ((you == null) ? 0 : you.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MoveRequest other = (MoveRequest) obj;
		if (food == null) {
			if (other.food != null)
				return false;
		} else if (!food.equals(other.food))
			return false;
		if (gameId == null) {
			if (other.gameId != null)
				return false;
		} else if (!gameId.equals(other.gameId))
			return false;
		if (height != other.height)
			return false;
		if (snakes == null) {
			if (other.snakes != null)
				return false;
		} else if (!snakes.equals(other.snakes))
			return false;
		if (turn != other.turn)
			return false;
		if (width != other.width)
			return false;
		if (you == null) {
			if (other.you != null)
				return false;
		} else if (!you.equals(other.you))
			return false;
		return true;
	}

}
