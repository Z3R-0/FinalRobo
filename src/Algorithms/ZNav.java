package Algorithms;

import RobotClasses.Box;
import Warehouse.Product;

import java.util.ArrayList;

public class ZNav extends Algorithm{
    private double BerekenLengte(ArrayList<Product> route){
        double lengte = 0;
        for(int i = 1;i<route.size();i++){
            lengte += route.get(i-1).getLocation().getLengte(route.get(i).getLocation());
        }
        lengte +=route.get(route.size()-1).getLocation().getLengte(route.get(0).getLocation());
        return lengte;
    }

    public ArrayList<Product> BerekenRoute(ArrayList<Product> order) {
        Algorithm ZAlgorithm = new ZAlgorithm();
        Algorithm Greedy = new Greedy();
        ArrayList<Product> routeZAlgoritme = ZAlgorithm.BerekenStart(order);
        ArrayList<Product> routeGreedy = Greedy.BerekenStart(order);

        if(BerekenLengte(routeZAlgoritme)>=BerekenLengte(routeGreedy)){
            return routeGreedy;
        }else{
            _2Opt optSwap = new _2Opt();
            ArrayList<Product> routeZNav = new ArrayList<Product>();
            routeZNav = optSwap.BerekenStart(routeZAlgoritme);
            return routeZNav;
        }
    }

    @Override
    protected ArrayList<Box> Run(ArrayList<Product> p) {
        return null;
    }
}
