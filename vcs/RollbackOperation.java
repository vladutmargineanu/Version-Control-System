package vcs;

import utils.ErrorCodeManager;
import utils.OperationType;


import java.util.ArrayList;

public final class RollbackOperation extends VcsOperation {
    public RollbackOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    @Override
    public int execute(Vcs vcs) {
        // Golim stagingul curent
        vcs.emptyStagingOperationList();
        // Aducem snapshot-ul de filesystem la versiunea data de ultimul commit
        vcs.changeActiveSnapshot(Vcs.nameHead.getCommitEntityList()
                .get(Vcs.nameHead.getCommitEntityList().size() - 1).getCommitEntity());

        return ErrorCodeManager.OK;
    }
}
