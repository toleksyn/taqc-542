package achornytc.task_03;

public class NavigationMenu {
    private static EcoNewsMenuItem ecoNewsMenuItem = new EcoNewsMenuItem();
    private TipsTricksMenuItem tipsTricksMenuItem;
    private PlacesMenuItem placesMenuItem;
    private AboutUsMenuItem aboutUsMenuItem;

    public NavigationMenu() {
        this.ecoNewsMenuItem = new EcoNewsMenuItem();
        this.tipsTricksMenuItem = new TipsTricksMenuItem();
        this.placesMenuItem = new PlacesMenuItem();
        this.aboutUsMenuItem = new AboutUsMenuItem();
    }
}