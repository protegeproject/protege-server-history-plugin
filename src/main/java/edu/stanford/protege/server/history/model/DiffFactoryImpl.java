package edu.stanford.protege.server.history.model;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;
import edu.stanford.protege.metaproject.api.UserId;
import org.semanticweb.owlapi.model.OWLObject;
import org.semanticweb.owlapi.model.OWLOntologyChange;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

/**
 * @author Rafael Gonçalves <br>
 * Stanford Center for Biomedical Informatics Research
 */
public class DiffFactoryImpl implements DiffFactory {
    private final TimeBasedGenerator gen = Generators.timeBasedGenerator(EthernetAddress.fromInterface());

    /**
     * No-args constructor
     */
    public DiffFactoryImpl() { }

    @Override
    public Change createChange(ChangeId changeId, Set<OWLOntologyChange> changes, ChangeDetails details, CommitMetadata commitMetadata, ChangeMode changeMode) {
        return new ChangeImpl(changeId, changes, details, commitMetadata, changeMode);
    }

    @Override
    public Change createChange(Set<OWLOntologyChange> changes, ChangeDetails details, CommitMetadata commitMetadata, ChangeMode changeMode) {
        return createChange(createChangeId(), changes, details, commitMetadata, changeMode);
    }

    @Override
    public CommitId createCommitId(String commitId) {
        return new CommitIdImpl(commitId);
    }

    @Override
    public CommitMetadata createCommitMetadata(CommitId commitId, String userId, Date date, String comment) {
        return new CommitMetadataImpl(commitId, userId, date, comment);
    }

    @Override
    public ChangeDetails createChangeDetails(RevisionTag revisionTag, OWLObject changeSubject, ChangeType changeType,
                                             Optional<OWLObject> property, Optional<String> newValue) {
        return new ChangeDetailsImpl(revisionTag, changeSubject, changeType, property, newValue);
    }

    @Override
    public RevisionTag createRevisionTag(String revisionTag) {
        return new RevisionTagImpl(revisionTag);
    }

    @Override
    public ChangeId createChangeId() {
        return createChangeId(gen.generate().toString());
    }

    @Override
    public ChangeId createChangeId(String changeId) {
        return new ChangeIdImpl(changeId);
    }

    @Override
    public Review createReview(ReviewStatus status, Optional<UserId> author, Optional<Date> date, Optional<String> comment, boolean isCommitted) {
        return new ReviewImpl(status, author, date, comment, isCommitted);
    }
}
