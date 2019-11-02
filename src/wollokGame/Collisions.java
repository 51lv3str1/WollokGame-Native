package wollokGame;

import java.util.HashMap;
import java.util.Map;

import org.uqbar.project.wollok.interpreter.WollokInterpreter;
import org.uqbar.project.wollok.interpreter.WollokInterpreterEvaluator;
import org.uqbar.project.wollok.interpreter.core.WollokObject;
import org.uqbar.project.wollok.interpreter.nativeobj.WollokJavaConversions;

import component.actor.Actor;

@SuppressWarnings({ "unused" })
public class Collisions {

	private static Collisions instance;

	private final Map<WollokObject, WollokObject> collisions;

	private Collisions() {
		this.collisions = new HashMap<WollokObject, WollokObject>();
	}

	public static Collisions getInstance() {
		if (instance == null) {
			instance = new Collisions();
		}

		return instance;
	}

	public void listenCollisionWithAny(WollokObject body, WollokObject clousure) {
		this.collisions.put(body, clousure);
	}

	public void stopListeningCollisionWithAny(WollokObject body) {
		this.collisions.remove(body);
	}

	private Boolean listenCollisions(WollokObject component) {
		return this.collisions.containsKey(component);
	}

	private Boolean samePosition(Actor collided, Actor collider) {
		return collided.getBoardPosition().equals(collider.getBoardPosition());
	}

	private void collides(Actor collided, Actor collider) {
		if (!collided.equals(collider) && collider.collisionable() && this.samePosition(collided, collider)) {
			this.collisions.get(collided.wrapper()).call("apply", collider.wrapper());
		}
	}

	public void collides(Actor collided, Actor[] colliders) {
		if (this.listenCollisions(collided.wrapper())) {
			for (int index = 0; index < colliders.length; index++) {
				this.collides(collided, colliders[index]);
			}
		}
	}

}