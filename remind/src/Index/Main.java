package Index;

public class Main {
    public static void main(String[] argv) {
        switch (argv[0]) {
            case "setTodaySch":
                SetDaySch.index();
                break;
            case "dayRemind":
                DayRemind.index();
                break;
            case "beforeRemind":
                BeforeRemind.index(argv[1]);
                break;
        }
    }
}
