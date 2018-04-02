package edu.stanford.protege.server.history.model;

import java.util.Date;

/**
 * @author Rafael Gonçalves <br>
 * Stanford Center for Biomedical Informatics Research
 */
public interface CommitMetadata extends Comparable<CommitMetadata> {

    /**
     * Get the commit identifier
     *
     * @return Commit identifier
     */
    CommitId getCommitId();

    /**
     * Get the user identifier of the commit author
     *
     * @return User identifier of commit author
     */
    String getAuthor();

    /**
     * Get the commit date
     *
     * @return Commit date
     */
    Date getDate();

    /**
     * Get the commit comment
     *
     * @return Commit comment
     */
    String getComment();

}
