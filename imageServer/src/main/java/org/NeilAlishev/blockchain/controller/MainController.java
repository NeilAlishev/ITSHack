package org.NeilAlishev.blockchain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Neil Alishev
 */
@Controller
public class MainController {

    @PostMapping("/upload")
    @ResponseBody
    public Map<String, Integer> singleFileUpload(@RequestParam("file") MultipartFile file) throws IOException, InterruptedException {
        try {
            byte[] bytes = file.getBytes();

            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            BufferedImage image = ImageIO.read(bais);

            try {
                File fileToWrite = new File("/Users/neil/Desktop/hack/keras-frcnn/input_dir/image.jpg");
                ImageIO.write(image, "jpg", fileToWrite);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        String command = "python2 /Users/neil/Desktop/hack/keras-frcnn/test_frcnn_count.py " +
                "--input_file ~/Desktop/hack/keras-frcnn/videos/test_video2.mp4 " +
                "--output_file ~/Desktop/hack/keras-frcnn/output/output_video1.mp4 " +
                "--output_dir ~/Desktop/hack/keras-frcnn/output_dir " +
                "--input_dir ~/Desktop/hack/keras-frcnn/input_dir --frame_rate=25\n";

        Process p = Runtime.getRuntime().exec(command);

        p.waitFor();
        InputStream inputStream = p.getInputStream();

        Scanner s = new Scanner(inputStream);

        String line = null;
        while (s.hasNext())
            line = s.nextLine();

        Map<String, Integer> result = new HashMap<>();
        result.put("result", Integer.parseInt(line));
        return result;
    }
}
