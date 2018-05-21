package Game.Presets;

/**
 * Class for saving already given kind of popular configurations
 * for oscillating game of lifes
 *
 * @author Jan Leuschner
 * @version 2018-05-20
 */
public class Oscillating {

    private Oscillating() {
        //Eine Instanz dieser Klasse lassen wir uns nicht bieten!!!
        System.exit(-1);
    }

    public static boolean[][] blinker = {
            {false, false, false, false, false},
            {false, false, false, false, false},
            {false, true, true, true, false},
            {false, false, false, false, false},
            {false, false, false, false, false}
    };

    public static boolean[][] clock = {
            {false, false, false, false, false, false},
            {false, false, false, true, false, false},
            {false, true, true, false, false, false},
            {false, false, false, true, true, false},
            {false, false, true, false, false, false},
            {false, false, false, false, false, false}
    };

    public static boolean[][] pulsator = {
            {false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false},
            {false, false, true, false, false, true, false, false, false},
            {false, true, false, true, true, false, true, false, false},
            {false, false, true, false, false, true, false, false, false},
            {false, false, true, false, false, true, false, false, false},
            {false, true, false, true, true, false, true, false, false},
            {false, false, true, false, false, true, false, false, false},
            {false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false},
    };

    public static boolean[][] pulsatorTwo = {
            {false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, true, false, false, false, false, false},
            {false, false, false, false, false, true, false, false, false, false, false},
            {false, false, false, false, true, true, true, false, false, false, false,},
            {false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, true, true, true, false, false, false, false},
            {false, false, false, false, false, true, false, false, false, false, false},
            {false, false, false, false, false, true, false, false, false, false, false},
            {false, false, false, false, false, true, false, false, false, false, false},
            {false, false, false, false, false, true, false, false, false, false, false},
            {false, false, false, false, true, true, true, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, true, true, true, false, false, false, false},
            {false, false, false, false, false, true, false, false, false, false, false},
            {false, false, false, false, false, true, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false}
    };
}
