package vcs;

import utils.ErrorCodeManager;
import utils.OutputWriter;
import utils.OperationType;

import java.util.ArrayList;

public final class StatusOperation extends VcsOperation {

    public StatusOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    @Override
    public int execute(Vcs vcs) {
        // Afisam numele branch-ului curent și modificarile care sunt in staging
        ArrayList<String> statusOperation = vcs.statusCommand();
        OutputWriter outputWriter = vcs.getOutputWriter();
        outputWriter.write("On branch: " + Vcs.nameHead.getBranchName() + "\n");
        outputWriter.write("Staged changes:\n");
        for(String e : statusOperation) {
            outputWriter.write(e);
        }
        return ErrorCodeManager.OK;
    }
}
