package project6;

public class SourceFile {
    public static String path = "pr4_5_data/";
    public static String[] files = {
            "grid6x6",
            "horizontal100",
            "input6",
            "input8",
            "input40",
            "input50",
            "input56",
            "input100",
            "input400",
            "rs1423"
    };
    public static String file(int i){
        return  path+files[i]+".txt";
    }
}
