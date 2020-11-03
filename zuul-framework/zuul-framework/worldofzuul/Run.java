package worldofzuul;

public class Run {
    Player p1 = new Player();
    Child c = new Child(p1);
    Adult a = new Adult(p1);
    Old o = new Old(p1);

    Run(){
        new InitGame(p1);

        while (p1.getAlive()){
            switch (p1.getStage()){
                case "child" -> c.play();
                case "adult" -> a.play();
                case "old" -> o.play();
            }
        }
        System.out.println("Thank you for playing.  Good bye.");
    }
}