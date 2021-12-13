package cnft.velo.main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Console;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class Main {


    public static String readFile(String path) throws  Exception{
        String data = "";
        File f = new File(path);
        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(f);
        while (sc.hasNextLine()) {
            sb.append(sc.nextLine()+"\n");
        }
        data = sb.toString();
        return data;
    }

    public static void main(String[] args) throws Exception{
        String ts = "@"+(int)System.currentTimeMillis()/1000.;
        if(args.length > 1) {
         if(args[1].equals("decode")) {
             String decoded = NFTCDecoder.decode(args[0]);
             FileWriter fw = new FileWriter("CodeNFT___DATA___" + ts);
             fw.write(decoded);
             fw.close();
         }
        }
        else {
            String data = readFile(args[0]);
            BufferedImage image = NFTCGenerator.generate(data);
            ImageIO.write(image, "jpg", new File("CodeNFT___"+ ts + ".jpg"));
        }
    }
}
