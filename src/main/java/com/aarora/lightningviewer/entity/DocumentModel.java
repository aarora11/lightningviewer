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

    public DocumentModel(String name, byte[] pic) {

        this.name = name;
        this.pic = pic;
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
