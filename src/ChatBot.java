import bot.Bot;
import java.util.*;


class ChatBot {
    public static void main(String[] args) {
        new ChatBot();
    }

    ChatBot() {
        Bot cbot = new Bot();
        Scanner in = new Scanner(System.in);
        while (true) {
            String m = in.nextLine();
            if (m.length() > 0) {
                System.out.println(cbot.sayInReturn(m));
            } else {
                System.out.println("Молчание золото :D");
            }
        }
    }
}
