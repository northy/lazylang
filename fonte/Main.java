//Alexsandro Thomas <alexsandrogthomas@gmail.com>

package fonte;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

class Main {
    public static String defaultShellPrefix = ">>>";
    public static String defaultShellIncomplete = "...";
    public static String shellPrefix = new String(defaultShellPrefix);
    public static String curLine = "";

    public static void main(String[] args) {
        Parser p;
        HashMap<String,Var> vars = new HashMap<String,Var>();
        HashMap<String,Function> functions = new HashMap<String,Function>();
        if (args.length>0) {
            p = new Parser(false);
            int cLine = 0;
            File file;
            Scanner fileInput;
            try {
                file = new File(args[0]);
                fileInput = new Scanner(file);
            }
            catch (Exception e) {
                System.out.println("Error opening file: ");
                System.out.print(args[0]);
                return;
            }
            
            while (fileInput.hasNextLine()) {
                cLine++;
                Main.curLine = "at line " + cLine;
                try {
                    p.parse(fileInput.nextLine(),vars,functions);
                }
                catch (Exception e) {
                    System.out.println("ERROR " + Main.curLine + ": " + e.getMessage());
                    System.exit(1);
                }
            }
            fileInput.close();
            if (shellPrefix.equals(defaultShellIncomplete)) {
                System.out.println("ERROR " + "at line " + cLine++ + " Unexpected EOF");
            }
        }
        else {
            p = new Parser(true);
            Scanner s = new Scanner(System.in, "UTF-8");
            String line="";
            for (;;) {
                System.out.print(Main.shellPrefix + " ");
                line=s.nextLine();  
                if (line.equals(":q")) break;
                try {
                    p.parse(line,vars,functions);
                }
                catch (Exception e) {
                    p.curString="";
                    System.out.println("ERROR " + Main.curLine + ": " + e.getMessage());
                }
            }
            s.close();
        }
    }
}
