package edu.stanford.protege.server.history.model;

/**
 * @author Rafael Gonçalves <br>
 * Stanford Center for Biomedical Informatics Research
 */
public interface LogDiffListener {

    void statusChanged(LogDiffEvent event);

}
