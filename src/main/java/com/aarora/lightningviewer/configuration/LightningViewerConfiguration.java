package com.aarora.lightningviewer.configuration;

import org.ghost4j.document.PDFDocument;
import org.ghost4j.renderer.SimpleRenderer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LightningViewerConfiguration {

    @Bean
    public PDFDocument pdfDocument(){
        return new PDFDocument();
    }

    @Bean
    public SimpleRenderer simpleRenderer(){
        return  new SimpleRenderer();
    }
}
