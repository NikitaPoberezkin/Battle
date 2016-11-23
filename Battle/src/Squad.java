import java.util.Random;

/**
 * Created by pober on 20.11.2016.
 */
public class Squad implements Cloneable {
    private Warrior[] warriors;
    private String name;

    public void AddWarrior(Warrior warrior, int i){ //нигде не используется и с прописной буквы...
        warriors[i]=warrior;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Squad(String name){
        Random rand = new Random();
        this.name = name;
        int num_of_warriors = rand.nextInt(2)+3;
        Warrior[] warriors = new Warrior[num_of_warriors];
        this.warriors = warriors;
    }

    public Warrior getRandomWarrior(){
        Random rand = new Random();
        int random_warrior;
        while (true) {
            random_warrior = rand.nextInt(this.warriors.length);
            if (this.warriors[random_warrior].isAlive()) return this.warriors[random_warrior];
        }
    }

    public boolean hasAliveWarriors(){
        for (int i=0; i<warriors.length; i++){
            if (warriors[i].isAlive()) return true;
        }
        return false;
    }

    public String toString(){
        //return "Отряд " + this.name; реализация, требуемая в задании(немного дополнил ее для удобства)
        String string = "";
        for (int i=0; i<warriors.length; i++){
            string+=warriors[i].toString() + "( " + warriors[i].attack() + " dmg, " + warriors[i].getHealth()+ " hp)" +" \n";
        }
        return "Отряд " + this.name +"\n" + string;
    }
//  нужна аннотация, +можно вернуть сразу Squad, а не  Object
    public Object clone(){
        try{
            Squad squad = (Squad) super.clone();
            squad.name = new String(this.name);
            squad.warriors = new Warrior[this.warriors.length];
            for (int i=0; i<warriors.length; i++){
                squad.warriors[i] = (Warrior) this.warriors[i].clone();
            }
            return squad;
        }
        catch (CloneNotSupportedException e){
            throw new UnsupportedOperationException(e);
        }
    }

    public void setWarriors(Warrior[] warriors){
        this.warriors = warriors;
    }

    public Warrior[] getWarriors(){
        return this.warriors;
    }
}
