import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution {

    /// Обов’зково ви маєте використати MinPQ.
    public static void main(String[] args) throws FileNotFoundException {
        // створюємо початкову дошку з файлу
        String fileName = "puzzles/puzzle04.txt";
        Scanner in = new Scanner(new FileInputStream(fileName));
        int N = in.nextInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.nextInt();
        Board initial = new Board(blocks);

        // розв’язати
        Solver solver = new Solver(initial);

        // надрукувати рішення
        if (!solver.isSolvable())
            System.out.println("Дошка не має розв’язку");
        else {
            System.out.println("Мінімальна кількість кроків = " + solver.moves());
            for (Board board : solver.solution())
                System.out.println(board);
        }
    }

}
