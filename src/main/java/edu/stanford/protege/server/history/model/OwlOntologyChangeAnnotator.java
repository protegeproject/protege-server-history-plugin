package edu.stanford.protege.server.history.model;

import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLOntologyChange;

import java.util.List;
import java.util.Optional;

/**
 * @author Rafael Gonçalves <br>
 * Stanford Center for Biomedical Informatics Research
 */
public interface OwlOntologyChangeAnnotator {

    /**
     * Get a bundle of OWL ontology changes annotated with a structured-text value based on the given parameters. These changes are subsequently
     * interpreted as a single change (i.e., one row in the history GUI) that bundles multiple OWLOntologyChange-level changes.
     *
     * @param changes   List of changes to be annotated
     * @param revisionTag   Revision tag
     * @param changeSubject Change subject (i.e., OWL entity)
     * @param changeType    Change type
     * @param p Annotation property that got changed
     * @param newValue  New value of the (annotation) property
     * @return List of annotated ontology changes
     */
    List<OWLOntologyChange> getAnnotatedChange(List<OWLOntologyChange> changes, RevisionTag revisionTag, OWLEntity changeSubject, ChangeType changeType,
                                               Optional<OWLEntity> p, Optional<String> newValue);

}
