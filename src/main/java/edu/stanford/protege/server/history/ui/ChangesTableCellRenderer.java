package edu.stanford.protege.server.history.ui;

import edu.stanford.protege.server.history.model.ChangeMode;
import edu.stanford.protege.server.history.model.ChangeType;
import edu.stanford.protege.server.history.model.Review;
import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.client.ui.GuiUtils;
import org.semanticweb.owlapi.model.OWLObject;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

/**
 * @author Rafael Gonçalves <br>
 * Stanford Center for Biomedical Informatics Research
 */
public class ChangesTableCellRenderer extends LogDiffCellRenderer {

    /**
     * Constructor
     *
     * @param editorKit OWL editor kit
     */
    public ChangesTableCellRenderer(OWLEditorKit editorKit) {
        super(editorKit);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                   int row, int column) {
        // coordinates passed to the renderer are in view coordinate system after sorting, so they need converting to
        // model coordinates before accessing the model
        int modelRow = table.convertRowIndexToModel(row);
        ChangeMode mode = (ChangeMode) table.getModel().getValueAt(modelRow, ChangesTableModel.Column.MODE.ordinal());
        ChangeType type = (ChangeType) table.getModel().getValueAt(modelRow, ChangesTableModel.Column.CHANGE_TYPE
            .ordinal());
        if (value instanceof OWLObject) {
            Component c = owlCellRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
                column);
            setBackground(table, type, mode, c, isSelected);
            return c;
        }
        if (value instanceof Boolean) {
            if ((Boolean) value) {
                return getIconLabel(table, type, mode, HistoryUiUtils.WARNING_ICON_FILENAME, isSelected, true);
            } else {
                value = null;
            }
        }
        if (value != null && value instanceof Review) {
            Review review = (Review) value;
            switch (review.getStatus()) {
                case ACCEPTED:
                    return getIconLabel(table, type, mode, HistoryUiUtils.REVIEW_ACCEPTED_ICON_FILENAME, isSelected, review
                        .isCommitted());
                case REJECTED:
                    return getIconLabel(table, type, mode, HistoryUiUtils.REVIEW_REJECTED_ICON_FILENAME, isSelected, review
                        .isCommitted());
                case PENDING:
                default:
                    value = "";
            }
        }
        if (value instanceof Date) {
            setText(HistoryUiUtils.getFormattedDate((Date) value));
        } else {
            setText(value != null ? value.toString() : "");
        }
        setBackground(table, type, mode, this, isSelected);
        setFont(table.getFont());
        return this;
    }

    private void setBackground(JTable table, ChangeType type, ChangeMode mode, Component c, boolean isSelected) {
        if (isSelected) {
            c.setBackground(table.getSelectionBackground());
            c.setForeground(table.getSelectionForeground());
        } else if (type.getDisplayColor().isPresent()) {
            c.setBackground(type.getDisplayColor().get());
            c.setForeground(HistoryUiUtils.UNSELECTED_FOREGROUND);
        } else {
            HistoryUiUtils.setComponentBackground(c, mode);
            c.setForeground(HistoryUiUtils.UNSELECTED_FOREGROUND);
        }
    }

    private JLabel getIconLabel(JTable table, ChangeType type, ChangeMode mode, String iconFilename, boolean
        isSelected, boolean committed) {
        Icon icon = GuiUtils.getIcon(iconFilename, 25, 25);
        // rpc
//        if(!committed) {
//            ImageIcon base = (ImageIcon)GuiUtils.getIcon(iconFilename, 30, 30);
//            ImageIcon badge = (ImageIcon) GuiUtils.getIcon(GuiUtils.NEW_REVIEW_ICON_FILENAME, 13, 13);
//            BufferedImage img = new BufferedImage(30, 30, AlphaComposite.SRC_OVER);
//            Graphics g = img.getGraphics();
//            g.drawImage(base.getImage(), 0, 0, null);
//            g.drawImage(badge.getImage(), 17, 0, null);
//            icon = new ImageIcon(img);
//        }
        JLabel lbl = new JLabel("", icon, JLabel.CENTER);
        lbl.setOpaque(true);
        setBackground(table, type, mode, lbl, isSelected);
        return lbl;
    }
}
