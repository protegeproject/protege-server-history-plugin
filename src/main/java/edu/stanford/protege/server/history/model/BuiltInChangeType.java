package edu.stanford.protege.server.history.model;

import java.awt.*;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Rafael Gonçalves <br>
 * Stanford Center for Biomedical Informatics Research
 */
public enum BuiltInChangeType implements ChangeType {
    LOGICAL("Logical"),
    SIGNATURE("Signature"),
    ANNOTATION("Annotation"),
    IMPORT("Import"),
    ONTOLOGY_ANNOTATION("Ontology Annotation"),
    ONTOLOGY_IRI("Ontology IRI");

    private String type;

    BuiltInChangeType(String type) {
        this.type = checkNotNull(type);
    }

    @Override
    public String toString() {
        return type;
    }

    @Override
    public String getDisplayName() {
        return type;
    }

    @Override
    public boolean isBuiltInType() {
        return true;
    }

    @Override
    public Optional<Color> getDisplayColor() {
        return Optional.empty();
    }
}
