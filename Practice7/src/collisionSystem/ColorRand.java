package collisionSystem;


import edu.princeton.cs.introcs.StdRandom;

import java.awt.*;

;

public class ColorRand {
    private static final int N = 13;
    public static Color sample(){
    int i = StdRandom.uniform(N);
    switch(i){
        case 0: return Color.BLACK;
        case 1: return Color.BLUE;
        case 2: return Color.CYAN;
        case 3: return Color.DARK_GRAY;
        case 4: return Color.GRAY;
        case 5: return Color.GREEN;
        case 6: return Color.LIGHT_GRAY;
        case 7: return Color.MAGENTA;
        case 8: return Color.ORANGE;
        case 9: return Color.PINK;
        case 10: return Color.RED;
        default: return Color.YELLOW;
    }
    }
}
