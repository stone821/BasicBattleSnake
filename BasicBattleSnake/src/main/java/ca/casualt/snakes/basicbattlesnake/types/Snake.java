package ca.casualt.snakes.basicbattlesnake.types;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.annotations.SerializedName;

/**
 * A snake on the board.
 *
 * @author Tony
 *
 */
public class Snake {

	private String id;
	private String name;
	private String taunt;
	@SerializedName("health_points")
	private int healthPoints;
	private List<List<Integer>> coords;

	/**
	 * Default constructor.
	 */
	public Snake() {
	}

	/**
	 * Copy Constructor.<br>
	 * (deep copy).
	 *
	 * @param toCopy
	 */
	public Snake(final Snake toCopy) {
		this.id = toCopy.id;
		this.name = toCopy.name;
		this.healthPoints = toCopy.healthPoints;
		this.coords = toCopy.coords.stream().map(pair -> {
			return new ArrayList<>(pair);
		}).collect(Collectors.toList());
	}

	/**
	 * @return the id
	 */
	public final String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public final void setId(final String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public final void setName(final String name) {
		this.name = name;
	}

	/**
	 * @return the taunt
	 */
	public final String getTaunt() {
		return taunt;
	}

	/**
	 * @param taunt
	 *            the taunt to set
	 */
	public final void setTaunt(final String taunt) {
		this.taunt = taunt;
	}

	/**
	 * @return the healthPoints
	 */
	public final int getHealthPoints() {
		return healthPoints;
	}

	/**
	 * @param healthPoints
	 *            the healthPoints to set
	 */
	public final void setHealthPoints(final int healthPoints) {
		this.healthPoints = healthPoints;
	}

	/**
	 * Convenience getter as list of points.
	 *
	 * @return
	 */
	public final List<Point> getCoordsAsPoints() {
		return coords.stream().map(Point::new).collect(Collectors.toList());
	}

	/**
	 *
	 * @return the head is the first point in the list.
	 */
	public final Point getHead() {
		return getCoordsAsPoints().get(0);
	}

	/**
	 * @return the coords
	 */
	public final List<List<Integer>> getCoords() {
		return coords;
	}

	/**
	 * @param coords
	 *            the coords to set
	 */
	public final void setCoords(final List<List<Integer>> points) {
		this.coords = points;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Snake [id=" + id + ", name=" + name + ", taunt=" + taunt + ", healthPoints=" + healthPoints
				+ ", coords=" + coords + "]";
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
		result = prime * result + healthPoints;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((coords == null) ? 0 : coords.hashCode());
		result = prime * result + ((taunt == null) ? 0 : taunt.hashCode());
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
		Snake other = (Snake) obj;
		if (healthPoints != other.healthPoints)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (coords == null) {
			if (other.coords != null)
				return false;
		} else if (!coords.equals(other.coords))
			return false;
		if (taunt == null) {
			if (other.taunt != null)
				return false;
		} else if (!taunt.equals(other.taunt))
			return false;
		return true;
	}

}
