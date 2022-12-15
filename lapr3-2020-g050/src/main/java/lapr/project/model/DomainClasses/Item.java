package lapr.project.model.DomainClasses;

public class Item implements Comparable<Item> {

    private final int id;
    private final String name;
    private final Double price;
    private final Double  weight;


    public Item(int id, String name, Double price, Double weight) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Double getWeight() {
        return weight;
    }


    @Override
    public String toString() {
        return String.format("Item->id:%s,name:%s,price:%s,Weight:%s %n", id, name, price,weight);

    }

    @Override
    public int compareTo(Item o) {
        return this.id - o.getId();
    }
}
