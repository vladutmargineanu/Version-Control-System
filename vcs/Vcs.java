package vcs;

import filesystem.FileSystemOperation;
import filesystem.FileSystemSnapshot;
import utils.AbstractOperation;
import utils.OutputWriter;
import utils.Visitor;

import java.util.ArrayList;

public final class Vcs implements Visitor {
    private final OutputWriter outputWriter;
    private FileSystemSnapshot activeSnapshot;
    private ArrayList<AbstractOperation> stagingOperationList;
    private ArrayList<String> statusOperation;
    private ArrayList<Branch> branchHead;
    static Branch nameHead;

    /**
     * Vcs constructor.
     *
     * @param outputWriter the output writer
     */
    public Vcs(OutputWriter outputWriter) {
        this.outputWriter = outputWriter;
    }

    /**
     * Does initialisations.
     */
    public void init() {
        this.activeSnapshot = new FileSystemSnapshot(outputWriter);

        //TODO other initialisations

        // Cream o lista in care tinem modificarile care sunt in staging
        this.stagingOperationList = new ArrayList<AbstractOperation>();
        // Pastram numele operatiilor care sunt in staging
        this.statusOperation = new ArrayList<String>();
        // Cream o lista cu branch-uri pentru prelucrare
        this.branchHead = new ArrayList<Branch>();
        // Pastram pointerul HEAD pe branch-ul curent
        Vcs.nameHead = new Branch("master");
        // Adaugam primul branch in lista
        this.branchHead.add(nameHead);
    }

    /**
     * Visits a file system operation.
     *
     * @param fileSystemOperation the file system operation
     * @return the return code
     */
    public int visit(FileSystemOperation fileSystemOperation) {
        return fileSystemOperation.execute(this.activeSnapshot);
    }

    /**
     * Visits a vcs operation.
     *
     * @param vcsOperation the vcs operation
     * @return return code
     */
    @Override
    public int visit(VcsOperation vcsOperation) {
        //TODO
       return vcsOperation.execute(this);

    }

    //TODO methods through which vcs operations interact with this

    public ArrayList<Branch> getBranchHeadList() {
        return this.branchHead;
    }

    public FileSystemSnapshot getActiveSnapshot() {
        return this.activeSnapshot;
    }

    public void addStagingOperationList(AbstractOperation operation) {
        this.stagingOperationList.add(operation);
    }

    public ArrayList<AbstractOperation> getStagingOperationList() {
        return this.stagingOperationList;
    }

    public void emptyStagingOperationList() {
        this.stagingOperationList.clear();
    }
    // Parcurgem lista cu staging si obtinem numele operatiilor track-uite
    public ArrayList<String> statusCommand() {
        for(AbstractOperation e : this.stagingOperationList) {
            switch (e.getType()) {
                case CHANGEDIR:
                    this.statusOperation.add("\tChanged directory to "
                            + e.getOperationArgs().get(0) + "\n");
                    break;
                case MAKEDIR:
                    this.statusOperation.add("\tCreated directory "
                            + e.getOperationArgs().get(0) + "\n");
                    break;
                case REMOVE:
                    this.statusOperation.add("\tRemoved "
                            + e.getOperationArgs().get(0) + "\n");
                    break;
                case TOUCH:
                    this.statusOperation.add("\tCreated file "
                            + e.getOperationArgs().get(1) + "\n");
                    break;
                case WRITETOFILE:
                    this.statusOperation.add("\tAdded \"" + e.getOperationArgs().get(1)
                            + "\" to file " + e.getOperationArgs().get(0) + "\n");
                    break;
                default:
                    break;
            }
        }
        return statusOperation;
    }

    public OutputWriter getOutputWriter() {
        return outputWriter;
    }

    public void changeActiveSnapshot(FileSystemSnapshot currActiveSnapshot) {
        this.activeSnapshot = currActiveSnapshot;
    }

}
