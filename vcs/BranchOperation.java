package vcs;

import utils.ErrorCodeManager;
import utils.OperationType;

import java.util.ArrayList;

public final class BranchOperation extends VcsOperation {

    public BranchOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    @Override
    public int execute(Vcs vcs) {
        Branch newBranch = new Branch(operationArgs.get(1));
        // Verific daca mai exista un branch cu acelasi nume
        for(Branch branch : vcs.getBranchHeadList()) {
            if(branch.getBranchName().equals(newBranch.getBranchName())) {
                return ErrorCodeManager.VCS_BAD_CMD_CODE;
            }
        }
        // Copiez toate commiturile din nameHead in noul branch
        for (Commit e : Vcs.nameHead.getCommitEntityList()) {
            newBranch.getCommitEntityList().add(e);
        }
        // Adaug branch in lista cu branch-uri
        vcs.getBranchHeadList().add(newBranch);
        return ErrorCodeManager.OK;
    }
}
