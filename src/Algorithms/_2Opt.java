package Algorithms;


import Warehouse.Location;
import Warehouse.Product;

import java.util.ArrayList;
import java.util.List;

public class _2Opt extends Algorithm{

    public double berekenTotaleAfstand(ArrayList<Location> order){
        //bewaarde lengtes van afstanden
        List<Double> lengtes = new ArrayList<Double>();

        //krijg lengtes van afstanden tussen Locations
        for (int x = 1; x < order.size(); x++) {
            lengtes.add((order.get(x).getLengte(order.get(x - 1))));
        }

        double totaleAfstand = 0;

        //voeg afstanden aan totaleAfstand toe
        for(double x : lengtes){
            totaleAfstand += x;
        }

        return totaleAfstand;
    }

    public ArrayList<Product> BerekenRoute(ArrayList<Product> order) {
        //bron: http://www.technical-recipes.com/2012/applying-c-implementations-of-2-opt-to-travelling-salesman-problems/
        ArrayList<Product> goedevolgorde = new ArrayList<Product>();

        ArrayList<Location> nieuwe_route = new ArrayList<Location>();

        ArrayList<Location> route = new ArrayList<Location>();
        for(Product p : order){
            Location Location = p.getLocation();
            route.add(Location);
        }
        System.out.println("simulatorTSP.graphic.Location arraylist gemaakt");
        System.out.println(route.toString());
        ArrayList<Location> beste_route = new ArrayList<Location>();

        // Get tour size
        double size = order.size();

        // repeat until no improvement is made
        int improve = 0;

        while (improve < 5) {
            double besteAfstand = berekenTotaleAfstand(route);

            for ( int i = 0; i < size - 1; i++ )
            {
                for ( int k = i + 1; k < size; k++)
                {
                    //ArrayList<simulatorTSP.graphic.Location> nieuwe_route = new ArrayList<simulatorTSP.graphic.Location>();

                    nieuwe_route = verwissel2Opt(route, i, k );


                    ArrayList<Location> new_distance_route = nieuwe_route;
                    double new_distance = berekenTotaleAfstand(new_distance_route);

                    if ( new_distance < besteAfstand )
                    {
                        // Improvement found so reset
                        improve = 0;
                        route = nieuwe_route;
                        besteAfstand = new_distance;
                        System.out.println("\nVERBETERING\n");
                        System.out.println(berekenTotaleAfstand(nieuwe_route));
                        System.out.println(nieuwe_route.toString());
                    } else {
                        System.out.println("Geen verbetering!\n");
                    }
                }
            }
            improve ++;
        }
        for(Product p: order){
            for(Location l: nieuwe_route){
                if(l == p.getLocation()){
                    goedevolgorde.add(p);
                }
            }
        }
        return goedevolgorde;
    }

    private ArrayList<Location> verwissel2Opt(ArrayList<Location> route, final int i, final int k){
        int size = route.size();
        ArrayList<Location> nieuwe_route = new ArrayList<Location>();

        for (int c = 0; c <= i - 1; c++) {
            Location Location = route.get(c);
            nieuwe_route.add(Location);
        }
        for (int c = k; c >= i; c--) {
            Location Location = route.get(c);
            nieuwe_route.add(Location);
        }
        for (int c = k + 1; c < size; c++) {
            Location Location = route.get(c);
            nieuwe_route.add(Location);
        }
        System.out.println("2opt uitgevoerd");
        return nieuwe_route;
    }

}
