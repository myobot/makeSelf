/**
 * Created by WZW on 2017/10/03.
 */
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OSExcute {
    public static void main(String[] args) {
        OSExcute.command("ipconfig") ;
    }
    public static void command(String command) {
        boolean err = false;
        try {
            Process process = new ProcessBuilder(command.split(" ")).start();
            BufferedReader result = new BufferedReader(new InputStreamReader(process.getInputStream(), "GBK"));
            String s ;
            while((s = result.readLine()) != null){
                System.out.println(s) ;
            }
            BufferedReader errors = new BufferedReader(new InputStreamReader(process.getErrorStream(), "GBK"));
            while((s = errors.readLine()) != null){
                System.err.println(s);
                err = true ;
            }
        } catch (IOException e) {
            System.out.println("Exception") ;
            if(!command.startsWith("CMD /C"))
                command("CMD /C"+command) ;
            else
                throw new RuntimeException(e) ;
        }
        if(err){
            try {
                throw new OSException("Error executing") ;
            } catch (OSException e) {
                e.printStackTrace();
            }

        }
    }
}