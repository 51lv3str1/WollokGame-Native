object scheduler {

	const property tasks = new Dictionary()

	method clearEvents() {
		tasks.clear()
	}

	method onTick(milliseconds, name, closure) {
		const task = new SchedulerTask(milliseconds = milliseconds, repeatable = true, task = closure)
		tasks.put(name, task)
		task.start()
	}

	method schedule(milliseconds, closure) {
		const task = new SchedulerTask(milliseconds = milliseconds, repeatable = false, task = closure)
		task.start()
	}

	method removeTickEvent(name) {
		const task = tasks.get(name)
		task.stop()
		task.remove(name)
	}

}

class SchedulerTask {

	const property milliseconds
	const property repeatable
	const property task

	method start() native

	method stop() native

}

