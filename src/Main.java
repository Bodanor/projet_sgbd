public class Main {
    public static void main(String[] args) {
       GUI test = new GUI();
       ControlleurGUI controleur = new ControlleurGUI(test);
       test.setControleur(controleur);
    }
}
