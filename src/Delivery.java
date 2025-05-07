import java.util.LinkedList;

public class Delivery extends Person{
    private Double averageRating;
    private static LinkedList<Double> ratings;
    private static LinkedList<String> comments;

    public Delivery(){
        ratings = new LinkedList<Double>();
        comments = new LinkedList<String>();
    }
    public static String getAverageRating(){
        double total = 0;
        for(double r : ratings){
            total += r;
        }
        return ratingToStar(total/ratings.size());
    }

    private static String ratingToStar(double rating){
        if(rating - Math.floor(rating) >= 0.5 ) {
            return "★".repeat((int) rating) + "⯨";
        }
        return "★".repeat((int) rating);
    }

    public static String getPastRatings(){
        String ret = "";
        for(int i = 0; i < ratings.size(); i++){
            ret += ratingToStar(ratings.get(i)) + " " + comments.get(i) + "\n";
        }
        return ret;
    }

    public void addCustomerRating(double rating, String comment){
        if(ratings.size() > 10){
            System.out.println(ratings.pop());
            System.out.println(comments.pop());
        }
        ratings.add(rating);
        comments.add(super.getName() + " " + comment);
    }

    public static void main(String[] args){

       // System.out.println(getAverageRating());
        //System.out.println(getPastRatings());


        Delivery d = new Delivery();
        d.setName("tom");
        for(int i = 0; i < 20; i++) {
            d.addCustomerRating(2.3 + i, "good");
            System.out.println(getAverageRating());
            System.out.println(getPastRatings());
        }
    }


}
