package worldofzuul;

public class Key extends Item{

    public int price;
    //private Room keyType;

    //Key(int price, Room keyType)
    Key(int price)
    {
        super();
        this.price = price;
        //this.keyType = keyType;
    }

    /*

    If the key is not just a generic key that can open a given door,
    it should have a type so that it knows which door it can unlock

    public boolean canUnlock(Room room)
    {
        if (this.keyType == room)
        {
            return true;
        }
        else {
            return false;
        }
    }

     */
}