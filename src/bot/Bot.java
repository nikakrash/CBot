package bot;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Bot {

    public boolean game = false;
    public int num = 0;
    public int[] stat = new int[100];
    public ArrayList<String> quest = new ArrayList<String>();
    public int count;

    public Bot() {
    }

    public String sayInReturn(String m) {
        String say = "";
        switch (m){
            case "!help":
                help();
                break;
            case "!exit":
                System.exit(0);
                break;
            case "!start":
                if (!game) {
                    say = start();
                } else {
                    say = "Игра уже начата!";
                }
                break;
            case "!end":
                end();
                break;
            case "!back":
                //Ждите в обновлении
                break;
            case "!next":
                //Ждите в обновлении
                break;
            case "!random":
                //Ждите в обновлении
                break;
            case "!number":
                //Ждите в обновлении
                break;
            case "!hint":
                hint();
                break;
            case "!stats":
                stats();
                break;
            default:
                check(m);
                break;
        }
        return say;
    }

    public String start(){
        game = true;
        num = 0;
        try {
            FileInputStream file = new FileInputStream("C:\\Users\\Nika\\Desktop\\3/quest.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(file));
            String line;
            while ((line = br.readLine()) != null){
                quest.add(line);
            }
        }
        catch(IOException e){
            String fail = "Загадок нет!";
            return fail;
        }
        count = quest.size() / 3;
        for (int i = 0; i < count; i++){
            stat[i] = 0;
        }
        return quest.get(0);
    }

    public void help(){
        try {
            FileInputStream file = new FileInputStream("C:\\Users\\Nika\\Desktop\\3/help.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(file));
            String line;
            while ((line = br.readLine()) != null){
                System.out.println(line);
            }
        }
        catch(IOException e){
            System.out.println("Помощи нет, но вы держитесь...");
        }
    }

    public void end(){
        if (game) {
            game = false;
            stats();
            System.out.print("Спасибо за игру :)");

        } else {
            System.out.print("Ты не играл");
        }
    }

    public void stats(){
        System.out.print("Вы на задаче номер: ");
        System.out.println(num);
        System.out.print("Ваши баллы: ");
        int done = 0;
        String notDone = "";
        for (int i = 0; i < count; i++){
            if (stat[i] == 1){
                done++;
            } else {
                notDone += Integer.toString(i) + " ";
            }
        }
        System.out.println(done);
        System.out.print("Нерешенными остались: ");
        System.out.println(notDone);
    }

    public void hint(){
        if (game){
            System.out.println(quest.get(num*3+1));

        } else {
            System.out.println("Для начала начните игру(!start)");
        }
    }

    public void check(String m) {
        if (game) {
            if (m.equals(quest.get(num * 3 + 2))) {
                stat[num] = 1;
                num++;
                if (num == count){
                    end();
                } else {
                    System.out.println("Молодец, лови следующую загадку ^_^");
                    System.out.println(quest.get(num * 3));
                }
            } else {
                System.out.println("Извини, но с вероятность 100% это не ответ к задаче, либо я очень тупой");
            }
        } else {
            System.out.println("Извини, но я плох в общении -_-");
        }
    }
}
