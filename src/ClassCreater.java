import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Method;

/**
 * Created by WZW on 2017/10/03.
 */
public class ClassCreater {
    public static void main(String args[]) throws Exception{
        BufferedOutputStream bi=new BufferedOutputStream(new FileOutputStream("D:\\IdeaProjects\\makeSelf\\newclass\\Class1.java"));
        String s="public class Class1{" +
                "public static void main(String args[]){" +
                "method();" +
                "}" +
                "public static void method(){" +
                "System.out.println(\"hello wrold!\");" +
                "}" +
                "}";
        byte b[]=s.getBytes();
        bi.write(b);
        bi.close();
        OSExcute.command("javac -d D:\\IdeaProjects\\makeSelf\\out\\production\\makeSelf D:\\IdeaProjects\\makeSelf\\newclass\\Class1.java");
//        OSExcute.command("java D:\\IdeaProjects\\makeSelf\\newclass\\Class1");
        ClassLoader loader=ClassLoader.getSystemClassLoader();
        Class<?> c=loader.loadClass("Class1");
        Method m[]=c.getDeclaredMethods();
        for(Method method:m){
            if(method.getName().equals("method")){
                method.invoke(c);
            }
        }
    }
}
