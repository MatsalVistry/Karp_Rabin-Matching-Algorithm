import java.io.File;
import java.util.Scanner;

public class KarpRabin
{
    public static void main(String [] args) throws Exception
    {
        File file = new File("DocOne.txt");
        Scanner sc = new Scanner(file);
        String first = "";
        String second = "";


        while(sc.hasNextLine())
        {
            first+=sc.nextLine();
        }

        file = new File("DocTwo.txt");
        sc = new Scanner(file);

        while(sc.hasNextLine())
        {
            second+=sc.nextLine();
        }

        long startTime = System.nanoTime();
        int firstHash = 0;
        int secondHash =0;

        for(int x=0;x<first.length();x++)
        {
            firstHash+=append(first.charAt(x));
            secondHash+=append(second.charAt(x));
        }

        System.out.println(firstHash);


        for(int x=first.length();x<second.length();x++)
        {
            secondHash+=append(second.charAt(x));

            if(x-first.length()>=0)
                secondHash-=skip(second.charAt(x-first.length()));
            if(secondHash==firstHash && first.equals(second.substring(x-first.length()+1,x+1)))
                System.out.println("Matches");

        }
        System.out.println(secondHash);
        long endTime = System.nanoTime();

        System.out.println("TIMING FOR KARP RABIN: "+ ((endTime-startTime)));

        long s2 = System.nanoTime();
        int s = second.indexOf(first);
        long e2 = System.nanoTime();

        System.out.println("TIMING FOR CONTAINS: "+s+ ((e2-s2)/1000000));

    }
    public static int append(char c)
    {
        String s = ""+c;
        int v = s.hashCode();
        v=(int)(v*Math.pow(v%33,3)%61319);
        return v;
    }
    public static int skip(char c)
    {
        String s = ""+c;
        int v = s.hashCode();
        v=(int)(v*Math.pow(v%33,3)%61319);
        return v;
    }
}
