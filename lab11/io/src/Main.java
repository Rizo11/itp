import java.io.File;
import java.io.FileOutputStream;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");
        try {
            File fout = new File("myOutFile.txt");
            FileOutputStream fos = new FileOutputStream(fout);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            bw.write("Write somthing to the file ...");
            bw.newLine();
            bw.close();
        } catch (FileNotFoundException e){
            // File was not found
            e.printStackTrace();
        } catch (IOException e) {
            // Problem when writing to the file
            e.printStackTrace();
        }
    }
}