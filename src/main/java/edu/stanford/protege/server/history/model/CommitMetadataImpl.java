package edu.stanford.protege.server.history.model;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Date;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * @author Rafael Gonçalves <br>
 * Stanford Center for Biomedical Informatics Research
 */
public final class CommitMetadataImpl implements CommitMetadata {
    private final CommitId commitId;
    private final String userId;
    private final Date date;
    private final String comment;

    /**
     * Constructor
     *
     * @param commitId  Commit identifier
     * @param userId    User identifier
     * @param date  Commit date
     * @param comment   Commit comment
     */
    public CommitMetadataImpl(CommitId commitId, String userId, Date date, String comment) {
        this.commitId = checkNotNull(commitId);
        this.userId = checkNotNull(userId);
        this.date = checkNotNull(date);
        this.comment = checkNotNull(comment);
    }

    @Override
    public CommitId getCommitId() {
        return commitId;
    }

    @Override
    public String getAuthor() {
        return userId;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public String getComment() {
        return comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommitMetadataImpl commit = (CommitMetadataImpl) o;
        return Objects.equal(commitId, commit.commitId) &&
                Objects.equal(userId, commit.userId) &&
                Objects.equal(date, commit.date) &&
                Objects.equal(comment, commit.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(commitId, userId, date, comment);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("commitId", commitId)
                .add("userId", userId)
                .add("date", date)
                .add("comment", comment)
                .toString();
    }

    @Override
    public int compareTo(CommitMetadata that) {
        return that.getDate().compareTo(this.date); // compare w.r.t. date only
    }
}
