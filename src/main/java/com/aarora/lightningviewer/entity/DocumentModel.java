package com.aarora.lightningviewer.entity;

import org.hibernate.tool.schema.internal.exec.GenerationTarget;

import javax.persistence.*;

@Entity
@Table(name = "document_model")
public class DocumentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Lob
    @Column(name = "picture")
    private byte[] pic;

    @Lob
    @Column(name = "document")
    private byte[] document;

    public byte[] getDocument() {
        return document;
    }

    public DocumentModel(String name, byte[] pic, byte[] document) {

        this.name = name;
        this.pic = pic;
        this.document = document;
    }

    public String getName() {
        return name;
    }

    public byte[] getPic() {
        return pic;
    }

    public DocumentModel() {
    }
}
