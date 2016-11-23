import java.util.Random;

/**
 * Created by pober on 22.11.2016.
 */
public class Battle {

    public static void oneRound(Squad squad1, Squad squad2){
        Warrior warrior1 = squad1.getRandomWarrior();
        Warrior warrior2 = squad2.getRandomWarrior();
        warrior2.takeDamage(warrior1.attack());
        System.out.println(warrior1.toString() + " нанес " + warrior2.toString() + " " + warrior1.attack() + " урона");
    }

    public static void oneBattle(Squad squad1, Squad squad2){
        System.out.println(squad1.toString());
        System.out.println(squad2.toString());
        System.out.print("Битва началась ");
        DateHelper dateHelper = new DateHelper();
        dateHelper.getFormattedStartDate();
        while (true){
            Battle.oneRound(squad1,squad2);
            System.out.println("\n"+squad1.toString()+squad2.toString());
            dateHelper.skipTime();
            if (squad2.hasAliveWarriors()/* == false не нужно*/){
                System.out.println("\n"+squad1.getName() + " победили, время сражения: " + dateHelper.getFormattedDiff());
                break;
            }
            Battle.oneRound(squad2,squad1);
            System.out.println("\n"+squad1.toString()+squad2.toString());
            dateHelper.skipTime();
            if (squad1.hasAliveWarriors() == false){
                System.out.println("\n"+squad2.getName() + " победили, время сражения: " + dateHelper.getFormattedDiff());
                break;
            }
        }
    }

    public static Squad createSquad(){
        String[] warriors_names = {"Вавирон","Гелегил","Боневал","Турин","Беон","Киронтар","Барионбар","Беон","Эльронрн","Белекдил","Экрадор","Бателидил","Ваон","Дирионлор","Тарсил","Эаррибар","Меагтар"};
        String[] squads_names = {"СИНИЕ","ЗЕЛЕНЫЕ","КРАСНЫЕ","ЖЕЛТЫЕ","БЕЛЫЕ","ЧЕРНЫЕ"};
        Random rand = new Random();
        Squad squad = new Squad(squads_names[rand.nextInt(squads_names.length)]); // Создание 1 отряда со случайным именем из массива squad_names
        Warrior[] warriors_squad1 = new Warrior[squad.getWarriors().length];
        for (int i=0; i<warriors_squad1.length; i++){
            switch (rand.nextInt(3)+1 /*магические числа сразу бросаются в глаза. почему именно 3+1?*/){
                case 1: warriors_squad1[i] = new Warlock(warriors_names[rand.nextInt(warriors_names.length)], squad); // Добавление в отряд бойца со случайным именем из массива warriors_names
                    break;
                case 2: warriors_squad1[i] = new Archer(warriors_names[rand.nextInt(warriors_names.length)], squad);
                    break;
                case 3: warriors_squad1[i] = new SwordMaster(warriors_names[rand.nextInt(warriors_names.length)], squad);
                    break;
            }
        }
        squad.setWarriors(warriors_squad1);
        return squad;
    }

    public static void main(String[] args) {
        Squad squad1 = Battle.createSquad();
        Squad squad2 = Battle.createSquad();
        System.out.println("В сражение вступают два отряда!\n");
        Battle.oneBattle(squad1,squad2);
    }
}
