package com.aarora.lightningviewer.repository;

import com.aarora.lightningviewer.entity.DocumentModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<DocumentModel, Integer> {

}
