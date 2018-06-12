package com.aarora.lightningviewer.service;

import com.aarora.lightningviewer.entity.DocumentModel;
import com.aarora.lightningviewer.repository.DocumentRepository;
import com.aarora.lightningviewer.utils.LightningUtils;
import org.ghost4j.document.DocumentException;
import org.ghost4j.document.PDFDocument;
import org.ghost4j.renderer.RendererException;
import org.ghost4j.renderer.SimpleRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.util.List;
import java.util.Optional;

public class DocumentService {
    @Autowired
    private DocumentRepository repository;

    public void processDocument(MultipartFile file) {
        try {
            File convFile = LightningUtils.convert(file);
            PDFDocument document = new PDFDocument();
            document.load(convFile);
            SimpleRenderer simpleRenderer = new SimpleRenderer();
            simpleRenderer.setResolution(300);
            List<Image> images = simpleRenderer.render(document);
            for (int i = 0; i < 1; i++) {
                ImageIO.write((RenderedImage) images.get(i), "png", new File("src\\main\\resources\\images\\" + (i + 1) + ".png"));
            }
            BufferedImage bImage = ImageIO.read(new File("src\\main\\resources\\images\\1.png"));
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "jpg", bos);
            byte[] arrayPic = bos.toByteArray();
            System.out.println(arrayPic.length);
            DocumentModel model = new DocumentModel(file.getOriginalFilename(), arrayPic);
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
