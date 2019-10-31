package scheduler;

import org.uqbar.project.wollok.interpreter.core.WollokObject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class SchedulerTask {

	/** Wollok instance for this game. */
	private final WollokObject wrapped;

	private Timer timer;

	public SchedulerTask(WollokObject wrapped) {
		this.wrapped = wrapped;
	}

	public void start() {
		final Integer milliseconds = Integer.valueOf(this.wrapped.call("milliseconds").toString());
		final Boolean repeatable = Boolean.valueOf(this.wrapped.call("repeatable").toString());
		final WollokObject closure = this.wrapped.call("task");

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