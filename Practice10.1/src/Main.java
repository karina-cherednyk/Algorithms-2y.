import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        s.nextInt();
        BigInteger a = s.nextBigInteger();
        BigInteger b = s.nextBigInteger();
        BigInteger TWO = BigInteger.valueOf(2);
        BigInteger a2;
        BigInteger b2;

        while(!a.equals(b)){
                a2 = a.divide(TWO);
                b2 = b.divide(TWO);
                if(a.compareTo(b2)<=0) b = b2;
                else  if(b.compareTo(a2)<=0) a = a2;
                else {
                    a = a2;
                    b = b2;
                }
            }
        System.out.println(a);
        }


    }

