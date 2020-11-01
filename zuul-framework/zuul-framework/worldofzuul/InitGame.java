package worldofzuul;

public class InitGame {
    private Parser parser;
    InitGame(Player p1){
        createRooms(p1);
        parser = new Parser();
        printWelcome(p1);
    }

    private void printWelcome(Player p1){
        System.out.println();
        System.out.println("welcome to real life bitch");
        System.out.println("real life sucks");
        setCountry(p1);
        setGender(p1);
        setEcon(p1);
        System.out.println("You have been born as " + p1.getFamilyEconomy().toString() + " " + p1.getGender().toString() + " living in " + p1.getCountry().toString());
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(p1.getCurrentRoom().getLongDescription());
    }

    private void createRooms(Player p1)
    {
        Room home, work, shop, school, hospital, outside;

        outside = new Room("outside the main entrance of the university");
        home = new Room("in a lecture theatre");
        work = new Room("in the campus pub");
        shop = new Room("in a computing lab");
        school = new Room("in the computing admin office");
        hospital = new Room("in the computing admin office");

        outside.setExit("home", home);
        outside.setExit("work", work);
        outside.setExit("shop", school);
        outside.setExit("hospital", hospital);
        outside.setExit("school", school);

        home.setExit("outside", outside);
        work.setExit("outside", outside);
        shop.setExit("outside", outside);
        hospital.setExit("outside", outside);
        school.setExit("outside", outside);


        p1.setCurrentRoom(home);
    }
    public void setCountry(Player p1){
        System.out.println("Please select a country \n" +
                "Vakannda | McD | Venesuela" );
        //todo unspagetti
        String s = parser.getWord();
        if(s.equals("McD") || s.equals("Vakannda") || s.equals("Venesuela"))
            p1.setCountry(Country.valueOf(s.toUpperCase()));
        else{
            System.out.println("Input invalid\n");
            setCountry(p1);
        }
    }
    public void setGender(Player p1){
        //todo add random
        p1.setGender(Gender.FEMALE);
    }
    public void setEcon(Player p1){
        p1.setFamilyEconomy(FamilyEconomy.MIDDLECLASS);
        //todo add random
    }
    public void setMoney(Player p1){
        p1.incMoney(p1.getCountry().getMoney()*p1.getGender().getMoneyMulti()*p1.getFamilyEconomy().getMoneyMulti());
    }


}
