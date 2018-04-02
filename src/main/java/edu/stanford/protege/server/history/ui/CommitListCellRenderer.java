package edu.stanford.protege.server.history.ui;

import edu.stanford.protege.server.history.model.CommitMetadata;
import org.protege.editor.owl.client.ui.GuiUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * @author Rafael Gonçalves <br>
 * Stanford Center for Biomedical Informatics Research
 */
public class CommitListCellRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        label.setBorder(new EmptyBorder(7, 7, 7, 3));
        label.setIcon(GuiUtils.getIcon(HistoryUiUtils.COMMIT_ICON_FILENAME, 17, 17));
        label.setIconTextGap(9);

        CommitMetadata c = (CommitMetadata) value;
        label.setToolTipText("Comment: " + c.getComment());

        String dateStr = HistoryUiUtils.getShortenedFormattedDate(c.getDate());
        label.setText("<html><strong>" + dateStr + " · " + c.getAuthor().toString() + // TODO: To review later
                "</strong><br><p style=\"padding-top:3;" + (!isSelected ? "color:gray;" : "") + "\"><nobr>" + c.getComment() + "</nobr></p></html>");
        return label;
    }

}
