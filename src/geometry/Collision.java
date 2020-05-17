package geometry;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.uqbar.project.wollok.interpreter.core.WollokObject;

import game.GameComponent;

public class Collision {

	private final Map<WollokObject, WollokObject> collisions;

	public Collision() {
		this.collisions = new HashMap<WollokObject, WollokObject>();
	}

	public void listenCollision(WollokObject component, WollokObject closure) {
		this.collisions.put(component, closure);
	}

	public void stopListeningCollision(WollokObject component) {
		this.collisions.remove(component);
	}

	private Boolean existCollisionBetween(GameComponent collided, GameComponent collider) {
		return this.collisions.containsKey(collided.asWollokObject()) && !collided.equals(collider)
				&& collided.position().equals(collider.position());
	}

	private void collides(GameComponent collided, GameComponent collider) {
		if (this.existCollisionBetween(collided, collider)) {
			this.collisions.get(collided.asWollokObject()).call("apply", collider.asWollokObject());
		}
	}

	private void collides(GameComponent collided, Collection<GameComponent> colliders) {
		colliders.stream().forEach(collider -> this.collides(collided, collider));
	}

	public void collides(Collection<GameComponent> colliders) {
		colliders.stream().forEach(collider -> this.collides(collider, colliders));
	}

	public void clear() {
		this.collisions.clear();
	}

}