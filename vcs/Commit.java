package vcs;

import filesystem.FileSystemSnapshot;
import utils.IDGenerator;

import java.util.ArrayList;

public class Commit {

    private int commitId;
    // Pastram o clona a filesystem-ului curent
    private FileSystemSnapshot commitEntity;
    // Operatia aplicata de commit
    private String stringCommit;

    // Constructor pentru commit-urile create dupa primul
    public Commit(FileSystemSnapshot commitEntity, ArrayList<String> operationArgs) {

        this.commitId = IDGenerator.generateCommitID();
        this.commitEntity = commitEntity;
        stringCommit = "";

        for (int i = 2; i < operationArgs.size(); i++) {

            stringCommit = stringCommit + " " + operationArgs.get(i);
        }

    }
    // Constructor pentru primul commit
    public Commit(FileSystemSnapshot commitEntity) {
        this.commitId = IDGenerator.generateCommitID();
        this.commitEntity = commitEntity;
        stringCommit = " First commit";
    }


    public int getCommitId() {
        return commitId;
    }

    public String getStringCommit() {
        return stringCommit;
    }

    public FileSystemSnapshot getCommitEntity() {
        return commitEntity;
    }
}
