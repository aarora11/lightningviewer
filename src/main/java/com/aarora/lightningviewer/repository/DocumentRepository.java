package com.aarora.lightningviewer.repository;

import com.aarora.lightningviewer.entity.DocumentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentRepository extends CrudRepository<DocumentModel, Integer> {

    @Query(value = "SELECT id FROM document_model" , nativeQuery = true)
    List<String> findAllDocumentNames();
}
