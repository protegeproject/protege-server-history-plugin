package edu.stanford.protege.server.history.ui;

import edu.stanford.protege.server.history.model.LogDiff;
import org.protege.editor.owl.OWLEditorKit;
import org.semanticweb.owlapi.model.*;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Rafael Gonçalves <br>
 * Stanford Center for Biomedical Informatics Research
 */
public class ChangeListCellRenderer extends JTextArea implements ListCellRenderer<OWLOntologyChange>, Serializable {
    private OwlCellRenderer owlCellRenderer;

    public ChangeListCellRenderer(OWLEditorKit editorKit) {
        super();
        owlCellRenderer = new OwlCellRenderer(checkNotNull(editorKit));
    }

    @Override
    public Component getListCellRendererComponent(JList list, OWLOntologyChange change, int index, boolean isSelected, boolean cellHasFocus) {
        if(change.isAxiomChange()) {
            OWLAxiom axiom = change.getAxiom();
            Component c = owlCellRenderer.getListCellRendererComponent(list, axiom, index, isSelected, cellHasFocus);
            setBackground(list, change, c, isSelected);
            return c;
        }
        else if(change.isImportChange()) {
            OWLImportsDeclaration decl = ((ImportChange)change).getImportDeclaration();
            Component c = owlCellRenderer.getListCellRendererComponent(list, decl.getIRI(), index, isSelected, cellHasFocus);
            setBackground(list, change, c, isSelected);
            return c;
        }
        else if(change instanceof SetOntologyID) {
            setText("New IRI: " + ((SetOntologyID)change).getNewOntologyID().getOntologyIRI().toString());
            setBackground(list, change, this, isSelected);
        }
        else if(change instanceof AnnotationChange) {
            OWLAxiom axiom = getAnnotationAssertionAxiom((AnnotationChange)change);
            Component c = owlCellRenderer.getListCellRendererComponent(list, axiom, index, isSelected, cellHasFocus);
            setBackground(list, change, c, isSelected);
            return c;
        }
        return this;
    }

    private void setBackground(JList list, OWLOntologyChange change, Component c, boolean isSelected) {
        if (isSelected) {
            c.setBackground(list.getSelectionBackground());
            c.setForeground(list.getSelectionForeground());
        }
        else {
            HistoryUiUtils.setComponentBackground(c, LogDiff.getChangeMode(change));
            c.setForeground(HistoryUiUtils.UNSELECTED_FOREGROUND);
        }
    }

    private OWLAnnotationAssertionAxiom getAnnotationAssertionAxiom(AnnotationChange change) {
        OWLAnnotation ann = change.getAnnotation();
        OWLOntology ont = change.getOntology();
        OWLDataFactory df = ont.getOWLOntologyManager().getOWLDataFactory();
        return df.getOWLAnnotationAssertionAxiom(ont.getOntologyID().getOntologyIRI().get(), ann);
    }
}
