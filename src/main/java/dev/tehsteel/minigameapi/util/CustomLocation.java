package dev.tehsteel.minigameapi.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public record CustomLocation(String world, double x, double y, double z, float yaw, float pitch) {
	public Location toBukkitLocation() {
		final World bukkitWorld = Bukkit.getWorld(world);

		if (bukkitWorld == null) {
			throw new RuntimeException("");
		}

		return new Location(bukkitWorld, x, y, z, yaw, pitch);
	}

	public static CustomLocation fromBukkitLocation(final Location location) {
		if (location == null) {
			throw new RuntimeException("Location hasn't been initialized.");
		}
		return new CustomLocation(location.getWorld().getName(), location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
	}
}
