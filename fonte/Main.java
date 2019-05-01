import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        HashMap<String,Var> map = new HashMap<String,Var>();
        if (args.length>0) {
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
                Parser.parse(fileInput.nextLine(),map);
            }
            fileInput.close();
        }
        else {
            Scanner s = new Scanner(System.in);
            String line;
            for (;;) {
                System.out.print("<$> ");
                line=s.nextLine();
                if (line.equals("q")) return;
                Parser.parse(line,map);
            }
        }
    }
}