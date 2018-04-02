package edu.stanford.protege.server.history.ui;

import edu.stanford.protege.server.history.model.ChangeMode;
import org.protege.editor.owl.client.ui.GuiUtils;

import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Rafael Gonçalves <br>
 * Center for Biomedical Informatics Research <br>
 * Stanford University
 */
public class HistoryUiUtils {
    public static final Color
        ADDITION_COLOR = new Color(219, 253, 217),
        REMOVAL_COLOR = new Color(255, 221, 220),
        ONTOLOGY_IRI_COLOR = new Color(242, 248, 255),
        DEFAULT_CHANGE_COLOR = new Color(250, 242, 209),
        WHITE_BACKGROUND = Color.WHITE,
        UNSELECTED_FOREGROUND = Color.BLACK;

    public static final Border
        MATTE_BORDER = new MatteBorder(1, 1, 1, 1, GuiUtils.BORDER_COLOR),
        EMPTY_BORDER = new EmptyBorder(0,0,0,0);

    public static final String
        USER_ICON_FILENAME = "user.png",
        USERS_ICON_FILENAME = "users.png",
        COMMIT_ICON_FILENAME = "commit.png",
        STRING_ICON_FILENAME = "string.png",
        ONTOLOGY_ICON_FILENAME = "ontology.png",
        WARNING_ICON_FILENAME = "warning.png",
        REVIEW_ACCEPTED_ICON_FILENAME = "review-accepted.png",
        REVIEW_REJECTED_ICON_FILENAME = "review-rejected.png",
        REVIEW_CLEAR_ICON_FILENAME = "review-clear.png",
        REVIEW_PENDING_ICON_FILENAME = "review-pending.png",
        NEW_REVIEW_ICON_FILENAME = "review-new.png";

    public static void setComponentBackground(Component c, ChangeMode mode) {
        switch(mode) {
            case ADDITION:
                c.setBackground(ADDITION_COLOR); break;
            case REMOVAL:
                c.setBackground(REMOVAL_COLOR); break;
            case ONTOLOGY_IRI:
                c.setBackground(ONTOLOGY_IRI_COLOR); break;
            case ALIGNED:
            case CUSTOM:
            default:
                c.setBackground(DEFAULT_CHANGE_COLOR); break;
        }
    }

    public static String getFormattedDate(Date date) {
        return new SimpleDateFormat("EEE, MMM d yyyy HH:mm").format(date);
    }

    public static String getShortenedFormattedDate(Date date) {
        return new SimpleDateFormat("MMM d yyyy HH:mm").format(date);
    }

}
