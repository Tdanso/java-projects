package View;

public interface IView {
    
    public void openStartPanel();

    public void closeStartPanel();

    public void openNewGamePanel();

    public void closeNewGamePanel();
    
    public void openEndGamePanel(boolean deadPlayer);

    public void openMainGUI(String shipType);
    
    public void start();
    
}
