package vcs;

import utils.OperationType;
import utils.ErrorCodeManager;

import java.util.ArrayList;

public final class CheckoutOperation extends VcsOperation {
    public CheckoutOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    @Override
    public int execute(Vcs vcs) {
        // Verific daca lista cu comenzile din staging este goala
        if(!vcs.getStagingOperationList().isEmpty()) {
            return ErrorCodeManager.VCS_STAGED_OP_CODE;
        }
        // Daca comanda este pentru commit-uri
        if(operationArgs.get(1).equals("-c")) {
            int index = -1;
            // Retin indexul la care se gaseste commit-ul cu id-ul respectiv
            for(Commit e : Vcs.nameHead.getCommitEntityList()) {
                if(Integer.toString(e.getCommitId()).equals(operationArgs.get(2))) {
                    index = Vcs.nameHead.getCommitEntityList().indexOf(e);
                }
            }
            // Sterg commit-urile care urmeaza dupa commit-ul cu id-ul respectiv
            if(index != -1) {
                Vcs.nameHead.getCommitEntityList().removeAll(Vcs.nameHead.getCommitEntityList().
                         subList(index + 1, Vcs.nameHead.getCommitEntityList().size()));
                // Setez activeSnapshot la la versiunea data de ultimul commit
                vcs.changeActiveSnapshot(Vcs.nameHead.getCommitEntityList()
                        .get(Vcs.nameHead.getCommitEntityList().size() - 1).getCommitEntity());

               return ErrorCodeManager.OK;
            } else {
                return ErrorCodeManager.VCS_BAD_PATH_CODE;
            }
        }
        // verific daca exista branch-ul cu numele dat in lista
        for(Branch branch : vcs.getBranchHeadList()) {
            // Setez pointerul Head la branch-ul respectiv
            if(branch.getBranchName().equals(operationArgs.get(1))) {
                Vcs.nameHead = branch;
                return ErrorCodeManager.OK;
            }
        }
        return ErrorCodeManager.VCS_BAD_CMD_CODE;
    }
}
