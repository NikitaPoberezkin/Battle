/**
 * Created by pober on 20.11.2016.
 */
public class Archer implements  Warrior{
    private String name;
    private int health;
    private int damage;
    private Squad squad;

    public Archer(String name, Squad squad){
        this.name = name;
        this.health = 130;
        this.damage = 120;
        this.squad = squad;
    }

    @Override
    public String toString () //форматирование должно быть единообразным во всем проекте. либо скобки везде на новой строке, либо везде на строке с сигнатурой метода
    {
        return "Лучник " + this.name + " из отряда " + this.squad.getName();
    }
    //дальше все аннотации @Override не нужны что ли?
    public int attack(){
        return this.damage;
    }

    public void takeDamage(int damage){
        this.health-=damage;
        if (this.health<0) this.health =0; //форматирование выглядит непрезентабельно. нужно привыкать сразу писать нормально. к тому же в этом месте излишне везде писать this
//        if (health < 0) {
//            health = 0;
//        }
    }

    public boolean isAlive(){
        if (this.health>0) return true; //во всех местах, где проверяется условие, либо возвращается результат проверки условия, можно сразу проверять/возвращать.  данном случае можно заменить на return health > 0;
        return false;
    }

    public void setSquadName(String name){
        squad.setName(name);
    }

    public Object clone(){
        try {
            Archer archer = (Archer) super.clone();
            archer.name = new String(this.name); //нет смысла так писать, так как строки в java неизменяемы. archer.name = this.name - безопасно
            return archer;
        }
        catch (CloneNotSupportedException e){
            throw new UnsupportedOperationException(e);
        }
    }

    public int getHealth(){
        return this.health;
    }
}
