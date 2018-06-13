package com.aarora.lightningviewer.service;

import com.aarora.lightningviewer.entity.DocumentModel;
import com.aarora.lightningviewer.repository.DocumentRepository;
import com.aarora.lightningviewer.utils.Constants;
import com.aarora.lightningviewer.utils.LightningUtils;
import org.apache.commons.io.FileUtils;
import org.ghost4j.document.DocumentException;
import org.ghost4j.document.PDFDocument;
import org.ghost4j.renderer.RendererException;
import org.ghost4j.renderer.SimpleRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.xml.ws.ServiceMode;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentService {
    @Autowired
    private DocumentRepository repository;

    @Autowired
    private PDFDocument document;

    @Autowired
    private SimpleRenderer simpleRenderer;

    public void processDocument(MultipartFile file) {
        File dir = new File(Constants.TEMP_IMAGE_PATH);
        if (!dir.exists()) {
            dir.mkdir();
        }
        try {
            // Load the file and create the image for the first page.
            File convFile = LightningUtils.convert(file);
            document.load(convFile);
            // Set the resolution of the image.
            simpleRenderer.setResolution(Constants.resolution);
            List<Image> images = simpleRenderer.render(document);
            // Write the image to temp storage.
            ImageIO.write((RenderedImage) images.get(0), "png", new File(Constants.TEMP_IMAGE_PATH + "temp.png"));
            //Read the image from the temp location in order to create a byte array
            BufferedImage bImage = ImageIO.read(new File(Constants.TEMP_IMAGE_PATH + "temp.png"));
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
//            ImageIO.write(bImage, "png", bos);
            byte[] arrayPic = bos.toByteArray();

            DocumentModel model = new DocumentModel(file.getOriginalFilename(), arrayPic, FileUtils.readFileToByteArray(convFile));
            /*
            Save the data to the database.
             */
            repository.save(model);
//            Optional<DocumentModel> m = repository.findById(1);
//            DocumentModel x = m.get();
//            BufferedImage img = ImageIO.read(new ByteArrayInputStream(x.getPic()));
//            FileOutputStream fos = new FileOutputStream("imagezzz.png");
//            fos.write(x.getPic());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (RendererException e) {
            e.printStackTrace();
        }
    }
}
