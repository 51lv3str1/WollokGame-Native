package game;

import java.util.HashMap;
import java.util.Map;

import org.uqbar.project.wollok.interpreter.core.WollokObject;

public class Scheduler {

	private static Scheduler instance;
	private final Map<String, SchedulerTask> tasks;

	private Scheduler() {
		this.tasks = new HashMap<String, SchedulerTask>();
	}

	public static Scheduler getInstance() {
		if (instance == null) {
			instance = new Scheduler();
		}

		return instance;
	}

	public void onTick(Integer milliseconds, String name, WollokObject closure) {
		final SchedulerTask task = new SchedulerTask(milliseconds, true, closure);
		this.tasks.put(name, task);
		task.start();
	}

	public void onTick(WollokObject milliseconds, WollokObject name, WollokObject closure) {
		this.onTick(Integer.valueOf(milliseconds.toString()), name.toString(), closure);
	}

	public void schedule(Integer milliseconds, WollokObject closure) {
		final SchedulerTask task = new SchedulerTask(milliseconds, false, closure);
		task.start();
	}

	public void schedule(WollokObject milliseconds, WollokObject closure) {
		this.schedule(Integer.valueOf(milliseconds.toString()), closure);
	}

	public void removeTickEvent(String name) {
		final SchedulerTask task = tasks.get(name);
		task.stop();
		tasks.remove(name);
	}

	public void removeTickEvent(WollokObject name) {
		this.removeTickEvent(name.toString());
	}
	
	public void clear() {
		this.tasks.keySet().forEach(task -> this.tasks.get(task).stop());
		this.tasks.clear();
	}

}