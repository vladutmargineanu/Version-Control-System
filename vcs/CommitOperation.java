package vcs;

import filesystem.FileSystemSnapshot;
import utils.OperationType;
import utils.ErrorCodeManager;

import java.util.ArrayList;

public final class CommitOperation extends VcsOperation {

    public CommitOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    @Override
    public int execute(Vcs vcs) {
        // Verific daca lista cu operatiile Staging este goala
        if(vcs.getStagingOperationList().isEmpty()) {
            return ErrorCodeManager.VCS_BAD_CMD_CODE;
        }
        // Adaug primul commit in branchul master
        if(Vcs.nameHead.getBranchName().equals("master")
                && Vcs.nameHead.getCommitEntityList().size() == 0) {
            Commit newCommit = new Commit(new FileSystemSnapshot(vcs.getOutputWriter()));
            Vcs.nameHead.getCommitEntityList().add(newCommit);
        }
        // Adaug commit in lista branch-ului curent
        FileSystemSnapshot commitSnapshot;
        commitSnapshot = vcs.getActiveSnapshot().cloneFileSystem();
        Commit newCommit = new Commit(commitSnapshot, operationArgs);
        Vcs.nameHead.getCommitEntityList().add(newCommit);
        // Dupa aplicarea comenzii, se goleste staging-ul
        vcs.emptyStagingOperationList();
        return ErrorCodeManager.OK;
    }
}
