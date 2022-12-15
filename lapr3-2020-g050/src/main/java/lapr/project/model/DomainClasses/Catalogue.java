package lapr.project.model.DomainClasses;

import lapr.project.model.BinaryTrees.AVL;

public class Catalogue extends AVL<Item> {

    public Item searchItemByName(String name){
        for(Item item : this.inOrder()){
            if(item.getName().equalsIgnoreCase(name))
                return item;
        }
        return null;
    }

}
