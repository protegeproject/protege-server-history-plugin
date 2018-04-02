package edu.stanford.protege.server.history.ui;

import edu.stanford.protege.server.history.model.Change;

import javax.swing.table.AbstractTableModel;

/**
 * @author Rafael Gonçalves <br>
 * Stanford Center for Biomedical Informatics Research
 */
public abstract class ChangeDetailsTableModel extends AbstractTableModel {

    /**
     * Set the change for this change details table
     *
     * @param change    Change
     */
    abstract void setChange(Change change);

}
