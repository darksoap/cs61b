import java.util.Arrays;

public class RadixSortTester {
    private static String[] str = new String[] {"ab", "va", "123", "AB", "AbcD", "aBc"};

    public static void main(String[] args) {
        long startTime=System.currentTimeMillis();
        System.out.println("执行代码块/方法");
        String[] res = RadixSort.sort(str);
        long endTime=System.currentTimeMillis();
        System.out.println("程序运行时间： "+(endTime - startTime)+"ms");

        long startTime2=System.currentTimeMillis();
        System.out.println("执行代码块/方法");
        Arrays.sort(str);
        long endTime2=System.currentTimeMillis();
        System.out.println("程序运行时间： "+(endTime2 - startTime2)+"ms");
        String[] next = str;
        for (String s : res) {
            System.out.println(s);
        }


        System.out.println();
        for (String s : next) {
            System.out.println(s);
        }
    }
}
