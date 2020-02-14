package vcs;

import utils.OutputWriter;
import utils.OperationType;

import java.util.ArrayList;

public final class LogOperation extends VcsOperation {
    public LogOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    @Override
    public int execute(Vcs vcs) {
        // Iau branch-ul curent si il parcurg pentru afisarea commit-urilor
        OutputWriter outputWriter = vcs.getOutputWriter();
        for(Commit e : Vcs.nameHead.getCommitEntityList()) {
            outputWriter.write("Commit id: " + e.getCommitId() + "\n");
            outputWriter.write("Message:" + e.getStringCommit() + "\n");
            // Verific daca am ajuns la capatul listei
            if(Vcs.nameHead.getCommitEntityList().indexOf(e)
                    != Vcs.nameHead.getCommitEntityList().size() - 1) {
                outputWriter.write("\n");
            }
        }
        return 0;
    }
}
