package cnft.velo.main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;

public class NFTCDecoder {

    private static  String binaryStringtoData(String binary) {

        StringBuilder sb = new StringBuilder(); // Some place to store the chars
        String input = binary;
        Arrays.stream( // Create a Stream
                input.split("(?<=\\G.{8})") // Splits the input string into 8-char-sections (Since a char has 8 bits = 1 byte)
        ).forEach(sk -> // Go through each 8-char-section...
                sb.append((char) Integer.parseInt(sk, 2)) // ...and turn it into an int and then to a char
        );
        String output = sb.toString(); // Output text (t)
        return  output;
    }


    private  static String getBinaryFromImage(BufferedImage image) {
        StringBuilder sb = new StringBuilder();
        int dims = image.getHeight() / 100;
        System.out.println(dims);
        for(int i = 0 ; i < dims ; i +=1) {
            for(int j = 0 ; j < dims; j+=1) {
                int x = j>0?(j*100)+50:50,
                y = i>0? (i*100)+20:20;
                int c = image.getRGB(x,y);
                Color color = new Color(c);
                if(color.equals(Color.black)) {
                    sb.append("0");
                }
                else {
                    sb.append("1");
                }
            }
        }
        System.out.println(sb);
        return  sb.toString();
    }

    public static String decode(String file) throws  Exception{
        StringBuilder sb = new StringBuilder();

        BufferedImage img = ImageIO.read(new File(file));

        return  binaryStringtoData(getBinaryFromImage(img));

    }
}
