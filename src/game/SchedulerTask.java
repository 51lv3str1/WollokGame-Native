package game;

import org.uqbar.project.wollok.interpreter.core.WollokObject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class SchedulerTask {

	private Timer timer;
	final private Integer milliseconds;
	final private Boolean repeatable;
	final private WollokObject closure;

	public SchedulerTask(Integer milliseconds, Boolean repeatable, WollokObject closure) {
		this.milliseconds = milliseconds;
		this.repeatable = repeatable;
		this.closure = closure;
	}

	public void start() {
		this.timer = new Timer(milliseconds, new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				closure.call("apply");
			}

		});

		this.timer.setRepeats(repeatable);
		this.timer.setCoalesce(true);
		this.timer.start();
	}

	public void stop() {
		this.timer.stop();
	}

}