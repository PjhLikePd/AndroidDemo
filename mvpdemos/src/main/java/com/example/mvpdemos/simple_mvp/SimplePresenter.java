package com.example.mvpdemos.simple_mvp;

public class SimplePresenter {
    private SimpleModel simpleModel;

    private SimpleActivity simpleActivity;

    public SimplePresenter(SimpleActivity simpleActivity) {
        this.simpleActivity = simpleActivity;
    }

    public void loadData() {
        simpleModel = new SimpleModel(userName -> simpleActivity.showUserName(userName));

        simpleModel.loadModel();
    }
}
