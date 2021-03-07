/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adidas.Utilities;

import Adidas.Exceptions.ResizeImageException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

/**
 *
 * @author Chahir Chalouati
 */
@Slf4j
public class MediaResizer {

    public static InputStream resizeImage(BufferedImage read, int height, int width, String fileExtentions) {

        try {

            BufferedImage asBufferedImage = Thumbnails
                    .of(read)
                    .outputFormat(fileExtentions)
                    .size(height, width)
                    .asBufferedImage();

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(asBufferedImage, fileExtentions, os);
            return new ByteArrayInputStream(os.toByteArray());

        } catch (IOException ex) {
            log.error("unable to resize file {*}", ex);
            throw new ResizeImageException("Unable to resize image {}: ", ex);
        }

    }

}
