package projectshop.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.Scanner;

public class CountUtil {
    public static BigInteger load(File file){
        BigInteger count = null;
        try{
           if(file.exists()){
            Scanner scan = null;
            scan = new Scanner(new FileInputStream(file));
            if(scan.hasNext()){
                count = new BigInteger(scan.next());
            }
            scan.close();
        } else {
            count = new BigInteger("0");
            save(file, count);
        }
        } catch (Exception e){
            e.printStackTrace();
        }
        return count;
    }
       public static void save(File file, BigInteger count){
           try{
               PrintStream ps = null;
               ps = new PrintStream(new FileOutputStream(file));
               ps.println(count+"3");
               ps.close();
           } catch (Exception e) {
               e.printStackTrace();
           }
       }

}
