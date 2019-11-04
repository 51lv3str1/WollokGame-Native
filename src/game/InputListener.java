package game;

public interface InputListener {

	public void listenKeyInput(KeyInput key);

	public void stoplisteningKeyInput(KeyInput key);

	public void listenMouseInput();

	public void stopListeningMouseInput();

}
