package edu.stanford.protege.server.history.ui;

import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.client.ui.GuiUtils;
import org.protege.editor.owl.ui.OWLIcons;
import org.protege.editor.owl.ui.renderer.OWLAnnotationPropertyIcon;
import org.protege.editor.owl.ui.renderer.OWLCellRenderer;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLObject;

import javax.swing.*;

/**
 * @author Rafael Gonçalves <br>
 * Stanford Center for Biomedical Informatics Research
 */
public class OwlCellRenderer extends OWLCellRenderer {
    private OWLEditorKit editorKit;

    public OwlCellRenderer(OWLEditorKit editorKit) {
        this(editorKit, true, true);
    }

    public OwlCellRenderer(OWLEditorKit editorKit, boolean renderExpression, boolean renderIcon) {
        super(editorKit, renderExpression, renderIcon);
        this.editorKit = editorKit;
        setOpaque(true);
        setHighlightKeywords(true);
    }

    @Override
    protected Icon getIcon(Object object) {
        if(object instanceof IRI) {
            setHighlightKeywords(false);
            IRI ontIri = editorKit.getOWLModelManager().getActiveOntology().getOntologyID().getOntologyIRI().get();
            if(!object.equals(ontIri)) { // IRI of OWLAnnotationSubject
                return new OWLAnnotationPropertyIcon();
            }
            else { // IRI of ontology
                return OWLIcons.getIcon(HistoryUiUtils.ONTOLOGY_ICON_FILENAME);
            }
        }
        else if(object instanceof String) {
            setHighlightKeywords(false);
            return GuiUtils.getIcon(HistoryUiUtils.STRING_ICON_FILENAME, 13, 13);
        }
        else {
            return editorKit.getWorkspace().getOWLIconProvider().getIcon((OWLObject) object);
        }
    }
}