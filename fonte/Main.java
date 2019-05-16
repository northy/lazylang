import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

class Main {
    public static String shellPrefix = "Prelude>";
    public static String curLine = "";

    public static void main(String[] args) {
        Parser p;
        HashMap<String,Var> vars = new HashMap<String,Var>();
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
                p.parse(fileInput.nextLine(),vars);
            }
            fileInput.close();
        }
        else {
            p = new Parser(true);
            Scanner s = new Scanner(System.in, "UTF-8");
            String line="";
            for (;;) {
                System.out.print(Main.shellPrefix + " ");
                line=s.nextLine();
                if (line.equals("q")) break;
                p.parse(line,vars);
            }
            s.close();
        }
    }
}
