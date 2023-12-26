package dev.tehsteel.minigameapi.typeadapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import dev.tehsteel.minigameapi.util.CustomLocation;

import java.io.IOException;

public final class CustomLocationAdapter extends TypeAdapter<CustomLocation> {
	@Override
	public void write(final JsonWriter out, final CustomLocation customLocation) throws IOException {
		out.beginObject();
		out.name("world").value(customLocation.world());
		out.name("x").value(customLocation.x());
		out.name("y").value(customLocation.y());
		out.name("z").value(customLocation.z());
		out.name("yaw").value(customLocation.yaw());
		out.name("pitch").value(customLocation.pitch());
		out.endObject();
	}

	@Override
	public CustomLocation read(final JsonReader in) throws IOException {
		String world = "world";
		double x = 0.0, y = 0.0, z = 0.0;
		float yaw = 0, pitch = 0;

		in.beginObject();
		while (in.hasNext()) {
			switch (in.nextName()) {
				case "world":
					world = in.nextString();
					break;
				case "x":
					x = in.nextDouble();
					break;
				case "y":
					y = in.nextDouble();
					break;
				case "z":
					z = in.nextDouble();
					break;
				case "yaw":
					yaw = (float) in.nextDouble();
					break;
				case "pitch":
					pitch = (float) in.nextDouble();
					break;
				default:
					in.skipValue();
					break;
			}
		}

		in.endObject();
		return new CustomLocation(world, x, y, z, yaw, pitch);
	}
}
