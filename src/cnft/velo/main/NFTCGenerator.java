package cnft.velo.main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class NFTCGenerator {

    public static String convertStringToBinary(String input) {

        StringBuilder result = new StringBuilder();
        char[] chars = input.toCharArray();
        for (char aChar : chars) {
            result.append(
                    String.format("%8s", Integer.toBinaryString(aChar))   // char -> int, auto-cast
                            .replaceAll(" ", "0")                         // zero pads
            );
        }
        return result.toString();

    }

    public static BufferedImage generate(String code) {
        String data = convertStringToBinary(code);
        System.out.println(data);
        StringBuilder dj = new StringBuilder();
        for (String j : data.split("")) {
            if (j.equals("1") || j.equals("0")) dj.append(j);
        }
        int scale = 100;
        data = dj.toString();
        String[] dataS = data.split("");
        double d = Math.sqrt(data.length());
        int s = ((int) d) + 1;
        System.out.println(data.length() + " " + s);
        BufferedImage image = new BufferedImage(s * scale, s * scale, BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();
        g = image.createGraphics();
        g.setColor(Color.white);
        int o = 0;
        g.fillRect(0, 0, s, s);
        for (int j = 0; j < s; j += 1) {

            for (int i = 0; i < s; i++) {
                String dw = dataS.length - 1 < o ? null : dataS[o], db = dataS.length - 1 < o ? null : (o > 0 ? dataS[o - 1] : "0");
                int w = dw == null ? 0 : Integer.valueOf(dw), b = db == null ? 0 : Integer.valueOf(db);
                int sum = w;
                if (sum == 0) {
                    g.setColor(Color.BLACK);
                } else {
                    double dv = Math.random();
                    if (dv < 0.6) {
                        Color c = new Color((int) (Math.random() * 0x1000000));
                        while (c.equals(Color.BLACK)) {
                            c=   new Color((int) (Math.random() * 0x1000000));
                        }
                        g.setColor(c);
                    } else {
                        g.setColor(Color.white);
                    }
                }
                g.fillRect(i * scale, j * scale, scale, scale);
                o += 1;
            }
        }
        return image;
    }
}
