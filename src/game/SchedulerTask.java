package game;

import org.uqbar.project.wollok.interpreter.core.WollokObject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class SchedulerTask {

	private Timer timer;
	final private Scheduler scheduler;
	final private Integer milliseconds;
	final private Boolean loops;
	final private WollokObject closure;

	public SchedulerTask(Scheduler scheduler, Integer milliseconds, Boolean loops, WollokObject closure) {
		this.scheduler = scheduler;
		this.milliseconds = milliseconds;
		this.loops = loops;
		this.closure = closure;
	}

	public void start() {
		this.timer = new Timer(milliseconds, new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// implementar control de clear aqui.
				closure.call("apply");
			}

		});

		this.timer.setRepeats(loops);
		this.timer.setCoalesce(true);
		this.timer.start();
	}

	public void stop() {
		this.timer.stop();
	}

}