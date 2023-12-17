package dev.tehsteel.minigameapi.arena;

/**
 * The ArenaException is an exception specific to Arena operations.
 */
public class ArenaException extends Exception {

	/**
	 * Constructs a new ArenaException with the specified detail message.
	 *
	 * @param message the detail message (which is saved for later retrieval by the getMessage() method)
	 */
	public ArenaException(final String message) {
		super(message);
	}
}
