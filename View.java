 package View;

public class View implements IView{
    
    private static View instance = null;
    public GUI mainGUI = null;

    public View() {
    }
    
    @Override
    public void openStartPanel() {
        this.mainGUI.openStartPanel();
    }

    @Override
    public void closeStartPanel() {
        mainGUI.closeStartPanel();
    }

    @Override
    public void openNewGamePanel() {
        this.mainGUI.openNewGamePanel();
    }

    @Override
    public void closeNewGamePanel() {
        this.mainGUI.closeNewGamePanel();
    }
    
    @Override
    public void openEndGamePanel(boolean deadPlayer){
        this.mainGUI.openEndGamePanel(deadPlayer);
    }

    @Override
    public void openMainGUI(String shipType) {
        this.mainGUI.createGUI(shipType);
    }
    
    
    @Override
    public void start(){
        if(this.mainGUI== null){
            this.mainGUI = new GUI();
            this.mainGUI.openStartPanel();
        }else
            this.mainGUI.openStartPanel();
    }
    
    
    //---------------------------------------------------------------
    // STATIC METHODS
    //---------------------------------------------------------------
    public static IView getInstance() {
        if (instance == null)
                instance = new View();
        return instance;
    }
    


    

}
