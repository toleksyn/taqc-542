package achornytc.task_03;

import java.util.Map;
import java.util.Set;

public class NavigationMenu {
    private EcoNewsMenuItem ecoNewsMenuItem ;
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
