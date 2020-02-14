package vcs;

import java.util.ArrayList;

public class Branch {
    // Camp cu numele branchului
    private String branchName;
    // Fiecare branch contine o lista de commit-uri
    private ArrayList<Commit> commitEntityList;

    public Branch(String branchName) {
        this.branchName = branchName;
        this.commitEntityList = new ArrayList<>();
    }

    public ArrayList<Commit> getCommitEntityList() {
        return commitEntityList;
    }

    public String getBranchName() {
        return branchName;
    }
}
