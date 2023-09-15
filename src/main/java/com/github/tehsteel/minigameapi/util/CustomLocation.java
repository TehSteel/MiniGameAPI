package com.github.tehsteel.minigameapi.util;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.bukkit.Bukkit;
import org.bukkit.Location;

@AllArgsConstructor
public final class CustomLocation {

	private final String world;

	private final double x;
	private final double y;
	private final double z;

	private final float yaw;
	private final float pitch;

	/**
	 * Creates a CustomLocation object from a Bukkit Location.
	 *
	 * @param location The Bukkit Location to be converted.
	 * @return A CustomLocation object equivalent to the given Bukkit Location.
	 */
	public static CustomLocation fromBukkitLocation(final Location location) {
		return new CustomLocation(location.getWorld().getName(),
				location.getX(),
				location.getY(),
				location.getZ(),
				location.getYaw(),
				location.getPitch()
		);
	}

	/**
	 * Deserializes a string into a CustomLocation object.
	 *
	 * @param location A string representation of the CustomLocation.
	 * @return The deserialized CustomLocation object.
	 * @throws NullPointerException  if the input string is null.
	 * @throws NumberFormatException if the input string is not in the expected format.
	 */
	public static CustomLocation deserialize(@NonNull final String location) {
		final String[] strings = location.split(" ");

		final String worldName = strings[0];

		final double x = Double.parseDouble(strings[1]);
		final double y = Double.parseDouble(strings[2]);
		final double z = Double.parseDouble(strings[3]);

		final float yaw = Float.parseFloat(strings[4]);
		final float pitch = Float.parseFloat(strings[5]);

		return new CustomLocation(worldName, x, y, z, yaw, pitch);
	}

	/**
	 * Converts a CustomLocation object to a Bukkit Location.
	 *
	 * @return The Bukkit Location.
	 */
	public Location toBukkitLocation() {
		return new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
	}

	/**
	 * Serializes a CustomLocation object into a string format.
	 *
	 * @return The serialized string.
	 */
	public String serialize() {
		return world + " " + x + " " + y + " " + z + " " + yaw + " " + pitch;
	}

}