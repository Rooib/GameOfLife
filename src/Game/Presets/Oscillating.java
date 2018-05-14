package Game.Presets;

public class Oscillating {

    public static boolean[][] blinker = {
            {false, false, false, false, false},
            {false, true, true, true, false},
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
}
